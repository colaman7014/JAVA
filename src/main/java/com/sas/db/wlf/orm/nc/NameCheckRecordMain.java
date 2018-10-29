package com.sas.db.wlf.orm.nc;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the NAME_CHECK_RECORD_MAIN database table.
 * 
 */
@Entity
@Table(name="NAME_CHECK_RECORD_MAIN", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="NameCheckRecordMain.findAll", query="SELECT n FROM NameCheckRecordMain n")
public class NameCheckRecordMain implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NameCheckRecordMainPK id;

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
	private String referenceNumber;

	@Column(name="ROUTE_RULE")
	private String routeRule;

	@Column(name="SCREEN_PROCESS")
	private String screenProcess;
	
	@Column(name="PARTY_NUMBER")
	private String partyNumber;

	@Column(name="TRANSACTION_DATE")
	private Date transactionDate;
	
	@Column(name="CASE_RK")
	private long caseRk;
	
	@Column(name="PEP_FLAG")
	private String pepFlag;
	
	@Column(name="PNMP_FLAG")
	private String pnmpFlag;

	public NameCheckRecordMain() {
	}

	public NameCheckRecordMainPK getId() {
		return this.id;
	}

	public void setId(NameCheckRecordMainPK id) {
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
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

	public long getCaseRk() {
		return caseRk;
	}

	public void setCaseRk(long caseRk) {
			this.caseRk = caseRk;
	}
	
	public String getPartyNumber() {
		return partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

	public String getPepFlag() {
		return pepFlag;
	}

	public void setPepFlag(String pepFlag) {
		this.pepFlag = pepFlag;
	}

	public String getPnmpFlag() {
		return pnmpFlag;
	}

	public void setPnmpFlag(String pnmpFlag) {
		this.pnmpFlag = pnmpFlag;
	}
}