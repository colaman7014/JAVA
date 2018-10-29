package com.sas.webservice.batch.screening;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.ISendMailDao;
import com.sas.db.aml.dao.fcfcore.IAccountIntegrationDao;
import com.sas.db.aml.dao.fcfcore.IFscPartyDimDao;
import com.sas.db.aml.dao.fcfcore.IXWatchlistSettingDao;
import com.sas.db.aml.orm.ecm.SendMail;
import com.sas.db.aml.orm.fcfcore.AccountIntegration;
import com.sas.db.aml.orm.fcfcore.FscPartyDim;
import com.sas.db.aml.orm.fcfcore.XWatchlistSetting;
import com.sas.db.wlf.dao.IXInvBlNcUploadRecordDao;
import com.sas.db.wlf.orm.XInvBlNcUploadRecord;
import com.sas.util.AmlConfiguration;
import com.sas.util.FTPUtil;
import com.sas.util.NameCheckUtil;
import com.sas.webservice.batch.screening.bean.CsvBean;
import com.sas.webservice.batch.screening.bean.UploadNameCheckOutputBean;
import com.sas.wlsearch.business.DataFluxMatchCode;
import com.sas.wlsearch.util.WatchListUtil;

/**
 * upload bath check: Name Check or Transaction Check
 * @author sas
 *
 */
@Service
public class ManualUploadNameCheck {
	
	private static final Logger logger = LoggerFactory.getLogger(ManualUploadNameCheck.class);
	
	private static final int CSV_ID = 1;
	private static final int CSV_NAME = 0;
	
	private static final String SCAN_TYPE_1 = "1";
	private static final String SCAN_TYPE_2 = "2";
	private static final String SCAN_TYPE_3 = "3";
	
	@Autowired
	IXInvBlNcUploadRecordDao iXInvBlNcUploadRecordDao;
	
	@Autowired
	IFscPartyDimDao iFscPartyDimDao;
	
	@Autowired
	DataFluxMatchCode dataFluxMatchCode;
	
	@Autowired
	IXWatchlistSettingDao iXWatchlistSettingDao;
	
	@Autowired
	ISendMailDao iSendMailDao;
	
	@Autowired
	IAccountIntegrationDao iAccountIntegrationDao;
	
	
	/**
	 * to generate Name check and Transaction Report
	 */
	public void uploadBatchCheck() {
		List<XInvBlNcUploadRecord> recordList = iXInvBlNcUploadRecordDao.queryUploadNameCheckRecord();
		
		for(XInvBlNcUploadRecord xInvBlNcUploadRecord: recordList){
			FTPUtil ftpUtil = null;
			try{
				updateXInvBlNcUploadRecordStatus(xInvBlNcUploadRecord, SwiftMtConst.UPLOAD_RECORD_STATUS_ONPROCESS);
				String csvInputPathName= xInvBlNcUploadRecord.getFilePath() + xInvBlNcUploadRecord.getFileName();
				List<CsvBean> csvInputList = this.adaptorCsvBean(csvInputPathName);
				String csvOutputPath = AmlConfiguration.getString("com.sas.manualUploadNamecheck.csv.output.filePath");
				List<String> csvOutputFileNames = new ArrayList<String>();
				if(xInvBlNcUploadRecord.getScanType().equals(SCAN_TYPE_1) || xInvBlNcUploadRecord.getScanType().equals(SCAN_TYPE_3)){
					Map<String, UploadNameCheckOutputBean> map = doNameCheckByCsv(csvInputList);
					String csvOutputFileName = xInvBlNcUploadRecord.getFileKey() + "_NameCheck.csv";
					this.generateNameCheckReport(csvOutputPath, csvOutputFileName, map.values());
					csvOutputFileNames.add(csvOutputFileName);
				}
				else if(xInvBlNcUploadRecord.getScanType().equals(SCAN_TYPE_2) || xInvBlNcUploadRecord.getScanType().equals(SCAN_TYPE_3)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String beginDate = sdf.format(xInvBlNcUploadRecord.getScanPeriodStart());
					String endDate = sdf.format(xInvBlNcUploadRecord.getScanPeriodEnd());
					Map<String, UploadNameCheckOutputBean> map = doTransactionCheckByCsv(csvInputList, beginDate, endDate);
					String csvOutputFileName = xInvBlNcUploadRecord.getFileKey() + "_TransactionCheck.csv";
					this.generateTransactionCheckReport(csvOutputPath, csvOutputFileName, map.values());
					csvOutputFileNames.add(csvOutputFileName);
				}
				
				//upload csv files to FTP server
				ftpUtil = new FTPUtil();
				String ftpUploadFolder = AmlConfiguration.getString("com.sas.manualUploadNamecheck.ftp.uploadFolder");
				for(String csvOutputFileName : csvOutputFileNames){
					ftpUtil.uploadFileToServer(csvOutputPath, csvOutputFileName, ftpUploadFolder, csvOutputFileName);
				}
				
				this.sendMail(xInvBlNcUploadRecord.getCreateUser(), csvOutputFileNames);
				updateXInvBlNcUploadRecordStatus(xInvBlNcUploadRecord, SwiftMtConst.UPLOAD_RECORD_STATUS_SUCCESS);
			}
			catch(Exception ex){
				logger.error(ex.toString(), ex);
				updateXInvBlNcUploadRecordStatus(xInvBlNcUploadRecord, SwiftMtConst.UPLOAD_RECORD_STATUS_FAIL);
			}
			finally{
				if(ftpUtil!=null){
					ftpUtil.close();
				}
			}
		}
	}
	
