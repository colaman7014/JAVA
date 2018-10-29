package com.sas.webservice.nameCheckStatus.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;

/**
 * AML Name Check Status Output Detail Bean
 * @author SAS
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NameCheckStatusOutputDetailBean {
	@XmlElement(name = "check_seq", required = true)
	private String checkSeq;

	@XmlElement(name = "check_result", required = true)
	private String checkResult;

	@XmlElement(name = "hit_list", required = true)
	private String routeRule;
	
	public NameCheckStatusOutputDetailBean(NameCheckRecordDetail detail){
		this.checkSeq = detail.getId().getCheckSeq();
		this.checkResult = detail.getCheckResult()==null?" ":detail.getCheckResult();
		this.routeRule = detail.getRouteRule();
	}

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
		return "NameCheckStatusOutputDetail [checkSeq=" + checkSeq
				+ ", checkResult=" + checkResult + ", routeRule=" + routeRule
				+ "]";
	}

}
