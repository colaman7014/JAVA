package com.sas.db.aml.dao.fcfcore;

import java.util.List;
import java.util.Map;

import com.sas.db.aml.orm.fcfcore.FscEntityWatchListDimChng;



/**
 * NCSC-FSC_ENTITY_WATCH_LIST_DIM(主要黑名單檔) Custom DAO 方法
 * @author SAS
 *
 */
public interface IFscEntityWatchListDimChngDaoCustom {
	List<FscEntityWatchListDimChng> nativeSql(String sql);
	String getInculsiveQuerySql(List<String> inculsiveList);
	List<String> queryEntityNameCandidate();
	int queryCountEntityNameCandidate(String changeCurrentInd, String deleteInd, String excludeInd,String screenProcess);
	Map<String, String> queryCountEntityNameCandidate(String changeCurrentInd, String deleteInd, String excludeInd,
			String screenProcess, int indexFrom, int indexTo);
}
