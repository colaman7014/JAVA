package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the X_COMBO_WHITELIST_HIS database table.
 * 
 */
@Entity
@Table(name="X_COMBO_WHITELIST_HIS")
@NamedQuery(name="XComboWhitelistHis.findAll", query="SELECT x FROM XComboWhitelistHis x")
public class XComboWhitelistHis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CASE_ID")
	private String caseId;

	@Column(name="BENEFICIARY_ENTITY_WATCH_LIST_KEY")
	private String beneficiaryEntityWatchListKey;

	@Column(name="BRANCH")
	private String branch;

	@Column(name="CASE_ACTION")
	private String caseAction;

	@Column(name="CASE_DISPOSITION")
	private String caseDisposition;

	@Column(name="CLOSED_IND")
	private String closedInd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="ENTITY_DISPLAY_NAME")
	private String entityDisplayName;

	@Column(name="PARTY_NUMBER")
	private String partyNumber;

	public XComboWhitelistHis() {
	}

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getBeneficiaryEntityWatchListKey() {
		return this.beneficiaryEntityWatchListKey;
	}

	public void setBeneficiaryEntityWatchListKey(String beneficiaryEntityWatchListKey) {
		this.beneficiaryEntityWatchListKey = beneficiaryEntityWatchListKey;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public String getClosedInd() {
		return this.closedInd;
	}

	public void setClosedInd(String closedInd) {
		this.closedInd = closedInd;
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

	public String getEntityDisplayName() {
		return this.entityDisplayName;
	}

	public void setEntityDisplayName(String entityDisplayName) {
		this.entityDisplayName = entityDisplayName;
	}
	public String getPartyNumber() {
		return this.partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

}