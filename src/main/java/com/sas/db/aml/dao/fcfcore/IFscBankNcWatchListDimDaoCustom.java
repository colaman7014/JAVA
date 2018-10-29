package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import com.sas.db.aml.orm.fcfcore.FscBankNcWatchListDim;

/**
 * NCSC-FSC_BANK_WATCH_LIST_DIM(黑名單銀行檔) Custom DAO 方法
 * @author SAS
 *
 */
public interface IFscBankNcWatchListDimDaoCustom {
	List<FscBankNcWatchListDim> nativeSql(String sql);
	String getInculsiveQuerySql(List<String> inculsiveList);
}
