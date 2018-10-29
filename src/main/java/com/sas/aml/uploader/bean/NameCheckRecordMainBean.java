package com.sas.aml.uploader.bean;

import java.util.Date;

import com.sas.db.wlf.orm.nc.NameCheckRecordMain;

public class NameCheckRecordMainBean {

	private String uniqueKey;

	private int ncReferenceId;
	
	private String branchNumber;
	private String branchNumberName;
	private String businessUnitId;
	private String callingSystem;
	private String callingUser;
	private String callingUserName;
	private String genAlertFlag;
	private String hitSeq;
	private String interfaceName;
	private String ncCloseReason;
	private String ncResult;
	private String referenceNumber;
	private String routeRule;
	private String screenProcess;
	private String partyNumber;
	private Date transactionDate;
	private long caseRk;
	private String pepFlag;
	private String pnmpFlag;
	private String transBranchNumber;
	private String transCallingSystem;	
	private String transNcResult;
	private String transRouteRule;
	private String transScreenProcess;
	
	public NameCheckRecordMainBean() {}
	
	public NameCheckRecordMainBean(NameCheckRecordMain nameCheckRecordMain) {

		this.uniqueKey = nameCheckRecordMain.getId().getUniqueKey();
		this.ncReferenceId = nameCheckRecordMain.getId().getNcReferenceId();
		this.branchNumber = nameCheckRecordMain.getBranchNumber();
		this.businessUnitId = nameCheckRecordMain.getBusinessUnitId();
		this.callingSystem = nameCheckRecordMain.getCallingSystem();
		this.callingUser = nameCheckRecordMain.getCallingUser();
		this.genAlertFlag = nameCheckRecordMain.getGenAlertFlag();
		this.hitSeq = nameCheckRecordMain.getHitSeq();
		this.interfaceName = nameCheckRecordMain.getInterfaceName();
		this.ncCloseReason = nameCheckRecordMain.getNcCloseReason();
		this.ncResult = nameCheckRecordMain.getNcResult();
		this.referenceNumber = nameCheckRecordMain.getReferenceNumber();
		this.routeRule = nameCheckRecordMain.getRouteRule();
		this.screenProcess = nameCheckRecordMain.getScreenProcess();
		this.partyNumber = nameCheckRecordMain.getPartyNumber();
		this.transactionDate = nameCheckRecordMain.getTransactionDate();
		this.caseRk = nameCheckRecordMain.getCaseRk();
		this.pepFlag = nameCheckRecordMain.getPepFlag();
		this.pnmpFlag = nameCheckRecordMain.getPnmpFlag();
	}


	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public int getNcReferenceId() {
		return ncReferenceId;
	}
	public void setNcReferenceId(int ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
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
	public String getGenAlertFlag() {
		return genAlertFlag;
	}
	public void setGenAlertFlag(String genAlertFlag) {
		this.genAlertFlag = genAlertFlag;
	}
	public String getHitSeq() {
		return hitSeq;
	}
	public void setHitSeq(String hitSeq) {
		this.hitSeq = hitSeq;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getNcCloseReason() {
		return ncCloseReason;
	}
	public void setNcCloseReason(String ncCloseReason) {
		this.ncCloseReason = ncCloseReason;
	}
	public String getNcResult() {
		return ncResult;
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
		return routeRule;
	}
	public void setRouteRule(String routeRule) {
		this.routeRule = routeRule;
	}
	public String getScreenProcess() {
		return screenProcess;
	}
	public void setScreenProcess(String screenProcess) {
		this.screenProcess = screenProcess;
	}
	public String getPartyNumber() {
		return partyNumber;
	}
	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}
	public Date getTransactionDate() {
		return transactionDate;
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

	public String getBranchNumberName() {
		return branchNumberName;
	}

	public void setBranchNumberName(String branchNumberName) {
		this.branchNumberName = branchNumberName;
	}

	public String getCallingUserName() {
		return callingUserName;
	}

	public void setCallingUserName(String callingUserName) {
		this.callingUserName = callingUserName;
	}

	public String getTransBranchNumber() {
		return transBranchNumber;
	}

	public void setTransBranchNumber(String transBranchNumber) {
		this.transBranchNumber = transBranchNumber;
	}

	public String getTransCallingSystem() {
		return transCallingSystem;
	}

	public void setTransCallingSystem(String transCallingSystem) {
		this.transCallingSystem = transCallingSystem;
	}

	public String getTransNcResult() {
		return transNcResult;
	}

	public void setTransNcResult(String transNcResult) {
		this.transNcResult = transNcResult;
	}

	public String getTransRouteRule() {
		return transRouteRule;
	}

	public void setTransRouteRule(String transRouteRule) {
		this.transRouteRule = transRouteRule;
	}

	public String getTransScreenProcess() {
		return transScreenProcess;
	}

	public void setTransScreenProcess(String transScreenProcess) {
		this.transScreenProcess = transScreenProcess;
	}
	
}
