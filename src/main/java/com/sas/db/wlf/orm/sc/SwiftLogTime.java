package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import com.sas.constraint.SwiftMtConst;

/**
 * The persistent class for the SWIFT_LOG_TIME database table.
 * 
 */
@Entity
@Table(name="SWIFT_LOG_TIME", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftLogTime.findAll", query="SELECT s FROM SwiftLogTime s")
public class SwiftLogTime implements Serializable {
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

	@Column(name="TIME_3")
	private Timestamp time3;

	@Column(name="TIME_4")
	private Timestamp time4;

	@Column(name="TIME_5")
	private Timestamp time5;

	@Column(name="TIME_6")
	private Timestamp time6;

	public SwiftLogTime() {
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

	public Timestamp getTime3() {
		return this.time3;
	}

	public void setTime3(Timestamp time3) {
		this.time3 = time3;
	}

	public Timestamp getTime4() {
		return this.time4;
	}

	public void setTime4(Timestamp time4) {
		this.time4 = time4;
	}

	public Timestamp getTime5() {
		return this.time5;
	}

	public void setTime5(Timestamp time5) {
		this.time5 = time5;
	}

	public Timestamp getTime6() {
		return this.time6;
	}

	public void setTime6(Timestamp time6) {
		this.time6 = time6;
	}
}