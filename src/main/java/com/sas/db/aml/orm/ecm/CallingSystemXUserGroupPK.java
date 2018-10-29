package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CALLING_SYSTEM_X_USER_GROUP database table.
 * 
 */
@Embeddable
public class CallingSystemXUserGroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CALLING_SYSTEM_CD")
	private String callingSystemCd;

	@Column(name="USER_GROUP_NM")
	private String userGroupNm;

	@Column(name="[ROLE]")
	private String role;

	public CallingSystemXUserGroupPK() {
	}
	public String getCallingSystemCd() {
		return this.callingSystemCd;
	}
	public void setCallingSystemCd(String callingSystemCd) {
		this.callingSystemCd = callingSystemCd;
	}
	public String getUserGroupNm() {
		return this.userGroupNm;
	}
	public void setUserGroupNm(String userGroupNm) {
		this.userGroupNm = userGroupNm;
	}
	public String getRole() {
		return this.role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CallingSystemXUserGroupPK)) {
			return false;
		}
		CallingSystemXUserGroupPK castOther = (CallingSystemXUserGroupPK)other;
		return 
			this.callingSystemCd.equals(castOther.callingSystemCd)
			&& this.userGroupNm.equals(castOther.userGroupNm)
			&& this.role.equals(castOther.role);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.callingSystemCd.hashCode();
		hash = hash * prime + this.userGroupNm.hashCode();
		hash = hash * prime + this.role.hashCode();
		
		return hash;
	}
}