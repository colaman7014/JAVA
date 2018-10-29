package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscCurrencyDim;

public interface IFscCurrencyDimDao extends CrudRepository<FscCurrencyDim, Long>,IFscCurrencyDimDaoCustom{
	public List<FscCurrencyDim> findAll();
	public List<FscCurrencyDim> findByCurrencyKey(String currencyKey);
	
}