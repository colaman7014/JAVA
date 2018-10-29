package com.sas.webservice.nameCheck;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.dataflux.wsdl.archserver.option.RowIn;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueue;
import com.sas.aml.retry.event.queue.repository.XRetryEventQueueRepository;
import com.sas.aml.retry.event.queue.service.QueueConstraint;
import com.sas.aml.retry.event.queue.service.XRetryEventQueueService;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.ICaseLiveDao;
import com.sas.db.aml.dao.ecm.IRefTableValueDao;
import com.sas.db.aml.dao.fcfcore.IFscBankNcWatchListDimDao;
import com.sas.db.aml.dao.fcfcore.IFscEntityNcWatchListDimDao;
import com.sas.db.aml.dao.fcfcore.IFscEntityWlXListSettingDao;
import com.sas.db.aml.dao.fcfcore.IFscLocationNcWatchListDimDao;
import com.sas.db.aml.dao.fcfcore.IXComboWhitelistMainDao;
import com.sas.db.aml.dao.fcfcore.IXPartyWhitelistMainDao;
import com.sas.db.aml.dao.fcfcore.IXScreenProcessSettingDao;
import com.sas.db.aml.dao.fcfcore.IXWatchlistCompressStringDao;
import com.sas.db.aml.dao.fcfcore.IXWatchlistSettingDao;
import com.sas.db.aml.orm.ecm.RefTableValue;
import com.sas.db.aml.orm.fcfcore.FscBankNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscEntityNcWatchListDim;
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
import com.sas.db.wlf.dao.nc.INameCheckLogTimeDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordDetailDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordMainDao;
import com.sas.db.wlf.dao.nc.INameHitRecordDao;
import com.sas.db.wlf.orm.CallingSystemAction;
import com.sas.db.wlf.orm.CallingSystemActionPK;
import com.sas.db.wlf.orm.KeyGenerate;
import com.sas.db.wlf.orm.nc.NameCheckLogTime;
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
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputDetail;
import com.sas.wlsearch.bean.MatchCodeResultBean;
import com.sas.wlsearch.business.DataFluxMatchCodeOption;
import com.sas.wlsearch.util.WatchListUtil;

/**setMap
 * AML Name Check 主程式
 * @author SAS
 *
 */
@Component
@WebService(endpointInterface = "com.sas.webservice.nameCheck.AmlNameCheck")
public class AmlNameCheckImpl implements AmlNameCheck {
	private static final Logger logger = LoggerFactory.getLogger(AmlNameCheckImpl.class);
	@Autowired
	ServletContext context; 
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
	DataFluxMatchCodeOption dataFluxMatchCodeOption;
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
	@Autowired
	XRetryEventQueueRepository xRetryEventQueueRepository;
	@Autowired
	XRetryEventQueueService xRetryEventQueueService;
	@Autowired
	INameCheckLogTimeDao iNameCheckLogTimeDao;

