package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the INCIDENT_UDF_CHAR_VALUE database table.
 * 
 */
@Embeddable
public class IncidentUdfCharValuePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="INCIDENT_RK", insertable=false, updatable=false)
	private long incidentRk;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VALID_FROM_DTTM", insertable=false, updatable=false)
	private java.util.Date validFromDttm;

	@Column(name="UDF_TABLE_NM", insertable=false, updatable=false)
	private String udfTableNm;

	@Column(name="UDF_NM", insertable=false, updatable=false)
	private String udfNm;

	@Column(name="ROW_NO")
	private long rowNo;

	public IncidentUdfCharValuePK() {
	}
	public long getIncidentRk() {
		return this.incidentRk;
	}
	public void setIncidentRk(long incidentRk) {
		this.incidentRk = incidentRk;
	}
	public java.util.Date getValidFromDttm() {
		return this.validFromDttm;
	}
	public void setValidFromDttm(java.util.Date validFromDttm) {
		this.validFromDttm = validFromDttm;
	}
	public String getUdfTableNm() {
		return this.udfTableNm;
	}
	public void setUdfTableNm(String udfTableNm) {
		this.udfTableNm = udfTableNm;
	}
	public String getUdfNm() {
		return this.udfNm;
	}
	public void setUdfNm(String udfNm) {
		this.udfNm = udfNm;
	}
	public long getRowNo() {
		return this.rowNo;
	}
	public void setRowNo(long rowNo) {
		this.rowNo = rowNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IncidentUdfCharValuePK)) {
			return false;
		}
		IncidentUdfCharValuePK castOther = (IncidentUdfCharValuePK)other;
		return 
			(this.incidentRk == castOther.incidentRk)
			&& this.validFromDttm.equals(castOther.validFromDttm)
			&& this.udfTableNm.equals(castOther.udfTableNm)
			&& this.udfNm.equals(castOther.udfNm)
			&& (this.rowNo == castOther.rowNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.incidentRk ^ (this.incidentRk >>> 32)));
		hash = hash * prime + this.validFromDttm.hashCode();
		hash = hash * prime + this.udfTableNm.hashCode();
		hash = hash * prime + this.udfNm.hashCode();
		hash = hash * prime + ((int) (this.rowNo ^ (this.rowNo >>> 32)));
		
		return hash;
	}
}