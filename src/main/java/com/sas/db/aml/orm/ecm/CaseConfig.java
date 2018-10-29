package com.sas.db.aml.orm.ecm;

import java.io.Serializable;

import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the CASE_CONFIG database table.
 * 
 */
@Entity
@Table(name="CASE_CONFIG", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="CaseConfig.findAll", query="SELECT c FROM CaseConfig c")
public class CaseConfig implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@Column(name="CASE_CONFIG_SEQ_NO")
	private Long caseConfigSeqNo;

	@Column(name="CASE_CATEGORY_CD")
	private String caseCategoryCd;

	@Column(name="CASE_SUBCATEGORY_CD")
	private String caseSubcategoryCd;

	@Column(name="CASE_TYPE_CD")
	private String caseTypeCd;

	@Column(name="INVESTIGATE_WORKFLOW_DEF_NM")
	private String investigateWorkflowDefNm;

	@Column(name="INVESTIGATOR_USER_ID")
	private String investigatorUserId;

	@Column(name="REOPEN_WORKFLOW_DEF_NM")
	private String reopenWorkflowDefNm;

	@Column(name="UI_DEF_FILE_NM")
	private String uiDefFileNm;

	public CaseConfig() {
	}

	public Long getCaseConfigSeqNo() {
		return this.caseConfigSeqNo;
	}

	public void setCaseConfigSeqNo(Long caseConfigSeqNo) {
		this.caseConfigSeqNo = caseConfigSeqNo;
	}

	public String getCaseCategoryCd() {
		return this.caseCategoryCd;
	}

	public void setCaseCategoryCd(String caseCategoryCd) {
		this.caseCategoryCd = caseCategoryCd;
	}

	public String getCaseSubcategoryCd() {
		return this.caseSubcategoryCd;
	}

	public void setCaseSubcategoryCd(String caseSubcategoryCd) {
		this.caseSubcategoryCd = caseSubcategoryCd;
	}

	public String getCaseTypeCd() {
		return this.caseTypeCd;
	}

	public void setCaseTypeCd(String caseTypeCd) {
		this.caseTypeCd = caseTypeCd;
	}

	public String getInvestigateWorkflowDefNm() {
		return this.investigateWorkflowDefNm;
	}

	public void setInvestigateWorkflowDefNm(String investigateWorkflowDefNm) {
		this.investigateWorkflowDefNm = investigateWorkflowDefNm;
	}

	public String getInvestigatorUserId() {
		return this.investigatorUserId;
	}

	public void setInvestigatorUserId(String investigatorUserId) {
		this.investigatorUserId = investigatorUserId;
	}

	public String getReopenWorkflowDefNm() {
		return this.reopenWorkflowDefNm;
	}

	public void setReopenWorkflowDefNm(String reopenWorkflowDefNm) {
		this.reopenWorkflowDefNm = reopenWorkflowDefNm;
	}

	public String getUiDefFileNm() {
		return this.uiDefFileNm;
	}

	public void setUiDefFileNm(String uiDefFileNm) {
		this.uiDefFileNm = uiDefFileNm;
	}

}