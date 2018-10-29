package com.eai.wsdl.sendRecv;

import javax.xml.bind.annotation.XmlElement;

public class TrxSvcRs {

	private TrxRs trsRs;

	@XmlElement(name = "TrxRs")
	public TrxRs getTrsRs() {
		return trsRs;
	}

	public void setTrsRs(TrxRs trsRs) {
		this.trsRs = trsRs;
	}

}
