package com.eai.wsdl.sendRecv;

import javax.xml.bind.annotation.XmlElement;

public class TrxRs {

	private String refID;

	private String statusCode;

	private String statusMsg;

	@XmlElement(name = "RefID")
	public String getRefID() {
		return refID;
	}

	public void setRefID(String refID) {
		this.refID = refID;
	}

	@XmlElement(name = "Scode")
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@XmlElement(name = "StatsMsg")
	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

}
