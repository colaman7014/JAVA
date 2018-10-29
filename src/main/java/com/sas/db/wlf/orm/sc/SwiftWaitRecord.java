package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;
import com.sas.webservice.swiftCheck.bean.SwiftCheckInputBean;


/**
 * The persistent class for the SWIFT_WAIT_RECORD database table.
 * 
 */
@Entity
@Table(name="SWIFT_WAIT_RECORD", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftWaitRecord.findAll", query="SELECT s FROM SwiftWaitRecord s")
public class SwiftWaitRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftWaitRecordPK id;

	@Column(name="BRANCH_NUMBER")
	private String branchNumber;

	@Column(name="BUSINESS_UNIT_ID")
	private String businessUnitId;

	@Column(name="CALLING_SYSTEM")
	private String callingSystem;

	@Column(name="CALLING_USER")
	private String callingUser;

	@Column(name="CHECK_STATUS")
	private String checkStatus;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DOCUMENTARY_CREDIT_NUMBER")
	private String documentaryCreditNumber;

	@Column(name="PARTY_NUMBER")
	private String partyNumber;

	@Column(name="SCREEN_PROCESS")
	private String screenProcess;

	@Column(name="SEQ_OF_TOTAL")
	private String seqOfTotal;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSACTION_DATE")
	private Timestamp transactionDate;
	
	@Column(name="SWIFT_TYPE")
	private String swiftType;

	public SwiftWaitRecord() {
		this.checkStatus = "N";
	}
	
	public SwiftWaitRecord(SwiftCheckInputBean input) {
		this.callingSystem = input.getCallingSystem();
		this.screenProcess = input.getScreenProcess();
		this.callingUser = input.getCallingUser();
		this.businessUnitId = input.getBusinessUnitId();
		this.branchNumber = input.getBranchNumber();
		this.transactionDate = transTime( input.getTransactionDate() );
		this.partyNumber = input.getPartyNumber();
		this.checkStatus = "N";
	}

	public SwiftWaitRecord(SwiftCheckInputBean input, String swiftString) {
		this.callingSystem = input.getCallingSystem();
		this.screenProcess = input.getScreenProcess();
		this.callingUser = input.getCallingUser();
		this.businessUnitId = input.getBusinessUnitId();
		this.branchNumber = input.getBranchNumber();
		this.transactionDate = transTime( input.getTransactionDate() );
		this.partyNumber = input.getPartyNumber();
		this.swiftFullText = swiftString;
		this.checkStatus = "N";
	}
	
	public SwiftWaitRecordPK getId() {
		return this.id;
	}

	public void setId(SwiftWaitRecordPK id) {
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

	public String getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Timestamp getCreatedTimestamp() {
		return this.createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getDocumentaryCreditNumber() {
		return this.documentaryCreditNumber;
	}

	public void setDocumentaryCreditNumber(String documentaryCreditNumber) {
		this.documentaryCreditNumber = documentaryCreditNumber;
	}

	public String getPartyNumber() {
		return this.partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

	public String getScreenProcess() {
		return this.screenProcess;
	}

	public void setScreenProcess(String screenProcess) {
		this.screenProcess = screenProcess;
	}

	public String getSeqOfTotal() {
		return this.seqOfTotal;
	}

	public void setSeqOfTotal(String seqOfTotal) {
		this.seqOfTotal = seqOfTotal;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public Timestamp getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getSwiftType() {
		return swiftType;
	}

	public void setSwiftType(String swiftType) {
		this.swiftType = swiftType;
	}

	// String -> Time Stamp
	private Timestamp transTime(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = sdf.parse(time);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "SwiftWaitRecord [id=" + id
				+ ", swiftType=" + swiftType 
				+ ", branchNumber=" + branchNumber
				+ ", businessUnitId=" + businessUnitId 
				+ ", callingSystem=" + callingSystem 
				+ ", callingUser=" + callingUser
				+ ", checkStatus=" + checkStatus 
				+ ", createdTimestamp=" + createdTimestamp 
				+ ", documentaryCreditNumber=" + documentaryCreditNumber 
				+ ", partyNumber=" + partyNumber
				+ ", screenProcess=" + screenProcess 
				+ ", seqOfTotal=" + seqOfTotal 
//				+ ", swiftFullText=" + swiftFullText
				+ ", transactionDate=" + transactionDate + "]";
	}

	
}