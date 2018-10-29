package com.sas.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.fcfcore.XWatchlistCompressString;
import com.sas.db.aml.orm.fcfcore.XWatchlistSetting;
import com.sas.db.wlf.orm.nc.NameHitRecord;
import com.sas.wlsearch.util.WatchListUtil;

public class NameCheckUtil {
	private static final Logger logger = LoggerFactory.getLogger(NameCheckUtil.class);
	
	public static boolean shortStr(String str){
		boolean result = false;
		int shortStrNumber = Integer.parseInt(AmlConfiguration.getString("com.sas.swiftCheck.shortStrNumber"));
		if(str != null && str.length() > 0){
			result = str.replaceAll(" ", "").length() <= shortStrNumber;
		}
		return result;
	}
	
	public static boolean shortTokenStr(String str){
		boolean result = false;
		int shortTokenNumber = Integer.parseInt(AmlConfiguration.getString("com.sas.swiftCheck.shortTokenNumber"));
		if(str != null && str.length() > 0){
			String strArray[] = str.split(" ");
			result = strArray.length <= shortTokenNumber;
		}
		return result;
	}
	/**
	 * 電文掃描，欄位資訊拆解演算法參數設定
	 * @param sourceMap
	 * @param destinationMap
	 */
	public static void setToDestinationMap(Map<String, String> sourceMap, Map<String, String> destinationMap, Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap){
		for (Map.Entry<String, String> entry : sourceMap.entrySet()) {
			String key = entry.getKey();
			String value = StringUtils.lianxuStr(StringUtils.convertToHalfWidth(entry.getValue())).toUpperCase();//WatchListUtil.convertWordToSpace(entry.getValue(), SwiftMtConst.COVERTWORD);
			String indFieldValue = StringUtils.CompressStringByEntityType(SwiftMtConst.ENTITY_TYPE_IND, value, null, xWatchlistCompressStringMap).get("en");
    		String orgFieldValue = StringUtils.CompressStringByEntityType(SwiftMtConst.ENTITY_TYPE_CORP, value, null, xWatchlistCompressStringMap).get("en");
//    		String sigFieldValue = StringUtils.CompressStringByEntityType(null, value, null, xWatchlistCompressStringMap).get("en");
			
    		if(indFieldValue != null && indFieldValue.length() > 0){
				setToDestinationMap(key, indFieldValue, destinationMap);
			}
    		if(orgFieldValue != null && orgFieldValue.length() > 0){
				setToDestinationMap(key, orgFieldValue, destinationMap);
			}
//    		if(sigFieldValue != null && sigFieldValue.length() > 0){
//				setToDestinationMap(key, sigFieldValue, destinationMap);
//			}
    		
    		setToDestinationMap(key, value, destinationMap);
		}
	}
	
