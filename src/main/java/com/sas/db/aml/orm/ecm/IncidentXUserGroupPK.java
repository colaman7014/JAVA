package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the INCIDENT_X_USER_GROUP database table.
 * 
 */
@Embeddable
public class IncidentXUserGroupPK   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="INCIDENT_RK", insertable=false, updatable=false)
	private BigDecimal incidentRk;
	
	@Column(name="USER_GROUP_NM")
	private String userGroupNm;

	
	public BigDecimal getIncidentRk() {
		return incidentRk;
	}
	public void setIncidentRk(BigDecimal incidentRk) {
		this.incidentRk = incidentRk;
	}
	public String getUserGroupNm() {
		return userGroupNm;
	}
	public void setUserGroupNm(String userGroupNm) {
		this.userGroupNm = userGroupNm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IncidentXUserGroupPK)) {
			return false;
		}
		IncidentXUserGroupPK castOther = (IncidentXUserGroupPK)other;
		return 
			(this.incidentRk == castOther.incidentRk)
			&& this.userGroupNm.equals(castOther.userGroupNm);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.incidentRk.intValue() ^ (this.incidentRk.intValue() >>> 32)));
		hash = hash * prime + this.userGroupNm.hashCode();
		
		return hash;
	}
}
