package com.sas.webservice.nameCheck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jws.WebService;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.dao.nc.IFtpDownloadNamecheckCtrlDao;
import com.sas.db.wlf.orm.nc.FtpDownloadNamecheckCtrl;
import com.sas.multipleNC.bean.NCOnlineErrMsgBean;
import com.sas.util.AmlConfiguration;
import com.sas.util.Base64Util;
import com.sas.util.EncodingDetect;
import com.sas.util.FTPUtil;
import com.sas.util.FileUtils;
import com.sas.util.TripleDesSecretUtils;
import com.sas.webservice.nameCheck.bean.AmlFtpExRemittanceNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.AmlFtpExRemittanceNameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.AmlFtpRemittanceNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.AmlFtpRemittanceNameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.FtpExRemittanceNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.FtpRemittanceNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.FtpRemittanceNameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.FtpRemittanceNameCheckInputEndBean;
import com.sas.webservice.nameCheck.bean.FtpRemittanceNameCheckInputHeadBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputDetail;

/**
 * AML Ftp Ex Remittance Name Check 國外匯款主程式
 * 
 * @author Danniel
 *
 */
@Component
@WebService(endpointInterface = "com.sas.webservice.nameCheck.AmlFtpExRemittanceNameCheck")
public class AmlFtpExRemittanceNameCheckImpl implements AmlFtpExRemittanceNameCheck {
	private static final Logger logger = LoggerFactory.getLogger(AmlFtpExRemittanceNameCheckImpl.class);

	@Autowired
	AmlNameCheck amlNameCheck;

	@Autowired
	IFtpDownloadNamecheckCtrlDao iFtpDownloadNamecheckCtrlDao;

	private static int ColArray[];
	private static int ColLength = 622;

	static {
		String[] strColStrings = SwiftMtConst.BOT_NC2_FTP_EXREMMITTANCE_LENGTH.split(",");
		// Splits each spaced integer into a String array.
		ColArray = new int[strColStrings.length];
		// Creates the integer array.
		for (int i = 0; i < strColStrings.length; i++) {
			ColArray[i] = Integer.parseInt(strColStrings[i]);
			ColLength += Integer.parseInt(strColStrings[i]);
		}
		ColLength = 622;
	}

	// @Scheduled(fixedDelay = 60000) // For 開發，每1分鐘，從上一個任務開始到下一個任務開始的間隔
	// @Scheduled(cron = "0 */5 * * * *") //每5分鐘(0, 5, 10, 15 ...)執行，參考 Cron 表達式
	// @Scheduled(fixedRate = 300000) //每隔5分鐘，從上一個任務完成開始到下一個任務開始的間隔，單位毫秒
	// @Scheduled(fixedDelay = 300000) //每隔5分鐘，從上一個任務開始到下一個任務開始的間隔，單位毫秒
	public void FtpExRemittanceNameCheck() {
		try {
			doExRemittanceNameCheck();
		} catch (Exception e) {
			logger.error(String.format("FtpNameCheck fail, exception : %s", e.getMessage()), e);
		}
	}

	@Override
	public AmlFtpExRemittanceNameCheckOutputBean doFtpExRemittanceNameCheck(
			AmlFtpExRemittanceNameCheckInputBean input) {
		Boolean trigger = input.isTrigger();

		AmlFtpExRemittanceNameCheckOutputBean output = new AmlFtpExRemittanceNameCheckOutputBean();

		try {
			output.setServerName(System.getenv("COMPUTERNAME"));

			if (trigger == null || trigger == false) {
				output.setFinish(false);
				return output;
			}

			doExRemittanceNameCheck();
			logger.info("------------------ WebService DoRemittanceNameCheck Finished ----------------------------");
		} catch (Exception e) {
			logger.error(String.format("RemittanceNameCheck fail, exception : %s", e.getMessage()), e);
			output.setFinish(false);
		} finally {
			logger.info(output.toString());
		}

		return output;
	}

	// @Scheduled(cron = "0 0 */6 * * *") // 每6小時(0, 6, 12 ...)執行，參考 Cron 表達式
	public void removeSucceedFiles() {
		final String successDir = AmlConfiguration.getString("com.sas.ftp.remittance.localResultOkURI");
		FileUtils.deleteFilesOlderThanNdays(90, successDir);
	}

	// @Scheduled(cron = "0 0 */6 * * *") // 每6小時(0, 6, 12 ...)執行，參考 Cron 表達式
	public void removeFailFiles() {
		final String failDir = AmlConfiguration.getString("com.sas.ftp.remittance.localResultNgURI");
		FileUtils.deleteFilesOlderThanNdays(180, failDir);
	}

