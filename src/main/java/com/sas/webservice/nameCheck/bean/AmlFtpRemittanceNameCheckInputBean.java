package com.sas.webservice.nameCheck.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Remittance Ftp Name Check Input Bean
 * 
 * @author Danniel
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class AmlFtpRemittanceNameCheckInputBean {
	@XmlElement(name = "trigger", required = true)
	private Boolean trigger;

	public Boolean isTrigger() {
		return trigger;
	}

	public void setTrigger(Boolean trigger) {
		this.trigger = trigger;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
