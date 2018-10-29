package com.sas.amlCheck.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.fcfcore.IFscCurrencyDimDao;
import com.sas.db.aml.dao.fcfcore.IXWatchlistSettingDao;
import com.sas.db.aml.orm.fcfcore.FscCurrencyDim;
import com.sas.db.aml.orm.fcfcore.XWatchlistSetting;

/**
 * Online Name Query(線上名單查詢)，type_dsce下拉式選單查詢程式
 * 
 * @author SAS
 *
 */
@Service
public class CurrencyOptionQueryService {
	private static final Logger logger = LoggerFactory.getLogger(CurrencyOptionQueryService.class);
	
	@Autowired
	IFscCurrencyDimDao iFscCurrencyDimDao;
	/**
	 *  查詢所有Currency Code資料
	 * @return FscCurrencyDim list
	 */
	public List<FscCurrencyDim> currencyOptionQueryService() {

		List<FscCurrencyDim> result= new ArrayList<FscCurrencyDim>();;
		try {
			String sql = iFscCurrencyDimDao.getCurrencyCodeQuerySql();
			result = iFscCurrencyDimDao.nativeSql(sql);
		} catch (Exception e) {
			logger.error(String.format("currencyOptionQueryService fail, exception : %s", e.getMessage()), e);
		}

		return result;
	}

}
