package com.sas.db.wlf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sas.db.wlf.orm.XNcscCaseResultTmp;

public class IXNcscCaseResultTmpDaoImpl implements IXNcscCaseResultTmpDaoCustomer {

	@PersistenceContext
	private EntityManager entityManager;

	private int batchSize = 20;
	
	@Override
	public void deleteBatch(List<XNcscCaseResultTmp> caseResultLst){
		int i =0;
		for (XNcscCaseResultTmp caseResult : caseResultLst) {
			if(entityManager.find(XNcscCaseResultTmp.class, caseResult.getId()) != null){
				entityManager.remove(caseResult);
			}
			i++;
			if (i % batchSize == 0 || i == caseResultLst.size()) {
				// Flush a batch of inserts and release memory.
				entityManager.flush();
				entityManager.clear();
			}
		}
	}

}
