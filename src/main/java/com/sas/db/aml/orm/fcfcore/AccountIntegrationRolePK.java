package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ACCOUNT_INTEGRATION_ROLE database table.
 * 
 */
@Embeddable
public class AccountIntegrationRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="EMP_ID")
	private String empId;

	@Column(name="[ROLE]")
	private String role;

	public AccountIntegrationRolePK() {
	}
	public String getEmpId() {
		return this.empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
		if (!(other instanceof AccountIntegrationRolePK)) {
			return false;
		}
		AccountIntegrationRolePK castOther = (AccountIntegrationRolePK)other;
		return 
			this.empId.equals(castOther.empId)
			&& this.role.equals(castOther.role);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.empId.hashCode();
		hash = hash * prime + this.role.hashCode();
		
		return hash;
	}
}