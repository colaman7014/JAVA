package com.sas.db.wlf.orm.nc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the NAME_CHECK_LOG_TIME database table.
 * 
 */
@Entity
@Table(name="NAME_CHECK_LOG_TIME", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="NameCheckLogTime.findAll", query="SELECT n FROM NameCheckLogTime n")
public class NameCheckLogTime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="UNIQUE_KEY", unique=true, nullable=false, length=128)
	private String uniqueKey;

	@Column(name="NCREFERENCE_ID", nullable=false)
	private int ncreferenceId;

	@Column(name="TIME_1")
	private Timestamp time1;

	@Column(name="TIME_2")
	private Timestamp time2;

	public NameCheckLogTime() {
	}

	public String getUniqueKey() {
		return this.uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public int getNcreferenceId() {
		return this.ncreferenceId;
	}

	public void setNcreferenceId(int ncreferenceId) {
		this.ncreferenceId = ncreferenceId;
	}

	public Timestamp getTime1() {
		return this.time1;
	}

	public void setTime1(Timestamp time1) {
		this.time1 = time1;
	}

	public Timestamp getTime2() {
		return this.time2;
	}

	public void setTime2(Timestamp time2) {
		this.time2 = time2;
	}

}