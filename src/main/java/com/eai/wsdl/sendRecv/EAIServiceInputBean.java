package com.eai.wsdl.sendRecv;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "IFX")
public class EAIServiceInputBean {

	private TrxSvcRq trxSvcRq;

	private InputHeader header;
	
	private String module;

	@XmlElement(name = "TrxSvcRq")
	public TrxSvcRq getTrxSvcRq() {
		return trxSvcRq;
	}

	public void setTrxSvcRq(TrxSvcRq trxSvcRq) {
		this.trxSvcRq = trxSvcRq;
	}

	@XmlElement(name = "Header")
	public InputHeader getHeader() {
		return header;
	}

	public void setHeader(InputHeader header) {
		this.header = header;
	}

	@XmlTransient
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	

}