	@Transactional
	private void sendMail(String empId, List<String> csvOutputFileNames){
		String mailSource = AmlConfiguration.getString("com.sas.manualUploadNamecheck.mail.source");
		String mailSender = AmlConfiguration.getString("com.sas.manualUploadNamecheck.mail.sender");
		String mailSubject = AmlConfiguration.getString("com.sas.manualUploadNamecheck.mail.subject");
		String mailContent = AmlConfiguration.getString("com.sas.manualUploadNamecheck.mail.content");
		String ftpUploadFolder = AmlConfiguration.getString("com.sas.manualUploadNamecheck.ftp.uploadFolder");
		AccountIntegration account = iAccountIntegrationDao.findOne(empId);
		SendMail bean = new SendMail();
		bean.setSource(mailSource);
		bean.setSender(mailSender);
		bean.setReceiver(account.getEmail());
		bean.setSubject(mailSubject);
		bean.setMailContent(mailContent);
		bean.setIsSend(false);
		bean.setIsAttachement(SwiftMtConst.MAIL_ATTACHMENT_Y);
		bean.setFilePath(ftpUploadFolder);
		bean.setFileName(StringUtils.join(csvOutputFileNames, ","));
		bean.setProcessDate(new Timestamp(System.currentTimeMillis()));
		iSendMailDao.save(bean);
	}
	
