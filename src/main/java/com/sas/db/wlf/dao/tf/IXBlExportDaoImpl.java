package com.sas.db.wlf.dao.tf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.orm.tf.XBlExport;

/**
 * @author meng
 * date 2017/12/15
 */
public class IXBlExportDaoImpl implements IXBlExportDaoCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * To Query B/L Export Data List 
	 * @param fileKey
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<XBlExport> queryBlExportDataByFileKey(String fileKey) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT	UNIQUE_KEY,	SEQ,	FILE_KEY,	SCR_NO,	L_C_NO,");
		sql.append("CURRENCY,	OUR_REF_NO,	SEQ_NO,	BL_NO,	SHIPPER,");
		sql.append("CONSIGNEE,	NOTIFY_PARTY,	SECOND_NOTIFY_PARTY,	SHIPPING_COMPANY,	FORWORDER,");
		sql.append("SHIPPING_AGENT,	COUNTRY_OF_ORIGIN,	PLACE_OF_RECEIPT,	COUNTRY_OF_RECEIPT,	PLACE_OF_DELIEVERY,");
		sql.append("COUNTRY_OF_DELIVERY,	VESSEL,	PRE_CARRIAGE_VESSEL,	PORT_OF_LANDING,	COUNTRY_OF_LANDING,");
		sql.append("PORT_OF_DISCHARGE,	COUNTRY_OF_DISCHARGE,	TRANSHIPMENT_PORT,	COUNTRY_OF_TRANSHIPMENT,	CREATE_DTTM,");
		sql.append("CREATE_USER,CASE_RK FROM ")
		   .append(SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG).append(".NCSC.X_BL_EXPORT WHERE 1=1");
		sql.append(" AND FILE_KEY = :fileKey");
		
		Query query = entityManager.createNativeQuery(sql.toString(),XBlExport.class);
		query.setParameter("fileKey" , fileKey);
		
		return query.getResultList();
	}

}
