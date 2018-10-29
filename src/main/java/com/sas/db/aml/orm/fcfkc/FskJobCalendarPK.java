package com.sas.db.aml.orm.fcfkc;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FSK_JOB_CALENDAR database table.
 * 
 */
@Embeddable
public class FskJobCalendarPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="job_calendar_id")
	private long jobCalendarId;

	@Column(name="segment_id")
	private String segmentId;

	public FskJobCalendarPK() {
	}
	public long getJobCalendarId() {
		return this.jobCalendarId;
	}
	public void setJobCalendarId(long jobCalendarId) {
		this.jobCalendarId = jobCalendarId;
	}
	public String getSegmentId() {
		return this.segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FskJobCalendarPK)) {
			return false;
		}
		FskJobCalendarPK castOther = (FskJobCalendarPK)other;
		return 
			(this.jobCalendarId == castOther.jobCalendarId)
			&& this.segmentId.equals(castOther.segmentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.jobCalendarId ^ (this.jobCalendarId >>> 32)));
		hash = hash * prime + this.segmentId.hashCode();
		
		return hash;
	}
}