package com.sas.aml.uploader.bean;

import java.sql.Timestamp;

import com.sas.db.wlf.orm.sc.SwiftWaitRecord;

public class SwiftWaitRecordBean {

	private String groupUniqueKey;
	private String uniqueKey;
	
	private String branchNumber;
	private String businessUnitId;
	private String callingSystem;
	private String callingUser;
	private String checkStatus;
	private Timestamp createdTimestamp;
	private String documentaryCreditNumber;
	private String partyNumber;
	private String screenProcess;
	private String seqOfTotal;
	private String swiftFullText;
	private Timestamp transactionDate;
	private String swiftType;
	
	public SwiftWaitRecordBean() {}
	
	public SwiftWaitRecordBean(SwiftWaitRecord swiftWaitRecord) {
		this.groupUniqueKey = swiftWaitRecord.getId().getGroupUniqueKey();
		this.uniqueKey = swiftWaitRecord.getId().getUniqueKey();
		this.branchNumber = swiftWaitRecord.getBranchNumber();
		this.businessUnitId = swiftWaitRecord.getBusinessUnitId();
		this.callingSystem = swiftWaitRecord.getCallingSystem();
		this.callingUser = swiftWaitRecord.getCallingUser();
		this.checkStatus = swiftWaitRecord.getCheckStatus();
		this.createdTimestamp = swiftWaitRecord.getCreatedTimestamp();
		this.documentaryCreditNumber = swiftWaitRecord.getDocumentaryCreditNumber();
		this.partyNumber = swiftWaitRecord.getPartyNumber();
		this.screenProcess = swiftWaitRecord.getScreenProcess();
		this.seqOfTotal = swiftWaitRecord.getSeqOfTotal();
		this.swiftFullText = swiftWaitRecord.getSwiftFullText();
		this.transactionDate = swiftWaitRecord.getTransactionDate();
		this.swiftType = swiftWaitRecord.getSwiftType();
	}
	
	public String getGroupUniqueKey() {
		return groupUniqueKey;
	}
	public void setGroupUniqueKey(String groupUniqueKey) {
		this.groupUniqueKey = groupUniqueKey;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getBranchNumber() {
		return branchNumber;
	}
	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}
	public String getBusinessUnitId() {
		return businessUnitId;
	}
	public void setBusinessUnitId(String businessUnitId) {
		this.businessUnitId = businessUnitId;
	}
	public String getCallingSystem() {
		return callingSystem;
	}
	public void setCallingSystem(String callingSystem) {
		this.callingSystem = callingSystem;
	}
	public String getCallingUser() {
		return callingUser;
	}
	public void setCallingUser(String callingUser) {
		this.callingUser = callingUser;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public String getDocumentaryCreditNumber() {
		return documentaryCreditNumber;
	}
	public void setDocumentaryCreditNumber(String documentaryCreditNumber) {
		this.documentaryCreditNumber = documentaryCreditNumber;
	}
	public String getPartyNumber() {
		return partyNumber;
	}
	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}
	public String getScreenProcess() {
		return screenProcess;
	}
	public void setScreenProcess(String screenProcess) {
		this.screenProcess = screenProcess;
	}
	public String getSeqOfTotal() {
		return seqOfTotal;
	}
	public void setSeqOfTotal(String seqOfTotal) {
		this.seqOfTotal = seqOfTotal;
	}
	public String getSwiftFullText() {
		return swiftFullText;
	}
	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}
	public Timestamp getTransactionDate() {
		return transactionDate;
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
}
