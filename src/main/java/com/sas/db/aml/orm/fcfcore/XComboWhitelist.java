package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the X_COMBO_WHITELIST database table.
 * 
 */
@Entity
@Table(name="X_COMBO_WHITELIST")
@NamedQuery(name="XComboWhitelist.findAll", query="SELECT x FROM XComboWhitelist x")
public class XComboWhitelist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XComboWhitelistPK id;

	@Column(name="BRANCH")
	private String branch;

	@Column(name="CASE_ID")
	private String caseId;

	@Column(name="CHANGE_CURRENT_IND")
	private String changeCurrentInd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="ENTITY_DISPLAY_NAME")
	private String entityDisplayName;

	public XComboWhitelist() {
	}

	public XComboWhitelistPK getId() {
		return this.id;
	}

	public void setId(XComboWhitelistPK id) {
		this.id = id;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getChangeCurrentInd() {
		return this.changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
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

}