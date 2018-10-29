package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import com.sas.db.aml.orm.fcfcore.FscEntityScWatchListDim;

/**
 * NCSC-FSC_ENTITY_WATCH_LIST_DIM(主要黑名單檔) Custom DAO 方法
 * @author SAS
 *
 */
public interface IFscEntityScWatchListDimDaoCustom {
	List<FscEntityScWatchListDim> nativeSql(String sql);
	String getInculsiveQuerySql(List<String> inculsiveList);
}
