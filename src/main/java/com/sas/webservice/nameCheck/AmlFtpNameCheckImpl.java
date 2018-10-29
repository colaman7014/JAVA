package com.sas.webservice.nameCheck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
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
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
import com.sas.webservice.nameCheck.bean.AmlFtpNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.AmlFtpNameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.FtpNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputDetail;

/**
 * AML Ftp Name Check 標準格式 主程式
 * 
 * @author Danniel 
 *
 */
@Component
@PropertySource("classpath:global.properties")
@WebService(endpointInterface = "com.sas.webservice.nameCheck.AmlFtpNameCheck")
public class AmlFtpNameCheckImpl implements AmlFtpNameCheck {
	private static final Logger logger = LoggerFactory.getLogger(AmlFtpNameCheckImpl.class);

	@Autowired
	AmlNameCheck amlNameCheck;

	@Autowired
	IFtpDownloadNamecheckCtrlDao iFtpDownloadNamecheckCtrlDao;
	
	@Value(SwiftMtConst.COM_SAS_FTP_SCHUDLE_ENABLE)
	private boolean isJobEnable;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Integer count1 = 1;
	// @Scheduled(fixedDelay = 60000) // For 開發，每1分鐘，從上一個任務開始到下一個任務開始的間隔
	// @Scheduled(cron = "0 */5 * * * *") //每5分鐘(0, 5, 10, 15 ...)執行，參考 Cron 表達式
	// @Scheduled(fixedRate = 300000) //每隔5分鐘，從上一個任務完成開始到下一個任務開始的間隔，單位毫秒
	// @Scheduled(fixedDelay = 300000) //每隔5分鐘，從上一個任務開始到下一個任務開始的間隔，單位毫秒
	@Scheduled(fixedRateString = SwiftMtConst.COM_SAS_FTP_TRIGGER_RATE) 
	public void FtpNameCheckSchdule() {
		if(isJobEnable) {
			try {
				logger.info(String.format("=== DoNameCheck 第 %s次執行，當前時間：%s", count1++, dateFormat.format(new Date())));
				doNameCheck();
			} catch (Exception e) {
				logger.error(String.format("FtpNameCheck fail, exception : %s", e.getMessage()), e);
			}
		}
		else {
			logger.info("============== Standard Ftp NameCheck Job is disabled!");
		}
	}

	@Override
	public AmlFtpNameCheckOutputBean doFtpNameCheck(AmlFtpNameCheckInputBean input) {
		Boolean trigger = input.isTrigger();

		AmlFtpNameCheckOutputBean output = new AmlFtpNameCheckOutputBean();
		try {
			output.setServerName(System.getenv("COMPUTERNAME"));

			if (trigger == null || trigger == false) {
				output.setFinish(false);
				return output;
			}

			doNameCheck();

		} catch (Exception e) {
			logger.error(String.format("FtpNameCheck fail, exception : %s", e.getMessage()), e);
			output.setFinish(false);
		} finally {
			logger.info(output.toString());
		}

		return output;
	}

