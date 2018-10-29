package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FSC_PARTY_ACCOUNT_BRIDGE database table.
 * 
 */
@Embeddable
public class FscPartyAccountBridgePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="account_key")
	private BigDecimal accountKey;
	
	@Column(name="party_key")
	private BigDecimal partyKey;
	
	@Column(name="role_key")
	private BigDecimal roleKey;

	@Column(name="change_begin_date")
	private Timestamp changeBeginDate;
	
	@Column(name="segment_id")
	private String segmentId;

	

	public FscPartyAccountBridgePK() {
	}

	public BigDecimal getAccountKey() {
		return this.accountKey;
	}

	public void setAccountKey(BigDecimal accountKey) {
		this.accountKey = accountKey;
	}

	public Timestamp getChangeBeginDate() {
		return this.changeBeginDate;
	}

	public void setChangeBeginDate(Timestamp changeBeginDate) {
		this.changeBeginDate = changeBeginDate;
	}

	public BigDecimal getPartyKey() {
		return this.partyKey;
	}

	public void setPartyKey(BigDecimal partyKey) {
		this.partyKey = partyKey;
	}

	public BigDecimal getRoleKey() {
		return this.roleKey;
	}

	public void setRoleKey(BigDecimal roleKey) {
		this.roleKey = roleKey;
	}

	public String getSegmentId() {
		return this.segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountKey == null) ? 0 : accountKey.hashCode());
		result = prime * result + ((partyKey == null) ? 0 : partyKey.hashCode());
		result = prime * result + ((roleKey == null) ? 0 : roleKey.hashCode());
		result = prime * result + ((changeBeginDate == null) ? 0 : changeBeginDate.hashCode());
		result = prime * result + ((segmentId == null) ? 0 : segmentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FscPartyAccountBridgePK other = (FscPartyAccountBridgePK) obj;

		if (!segmentId.equals(other.segmentId)) return false;
		if (accountKey.intValue() != other.accountKey.intValue()) return false;
		if (partyKey.intValue() != other.partyKey.intValue()) return false;
		if (roleKey.intValue() != other.roleKey.intValue()) return false;
		if (!changeBeginDate.equals(other.changeBeginDate)) return false;
		return true;
	}

}