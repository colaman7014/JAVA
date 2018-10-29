package com.sas.multipleNC.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.fcfcore.IAccountIntegrationDao;
import com.sas.db.aml.orm.fcfcore.AccountIntegration;
import com.sas.db.wlf.dao.IXInvBlNcUploadRecordDao;
import com.sas.db.wlf.dao.tf.IXOnlineNameCheckUploadDao;
import com.sas.db.wlf.orm.XInvBlNcUploadRecord;
import com.sas.db.wlf.orm.tf.XOnlineNamecheckUpload;
import com.sas.db.wlf.orm.tf.XOnlineNamecheckUploadPK;
import com.sas.multipleNC.bean.NCOnlineErrMsgBean;
import com.sas.util.AmlConfiguration;
import com.sas.util.Base64Util;
import com.sas.util.ExcelUtils;
import com.sas.util.FTPUtil;
import com.sas.webservice.nameCheck.AmlFtpExRemittanceNameCheck;
import com.sas.webservice.nameCheck.AmlFtpNameCheck;
import com.sas.webservice.nameCheck.AmlFtpRemittanceNameCheck;
import com.sas.webservice.nameCheck.AmlNameCheck;
import com.sas.webservice.nameCheck.bean.FtpNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputDetail;

@Service
public class AmlNCOnlineCheckService {
	private static final Logger logger = LoggerFactory.getLogger(AmlNCOnlineCheckService.class);
	@Autowired
	AmlNameCheck amlNameCheck;
	@Autowired
	IXInvBlNcUploadRecordDao iXInvBlNcUploadRecordDao;
	@Autowired
	AmlFtpRemittanceNameCheck amlFtpRemittanceNameCheck;
	@Autowired
	AmlFtpNameCheck amlFtpNameCheck;
	@Autowired
	AmlFtpExRemittanceNameCheck amlFtpExRemittanceNameCheck;
	@Autowired
	IAccountIntegrationDao iAccountIntegrationDao;
	@Autowired
	IXOnlineNameCheckUploadDao iXOnlineNameCheckUploadDao;

	public XInvBlNcUploadRecord getNCCheckFile(String fileKey) {
		return iXInvBlNcUploadRecordDao.findByFileKey(fileKey);
	}

