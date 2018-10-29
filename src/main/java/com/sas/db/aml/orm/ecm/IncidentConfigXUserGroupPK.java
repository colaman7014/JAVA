package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the INCIDENT_CONFIG_X_USER_GROUP database table.
 * 
 */
@Embeddable
public class IncidentConfigXUserGroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="INCIDENT_CONFIG_SEQ_NO", insertable=false, updatable=false)
	private long incidentConfigSeqNo;

	@Column(name="USER_GROUP_NM")
	private String userGroupNm;

	public IncidentConfigXUserGroupPK() {
	}
	public long getIncidentConfigSeqNo() {
		return this.incidentConfigSeqNo;
	}
	public void setIncidentConfigSeqNo(long incidentConfigSeqNo) {
		this.incidentConfigSeqNo = incidentConfigSeqNo;
	}
	public String getUserGroupNm() {
		return this.userGroupNm;
	}
	public void setUserGroupNm(String userGroupNm) {
		this.userGroupNm = userGroupNm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IncidentConfigXUserGroupPK)) {
			return false;
		}
		IncidentConfigXUserGroupPK castOther = (IncidentConfigXUserGroupPK)other;
		return 
			(this.incidentConfigSeqNo == castOther.incidentConfigSeqNo)
			&& this.userGroupNm.equals(castOther.userGroupNm);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.incidentConfigSeqNo ^ (this.incidentConfigSeqNo >>> 32)));
		hash = hash * prime + this.userGroupNm.hashCode();
		
		return hash;
	}
}