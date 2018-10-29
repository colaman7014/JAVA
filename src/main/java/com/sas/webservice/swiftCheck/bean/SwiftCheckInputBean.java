package com.sas.webservice.swiftCheck.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * AML SWIFT Check Input Bean
 * @author SAS
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class SwiftCheckInputBean {
//	@XmlElement(name = "interface_name", required = false)
	@XmlTransient
	protected String interfaceName;
	@XmlElement(name = "calling_system", required = true)
	protected String callingSystem;
	@XmlElement(name = "realtime_flag", required = true)
	private String genAlertFlag;		// BOT 新增
	@XmlElement(name = "screen_function", required = true)
	protected String screenProcess;
	@XmlElement(name = "calling_user", required = false)
	protected String callingUser;
	@XmlElement(name = "branch_number", required = true)
	protected String branchNumber;
	@XmlElement(name = "unit", required = false)
	protected String businessUnitId;
//	@XmlElement(name = "transaction_date", required = false)
	@XmlTransient
	protected String transactionDate;
	@XmlElement(name = "unique_key", required = true)
	protected String uniqueKey;
	@XmlElement(name = "swift_rje", required = true)
	protected String swiftRje;
	@XmlElement(name = "party_number", required = true)
	protected String partyNumber;
	
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getCallingSystem() {
		return callingSystem;
	}
	public void setCallingSystem(String callingSystem) {
		this.callingSystem = callingSystem;
	}
	public String getGenAlertFlag() {
		return genAlertFlag;
	}
	public void setGenAlertFlag(String genAlertFlag) {
		this.genAlertFlag = genAlertFlag;
	}
	public String getScreenProcess() {
		return screenProcess;
	}
	public void setScreenProcess(String screenProcess) {
		this.screenProcess = screenProcess;
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
	public String getBusinessUnitId() {
		return businessUnitId;
	}
	public void setBusinessUnitId(String businessUnitId) {
		this.businessUnitId = businessUnitId;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getSwiftRje() {
		return swiftRje;
	}
	public void setSwiftRje(String swiftRje) {
		this.swiftRje = swiftRje;
	}
	public String getPartyNumber() {
		return partyNumber;
	}
	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}
	@Override
	public String toString() {
		return "SwiftCheckInputBean [interfaceName=" + interfaceName
				+ ", callingSystem=" + callingSystem + ", genAlertFlag="
				+ genAlertFlag + ", screenProcess=" + screenProcess
				+ ", callingUser=" + callingUser + ", branchNumber="
				+ branchNumber + ", businessUnitId=" + businessUnitId
				+ ", transactionDate=" + transactionDate + ", uniqueKey="
				+ uniqueKey + ", swiftRje=" + swiftRje + ", partyNumber="
				+ partyNumber + "]";
	}
}