	/**
	 * AML Name Check 主要程式, 交易控制需額外處理
	 */
	@Override
	public NameCheckOutputBean NonTransactionNameCheck(NameCheckInputBean input, long id) {
		logger.info("\n --------------- NameCheck Begin......  --------------- \n");
		logger.info(input.toString());
		Date currentDateTime = new Date();
		Timestamp currentTimestamp = new Timestamp(currentDateTime.getTime());
		NameCheckLogTime nameCheckLogTime = new NameCheckLogTime();
		nameCheckLogTime.setTime1(currentTimestamp);
		
		NameCheckOutputBean nameCheckOutputBean = new NameCheckOutputBean();
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
		List<NameCheckInputDetailBean> seq = input.getSeq();
		
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
		
		try{
			List<KeyGenerate> keyGenerateList = iKeyGenerateDao.findByUniqueKeyOrderByProcessDttmDesc(uniqueKey);
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
				KeyGenerate keyGenerate = new KeyGenerate();
				keyGenerate.setUniqueKey(uniqueKey);
				keyGenerate.setInterfaceType(SwiftMtConst.INTERFACE_TYPE_NAMECHECK);
				keyGenerate.setProcessDttm(new Date());
				iKeyGenerateDao.save(keyGenerate);
				ncReferenceId = keyGenerate.getReferenceId();
				nameCheckLogTime.setUniqueKey(uniqueKey);
				nameCheckLogTime.setNcreferenceId(ncReferenceId);
				iNameCheckLogTimeDao.save(nameCheckLogTime);
				
				List<NameCheckRecordDetail> nameCheckRecordDetailList = new ArrayList<NameCheckRecordDetail>();
				List<NameHitRecord> totalNameHitRecordList = new ArrayList<NameHitRecord>();
				Map<String, XScreenProcessSetting> xScreenProcessSettingMap = getXScreenProcessSettingMap(screenProcess);
				XWatchlistSetting xWatchlistSetting = iXWatchlistSettingDao.findByChangeCurrentInd(SwiftMtConst.CHANGE_CURRENT_IND_Y);
				Map<String, String> rankOfWatchListMap = getRankOfWatchListMap();
				Map<String, String> rankOfSubWatchListMap = getRankOfSubWatchListMap();
				Map<String, String> subWatchListTypeAndWatchListTypeMappingMap = getSubWatchListTypeAndWatchListTypeMapping();
				Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap= xWatchlistCompressString();
				String sensitivity = String.valueOf(xWatchlistSetting.getFuzzyMatchingSenstive().setScale(0, BigDecimal.ROUND_HALF_UP)); //Fuzzy Mathc Sensitivy
				boolean isNcResult = false;
				for(NameCheckInputDetailBean detail :  seq){
					String checkSeq = detail.getCheckSeq();
					String entityType = detail.getEntityType();
					String entityRelationship = detail.getEntityRelationship();
					String entityRelationshipDesc = detail.getEntityRelationshipDesc();
					String inputEnName = detail.getEnglishName() == null ? "" :  detail.getEnglishName();
					String inputCnName = detail.getNonEnglishName() == null ? "" : detail.getNonEnglishName();
					long getStandardrizeStringStartTime = System.currentTimeMillis();
					Map<String,String> partyWhiteNameMap=standardrizeInputForPartyWhitelist(inputCnName,inputEnName);
					Map<String, Map<String, String>> nameMap=StringUtils.standardrizeInput(entityType, inputCnName,inputEnName,xWatchlistCompressStringMap);
					long getStandardrizeStringEndTime = System.currentTimeMillis();
					logger.debug("Thread unikey:{} ncRfId:{} CheckSeq:{} getStandardrizeStringTime:{}",  input.getUniqueKey(), ncReferenceId, checkSeq,(getStandardrizeStringEndTime - getStandardrizeStringStartTime));
					List<String> nonEnglishName =StringUtils.getNameList(nameMap, "cn");
					List<String> englishName =StringUtils.getNameList(nameMap, "en");
					List<String> inputCnFuzzyName =StringUtils.getNameList(nameMap, "cnFuzzy");
					List<String> inputEnFuzzyName =StringUtils.getNameList(nameMap, "enFuzzy");
					String cccCode = detail.getCccCode();
					String idNumber = detail.getIdNumber();
					String bicSwiftCode = detail.getBicSwiftCode();
					String freeFormatText = detail.getFreeFormatText();
					String country = detail.getCountry();
					String yearOfBirth = detail.getYearOfBirth();
					String gender = detail.getGender();
										
					String checkResult = SwiftMtConst.NC_RESULT_NO_HIT;
					String routeRule = "";
					
					String partyNonEnglishName=partyWhiteNameMap.get("cn");
					String partyEnglishName=partyWhiteNameMap.get("en");
					boolean isInPartyWhiteList = checkInPartyWhitelist(partyNumber,partyNonEnglishName,partyEnglishName);
						
					List<NameHitRecord> nameHitRecordList = new ArrayList<NameHitRecord>();
					List<NameHitRecord> countryCodeNameHitRecordList = new ArrayList<NameHitRecord>();
					//輸入欄位資訊判斷
					if(!isInPartyWhiteList) {
						if(chkScanFields(screenProcess, inputEnName, inputCnName, cccCode, idNumber, bicSwiftCode, freeFormatText, country)){
							checkResult = SwiftMtConst.NC_RESULT_NON_CHECK;
						}else{
							if(SwiftMtConst.ENTITY_TYPE_BANK.equals(entityType)){ //entity_type = 04(銀行) 掃描方式
								nameHitRecordList = handleBankNameHitRecordList(
										uniqueKey, ncReferenceId, checkSeq,
										nonEnglishName, englishName,
										inputCnFuzzyName, inputEnFuzzyName,
										bicSwiftCode, xWatchlistSetting,
										xWatchlistCompressStringMap, sensitivity);
								if(!StringUtils.isEmpty(bicSwiftCode)){
									String countryCode = bicSwiftCode.substring(5, 6);
									countryCodeNameHitRecordList = handleCountryCode(uniqueKey, ncReferenceId, checkSeq, countryCode, xWatchlistSetting);
								}
							}else if(SwiftMtConst.ENTITY_TYPE_COUNTRY.equals(entityType)){  //entity_type = 08(國家) 掃描方式
								if(country ==  null || country.length() == 0){
									checkResult = SwiftMtConst.NC_RESULT_NON_CHECK;
								}else{
									nameHitRecordList = handleLocationNameHitRecordList(uniqueKey, ncReferenceId, checkSeq,country, xWatchlistSetting, sensitivity);
									countryCodeNameHitRecordList = handleCountryCode(uniqueKey, ncReferenceId, checkSeq, country, xWatchlistSetting);
								}
							}else{
								nameHitRecordList = handleNameHitRecordList(
										uniqueKey, ncReferenceId, checkSeq,
										nonEnglishName, englishName,
										inputCnFuzzyName, inputEnFuzzyName,
										cccCode, idNumber, bicSwiftCode,
										freeFormatText, country, screenProcess,
										entityType, gender, xWatchlistSetting,
										xWatchlistCompressStringMap, sensitivity);
							}
							if(SwiftMtConst.ENTITY_TYPE_CORP.equals(entityType) || SwiftMtConst.ENTITY_TYPE_INDANDCORP.equals(entityType)) {

								nameHitRecordList
										.addAll(handleBankNameHitRecordList(
												uniqueKey, ncReferenceId, checkSeq,
												nonEnglishName, englishName,
												inputCnFuzzyName, inputEnFuzzyName,
												bicSwiftCode, xWatchlistSetting,
												xWatchlistCompressStringMap,
												sensitivity));
                
								if(!StringUtils.isEmpty(bicSwiftCode) && bicSwiftCode != null && bicSwiftCode.length() > 6){

									String countryCode = bicSwiftCode.substring(5, 6);
									countryCodeNameHitRecordList = handleCountryCode(uniqueKey, ncReferenceId, checkSeq, countryCode, xWatchlistSetting);
								}
							}
						}
						List<String> entityTypeList = handelEntityType(entityType);
						
						nameHitRecordList = setWatchListType(nameHitRecordList, xScreenProcessSettingMap, entityTypeList,rankOfWatchListMap, rankOfSubWatchListMap);
						
						//剔除交易對手白名單
						if(partyNumber != null && !"".equals(partyNumber) && (SwiftMtConst.SCREEN_PROCESS_Transaction_Screening.equals(screenProcess) || SwiftMtConst.SCREEN_PROCESS_Trade_Finance_Screening.equals(screenProcess)) && "05".equals(entityRelationship)){
							filterXComboWhitelist(partyNumber, nameHitRecordList);
						}

                        Map<String, String> calcNameMap = new HashMap<String, String>();
                        if(SwiftMtConst.ENTITY_TYPE_ALL.equals(entityType)
                                || SwiftMtConst.ENTITY_TYPE_INDANDCORP.equals(entityType)
                                || SwiftMtConst.ENTITY_TYPE_FREEFPRMAT.equals(entityType)) {
                            calcNameMap = nameMap.get(SwiftMtConst.COMPRESSSTRING_CORP);
                        } else if (SwiftMtConst.ENTITY_TYPE_IND.equals(entityType)
                        ) {
                            calcNameMap = nameMap.get(SwiftMtConst.COMPRESSSTRING_IND);
                        } else if (SwiftMtConst.ENTITY_TYPE_CORP.equals(entityType)
                                || SwiftMtConst.ENTITY_TYPE_BANK.equals(entityType)
                                || SwiftMtConst.ENTITY_TYPE_COUNTRY.equals(entityType)
    							||SwiftMtConst.ENTITY_TYPE_PORT.equals(entityType)
    							||SwiftMtConst.ENTITY_TYPE_BOAT.equals(entityType)) {
                            calcNameMap = nameMap.get(SwiftMtConst.COMPRESSSTRING_CORP);
                        }else{
                            calcNameMap = nameMap.get(SwiftMtConst.COMPRESSSTRING_SIG);
                        }
	
						if(nameHitRecordList != null && nameHitRecordList.size() > 0){
							for(NameHitRecord nameHitRecord : nameHitRecordList){
								NameCheckUtil.calculateScore(nameHitRecord,	calcNameMap.get("cn"), calcNameMap.get("en"), idNumber, country, yearOfBirth, xWatchlistSetting, entityType);
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
						
//						List<NameHitRecord> newNameHitRecordList=setNameHitRecordListWatchListSubTypeCd(nameHitRecordList);
						totalNameHitRecordList.addAll(nameHitRecordList);

						if(countryCodeNameHitRecordList.size() > 0){
							countryCodeNameHitRecordList = setWatchListType(countryCodeNameHitRecordList, xScreenProcessSettingMap, entityTypeList,rankOfWatchListMap, rankOfSubWatchListMap);

							for(NameHitRecord nameHitRecord : countryCodeNameHitRecordList){
								nameHitRecord.setMixScore(nameHitRecord.getExactMatchScore());
								//2018.2.22 jerry BOT detail routeRule 為多個分類以逗號區隔
								routeRule = NameCheckUtil.sortRule(routeRule, nameHitRecord.getWatchListSubTypeCd(),
										rankOfSubWatchListMap,
										SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
							}
							filterDataByScore(countryCodeNameHitRecordList, xScreenProcessSettingMap);
							totalNameHitRecordList.addAll(countryCodeNameHitRecordList);
							if(countryCodeNameHitRecordList.size()>0){
								checkResult = SwiftMtConst.NC_RESULT_HIT;
								isNcResult = true;
							}
						}
					}
					NameCheckOutputDetail nameCheckOutputDetail = new NameCheckOutputDetail();
					nameCheckOutputDetailList.add(nameCheckOutputDetail);
					nameCheckOutputDetail.setCheckSeq(checkSeq);
					nameCheckOutputDetail.setCheckResult(checkResult);
					nameCheckOutputDetail.setRouteRule(routeRule);
					
					NameCheckRecordDetail nameCheckRecordDetail = setNameCheckRecordDetail(uniqueKey, ncReferenceId, checkSeq, entityType, entityRelationship, 
							entityRelationshipDesc, inputEnName, inputCnName, cccCode, idNumber, bicSwiftCode, freeFormatText, country, yearOfBirth, gender, routeRule, checkResult);
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
						routeRule = NameCheckUtil.seriousRule(routeRule, nameCheckRecordDetail.getRouteRule(), subWatchListTypeAndWatchListTypeMappingMap, rankOfWatchListMap);
					}
					for(NameCheckRecordDetail nameCheckRecordDetail : nameCheckRecordDetailList){
						String detailRouteRule = nameCheckRecordDetail.getRouteRule();
						if(detailRouteRule!= null && !"".equals(detailRouteRule) && NameCheckUtil.checkWatchlistTypeRelationship(subWatchListTypeAndWatchListTypeMappingMap, detailRouteRule, routeRule)){
							hitSeq += String.format(",%s", nameCheckRecordDetail.getId().getCheckSeq());
						}
					}
					String hitSeqTmp = hitSeq.indexOf(",") > -1 ? hitSeq.substring(1) : hitSeq;
					if(hitSeqTmp.indexOf(",") > -1){
						hitSeq = hitSeqTmp.substring(0, hitSeqTmp.indexOf(","));
					}else{
						hitSeq = hitSeqTmp;
					}
				}
				
				/********************************/
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
				currentDateTime = new Date();
				currentTimestamp = new Timestamp(currentDateTime.getTime());
				nameCheckLogTime.setTime2(currentTimestamp);
				iNameCheckLogTimeDao.save(nameCheckLogTime);
				logger.info("\n ##################### NameCheck Response ##################### \n");
			}
		}catch(Exception e){
			logger.error(String.format("NameCheck fail, exception : %s", e.getMessage()), e);
			XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.saveEntityToXRetryEventQueue(SwiftMtConst.SOURCE_TYPE_RT_NC, NameCheckInputBean.class.getName(), 
					input, HttpMethod.POST, 
					AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) +  context.getContextPath() + "/rest/retryNameCheck", 3,
					"retryNameCheck", id, QueueConstraint.QUEUE_EVENT_STATUS_WAITING, false, "");
			if(xRetryEventQueue == null) {
				logger.info("Retry id not exist");
			} else {
				logger.info("Retry id exist : "+ xRetryEventQueue.getId());
			}
			nameCheckOutputBean.setInterfaceName(interfaceName);
			nameCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SYSTEMERR);
			nameCheckOutputBean.setErrorMessage(SwiftMtConst.ERROR_CODE_INTERNALERR_MESSAGE);
			nameCheckOutputBean.setNcResult(SwiftMtConst.NC_RESULT_FAIL);
			nameCheckOutputBean.setUniqueKey(uniqueKey);
			nameCheckOutputBean.setRefernceNumber(referenceNumber);
			nameCheckOutputBean.setRouteRule("");
			nameCheckOutputBean.setHitSeq("");
			nameCheckOutputBean.setSeq(null);
			logger.info(nameCheckOutputBean.toString());
			
			return nameCheckOutputBean;
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
	/**
	 * 2018.02.22 jerry
	 * 
	 * 將X_WATCHLIST_COMPRESS_STRING的資料依照個人法人和符號分類存成map
	 */
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
				}else if("IND".equalsIgnoreCase(xWatchlistCompressString.getCompressType())){
					IndCompressStringList.add(xWatchlistCompressString);
				}else if("SIG".equalsIgnoreCase(xWatchlistCompressString.getCompressType())){
					SignCompressStringList.add(xWatchlistCompressString);
				}
			}
		}
		xWatchlistCompressStringMap.put("CORP", CorpCompressStringList);
		xWatchlistCompressStringMap.put("IND", IndCompressStringList);
		xWatchlistCompressStringMap.put("SIG", SignCompressStringList);
		
		return xWatchlistCompressStringMap;
	}
	/***
	 * 字串處理方法
	 * @param inputString
	 * @return
	 */
