package com.eai.wsdl.sendRecv;

import javax.xml.bind.annotation.XmlElement;

public class TrxSvcRq {

	private TrxRq trxRq;

	@XmlElement(name = "TrxRq")
	public TrxRq getTrxRq() {
		return trxRq;
	}

	public void setTrxRq(TrxRq trxRq) {
		this.trxRq = trxRq;
	}
}
