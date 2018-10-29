package com.sas.webservice.createCase.bean;

import java.util.Date;

public class CreateCaseInputBean {
	
	private long caseRk;
	
	private long incidentRk;
	
	private Date validFromDttm;
	
	private String ncReferenceId;
	
	private String callingSystem;
	
	private String callingUser;
	
	private String branchNumber;
	
	private String routeRule;
	
	private String transactionDate;
	
	private String hitSeq;
	
	private String partyNo;
	
	private String referenceNumber;
	
	private String uniqueKey;
	
	private String sourceType;
	
	private boolean isCreateIncident;
	
	private String screenProcess;
	
	private String swiftFullText;
	
	public CreateCaseInputBean(){
		
	}

	public CreateCaseInputBean(String ncReferenceId, String callingSystem,
			String callingUser, String branchNumber, String routeRule, 
			String transactionDate, 	String hitSeq, String partyNo, 
			String referenceNumber, String uniqueKey, String sourceType, 
			boolean isCreateIncident, String screenProcess){
		this.ncReferenceId = ncReferenceId;
		this.callingSystem = callingSystem;
		this.callingUser = callingUser;
		this.branchNumber = branchNumber;
		this.routeRule = routeRule;
		this.transactionDate = transactionDate;
		this.hitSeq = hitSeq;
		this.partyNo = partyNo;
		this.referenceNumber = referenceNumber;
		this.uniqueKey = uniqueKey;
		this.sourceType = sourceType;
		this.isCreateIncident = isCreateIncident;
		this.screenProcess = screenProcess;
	}
	public long getCaseRk() {
		return caseRk;
	}

	public void setCaseRk(long caseRk) {
		this.caseRk = caseRk;
	}

	public long getIncidentRk() {
		return incidentRk;
	}

	public void setIncidentRk(long incidentRk) {
		this.incidentRk = incidentRk;
	}

	public Date getValidFromDttm() {
		return validFromDttm;
	}

	public void setValidFromDttm(Date validFromDttm) {
		this.validFromDttm = validFromDttm;
	}

	public String getNcReferenceId() {
		return ncReferenceId;
	}

	public void setNcReferenceId(String ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
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

	public String getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getRouteRule() {
		return routeRule;
	}

	public void setRouteRule(String routeRule) {
		this.routeRule = routeRule;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getHitSeq() {
		return hitSeq;
	}

	public void setHitSeq(String hitSeq) {
		this.hitSeq = hitSeq;
	}

	public String getPartyNo() {
		return partyNo;
	}

	public void setPartyNo(String partyNo) {
		this.partyNo = partyNo;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public void setReferenceNo(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public boolean isCreateIncident() {
		return isCreateIncident;
	}

	public void setCreateIncident(boolean isCreateIncident) {
		this.isCreateIncident = isCreateIncident;
	}

	public String getScreenProcess() {
		return screenProcess;
	}

	public void setScreenProcess(String screenProcess) {
		this.screenProcess = screenProcess;
	}

	public String getSwiftFullText() {
		return swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}
	
	
}
