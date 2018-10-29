package com.sas.webservice.batch.screening;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.jws.WebService;
import javax.persistence.LockModeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sas.constraint.SwiftMtConst;
//import com.sas.db.aml.dao.fcfcore.IFscPartyAssocAttributeDao;
//import com.sas.db.aml.dao.fcfcore.IFscPartyAssocDao;
import com.sas.db.aml.dao.fcfcore.IFscPartyDimWlfDao;
//import com.sas.db.aml.orm.fcfcore.FscPartyAssoc;
import com.sas.db.aml.orm.fcfcore.FscPartyDimWlf;
import com.sas.util.StringUtils;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingInputBean;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingOutputBean;
import com.sas.webservice.nameCheck.AMLBatchNameCheckThread;
import com.sas.webservice.nameCheck.AmlAsynBatchCheck;
import com.sas.webservice.nameCheck.AmlBatchNameCheck;
import com.sas.webservice.nameCheck.bean.BatchNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.BatchNameCheckInputDetailBean;


/**
 * 夜批掃描 Name Checking 主程式 (全客戶掃全名單)
 * @author SAS
 *
 */
@Component
@WebService(endpointInterface = "com.sas.webservice.batch.screening.BatchNameCheckingFullPartyFullScan")
public class BatchNameCheckingFullPartyFullScanImpl implements BatchNameCheckingFullPartyFullScan {
	private static final Logger logger = LoggerFactory.getLogger(BatchNameCheckingFullPartyFullScanImpl.class);

	@Autowired
	IFscPartyDimWlfDao iFscPartyDimWlfDao;
	
	@Autowired
	AmlBatchNameCheck amlBatchNameCheck;

	@Autowired @Lazy
	AmlAsynBatchCheck amlAsynBatchCheck;
	