	/**
	 * 處理手動國外匯款、國內匯款、標準名單檔案上傳資料處理
	 * 
	 * @param uploadType
	 * @param callingUser
	 * @param multipartFile
	 * @param scanType
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
//	 @Transactional
	public NCOnlineErrMsgBean saveNCheckFile(String uploadType, String callingUser, MultipartFile multipartFile,
			String scanType, String startDate, String endDate) throws IOException, ParseException {
		NCOnlineErrMsgBean result = new NCOnlineErrMsgBean();
		String fileKey = UUID.randomUUID().toString().toUpperCase();
		String dataFileCharset = "UTF-8", branchNum = "";
		XInvBlNcUploadRecord xInvBlNcUploadRecord = new XInvBlNcUploadRecord();
		String filePath = AmlConfiguration.getString(String.format("com.sas.upload.filePath.%s", uploadType));
		String newFileName = multipartFile.getOriginalFilename();
		String fileNameToCreate = filePath + fileKey;
		
		File file = new File(fileNameToCreate);
		if (file.exists()) {
			File newFile = new File(fileNameToCreate);
			file.renameTo(newFile);
		} else {
			FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
		}
		File uploadFile = multipartToFile(multipartFile);
		// Excute save mark
		boolean needSave = false;
		// List<XOnlineNamecheckUpload> xRecordList = new ArrayList<>();
		NCOnlineErrMsgBean errMsgBean = new NCOnlineErrMsgBean(false, false, 0, 0, 0, new ArrayList<String>(),
				fileKey);
		Charset charset = FTPUtil.detectCharset(uploadFile,
				AmlConfiguration.getString(SwiftMtConst.COM_SAS_CHARSET_DETECHED).split(","));
		if (charset == null) {
			xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
			xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_FILE_CHARSET_ERROR);
			errMsgBean.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_FILE_CHARSET_ERROR);
			errMsgBean.setFileFormatError(true);
			errMsgBean.setValidateError(true);
			return errMsgBean;
		} else {
			dataFileCharset =  charset.toString();
		}
		
		AccountIntegration account = iAccountIntegrationDao.findOne(callingUser);
		if (account == null) {
			xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
			xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_ACCOUNT_BRANCH_NOT_EXIST_ERROR);
			errMsgBean.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_ACCOUNT_BRANCH_NOT_EXIST_ERROR);
			errMsgBean.setFileFormatError(true);
			errMsgBean.setValidateError(true);
			return errMsgBean;
		} else {
			branchNum = account.getDeptNo();
		}
		
		if (SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT.equals(uploadType)) {
			needSave = true;
			xInvBlNcUploadRecord.setFileKey(fileKey);
			xInvBlNcUploadRecord.setScanPeriodStart(new Timestamp(System.currentTimeMillis()));
			xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
			xInvBlNcUploadRecord.setScanType(scanType);
			
			try {
				if (!(uploadFile.isFile() && StringUtils.startsWithIgnoreCase(uploadFile.getName(), SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT_FILE_PREFIX)
						&& StringUtils.endsWithIgnoreCase(uploadFile.getName(), ".xlsx") && uploadFile.length() != 0)) {
					xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
					xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_FILENAME_ERROR);
					errMsgBean.getErrorMsgStringList().add("File Format Error!");
					errMsgBean.setFileFormatError(true);
					errMsgBean.setValidateError(true);
					return errMsgBean;
				}
				
				dataFileCharset = charset.toString();
				List<NameCheckOutputBean> nameCheckOutputBeanList = new ArrayList<NameCheckOutputBean>();
				List<NameCheckInputBean> nameCheckInputBeanList = importXStandardFormat(new FileInputStream(uploadFile),
						errMsgBean, callingUser, branchNum);
				logger.info("Online Standard NameCheck NameCheckInputBeanList:::" + nameCheckInputBeanList.toString());
				int rowNum = 0;
				if(nameCheckInputBeanList.isEmpty()) {
					xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
					xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
					errMsgBean.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
					return errMsgBean;
				}
				
				for (NameCheckInputBean inputBean : nameCheckInputBeanList) {
					rowNum++;
					logger.info("inputBean in:::" + inputBean.toString());
					// NameCheck(inputBean) 進行名單掃描
					NameCheckOutputBean outputBean = new NameCheckOutputBean();
					try {
						outputBean = amlNameCheck.NameCheck(inputBean);
						if (outputBean != null) {
							nameCheckOutputBeanList.add(outputBean);
						} else {
							errMsgBean.setErrorCount(errMsgBean.getErrorCount() + 1);
							errMsgBean.setValidateError(true);
							errMsgBean.getErrorMsgStringList().add("Error in row" + rowNum + ", NameCheck Scan Fail");
							logger.info("Error in row" + rowNum + ", Online Standard NameCheck Output Error ::: "
									+ inputBean.getUniqueKey());
						}
					} catch (Exception ex) {
						errMsgBean.setErrorCount(errMsgBean.getErrorCount() + 1);
						errMsgBean.setValidateError(true);
						errMsgBean.getErrorMsgStringList().add("Error in row" + rowNum + ", NameCheck Scan Fail");
						logger.info("Error in row" + rowNum + ", NameCheck Error:::" + inputBean.getUniqueKey()
								+ ", Error Message ::: " + ex.getMessage());
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
					}
				}
				
				if (CollectionUtils.isEmpty(nameCheckOutputBeanList) == false) {
					logger.info("Online Standard NameCheck nameCheckOutputBeanList:::" + nameCheckOutputBeanList.toString());
					String primeFileName = StringUtils.removeStartIgnoreCase(newFileName, SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT_FILE_PREFIX);
					primeFileName = StringUtils.removeEndIgnoreCase(primeFileName, ".xlsx"); // 取出名字中間部份：<CallingSystem>_<file
					String resultFileName =  SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT_FILE_RESULT_PREFIX + primeFileName; // NCRESULT_<CallingSystem>_<filename>_<yyyyMMdd>
					String realResultFileName =  SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT_FILE_RESULT_PREFIX + fileKey;
					
					List<XOnlineNamecheckUpload> xOnlineNamecheckUploads = new ArrayList<>();
					xInvBlNcUploadRecord.setFilePath(filePath);
					xInvBlNcUploadRecord.setFileName(newFileName);
					xInvBlNcUploadRecord.setScanPeriodEnd(new Timestamp(System.currentTimeMillis()));
					boolean isOk = WriteXlsxResult(realResultFileName, nameCheckInputBeanList, nameCheckOutputBeanList,
							errMsgBean, filePath, xOnlineNamecheckUploads, fileKey, uploadType, callingUser, branchNum);
					
					if (!isOk) {
						logger.info("Online Standard Write File Fail");
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						errMsgBean.getErrorMsgStringList().add("Write File Fail");
						errMsgBean.setValidateError(true);
						return errMsgBean;
					}
					xInvBlNcUploadRecord.setResultFile(String.format("%s.xlsx", resultFileName));
					xInvBlNcUploadRecord.setResultPath(String.format("%s", filePath));
				} else {
					logger.info("Online Standard Convert NameCheckInputBean Fail!");
					xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
					xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
					errMsgBean.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
					errMsgBean.setValidateError(true);
					return errMsgBean;
				}
			} catch (Exception e) {
				logger.info("Online Standard Name Check Fail!");
				xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
				xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_INTERNAL_SERVER_ERROR);
				errMsgBean.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_INTERNAL_SERVER_ERROR);
				errMsgBean.setValidateError(true);
				return errMsgBean;
			}

			result = errMsgBean;
		} else if (SwiftMtConst.UPLOAD_TYPE_FTP_REMITTANCE_IMPORT.equals(uploadType)) {
			needSave = true;
			xInvBlNcUploadRecord.setFileKey(fileKey);
			xInvBlNcUploadRecord.setScanPeriodStart(new Timestamp(System.currentTimeMillis()));
			xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
			xInvBlNcUploadRecord.setScanType(scanType);
			try {
				if (!(uploadFile.isFile() && StringUtils.startsWithIgnoreCase(uploadFile.getName(), SwiftMtConst.UPLOAD_TYPE_FTP_REMITTANCE_IMPORT_FILE_PREFIX)
						&& StringUtils.endsWithIgnoreCase(uploadFile.getName(), ".d") && uploadFile.length() != 0)) {
					xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
					xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_FILENAME_ERROR);
					errMsgBean.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_FILENAME_ERROR);
					errMsgBean.setFileFormatError(true);
					errMsgBean.setValidateError(true);
					return errMsgBean;
				}
				
				List<NameCheckInputBean> nameCheckInputBeanList = amlFtpRemittanceNameCheck
						.FtpRemittanceNameCheck(uploadFile, dataFileCharset, errMsgBean, callingUser);
				List<NameCheckOutputBean> nameCheckOutputBeanList = new ArrayList<NameCheckOutputBean>();
				int rowNum = 0;
				if(nameCheckInputBeanList.isEmpty()) {
					logger.info("FtpRemittanceNameCheck nameCheckInputBeanList:::" + nameCheckInputBeanList.toString());
					xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
					xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
					errMsgBean.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
					errMsgBean.setFileFormatError(true);
					errMsgBean.setValidateError(true);
					return errMsgBean;
				}
				
				for (NameCheckInputBean inputBean : nameCheckInputBeanList) {
					rowNum++;
					NameCheckOutputBean outputBean = new NameCheckOutputBean();
					try {
						outputBean = amlNameCheck.NameCheck(inputBean);
						if (outputBean != null) {
							nameCheckOutputBeanList.add(outputBean);
						} else {
							errMsgBean.setErrorCount(errMsgBean.getErrorCount() + 1);
							errMsgBean.setValidateError(true);
							errMsgBean.getErrorMsgStringList().add("Error in row" + rowNum + ", NameCheck Scan Fail");
							logger.info("NameCheck Output Error ::: " + inputBean.getUniqueKey());
						}
					} catch (Exception ex) {
						errMsgBean.setErrorCount(errMsgBean.getErrorCount() + 1);
						errMsgBean.setValidateError(true);
						errMsgBean.getErrorMsgStringList().add("Error in row" + rowNum + ", NameCheck Scan Fail");
						logger.info("NameCheck Error:::" + inputBean.getUniqueKey() + ", Error Message ::: "
								+ ex.getMessage());
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
					}
				}
				
				if (CollectionUtils.isEmpty(nameCheckOutputBeanList) == false) {
					logger.info("FtpRemittanceNameCheck nameCheckOutputBeanList:::" + nameCheckOutputBeanList.toString());
					// return 格式為：NC1RESULT_<CallingSystem>_<file name>_<yyyyMMdd>.h,
					// NC1RESULT_<CallingSystem>_<file name>_<yyyyMMdd>.d
					String primeFileName = StringUtils.removeStartIgnoreCase(newFileName, SwiftMtConst.UPLOAD_TYPE_FTP_REMITTANCE_IMPORT_FILE_PREFIX);
					primeFileName = StringUtils.removeEndIgnoreCase(primeFileName, ".d"); // 取出名字中間部份：<CallingSystem>_<file
					String resultFileName = SwiftMtConst.UPLOAD_TYPE_FTP_REMITTANCE_IMPORT_FILE_RESULT_PREFIX + primeFileName; // NCRESULT_<CallingSystem>_<filename>_<yyyyMMdd>
					String realResultFileName = SwiftMtConst.UPLOAD_TYPE_FTP_REMITTANCE_IMPORT_FILE_RESULT_PREFIX + fileKey;
					
					List<XOnlineNamecheckUpload> xOnlineNamecheckUploads = new ArrayList<>();
					xInvBlNcUploadRecord.setFilePath(filePath);
					xInvBlNcUploadRecord.setFileName(newFileName);
					xInvBlNcUploadRecord.setScanPeriodEnd(new Timestamp(System.currentTimeMillis()));
					boolean isOk = WriteResult(realResultFileName, nameCheckInputBeanList, nameCheckOutputBeanList,
							errMsgBean, filePath, xOnlineNamecheckUploads, fileKey, uploadType, callingUser, branchNum);
					if (!isOk) {
						logger.info("Write File Fail");
						errMsgBean.getErrorMsgStringList().add("Write File Fail");
						errMsgBean.setValidateError(true);
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						return errMsgBean;
					}

					xInvBlNcUploadRecord.setResultFile(String.format("%s.d", resultFileName));
					xInvBlNcUploadRecord.setResultPath(String.format("%s", filePath));
					
				} else {
					logger.info("Convert NameCheckInputBean Fail!");
					xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
					xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
					errMsgBean.setValidateError(true);
					return errMsgBean;
				}
			} catch (Exception e) {
				logger.error("Online Remittance NameCheck saveNCheckFile Exception : {}", e);
				xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
				xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_INTERNAL_SERVER_ERROR);
				errMsgBean.setValidateError(true);
				return errMsgBean;
			}
			result = errMsgBean;
		} else if (SwiftMtConst.UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT.equals(uploadType)) {
			needSave = true;
			xInvBlNcUploadRecord.setFileKey(fileKey);
			xInvBlNcUploadRecord.setScanPeriodStart(new Timestamp(System.currentTimeMillis()));
			xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
			xInvBlNcUploadRecord.setScanType(scanType);
			try {
				List<NameCheckInputBean> nameCheckInputBeanList = amlFtpExRemittanceNameCheck
						.FtpExRemittanceNameCheck(uploadFile, dataFileCharset, errMsgBean, branchNum, callingUser);
				logger.info("FtpExRemittanceNameCheck nameCheckInputBeanList:::" + nameCheckInputBeanList.toString());
				List<NameCheckOutputBean> nameCheckOutputBeanList = new ArrayList<NameCheckOutputBean>();
				if(nameCheckInputBeanList.isEmpty()) {
					xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
					xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
					errMsgBean.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
					errMsgBean.setFileFormatError(true);
					errMsgBean.setValidateError(true);
					return errMsgBean;
				}
				int rowNum = 0;
				for (NameCheckInputBean inputBean : nameCheckInputBeanList) {
					rowNum++;
					NameCheckOutputBean outputBean = new NameCheckOutputBean();
					try {
						outputBean = amlNameCheck.NameCheck(inputBean);
						if (outputBean != null) {
							nameCheckOutputBeanList.add(outputBean);
						} else {
							errMsgBean.setErrorCount(errMsgBean.getErrorCount() + 1);
							errMsgBean.setValidateError(true);
							errMsgBean.getErrorMsgStringList().add("Error in row" + rowNum + ", NameCheck Scan Fail");
							logger.info("NameCheck Output Error ::: " + inputBean.getUniqueKey());
						}
					} catch (Exception ex) {
						errMsgBean.setErrorCount(errMsgBean.getErrorCount() + 1);
						errMsgBean.setValidateError(true);
						errMsgBean.getErrorMsgStringList().add("Error in row" + rowNum + ", NameCheck Scan Fail");
						logger.info("NameCheck Error:::" + inputBean.getUniqueKey() + ", Error Message ::: "
								+ ex.getMessage());
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
					}
				}
				if (CollectionUtils.isEmpty(nameCheckOutputBeanList) == false) {
					logger.info("FtpExRemittanceNameCheck nameCheckOutputBeanList:::" + nameCheckOutputBeanList.toString());
					// return 格式為：NC2RESULT_<file name>.h,
					// NC2RESULT_<file name>.d
					String resultFileName = SwiftMtConst.UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT_FILE_RESULT_PREFIX + newFileName; // NC2RESULT_<file name>
					String realResultFileName = SwiftMtConst.UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT_FILE_RESULT_PREFIX + fileKey;
					
					List<XOnlineNamecheckUpload> xOnlineNamecheckUploads = new ArrayList<>();
					xInvBlNcUploadRecord.setFilePath(filePath);
					xInvBlNcUploadRecord.setFileName(newFileName);
					xInvBlNcUploadRecord.setScanPeriodEnd(new Timestamp(System.currentTimeMillis()));
					
					boolean isOk = WriteResult(realResultFileName, nameCheckInputBeanList, nameCheckOutputBeanList,
							errMsgBean, filePath, xOnlineNamecheckUploads, fileKey, uploadType, callingUser, branchNum);
					if (!isOk) {
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						errMsgBean.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						errMsgBean.setValidateError(true);
						logger.info("Write File Fail");
					}
					
					xInvBlNcUploadRecord.setResultFile(String.format("%s.d", resultFileName));
					xInvBlNcUploadRecord.setResultPath(String.format("%s", filePath));
					
				} else {
					logger.info("Convert NameCheckInputBean Fail!");
					xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
					xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
					errMsgBean.setValidateError(true);
				}
			} catch (Exception e) {
				xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
				xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_INTERNAL_SERVER_ERROR);
				errMsgBean.setValidateError(true);
				logger.error("Online Remittance NameCheck saveNCheckFile Exception : {}", e);
			}
			result = errMsgBean;
		}

		// To Check Data need Save Record
		if (needSave) {
			Timestamp now = new Timestamp(new Date().getTime());
			xInvBlNcUploadRecord.setCreateTime(now);
			xInvBlNcUploadRecord.setCreateUser(callingUser);
			xInvBlNcUploadRecord.setFileName(newFileName);
			xInvBlNcUploadRecord.setFilePath(filePath);
			xInvBlNcUploadRecord.setEnOrgFile(Base64Util.fileToBase64(new File(
					String.format("%s%s", filePath, xInvBlNcUploadRecord.getFileKey()))));
			
			if (SwiftMtConst.UPLOAD_TYPE_FTP_REMITTANCE_IMPORT.equals(uploadType))
				xInvBlNcUploadRecord.setEnResultFile(Base64Util
						.fileToBase64(new File(String.format("%s%s%s.d",
								xInvBlNcUploadRecord.getResultPath(),
								SwiftMtConst.UPLOAD_TYPE_FTP_REMITTANCE_IMPORT_FILE_RESULT_PREFIX,
								xInvBlNcUploadRecord.getFileKey()))));
			if (SwiftMtConst.UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT.equals(uploadType))
				xInvBlNcUploadRecord.setEnResultFile(Base64Util
						.fileToBase64(new File(String.format("%s%s%s.d",
								xInvBlNcUploadRecord.getResultPath(),
								SwiftMtConst.UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT_FILE_RESULT_PREFIX,
								xInvBlNcUploadRecord.getFileKey()))));
			if (SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT.equals(uploadType))
				xInvBlNcUploadRecord.setEnResultFile(Base64Util
						.fileToBase64(new File(String.format("%s%s%s.xlsx",
								xInvBlNcUploadRecord.getResultPath(),
								SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT_FILE_RESULT_PREFIX,
								xInvBlNcUploadRecord.getFileKey()))));
			xInvBlNcUploadRecord.setUpdateTime(now);
			xInvBlNcUploadRecord.setUploadType(uploadType);
			iXInvBlNcUploadRecordDao.save(xInvBlNcUploadRecord);
		}
		return result;
	}

	private boolean WriteXlsxResult(String fileName, List<NameCheckInputBean> nameCheckInputBeanList,
			List<NameCheckOutputBean> nameCheckOutputBeanList, NCOnlineErrMsgBean errMsgBean, String sourcePath,
			List<XOnlineNamecheckUpload> xOnlineNamecheckUploads, String fileKey, String type, String callingUser,
			String branchNum) {
		boolean result = true;
		File resultFileD = new File(sourcePath, String.format("%s.xlsx", fileName));
		FileOutputStream fileOut = null;
		XSSFWorkbook wb = new XSSFWorkbook();
		String sheetName = "RESULT";// name of sheet
		XSSFSheet sheet = wb.createSheet(sheetName);
		try {
			resultFileD.createNewFile();
			fileOut = FileUtils.openOutputStream(resultFileD);
			int index = 0, titleIndex = 0;
			
			XSSFRow row = sheet.createRow(index++);
			row.createCell(titleIndex++).setCellValue("<NcReferenceId>");
			row.createCell(titleIndex++).setCellValue("<UniqueKey>");
			row.createCell(titleIndex++).setCellValue("<ErrorCode>");
			row.createCell(titleIndex++).setCellValue("<ErrorMessage>");
			row.createCell(titleIndex++).setCellValue("<NcResult>");
			row.createCell(titleIndex++).setCellValue("<Route Rule>");
			row.createCell(titleIndex++).setCellValue("<Hit Seq>");
			row.createCell(titleIndex++).setCellValue("<Check Seq>");
			row.createCell(titleIndex++).setCellValue("<CheckResult>");
			row.createCell(titleIndex++).setCellValue("<Route Rule>");
			for (NameCheckOutputBean bean : nameCheckOutputBeanList) {
				
				XOnlineNamecheckUpload xOnlineNamecheckUpload = new XOnlineNamecheckUpload();
				xOnlineNamecheckUpload.setType("標準格式");
				List<NameCheckOutputDetail> detailList = bean.getSeq();
				String unikey = bean.getUniqueKey();
				XOnlineNamecheckUploadPK xOnlineNamecheckUploadPK = new XOnlineNamecheckUploadPK(unikey, index,
						fileKey);
				xOnlineNamecheckUpload.setId(xOnlineNamecheckUploadPK);
				xOnlineNamecheckUpload.setCallingUser(callingUser);
				xOnlineNamecheckUpload.setBranchNumber(branchNum);
				if (detailList != null && !detailList.isEmpty()) {
					for (int i = 0; i < detailList.size(); i++) {
						row = sheet.createRow(index++);
						int detailIndex = 0;
						xOnlineNamecheckUpload.setNcReferenceId(bean.getNcReferenceId());
						xOnlineNamecheckUpload.setCaseRk(bean.getCaseRk());
//						row.createCell(detailIndex++).setCellValue("Danniel");
						XSSFCell ncReferenceIdCell = row.createCell(detailIndex++);
						ncReferenceIdCell.setCellValue(bean.getNcReferenceId() != null ? bean.getNcReferenceId() : " ");
						XSSFCell ncUnikeyCell = row.createCell(detailIndex++);
						ncUnikeyCell.setCellValue(bean.getUniqueKey() != null ? bean.getUniqueKey() : " ");
						XSSFCell ncErrorCodeCell = row.createCell(detailIndex++);
						ncErrorCodeCell.setCellValue(bean.getErrorCode() != null ? bean.getErrorCode() : " ");
						XSSFCell ncErrorMessageCell = row.createCell(detailIndex++);
						ncErrorMessageCell.setCellValue(bean.getErrorMessage() != null ? bean.getErrorMessage() : " ");
						XSSFCell ncNCResultCell = row.createCell(detailIndex++);
						ncNCResultCell.setCellValue(bean.getNcResult() != null ? bean.getNcResult() : " ");
						XSSFCell ncRouteRuleCell = row.createCell(detailIndex++);
						ncRouteRuleCell.setCellValue(bean.getRouteRule() != null ? bean.getRouteRule() : " ");
						XSSFCell ncHitSeqCell = row.createCell(detailIndex++);
						if (bean.getHitSeq() == null) {
							ncHitSeqCell.setCellValue(" ");
						} else if (bean.getHitSeq().length() > 5) {
							ncHitSeqCell.setCellValue(bean.getHitSeq().split(",")[0]);
						} else {
							ncHitSeqCell.setCellValue(bean.getHitSeq());
						}
						XSSFCell ncCheckSeqCell = row.createCell(detailIndex++);
						ncCheckSeqCell.setCellValue(
								detailList.get(i).getCheckSeq() != null ? detailList.get(i).getCheckSeq() : " ");
						XSSFCell ncCheckResultCell = row.createCell(detailIndex++);
						ncCheckResultCell.setCellValue(
								detailList.get(i).getCheckResult() != null ? detailList.get(i).getCheckResult() : " ");
						XSSFCell ncRouteRuleDetailCell = row.createCell(detailIndex++);
						ncRouteRuleDetailCell.setCellValue(
								detailList.get(i).getRouteRule() != null ? detailList.get(i).getRouteRule() : " ");
					}
				} else {
					for (NameCheckInputBean nameCheckInputBean : nameCheckInputBeanList) {
						if (unikey != null && nameCheckInputBean.getUniqueKey().equals(unikey)) {
							for (int j = 0; j < nameCheckInputBean.getSeq().size(); j++) {
								row = sheet.createRow(index++);
								int detailIndex = 0;
								row.createCell(detailIndex++).setCellValue(bean.getNcReferenceId() != null ? bean.getNcReferenceId() : " ");
								row.createCell(detailIndex++).setCellValue(bean.getUniqueKey() != null ? bean.getUniqueKey() : " ");
								row.createCell(detailIndex++).setCellValue(bean.getErrorCode() != null ? bean.getErrorCode() : " ");
								row.createCell(detailIndex++).setCellValue(bean.getErrorMessage() != null ? bean.getErrorMessage() : " ");
								row.createCell(detailIndex++).setCellValue(bean.getNcResult() != null ? bean.getNcResult() : " ");
								row.createCell(detailIndex++).setCellValue(bean.getRouteRule() != null ? bean.getRouteRule() : " ");
								XSSFCell ncHitSeqCell = row.createCell(detailIndex++);
								if (bean.getHitSeq() == null) {
									ncHitSeqCell.setCellValue(" ");
								} else if (bean.getHitSeq().length() > 5) {
									ncHitSeqCell.setCellValue(bean.getHitSeq().split(",")[0]);
								} else {
									ncHitSeqCell.setCellValue(bean.getHitSeq());
								}
								row.createCell(detailIndex++).setCellValue(nameCheckInputBean.getSeq().get(j).getCheckSeq() != null
										? nameCheckInputBean.getSeq().get(j).getCheckSeq()
										: " ");
								row.createCell(detailIndex++).setCellValue(" ");
								row.createCell(detailIndex++).setCellValue(" ");
							}
						}
					}
				}
				xOnlineNamecheckUploads.add(xOnlineNamecheckUpload);
			}
			// write this workbook to an Outputstream.
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			iXOnlineNameCheckUploadDao.save(xOnlineNamecheckUploads);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = false;
			errMsgBean.getErrorMsgStringList().add("Write File Error");
		} finally {
			try {
				if (fileOut != null) {
					fileOut.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return result;
	}

	private boolean WriteResult(String fileName, List<NameCheckInputBean> nameCheckInputBeanList,
			List<NameCheckOutputBean> nameCheckOutputBeanList, NCOnlineErrMsgBean errMsgBean, String sourcePath,
			List<XOnlineNamecheckUpload> xOnlineNamecheckUploads, String fileKey, String type, String callingUser,
			String branchNum) {
		BufferedWriter writerD = null;
		boolean result = true;
		int successCount = 0, errorCount = 0;
		try {
			// sourcePath = "E:\\SAS\\Doc\\BOT\\AMLFTP\\OnlineResult\\";
			final File resultFileD = new File(sourcePath, String.format("%s.d", fileName));
			resultFileD.createNewFile();
			final boolean append = false;
			writerD = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFileD, append), "UTF-8"));
			final StringBuffer bfr = new StringBuffer();
			final StringBuffer detailBfr = new StringBuffer();
			final List<String> lines = new ArrayList<String>();
			int index = 0;
			for (NameCheckOutputBean bean : nameCheckOutputBeanList) {
				index++;
				XOnlineNamecheckUpload xOnlineNamecheckUpload = new XOnlineNamecheckUpload();
				switch (type) {
					case "8":
						xOnlineNamecheckUpload.setType("國內匯款");
						break;
					case "9":
						xOnlineNamecheckUpload.setType("國外匯款");
						break;
				}
				List<NameCheckOutputDetail> detailList = bean.getSeq();
				String unikey = bean.getUniqueKey();
				XOnlineNamecheckUploadPK xOnlineNamecheckUploadPK = new XOnlineNamecheckUploadPK(unikey, index,
						fileKey);
				xOnlineNamecheckUpload.setId(xOnlineNamecheckUploadPK);
				xOnlineNamecheckUpload.setCallingUser(callingUser);
				xOnlineNamecheckUpload.setBranchNumber(branchNum);
				if (detailList != null && !detailList.isEmpty()) {
					for (int i = 0; i < detailList.size(); i++) {
						xOnlineNamecheckUpload.setNcReferenceId(bean.getNcReferenceId());
						xOnlineNamecheckUpload.setCaseRk(bean.getCaseRk());
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
						errMsgBean.setSuccessCount(successCount++);
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
								errMsgBean.setErrorCount(errorCount++);
							}
						}
					}
				}
				xOnlineNamecheckUploads.add(xOnlineNamecheckUpload);
			}
			writerD.flush();
			iXOnlineNameCheckUploadDao.save(xOnlineNamecheckUploads);
		} catch (IOException e) {
			result = false;
			logger.error("File Write Error!");
			errMsgBean.getErrorMsgStringList().add("Write File Error");
		} finally {
			IOUtils.closeQuietly(writerD);
		}
		return result;
	}

	private static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	/**
	 * 解析標準格式之Excel
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<NameCheckInputBean> importXStandardFormat(InputStream file, NCOnlineErrMsgBean errMsgBean, String callingUser, String branchNum)
			throws IOException {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowTime = sdf.format(now) ;
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		List<NameCheckInputBean> xNCImportList = new ArrayList<NameCheckInputBean>();
		Map<String, NameCheckInputBean> ncBeanMap = new LinkedHashMap<String, NameCheckInputBean>();

		boolean isStartRecord = false;
		int seq = 1;
		// Document Error mark
		boolean isDocFormatErr = false;
		// Effective Row Count
		int effCount = 0;
		// Error Row Count
		int errCount = 0;
		// Succes Row Count
		int successCount = 0;
		// Row Error Msg List
		List<String> errMsgList = new ArrayList<String>();
		while (rowIterator.hasNext()) {
			boolean hasError = false;
			Row row = rowIterator.next();
			logger.info("row.getRowNum() = " + row.getRowNum());
			if(row.getRowNum() > 0) {
				// To Check Primary Key Column Empty
				if ((StringUtils.isBlank(ExcelUtils.getCellValueToString(row.getCell(7)))
						&& StringUtils.isBlank(ExcelUtils.getCellValueToString(row.getCell(8)))
						&& StringUtils.isBlank(ExcelUtils.getCellValueToString(row.getCell(9)))
						&& StringUtils.isBlank(ExcelUtils.getCellValueToString(row.getCell(10)))
						&& StringUtils.isBlank(ExcelUtils.getCellValueToString(row.getCell(11))))) {
					errMsgBean.getErrorMsgStringList()
							.add("Error in " + String.valueOf(row.getRowNum() + 1) + " Line, Require Scan Entity .");
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				// To Create Error Column Index List
				String unikey = ExcelUtils.getCellValueToString(row.getCell(6));
				FtpNameCheckInputBean ftpBean = new FtpNameCheckInputBean();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					isStartRecord = true;
					// To Get Cell String Value
					String word = com.sas.util.StringUtils.convertToHalfWidth(ExcelUtils.getCellValueToString(cell)).trim();					
					// 3. Screen Function
					if (cell.getColumnIndex() == 0) {
						if (word == null || "".equals(word) || StringUtils.isBlank(word)) {
							errMsgBean.getErrorMsgStringList().add(
									"Error in " + String.valueOf(row.getRowNum() + 1) + " Line, Require Screen Function.");
							hasError = true;
						} else {
							if (word.length() != 1 || (!SwiftMtConst.SCREEN_PROCESS_Account_Opening.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_Customer_Event.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_Transaction_Screening.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_Trade_Finance_Screening.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_SWIFT_Screening.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_BATCH_NAME_CHECKING.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_BATCH_TRANCTION_SCREENING.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_BATCH_TRADE_FINANCE_SCREENING.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_BATCH_SWIFT_SCREENING.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_ONLINE_NAME_CHECKING.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_BL_SCREENING.equals(word)
									&& !SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_INV_SCREENING.equals(word))) {
								errMsgBean.getErrorMsgStringList().add("Error in " + String.valueOf(row.getRowNum() + 1)
										+ " Line, Screen Function Error.");
								hasError = true;
							}
						}
						ftpBean.setScreenFunction(word);
						continue;
					}
					// 7. Unique Key
					if (cell.getColumnIndex() == 1) {
						if (StringUtils.isBlank(word)) {
							errMsgBean.getErrorMsgStringList()
									.add("Error in " + String.valueOf(row.getRowNum() + 1) + " Line, Require Unique Key.");
							hasError = true;
						}
						ftpBean.setUniqueKey(word);
						continue;
					}
					// 8. Party Number
					if (cell.getColumnIndex() == 2) {
						ftpBean.setPartyNumber(word);
						continue;
					}
					// 9. Check Seq.
					if (cell.getColumnIndex() == 3) {
						if (StringUtils.isBlank(word)) {
							errMsgBean.getErrorMsgStringList()
									.add("Error in " + String.valueOf(row.getRowNum() + 1) + " Line, Require Check Seq.");
							hasError = true;
						}
						ftpBean.setCheckSeq(word);
						continue;
					}
					// 10. Entity Type
					if (cell.getColumnIndex() == 4) {
						if (StringUtils.isBlank(word)) {
							errMsgBean.getErrorMsgStringList()
									.add("Error in " + String.valueOf(row.getRowNum() + 1) + " Line, Require Entity Type.");
							hasError = true;
						}
						ftpBean.setEntityType(word);
						continue;
					}
					// 11. Entity Relationship
					if (cell.getColumnIndex() == 5) {
						if (StringUtils.isBlank(word)) {
							errMsgBean.getErrorMsgStringList().add("Error in " + String.valueOf(row.getRowNum() + 1)
									+ " Line, Require Entity Relationship.");
							hasError = true;
						}
						ftpBean.setEntityRelationship(word);
						continue;
					}
					// 12. Entity Relationship Desc
					if (cell.getColumnIndex() == 6) {
						ftpBean.setEntityRelationshipDesc(word);
					}
					// 13. English Name
					if (cell.getColumnIndex() == 7) {
						ftpBean.setEnglishName(word);
						continue;
					}
					// 14. None English Name
					if (cell.getColumnIndex() == 8) {
						ftpBean.setNonEnglishName(word);
						continue;
					}
					// 15. CCC Code
					if (cell.getColumnIndex() == 9) {
						ftpBean.setCccCode(word);
						continue;
					}
					// 16. ID Number
					if (cell.getColumnIndex() == 10) {
						ftpBean.setIdNumber(word);
						continue;
					}
					// 17. Bic/Swift Code
					if (cell.getColumnIndex() == 11) {
						ftpBean.setBicSwiftCode(word);
						continue;
					}
					// 18. Country
					if (cell.getColumnIndex() == 12) {
						ftpBean.setCountry(word);
						continue;
					}
					// 19. Year of Birth
					if (cell.getColumnIndex() == 13) {
						ftpBean.setYearofBirth(word);
						continue;
					}
				}
				
				ftpBean.setCallingSystem("SASUL");
				ftpBean.setRealtimeFlag("Y");
				ftpBean.setCallingUser(callingUser);
				ftpBean.setBranchNumber(branchNum);
				String newUnikey = String.format("%s%s%s", ftpBean.getUniqueKey(), nowTime, "SASUL");
				ftpBean.setUniqueKey(newUnikey);
				ftpBean.setUnit("");
	
				if (ncBeanMap.containsKey(newUnikey)) {
					NameCheckInputBean input = ncBeanMap.get(newUnikey);
					List<NameCheckInputDetailBean> inputSeq = input.getSeq();
					inputSeq.add(new NameCheckInputDetailBean(ftpBean));
				} else {
					NameCheckInputBean input = new NameCheckInputBean(ftpBean);
					List<NameCheckInputDetailBean> inputSeq = new ArrayList<NameCheckInputDetailBean>();
					input.setSeq(inputSeq);
					inputSeq.add(new NameCheckInputDetailBean(ftpBean));
					ncBeanMap.put(input.getUniqueKey(), input);
				}
	
				if (isStartRecord) {
					if (hasError) {
						errCount++;
					} else {
						successCount++;
					}
					errMsgBean.setSuccessCount(successCount);
					errMsgBean.setErrorCount(errCount);
				}
			}
		}
		for (Entry<String, NameCheckInputBean> entry : ncBeanMap.entrySet()) {
			logger.info(entry.getKey() + "/" + entry.getValue());
			xNCImportList.add(entry.getValue());
		}
		// To Close Excel WorkBook
		workbook.close();
		return xNCImportList;
	}
}
