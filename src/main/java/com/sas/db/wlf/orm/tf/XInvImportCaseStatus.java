package com.sas.db.wlf.orm.tf;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the X_INV_IMPORT_CASE_STATUS database table.
 * 
 */
@Entity
@Table(name="X_INV_IMPORT_CASE_STATUS", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="XInvImportCaseStatus.findAll", query="SELECT x FROM XInvImportCaseStatus x")
public class XInvImportCaseStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XInvImportCaseStatusPK id;

	@Column(name="BL_NO")
	private String blNo;

	@Column(name="CASE_RK")
	private BigDecimal caseRk;

	@Column(name="CASE_STATUS")
	private String caseStatus;

	@Column(name="IB_NO")
	private String ibNo;

	@Column(name="INVOICE_NO")
	private String invoiceNo;

	@Column(name="L_C_NO")
	private String lCNo;

	@Column(name="SCR_NO")
	private String scrNo;

	@Column(name="[TYPE]")
	private String type;
	
	@Column(name="CREATE_DTTM")
	private Timestamp createDttm;

	public XInvImportCaseStatus() {
	}

	public XInvImportCaseStatusPK getId() {
		return this.id;
	}

	public void setId(XInvImportCaseStatusPK id) {
		this.id = id;
	}

	public String getBlNo() {
		return this.blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public BigDecimal getCaseRk() {
		return this.caseRk;
	}

	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
	}

	public String getCaseStatus() {
		return this.caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getIbNo() {
		return this.ibNo;
	}

	public void setIbNo(String ibNo) {
		this.ibNo = ibNo;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getLCNo() {
		return this.lCNo;
	}

	public void setLCNo(String lCNo) {
		this.lCNo = lCNo;
	}

	public String getScrNo() {
		return this.scrNo;
	}

	public void setScrNo(String scrNo) {
		this.scrNo = scrNo;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getlCNo() {
		return lCNo;
	}

	public void setlCNo(String lCNo) {
		this.lCNo = lCNo;
	}

	public Timestamp getCreateDttm() {
		return createDttm;
	}

	public void setCreateDttm(Timestamp createDttm) {
		this.createDttm = createDttm;
	}

}