package com.sas.amlCheck.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataflux.wsdl.archserver.option.RowIn;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.IFullRefTableTranDao;
import com.sas.db.aml.dao.ecm.IRefTableValueDao;
import com.sas.db.aml.dao.fcfcore.IFscBankNcWatchListDimDao;
import com.sas.db.aml.dao.fcfcore.IFscEntityNcWatchListDimDao;
import com.sas.db.aml.dao.fcfcore.IFscEntityWlXListSettingDao;
import com.sas.db.aml.dao.fcfcore.IFscLocationNcWatchListDimDao;
import com.sas.db.aml.dao.fcfcore.IXScreenProcessSettingDao;
import com.sas.db.aml.dao.fcfcore.IXWatchlistCompressStringDao;
// import com.sas.db.aml.dao.fcfcore.IXWatchlistCompressStringDao;
import com.sas.db.aml.dao.fcfcore.IXWatchlistSettingDao;
import com.sas.db.aml.orm.ecm.FullRefTableTran;
import com.sas.db.aml.orm.ecm.RefTableValue;
import com.sas.db.aml.orm.fcfcore.FscBankNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscEntityNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscLocationNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.XFscEntityWlXListSetting;
import com.sas.db.aml.orm.fcfcore.XScreenProcessSetting;
import com.sas.db.aml.orm.fcfcore.XWatchlistCompressString;
import com.sas.db.aml.orm.fcfcore.XWatchlistSetting;
import com.sas.util.AmlConfiguration;
import com.sas.util.NameCheckUtil;
import com.sas.util.StringUtils;
import com.sas.webservice.nameCheck.bean.NameCheckInputRestBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputRestBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputRestDetailBean;
import com.sas.wlsearch.bean.MatchCodeResultBean;
import com.sas.wlsearch.business.DataFluxMatchCodeOption;
import com.sas.wlsearch.util.WatchListUtil;

/**
 * Online Name Query(線上名單查詢)主程式
 * @author SAS
 *
 */
