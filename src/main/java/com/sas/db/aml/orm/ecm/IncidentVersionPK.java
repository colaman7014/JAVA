package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the INCIDENT_VERSION database table.
 * 
 */
@Embeddable
public class IncidentVersionPK  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="INCIDENT_RK")
	private BigDecimal incidentRk;
	
	@Column(name="VALID_FROM_DTTM")
	private Timestamp validFromDttm;
	
	
	
	public BigDecimal getIncidentRk() {
		return incidentRk;
	}

	public void setIncidentRk(BigDecimal incidentRk) {
		this.incidentRk = incidentRk;
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
		if (!(other instanceof IncidentVersionPK)) {
			return false;
		}
		IncidentVersionPK castOther = (IncidentVersionPK)other;
		return 
			(this.incidentRk == castOther.incidentRk)
			&& this.validFromDttm.equals(castOther.validFromDttm);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.incidentRk.intValue() ^ (this.incidentRk.intValue() >>> 32)));
		hash = hash * prime + this.validFromDttm.hashCode();
		
		return hash;
	}
	
	
}
