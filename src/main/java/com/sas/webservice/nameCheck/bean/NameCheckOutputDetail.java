package com.sas.webservice.nameCheck.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * AML Name Check Output Detail Bean
 * @author SAS
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NameCheckOutputDetail {
	@XmlElement(name = "check_seq", required = true)
	private String checkSeq;

	@XmlElement(name = "check_result", required = true)
	private String checkResult;

	@XmlElement(name = "hit_list", required = true)
	private String routeRule;

	public String getCheckSeq() {
		return checkSeq;
	}

	public void setCheckSeq(String checkSeq) {
		this.checkSeq = checkSeq;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getRouteRule() {
		return routeRule;
	}

	public void setRouteRule(String routeRule) {
		this.routeRule = routeRule;
	}

	@Override
	public String toString() {
		return "NameCheckOutputDeatil [checkSeq=" + checkSeq
				+ ", checkResult=" + checkResult + ", routeRule="
				+ routeRule + "]";
	}
}
