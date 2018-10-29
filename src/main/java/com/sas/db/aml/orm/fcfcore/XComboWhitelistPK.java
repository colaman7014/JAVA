package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the X_COMBO_WHITELIST database table.
 * 
 */
@Embeddable
public class XComboWhitelistPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PARTY_NUMBER")
	private String partyNumber;

	@Column(name="BENEFICIARY_ENTITY_WATCH_LIST_KEY")
	private String beneficiaryEntityWatchListKey;

	public XComboWhitelistPK() {
	}
	public String getPartyNumber() {
		return this.partyNumber;
	}
	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}
	public String getBeneficiaryEntityWatchListKey() {
		return this.beneficiaryEntityWatchListKey;
	}
	public void setBeneficiaryEntityWatchListKey(String beneficiaryEntityWatchListKey) {
		this.beneficiaryEntityWatchListKey = beneficiaryEntityWatchListKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof XComboWhitelistPK)) {
			return false;
		}
		XComboWhitelistPK castOther = (XComboWhitelistPK)other;
		return 
			this.partyNumber.equals(castOther.partyNumber)
			&& this.beneficiaryEntityWatchListKey.equals(castOther.beneficiaryEntityWatchListKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.partyNumber.hashCode();
		hash = hash * prime + this.beneficiaryEntityWatchListKey.hashCode();
		
		return hash;
	}
}