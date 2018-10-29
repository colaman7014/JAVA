package com.sas.db.wlf.orm.nc;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the NAME_CHECK_RECORD_DETAIL database table.
 * 
 */
@Embeddable
public class NameCheckRecordDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="UNIQUE_KEY")
	private String uniqueKey;

	@Column(name="NC_REFERENCE_ID")
	private int ncReferenceId;

	@Column(name="CHECK_SEQ")
	private String checkSeq;

	public NameCheckRecordDetailPK() {
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
	public String getCheckSeq() {
		return this.checkSeq;
	}
	public void setCheckSeq(String checkSeq) {
		this.checkSeq = checkSeq;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NameCheckRecordDetailPK)) {
			return false;
		}
		NameCheckRecordDetailPK castOther = (NameCheckRecordDetailPK)other;
		return 
			this.uniqueKey.equals(castOther.uniqueKey)
			&& (this.ncReferenceId == castOther.ncReferenceId)
			&& this.checkSeq.equals(castOther.checkSeq);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.uniqueKey.hashCode();
		hash = hash * prime + this.ncReferenceId;
		hash = hash * prime + this.checkSeq.hashCode();
		
		return hash;
	}
}