	private List<CsvBean> adaptorCsvBean(String csvPathName) throws IOException{
		FileReader fr = null;
		BufferedReader br = null;
		List<CsvBean> returnList = new ArrayList<CsvBean>();
		try {
			fr = new FileReader(csvPathName);
			br = new BufferedReader(fr);
			while(br.ready()){
				CsvBean bean = new CsvBean();
				String csvLineData[] = StringUtils.split(br.readLine(), SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bean.setCsvId(csvLineData[CSV_ID]);
				bean.setCsvName(csvLineData[CSV_NAME]);
				returnList.add(bean);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("generateCsvBean fail", e);
			throw new IOException(e.toString(), e);
		}
		finally{
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(fr);
		}
		return returnList;
	}
	
	@Transactional
	private void updateXInvBlNcUploadRecordStatus(XInvBlNcUploadRecord xInvBlNcUploadRecord, String status){
		xInvBlNcUploadRecord.setScanStatus(status);
		iXInvBlNcUploadRecordDao.save(xInvBlNcUploadRecord);
	}
	
	private Map<String, UploadNameCheckOutputBean> doNameCheckByCsv(List<CsvBean> csvList) throws Exception{
		Map<String, UploadNameCheckOutputBean> map = new HashMap<String, UploadNameCheckOutputBean>();
		XWatchlistSetting xWatchlistSetting = iXWatchlistSettingDao.findByChangeCurrentInd(SwiftMtConst.CHANGE_CURRENT_IND_Y);

		try {
			for(CsvBean csvBean :csvList){
				boolean isChineseName = false;
				String inputCnName = null;
				String inputEnName = null;
				if(com.sas.util.StringUtils.isEnglish(csvBean.getCsvName())){
					inputEnName = csvBean.getCsvName();
					logger.debug(csvBean.getCsvName() + " is English Name");
				}
				else{
					inputCnName = csvBean.getCsvName();
					isChineseName = true;
					logger.debug(csvBean.getCsvName() + " is Chinese Name");
				}
				
				List<FscPartyDim> partyDimList = null;
				/***** ExactlyMatch START *****/
				if(isChineseName){
					partyDimList = iFscPartyDimDao.findByPartyIdentificationIdOrPartyNameEng(csvBean.getCsvId(), csvBean.getCsvName());
				}
				else{
					partyDimList = iFscPartyDimDao.findByPartyIdentificationIdOrPartyName(csvBean.getCsvId(), csvBean.getCsvName());
				}
				
				this.mergeNameMap(map, partyDimList, csvBean.getCsvId(), csvBean.getCsvName(), xWatchlistSetting.getExactMatchingScore());
				partyDimList = null;
				/***** ExactlyMatch END *****/
				
				/***** FuzzyMatch START *****/
				Map<String, String> matchCodeMap = dataFluxMatchCode.datafluxMatchCode(inputCnName, inputEnName);
				List<String> matchCodeList = new ArrayList<String>();
				for (Map.Entry<String, String> entry : matchCodeMap.entrySet()) {
					if(entry.getKey() != null && entry.getKey().length() > 0){
						matchCodeList.add(entry.getValue());
					}
				}
				if(isChineseName){
					partyDimList = iFscPartyDimDao.findByMatchCodePartyNameEngIn(matchCodeList);
				}
				else{
					partyDimList = iFscPartyDimDao.findByMatchCodePartyNameIn(matchCodeList);
				}
				this.mergeNameMap(map, partyDimList, csvBean.getCsvId(), csvBean.getCsvName(), xWatchlistSetting.getFuzzyMatchingScore());
				partyDimList = null;
				/***** FuzzyMatch END *****/
				
				/***** InclusiveMatch START *****/
				List<String> inclusiveList = WatchListUtil.handelInclusiveList(inputCnName, inputEnName);
				if(inclusiveList.size() > 0){
					partyDimList = iFscPartyDimDao.queryInclusiveNameCheck(inclusiveList, isChineseName);
					this.mergeNameMap(map, partyDimList, csvBean.getCsvId(), csvBean.getCsvName(), xWatchlistSetting);
				}
				/***** InclusiveMatch END *****/
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("doExactlyMatch", e);
			throw new Exception(e.toString(), e);
		}
		
		return map;
	}
	
	private Map<String, UploadNameCheckOutputBean> doTransactionCheckByCsv(List<CsvBean> csvList, String beginDate, String endDate) throws Exception{
		Map<String, UploadNameCheckOutputBean> map = new HashMap<String, UploadNameCheckOutputBean>();
		XWatchlistSetting xWatchlistSetting = iXWatchlistSettingDao.findByChangeCurrentInd(SwiftMtConst.CHANGE_CURRENT_IND_Y);

		try {
			for(CsvBean csvBean :csvList){
				boolean isChineseName = false;
				String inputCnName = null;
				String inputEnName = null;
				if(com.sas.util.StringUtils.isEnglish(csvBean.getCsvName())){
					inputEnName = csvBean.getCsvName();
				}
				else{
					inputCnName = csvBean.getCsvName();
					isChineseName = true;
				}
				
				/***** ExactlyMatch START *****/
				List<Map<String, Object>> resultList = iFscPartyDimDao.queryExactTransactionCheck(csvBean.getCsvId(), csvBean.getCsvName(), beginDate, endDate, isChineseName);
				this.mergeTransactionMap(map, resultList, csvBean.getCsvId(), csvBean.getCsvName(), xWatchlistSetting.getExactMatchingScore());
				resultList = null;
				/***** ExactlyMatch END *****/
				
				/***** FuzzyMatch START *****/
				
				
				Map<String, String> matchCodeMap = dataFluxMatchCode.datafluxMatchCode(inputCnName, inputEnName);
				List<String> matchCodeList = new ArrayList<String>();
				for (Map.Entry<String, String> entry : matchCodeMap.entrySet()) {
					if(entry.getKey() != null && entry.getKey().length() > 0){
						matchCodeList.add(entry.getValue());
					}
				}
				resultList = iFscPartyDimDao.queryFuzzyTransactionCheck(matchCodeList, beginDate, endDate, isChineseName);
				this.mergeTransactionMap(map, resultList, csvBean.getCsvId(), csvBean.getCsvName(), xWatchlistSetting.getFuzzyMatchingScore());
				resultList = null;
				/***** FuzzyMatch END *****/
				
				/***** InclusiveMatch START *****/
				List<String> inclusiveList = WatchListUtil.handelInclusiveList(inputCnName, inputEnName);
				if(inclusiveList.size() > 0){
					resultList = iFscPartyDimDao.queryInclusiveTransactionCheck(inclusiveList, beginDate, endDate, isChineseName);
					this.mergeTransactionMap(map, resultList, csvBean.getCsvId(), csvBean.getCsvName(), xWatchlistSetting.getInclusiveMatchingScore());
				}
				/***** InclusiveMatch END *****/
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("doExactlyMatch", e);
			throw new Exception(e.toString(), e);
		}
		
		return map;
	}
	
	private int calculatenIclusiveScore(UploadNameCheckOutputBean bean, String csvName, XWatchlistSetting xWatchlistSetting){
		int result = xWatchlistSetting.getInclusiveMatchingScore().intValue();
		boolean isEnglish = com.sas.util.StringUtils.isEnglish(csvName);
		float hitWordRatio = 0;
		if(isEnglish){
			NameCheckUtil.hitWordCount(bean.getPartyName(), csvName, isEnglish);
		}else{
			NameCheckUtil.hitWordCount(bean.getPartyName2(), csvName, isEnglish);
		}
		//	result = Math.round((xWatchlistSetting.getInclusiveMatchingScore().intValue() * (hitWordRatio >= 0.5 ? 1 : 0)  + ((xWatchlistSetting.getExactMatchingScore().intValue() - xWatchlistSetting.getInclusiveMatchingScore().intValue()) * hitWordRatio)));
		//	result = Math.round((xWatchlistSetting.getFuzzyMatchingScore().intValue() + ((xWatchlistSetting.getExactMatchingScore().intValue() - xWatchlistSetting.getFuzzyMatchingScore().intValue()) * hitWordRatio)));
		result = Math.round(NameCheckUtil.calculatInclusiveMatchScore(hitWordRatio, xWatchlistSetting));

		return result;
	}
	
	private Map<String, UploadNameCheckOutputBean> mergeNameMap(Map<String, UploadNameCheckOutputBean> map, List<FscPartyDim> partyDimList, String csvId, String csvName, XWatchlistSetting xWatchlistSetting){	
		for(FscPartyDim partyDim :partyDimList){
			String key = generateKey(csvId, csvName, partyDim.getPartyIdentificationId(), partyDim.getPartyName(), partyDim.getPartyNameEng());
			
			if(map.containsKey(key)){
				UploadNameCheckOutputBean bean = map.get(key);
				bean.setScore(new BigDecimal(calculatenIclusiveScore(bean, csvName, xWatchlistSetting)));
			}
			else{
				UploadNameCheckOutputBean bean = new UploadNameCheckOutputBean();
				bean.setCsvId(csvId);
				bean.setCsvName(csvName);
				bean.setPartyIdentificationId(partyDim.getPartyIdentificationId());
				bean.setPartyName(partyDim.getPartyName());
				bean.setPartyName2(partyDim.getPartyNameEng());
				bean.setScore(new BigDecimal(calculatenIclusiveScore(bean, csvName, xWatchlistSetting)));
				map.put(key, bean);
			}
		}
		return map;
	}
	
	private Map<String, UploadNameCheckOutputBean> mergeNameMap(Map<String, UploadNameCheckOutputBean> map, List<FscPartyDim> partyDimList, String csvId, String csvName, BigDecimal score){
		
		for(FscPartyDim partyDim :partyDimList){
			String key = generateKey(csvId, csvName, partyDim.getPartyIdentificationId(), partyDim.getPartyName(), partyDim.getPartyNameEng());
			if(map.containsKey(key)){
				UploadNameCheckOutputBean bean = map.get(key);
				if(score.compareTo(bean.getScore())>0){
					bean.setScore(score);
				}
			}
			else{
				UploadNameCheckOutputBean bean = new UploadNameCheckOutputBean();
				bean.setCsvId(csvId);
				bean.setCsvName(csvName);
				bean.setPartyIdentificationId(partyDim.getPartyIdentificationId());
				bean.setPartyName(partyDim.getPartyName());
				bean.setPartyName2(partyDim.getPartyNameEng());
				bean.setScore(score);
				map.put(key, bean);
			}
		}
		return map;
	}
	
	private Map<String, UploadNameCheckOutputBean> mergeTransactionMap(Map<String, UploadNameCheckOutputBean> map, List<Map<String, Object>> transactionList, String csvId, String csvName, BigDecimal score){
		
		for(Map<String, Object> obj :transactionList){
			String partyIdentificationId = obj.get("PARTY_IDENTIFICATION_ID")==null? null: obj.get("PARTY_IDENTIFICATION_ID").toString();
			String partyName = obj.get("PARTY_NAME")==null? null: obj.get("PARTY_NAME").toString();
			String partyName2 = obj.get("PARTY_NAME_2")==null? null: obj.get("PARTY_NAME_2").toString();
			String key = generateKey(csvId, csvName, partyIdentificationId, partyName, partyName2);
			if(map.containsKey(key)){
				UploadNameCheckOutputBean bean = map.get(key);
				if(score.compareTo(bean.getScore())>0){
					UploadNameCheckOutputBean higherScoreBean = adaptorTransactionResult(obj, csvId, csvName, partyIdentificationId, partyName, partyName2, score);
					map.put(key, higherScoreBean);
				}
			}
			else{
				UploadNameCheckOutputBean bean = adaptorTransactionResult(obj, csvId, csvName, partyIdentificationId, partyName, partyName2, score);
				map.put(key, bean);
			}
		}
		return map;
	}
	
	private UploadNameCheckOutputBean adaptorTransactionResult(Map<String, Object> obj, String csvId, String csvName, String partyIdentificationId, String partyName, String partyName2, BigDecimal score){
		UploadNameCheckOutputBean bean = new UploadNameCheckOutputBean();
		bean.setCsvId(csvId);
		bean.setCsvName(csvName);
		bean.setPartyIdentificationId(partyIdentificationId);
		bean.setPartyName(partyName);
		bean.setPartyName2(partyName2);
		bean.setScore(score);
		
		BigDecimal remitterExtPartyKey = obj.get("REMITTER_EXT_PARTY_KEY")==null? null: (BigDecimal)obj.get("REMITTER_EXT_PARTY_KEY");
		BigDecimal beneficiaryExtPartyKey = obj.get("BENEFICIARY_EXT_PARTY_KEY")==null? null: (BigDecimal)obj.get("BENEFICIARY_EXT_PARTY_KEY");
		BigDecimal secondaryExtPartyKey = obj.get("SECONDARY_ACCOUNT_KEY")==null? null: (BigDecimal)obj.get("SECONDARY_ACCOUNT_KEY");
		
		if(remitterExtPartyKey!=null && remitterExtPartyKey.intValue()>-1){
			bean.setTransactionKey(remitterExtPartyKey.toString());
		}
		else if(beneficiaryExtPartyKey!=null && beneficiaryExtPartyKey.intValue()>-1){
			bean.setTransactionKey(beneficiaryExtPartyKey.toString());
		}
		else if(secondaryExtPartyKey!=null && secondaryExtPartyKey.intValue()>-1){
			bean.setTransactionKey(secondaryExtPartyKey.toString());
		}
		
		bean.setTransactionName(obj.get("TRANCACTION_NAME")==null? null: obj.get("TRANCACTION_NAME").toString());
		bean.setTransactionNo(obj.get("TRANSACTION_REFERENCE_NUMBER")==null? null: obj.get("TRANSACTION_REFERENCE_NUMBER").toString());
		bean.setTrancactionDate(obj.get("DATE_KEY")==null? null: obj.get("DATE_KEY").toString());
		bean.setTrancactionType(obj.get("TRANSACTION_TYPE")==null? null: obj.get("TRANSACTION_TYPE").toString());
		bean.setTrancactionAmount(obj.get("CURRENCY_AMOUNT")==null? null: (BigDecimal)obj.get("CURRENCY_AMOUNT"));
		return bean;
	}
	
	private String generateKey(String csvId, String csvName, String partyIdentificationId, String partyName, String partyName2){
		return csvId + "~|" + csvName  + "~|" + (partyIdentificationId==null? "": partyIdentificationId)  + "~|" + (partyName==null? "": partyName)  + "~|" + (partyName2==null? "": partyName2);
	}
	
	private void generateNameCheckReport(String outputPath, String fileName, 
			Collection<UploadNameCheckOutputBean> collections) throws IOException{
		
		FileWriter writer = null;
		try {
			writer = new FileWriter(outputPath + fileName, false);
			String header = AmlConfiguration.getString("com.sas.manualUploadNamecheck.csv.output.header.nameCheck");
			writer.write(header + "\r\n");
			for(UploadNameCheckOutputBean bean : collections){
				StringBuffer bfr = new StringBuffer();
				bfr.append("\"").append(bean.getCsvId()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getCsvName()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getPartyIdentificationId()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getPartyName()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getPartyName2()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getScore()).append("\"").append("\r\n");
				writer.write(bfr.toString());
			}
			writer.flush();
			
		} catch (IOException ex) {
			logger.error(ex.toString(), ex);
			throw ex;
		}
		finally{
			IOUtils.closeQuietly(writer);
		}
	}
	
	private void generateTransactionCheckReport(String outputPath, String fileName, 
			Collection<UploadNameCheckOutputBean> collections) throws IOException {
		FileWriter writer = null;
		try {
			writer = new FileWriter(outputPath + fileName, false);
			String header = AmlConfiguration.getString("com.sas.manualUploadNamecheck.csv.output.header.transactionCheck");
			writer.write(header + "\r\n");
			for(UploadNameCheckOutputBean bean : collections){
				StringBuffer bfr = new StringBuffer();
				bfr.append("\"").append(bean.getCsvId()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getCsvName()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getPartyIdentificationId()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getPartyName()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getPartyName2()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getTransactionKey()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getTransactionName()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getTransactionNo()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getTrancactionDate()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getTrancactionType()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getTrancactionAmount()).append("\"").append(SwiftMtConst.UPLOAD_RECORD_CSV_SPLIT);
				bfr.append("\"").append(bean.getScore()).append("\"").append("\r\n");
				writer.write(bfr.toString());
			}
			writer.flush();
			
		} catch (IOException ex) {
			logger.error(ex.toString(), ex);
			throw ex;
		}
		finally{
			IOUtils.closeQuietly(writer);
		}
	}
}
