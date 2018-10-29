package com.eai.wsdl.sendRecv;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IFX")
public class EAIServiceOutputBean {

	private OutputHeader header;

	private TrxSvcRs trxSvcRs;

	@XmlElement(name = "TrxSvcRs")
	public TrxSvcRs getTrxSvcRs() {
		return trxSvcRs;
	}

	public void setTrxSvcRs(TrxSvcRs trxSvcRs) {
		this.trxSvcRs = trxSvcRs;
	}

	@XmlElement(name = "Header")
	public OutputHeader getHeader() {
		return header;
	}

	public void setHeader(OutputHeader header) {
		this.header = header;
	}
}
