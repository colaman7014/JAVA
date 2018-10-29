package com.sas.webservice.batch.screening;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.fcfcore.IFscEntityWatchListDimChngDao;
//import com.sas.db.aml.dao.fcfcore.IFscPartyAssocAttributeDao;
//import com.sas.db.aml.dao.fcfcore.IFscPartyAssocDao;
import com.sas.db.aml.dao.fcfcore.IFscPartyDimChngDao;
import com.sas.db.aml.dao.fcfcore.IFscPartyDimDao;
import com.sas.db.aml.dao.fcfcore.IFscPartyDimScanCandidateDao;
//import com.sas.db.aml.orm.fcfcore.FscPartyAssoc;
import com.sas.db.aml.orm.fcfcore.FscPartyDim;
import com.sas.db.aml.orm.fcfcore.FscPartyDimChng;
import com.sas.db.aml.orm.fcfcore.FscPartyDimScanCandidate;
import com.sas.util.AmlConfiguration;
import com.sas.util.NameCheckUtil;
import com.sas.util.StringUtils;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingInputBean;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingOutputBean;
import com.sas.webservice.nameCheck.AMLBatchNameCheckThread;
import com.sas.webservice.nameCheck.AmlAsynBatchCheck;
import com.sas.webservice.nameCheck.AmlBatchNameCheck;
import com.sas.webservice.nameCheck.bean.BatchNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.BatchNameCheckInputDetailBean;
import com.sas.wlsearch.util.WatchListUtil;


/**
 * 夜批掃描 Name Checking 主程式 (異動客戶掃全名單 & 全客戶掃異動名單)
 * @author SAS
 *
 */
@Async
@Component
@Scope("prototype")
@WebService(endpointInterface = "com.sas.webservice.batch.screening.BatchNameCheckingChangeScan")
public class BatchNameCheckingChangeScanImpl implements BatchNameCheckingChangeScan {
	private static final Logger logger = LoggerFactory.getLogger(BatchNameCheckingChangeScanImpl.class);
	
	@Autowired
	IFscPartyDimDao iFscPartyDimDao;
	
	@Autowired   //異動客戶
	IFscPartyDimChngDao iFscPartyDimChngDao;
	
	@Autowired
	IFscPartyDimScanCandidateDao iFscPartyDimScanCandidateDao;
	
	@Autowired
	AmlBatchNameCheck amlBatchNameCheck;
	
	@Autowired
	IFscEntityWatchListDimChngDao iFscEntityWatchListDimChngDao;
	
	@Autowired @Lazy
	AmlAsynBatchCheck amlAsynBatchCheck;
	

	/**
	 * 夜批掃描 Name Checking 主程式 
	 */
	@Override
	public BatchNameCheckingOutputBean batchNameCheckingChangeScanImpl(BatchNameCheckingInputBean inputTmp) {
		// TODO Auto-generated method stub	
		BatchNameCheckingOutputBean output = new BatchNameCheckingOutputBean();
		output.setFinish(false);
		output.setServerName(System.getenv("COMPUTERNAME"));
		amlAsynBatchCheck.doAsyncBatchNameCheckingChangeScan();
		output.setFinish(true);
		return output;
	}
	
	

