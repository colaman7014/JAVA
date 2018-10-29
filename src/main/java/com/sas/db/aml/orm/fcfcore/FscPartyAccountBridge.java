package com.sas.db.aml.orm.fcfcore;
import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FSC_PARTY_ACCOUNT_BRIDGE database table.
 * 
 */
@Entity
@Table(name="FSC_PARTY_ACCOUNT_BRIDGE")
@NamedQuery(name="FscPartyAccountBridge.findAll", query="SELECT f FROM FscPartyAccountBridge f")
public class FscPartyAccountBridge implements Serializable {
	private static final long serialVersionUID = 1L;


	@EmbeddedId
	private FscPartyAccountBridgePK id;

	@Column(name="change_current_ind")
	private String changeCurrentInd;

	@Column(name="change_end_date")
	private Timestamp changeEndDate;


	@Column(name="role_desc")
	private String roleDesc;


	public FscPartyAccountBridge() {
	}


	public String getChangeCurrentInd() {
		return this.changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	public Timestamp getChangeEndDate() {
		return this.changeEndDate;
	}

	public void setChangeEndDate(Timestamp changeEndDate) {
		this.changeEndDate = changeEndDate;
	}


	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}