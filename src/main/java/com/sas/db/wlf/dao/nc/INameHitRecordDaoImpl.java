package com.sas.db.wlf.dao.nc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sas.db.wlf.orm.nc.NameHitRecord;

/**
 * NCSC-NAME_HIT_RECORD(Name Check 掃中名單紀錄檔) Custom DAO 方法實作
 * @author SAS
 *
 */
public class INameHitRecordDaoImpl implements INameHitRecordDaoCustom {

	@PersistenceContext
	private EntityManager entityManager;

	private int batchSize = 20;
	
    /**
     * batch insert 方法
     */
	@Override
	@Transactional
	public void batchSave(List<NameHitRecord> nameHitRecordList){
		int i =0;
		for (NameHitRecord nameHitRecord : nameHitRecordList) {
			if(entityManager.find(NameHitRecord.class, nameHitRecord.getId()) != null){
				entityManager.merge(nameHitRecord);
			}else{
				entityManager.persist(nameHitRecord);
			}
			i++;
			if (i % batchSize == 0 || i == nameHitRecordList.size()) {
				// Flush a batch of inserts and release memory.
				entityManager.flush();
				entityManager.clear();
			}
		}
	}
}
