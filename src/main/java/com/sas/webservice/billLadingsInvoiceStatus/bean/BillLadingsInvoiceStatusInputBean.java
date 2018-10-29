package com.sas.webservice.billLadingsInvoiceStatus.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sas.webservice.adapter.Y4MMDDAdapter;

@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillLadingsInvoiceStatusInputBean {
	@XmlElement(name = "interface_name", required = false)
	private String interfaceName;
	
	@XmlElement(name = "nc_reference_id", required = false)
	private String ncReferenceId;
	
	@XmlElement(name = "unique_key", required = false)
	private String uniqueKey;

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
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

	@Override
	public String toString() {
		return "BillLadingsInvoiceStatusInputBean [interfaceName="
				+ interfaceName + ", ncReferenceId=" + ncReferenceId
				+ ", uniqueKey=" + uniqueKey + "]";
	}
}
