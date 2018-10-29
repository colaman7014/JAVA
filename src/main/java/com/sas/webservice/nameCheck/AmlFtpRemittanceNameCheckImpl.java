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
import java.io.UnsupportedEncodingException;
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
import com.sas.webservice.nameCheck.bean.AmlFtpRemittanceNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.AmlFtpRemittanceNameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.FtpRemittanceNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.FtpRemittanceNameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.FtpRemittanceNameCheckInputEndBean;
import com.sas.webservice.nameCheck.bean.FtpRemittanceNameCheckInputHeadBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputDetail;

/**
 * AML Ftp Remittance Name Check 主程式 - 國外匯款
 * 
 * @author Danniel
 *
 */
@Component
@PropertySource("classpath:global.properties")
@WebService(endpointInterface = "com.sas.webservice.nameCheck.AmlFtpRemittanceNameCheck")
public class AmlFtpRemittanceNameCheckImpl implements AmlFtpRemittanceNameCheck {
	private static final Logger logger = LoggerFactory.getLogger(AmlFtpRemittanceNameCheckImpl.class);

	@Autowired
	AmlNameCheck amlNameCheck;

	@Autowired
	IFtpDownloadNamecheckCtrlDao iFtpDownloadNamecheckCtrlDao;
	
	@Value(SwiftMtConst.COM_SAS_FTP_REMITTANCE_SCHUDLE_ENABLE)
	private boolean isJobEnable;

	private static int HeadArray[];
	private static int DetailArray[];
	private static int EndArray[];
	private static int HeadCount = 269;
	private static int DetailCount = 269;
	private static int EndCount = 269;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Integer count1 = 1;
	
	static {
		String[] strHeadStrings = SwiftMtConst.BOT_NC1_FTP_REMMITTANCE_HEAD_LENGTH.split(","); 
		String[] strDetailStrings = SwiftMtConst.BOT_NC1_FTP_REMMITTANCE_DETAIL_LENGTH.split(","); 
		String[] strEndStrings = SwiftMtConst.BOT_NC1_FTP_REMMITTANCE_END_LENGTH.split(","); 
		// Splits each spaced integer into a String array.
		HeadArray = new int[strHeadStrings.length]; 
		DetailArray = new int[strDetailStrings.length]; 
		EndArray = new int[strEndStrings.length]; 
		// Creates the integer array.
		for (int i = 0; i < strHeadStrings.length; i++){
			HeadArray[i] = Integer.parseInt(strHeadStrings[i]); 
		}
		for (int i = 0; i < strDetailStrings.length; i++){
			DetailArray[i] = Integer.parseInt(strDetailStrings[i]); 
		}
		for (int i = 0; i < strEndStrings.length; i++){
			EndArray[i] = Integer.parseInt(strEndStrings[i]); 
		}
	}
	
	// @Scheduled(fixedDelay = 60000) // For 開發，每1分鐘，從上一個任務開始到下一個任務開始的間隔
	// @Scheduled(cron = "0 */5 * * * *") //每5分鐘(0, 5, 10, 15 ...)執行，參考 Cron 表達式
	// @Scheduled(fixedRate = 300000) //每隔5分鐘，從上一個任務完成開始到下一個任務開始的間隔，單位毫秒
	// @Scheduled(fixedDelay = 300000) //每隔5分鐘，從上一個任務開始到下一個任務開始的間隔，單位毫秒
	@Scheduled(fixedRateString = SwiftMtConst.COM_SAS_FTP_REMITTANCE_TRIGGER_RATE) 
	public void FtpRemittanceNameCheck() {
		 if(isJobEnable) {
			 try {
				logger.info(String.format("=== DoRemittanceNameCheck 第 %s次執行，當前時間：%s", count1++, dateFormat.format(new Date())));
				doRemittanceNameCheck();
			} catch (Exception e) {
				logger.error(String.format("FtpNameCheck fail, exception : %s", e.getMessage()), e);
			}
		}
		else {
			logger.info("============== Remittance Ftp NameCheck Job is disabled!");
		}
	}

