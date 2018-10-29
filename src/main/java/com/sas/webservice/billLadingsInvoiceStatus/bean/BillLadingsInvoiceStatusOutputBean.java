package com.sas.webservice.billLadingsInvoiceStatus.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;

@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillLadingsInvoiceStatusOutputBean {
	@XmlElement(name = "interface_name", required = true)
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

	@XmlElement(name = "reference_number", required = false)
	private String referenceNumber;
	
	@XmlElement(name = "route_rule", required = false)
	private String routeRule;
	
	@XmlElement(name = "nc_case_id", required = false)
	private String ncCaseId;
	
	@XmlElement(name = "nc_case_status", required = false)
	private String ncCaseStatus;
	
	@XmlElement(name = "nc_close_reason", required = false)
	private String ncCloseReason;
	
//	@XmlElement(name = "pep_flag", required = false)
//	private String pepFlag;
	
//	@XmlElement(name = "pnmp_flag", required = false)
//	private String pnmpFlag;
	
//	@XmlElementWrapper(name="NameCheckOutputDeatils")
//	@XmlElement(name = "seq")
//	private List<NameCheckStatusOutputDetailBean> seq;
	
	public BillLadingsInvoiceStatusOutputBean(){
		
	}
	
	public BillLadingsInvoiceStatusOutputBean(NameCheckRecordMain main){
		if(main != null){
			this.interfaceName = main.getInterfaceName();
			this.ncReferenceId = String.valueOf(main.getId().getNcReferenceId());
			this.ncResult = main.getNcResult();
			this.uniqueKey = main.getId().getUniqueKey();
			this.referenceNumber = String.valueOf(main.getReferenceNumber());
			this.routeRule = main.getRouteRule();
			this.ncCloseReason = main.getNcCloseReason();
			this.ncCaseId = main.getCaseRk() != 0 ? String.valueOf(main.getCaseRk()) : "";
//			this.pepFlag = main.getPepFlag();
//			this.pnmpFlag = main.getPnmpFlag();
		}
	}
	
	public BillLadingsInvoiceStatusOutputBean(NameCheckRecordMain main, List<NameCheckRecordDetail> detailList){
		if(main != null){
			this.interfaceName = main.getInterfaceName();
			this.ncReferenceId = String.valueOf(main.getId().getNcReferenceId());
			this.ncResult = main.getNcResult();
			this.uniqueKey = main.getId().getUniqueKey();
			this.referenceNumber = String.valueOf(main.getReferenceNumber());
			this.routeRule = main.getRouteRule();
			this.ncCloseReason = main.getNcCloseReason();
			this.ncCaseId = main.getCaseRk() != 0 ? String.valueOf(main.getCaseRk()) : "";
//			this.pepFlag = main.getPepFlag();
//			this.pnmpFlag = main.getPnmpFlag();
			
//			this.seq = new ArrayList<NameCheckStatusOutputDetailBean>();
//			if(detailList != null && detailList.size() > 0){
//				for(NameCheckRecordDetail detail : detailList){
//					seq.add(new NameCheckStatusOutputDetailBean(detail));
//				}
//			}
		}
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
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
//	public String getPepFlag() {
//		return pepFlag;
//	}
//	public void setPepFlag(String pepFlag) {
//		this.pepFlag = pepFlag;
//	}
//	public String getPnmpFlag() {
//		return pnmpFlag;
//	}
//	public void setPnmpFlag(String pnmpFlag) {
//		this.pnmpFlag = pnmpFlag;
//	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}