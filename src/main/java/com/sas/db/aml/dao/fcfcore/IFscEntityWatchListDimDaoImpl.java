package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sas.db.aml.orm.fcfcore.FscEntityWatchListDim;
import com.sas.util.AmlConfiguration;
import com.sas.util.StringUtils;

/**
 * NCSC-FSC_BANK_WATCH_LIST_DIM(主要黑名單檔) Custom DAO 方法實作
 * @author SAS
 *
 */
public class IFscEntityWatchListDimDaoImpl implements IFscEntityWatchListDimDaoCustom {

	@PersistenceContext(unitName = "amlEntityManagerFactory")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<FscEntityWatchListDim> nativeSql(String sql) {
		
		Query query = entityManager.createNativeQuery(sql, FscEntityWatchListDim.class);
		List<FscEntityWatchListDim> resultList = (List<FscEntityWatchListDim>)query.getResultList();
		
		return resultList;
	}

	/**
	 * 組合Inculsive SQL方法
	 */
	@Override
	public String getInculsiveQuerySql(List<String> inculsiveList){
		StringBuffer sb = new StringBuffer();
//		String select = "   SELECT ENTITY_WATCH_LIST_KEY, ENTITY_NAME, IDENTIFICATION_ID, CITIZENSHIP_COUNTRY_CODE, YEAR_OF_BIRTH, WATCH_LIST_NAME, CATEGORY_DESC   ";
		String select = "   SELECT * ";
		String from = String.format("   FROM %s.%s.FSC_ENTITY_WATCH_LIST_DIM   ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
		
		StringBuffer csb = new StringBuffer();
		for(String str : inculsiveList){
			csb.append(String.format(" OR CONTAINS(ENTITY_NAME, '%s')", StringUtils.handelEscape(str)));
		}
		String andInculsiveOr = String.format(" AND (%s)", csb.substring(3));
		
		String where = String.format("   WHERE CHANGE_CURRENT_IND = 'Y' AND DELETE_IND = 'N' AND EXCLUDE_IND = 'N' %s  ", andInculsiveOr);
		return sb.append(select).append(from).append(where).toString();
	}
}
