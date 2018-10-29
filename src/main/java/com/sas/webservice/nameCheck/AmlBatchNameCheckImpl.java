package com.sas.webservice.nameCheck;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dataflux.xsd.archserver.RowIn;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.ICaseLiveDao;
import com.sas.db.aml.dao.ecm.IRefTableValueDao;
import com.sas.db.aml.dao.fcfcore.IFscBankNcWatchListDimDao;
import com.sas.db.aml.dao.fcfcore.IFscEntityNcWatchListDimDao;
import com.sas.db.aml.dao.fcfcore.IFscEntityWatchListDimChngDao;
import com.sas.db.aml.dao.fcfcore.IFscEntityWlXListSettingDao;
import com.sas.db.aml.dao.fcfcore.IFscLocationNcWatchListDimDao;
import com.sas.db.aml.dao.fcfcore.IXComboWhitelistMainDao;
import com.sas.db.aml.dao.fcfcore.IXScreenProcessSettingDao;
import com.sas.db.aml.dao.fcfcore.IXWatchlistCompressStringDao;
import com.sas.db.aml.dao.fcfcore.IXWatchlistSettingDao;
import com.sas.db.aml.orm.ecm.CaseLive;
import com.sas.db.aml.orm.ecm.RefTableValue;
import com.sas.db.aml.orm.fcfcore.FscBankNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscEntityNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscEntityWatchListDimChng;
import com.sas.db.aml.orm.fcfcore.FscLocationNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.XComboWhitelistMain;
import com.sas.db.aml.orm.fcfcore.XFscEntityWlXListSetting;
import com.sas.db.aml.orm.fcfcore.XPartyWhitelistMain;
import com.sas.db.aml.orm.fcfcore.XPartyWhitelistMainPK;
import com.sas.db.aml.orm.fcfcore.XScreenProcessSetting;
import com.sas.db.aml.orm.fcfcore.XWatchlistCompressString;
import com.sas.db.aml.orm.fcfcore.XWatchlistSetting;
import com.sas.db.wlf.dao.IBranchNumberActionDao;
import com.sas.db.wlf.dao.ICallingSystemActionDao;
import com.sas.db.wlf.dao.IKeyGenerateDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordDetailDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordMainDao;
import com.sas.db.wlf.dao.nc.INameHitRecordDao;
import com.sas.db.wlf.orm.CallingSystemAction;
import com.sas.db.wlf.orm.CallingSystemActionPK;
import com.sas.db.wlf.orm.KeyGenerate;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetailPK;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;
import com.sas.db.wlf.orm.nc.NameCheckRecordMainPK;
import com.sas.db.wlf.orm.nc.NameHitRecord;
import com.sas.util.AmlConfiguration;
import com.sas.util.NameCheckUtil;
import com.sas.util.StringUtils;
import com.sas.webservice.createCase.AmlCreateCase;
import com.sas.webservice.createCase.bean.CreateCaseInputBean;
import com.sas.webservice.nameCheck.bean.BatchNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.BatchNameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.BatchNameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputDetail;
import com.sas.wlsearch.business.DataFluxMatchCode;
import com.sas.wlsearch.util.WatchListUtil;
import com.sas.db.aml.dao.fcfcore.IXPartyWhitelistMainDao;

/**
 * AML Name Check 主程式
 * @author SAS
 *
 */
