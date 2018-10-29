package com.sas.webservice.nameCheck.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * AML Name Check Output Bean
 * @author SAS
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NameCheckOutputBean {

	@XmlTransient
	private String interfaceName;	
	
	@XmlElement(name = "nc_reference_id", required = true)
	private String ncReferenceId;
	
	@XmlElement(name = "unique_key", required = true)
	private String uniqueKey;
	
	@XmlElement(name = "status_code", required = true)
	private String errorCode;
	
	@XmlElement(name = "status_message", required = true)
	private String errorMessage;

	@XmlElement(name = "nc_result", required = true)
	private String ncResult;

	@XmlElement(name = "hit_list_session", required = true)
	private String routeRule;
	
	@XmlElement(name = "hit_seq", required = true)
	private String hitSeq;

	@XmlTransient
	private String refernceNumber;	

	@XmlElement(name = "seq", required = true)
	private List<NameCheckOutputDetail> seq;
	
	//只記錄當下名單檢核產生的caseRk，不回傳至xml中
	@XmlTransient
	private BigDecimal caseRk;
	
	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getNcReferenceId() {
		return ncReferenceId;
	}

	public void setNcReferenceId(String ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getNcResult() {
		return ncResult;
	}

	public void setNcResult(String ncResult) {
		this.ncResult = ncResult;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getRefernceNumber() {
		return refernceNumber;
	}

	public void setRefernceNumber(String refernceNumber) {
		this.refernceNumber = refernceNumber;
	}

	public String getRouteRule() {
		return routeRule;
	}

	public void setRouteRule(String routeRule) {
		this.routeRule = routeRule;
	}

	public String getHitSeq() {
		return hitSeq;
	}

	public void setHitSeq(String hitSeq) {
		this.hitSeq = hitSeq;
	}

	public List<NameCheckOutputDetail> getSeq() {
		return seq;
	}

	public void setSeq(List<NameCheckOutputDetail> seq) {
		this.seq = seq;
	}


	@Override
	public String toString() {
		return "NameCheckOutputBean [interfaceName=" + interfaceName + ", ncReferenceId=" + ncReferenceId
				+ ", uniqueKey=" + uniqueKey + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ ", ncResult=" + ncResult + ", routeRule=" + routeRule + ", hitSeq=" + hitSeq + ", refernceNumber="
				+ refernceNumber + ", seq=" + seq + ", caseRk=" + caseRk + "]";
	}

	public BigDecimal getCaseRk() {
		return caseRk;
	}

	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
	}
}
