package com.sas.tradeFinance.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.fcfcore.IAccountIntegrationDao;
import com.sas.db.aml.orm.fcfcore.AccountIntegration;
import com.sas.db.wlf.dao.IXInvBlNcUploadRecordDao;
import com.sas.db.wlf.dao.tf.IXBlExportDao;
import com.sas.db.wlf.dao.tf.IXBlImportDao;
import com.sas.db.wlf.dao.tf.IXInvExportDao;
import com.sas.db.wlf.dao.tf.IXInvImportDao;
import com.sas.db.wlf.orm.XInvBlNcUploadRecord;
import com.sas.db.wlf.orm.tf.XBlExport;
import com.sas.db.wlf.orm.tf.XBlImport;
import com.sas.db.wlf.orm.tf.XInvExport;
import com.sas.db.wlf.orm.tf.XInvImport;
import com.sas.db.wlf.orm.tf.XOnlineNamecheckUpload;
import com.sas.db.wlf.orm.tf.XOnlineNamecheckUploadPK;
import com.sas.tradeFinance.bean.XBlExportBean;
import com.sas.tradeFinance.bean.XBlImportBean;
import com.sas.tradeFinance.bean.XBlInvErrMsgBean;
import com.sas.tradeFinance.bean.XBlInvValidateBean;
import com.sas.tradeFinance.bean.XInvExportBean;
import com.sas.tradeFinance.bean.XInvImportBean;
import com.sas.tradeFinance.upload.ImportXBlExport;
import com.sas.tradeFinance.upload.ImportXBlImport;
import com.sas.tradeFinance.upload.ImportXInvExport;
import com.sas.tradeFinance.upload.ImportXInvImport;
import com.sas.util.AmlConfiguration;
import com.sas.util.Base64Util;
import com.sas.util.EncodingDetect;
import com.sas.util.FTPUtil;
import com.sas.webservice.nameCheck.AmlNameCheck;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputDetail;

@Service
public class AmlTradeFinanceCheckService {
	private static final Logger logger = LoggerFactory.getLogger(AmlTradeFinanceCheckService.class);
	@Autowired
	AmlNameCheck amlNameCheck;
	@Autowired
	IXInvExportDao iXInvExportDao;
	@Autowired
	IXInvImportDao iXInvImportDao;
	@Autowired
	IXBlExportDao iXBlExportDao;
	@Autowired
	IXBlImportDao iXBlImportDao;
	@Autowired
	IXInvBlNcUploadRecordDao iXInvBlNcUploadRecordDao;
	@Autowired
	XInvCreateCase xInvCreateCase;
	@Autowired
	IAccountIntegrationDao iAccountIntegrationDao;
	
