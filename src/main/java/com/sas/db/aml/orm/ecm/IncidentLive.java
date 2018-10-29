package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;

/**
 * The persistent class for the INCIDENT_LIVE database table.
 * 
 */
@Entity
@Table(name="INCIDENT_LIVE", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
public class IncidentLive implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="INCIDENT_RK")
	private BigDecimal incidentRk;
	
	@Column(name="VALID_FROM_DTTM")
	private Timestamp validFromDttm;
	
	@Column(name="VALID_TO_DTTM")
	private Timestamp validToDttm;
	
	@Column(name="CASE_RK")
	private BigDecimal caseRk;
	
	@Column(name="INCIDENT_ID")
	private String incidentId;
	
	@Column(name="SOURCE_SYSTEM_CD")
	private String sourceSystemCd;
	
	@Column(name="INCIDENT_TYPE_CD")
	private String incidentTypeCd;
	
	@Column(name="INCIDENT_CATEGORY_CD")
	private String incidentCategoryCd;
	
	@Column(name="INCIDENT_SUBCATEGORY_CD")
	private String incidentSubcategoryCd;
	
	@Column(name="INCIDENT_DESC")
	private String incidentDesc;
	
	@Column(name="INCIDENT_FROM_DT")
	private Date incidentFromDt;
	
	@Column(name="INCIDENT_FROM_TM")
	private String incidentFromTm;
	
	@Column(name="INCIDENT_TO_DT")
	private Date incidentToDt;
	
	@Column(name="INCIDENT_TO_TM")
	private String incidentToTm;
	
	@Column(name="DETECTION_DT")
	private Date detectionDt;
	
	@Column(name="DETECTION_TM")
	private String detectionTm;
	
	@Column(name="NOTIFICATION_DT")
	private Date notificationDt;
	
	@Column(name="NOTIFICATION_TM")
	private String notificationTm;
	
	@Column(name="UI_DEF_FILE_NM")
	private String uiDefFileNm;
	
	@Column(name="CREATE_USER_ID")
	private String createUserId;
	
	@Column(name="CREATE_DTTM")
	private Timestamp createDttm;
	
	@Column(name="UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name="VERSION_NO")
	private float versionNo;
	
	@Column(name="DELETE_FLG")
	private String deleteFlg;
	
	@Column(name="INVESTIGATOR_USER_ID")
	private String investigatorUserId;
	
	@Column(name="INCIDENT_DISPOSITION_CD")
	private String incidentDispositionCd;
	
	@Column(name="CLOSE_DTTM")
	private Timestamp closeDttm;
	
	@Column(name="INCIDENT_STATUS_CD")
	private String incidentStatusCd;
	

	
	public BigDecimal getIncidentRk() {
		return incidentRk;
	}



	public void setIncidentRk(BigDecimal incidentRk) {
		this.incidentRk = incidentRk;
	}



	public Timestamp getValidFromDttm() {
		return validFromDttm;
	}



	public void setValidFromDttm(Timestamp validFromDttm) {
		this.validFromDttm = validFromDttm;
	}



	public Timestamp getValidToDttm() {
		return validToDttm;
	}



	public void setValidToDttm(Timestamp validToDttm) {
		this.validToDttm = validToDttm;
	}



	public BigDecimal getCaseRk() {
		return caseRk;
	}



	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
	}



	public String getIncidentId() {
		return incidentId;
	}



	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}



	public String getSourceSystemCd() {
		return sourceSystemCd;
	}



	public void setSourceSystemCd(String sourceSystemCd) {
		this.sourceSystemCd = sourceSystemCd;
	}



	public String getIncidentTypeCd() {
		return incidentTypeCd;
	}



	public void setIncidentTypeCd(String incidentTypeCd) {
		this.incidentTypeCd = incidentTypeCd;
	}



	public String getIncidentCategoryCd() {
		return incidentCategoryCd;
	}



	public void setIncidentCategoryCd(String incidentCategoryCd) {
		this.incidentCategoryCd = incidentCategoryCd;
	}



	public String getIncidentSubcategoryCd() {
		return incidentSubcategoryCd;
	}



	public void setIncidentSubcategoryCd(String incidentSubcategoryCd) {
		this.incidentSubcategoryCd = incidentSubcategoryCd;
	}



	public String getIncidentDesc() {
		return incidentDesc;
	}



	public void setIncidentDesc(String incidentDesc) {
		this.incidentDesc = incidentDesc;
	}



	public Date getIncidentFromDt() {
		return incidentFromDt;
	}



	public void setIncidentFromDt(Date incidentFromDt) {
		this.incidentFromDt = incidentFromDt;
	}



	public String getIncidentFromTm() {
		return incidentFromTm;
	}



	public void setIncidentFromTm(String incidentFromTm) {
		this.incidentFromTm = incidentFromTm;
	}



	public Date getIncidentToDt() {
		return incidentToDt;
	}



	public void setIncidentToDt(Date incidentToDt) {
		this.incidentToDt = incidentToDt;
	}



	public String getIncidentToTm() {
		return incidentToTm;
	}



	public void setIncidentToTm(String incidentToTm) {
		this.incidentToTm = incidentToTm;
	}



	public Date getDetectionDt() {
		return detectionDt;
	}



	public void setDetectionDt(Date detectionDt) {
		this.detectionDt = detectionDt;
	}



	public String getDetectionTm() {
		return detectionTm;
	}



	public void setDetectionTm(String detectionTm) {
		this.detectionTm = detectionTm;
	}



	public Date getNotificationDt() {
		return notificationDt;
	}



	public void setNotificationDt(Date notificationDt) {
		this.notificationDt = notificationDt;
	}



	public String getNotificationTm() {
		return notificationTm;
	}



	public void setNotificationTm(String notificationTm) {
		this.notificationTm = notificationTm;
	}



	public String getUiDefFileNm() {
		return uiDefFileNm;
	}



	public void setUiDefFileNm(String uiDefFileNm) {
		this.uiDefFileNm = uiDefFileNm;
	}



	public String getCreateUserId() {
		return createUserId;
	}



	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}



	public Timestamp getCreateDttm() {
		return createDttm;
	}



	public void setCreateDttm(Timestamp createDttm) {
		this.createDttm = createDttm;
	}



	public String getUpdateUserId() {
		return updateUserId;
	}



	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}



	public float getVersionNo() {
		return versionNo;
	}



	public void setVersionNo(float versionNo) {
		this.versionNo = versionNo;
	}



	public String getDeleteFlg() {
		return deleteFlg;
	}



	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}



	public String getInvestigatorUserId() {
		return investigatorUserId;
	}



	public void setInvestigatorUserId(String investigatorUserId) {
		this.investigatorUserId = investigatorUserId;
	}



	public String getIncidentDispositionCd() {
		return incidentDispositionCd;
	}



	public void setIncidentDispositionCd(String incidentDispositionCd) {
		this.incidentDispositionCd = incidentDispositionCd;
	}



	public Timestamp getCloseDttm() {
		return closeDttm;
	}



	public void setCloseDttm(Timestamp closeDttm) {
		this.closeDttm = closeDttm;
	}



	public String getIncidentStatusCd() {
		return incidentStatusCd;
	}



	public void setIncidentStatusCd(String incidentStatusCd) {
		this.incidentStatusCd = incidentStatusCd;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
