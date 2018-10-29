package com.sas.webservice.swiftCheck.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.orm.sc.SwiftCheckRecord;
import com.sas.webservice.adapter.Y4MMDDAdapter;

/**
 * AML SWIFT Check Output Bean
 * @author SAS
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class SwiftCheckOutputBean {
//	@XmlElement(name = "interface_name", required = false)
	@XmlTransient
	protected String interfaceName;		// BOT 沒有
	@XmlElement(name = "nc_reference_id", required = true)
	protected String ncReferenceId;	
	@XmlElement(name = "status_code", required = true)
	private String errorCode;
	@XmlElement(name = "status_message", required = true)
	private String errorMessage;
//	@XmlElement(name = "nc_result", required = false)
	@XmlTransient
	private String ncResult;			// BOT 沒有
//	@XmlElement(name = "nc_close_reason", required = false)
	@XmlTransient
	private String ncCloseReason;		// BOT 沒有
//	@XmlElement(name = "transaction_date", required = false)
//	@XmlJavaTypeAdapter(Y4MMDDAdapter.class)
	@XmlTransient
	private Date transactionDate;		// BOT 沒有
	@XmlElement(name = "unique_key", required = true)
	private String uniqueKey;	
//	@XmlElement(name = "route_rule", required = false)
	@XmlTransient
	private String routeRule;			// BOT 沒有
	
	public SwiftCheckOutputBean(){
	}
	
	public SwiftCheckOutputBean(SwiftCheckRecord swiftCheckRecord){
		this.ncReferenceId = String.valueOf(swiftCheckRecord.getId().getReferenceId());
		this.errorCode = SwiftMtConst.ERROR_CODE_SUCCESS;
		this.errorMessage = "";
		this.ncResult = swiftCheckRecord.getNcResult();
		this.transactionDate = swiftCheckRecord.getTransactionDate();
		this.uniqueKey = swiftCheckRecord.getId().getUniqueKey();
		this.routeRule = swiftCheckRecord.getRouteRule();
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

	public String getNcCloseReason() {
		return ncCloseReason;
	}

	public void setNcCloseReason(String ncCloseReason) {
		this.ncCloseReason = ncCloseReason;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
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

	@Override
	public String toString() {
		return "SwiftCheckOutputBean [interfaceName=" + interfaceName
				+ ", ncReferenceId=" + ncReferenceId + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + ", ncResult="
				+ ncResult + ", ncCloseReason=" + ncCloseReason
				+ ", transactionDate=" + transactionDate + ", uniqueKey="
				+ uniqueKey + ", routeRule=" + routeRule + "]";
	}
	
}
