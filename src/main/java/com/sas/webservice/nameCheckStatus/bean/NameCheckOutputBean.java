package com.sas.webservice.nameCheckStatus.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.sas.webservice.nameCheck.bean.NameCheckOutputDetail;

/**
 * AML Name Check Status Output Bean
 * @author SAS
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NameCheckOutputBean {
	@XmlElement(name = "interface_Name", required = false)
	private String interfaceName;
	
	@XmlElement(name = "nc_reference_id", required = true)
	private String ncReferenceId;
	
	@XmlElement(name = "error_code", required = true)
	private String errorCode;

	@XmlElement(name = "error_message", required = true)
	private String errorMessage;

	@XmlElement(name = "nc_result", required = true)
	private String ncResult;

	@XmlElement(name = "unique_key", required = true)
	private String uniqueKey;

	@XmlElement(name = "reference_number", required = true)
	private String refernceNumber;
	
	@XmlElement(name = "route_rule", required = true)
	private String routeRule;
	
	@XmlElement(name = "hit_seq", required = true)
	private String hitSeq;
	
//	@XmlElementWrapper(name="NameCheckOutputDeatils", required = true)
	@XmlElement(name = "seq", required = true)
	private List<NameCheckOutputDetail> seq;
	
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
		return "NameCheckOutputBean [interfaceName=" + interfaceName
				+ ", ncReferenceId=" + ncReferenceId + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + ", ncResult="
				+ ncResult + ", uniqueKey=" + uniqueKey + ", refernceNumber="
				+ refernceNumber + ", routeRule=" + routeRule + ", hitSeq="
				+ hitSeq + ", seq=" + seq + "]";
	}
}
