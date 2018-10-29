package com.sas.db.wlf.orm.tf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the X_INV_IMPORT_CASE_STATUS database table.
 * 
 */
@Embeddable
public class XInvImportCaseStatusPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="UNIQUE_KEY")
	private String uniqueKey;

	@Column(name="SEQ")
	private int seq;

	@Column(name="FILE_KEY")
	private String fileKey;

	public XInvImportCaseStatusPK() {
	}
	public XInvImportCaseStatusPK(String uniqueKey, int seq, String fileKey) {
		this.uniqueKey = uniqueKey;
		this.seq = seq;
		this.fileKey = fileKey;
	}
	public String getUniqueKey() {
		return this.uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public int getSeq() {
		return this.seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getFileKey() {
		return this.fileKey;
	}
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof XInvImportCaseStatusPK)) {
			return false;
		}
		XInvImportCaseStatusPK castOther = (XInvImportCaseStatusPK)other;
		return 
			this.uniqueKey.equals(castOther.uniqueKey)
			&& (this.seq == castOther.seq)
			&& this.fileKey.equals(castOther.fileKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.uniqueKey.hashCode();
		hash = hash * prime + this.seq;
		hash = hash * prime + this.fileKey.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "XInvImportCaseStatusPK [uniqueKey=" + uniqueKey + ", seq=" + seq + ", fileKey=" + fileKey + "]";
	}
	
}