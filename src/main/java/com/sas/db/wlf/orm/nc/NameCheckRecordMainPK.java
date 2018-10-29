package com.sas.db.wlf.orm.nc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the NAME_CHECK_RECORD_MAIN database table.
 * 
 */
@Embeddable
public class NameCheckRecordMainPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="UNIQUE_KEY")
	private String uniqueKey;

	@Column(name="NC_REFERENCE_ID")
	private int ncReferenceId;

	public NameCheckRecordMainPK() {
	}
	public NameCheckRecordMainPK(String uniqueKey, int ncReferenceId) {
		this.uniqueKey = uniqueKey;
		this.ncReferenceId = ncReferenceId;
	}
	public String getUniqueKey() {
		return this.uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public int getNcReferenceId() {
		return this.ncReferenceId;
	}
	public void setNcReferenceId(int ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NameCheckRecordMainPK)) {
			return false;
		}
		NameCheckRecordMainPK castOther = (NameCheckRecordMainPK)other;
		return 
			this.uniqueKey.equals(castOther.uniqueKey)
			&& (this.ncReferenceId == castOther.ncReferenceId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.uniqueKey.hashCode();
		hash = hash * prime + this.ncReferenceId;
		
		return hash;
	}
}