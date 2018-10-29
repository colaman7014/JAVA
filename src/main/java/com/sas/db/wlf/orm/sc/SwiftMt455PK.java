package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SWIFT_MT455 database table.
 * 
 */
@Embeddable
public class SwiftMt455PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="UNIQUE_KEY")
	private String uniqueKey;

	@Column(name="REFERENCE_ID")
	private int referenceId;

	public SwiftMt455PK() {
	}
	public String getUniqueKey() {
		return this.uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public int getReferenceId() {
		return this.referenceId;
	}
	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SwiftMt455PK)) {
			return false;
		}
		SwiftMt455PK castOther = (SwiftMt455PK)other;
		return 
			this.uniqueKey.equals(castOther.uniqueKey)
			&& (this.referenceId == castOther.referenceId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.uniqueKey.hashCode();
		hash = hash * prime + this.referenceId;
		
		return hash;
	}
}