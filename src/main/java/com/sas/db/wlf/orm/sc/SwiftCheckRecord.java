package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the SWIFT_CHECK_RECORD database table.
 * 
 */
@Entity
@Table(name="SWIFT_CHECK_RECORD", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftCheckRecord.findAll", query="SELECT s FROM SwiftCheckRecord s")
public class SwiftCheckRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftCheckRecordPK id;

	@Column(name="BRANCH_NUMBER")
	private String branchNumber;

	@Column(name="BUSINESS_UNIT_ID")
	private String businessUnitId;

	@Column(name="CALLING_SYSTEM")
	private String callingSystem;

	@Column(name="CALLING_USER")
	private String callingUser;

	@Column(name="NC_CLOSE_REASON")
	private String ncCloseReason;

	@Column(name="NC_RESULT")
	private String ncResult;

	@Column(name="ROUTE_RULE")
	private String routeRule;

	@Column(name="SCREEN_PROCESS")
	private String screenProcess;

	@Column(name="SWIFT_TYPE")
	private String swiftType;
	
	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Temporal(TemporalType.DATE)
	@Column(name="TRANSACTION_DATE")
	private Date transactionDate;
	
	@Column(name="CASE_RK")
	private long caseRk;
	
	@Column(name="PARTY_NUMBER")
	private String partyNumber;
	
	@Column(name="GEN_ALERT_FLAG")
	private String genAlertFlag;
	
	@Column(name="REFERENCE_NUMBER")
	private String referenceNumber;


	public SwiftCheckRecord() {
	}
	
	public SwiftCheckRecord(
			String uniqueKey, int referenceId, String branchNumber, String genAlertFlag, String businessUnitId, 
			String callingSystem, String callingUser,String ncCloseReason, String ncResult, 
			String routeRule, String screenProcess, String swiftType, Date transactionDate, 
			String partyNumber, String swiftFullText) {
		this.id = new SwiftCheckRecordPK();
		this.id.setUniqueKey(uniqueKey);
		this.id.setReferenceId(referenceId);
		this.branchNumber = branchNumber;
		this.genAlertFlag = genAlertFlag;
		this.businessUnitId = businessUnitId;
		this.callingSystem = callingSystem;
		this.callingUser = callingUser;
		this.ncCloseReason = ncCloseReason;
		this.ncResult = ncResult;
		this.routeRule = routeRule;
		this.screenProcess = screenProcess;
		this.swiftType = swiftType;
		this.transactionDate = transactionDate;
		this.swiftFullText = swiftFullText;
		this.partyNumber = partyNumber;
	}

	public SwiftCheckRecordPK getId() {
		return this.id;
	}

	public void setId(SwiftCheckRecordPK id) {
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

	public String getSwiftType() {
		return this.swiftType;
	}

	public void setSwiftType(String swiftType) {
		this.swiftType = swiftType;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getSwiftFullText() {
		return swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
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

	public String getGenAlertFlag() {
		return genAlertFlag;
	}

	public void setGenAlertFlag(String genAlertFlag) {
		this.genAlertFlag = genAlertFlag;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
}