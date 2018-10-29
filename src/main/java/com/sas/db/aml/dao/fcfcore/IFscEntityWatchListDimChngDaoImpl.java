package com.sas.db.aml.dao.fcfcore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.jpa.HibernateQuery;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.fcfcore.FscEntityWatchListDimChng;
import com.sas.util.AmlConfiguration;
import com.sas.util.StringUtils;

/**
 * NCSC-FSC_BANK_WATCH_LIST_DIM(主要黑名單檔) Custom DAO 方法實作
 * @author SAS
 *
 */
public class IFscEntityWatchListDimChngDaoImpl implements IFscEntityWatchListDimChngDaoCustom {

	@PersistenceContext(unitName = "amlEntityManagerFactory")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<FscEntityWatchListDimChng> nativeSql(String sql) {
		
		Query query = entityManager.createNativeQuery(sql, FscEntityWatchListDimChng.class);
		List<FscEntityWatchListDimChng> resultList = (List<FscEntityWatchListDimChng>)query.getResultList();
		
		return resultList;
	}

	/**
	 * 組合Inculsive SQL方法
	 */
	@Override
	public String getInculsiveQuerySql(List<String> inculsiveList){
		StringBuffer sb = new StringBuffer();
		
		List<String> findReduplicationList = findReduplication(inculsiveList);
		if(findReduplicationList.size()==0){ //沒有疊字
//			String select = "   SELECT ENTITY_WATCH_LIST_KEY, ENTITY_NAME, IDENTIFICATION_ID, CITIZENSHIP_COUNTRY_CODE, YEAR_OF_BIRTH, WATCH_LIST_NAME, CATEGORY_DESC   ";
			String select = "   SELECT * ";
			String from = String.format("   FROM %s.%s.FSC_ENTITY_WATCH_LIST_DIM_CHNG   ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
			
			StringBuffer csb = new StringBuffer();
			for(String str : inculsiveList){
				csb.append(String.format(" OR CONTAINS(ENTITY_NAME, '%s')", StringUtils.handelEscape(str)));
			}
			String andInculsiveOr = String.format(" AND (%s)", csb.substring(3));
			
			String where = String.format("   WHERE CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' %s  ", andInculsiveOr);
			return sb.append(select).append(from).append(where).toString();
		}else{		
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
			sb.append("     FROM ").append(AmlConfiguration.getAmlCatalog()).append(".").append(AmlConfiguration.getFcfcoreSchema()).append(".").append("FSC_ENTITY_WATCH_LIST_DIM_CHNG ");
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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryEntityNameCandidate() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("   select entity_name ");
		sb.append("   from ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG t1 ");
		sb.append("   where t1.change_current_ind = 'Y' and t1.delete_ind = 'N' and t1.exclude_Ind = 'N' ");
		
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		List<String> returnList = new ArrayList<String>();
		for(Map<String, Object> obj : tmpList){
			String entityName = (String) (obj.get("entity_name")==null? "": obj.get("entity_name"));
			returnList.add(entityName);
		}
		return returnList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public int queryCountEntityNameCandidate(String changeCurrentInd , String deleteInd, String excludeInd , String screenProcess) {	
		StringBuffer sb = new StringBuffer();
		
		sb.append("   SELECT COUNT(*) as count_number   ");
		sb.append("   FROM (  ");
		sb.append("       SELECT DISTINCT ENTITY_WATCH_LIST_KEY, ENTITY_NAME , Entity_Type_Cd  ");
		sb.append("   FROM ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".X_FSC_ENTITY_WL_X_LIST_SETTING T1 ");
		sb.append("   , ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".X_SCREEN_PROCESS_SETTING T2 ");
		sb.append("   , ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG T3 ");
		sb.append("      WHERE T1.WatchList_Sub_Type_Cd = T2.WatchList_Sub_Type_Cd  ");
		sb.append("        AND T1.entity_watch_list_number = T3.entity_watch_list_number  ");
		sb.append("        AND T1.change_current_ind = '").append(changeCurrentInd).append("'  ");
		sb.append("       AND T2.change_current_ind = '").append(changeCurrentInd).append("' AND T2.DELETE_IND='").append(deleteInd).append("'  ");
		sb.append("            AND T2.Screen_Process_Type_Cd = '").append(screenProcess).append("'  ");
		sb.append("              AND T3.change_current_ind = '").append(changeCurrentInd).append("' AND T3.delete_ind = '").append(deleteInd).append("' AND T3.exclude_ind = '").append(excludeInd).append("' ) A1  ");
	
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		int countNumber = 0;
		for(Map<String, Object> obj : tmpList){
			countNumber = (int) (obj.get("count_number")==null? 0: obj.get("count_number"));
		}
		return countNumber;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> queryCountEntityNameCandidate(String changeCurrentInd , String deleteInd, String excludeInd , String screenProcess, int indexFrom , int indexTo) {	
		StringBuffer sb = new StringBuffer();

		sb.append("   SELECT B1.ENTITY_NAME , b1.ENTITY_TYPE_CD ");
		sb.append("   FROM ( ");
		sb.append("   SELECT ROW_NUMBER() OVER(ORDER BY A1.ENTITY_WATCH_LIST_KEY ) AS Row , A1.ENTITY_NAME , A1.ENTITY_TYPE_CD ");
		sb.append("   FROM ( ");
		sb.append("   SELECT DISTINCT ENTITY_WATCH_LIST_KEY, ENTITY_NAME , Entity_Type_Cd ");
		sb.append("   FROM ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".X_FSC_ENTITY_WL_X_LIST_SETTING T1 ");
		sb.append("   , ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".X_SCREEN_PROCESS_SETTING T2 ");
		sb.append("   , ").append(SwiftMtConst.COM_SAS_JPACFG_AML_CATALOG).append(".").append(SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA).append(".FSC_ENTITY_WATCH_LIST_DIM_CHNG T3 ");
		sb.append("   WHERE T1.WatchList_Sub_Type_Cd = T2.WatchList_Sub_Type_Cd ");
		sb.append("   	AND T1.entity_watch_list_number = T3.entity_watch_list_number ");
		sb.append("     AND T1.change_current_ind = '").append(changeCurrentInd).append("'  ");
		sb.append("     AND T2.change_current_ind = '").append(changeCurrentInd).append("' AND T2.DELETE_IND='").append(deleteInd).append("'  ");
		sb.append("     AND T2.Screen_Process_Type_Cd = '").append(screenProcess).append("'  ");
		sb.append("     AND T3.change_current_ind = '").append(changeCurrentInd).append("' AND T3.delete_ind = '").append(deleteInd).append("' AND T3.exclude_ind = '").append(excludeInd).append("' ) A1  ");
		sb.append("   ) B1 ");
		sb.append("   where B1.Row between ").append(indexFrom).append(" and ").append(indexTo);

		
		HibernateQuery query = (HibernateQuery) entityManager.createNativeQuery(sb.toString());
		((HibernateQuery)query).getHibernateQuery().setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> tmpList = query.getResultList();
		Map<String, String> entityNameInfoMap = new HashMap<String, String>();
		for(Map<String, Object> obj : tmpList){
			String entityName = (String) (obj.get("ENTITY_NAME")==null? "": obj.get("ENTITY_NAME"));
			String entityTypeCd = (String) (obj.get("ENTITY_TYPE_CD")==null? "": obj.get("ENTITY_TYPE_CD"));
			entityNameInfoMap.put(entityName, entityTypeCd);
		}
		return entityNameInfoMap;
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