	private static void setToDestinationMap(String key, String value, Map<String, String> destinationMap){
		int limitIndex = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_LIMITINDEX));
		int maxIndex = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_MAXINDEX));
		int fieldMaxLength = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_FIELDMAXLENGTH));
		
		if(value != null){
			if(value.contains(" ")){
				for(String str : WatchListUtil.getShiftInMaxArrangementInLimitList(value.split(" "), maxIndex, limitIndex)){
					if(str != null && str.length() > 0 && str.length() <= fieldMaxLength){
						destinationMap.put(str, key);
						destinationMap.put(StringUtils.sortString(str), key);
						//compress all space to a word
						destinationMap.put(str.replace(" ", ""), key);
					}
				}
			}else{
				if(value.length() <= fieldMaxLength){
					destinationMap.put(value, key);
				}
			}
		}
	}
	
	/**
	 * 電文掃描，欄位資訊拆解演算法參數設定
	 * @param sourceMap
	 * @param destinationMap
	 */
	public static void setToDestinationMap(Map<String, String> sourceMap, Map<String, String> destinationMap){
		for (Map.Entry<String, String> entry : sourceMap.entrySet()) {
			String key = entry.getKey();
			String value = WatchListUtil.convertWordToSpace(entry.getValue(), SwiftMtConst.COVERTWORD);
			int limitIndex = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_LIMITINDEX));
			int maxIndex = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_MAXINDEX));
			int fieldMaxLength = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_FIELDMAXLENGTH));
			
			if(value != null){
				if(value.contains(" ")){
					for(String str : WatchListUtil.getShiftInMaxArrangementInLimitList(value.split(" "), maxIndex, limitIndex)){
						if(str != null && str.length() > 0 && str.length() < fieldMaxLength){
							destinationMap.put(str, key);
						}
					}
				}else{
					if(value.length() < fieldMaxLength){
						destinationMap.put(value, key);
					}
				}
			}
		}
	}
	
	/**
	 * 字串拆解處理
	 * @param inputStr 輸入字串
	 * @return 
	 */
	public static List<String> getShiftInMaxArrangementInLimitList(String inputStr){
		List<String> resultList = new ArrayList<String>();
		//String value = WatchListUtil.convertWordToSpace(inputStr, SwiftMtConst.COVERTWORD);
		String value = inputStr;
		int limitIndex = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_LIMITINDEX));
		int maxIndex = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_MAXINDEX));
		int fieldMaxLength = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_FIELDMAXLENGTH));
		
		if(value != null){
			if(value.contains(" ")){
				for(String str : WatchListUtil.getShiftInMaxArrangementInLimitList(value.split(" "), maxIndex, limitIndex)){
					if(str != null && str.length() > 0 && str.length() < fieldMaxLength){
						resultList.add(str);
					}
				}
			}else{
				if(value.length() < fieldMaxLength){
					resultList.add(value);
				}
			}
		}
		
		return resultList;
	}

	/**
	 * MEGA員工代碼補齊，前面帶0
	 * @param callingUser
	 * @return
	 */
	public static String alignmentEmpNo(String callingUser){
		String result = callingUser;
		
		if(result != null && result.length() < 6){
			while(result.length() < 6){
				result = alignmentEmpNo(String.format("0%s", result));
			}
		}
		
		return result;
	}
	/**
	 * 判斷使用的介面是哪一種型態
	 * @param interfaceType
	 * @param screenProcess
	 * @return
	 */
	public static String getSourceType(String interfaceType, String screenProcess){
		String result = "";
		if(SwiftMtConst.INTERFACE_TYPE_SWIFTCHECK.equalsIgnoreCase(interfaceType)){
			result = SwiftMtConst.SOURCE_TYPE_RT_SC;
		}else if(SwiftMtConst.INTERFACE_TYPE_NAMECHECK.equalsIgnoreCase(interfaceType)){
			if(SwiftMtConst.SCREEN_PROCESS_Account_Opening.equals(screenProcess) || SwiftMtConst.SCREEN_PROCESS_Customer_Event.equals(screenProcess)){
				result = SwiftMtConst.SOURCE_TYPE_RT_NC;
			}else if(SwiftMtConst.SCREEN_PROCESS_Transaction_Screening.equals(screenProcess) || SwiftMtConst.SCREEN_PROCESS_Trade_Finance_Screening.equals(screenProcess)){
				result = SwiftMtConst.SOURCE_TYPE_RT_TC;
			}else if(SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_BL_SCREENING.equals(screenProcess)){
				result = SwiftMtConst.SOURCE_TYPE_OL_BOL;
			}else if(SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_INV_SCREENING.equals(screenProcess)){
				result = SwiftMtConst.SOURCE_TYPE_OL_INV;
			}
		}else if(SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK.equalsIgnoreCase(interfaceType)){
			result = SwiftMtConst.SOURCE_TYPE_BT_NC;
		}else if(SwiftMtConst.INTERFACE_TYPE_BATCHSWIFTCHECK.equalsIgnoreCase(interfaceType)){
			result = SwiftMtConst.SOURCE_TYPE_BT_SC;
		}else if(SwiftMtConst.INTERFACE_TYPE_BATCHTRANSACTIONSCREENING.equalsIgnoreCase(interfaceType)){
			result = SwiftMtConst.SOURCE_TYPE_BT_TC;
		}
		return result;
	}

	/**
	 * 針對名單分類做排序顯示，並替除相同分類
	 * @param oldRule
	 * @param newRule
	 * @return
	 */
	public static  String sortRule(String oldRule, String newRule){
		String result = "";
		String newRuleList[] = newRule.split(",");
		
		for(String str : newRuleList){
			if(str.length() > 0 && oldRule.indexOf(str) < 0){
				if(oldRule.length() > 0){
					oldRule = String.format("%s,%s", oldRule, str);
				}else{
					oldRule = str;
				}
			}
		}
		
		List<String> resultlist = new ArrayList<String>();
		String oldRuleList[] = oldRule.split(",");
		for(String str : oldRuleList){
			resultlist.add(str);
		}
		
		Collections.sort(resultlist,
		        new Comparator<String>() {
		            public int compare(String o1, String o2) {
		                return o1.compareTo(o2);
		            }
		        });
		if(resultlist.size()<=20){
			for(String str : resultlist){
//				result += ";"+str;
				result += SwiftMtConst.SUB_TYPE_CD_SPLIT+str;				
			}
		}else{
			for(int i=0,max=20;i<max;i++){
				String str=resultlist.get(i);
//				result += ";"+str;
				result += SwiftMtConst.SUB_TYPE_CD_SPLIT+str;
			}
		}
		result = result.length() > 0 ? result.substring(1) : result;
		return result;
	}
	/**
	 * 區別新舊名單的嚴重程度
	 * @param oldRule
	 * @param newRule
	 * @param rankOfWatchListMap
	 * @return
	 */
	public static  String judgeRule(String oldRule, String newRule, Map<String, String> rankOfWatchListMap){
		String rule = oldRule;
		if(oldRule != null && oldRule.length() > 0){
			if(newRule != null && newRule.length() > 0){
				if(newRule.indexOf(",") > 0){
					String newRuleList[] = newRule.split(",");
					newRule = newRuleList[0];
				}
				
				String oldSettingVal = oldRule != null && !"".equals(oldRule) ? rankOfWatchListMap.get(oldRule) : "";
				String newSettingVal = newRule != null && !"".equals(newRule) ? rankOfWatchListMap.get(newRule) : "";
				int oldValue = "".equals(oldSettingVal) ? 99 : Integer.parseInt(oldSettingVal);
				int newValue = "".equals(newSettingVal) ? 99 : Integer.parseInt(newSettingVal);
				if(oldValue > newValue){
					rule = newRule;
				}
			}
		}else{
			if(newRule != null && newRule.length() > 0){
				rule = newRule;
			}
		}
		
		return rule;
	}
	/**
	 * 比較新舊名單的嚴重程度
	 * @param oldRule
	 * @param newRule
	 * @param rankOfWatchListMap
	 * @return
	 */
	private static String compareRule(String oldRule, String newRule, Map<String, String> rankOfWatchListMap){
		String rule = oldRule;
		String oldSettingVal = oldRule != null && !"".equals(oldRule) ? rankOfWatchListMap.get(oldRule) : "";
		String newSettingVal = newRule != null && !"".equals(newRule) ? rankOfWatchListMap.get(newRule) : "";
		int oldValue = "".equals(oldSettingVal) ? 99 : Integer.parseInt(oldSettingVal);
		int newValue = "".equals(newSettingVal) ? 99 : Integer.parseInt(newSettingVal);
		if(oldValue > newValue){
			rule = newRule;
		}
		return rule;
	}
	
	/**
	 * 根據名單小類取得最嚴重的名單大類
	 * @param oldRule
	 * @param newRule -- 名單小類(WatchListSubTypeCd) 
	 * @param subWatchListTypeAndWatchListTypeMappingMap -- 名單大小類相依Map
	 * @param rankOfWatchListMap -- 名單大類Map
	 * @return
	 */
	public static String seriousRule(String oldRule, String newRule,
			Map<String, String> subWatchListTypeAndWatchListTypeMappingMap, Map<String, String> rankOfWatchListMap) {
		String rule = oldRule;
		if (oldRule != null && oldRule.length() > 0) {
			String seriousOldRule = "";
			if (oldRule.indexOf(",") > 0) {
				String oldRuleList[] = newRule.split(",");
				for (String tmpOldRule : oldRuleList) {
					tmpOldRule = subWatchListTypeAndWatchListTypeMappingMap.get(tmpOldRule) != null
							? subWatchListTypeAndWatchListTypeMappingMap.get(tmpOldRule)
							: "";
					seriousOldRule = compareRule(seriousOldRule, tmpOldRule, rankOfWatchListMap);
				}
			} else {
				oldRule = subWatchListTypeAndWatchListTypeMappingMap.get(oldRule) != null
						? subWatchListTypeAndWatchListTypeMappingMap.get(oldRule)
						: "";
				seriousOldRule = oldRule;
			}

			String seriousNewRule = "";
			if (newRule != null && newRule.length() > 0) {
				if (newRule.indexOf(",") > 0) {
					String newRuleList[] = newRule.split(",");
					for (String tmpNewRule : newRuleList) {
						tmpNewRule = subWatchListTypeAndWatchListTypeMappingMap.get(tmpNewRule) != null
								? subWatchListTypeAndWatchListTypeMappingMap.get(tmpNewRule)
								: "";
						seriousNewRule = compareRule(seriousNewRule, tmpNewRule, rankOfWatchListMap);
					}
				} else {
					newRule = subWatchListTypeAndWatchListTypeMappingMap.get(newRule) != null
							? subWatchListTypeAndWatchListTypeMappingMap.get(newRule)
							: "";
					seriousNewRule = newRule;
				}
			} else {
				newRule = subWatchListTypeAndWatchListTypeMappingMap.get(newRule) != null
						? subWatchListTypeAndWatchListTypeMappingMap.get(newRule)
						: "";
				seriousNewRule = newRule;
			}

			rule = compareRule(seriousOldRule, seriousNewRule, rankOfWatchListMap);
		} else {
			if (newRule != null && newRule.length() > 0) {
				String seriousNewRule = "";
				if (newRule.indexOf(",") > 0) {
					String newRuleList[] = newRule.split(",");
					for (String tmpNewRule : newRuleList) {
						tmpNewRule = subWatchListTypeAndWatchListTypeMappingMap.get(tmpNewRule) != null
								? subWatchListTypeAndWatchListTypeMappingMap.get(tmpNewRule)
								: "";
						seriousNewRule = compareRule(seriousNewRule, tmpNewRule, rankOfWatchListMap);
					}
				} else {
					newRule = subWatchListTypeAndWatchListTypeMappingMap.get(newRule) != null
							? subWatchListTypeAndWatchListTypeMappingMap.get(newRule)
							: "";
					seriousNewRule = newRule;
				}
				rule = seriousNewRule;
			}
		}
		return rule;
	}
	
	/**
	 * 計算加權分數
	 * @param nameHitRecord
	 * @param inputCnName
	 * @param inputEnName
	 * @param identification
	 * @param country
	 * @param yearOfBirth
	 * @param xWatchlistSetting
	 */
	public static void calculateScore(NameHitRecord nameHitRecord,
			String inputCnName, String inputEnName, String identification, String country, String yearOfBirth, 
			XWatchlistSetting xWatchlistSetting, String entityType) {
		int inclusiveMatchScore = 0;
		if(nameHitRecord.getInclusiveMatchScore() > 0){
			//float hitWordRatio = calculateHitWordRatio(nameHitRecord.getEntityName(), inputCnName, inputEnName);
//			inclusiveMatchScore = Math.round((xWatchlistSetting.getInclusiveMatchingScore().intValue() * (hitWordRatio >= 0.5 ? 1 : 0)  + ((xWatchlistSetting.getExactMatchingScore().intValue() - xWatchlistSetting.getInclusiveMatchingScore().intValue()) * hitWordRatio)));

			int DLParameter = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_DAMERAU_LEVENSHTEIN_PARAMETER));
