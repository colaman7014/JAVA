package com.sas.db.wlf.orm.batch;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the BATCH_NAME_CHECK_RECORD_MAIN database table.
 * 
 */
@Entity
@Table(name="BATCH_NAME_CHECK_RECORD_MAIN")
@NamedQuery(name="BatchNameCheckRecordMain.findAll", query="SELECT n FROM BatchNameCheckRecordMain n")
public class BatchNameCheckRecordMain implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BatchNameCheckRecordMainPK id;

	@Column(name="BRANCH_NUMBER")
	private String branchNumber;

	@Column(name="BUSINESS_UNIT_ID")
	private String businessUnitId;

	@Column(name="CALLING_SYSTEM")
	private String callingSystem;

	@Column(name="CALLING_USER")
	private String callingUser;

	@Column(name="GEN_ALERT_FLAG")
	private String genAlertFlag;

	@Column(name="HIT_SEQ")
	private String hitSeq;

	@Column(name="INTERFACE_NAME")
	private String interfaceName;

	@Column(name="NC_CLOSE_REASON")
	private String ncCloseReason;

	@Column(name="NC_RESULT")
	private String ncResult;

	@Column(name="REFERENCE_NUMBER")
	private int referenceNumber;

	@Column(name="ROUTE_RULE")
	private String routeRule;

	@Column(name="SCREEN_PROCESS")
	private String screenProcess;

	@Column(name="TRANSACTION_DATE")
	private Date transactionDate;

	public BatchNameCheckRecordMain() {
	}

	public BatchNameCheckRecordMainPK getId() {
		return this.id;
	}

	public void setId(BatchNameCheckRecordMainPK id) {
		this.id = id;
	}

	public String getBranchNumber() {
		return this.branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getBusinessUnitId() {
		return this.businessUnitId;
	}

	public void setBusinessUnitId(String businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getCallingSystem() {
		return this.callingSystem;
	}

	public void setCallingSystem(String callingSystem) {
		this.callingSystem = callingSystem;
	}

	public String getCallingUser() {
		return this.callingUser;
	}

	public void setCallingUser(String callingUser) {
		this.callingUser = callingUser;
	}

	public String getGenAlertFlag() {
		return this.genAlertFlag;
	}

	public void setGenAlertFlag(String genAlertFlag) {
		this.genAlertFlag = genAlertFlag;
	}

	public String getHitSeq() {
		return this.hitSeq;
	}

	public void setHitSeq(String hitSeq) {
		this.hitSeq = hitSeq;
	}

	public String getInterfaceName() {
		return this.interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getNcCloseReason() {
		return this.ncCloseReason;
	}

	public void setNcCloseReason(String ncCloseReason) {
		this.ncCloseReason = ncCloseReason;
	}

	public String getNcResult() {
		return this.ncResult;
	}

	public void setNcResult(String ncResult) {
		this.ncResult = ncResult;
	}

	public int getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getRouteRule() {
		return this.routeRule;
	}

	public void setRouteRule(String routeRule) {
		this.routeRule = routeRule;
	}

	public String getScreenProcess() {
		return this.screenProcess;
	}

	public void setScreenProcess(String screenProcess) {
		this.screenProcess = screenProcess;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}