package com.eai.wsdl.sendRecv;

import javax.xml.bind.annotation.XmlElement;

public class OutputHeader extends InputHeader {

	private Status status;

	private String serverDt;
	
	@XmlElement(name = "Status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@XmlElement(name = "ServerDt")
	public String getServerDt() {
		return serverDt;
	}

	public void setServerDt(String serverDt) {
		this.serverDt = serverDt;
	}
}
