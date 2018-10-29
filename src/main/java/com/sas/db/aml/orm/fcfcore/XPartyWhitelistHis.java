package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the X_PARTY_WHITELIST_HIS database table.
 * 
 */
@Entity
@Table(name="X_PARTY_WHITELIST_HIS")
@NamedQuery(name="XPartyWhitelistHis.findAll", query="SELECT x FROM XPartyWhitelistHis x")
public class XPartyWhitelistHis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CASE_ID")
	private String caseId;

	@Column(name="CASE_ACTION")
	private String caseAction;

	@Column(name="CASE_DISPOSITION")
	private String caseDisposition;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="PARTY_NAME")
	private String partyName;

	@Column(name="PARTY_NUMBER")
	private String partyNumber;

	@Column(name="REMARK")
	private String remark;

	public XPartyWhitelistHis() {
	}

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseAction() {
		return this.caseAction;
	}

	public void setCaseAction(String caseAction) {
		this.caseAction = caseAction;
	}

	public String getCaseDisposition() {
		return this.caseDisposition;
	}

	public void setCaseDisposition(String caseDisposition) {
		this.caseDisposition = caseDisposition;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getPartyName() {
		return this.partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyNumber() {
		return this.partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}