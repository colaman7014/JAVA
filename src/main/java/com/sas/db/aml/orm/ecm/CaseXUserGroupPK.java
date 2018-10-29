package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the CASE_X_USER_GROUP database table.
 * 
 */
@Embeddable
public class CaseXUserGroupPK  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="CASE_RK", insertable=false, updatable=false)
	private BigDecimal caseRk;
	
	@Column(name="USER_GROUP_NM")
	private String userGroupNm;
	

	
	public BigDecimal getCaseRk() {
		return caseRk;
	}

	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
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
		if (!(other instanceof CaseXUserGroupPK)) {
			return false;
		}
		CaseXUserGroupPK castOther = (CaseXUserGroupPK)other;
		return 
			(this.caseRk == castOther.caseRk)
			&& this.userGroupNm.equals(castOther.userGroupNm);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.caseRk.intValue() ^ (this.caseRk.intValue() >>> 32)));
		hash = hash * prime + this.userGroupNm.hashCode();
		
		return hash;
	}
}
