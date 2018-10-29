package com.sas.db.wlf.orm;

import java.io.Serializable;

import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the X_NCSC_CASE_RESULT database table.
 * 
 */
@Entity
@Table(name="X_NCSC_CASE_RESULT", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="XNcscCaseResult.findAll", query="SELECT x FROM XNcscCaseResult x")
public class XNcscCaseResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XNcscCaseResultPK id;

	@Column(name="CHECK_RESULT")
	private String checkResult;
	
	@Column(name="CHECK_SEQ")
	private String checkSeq;

	@Column(name="WHITELIST_CASE_ID")
	private String whitelistCaseId;

	@Column(name="WHITELIST_IND")
	private String whitelistInd;
	

	public XNcscCaseResult() {
	}
	
	public XNcscCaseResult(XNcscCaseResultTmp caseResultTmp) {
		this.id = new XNcscCaseResultPK(caseResultTmp.getId());
		this.checkResult = caseResultTmp.getCheckResult();
		this.checkSeq = caseResultTmp.getCheckSeq();
		this.whitelistInd = caseResultTmp.getWhitelistInd();
		this.whitelistCaseId = caseResultTmp.getWhitelistCaseId();
	}

	public XNcscCaseResultPK getId() {
		return this.id;
	}

	public void setId(XNcscCaseResultPK id) {
		this.id = id;
	}

	public String getCheckResult() {
		return this.checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
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

	public String getCheckSeq() {
		return checkSeq;
	}

	public void setCheckSeq(String checkSeq) {
		this.checkSeq = checkSeq;
	}

	@Override
	public String toString() {
		return "XNcscCaseResult [id=" + id + ", checkResult=" + checkResult + ", checkSeq=" + checkSeq
				+ ", whitelistCaseId=" + whitelistCaseId + ", whitelistInd=" + whitelistInd + "]";
	}
	
	
	
}