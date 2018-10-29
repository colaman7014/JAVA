package com.sas.db.aml.dao.fcfcore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sas.db.aml.orm.fcfcore.FscEntityNcWatchListDim;
import com.sas.util.AmlConfiguration;
import com.sas.util.StringUtils;

/**
 * NCSC-FSC_BANK_WATCH_LIST_DIM(主要黑名單檔) Custom DAO 方法實作
 * @author SAS
 *
 */
public class IFscEntityNcWatchListDimDaoImpl implements IFscEntityNcWatchListDimDaoCustom {

	@PersistenceContext(unitName = "amlEntityManagerFactory")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<FscEntityNcWatchListDim> nativeSql(String sql) {
		
		Query query = entityManager.createNativeQuery(sql, FscEntityNcWatchListDim.class);
		List<FscEntityNcWatchListDim> resultList = (List<FscEntityNcWatchListDim>)query.getResultList();
		
		return resultList;
	}

	/**
	 * 組合Inculsive SQL方法
	 */
	@Override
	public String getInculsiveQuerySql(List<String> inculsiveList){
		StringBuffer sb = new StringBuffer();
//		String select = "   SELECT ENTITY_WATCH_LIST_KEY, ENTITY_NAME, IDENTIFICATION_ID, CITIZENSHIP_COUNTRY_CODE, YEAR_OF_BIRTH, WATCH_LIST_NAME, CATEGORY_DESC   ";
		
		List<String> findReduplicationList = findReduplication(inculsiveList);
		
		
		if(findReduplicationList.size()==0){ //沒有疊字
			
			String select = "   SELECT * ";
			String from = String.format("   FROM %s.%s.FSC_ENTITY_NC_WATCH_LIST_DIM   ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
			
			StringBuffer csb = new StringBuffer();
			for(String str : inculsiveList){
				csb.append(String.format(" OR CONTAINS(ENTITY_NAME, '%s')", StringUtils.handelEscape(str)));
			}
			
			String andInculsiveOr = String.format(" AND (%s)", csb.substring(3));		
			String where = String.format("   WHERE CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' %s  ", andInculsiveOr);
			String sbResult = sb.append(select).append(from).append(where).toString();
			return sbResult;
			
		}else{  //特別處理疊字 Ex.林美美   or cheng chen hsiu chen
			String 	findReduplicationWord = findReduplicationList.get(0);
			StringBuffer csb = new StringBuffer();
			for(int i=0 ; i<inculsiveList.size() ; i++){
				if(i!=0){
					csb.append(String.format(" OR CONTAINS(ENTITY_NAME, '%s')", StringUtils.handelEscape(inculsiveList.get(i))));				
				}else{
					csb.append(String.format(" CONTAINS(ENTITY_NAME, '%s')", StringUtils.handelEscape(inculsiveList.get(i))));
				}
			}		
			sb.append(" SELECT * ");
			sb.append("   FROM ( ");
			sb.append("     SELECT *,  ( LEN(ENTITY_NAME) - LEN(REPLACE(ENTITY_NAME ,'").append(findReduplicationWord).append("',''))) /  LEN('").append(findReduplicationWord).append("') AS MULTI_COUNT ");
			sb.append("     FROM ").append(AmlConfiguration.getAmlCatalog()).append(".").append(AmlConfiguration.getFcfcoreSchema()).append(".").append("FSC_ENTITY_NC_WATCH_LIST_DIM ");
			sb.append("     WHERE CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' ");
			sb.append("     AND ( ");
			sb.append( csb.toString() );
			sb.append("          ) ");
			sb.append("     ) T1 ");
			sb.append(" WHERE T1.MULTI_COUNT = 0 OR T1.MULTI_COUNT > 1  ");
			String sbResult = sb.toString();
			return sbResult;
			
		}

	}
	
	
	

	/**
	 * 疊字判斷方法
	 * @param inclusiveList
	 * @return
	 */
	public static List<String> findReduplication(List<String> inclusiveList){
		List<String> reduplicationList = new ArrayList<String>();
		for(String tmpWord : inclusiveList){
			if(tmpWord.contains(" AND ")){
				String [] wordSpilit = tmpWord.split(" AND ");
				Map<String,String> tmpMap = new HashMap<String, String>();
				String tmp = "";
				for(String word : wordSpilit){
					tmp = word.replace("\"", "").trim();
					tmpMap.put(tmp, tmp);
				}
				if(tmpMap.size() == 1){//為疊字
					reduplicationList.add(tmp);
				}
			}

		}
		return reduplicationList;
	}
	
}
