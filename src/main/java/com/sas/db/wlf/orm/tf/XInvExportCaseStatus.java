package com.sas.db.wlf.orm.tf;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the X_INV_EXPORT_CASE_STATUS database table.
 * 
 */
@Entity
@Table(name="X_INV_EXPORT_CASE_STATUS", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="XInvExportCaseStatus.findAll", query="SELECT x FROM XInvExportCaseStatus x")
public class XInvExportCaseStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XInvExportCaseStatusPK id;

	@Column(name="CASE_RK")
	private BigDecimal caseRk;

	@Column(name="CASE_STATUS")
	private String caseStatus;

	@Column(name="CURRNECY")
	private String currnecy;

	@Column(name="INVOICE_NO")
	private String invoiceNo;

	@Column(name="OUR_REF_NO")
	private String ourRefNo;

	@Column(name="SCR_NO")
	private String scrNo;

	@Column(name="SEQ_NO")
	private String seqNo;
	
	@Column(name="CREATE_DTTM")
	private Timestamp createDttm;

	public XInvExportCaseStatus() {
	}

	public XInvExportCaseStatusPK getId() {
		return this.id;
	}

	public void setId(XInvExportCaseStatusPK id) {
		this.id = id;
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

	public String getCurrnecy() {
		return this.currnecy;
	}

	public void setCurrnecy(String currnecy) {
		this.currnecy = currnecy;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getOurRefNo() {
		return this.ourRefNo;
	}

	public void setOurRefNo(String ourRefNo) {
		this.ourRefNo = ourRefNo;
	}

	public String getScrNo() {
		return this.scrNo;
	}

	public void setScrNo(String scrNo) {
		this.scrNo = scrNo;
	}

	public String getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public Timestamp getCreateDttm() {
		return createDttm;
	}

	public void setCreateDttm(Timestamp createDttm) {
		this.createDttm = createDttm;
	}

	
}