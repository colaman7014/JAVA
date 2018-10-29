package com.sas.db.wlf.dao.tf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.orm.tf.XInvExport;


/**
 * @author meng
 * date 2017/12/15
 */
public class IXInvExportDaoImpl implements IXInvExportDaoCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<XInvExport> queryInvExportDataByFileKey(String fileKey) {
		
		StringBuilder sql =new StringBuilder();
		sql.append("SELECT	UNIQUE_KEY,	SEQ,	FILE_KEY,	CASE_RK,	SCR_NO,");
		sql.append(" CURRNECY,	OUR_REF_NO,	SEQ_NO,	INVOICE_NO,	ITEM_SEQ,");
		sql.append(" HK_HS_CODE,	APPLICANT,	CATEGORY_OF_GOODS,	DESCRIPTION_OF_GOODS,	UNIT_PRICE,");
		sql.append(" INV_CURRENCY,	QUANTITY,	AMOUNT,	CREATE_DTTM,	CREATE_USER ");
		sql.append(" FROM ")
		   .append(SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG).append(".NCSC.X_INV_EXPORT WHERE 1=1");
		sql.append(" AND FILE_KEY = :fileKey");

		Query query = entityManager.createNativeQuery(sql.toString(),XInvExport.class);
		query.setParameter("fileKey" , fileKey);
		
		return query.getResultList();
	}

}
