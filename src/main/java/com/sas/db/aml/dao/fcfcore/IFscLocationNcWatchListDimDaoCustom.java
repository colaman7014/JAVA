package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import com.sas.db.aml.orm.fcfcore.FscLocationNcWatchListDim;

/**
 * NCSC-FSC_LOCATION_WATCH_LIST_DIM(主要黑名單檔) Custom DAO 方法
 * @author SAS
 *
 */
public interface IFscLocationNcWatchListDimDaoCustom {
	List<FscLocationNcWatchListDim> nativeSql(String sql);
	String getInculsiveQuerySql(List<String> inculsiveList);
}
