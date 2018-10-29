package com.sas.db.wlf.dao.tf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.orm.tf.XInvImport;

/**
 * @author meng
 * date 2017/12/15
 */
public class IXInvImportDaoImpl implements IXInvImportDaoCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<XInvImport> queryInvImportDataByFileKey(String fileKey) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT UNIQUE_KEY,	SEQ,	FILE_KEY,	CASE_RK,	SCR_NO,");
		sql.append(" [TYPE],	L_C_NO,	IB_NO,	BL_NO,	INVOICE_NO,");
		sql.append(" ITEM_SEQ,	HK_HS_CODE,	OUR_CUSTOMER,	APPLICANT,	CATEGORY_OF_GOODS,");
		sql.append(" DESCRIPTION_OF_GOODS,	CURRNECY,	UNIT_PRICE,	QUANTITY,	AMOUNT,");
		sql.append(" QUERY_PRICE,	CREATE_DTTM,	CREATE_USER ");
		sql.append(" FROM ")
		   .append(SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG).append(".NCSC.X_INV_IMPORT WHERE 1=1");
		sql.append(" AND FILE_KEY = :fileKey");
		
		Query query = entityManager.createNativeQuery(sql.toString(),XInvImport.class);
		query.setParameter("fileKey" , fileKey);
		
		return query.getResultList();
	}

}
