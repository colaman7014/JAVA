package com.sas.db.wlf.dao.tf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sas.db.wlf.orm.tf.XInvImportCaseItem;

public class IXInvImportCaseItemDaoImpl implements IXInvImportCaseItemDaoCustom{

	@PersistenceContext
	private EntityManager entityManager;

	private int batchSize = 20;
	
	/* 批次新增
	 * @see com.sas.db.wlf.dao.tf.IXInvImportCaseItemDaoCustomer#batchSave(java.util.List)
	 */
	@Override
	public void batchSave(List<XInvImportCaseItem> caseItems){
		int i =0;
		for (XInvImportCaseItem item : caseItems) {
			if(entityManager.find(XInvImportCaseItem.class, item.getId()) != null){
				entityManager.merge(item);
			}else{
				entityManager.persist(item);
			}
			i++;
			if (i % batchSize == 0 || i == caseItems.size()) {
				// Flush a batch of inserts and release memory.
				entityManager.flush();
				entityManager.clear();
			}
		}
	}

}
