package com.sas.webservice.nameCheck.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

import com.sas.constraint.SwiftMtConst;
import com.sas.util.ValidateString;

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
	@NotEmpty(message="Interface Name Empty ")
	@ValidateString(acceptedValues = { SwiftMtConst.INTERFACE_TYPE_NAMECHECK,
			SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK,
			SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECKSTATUS,
			SwiftMtConst.INTERFACE_TYPE_BATCHTRANSACTIONSCREENING }, message = "Interface Name Format Error")
	private String interfaceName;

	@XmlElement(name = "calling_system", required = true)
	@NotEmpty(message="Calling System Empty ")
	private String callingSystem;
	
	@XmlElement(name = "realtime_flag", required = true)
	@NotEmpty(message="GenAlert Flag Empty ")
	private String genAlertFlag;

	@XmlElement(name = "screen_function", required = true)
	@NotEmpty(message="Screen Process Empty ")
	@ValidateString(acceptedValues = { SwiftMtConst.SCREEN_PROCESS_Account_Opening,
			SwiftMtConst.SCREEN_PROCESS_Transaction_Screening,
			SwiftMtConst.SCREEN_PROCESS_SWIFT_Screening,
			SwiftMtConst.SCREEN_PROCESS_BATCH_TRANCTION_SCREENING,
			SwiftMtConst.SCREEN_PROCESS_BATCH_SWIFT_SCREENING,
			SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_BL_SCREENING,
			SwiftMtConst.SCREEN_PROCESS_Customer_Event,
			SwiftMtConst.SCREEN_PROCESS_Trade_Finance_Screening,
			SwiftMtConst.SCREEN_PROCESS_BATCH_NAME_CHECKING,
			SwiftMtConst.SCREEN_PROCESS_BATCH_TRADE_FINANCE_SCREENING,
			SwiftMtConst.SCREEN_PROCESS_ONLINE_NAME_CHECKING,
			SwiftMtConst.SCREEN_PROCESS_ONLINE_TRADE_FINANCE_INV_SCREENING }, message = "Screen Process Format Error")
	private String screenProcess;

	@XmlElement(name = "calling_user", required = false)
	private String callingUser;
	
	@XmlElement(name = "branch_number", required = true)
	@NotEmpty(message="Branch Number Empty ")
	private String branchNumber;

	@XmlElement(name = "unit", required = false)
	private String businessUnitID;
	
	@XmlElement(name = "unique_key", required = true)
	@NotEmpty(message="Unique Key Empty ")
	private String uniqueKey;

	@XmlElement(name = "party_number", required = true)
	private String partyNumber;

//	@XmlElement(name = "transaction_date", required = false)
//	@XmlJavaTypeAdapter(Y4MMDDAdapter.class)
	@XmlTransient
	private Date transactionDate; 

//	@XmlElement(name = "reference_number", required = false)
	@XmlTransient
	private String referenceNumber;	

	// @XmlElementWrapper(name="NameCheckInputDetails")
	@XmlElement(name = "seq")
	@Size(max=20, message="Over SeqLimit")
	@NotEmpty(message="seq Empty ")
	private List<NameCheckInputDetailBean> seq;
	
	public NameCheckInputBean() {
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
