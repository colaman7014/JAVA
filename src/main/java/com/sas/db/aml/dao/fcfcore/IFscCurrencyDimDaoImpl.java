package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sas.db.aml.orm.fcfcore.FscBankWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscCurrencyDim;
import com.sas.util.AmlConfiguration;
import com.sas.util.StringUtils;


public class IFscCurrencyDimDaoImpl implements IFscCurrencyDimDaoCustom {

	@PersistenceContext(unitName = "amlEntityManagerFactory")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<FscCurrencyDim> nativeSql(String sql) {
		
		Query query = entityManager.createNativeQuery(sql, FscCurrencyDim.class);
		List<FscCurrencyDim> resultList = (List<FscCurrencyDim>)query.getResultList();
		
		return resultList;
	}

	@Override
	public String getCurrencyCodeQuerySql(){
		StringBuffer sb = new StringBuffer();
		String select = "   SELECT * ";
		String from = String.format("FROM %s.%s.FSC_CURRENCY_DIM   ", AmlConfiguration.getAmlCatalog(), AmlConfiguration.getFcfcoreSchema());
		String where = String.format(" where CHARINDEX('DEPRECATED', currency_name) = 0 order by currency_code");		
		return sb.append(select).append(from).append(where).toString();
	}


}
