package com.sas.webservice.nameCheck.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Ftp Name Check Output Bean
 * 
 * @author Eric Su
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class AmlFtpNameCheckOutputBean {
	@XmlElement(name = "finish", required = true)
	private Boolean finish;

	@XmlElement(name = "message", required = false)
	private Boolean message;

	@XmlElement(name = "server_name", required = true)
	private String serverName;

	public Boolean isFinish() {
		return finish;
	}

	public void setFinish(Boolean finish) {
		this.finish = finish;
	}

	public Boolean isMessage() {
		return message;
	}

	public void setMessage(boolean message) {
		this.message = message;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