	@Override
	public AmlFtpRemittanceNameCheckOutputBean doFtpRemittanceNameCheck(AmlFtpRemittanceNameCheckInputBean input) {
		Boolean trigger = input.isTrigger();

		AmlFtpRemittanceNameCheckOutputBean output = new AmlFtpRemittanceNameCheckOutputBean();
		
		try {
			output.setServerName(System.getenv("COMPUTERNAME"));

			if (trigger == null || trigger == false) {
				output.setFinish(false);
				return output;
			}

			doRemittanceNameCheck();
			logger.info("------------------ WebService DoRemittanceNameCheck Finished ----------------------------");
		} catch (Exception e) {
			logger.error(String.format("RemittanceNameCheck fail, exception : %s", e.getMessage()), e);
			output.setFinish(false);
		} finally {
			logger.info(output.toString());
		}

		return output;
	}

	 @Scheduled(cron = "0 0 6 * * ?") // 每6小時(0, 6, 12 ...)執行，參考 Cron 表達式
	public void removeSucceedFiles() {
		 if(isJobEnable) {
			 final String successDir = AmlConfiguration.getString("com.sas.ftp.remittance.localResultOkURI");
			 FileUtils.deleteFilesOlderThanNdays(90, successDir);
		 }
	}

	 @Scheduled(cron = "0 0 6 * * ?") // 每6小時(0, 6, 12 ...)執行，參考 Cron 表達式
	public void removeFailFiles() {
		 if(isJobEnable) {
			 final String failDir = AmlConfiguration.getString("com.sas.ftp.remittance.localResultNgURI");
			 FileUtils.deleteFilesOlderThanNdays(180, failDir);
		 }
	}

