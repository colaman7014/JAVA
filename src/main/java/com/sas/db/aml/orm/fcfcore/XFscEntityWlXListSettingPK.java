package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the X_FSC_ENTITY_WL_X_LIST_SETTING database table.
 * 
 */
@Embeddable
public class XFscEntityWlXListSettingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Unique_Key")
	private long uniqueKey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Change_Begin_Date")
	private java.util.Date changeBeginDate;

	@Column(name="Change_Current_Ind")
	private String changeCurrentInd;

	public XFscEntityWlXListSettingPK() {
	}

	public long getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(long uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public java.util.Date getChangeBeginDate() {
		return changeBeginDate;
	}

	public void setChangeBeginDate(java.util.Date changeBeginDate) {
		this.changeBeginDate = changeBeginDate;
	}

	public String getChangeCurrentInd() {
		return changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((changeBeginDate == null) ? 0 : changeBeginDate.hashCode());
		result = prime
				* result
				+ ((changeCurrentInd == null) ? 0 : changeCurrentInd.hashCode());
		result = prime * result + (int) (uniqueKey ^ (uniqueKey >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XFscEntityWlXListSettingPK other = (XFscEntityWlXListSettingPK) obj;
		if (changeBeginDate == null) {
			if (other.changeBeginDate != null)
				return false;
		} else if (!changeBeginDate.equals(other.changeBeginDate))
			return false;
		if (changeCurrentInd == null) {
			if (other.changeCurrentInd != null)
				return false;
		} else if (!changeCurrentInd.equals(other.changeCurrentInd))
			return false;
		if (uniqueKey != other.uniqueKey)
			return false;
		return true;
	}
	
}