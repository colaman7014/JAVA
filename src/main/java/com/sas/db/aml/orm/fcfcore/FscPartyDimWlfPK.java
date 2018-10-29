package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FSC_PARTY_DIM database table.
 * 
 */
@Embeddable
public class FscPartyDimWlfPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="party_key")
	private long partyKey;

	@Column(name="segment_id")
	private String segmentId;

	public FscPartyDimWlfPK() {
	}
	public long getPartyKey() {
		return this.partyKey;
	}
	public void setPartyKey(long partyKey) {
		this.partyKey = partyKey;
	}
	public String getSegmentId() {
		return this.segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FscPartyDimWlfPK)) {
			return false;
		}
		FscPartyDimWlfPK castOther = (FscPartyDimWlfPK)other;
		return 
			(this.partyKey == castOther.partyKey)
			&& this.segmentId.equals(castOther.segmentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.partyKey ^ (this.partyKey >>> 32)));
		hash = hash * prime + this.segmentId.hashCode();
		
		return hash;
	}
}