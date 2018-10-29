package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the X_PARTY_WHITELIST_MAIN database table.
 * 
 */
@Entity
@Table(name="X_PARTY_WHITELIST_MAIN")
@NamedQuery(name="XPartyWhitelistMain.findAll", query="SELECT x FROM XPartyWhitelistMain x")
public class XPartyWhitelistMain implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XPartyWhitelistMainPK id;

	@Column(name="CASE_ACTION")
	private String caseAction;

	@Column(name="CASE_DISPOSITION")
	private String caseDisposition;

	@Column(name="CASE_ID")
	private String caseId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="IS_WHITELIST")
	private String isWhitelist;

	@Column(name="REMARK")
	private String remark;

	public XPartyWhitelistMain() {
	}

	public XPartyWhitelistMainPK getId() {
		return this.id;
	}

	public void setId(XPartyWhitelistMainPK id) {
		this.id = id;
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

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
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

	public String getIsWhitelist() {
		return this.isWhitelist;
	}

	public void setIsWhitelist(String isWhitelist) {
		this.isWhitelist = isWhitelist;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}