@Service
public class AmlNameCheckService {
	private static final Logger logger = LoggerFactory.getLogger(AmlNameCheckService.class);
	@Autowired
	IFscEntityNcWatchListDimDao iFscEntityNcWatchListDimDao;
	@Autowired
	IFscEntityWlXListSettingDao iFscEntityWlXListSetDao;
	@Autowired
	DataFluxMatchCodeOption dataFluxMatchCodeOption;
	@Autowired
	IXScreenProcessSettingDao iXScreenProcessSettingDao;
	@Autowired
	IXWatchlistSettingDao iXWatchlistSettingDao;
	@Autowired
	IFullRefTableTranDao iFullRefTableTranDao;
	@Autowired
	IFscLocationNcWatchListDimDao iFscLocationNcWatchListDimDao;
	@Autowired
	IFscBankNcWatchListDimDao iFscBankNcWatchListDimDao;
	@Autowired
	IXWatchlistCompressStringDao iXWatchlistCompressStringDao;
	@Autowired	
	IRefTableValueDao iRefTableValueDao;	
	/**
	 * 線上名單查詢程式
	 * @param nameCheckInputRestBean
	 * @return NameCheckOutputRestBean
	 */
	public NameCheckOutputRestBean onlineNameCheck(NameCheckInputRestBean nameCheckInputRestBean, String locale){
		logger.warn("onlineNameCheck input : " + nameCheckInputRestBean.toString());
		String type_desc = nameCheckInputRestBean.getType_desc();
		Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap= xWatchlistCompressString();
		
		//因前端畫面並無中英文分開欄位
		if (StringUtils.isEnglish(nameCheckInputRestBean.getCh_name()))
			nameCheckInputRestBean.setCh_name("");
		if (!StringUtils.isEnglish(nameCheckInputRestBean.getEn_name()))
			nameCheckInputRestBean.setEn_name("");
		
		Map<String,String> nameMap=standardrizeInput(nameCheckInputRestBean.getCh_name()!=null?nameCheckInputRestBean.getCh_name():"",nameCheckInputRestBean.getEn_name()!=null ?nameCheckInputRestBean.getEn_name():"",type_desc,xWatchlistCompressStringMap);
		Map<String, String> rankOfWatchListMap = getRankOfWatchListMap();
		Map<String, String> rankOfSubWatchListMap = getRankOfSubWatchListMap();
		Map<String, String> subWatchListTypeAndWatchListTypeMappingMap = getSubWatchListTypeAndWatchListTypeMapping();
		String inputCnName =nameMap.get("cn");
		String inputEnName =nameMap.get("en");	
		String inputCnFuzzyName =nameMap.get("cnFuzzy");
		String inputEnFuzzyName =nameMap.get("enFuzzy");
		String ccc_code = nameCheckInputRestBean.getCcc_code();
		String id_input = nameCheckInputRestBean.getId_input();
		String country_cd = nameCheckInputRestBean.getCountry_cd();
		String yearOfBirth = nameCheckInputRestBean.getYearOfBirth();
		String screenProcess = SwiftMtConst.SCREEN_PROCESS_ONLINE_NAME_CHECKING;
		NameCheckOutputRestBean nameCheckOutputRestBean = new NameCheckOutputRestBean();
		XWatchlistSetting xWatchlistSetting = iXWatchlistSettingDao.findByChangeCurrentInd(SwiftMtConst.CHANGE_CURRENT_IND_Y);
		String sensitivity = String.valueOf(xWatchlistSetting.getFuzzyMatchingSenstive().setScale(0, BigDecimal.ROUND_HALF_UP)); //Fuzzy Mathc Sensitivy
		Map<String, XScreenProcessSetting> xScreenProcessSettingMap = getXScreenProcessSettingMap(screenProcess);
		Map<String, FullRefTableTran> fullRefTableTranMap = getFullRefTableTranMap(locale);
		Map<String, FullRefTableTran> subFullRefTableTranMap = getSubFullRefTableTranMap(locale);
		try{
			List<NameCheckOutputRestDetailBean> SASTableData_MERGE = new ArrayList<NameCheckOutputRestDetailBean>();
			Map<Long, NameCheckOutputRestDetailBean> sasTableDataMap = new HashMap<Long, NameCheckOutputRestDetailBean>();
			nameCheckOutputRestBean.setSASTableData_MERGE(SASTableData_MERGE);
			nameCheckOutputRestBean.setInputCnName(nameCheckInputRestBean.getCh_name());
			nameCheckOutputRestBean.setInputEnName(nameCheckInputRestBean.getEn_name());
			nameCheckOutputRestBean.setInputCccCode(ccc_code);
			nameCheckOutputRestBean.setInputId(id_input);
			
			List<String> typeDescList = NameCheckUtil.handelEntityType(type_desc);
			List<String> entityNameList = new ArrayList<String>();
			List<String> entityWatchListNumberList = new ArrayList<String>();
			
			if(!StringUtils.isEmpty(inputCnName)){
				entityNameList.add(inputCnName);
			}
			if(!StringUtils.isEmpty(inputEnName)){
				entityNameList.add(inputEnName);
			}
			if(!StringUtils.isEmpty(ccc_code)){
				entityNameList.add(ccc_code);
			}
			
			if(SwiftMtConst.ENTITY_TYPE_BANK.equals(type_desc)){ //bank
				handleBankNameHitRecordList(sensitivity, inputCnName, inputEnName, sasTableDataMap, entityWatchListNumberList, xWatchlistSetting);
			}else if(SwiftMtConst.ENTITY_TYPE_COUNTRY.equals(type_desc)){ //location
				handleLocationNameHitRecordList(sensitivity, country_cd, sasTableDataMap, entityWatchListNumberList, xWatchlistSetting);
			}else{
				if(SwiftMtConst.ENTITY_TYPE_CORP.equals(type_desc) || SwiftMtConst.ENTITY_TYPE_INDANDCORP.equals(type_desc)) {
					handleBankNameHitRecordList(sensitivity, inputCnName, inputEnName, sasTableDataMap, entityWatchListNumberList, xWatchlistSetting);
				}
				logger.debug(String.format("ch: %s ; en: %s ", inputCnName, inputEnName));
				List<FscEntityNcWatchListDim> exactList = iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndEntityNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList);
				handelHitRecord(sasTableDataMap, entityWatchListNumberList, exactList, SwiftMtConst.NAME_CHECK_EXACT, xWatchlistSetting);

//				if (org.apache.commons.lang3.StringUtils.isNotBlank(id_input)) {
//					List<FscEntityNcWatchListDim> idList = iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndIdentificationId(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, id_input);
//					handelHitRecord(sasTableDataMap, entityWatchListNumberList, idList, SwiftMtConst.NAME_CHECK_EXACT, xWatchlistSetting);				
//				}
				
				logger.debug(String.format("datafluxMatchCode sensitivity %s, inputCnFuzzyName %s, inputEnFuzzyName %s ",sensitivity, inputCnFuzzyName, inputEnFuzzyName));
				Map<String, MatchCodeResultBean> matchCodeMap = dataFluxMatchCodeOption.datafluxMatchCode(sensitivity, inputCnFuzzyName, inputEnFuzzyName);

				List<String> matchCodeList = new ArrayList<String>();	
				if(!StringUtils.isEmpty(inputCnName) && matchCodeMap.containsKey(inputCnName)){
					if(SwiftMtConst.ENTITY_TYPE_IND.equals(type_desc)){ //僅撈取個人
						matchCodeList.add(matchCodeMap.get(inputCnName).getMatchcodeInd());
					}else if(SwiftMtConst.ENTITY_TYPE_INDANDCORP.equals(type_desc)){ // 個人與法人
						matchCodeList.add(matchCodeMap.get(inputCnName).getMatchcodeInd());
						matchCodeList.add(matchCodeMap.get(inputCnName).getMatchcodeOrg());
					}else{ //其它,撈取法人
						matchCodeList.add(matchCodeMap.get(inputCnName).getMatchcodeOrg());
					}
					
				}
				
				if(!StringUtils.isEmpty(inputEnName) && matchCodeMap.containsKey(inputEnName)){
					if(SwiftMtConst.ENTITY_TYPE_IND.equals(type_desc)){ //僅撈取個人
						matchCodeList.add(matchCodeMap.get(inputEnName).getMatchcodeInd());
					}else if(SwiftMtConst.ENTITY_TYPE_INDANDCORP.equals(type_desc)){ //個人與法人
						matchCodeList.add(matchCodeMap.get(inputEnName).getMatchcodeOrg());
						matchCodeList.add(matchCodeMap.get(inputEnName).getMatchcodeInd());
					}else{ //其它,撈取法人
						matchCodeList.add(matchCodeMap.get(inputEnName).getMatchcodeOrg());
					}		
				}	
				
				logger.debug("matchCode : "+Arrays.toString(matchCodeList.toArray()));
				List<FscEntityNcWatchListDim> fuzzyList = iFscEntityNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeEntityNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, matchCodeList);
				logger.debug("fuzzyList : "+ Arrays.toString(fuzzyList.toArray()));
				handelHitRecord(sasTableDataMap, entityWatchListNumberList, fuzzyList, SwiftMtConst.NAME_CHECK_FUZZY, xWatchlistSetting);

				List<String> inclusiveList = WatchListUtil.handelInclusiveList(inputCnName, inputEnName);

				if(inclusiveList.size() > 0){
					String inclusiveSql = iFscEntityNcWatchListDimDao.getInculsiveQuerySql(inclusiveList);
					logger.debug("inclusiveSql : " + inclusiveSql);
					List<FscEntityNcWatchListDim> inculsiveList = iFscEntityNcWatchListDimDao.nativeSql(inclusiveSql);
					handelHitRecord(sasTableDataMap, entityWatchListNumberList, inculsiveList, SwiftMtConst.NAME_CHECK_INCLUSIVE, xWatchlistSetting);				

				}
			}
			