	/**
	 * FTP remittance name check FTP下載，批次處理名單掃描程式入口
	 */
	public Map<String, String> doRemittanceNameCheck() {
		/* 時間測試 */
		Map<String, String> successResult = new LinkedHashMap<String, String>();
		Map<String, String> failResult = new LinkedHashMap<String, String>();
		Map<String, String> downloadFiles = new LinkedHashMap<String, String>();
		try {
			downloadFiles = downloadFolderFromFtp();
		} catch (Exception e) {
			return successResult;
		}
		/* 測試 Local 時使用 */
//		Map<String, String> downloadFiles = new LinkedHashMap<String, String>();
//		downloadFiles.put("NC1_BOT983RT0701311101_20180131.h", "NC1_BOT983RT0701311101_20180131.d");
//		downloadFiles.put("NC1_BOT983RT0701301102_20180130.H", "NC1_BOT983RT0701301102_20180130.D");
//		downloadFiles.put("NC1_BOT983RT0701302501_20180130.H", "NC1_BOT983RT0701302501_20180130.D");
//		downloadFiles.put("NC1_BOT983RT0701302502_20180130.H", "NC1_BOT983RT0701302502_20180130.D");
//		downloadFiles.put("NC1_BOT983RT0701311101_20180131.h", "NC1_BOT983RT0701311101_20180131.d");
//		downloadFiles.put("NC1_BOT983RT0701311102_20180131.h", "NC1_BOT983RT0701311102_20180131.d");
//		downloadFiles.put("NC1_BOT983RT0701312502_20180131.h", "NC1_BOT983RT0701312502_20180131.d");
//		downloadFiles.put("NC1_BOT983RT0702012502_20180201.h", "NC1_BOT983RT0702012502_20180201.d");
		/* 測試 Local 時使用 */

		final List<FtpDownloadNamecheckCtrl> ftpDownloadNCResult = new ArrayList<FtpDownloadNamecheckCtrl>();

		if (CollectionUtils.isEmpty(downloadFiles)) {
			logger.info("downloadFiles empty");
			// 沒有下載，就不用 Name Check
			return successResult;
		}
		
		String localURI = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_LOCALURI);
		String remotePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_REMOTERESULTURI);

		for (Entry<String, String> e : downloadFiles.entrySet()) {
			FtpDownloadNamecheckCtrl ftpDownloadCtrl = null;
			String ctrlFileName = e.getKey(); // 控制檔只是用來確定可下載資料檔，沒有其它用途 格式為：NC_<CallingSystem>_<file name>_<yyyyMMdd>.h
			String dataFileName = e.getValue(); // 格式為：NC1_<CallingSystem>_<file name>_<yyyyMMdd>.d
			String dataFileCharset = SwiftMtConst.NC_FTP_FILE_CHARSET;
			
			try {
				ftpDownloadCtrl = new FtpDownloadNamecheckCtrl();
				List<NameCheckOutputBean> nameCheckOutputBeanList = new ArrayList<NameCheckOutputBean>();
				String primeFileName = StringUtils.removeStartIgnoreCase(dataFileName, "NC1_");
				primeFileName = StringUtils.removeEndIgnoreCase(primeFileName, ".d"); // 取出名字中間部份：<CallingSystem>_<file
																						// name>_<yyyyMMdd>
				/* 測試 Local 時使用 */
//				File dataFile = new File("C:\\Users\\User\\Desktop\\", dataFileName);
				/* 測試 Local 時使用 */
				File dataFile = new File(localURI, dataFileName);
				if (dataFile.exists()) {
					File newFile = new File(localURI, dataFileName);
					dataFile.renameTo(newFile);
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
				String newFileName = "NC1RESULT_" + primeFileName; // NCRESULT_<CallingSystem>_<file name>_<yyyyMMdd>
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

				
				List<NameCheckInputBean> nameCheckInputBeanList = FtpRemittanceNameCheck(dataFile, dataFileCharset, null, null);
				logger.info("FtpRemittanceNameCheck nameCheckInputBeanList:::" + nameCheckInputBeanList.toString());
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
						logger.error("NameCheck Error {}" , ex.getMessage());
						List<NameCheckOutputDetail> detailList = new ArrayList<>();
						for(NameCheckInputDetailBean errorSeq: inputBean.getSeq()) {
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
				logger.info("FtpRemittanceNameCheck nameCheckOutputBeanList:::" + nameCheckOutputBeanList.toString());
				if (CollectionUtils.isEmpty(nameCheckOutputBeanList) == false) {
					// return 格式為：NCRESULT_<CallingSystem>_<file name>_<yyyyMMdd>.h,
					// NCRESULT_<CallingSystem>_<file name>_<yyyyMMdd>.d
					Pair<String, String> files = writeFile(newFileName, nameCheckInputBeanList, nameCheckOutputBeanList, dataFileCharset);
					successResult.put(ctrlFileName, dataFileName);
					if (uploadFileToServer(files) == false) {
						failResult.put(ctrlFileName, dataFileName);
					}
				} else {
					failResult.put(ctrlFileName, dataFileName);
				}
			} catch (Exception e1) {
				logger.info("FTP Remittance Name Check Error");
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
		final String failPath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_LOCALRESULTNGURI);
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

		final String successPath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_LOCALRESULTOKURI);
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
//			final String sourcePath = "E:\\SAS\\Doc\\BOT\\AMLFTP\\NC1\\result";
			final String sourcePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_LOCALRESULTURI);
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
			
			String remoteURI = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_REMOTEURI);
			if (remoteURI.endsWith("/")) {
				remoteURI = StringUtils.removeEnd(remoteURI, "/");
			}
			String localURI = AmlConfiguration.getString(SwiftMtConst.COM_SAS_FTP_REMITTANCE_LOCALURI);
			if (localURI.endsWith("/")) {
				localURI = StringUtils.removeEnd(localURI, "/");
			}
			ftpUtil = new FTPUtil(ftpServer, ftpPort, ftpUser, ftpPwd);
			final FTPFile[] ftpFiles = ftpUtil.getRemoteFileList(remoteURI); // 先取得全部的檔案列表
			final Map<String, FTPFile> fileMap = new LinkedHashMap<String, FTPFile>(); // fileName:FTPFile
			for (FTPFile f : ftpFiles) { // 過濾只留 NC1_ 開頭的檔案
				if (f.isFile() && StringUtils.startsWithIgnoreCase(f.getName(), "NC1_")
						&& (StringUtils.endsWithIgnoreCase(f.getName(), ".h") ||
								StringUtils.endsWithIgnoreCase(f.getName(), ".d"))
				) { //20180126增加只搜尋.h .H及.d .D條件判斷
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

	/**
	 * 整理整批資料
	 * 
	 * @param file
	 * @return
	 */
	public List<NameCheckInputBean> FtpRemittanceNameCheck(File file, String dataFileCharset, NCOnlineErrMsgBean errMsg, String callingUser) {
		List<NameCheckInputBean> resultList = new ArrayList<NameCheckInputBean>();
		List<FtpRemittanceNameCheckInputDetailBean> detailsBean = new ArrayList<>();
		FtpRemittanceNameCheckInputBean inputBean = new FtpRemittanceNameCheckInputBean();
		BufferedReader br = null;
		int effCount = 0;
		try {
			if ( errMsg!=null ) {
				if (!(file.isFile() && StringUtils.startsWithIgnoreCase(file.getName(), "NC1_")
						&& StringUtils.endsWithIgnoreCase(file.getName(), ".d"))) {
					errMsg.setFileFormatError(true);
					errMsg.getErrorMsgStringList().add("File Format Error!");
					errMsg.setValidateError(true);
					return resultList;
				}
			}
			FileInputStream fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis, dataFileCharset!=null ? dataFileCharset : SwiftMtConst.NC_FTP_FILE_CHARSET));
			String line;
			char ch;
			int rowNum = 0;
			boolean firstLine = true;  
			while ((line = br.readLine()) != null) {
				if(firstLine && "UTF-8".equalsIgnoreCase(dataFileCharset)) {
		        	line = FTPUtil.removeUTF8BOM(line);  
		        	firstLine = false;
		        }
				rowNum++;
				if (StringUtils.isBlank(line)) {
					if(errMsg!=null) {
						if(line.length() > HeadCount) {
							errMsg.setFileFormatError(true);
							errMsg.getErrorMsgStringList().add("Error in row" + rowNum+", File Format Error!");
							errMsg.setValidateError(true);
//							return resultList;
						}
					}
					continue;
				} else {
					ch = line.charAt(0);
					int length = line.length();
					if (ch == '1') {
						if(errMsg!=null) {
							if(line.length() > DetailCount) {
								errMsg.setFileFormatError(true);
								errMsg.getErrorMsgStringList().add("Error in row" + rowNum+", File Format Error!");
								errMsg.setValidateError(true);
//								return resultList;
							}
						}
						inputBean.setHeadBean(
//								lineToHeadBean(new int[] { 1, 3, 10, 5, 8, 23, 68, 82, 1, 68 }, line, dataFileCharset)
								lineToHeadBean(HeadArray, line, dataFileCharset)
						);
					} else if (ch == '2') {
						if(errMsg!=null) {
							if(line.length() > DetailCount){
								errMsg.setFileFormatError(true);
								errMsg.getErrorMsgStringList().add("Error in row" + rowNum+", File Format Error!");
								errMsg.setValidateError(true);
//								return resultList;
							}
						}
						detailsBean.add(
//								lineToDetailBean(new int[] { 1, 3, 10, 5, 8, 7, 2, 14, 68, 14, 52, 7, 10, 68}, line, dataFileCharset)
								lineToDetailBean(DetailArray, line, dataFileCharset)
						);
						inputBean.setDetailsBean(detailsBean);
					} else if (ch == '3') {
//						if(line.length()!=EndCount){
//							errMsg.setFileFormatError(true);
//							errMsg.getErrorMsgStringList().add("File Format Error!");
//							errMsg.setValidateError(true);
//							return null;
//						}
//						inputBean.setEndBean(
//								lineToEndBean(EndArray, line, dataFileCharset)
//						);
					} else {
						logger.error("FtpRemittanceNameCheck Parameter Section Error : {}", ch);
					}
				}
			}
			if (inputBean != null) {
				Map<String, NameCheckInputBean> inputBeanMap = ftpBeanListToInputBeanMap(inputBean, callingUser);
				for (Map.Entry<String, NameCheckInputBean> entry : inputBeanMap.entrySet()) {
					resultList.add(entry.getValue());
					if(errMsg!=null) {
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
	private Map<String, NameCheckInputBean> ftpBeanListToInputBeanMap(FtpRemittanceNameCheckInputBean ftpInputBean, String callingUser) {
		Map<String, NameCheckInputBean> ncBeanMap = new LinkedHashMap<String, NameCheckInputBean>();
		
		/* 首筆轉換 */
		FtpRemittanceNameCheckInputHeadBean headBean = ftpInputBean.getHeadBean();
		String headUniqueKey = headBean.getBranchNumber() + headBean.getBatchNo() + headBean.getSeqNo();
		NameCheckInputBean inputHeadBean = MapFtpBeanToNCInputBean(headBean, callingUser);
		List<NameCheckInputDetailBean> detailHeadBean = MapFtpBeanToNCInputDetailBean(headBean);
		inputHeadBean.setSeq(detailHeadBean);
		ncBeanMap.put(headUniqueKey, inputHeadBean);
		
		/* 明細轉換 */
		for (FtpRemittanceNameCheckInputDetailBean detailBean : ftpInputBean.getDetailsBean()) {
			String uniqueKey = detailBean.getBranchNumber() + detailBean.getBatchNo() + detailBean.getSeqNo();
			if (ncBeanMap.containsKey(uniqueKey)) {
				NameCheckInputBean input = ncBeanMap.get(uniqueKey);
				List<NameCheckInputDetailBean> detailSeq =  MapFtpBeanToNCInputDetailBean(detailBean);
				input.getSeq().addAll(detailSeq);
			} else {
				NameCheckInputBean input = MapFtpBeanToNCInputBean(detailBean, callingUser);
				input.setSeq(MapFtpBeanToNCInputDetailBean(detailBean));
				ncBeanMap.put(uniqueKey, input);
			}
		}
		return ncBeanMap;
	}

	private List<NameCheckInputDetailBean> MapFtpBeanToNCInputDetailBean(Object bean) {
		List<NameCheckInputDetailBean> detailBeanList = new ArrayList<>();
		
		switch (bean.getClass().getSimpleName()) {
			case "FtpRemittanceNameCheckInputHeadBean":
				NameCheckInputDetailBean scanDetailBean = new NameCheckInputDetailBean();
				FtpRemittanceNameCheckInputHeadBean headBean = (FtpRemittanceNameCheckInputHeadBean) bean;
				String remitterName = com.sas.util.StringUtils.convertToHalfWidth(headBean.getRemitterName()).trim();
				scanDetailBean.setCheckSeq(SwiftMtConst.BOT_NC1_FTP_CHECKSEQ_HEAD);
				scanDetailBean.setEntityType(SwiftMtConst.ENTITY_TYPE_INDANDCORP);
				scanDetailBean.setEntityRelationship("01");
				scanDetailBean.setEntityRelationshipDesc("");
				if (com.sas.util.StringUtils.isEnglish(remitterName)) {
					scanDetailBean.setEnglishName(remitterName);
					scanDetailBean.setNonEnglishName("");
				} else {
					scanDetailBean.setEnglishName("");
					scanDetailBean.setNonEnglishName(remitterName);
				}
				scanDetailBean.setCccCode("");
				scanDetailBean.setIdNumber("");
				scanDetailBean.setBicSwiftCode("");
				scanDetailBean.setCountry("TW");
				scanDetailBean.setYearOfBirth("");
				detailBeanList.add(scanDetailBean);
			break;
		case "FtpRemittanceNameCheckInputDetailBean":
			FtpRemittanceNameCheckInputDetailBean detailBean = (FtpRemittanceNameCheckInputDetailBean) bean;
			String beneficiaryName = "", uniformName = "";
			beneficiaryName = com.sas.util.StringUtils.convertToHalfWidth(detailBean.getBeneficiaryName()).trim();
			uniformName = com.sas.util.StringUtils.convertToHalfWidth(detailBean.getUniformName()).trim();
			if(beneficiaryName != null && !"".equals(beneficiaryName)) {
				NameCheckInputDetailBean scanBeneficiaryNameBean = new NameCheckInputDetailBean();
				scanBeneficiaryNameBean.setCheckSeq(SwiftMtConst.BOT_NC1_FTP_CHECKSEQ_DETAIL_BENEFICIARY);
				scanBeneficiaryNameBean.setEntityType(SwiftMtConst.ENTITY_TYPE_INDANDCORP);
				scanBeneficiaryNameBean.setEntityRelationship("04");
				scanBeneficiaryNameBean.setEntityRelationshipDesc("");
				if (com.sas.util.StringUtils.isEnglish(beneficiaryName)) {
					scanBeneficiaryNameBean.setEnglishName(beneficiaryName);
					scanBeneficiaryNameBean.setNonEnglishName("");
				} else {
					scanBeneficiaryNameBean.setEnglishName("");
					scanBeneficiaryNameBean.setNonEnglishName(beneficiaryName);
				}
				scanBeneficiaryNameBean.setCccCode("");
				scanBeneficiaryNameBean.setIdNumber("");
				scanBeneficiaryNameBean.setBicSwiftCode("");
				scanBeneficiaryNameBean.setCountry("TW");
				scanBeneficiaryNameBean.setYearOfBirth("");
				detailBeanList.add(scanBeneficiaryNameBean);
			}
			
			if(uniformName != null && !"".equals(uniformName)) {
				NameCheckInputDetailBean scanUniformNameBean = new NameCheckInputDetailBean();
				scanUniformNameBean.setCheckSeq(SwiftMtConst.BOT_NC1_FTP_CHECKSEQ_DETAIL_UNIFORM);
				scanUniformNameBean.setEntityType(SwiftMtConst.ENTITY_TYPE_INDANDCORP);
				scanUniformNameBean.setEntityRelationship("01");
				scanUniformNameBean.setEntityRelationshipDesc("");
				if (com.sas.util.StringUtils.isEnglish(uniformName)) {
					scanUniformNameBean.setEnglishName(uniformName);
					scanUniformNameBean.setNonEnglishName("");
				} else {
					scanUniformNameBean.setEnglishName("");
					scanUniformNameBean.setNonEnglishName(uniformName);
				}
				scanUniformNameBean.setCccCode("");
				scanUniformNameBean.setIdNumber("");
				scanUniformNameBean.setBicSwiftCode("");
				scanUniformNameBean.setCountry("TW");
				scanUniformNameBean.setYearOfBirth("");
				detailBeanList.add(scanUniformNameBean);
			}
			break;
		default:
			break;
		}
		return detailBeanList;
	}

	private NameCheckInputBean MapFtpBeanToNCInputBean(Object bean, String callingUser) {
		NameCheckInputBean scanBean = new NameCheckInputBean();
		switch (bean.getClass().getSimpleName()) {
		case "FtpRemittanceNameCheckInputHeadBean":
			FtpRemittanceNameCheckInputHeadBean headBean = (FtpRemittanceNameCheckInputHeadBean) bean;
			scanBean.setBranchNumber(headBean.getBranchNumber());
			scanBean.setBusinessUnitID(SwiftMtConst.BOT_NC_DEFAULT_BUSINESS_UNIT_ID);
			scanBean.setCallingSystem(SwiftMtConst.BOT_NC_DEFAULT_CALLING_SYSTEM);
			scanBean.setCallingUser(callingUser!=null?callingUser:SwiftMtConst.BOT_NC_DEFAULT_CALLING_USER);
			scanBean.setGenAlertFlag(SwiftMtConst.BOT_NC_DEFAULT_ALERT_FLAG);
			scanBean.setPartyNumber("");
			scanBean.setScreenProcess("3");
			scanBean.setUniqueKey(headBean.getBranchNumber() + headBean.getBatchNo() + headBean.getSeqNo());
			break;
		case "FtpRemittanceNameCheckInputDetailBean":
			FtpRemittanceNameCheckInputDetailBean detailBean = (FtpRemittanceNameCheckInputDetailBean) bean;
			scanBean.setBranchNumber(detailBean.getBranchNumber());
			scanBean.setBusinessUnitID(SwiftMtConst.BOT_NC_DEFAULT_BUSINESS_UNIT_ID);
			scanBean.setCallingSystem(SwiftMtConst.BOT_NC_DEFAULT_CALLING_SYSTEM);
			scanBean.setCallingUser(callingUser!=null?callingUser:SwiftMtConst.BOT_NC_DEFAULT_CALLING_USER);
			scanBean.setGenAlertFlag(SwiftMtConst.BOT_NC_DEFAULT_ALERT_FLAG);
			if(detailBean.getUniformName() != null && !detailBean.getUniformName().equals(""))
				scanBean.setPartyNumber(detailBean.getUniformNum());
			else
				scanBean.setPartyNumber("");
			scanBean.setScreenProcess("3");
			scanBean.setUniqueKey(detailBean.getBranchNumber() + detailBean.getBatchNo() + detailBean.getSeqNo());
			break;
		default:
			break;
		}
		return scanBean;
	}

	public static void getBytes(byte[] source, int srcBegin, int srcEnd, byte[] destination, int dstBegin)  {
		if(source.length < srcEnd) {
			logger.error("File Input Length Error {} ", source.length);
			System.arraycopy(source, srcBegin, destination, dstBegin, source.length - srcBegin);
		}
		else {
			System.arraycopy(source, srcBegin, destination, dstBegin, srcEnd - srcBegin);
		}
	}

	public static byte[] subbytes(byte[] source, int srcBegin, int srcEnd) {
		byte destination[];

		destination = new byte[srcEnd - srcBegin];
		getBytes(source, srcBegin, srcEnd, destination, 0);

		return destination;
	}

	private List<String> MappingStringUsingIntArray (int[] paraLength, String line, String dataFileCharset) throws UnsupportedEncodingException {
		List<String> filedsList = new ArrayList<String>();
		byte[] bytes = line.getBytes(dataFileCharset);
		int beginIndex = 0;
		for (int integer : paraLength) {
			int endIndex = beginIndex+integer;
			String str = new String(subbytes(bytes, beginIndex, endIndex), dataFileCharset);
			beginIndex=endIndex;
			filedsList.add(str);
		}
		return filedsList;
	}

	private FtpRemittanceNameCheckInputHeadBean lineToHeadBean(int[] paraLength, String line, String dataFileCharset) throws UnsupportedEncodingException {
		line = line.replace("\4", " ");
		line = line.replace("\7", " ");
		List<String> filedsList = MappingStringUsingIntArray(paraLength, line, dataFileCharset);
		for (String field : filedsList) {
			field = com.sas.util.StringUtils.convertToHalfWidth(field).trim();
		}
		return new FtpRemittanceNameCheckInputHeadBean(filedsList);
	}

	private FtpRemittanceNameCheckInputDetailBean lineToDetailBean(int[] paraLength, String line, String dataFileCharset) throws UnsupportedEncodingException {
		line = line.replace("\4", " ");
		line = line.replace("\7", " ");
		List<String> filedsList = MappingStringUsingIntArray(paraLength, line, dataFileCharset);
		for (String field : filedsList) {
			field = com.sas.util.StringUtils.convertToHalfWidth(field).trim();
		}
		return new FtpRemittanceNameCheckInputDetailBean(filedsList);
	}

	private FtpRemittanceNameCheckInputEndBean lineToEndBean(int[] paraLength, String line, String dataFileCharset) throws UnsupportedEncodingException {
		line = line.replace("\4", " ");
		line = line.replace("\7", " ");
		List<String> filedsList = MappingStringUsingIntArray(paraLength, line, dataFileCharset);
		for (String field : filedsList) {
			field = com.sas.util.StringUtils.convertToHalfWidth(field).trim();
		}
		return new FtpRemittanceNameCheckInputEndBean(filedsList);
	}

	public static void main1(String[] args) {
//		String fileName = "E:\\SAS\\Doc\\BOT\\AMLFTP\\NC1\\NC1_BOT983RT0701312501_20180131.d";
		String fileName = "E:\\SAS\\Doc\\BOT\\AMLFTP\\NC1\\NC1_BOT983RT0702021101_20180202.d";
		File sampleFile = new File(fileName);
		AmlFtpRemittanceNameCheckImpl amlRemittanceNameCheckImpl = new AmlFtpRemittanceNameCheckImpl();
		amlRemittanceNameCheckImpl.FtpRemittanceNameCheck(sampleFile, "Big5", new NCOnlineErrMsgBean(), null);
	}

	public static void main(String[] args) throws IOException {
		AmlFtpRemittanceNameCheckImpl amlFtpNameCheckImpl = new AmlFtpRemittanceNameCheckImpl();
		amlFtpNameCheckImpl.doRemittanceNameCheck();
	}
}
