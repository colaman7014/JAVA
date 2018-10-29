package com.sas.db.wlf.dao.tf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.orm.tf.XBlExport;
import com.sas.db.wlf.orm.tf.XOnlineNamecheckUpload;
import com.sas.db.wlf.orm.tf.XOnlineNamecheckUploadPK;

/**
 * NCSC-X_ONLINE_NAMECHECK_UPLOAD(ONLINE_UPLOAD) DAO
 * @author SAS
 *
 */
public class IXOnlineNameCheckUploadDaoImpl{
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * To Query B/L Export Data List 
	 * @param fileKey
	 */
	@SuppressWarnings("unchecked")
	public List<XOnlineNamecheckUpload> queryOnlineMulitDataByFileKey(String fileKey) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ")
		   .append(SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG).append(".NCSC.X_ONLINE_NAMECHECK_UPLOAD WHERE 1=1");
		sql.append(" AND FILE_KEY = :fileKey");
		
		Query query = entityManager.createNativeQuery(sql.toString(),XOnlineNamecheckUpload.class);
		query.setParameter("fileKey" , fileKey);
		
		return query.getResultList();
	}}
