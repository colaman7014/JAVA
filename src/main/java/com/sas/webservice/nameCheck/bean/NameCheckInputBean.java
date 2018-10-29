package com.sas.webservice.nameCheck.bean;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.StringUtils;

import com.sas.constraint.SwiftMtConst;
import com.sas.webservice.adapter.Y4MMDDAdapter;

/**
 * AML Name Check Input Bean
 * 
 * @author SAS
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NameCheckInputBean {
//	@XmlElement(name = "interface_name", required = false)
	@XmlTransient
	private String interfaceName;

	@XmlElement(name = "calling_system", required = true)
	private String callingSystem;
	
	@XmlElement(name = "realtime_flag", required = true)
	private String genAlertFlag;

	@XmlElement(name = "screen_function", required = true)
	private String screenProcess;

	@XmlElement(name = "calling_user", required = false)
	private String callingUser;
	
	@XmlElement(name = "branch_number", required = true)
	private String branchNumber;

	@XmlElement(name = "unit", required = false)
	private String businessUnitID;
	
	@XmlElement(name = "unique_key", required = true)
	private String uniqueKey;

	@XmlElement(name = "party_number", required = true)
	private String partyNumber;

//	@XmlElement(name = "transaction_date", required = false)
//	@XmlJavaTypeAdapter(Y4MMDDAdapter.class)
	@XmlTransient
	private Date transactionDate; //BOT 沒有，但會檢查其值

//	@XmlElement(name = "reference_number", required = false)
	@XmlTransient
	private String referenceNumber;		//BOT 沒有，但會檢查其值

	// @XmlElementWrapper(name="NameCheckInputDetails")
	@XmlElement(name = "seq")
	private List<NameCheckInputDetailBean> seq;
	
	public NameCheckInputBean() {
	}

	public NameCheckInputBean(FtpNameCheckInputBean ftpBean) {
		this.callingSystem = ftpBean.getCallingSystem();
		this.genAlertFlag = ftpBean.getRealtimeFlag();
		this.screenProcess = ftpBean.getScreenFunction();
		this.callingUser = ftpBean.getCallingUser();
		this.branchNumber = ftpBean.getBranchNumber();
		this.businessUnitID = ftpBean.getUnit();
		this.uniqueKey = ftpBean.getUniqueKey();
		this.partyNumber = ftpBean.getPartyNumber();
	}

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

	public List<NameCheckInputDetailBean> getSeq() {
		return seq;
	}

	public void setSeq(List<NameCheckInputDetailBean> seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "NameCheckInputBean [interfaceName=" + interfaceName + ", callingSystem=" + callingSystem
				+ ", screenProcess=" + screenProcess + ", callingUser=" + callingUser + ", businessUnitID="
				+ businessUnitID + ", branchNumber=" + branchNumber + ", genAlertFlag=" + genAlertFlag
				+ ", transactionDate=" + transactionDate + ", uniqueKey=" + uniqueKey + ", referenceNumber="
				+ referenceNumber + ", partyNumber=" + partyNumber + ", seq=" + seq + "]";
	}
}
