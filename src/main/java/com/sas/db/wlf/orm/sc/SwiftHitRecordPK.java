package com.sas.db.wlf.orm.sc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * The persistent class for the SWIFT_HIT_RECORD database table.
 * 
 */
@Embeddable
public class SwiftHitRecordPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="UNIQUE_KEY")
	private String uniqueKey;
	
	@Column(name="REFERENCE_ID")
	private int referenceId;
	
	@Column(name="SEQ")
	private String seq;
	
	public SwiftHitRecordPK(){
		
	}
	
	public SwiftHitRecordPK(String uniqueKey, int referenceId, String seq){
		this.uniqueKey = uniqueKey;
		this.referenceId = referenceId;
		this.seq = seq;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public int getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + referenceId;
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		result = prime * result
				+ ((uniqueKey == null) ? 0 : uniqueKey.hashCode());
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
		SwiftHitRecordPK other = (SwiftHitRecordPK) obj;
		if (referenceId != other.referenceId)
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		if (uniqueKey == null) {
			if (other.uniqueKey != null)
				return false;
		} else if (!uniqueKey.equals(other.uniqueKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SwiftHitRecordPK [uniqueKey=" + uniqueKey + ", referenceId=" + referenceId + ", seq=" + seq + "]";
	}
	
	
}