	/**
	 * FTP remittance name check FTP下載，批次處理名單掃描程式入口
	 */
	public Map<String, String> doExRemittanceNameCheck() {
		/* 時間測試 */
		Map<String, String> successResult = new LinkedHashMap<String, String>();
		Map<String, String> failResult = new LinkedHashMap<String, String>();
		final Map<String, String> downloadFiles = downloadFolderFromFtp();
		/* 測試 Local 時使用 */
		// Map<String, String> downloadFiles = new LinkedHashMap<String, String>();
		// downloadFiles.put("NC1_BOT983RT0702071102_20180207.h",
		// "NC1_BOT983RT0702071102_20180207.d");
		// downloadFiles.put("NC1_BOT983RT0701301102_20180130.H",
		// "NC1_BOT983RT0701301102_20180130.D");
		// downloadFiles.put("NC1_BOT983RT0701302501_20180130.H",
		// "NC1_BOT983RT0701302501_20180130.D");
		// downloadFiles.put("NC1_BOT983RT0701302502_20180130.H",
		// "NC1_BOT983RT0701302502_20180130.D");
		// downloadFiles.put("NC1_BOT983RT0701311101_20180131.h",
		// "NC1_BOT983RT0701311101_20180131.d");
		// downloadFiles.put("NC1_BOT983RT0701311102_20180131.h",
		// "NC1_BOT983RT0701311102_20180131.d");
		// downloadFiles.put("NC1_BOT983RT0701312502_20180131.h",
		// "NC1_BOT983RT0701312502_20180131.d");
		// downloadFiles.put("NC1_BOT983RT0702012502_20180201.h",
		// "NC1_BOT983RT0702012502_20180201.d");
		/* 測試 Local 時使用 */

		final List<FtpDownloadNamecheckCtrl> ftpDownloadNCResult = new ArrayList<FtpDownloadNamecheckCtrl>();

		if (CollectionUtils.isEmpty(downloadFiles)) {
			logger.info("downloadFiles empty");
			// 沒有下載，就不用 Name Check
			return successResult;
		}

		String localURI = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_EXREMITTANCE_LOCALURI);
		String remotePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_EXREMITTANCE_REMOTERESULTURI);

		for (Entry<String, String> e : downloadFiles.entrySet()) {
			FtpDownloadNamecheckCtrl ftpDownloadCtrl = null;
			String ctrlFileName = e.getKey(); // 控制檔只是用來確定可下載資料檔，沒有其它用途 格式為：NC_<CallingSystem>_<file name>_<yyyyMMdd>.h
			String dataFileName = e.getValue(); // 格式為：NC1_<CallingSystem>_<file name>_<yyyyMMdd>.d
			String dataFileCharset = SwiftMtConst.NC_FTP_FILE_CHARSET;

			try {
				ftpDownloadCtrl = new FtpDownloadNamecheckCtrl();
				List<NameCheckOutputBean> nameCheckOutputBeanList = new ArrayList<NameCheckOutputBean>();
				String primeFileName = StringUtils.removeStartIgnoreCase(dataFileName, "NC2_");
				primeFileName = StringUtils.removeEndIgnoreCase(primeFileName, ".d"); // 取出名字中間部份：<CallingSystem>_<file
																						// name>_<yyyyMMdd>
				/* 測試 Local 時使用 */
				// File dataFile = new File("E:\\SAS\\Doc\\BOT\\AMLFTP\\NC2\\", dataFileName);
				/* 測試 Local 時使用 */
				File dataFile = new File(localURI, dataFileName);
				String newFileName = "NC2RESULT_" + primeFileName; // NCRESULT_<CallingSystem>_<file name>_<yyyyMMdd>
				ftpDownloadCtrl.setDownloadFile(dataFileName);
				ftpDownloadCtrl.setDownloadTime(new Timestamp(dataFile.lastModified()));
				ftpDownloadCtrl.setUploadPath(remotePath);
				ftpDownloadCtrl.setUploadFile(newFileName + ".d");

				// 取得FTPFile字元集格式
				try {
					boolean isAcceptCharset = false;
					dataFileCharset = EncodingDetect.getJavaEncode(dataFile);
					String[] charsets = AmlConfiguration.getString(SwiftMtConst.COM_SAS_CHARSET_DETECHED).split(",");
					isAcceptCharset = Arrays.asList(charsets).contains(dataFileCharset);
					if(dataFileCharset == null || "".equals(dataFileCharset) || isAcceptCharset == false) {
						logger.error(dataFileName +" - File charset Illegal can't detect! dataFileCharset :: " + dataFileCharset);
					}
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}


				List<NameCheckInputBean> nameCheckInputBeanList = FtpExRemittanceNameCheck(dataFile, dataFileCharset,
						null, null, "106870");
				logger.info("FtpExRemittanceNameCheck nameCheckInputBeanList:::" + nameCheckInputBeanList.toString());
				for (NameCheckInputBean inputBean : nameCheckInputBeanList) {
					NameCheckOutputBean outputBean = new NameCheckOutputBean();
					try {
						outputBean = amlNameCheck.NameCheck(inputBean);
						if (outputBean != null) {
							nameCheckOutputBeanList.add(outputBean);
							ftpDownloadCtrl.setScanStatus(outputBean.getErrorCode());
							logger.info("NameCheck OK ErrorCode:::" + outputBean.getErrorCode());
						}
					} catch (Exception ex) {
						logger.info("NameCheck error:::" + ex.getMessage());
						logger.error("NameCheck Error {}", ex.getMessage());
						List<NameCheckOutputDetail> detailList = new ArrayList<>();
						for (NameCheckInputDetailBean errorSeq : inputBean.getSeq()) {
							NameCheckOutputDetail detail = new NameCheckOutputDetail();
							detail.setCheckSeq(errorSeq.getCheckSeq());
						}
						outputBean.setSeq(detailList);
						outputBean.setUniqueKey(inputBean.getUniqueKey());
						outputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SYSTEMERR);
						outputBean.setErrorMessage(ex.getMessage());
						nameCheckOutputBeanList.add(outputBean);
						ftpDownloadCtrl.setScanStatus(outputBean.getErrorCode());
						failResult.put(ctrlFileName, dataFileName);
					}
				}
				logger.info("FtpExRemittanceNameCheck nameCheckOutputBeanList:::" + nameCheckOutputBeanList.toString());
				if (CollectionUtils.isEmpty(nameCheckOutputBeanList) == false) {
					// return 格式為：NCRESULT_<CallingSystem>_<file name>_<yyyyMMdd>.h,
					// NCRESULT_<CallingSystem>_<file name>_<yyyyMMdd>.d
					Pair<String, String> files = writeFile(newFileName, nameCheckInputBeanList, nameCheckOutputBeanList,
							dataFileCharset);
					successResult.put(ctrlFileName, dataFileName);
					if (uploadFileToServer(files) == false) {
						failResult.put(ctrlFileName, dataFileName);
					}
				} else {
					failResult.put(ctrlFileName, dataFileName);
				}
			} catch (Exception e1) {
				logger.info("FTP Ex Remittance Name Check Error");
				logger.error("doFtpNameCheck Exception : {}", e1);
				failResult.put(ctrlFileName, dataFileName);
			} finally {
				if (ftpDownloadCtrl != null && ftpDownloadCtrl.getDownloadTime() != null) {
					ftpDownloadNCResult.add(ftpDownloadCtrl);
				}
			}
		}
		if (CollectionUtils.isEmpty(ftpDownloadNCResult) == false) {
			iFtpDownloadNamecheckCtrlDao.batchSave(ftpDownloadNCResult);
		}

		final String failPath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_EXREMITTANCE_LOCALRESULTNGURI);
		for (Entry<String, String> e : failResult.entrySet()) {
			File ctrlFile = new File(localURI, e.getKey());
			if (ctrlFile.exists()) {
				File newFile = new File(failPath, e.getKey());
				ctrlFile.renameTo(newFile);
			}

			File dataFile = new File(localURI, e.getValue());
			if (dataFile.exists()) {
				File newFile = new File(failPath, e.getValue());
				dataFile.renameTo(newFile);
			}
		}

		final String successPath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_EXREMITTANCE_LOCALRESULTOKURI);
		for (Entry<String, String> e : successResult.entrySet()) {
			File ctrlFile = new File(localURI, e.getKey());
			if (ctrlFile.exists()) {
				File newFile = new File(successPath, e.getKey());
				ctrlFile.renameTo(newFile);
			}

			File dataFile = new File(localURI, e.getValue());
			if (dataFile.exists()) {
				File newFile = new File(successPath, e.getValue());
				dataFile.renameTo(newFile);
			}
		}
		logger.info("Ftp ExRemittanceNameCheck Name Check Finished ::: " + successResult.toString());
		return successResult;
	}

	/**
	 * 
	 * @param fileName
	 * @param nameCheckOutputBeanList
	 * @return 格式為：NCRESULT_&lt;CallingSystem&gt;_&lt;file
	 *         name&gt;_&lt;yyyyMMdd&gt;.h, NCRESULT_&lt;CallingSystem&gt;_&lt;file
	 *         name&gt;_&lt;yyyyMMdd&gt;.d
	 * @throws IOException
	 */
	private Pair<String, String> writeFile(String fileName, List<NameCheckInputBean> nameCheckInputBeanList,
			List<NameCheckOutputBean> nameCheckOutputBeanList, String outputFileCharset) throws IOException {
		BufferedWriter writerH = null;
		BufferedWriter writerD = null;
		Pair<String, String> result = null;
		try {
			// final String sourcePath = "E:\\SAS\\Doc\\BOT\\AMLFTP\\NC1\\result";
			final String sourcePath = AmlConfiguration.getString("com.sas.ftp.exremittance.localResultURI");
			final File resultFileH = new File(sourcePath, String.format("%s.h", fileName));
			final File resultFileD = new File(sourcePath, String.format("%s.d", fileName));
			final boolean append = false;

			// writerH = new BufferedWriter(new OutputStreamWriter(new
			// FileOutputStream(resultFileH, append), SwiftMtConst.NC_FTP_FILE_CHARSET));
			writerH = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(resultFileH, append), outputFileCharset));
			writerH.write(String.valueOf(nameCheckOutputBeanList.size()));
			writerH.flush();

			writerD = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(resultFileD, append), outputFileCharset));
			final StringBuffer bfr = new StringBuffer();
			final StringBuffer detailBfr = new StringBuffer();
			final List<String> lines = new ArrayList<String>();
			for (NameCheckOutputBean bean : nameCheckOutputBeanList) {
				List<NameCheckOutputDetail> detailList = bean.getSeq();
				String unikey = bean.getUniqueKey();
				if (detailList != null && !detailList.isEmpty()) {
					for (int i = 0; i < detailList.size(); i++) {
						bfr.setLength(0);
						bfr.append(bean.getNcReferenceId() != null ? StringUtils.rightPad(bean.getNcReferenceId(), 32)
								: StringUtils.rightPad(" ", 32)).append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						bfr.append(StringUtils.rightPad(bean.getUniqueKey(), 128))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						bfr.append(StringUtils.rightPad(bean.getErrorCode(), 1))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						bfr.append(StringUtils.rightPad(bean.getErrorMessage(), 255))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						bfr.append(bean.getNcResult() != null ? StringUtils.rightPad(bean.getNcResult(), 2)
								: StringUtils.rightPad(" ", 2)).append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						bfr.append(bean.getRouteRule() != null ? StringUtils.rightPad(bean.getRouteRule(), 2)
								: StringUtils.rightPad(" ", 2)).append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						if (bean.getHitSeq() == null) {
							bfr.append(StringUtils.rightPad(" ", 5)).append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						} else if (bean.getHitSeq().length() > 5) {
							bfr.append(StringUtils.rightPad(bean.getHitSeq().split(",")[0], 5))
									.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						} else {
							bfr.append(StringUtils.rightPad(bean.getHitSeq(), 5))
									.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						}
						writerD.write(bfr.toString());
						detailBfr.setLength(0);
						detailBfr.append(StringUtils.leftPad(detailList.get(i).getCheckSeq(), 5))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						detailBfr.append(StringUtils.rightPad(detailList.get(i).getCheckResult(), 2))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						detailBfr.append(StringUtils.rightPad(detailList.get(i).getRouteRule(), 32));
						writerD.write(detailBfr.toString());
						writerD.newLine();
						lines.add(bfr.toString());
					}
				} else {
					for (NameCheckInputBean nameCheckInputBean : nameCheckInputBeanList) {
						if (unikey != null && nameCheckInputBean.getUniqueKey().equals(unikey)) {
							for (int j = 0; j < nameCheckInputBean.getSeq().size(); j++) {
								bfr.setLength(0);
								bfr.append(bean.getNcReferenceId() != null
										? StringUtils.rightPad(bean.getNcReferenceId(), 32)
										: StringUtils.rightPad(" ", 32)).append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								bfr.append(StringUtils.rightPad(bean.getUniqueKey(), 128))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								bfr.append(StringUtils.rightPad(bean.getErrorCode(), 1))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								bfr.append(StringUtils.rightPad(bean.getErrorMessage(), 255))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								bfr.append(bean.getNcResult() != null ? StringUtils.rightPad(bean.getNcResult(), 2)
										: StringUtils.rightPad(" ", 2)).append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								bfr.append(bean.getRouteRule() != null ? StringUtils.rightPad(bean.getRouteRule(), 2)
										: StringUtils.rightPad(" ", 2)).append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								if (bean.getHitSeq() == null) {
									bfr.append(StringUtils.rightPad(" ", 5)).append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								} else if (bean.getHitSeq().length() > 5) {
									bfr.append(StringUtils.rightPad(bean.getHitSeq().split(",")[0], 5))
											.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								} else {
									bfr.append(StringUtils.rightPad(bean.getHitSeq(), 5))
											.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								}
								writerD.write(bfr.toString());
								detailBfr.setLength(0);
								detailBfr.append(
										StringUtils.leftPad(nameCheckInputBean.getSeq().get(j).getCheckSeq(), 5))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								detailBfr.append(StringUtils.rightPad(" ", 2)).append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								detailBfr.append(StringUtils.rightPad(" ", 32));
								writerD.write(detailBfr.toString());
								writerD.newLine();
								lines.add(bfr.toString());
							}
						}
					}
				}
			}
			writerD.flush();
			result = Pair.of(resultFileH.getName(), resultFileD.getName());
		} finally {
			IOUtils.closeQuietly(writerH);
			IOUtils.closeQuietly(writerD);
		}

		return result;
	}

	/**
	 * 將檔案回傳FTP
	 */
	private boolean uploadFileToServer(Pair<String, String> uploadFiles) {
		boolean result = false;
		FTPUtil ftpUtil = null;
		try {
			String ftpServer = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_SERVER);
			int ftpPort = Integer.valueOf(AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_PORT));
			String ftpUser = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_USER);
			String ftpPwd = Base64Util.decodeFromString(AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_PASSWORD));
			ftpUtil = new FTPUtil(ftpServer, ftpPort, ftpUser, ftpPwd);
			String sourcePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_LOCALRESULTURI);
			if (sourcePath.endsWith("/")) {
				sourcePath = StringUtils.removeEnd(sourcePath, "/");
			}
			String remotePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_REMOTERESULTURI);
			if (remotePath.endsWith("/")) {
				remotePath = StringUtils.removeEnd(remotePath, "/");
			}

			ftpUtil.uploadFileToServer(sourcePath, uploadFiles.getSecond(), remotePath, uploadFiles.getSecond()); // 先放資料檔
			ftpUtil.uploadFileToServer(sourcePath, uploadFiles.getFirst(), remotePath, uploadFiles.getFirst()); // 再放控制檔

			result = true;
		} catch (Exception e) {
			logger.info("uploadFileToServer Exception :::" + e.getMessage());
			logger.error("uploadFileToServer Exception : {}", e);
		} finally {
			IOUtils.closeQuietly(ftpUtil);
		}

		return result;
	}

	/**
	 * 將檔案回傳FTP
	 */
	private boolean uploadFilesToServer(Map<String, String> uploadFiles) {
		boolean result = false;
		FTPUtil ftpUtil = null;
		try {
			String ftpServer = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_SERVER);
			int ftpPort = Integer.valueOf(AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_PORT));
			String ftpUser = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_USER);
			String ftpPwd = Base64Util.decodeFromString(AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_PASSWORD));

			ftpUtil = new FTPUtil(ftpServer, ftpPort, ftpUser, ftpPwd);
			String sourcePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_LOCALRESULTURI);
			if (sourcePath.endsWith("/")) {
				sourcePath = StringUtils.removeEnd(sourcePath, "/");
			}
			String remotePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_REMOTERESULTURI);
			if (remotePath.endsWith("/")) {
				remotePath = StringUtils.removeEnd(remotePath, "/");
			}
			File[] fileList = new File(sourcePath).listFiles();
			for (File file : fileList) {
				ftpUtil.uploadFileToServer(sourcePath, file.getName(), remotePath, file.getName());
			}

			for (Entry<String, String> e : uploadFiles.entrySet()) {
				ftpUtil.uploadFileToServer(sourcePath, e.getValue(), remotePath, e.getValue()); // 先放資料檔
				ftpUtil.uploadFileToServer(sourcePath, e.getKey(), remotePath, e.getKey()); // 再放控制檔
			}

			result = true;
		} catch (Exception e) {
			logger.info("uploadFileToServer Exception :::" + e.getMessage());
			logger.error("uploadFileToServer Exception : {}", e);
		} finally {
			IOUtils.closeQuietly(ftpUtil);
		}

		return result;
	}

	/**
	 * 從FTP下載整批檔案
	 */
	private Map<String, String> downloadFolderFromFtp() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		FTPUtil ftpUtil = null;
		try {
			String ftpServer = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_SERVER);
			int ftpPort = Integer.valueOf(AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_PORT));
			String ftpUser = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_USER);
			String ftpPwd = Base64Util.decodeFromString(AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_NC_PASSWORD));
			String remoteURI = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_EXREMITTANCE_REMOTEURI);
			if (remoteURI.endsWith("/")) {
				remoteURI = StringUtils.removeEnd(remoteURI, "/");
			}
			String localURI = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_EXREMITTANCE_LOCALURI);
			if (localURI.endsWith("/")) {
				localURI = StringUtils.removeEnd(localURI, "/");
			}
			ftpUtil = new FTPUtil(ftpServer, ftpPort, ftpUser, ftpPwd);
			final FTPFile[] ftpFiles = ftpUtil.getRemoteFileList(remoteURI); // 先取得全部的檔案列表
			final Map<String, FTPFile> fileMap = new LinkedHashMap<String, FTPFile>(); // fileName:FTPFile
			for (FTPFile f : ftpFiles) { // 過濾只留 NC1_ 開頭的檔案
				if (f.isFile() && StringUtils.startsWithIgnoreCase(f.getName(), "NC2_")
						&& (StringUtils.endsWithIgnoreCase(f.getName(), ".h")
								|| StringUtils.endsWithIgnoreCase(f.getName(), ".d"))) { // 20180126增加只搜尋.h .H及.d .D條件判斷
					fileMap.put(f.getName(), f);
				}
			}

			final String[] dataFileNameTails = { ".d", ".D" }; // 比對資料檔，檔名結尾
			for (String ctrlFileName : fileMap.keySet()) {
				// 控制檔是用來確定資料檔是否輸出完畢，不管檔案裡面的內容
				// 找到控制檔時，看資料檔是否存在
				if (StringUtils.endsWithIgnoreCase(ctrlFileName, ".h") == false)
					continue;

				final String fileName = StringUtils.removeEndIgnoreCase(ctrlFileName, ".h");
				String dataFileName = null;
				boolean hasDataFile = false;
				for (String tail : dataFileNameTails) { // 大小寫各找一次
					dataFileName = fileName + tail;
					if (fileMap.containsKey(dataFileName)) { // 資料檔是否存在
						hasDataFile = true;
						break;
					}
				}

				// 沒有資料檔，找下一個控制檔
				if (hasDataFile == false)
					continue;

				// 有資料檔，則下載控制檔、資料檔
				final String remoteCrtlFile = remoteURI + "/" + ctrlFileName;
				logger.info("remoteCrtlFile::::" + remoteCrtlFile);
				ftpUtil.getRemoteFile(remoteCrtlFile, localURI + "/" + ctrlFileName);
				final String remoteDataFile = remoteURI + "/" + dataFileName;
				logger.info("remoteDataFile::::" + remoteDataFile);
				ftpUtil.getRemoteFile(remoteDataFile, localURI + "/" + dataFileName);

				// 下載完後，刪除
				ftpUtil.deleteRemoteFile(remoteCrtlFile);
				ftpUtil.deleteRemoteFile(remoteDataFile);

				// 填寫傳回的檔名
				result.put(ctrlFileName, dataFileName);
			}
		} catch (Exception e) {
			logger.error("downloadFolderFromFtp Exception : {}", e);
		} finally {
			IOUtils.closeQuietly(ftpUtil);
		}

		return result;
	}

	public static String removeExtension(String fileName) {
		if (fileName.indexOf(".") > 0) {
			return fileName.substring(0, fileName.lastIndexOf("."));
		} else {
			return fileName;
		}
	}

	/**
	 * 整理整批資料
	 * 
	 * @param file
	 * @return
	 */
	public List<NameCheckInputBean> FtpExRemittanceNameCheck(File file, String dataFileCharset,
			NCOnlineErrMsgBean errMsg, String branchNumber, String callingUser) {
		List<NameCheckInputBean> resultList = new ArrayList<NameCheckInputBean>();
		List<FtpExRemittanceNameCheckInputBean> detailsBean = new ArrayList<>();
		BufferedReader br = null;
		String unikey = "";
		int effCount = 0;
		try {
			if (errMsg != null) {
				if (!file.isFile()) {
					errMsg.setFileFormatError(true);
					errMsg.getErrorMsgStringList().add("File Format Error!");
					errMsg.setValidateError(true);
				} else {
					Date now = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//					unikey = FilenameUtils.removeExtension(file.getName()) + "-" + sdf.format(now) + "-";
					unikey = removeExtension(file.getName()) + "-" + sdf.format(now) + "-";
				}
			}
			FileInputStream fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis, dataFileCharset));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (StringUtils.isBlank(line)) {
					continue;
				} else {
					byte[] bytes = line.getBytes(dataFileCharset);
					logger.info("Row Length ::::" + line.length());
					String strHead = new String(subbytes(bytes, 0, 4)).trim();
					FtpExRemittanceNameCheckInputBean inputBean = new FtpExRemittanceNameCheckInputBean();
					if (StringUtils.isNumeric(strHead)) {
						if (errMsg != null) {
							if (line.length() != ColLength) {
								errMsg.setFileFormatError(true);
								errMsg.getErrorMsgStringList().add("File Format Error!");
								errMsg.setValidateError(true);
							}
						}
						inputBean = lineToBean(ColArray, line, dataFileCharset);
						detailsBean.add(inputBean);
					} else {
						continue;
					}
				}
			}
			if (detailsBean != null) {
				Map<String, NameCheckInputBean> inputBeanMap = ftpBeanListToInputBeanMap(detailsBean, unikey,
						branchNumber, callingUser);
				for (Map.Entry<String, NameCheckInputBean> entry : inputBeanMap.entrySet()) {
					resultList.add(entry.getValue());
					if (errMsg != null) {
						errMsg.setEffCount(effCount++);
					}
					logger.debug(entry.getValue().toString());
				}
			}
		} catch (FileNotFoundException e) {
			logger.info("FtpNameCheck FileNotFoundException : " + e.getMessage());
			logger.error("FtpNameCheck FileNotFoundException : {}", e);
			errMsg.setValidateError(true);
		} catch (IOException e) {
			logger.info("FtpNameCheck IOException : " + e.getMessage());
			logger.error("FtpNameCheck IOException : {}", e);
			errMsg.setValidateError(true);
		} finally {
			IOUtils.closeQuietly(br);
		}
		return resultList;
	}

	/**
	 * FtpNameCheckInputBean to
	 * 
	 * @param ftpBeanList
	 * @return
	 */
	private Map<String, NameCheckInputBean> ftpBeanListToInputBeanMap(
			List<FtpExRemittanceNameCheckInputBean> ftpInputBean, String unikey, String branchNumber, String callingUser) {
		Map<String, NameCheckInputBean> ncBeanMap = new LinkedHashMap<String, NameCheckInputBean>();
		/* 轉換 */
		for (FtpExRemittanceNameCheckInputBean ftpExRemittanceNameCheckInputBean : ftpInputBean) {
			String uniqueKey = unikey + ftpExRemittanceNameCheckInputBean.getOrderNo();
			if (ncBeanMap.containsKey(uniqueKey)) {
				NameCheckInputBean input = ncBeanMap.get(uniqueKey);
				List<NameCheckInputDetailBean> detailSeq = MapFtpBeanToNCInputDetailBean(
						ftpExRemittanceNameCheckInputBean);
				input.getSeq().addAll(detailSeq);
			} else {
				NameCheckInputBean input = MapFtpBeanToNCInputBean(ftpExRemittanceNameCheckInputBean, uniqueKey,
						branchNumber, callingUser);
				input.setSeq(MapFtpBeanToNCInputDetailBean(ftpExRemittanceNameCheckInputBean));
				ncBeanMap.put(uniqueKey, input);
			}
		}
		return ncBeanMap;
	}

	private List<NameCheckInputDetailBean> MapFtpBeanToNCInputDetailBean(
			FtpExRemittanceNameCheckInputBean ftpExRemittanceNameCheckInputBean) {
		List<NameCheckInputDetailBean> detailBeanList = new ArrayList<>();
		String country = "", beneficiarySwiftCode = "", beneficiaryBankNo = "", beneficiaryName1 = "",
				beneficiaryName2 = "", customerName1 = "", customerName2 = "", uniformName = "";
		beneficiaryBankNo = com.sas.util.StringUtils
				.convertToHalfWidth(ftpExRemittanceNameCheckInputBean.getBeneficiaryBankNo()).trim();
		beneficiaryName1 = com.sas.util.StringUtils
				.convertToHalfWidth(ftpExRemittanceNameCheckInputBean.getBeneficiaryName1()).trim();
		beneficiaryName2 = com.sas.util.StringUtils
				.convertToHalfWidth(ftpExRemittanceNameCheckInputBean.getBeneficiaryName2()).trim();
		beneficiarySwiftCode = com.sas.util.StringUtils
				.convertToHalfWidth(ftpExRemittanceNameCheckInputBean.getBeneficiarySwiftCode()).trim();
		country = com.sas.util.StringUtils.convertToHalfWidth(ftpExRemittanceNameCheckInputBean.getCountry()).trim();
		customerName1 = com.sas.util.StringUtils
				.convertToHalfWidth(ftpExRemittanceNameCheckInputBean.getCustomerName1()).trim();
		customerName2 = com.sas.util.StringUtils
				.convertToHalfWidth(ftpExRemittanceNameCheckInputBean.getCustomerName2()).trim();

		if (beneficiaryName1 != null && !"".equals(beneficiaryName1)) {
			NameCheckInputDetailBean scanBeneficiaryNameBean = new NameCheckInputDetailBean();
			scanBeneficiaryNameBean.setCheckSeq(SwiftMtConst.BOT_NC2_FTP_CHECKSEQ_DETAIL_BENEFICIARY1);
			scanBeneficiaryNameBean.setEntityType(SwiftMtConst.ENTITY_TYPE_INDANDCORP);
			scanBeneficiaryNameBean.setEntityRelationship("04");
			scanBeneficiaryNameBean.setEntityRelationshipDesc("");
			if (com.sas.util.StringUtils.isEnglish(beneficiaryName1)) {
				scanBeneficiaryNameBean.setEnglishName(beneficiaryName1);
				scanBeneficiaryNameBean.setNonEnglishName("");
			} else {
				scanBeneficiaryNameBean.setEnglishName("");
				scanBeneficiaryNameBean.setNonEnglishName(beneficiaryName1);
			}
			scanBeneficiaryNameBean.setCccCode("");
			scanBeneficiaryNameBean.setIdNumber("");
			scanBeneficiaryNameBean.setBicSwiftCode(beneficiarySwiftCode != null ? beneficiarySwiftCode : "");
			scanBeneficiaryNameBean.setCountry("");
			scanBeneficiaryNameBean.setYearOfBirth("");
			detailBeanList.add(scanBeneficiaryNameBean);
		}
		if (beneficiaryName2 != null && !"".equals(beneficiaryName2)) {
			NameCheckInputDetailBean scanBeneficiaryNameBean = new NameCheckInputDetailBean();
			scanBeneficiaryNameBean.setCheckSeq(SwiftMtConst.BOT_NC2_FTP_CHECKSEQ_DETAIL_BENEFICIARY2);
			scanBeneficiaryNameBean.setEntityType(SwiftMtConst.ENTITY_TYPE_INDANDCORP);
			scanBeneficiaryNameBean.setEntityRelationship("04");
			scanBeneficiaryNameBean.setEntityRelationshipDesc("");
			if (com.sas.util.StringUtils.isEnglish(beneficiaryName2)) {
				scanBeneficiaryNameBean.setEnglishName(beneficiaryName2);
				scanBeneficiaryNameBean.setNonEnglishName("");
			} else {
				scanBeneficiaryNameBean.setEnglishName("");
				scanBeneficiaryNameBean.setNonEnglishName(beneficiaryName2);
			}
			scanBeneficiaryNameBean.setCccCode("");
			scanBeneficiaryNameBean.setIdNumber("");
			scanBeneficiaryNameBean.setBicSwiftCode(beneficiarySwiftCode != null ? beneficiarySwiftCode : "");
			scanBeneficiaryNameBean.setCountry("");
			scanBeneficiaryNameBean.setYearOfBirth("");
			detailBeanList.add(scanBeneficiaryNameBean);
		}
		if (country != null && !"".equals(country)) {
			NameCheckInputDetailBean scanUniformNameBean = new NameCheckInputDetailBean();
			scanUniformNameBean.setCheckSeq(SwiftMtConst.BOT_NC2_FTP_CHECKSEQ_DETAIL_BENEFICIARY_COUNTRY);
			scanUniformNameBean.setEntityType(SwiftMtConst.ENTITY_TYPE_COUNTRY);
			scanUniformNameBean.setEntityRelationship("04");
			scanUniformNameBean.setEntityRelationshipDesc("");
			if (com.sas.util.StringUtils.isEnglish(country)) {
				scanUniformNameBean.setEnglishName("");
				scanUniformNameBean.setNonEnglishName("");
			} else {
				scanUniformNameBean.setEnglishName("");
				scanUniformNameBean.setNonEnglishName("");
			}
			scanUniformNameBean.setCccCode("");
			scanUniformNameBean.setIdNumber("");
			scanUniformNameBean.setBicSwiftCode("");
			scanUniformNameBean.setCountry(country);
			scanUniformNameBean.setYearOfBirth("");
			detailBeanList.add(scanUniformNameBean);
		}

		if (customerName1 != null && !"".equals(customerName1)) {
			NameCheckInputDetailBean scanUniformNameBean = new NameCheckInputDetailBean();
			scanUniformNameBean.setCheckSeq(SwiftMtConst.BOT_NC2_FTP_CHECKSEQ_DETAIL_UNIFORM1);
			scanUniformNameBean.setEntityType(SwiftMtConst.ENTITY_TYPE_INDANDCORP);
			scanUniformNameBean.setEntityRelationship("01");
			scanUniformNameBean.setEntityRelationshipDesc("");
			if (com.sas.util.StringUtils.isEnglish(customerName1)) {
				scanUniformNameBean.setEnglishName(customerName1);
				scanUniformNameBean.setNonEnglishName("");
			} else {
				scanUniformNameBean.setEnglishName("");
				scanUniformNameBean.setNonEnglishName(customerName1);
			}
			scanUniformNameBean.setCccCode("");
			scanUniformNameBean.setIdNumber("");
			scanUniformNameBean.setBicSwiftCode("");
			scanUniformNameBean.setCountry("TW");
			scanUniformNameBean.setYearOfBirth("");
			detailBeanList.add(scanUniformNameBean);
		}
		if (customerName2 != null && !"".equals(customerName2)) {
			NameCheckInputDetailBean scanUniformNameBean = new NameCheckInputDetailBean();
			scanUniformNameBean.setCheckSeq(SwiftMtConst.BOT_NC2_FTP_CHECKSEQ_DETAIL_UNIFORM2);
			scanUniformNameBean.setEntityType(SwiftMtConst.ENTITY_TYPE_INDANDCORP);
			scanUniformNameBean.setEntityRelationship("01");
			scanUniformNameBean.setEntityRelationshipDesc("");
			if (com.sas.util.StringUtils.isEnglish(customerName2)) {
				scanUniformNameBean.setEnglishName(customerName2);
				scanUniformNameBean.setNonEnglishName("");
			} else {
				scanUniformNameBean.setEnglishName("");
				scanUniformNameBean.setNonEnglishName(customerName2);
			}
			scanUniformNameBean.setCccCode("");
			scanUniformNameBean.setIdNumber("");
			scanUniformNameBean.setBicSwiftCode("");
			scanUniformNameBean.setCountry("TW");
			scanUniformNameBean.setYearOfBirth("");
			detailBeanList.add(scanUniformNameBean);
		}
		return detailBeanList;
	}

	private NameCheckInputBean MapFtpBeanToNCInputBean(FtpExRemittanceNameCheckInputBean bean, String unikey,
			String branchNumber, String callingUser) {
		NameCheckInputBean scanBean = new NameCheckInputBean();
		scanBean.setBranchNumber(branchNumber);
		scanBean.setBusinessUnitID(SwiftMtConst.BOT_NC_DEFAULT_BUSINESS_UNIT_ID);
		scanBean.setCallingSystem(SwiftMtConst.NC2_DEFAULT_CALLING_SYSTEM);
		scanBean.setCallingUser(callingUser);
		scanBean.setGenAlertFlag(SwiftMtConst.BOT_NC_DEFAULT_ALERT_FLAG);
		scanBean.setPartyNumber("");
		scanBean.setScreenProcess(SwiftMtConst.NC2_DEFAULT_SCREEN_PROCESS);
		scanBean.setUniqueKey(unikey);
		return scanBean;
	}

	public static void getBytes(byte[] source, int srcBegin, int srcEnd, byte[] destination, int dstBegin) {
		if (source.length < srcEnd) {
			logger.error("File Input Length Error {} ", source.length);
			System.arraycopy(source, srcBegin, destination, dstBegin, source.length - srcBegin);
		} else {
			System.arraycopy(source, srcBegin, destination, dstBegin, srcEnd - srcBegin);
		}
	}

	public static byte[] subbytes(byte[] source, int srcBegin, int srcEnd) {
		byte destination[];
		destination = new byte[srcEnd - srcBegin];
		getBytes(source, srcBegin, srcEnd, destination, 0);
		return destination;
	}

	private List<String> MappingStringUsingIntArray(int[] paraLength, String line, String dataFileCharset)
			throws UnsupportedEncodingException {
		List<String> filedsList = new ArrayList<String>();
		byte[] bytes = line.getBytes(dataFileCharset);
		int beginIndex = 0;
		for (int integer : paraLength) {
			int endIndex = beginIndex + integer;
			String str = new String(subbytes(bytes, beginIndex, endIndex), dataFileCharset).trim();
			beginIndex = endIndex;
			filedsList.add(str);
		}
		return filedsList;
	}

	@SuppressWarnings("unused")
	private FtpExRemittanceNameCheckInputBean lineToBean(int[] paraLength, String line, String dataFileCharset)
			throws UnsupportedEncodingException {
		List<String> filedsList = MappingStringUsingIntArray(paraLength, line, dataFileCharset);
		for (String field : filedsList) {
			field = com.sas.util.StringUtils.convertToHalfWidth(field).trim();
		}
		return new FtpExRemittanceNameCheckInputBean(filedsList);
	}

	public static void main(String[] args) {
		// String fileName =
		// "E:\\SAS\\Doc\\BOT\\AMLFTP\\NC1\\NC1_BOT983RT0701312501_20180131.d";
		String fileName = "E:\\SAS\\Doc\\BOT\\AMLFTP\\NC2\\REMITT_99.prn";
		File sampleFile = new File(fileName);
		String branchNumber = "102";
		AmlFtpExRemittanceNameCheckImpl amlRemittanceNameCheckImpl = new AmlFtpExRemittanceNameCheckImpl();
		amlRemittanceNameCheckImpl.FtpExRemittanceNameCheck(sampleFile, "UTF-8", new NCOnlineErrMsgBean(),
				branchNumber, "106870");
	}

	public static void main1(String[] args) throws IOException {
		AmlFtpExRemittanceNameCheckImpl amlFtpNameCheckImpl = new AmlFtpExRemittanceNameCheckImpl();
		amlFtpNameCheckImpl.doExRemittanceNameCheck();
	}

}