			Map<String, List<XFscEntityWlXListSetting>> setMap = querySetMap(entityWatchListNumberList);
			String url = AmlConfiguration.getString("com.sas.onlineNameCheck.detail.url");
			for (Map.Entry<Long, NameCheckOutputRestDetailBean> entry : sasTableDataMap.entrySet()) {
				NameCheckOutputRestDetailBean detail = entry.getValue();
				if(setMap != null){
					List<XFscEntityWlXListSetting> Allset = setMap.get(detail.getEntity_watch_list_number());									

					if(Allset != null){
						String newWatchListSubTypeCd="";
						String newWatchListTypeCd="";
						String watchListTypeCdNm="";
						String watchListSubTypeCdNm ="";
						String urlString="";
						String[] watchListTypeCdTransArray;
						String[] watchListSubTypeCdTransArray;						
						for(XFscEntityWlXListSetting xFscEntityWlXListSetting:Allset){
							if(xScreenProcessSettingMap.get(xFscEntityWlXListSetting.getWatchListSubTypeCd()) != null){
								newWatchListSubTypeCd = NameCheckUtil.sortRule(newWatchListSubTypeCd,
										xFscEntityWlXListSetting.getWatchListSubTypeCd(),
										rankOfSubWatchListMap,
										SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
								
								urlString = String.format(url, xFscEntityWlXListSetting.getWatchListName(), detail.getEntity_watch_list_number(), detail.getEntity_watch_list_key());
							}
						}
						
						watchListSubTypeCdTransArray=newWatchListSubTypeCd.split(SwiftMtConst.SUB_TYPE_CD_SPLIT);
						newWatchListTypeCd = NameCheckUtil.seriousRule(newWatchListTypeCd,
								watchListSubTypeCdTransArray[0],
								subWatchListTypeAndWatchListTypeMappingMap, rankOfWatchListMap);
						watchListTypeCdTransArray=newWatchListTypeCd.split(SwiftMtConst.SUB_TYPE_CD_SPLIT);
						
						for(String watchListSubTypeCd:watchListSubTypeCdTransArray){
							if(subFullRefTableTranMap.get(watchListSubTypeCd)!=null){
								FullRefTableTran matchedWatchListSubTypeCd=subFullRefTableTranMap.get(watchListSubTypeCd);
								logger.info("watchListSubTypeCd:"+ToStringBuilder.reflectionToString(subFullRefTableTranMap.get(watchListSubTypeCd).getId()));
								if(matchedWatchListSubTypeCd.getValueDesc()!=null){
									String valueDesc=matchedWatchListSubTypeCd.getValueDesc();
									watchListSubTypeCdNm=watchListSubTypeCdNm.concat(SwiftMtConst.SUB_TYPE_CD_SPLIT+valueDesc) ;
								}
							}
						}
							
						for(String watchListTypeCd:watchListTypeCdTransArray){
							if(fullRefTableTranMap.get(watchListTypeCd)!=null){
								FullRefTableTran matchedWatchListTypeCd=fullRefTableTranMap.get(watchListTypeCd);
								logger.info(fullRefTableTranMap.get(watchListTypeCd).toString());	
								if(matchedWatchListTypeCd.getValueDesc()!=null){
										String valueDesc=matchedWatchListTypeCd.getValueDesc();
										watchListTypeCdNm=watchListTypeCdNm.concat(SwiftMtConst.SUB_TYPE_CD_SPLIT+valueDesc) ;
									}
							}
						}
						watchListTypeCdNm = watchListTypeCdNm.length() > 0 ? watchListTypeCdNm.substring(0,1).equals(SwiftMtConst.SUB_TYPE_CD_SPLIT)  ? watchListTypeCdNm.substring(1) : watchListTypeCdNm : watchListTypeCdNm;
						watchListSubTypeCdNm = watchListSubTypeCdNm.length() > 0 ? watchListSubTypeCdNm.substring(0,1).equals(SwiftMtConst.SUB_TYPE_CD_SPLIT) ? watchListSubTypeCdNm.substring(1) : watchListSubTypeCdNm : watchListSubTypeCdNm;
						
						detail.setWatchList_type_cd(newWatchListTypeCd);
						detail.setWatchList_sub_type_cd(newWatchListSubTypeCd);
						detail.setWatchList_type_cd_nm(watchListTypeCdNm);
						detail.setWatchList_sub_type_cd_nm(watchListSubTypeCdNm);
						detail.setUrl(String.format(urlString, Allset.get(0).getWatchListName(), detail.getEntity_watch_list_number(), detail.getEntity_watch_list_key()));
						if(typeDescList != null && typeDescList.size() > 0){
							if(typeDescList.contains(Allset.get(0).getEntityTypeCd())){
								SASTableData_MERGE.add(detail);
							}
						}else{
							SASTableData_MERGE.add(detail);
						}
					}
				}
			}
			logger.debug("SASTableData_MERGE.size : " +SASTableData_MERGE.size());
			calculateScore(SASTableData_MERGE, inputCnName, inputEnName, id_input, country_cd, yearOfBirth, xWatchlistSetting, type_desc);
			for(NameCheckOutputRestDetailBean detail : SASTableData_MERGE){
				logger.debug("detail : " +detail);
			}
			filterDataByScore(SASTableData_MERGE, xScreenProcessSettingMap);
			nameCheckOutputRestBean.setHitCount(String.valueOf(SASTableData_MERGE.size()));
		}catch(Exception e){
			logger.error(String.format("onlineNameCheck fail, exception : %s", e.getMessage()), e);
		}
		logger.warn("onlineNameCheck output : " + nameCheckOutputRestBean.toString());
		return nameCheckOutputRestBean;
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
	/***
	 * 字串處理方法
	 * @param inputString
	 * @return
	 */
	public Map<String, String> standardrizeInput(String nonEnglishName,String englishName,String type_desc,Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap){
		Map<String,String> nameMap=StringUtils.standardrizeInput(type_desc, nonEnglishName,englishName,xWatchlistCompressStringMap);
		return nameMap;
	}		
	/**
	 * FuzzyMatch(名單銀行檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscBankNcWatchListDim>
	 */
	private List<FscBankNcWatchListDim> findBankByFuzzyMatch(String sensitivity, String inputCnName, String inputEnName){
		List<FscBankNcWatchListDim> resultList = new ArrayList<FscBankNcWatchListDim>();
		List<String> entityNameList = new ArrayList<String>();
		Map<String, MatchCodeResultBean> matchCodeMap = dataFluxMatchCodeOption.datafluxMatchCode(sensitivity, inputCnName, inputEnName);
		if(!StringUtils.isEmpty(inputCnName) && matchCodeMap.containsKey(inputCnName)){		
			entityNameList.add(matchCodeMap.get(inputCnName).getMatchcodeOrg());
		}
		if(!StringUtils.isEmpty(inputEnName) && matchCodeMap.containsKey(inputEnName)){
			entityNameList.add(matchCodeMap.get(inputEnName).getMatchcodeOrg());
		}
		
		resultList.addAll(iFscBankNcWatchListDimDao.findByChangeCurrentIndAndDeleteIndAndExcludeIndAndMatchCodeBankNameIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.EXCLUDE_IND_N, entityNameList));
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
	 * Inclusive 方式掃描(名單銀行檔)
	 * @param inputCnName
	 * @param inputEnName
	 * @return List<FscEntityWatchListDim>
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
	 * entity_type = 04(銀行)， 黑名單掃描方法
	 * @param inputCnName
	 * @param inputEnName
	 * @param sasTableDataMap
	 * @param entityWatchListNumberList
	 * @param xWatchlistSetting
	 */
	private void handleBankNameHitRecordList(String sensitivity, String inputCnName, String inputEnName, Map<Long, NameCheckOutputRestDetailBean> sasTableDataMap, List<String> entityWatchListNumberList, XWatchlistSetting xWatchlistSetting){
		List<FscBankNcWatchListDim> exactMatchBankNameList = new ArrayList<FscBankNcWatchListDim>();
		List<FscBankNcWatchListDim> fuzzyMatchBankNameList = new ArrayList<FscBankNcWatchListDim>();
		List<FscBankNcWatchListDim> inclusiveMatchBankNameList = new ArrayList<FscBankNcWatchListDim>();
		List<FscBankNcWatchListDim> bicSwiftCodeList = new ArrayList<FscBankNcWatchListDim>();
		
		if(!StringUtils.isEmpty(inputEnName) || !StringUtils.isEmpty(inputCnName)){
			exactMatchBankNameList = findBankByExactMatch(inputCnName, inputEnName);
			fuzzyMatchBankNameList = findBankByFuzzyMatch(sensitivity, inputCnName, inputEnName);
			inclusiveMatchBankNameList = findBankByInclusiveMatchList(inputCnName, inputEnName);
			bicSwiftCodeList = findByBicSwiftCode(inputEnName);
		}
		
		if(exactMatchBankNameList != null && exactMatchBankNameList.size() > 0){
			handelBankHitRecord(sasTableDataMap, entityWatchListNumberList, exactMatchBankNameList, SwiftMtConst.NAME_CHECK_EXACT, xWatchlistSetting);
		}
		
		if(fuzzyMatchBankNameList != null && fuzzyMatchBankNameList.size() > 0){
			handelBankHitRecord(sasTableDataMap, entityWatchListNumberList, fuzzyMatchBankNameList, SwiftMtConst.NAME_CHECK_FUZZY, xWatchlistSetting);
		}
		
		if(inclusiveMatchBankNameList != null && inclusiveMatchBankNameList.size() > 0){
			handelBankHitRecord(sasTableDataMap, entityWatchListNumberList, inclusiveMatchBankNameList, SwiftMtConst.NAME_CHECK_INCLUSIVE, xWatchlistSetting);
		}
		
		if(bicSwiftCodeList != null && bicSwiftCodeList.size() > 0){
			handelBankHitRecord(sasTableDataMap, entityWatchListNumberList, bicSwiftCodeList, SwiftMtConst.NAME_CHECK_EXACT, xWatchlistSetting);
		}
	}
	
