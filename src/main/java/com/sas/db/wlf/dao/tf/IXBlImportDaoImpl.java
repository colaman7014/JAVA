package com.sas.db.wlf.dao.tf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.orm.tf.XBlImport;

/**
 * @author meng
 * date 2017/12/15
 */
public class IXBlImportDaoImpl implements IXBlImportDaoCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<XBlImport> queryBlImportDataByFileKey(String fileKey) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT	UNIQUE_KEY,	SEQ,	FILE_KEY,	SCR_NO,	[TYPE],");
		sql.append("L_C_NO,	IB_NO,	BL_NO,	SHIPPER,	CONSIGNEE,");
		sql.append("NOTIFY_PARTY,	SECOND_NOTIFY_PARTY,	SHIPPING_COMPANY,	FORWORDER,	SHIPPING_AGENT,");
		sql.append("COUNTRY_OF_ORIGIN,	PLACE_OF_RECEIPT,	COUNTRY_OF_RECEIPT,	PLACE_OF_DELIEVERY,	COUNTRY_OF_DELIVERY,");
		sql.append("VESSEL,	PRE_CARRIAGE_VESSEL,	PORT_OF_LANDING,	PORT_OF_DISCHARGE,	COUNTRY_OF_LANDING,");
		sql.append("COUNTRY_OF_DISCHARGE,	TRANSHIPMENT_PORT,	COUNTRY_OF_TRANSHIPMENT,	CREATE_DTTM,	CREATE_USER,");
		sql.append("CASE_RK FROM ")
		   .append(SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG).append(".NCSC.X_BL_IMPORT WHERE 1=1");
		sql.append(" AND FILE_KEY = :fileKey");

		Query query = entityManager.createNativeQuery(sql.toString(),XBlImport.class);
		query.setParameter("fileKey" , fileKey);
		
		return query.getResultList();
	}
	
}
