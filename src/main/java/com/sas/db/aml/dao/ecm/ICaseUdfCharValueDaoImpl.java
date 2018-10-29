package com.sas.db.aml.dao.ecm;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sas.db.aml.orm.ecm.CaseUdfCharValue;

/**
 * AML.ECM.CASE_UDF_CHAR_VALUE(Create Case使用) Custom DAO 實作
 * @author SAS
 *
 */
public class ICaseUdfCharValueDaoImpl implements ICaseUdfCharValueDaoCustom {

	
	@PersistenceContext
	private EntityManager entityManager;

	private int batchSize = 20;
	
	@Override
	public void batchSave(List<CaseUdfCharValue> caseUdfCharValueList){
		int i =0;
		for (CaseUdfCharValue caseUdfCharValue : caseUdfCharValueList) {
			if(entityManager.find(CaseUdfCharValue.class, caseUdfCharValue.getId()) != null){
				entityManager.merge(caseUdfCharValue);
			}else{
				entityManager.persist(caseUdfCharValue);
			}
			i++;
			if (i % batchSize == 0 || i == caseUdfCharValueList.size()) {
				// Flush a batch of inserts and release memory.
				entityManager.flush();
				entityManager.clear();
			}
		}
	}

}