	/**
	 * 命中名單分數加權計算(寫入ExactMatch, FuzzyMatch, InclusiveMatch分數
	 * )
	 * @param sasTableDataMap
	 * @param entityWatchListNumberList
	 * @param fscEntityWatchListDimList
	 * @param queryType
	 * @param xWatchlistSetting
	 */
	private void handelBankHitRecord(
			Map<Long, NameCheckOutputRestDetailBean> sasTableDataMap, List<String> entityWatchListNumberList, List<FscBankNcWatchListDim> bankNnameHitRecordList, 
			String queryType, XWatchlistSetting xWatchlistSetting){
		if(bankNnameHitRecordList != null && bankNnameHitRecordList.size() > 0){
			for(FscBankNcWatchListDim dim : bankNnameHitRecordList){
				long entity_watch_list_key = dim.getBankWatchListKey();
				String entity_watch_list_number = dim.getBankWatchListNumber();
				NameCheckOutputRestDetailBean nameCheckOutputRestDetail = null;
				if(sasTableDataMap.get(entity_watch_list_key) == null){
					nameCheckOutputRestDetail = new NameCheckOutputRestDetailBean(dim);
				}else{
					nameCheckOutputRestDetail = sasTableDataMap.get(entity_watch_list_key);
				}
				
				if(SwiftMtConst.NAME_CHECK_EXACT.equalsIgnoreCase(queryType)){
					nameCheckOutputRestDetail.setExactScore(xWatchlistSetting.getExactMatchingScore().intValue());
					nameCheckOutputRestDetail.setExact(SwiftMtConst.NAME_CHECK_Y);
				}else if(SwiftMtConst.NAME_CHECK_FUZZY.equalsIgnoreCase(queryType)){
					nameCheckOutputRestDetail.setFluxScore(xWatchlistSetting.getFuzzyMatchingScore().intValue());
					nameCheckOutputRestDetail.setFlux(SwiftMtConst.NAME_CHECK_Y);
				}else if(SwiftMtConst.NAME_CHECK_INCLUSIVE.equalsIgnoreCase(queryType)){
					nameCheckOutputRestDetail.setInclusiveScore(xWatchlistSetting.getInclusiveMatchingScore().intValue());
					nameCheckOutputRestDetail.setInclusive(SwiftMtConst.NAME_CHECK_Y);
				}
				entityWatchListNumberList.add(entity_watch_list_number);
				sasTableDataMap.put(entity_watch_list_key, nameCheckOutputRestDetail);
			}
		}
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
			resultList.add(entry.getValue().getMatchcodeInd());
			resultList.add(entry.getValue().getMatchcodeOrg());
		}
		
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
	 * entity_type = 08(國家)， 黑名單掃描方法
	 * @param country
	 * @param sasTableDataMap
	 * @param entityWatchListNumberList
	 * @param xWatchlistSetting
	 */
	private void handleLocationNameHitRecordList(String sensitivity, String country, Map<Long, NameCheckOutputRestDetailBean> sasTableDataMap, List<String> entityWatchListNumberList, XWatchlistSetting xWatchlistSetting){
		List<FscLocationNcWatchListDim> exactMatchLocationNameList = new ArrayList<FscLocationNcWatchListDim>();
		List<FscLocationNcWatchListDim> fuzzyMatchLocationNameList = new ArrayList<FscLocationNcWatchListDim>();
		List<FscLocationNcWatchListDim> exactMatchContryCodeNameList = new ArrayList<FscLocationNcWatchListDim>();
		Map<String, List<XWatchlistCompressString>> mapXWatchlistCompressStringMap = xWatchlistCompressString();
		List<XWatchlistCompressString> xWatchlistSIGCompressStringList = mapXWatchlistCompressStringMap.get(SwiftMtConst.COMPRESSSTRING_SIG);
		List<XWatchlistCompressString> xWatchlistCORPCompressStringList = mapXWatchlistCompressStringMap.get(SwiftMtConst.COMPRESSSTRING_CORP);
		country = StringUtils.DoSignCompressString(country, xWatchlistSIGCompressStringList);
		country = StringUtils.DoCorpCompressString(country, xWatchlistCORPCompressStringList);
		//step.6移除前後空白
		if(country != null){
			country = org.apache.commons.lang3.StringUtils.stripEnd(country, " ");
			country = org.apache.commons.lang3.StringUtils.stripStart(country, " ");
		}
		
		List<String> locationStrList = NameCheckUtil.getShiftInMaxArrangementInLimitList(country);
		
		if(locationStrList != null && locationStrList.size() >0){
			exactMatchLocationNameList = findLocationByExactMatch(locationStrList);
			fuzzyMatchLocationNameList = findLocationByFuzzyMatch(getFuzzyFreeFormatTextMatchCode(sensitivity, locationStrList));
			exactMatchContryCodeNameList = findCountryCodeByExactMatch(country);
		}
		
		if(exactMatchLocationNameList != null && exactMatchLocationNameList.size() > 0){
			handelLocationHitRecord(sasTableDataMap, entityWatchListNumberList, exactMatchLocationNameList, SwiftMtConst.NAME_CHECK_EXACT, xWatchlistSetting);
		}
		
		if(fuzzyMatchLocationNameList != null && fuzzyMatchLocationNameList.size() > 0){
			handelLocationHitRecord(sasTableDataMap, entityWatchListNumberList, fuzzyMatchLocationNameList, SwiftMtConst.NAME_CHECK_FUZZY, xWatchlistSetting);
		}
		
		if(exactMatchContryCodeNameList != null && exactMatchContryCodeNameList.size() > 0){
			handelLocationHitRecord(sasTableDataMap, entityWatchListNumberList, exactMatchContryCodeNameList, SwiftMtConst.NAME_CHECK_EXACT, xWatchlistSetting);
		}
	}
	