	/**
	 * 夜批掃描 Name Checking 主程式 
	 */
	public BatchNameCheckingOutputBean batchNameCheckingFullPartyFullScanImpl(BatchNameCheckingInputBean inputTmp) {
		// TODO Auto-generated method stub
		
		BatchNameCheckingOutputBean output = new BatchNameCheckingOutputBean();
		output.setFinish(false);
		output.setServerName(System.getenv("COMPUTERNAME"));
		
		amlAsynBatchCheck.doAsyncBatchNameCheckingFullPartyFullScan();
		
		output.setFinish(true);
		return output;
	}
	
	
	public void doBatchNameCheckingFullPartyFullScan() {

		//變數定義
		Timestamp now = new Timestamp(System.currentTimeMillis());
		int countUniqueKey=0;
		String uniqueKey = SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK+"_F_"+new SimpleDateFormat("yyyyMMddHHmmss").format(now);
		String referenceNumber = new SimpleDateFormat("MMddHHmm").format(now);
		String interfaceName = SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK;
		String callingSystem = SwiftMtConst.CALLING_SYSTEM_NON_SWIFT;
		String screenProcess = SwiftMtConst.SCREEN_PROCESS_BATCH_NAME_CHECKING;
		String callingUser = "SYSTEM";
		String genAlertFlag = "Y";
		List<BatchNameCheckInputBean> nameCheckInputList = new ArrayList<BatchNameCheckInputBean>();		
		List<FscPartyDimWlf> fscPartyDimMainList = new ArrayList<FscPartyDimWlf>();	
//		List<FscPartyAssoc> fscPartyAssocList = new ArrayList<FscPartyAssoc>();  //台銀夜批掃描不串關係人
		List<Long> partyKeyList = new ArrayList<Long>();

		int handleCount = 1000 ; //每次處理幾筆
		int countParty = iFscPartyDimWlfDao.countBatchNameCheck("N"); //先撈取本行客戶			
		while(countParty > 0){   //處理本行客戶	
			nameCheckInputList = new ArrayList<BatchNameCheckInputBean>();
		    partyKeyList = new ArrayList<Long>();
			fscPartyDimMainList = iFscPartyDimWlfDao.queryBatchNameCheckWlf("N", handleCount);	
			for(FscPartyDimWlf fpdBe : fscPartyDimMainList){
				partyKeyList.add(fpdBe.getId().getPartyKey());
				int checkSeq = 1;
				//組合Input資訊
				BatchNameCheckInputBean input = new BatchNameCheckInputBean();
				input.setInterfaceName(interfaceName); //AML_BatchNameCheck
				input.setCallingSystem(callingSystem); //99
				input.setScreenProcess(screenProcess);  //Batch Name Checking
				input.setCallingUser(callingUser);
				input.setBusinessUnitID("");
				input.setBranchNumber(fpdBe.getPrimaryBranchNumber());    //夜批代的分行名稱0C3
				input.setGenAlertFlag(genAlertFlag); 
				input.setTransactionDate(now);
				countUniqueKey++;
				input.setUniqueKey(uniqueKey+String.valueOf(countUniqueKey));
				input.setReferenceNumber(referenceNumber);
				input.setPartyNumber(fpdBe.getPartyNumber());
				input.setNightBatchOption("FullPartyFullScan"); //全客戶掃全名單類型
				
				//組合Input中 Detail資訊
				List<BatchNameCheckInputDetailBean> detailList = new ArrayList<BatchNameCheckInputDetailBean>();
				String entityType = getEntityType(fpdBe.getPartyTypeCode());
				
				//處理party_name1
				if(!StringUtils.isEmpty(fpdBe.getPartyName())){
					BatchNameCheckInputDetailBean detailBe =  new BatchNameCheckInputDetailBean();
					detailBe.setCheckSeq(String.valueOf(checkSeq));
					detailBe.setEntityType(entityType); //有區分個人 or 法人
					detailBe.setEntityRelationship("01"); //關係人
					detailBe.setEntityRelationshipDesc(""); //關係人描述
					String partyName = StringUtils.convertToHalfWidth(fpdBe.getPartyName()).trim();
						if(StringUtils.isEnglish(partyName)){
	 						detailBe.setEnglishName(partyName);
	 						detailBe.setNonEnglishName("");
	 					}else{
	 						detailBe.setEnglishName("");
	 						detailBe.setNonEnglishName(partyName);
	 					}
						detailBe.setIdNumber(fpdBe.getPartyIdentificationId());//查ID
						if(fpdBe.getPartyDateOfBirth() !=null){
							detailBe.setYearOfBirth(new SimpleDateFormat("yyyy").format(fpdBe.getPartyDateOfBirth())); //查出生年
						}
						detailBe.setMatchCodeEntityName(fpdBe.getMatchCodePartyName());
						detailBe.setCountry(fpdBe.getCitizenshipCountryCode());
					detailList.add(detailBe);
					checkSeq++;
				}

				//處理party_name2
				if(!StringUtils.isEmpty(fpdBe.getPartyNameEng())){
					BatchNameCheckInputDetailBean detailBe =  new BatchNameCheckInputDetailBean();
					detailBe.setCheckSeq(String.valueOf(checkSeq));
					detailBe.setEntityType(entityType); //有區分個人 or 法人
					detailBe.setEntityRelationship("01"); //關係人
					detailBe.setEntityRelationshipDesc(""); //關係人描述
					String partyName2 = StringUtils.convertToHalfWidth(fpdBe.getPartyNameEng()).trim();
						if(StringUtils.isEnglish(partyName2)){
	 						detailBe.setEnglishName(partyName2);
	 						detailBe.setNonEnglishName("");
	 					}else{
	 						detailBe.setEnglishName("");
	 						detailBe.setNonEnglishName(partyName2);
	 					}
						detailBe.setMatchCodeEntityName(fpdBe.getMatchCodePartyNameEng());
					detailBe.setIdNumber(fpdBe.getPartyIdentificationId());//查ID
					if(fpdBe.getPartyDateOfBirth() !=null){
						detailBe.setYearOfBirth(new SimpleDateFormat("yyyy").format(fpdBe.getPartyDateOfBirth())); //查出生年
					}
					detailBe.setCountry(fpdBe.getCitizenshipCountryCode());
					detailList.add(detailBe);
					checkSeq++;
				}
				
				input.setSeq(detailList);
				nameCheckInputList.add(input);

			}
					
			ExecutorService executor = Executors.newFixedThreadPool(6);
			List<AMLBatchNameCheckThread> jobs = new ArrayList<AMLBatchNameCheckThread>();
			for(BatchNameCheckInputBean inputBe : nameCheckInputList){
				if(inputBe.getSeq().size() > 0){
					AMLBatchNameCheckThread amlBatchNameCheckThread = new AMLBatchNameCheckThread();
					amlBatchNameCheckThread.setAmlBatchNameCheck(amlBatchNameCheck);
					amlBatchNameCheckThread.setNameCheckInputBean(inputBe);
					jobs.add(amlBatchNameCheckThread);
				}
			}
			try {
				executor.invokeAll(jobs);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				executor.shutdown();
			}	
			
			if(partyKeyList.size()!=0){
				try{
					List<FscPartyDimWlf> needUpdateList = iFscPartyDimWlfDao.findByIdPartyKeyIn(partyKeyList);
					for(FscPartyDimWlf be : needUpdateList){
						be.setWlfFlg("Y");				
					}
					iFscPartyDimWlfDao.save(needUpdateList);
					iFscPartyDimWlfDao.flush();
					
				} catch (Exception e){
					logger.error(e.getMessage(), e);
				}			
			}
			
			countParty = iFscPartyDimWlfDao.countBatchNameCheck("N"); //撈取本行客戶剩餘筆數	
	
		}
		
		

		countParty = iFscPartyDimWlfDao.countBatchNameCheck("Y"); //撈取非本行客戶	
		while(countParty > 0){   
			//Step1. 撈取非本行客戶
		    nameCheckInputList = new ArrayList<BatchNameCheckInputBean>();
		    partyKeyList = new ArrayList<Long>();
		    fscPartyDimMainList = iFscPartyDimWlfDao.queryBatchNameCheckWlf("Y", handleCount);	
		    
		    for(FscPartyDimWlf fpdBe : fscPartyDimMainList){
		    	partyKeyList.add(fpdBe.getId().getPartyKey());
		//    	fscPartyAssocList = iFscPartyAssocDao.findOneByIdRelatedPartyNumberOrderByIdRelatedPartyNumber(fpdBe.getPartyNumber());
		    	int checkSeq = 1;
				//組合Input資訊
				BatchNameCheckInputBean input = new BatchNameCheckInputBean();
				input.setInterfaceName(interfaceName); //AML_BatchNameCheck
				input.setCallingSystem(callingSystem); //99
				input.setScreenProcess(screenProcess);  //Batch Name Checking
				input.setCallingUser(callingUser);   //?
				input.setBusinessUnitID("");  //?
				input.setBranchNumber(fpdBe.getPrimaryBranchNumber());    //港行夜批代的分行名稱0C3
				input.setGenAlertFlag(genAlertFlag); 
				input.setTransactionDate(now);
				input.setNightBatchOption("FullPartyFullScan"); //全客戶掃全名單類型
				countUniqueKey++;
				input.setUniqueKey(uniqueKey+String.valueOf(countUniqueKey));
				input.setReferenceNumber(referenceNumber);
				/*
				if(fscPartyAssocList.size() > 0 ){ //有串到關係人
					input.setPartyNumber(fscPartyAssocList.get(0).getId().getPartyNumber());   //放原始本行客戶
				}else{
					input.setPartyNumber("");
				}
				*/
				input.setPartyNumber(fpdBe.getPartyNumber());
				
							
				//組合Input中 Detail資訊
				List<BatchNameCheckInputDetailBean> detailList = new ArrayList<BatchNameCheckInputDetailBean>();				
				String entity_type = getEntityType(fpdBe.getPartyTypeCode());
				
				//處理party_name1
				if(!StringUtils.isEmpty(fpdBe.getPartyName())){
					BatchNameCheckInputDetailBean detailBe =  new BatchNameCheckInputDetailBean();
					/*
					if(fscPartyAssocList.size() > 0 ){ //有串到關係人
						detailBe.setCheckSeq(String.valueOf(checkSeq)+"_!_"+fpdBe.getPartyNumber());
					}else{  //沒串到關係人
						detailBe.setCheckSeq(String.valueOf(checkSeq)+"_!_"+fpdBe.getPartyNumber());
					}
					*/
					detailBe.setCheckSeq(String.valueOf(checkSeq));
					
					detailBe.setEntityType(entity_type); //有區分個人 or 法人
					detailBe.setEntityRelationship("01"); //關係人
					/*
					if(fscPartyAssocList.size() >0){ //是否有串到關係人
						detailBe.setEntityRelationship(fscPartyAssocList.get(0).getId().getRelationshipTypeCode()); //關係人代碼
					}else{
						detailBe.setEntityRelationship("");
					}
					*/				
					detailBe.setEntityRelationshipDesc(""); //關係人描述
					String partyName = StringUtils.convertToHalfWidth(fpdBe.getPartyName()).trim();
						if(StringUtils.isEnglish(partyName)){
	 						detailBe.setEnglishName(partyName);
	 						detailBe.setNonEnglishName("");
	 					}else{
	 						detailBe.setEnglishName("");
	 						detailBe.setNonEnglishName(partyName);
	 					}
						detailBe.setIdNumber(fpdBe.getPartyIdentificationId());//查ID
						if(fpdBe.getPartyDateOfBirth() !=null){
							detailBe.setYearOfBirth(new SimpleDateFormat("yyyy").format(fpdBe.getPartyDateOfBirth())); //查出生年
						}
						detailBe.setMatchCodeEntityName(fpdBe.getMatchCodePartyName());
						detailBe.setCountry(fpdBe.getCitizenshipCountryCode());
					detailList.add(detailBe);
					checkSeq++;
				}

				//處理party_name2
				if(!StringUtils.isEmpty(fpdBe.getPartyNameEng())){
					BatchNameCheckInputDetailBean detailBe =  new BatchNameCheckInputDetailBean();
					/*
					if(fscPartyAssocList.size() > 0 ){ //有串到關係人
						detailBe.setCheckSeq(String.valueOf(checkSeq)+"_!_"+fpdBe.getPartyNumber());
					}else{
						detailBe.setCheckSeq(String.valueOf(checkSeq)+"_!_"+fpdBe.getPartyNumber());
					}
					*/
					detailBe.setCheckSeq(String.valueOf(checkSeq));
					
					detailBe.setEntityType(entity_type); //有區分個人 or 法人
					/*
					if(fscPartyAssocList.size() >0){ //是否有串到關係人
						detailBe.setEntityRelationship(fscPartyAssocList.get(0).getId().getRelationshipTypeCode()); //關係人
					}else{
						detailBe.setEntityRelationship("");
					}
					*/
					detailBe.setEntityRelationship("01");
					
					detailBe.setEntityRelationshipDesc(""); //關係人描述
					String partyName2 = StringUtils.convertToHalfWidth(fpdBe.getPartyNameEng()).trim();
						if(StringUtils.isEnglish(partyName2)){
	 						detailBe.setEnglishName(partyName2);
	 						detailBe.setNonEnglishName("");
	 					}else{
	 						detailBe.setEnglishName("");
	 						detailBe.setNonEnglishName(partyName2);
	 					}
						detailBe.setMatchCodeEntityName(fpdBe.getMatchCodePartyNameEng());
					detailBe.setIdNumber(fpdBe.getPartyIdentificationId());//查ID
					if(fpdBe.getPartyDateOfBirth() !=null){
						detailBe.setYearOfBirth(new SimpleDateFormat("yyyy").format(fpdBe.getPartyDateOfBirth())); //查出生年
					}

					detailBe.setCountry(fpdBe.getCitizenshipCountryCode());
					detailList.add(detailBe);
					checkSeq++;
				}			
				input.setSeq(detailList);
				nameCheckInputList.add(input);	
		    }
		    
		    
		    
			ExecutorService executor = Executors.newFixedThreadPool(6);
			List<AMLBatchNameCheckThread> jobs = new ArrayList<AMLBatchNameCheckThread>();
			for(BatchNameCheckInputBean inputBe : nameCheckInputList){
				if(inputBe.getSeq().size() > 0){
					AMLBatchNameCheckThread amlBatchNameCheckThread = new AMLBatchNameCheckThread();
					amlBatchNameCheckThread.setAmlBatchNameCheck(amlBatchNameCheck);
					amlBatchNameCheckThread.setNameCheckInputBean(inputBe);
					jobs.add(amlBatchNameCheckThread);
				}
			}
			try {
				executor.invokeAll(jobs);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				executor.shutdown();
			}
			
			if(partyKeyList.size()!=0){
				try{
					List<FscPartyDimWlf> needUpdateList = iFscPartyDimWlfDao.findByIdPartyKeyIn(partyKeyList);
					for(FscPartyDimWlf be : needUpdateList){
						be.setWlfFlg("Y");				
					}
					iFscPartyDimWlfDao.save(needUpdateList);
					iFscPartyDimWlfDao.flush();
					
				} catch (Exception e){
					logger.error(e.getMessage(), e);
				}			
			}
			
			countParty = iFscPartyDimWlfDao.countBatchNameCheck("Y"); //撈取本行客戶剩餘筆數	
		   	    
		}
		
		
		//Step4 整理&FTP上傳
		//batchNameCheckingResultFTPUpload.batchNameCheckingResultFTPUpload(interfaceName, screenProcess, referenceNumber, fileName);

	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE, readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	private void doSaveWlfFlgtoY(List<Long> partyKeyList){
		iFscPartyDimWlfDao.updateBatchNameCheckWlf(partyKeyList);
	}

	/**
	 * 判斷個人或是法人
	 * @param partyTypeDesc
	 * @return
	 */
	private String getEntityType(String partyTypeCode) {
		String entity_type;
		if("E".equals(partyTypeCode) || "FI".equals(partyTypeCode) || "GV".equals(partyTypeCode) ||"NPI".equals(partyTypeCode) 
				||"COR".equals(partyTypeCode) ||"PS".equals(partyTypeCode)  ||"PI".equals(partyTypeCode) ){
			entity_type = "03"; //為法人
		}else{
			entity_type= "02"; //為個人
		}
		return entity_type;
	}
}
