package com.sas.db.aml.orm.ecm;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;

/**
 * The persistent class for the CASE_LIVE database table.
 * 
 */
@Entity
@Table(name="CASE_LIVE", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="CaseLive.findAll", query="SELECT c FROM CaseLive c")
public class CaseLive implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CASE_RK")
	private BigDecimal caseRk;
	
	@Column(name="VALID_FROM_DTTM")
	private Timestamp validFromDttm;
	
	@Column(name="VALID_TO_DTTM")
	private Timestamp validToDttm;
	
	@Column(name="CASE_ID")
	private String caseId;
	
	@Column(name="SOURCE_SYSTEM_CD")
	private String sourceSystemCd;
	
	@Column(name="CASE_TYPE_CD")
	private String caseTypeCd;
	
	@Column(name="CASE_CATEGORY_CD")
	private String caseCategoryCd;
	
	@Column(name="CASE_SUBCATEGORY_CD")
	private String caseSubcategoryCd;
	
	@Column(name="CASE_STATUS_CD")
	private String caseStatusCd;
	
	@Column(name="CASE_DISPOSITION_CD")
	private String caseDispositionCd;
	
	@Column(name="CASE_DESC")
	private String caseDesc;
	
	@Column(name="CASE_LINK_SK")
	private Float caseLinkSk;
	
	@Column(name="PRIORITY_CD")
	private String priorityCd;
	
	@Column(name="REGULATORY_RPT_RQD_FLG")
	private String regulatoryRptRqdFlg;
	
	@Column(name="INVESTIGATOR_USER_ID")
	private String investigatorUserId;
	
	@Column(name="OPEN_DTTM")
	private Timestamp openDttm;
	
	@Column(name="REOPEN_DTTM")
	private Timestamp reopenDttm;
	
	@Column(name="CLOSE_DTTM")
	private Timestamp closeDttm;
	
	@Column(name="UI_DEF_FILE_NM")
	private String uiDefFileNm;
	
	@Column(name="CREATE_USER_ID")
	private String createUserId;
	
	@Column(name="CREATE_DTTM")
	private Timestamp createDttm;
	
	@Column(name="UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name="VERSION_NO")
	private Float versionNo;
	
	@Column(name="DELETE_FLG")
	private String deleteFlg;
	

	public BigDecimal getCaseRk() {
		return caseRk;
	}

	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
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



	public String getCaseId() {
		return caseId;
	}



	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}



	public String getSourceSystemCd() {
		return sourceSystemCd;
	}



	public void setSourceSystemCd(String sourceSystemCd) {
		this.sourceSystemCd = sourceSystemCd;
	}



	public String getCaseTypeCd() {
		return caseTypeCd;
	}



	public void setCaseTypeCd(String caseTypeCd) {
		this.caseTypeCd = caseTypeCd;
	}



	public String getCaseCategoryCd() {
		return caseCategoryCd;
	}



	public void setCaseCategoryCd(String caseCategoryCd) {
		this.caseCategoryCd = caseCategoryCd;
	}



	public String getCaseSubcategoryCd() {
		return caseSubcategoryCd;
	}



	public void setCaseSubcategoryCd(String caseSubcategoryCd) {
		this.caseSubcategoryCd = caseSubcategoryCd;
	}



	public String getCaseStatusCd() {
		return caseStatusCd;
	}



	public void setCaseStatusCd(String caseStatusCd) {
		this.caseStatusCd = caseStatusCd;
	}



	public String getCaseDispositionCd() {
		return caseDispositionCd;
	}



	public void setCaseDispositionCd(String caseDispositionCd) {
		this.caseDispositionCd = caseDispositionCd;
	}



	public String getCaseDesc() {
		return caseDesc;
	}



	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}



	public float getCaseLinkSk() {
		return caseLinkSk;
	}



	public void setCaseLinkSk(float caseLinkSk) {
		this.caseLinkSk = caseLinkSk;
	}



	public String getPriorityCd() {
		return priorityCd;
	}



	public void setPriorityCd(String priorityCd) {
		this.priorityCd = priorityCd;
	}



	public String getRegulatoryRptRqdFlg() {
		return regulatoryRptRqdFlg;
	}



	public void setRegulatoryRptRqdFlg(String regulatoryRptRqdFlg) {
		this.regulatoryRptRqdFlg = regulatoryRptRqdFlg;
	}



	public String getInvestigatorUserId() {
		return investigatorUserId;
	}



	public void setInvestigatorUserId(String investigatorUserId) {
		this.investigatorUserId = investigatorUserId;
	}



	public Timestamp getOpenDttm() {
		return openDttm;
	}



	public void setOpenDttm(Timestamp openDttm) {
		this.openDttm = openDttm;
	}



	public Timestamp getReopenDttm() {
		return reopenDttm;
	}



	public void setReopenDttm(Timestamp reopenDttm) {
		this.reopenDttm = reopenDttm;
	}



	public Timestamp getCloseDttm() {
		return closeDttm;
	}



	public void setCloseDttm(Timestamp closeDttm) {
		this.closeDttm = closeDttm;
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



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CaseLive [caseRk=" + caseRk + ", validFromDttm=" + validFromDttm + ", validToDttm=" + validToDttm
				+ ", caseId=" + caseId + ", sourceSystemCd=" + sourceSystemCd + ", caseTypeCd=" + caseTypeCd
				+ ", caseCategoryCd=" + caseCategoryCd + ", caseSubcategoryCd=" + caseSubcategoryCd + ", caseStatusCd="
				+ caseStatusCd + ", caseDispositionCd=" + caseDispositionCd + ", caseDesc=" + caseDesc + ", caseLinkSk="
				+ caseLinkSk + ", priorityCd=" + priorityCd + ", regulatoryRptRqdFlg=" + regulatoryRptRqdFlg
				+ ", investigatorUserId=" + investigatorUserId + ", openDttm=" + openDttm + ", reopenDttm=" + reopenDttm
				+ ", closeDttm=" + closeDttm + ", uiDefFileNm=" + uiDefFileNm + ", createUserId=" + createUserId
				+ ", createDttm=" + createDttm + ", updateUserId=" + updateUserId + ", versionNo=" + versionNo
				+ ", deleteFlg=" + deleteFlg + "]";
	}
}