	public void fullPartyChangeScan(Timestamp now, int countUniqueKey, String uniqueKey, String referenceNumber,
			String interfaceName, String callingSystem, String screenProcess, String callingUser, String genAlertFlag) {

		int DLParameter = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_DAMERAU_LEVENSHTEIN_PARAMETER));
		
		// Step1 刪除 Fsc_Party_Dim_Scan_Candidate
		//iFscPartyDimScanCandidateDao.truncateFscPartyDimScanCandidate(); //修改使用 SAS Code 來刪除
		
		// Step2 撈取 Exact Match + Fuzzy Match + ID Match 的 Candidate 客戶，並寫入 FSC_PARTY_DIM_SCAN_CANDIDATE table
		iFscPartyDimDao.insertPartyKeyCandidateByExactOrFuzzyOrId();
			
		// Step3  撈取  Inclusive Match 的 Candidate 客戶，並加入差異度比對演算法
		Map<String, String> entityNameCandidateMap = new HashMap<String, String>();
		int limitCount = 1000;
		List<FscPartyDimScanCandidate> fscPartyDimScanCandidateList = new ArrayList<FscPartyDimScanCandidate>();
		int entityNameCandidateCount = iFscEntityWatchListDimChngDao.queryCountEntityNameCandidate(SwiftMtConst.CHANGE_CURRENT_IND_Y,SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, SwiftMtConst.SCREEN_PROCESS_BATCH_NAME_CHECKING);
		NameCheckUtil nameCheckUtil = new NameCheckUtil();
		for(int i=1;i<entityNameCandidateCount+limitCount;i=i+limitCount){
			entityNameCandidateMap = iFscEntityWatchListDimChngDao.queryCountEntityNameCandidate(SwiftMtConst.CHANGE_CURRENT_IND_Y,SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, SwiftMtConst.SCREEN_PROCESS_BATCH_NAME_CHECKING, i, i+limitCount-1);		
			for (Entry<String, String> entryEntityName : entityNameCandidateMap.entrySet()) {
				String entityNameTmp = entryEntityName.getKey();
				String entityTypeCode = entryEntityName.getValue();
				Map<Long,List<String>> inclusiveMatchList =  findByInclusiveMatchList(entityNameTmp, entityTypeCode);
				for (Entry<Long, List<String>> entry : inclusiveMatchList.entrySet()) {
					long partykeyTmp = entry.getKey();
					List<String> partyNameTmpList = entry.getValue();
					for(String partyNameTmp : partyNameTmpList){
						float hitWordRatio = 0;
						if(StringUtils.isEnglish(partyNameTmp)){
							hitWordRatio = nameCheckUtil.calculateHitWordRatio( entityNameTmp, "", partyNameTmp, DLParameter);
						}else{
							hitWordRatio = nameCheckUtil.calculateHitWordRatio( entityNameTmp, partyNameTmp , "", DLParameter);
						}
						if(hitWordRatio >= 0.5){ 
							FscPartyDimScanCandidate be = new FscPartyDimScanCandidate();
							be.setPartyKey(partykeyTmp);
							fscPartyDimScanCandidateList.add(be);	
						}
					}
				}		
			}
		
			if(fscPartyDimScanCandidateList.size()>0){
				iFscPartyDimScanCandidateDao.save(fscPartyDimScanCandidateList);
				fscPartyDimScanCandidateList = new ArrayList<FscPartyDimScanCandidate>();
			}
		}
		
		
		int partyKeycount = iFscPartyDimScanCandidateDao.queryCountFscPartyDimScanCandidate();
		if(partyKeycount > 0){
			List<BatchNameCheckInputBean> nameCheckInputList = new ArrayList<BatchNameCheckInputBean>();
			for(int i=1;i<partyKeycount+limitCount;i=i+limitCount){
				nameCheckInputList = new ArrayList<BatchNameCheckInputBean>();
				List<Long> partyKeyTmpList =iFscPartyDimScanCandidateDao.queryCountFscPartyDimScanCandidate(i, i+limitCount-1);
				List<FscPartyDim> fscPartyDimList = new ArrayList<FscPartyDim>();
				if(partyKeyTmpList.size() >0){
					fscPartyDimList = iFscPartyDimDao.queryCandidatePartyKey("N", partyKeyTmpList);		
				}
				for(FscPartyDim fpdBe : fscPartyDimList){
					int checkSeq = 1;
					//組合Input資訊
					BatchNameCheckInputBean input = new BatchNameCheckInputBean();
					input.setInterfaceName(interfaceName); //AML_BatchNameCheck
					input.setCallingSystem(callingSystem); //99
					input.setScreenProcess(screenProcess);  //Batch Name Checking
					input.setCallingUser(callingUser);
					input.setBusinessUnitID("");
					input.setBranchNumber(fpdBe.getPrimaryBranchNumber());    
					input.setGenAlertFlag(genAlertFlag); 
					input.setTransactionDate(now);
					countUniqueKey++;
					input.setUniqueKey(uniqueKey+"_D3_"+String.valueOf(countUniqueKey));
					input.setReferenceNumber(referenceNumber);
					input.setPartyNumber(fpdBe.getPartyNumber());
					input.setNightBatchOption("FullPartyChangeScan"); //全客戶掃異動名單類型
					
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
							//detailBe.setCountry(fpdBe.getCitizenshipCountryName());//查國家
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
						//detailBe.setCountry(fpdBe.getCitizenshipCountryName());//查國家
						detailBe.setCountry(fpdBe.getCitizenshipCountryCode());
						detailList.add(detailBe);
						checkSeq++;
					}					
					input.setSeq(detailList);
					nameCheckInputList.add(input);		
				}				
				//執行Thread AML_BatchNameCheck
				if(nameCheckInputList.size()>0){
					threadAMLBatchNameCheck(nameCheckInputList);
				}
			}
			
			//List<FscPartyAssoc> fscPartyAssocList = new ArrayList<FscPartyAssoc>();	
			for(int i=1;i<partyKeycount+limitCount;i=i+limitCount){	
				//撈取非本行客戶
				nameCheckInputList = new ArrayList<BatchNameCheckInputBean>();
				List<Long> partyKeyTmpList =iFscPartyDimScanCandidateDao.queryCountFscPartyDimScanCandidate(i, i+limitCount-1);
				List<FscPartyDim> fscPartyDimList = new ArrayList<FscPartyDim>();
				if(partyKeyTmpList.size() >0){
					fscPartyDimList = iFscPartyDimDao.queryCandidatePartyKey("Y", partyKeyTmpList);
				}
				partyKeyTmpList = new ArrayList<Long>();
				for(FscPartyDim fpdBe : fscPartyDimList){
			    	//fscPartyAssocList = iFscPartyAssocDao.findOneByIdRelatedPartyNumberOrderByIdRelatedPartyNumber(fpdBe.getPartyNumber());
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
					input.setNightBatchOption("FullPartyChangeScan"); //全客戶異動名單類型
					countUniqueKey++;
					input.setUniqueKey(uniqueKey+"_D4_"+String.valueOf(countUniqueKey));
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
							//detailBe.setCountry(fpdBe.getCitizenshipCountryName());//查國家
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
						detailBe.setEntityRelationship("01");
						/*
						if(fscPartyAssocList.size() >0){ //是否有串到關係人
							detailBe.setEntityRelationship(fscPartyAssocList.get(0).getId().getRelationshipTypeCode()); //關係人
						}else{
							detailBe.setEntityRelationship("");
						}
						*/
						
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
						
						//detailBe.setCountry(fpdBe.getCitizenshipCountryName());//查國家
						detailBe.setCountry(fpdBe.getCitizenshipCountryCode());
						detailList.add(detailBe);
						checkSeq++;
					}			
					input.setSeq(detailList);
					nameCheckInputList.add(input);	
			    }
			    
				//執行Thread AML_BatchNameCheck
				if(nameCheckInputList.size()>0){
					threadAMLBatchNameCheck(nameCheckInputList);
				}					
			}
		}
	}

	private void threadAMLBatchNameCheck(List<BatchNameCheckInputBean> nameCheckInputList) {
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
	}

	public void changePartyFullScan(Timestamp now, int countUniqueKey, String uniqueKey, String referenceNumber,
			String interfaceName, String callingSystem, String screenProcess, String callingUser, String genAlertFlag) {
		List<BatchNameCheckInputBean> nameCheckInputList = new ArrayList<BatchNameCheckInputBean>();
//		List<FscPartyAssoc> fscPartyAssocList = new ArrayList<FscPartyAssoc>();	
//		List<FscPartyDimChng> fscPartyDimChngMainListAll = new ArrayList<FscPartyDimChng>();	
		List<FscPartyDimChng> fscPartyDimChngMainList = new ArrayList<FscPartyDimChng>();	
//		fscPartyDimChngMainList = iFscPartyDimChngDao.findByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescNot("Y","N","D");		
//		fscPartyDimChngMainListAll.addAll(fscPartyDimChngMainList);
//		fscPartyDimChngMainList = iFscPartyDimChngDao.findByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescIsNull("Y","N");
//		fscPartyDimChngMainListAll.addAll(fscPartyDimChngMainList);
		fscPartyDimChngMainList = iFscPartyDimChngDao.findByChangeCurrentIndAndExternalPartyInd("Y", "N"); //內部客戶全掃
		for(FscPartyDimChng fpdBe : fscPartyDimChngMainList){
			int checkSeq = 1;
			//組合Input資訊
			BatchNameCheckInputBean input = new BatchNameCheckInputBean();
			input.setInterfaceName(interfaceName); //AML_BatchNameCheck
			input.setCallingSystem(callingSystem); //99
			input.setScreenProcess(screenProcess);  //Batch Name Checking
			input.setCallingUser(callingUser);
			input.setBusinessUnitID("");
			input.setBranchNumber(fpdBe.getPrimaryBranchNumber());    //港行夜批代的分行名稱0C3
			input.setGenAlertFlag(genAlertFlag); 
			input.setTransactionDate(now);
			countUniqueKey++;
			input.setUniqueKey(uniqueKey+"_D1_"+String.valueOf(countUniqueKey));
			input.setReferenceNumber(referenceNumber);
			input.setPartyNumber(fpdBe.getPartyNumber());
			input.setNightBatchOption("ChangePartyFullScan"); //異動客戶掃全名單類型
			
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
					//detailBe.setCountry(fpdBe.getCitizenshipCountryName());//查國家
					detailBe.setCountry(fpdBe.getCitizenshipCountryCode());
					
				detailList.add(detailBe);
				checkSeq++;
			}

			//處理party_NameEng
			if(!StringUtils.isEmpty(fpdBe.getPartyNameEng())){
				BatchNameCheckInputDetailBean detailBe =  new BatchNameCheckInputDetailBean();
				detailBe.setCheckSeq(String.valueOf(checkSeq));
				detailBe.setEntityType(entityType); //有區分個人 or 法人
				detailBe.setEntityRelationship("01"); //關係人
				detailBe.setEntityRelationshipDesc(""); //關係人描述
				String partyNameEng = StringUtils.convertToHalfWidth(fpdBe.getPartyNameEng()).trim();
					if(StringUtils.isEnglish(partyNameEng)){
 						detailBe.setEnglishName(partyNameEng);
 						detailBe.setNonEnglishName("");
 					}else{
 						detailBe.setEnglishName("");
 						detailBe.setNonEnglishName(partyNameEng);
 					}
					detailBe.setMatchCodeEntityName(fpdBe.getMatchCodePartyNameEng());
				detailBe.setIdNumber(fpdBe.getPartyIdentificationId());//查ID
				if(fpdBe.getPartyDateOfBirth() !=null){
					detailBe.setYearOfBirth(new SimpleDateFormat("yyyy").format(fpdBe.getPartyDateOfBirth())); //查出生年
				}
				//detailBe.setCountry(fpdBe.getCitizenshipCountryName());//查國家
				detailBe.setCountry(fpdBe.getCitizenshipCountryCode());
				detailList.add(detailBe);
				checkSeq++;
			}
			
			input.setSeq(detailList);
			nameCheckInputList.add(input);

		}
	

	
		//Step4 撈取非本行客戶(異動名單)
