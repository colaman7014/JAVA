package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the CASE_UDF_CHAR_VALUE database table.
 * 
 */
@Embeddable
public class CaseUdfCharValuePK implements Serializable{
	
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CASE_RK")
	private long caseRk;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VALID_FROM_DTTM")
	private Date validFromDttm;
	
	@Column(name="UDF_TABLE_NM")
	private String udfTableName;
	
	@Column(name="UDF_NM")
	private String udfName;
	
	@Column(name="ROW_NO")
	private long rowNo;

	public long getCaseRk() {
		return caseRk;
	}

	public void setCaseRk(long caseRk) {
		this.caseRk = caseRk;
	}

	public Date getValidFromDttm() {
		return validFromDttm;
	}

	public void setValidFromDttm(Date validFromDttm) {
		this.validFromDttm = validFromDttm;
	}

	public String getUdfTableName() {
		return udfTableName;
	}

	public void setUdfTableName(String udfTableName) {
		this.udfTableName = udfTableName;
	}

	public String getUdfName() {
		return udfName;
	}

	public void setUdfName(String udfName) {
		this.udfName = udfName;
	}

	public long getRowNo() {
		return rowNo;
	}

	public void setRowNo(long rowNo) {
		this.rowNo = rowNo;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CaseUdfCharValuePK)) {
			return false;
		}
		CaseUdfCharValuePK castOther = (CaseUdfCharValuePK)other;
		return 
			(this.caseRk == castOther.caseRk)
			&& this.validFromDttm.equals(castOther.validFromDttm)
			&& this.udfTableName.equals(castOther.udfTableName)
			&& this.udfName.equals(castOther.udfName)
			&& this.rowNo==castOther.rowNo;
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.caseRk ^ (this.caseRk >>> 32)));
		hash = hash * prime + this.validFromDttm.hashCode();
		hash = hash * prime + this.udfTableName.hashCode();
		hash = hash * prime + this.udfName.hashCode();
		
		return hash;
	}

	@Override
	public String toString() {
		return "CaseUdfCharValuePK [caseRk=" + caseRk + ", validFromDttm=" + validFromDttm + ", udfTableName="
				+ udfTableName + ", udfName=" + udfName + ", rowNo=" + rowNo + "]";
	}
	
}