//			float hitWordRatio = calculateHitWordRatio( nameHitRecord.getEntityName(), inputCnName, inputEnName, DLParameter); //加入差異度比較
			float hitWordRatio = calculateHitWordRatio( nameHitRecord.getEntityName(), inputCnName, inputEnName, DLParameter, entityType); //加入差異度比較
			inclusiveMatchScore = Math.round(NameCheckUtil.calculatInclusiveMatchScore(hitWordRatio, xWatchlistSetting));

		}
		
		int weightScore = 0;
		if(!SwiftMtConst.ENTITY_TYPE_COUNTRY.equals(entityType)){
			weightScore = calculateWeightScore(
					nameHitRecord.getIdentificationId(), nameHitRecord.getCitizenshipCountryCode(), nameHitRecord.getYearOfBirth(), 
					identification, country, yearOfBirth, xWatchlistSetting, nameHitRecord.getCitizenshipCountryName());
		}
		
		int finalScore = 0;
	    finalScore = Math.max(nameHitRecord.getExactMatchScore() + weightScore, nameHitRecord.getFuzzyMatchScore() + weightScore);
	    finalScore = Math.max(finalScore, inclusiveMatchScore + weightScore);
	    finalScore = Math.min(finalScore, 100);
	    
	    nameHitRecord.setMixScore(finalScore);
	    nameHitRecord.setInclusiveMatchScore(inclusiveMatchScore);
	    nameHitRecord.setWeightScore(weightScore);
	    
	    logger.info("Exact Score :: " + nameHitRecord.getExactMatchScore() +
	    ", Fuzzy Score :: " + nameHitRecord.getFuzzyMatchScore() + 
	    ", InclusiveMatch Score  :: " + nameHitRecord.getInclusiveMatchScore() + 
	    ", Weight Score  :: " + nameHitRecord.getWeightScore() +
	    ", Final Score  :: " + nameHitRecord.getMixScore());
	}
	
	public static int calculateWeightScore(String targetIdentification_id, String targetCountry_code, BigDecimal targetYear_of_birth, String identification, String country, String yearOfBirth, XWatchlistSetting xWatchlistSetting, String targetCountry_name){
		float weightScore = 0;
		if(identification != null && identification.length() > 0 && targetIdentification_id != null){
			if(identification.equalsIgnoreCase(targetIdentification_id)){
				weightScore += xWatchlistSetting.getIdWeightMatchingScore().floatValue();
			}else{
				weightScore -= xWatchlistSetting.getIdWeightNonMatchingScore().floatValue();
			}	
		}
		
		if(country != null && country.length() > 0 && targetCountry_code != null){
			country = country != null ? country.trim() : country;
			targetCountry_code = targetCountry_code != null ? targetCountry_code.trim() : targetCountry_code;
			targetCountry_name = targetCountry_name != null ? targetCountry_name.trim() : targetCountry_name;
			if(country.equalsIgnoreCase(targetCountry_code) || country.equalsIgnoreCase(targetCountry_name)){
				weightScore += xWatchlistSetting.getCryWeightMatchingScore().floatValue();
			}else{
				weightScore -= xWatchlistSetting.getCryWeightNonMatchingScore().floatValue();
			}	
		}
		
		if(yearOfBirth != null && yearOfBirth.length() > 0 && targetYear_of_birth != null){
			int yearMinus = Math.abs(Integer.valueOf(yearOfBirth) - targetYear_of_birth.intValue());
			if(yearMinus <= xWatchlistSetting.getBirthYeras().intValue()){
				weightScore += xWatchlistSetting.getBirWeightMatchingScore().floatValue();
			}else{
				weightScore -= xWatchlistSetting.getBirWeightNonMatchingScore().floatValue();
			}
		}
		return Math.round(weightScore);
	}
	
	/**
	 * 取字數較大著為分母，計算命中比例  //deprecated 停用
	 * @param entity_name
	 * @param inputCnName
	 * @param inputEnName
	 * @return
	 */
	public static float calculateHitWordRatio(String entity_name, String inputCnName, String inputEnName, String inputCnNamePinYin){
		float hitWordRatio = 0;
		if(!StringUtils.isEnglish(entity_name)){	//not english
			if(inputCnName != null && inputCnName.length() > 0){
				if(entity_name.length() == inputCnName.length()){
					hitWordRatio = (float)hitWordCount(inputCnName, entity_name, false) / entity_name.length();
				}
				/*
				if(entity_name.length() > inputCnName.length()){
					hitWordRatio = (float)hitWordCount(inputCnName, entity_name, false) / entity_name.length();
				}else{
					hitWordRatio = (float)hitWordCount(entity_name, inputCnName, false) / inputCnName.length();
				}
				*/
			}
		}else{	//english
			if(inputEnName != null && inputEnName.length() > 0){
				if(entity_name.split(" ").length > inputEnName.split(" ").length){
					hitWordRatio = (float)hitWordCount(inputEnName, entity_name, true) / entity_name.split(" ").length;
				}else{
					hitWordRatio = (float)hitWordCount(entity_name, inputEnName, true) / inputEnName.split(" ").length;
				}
			}
			
			if(hitWordRatio == 0 && !StringUtils.isEmpty(inputCnNamePinYin)){
				if(entity_name.split(" ").length > inputCnNamePinYin.split(" ").length){
					hitWordRatio = (float)hitWordCount(inputCnNamePinYin, entity_name, true) / entity_name.split(" ").length;
				}else{
					hitWordRatio = (float)hitWordCount(entity_name, inputCnNamePinYin, true) / inputCnNamePinYin.split(" ").length;
				}
			}
		}
		
		return hitWordRatio;
	}
	
	/**
	 * 取字數較大著為分母，計算命中比例
	 * @param entity_name
	 * @param inputCnName
	 * @param inputEnName
	 * @return
	 */
	public static float calculateHitWordRatio(String entity_name, String inputCnName, String inputEnName){
		float hitWordRatio = 0;
		if(!StringUtils.isEnglish(entity_name)){	//not english
			if(inputCnName != null && inputCnName.length() > 0){ 
				if(inputCnName.length() < 6){  //五個中文字以下才做全姓名相同字數置換精準比對
					if(entity_name.length() == inputCnName.length()){
						hitWordRatio = (float)hitWordCount(inputCnName, entity_name, false) / entity_name.length();
						hitWordRatio = hitWordRatio < 1 ? 0 : 1;
					}
				}else{
					if(entity_name.length() > inputCnName.length()){
						hitWordRatio = (float)hitWordCount(inputCnName, entity_name, false) / entity_name.length();
					}else{
						hitWordRatio = (float)hitWordCount(entity_name, inputCnName, false) / inputCnName.length();
					}
				}
			}
		}else{	//english
			if(inputEnName != null && inputEnName.length() > 0){
				if(entity_name.length() > inputEnName.length()){
					hitWordRatio = (float)hitWordCount(inputEnName, entity_name, true) / entity_name.split(" ").length;
				}else{
					hitWordRatio = (float)hitWordCount(entity_name, inputEnName, true) / inputEnName.split(" ").length;
				}
			}
		}
		
		return hitWordRatio;
	}
	
	
	/**
	 * 取字數較大著為分母，計算命中比例
	 * @param entity_name
	 * @param inputCnName
	 * @param inputEnName
	 * @return
	 */
	public static float calculateHitWordRatio(String entity_name, String inputCnName, String inputEnName, int DLParameter){
		float hitWordRatio = 0;
		if(!StringUtils.isEnglish(entity_name)){	//not english
			if(inputCnName != null && inputCnName.length() > 0){ 
				if(inputCnName.length() < 6){  //五個中文字以下才做全姓名相同字數置換精準比對
					if(entity_name.length() == inputCnName.length()){
						hitWordRatio = (float)hitWordCount(inputCnName, entity_name, false) / entity_name.length();
						hitWordRatio = hitWordRatio < 1 ? 0 : 1;
					}
				}else{
					if(entity_name.length() > inputCnName.length()){
						hitWordRatio = (float)hitWordCount(inputCnName, entity_name, false) / entity_name.length();
					}else{
						hitWordRatio = (float)hitWordCount(entity_name, inputCnName, false) / inputCnName.length();
					}
				}
			}
		}else{	//english
			int entityNameToken = entity_name.split(" ").length;
			int inputEnNameToken = inputEnName.split(" ").length;

			if(inputEnName != null && inputEnNameToken > 0){
				if(inputEnNameToken == entityNameToken){  //當兩邊 Token 相等時，開始用DLAlgorithm，比對三中二的差異
					int hitCount = hitWordCount(inputEnName, entity_name, true);
					if((inputEnNameToken-1) == hitCount && hitCount != 0){  //開始用DLAlgorithm，比對三中二的差異
						//1.找出不一樣的那一個
						String DLName1="";
						String DLName2="";
						String inputEnNameTmp = " "+ inputEnName.trim() + " ";
						String [] entityNameSpilit = entity_name.split(" ");
						for(String tmp : entityNameSpilit){
							if(!inputEnNameTmp.contains(" " + tmp + " ")){
								DLName1 = tmp;
							}
						}
						
						String entityNameTmp =  " "+ entity_name.trim() + " ";
						String [] inputEnNameSpilit = inputEnName.split(" ");
						for(String tmp : inputEnNameSpilit){
							if(!entityNameTmp.contains(" " + tmp + " ")){
								DLName2 = tmp;
							}
						}
						
						//2.將不一樣的Token 拿去計算 DL Algorithm，比較兩者間的差異度
						int DLDiff = new DamerauLevenshteinAlgorithm(2,2,2,2).execute(DLName1, DLName2);
						System.out.println("DiffResult - DLName1="+DLName1 + "  DLName2="+DLName2 + "DLDiff="+DLDiff);
						//3.如果差異度大的話，降低分數(強迫排掉)
						if(DLDiff > DLParameter){
							hitWordRatio = 0;
						}else{
							hitWordRatio = (float)hitCount / entityNameToken;
						}
						
						
					}else if(hitCount == inputEnNameToken){ 
						hitWordRatio = (float)hitCount / entityNameToken;
					}
				}else if(entityNameToken > inputEnNameToken){
					hitWordRatio = (float)hitWordCount(inputEnName, entity_name, true) / entityNameToken;
					
				}else{
					hitWordRatio = (float)hitWordCount(entity_name, inputEnName, true) / inputEnNameToken;
					
				}
			}
		}
		
		return hitWordRatio;
	}
	
	
	/**
	 * 取字數較大著為分母，計算命中比例
	 * @param entity_name
	 * @param inputCnName
	 * @param inputEnName
	 * @return
	 */
	public static float calculateHitWordRatio(String entity_name, String inputCnName, String inputEnName, int DLParameter , String entityType){
		float hitWordRatio = 0;
		if(!StringUtils.isEnglish(entity_name)){	//not english
			if(inputCnName != null && inputCnName.length() > 0){ 
				if(inputCnName.length() <= SwiftMtConst.INCLUSIVE_MATCH_CH_NAME_TOKEN_LIMIT){  //五個中文字以下才做全姓名相同字數置換精準比對
					if(entity_name.length() == inputCnName.length()){
						hitWordRatio = (float)hitWordCount(inputCnName, entity_name, false) / entity_name.length();
						hitWordRatio = hitWordRatio < 1 ? 0 : 1;
					}
				}else{
					if(entity_name.length() > inputCnName.length()){
						hitWordRatio = (float)hitWordCount(inputCnName, entity_name, false) / entity_name.length();
					}else{
						hitWordRatio = (float)hitWordCount(entity_name, inputCnName, false) / inputCnName.length();
					}
				}
			}
		}else{	//english
			int entityNameToken = entity_name.split(" ").length;
			int inputEnNameToken = inputEnName.split(" ").length;
			if(inputEnName != null && inputEnNameToken > 0){
				if(inputEnNameToken == entityNameToken){  //當兩邊 Token 相等時，開始用DLAlgorithm，比對三中二的差異					
					int hitCount = hitWordCount(inputEnName, entity_name, true);
					if((inputEnNameToken-1) == hitCount && hitCount != 0){  //開始用DLAlgorithm，比對三中二、四中三、、、、的差異			
						if(inputEnNameToken==SwiftMtConst.INCLUSIVE_MATCH_EN_NAME_TOKEN_LIMIT && SwiftMtConst.ENTITY_TYPE_IND.equals(entityType)){ //Token數=3 且為個人，且與名單只有1個Token不同時，處理漢語拼音，有順序性比對方法				
							String [] inputEnNameSpilit = inputEnName.split(" ");
							if(inputEnNameSpilit.length==SwiftMtConst.INCLUSIVE_MATCH_EN_NAME_TOKEN_LIMIT){
								String inputEnNameOrder1 = inputEnNameSpilit[0]+inputEnNameSpilit[1]+inputEnNameSpilit[2];
								String inputEnNameOrder2 = inputEnNameSpilit[1]+inputEnNameSpilit[2]+inputEnNameSpilit[0];
								String inputEnNameOrder3 = inputEnNameSpilit[2]+inputEnNameSpilit[0]+inputEnNameSpilit[1];
								entity_name = entity_name.replace(" " , "");
								int DLDiff1 = new DamerauLevenshteinAlgorithm(2,2,2,2).execute(inputEnNameOrder1, entity_name);
								int DLDiff2 = new DamerauLevenshteinAlgorithm(2,2,2,2).execute(inputEnNameOrder2, entity_name);
								int DLDiff3 = new DamerauLevenshteinAlgorithm(2,2,2,2).execute(inputEnNameOrder3, entity_name);
								//3.如果差異度大的話，降低分數(強迫排掉)
								if(DLDiff1 <= DLParameter || DLDiff2 <= DLParameter || DLDiff3 <= DLParameter ){
									hitWordRatio = (float)hitCount / entityNameToken;
								}else{
									hitWordRatio=0;
								}
							}
						}else{
							//1.找出不一樣的那一個
							String DLName1="";
							String DLName2="";
							String inputEnNameTmp = " "+ inputEnName.trim() + " ";
							String [] entityNameSpilit = entity_name.split(" ");
							for(String tmp : entityNameSpilit){
								if(!inputEnNameTmp.contains(" " + tmp + " ")){
									DLName1 = tmp;
								}
							}
							
							String entityNameTmp =  " "+ entity_name.trim() + " ";
							String [] inputEnNameSpilit = inputEnName.split(" ");
							for(String tmp : inputEnNameSpilit){
								if(!entityNameTmp.contains(" " + tmp + " ")){
									DLName2 = tmp;
								}
							}
							
							//2.將不一樣的Token 拿去計算 DL Algorithm，比較兩者間的差異度
							int DLDiff = new DamerauLevenshteinAlgorithm(2,2,2,2).execute(DLName1, DLName2);
							System.out.println("DiffResult - DLName1="+DLName1 + "  DLName2="+DLName2 + "DLDiff="+DLDiff);
							//3.如果差異度大的話，降低分數(強迫排掉)
							if(DLDiff > DLParameter){
								hitWordRatio = 0;
							}else{
								hitWordRatio = (float)hitCount / entityNameToken;
							}
						}
							
					}else if(hitCount == inputEnNameToken){ 
						hitWordRatio = (float)hitCount / entityNameToken;
					}
				}else if(entityNameToken > inputEnNameToken){
					hitWordRatio = (float)hitWordCount(inputEnName, entity_name, true) / entityNameToken;
					
				}else{
					hitWordRatio = (float)hitWordCount(entity_name, inputEnName, true) / inputEnNameToken;
					
				}
			}
		}
		
		return hitWordRatio;
	}
	
	
	
	public static float calculatInclusiveMatchScore(float hitWordRatio, XWatchlistSetting xWatchlistSetting){
		float score = 0;
		score = (SwiftMtConst.INCLUSIVE_MATCH_SOCRE_BASE * (hitWordRatio >= 0.5 ? 1 : 0)  
				+ ((xWatchlistSetting.getExactMatchingScore().intValue() - SwiftMtConst.INCLUSIVE_MATCH_SOCRE_BASE) * hitWordRatio));
		return score;
	}
	
	/**
	 * 計算命中字數
	 * @param entity_name (smaller)
	 * @param inputName (bigger)
	 * @param isEnglish
	 * @return
	 */
	public static int hitWordCount(String shorterName, String longerName, boolean isEnglish){
		int count = 0;
		// String charsetName = "UTF-8";
		if(isEnglish){
			String shorterList[] = shorterName.split(" ");
			String longerList[] = longerName.split(" ");
			for (int i = 0; i < shorterList.length; i++) {
				for(int j=0; j<longerList.length; j++){ 
					if(longerList[j].equalsIgnoreCase(shorterList[i])){
						count++;
						longerList[j] = "";
						break;
					}
				}
			}
		}else{
			try {
				char[] shorterChars = shorterName.toCharArray();
				char[] longerChars = longerName.toCharArray();
				for(int i=0; i<shorterChars.length; i++){   
					for(int j=0; j<longerChars.length; j++){   
						if(longerChars[j]==(shorterChars[i])){
							count++;
							longerChars[j] = ' ';
							break;
						}
				    }
			    }
			} catch (Exception e) {
				logger.error(" UnsupportedEncodingException : {}", e);
			} 
		}
		return count;
	}
	
	public static int hitWordCount(int wordLength){
		int count = 0;
		
		switch(wordLength) { 
	        case 10: 
	        case 9: 
	        	count = 6; 
	            break; 
	        case 8: 
	        case 7: 
	        	count = 5; 
	            break; 
	        case 6: 
	        	count = 4; 
	            break;
	        case 5:
	        case 4: 
	        	count = 3; 
	            break;
	        case 3: 
	        	count = 2; 
	            break;
	        default: 
	        	count = wordLength;
	    }
		return count;
	}
	/**
	 * 依照rankOfWatchListMap所定義的display order來排序Rule
	 * type為大分類或小分類
	 */
	public static String sortRule(String oldRule, String newRule,Map<String, String> rankOfWatchListMap,String type){
		String result = "";
		String newRuleList[] = newRule.split(",");
		
		
		for(String str : newRuleList){
			if(str.length() > 0 && oldRule.indexOf(str) < 0){
				if(oldRule.length() > 0){
					oldRule = String.format("%s,%s", oldRule, str);
				}else{
					oldRule = str;
				}
			}
		}
		
		List<String> resultlist = new ArrayList<String>();
		String temp="";
		String oldRuleList[] = oldRule.split(",");
		String firstRuleName="";
		String nextRuleName="";
		
			if(oldRuleList.length<=1){

			}else{
				
					for(int i=0,max=oldRuleList.length-1;i<max;i++){
						//大類小類區隔
						if(type.equals(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD)){
							if(oldRuleList[i] != null) {
								firstRuleName=rankOfWatchListMap.get(oldRuleList[i].length() > 2 ? oldRuleList[i].substring(0,3) : oldRuleList[i].substring(0,2));
								nextRuleName=rankOfWatchListMap.get(oldRuleList[i+1].length() > 2 ? oldRuleList[i+1].substring(0,3) : oldRuleList[i+1].substring(0,2));
							}
						}else{
							firstRuleName=rankOfWatchListMap.get(oldRuleList[i]);
							nextRuleName=rankOfWatchListMap.get(oldRuleList[i+1]);							
						}
						if(firstRuleName!=null&&nextRuleName!=null){

							if(Integer.parseInt(firstRuleName)>Integer.parseInt(nextRuleName)){
								temp=oldRuleList[i];
								oldRuleList[i]=oldRuleList[i+1];
								oldRuleList[i+1]=temp;
							}
						}
					}
				
			}
		for(int i=0,max=oldRuleList.length;i<max;i++){
			resultlist.add(i,oldRuleList[i]);
		}


		if(resultlist.size()<=20){
			for(String str : resultlist){
				result += SwiftMtConst.SUB_TYPE_CD_SPLIT+str;				
			}
		}else{
			for(int i=0,max=20;i<max;i++){
				String str=resultlist.get(i);
				result += SwiftMtConst.SUB_TYPE_CD_SPLIT+str;
			}
		}
		result = result.length() > 0 ? result.substring(1) : result;
		return result;
	}	
	
	/**
	 * 1.將前端輸入EntityType轉換 
	 * @param entityType 
	 * @return List<String>
	 */
	public static List<String> handelEntityType(String entityType){
		List<String> entityTypeList = new ArrayList<String>();
		if(SwiftMtConst.ENTITY_TYPE_IND.equalsIgnoreCase(entityType)){
			entityTypeList.add(SwiftMtConst.ENTITY_TYPE_IND);
			entityTypeList.add(SwiftMtConst.ENTITY_TYPE_INDANDCORP);
		}else if(SwiftMtConst.ENTITY_TYPE_CORP.equalsIgnoreCase(entityType)){
			entityTypeList.add(SwiftMtConst.ENTITY_TYPE_CORP);
			entityTypeList.add(SwiftMtConst.ENTITY_TYPE_BANK);
			entityTypeList.add(SwiftMtConst.ENTITY_TYPE_INDANDCORP);
		}else if(SwiftMtConst.ENTITY_TYPE_INDANDCORP.equalsIgnoreCase(entityType)){
			entityTypeList.add(SwiftMtConst.ENTITY_TYPE_IND);
			entityTypeList.add(SwiftMtConst.ENTITY_TYPE_CORP);
			entityTypeList.add(SwiftMtConst.ENTITY_TYPE_BANK);
			entityTypeList.add(SwiftMtConst.ENTITY_TYPE_INDANDCORP);
		}else if(!SwiftMtConst.ENTITY_TYPE_ALL.equalsIgnoreCase(entityType) && !SwiftMtConst.ENTITY_TYPE_FREEFPRMAT.equalsIgnoreCase(entityType)){
			entityTypeList.add(entityType);
		}
		return entityTypeList;
	}
	
	public static Boolean checkWatchlistTypeRelationship(Map<String, String> subWatchListTypeAndWatchListTypeMappingMap ,String detailRouteRule ,String routeRule ){
		for (String sub : detailRouteRule.split(",")) {
			String watchlistTypeCd = subWatchListTypeAndWatchListTypeMappingMap.get(sub);
			if (watchlistTypeCd != null && watchlistTypeCd.indexOf(routeRule) > -1)
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static void main(String[] args) {
		float hitWordRatio = NameCheckUtil.calculateHitWordRatio("马英九","馬英九","馬英九", 3, "9");
		

		System.out.println(hitWordRatio);
		System.out.println(NameCheckUtil.hitWordCount("馬英九","马英九",false));
	}
}
