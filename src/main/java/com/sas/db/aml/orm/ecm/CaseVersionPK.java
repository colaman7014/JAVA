package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the CASE_VERSION database table.
 * 
 */
@Embeddable
public class CaseVersionPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="CASE_RK")
	private BigDecimal caseRk;
	
	@Column(name="VALID_FROM_DTTM")
	private Timestamp validFromDttm;
	

	
	public BigDecimal getCaseRk() {
		return caseRk;
	}

	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
	}

	public Timestamp getValidFromDttm() {
		return validFromDttm;
	}

	public void setValidFromDttm(Timestamp validFromDttm) {
		this.validFromDttm = validFromDttm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CaseVersionPK)) {
			return false;
		}
		CaseVersionPK castOther = (CaseVersionPK)other;
		return 
			(this.caseRk == castOther.caseRk)
			&& this.validFromDttm.equals(castOther.validFromDttm);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.caseRk.intValue() ^ (this.caseRk.intValue() >>> 32)));
		hash = hash * prime + this.validFromDttm.hashCode();
		
		return hash;
	}


}
