package com.sas.webservice.nameCheck.bean;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sas.webservice.adapter.Y4MMDDAdapter;

/**
 * AML Name Check Input Bean
 * @author SAS
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class BatchNameCheckInputBean {
	@XmlElement(name = "interface_name", required = false)
	private String interfaceName;
	
	@XmlElement(name = "calling_system", required = true)
	private String callingSystem;

	@XmlElement(name = "screen_process", required = true)
	private String screenProcess;
	
	@XmlElement(name = "calling_user", required = false)
	private String callingUser;

	@XmlElement(name = "business_unit_id", required = false)
	private String businessUnitID;

	@XmlElement(name = "branch_number", required = true)
	private String branchNumber;
	
	@XmlElement(name = "gen_alert_flag", required = true)
	private String genAlertFlag;

	@XmlElement(name = "transaction_date", required = true)
	@XmlJavaTypeAdapter(Y4MMDDAdapter.class)
	private Date transactionDate;

	@XmlElement(name = "unique_key", required = true)
	private String uniqueKey;
	
	@XmlElement(name = "reference_number", required = true)
	private String referenceNumber;
	
	@XmlElement(name = "party_number", required = false)
	private String partyNumber;
	
	@XmlElement(name = "night_batch_option", required = false)
	private String nightBatchOption;
	
	
//	@XmlElementWrapper(name="NameCheckInputDetails")
	@XmlElement(name = "seq")
	private List<BatchNameCheckInputDetailBean> seq;

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

	public String getBusinessUnitID() {
		return businessUnitID;
	}

	public void setBusinessUnitID(String businessUnitID) {
		this.businessUnitID = businessUnitID;
	}

	public String getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getGenAlertFlag() {
		return genAlertFlag;
	}

	public void setGenAlertFlag(String genAlertFlag) {
		this.genAlertFlag = genAlertFlag;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getPartyNumber() {
		return partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

	public List<BatchNameCheckInputDetailBean> getSeq() {
		return seq;
	}

	public void setSeq(List<BatchNameCheckInputDetailBean> seq) {
		this.seq = seq;
	}

	public String getNightBatchOption() {
		return nightBatchOption;
	}

	public void setNightBatchOption(String nightBatchOption) {
		this.nightBatchOption = nightBatchOption;
	}

	@Override
	public String toString() {
		return "NameCheckInputBean [interfaceName=" + interfaceName
				+ ", callingSystem=" + callingSystem + ", screenProcess="
				+ screenProcess + ", callingUser=" + callingUser
				+ ", businessUnitID=" + businessUnitID + ", branchNumber="
				+ branchNumber + ", genAlertFlag=" + genAlertFlag
				+ ", transactionDate=" + transactionDate + ", uniqueKey="
				+ uniqueKey + ", referenceNumber=" + referenceNumber
				+ ", partyNumber=" + partyNumber + ", seq=" + seq + "]";
	}
}
