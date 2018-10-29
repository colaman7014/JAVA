package com.sas.db.wlf.orm;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the X_NCSC_CASE_RESULT_TMP database table.
 * 
 */
@Entity
@Table(name="X_NCSC_CASE_RESULT_TMP", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="XNcscCaseResultTmp.findAll", query="SELECT x FROM XNcscCaseResultTmp x")
public class XNcscCaseResultTmp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XNcscCaseResultTmpPK id;

	@Column(name="CHECK_RESULT")
	private String checkResult;

	@Column(name="CHECK_SEQ")
	private String checkSeq;

	@Column(name="VIEW_ID")
	private String viewId;

	@Column(name="WHITELIST_CASE_ID")
	private String whitelistCaseId;

	@Column(name="WHITELIST_IND")
	private String whitelistInd;

	public XNcscCaseResultTmp() {
	}
	
	public XNcscCaseResultTmp(XNcscCaseResult caseResult) {
		this.id = new XNcscCaseResultTmpPK(caseResult.getId());
		this.checkResult = caseResult.getCheckResult();
		this.checkSeq = caseResult.getCheckSeq();
		this.whitelistInd = caseResult.getWhitelistInd();
		this.whitelistCaseId = caseResult.getWhitelistCaseId();
	}

	public XNcscCaseResultTmpPK getId() {
		return this.id;
	}

	public void setId(XNcscCaseResultTmpPK id) {
		this.id = id;
	}

	public String getCheckResult() {
		return this.checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getCheckSeq() {
		return this.checkSeq;
	}

	public void setCheckSeq(String checkSeq) {
		this.checkSeq = checkSeq;
	}

	public String getViewId() {
		return this.viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getWhitelistCaseId() {
		return this.whitelistCaseId;
	}

	public void setWhitelistCaseId(String whitelistCaseId) {
		this.whitelistCaseId = whitelistCaseId;
	}

	public String getWhitelistInd() {
		return this.whitelistInd;
	}

	public void setWhitelistInd(String whitelistInd) {
		this.whitelistInd = whitelistInd;
	}

}