//		fscPartyDimChngMainListAll = new  ArrayList<FscPartyDimChng>();
//		fscPartyDimChngMainList = new ArrayList<FscPartyDimChng>();
//		fscPartyDimChngMainList = iFscPartyDimChngDao.findByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescNot("Y","Y","D");	
//		fscPartyDimChngMainListAll.addAll(fscPartyDimChngMainList);
//		fscPartyDimChngMainList = iFscPartyDimChngDao.findByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescIsNull("Y","Y");
//		fscPartyDimChngMainListAll.addAll(fscPartyDimChngMainList);
		fscPartyDimChngMainList = iFscPartyDimChngDao.findByChangeCurrentIndAndExternalPartyInd("Y", "Y"); //外部客戶全掃
		for(FscPartyDimChng fpdBe : fscPartyDimChngMainList){	

			//先撈取關係人
			//fscPartyAssocList = iFscPartyAssocDao.findOneByIdRelatedPartyNumberOrderByIdRelatedPartyNumber(fpdBe.getPartyNumber());			
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
				input.setNightBatchOption("ChangePartyFullScan"); //異動客戶掃全名單類型
				countUniqueKey++;
				input.setUniqueKey(uniqueKey+"_D2_"+String.valueOf(countUniqueKey));
				input.setReferenceNumber(referenceNumber);
				input.setPartyNumber(fpdBe.getPartyNumber());
				/*
				if(fscPartyAssocList.size() > 0 ){ //有串到關係人
					input.setPartyNumber(fscPartyAssocList.get(0).getId().getPartyNumber());   //放原始本行客戶
				}else{
					input.setPartyNumber("");
				}
				*/
				
							
				//組合Input中 Detail資訊
				List<BatchNameCheckInputDetailBean> detailList = new ArrayList<BatchNameCheckInputDetailBean>();				
				String entity_type = getEntityType(fpdBe.getPartyTypeCode());
				
				//處理party_name1
				if(!StringUtils.isEmpty(fpdBe.getPartyName())){
					BatchNameCheckInputDetailBean detailBe =  new BatchNameCheckInputDetailBean();
					detailBe.setCheckSeq(String.valueOf(checkSeq));
					/*
					if(fscPartyAssocList.size() > 0 ){ //有串到關係人
						detailBe.setCheckSeq(String.valueOf(checkSeq)+"_!_"+fpdBe.getPartyNumber());
					}else{  //沒串到關係人
						detailBe.setCheckSeq(String.valueOf(checkSeq)+"_!_"+fpdBe.getPartyNumber());
					}
					*/
					
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
						//detailBe.setCountry(fpdBe.getCitizenshipCountryName());//查國家
						detailBe.setCountry(fpdBe.getCitizenshipCountryCode());
						
					detailList.add(detailBe);
					checkSeq++;
				}

				//處理party_NameEng
				if(!StringUtils.isEmpty(fpdBe.getPartyNameEng())){
					BatchNameCheckInputDetailBean detailBe =  new BatchNameCheckInputDetailBean();
					detailBe.setCheckSeq(String.valueOf(checkSeq));
					/*
					if(fscPartyAssocList.size() > 0 ){ //有串到關係人
						detailBe.setCheckSeq(String.valueOf(checkSeq)+"_!_"+fpdBe.getPartyNumber());
					}else{
						detailBe.setCheckSeq(String.valueOf(checkSeq)+"_!_"+fpdBe.getPartyNumber());
					}
					*/
					
					detailBe.setEntityType(entity_type); //有區分個人 or 法人
					detailBe.setEntityRelationship("01");
					/*
					if(fscPartyAssocList.size() >0){ //是否有串到關係人
						detailBe.setEntityRelationship(fscPartyAssocList.get(0).getId().getRelationshipTypeCode()); //關係人
					}else{
						detailBe.setEntityRelationship("");
					}
					*/
					
					detailBe.setEntityRelationshipDesc(""); //關係人描述
					String partyNameEng = StringUtils.convertToHalfWidth(fpdBe.getPartyNameEng()).trim();
						if(StringUtils.isEnglish(partyNameEng)){
	 						detailBe.setEnglishName(partyNameEng);
	 						detailBe.setNonEnglishName("");
	 					}else{
	 						detailBe.setEnglishName("");
	 						detailBe.setNonEnglishName(partyNameEng);
	 					}
						detailBe.setMatchCodeEntityName(fpdBe.getMatchCodePartyNameEng());
					detailBe.setIdNumber(fpdBe.getPartyIdentificationId());//查ID
					if(fpdBe.getPartyDateOfBirth() !=null){
						detailBe.setYearOfBirth(new SimpleDateFormat("yyyy").format(fpdBe.getPartyDateOfBirth())); //查出生年
					}
					
					//detailBe.setCountry(fpdBe.getCitizenshipCountryName());//查國家
					detailBe.setCountry(fpdBe.getCitizenshipCountryCode());
					detailList.add(detailBe);
					checkSeq++;
				}			
				input.setSeq(detailList);
				nameCheckInputList.add(input);	

		}
		// 執行 Thread AML_BatchNameCheck
		threadAMLBatchNameCheck(nameCheckInputList);
		
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
	
	/**
	 * Inclusive 方式掃描(名單主檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscEntityNcWatchListDim>
	 */
	private Map<Long,List<String>> findByInclusiveMatchList(String entityName, String entityTypeCode){
		Map<Long,List<String>> resultMapList = new HashMap<Long,List<String>>();
		List<String> inclusiveList = new ArrayList<String>();
		if(!StringUtils.isEmpty(entityName)){
			List<List<String>> cnList = WatchListUtil.permutation(entityName, false);
			if(cnList != null && cnList.size() > 0){
				handelInclusiveString(inclusiveList, cnList);
			}
		}
		if(!StringUtils.isEmpty(entityName)){
			List<List<String>> enList = WatchListUtil.permutation(entityName, true);
			if(enList != null && enList.size() > 0){
				handelInclusiveString(inclusiveList, enList);
			}
		}
		if(inclusiveList.size() > 0){
			resultMapList = iFscPartyDimDao.queryCandidateInclusive(inclusiveList, entityTypeCode);
		}
		return resultMapList;
	}
	
	/**
	 * Inclusive 字串轉換處理
	 * @param inclusiveList
	 * @param nameList
	 */
	private void handelInclusiveString(List<String> inclusiveList, List<List<String>> nameList){
		for(List<String> strList : nameList){
			StringBuffer sb = new StringBuffer();
			for(String str : strList){
				sb.append(String.format("AND \"%s\" ", str));
			}
			inclusiveList.add(sb.substring(4));
		}
	}
	
	
}
