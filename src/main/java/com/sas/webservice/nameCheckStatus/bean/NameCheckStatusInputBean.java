package com.sas.webservice.nameCheckStatus.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sas.constraint.SwiftMtConst;
import com.sas.webservice.adapter.Y4MMDDAdapter;

/**
 * AML Name Check Status Input Bean
 * @author SAS
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NameCheckStatusInputBean {
//	@XmlElement(name = "interface_name", required = false)
	@XmlTransient
	private String interfaceName;
	
	@XmlElement(name = "calling_system", required = true)
	private String callingSystem;
	
//	@XmlElement(name = "transaction_date", required = false)
//	@XmlJavaTypeAdapter(Y4MMDDAdapter.class)
	@XmlTransient
	private Date transactionDate;
	
	@XmlElement(name = "nc_reference_id", required = false)
	private String ncReferenceId;
	
//	@XmlElement(name = "reference_number", required = false)
	@XmlTransient
	private String referenceNumber;
	
	@XmlElement(name = "unique_key", required = false)
	private String uniqueKey;

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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getNcReferenceId() {
		return ncReferenceId;
	}

	public void setNcReferenceId(String ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
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

	@Override
	public String toString() {
		return "NameCheckStatusInputBean [interfaceName=" + interfaceName
				+ ", callingSystem=" + callingSystem + ", transactionDate="
				+ transactionDate + ", ncReferenceId=" + ncReferenceId
				+ ", referenceNumber=" + referenceNumber + ", uniqueKey="
				+ uniqueKey + "]";
	}	
}