	/**
	 * 處理貿易融資、手動名單檔案上傳資料處理
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
	@SuppressWarnings("unchecked")
	@Transactional
	public XBlInvErrMsgBean saveNCheckFile(String uploadType, String callingUser, MultipartFile multipartFile, String scanType, String startDate, String endDate) throws IOException, ParseException{
		XBlInvErrMsgBean result = new XBlInvErrMsgBean();
		String fileKey = UUID.randomUUID().toString().toUpperCase();
		String dataFileCharset = "UTF-8", branchNum = "";
		String filePath = AmlConfiguration.getString(String.format("com.sas.upload.filePath.%s", uploadType));
		String newFileName = String.format("%s_%s", new Date().getTime(), multipartFile.getOriginalFilename());
		String newFileLocation = String.format("%s%s", filePath, newFileName);
		String realResultFileName =  SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT_FILE_RESULT_PREFIX + fileKey;
		String resultFileName = String.format("%s.xlsx", realResultFileName);
		
		XInvBlNcUploadRecord xInvBlNcUploadRecord = new XInvBlNcUploadRecord();
		
		File file = new File(newFileLocation);
		if (file.exists()) {
			File newFile = new File(newFileLocation);
			file.renameTo(newFile);
		} else {
			FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
		}
		
		File uploadFile = multipartToFile(multipartFile);
		InputStream targetStream = new FileInputStream(uploadFile);
		try {
			dataFileCharset = EncodingDetect.getJavaEncode(uploadFile);
			boolean isAcceptCharset = false;
			String[] charsets = AmlConfiguration.getString(SwiftMtConst.COM_SAS_CHARSET_DETECHED).split(",");
			isAcceptCharset = Arrays.asList(charsets).contains(dataFileCharset);
			if(dataFileCharset == null || "".equals(dataFileCharset) || isAcceptCharset == false) {
				xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
				xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_FILE_CHARSET_ERROR);
				result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_FILE_CHARSET_ERROR);
				result.setValidateError(true);
				return result;
			}
		} catch (Exception ex) {
			xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
			xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_FILE_CHARSET_ERROR);
			result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_FILE_CHARSET_ERROR);
			result.setValidateError(true);
			logger.error(ex.getMessage(), ex);
			return result;
		}
		
		AccountIntegration account = iAccountIntegrationDao.findOne(callingUser);
		if (account == null) {
			xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
			xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_ACCOUNT_BRANCH_NOT_EXIST_ERROR);
			result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_ACCOUNT_BRANCH_NOT_EXIST_ERROR);
			result.setValidateError(true);
			return result;
		} else {
			branchNum = account.getDeptNo();
		}
		//Excute save mark
		boolean needSave = false;
		
		if(SwiftMtConst.UPLOAD_TYPE_BL_IMPORT.equals(uploadType)){
			XBlInvValidateBean xBlInvValidateBean = ImportXBlImport.importXBlImport(targetStream);
			List<XBlImportBean> xBlBeanList = (ArrayList<XBlImportBean>)xBlInvValidateBean.getDataList();
			if(!xBlInvValidateBean.isDocStatusErr()){
				if(!CollectionUtils.isEmpty(xBlBeanList)){
					List<XBlImport> xBlImportList = new ArrayList<XBlImport>();
					for(XBlImportBean bean : xBlBeanList){
						xBlImportList.add(new XBlImport(bean));
					}
					//起案流程
					List<Object> nameCheckList = checkBLImport(xBlImportList, callingUser, fileKey, result);
					List<NameCheckInputBean> nameCheckInputBeanList = (List<NameCheckInputBean>) (nameCheckList != null ? nameCheckList.get(0) : new ArrayList<>());
					if(nameCheckInputBeanList == null) {
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
						result.setValidateError(true);
					}
					
					List<NameCheckOutputBean> nameCheckOutputBeanList = (List<NameCheckOutputBean>) (nameCheckList != null ? nameCheckList.get(1) : new ArrayList<>());
					if(nameCheckOutputBeanList == null) {
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
						result.setValidateError(true);
					}
					
					boolean isOk = WriteXlsxResult(realResultFileName, nameCheckInputBeanList, nameCheckOutputBeanList,
							result, filePath);
					if (!isOk) {
						logger.info("BL Import Save Result File Fail");
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						result.setValidateError(true);
					}
				}
				xInvBlNcUploadRecord.setFileKey(fileKey);
				xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
			}
			
			needSave = !xBlInvValidateBean.isDocStatusErr();
			result = xBlInvValidateBean.getRespUIBean();
		}else if(SwiftMtConst.UPLOAD_TYPE_BL_EXPORT.equals(uploadType)){
			XBlInvValidateBean xBlInvValidateBean = ImportXBlExport.importXBLExport(targetStream);
			List<XBlExportBean> xBlBeanList = (ArrayList<XBlExportBean>)xBlInvValidateBean.getDataList();
			if(!xBlInvValidateBean.isDocStatusErr()){
				if(!CollectionUtils.isEmpty(xBlBeanList)){
					List<XBlExport> xBlExportList = new ArrayList<XBlExport>();
					for(XBlExportBean bean : xBlBeanList){
						xBlExportList.add(new XBlExport(bean));
					}
					//起案流程
					List<Object> nameCheckList = checkBLExport(xBlExportList, callingUser, fileKey, result);
					List<NameCheckInputBean> nameCheckInputBeanList = (List<NameCheckInputBean>) (nameCheckList != null ? nameCheckList.get(0) : new ArrayList<>());
					if(nameCheckInputBeanList == null) {
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
						result.setValidateError(true);
					}
						
					List<NameCheckOutputBean> nameCheckOutputBeanList = (List<NameCheckOutputBean>) (nameCheckList != null ? nameCheckList.get(1) : new ArrayList<>());
					if(nameCheckOutputBeanList == null) {
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
						result.setValidateError(true);
					}
					
					boolean isOk = WriteXlsxResult(realResultFileName, nameCheckInputBeanList, nameCheckOutputBeanList,
							result, filePath);
					if (!isOk) {
						logger.info("BL Export Save Result File Fail");
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						result.setValidateError(true);
					}
				}
				xInvBlNcUploadRecord.setFileKey(fileKey);
				xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
			}
			
			needSave = !xBlInvValidateBean.isDocStatusErr();
			result = xBlInvValidateBean.getRespUIBean();
		}else if(SwiftMtConst.UPLOAD_TYPE_INV_IMPORT.equals(uploadType)){
			XBlInvValidateBean xBlInvValidateBean = ImportXInvImport.importXInvImport(targetStream);
			List<XInvImportBean> xBlBeanList = (ArrayList<XInvImportBean>)xBlInvValidateBean.getDataList();
			if(!xBlInvValidateBean.isDocStatusErr()){
				if(!CollectionUtils.isEmpty(xBlBeanList)){
					List<XInvImport> xInvImportList = new ArrayList<XInvImport>();
					for(XInvImportBean bean : xBlBeanList){
						xInvImportList.add(new XInvImport(bean));
					}
					//起案流程
					List<Object> nameCheckList = checkInvImport(xInvImportList, callingUser, fileKey, result);
					List<NameCheckInputBean> nameCheckInputBeanList = (List<NameCheckInputBean>) (nameCheckList != null ? nameCheckList.get(0) : new ArrayList<>());
					if(nameCheckInputBeanList == null) {
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
						result.setValidateError(true);
					}
					
					List<NameCheckOutputBean> nameCheckOutputBeanList = (List<NameCheckOutputBean>) (nameCheckList != null ? nameCheckList.get(1) : new ArrayList<>());
					if(nameCheckOutputBeanList == null) {
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
						result.setValidateError(true);
					}
					
					boolean isOk = WriteXlsxResult(realResultFileName, nameCheckInputBeanList, nameCheckOutputBeanList,
							result, filePath);
					if (!isOk) {
						logger.info("Inv Import Save Result File Fail");
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						result.setValidateError(true);
					}
				}
				xInvBlNcUploadRecord.setFileKey(fileKey);
				xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
			}
			
			needSave = !xBlInvValidateBean.isDocStatusErr();
			result = xBlInvValidateBean.getRespUIBean();
		}else if(SwiftMtConst.UPLOAD_TYPE_INV_EXPORT.equals(uploadType)){
			XBlInvValidateBean xBlInvValidateBean = ImportXInvExport.importXinvExport(targetStream);
			List<XInvExportBean> xBlBeanList = (ArrayList<XInvExportBean>)xBlInvValidateBean.getDataList();
			if(!xBlInvValidateBean.isDocStatusErr()){
				if(!CollectionUtils.isEmpty(xBlBeanList)){
					List<XInvExport> xInvExportList = new ArrayList<XInvExport>();
					for(XInvExportBean bean : xBlBeanList){
						xInvExportList.add(new XInvExport(bean));
					}
					//起案流程
					List<Object> nameCheckList = checkInvExport(xInvExportList, callingUser, fileKey, result);
					List<NameCheckInputBean> nameCheckInputBeanList = (List<NameCheckInputBean>) (nameCheckList != null ? nameCheckList.get(0) : new ArrayList<>());
					if(nameCheckInputBeanList == null) {
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR);
						result.setValidateError(true);
					}
					
					
					List<NameCheckOutputBean> nameCheckOutputBeanList = (List<NameCheckOutputBean>) (nameCheckList != null ? nameCheckList.get(1) : new ArrayList<>());
					if(nameCheckOutputBeanList == null) {
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR);
						result.setValidateError(true);
					}
					
					boolean isOk = WriteXlsxResult(realResultFileName, nameCheckInputBeanList, nameCheckOutputBeanList,
							result, filePath);
					if (!isOk) {
						logger.info("Inv Export Save Result File Fail");
						xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
						xInvBlNcUploadRecord.setResultFile(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						result.getErrorMsgStringList().add(SwiftMtConst.FTP_NC_SAVE_RESULT_FILE_ERROR);
						result.setValidateError(true);
					}
				}			
				xInvBlNcUploadRecord.setFileKey(fileKey);
				xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_Y);
			}
			
			needSave = !xBlInvValidateBean.isDocStatusErr();
			result = xBlInvValidateBean.getRespUIBean();
		}else if(SwiftMtConst.UPLOAD_TYPE_NC_MANUAL.equals(uploadType)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			xInvBlNcUploadRecord.setFileKey(fileKey);
			xInvBlNcUploadRecord.setScanPeriodStart(new Timestamp(sdf.parse(startDate).getTime()));
			xInvBlNcUploadRecord.setScanPeriodEnd(new Timestamp(sdf.parse(endDate).getTime()));
			xInvBlNcUploadRecord.setScanStatus(SwiftMtConst.SCAN_STATUS_N);
			xInvBlNcUploadRecord.setScanType(scanType);
			
			result = new XBlInvErrMsgBean(false, 0, 0, 0, null);
		}
		
		// To Check Data need Save Record
		if(needSave){
//			multipartFile.transferTo(new File(newFileLocation));
			Timestamp now = new Timestamp(new Date().getTime());
			xInvBlNcUploadRecord.setCreateTime(now);
			xInvBlNcUploadRecord.setCreateUser(callingUser);
			xInvBlNcUploadRecord.setFileName(newFileName);
			xInvBlNcUploadRecord.setFilePath(filePath);
			xInvBlNcUploadRecord.setResultFile(resultFileName);
			xInvBlNcUploadRecord.setResultPath(filePath);
			xInvBlNcUploadRecord.setEnOrgFile(Base64Util.fileToBase64(new File(
					String.format("%s%s", filePath, newFileName))));
			if (StringUtils.endsWith(xInvBlNcUploadRecord.getResultFile(),
					".xlsx"))
				xInvBlNcUploadRecord.setEnResultFile(Base64Util
						.fileToBase64(new File(String.format("%s%s", filePath,
								resultFileName))));
			xInvBlNcUploadRecord.setUpdateTime(now);
			xInvBlNcUploadRecord.setUploadType(uploadType);
			iXInvBlNcUploadRecordDao.save(xInvBlNcUploadRecord);
		}
		return result;
	}
	
	private List<Object> checkInvExport(List<XInvExport> xInvExportList, String callingUser, String fileKey,  XBlInvErrMsgBean errMsgBean){
		List<Object> listOfNameCheck = new ArrayList<>();
		List<NameCheckInputBean> nameCheckInputBeanList = new ArrayList<>();
		List<NameCheckOutputBean> nameCheckOutputBeanList = new ArrayList<>();
		
		for(XInvExport xInvExport : xInvExportList){
			NameCheckInputBean nameCheckInputBean = setInvExportNameCheckInputBean(xInvExport, callingUser); 
			nameCheckInputBeanList.add(nameCheckInputBean);
			NameCheckOutputBean nameCheckOutputBean = doInvExportCheck(nameCheckInputBean, callingUser, fileKey);
			nameCheckOutputBeanList.add(nameCheckOutputBean);
			xInvExport.setCaseRk(nameCheckOutputBean.getCaseRk());
			xInvExport.getId().setFileKey(fileKey);
			Timestamp ts = new Timestamp(new Date().getTime());
			xInvExport.setCreateDttm(ts);
			xInvExport.setCreateUser(callingUser);
			iXInvExportDao.save(xInvExport);
		}
//		for(XInvExport xInvExport : xInvExportList){
//			xInvCreateCase.createInvExportCase(xInvExport);
//		}
		listOfNameCheck.add(nameCheckInputBeanList);
		listOfNameCheck.add(nameCheckOutputBeanList);
		return listOfNameCheck;
	}
	
	private NameCheckOutputBean doInvExportCheck(NameCheckInputBean inputBean, String callingUser, String uniqueKey){
		logger.info("xInvExport nameCheckinputBean before namecheck:"+ inputBean);	
		NameCheckOutputBean nameCheckOutputBean = amlNameCheck.NonTransactionNameCheck(inputBean, 0);
		logger.info("xInvExport return nameCheckOutputBean after namecheck:" + nameCheckOutputBean);
		return nameCheckOutputBean;
	}
	
	private List<Object> checkInvImport(List<XInvImport> xInvImportList, String callingUser, String fileKey, XBlInvErrMsgBean errMsgBean){
		List<Object> listOfNameCheck = new ArrayList<>();
		List<NameCheckInputBean> nameCheckInputBeanList = new ArrayList<>();
		List<NameCheckOutputBean> nameCheckOutputBeanList = new ArrayList<>();
		
		for(XInvImport xInvImport : xInvImportList){
			NameCheckInputBean nameCheckInputBean = setInvImportNameCheckInputBean(xInvImport, callingUser); 
			nameCheckInputBeanList.add(nameCheckInputBean);
			NameCheckOutputBean nameCheckOutputBean = doInvImportCheck(nameCheckInputBean, callingUser, fileKey);
			nameCheckOutputBeanList.add(nameCheckOutputBean);
			xInvImport.setCaseRk(nameCheckOutputBean.getCaseRk());
			xInvImport.getId().setFileKey(fileKey);
			Timestamp ts = new Timestamp(new Date().getTime());
			xInvImport.setCreateDttm(ts);
			xInvImport.setCreateUser(callingUser);
			iXInvImportDao.save(xInvImport);
		}
//		for(XInvImport xInvImport : xInvImportList){
//			xInvCreateCase.createInvImportCase(xInvImport);
//		}
		listOfNameCheck.add(nameCheckInputBeanList);
		listOfNameCheck.add(nameCheckOutputBeanList);
		return listOfNameCheck;
	}
	
	private NameCheckOutputBean doInvImportCheck(NameCheckInputBean inputBean, String callingUser, String uniqueKey){
		logger.info("XInvImport nameCheckinputBean before namecheck:"+ inputBean);	
		NameCheckOutputBean nameCheckOutputBean = amlNameCheck.NonTransactionNameCheck(inputBean, 0);
		logger.info("XInvImport return nameCheckOutputBean after namecheck:"+nameCheckOutputBean);
		return nameCheckOutputBean;
	}
	
	private List<Object> checkBLExport(List<XBlExport> xBlExportList, String callingUser, String fileKey, XBlInvErrMsgBean errMsgBean){
		List<Object> listOfNameCheck = new ArrayList<>();
		List<NameCheckInputBean> nameCheckInputBeanList = new ArrayList<>();
		List<NameCheckOutputBean> nameCheckOutputBeanList = new ArrayList<>();
		for(XBlExport xBlExport : xBlExportList){
			NameCheckInputBean nameCheckInputBean = setBLExportNameCheckInputBean(xBlExport, callingUser); 
			nameCheckInputBeanList.add(nameCheckInputBean);
			NameCheckOutputBean nameCheckOutputBean = doBLExportCheck(nameCheckInputBean, callingUser);
			nameCheckOutputBeanList.add(nameCheckOutputBean);
			xBlExport.setCaseRk(nameCheckOutputBean.getCaseRk());
			xBlExport.getId().setFileKey(fileKey);
			iXBlExportDao.save(xBlExport);
		}
		listOfNameCheck.add(nameCheckInputBeanList);
		listOfNameCheck.add(nameCheckOutputBeanList);
		return listOfNameCheck;
	}
	
	private NameCheckOutputBean doBLExportCheck(NameCheckInputBean inputBean, String callingUser){
		logger.info("xBlExport nameCheckinputBean before namecheck:"+ inputBean);		
		NameCheckOutputBean nameCheckOutputBean = amlNameCheck.NonTransactionNameCheck(inputBean, 0);
		logger.info("xBlExport return nameCheckOutputBean after namecheck:"+nameCheckOutputBean);		
		return nameCheckOutputBean;
	}
	
	@SuppressWarnings("rawtypes")
	private List<Object> checkBLImport(List<XBlImport> xBlImportList, String callingUser, String fileKey, XBlInvErrMsgBean errMsgBean){
		List<Object> listOfNameCheck = new ArrayList<>();
		List<NameCheckInputBean> nameCheckInputBeanList = new ArrayList<>();
		List<NameCheckOutputBean> nameCheckOutputBeanList = new ArrayList<>();
		for(XBlImport xBlImport : xBlImportList){
			NameCheckInputBean nameCheckInputBean = setBLImportNameCheckInputBean(xBlImport, callingUser); 
			nameCheckInputBeanList.add(nameCheckInputBean);
			NameCheckOutputBean nameCheckOutputBean = doBLImportCheck(nameCheckInputBean, callingUser);
			nameCheckOutputBeanList.add(nameCheckOutputBean);
			if(nameCheckOutputBean != null) {
				
			}
			
			xBlImport.getId().setFileKey(fileKey);		
			iXBlImportDao.save(xBlImport);
		}
		listOfNameCheck.add(nameCheckInputBeanList);
		listOfNameCheck.add(nameCheckOutputBeanList);
		return listOfNameCheck;
	}
	
	private NameCheckOutputBean doBLImportCheck(NameCheckInputBean inputBean, String callingUser){
		logger.info("XBlImport nameCheckinputBean before namecheck:" + inputBean);
		NameCheckOutputBean nameCheckOutputBean = amlNameCheck.NonTransactionNameCheck(inputBean, 0);
		logger.info("XBlImport return nameCheckOutputBean after namecheck:" + nameCheckOutputBean);
		return nameCheckOutputBean;
	}
	
	private NameCheckInputBean setBLExportNameCheckInputBean(XBlExport xBlExport, String callingUser){
		String callingSystem = AmlConfiguration.getString(SwiftMtConst.UPLOAD_TYPE_TRADEFINANCE_IMPORT_FILE_CALLINGSYSTEM);
		String screenProcess = SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_BL_SCREENING;
		String genAlertFlag = AmlConfiguration.getString(SwiftMtConst.UPLOAD_TYPE_TRADEFINANCE_IMPORT_FILE_GENALERTFLAG);
		Date transactionDate = new Date();
		String uniqueKey = xBlExport.getBlNo();
//		String referenceNumber = String.format("%s%s%s%s", xBlExport.getScrNo(), xBlExport.getCurrency(), xBlExport.getOurRefNo(), xBlExport.getSeqNo());
		String referenceNumber =String.valueOf((int) (new Date().getTime()/1000));
		NameCheckInputBean input = new NameCheckInputBean();
		AccountIntegration account = iAccountIntegrationDao.findOne(callingUser);
		String deptNo = account != null ? account.getDeptNo() : "";
		List<NameCheckInputDetailBean> detailList = setBLExportNameCheckInputDetailBeanList(xBlExport);
		input.setSeq(detailList);
		
		input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_NAMECHECK);
		input.setCallingSystem(callingSystem);
		input.setScreenProcess(screenProcess);
		input.setCallingUser(callingUser);
		input.setBusinessUnitID("");
		input.setBranchNumber(deptNo);
		input.setGenAlertFlag(genAlertFlag);
		input.setTransactionDate(transactionDate);
		input.setUniqueKey(uniqueKey);
		input.setReferenceNumber(referenceNumber);
		logger.debug(input.toString());
		return input;
	}
	
	private List<NameCheckInputDetailBean> setBLExportNameCheckInputDetailBeanList(XBlExport xBlExport){
		List<NameCheckInputDetailBean> detailList = new ArrayList<NameCheckInputDetailBean>();
		detailList.add(new NameCheckInputDetailBean("1", "09", "09", "Shipper/Exporter", xBlExport.getShipper()));
		if(xBlExport.getConsignee() != null && !"".equals(xBlExport.getConsignee()) && !SwiftMtConst.TF_TO_ORDER.equalsIgnoreCase(xBlExport.getConsignee())){
			detailList.add(new NameCheckInputDetailBean("2", "09", "09", "Consignee", xBlExport.getConsignee()));
		}
		if(xBlExport.getNotifyParty() != null && !"".equals(xBlExport.getNotifyParty())){
			detailList.add(new NameCheckInputDetailBean("3", "09", "09", "Notify Party", xBlExport.getNotifyParty()));
		}
		if(xBlExport.getSecondNotifyParty() != null && !"".equals(xBlExport.getSecondNotifyParty())){
			detailList.add(new NameCheckInputDetailBean("4", "09", "09", "Second Notify Party", xBlExport.getSecondNotifyParty()));
		}
		if(xBlExport.getShippingCompany() != null && !"".equals(xBlExport.getShippingCompany())){
			detailList.add(new NameCheckInputDetailBean("5", "09", "09", "Shipping Company", xBlExport.getShippingCompany()));
		}
		if(xBlExport.getForworder() != null && !"".equals(xBlExport.getForworder())){
			detailList.add(new NameCheckInputDetailBean("6", "09", "09", "Forworder", xBlExport.getForworder()));
		}
		if(xBlExport.getShippingAgent() != null && !"".equals(xBlExport.getShippingAgent())){
			detailList.add(new NameCheckInputDetailBean("7", "09", "09", "Shipping Agent", xBlExport.getShippingAgent()));
		}
		if(xBlExport.getShipper() != null && !"".equals(xBlExport.getShipper())){
			detailList.add(new NameCheckInputDetailBean("8", "08", "09", "Country of Origin", xBlExport.getShipper()));
		}
		if(xBlExport.getPlaceOfReceipt() != null && !"".equals(xBlExport.getPlaceOfReceipt())){
			detailList.add(new NameCheckInputDetailBean("9", "08", "09", "Place of Receipt", xBlExport.getPlaceOfReceipt()));
		}
		if(xBlExport.getCountryOfReceipt() != null && !"".equals(xBlExport.getCountryOfReceipt())){
			detailList.add(new NameCheckInputDetailBean("10", "08", "09", "Country of Receipt", xBlExport.getCountryOfReceipt()));
		}
		if(xBlExport.getPlaceOfDelievery() != null && !"".equals(xBlExport.getPlaceOfDelievery())){
			detailList.add(new NameCheckInputDetailBean("11", "08", "09", "Place of Delivery", xBlExport.getPlaceOfDelievery()));
		}
		if(xBlExport.getCountryOfDelivery() != null && !"".equals(xBlExport.getCountryOfDelivery())){
			detailList.add(new NameCheckInputDetailBean("12", "08", "09", "Country of Delivery", xBlExport.getCountryOfDelivery()));
		}
		if(xBlExport.getVessel() != null && !"".equals(xBlExport.getVessel())){
			detailList.add(new NameCheckInputDetailBean("13", "06", "09", "Vessel", xBlExport.getVessel()));
		}
		if(xBlExport.getPreCarriageVessel() != null && !"".equals(xBlExport.getPreCarriageVessel())){
			detailList.add(new NameCheckInputDetailBean("14", "06", "09", "Pre Carriage Vessel", xBlExport.getPreCarriageVessel()));
		}
		if(xBlExport.getPortOfLanding() != null && !"".equals(xBlExport.getPortOfLanding())){
			detailList.add(new NameCheckInputDetailBean("15", "08", "09", "Port of Loading", xBlExport.getPortOfLanding()));
		}
		if(xBlExport.getCountryOfLanding() != null && !"".equals(xBlExport.getCountryOfLanding())){
			detailList.add(new NameCheckInputDetailBean("16", "08", "09", "Country of Loading", xBlExport.getCountryOfLanding()));
		}
		if(xBlExport.getPortOfDischarge() != null && !"".equals(xBlExport.getPortOfDischarge())){
			detailList.add(new NameCheckInputDetailBean("17", "08", "09", "Port of Discharge", xBlExport.getPortOfDischarge()));
		}
		if(xBlExport.getCountryOfDischarge() != null && !"".equals(xBlExport.getCountryOfDischarge())){
			detailList.add(new NameCheckInputDetailBean("18", "08", "09", "Country of Discharge", xBlExport.getCountryOfDischarge()));
		}
		if(xBlExport.getTranshipmentPort() != null && !"".equals(xBlExport.getTranshipmentPort())){
			detailList.add(new NameCheckInputDetailBean("19", "08", "09", "Transhipment Port", xBlExport.getTranshipmentPort()));
		}
		if(xBlExport.getCountryOfTranshipment() != null && !"".equals(xBlExport.getCountryOfTranshipment())){
			detailList.add(new NameCheckInputDetailBean("20", "08", "09", "Country of Transhipment", xBlExport.getCountryOfTranshipment()));
		}
		
		return detailList;
	}
	
	private NameCheckInputBean setInvExportNameCheckInputBean(XInvExport xInvExport, String callingUser){
		String callingSystem = AmlConfiguration.getString(SwiftMtConst.UPLOAD_TYPE_TRADEFINANCE_IMPORT_FILE_CALLINGSYSTEM);
		String screenProcess = SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_BL_SCREENING;
		String genAlertFlag = AmlConfiguration.getString(SwiftMtConst.UPLOAD_TYPE_TRADEFINANCE_IMPORT_FILE_GENALERTFLAG);
		Date transactionDate = new Date();
		String uniqueKey = xInvExport.getId().getUniqueKey();
//		String referenceNumber = String.format("%s%s%s%s", xBlImport.getScrNo(), xBlImport.getType(), xBlImport.getLCNo(), xBlImport.getIbNo());
		String referenceNumber =String.valueOf((int) (new Date().getTime()/1000));
		NameCheckInputBean input = new NameCheckInputBean();
		AccountIntegration account = iAccountIntegrationDao.findOne(callingUser);
		String deptNo = account != null ? account.getDeptNo() : "";
		List<NameCheckInputDetailBean> detailList = setInvExportNameCheckInputDetailBeanList(xInvExport);
		input.setSeq(detailList);
		
		input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_NAMECHECK);
		input.setCallingSystem(callingSystem);
		input.setScreenProcess(screenProcess);
		input.setCallingUser(callingUser);
		input.setBusinessUnitID("");
		input.setBranchNumber(deptNo);//暫時設總行
		input.setGenAlertFlag(genAlertFlag);
		input.setTransactionDate(transactionDate);
		input.setUniqueKey(uniqueKey);
		input.setReferenceNumber(referenceNumber);
		logger.debug(input.toString());
		return input;
	}
	
	private NameCheckInputBean setInvImportNameCheckInputBean(XInvImport xInvImport, String callingUser){
		String callingSystem = AmlConfiguration.getString(SwiftMtConst.UPLOAD_TYPE_TRADEFINANCE_IMPORT_FILE_CALLINGSYSTEM);
		String screenProcess = SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_BL_SCREENING;
		String genAlertFlag = AmlConfiguration.getString(SwiftMtConst.UPLOAD_TYPE_TRADEFINANCE_IMPORT_FILE_GENALERTFLAG);
		Date transactionDate = new Date();
		String uniqueKey = xInvImport.getId().getUniqueKey();
//		String referenceNumber = String.format("%s%s%s%s", xInvExport.getScrNo(), xInvExport.getCurrnecy(), xInvExport.getOurRefNo(), xInvExport.getSeqNo());
		String referenceNumber =String.valueOf((int) (new Date().getTime()/1000));
		NameCheckInputBean input = new NameCheckInputBean();
		AccountIntegration account = iAccountIntegrationDao.findOne(callingUser);
		String deptNo = account != null ? account.getDeptNo() : "";
		List<NameCheckInputDetailBean> detailList = setInvImportNameCheckInputDetailBeanList(xInvImport);
		input.setSeq(detailList);
		
		input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_NAMECHECK);
		input.setCallingSystem(callingSystem);
		input.setScreenProcess(screenProcess);
		input.setCallingUser(callingUser);
		input.setBusinessUnitID("");
		input.setBranchNumber(deptNo);
		input.setGenAlertFlag(genAlertFlag);
		input.setTransactionDate(transactionDate);
		input.setUniqueKey(uniqueKey);
		input.setReferenceNumber(referenceNumber);
		logger.debug(input.toString());
		return input;
	}

	private List<NameCheckInputDetailBean> setInvExportNameCheckInputDetailBeanList(XInvExport xInvExport){
		List<NameCheckInputDetailBean> detailList = new ArrayList<NameCheckInputDetailBean>();
		if(xInvExport.getHkHsCode() != null && !"".equals(xInvExport.getHkHsCode())){
			detailList.add(new NameCheckInputDetailBean("1", "07", "09", "HK HS Code", xInvExport.getHkHsCode()));
		}
		if(xInvExport.getApplicant() != null && !"".equals(xInvExport.getApplicant())){
			detailList.add(new NameCheckInputDetailBean("2", "09", "09", "Applicant", xInvExport.getApplicant()));
		}
		
		return detailList;
	}
	
	private List<NameCheckInputDetailBean> setInvImportNameCheckInputDetailBeanList(XInvImport xInvImport){
		List<NameCheckInputDetailBean> detailList = new ArrayList<NameCheckInputDetailBean>();
		if(xInvImport.getHkHsCode() != null && !"".equals(xInvImport.getHkHsCode())){
			detailList.add(new NameCheckInputDetailBean("1", "07", "09", "HK HS Code", xInvImport.getHkHsCode()));
		}
		if(xInvImport.getApplicant() != null && !"".equals(xInvImport.getApplicant())){
			detailList.add(new NameCheckInputDetailBean("2", "09", "09", "Applicant", xInvImport.getApplicant()));
		}
		
		return detailList;
	}
	
	private NameCheckInputBean setBLImportNameCheckInputBean(XBlImport xBlImport, String callingUser){
		String callingSystem = AmlConfiguration.getString(SwiftMtConst.UPLOAD_TYPE_TRADEFINANCE_IMPORT_FILE_CALLINGSYSTEM);
		String screenProcess = SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_BL_SCREENING;
		String genAlertFlag = AmlConfiguration.getString(SwiftMtConst.UPLOAD_TYPE_TRADEFINANCE_IMPORT_FILE_GENALERTFLAG);
		Date transactionDate = new Date();
		String uniqueKey = xBlImport.getId().getUniqueKey();
//		String referenceNumber = String.format("%s%s%s%s", xInvImport.getScrNo(), xInvImport.getType(), xInvImport.getLCNo(), xInvImport.getIbNo());
		String referenceNumber =String.valueOf((int) (new Date().getTime()/1000));
		NameCheckInputBean input = new NameCheckInputBean();
		AccountIntegration account = iAccountIntegrationDao.findOne(callingUser);
		String deptNo = account != null ? account.getDeptNo() : "";
		List<NameCheckInputDetailBean> detailList = setBLImportNameCheckInputDetailBeanList(xBlImport);
		input.setSeq(detailList);
		
		input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_NAMECHECK);
		input.setCallingSystem(callingSystem);
		input.setScreenProcess(screenProcess);
		input.setCallingUser(callingUser);
		input.setBusinessUnitID("");
		input.setBranchNumber(deptNo);//總行
		input.setGenAlertFlag(genAlertFlag);
		input.setTransactionDate(transactionDate);
		input.setUniqueKey(uniqueKey);
		input.setReferenceNumber(referenceNumber);
		logger.debug(input.toString());
		return input;
	}
	
	private List<NameCheckInputDetailBean> setBLImportNameCheckInputDetailBeanList(XBlImport xBlImport){
		List<NameCheckInputDetailBean> detailList = new ArrayList<NameCheckInputDetailBean>();
		detailList.add(new NameCheckInputDetailBean("1", "09", "09", "Shipper/Exporter", xBlImport.getShipper()));
		if(xBlImport.getConsignee() != null && !"".equals(xBlImport.getConsignee()) && !SwiftMtConst.TF_TO_ORDER.equalsIgnoreCase(xBlImport.getConsignee())){
			detailList.add(new NameCheckInputDetailBean("2", "09", "09", "Consignee", xBlImport.getConsignee()));
		}
		if(xBlImport.getNotifyParty() != null && !"".equals(xBlImport.getNotifyParty())){
			detailList.add(new NameCheckInputDetailBean("3", "09", "09", "Notify Party", xBlImport.getNotifyParty()));
		}
		if(xBlImport.getSecondNotifyParty() != null && !"".equals(xBlImport.getSecondNotifyParty())){
			detailList.add(new NameCheckInputDetailBean("4", "09", "09", "Second Notify Party", xBlImport.getSecondNotifyParty()));
		}
		if(xBlImport.getShippingCompany() != null && !"".equals(xBlImport.getShippingCompany())){
			detailList.add(new NameCheckInputDetailBean("5", "09", "09", "Shipping Company", xBlImport.getShippingCompany()));
		}
		if(xBlImport.getForworder() != null && !"".equals(xBlImport.getForworder())){
			detailList.add(new NameCheckInputDetailBean("6", "09", "09", "Forworder", xBlImport.getForworder()));
		}
		if(xBlImport.getShippingAgent() != null && !"".equals(xBlImport.getShippingAgent())){
			detailList.add(new NameCheckInputDetailBean("7", "09", "09", "Shipping Agent", xBlImport.getShippingAgent()));
		}
		if(xBlImport.getShipper() != null && !"".equals(xBlImport.getShipper())){
			detailList.add(new NameCheckInputDetailBean("8", "08", "09", "Country of Origin", xBlImport.getShipper()));
		}
		if(xBlImport.getPlaceOfReceipt() != null && !"".equals(xBlImport.getPlaceOfReceipt())){
			detailList.add(new NameCheckInputDetailBean("9", "08", "09", "Place of Receipt", xBlImport.getPlaceOfReceipt()));
		}
		if(xBlImport.getCountryOfReceipt() != null && !"".equals(xBlImport.getCountryOfReceipt())){
			detailList.add(new NameCheckInputDetailBean("10", "08", "09", "Country of Receipt", xBlImport.getCountryOfReceipt()));
		}
		if(xBlImport.getPlaceOfDelievery() != null && !"".equals(xBlImport.getPlaceOfDelievery())){
			detailList.add(new NameCheckInputDetailBean("11", "08", "09", "Place of Delivery", xBlImport.getPlaceOfDelievery()));
		}
		if(xBlImport.getCountryOfDelivery() != null && !"".equals(xBlImport.getCountryOfDelivery())){
			detailList.add(new NameCheckInputDetailBean("12", "08", "09", "Country of Delivery", xBlImport.getCountryOfDelivery()));
		}
		if(xBlImport.getVessel() != null && !"".equals(xBlImport.getVessel())){
			detailList.add(new NameCheckInputDetailBean("13", "06", "09", "Vessel", xBlImport.getVessel()));
		}
		if(xBlImport.getPreCarriageVessel() != null && !"".equals(xBlImport.getPreCarriageVessel())){
			detailList.add(new NameCheckInputDetailBean("14", "06", "09", "Pre Carriage Vessel", xBlImport.getPreCarriageVessel()));
		}
		if(xBlImport.getPortOfLanding() != null && !"".equals(xBlImport.getPortOfLanding())){
			detailList.add(new NameCheckInputDetailBean("15", "08", "09", "Port of Loading", xBlImport.getPortOfLanding()));
		}
		if(xBlImport.getCountryOfLanding() != null && !"".equals(xBlImport.getCountryOfLanding())){
			detailList.add(new NameCheckInputDetailBean("16", "08", "09", "Country of Loading", xBlImport.getCountryOfLanding()));
		}
		if(xBlImport.getPortOfDischarge() != null && !"".equals(xBlImport.getPortOfDischarge())){
			detailList.add(new NameCheckInputDetailBean("17", "08", "09", "Port of Discharge", xBlImport.getPortOfDischarge()));
		}
		if(xBlImport.getCountryOfDischarge() != null && !"".equals(xBlImport.getCountryOfDischarge())){
			detailList.add(new NameCheckInputDetailBean("18", "08", "09", "Country of Discharge", xBlImport.getCountryOfDischarge()));
		}
		if(xBlImport.getTranshipmentPort() != null && !"".equals(xBlImport.getTranshipmentPort())){
			detailList.add(new NameCheckInputDetailBean("19", "08", "09", "Transhipment Port", xBlImport.getTranshipmentPort()));
		}
		if(xBlImport.getCountryOfTranshipment() != null && !"".equals(xBlImport.getCountryOfTranshipment())){
			detailList.add(new NameCheckInputDetailBean("20", "08", "09", "Country of Transhipment", xBlImport.getCountryOfTranshipment()));
		}
		
		return detailList;
	}
	
	@SuppressWarnings("unused")
	private boolean WriteXlsxResult(String fileName, List<NameCheckInputBean> nameCheckInputBeanList,
			List<NameCheckOutputBean> nameCheckOutputBeanList, XBlInvErrMsgBean errMsgBean, String sourcePath) {
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
				List<NameCheckOutputDetail> detailList = bean.getSeq();
				String unikey = bean.getUniqueKey();
				if (detailList != null && !detailList.isEmpty()) {
					for (int i = 0; i < detailList.size(); i++) {
						row = sheet.createRow(index++);
						int detailIndex = 0;
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
			}
			// write this workbook to an Outputstream.
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			result = false;
			logger.error(e.getMessage(),e);
			errMsgBean.getErrorMsgStringList().add("Write File Error");
		} finally {
			try {
				if (fileOut != null) {
					fileOut.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
		return result;
	}
	
	private static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}
}
