package com.sas.db.wlf.dao.tf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sas.db.wlf.orm.tf.XInvExportCaseItem;
import com.sas.db.wlf.orm.tf.XInvImportCaseItem;

public class IXInvExportCaseItemDaoImpl implements IXInvExportCaseItemDaoCustom{

	@PersistenceContext
	private EntityManager entityManager;

	private int batchSize = 20;
	
	/* 批次Save
	 * @see com.sas.db.wlf.dao.tf.IXInvExportCaseItemDaoCustomer#batchSave(java.util.List)
	 */
	@Override
	public void batchSave(List<XInvExportCaseItem> caseItems){
		int i =0;
		for (XInvExportCaseItem item : caseItems) {
			if(entityManager.find(XInvExportCaseItem.class, item.getId()) != null){
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