	/**
	 * 命中名單分數加權計算(寫入ExactMatch, FuzzyMatch, InclusiveMatch分數
	 * )
	 * @param sasTableDataMap
	 * @param entityWatchListNumberList
	 * @param fscEntityWatchListDimList
	 * @param queryType
	 * @param xWatchlistSetting
	 */
	private void handelLocationHitRecord(
			Map<Long, NameCheckOutputRestDetailBean> sasTableDataMap, List<String> entityWatchListNumberList, List<FscLocationNcWatchListDim> locationameHitRecordList, 
			String queryType, XWatchlistSetting xWatchlistSetting){
		if(locationameHitRecordList != null && locationameHitRecordList.size() > 0){
			for(FscLocationNcWatchListDim dim : locationameHitRecordList){
				long entity_watch_list_key = dim.getLocationWatchListKey();
				String entity_watch_list_number = dim.getLocationWatchListNumber();
				NameCheckOutputRestDetailBean nameCheckOutputRestDetail = null;
				if(sasTableDataMap.get(entity_watch_list_key) == null){
					nameCheckOutputRestDetail = new NameCheckOutputRestDetailBean(dim);
					logger.info("nameCheckOutputRestDetail.getCitizenship_country_code()" + nameCheckOutputRestDetail.getCitizenship_country_code() + "nameCheckOutputRestDetail.getCitizenship_country_code()"+ nameCheckOutputRestDetail.getCitizenship_country_code()  + "dim.getCountryCode()" +dim.getCountryCode());
				}else{
					nameCheckOutputRestDetail = sasTableDataMap.get(entity_watch_list_key);
				}
				
				if(SwiftMtConst.NAME_CHECK_EXACT.equalsIgnoreCase(queryType)){
					nameCheckOutputRestDetail.setExactScore(xWatchlistSetting.getExactMatchingScore().intValue());
					nameCheckOutputRestDetail.setExact(SwiftMtConst.NAME_CHECK_Y);
				}else if(SwiftMtConst.NAME_CHECK_FUZZY.equalsIgnoreCase(queryType)){
					nameCheckOutputRestDetail.setFluxScore(xWatchlistSetting.getFuzzyMatchingScore().intValue());
					nameCheckOutputRestDetail.setFlux(SwiftMtConst.NAME_CHECK_Y);
				}else if(SwiftMtConst.NAME_CHECK_INCLUSIVE.equalsIgnoreCase(queryType)){
					nameCheckOutputRestDetail.setInclusiveScore(xWatchlistSetting.getInclusiveMatchingScore().intValue());
					nameCheckOutputRestDetail.setInclusive(SwiftMtConst.NAME_CHECK_Y);
				}
				entityWatchListNumberList.add(entity_watch_list_number);
				sasTableDataMap.put(entity_watch_list_key, nameCheckOutputRestDetail);
			}
		}
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
	
	private Map<String, FullRefTableTran> getFullRefTableTranMap(String locale){
		logger.info("locale:::::: "+ locale);
		Map<String, FullRefTableTran> fullRefTableTranMap = new HashMap<String, FullRefTableTran>();
		List<FullRefTableTran> refWatchListTypeCdList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
		for(FullRefTableTran refWatchListTypeCd : refWatchListTypeCdList){
			fullRefTableTranMap.put(refWatchListTypeCd.getId().getValueCd(), refWatchListTypeCd);
		}
		
		
		return fullRefTableTranMap;	
	}
	
	private Map<String, FullRefTableTran> getSubFullRefTableTranMap(String locale){
		logger.info("locale:::::: "+ locale);
		Map<String, FullRefTableTran> fullRefTableTranMap = new HashMap<String, FullRefTableTran>();
		List<FullRefTableTran> refWatchListTypeCdList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
		for(FullRefTableTran refWatchListTypeCd : refWatchListTypeCdList){
			fullRefTableTranMap.put(refWatchListTypeCd.getId().getValueCd(), refWatchListTypeCd);
		}
		return fullRefTableTranMap;	
	}
		
	/**
	 * 1.過濾非Online Name Query查詢的黑名單  2.過濾掉分數未達標準的黑名單
	 * @param SASTableData_MERGE
	 * @param xScreenProcessSettingMap
	 * @return List<NameCheckOutputRestDetailBean>
	 */
	private void filterDataByScore(List<NameCheckOutputRestDetailBean> SASTableData_MERGE, Map<String, XScreenProcessSetting> xScreenProcessSettingMap){
		//jerry 2018.02.09 修改一人有多個分類問題
		Iterator<NameCheckOutputRestDetailBean> iterator = SASTableData_MERGE.iterator();
		while(iterator.hasNext()){		
			NameCheckOutputRestDetailBean detail = iterator.next();
			XScreenProcessSetting xScreenProcessSetting=null;
			String[] detailStringArray=detail.getWatchList_sub_type_cd().split(",");
			for(String detailString:detailStringArray){
				xScreenProcessSetting = xScreenProcessSettingMap.get(detailString);
				if(xScreenProcessSetting == null){
					iterator.remove();
				}
			}
		
			if(xScreenProcessSetting != null && detail.getTotal_score() < xScreenProcessSetting.getScore().floatValue()){
				iterator.remove();
			}
		}
	}
	
	/**
	 * 1.撈取X_SCREEN_PROCESS_SETTING(Screen_process對應名單類型) 2.轉換成Map<String, XScreenProcessSetting>型態
	 * @param screenProcess
	 * @return Map<String, XScreenProcessSetting>
	 */
	private Map<String, XScreenProcessSetting> getXScreenProcessSettingMap(String screenProcess){
		Map<String, XScreenProcessSetting> XScreenProcessSettingMap =  new HashMap<String, XScreenProcessSetting>();
		List<XScreenProcessSetting> xScreenProcessSettingList = iXScreenProcessSettingDao.findByChangeCurrentIndAndDeleteIndAndIdScreenProcessTypeCd(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, screenProcess);
		for(XScreenProcessSetting xScreenProcessSetting : xScreenProcessSettingList){
			String watchlistSubTypeCd = xScreenProcessSetting.getId().getWatchListSubTypeCd();
			if(!XScreenProcessSettingMap.containsKey(watchlistSubTypeCd)){
				XScreenProcessSettingMap.put(watchlistSubTypeCd, xScreenProcessSetting);
			}
		}
		
		return XScreenProcessSettingMap;
	}
	
	/**
	 * 1.撈取 X_FSC_ENTITY_WL_X_LIST_SETTING(黑名單entity_watch_list_number 對應到名單分類方法) 2.轉換成 Map<String, XFscEntityWlXListSetting> 型態
	 * @param entityWatchListNumberList
	 * @return Map<String, XFscEntityWlXListSetting>
	 */
	private Map<String, List<XFscEntityWlXListSetting>> querySetMap(List<String> entityWatchListNumberList){
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
		    	 List<XFscEntityWlXListSetting> setList = iFscEntityWlXListSetDao.findByIdChangeCurrentIndAndEntityWatchListNumberIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, tmpList);
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
		    	 tmpList = new ArrayList<String>();
		     }
		    }
		   }else{
		    List<XFscEntityWlXListSetting> setList = iFscEntityWlXListSetDao.findByIdChangeCurrentIndAndEntityWatchListNumberIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, entityWatchListNumberList);
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
		
		  return setMap;
		 }
	/**
	 * 命中名單分數加權計算(寫入ExactMatch, FuzzyMatch, InclusiveMatch分數
	 * )
	 * @param sasTableDataMap
	 * @param entityWatchListNumberList
	 * @param fscEntityWatchListDimList
	 * @param queryType
	 * @param xWatchlistSetting
	 */
	private void handelHitRecord(
			Map<Long, NameCheckOutputRestDetailBean> sasTableDataMap, List<String> entityWatchListNumberList, List<FscEntityNcWatchListDim> fscEntityNcWatchListDimList, 
			String queryType, XWatchlistSetting xWatchlistSetting){
		if(fscEntityNcWatchListDimList != null && fscEntityNcWatchListDimList.size() > 0){
			for(FscEntityNcWatchListDim dim : fscEntityNcWatchListDimList){
				long entity_watch_list_key = dim.getEntityWatchListKey();
				String entity_watch_list_number = dim.getEntityWatchListNumber();
				NameCheckOutputRestDetailBean nameCheckOutputRestDetail = null;
				if(sasTableDataMap.get(entity_watch_list_key) == null){
					nameCheckOutputRestDetail = new NameCheckOutputRestDetailBean(dim);
				}else{
					nameCheckOutputRestDetail = sasTableDataMap.get(entity_watch_list_key);
				}
				
				if(SwiftMtConst.NAME_CHECK_EXACT.equalsIgnoreCase(queryType)){
					nameCheckOutputRestDetail.setExactScore(xWatchlistSetting.getExactMatchingScore().intValue());
					nameCheckOutputRestDetail.setExact(SwiftMtConst.NAME_CHECK_Y);
				}else if(SwiftMtConst.NAME_CHECK_FUZZY.equalsIgnoreCase(queryType)){
					nameCheckOutputRestDetail.setFluxScore(xWatchlistSetting.getFuzzyMatchingScore().intValue());
					nameCheckOutputRestDetail.setFlux(SwiftMtConst.NAME_CHECK_Y);
				}else if(SwiftMtConst.NAME_CHECK_INCLUSIVE.equalsIgnoreCase(queryType)){
					nameCheckOutputRestDetail.setInclusiveScore(xWatchlistSetting.getInclusiveMatchingScore().intValue());
					nameCheckOutputRestDetail.setInclusive(SwiftMtConst.NAME_CHECK_Y);
				}
				entityWatchListNumberList.add(entity_watch_list_number);
				sasTableDataMap.put(entity_watch_list_key, nameCheckOutputRestDetail);
			}
		}
	}
	
	/**
	 * 計算命中名單分數，算出最後Total分數
	 * @param SASTableData_MERGE
	 * @param inputCnName
	 * @param inputEnName
	 * @param identification
	 * @param country
	 * @param yearOfBirth
	 * @param xWatchlistSetting
	 */
	private void calculateScore(List<NameCheckOutputRestDetailBean> SASTableData_MERGE, String inputCnName, String inputEnName, String identification, String country, String yearOfBirth, XWatchlistSetting xWatchlistSetting, String entityType) {
		for(NameCheckOutputRestDetailBean detail : SASTableData_MERGE){
			if(SwiftMtConst.NAME_CHECK_Y.equalsIgnoreCase(detail.getInclusive())){
				int DLParameter = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_DAMERAU_LEVENSHTEIN_PARAMETER));
				float hitWordRatio = NameCheckUtil.calculateHitWordRatio(detail.getEntity_name(), inputCnName, inputEnName, DLParameter, entityType); //加入差異度比較
				float inclusiveMatchScore = NameCheckUtil.calculatInclusiveMatchScore(hitWordRatio, xWatchlistSetting);
				detail.setInclusiveScore(inclusiveMatchScore);
			}
			BigDecimal targetYearOfBirth = (!"NULL".equalsIgnoreCase(detail.getYear_of_birth()) && detail.getYear_of_birth() != null) ? new BigDecimal(detail.getYear_of_birth()) : null;
			if(!SwiftMtConst.ENTITY_TYPE_COUNTRY.equals(entityType)){
				detail.setWeightScore(
						NameCheckUtil.calculateWeightScore(
								detail.getIdentification_id(), detail.getCitizenship_country_code(), targetYearOfBirth, 
								identification, country, yearOfBirth, xWatchlistSetting, detail.getCitizenship_country_name()));
			}
			
			float finalScore = 0;
		    finalScore = Math.max(detail.getExactScore() + detail.getWeightScore(), detail.getFluxScore() + detail.getWeightScore());
		    finalScore = Math.max(finalScore, detail.getInclusiveScore() + detail.getWeightScore());
		    finalScore = Math.min(finalScore, 100);
		    detail.setTotal_score(finalScore);
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
	 * 取得所有Account Mail 的 BranchNumber
	 **/
//	public List<String> getAllMailBranchNumberList(){
//		List<String> branchNumbers = iAccountSendMailDao.findDistinctDept();
//		return branchNumbers;
//	}
	
	/**
	 * 取得BranchNumber 的所有Account Mail
	 **/
//	public List<AccountSendMail> getMailsUsingBranchNumber(String  branchNumber){
//		List<AccountSendMail> accountSendMails = iAccountSendMailDao.findByDeptNo(branchNumber);
//		return accountSendMails;
//	}
//	
//	public  List<AccountSendMail> updateAllCfgByBranchNum(List<AccountSendMail> accountSendMails) {
//		 List<AccountSendMail> newEntities = iAccountSendMailDao.save(accountSendMails);
//		 if(newEntities!=null && !newEntities.isEmpty()) {
//			 iAccountSendMailDao.flush();
//			 return newEntities;
//		 }
//		return newEntities;
//	}
}
