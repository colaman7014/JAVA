package com.sas.db.wlf.dao.nc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;

public class INameCheckRecordMainDaoImpl implements iNameCheckRecordMainDaoCoustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NameCheckRecordMain> queryNameCheckRecordByUniqueKey(String uniqueKey) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 	INTERFACE_NAME,	CALLING_SYSTEM,	SCREEN_PROCESS,	CALLING_USER,	BUSINESS_UNIT_ID,");
		sql.append(" BRANCH_NUMBER,	GEN_ALERT_FLAG,	TRANSACTION_DATE,	UNIQUE_KEY,	REFERENCE_NUMBER,");
		sql.append(" NC_REFERENCE_ID,	NC_RESULT,	ROUTE_RULE,	HIT_SEQ,	NC_CLOSE_REASON,");
		sql.append(" PARTY_NUMBER,	CASE_RK,	PEP_FLAG,	PNMP_FLAG ");
		sql.append(" FROM ").append(SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG).append(".NCSC.NAME_CHECK_RECORD_MAIN ");
		sql.append(" WHERE 1=1");
		sql.append(" AND UNIQUE_KEY = :uniqueKey");
		
		Query query = entityManager.createNativeQuery(sql.toString(),NameCheckRecordMain.class);
		query.setParameter("uniqueKey" , uniqueKey);
		
		return query.getResultList();
	}

}
