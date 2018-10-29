package com.eai.wsdl.sendRecv;

import javax.xml.bind.annotation.XmlElement;

public class Status {
	private String statusCode;

	private String severity;

	private String statusDesc;

	private String system;

	@XmlElement(name = "StatusCode")
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@XmlElement(name = "Severity")
	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	@XmlElement(name = "StatusDesc")
	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	@XmlElement(name = "System")
	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

}
