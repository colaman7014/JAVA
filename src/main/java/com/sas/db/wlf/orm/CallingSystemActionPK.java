package com.sas.db.wlf.orm;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CALLING_SYSTEM_ACTION database table.
 * 
 */
@Embeddable
public class CallingSystemActionPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "CALLING_SYSTEM")
	private String callingSystem;

	@Column(name = "CALLING_DEPT")
	private String callingDept;

	public CallingSystemActionPK() {
	}

	public CallingSystemActionPK(String callingSystem, String callingDept) {
		this.callingSystem = callingSystem;
		this.callingDept = callingDept;
	}

	public String getCallingSystem() {
		return this.callingSystem;
	}

	public void setCallingSystem(String callingSystem) {
		this.callingSystem = callingSystem;
	}

	public String getCallingDept() {
		return this.callingDept;
	}

	public void setCallingDept(String callingDept) {
		this.callingDept = callingDept;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CallingSystemActionPK)) {
			return false;
		}
		CallingSystemActionPK castOther = (CallingSystemActionPK) other;
		return this.callingSystem.equals(castOther.callingSystem) && this.callingDept.equals(castOther.callingDept);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.callingSystem.hashCode();
		hash = hash * prime + this.callingDept.hashCode();

		return hash;
	}
}