@Component
@WebService(endpointInterface = "com.sas.webservice.nameCheck.AmlBatchNameCheck")
public class AmlBatchNameCheckImpl implements AmlBatchNameCheck {
	private static final Logger logger = LoggerFactory.getLogger(AmlBatchNameCheckImpl.class);
	@Autowired
	IKeyGenerateDao iKeyGenerateDao;
	@Autowired
	INameCheckRecordMainDao iNameCheckRecordMainDao;
	@Autowired
	INameCheckRecordDetailDao iNameCheckRecordDetailDao;
	@Autowired
	INameHitRecordDao iNameHitRecordDao;
	@Autowired
	IFscEntityNcWatchListDimDao iFscEntityNcWatchListDimDao;
	@Autowired
	IFscEntityWatchListDimChngDao iFscEntityWatchListDimChngDao;
	@Autowired
	IFscBankNcWatchListDimDao iFscBankNcWatchListDimDao;
	@Autowired
	IFscLocationNcWatchListDimDao iFscLocationNcWatchListDimDao;
	@Autowired
	IFscEntityWlXListSettingDao iFscEntityWlXListSettingDao;
	@Autowired
	IXScreenProcessSettingDao iXScreenProcessSettingDao;
	@Autowired
	IXWatchlistSettingDao iXWatchlistSettingDao;
	@Autowired
	DataFluxMatchCode dataFluxMatchCode;
	@Autowired
	AmlCreateCase amlCreateCase;
	@Autowired
	IRefTableValueDao iRefTableValueDao;
	@Autowired
	IXComboWhitelistMainDao iXComboWhitelistMainDao;
	@Autowired
	ICallingSystemActionDao iCallingSystemActionDao;
	@Autowired
	IBranchNumberActionDao iBranchNumberActionDao;
	@Autowired
	ICaseLiveDao iCaseLiveDao;
	@Autowired
	IXWatchlistCompressStringDao iXWatchlistCompressStringDao;	
	@Autowired
	IXPartyWhitelistMainDao iXPartyWhitelistMainDao;	
	
	
	/**
	 * AML Name Check 主要程式, 交易控制需額外處理
	 */
	@Override
	public BatchNameCheckOutputBean NonTransactionNameCheck(BatchNameCheckInputBean input) {
		BatchNameCheckOutputBean nameCheckOutputBean = new BatchNameCheckOutputBean();
		List<NameCheckOutputDetail> nameCheckOutputDetailList = new ArrayList<NameCheckOutputDetail>();
		String interfaceName = input.getInterfaceName();
		String callingSystem = input.getCallingSystem();
		String screenProcess = input.getScreenProcess();
		String callingUser = input.getCallingUser();
		String businessUnitID = input.getBusinessUnitID();
		String branchNumber = input.getBranchNumber();
		String genAlertFlag = input.getGenAlertFlag();
		Date transactionDate = input.getTransactionDate();
		String uniqueKey = input.getUniqueKey();
		String referenceNumber = input.getReferenceNumber();
		String partyNumber = input.getPartyNumber();
		String nightBatchOption = input.getNightBatchOption();
		List<BatchNameCheckInputDetailBean> seq = input.getSeq();
		logger.info("Thread partyNumber:{} uniKey:{} start", partyNumber, uniqueKey);
		long chkFieldsStartTime = System.currentTimeMillis();
		//check input format
		if(chkFields(interfaceName, callingSystem, screenProcess, branchNumber, genAlertFlag, uniqueKey, seq)){
			nameCheckOutputBean.setInterfaceName(interfaceName);
			nameCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_FORMATERR);
			nameCheckOutputBean.setErrorMessage(SwiftMtConst.ERROR_CODE_FORMATERR_MESSAGE);
			nameCheckOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
			nameCheckOutputBean.setUniqueKey(uniqueKey);
			nameCheckOutputBean.setRefernceNumber(referenceNumber);
			nameCheckOutputBean.setRouteRule("");
			nameCheckOutputBean.setHitSeq("");
			nameCheckOutputBean.setSeq(null);
			return nameCheckOutputBean;
		}
		long chkFieldsEndTime = System.currentTimeMillis();
		logger.debug("Thread partyNumber:{} uniKey:{} chkFieldsTime:{}", partyNumber, uniqueKey,(chkFieldsEndTime - chkFieldsStartTime));
		try{
			long keyGenerateStartTime = System.currentTimeMillis();
			List<KeyGenerate> keyGenerateList = iKeyGenerateDao.findByUniqueKeyOrderByProcessDttmDesc(uniqueKey);
			long keyGenerateEndTime = System.currentTimeMillis();
			logger.debug("Thread partyNumber:{}  uniKey:{} findkeyGenerateTime:{}", partyNumber, uniqueKey,(keyGenerateEndTime - keyGenerateStartTime));
			
			Integer ncReferenceId = null;
			if(!isUnique(keyGenerateList, callingSystem)){
				logger.info(String.format("uniqueKey : %s, conflict.", uniqueKey));
				ncReferenceId = keyGenerateList.get(0).getReferenceId();
				nameCheckOutputBean.setInterfaceName(interfaceName);
				nameCheckOutputBean.setNcReferenceId(String.valueOf(ncReferenceId));
				nameCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_UNIQUE_KEY_CONFLICT);
				nameCheckOutputBean.setErrorMessage(String.format("Unique Key : %s already in used.", uniqueKey));
				nameCheckOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
				nameCheckOutputBean.setUniqueKey(uniqueKey);
				nameCheckOutputBean.setRefernceNumber(referenceNumber);
				nameCheckOutputBean.setRouteRule("");
				nameCheckOutputBean.setHitSeq("");
				nameCheckOutputBean.setSeq(null);
			}else{
				long genKeyStartTime = System.currentTimeMillis();
				KeyGenerate keyGenerate = new KeyGenerate();
				keyGenerate.setUniqueKey(uniqueKey);
				keyGenerate.setInterfaceType(SwiftMtConst.INTERFACE_TYPE_NAMECHECK);
				keyGenerate.setProcessDttm(new Date());
				iKeyGenerateDao.save(keyGenerate);
				ncReferenceId = keyGenerate.getReferenceId();
				long genKeyEndTime = System.currentTimeMillis();
				logger.debug("Thread unikey:{} ncRfId:{} genKeyTime:{}", input.getUniqueKey(), ncReferenceId,(genKeyEndTime - genKeyStartTime));
				
				long getSCSettingStartTime = System.currentTimeMillis();
				List<NameCheckRecordDetail> nameCheckRecordDetailList = new ArrayList<NameCheckRecordDetail>();
				List<NameHitRecord> totalNameHitRecordList = new ArrayList<NameHitRecord>();
				Map<String, XScreenProcessSetting> xScreenProcessSettingMap = getXScreenProcessSettingMap(screenProcess);
				long getSCSettingSettingEndTime = System.currentTimeMillis();
				logger.debug("Thread unikey:{} ncRfId:{} genKeyTime:{}", input.getUniqueKey(),(getSCSettingSettingEndTime - getSCSettingStartTime));
				
				long getWatchSettingStartTime = System.currentTimeMillis();
				XWatchlistSetting xWatchlistSetting = iXWatchlistSettingDao.findByChangeCurrentInd(SwiftMtConst.CHANGE_CURRENT_IND_Y);
				Map<String, String> rankOfWatchListMap = getRankOfWatchListMap();
				Map<String, String> rankOfSubWatchListMap = getRankOfSubWatchListMap();
				Map<String, String> subWatchListTypeAndWatchListTypeMappingMap = getSubWatchListTypeAndWatchListTypeMapping();
				long getWatchSettingEndTime = System.currentTimeMillis();
				logger.debug("Thread unikey:{} ncRfId:{} genKeyTime:{}", input.getUniqueKey(),(getWatchSettingEndTime - getWatchSettingStartTime));
				
				//FIXME
				Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap= xWatchlistCompressString();
				
				boolean isNcResult = false;
				for(BatchNameCheckInputDetailBean detail :  seq){
					String checkSeq = detail.getCheckSeq();
					String entityType = detail.getEntityType();
					String entityRelationship = detail.getEntityRelationship();
					String entityRelationshipDesc = detail.getEntityRelationshipDesc();
					String nonEnglishName = detail.getNonEnglishName();
					String englishName = detail.getEnglishName();
					String cccCode = detail.getCccCode();
					String idNumber = detail.getIdNumber();
					String bicSwiftCode = detail.getBicSwiftCode();
					String freeFormatText = detail.getFreeFormatText();
					String country = detail.getCountry();
					String yearOfBirth = detail.getYearOfBirth();
					String gender = detail.getGender();									
					String checkResult = SwiftMtConst.NC_RESULT_NO_HIT;
					String routeRule = "";					
					String matchCodeEntityName = detail.getMatchCodeEntityName();
					boolean isInPartyWhiteList=checkInPartyWhitelist(partyNumber);			

					List<NameHitRecord> nameHitRecordList = new ArrayList<NameHitRecord>();
					List<NameHitRecord> countryCodeNameHitRecordList = new ArrayList<NameHitRecord>();
					//輸入欄位資訊判斷
					if(chkScanFields(screenProcess, englishName, nonEnglishName, cccCode, idNumber, bicSwiftCode, freeFormatText, country)){
						checkResult = SwiftMtConst.NC_RESULT_NON_CHECK;
					}else{
						if(!isInPartyWhiteList){ //判斷是否為白名單的客戶資料
							if("04".equals(entityType)){ //entity_type = 04(銀行) 掃描方式   //夜批沒有這段
								long getBnkStartTime = System.currentTimeMillis();
								nameHitRecordList = handleBankNameHitRecordList(uniqueKey, ncReferenceId, checkSeq, englishName, nonEnglishName, bicSwiftCode, xWatchlistSetting);
								if(!StringUtils.isEmpty(bicSwiftCode)){
									String countryCode = bicSwiftCode.substring(5, 6);
									countryCodeNameHitRecordList = handleCountryCode(uniqueKey, ncReferenceId, checkSeq, countryCode, xWatchlistSetting);
								}
								long getBnkEndTime = System.currentTimeMillis();
								logger.debug("Thread unikey:{} ncRfId:{} CheckSeq:{} getBnkTime:{}", input.getUniqueKey(), ncReferenceId, checkSeq,(getBnkEndTime - getBnkStartTime));
							}else if("08".equals(entityType)){  //entity_type = 08(國家) 掃描方式   //夜批沒有這段
								long getLocationStartTime = System.currentTimeMillis();
								nameHitRecordList = handleLocationameHitRecordList(uniqueKey, ncReferenceId, checkSeq, englishName, nonEnglishName, xWatchlistSetting);
								long getLocationEndTime = System.currentTimeMillis();
								logger.debug("Thread unikey:{} ncRfId:{} CheckSeq:{} genLocationTime:{}", input.getUniqueKey(), ncReferenceId, checkSeq,(getLocationEndTime - getLocationStartTime));
							}else{ 
								long getNormalNcStartTime = System.currentTimeMillis();
								nameHitRecordList = handleNameHitRecordList(uniqueKey, ncReferenceId, checkSeq, englishName, nonEnglishName, cccCode,
										idNumber, bicSwiftCode, freeFormatText, country, screenProcess, entityType, gender, matchCodeEntityName, xWatchlistSetting, nightBatchOption, xWatchlistCompressStringMap);
								long getNormalNcEndTime = System.currentTimeMillis();
								logger.debug("Thread unikey:{} ncRfId:{} CheckSeq:{} genLocationTime:{}",  input.getUniqueKey(), ncReferenceId, checkSeq,(getNormalNcEndTime - getNormalNcStartTime));
							}
						}
					}
					List<String> entityTypeList = handelEntityType(entityType);
					
					nameHitRecordList = setWatchListType(nameHitRecordList, xScreenProcessSettingMap, entityTypeList,rankOfWatchListMap);
					//剔除交易對手白名單
					if(partyNumber != null && !"".equals(partyNumber) && (SwiftMtConst.SCREEN_PROCESS_Transaction_Screening.equals(screenProcess) || SwiftMtConst.SCREEN_PROCESS_Trade_Finance_Screening.equals(screenProcess)) && "05".equals(entityRelationship)){
						filterXComboWhitelist(partyNumber, nameHitRecordList);
					}
					
					if(nameHitRecordList != null && nameHitRecordList.size() > 0){

						for(NameHitRecord nameHitRecord : nameHitRecordList){
							NameCheckUtil.calculateScore(nameHitRecord,	nonEnglishName, englishName, idNumber, country, yearOfBirth, xWatchlistSetting, entityType);
						}
						
						filterDataByScore(nameHitRecordList, xScreenProcessSettingMap);
						
						for(NameHitRecord nameHitRecord : nameHitRecordList){
							routeRule = NameCheckUtil.sortRule(routeRule, nameHitRecord.getWatchListSubTypeCd(),
									rankOfSubWatchListMap,
									SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
						}
						if(nameHitRecordList.size()>0){
							checkResult = SwiftMtConst.NC_RESULT_HIT;
							isNcResult = true;
						}
					}
					
					
					List<NameHitRecord> newNameHitRecordList=setNameHitRecordListWatchListSubTypeCd(nameHitRecordList);
					totalNameHitRecordList.addAll(newNameHitRecordList);
					
					if(countryCodeNameHitRecordList.size() > 0){
						countryCodeNameHitRecordList = setWatchListType(countryCodeNameHitRecordList, xScreenProcessSettingMap, entityTypeList,rankOfWatchListMap);
						totalNameHitRecordList.addAll(countryCodeNameHitRecordList);
						
						for(NameHitRecord nameHitRecord : countryCodeNameHitRecordList){
							nameHitRecord.setMixScore(nameHitRecord.getExactMatchScore());
							//2018.2.22 jerry BOT detail routeRule 為多個分類以逗號區隔
							routeRule = NameCheckUtil.sortRule(routeRule, nameHitRecord.getWatchListSubTypeCd());
						}
					}
					
					NameCheckOutputDetail nameCheckOutputDetail = new NameCheckOutputDetail();
					nameCheckOutputDetailList.add(nameCheckOutputDetail);
					nameCheckOutputDetail.setCheckSeq(checkSeq);
					nameCheckOutputDetail.setCheckResult(checkResult);
					nameCheckOutputDetail.setRouteRule(routeRule);
					
					NameCheckRecordDetail nameCheckRecordDetail = setNameCheckRecordDetail(uniqueKey, ncReferenceId, checkSeq, entityType, entityRelationship, 
							entityRelationshipDesc, englishName, nonEnglishName, cccCode, idNumber, bicSwiftCode, freeFormatText, country, yearOfBirth, gender, routeRule, checkResult);
					nameCheckRecordDetailList.add(nameCheckRecordDetail);
					
				}
				
				String ncResult = isNcResult ? SwiftMtConst.NC_RESULT_HIT : SwiftMtConst.NC_RESULT_NO_HIT;
				String routeRule = "";
				String hitSeq = "";
				boolean needCreateCase = SwiftMtConst.CREATE_CASE_N.equalsIgnoreCase(genAlertFlag);
				if(SwiftMtConst.CREATE_CASE_Y.equalsIgnoreCase(genAlertFlag)) {
					CallingSystemAction callingSystemAction = iCallingSystemActionDao.findOne(new CallingSystemActionPK(callingSystem, "*"));
					if (callingSystemAction != null) {
						String dbNeedCreateCase = callingSystemAction.getCreateCase();
						if (StringUtils.isEmpty(dbNeedCreateCase) == false) {
							needCreateCase = SwiftMtConst.CREATE_CASE_Y.equalsIgnoreCase(dbNeedCreateCase);
						}
					}
				}
				
				if(nameCheckRecordDetailList != null && nameCheckRecordDetailList.size() > 0){
					for(NameCheckRecordDetail nameCheckRecordDetail : nameCheckRecordDetailList){
//						routeRule = NameCheckUtil.judgeRule(routeRule, nameCheckRecordDetail.getRouteRule());
						//routeRule = NameCheckUtil.seriousRule(routeRule, nameCheckRecordDetail.getRouteRule(), rankOfWatchListMap);
						routeRule = NameCheckUtil.seriousRule(routeRule, nameCheckRecordDetail.getRouteRule(), subWatchListTypeAndWatchListTypeMappingMap, rankOfWatchListMap);
					}
					for(NameCheckRecordDetail nameCheckRecordDetail : nameCheckRecordDetailList){
						String detailRouteRule = nameCheckRecordDetail.getRouteRule();
						if(detailRouteRule!= null && !"".equals(detailRouteRule) && NameCheckUtil.checkWatchlistTypeRelationship(subWatchListTypeAndWatchListTypeMappingMap, detailRouteRule, routeRule)){
							hitSeq += String.format(",%s", nameCheckRecordDetail.getId().getCheckSeq());
						}
					}
					 if(hitSeq.length() > 0 && hitSeq.indexOf(",") > -1){
				         String hitSeqTmp = hitSeq.substring(1);
				        if(hitSeqTmp.indexOf(",")>0){
				             hitSeq = hitSeqTmp.substring(0,hitSeqTmp.indexOf(","));
				         }else{
				             hitSeq = hitSeqTmp;
				         }
				     }
				}
				
				//2018.2.22 jerry BOT Main routeRule 為多個分類中最嚴重分類
				NameCheckRecordMain nameCheckRecordMain = setNameCheckRecordMain(uniqueKey, ncReferenceId, interfaceName, callingSystem, screenProcess, callingUser, 
						businessUnitID, branchNumber, genAlertFlag, transactionDate, referenceNumber, ncResult, routeRule, hitSeq, partyNumber);
				iNameHitRecordDao.batchSave(totalNameHitRecordList);
				iNameCheckRecordDetailDao.batchSave(nameCheckRecordDetailList);
				iNameCheckRecordMainDao.save(nameCheckRecordMain);
								
				if(totalNameHitRecordList.size() > 0){
					// create case for name check
					BigDecimal caseRk = null;
					SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd");
					String sourceType = NameCheckUtil.getSourceType(interfaceName, screenProcess);
					CreateCaseInputBean createCaseInputBean = new CreateCaseInputBean(String.valueOf(ncReferenceId), callingSystem, callingUser, 
							branchNumber, routeRule, spf.format(transactionDate), 
							hitSeq, partyNumber, referenceNumber, 
							uniqueKey, sourceType, false,
							screenProcess);
					
					if(needCreateCase){
						caseRk = amlCreateCase.createCase(createCaseInputBean);
						nameCheckRecordMain.setNcResult(SwiftMtConst.NC_RESULT_PENDING);
						nameCheckRecordMain.setCaseRk(caseRk.longValue());
						iNameCheckRecordMainDao.save(nameCheckRecordMain);						
						nameCheckOutputBean.setCaseRk(caseRk);
					}else{
						iNameCheckRecordMainDao.save(nameCheckRecordMain);
					}
				}
				
				nameCheckOutputBean.setInterfaceName(interfaceName);
				nameCheckOutputBean.setNcReferenceId(String.valueOf(ncReferenceId));
				nameCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SUCCESS);
				nameCheckOutputBean.setErrorMessage("");
				nameCheckOutputBean.setNcResult(ncResult);
				nameCheckOutputBean.setUniqueKey(uniqueKey);
				nameCheckOutputBean.setRefernceNumber(referenceNumber);
				nameCheckOutputBean.setRouteRule(routeRule);
				nameCheckOutputBean.setHitSeq(hitSeq);
				nameCheckOutputBean.setSeq(nameCheckOutputDetailList);
			}
		}catch(Exception e){
			logger.error(String.format("NameCheck fail, exception : %s", e.getMessage()), e);
			nameCheckOutputBean.setInterfaceName(interfaceName);
			nameCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SYSTEMERR);
			nameCheckOutputBean.setErrorMessage(e.getMessage());
			nameCheckOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
			nameCheckOutputBean.setUniqueKey(uniqueKey);
			nameCheckOutputBean.setRefernceNumber("");
			nameCheckOutputBean.setRouteRule("");
			nameCheckOutputBean.setHitSeq("");
			nameCheckOutputBean.setSeq(null);
		}
		logger.info(nameCheckOutputBean.toString());
		
		return nameCheckOutputBean;
	}
	
	/**
	 * 	2018.2.22 jerry BOT nameHitRecord 大分類=小分類
	 */
	public 	List<NameHitRecord> setNameHitRecordListWatchListSubTypeCd(List<NameHitRecord> nameHitRecordList){
		List<NameHitRecord> newNameHitRecordList = nameHitRecordList;
		for(NameHitRecord nameHitRecord : newNameHitRecordList){
			nameHitRecord.setWatchListSubTypeCd(nameHitRecord.getWatchListTypeCd());
		}
		return newNameHitRecordList;
	}
	
	/***
	 * 字串處理方法
	 * @param inputString
	 * @return
	 */
