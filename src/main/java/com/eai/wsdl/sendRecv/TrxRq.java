package com.eai.wsdl.sendRecv;

import javax.xml.bind.annotation.XmlElement;

public class TrxRq {

	private String lastUpdateDate;

	private String refID;

	private String dataContentCode;

	private String lastUpdateTime;

	private String custPermId;

	private String dataValue;

	@XmlElement(name = "LastUpdateDate")
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@XmlElement(name = "RefID")
	public String getRefID() {
		return refID;
	}

	public void setRefID(String refID) {
		this.refID = refID;
	}

	@XmlElement(name = "DataContentCode")
	public String getDataContentCode() {
		return dataContentCode;
	}

	public void setDataContentCode(String dataContentCode) {
		this.dataContentCode = dataContentCode;
	}

	@XmlElement(name = "LastUpdateTime")
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@XmlElement(name = "CustPermId")
	public String getCustPermId() {
		return custPermId;
	}

	public void setCustPermId(String custPermId) {
		this.custPermId = custPermId;
	}

	@XmlElement(name = "DataValue")
	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

}