//	public Map<String, String> standardrizeInput(String nonEnglishName,String englishName,String entityType,Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap){
//		//Jerry 2017.02.05 standardrizeInput
//		Map<String,String> nameMap=StringUtils.standardrizeInput(entityType, nonEnglishName,englishName,xWatchlistCompressStringMap);
//		return nameMap;
//	}

	/***
	 * 白名單字串處理方法
	 * @param inputString
	 * @return
	 */
	public Map<String, String> standardrizeInputForPartyWhitelist(String nonEnglishName,String englishName){
		String standardInputCnName = StringUtils.convertToHalfWidth(nonEnglishName);
		String standardInputEnName = StringUtils.convertToHalfWidth(englishName);
		standardInputEnName=standardInputEnName.toUpperCase();
		Map<String,String> nameMap = new HashMap<String,String>();
		if(standardInputEnName != "" || standardInputCnName != "") {
			//多個空白(含tab)replace成一個
			if(standardInputCnName != null){
				standardInputCnName=StringUtils.lianxuStr(standardInputCnName);
			}
			if(standardInputEnName != null){
				standardInputEnName=StringUtils.lianxuStr(standardInputEnName);
			}	
			//移除前後空白
			if(standardInputEnName != null){
				standardInputEnName = org.apache.commons.lang.StringUtils.stripEnd(standardInputEnName, " ");
				standardInputEnName = org.apache.commons.lang.StringUtils.stripStart(standardInputEnName, " ");
			}
			if(standardInputCnName != null){
				standardInputCnName = org.apache.commons.lang.StringUtils.stripEnd(standardInputCnName, " ");
				standardInputCnName = org.apache.commons.lang.StringUtils.stripStart(standardInputCnName, " ");
			}
		}
		nameMap.put("cn", standardInputCnName);
		nameMap.put("en", standardInputEnName);
		return nameMap;
	}		
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
	private boolean chkFields(String interfaceName, String callingSystem, String screenProcess, String branchNumber, String genAlertFlag, String uniqueKey, List<NameCheckInputDetailBean> seq){
		boolean result = false;
		if(StringUtils.isEmpty(interfaceName) 
				|| (!SwiftMtConst.INTERFACE_TYPE_NAMECHECK.equals(interfaceName) && !SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK.equals(interfaceName) && 
					!SwiftMtConst.INTERFACE_TYPE_BATCHTRANSACTIONSCREENING.equals(interfaceName) && !SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECKSTATUS.equals(interfaceName))
		){
			logger.info("InterfaceName Empty");
			result = true;
		}
		
		if(StringUtils.isEmpty(callingSystem)
		){
			logger.info("CallingSystem Empty");
			result = true;
		}
		
		// BOT CallingSystem 檢查功能
		if( iCallingSystemActionDao.findOne(new CallingSystemActionPK(callingSystem, "*")) == null ) {
			logger.info("CallingSystem Not Exist");
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
			logger.info("ScreenProcess ::: " + screenProcess);
			logger.info("ScreenProcess Format Error");
			result = true;
		}
		
		if(StringUtils.isEmpty(branchNumber)
		){
			logger.info("BranchNumber Empty");
			result = true;
		}
		
		// BOT分行檢查功能
		if( iBranchNumberActionDao.findOne(branchNumber) == null ) {
			logger.info("BranchNumber Not Exist");
			result = true;
		}
		
		if(StringUtils.isEmpty(genAlertFlag)
		){
			logger.info("GenAlertFlag Empty");
			result = true;
		}
		
		if(StringUtils.isEmpty(uniqueKey)
		){
			logger.info("UniqueKey Empty");
			result = true;
		}
		String limit = AmlConfiguration.getString("com.sas.namecheck.detail.seq.limit");
		int seqLimit = StringUtils.isEmpty(limit) ? 20 : Integer.parseInt(limit);
		if(seq == null || seq.size() == 0 || seq.size() > seqLimit
		){
			logger.info("Over SeqLimit");
			result = true;
		}
		return result;
	}
	
	/**
	 * 需做交易控制的name check
	 */
	@Override
	@Transactional
	public NameCheckOutputBean NameCheck(NameCheckInputBean input) {
		input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_NAMECHECK);
		input.setTransactionDate(new Date());
		return NonTransactionNameCheck(input, 0);
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
	/**
	 * 是否在客戶白名單內
	 * @param partyNumber
	 * @param inputCnName
	 * @param inputEnName
	 */
	private boolean checkInPartyWhitelist(String partyNumber,String inputCnName,String inputEnName) {
		boolean isWhiteList = false;
		boolean cnResult=false;
		boolean enResult=false;		
		XPartyWhitelistMainPK xPartyWhitelistMainPK = new XPartyWhitelistMainPK();
		XPartyWhitelistMain inputCnNameXPartyWhitelistMain = new XPartyWhitelistMain();
		XPartyWhitelistMain inputEnNameXPartyWhitelistMain = new XPartyWhitelistMain();
		if(partyNumber != null && !"".equals(partyNumber))
		{
			xPartyWhitelistMainPK.setPartyNumber(partyNumber);	
			if(inputCnName != null && !"".equals(inputCnName)){
				xPartyWhitelistMainPK.setPartyName(inputCnName);	
				inputCnNameXPartyWhitelistMain = iXPartyWhitelistMainDao.findOne(xPartyWhitelistMainPK);
			}
			if(inputEnName!=null && !"".equals(inputEnName)){
				xPartyWhitelistMainPK.setPartyName(inputEnName);	
				inputEnNameXPartyWhitelistMain = iXPartyWhitelistMainDao.findOne(xPartyWhitelistMainPK);
			}
		}		
		if(inputCnNameXPartyWhitelistMain != null){
			cnResult = "Y".equalsIgnoreCase(inputCnNameXPartyWhitelistMain.getIsWhitelist()) ? true : false;
		} 
		if(inputEnNameXPartyWhitelistMain != null){
			enResult = "Y".equalsIgnoreCase(inputEnNameXPartyWhitelistMain.getIsWhitelist()) ? true : false;
		}
		if(cnResult==true||enResult==true){
			isWhiteList=true;
		}else{
			isWhiteList=false;			
		}
		return isWhiteList;
	}		
	
	/*
	 * 取得名單大小類的顯示順序及其資訊
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
			String uniqueKey,
			int ncReferenceId,
			String checkSeq,
			List<String> inputCnName,
			List<String> inputEnName,
			List<String> inputCnFuzzyName,
			List<String> inputEnFuzzyName,
			String cccCode,
			String idNumber,
			String bicSwiftCode,
			String freeFormatText,
			String country,
			String screenProcess,
			String entityType,
			String gender,
			XWatchlistSetting xWatchlistSetting,
			Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap,
			String sensitivity){
		List<FscEntityNcWatchListDim> exactMatchList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscEntityNcWatchListDim> fuzzyMatchList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscEntityNcWatchListDim> inclusiveMatchList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscEntityNcWatchListDim> cccCodeList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscEntityNcWatchListDim> isNumberList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscEntityNcWatchListDim> freeFormatTextList = new ArrayList<FscEntityNcWatchListDim>();
		List<FscBankNcWatchListDim> bicSwiftCodeList = new ArrayList<FscBankNcWatchListDim>();
		List<FscLocationNcWatchListDim> countryList = new ArrayList<FscLocationNcWatchListDim>();
//		String inputCnName =nameMap.get("cn");
//		String inputEnName =nameMap.get("en");
//		String inputCnFuzzyName =nameMap.get("cnFuzzy");
//		String inputEnFuzzyName =nameMap.get("enFuzzy");
		
		if(!inputCnName.isEmpty() || !inputEnName.isEmpty()){
			logger.info("inputCnName::: " + inputCnName + ", inputEnName::::"+ inputEnName);
			exactMatchList = findByExactMatch(inputCnName, inputEnName);
			fuzzyMatchList = findByFuzzyMatch( entityType, sensitivity, inputCnFuzzyName, inputEnFuzzyName);
			inclusiveMatchList = findByInclusiveMatchList(inputCnName, inputEnName);
		}
		
		if(!StringUtils.isEmpty(cccCode)){
			cccCodeList = findByCccCode(cccCode);
		}
		
		if(!StringUtils.isEmpty(idNumber)){
			isNumberList = findByIdNumber(idNumber);
		}
		
		if(!StringUtils.isEmpty(bicSwiftCode)){
			bicSwiftCodeList = findByBicSwiftCode(bicSwiftCode);
		}
		
		if(!StringUtils.isEmpty(freeFormatText)){
			String inputFreeFormatText = WatchListUtil.convertWordTo(freeFormatText, SwiftMtConst.COVERTWORD, " ");
			freeFormatTextList = findByFreeFormatText(sensitivity, inputFreeFormatText);
		}
		
		if(!"1".equals(screenProcess) && !"2".equals(screenProcess) && !StringUtils.isEmpty(country) ){
			String inputCountryName = WatchListUtil.convertWordTo(country, SwiftMtConst.COVERTWORD, "");
			countryList = findByFuzzyCountryName(sensitivity, inputCountryName);
		}
		
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
	private List<NameHitRecord> handleLocationNameHitRecordList(String uniqueKey, int ncReferenceId, String checkSeq, String country, XWatchlistSetting xWatchlistSetting, String sensitivity){
		List<NameHitRecord> nameHitRecordList = new ArrayList<NameHitRecord>();
		List<String> compressStringList = new ArrayList<>();
		Map<String, List<XWatchlistCompressString>> mapXWatchlistCompressStringMap = xWatchlistCompressString();
		List<XWatchlistCompressString> xWatchlistCompressStringList = mapXWatchlistCompressStringMap.get(SwiftMtConst.COMPRESSSTRING_CORP);
		xWatchlistCompressStringList.addAll(mapXWatchlistCompressStringMap.get(SwiftMtConst.COMPRESSSTRING_CORP));
		if(xWatchlistCompressStringList != null && xWatchlistCompressStringList.size() > 0) {
			for (XWatchlistCompressString xWatchlistCompressString : xWatchlistCompressStringList) {
				compressStringList.add(xWatchlistCompressString.getCompressString());
			}
		}
		
		String input = StringUtils.standardizationInputFromLast(country, SwiftMtConst.ENTITY_TYPE_CORP, compressStringList);
		List<String> locationStrList = NameCheckUtil.getShiftInMaxArrangementInLimitList(input);
		
		List<FscLocationNcWatchListDim> exactMatchLocationNameList = new ArrayList<FscLocationNcWatchListDim>();
		List<FscLocationNcWatchListDim> fuzzyMatchLocationNameList = new ArrayList<FscLocationNcWatchListDim>();
		
		exactMatchLocationNameList = findLocationByExactMatch(locationStrList);
		fuzzyMatchLocationNameList = findLocationByFuzzyMatch(getFuzzyFreeFormatTextMatchCode(sensitivity, locationStrList));
		
		Map<Long, NameHitRecord> nameHitRecordMap = new HashMap<Long, NameHitRecord>();
		
		setLocationNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_EXACT, exactMatchLocationNameList, uniqueKey, ncReferenceId, checkSeq, xWatchlistSetting);
		setLocationNCDefaultScore(nameHitRecordMap, SwiftMtConst.NAME_CHECK_FUZZY, fuzzyMatchLocationNameList, uniqueKey, ncReferenceId, checkSeq, xWatchlistSetting);
		
		for (Map.Entry<Long, NameHitRecord> entry : nameHitRecordMap.entrySet()) {
			nameHitRecordList.add(entry.getValue());
		}
		return nameHitRecordList;
	}
	
	private List<NameHitRecord> handleCountryCode(String uniqueKey, int ncReferenceId, String checkSeq, String countryCode, XWatchlistSetting xWatchlistSetting){
		List<NameHitRecord> nameHitRecordList = new ArrayList<NameHitRecord>();
		List<FscLocationNcWatchListDim> exactMatchLocationNameList = new ArrayList<FscLocationNcWatchListDim>();
		
		if(!StringUtils.isEmpty(countryCode)){
			exactMatchLocationNameList = findCountryCodeByExactMatch(countryCode);
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
	private List<NameHitRecord> handleBankNameHitRecordList(String uniqueKey, int ncReferenceId, String checkSeq, List<String> inputCnName, List<String> inputEnName,
			List<String> inputCnFuzzyName, List<String> inputEnFuzzyName,String bicSwiftCode, XWatchlistSetting xWatchlistSetting,
			Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap, String sensitivity){
		List<NameHitRecord> nameHitRecordList = new ArrayList<NameHitRecord>();
		List<FscBankNcWatchListDim> exactMatchBankNameList = new ArrayList<FscBankNcWatchListDim>();
		List<FscBankNcWatchListDim> fuzzyMatchBankNameList = new ArrayList<FscBankNcWatchListDim>();
		List<FscBankNcWatchListDim> inclusiveMatchBankNameList = new ArrayList<FscBankNcWatchListDim>();
		List<FscBankNcWatchListDim> bicSwiftCodeList = new ArrayList<FscBankNcWatchListDim>();
		
		if(!inputEnName.isEmpty() || !inputCnName.isEmpty()){
			exactMatchBankNameList = findBankByExactMatch(inputCnName, inputEnName);
			fuzzyMatchBankNameList = findBankByFuzzyMatch(sensitivity, inputCnFuzzyName, inputEnFuzzyName);
			inclusiveMatchBankNameList = findBankByInclusiveMatchList(inputCnName, inputEnName);
		}
		if(!StringUtils.isEmpty(bicSwiftCode)){
			String newScanBicCode = WatchListUtil.getFirstNCharactersInBCCCode(bicSwiftCode);
			bicSwiftCodeList = findByBicSwiftCode(newScanBicCode.contains("%")?newScanBicCode.replace("%", ""):newScanBicCode);
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
	private List<NameHitRecord> setWatchListType(List<NameHitRecord> nameHitRecordList,
			Map<String, XScreenProcessSetting> xScreenProcessSettingMap, List<String> entityTypeList,
			Map<String, String> rankOfWatchListMap, Map<String, String> rankOfSubWatchListMap) {
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
						if (xScreenProcessSettingMap.get(xFscEntityWlXListSetting.getWatchListSubTypeCd()) != null) {
							newWatchListSubTypeCd = NameCheckUtil.sortRule(newWatchListSubTypeCd,
									xFscEntityWlXListSetting.getWatchListSubTypeCd(), rankOfSubWatchListMap,
									SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
							newWatchListTypeCd = NameCheckUtil.sortRule(newWatchListTypeCd,
									xFscEntityWlXListSetting.getWatchListTypeCd(), rankOfWatchListMap,
									SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
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
	private List<FscBankNcWatchListDim> findBankByInclusiveMatchList(List<String> inputCnName, List<String> inputEnName){
		List<FscBankNcWatchListDim> resultList = new ArrayList<FscBankNcWatchListDim>();
		List<String> inclusiveList = new ArrayList<String>();
		StringUtils.getInclusiveString(inclusiveList, inputCnName, false);
		StringUtils.getInclusiveString(inclusiveList, inputEnName, true);
		
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
	private List<FscEntityNcWatchListDim> findByInclusiveMatchList(List<String> inputCnName, List<String> inputEnName){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();
		List<String> inclusiveList = new ArrayList<String>();
		StringUtils.getInclusiveString(inclusiveList, inputCnName, false);
		StringUtils.getInclusiveString(inclusiveList, inputEnName, true);

		if(inclusiveList.size() > 0){
			String inclusiveSql = iFscEntityNcWatchListDimDao.getInculsiveQuerySql(inclusiveList);
			logger.debug("inclusiveSql : " + inclusiveSql);
			resultList.addAll(iFscEntityNcWatchListDimDao.nativeSql(inclusiveSql));
		}
		return resultList;
	}
	
	/**
	 * FreeFormat掃描 (名單主檔)
	 * @param freeFormatText
	 * @return List<FscEntityNcWatchListDim>
	 */
	private List<FscEntityNcWatchListDim> findByFreeFormatText(String sensitivity, String freeFormatText){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();
		List<String> freeFormatTextList = handelFreeFormatText(freeFormatText);
		if(freeFormatTextList.size() > 0){
			List<String> fuzzyFreeFormatTextList = getFuzzyFreeFormatTextMatchCode(sensitivity, freeFormatTextList);
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
	private List<String> getFuzzyFreeFormatTextMatchCode(String sensitivity, List<String> freeFormatTextList){
		List<String> resultList =  new ArrayList<String>();
		Map<String, MatchCodeResultBean> fuzzyMatchMap = new HashMap<String, MatchCodeResultBean>();
		List<RowIn> rowInList =  new ArrayList<RowIn>();
		int i = 0;
		for(String key : freeFormatTextList){
			RowIn rowIn = new RowIn();
			if(StringUtils.isEnglish(key)){
				rowIn.setEnName(key);
				rowIn.setChName("");
			}else{
				rowIn.setEnName("");
				rowIn.setChName(key);
			}
			rowInList.add(rowIn);
			
			i++;
			//dataflux max rowin number : 50000 for each request
			if(i % 50000 == 0 || i == freeFormatTextList.size()){
				List<Map<String, MatchCodeResultBean>> datafluxMatchCodeList = dataFluxMatchCodeOption.datafluxMatchCodeList(sensitivity, rowInList);
				
				for(Map<String, MatchCodeResultBean> matchCodeMap : datafluxMatchCodeList){
					fuzzyMatchMap.putAll(matchCodeMap);
				}
				rowInList =  new ArrayList<RowIn>();
			}
		}
		for (Map.Entry<String, MatchCodeResultBean> entry : fuzzyMatchMap.entrySet()) {
			resultList.add(entry.getValue().getMatchcodeOrg());
			resultList.add(entry.getValue().getMatchcodeInd());
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
	private List<FscLocationNcWatchListDim> findByFuzzyCountryName(String sensitivity, String matchCodeCountryName){
		List<FscLocationNcWatchListDim> resultList = new ArrayList<FscLocationNcWatchListDim>();
		Map<String, MatchCodeResultBean> matchCodeMap = dataFluxMatchCodeOption.datafluxMatchCode(sensitivity , null, matchCodeCountryName);
		
		//國家只取法人		
		if(matchCodeMap.containsKey(matchCodeCountryName)){
			resultList.addAll(iFscLocationNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryName(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, matchCodeMap.get(matchCodeCountryName).getMatchcodeOrg()));
		}
		
		return resultList;
	}
	
	/**
	 * 撈取Bic Code(名單銀行檔)
	 * @param bicSwiftCode
	 * @return List<FscBankNcWatchListDim>
	 */
	private List<FscBankNcWatchListDim> findByBicSwiftCode(String bicSwiftCode){
		List<FscBankNcWatchListDim> resultList = new ArrayList<FscBankNcWatchListDim>();
		if(bicSwiftCode.length() >= Integer.parseInt(AmlConfiguration.getString("com.sas.swiftCheck.bccCodeNNumbers")))
			resultList.addAll(iFscBankNcWatchListDimDao.findByChangeCurrentIndAndBccCodeStartingWith(SwiftMtConst.CHANGE_CURRENT_IND_Y, bicSwiftCode));
		else
			resultList.addAll(iFscBankNcWatchListDimDao.findByChangeCurrentIndAndBccCode(SwiftMtConst.CHANGE_CURRENT_IND_Y, bicSwiftCode));
		return resultList;
	}
	
	/**
	 * 撈取CCC Code(名單主檔)
	 * @param cccCode
	 * @return List<FscEntityNcWatchListDim>
	 */
	private List<FscEntityNcWatchListDim> findByCccCode(String cccCode){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();		
		resultList.addAll(iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityName(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, cccCode));
		return resultList;
	}
	
	/**
	 * 撈取 IdNumber(名單主檔)
	 * @param idNumber
	 * @return List<FscEntityNcWatchListDim>
	 */
	private List<FscEntityNcWatchListDim> findByIdNumber(String idNumber){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();		
		resultList.addAll(iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndIdentificationId(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, idNumber));
		return resultList;
	}
	
	/**
	 * FuzzyMatch(名單國家檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscLocationNcWatchListDim> 
	 */
	private List<FscLocationNcWatchListDim> findLocationByFuzzyMatch(List<String> countryList){
		List<FscLocationNcWatchListDim> resultList = new ArrayList<FscLocationNcWatchListDim>();
		int limit = 2000;
		if(countryList.size() > limit){
			int i = 0;
			for(String str : countryList){
				i++;
				List<String> tmpList = new ArrayList<String>();
				tmpList.add(str);
				
				if(i % limit == 0 || i == countryList.size()){
					resultList.addAll(iFscLocationNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, tmpList));
					}
			}
		}else{
			resultList.addAll(iFscLocationNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeCountryNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, countryList));
		}
		
		return resultList;
	}
	
	/**
	 * FuzzyMatch(名單國家檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscLocationNcWatchListDim> 
	 */
	private List<FscLocationNcWatchListDim> findLocationByFuzzyMatch(String sensitivity, String inputCnName, String inputEnName){
		List<FscLocationNcWatchListDim> resultList = new ArrayList<FscLocationNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		Map<String, MatchCodeResultBean> matchCodeMap = dataFluxMatchCodeOption.datafluxMatchCode(sensitivity, inputCnName, inputEnName);
		if(!StringUtils.isEmpty(inputCnName) && matchCodeMap.containsKey(inputCnName)){
			entityNameList.add(matchCodeMap.get(inputCnName).getMatchcodeOrg());
		}
		if(!StringUtils.isEmpty(inputEnName) && matchCodeMap.containsKey(inputEnName)){
			entityNameList.add(matchCodeMap.get(inputEnName).getMatchcodeOrg());
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
	private List<FscBankNcWatchListDim> findBankByFuzzyMatch(String sensitivity, List<String> inputCnName, List<String> inputEnName){
		List<FscBankNcWatchListDim> resultList = new ArrayList<FscBankNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();

		List<String> loopList = inputCnName.size() >= inputEnName.size() ? inputCnName: inputEnName;

		for (int i = 0; i < loopList.size(); i++) {
			String cnName = inputCnName.size() - 1 >= i ? inputCnName.get(i): null;
			String enName = inputEnName.size() - 1 >= i ? inputEnName.get(i): null;
			getFuzzyMatchCodeName(sensitivity, cnName, enName, entityNameList);
		}

		resultList.addAll(iFscBankNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeBankNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
		return resultList;
	}

	private void getFuzzyMatchCodeName(String sensitivity, String inputCnName, String inputEnName, List<String> entityNameList){
		Map<String, MatchCodeResultBean> matchCodeMap = dataFluxMatchCodeOption.datafluxMatchCode(sensitivity, inputCnName, inputEnName);
		if(!StringUtils.isEmpty(inputCnName) && matchCodeMap.containsKey(inputCnName)){
			entityNameList.add(matchCodeMap.get(inputCnName).getMatchcodeOrg());
		}
		if(!StringUtils.isEmpty(inputEnName) && matchCodeMap.containsKey(inputEnName)){
			entityNameList.add(matchCodeMap.get(inputEnName).getMatchcodeOrg());
		}
	}

	private void getFuzzyMatchCodeName(String sensitivity, String inputCnName, String inputEnName, List<String> entityNameList, String entityType){
		Map<String, MatchCodeResultBean> matchCodeMap = dataFluxMatchCodeOption.datafluxMatchCode(sensitivity, inputCnName, inputEnName);

		if(!StringUtils.isEmpty(inputCnName) && matchCodeMap.containsKey(inputCnName)){
			if("02".equals(entityType)){ //僅撈取個人
				entityNameList.add(matchCodeMap.get(inputCnName).getMatchcodeInd());
			}else if("01".equals(entityType) || "09".equals(entityType)){ // 個人與法人
				entityNameList.add(matchCodeMap.get(inputCnName).getMatchcodeInd());
				entityNameList.add(matchCodeMap.get(inputCnName).getMatchcodeOrg());
			}else{ //法人
				entityNameList.add(matchCodeMap.get(inputCnName).getMatchcodeOrg());
			}

		}

		if(!StringUtils.isEmpty(inputEnName) && matchCodeMap.containsKey(inputEnName)){
			//entityNameList.add(matchCodeMap.get(inputEnName));
			if("02".equals(entityType)){ //僅撈取個人
				entityNameList.add(matchCodeMap.get(inputEnName).getMatchcodeInd());
			}else if("01".equals(entityType) || "09".equals(entityType)){ // 個人與法人
				entityNameList.add(matchCodeMap.get(inputEnName).getMatchcodeInd());
				entityNameList.add(matchCodeMap.get(inputEnName).getMatchcodeOrg());
			}else{ //法人
				entityNameList.add(matchCodeMap.get(inputEnName).getMatchcodeOrg());
			}

		}
	}
	
	/**
	 * FuzzyMatch (名單主檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscEntityNcWatchListDim> 
	 */
	private List<FscEntityNcWatchListDim> findByFuzzyMatch(String entityType , String sensitivity, List<String> inputCnName, List<String> inputEnName){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		List<String> loopList = inputCnName.size() >= inputEnName.size() ? inputCnName: inputEnName;
		
		for (int i = 0; i < loopList.size(); i++) {
			String cnName = inputCnName.size() - 1  >= i ? inputCnName.get(i): null;
			String enName = inputEnName.size() - 1  >= i ? inputEnName.get(i): null;
			getFuzzyMatchCodeName(sensitivity, cnName, enName, entityNameList, entityType);
		}
		
		resultList.addAll(iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeEntityNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
		return resultList;
	}
	
	/**
	 * Exact Match(名單國家檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscLocationNcWatchListDim>
	 */
	private List<FscLocationNcWatchListDim> findLocationByExactMatch(List<String> countryList){
		List<FscLocationNcWatchListDim> resultList = new ArrayList<FscLocationNcWatchListDim>();
		int limit = 2000;
		if(countryList.size() > limit){
			int i = 0;
			for(String str : countryList){
				i++;
				List<String> tmpList = new ArrayList<String>();
				tmpList.add(str);
				
				if(i % limit == 0 || i == countryList.size()){
					resultList.addAll(iFscLocationNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, tmpList));
				}
			}
		}else{
			resultList.addAll(iFscLocationNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndCountryNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, countryList));
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
	private List<FscBankNcWatchListDim> findBankByExactMatch(List<String> inputCnName, List<String> inputEnName){
		List<FscBankNcWatchListDim> resultList = new ArrayList<FscBankNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		if(!inputCnName.isEmpty()){
			entityNameList.addAll(inputCnName);
		}
		if(!inputEnName.isEmpty()){
			entityNameList.addAll(inputEnName);
		}
		resultList.addAll(iFscBankNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndBankNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
		return resultList;
	}
	
	/**
	 * Exact Match (名單主檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscEntityNcWatchListDim>
	 */
	private List<FscEntityNcWatchListDim> findByExactMatch(List<String> inputCnName, List<String> inputEnName){
		List<FscEntityNcWatchListDim> resultList = new ArrayList<FscEntityNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		if(!inputCnName.isEmpty()){
			entityNameList.addAll(inputCnName);
		}
		if(!inputEnName.isEmpty()){
			entityNameList.addAll(inputEnName);
		}
		
		resultList.addAll(iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
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
		
		if(StringUtils.isEmpty(englishName) && StringUtils.isEmpty(nonEnglishName) && StringUtils.isEmpty(cccCode) && StringUtils.isEmpty(idNumber) && StringUtils.isEmpty(bicSwiftCode) && StringUtils.isEmpty(country)){
			result = true;
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

}