//	public Map standardrizeInput(String nonEnglishName,String englishName,String entityType,Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap){
//		//Jerry 2017.02.05 standardrizeInput
//		Map<String,String> nameMap=StringUtils.standardrizeInput(entityType, nonEnglishName,englishName,xWatchlistCompressStringMap);
//		return nameMap;
//	}
	/**
	 * interface Name Check 各欄位檢核
	 * @param interfaceName
	 * @param callingSystem
	 * @param screenProcess
	 * @param branchNumber
	 * @param genAlertFlag
	 * @param transactionDate
	 * @param uniqueKey
	 * @param referenceNumber
	 * @param seq
	 */
	private boolean chkFields(String interfaceName, String callingSystem, String screenProcess, String branchNumber, String genAlertFlag, String uniqueKey, List<BatchNameCheckInputDetailBean> seq){
		boolean result = false;
		if(StringUtils.isEmpty(interfaceName) 
				|| (!SwiftMtConst.INTERFACE_TYPE_NAMECHECK.equals(interfaceName) && !SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK.equals(interfaceName) && 
					!SwiftMtConst.INTERFACE_TYPE_BATCHTRANSACTIONSCREENING.equals(interfaceName) && !SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECKSTATUS.equals(interfaceName))
		){
			result = true;
		}
		
		if(StringUtils.isEmpty(callingSystem)
		){
			result = true;
		}
		
		if(screenProcess == null || "".equals(screenProcess)
				|| (!SwiftMtConst.SCREEN_PROCESS_Account_Opening.equals(screenProcess) && !SwiftMtConst.SCREEN_PROCESS_Customer_Event.equals(screenProcess) && 
					!SwiftMtConst.SCREEN_PROCESS_Transaction_Screening.equals(screenProcess) && !SwiftMtConst.SCREEN_PROCESS_Trade_Finance_Screening.equals(screenProcess) && 
					!SwiftMtConst.SCREEN_PROCESS_SWIFT_Screening.equals(screenProcess) && !SwiftMtConst.SCREEN_PROCESS_BATCH_NAME_CHECKING.equals(screenProcess) && 
					!SwiftMtConst.SCREEN_PROCESS_BATCH_TRANCTION_SCREENING.equals(screenProcess) && !SwiftMtConst.SCREEN_PROCESS_BATCH_TRADE_FINANCE_SCREENING.equals(screenProcess) && 
					!SwiftMtConst.SCREEN_PROCESS_BATCH_SWIFT_SCREENING.equals(screenProcess) && !SwiftMtConst.SCREEN_PROCESS_ONLINE_NAME_CHECKING.equals(screenProcess) && 
					!SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_BL_SCREENING.equals(screenProcess) && !SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_INV_SCREENING.equals(screenProcess)
					)
		){
			result = true;
		}
		
		if(StringUtils.isEmpty(branchNumber)
		){
			result = true;
		}
		
		if(StringUtils.isEmpty(genAlertFlag)
		){
			result = true;
		}
		
		if(StringUtils.isEmpty(uniqueKey)
		){
			result = true;
		}
		String limit = AmlConfiguration.getString("com.sas.namecheck.detail.seq.limit");
		int seqLimit = StringUtils.isEmpty(limit) ? 20 : Integer.parseInt(limit);
		if(seq == null || seq.size() == 0 || seq.size() > seqLimit
		){
			result = true;
		}
		return result;
	}
	
	/**
	 * 需做交易控制的name check
	 */
	@Override
	@Transactional
	public BatchNameCheckOutputBean NameCheck(BatchNameCheckInputBean input) {
		return NonTransactionNameCheck(input);
	}
	
	/**
	 * 剔除交易組合白名單
	 * @param partyNumber
	 * @param nameHitRecordList
	 */
	private void filterXComboWhitelist(String partyNumber, List<NameHitRecord> nameHitRecordList){
		List<XComboWhitelistMain> xComboWhitelistMainList = iXComboWhitelistMainDao.findByPartyNumber(partyNumber);
		Map<String, XComboWhitelistMain> xComboWhitelistMainMap = new HashMap<String, XComboWhitelistMain>();
		for(XComboWhitelistMain main :  xComboWhitelistMainList){
			xComboWhitelistMainMap.put(main.getBeneficiaryEntityWatchListKey(), main);
		}
		
		Iterator<NameHitRecord> iterator = nameHitRecordList.iterator();
		int count = 0;
		while(iterator.hasNext()){
			NameHitRecord nameHitRecord = iterator.next();
			XComboWhitelistMain main = xComboWhitelistMainMap.get(String.valueOf(nameHitRecord.getEntityWatchListKey()));
			if(main != null){
				iterator.remove();
			}
			count++;
		}
	}
	
	/*
	 * 取得名單大小類的對應資訊
	 * */
	private Map<String, String> getSubWatchListTypeAndWatchListTypeMapping() {
		Map<String, String> resultMap = new HashMap<String, String>();
		List<RefTableValue> watchListTypeCdList = iRefTableValueDao
				.findByIdRefTableNm(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
		for(RefTableValue value : watchListTypeCdList){
			resultMap.put(value.getId().getValueCd(), value.getRefTableValue().getId().getValueCd());
		}
		return resultMap;
	}
	
	/**
	 * 取得名單大類的顯示順序，來代表嚴重程度
	 */
	private Map<String, String> getRankOfWatchListMap(){
		Map<String, String> resultMap = new HashMap<String, String>();
		List<RefTableValue> watchListTypeCdList = iRefTableValueDao.findByIdRefTableNm(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
		for(RefTableValue value : watchListTypeCdList){
			resultMap.put(value.getId().getValueCd(), value.getDisplayOrderNo().toString());
		}
		
		return resultMap;
	}
	
/**
 * 判斷之前是否有查詢過的紀錄
 * @param keyGenerateList
 * @param callingSystem
 * @return isUnique
 */
	private boolean isUnique(List<KeyGenerate> keyGenerateList, String callingSystem){
		boolean result = true;
		if(keyGenerateList != null && keyGenerateList.size() > 0){
			for(KeyGenerate keyGenerate : keyGenerateList){
				NameCheckRecordMain nameCheckRecordMain = iNameCheckRecordMainDao.findOne(new NameCheckRecordMainPK(keyGenerate.getUniqueKey(), keyGenerate.getReferenceId()));
				if(nameCheckRecordMain != null && callingSystem.equalsIgnoreCase(nameCheckRecordMain.getCallingSystem())){
					return false;
				}
			}
		}
		return result;
	}
	
	/**
	 * 黑名單掃描方法
	 * @param uniqueKey
	 * @param ncReferenceId
	 * @param checkSeq
	 * @param englishName
	 * @param nonEnglishName
	 * @param cccCode
	 * @param idNumber
	 * @param bicSwiftCode
	 * @param freeFormatText
	 * @param country
	 * @param screenProcess
	 * @param entityType
	 * @param gender
	 * @param xWatchlistSetting
	 * @return List<NameHitRecord>
	 */
	private List<NameHitRecord> handleNameHitRecordList(
			String uniqueKey, int ncReferenceId, String checkSeq, 
			String englishName, String nonEnglishName, String cccCode,
			String idNumber, String bicSwiftCode, String freeFormatText,
			String country, String screenProcess, String entityType, String gender, String matchCodeEntityName, XWatchlistSetting xWatchlistSetting, String nightBatchOption, Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap){
		
		List<FscEntityNcWatchListDim> exactMatchList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscEntityNcWatchListDim> fuzzyMatchList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscEntityNcWatchListDim> inclusiveMatchList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscEntityNcWatchListDim> cccCodeList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscEntityNcWatchListDim> isNumberList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscEntityNcWatchListDim> freeFormatTextList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscBankNcWatchListDim> bicSwiftCodeList = new ArrayList<FscBankNcWatchListDim>();
		List<FscLocationNcWatchListDim> countryList = new ArrayList<FscLocationNcWatchListDim>();
		
		if(!StringUtils.isEmpty(englishName) || !StringUtils.isEmpty(nonEnglishName)){
		//	String [] stand_ch = ("03,09").indexOf(entityType) > -1 ? AmlConfiguration.getString(SwiftMtConst.COM_SAS_STANDARDIZATION_CHINESE).split(",") : new String[0];
		//	String [] stand_en = ("03,09").indexOf(entityType) > -1 ? AmlConfiguration.getString(SwiftMtConst.COM_SAS_STANDARDIZATION_ENGLISH).split(",") : new String[0];
			//FIXME
			Map<String,Map<String,String>> nameMap= StringUtils.standardrizeInput(entityType,nonEnglishName,englishName,xWatchlistCompressStringMap);
			List<String> inputCnName =StringUtils.getNameList(nameMap, "cn");
			List<String> inputEnName =StringUtils.getNameList(nameMap, "en");
			

			long getExatStartTime = System.currentTimeMillis();
			exactMatchList = findByExactMatch(inputCnName, inputEnName, nightBatchOption); //有加入nightBatchOption
			long getExatEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getExactMatch:{}", uniqueKey, ncReferenceId, checkSeq,(getExatEndTime - getExatStartTime));
			
			long getFuzyStartTime = System.currentTimeMillis();
			fuzzyMatchList = findByFuzzyMatch(inputCnName, inputEnName, matchCodeEntityName, nightBatchOption); //有加入nightBatchOption
			long getFuzyEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getFuzyMatch:{}", uniqueKey, ncReferenceId, checkSeq,(getFuzyEndTime - getFuzyStartTime));
			
			long getIclStartTime = System.currentTimeMillis();
			inclusiveMatchList = findByInclusiveMatchList(inputCnName, inputEnName, nightBatchOption);
			long getIclEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getIclMatch:{}", uniqueKey, ncReferenceId, checkSeq,(getIclEndTime - getIclStartTime));
			
		}
		if(!StringUtils.isEmpty(cccCode)){
			long getCCCStartTime = System.currentTimeMillis();
			cccCodeList = findByCccCode(cccCode, nightBatchOption);
			long getCCCEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getCCC:{}", uniqueKey, ncReferenceId, checkSeq,(getCCCEndTime - getCCCStartTime));
		}
		if(!StringUtils.isEmpty(idNumber)){
			long getIdStartTime = System.currentTimeMillis();
			isNumberList = findByIdNumber(idNumber, nightBatchOption);
			long getIdEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getId:{}", uniqueKey, ncReferenceId, checkSeq,(getIdEndTime - getIdStartTime));
		}
		if(!StringUtils.isEmpty(bicSwiftCode)){  //夜批沒有這段
			
			long getBicStartTime = System.currentTimeMillis();
			bicSwiftCodeList = findByBicSwiftCode(bicSwiftCode);
			long getBicEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getBicStart:{}", uniqueKey, ncReferenceId, checkSeq,(getBicEndTime - getBicStartTime));
		}
		if(!StringUtils.isEmpty(freeFormatText)){   //夜批沒有這段
			long getFFTStartTime = System.currentTimeMillis();
			String inputFreeFormatText = WatchListUtil.convertWordTo(freeFormatText, SwiftMtConst.COVERTWORD, " ");
			freeFormatTextList = findByFreeFormatText(inputFreeFormatText);
			long getFFTEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getFFT:{}", uniqueKey, ncReferenceId, checkSeq,(getFFTEndTime - getFFTStartTime));
		}
//		if(!"1".equals(screenProcess) && !"2".equals(screenProcess) && !StringUtils.isEmpty(country) ){     //夜批沒有這段
//			long getCntryStartTime = System.currentTimeMillis();
//			String inputCountryName = WatchListUtil.convertWordTo(country, SwiftMtConst.COVERTWORD, "");
//			countryList = findByFuzzyCountryName(inputCountryName);
//			long getCntryEndTime = System.currentTimeMillis();
//			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getCntry:{}", uniqueKey, ncReferenceId, checkSeq,(getCntryEndTime - getCntryStartTime));
//		}
		
		List<NameHitRecord> nameHitRecordList = getNameHitRecordList(uniqueKey, ncReferenceId, checkSeq,
				exactMatchList, fuzzyMatchList, inclusiveMatchList, cccCodeList, isNumberList, freeFormatTextList, bicSwiftCodeList, countryList, entityType, gender, xWatchlistSetting);
		
		return nameHitRecordList;
	}
	
	/**
	 * entity_type = 08(國家)， 黑名單掃描方法
	 * @param uniqueKey
	 * @param ncReferenceId
	 * @param checkSeq
	 * @param englishName
	 * @param nonEnglishName
	 * @param xWatchlistSetting
	 * @return List<NameHitRecord>
	 */
	private List<NameHitRecord> handleLocationameHitRecordList(String uniqueKey, int ncReferenceId, String checkSeq, String englishName, String nonEnglishName, XWatchlistSetting xWatchlistSetting){
		List<NameHitRecord> nameHitRecordList = new ArrayList<NameHitRecord>();
		List<FscLocationNcWatchListDim> exactMatchLocationNameList = new ArrayList<FscLocationNcWatchListDim>();
		List<FscLocationNcWatchListDim> fuzzyMatchLocationNameList = new ArrayList<FscLocationNcWatchListDim>();
		List<FscLocationNcWatchListDim> inclusiveMatchLocationNameList = new ArrayList<FscLocationNcWatchListDim>();
		
		if(!StringUtils.isEmpty(englishName) || !StringUtils.isEmpty(nonEnglishName)){
			String inputCnName = StringUtils.standardizationInput(nonEnglishName, new String[0]);
			String inputEnName = StringUtils.standardizationInput(englishName, new String[0]);
			long getLocatExatStartTime = System.currentTimeMillis();
			exactMatchLocationNameList = findLocationByExactMatch(inputCnName, inputEnName);
			long getLocatExatEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getLocatExat:{}", uniqueKey, ncReferenceId, checkSeq,(getLocatExatEndTime - getLocatExatStartTime));
			
			long getLocatFuzyStartTime = System.currentTimeMillis();
			fuzzyMatchLocationNameList = findLocationByFuzzyMatch(inputCnName, inputEnName);
			long getLocatFuzyEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} LocatFuzy:{}", uniqueKey, ncReferenceId, checkSeq,(getLocatFuzyEndTime - getLocatFuzyStartTime));
			
			long getLocatIclStartTime = System.currentTimeMillis();
			inclusiveMatchLocationNameList = findLocationByInclusiveMatchList(inputCnName, inputEnName);
			long getLocatIclEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} LocatIcl:{}", uniqueKey, ncReferenceId, checkSeq,(getLocatIclEndTime - getLocatIclStartTime));
			
		}
		Map<Long, NameHitRecord> nameHitRecordMap = new HashMap<Long, NameHitRecord>();
		
		setLocationNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_EXACT, exactMatchLocationNameList, uniqueKey, ncReferenceId, checkSeq, xWatchlistSetting);	
		setLocationNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_FUZZY, fuzzyMatchLocationNameList, uniqueKey, ncReferenceId, checkSeq, xWatchlistSetting);	
		setLocationNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_INCLUSIVE, inclusiveMatchLocationNameList, uniqueKey, ncReferenceId, checkSeq, xWatchlistSetting);
		
		for (Map.Entry<Long, NameHitRecord> entry : nameHitRecordMap.entrySet()) {
			nameHitRecordList.add(entry.getValue());
		}
		return nameHitRecordList;
	}
	
	private List<NameHitRecord> handleCountryCode(String uniqueKey, int ncReferenceId, String checkSeq, String countryCode, XWatchlistSetting xWatchlistSetting){
		List<NameHitRecord> nameHitRecordList = new ArrayList<NameHitRecord>();
		List<FscLocationNcWatchListDim> exactMatchLocationNameList = new ArrayList<FscLocationNcWatchListDim>();
		
		if(!StringUtils.isEmpty(countryCode)){
			long getCountryCodeStartTime = System.currentTimeMillis();
			exactMatchLocationNameList = findCountryCodeByExactMatch(countryCode);
			long getCountryCodeEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} CountryExat:{}", uniqueKey, ncReferenceId, checkSeq,(getCountryCodeEndTime - getCountryCodeStartTime));
		}
		Map<Long, NameHitRecord> nameHitRecordMap = new HashMap<Long, NameHitRecord>();
		
		setLocationNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_EXACT, exactMatchLocationNameList, uniqueKey, ncReferenceId, checkSeq, xWatchlistSetting);
		for (Map.Entry<Long, NameHitRecord> entry : nameHitRecordMap.entrySet()) {
			nameHitRecordList.add(entry.getValue());
		}
		return nameHitRecordList;
	}
	
    /**
     * entity_type=04(銀行)，黑名單掃描方法
     * @param uniqueKey
     * @param ncReferenceId
     * @param checkSeq
     * @param englishName
     * @param nonEnglishName
     * @param bicSwiftCode
     * @param xWatchlistSetting
     * @return List<NameHitRecord>
     */
	private List<NameHitRecord> handleBankNameHitRecordList(String uniqueKey, int ncReferenceId, String checkSeq, String englishName, String nonEnglishName, String bicSwiftCode, XWatchlistSetting xWatchlistSetting){
		List<NameHitRecord> nameHitRecordList = new ArrayList<NameHitRecord>();
		List<FscBankNcWatchListDim> exactMatchBankNameList = new ArrayList<FscBankNcWatchListDim>();
		List<FscBankNcWatchListDim> fuzzyMatchBankNameList = new ArrayList<FscBankNcWatchListDim>();
		List<FscBankNcWatchListDim> inclusiveMatchBankNameList = new ArrayList<FscBankNcWatchListDim>();
		List<FscBankNcWatchListDim> bicSwiftCodeList = new ArrayList<FscBankNcWatchListDim>();
		if(!StringUtils.isEmpty(englishName) || !StringUtils.isEmpty(nonEnglishName)){
			String inputCnName = StringUtils.standardizationInput(nonEnglishName, new String[0]);
			String inputEnName = StringUtils.standardizationInput(englishName, new String[0]);
			long getBnkExactStartTime = System.currentTimeMillis();
			exactMatchBankNameList = findBankByExactMatch(inputCnName, inputEnName);
			long getBnkExactEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getBnkExactTime:{}", uniqueKey, ncReferenceId, checkSeq,(getBnkExactEndTime - getBnkExactStartTime));
			
			long getBnkFuzzyStartTime = System.currentTimeMillis();
			fuzzyMatchBankNameList = findBankByFuzzyMatch(inputCnName, inputEnName);
			long getBnkFuzzyEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getBnkFuzzyTime:{}", uniqueKey, ncReferenceId, checkSeq,(getBnkFuzzyEndTime - getBnkFuzzyStartTime));
			
			long getBnkIclStartTime = System.currentTimeMillis();
			inclusiveMatchBankNameList = findBankByInclusiveMatchList(inputCnName, inputEnName);
			long getBnkIclEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getBnkIclTime:{}", uniqueKey, ncReferenceId, checkSeq,(getBnkIclEndTime - getBnkIclStartTime));
			
		}
		if(!StringUtils.isEmpty(bicSwiftCode)){
			long getBicExactStartTime = System.currentTimeMillis();
			bicSwiftCodeList = findByBicSwiftCode(bicSwiftCode);
			long getBicExactEndTime = System.currentTimeMillis();
			logger.debug("Thread unikey:{} ncRfId:{} checkSeq:{} getBicExactTime:{}", uniqueKey, ncReferenceId, checkSeq,(getBicExactEndTime - getBicExactStartTime));
		}
		Map<Long, NameHitRecord> nameHitRecordMap = new HashMap<Long, NameHitRecord>();
		setBankNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_EXACT, exactMatchBankNameList, uniqueKey, ncReferenceId, checkSeq, xWatchlistSetting);
		setBankNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_FUZZY, fuzzyMatchBankNameList, uniqueKey, ncReferenceId, checkSeq, xWatchlistSetting);
		setBankNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_INCLUSIVE, inclusiveMatchBankNameList, uniqueKey, ncReferenceId, checkSeq, xWatchlistSetting);
		setBankNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_EXACT, bicSwiftCodeList, uniqueKey, ncReferenceId, checkSeq, xWatchlistSetting);

		for (Map.Entry<Long, NameHitRecord> entry : nameHitRecordMap.entrySet()) {
			nameHitRecordList.add(entry.getValue());
		}
		return nameHitRecordList;
	}
	
	/**
	 * 1.將前端輸入EntityType轉換 
	 * @param entityType 
	 * @return List<String>
	 */
	private List<String> handelEntityType(String entityType){
		List<String> entityTypeList = new ArrayList<String>();
		if("02".equalsIgnoreCase(entityType)){
			entityTypeList.add("02");
			entityTypeList.add("09");
		}else if("03".equalsIgnoreCase(entityType)){
			entityTypeList.add("03");
			entityTypeList.add("04");
			entityTypeList.add("09");
		}else if("09".equalsIgnoreCase(entityType)){
			entityTypeList.add("02");
			entityTypeList.add("03");
			entityTypeList.add("04");
			entityTypeList.add("09");
		}else if(!"01".equalsIgnoreCase(entityType) && !"99".equalsIgnoreCase(entityType)){
			entityTypeList.add(entityType);
		}
		return entityTypeList;
	}
	
	/**
	 * 1.撈取X_SCREEN_PROCESS_SETTING(Screen_process對應名單類型) 2.轉換成Map<String, XScreenProcessSetting>型態
	 * @param screenProcess
	 * @return Map<String, XScreenProcessSetting>
	 */
	private Map<String, XScreenProcessSetting> getXScreenProcessSettingMap(String screenProcess){
		Map<String, XScreenProcessSetting> XScreenProcessSettingMap =  new HashMap<String, XScreenProcessSetting>();
		List<XScreenProcessSetting> xScreenProcessSettingList = new ArrayList<XScreenProcessSetting>();
		if(SwiftMtConst.SCREEN_PROCESS_BATCH_TRANCTION_SCREENING.equals(screenProcess)){ //Batch Transaction(ScreenProcess=7) 需要排除 RealTime Transaction (ScreenProcess=3) 掃描有重疊的名單
			
			//撈取 Batch Transaction(ScreenProcess=7) 
			List<XScreenProcessSetting> sourcexScreenProcessSettingList = iXScreenProcessSettingDao.findByChangeCurrentIndAndDeleteIndAndIdScreenProcessTypeCd(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, screenProcess);
			
			//Batch Transaction(ScreenProcess=3) & 整理需要排除的 watchListSubTypeCd
			List<XScreenProcessSetting> excludeXScreenProcessSettingList = iXScreenProcessSettingDao.findByChangeCurrentIndAndDeleteIndAndIdScreenProcessTypeCd(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.SCREEN_PROCESS_Transaction_Screening);
			Set<String> excludeSet = new HashSet<String>();
			for(XScreenProcessSetting be : excludeXScreenProcessSettingList){
				excludeSet.add(be.getId().getWatchListSubTypeCd());
			}
  			
			for(XScreenProcessSetting be : sourcexScreenProcessSettingList){
				if(!excludeSet.contains(be.getId().getWatchListSubTypeCd())){
					xScreenProcessSettingList.add(be);
				}
			}
	
		}else if(SwiftMtConst.SCREEN_PROCESS_BATCH_TRADE_FINANCE_SCREENING.equals(screenProcess)){ //Batch Trade Finance(ScreenProcess=8) 需要排除 RealTime Trade Finance(ScreenProcess=4) 掃描有重疊的名單
			//撈取 Batch Transaction(ScreenProcess=7) 
			List<XScreenProcessSetting> sourcexScreenProcessSettingList = iXScreenProcessSettingDao.findByChangeCurrentIndAndDeleteIndAndIdScreenProcessTypeCd(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, screenProcess);
			
			//Batch Transaction(ScreenProcess=3) & 整理需要排除的 watchListSubTypeCd
			List<XScreenProcessSetting> excludeXScreenProcessSettingList = iXScreenProcessSettingDao.findByChangeCurrentIndAndDeleteIndAndIdScreenProcessTypeCd(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.SCREEN_PROCESS_Trade_Finance_Screening);
			Set<String> excludeSet = new HashSet<String>();
			for(XScreenProcessSetting be : excludeXScreenProcessSettingList){
				excludeSet.add(be.getId().getWatchListSubTypeCd());
			}
  			
			for(XScreenProcessSetting be : sourcexScreenProcessSettingList){
				if(!excludeSet.contains(be.getId().getWatchListSubTypeCd())){
					xScreenProcessSettingList.add(be);
				}
			}
		}else{
			xScreenProcessSettingList = iXScreenProcessSettingDao.findByChangeCurrentIndAndDeleteIndAndIdScreenProcessTypeCd(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, screenProcess);
		}

		for(XScreenProcessSetting xScreenProcessSetting : xScreenProcessSettingList){
			String watchlistSubTypeCd = xScreenProcessSetting.getId().getWatchListSubTypeCd();
			if(!XScreenProcessSettingMap.containsKey(watchlistSubTypeCd)){
				XScreenProcessSettingMap.put(watchlistSubTypeCd, xScreenProcessSetting);
			}
		}
		return XScreenProcessSettingMap;
	}
	

	/**
	 * 處理名單類型優先順序，SL > CLH > TF > CL1 > CL2
	 * @param oldRule
	 * @param newRule
	 * @return
	 */
	private String judgeRule(String oldRule, String newRule){
		String rule = oldRule;
		if(oldRule != null && oldRule.length() > 0 && newRule != null && newRule.length() > 0 ){
			int oldValue = Integer.parseInt(AmlConfiguration.getString(String.format("com.sas.routeRule.%s", oldRule)));
			int newValue = Integer.parseInt(AmlConfiguration.getString(String.format("com.sas.routeRule.%s", newRule)));
			if(oldValue > newValue){
				rule = newRule;
			}
		}
		return rule;
	}
	
	/**
	 * 名單給分處理(國家)
	 * @param nameHitRecordMap
	 * @param checkType
	 * @param dimList
	 * @param uniqueKey
	 * @param ncReferenceId
	 * @param checkSeq
	 * @param xWatchlistSetting
	 */
	private void setLocationNCDefaultScore(
			Map<Long, NameHitRecord> nameHitRecordMap, String checkType, List<FscLocationNcWatchListDim> dimList,
			String uniqueKey, int ncReferenceId, String checkSeq, XWatchlistSetting xWatchlistSetting){
		int seq = nameHitRecordMap.size();
		if(dimList != null && dimList.size() > 0){
			for(FscLocationNcWatchListDim dim : dimList){
				NameHitRecord nameHitRecord = null;
				if(nameHitRecordMap.containsKey(dim.getLocationWatchListKey())){
					nameHitRecord = nameHitRecordMap.get(dim.getLocationWatchListKey());	
				}else{
					nameHitRecord = new NameHitRecord(dim, uniqueKey, ncReferenceId, checkSeq, seq++);
					nameHitRecordMap.put(dim.getLocationWatchListKey(), nameHitRecord);
				}
				
				if(SwiftMtConst.NAME_CHECK_EXACT.equalsIgnoreCase(checkType)){
					nameHitRecord.setExactMatchScore(xWatchlistSetting.getExactMatchingScore().intValue());
				}else if(SwiftMtConst.NAME_CHECK_FUZZY.equalsIgnoreCase(checkType)){
					nameHitRecord.setFuzzyMatchScore(xWatchlistSetting.getFuzzyMatchingScore().intValue());
				}else if(SwiftMtConst.NAME_CHECK_INCLUSIVE.equalsIgnoreCase(checkType)){
					nameHitRecord.setInclusiveMatchScore(xWatchlistSetting.getInclusiveMatchingScore().intValue());
				}
			}
		}
	}
	/**
	 * 名單給分處理(銀行)
	 * @param nameHitRecordMap
	 * @param checkType
	 * @param dimList
	 * @param uniqueKey
	 * @param ncReferenceId
	 * @param checkSeq
	 * @param xWatchlistSetting
	 */
	private void setBankNCDefaultScore(
			Map<Long, NameHitRecord> nameHitRecordMap, String checkType, List<FscBankNcWatchListDim> dimList,
			String uniqueKey, int ncReferenceId, String checkSeq, XWatchlistSetting xWatchlistSetting){
		int seq = nameHitRecordMap.size();
		if(dimList != null && dimList.size() > 0){
			for(FscBankNcWatchListDim dim : dimList){
				NameHitRecord nameHitRecord = null;
				if(nameHitRecordMap.containsKey(dim.getBankWatchListKey())){
					nameHitRecord = nameHitRecordMap.get(dim.getBankWatchListKey());	
				}else{
					nameHitRecord = new NameHitRecord(dim, uniqueKey, ncReferenceId, checkSeq, seq++);
					nameHitRecordMap.put(dim.getBankWatchListKey(), nameHitRecord);
				}
				
				if(SwiftMtConst.NAME_CHECK_EXACT.equalsIgnoreCase(checkType)){
					nameHitRecord.setExactMatchScore(xWatchlistSetting.getExactMatchingScore().intValue());
				}else if(SwiftMtConst.NAME_CHECK_FUZZY.equalsIgnoreCase(checkType)){
					nameHitRecord.setFuzzyMatchScore(xWatchlistSetting.getFuzzyMatchingScore().intValue());
				}else if(SwiftMtConst.NAME_CHECK_INCLUSIVE.equalsIgnoreCase(checkType)){
					nameHitRecord.setInclusiveMatchScore(xWatchlistSetting.getInclusiveMatchingScore().intValue());
				}
			}
		}
	}
	
	/**
	 * 名單給分處理
	 * @param nameHitRecordMap
	 * @param checkType
	 * @param dimList
	 * @param uniqueKey
	 * @param ncReferenceId
	 * @param checkSeq
	 * @param entityType
	 * @param gender
	 * @param xWatchlistSetting
	 */
	private void setNCDefaultScore(
			Map<Long, NameHitRecord> nameHitRecordMap, String checkType, List<FscEntityNcWatchListDim> dimList,
			String uniqueKey, int ncReferenceId, String checkSeq, String entityType, String gender, XWatchlistSetting xWatchlistSetting){
		int seq = nameHitRecordMap.size();
		if(dimList != null && dimList.size() > 0){
			for(FscEntityNcWatchListDim dim : dimList){
				NameHitRecord nameHitRecord = null;
				if(nameHitRecordMap.containsKey(dim.getEntityWatchListKey())){
					nameHitRecord = nameHitRecordMap.get(dim.getEntityWatchListKey());
				}else{
//					if("02".equals(entityType)){
//						if(gender != null && gender.equalsIgnoreCase(dim.getGender())){
//							nameHitRecord = new NameHitRecord(dim, uniqueKey, ncReferenceId, checkSeq, seq++);
//							nameHitRecordMap.put(dim.getEntityWatchListKey(), nameHitRecord);
//						}
//					}else{
//						nameHitRecord = new NameHitRecord(dim, uniqueKey, ncReferenceId, checkSeq, seq++);
//						nameHitRecordMap.put(dim.getEntityWatchListKey(), nameHitRecord);
//					}
					
					nameHitRecord = new NameHitRecord(dim, uniqueKey, ncReferenceId, checkSeq, seq++);
					nameHitRecordMap.put(dim.getEntityWatchListKey(), nameHitRecord);
				}
				
				if(SwiftMtConst.NAME_CHECK_EXACT.equalsIgnoreCase(checkType)){
					nameHitRecord.setExactMatchScore(xWatchlistSetting.getExactMatchingScore().intValue());
				}else if(SwiftMtConst.NAME_CHECK_FUZZY.equalsIgnoreCase(checkType)){
					nameHitRecord.setFuzzyMatchScore(xWatchlistSetting.getFuzzyMatchingScore().intValue());
				}else if(SwiftMtConst.NAME_CHECK_INCLUSIVE.equalsIgnoreCase(checkType)){
					nameHitRecord.setInclusiveMatchScore(xWatchlistSetting.getInclusiveMatchingScore().intValue());
				}
			}
		}
	}
	
    /**
     * 名單給分處理
     * @param nameHitRecordMap
     * @param checkType
     * @param dimList
     * @param uniqueKey
     * @param ncReferenceId
     * @param checkSeq
     */
	private void setNCDefaultScore(
			Map<Long, NameHitRecord> nameHitRecordMap, String checkType, List<FscEntityNcWatchListDim> dimList,
			String uniqueKey, int ncReferenceId, String checkSeq){
		int seq = nameHitRecordMap.size();
		if(dimList != null && dimList.size() > 0){
			for(FscEntityNcWatchListDim dim : dimList){
				NameHitRecord nameHitRecord = null;
				if(nameHitRecordMap.containsKey(dim.getEntityWatchListKey())){
					nameHitRecord = nameHitRecordMap.get(dim.getEntityWatchListKey());
				}else{
					nameHitRecord = new NameHitRecord(dim, uniqueKey, ncReferenceId, checkSeq, seq++);
					nameHitRecordMap.put(dim.getEntityWatchListKey(), nameHitRecord);
				}
				
				if(SwiftMtConst.NAME_CHECK_EXACT.equalsIgnoreCase(checkType)){
					nameHitRecord.setExactMatchScore(SwiftMtConst.EXACT_MATCH_SCORE_DEFAULT);
				}else if(SwiftMtConst.NAME_CHECK_FUZZY.equalsIgnoreCase(checkType)){
					nameHitRecord.setFuzzyMatchScore(SwiftMtConst.FUZZY_MATCH_SCORE_DEFAULT);
				}else if(SwiftMtConst.NAME_CHECK_INCLUSIVE.equalsIgnoreCase(checkType)){
					nameHitRecord.setInclusiveMatchScore(SwiftMtConst.INCLUSIVE_MATCH_SOCRE_DEFALUT);
				}
			}
		}
	}
	
	/**
	 * 1.過濾非Screening Process+entityTypeList 查詢的名單  2.並找出名單類型(WatchListTypeCd) 與名單子類型(WatchListSubTypeCd)
	 * @param nameHitRecordList
	 * @param xScreenProcessSettingMap
	 * @param entityTypeList
	 * @return List<NameHitRecord>
	 */
	private List<NameHitRecord> setWatchListType(List<NameHitRecord> nameHitRecordList, Map<String, XScreenProcessSetting> xScreenProcessSettingMap, List<String> entityTypeList,Map<String, String> rankOfWatchListMap){
		List<NameHitRecord> result = new ArrayList<NameHitRecord>();
		List<String> entityWatchListNumberList = new ArrayList<String>();
		for(NameHitRecord nameHitRecord : nameHitRecordList){
			if(!entityWatchListNumberList.contains(nameHitRecord.getEntityWatchListNumber())){
				entityWatchListNumberList.add(nameHitRecord.getEntityWatchListNumber());
			}
		}
		
		Map<String, List<XFscEntityWlXListSetting>> setMap = new HashMap<String, List<XFscEntityWlXListSetting>>();
		if(entityWatchListNumberList != null && entityWatchListNumberList.size() > 0){
			int limit = 2000;
			if(entityWatchListNumberList.size() > limit){
				int i = 0;
				List<String> tmpList = new ArrayList<String>();
				for(String number : entityWatchListNumberList){
					i++;					
					tmpList.add(number);
					
					if(i % limit == 0 || i == entityWatchListNumberList.size()){
						List<XFscEntityWlXListSetting> setList = iFscEntityWlXListSettingDao.findByIdChangeCurrentIndAndEntityWatchListNumberIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, tmpList);
						tmpList = new ArrayList<String>();
						for(XFscEntityWlXListSetting set : setList){
							String entityWatchListNumber=set.getEntityWatchListNumber();
							if(setMap.get(entityWatchListNumber)==null){
								List<XFscEntityWlXListSetting> resultList = new ArrayList<XFscEntityWlXListSetting>();
								resultList.add(set);
								setMap.put(set.getEntityWatchListNumber(), resultList);
							}else{
								List<XFscEntityWlXListSetting> oldResultList = setMap.get(entityWatchListNumber);
								oldResultList.add(set);
								setMap.put(set.getEntityWatchListNumber(), oldResultList);
							}
						}
					}
				}
			}else{
				List<XFscEntityWlXListSetting> setList = iFscEntityWlXListSettingDao.findByIdChangeCurrentIndAndEntityWatchListNumberIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, entityWatchListNumberList);
				for(XFscEntityWlXListSetting set : setList){
					String entityWatchListNumber=set.getEntityWatchListNumber();
					if(setMap.get(entityWatchListNumber)==null){
						List<XFscEntityWlXListSetting> resultList = new ArrayList<XFscEntityWlXListSetting>();
						resultList.add(set);
						setMap.put(set.getEntityWatchListNumber(), resultList);
					}else{
						List<XFscEntityWlXListSetting> oldResultList = setMap.get(entityWatchListNumber);
						oldResultList.add(set);
						setMap.put(set.getEntityWatchListNumber(), oldResultList);
					}
				}
			}
		}
		
		if(setMap != null){
			for(NameHitRecord nameHitRecord : nameHitRecordList){
				List<XFscEntityWlXListSetting> set = setMap.get(nameHitRecord.getEntityWatchListNumber());
				if(set != null){
					String newWatchListSubTypeCd="";
					String newWatchListTypeCd="";
					for(XFscEntityWlXListSetting xFscEntityWlXListSetting:set){
						if(xScreenProcessSettingMap.get(xFscEntityWlXListSetting.getWatchListSubTypeCd()) != null){
							newWatchListSubTypeCd = NameCheckUtil.sortRule(newWatchListSubTypeCd, xFscEntityWlXListSetting.getWatchListSubTypeCd(),rankOfWatchListMap,SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
							newWatchListTypeCd = NameCheckUtil.sortRule(newWatchListTypeCd,xFscEntityWlXListSetting.getWatchListTypeCd(),rankOfWatchListMap,SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
						}
						
					}
					
					if(entityTypeList != null && entityTypeList.size() > 0){
						if(entityTypeList.contains(set.get(0).getEntityTypeCd())){
							nameHitRecord.setTypeDesc(set.get(0).getEntityTypeCd());
							nameHitRecord.setWatchListTypeCd(newWatchListTypeCd);
							nameHitRecord.setWatchListSubTypeCd(newWatchListSubTypeCd);
							result.add(nameHitRecord);
						}
					}else{
						nameHitRecord.setTypeDesc(set.get(0).getEntityTypeCd());
						nameHitRecord.setWatchListTypeCd(newWatchListTypeCd);
						nameHitRecord.setWatchListSubTypeCd(newWatchListSubTypeCd);
						result.add(nameHitRecord);
					}
				}
			}
		}
		return result;
	}

	/**
	 * 名單掃描方式處理
	 * @param uniqueKey
	 * @param ncReferenceId
	 * @param checkSeq
	 * @param exactMatchList
	 * @param fuzzyMatchList
	 * @param inclusiveMatchList
	 * @param cccCodeList
	 * @param isNumberList
	 * @param freeFormatTextList
	 * @param bicSwiftCodeList
	 * @param countryList
	 * @param entityType
	 * @param gender
	 * @param xWatchlistSetting
	 * @return List<NameHitRecord>
	 */
	private List<NameHitRecord> getNameHitRecordList(String uniqueKey, int ncReferenceId, String checkSeq,
			List<FscEntityNcWatchListDim> exactMatchList, List<FscEntityNcWatchListDim> fuzzyMatchList, List<FscEntityNcWatchListDim> inclusiveMatchList, 
			List<FscEntityNcWatchListDim> cccCodeList, List<FscEntityNcWatchListDim> isNumberList, List<FscEntityNcWatchListDim> freeFormatTextList, 
			List<FscBankNcWatchListDim> bicSwiftCodeList, List<FscLocationNcWatchListDim> countryList, String entityType, String gender, XWatchlistSetting xWatchlistSetting){
		List<NameHitRecord> nameHitRecordList = new ArrayList<NameHitRecord>();
		Map<Long, NameHitRecord> nameHitRecordMap = new HashMap<Long, NameHitRecord>();
		setNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_EXACT, exactMatchList, uniqueKey, ncReferenceId, checkSeq, entityType, gender, xWatchlistSetting);
		setNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_FUZZY, fuzzyMatchList, uniqueKey, ncReferenceId, checkSeq, entityType, gender, xWatchlistSetting);
		setNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_INCLUSIVE, inclusiveMatchList, uniqueKey, ncReferenceId, checkSeq, entityType, gender, xWatchlistSetting);
		setNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_EXACT, cccCodeList, uniqueKey, ncReferenceId, checkSeq, entityType, gender, xWatchlistSetting);
		setNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_EXACT, isNumberList, uniqueKey, ncReferenceId, checkSeq, entityType, gender, xWatchlistSetting);
		setNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_FUZZY, freeFormatTextList, uniqueKey, ncReferenceId, checkSeq, entityType, gender, xWatchlistSetting);
		//bicSwiftCodeList
		//countryList
		for (Map.Entry<Long, NameHitRecord> entry : nameHitRecordMap.entrySet()) {
			nameHitRecordList.add(entry.getValue());
		}
		return nameHitRecordList;
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
	
	/**
	 * Inclusive 方式掃描(名單國家檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscEntityNcWatchListDim>
	 */
	private List<FscLocationNcWatchListDim> findLocationByInclusiveMatchList(String inputCnName, String inputEnName){
		List<FscLocationNcWatchListDim> resultList = new ArrayList<FscLocationNcWatchListDim>();
		List<String> inclusiveList = new ArrayList<String>();
		if(!StringUtils.isEmpty(inputCnName)){
			List<List<String>> cnList = WatchListUtil.permutation(inputCnName, false);
			if(cnList != null && cnList.size() > 0){
				handelInclusiveString(inclusiveList, cnList);
			}
		}
		if(!StringUtils.isEmpty(inputEnName)){
			List<List<String>> enList = WatchListUtil.permutation(inputEnName, true);
			if(enList != null && enList.size() > 0){
				handelInclusiveString(inclusiveList, enList);
			}
		}
		if(inclusiveList.size() > 0){
			String inclusiveSql = iFscLocationNcWatchListDimDao.getInculsiveQuerySql(inclusiveList);
			resultList.addAll(iFscLocationNcWatchListDimDao.nativeSql(inclusiveSql));
		}
		return resultList;
	}
	
	/**
	 * Inclusive 方式掃描(名單銀行檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscEntityNcWatchListDim>
	 */
	private List<FscBankNcWatchListDim> findBankByInclusiveMatchList(String inputCnName, String inputEnName){
		List<FscBankNcWatchListDim> resultList = new ArrayList<FscBankNcWatchListDim>();
		List<String> inclusiveList = new ArrayList<String>();
		if(!StringUtils.isEmpty(inputCnName)){
			List<List<String>> cnList = WatchListUtil.permutation(inputCnName, false);
			if(cnList != null && cnList.size() > 0){
				handelInclusiveString(inclusiveList, cnList);
			}
		}
		if(!StringUtils.isEmpty(inputEnName)){
			List<List<String>> enList = WatchListUtil.permutation(inputEnName, true);
			if(enList != null && enList.size() > 0){
				handelInclusiveString(inclusiveList, enList);
			}
		}
		if(inclusiveList.size() > 0){
			String inclusiveSql = iFscBankNcWatchListDimDao.getInculsiveQuerySql(inclusiveList);
			resultList.addAll(iFscBankNcWatchListDimDao.nativeSql(inclusiveSql));
		}
		return resultList;
	}
	
	/**
	 * Inclusive 方式掃描(名單主檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscEntityNcWatchListDim>
	 */
	private List<FscEntityNcWatchListDim> findByInclusiveMatchList(List<String> inputCnName, List<String> inputEnName, String nightBatchOption){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();
		List<String> inclusiveList = new ArrayList<String>();
		StringUtils.getInclusiveString(inclusiveList, inputCnName, false);
		StringUtils.getInclusiveString(inclusiveList, inputEnName, true);
		if(inclusiveList.size() > 0){

			if("FullPartyChangeScan".equals(nightBatchOption)){ //全客戶掃異動名單
				String inclusiveSql = iFscEntityWatchListDimChngDao.getInculsiveQuerySql(inclusiveList);
				logger.debug("inclusiveSql : " + inclusiveSql);
				resultList.addAll(changeTypeFscEntityWatchListDim(iFscEntityWatchListDimChngDao.nativeSql(inclusiveSql)));
			}else{ // 1.全客戶掃全名單 2.異動客戶掃全名單
				String inclusiveSql = iFscEntityNcWatchListDimDao.getInculsiveQuerySql(inclusiveList);
				logger.debug("inclusiveSql : " + inclusiveSql);
				resultList.addAll(iFscEntityNcWatchListDimDao.nativeSql(inclusiveSql));
			}
			
		}
		return resultList;
	}
	
	/**
	 * FreeFormat掃描 (名單主檔)
	 * @param freeFormatText
	 * @return List<FscEntityNcWatchListDim>
	 */
	private List<FscEntityNcWatchListDim> findByFreeFormatText(String freeFormatText){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();
		List<String> freeFormatTextList = NameCheckUtil.getShiftInMaxArrangementInLimitList(freeFormatText);
		if(freeFormatTextList.size() > 0){
			List<String> fuzzyFreeFormatTextList = getFuzzyFreeFormatTextMatchCode(freeFormatTextList);
			if(fuzzyFreeFormatTextList.size() > 0){
				resultList.addAll(iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeEntityNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, fuzzyFreeFormatTextList));
			}
		}
		return resultList;
	}
	
	/**
	 * 將Free Format字串，透過DataFlux方法模糊化名稱
	 * @param freeFormatTextList
	 * @return List<String> 
	 */
	private List<String> getFuzzyFreeFormatTextMatchCode(List<String> freeFormatTextList){
		List<String> resultList =  new ArrayList<String>();
		Map<String, String> fuzzyMatchMap = new HashMap<String, String>();
		List<RowIn> rowInList =  new ArrayList<RowIn>();
		int i = 0;
		for(String key : freeFormatTextList){
			RowIn rowIn = new RowIn();
			rowIn.setChName("");
			rowIn.setEnName(key);
			rowInList.add(rowIn);
			
			i++;
			//dataflux max rowin number : 50000 for each request
			if(i % 50000 == 0 || i == freeFormatTextList.size()){
				List<Map<String, String>> datafluxMatchCodeList = dataFluxMatchCode.datafluxMatchCodeList(rowInList);
				
				for(Map<String, String> matchCodeMap : datafluxMatchCodeList){
					fuzzyMatchMap.putAll(matchCodeMap);
				}
				rowInList =  new ArrayList<RowIn>();
			}
		}
		for (Map.Entry<String, String> entry : fuzzyMatchMap.entrySet()) {
			resultList.add(entry.getValue());
		}
		
		return resultList;
	}
	
	/**
	 * Input 欄位拆解成Free Format格式
	 * @param freeFormatText
	 * @return  List<String>
	 */
	private List<String> handelFreeFormatText(String freeFormatText){
		List<String> resultList =  new ArrayList<String>();
		int limitIndex = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_LIMITINDEX));
		int maxIndex = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_MAXINDEX));
		int fieldMaxLength = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_FIELDMAXLENGTH));
		
		if(freeFormatText.contains(" ")){
			for(String str : WatchListUtil.getShiftInMaxArrangementInLimitList(freeFormatText.split(" "), maxIndex, limitIndex)){
				if(str != null && str.length() > 0 && str.length() < fieldMaxLength){
					resultList.add(str);
				}
			}
		}else{
			if(freeFormatText.length() < fieldMaxLength){
				resultList.add(freeFormatText);
			}
		}
		
		return resultList;
	}
	
    /**
     * FuzzyMatch(名單國家檔)
     * @param matchCodeCountryName
     * @return List<FscLocationNcWatchListDim>
     */
	private List<FscLocationNcWatchListDim> findByFuzzyCountryName(String matchCodeCountryName){
		List<FscLocationNcWatchListDim> resultList = new ArrayList<FscLocationNcWatchListDim>();
		Map<String, String> matchCodeMap = dataFluxMatchCode.datafluxMatchCode(null, matchCodeCountryName);
		resultList.addAll(iFscLocationNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryName(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, matchCodeMap.get(matchCodeCountryName)));
		return resultList;
	}
	
	/**
	 * 撈取Bic Code(名單銀行檔)
	 * @param bicSwiftCode
	 * @return List<FscBankNcWatchListDim>
	 */
	private List<FscBankNcWatchListDim> findByBicSwiftCode(String bicSwiftCode){
		List<FscBankNcWatchListDim> resultList = new ArrayList<FscBankNcWatchListDim>();
		resultList.addAll(iFscBankNcWatchListDimDao.findByChangeCurrentIndAndBccCode(SwiftMtConst.CHANGE_CURRENT_IND_Y, bicSwiftCode));
		return resultList;
	}
	
	/**
	 * 撈取CCC Code(名單主檔)
	 * @param cccCode
	 * @return List<FscEntityNcWatchListDim>
	 */
	private List<FscEntityNcWatchListDim> findByCccCode(String cccCode, String nightBatchOption){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();		
		
		if("FullPartyChangeScan".equals(nightBatchOption)){ //全客戶掃異動名單
			resultList.addAll(changeTypeFscEntityWatchListDim(iFscEntityWatchListDimChngDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityName(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, cccCode)));
		}else{ // 1.全客戶掃全名單 2.異動客戶掃全名單
			resultList.addAll(iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityName(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, cccCode));
		}
		
		return resultList;
	}
	
	/**
	 * 撈取 IdNumber(名單主檔)
	 * @param idNumber
	 * @return List<FscEntityNcWatchListDim>
	 */
	private List<FscEntityNcWatchListDim> findByIdNumber(String idNumber, String nightBatchOption){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();		
		
		if("FullPartyChangeScan".equals(nightBatchOption)){ //全客戶掃異動名單
			resultList.addAll(changeTypeFscEntityWatchListDim(iFscEntityWatchListDimChngDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndIdentificationId(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, idNumber)));
		}else{ // 1.全客戶掃全名單 2.異動客戶掃全名單
			resultList.addAll(iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndIdentificationId(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, idNumber));
		}
		return resultList;
	}
	
	/**
	 * FuzzyMatch(名單國家檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscLocationNcWatchListDim> 
	 */
	private List<FscLocationNcWatchListDim> findLocationByFuzzyMatch(String inputCnName, String inputEnName){
		List<FscLocationNcWatchListDim> resultList = new ArrayList<FscLocationNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		Map<String, String> matchCodeMap = dataFluxMatchCode.datafluxMatchCode(inputCnName, inputEnName);
		if(!StringUtils.isEmpty(inputCnName) && matchCodeMap.get(inputCnName) != null){
			entityNameList.add(matchCodeMap.get(inputCnName));
		}
		if(!StringUtils.isEmpty(inputEnName) && matchCodeMap.get(inputEnName) != null){
			entityNameList.add(matchCodeMap.get(inputEnName));
		}
		
		resultList.addAll(iFscLocationNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
		return resultList;
	}
	
	/**
	 * FuzzyMatch(名單銀行檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscBankNcWatchListDim>
	 */
	private List<FscBankNcWatchListDim> findBankByFuzzyMatch(String inputCnName, String inputEnName){
		List<FscBankNcWatchListDim> resultList = new ArrayList<FscBankNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		Map<String, String> matchCodeMap = dataFluxMatchCode.datafluxMatchCode(inputCnName, inputEnName);
		if(!StringUtils.isEmpty(inputCnName) && matchCodeMap.get(inputCnName) != null){
			entityNameList.add(matchCodeMap.get(inputCnName));
		}
		if(!StringUtils.isEmpty(inputEnName) && matchCodeMap.get(inputEnName) != null){
			entityNameList.add(matchCodeMap.get(inputEnName));
		}
		
		resultList.addAll(iFscBankNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeBankNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
		return resultList;
	}
	
	/**
	 * FuzzyMatch (名單主檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @param matchCodeEntityName
	 * @return List<FscEntityNcWatchListDim> 
	 */
	private List<FscEntityNcWatchListDim> findByFuzzyMatch(List<String> inputCnName, List<String> inputEnName, String matchCodeEntityName, String nightBatchOption){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		
		long getMatchCodeStartTime = System.currentTimeMillis();
		//Map<String, String> matchCodeMap = dataFluxMatchCode.datafluxMatchCode(inputCnName, inputEnName);
		long getMatchCodeEndTime = System.currentTimeMillis();
		logger.debug("Thread inputCnNm:{} inputEnNm:{} getMatchCode:{}", inputCnName, inputEnName,(getMatchCodeEndTime - getMatchCodeStartTime));
		if(matchCodeEntityName == null){
			matchCodeEntityName="";
		}else{
			if(matchCodeEntityName.replace("$", "").length()==0){
				matchCodeEntityName = "";
			}
		}

		
		if(!inputCnName.isEmpty() && !StringUtils.isEmpty(matchCodeEntityName)){
			entityNameList.add(matchCodeEntityName);
		}
		if(!inputEnName.isEmpty() && !StringUtils.isEmpty(matchCodeEntityName)){
			entityNameList.add(matchCodeEntityName);
		}
		if(entityNameList.size() != 0){
			if("FullPartyChangeScan".equals(nightBatchOption)){ //全客戶掃異動名單
				resultList.addAll(changeTypeFscEntityWatchListDim(iFscEntityWatchListDimChngDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeEntityNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList)));
			}else{ // 1.全客戶掃全名單 2.異動客戶掃全名單
				resultList.addAll(iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeEntityNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
			}
		}
		return resultList;
	}
	
	/**
	 * Exact Match(名單國家檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscLocationNcWatchListDim>
	 */
	private List<FscLocationNcWatchListDim> findLocationByExactMatch(String inputCnName, String inputEnName){
		List<FscLocationNcWatchListDim> resultList = new ArrayList<FscLocationNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		if(!StringUtils.isEmpty(inputCnName)){
			entityNameList.add(inputCnName);
		}
		if(!StringUtils.isEmpty(inputEnName)){
			entityNameList.add(inputEnName);
		}
		resultList.addAll(iFscLocationNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
		return resultList;
	}
	/**
	 * Exact Match(名單國家代碼檔)
	 * @param countryCode
	 * @return
	 */
	private List<FscLocationNcWatchListDim> findCountryCodeByExactMatch(String countryCode){
		List<FscLocationNcWatchListDim> resultList = new ArrayList<FscLocationNcWatchListDim>();
		resultList.addAll(iFscLocationNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryCodeOrCountryCode3(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, countryCode));
		return resultList;
	}
	
	/**
	 * Exact Match(名單銀行檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscBankNcWatchListDim>
	 */
	private List<FscBankNcWatchListDim> findBankByExactMatch(String inputCnName, String inputEnName){
		List<FscBankNcWatchListDim> resultList = new ArrayList<FscBankNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		if(!StringUtils.isEmpty(inputCnName)){
			entityNameList.add(inputCnName);
		}
		if(!StringUtils.isEmpty(inputEnName)){
			entityNameList.add(inputEnName);
		}
		resultList.addAll(iFscBankNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBankNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
		return resultList;
	}
	
	/**
	 * Exact Match (名單主檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscEntityWatchListDim>
	 */
	private List<FscEntityNcWatchListDim> findByExactMatch(List<String> inputCnName, List<String> inputEnName, String nightBatchOption){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		if(!inputCnName.isEmpty()){
			entityNameList.addAll(inputCnName);
		}
		if(!inputEnName.isEmpty()){
			entityNameList.addAll(inputEnName);
		}
		
		if("FullPartyChangeScan".equals(nightBatchOption)){ //全客戶掃異動名單
			resultList.addAll(changeTypeFscEntityWatchListDim(iFscEntityWatchListDimChngDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList)));
		}else{ // 1.全客戶掃全名單 2.異動客戶掃全名單
			resultList.addAll(iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
		}
		
		return resultList;
	}

	
	
	/**
	 * Input資訊內容檢查判斷，有區分名單掃描/交易掃描型態
	 * @param screenProcess
	 * @param englishName
	 * @param nonEnglishName
	 * @param cccCode
	 * @param idNumber
	 * @param bicSwiftCode
	 * @param freeFormatText
	 * @param country
	 * @return boolean
	 */
	private boolean chkScanFields(
			String screenProcess, String englishName, String nonEnglishName, 
			String cccCode, String idNumber, String bicSwiftCode, 
			String freeFormatText, String country){
		boolean result = false;
		String nameListScreening = AmlConfiguration.getString(SwiftMtConst.COM_SAS_NAMELISTSCREENING_SCREENPROCESS);
		String transcationScreening = AmlConfiguration.getString(SwiftMtConst.COM_SAS_TRANSCATIONSCREENING_SCREENPROCESS);

		if(nameListScreening.indexOf(screenProcess) > -1){
			// 名單掃描
			if(StringUtils.isEmpty(englishName) && StringUtils.isEmpty(nonEnglishName) && StringUtils.isEmpty(cccCode) && StringUtils.isEmpty(idNumber) && StringUtils.isEmpty(bicSwiftCode) && StringUtils.isEmpty(freeFormatText)){
				result = true;
			}
		}else if(transcationScreening.indexOf(screenProcess) > -1){
			// 交易掃描
			if(StringUtils.isEmpty(englishName) && StringUtils.isEmpty(nonEnglishName) && StringUtils.isEmpty(cccCode) && StringUtils.isEmpty(idNumber) && StringUtils.isEmpty(bicSwiftCode) && StringUtils.isEmpty(freeFormatText) && StringUtils.isEmpty(country)){
				result = true;
			}
		}
		return result;
		
	}
		
	/**
	 * 組合成Real Time Output資訊(NameCheckRecordDetail 名單查訊紀錄主檔)
	 * @param uniqueKey
	 * @param ncReferenceId
	 * @param interfaceName
	 * @param callingSystem
	 * @param screenProcess
	 * @param callingUser
	 * @param businessUnitID
	 * @param branchNumber
	 * @param genAlertFlag
	 * @param transactionDate
	 * @param referenceNumber
	 * @param ncResult
	 * @param routeRule
	 * @param hitSeq
	 * @param partyNumber
	 * @return NameCheckRecordMain
	 */
	private NameCheckRecordMain setNameCheckRecordMain(
			String uniqueKey, int ncReferenceId, String interfaceName, 
			String callingSystem, String screenProcess, String callingUser, 
			String businessUnitID, String branchNumber, String genAlertFlag, 
			Date transactionDate, String referenceNumber, String ncResult, 
			String routeRule, String hitSeq, String partyNumber){
		NameCheckRecordMain nameCheckRecordMain = new NameCheckRecordMain();
		NameCheckRecordMainPK nameCheckRecordMainPK = new NameCheckRecordMainPK();
		nameCheckRecordMain.setId(nameCheckRecordMainPK);
		nameCheckRecordMainPK.setUniqueKey(uniqueKey);
		nameCheckRecordMainPK.setNcReferenceId(ncReferenceId);
		nameCheckRecordMain.setInterfaceName(interfaceName);
		nameCheckRecordMain.setCallingSystem(callingSystem);
		nameCheckRecordMain.setScreenProcess(screenProcess);
		nameCheckRecordMain.setCallingUser(callingUser);
		nameCheckRecordMain.setBusinessUnitId(businessUnitID);
		nameCheckRecordMain.setBranchNumber(branchNumber);
		nameCheckRecordMain.setGenAlertFlag(genAlertFlag);
		nameCheckRecordMain.setTransactionDate(transactionDate);
		nameCheckRecordMain.setReferenceNumber(referenceNumber);
		nameCheckRecordMain.setNcResult(ncResult);
		nameCheckRecordMain.setRouteRule(routeRule);
		nameCheckRecordMain.setHitSeq(hitSeq);
		nameCheckRecordMain.setPartyNumber(partyNumber);
		
		return nameCheckRecordMain;
	}
	
	/**
	 * 組合成Real Time Output資訊(NameCheckRecordDetail 名單查訊紀錄明細檔)
	 * @param uniqueKey
	 * @param ncReferenceId
	 * @param checkSeq
	 * @param entityType
	 * @param entityRelationship
	 * @param entityRelationshipDesc
	 * @param englishName
	 * @param nonEnglishName
	 * @param cccCode
	 * @param idNumber
	 * @param bicSwiftCode
	 * @param freeFormatText
	 * @param country
	 * @param yearOfBirth
	 * @param gender
	 * @param routeRule
	 * @return NameCheckRecordDetail
	 */
	private NameCheckRecordDetail setNameCheckRecordDetail(
			String uniqueKey, int ncReferenceId, String checkSeq, 
			String entityType, String entityRelationship, String entityRelationshipDesc, 
			String englishName, String nonEnglishName, String cccCode, String idNumber, 
			String bicSwiftCode, String freeFormatText, String country, String yearOfBirth, String gender, String routeRule, String checkResult){
		NameCheckRecordDetail nameCheckRecordDetail = new NameCheckRecordDetail();
		NameCheckRecordDetailPK nameCheckRecordDetailPK = new NameCheckRecordDetailPK();
		nameCheckRecordDetail.setId(nameCheckRecordDetailPK);
		
		nameCheckRecordDetailPK.setUniqueKey(uniqueKey);
		nameCheckRecordDetailPK.setNcReferenceId(ncReferenceId);
		nameCheckRecordDetailPK.setCheckSeq(checkSeq);
		
		nameCheckRecordDetail.setEntityType(entityType);
		nameCheckRecordDetail.setEntityRelationship(entityRelationship);
		nameCheckRecordDetail.setEntityRelationshipDesc(entityRelationshipDesc);
		nameCheckRecordDetail.setEnglishName(englishName);
		nameCheckRecordDetail.setNonEnglishName(nonEnglishName);
		nameCheckRecordDetail.setCccCode(cccCode);
		nameCheckRecordDetail.setIdNumber(idNumber);
		nameCheckRecordDetail.setBicSwiftCode(bicSwiftCode);
		nameCheckRecordDetail.setFreeFormatText(freeFormatText);
		nameCheckRecordDetail.setCountry(country);
		if(yearOfBirth != null && yearOfBirth.length() > 0){
			nameCheckRecordDetail.setYearOfBirth(BigDecimal.valueOf(Long.parseLong(yearOfBirth)));
		}
		nameCheckRecordDetail.setGender(gender);
		nameCheckRecordDetail.setRouteRule(routeRule);
		nameCheckRecordDetail.setCheckResult(checkResult);
		return nameCheckRecordDetail;
	}
	
	private void filterDataByScore(List<NameHitRecord> nameHitRecordList, Map<String, XScreenProcessSetting> xScreenProcessSettingMap){
		Iterator<NameHitRecord> iterator = nameHitRecordList.iterator();
		while(iterator.hasNext()){
			NameHitRecord detail = iterator.next();
			XScreenProcessSetting xScreenProcessSetting=null;
			String[] detailStringArray=detail.getWatchListSubTypeCd().split(",");
			for(String detailString:detailStringArray){
				xScreenProcessSetting = xScreenProcessSettingMap.get(detailString);
				if(xScreenProcessSetting == null){
					iterator.remove();
				}
			}
			
			if(xScreenProcessSetting != null && detail.getMixScore() < xScreenProcessSetting.getScore().floatValue()){
				iterator.remove();
			}
		}	
	}
	
	
	/**
	 * 轉型 List<FscEntityWatchListDimChng> ->  List<FscEntityWatchListDim> 
	 * @param fscEntityWatchListDimChngList
	 * @return
	 */
	private List<FscEntityNcWatchListDim> changeTypeFscEntityWatchListDim(List<FscEntityWatchListDimChng> fscEntityWatchListDimChngList){
		List<FscEntityNcWatchListDim> fscEntityWatchListDimList = new ArrayList<FscEntityNcWatchListDim>();
		for(FscEntityWatchListDimChng beChng : fscEntityWatchListDimChngList){
			FscEntityNcWatchListDim be = new FscEntityNcWatchListDim();
			BeanUtils.copyProperties(beChng, be);
			fscEntityWatchListDimList.add(be);
		}
		return fscEntityWatchListDimList;
	}
		

	public Map<String, List<XWatchlistCompressString>> xWatchlistCompressString(){
		Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap = new HashMap<String, List<XWatchlistCompressString>>();
		List<XWatchlistCompressString> allCompressStringList= iXWatchlistCompressStringDao.findAll();
		List<XWatchlistCompressString> CorpCompressStringList=new ArrayList<XWatchlistCompressString>();
		List<XWatchlistCompressString> IndCompressStringList=new ArrayList<XWatchlistCompressString>();
		List<XWatchlistCompressString> SignCompressStringList=new ArrayList<XWatchlistCompressString>();	
		if(allCompressStringList != null && allCompressStringList.size() > 0){
			for(XWatchlistCompressString xWatchlistCompressString:allCompressStringList){
				if("CORP".equalsIgnoreCase(xWatchlistCompressString.getCompressType())){
					CorpCompressStringList.add(xWatchlistCompressString);
//					logger.info("CorpCompressStringList xWatchlistCompressString.getStringKey="+xWatchlistCompressString.getStringKey());
				}else if("IND".equalsIgnoreCase(xWatchlistCompressString.getCompressType())){
					IndCompressStringList.add(xWatchlistCompressString);		
//					logger.info("IndCompressStringList xWatchlistCompressString.getStringKey="+xWatchlistCompressString.getStringKey());
				}else if("SIG".equalsIgnoreCase(xWatchlistCompressString.getCompressType())){
					SignCompressStringList.add(xWatchlistCompressString);		
//					logger.info("SignCompressStringList xWatchlistCompressString.getStringKey="+xWatchlistCompressString.getStringKey());
				}
			}
		}
		xWatchlistCompressStringMap.put("CORP", CorpCompressStringList);
		xWatchlistCompressStringMap.put("IND", IndCompressStringList);
		xWatchlistCompressStringMap.put("SIG", SignCompressStringList);
		
		return xWatchlistCompressStringMap;
	}
	
	/**
	 * 取得名單小類的顯示順序，來代表嚴重程度
	 */
	private Map<String, String> getRankOfSubWatchListMap(){
		Map<String, String> resultMap = new HashMap<String, String>();
		List<RefTableValue> watchListTypeCdList = iRefTableValueDao.findByIdRefTableNm(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
		for(RefTableValue value : watchListTypeCdList){
			resultMap.put(value.getId().getValueCd(), value.getDisplayOrderNo().toString());
		}

		return resultMap;
	}
	
	//判斷是否為白名單
	private boolean checkInPartyWhitelist(String partyNumber){
		List<XPartyWhitelistMain> inputPartyNumberList = iXPartyWhitelistMainDao.findByIdPartyNumber(partyNumber);	
		if(inputPartyNumberList.size()>0){
			return true;
		}else{
			return false;			
		}
	}	
	

}
