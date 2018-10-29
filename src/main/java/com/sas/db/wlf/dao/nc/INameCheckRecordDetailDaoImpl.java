package com.sas.db.wlf.dao.nc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;

/**
 * NCSC-NAME_CHECK_RECORD_DETAIL(Name Check 紀錄明細檔) Custom DAO 方法實作
 * @author SAS
 *
 */
public class INameCheckRecordDetailDaoImpl implements INameCheckRecordDetailDaoCustom {

	@PersistenceContext
	private EntityManager entityManager;

	private int batchSize = 20;
	
	@Override
	@Transactional
	public void batchSave(List<NameCheckRecordDetail> nameCheckRecordDetailList){
		int i =0;
		for (NameCheckRecordDetail nameCheckRecordDetail : nameCheckRecordDetailList) {
			if(entityManager.find(NameCheckRecordDetail.class, nameCheckRecordDetail.getId()) != null){
				entityManager.merge(nameCheckRecordDetail);
			}else{
				entityManager.persist(nameCheckRecordDetail);
			}
			i++;
			if (i % batchSize == 0 || i == nameCheckRecordDetailList.size()) {
				// Flush a batch of inserts and release memory.
				entityManager.flush();
				entityManager.clear();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NameCheckRecordDetail> queryNameCheckRecordDetailByUniqueKey(String uniqueKey) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT UNIQUE_KEY,	NC_REFERENCE_ID,	CHECK_SEQ,	ENTITY_TYPE,	ENTITY_RELATIONSHIP,");
		sql.append("ENTITY_RELATIONSHIP_DESC,	ENGLISH_NAME,	NON_ENGLISH_NAME,	CCC_CODE,	COUNTRY,");
		sql.append("ID_NUMBER,	BIC_SWIFT_CODE,	FREE_FORMAT_TEXT,	YEAR_OF_BIRTH,	GENDER,	CHECK_RESULT,");
		sql.append("ROUTE_RULE FROM	");
		sql.append(SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG).append(".NCSC.").append("NAME_CHECK_RECORD_DETAIL ");
		sql.append("WHERE 1=1 ");
		sql.append(" AND UNIQUE_KEY = :uniqueKey");
		
		Query query = entityManager.createNativeQuery(sql.toString(),NameCheckRecordDetail.class);
		query.setParameter("uniqueKey" , uniqueKey);
		
		return query.getResultList();
	}
}
