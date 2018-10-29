package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CASE_CONFIG_X_USER_GROUP database table.
 * 
 */
@Embeddable
public class CaseConfigXUserGroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CASE_CONFIG_SEQ_NO", insertable=false, updatable=false)
	private long caseConfigSeqNo;

	@Column(name="USER_GROUP_NM")
	private String userGroupNm;

	public CaseConfigXUserGroupPK() {
	}
	public long getCaseConfigSeqNo() {
		return this.caseConfigSeqNo;
	}
	public void setCaseConfigSeqNo(long caseConfigSeqNo) {
		this.caseConfigSeqNo = caseConfigSeqNo;
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
		if (!(other instanceof CaseConfigXUserGroupPK)) {
			return false;
		}
		CaseConfigXUserGroupPK castOther = (CaseConfigXUserGroupPK)other;
		return 
			(this.caseConfigSeqNo == castOther.caseConfigSeqNo)
			&& this.userGroupNm.equals(castOther.userGroupNm);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.caseConfigSeqNo ^ (this.caseConfigSeqNo >>> 32)));
		hash = hash * prime + this.userGroupNm.hashCode();
		
		return hash;
	}
}