package com.sas.db.wlf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.orm.XInvBlNcUploadRecord;


/**
 * @author meng
 * date 2017/12/04
 */
public class IXInvBlNcUploadRecordDaoImpl implements IXInvBlNcUploadRecordDaoCustomer{

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * To Query File Upload List By Status
	 * @param uploadType
	 * @param scanType
	 * @param startDate
	 * @param endDates
	 * @param scanStatus
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<XInvBlNcUploadRecord> queryFileUploadCondition(String createUser, String uploadType, String scanType,
			String startDate, String endDate, String scanStatus) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT FILE_KEY, UPLOAD_TYPE, SCAN_TYPE, SCAN_PERIOD_START, SCAN_PERIOD_END, EN_ORG_FILE, EN_RESULT_FILE, ");
		sql.append(" SCAN_STATUS, CREATE_USER, CREATE_TIME, UPDATE_TIME, FILE_PATH, FILE_NAME ,RESULT_PATH ,RESULT_FILE ");
		sql.append(" FROM ").append(SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG).append(".NCSC.X_INV_BL_NC_UPLOAD_RECORD WHERE 1=1");
		if(!StringUtils.isBlank(createUser)) {
			sql.append(" AND CREATE_USER = :createUser");
		}
		if(!StringUtils.isBlank(scanStatus)) {
			sql.append(" AND SCAN_STATUS = :scanStatus");
		}
		
		if(!StringUtils.isBlank(uploadType)){
			sql.append(" AND UPLOAD_TYPE = :uploadType");
		}
		
		if(!StringUtils.isBlank(scanType)){
			sql.append(" AND SCAN_TYPE = :scanType");
		}
		
		if(!StringUtils.isBlank(startDate) ) {
			sql.append(" AND CREATE_TIME >= :startDate");
		}
		if(!StringUtils.isBlank(endDate)){
			sql.append(" AND CREATE_TIME <= :endDate");
		}
		
		// To Set Query SQL Parameter
		Query query = entityManager.createNativeQuery(sql.toString(),XInvBlNcUploadRecord.class);
		if(!StringUtils.isBlank(createUser)) {
			query.setParameter("createUser" , createUser);
		}
		if(!StringUtils.isBlank(uploadType)){
			query.setParameter("uploadType" , uploadType);
		} 
		if(!StringUtils.isBlank(scanType)){
			query.setParameter("scanType" , scanType);
		}
		if(!StringUtils.isBlank(scanStatus)) {
			query.setParameter("scanStatus" , scanStatus);
		}
		if(!StringUtils.isBlank(startDate) 
				&& !StringUtils.isBlank(endDate)){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		return query.getResultList();
	}

	@Override
	public List<XInvBlNcUploadRecord> queryFileUploadConditionByUsers(List<String> createUsers, String uploadType,
			String scanType, String startDate, String endDate, String scanStatus) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT FILE_KEY, UPLOAD_TYPE, SCAN_TYPE, SCAN_PERIOD_START, SCAN_PERIOD_END, EN_ORG_FILE, EN_RESULT_FILE, ");
		sql.append(" SCAN_STATUS, CREATE_USER, CREATE_TIME, UPDATE_TIME, FILE_PATH, FILE_NAME ,RESULT_PATH ,RESULT_FILE ");
		sql.append(" FROM ").append(SwiftMtConst.COM_SAS_JPACFG_NCSC_CATALOG).append(".NCSC.X_INV_BL_NC_UPLOAD_RECORD WHERE 1=1");
		if(!createUsers.isEmpty()) {
			sql.append(" AND CREATE_USER in :createUsers");
		}
		if(!StringUtils.isBlank(scanStatus)) {
			sql.append(" AND SCAN_STATUS = :scanStatus");
		}
		
		if(!StringUtils.isBlank(uploadType)){
			sql.append(" AND UPLOAD_TYPE = :uploadType");
		}
		
		if(!StringUtils.isBlank(scanType)){
			sql.append(" AND SCAN_TYPE = :scanType");
		}
		
		if(!StringUtils.isBlank(startDate) ) {
			sql.append(" AND CREATE_TIME >= :startDate");
		}
		if(!StringUtils.isBlank(endDate)){
			sql.append(" AND CREATE_TIME <= :endDate");
		}
		
		// To Set Query SQL Parameter
		Query query = entityManager.createNativeQuery(sql.toString(),XInvBlNcUploadRecord.class);
		if(!createUsers.isEmpty()) {
			query.setParameter("createUsers" , createUsers);
		}
		if(!StringUtils.isBlank(uploadType)){
			query.setParameter("uploadType" , uploadType);
		} 
		if(!StringUtils.isBlank(scanType)){
			query.setParameter("scanType" , scanType);
		}
		if(!StringUtils.isBlank(scanStatus)) {
			query.setParameter("scanStatus" , scanStatus);
		}
		if(!StringUtils.isBlank(startDate) 
				&& !StringUtils.isBlank(endDate)){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		return query.getResultList();
	}

}
