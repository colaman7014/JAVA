package com.sas.db.wlf.orm;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the X_NCSC_CASE_RESULT_TMP database table.
 * 
 */
@Embeddable
public class XNcscCaseResultTmpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CASE_RK")
	private long caseRk;

	@Column(name="UNIQUE_KEY")
	private String uniqueKey;

	@Column(name="NC_REFERENCE_ID")
	private int ncReferenceId;

	@Column(name="SEQ")
	private int seq;
	
	@Column(name="ROW_NO")
	private int rowNo;
	
	public XNcscCaseResultTmpPK(XNcscCaseResultPK resultPK) {
		this.caseRk = resultPK.getCaseRk();
		this.ncReferenceId = resultPK.getNcReferenceId();
		this.seq = resultPK.getSeq();
		this.uniqueKey = resultPK.getUniqueKey();
		this.rowNo = resultPK.getRowNo();
	}

	public XNcscCaseResultTmpPK() {
	}
	public long getCaseRk() {
		return this.caseRk;
	}
	public void setCaseRk(long caseRk) {
		this.caseRk = caseRk;
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
	public int getSeq() {
		return this.seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof XNcscCaseResultTmpPK)) {
			return false;
		}
		XNcscCaseResultTmpPK castOther = (XNcscCaseResultTmpPK)other;
		return 
			(this.caseRk == castOther.caseRk)
			&& this.uniqueKey.equals(castOther.uniqueKey)
			&& (this.ncReferenceId == castOther.ncReferenceId)
			&& (this.seq == castOther.seq)
			&&(this.rowNo == castOther.rowNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.caseRk ^ (this.caseRk >>> 32)));
		hash = hash * prime + this.uniqueKey.hashCode();
		hash = hash * prime + this.ncReferenceId;
		hash = hash * prime + this.seq;
		hash = hash * prime + this.rowNo;
		
		return hash;
	}
}