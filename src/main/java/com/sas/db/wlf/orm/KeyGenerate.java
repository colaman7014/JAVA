package com.sas.db.wlf.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the KEY_GENERATE database table.
 * 
 */
@Entity
@Table(name="KEY_GENERATE", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="KeyGenerate.findAll", query="SELECT k FROM KeyGenerate k")
public class KeyGenerate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REFERENCE_ID")
	private int referenceId;

	@Column(name="INTERFACE_TYPE")
	private String interfaceType;

	@Column(name="UNIQUE_KEY")
	private String uniqueKey;
	
	@Temporal(TemporalType.DATE)
	@Column(name="PROCESS_DTTM")
	private Date processDttm;

	public KeyGenerate() {
	}

	public int getReferenceId() {
		return this.referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public String getInterfaceType() {
		return this.interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getUniqueKey() {
		return this.uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public Date getProcessDttm() {
		return processDttm;
	}

	public void setProcessDttm(Date processDttm) {
		this.processDttm = processDttm;
	}

}