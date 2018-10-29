package com.sas.wlsearch.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sas.constraint.SwiftMtConst;
import com.sas.swift.bean.BicCodeBean;
import com.sas.swift.bean.SwiftConfigBean;
import com.sas.swift.bean.SwiftHitRecordBean;
import com.sas.util.AmlConfiguration;
import com.sas.util.StatementManager;
import com.sas.wlsearch.util.WatchListUtil;
@Component
public class WatchListSearchDAO {
	private static final Logger logger = LoggerFactory.getLogger(WatchListSearchDAO.class);
	
	public void getSwiftHitRecord(List<SwiftHitRecordBean> swiftHitRecordBeanList, String sql, String uniqueKey, int referenceId,Connection conn, Map<String, Set<String>> fieldValueFuzzyMap, Map<String, Set<String>> countryCityFieldValueFuzzyMap, Map<String, String> swiftTagFieldNameMap){
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			psm = conn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
				String watchListModel = rs.getString("WATCH_LIST_MODEL");
				String tagName = rs.getString("TAGNAME");
				//於Field_Name前面加上FieldTag Ex.(59)BENEFICIARY_CUSTOMER
				String fieldName = "(" + tagName + ")" +swiftTagFieldNameMap.get(tagName);
				int seq = swiftHitRecordBeanList.size();
				swiftHitRecordBeanList.add(handelHitRecordByModel(uniqueKey, referenceId, watchListModel, fieldName, fieldValueFuzzyMap, countryCityFieldValueFuzzyMap, rs, seq));
			}
		} catch (Exception ex) {
			logger.error(String.format("getSwiftHitRecord fail, Exception : %s", ex.getMessage()), ex);
		} finally {
			StatementManager.close(psm, rs);
		}
	}
	
	private SwiftHitRecordBean handelHitRecordByModel(String uniqueKey, int referenceId, String watchListModel, String fieldName, Map<String, Set<String>> fieldValueFuzzyMap, Map<String, Set<String>> countryCityFieldValueFuzzyMap, ResultSet rs, int seq) throws SQLException{
		SwiftHitRecordBean swiftHitRecordBean = new SwiftHitRecordBean();
		swiftHitRecordBean.setUniqueKey(uniqueKey);
		swiftHitRecordBean.setReferenceId(String.valueOf(referenceId));
		swiftHitRecordBean.setSeq(String.valueOf(seq+1));
		swiftHitRecordBean.setFieldName(fieldName);
		adaptorFieldValue(swiftHitRecordBean, fieldValueFuzzyMap, countryCityFieldValueFuzzyMap, rs.getString("SEARCH_KEY"), rs.getString("SEARCH_MAP"), rs.getString("TAGNAME"));
		swiftHitRecordBean.setWatchListModel(watchListModel);
		swiftHitRecordBean.setWatchListName(rs.getString("WATCH_LIST_NAME"));
		swiftHitRecordBean.setEntityWatchListKey(rs.getString("WATCH_LIST_KEY"));
		swiftHitRecordBean.setEntityWatchListNumber(rs.getString("WATCH_LIST_NUMBER"));
		swiftHitRecordBean.setEntityNameDisplay(rs.getString("NAME_DISPLAY"));
		swiftHitRecordBean.setMatch_code(rs.getString("match_code"));
		swiftHitRecordBean.setNospace_name_ind(rs.getString("nospace_name_ind"));
		if("ScreenField".equalsIgnoreCase(watchListModel)){			
			swiftHitRecordBean.setYearOfBirth(rs.getString("YEAR_OF_BIRTH") != null ? new BigDecimal(rs.getString("YEAR_OF_BIRTH")) : null);
			swiftHitRecordBean.setPlaceOfBirth(rs.getString("PLACE_OF_BIRTH"));
			swiftHitRecordBean.setFirstName(rs.getString("FIRST_NAME"));
			swiftHitRecordBean.setLastName(rs.getString("LAST_NAME"));
			swiftHitRecordBean.setMiddleName(rs.getString("MIDDLE_NAME"));
			swiftHitRecordBean.setEntityName(rs.getString("ENTITY_NAME"));
			swiftHitRecordBean.setCitizenshipCountryCode(rs.getString("CITIZENSHIP_COUNTRY_CODE"));
			swiftHitRecordBean.setIdentificationId(rs.getString("IDENTIFICATION_ID"));
			swiftHitRecordBean.setIdentificationTypeDesc(rs.getString("IDENTIFICATION_TYPE_DESC"));
			swiftHitRecordBean.setTypeDesc(rs.getString("TYPE_DESC"));
		}else if("ScreenCountryCity".equalsIgnoreCase(watchListModel)){
			swiftHitRecordBean.setCitizenshipCountryCode(rs.getString("CODE"));
			swiftHitRecordBean.setEntityName(rs.getString("NAME"));
		}else if("ScreenBicCountry".equalsIgnoreCase(watchListModel)){
			swiftHitRecordBean.setCitizenshipCountryCode(rs.getString("CODE"));
			swiftHitRecordBean.setIdentificationId(rs.getString("CODE"));
		}
		return swiftHitRecordBean;
	}
	
	/**
	 * 組出fieldValue字串: tagName + 搜尋條件值
	 * @param swiftHitRecordBean
	 * @param fieldValueFuzzyMap
	 * @param searchKey
	 * @param searchMap
	 * @param tagName
	 */
	private void adaptorFieldValue(SwiftHitRecordBean swiftHitRecordBean, Map<String, Set<String>> fieldValueFuzzyMap, Map<String, Set<String>> countryCityFieldValueFuzzyMap,
			String searchKey, String searchMap, String tagName) {
		Set<String> allSet = new LinkedHashSet<String>();
		allSet.add(tagName+":");
		if("screenFieldFuzzyMap".equalsIgnoreCase(searchMap)){
			allSet.addAll(fieldValueFuzzyMap.get(searchKey));
		}else if("screenCountryCityFuzzyMap".equalsIgnoreCase(searchMap)){
			logger.debug("searchKey : " + searchKey);
			logger.debug("countryCityFieldValueFuzzyMap : " + countryCityFieldValueFuzzyMap.get(searchKey).toArray().toString());
			allSet.addAll(countryCityFieldValueFuzzyMap.get(searchKey));
		}else{
			allSet.add(searchKey);
		}
		swiftHitRecordBean.setFieldValue(org.apache.commons.lang.StringUtils.join(allSet, SwiftMtConst.MULTIFIELDSPLIT));
	}
	
	private String handelEscape(String str){
		String result = str;
		if(str != null && str.length() > 0){
			result = str.replaceAll("'", "''");
		}
		return result;
	}
	
	public List<String> getScreenFieldToSelectList(Map<String, List<String>> toSelectMap){
		String tmpFieldName = "";
		StringBuffer sb = new StringBuffer();
		String selectSqlTemplate = " SELECT '%s' AS TAGNAME, '%s' AS WATCH_LIST_MODEL, ENTITY_WATCH_LIST_KEY AS WATCH_LIST_KEY, (WATCH_LIST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NAME, (ENTITY_WATCH_LIST_NUMBER COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NUMBER, (TYPE_DESC COLLATE Chinese_Taiwan_Stroke_CI_AS) AS TYPE_DESC, YEAR_OF_BIRTH, null AS PLACE_OF_BIRTH, (FIRST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS FIRST_NAME, (LAST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS LAST_NAME, (MIDDLE_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS MIDDLE_NAME, (ENTITY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS ENTITY_NAME, (CITIZENSHIP_COUNTRY_CODE COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CITIZENSHIP_COUNTRY_CODE, (CHANGE_CURRENT_IND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CHANGE_CURRENT_IND, null as CODE, null as NAME, (ENTITY_NAME_DISPLAY COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME_DISPLAY, (IDENTIFICATION_ID COLLATE Chinese_Taiwan_Stroke_CI_AS) AS IDENTIFICATION_ID, null AS IDENTIFICATION_TYPE_DESC, 'toSelectMap' AS SEARCH_MAP, (ENTITY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS SEARCH_KEY, (match_code_entity_name COLLATE Chinese_Taiwan_Stroke_CI_AS) AS match_code, (nospace_name_ind COLLATE Chinese_Taiwan_Stroke_CI_AS) AS nospace_name_ind ";
		
		String selectSql = "";
		String fromSql = String.format(" FROM %s.%s.FSC_ENTITY_SC_WATCH_LIST_DIM ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
		String unionAll = 	" ) UNION All ";
		String whereSql = " WHERE ( CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' ) AND ( 1 <> 1 ";
		String orEntityNameSqlTemplate = " OR ENTITY_NAME = N'%s' ";
		List<String> sqlList = new ArrayList<String>();
		int i = 0;
		for (Map.Entry<String, List<String>> entry : toSelectMap.entrySet()) {
			i++;
			if(i%8000 == 0){
				sb.append(" ) ");
				String tmpSql = sb.substring(unionAll.length()).toString();
				logger.debug("tmpSql : " + tmpSql);
				sqlList.add(tmpSql);
				sb.delete(0, sb.length());
			}
			
			String key = handelEscape(entry.getKey());
			List<String> valList = entry.getValue();
			
			for(String value : valList){
				if("".equals(tmpFieldName) || !tmpFieldName.equalsIgnoreCase(value) || i%8000 == 0){
					String fieldModel[] = value.split("@");
					selectSql = String.format(selectSqlTemplate, fieldModel[0], fieldModel[1]);
					sb.append(unionAll);
					sb.append(selectSql);
					sb.append(fromSql);
					sb.append(whereSql);
					sb.append(String.format(orEntityNameSqlTemplate, key));
					tmpFieldName = value;
				}
			}			
			
			sb.append(String.format(orEntityNameSqlTemplate, key));
		}
		
		int index = 0;
		if(toSelectMap.size() > 0){
			sb.append(" ) ");
			index = unionAll.length();
		}
		String lastSql = sb.substring(index).toString();
		logger.debug("lastSql : " + lastSql);
		sqlList.add(lastSql);
		logger.debug("sqlList size : " + sqlList.size());
		sb = null;
		return sqlList;
	}
	
	public String getScreenFieldToSelect(Map<String, String> toSelectMap){
		String sql = "";
		String tmpFieldName = "";
		StringBuffer sb = new StringBuffer();
		String selectSqlTemplate = " SELECT '%s' AS TAGNAME, '%s' AS WATCH_LIST_MODEL, ENTITY_WATCH_LIST_KEY AS WATCH_LIST_KEY, (WATCH_LIST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NAME, (ENTITY_WATCH_LIST_NUMBER COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NUMBER, (TYPE_DESC COLLATE Chinese_Taiwan_Stroke_CI_AS) AS TYPE_DESC, YEAR_OF_BIRTH, null AS PLACE_OF_BIRTH, (FIRST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS FIRST_NAME, (LAST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS LAST_NAME, (MIDDLE_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS MIDDLE_NAME, (ENTITY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS ENTITY_NAME, (CITIZENSHIP_COUNTRY_CODE COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CITIZENSHIP_COUNTRY_CODE, (CHANGE_CURRENT_IND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CHANGE_CURRENT_IND, null as CODE, null as NAME, (ENTITY_NAME_DISPLAY COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME_DISPLAY, (IDENTIFICATION_ID COLLATE Chinese_Taiwan_Stroke_CI_AS) AS IDENTIFICATION_ID, null AS IDENTIFICATION_TYPE_DESC, 'toSelectMap' AS SEARCH_MAP, (ENTITY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS SEARCH_KEY, (match_code_entity_name COLLATE Chinese_Taiwan_Stroke_CI_AS) AS match_code, (nospace_name_ind COLLATE Chinese_Taiwan_Stroke_CI_AS) AS nospace_name_ind ";
		
		String selectSql = "";
		String fromSql = String.format(" FROM %s.%s.FSC_ENTITY_SC_WATCH_LIST_DIM ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
		String unionAll = 	" ) UNION All ";
		String whereSql = " WHERE ( CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' AND SORT_NAME_IND = 'N' ) AND ( 1 <> 1 ";
		String orEntityNameSqlTemplate = " OR ENTITY_NAME = N'%s' ";
		List<String> sqlList = new ArrayList<String>();
		int i = 0;
		for (Map.Entry<String, String> entry : toSelectMap.entrySet()) {
			i++;
			if(i%8000 == 0){
				sb.append(" ) ");
				String tmpSql = sb.substring(unionAll.length()).toString();
				logger.debug("tmpSql : " + tmpSql);
				sqlList.add(tmpSql);
				sb.delete(0, sb.length());
			}
			
			String key = handelEscape(entry.getKey());
			String value = entry.getValue();
			
			if("".equals(tmpFieldName) || !tmpFieldName.equalsIgnoreCase(value) || i%8000 == 0){
				String fieldModel[] = value.split("@");
				selectSql = String.format(selectSqlTemplate, fieldModel[0], fieldModel[1]);
				sb.append(unionAll);
				sb.append(selectSql);
				sb.append(fromSql);
				sb.append(whereSql);
				sb.append(String.format(orEntityNameSqlTemplate, key));
				tmpFieldName = value;
			}
			
			
			sb.append(String.format(orEntityNameSqlTemplate, key));
		}
		
		int index = 0;
		if(toSelectMap.size() > 0){
			sb.append(" ) ");
			index = unionAll.length();
		}
		sql = sb.substring(index).toString();
		logger.debug("sql : " + sql);
		sqlList.add(sql);
		logger.debug("sqlList size : " + sqlList.size());
		sb = null;
		return sql;
	}
	
	public String getScreenFieldToSelectFuzzy(Map<String, String> screenFieldFuzzyMap){
		String sql = "";
		String tmpFieldName = "";
		StringBuffer sb = new StringBuffer();
		String selectSqlTemplate = " SELECT '%s' AS TAGNAME, '%s' AS WATCH_LIST_MODEL, ENTITY_WATCH_LIST_KEY AS WATCH_LIST_KEY, (WATCH_LIST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NAME, (ENTITY_WATCH_LIST_NUMBER COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NUMBER, (TYPE_DESC COLLATE Chinese_Taiwan_Stroke_CI_AS) AS TYPE_DESC, YEAR_OF_BIRTH, null AS PLACE_OF_BIRTH, (FIRST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS FIRST_NAME, (LAST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS LAST_NAME, (MIDDLE_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS MIDDLE_NAME, (ENTITY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS ENTITY_NAME, (CITIZENSHIP_COUNTRY_CODE COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CITIZENSHIP_COUNTRY_CODE, (CHANGE_CURRENT_IND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CHANGE_CURRENT_IND, null as CODE, null as NAME, (ENTITY_NAME_DISPLAY COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME_DISPLAY, (IDENTIFICATION_ID COLLATE Chinese_Taiwan_Stroke_CI_AS) AS IDENTIFICATION_ID, null AS IDENTIFICATION_TYPE_DESC, 'screenFieldFuzzyMap' AS SEARCH_MAP, (MATCH_CODE_ENTITY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS SEARCH_KEY, (match_code_entity_name COLLATE Chinese_Taiwan_Stroke_CI_AS) AS match_code, (nospace_name_ind COLLATE Chinese_Taiwan_Stroke_CI_AS) AS nospace_name_ind ";
		
		String selectSql = "";
		String fromSql = String.format(" FROM %s.%s.FSC_ENTITY_SC_WATCH_LIST_DIM ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
		String unionAll = 	" ) UNION All ";
		String whereSql = " WHERE ( CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' ) AND ( 1 <> 1 ";
		String orMatchCodeIndividualSqlTemplate = " OR MATCH_CODE_ENTITY_NAME = '%s' ";
		
		
		for (Map.Entry<String, String> entry : screenFieldFuzzyMap.entrySet()) {
			String key = handelEscape(entry.getKey());
			String value = entry.getValue();
			
			if("".equals(tmpFieldName) || !tmpFieldName.equalsIgnoreCase(value)){
				String fieldModel[] = value.split("@");
				selectSql = String.format(selectSqlTemplate, fieldModel[0], fieldModel[1]);
				sb.append(unionAll);
				sb.append(selectSql);
				sb.append(fromSql);
				sb.append(whereSql);
				sb.append(String.format(orMatchCodeIndividualSqlTemplate, key));
				
				tmpFieldName = value;
			}
			
			sb.append(String.format(orMatchCodeIndividualSqlTemplate, key));
		}
		
		int index = 0;
		if(screenFieldFuzzyMap.size() > 0){
			sb.append(" ) ");
			index = unionAll.length();
		}
		sql = sb.substring(index).toString();
		sb = null;
		return sql;
	}
	
	public String getScreenFieldToSelectFuzzyList(Map<String, List<String>> screenFieldFuzzyMap){
		String sql = "";
		String tmpFieldName = "";
		StringBuffer sb = new StringBuffer();
		String selectSqlTemplate = " SELECT '%s' AS TAGNAME, '%s' AS WATCH_LIST_MODEL, ENTITY_WATCH_LIST_KEY AS WATCH_LIST_KEY, (WATCH_LIST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NAME, (ENTITY_WATCH_LIST_NUMBER COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NUMBER, (TYPE_DESC COLLATE Chinese_Taiwan_Stroke_CI_AS) AS TYPE_DESC, YEAR_OF_BIRTH, null AS PLACE_OF_BIRTH, (FIRST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS FIRST_NAME, (LAST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS LAST_NAME, (MIDDLE_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS MIDDLE_NAME, (ENTITY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS ENTITY_NAME, (CITIZENSHIP_COUNTRY_CODE COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CITIZENSHIP_COUNTRY_CODE, (CHANGE_CURRENT_IND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CHANGE_CURRENT_IND, null as CODE, null as NAME, (ENTITY_NAME_DISPLAY COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME_DISPLAY, (IDENTIFICATION_ID COLLATE Chinese_Taiwan_Stroke_CI_AS) AS IDENTIFICATION_ID, null AS IDENTIFICATION_TYPE_DESC, 'screenFieldFuzzyMap' AS SEARCH_MAP, (MATCH_CODE_ENTITY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS SEARCH_KEY, (match_code_entity_name COLLATE Chinese_Taiwan_Stroke_CI_AS) AS match_code, (nospace_name_ind COLLATE Chinese_Taiwan_Stroke_CI_AS) AS nospace_name_ind ";
		
		String selectSql = "";
		String fromSql = String.format(" FROM %s.%s.FSC_ENTITY_SC_WATCH_LIST_DIM ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
		String unionAll = 	" ) UNION All ";
		String whereSql = " WHERE ( CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' ) AND ( 1 <> 1 ";
		String orMatchCodeIndividualSqlTemplate = " OR MATCH_CODE_ENTITY_NAME = '%s' ";
		
		
		for (Map.Entry<String, List<String>> entry : screenFieldFuzzyMap.entrySet()) {
			String key = handelEscape(entry.getKey());
			List<String> strList = entry.getValue();
			for(String value : strList){
				if("".equals(tmpFieldName) || !tmpFieldName.equalsIgnoreCase(value)){
					String fieldModel[] = value.split("@");
					selectSql = String.format(selectSqlTemplate, fieldModel[0], fieldModel[1]);
					sb.append(unionAll);
					sb.append(selectSql);
					sb.append(fromSql);
					sb.append(whereSql);
					sb.append(String.format(orMatchCodeIndividualSqlTemplate, key));
					
					tmpFieldName = value;
				}
				
				sb.append(String.format(orMatchCodeIndividualSqlTemplate, key));
			}
		}
		
		int index = 0;
		if(screenFieldFuzzyMap.size() > 0){
			sb.append(" ) ");
			index = unionAll.length();
		}
		sql = sb.substring(index).toString();
		sb = null;
		return sql;
	}
	
	public String getScreenBicCountry(Map<String, BicCodeBean> screenBicCountryMap){
		String sql = "";
		String tmpFieldName = "";
		StringBuffer bankSb = new StringBuffer();
		String selectBankSqlTemplate = " SELECT '%s' AS TAGNAME ,'%s' AS WATCH_LIST_MODEL, BANK_WATCH_LIST_KEY AS WATCH_LIST_KEY, (WATCH_LIST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NAME, (BANK_WATCH_LIST_NUMBER COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NUMBER , null as TYPE_DESC ,null as YEAR_OF_BIRTH ,null as PLACE_OF_BIRTH ,null as FIRST_NAME ,null as LAST_NAME ,null as MIDDLE_NAME ,null as ENTITY_NAME , null as CITIZENSHIP_COUNTRY_CODE,(CHANGE_CURRENT_IND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CHANGE_CURRENT_IND , (BCC_CODE COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CODE ,(BANK_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME, (BANK_NAME_DISPLAY COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME_DISPLAY, null as IDENTIFICATION_ID, null as IDENTIFICATION_TYPE_DESC, 'screenBicCountryMap' AS SEARCH_MAP, ('%s' COLLATE Chinese_Taiwan_Stroke_CI_AS) AS SEARCH_KEY, (match_code_bank_name COLLATE Chinese_Taiwan_Stroke_CI_AS) AS match_code, (nospace_name_ind COLLATE Chinese_Taiwan_Stroke_CI_AS) AS nospace_name_ind "; 
		
		String selectBankSql = "";
		String fromBankSql = String.format(" FROM %s.%s.FSC_BANK_SC_WATCH_LIST_DIM ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
		String unionAll = 	" ) UNION All ";
		String whereBankSql = " WHERE ( CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' AND SORT_NAME_IND = 'N' ) AND ( 1 <> 1 ";
		
		String orBccCodeSqlLikeTemplate = " OR BCC_CODE LIKE '%s' ";
		String orBccCodeSqlTemplate = " OR BCC_CODE = '%s' ";
		
		StringBuffer ccSb = new StringBuffer();
		String selectccSqlTemplate = " SELECT '%s' AS TAGNAME ,'%s' AS WATCH_LIST_MODEL, LOCATION_WATCH_LIST_KEY AS WATCH_LIST_KEY, (WATCH_LIST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NAME, (LOCATION_WATCH_LIST_NUMBER COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NUMBER ,null as TYPE_DESC ,null as YEAR_OF_BIRTH ,null as PLACE_OF_BIRTH ,null as FIRST_NAME ,null as LAST_NAME ,null as MIDDLE_NAME ,null as ENTITY_NAME , null as CITIZENSHIP_COUNTRY_CODE ,(CHANGE_CURRENT_IND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CHANGE_CURRENT_IND ,(COUNTRY_CODE COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CODE ,(COUNTRY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME, (COUNTRY_NAME_DISPLAY COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME_DISPLAY, null as IDENTIFICATION_ID, null as IDENTIFICATION_TYPE_DESC, 'COUNTRY_CODE' AS SEARCH_MAP, ('%s' COLLATE Chinese_Taiwan_Stroke_CI_AS) AS SEARCH_KEY, (match_code_country_name COLLATE Chinese_Taiwan_Stroke_CI_AS) AS match_code, (nospace_name_ind COLLATE Chinese_Taiwan_Stroke_CI_AS) AS nospace_name_ind ";
		
		String selectccSql = "";
		String fromccSql = String.format(" FROM %s.%s.FSC_LOCATION_SC_WATCH_LIST_DIM ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
		String whereccSql = " WHERE ( CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' AND SORT_NAME_IND = 'N' ) AND ( 1 <> 1 ";
		String orCountryCodeSqlTemplate = " OR COUNTRY_CODE = '%s' OR COUNTRY_CODE_3 = '%s' ";
		
		for (Map.Entry<String, BicCodeBean> entry : screenBicCountryMap.entrySet()) {
			String key = entry.getKey();
			BicCodeBean bean = entry.getValue();
			String countryCode = handelEscape(bean.getCountryCode());
			String bicCode = handelEscape(bean.getBicCode());
			if("".equals(tmpFieldName) || !tmpFieldName.equalsIgnoreCase(key)){
				String fieldModel[] = key.split("@");
				selectBankSql = String.format(selectBankSqlTemplate, fieldModel[0], fieldModel[1], bicCode);
				bankSb.append(unionAll);
				bankSb.append(selectBankSql);
				bankSb.append(fromBankSql);
				bankSb.append(whereBankSql);
				
				if(bicCode != null) {
					String bicCodes[] = bicCode.split(" ");
					for (int i = 0; i < bicCodes.length; i++) {
						if(bicCodes[i].length() == 8 || bicCodes[i].length() == 11)
							bankSb.append(String.format(orBccCodeSqlLikeTemplate, WatchListUtil.getFirstNCharactersInBCCCode(bicCodes[i])));
					}
				} else {
					bankSb.append(String.format(orBccCodeSqlTemplate, ""));
				}
				
				selectccSql = String.format(selectccSqlTemplate, fieldModel[0], fieldModel[1], countryCode);
				ccSb.append(unionAll);
				ccSb.append(selectccSql);
				ccSb.append(fromccSql);
				ccSb.append(whereccSql);
				ccSb.append(String.format(orCountryCodeSqlTemplate, countryCode, countryCode));
				
				tmpFieldName = key;
			}
			
			if(bicCode != null) {
				bankSb.append(String.format(orBccCodeSqlLikeTemplate, bicCode));
			} else {
				bankSb.append(String.format(orBccCodeSqlTemplate, ""));
			}
			
			ccSb.append(String.format(orCountryCodeSqlTemplate, countryCode, countryCode));
		}
		
		int index = 0;
		if(screenBicCountryMap.size() > 0){
			ccSb.append(" ) ");
			index = unionAll.length();
		}
		sql = bankSb.substring(index).toString() + ccSb.toString() ;
		bankSb = null;
		ccSb = null;
		
		return sql;
	}
	
	public String getFirstNCharactersInBCCCode(String bicCode) {
		if(bicCode != null) {
			int bccStrNumber = Integer.parseInt(AmlConfiguration.getString("com.sas.swiftCheck.bccCodeNNumbers")) != 0 
					? Integer.parseInt(AmlConfiguration.getString("com.sas.swiftCheck.bccCodeNNumbers")) 
					: bicCode.length();
			bicCode = bicCode.substring(0, Math.min(bicCode.length(), bccStrNumber));
			bicCode = String.format("%s%%", bicCode);
		} else {
			bicCode = "";
		}
		return bicCode;
	}
	
	public String getCountryCityFuzzy(Map<String, String> screenCountryCityFuzzyMap){
		String sql = "";
		String tmpFieldName = "";
		
		StringBuffer ccSb = new StringBuffer();
//		String selectccSqlTemplate = " SELECT '%s' AS FIELDNAME ,'%s' AS WATCH_LIST_MODEL ,LOCATION_WATCH_LIST_KEY ,(LOCATION_WATCH_LIST_NUMBER COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NUMBER ,(WATCH_LIST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NAME ,null as CATEGORY_DESC ,null as TYPE_DESC ,null as YEAR_OF_BIRTH ,null as PLACE_OF_BIRTH ,null as FIRST_NAME ,null as LAST_NAME ,null as MIDDLE_NAME ,null as ENTITY_NAME ,null as MATCH_CODE_INDIVIDUAL ,null as CITIZENSHIP_COUNTRY_CODE ,(CHANGE_CURRENT_IND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CHANGE_CURRENT_IND ,(AML_CATEGORY_CD COLLATE Chinese_Taiwan_Stroke_CI_AS) AS AML_CATEGORY_CD ,(COUNTRY_CODE COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CODE ,(COUNTRY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME ";
//		String selectccSqlTemplate = " SELECT '%s' AS FIELDNAME ,'%s' AS WATCH_LIST_MODEL, LOCATION_WATCH_LIST_KEY AS WATCH_LIST_KEY, (WATCH_LIST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NAME, (LOCATION_WATCH_LIST_NUMBER COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NUMBER , null as TYPE_DESC ,null as YEAR_OF_BIRTH ,null as PLACE_OF_BIRTH ,null as FIRST_NAME ,null as LAST_NAME ,null as MIDDLE_NAME ,null as ENTITY_NAME , null as CITIZENSHIP_COUNTRY_CODE ,(CHANGE_CURRENT_IND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CHANGE_CURRENT_IND , (COUNTRY_CODE COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CODE ,(COUNTRY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME, (COUNTRY_NAME_DISPLAY COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME_DISPLAY, null as IDENTIFICATION_ID, null as IDENTIFICATION_TYPE_DESC ";
		String selectccSqlTemplate = " SELECT '%s' AS TAGNAME ,'%s' AS WATCH_LIST_MODEL, LOCATION_WATCH_LIST_KEY AS WATCH_LIST_KEY, (WATCH_LIST_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NAME, (LOCATION_WATCH_LIST_NUMBER COLLATE Chinese_Taiwan_Stroke_CI_AS) AS WATCH_LIST_NUMBER , null as TYPE_DESC ,null as YEAR_OF_BIRTH ,null as PLACE_OF_BIRTH ,null as FIRST_NAME ,null as LAST_NAME ,null as MIDDLE_NAME ,null as ENTITY_NAME , null as CITIZENSHIP_COUNTRY_CODE ,(CHANGE_CURRENT_IND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CHANGE_CURRENT_IND , (COUNTRY_CODE COLLATE Chinese_Taiwan_Stroke_CI_AS) AS CODE ,(COUNTRY_NAME COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME, (COUNTRY_NAME_DISPLAY COLLATE Chinese_Taiwan_Stroke_CI_AS) AS NAME_DISPLAY, null as IDENTIFICATION_ID, null as IDENTIFICATION_TYPE_DESC, 'screenCountryCityFuzzyMap' AS SEARCH_MAP, match_code_country_name AS SEARCH_KEY, (match_code_country_name COLLATE Chinese_Taiwan_Stroke_CI_AS) AS match_code, (nospace_name_ind COLLATE Chinese_Taiwan_Stroke_CI_AS) AS nospace_name_ind ";
		
		String selectccSql = "";
		String fromccSql = String.format(" FROM %s.%s.FSC_LOCATION_SC_WATCH_LIST_DIM ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
		String unionAll = 	" ) UNION All ";
		String whereccSql = " WHERE ( CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' ) AND ( 1 <> 1 ";
		String orCountryCodeSqlTemplate = " OR MATCH_CODE_COUNTRY_NAME = '%s' OR COUNTRY_CODE = '%s' OR COUNTRY_CODE_3 = '%s' ";
		
		for (Map.Entry<String, String> entry : screenCountryCityFuzzyMap.entrySet()) {
			String key = handelEscape(entry.getKey());
			String value = entry.getValue();
			
			if("".equals(tmpFieldName) || !tmpFieldName.equalsIgnoreCase(value)){
				String fieldModel[] = value.split("@");
				
				selectccSql = String.format(selectccSqlTemplate, fieldModel[0], fieldModel[1]);
				ccSb.append(unionAll);
				ccSb.append(selectccSql);
				ccSb.append(fromccSql);
				ccSb.append(whereccSql);
				ccSb.append(String.format(orCountryCodeSqlTemplate, key, key, key));
				
				tmpFieldName = value;
			}
			
			ccSb.append(String.format(orCountryCodeSqlTemplate, key, key, key));
		}
		
		int index = 0;
		if(screenCountryCityFuzzyMap.size() > 0){
			ccSb.append(" ) ");
			index = unionAll.length();
		}
		sql = ccSb.substring(index).toString();
		ccSb = null;
		return sql;
	}
}
