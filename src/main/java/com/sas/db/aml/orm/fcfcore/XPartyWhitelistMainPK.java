package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the X_PARTY_WHITELIST_MAIN database table.
 * 
 */
@Embeddable
public class XPartyWhitelistMainPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PARTY_NUMBER")
	private String partyNumber;

	@Column(name="PARTY_NAME")
	private String partyName;

	public XPartyWhitelistMainPK() {
	}
	public String getPartyNumber() {
		return this.partyNumber;
	}
	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}
	public String getPartyName() {
		return this.partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof XPartyWhitelistMainPK)) {
			return false;
		}
		XPartyWhitelistMainPK castOther = (XPartyWhitelistMainPK)other;
		return 
			this.partyNumber.equals(castOther.partyNumber)
			&& this.partyName.equals(castOther.partyName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.partyNumber.hashCode();
		hash = hash * prime + this.partyName.hashCode();
		
		return hash;
	}
}