	@Scheduled(cron = "0 0 18 * * ?") // 每6小時(0, 6, 12 ...)執行，參考 Cron表達式
	public void removeSucceedFiles() {
		 if(isJobEnable) {
			 final String successDir = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_LOCALRESULTOKURI);
			 FileUtils.deleteFilesOlderThanNdays(90, successDir);
		 }
	}

	@Scheduled(cron = "0 0 18 * * ?") // 每6小時(0, 6, 12 ...)執行，參考 Cron表達式
	public void removeFailFiles() {
		 if(isJobEnable) {
			 final String failDir = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_LOCALRESULTNGURI);
			 FileUtils.deleteFilesOlderThanNdays(180, failDir);
		 }
	}

	/**
	 * FTP name check FTP下載，批次處理名單掃描程式入口
	 * 
	 */
	@Transactional
	public Map<String, String> doNameCheck() {
		Map<String, String> successResult = new LinkedHashMap<String, String>();
		Map<String, String> failResult = new LinkedHashMap<String, String>();
		Map<String, String> downloadFiles = new LinkedHashMap<String, String>();
		try {
			downloadFiles = downloadFolderFromFtp();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return successResult;
		}

		/* 測試 Local 時使用 */
//		downloadFiles.put("NC_FCTC2_EasyGo_20180314.h", "NC_FCTC2_EasyGo_20180314.d");
		/* 測試 Local 時使用 */

		final List<FtpDownloadNamecheckCtrl> ftpDownloadNCResult = new ArrayList<FtpDownloadNamecheckCtrl>();

		if (CollectionUtils.isEmpty(downloadFiles)) {
			// 沒有下載，就不用 Name Check
			logger.info("downloadFiles empty");
			return successResult;
		}
		logger.info("downloadFiles: " + downloadFiles.size());

		String localURI = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_LOCALURI);
		String remotePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMOTERESULTURI);

		for (Entry<String, String> e : downloadFiles.entrySet()) {
			FtpDownloadNamecheckCtrl ftpDownloadCtrl = null;
			String ctrlFileName = e.getKey(); // 控制檔只是用來確定可下載資料檔，沒有其它用途 格式為：NCB5_ or NCU8_<CallingSystem>_<file name>_<yyyyMMdd>.h
			String dataFileName = e.getValue(); // 格式為：NCB5_ or NCU8_<CallingSystem>_<file name>_<yyyyMMdd>.d 三四字元用以判斷編碼 B5: BIG5; U8: UTF-8
			String dataFileCharset = SwiftMtConst.NC_FTP_FILE_CHARSET;
			logger.info("dataFileName:" + dataFileName);
			try {
				ftpDownloadCtrl = new FtpDownloadNamecheckCtrl();
				List<NameCheckOutputBean> nameCheckOutputBeanList = new ArrayList<NameCheckOutputBean>();
				String primeFileName = StringUtils.removeStartIgnoreCase(dataFileName, "NCB5_");
				primeFileName = StringUtils.removeStartIgnoreCase(primeFileName, "NCU8_");
				primeFileName = StringUtils.removeEndIgnoreCase(primeFileName, ".d"); // 取出名字中間部份：<CallingSystem>_<file
				boolean isExist = true;																		// name>_<yyyyMMdd>
				File dataFile = new File(localURI, dataFileName);
				if (dataFile.exists()) {
					File newFile = new File(localURI, dataFileName);
					isExist = dataFile.renameTo(newFile);
				} else {
					File newFile = new File(localURI, dataFileName);
					InputStream in = new FileInputStream(dataFile);
					OutputStream out = new FileOutputStream(newFile);
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0){
						out.write(buf, 0, len);
					}
					in.close();
					out.close();
				}
				/* 測試 Local 時使用 */
				//File dataFile = new File("C:\\Users\\User\\Desktop\\", dataFileName);
				/* 測試 Local 時使用 */
				String newFileName = "NCRESULT_" + primeFileName; // NCRESULT_<CallingSystem>_<file name>_<yyyyMMdd>
				ftpDownloadCtrl.setDownloadFile(dataFileName);
				ftpDownloadCtrl.setDownloadTime(new Timestamp(dataFile.lastModified()));
				ftpDownloadCtrl.setUploadPath(remotePath);
				ftpDownloadCtrl.setUploadFile(newFileName + ".d");

				// 取得FTPFile字元集格式
//				try {
//					boolean isAcceptCharset = false;
//					dataFileCharset = EncodingDetect.getJavaEncode(dataFile);
//					String[] charsets = AmlConfiguration.getString(SwiftMtConst.COM_SAS_CHARSET_DETECHED).split(",");
//					isAcceptCharset = Arrays.asList(charsets).contains(dataFileCharset);
//					if(dataFileCharset == null || "".equals(dataFileCharset) || isAcceptCharset == false) {
//						logger.error(dataFileName +" - File charset Illegal can't detect! dataFileCharset :: " + dataFileCharset);
//					}
//				} catch (Exception ex) {
//					logger.error(ex.getMessage(), ex);
//				}
				
				if (StringUtils.startsWith(dataFileName, "NCB5_"))
					dataFileCharset = "BIG5";
				else if (StringUtils.startsWith(dataFileName, "NCU8_"))
					dataFileCharset = "UTF-8";

				// 將檔案Mapping為名單掃描nameCheckInputBeanList
				List<NameCheckInputBean> nameCheckInputBeanList = FtpNameCheck(dataFile, null, dataFileCharset);
				logger.info("nameCheckInputBeanList:::" + nameCheckInputBeanList.toString());
				for (NameCheckInputBean inputBean : nameCheckInputBeanList) {
					logger.info("inputBean in:::" + inputBean.toString());
					// NameCheck(inputBean) 進行名單掃描
					NameCheckOutputBean outputBean = new NameCheckOutputBean();
					try {
						outputBean = amlNameCheck.NameCheck(inputBean);
						// outputBean 取得名單掃描結果
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
				logger.info("nameCheckInputBeanList: " + nameCheckInputBeanList.size());

				if (CollectionUtils.isEmpty(nameCheckOutputBeanList) == false) {
					logger.info("WriteFile begin");
					// return 格式為：NCRESULT_<CallingSystem>_<file name>_<yyyyMMdd>.h,
					// NCRESULT_<CallingSystem>_<file name>_<yyyyMMdd>.d
					Pair<String, String> files = writeFile(newFileName, nameCheckInputBeanList, nameCheckOutputBeanList, dataFileCharset);
					successResult.put(ctrlFileName, dataFileName);
					if (uploadFileToServer(files) == false) {
						failResult.put(ctrlFileName, dataFileName);
					}
				} else {
					logger.info("nameCheckOutputBeanList not" + ctrlFileName + " ,dataFileName : " + dataFileName);
					failResult.put(ctrlFileName, dataFileName);
				}
			} catch (Exception e1) {
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

		boolean isSaveSucceed = false;
		final String failPath = AmlConfiguration.getString("com.sas.ftp.localResultNgURI");
		for (Entry<String, String> e : failResult.entrySet()) {
			File ctrlFile = new File(localURI, e.getKey());
			if (ctrlFile.exists()) {
				File newFile = new File(failPath, e.getKey());
				try {
					org.apache.commons.io.FileUtils.copyFile(ctrlFile, newFile);
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
			}
			logger.info("====== " + ctrlFile.getName() + " CtrlFile isSaveSucceed :: ======" + isSaveSucceed);
			File dataFile = new File(localURI, e.getValue());
			if (dataFile.exists()) {
				File newFile = new File(failPath, e.getValue());
				try {
					org.apache.commons.io.FileUtils.copyFile(dataFile, newFile);
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
			}
			logger.info("====== " + dataFile.getName() + " DataFile isSaveSucceed :: ======" + isSaveSucceed);
		}

		final String successPath = AmlConfiguration.getString("com.sas.ftp.localResultOkURI");
		for (Entry<String, String> e : successResult.entrySet()) {
			File ctrlFile = new File(localURI, e.getKey());
			if (ctrlFile.exists()) {
				File newFile = new File(successPath, e.getKey());
				try {
					org.apache.commons.io.FileUtils.copyFile(ctrlFile, newFile);
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
			}
			logger.info("====== " + ctrlFile.getName() + " CtrlFile isSaveSucceed :: ======" + isSaveSucceed);
			File dataFile = new File(localURI, e.getValue());
			if (dataFile.exists()) {
				File newFile = new File(successPath, e.getValue());
				try {
					org.apache.commons.io.FileUtils.copyFile(dataFile, newFile);
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
			}
			logger.info("====== " + dataFile.getName() + " DataFile isSaveSucceed :: ======" + isSaveSucceed);
		}
		logger.info("successResult :: " + successResult);
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
	private Pair<String, String> writeFile(String fileName, List<NameCheckInputBean> nameCheckInputBeanList, List<NameCheckOutputBean> nameCheckOutputBeanList,
			String outputFileCharset) throws IOException {
		BufferedWriter writerH = null;
		BufferedWriter writerD = null;
		Pair<String, String> result = null;
		try {
//			final String sourcePath = "E:\\SAS\\Doc\\BOT\\AMLFTP\\NC\\result";
			final String sourcePath = AmlConfiguration.getString("com.sas.ftp.localResultURI");
			final File resultFileH = new File(sourcePath, String.format("%s.h", fileName));
			final File resultFileD = new File(sourcePath, String.format("%s.d", fileName));
			final boolean append = false;
			logger.info("sourcePath::" + sourcePath + ", fileName::"+String.format("%s.d", fileName));
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
						bfr.append(bean.getNcReferenceId()!=null?
								StringUtils.rightPad(bean.getNcReferenceId(), 32):
								StringUtils.rightPad(" ", 32))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						bfr.append(StringUtils.rightPad(bean.getUniqueKey(), 128))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						bfr.append(StringUtils.rightPad(bean.getErrorCode(), 1))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						bfr.append(StringUtils.rightPad(bean.getErrorMessage(), 255))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						bfr.append(bean.getNcResult()!=null?StringUtils.rightPad(bean.getNcResult(), 2):StringUtils.rightPad(" ", 2))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						bfr.append(bean.getRouteRule()!=null?StringUtils.rightPad(bean.getRouteRule(), 2):StringUtils.rightPad(" ", 2))
								.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						if( bean.getHitSeq()==null) {
							bfr.append(StringUtils.rightPad(" ", 5))
							.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						}
						else if( bean.getHitSeq().length() > 5) {
							bfr.append(StringUtils.rightPad(bean.getHitSeq().split(",")[0], 5))
							.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
						}
						else {
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
						if(unikey!=null && nameCheckInputBean.getUniqueKey().equals(unikey)) {
							for (int j = 0; j < nameCheckInputBean.getSeq().size(); j++) {
								bfr.setLength(0);
								bfr.append(bean.getNcReferenceId()!=null?
										StringUtils.rightPad(bean.getNcReferenceId(), 32):
										StringUtils.rightPad("", 32))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								bfr.append(StringUtils.rightPad(bean.getUniqueKey(), 128))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								bfr.append(StringUtils.rightPad(bean.getErrorCode(), 1))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								bfr.append(StringUtils.rightPad(bean.getErrorMessage(), 255))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								bfr.append(bean.getNcResult()!=null?StringUtils.rightPad(bean.getNcResult(), 2):StringUtils.rightPad(" ", 2))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								bfr.append(bean.getRouteRule()!=null?StringUtils.rightPad(bean.getRouteRule(), 2):StringUtils.rightPad(" ", 2))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								if( bean.getHitSeq()==null) {
									bfr.append(StringUtils.rightPad(" ", 5))
									.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								}
								else if( bean.getHitSeq().length() > 5) {
									bfr.append(StringUtils.rightPad(bean.getHitSeq().split(",")[0], 5))
									.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								}
								else {
									bfr.append(StringUtils.rightPad(bean.getHitSeq(), 5))
									.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								}
								writerD.write(bfr.toString());
								detailBfr.setLength(0);
								detailBfr.append(StringUtils.leftPad(
										nameCheckInputBean.getSeq().get(j).getCheckSeq(), 5))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
								detailBfr.append(StringUtils.rightPad(" ", 2))
										.append(SwiftMtConst.NC_FTP_FIELD_SPLIT);
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
			logger.info("result::: " + result);
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
			String sourcePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_LOCALRESULTURI);
			if (sourcePath.endsWith("/")) {
				sourcePath = StringUtils.removeEnd(sourcePath, "/");
			}
			String remotePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMOTERESULTURI);
			if (remotePath.endsWith("/")) {
				remotePath = StringUtils.removeEnd(remotePath, "/");
			}

			ftpUtil.uploadFileToServer(sourcePath, uploadFiles.getSecond(), remotePath, uploadFiles.getSecond()); // 先放資料檔
			ftpUtil.uploadFileToServer(sourcePath, uploadFiles.getFirst(), remotePath, uploadFiles.getFirst()); // 再放控制檔

			result = true;
		} catch (Exception e) {
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
			String sourcePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_LOCALRESULTURI);
			if (sourcePath.endsWith("/")) {
				sourcePath = StringUtils.removeEnd(sourcePath, "/");
			}
			String remotePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMOTERESULTURI);
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
			String remoteURI = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMOTEURI);
			if (remoteURI.endsWith("/")) {
				remoteURI = StringUtils.removeEnd(remoteURI, "/");
			}
			String localURI = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_LOCALURI);
			if (localURI.endsWith("/")) {
				localURI = StringUtils.removeEnd(localURI, "/");
			}
			ftpUtil = new FTPUtil(ftpServer, ftpPort, ftpUser, ftpPwd);
			final FTPFile[] ftpFiles = ftpUtil.getRemoteFileList(remoteURI); // 先取得全部的檔案列表
			final Map<String, FTPFile> fileMap = new LinkedHashMap<String, FTPFile>(); // fileName:FTPFile
			for (FTPFile f : ftpFiles) { // 過濾只留 NCU8_ OR NCB5_開頭的檔案
				if (f.isFile() && (StringUtils.startsWithIgnoreCase(f.getName(), "NCU8_") || StringUtils.startsWithIgnoreCase(f.getName(), "NCB5_"))
						&& (StringUtils.endsWithIgnoreCase(f.getName(), ".h") ||
							StringUtils.endsWithIgnoreCase(f.getName(), ".d")) // 20180126增加只搜尋.h及.d 條件判斷
				) { //20180126增加只搜尋.h .H及.d .D條件判斷'
					logger.info("getName:::"+f.getName());
					fileMap.put(f.getName(), f);
				}
			}

			final String[] dataFileNameTails = { ".d", ".D" }; // 比對資料檔，檔名結尾
			for (String ctrlFileName : fileMap.keySet()) {
				logger.info("ctrlFileName:::" + ctrlFileName);
				// 控制檔是用來確定資料檔是否輸出完畢，不管檔案裡面的內容
				// 找到控制檔時，看資料檔是否存在
				if (StringUtils.endsWithIgnoreCase(ctrlFileName, ".h") == false || StringUtils.endsWithIgnoreCase(ctrlFileName, ".H") == false)
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

				// 下載路徑:
				// D:/SAS/Config/Lev1/Web/WebAppServer/SASServer99_1/AmlConfig/ftpNameCheck/ftpDownload
				// 有資料檔，則下載控制檔、資料檔
				final String remoteCrtlFile = remoteURI + "/" + ctrlFileName;
				ftpUtil.getRemoteFile(remoteCrtlFile, localURI + "/" + ctrlFileName);
				final String remoteDataFile = remoteURI + "/" + dataFileName;
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
	public static String filterNonAscii(String inString) {
        // Create the encoder and decoder for the character encoding
        Charset charset = Charset.forName("US-ASCII");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();
        // This line is the key to removing "unmappable" characters.
        encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        String result = inString;

        try {
            // Convert a string to bytes in a ByteBuffer
            ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(inString));

            // Convert bytes in a ByteBuffer to a character ByteBuffer and then to a string.
            CharBuffer cbuf = decoder.decode(bbuf);
            result = cbuf.toString();
        } catch (CharacterCodingException cce) {
            String errorMessage = "Exception during character encoding/decoding: " + cce.getMessage();
            logger.error(errorMessage, cce);
        }

        return result;  
    }
	/**
	 * 整理整批資料
	 * 
	 * @param file
	 * @param dataFileCharset 
	 * @return
	 */
	public List<NameCheckInputBean> FtpNameCheck(File file, NCOnlineErrMsgBean errMsg, String dataFileCharset) {
		List<NameCheckInputBean> resultList = new ArrayList<NameCheckInputBean>();
		BufferedReader br = null;
		try {
			if ( errMsg!=null ) {
				if (!(file.isFile() && (StringUtils.startsWithIgnoreCase(file.getName(), "NCB5_") || StringUtils.startsWithIgnoreCase(file.getName(), "NCU8_"))
						&& StringUtils.endsWithIgnoreCase(file.getName(), ".d") && file.length() != 0)) {
					errMsg.getErrorMsgStringList().add("File Format Error!");
					errMsg.setFileFormatError(true);
					errMsg.setValidateError(true);
				}
			}
			FileInputStream fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis, dataFileCharset!=null ? dataFileCharset : SwiftMtConst.NC_FTP_FILE_CHARSET));
			String line;
			List<FtpNameCheckInputBean> ftpBeanList = new ArrayList<FtpNameCheckInputBean>();
			int lineNum = 0;
			boolean firstLine = true;  
			while ((line = br.readLine()) != null) {
				if(firstLine && "UTF-8".equalsIgnoreCase(dataFileCharset)) {
		        	line = FTPUtil.removeUTF8BOM(line);  
		        	firstLine = false;
		        }
				if("US-ASCII".equalsIgnoreCase(dataFileCharset)) {
					line = new String(line.getBytes(), "UTF-8");
					line = filterNonAscii(line);
					line = new String(line.getBytes("US-ASCII"), "UTF-8");
					line = FTPUtil.removeUTF8BOM(line);  
		        	firstLine = false;
				}
				lineNum++;
				if (StringUtils.isBlank(line))
					continue;
				FtpNameCheckInputBean inputBean = lineToBean(line, errMsg, lineNum);
				if ( errMsg!=null ) {
					boolean isValid = ValidateStandardFormat(inputBean, errMsg, lineNum);
					if(!isValid) {
						errMsg.getErrorMsgStringList().add("File Format Error!");
						errMsg.setFileFormatError(true);
						errMsg.setValidateError(true);
					}
				}
				ftpBeanList.add(inputBean);
			}

			Map<String, NameCheckInputBean> inputBeanMap = ftpBeanListToInputBeanMap(ftpBeanList);
			for (Map.Entry<String, NameCheckInputBean> entry : inputBeanMap.entrySet()) {
				if(errMsg!=null) {
					errMsg.setEffCount(errMsg.getEffCount()+1);
				}
				resultList.add(entry.getValue());
			}

		} catch (FileNotFoundException e) {
			logger.error("FtpNameCheck FileNotFoundException : {}", e);
			errMsg.setValidateError(true);
		} catch (IOException e) {
			logger.error("FtpNameCheck IOException : {}", e);
			errMsg.setValidateError(true);
		} finally {
			IOUtils.closeQuietly(br);
		}
		return resultList;
	}

	private boolean ValidateStandardFormat(FtpNameCheckInputBean inputBean, NCOnlineErrMsgBean errMsg, int lineNum) {
		boolean isValid = true;
		if(inputBean.getCallingSystem() == null || inputBean.getCallingSystem() == "" ) {
			isValid = false;
			errMsg.getErrorMsgStringList().add("Error in " + lineNum+ " Line , Need Calling System.");
		}
		if(inputBean.getRealtimeFlag() == null || inputBean.getRealtimeFlag() == "" ) {
			isValid = false;
			errMsg.getErrorMsgStringList().add("Error in " + lineNum+ " Line , Need Realtime Flag.");
		}
		if(inputBean.getScreenFunction() == null || inputBean.getScreenFunction() == "" ) {
			isValid = false;
			errMsg.getErrorMsgStringList().add("Error in " + lineNum+ " Line, Need Screen Function.");
		}
		if(inputBean.getBranchNumber() == null || inputBean.getBranchNumber() == "" ) {
			isValid = false;
			errMsg.getErrorMsgStringList().add("Error in " + lineNum+ " Line, Need Branch Number.");
		}
		if(inputBean.getUniqueKey() == null || inputBean.getUniqueKey() == "" ) {
			isValid = false;
			errMsg.getErrorMsgStringList().add("Error in " + lineNum+ " Line, Need UniqueKey.");
		}
		if(inputBean.getCheckSeq() == null || inputBean.getCheckSeq() == "" ) {
			isValid = false;
			errMsg.getErrorMsgStringList().add("Error in " + lineNum+ " Line, Need Check Seqence.");
		}
		if(inputBean.getEntityType() == null || inputBean.getEntityType() == "" ) {
			isValid = false;
			errMsg.getErrorMsgStringList().add("Error in " + lineNum+ " Line, Need Entity Type.");
		}
		if(inputBean.getEntityRelationship() == null || inputBean.getEntityRelationship() == "" ) {
			isValid = false;
			errMsg.getErrorMsgStringList().add("Error in " + lineNum+ " Line, Need Entity Relationship.");
		}
		if((inputBean.getIdNumber() == null || inputBean.getIdNumber() == "") &&
				(inputBean.getBicSwiftCode() == null || inputBean.getBicSwiftCode() == "") &&
				(inputBean.getEnglishName() == null || inputBean.getEnglishName() == "") &&
				(inputBean.getNonEnglishName() == null || inputBean.getNonEnglishName() == "") &&
				(inputBean.getCccCode() == null || inputBean.getCccCode() == "") ) {
			isValid = false;
			errMsg.getErrorMsgStringList().add("Error in " + lineNum+ " Line, Need One Scan Object.");
		}
		
		return isValid;
	}

	/**
	 * FtpNameCheckInputBean to
	 * 
	 * @param ftpBeanList
	 * @return
	 */
	private Map<String, NameCheckInputBean> ftpBeanListToInputBeanMap(List<FtpNameCheckInputBean> ftpBeanList) {
		Map<String, NameCheckInputBean> ncBeanMap = new LinkedHashMap<String, NameCheckInputBean>();
		for (FtpNameCheckInputBean ftpBean : ftpBeanList) {
			if (ncBeanMap.containsKey(ftpBean.getUniqueKey())) {
				NameCheckInputBean input = ncBeanMap.get(ftpBean.getUniqueKey());
				List<NameCheckInputDetailBean> seq = input.getSeq();
				seq.add(new NameCheckInputDetailBean(ftpBean));
			} else {
				NameCheckInputBean input = new NameCheckInputBean(ftpBean);
				List<NameCheckInputDetailBean> seq = new ArrayList<NameCheckInputDetailBean>();
				input.setSeq(seq);
				seq.add(new NameCheckInputDetailBean(ftpBean));
				ncBeanMap.put(input.getUniqueKey(), input);
			}
		}
		return ncBeanMap;
	}

	private FtpNameCheckInputBean lineToBean(String line, NCOnlineErrMsgBean errMsg, int lineNum) {
		String[] fields = new String[19];
		Arrays.fill(fields, " ");
		int beginIndex = 0;
		int fieldIndex = 0;
		for (int index = line.indexOf(SwiftMtConst.NC_FTP_FIELD_SPLIT); index >= 0; index = line
				.indexOf(SwiftMtConst.NC_FTP_FIELD_SPLIT, index + 1)) {
			fields[fieldIndex] = line.substring(beginIndex, index);////// here you will get all the index of "("
			beginIndex = index + 2;
			fieldIndex++;
			if(fieldIndex == 18) {
				if(line.length() > beginIndex && line.substring(beginIndex)!=null) {
					fields[fieldIndex] = line.substring(beginIndex);
				}
			}
		}
		
		List<String> filedsList = new ArrayList<String>();
		for (String field : fields) {
			field = com.sas.util.StringUtils.convertToHalfWidth(field).trim();
			filedsList.add(field);
		}
		
		if( errMsg != null) {
			if(fieldIndex < 18) {
				errMsg.setErrorCount(errMsg.getErrorCount()+1);
				errMsg.getErrorMsgStringList().add("Error in "+lineNum+" Line, Parameter Count Error!");
				return new FtpNameCheckInputBean();
			}
		}
		return new FtpNameCheckInputBean(filedsList);
	}

	public static void main1(String[] args) {
		String fileName = "C:\\Users\\User\\Desktop\\NC_GEID_1070613001_20180608.d";
		File sampleFile = new File(fileName);
		AmlFtpNameCheckImpl amlFtpNameCheckImpl = new AmlFtpNameCheckImpl();
		amlFtpNameCheckImpl.FtpNameCheck(sampleFile, null,"BIG5");
	}

	public static void main(String[] args) throws IOException {
		AmlFtpNameCheckImpl amlFtpNameCheckImpl = new AmlFtpNameCheckImpl();
		amlFtpNameCheckImpl.doNameCheck();
	}


}
