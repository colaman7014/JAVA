package com.sas.webservice.swiftCheckStatus.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.sas.db.wlf.orm.sc.SwiftCheckRecord;
/**
 * AML SWIFT Check Status Output Bean
 * @author SAS
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class SwiftCheckStatusOutputBean {
//	@XmlElement(name = "interface_name", required = false)
	@XmlTransient
	private String interfaceName;	// BOT 沒有
	
	@XmlElement(name = "nc_reference_id", required = true)
	private String ncReferenceId;
	
	@XmlElement(name = "unique_key", required = true)
	private String uniqueKey;
		
	@XmlElement(name = "status_code", required = true)
	private String errorCode;

	@XmlElement(name = "status_message", required = true)
	private String errorMessage;
	
	@XmlElement(name = "branch_number", required = true)
	protected String branchNumber;	// BOT 新增

	@XmlElement(name = "unit", required = true)
	protected String businessUnitId;	// BOT 新增
	
	@XmlElement(name = "nc_result", required = true)
	private String ncResult;
	
	@XmlElement(name = "hit_list_session", required = true)
	private String routeRule;
	
	@XmlElement(name = "nc_case_id", required = true)
	private String ncCaseId;

	@XmlElement(name = "nc_case_status", required = true)
	private String ncCaseStatus;

//	@XmlElement(name = "nc_close_reason", required = true)
	@XmlTransient
	private String ncCloseReason;	// BOT 沒有
	
	public SwiftCheckStatusOutputBean(){
	}
	
	public SwiftCheckStatusOutputBean(String interfaceName, String ncReferenceId, String errorCode, String errorMessage, String ncResult, String uniqueKey, String routeRule, String ncCaseId, String ncCaseStatus, String ncCloseReason, String branchNumber, String businessUnitId){
		this.interfaceName = interfaceName;
		this.ncReferenceId = ncReferenceId;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.ncResult = ncResult;
		this.uniqueKey = uniqueKey;
		this.routeRule = routeRule;
		this.ncCaseId = ncCaseId;
		this.ncCaseStatus = ncCaseStatus;
		this.ncCloseReason = ncCloseReason;
		this.branchNumber = branchNumber;
		this.businessUnitId = businessUnitId;
	}
	
	public SwiftCheckStatusOutputBean(SwiftCheckRecord swiftCheckRecord){
		this.ncReferenceId = String.valueOf(swiftCheckRecord.getId().getReferenceId());
		this.ncResult = swiftCheckRecord.getNcResult();
		this.uniqueKey = swiftCheckRecord.getId().getUniqueKey();
		this.routeRule = swiftCheckRecord.getRouteRule();
		this.ncCloseReason = swiftCheckRecord.getNcCloseReason();
		this.branchNumber = swiftCheckRecord.getBranchNumber();
		this.businessUnitId = swiftCheckRecord.getBusinessUnitId();
	}

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

	public String getRouteRule() {
		return routeRule;
	}

	public void setRouteRule(String routeRule) {
		this.routeRule = routeRule;
	}

	public String getNcCaseId() {
		return ncCaseId;
	}

	public void setNcCaseId(String ncCaseId) {
		this.ncCaseId = ncCaseId;
	}

	public String getNcCaseStatus() {
		return ncCaseStatus;
	}

	public void setNcCaseStatus(String ncCaseStatus) {
		this.ncCaseStatus = ncCaseStatus;
	}

	public String getNcCloseReason() {
		return ncCloseReason;
	}

	public void setNcCloseReason(String ncCloseReason) {
		this.ncCloseReason = ncCloseReason;
	}

	public String getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(String businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
