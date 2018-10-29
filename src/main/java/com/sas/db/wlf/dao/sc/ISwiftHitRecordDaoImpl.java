package com.sas.db.wlf.dao.sc;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sas.db.wlf.orm.sc.SwiftHitRecord;
/**
 * NCSC-SWIFT_HIT_RECORD(SWIFT Check 掃中名單紀錄檔) Custom DAO 實作
 * @author SAS
 *
 */
public class ISwiftHitRecordDaoImpl implements ISwiftHitRecordDaoCustom {

	@PersistenceContext
	private EntityManager entityManager;

	private int batchSize = 20;
	
	/**
	 * insert batch 方法控制
	 */
	@Override
	@Transactional
	public void batchSave(List<SwiftHitRecord> swiftHitRecordList){
		int i =0;
		for (SwiftHitRecord swiftHitRecord : swiftHitRecordList) {
			if(entityManager.find(SwiftHitRecord.class, swiftHitRecord.getId()) != null){
				entityManager.merge(swiftHitRecord);
			}else{
				entityManager.persist(swiftHitRecord);
			}
			
			i++;
			if (i % batchSize == 0 || i == swiftHitRecordList.size()) {
				// Flush a batch of inserts and release memory.
				entityManager.flush();
				entityManager.clear();
			}
		}
	}
	
}
