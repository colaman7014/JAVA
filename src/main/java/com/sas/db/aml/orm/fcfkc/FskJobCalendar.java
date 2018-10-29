package com.sas.db.aml.orm.fcfkc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FSK_JOB_CALENDAR database table.
 * 
 */
@Entity
@Table(name="FSK_JOB_CALENDAR", schema=SwiftMtConst.COM_SAS_JPACFG_AML_FCFKC_SCHEMA)
@NamedQuery(name="FskJobCalendar.findAll", query="SELECT f FROM FskJobCalendar f")
public class FskJobCalendar implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FskJobCalendarPK id;

	@Column(name="business_day_count")
	private BigDecimal businessDayCount;

	@Column(name="calendar_date")
	private Timestamp calendarDate;

	@Column(name="daily_rundate_ind")
	private String dailyRundateInd;

	@Column(name="monthly_rundate_ind")
	private String monthlyRundateInd;

	@Column(name="rundate_ind")
	private String rundateInd;

	@Column(name="status_ind")
	private String statusInd;

	@Column(name="weekly_rundate_ind")
	private String weeklyRundateInd;

	public FskJobCalendar() {
	}

	public FskJobCalendarPK getId() {
		return this.id;
	}

	public void setId(FskJobCalendarPK id) {
		this.id = id;
	}

	public BigDecimal getBusinessDayCount() {
		return this.businessDayCount;
	}

	public void setBusinessDayCount(BigDecimal businessDayCount) {
		this.businessDayCount = businessDayCount;
	}

	public Timestamp getCalendarDate() {
		return this.calendarDate;
	}

	public void setCalendarDate(Timestamp calendarDate) {
		this.calendarDate = calendarDate;
	}

	public String getDailyRundateInd() {
		return this.dailyRundateInd;
	}

	public void setDailyRundateInd(String dailyRundateInd) {
		this.dailyRundateInd = dailyRundateInd;
	}

	public String getMonthlyRundateInd() {
		return this.monthlyRundateInd;
	}

	public void setMonthlyRundateInd(String monthlyRundateInd) {
		this.monthlyRundateInd = monthlyRundateInd;
	}

	public String getRundateInd() {
		return this.rundateInd;
	}

	public void setRundateInd(String rundateInd) {
		this.rundateInd = rundateInd;
	}

	public String getStatusInd() {
		return this.statusInd;
	}

	public void setStatusInd(String statusInd) {
		this.statusInd = statusInd;
	}

	public String getWeeklyRundateInd() {
		return this.weeklyRundateInd;
	}

	public void setWeeklyRundateInd(String weeklyRundateInd) {
		this.weeklyRundateInd = weeklyRundateInd;
	}

}