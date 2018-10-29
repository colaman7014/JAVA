package com.sas.db.wlf.orm.tf;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the X_ONLINE_NAMECHECK_UPLOAD database table.
 * 
 */
@Entity
@Table(name="X_ONLINE_NAMECHECK_UPLOAD", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="XOnlineNamecheckUpload.findAll", query="SELECT x FROM XOnlineNamecheckUpload x")
public class XOnlineNamecheckUpload implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XOnlineNamecheckUploadPK id;

	@Column(name="BRANCH_NUMBER")
	private String branchNumber;

	@Column(name="CALLING_USER")
	private String callingUser;

	@Column(name="CASE_RK")
	private BigDecimal caseRk;

	@Column(name="CREATE_DTTM")
	private Timestamp createDttm;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="NC_REFERENCE_ID")
	private String ncReferenceId;

	@Column(name="[TYPE]")
	private String type;

	public XOnlineNamecheckUpload() {
	}

	public XOnlineNamecheckUploadPK getId() {
		return this.id;
	}

	public void setId(XOnlineNamecheckUploadPK id) {
		this.id = id;
	}

	public String getBranchNumber() {
		return this.branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getCallingUser() {
		return this.callingUser;
	}

	public void setCallingUser(String callingUser) {
		this.callingUser = callingUser;
	}

	public BigDecimal getCaseRk() {
		return this.caseRk;
	}

	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
	}

	public Timestamp getCreateDttm() {
		return this.createDttm;
	}

	public void setCreateDttm(Timestamp createDttm) {
		this.createDttm = createDttm;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getNcReferenceId() {
		return this.ncReferenceId;
	}

	public void setNcReferenceId(String ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}