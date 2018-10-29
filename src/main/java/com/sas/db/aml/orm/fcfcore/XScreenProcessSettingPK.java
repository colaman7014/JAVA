package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the X_SCREEN_PROCESS_SETTING database table.
 * 
 */
@Embeddable
public class XScreenProcessSettingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Setting_Key")
	private long settingKey;

	@Column(name="Screen_Process_Type_Cd")
	private String screenProcessTypeCd;

	@Column(name="WatchList_Sub_Type_Cd")
	private String watchListSubTypeCd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Change_Begin_Date")
	private java.util.Date changeBeginDate;

	public XScreenProcessSettingPK() {
	}

	public long getSettingKey() {
		return settingKey;
	}

	public void setSettingKey(long settingKey) {
		this.settingKey = settingKey;
	}

	public String getScreenProcessTypeCd() {
		return screenProcessTypeCd;
	}

	public void setScreenProcessTypeCd(String screenProcessTypeCd) {
		this.screenProcessTypeCd = screenProcessTypeCd;
	}

	public String getWatchListSubTypeCd() {
		return watchListSubTypeCd;
	}

	public void setWatchListSubTypeCd(String watchListSubTypeCd) {
		this.watchListSubTypeCd = watchListSubTypeCd;
	}

	public java.util.Date getChangeBeginDate() {
		return changeBeginDate;
	}

	public void setChangeBeginDate(java.util.Date changeBeginDate) {
		this.changeBeginDate = changeBeginDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((changeBeginDate == null) ? 0 : changeBeginDate.hashCode());
		result = prime
				* result
				+ ((screenProcessTypeCd == null) ? 0 : screenProcessTypeCd
						.hashCode());
		result = prime * result + (int) (settingKey ^ (settingKey >>> 32));
		result = prime
				* result
				+ ((watchListSubTypeCd == null) ? 0 : watchListSubTypeCd
						.hashCode());
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
		XScreenProcessSettingPK other = (XScreenProcessSettingPK) obj;
		if (changeBeginDate == null) {
			if (other.changeBeginDate != null)
				return false;
		} else if (!changeBeginDate.equals(other.changeBeginDate))
			return false;
		if (screenProcessTypeCd == null) {
			if (other.screenProcessTypeCd != null)
				return false;
		} else if (!screenProcessTypeCd.equals(other.screenProcessTypeCd))
			return false;
		if (settingKey != other.settingKey)
			return false;
		if (watchListSubTypeCd == null) {
			if (other.watchListSubTypeCd != null)
				return false;
		} else if (!watchListSubTypeCd.equals(other.watchListSubTypeCd))
			return false;
		return true;
	}
}