package com.eai.wsdl.sendRecv;

import javax.xml.bind.annotation.XmlElement;

public class InputHeader {

	private String msgId;

	private String clientAppSeq;

	private String serverAppSeq;

	@XmlElement(name = "MsgId")
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@XmlElement(name = "ClientAppSeq")
	public String getClientAppSeq() {
		return clientAppSeq;
	}

	public void setClientAppSeq(String clientAppSeq) {
		this.clientAppSeq = clientAppSeq;
	}

	@XmlElement(name = "ServerAppSeq")
	public String getServerAppSeq() {
		return serverAppSeq;
	}

	public void setServerAppSeq(String serverAppSeq) {
		this.serverAppSeq = serverAppSeq;
	}

}
