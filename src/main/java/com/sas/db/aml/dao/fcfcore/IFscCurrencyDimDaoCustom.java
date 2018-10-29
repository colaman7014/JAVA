package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import com.sas.db.aml.orm.fcfcore.FscCurrencyDim;


public interface IFscCurrencyDimDaoCustom {
	List<FscCurrencyDim> nativeSql(String sql);
	String getCurrencyCodeQuerySql();
}
