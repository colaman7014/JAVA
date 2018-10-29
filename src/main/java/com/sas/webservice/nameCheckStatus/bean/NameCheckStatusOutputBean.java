package com.sas.webservice.nameCheckStatus.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;

/**
 * AML Name Check Status Output Bean
 * @author SAS
 *
 */
@XmlRootElement(name = "aml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NameCheckStatusOutputBean {
public String getNcCloseReason() {
		return ncCloseReason;
	}

	public void setNcCloseReason(String ncCloseReason) {
		this.ncCloseReason = ncCloseReason;
	}

	//	@XmlElement(name = "interface_name", required = false)
	@XmlTransient
	private String interfaceName;	//BOT 沒有
	
	@XmlElement(name = "nc_reference_id", required = true)
	private String ncReferenceId;
	
	@XmlElement(name = "unique_key", required = true)
	private String uniqueKey;
	
	@XmlElement(name = "status_code", required = true)
	private String errorCode;

	@XmlElement(name = "status_message", required = true)
	private String errorMessage;
	
	@XmlElement(name = "branch_number", required = true)
	protected String branchNumber;		// BOT 新增

	@XmlElement(name = "unit", required = true)
	protected String businessUnitId;	// BOT 新增

	@XmlElement(name = "nc_result", required = true)
	private String ncResult;
	
	@XmlElement(name = "nc_case_id", required = true)
	private String ncCaseId;
	
	@XmlElement(name = "nc_case_status", required = true)
	private String ncCaseStatus;
	
	@XmlElement(name = "hit_list_session", required = true)
	private String routeRule;
	
	@XmlElement(name = "hit_seq", required = true)
	private String hitSeq;				// BOT 新增

//	@XmlElement(name = "reference_number", required = false)
	@XmlTransient
	private String referenceNumber;
		
//	@XmlElement(name = "nc_close_reason", required = false)
	@XmlTransient
	private String ncCloseReason;
	
//	@XmlElement(name = "pep_flag", required = false)
	@XmlTransient
	private String pepFlag;
	
//	@XmlElement(name = "pnmp_flag", required = false)
	@XmlTransient
	private String pnmpFlag;
	
//	@XmlElementWrapper(name="NameCheckOutputDeatils")
	@XmlElement(name = "seq")
	private List<NameCheckStatusOutputDetailBean> seq;
	
	public NameCheckStatusOutputBean(){
		
	}
	
	public NameCheckStatusOutputBean(NameCheckRecordMain main, List<NameCheckRecordDetail> detailList){
		if(main != null){
			this.interfaceName = main.getInterfaceName();
			this.ncReferenceId = String.valueOf(main.getId().getNcReferenceId());
			this.ncResult = main.getNcResult();
			this.uniqueKey = main.getId().getUniqueKey();
			this.referenceNumber = String.valueOf(main.getReferenceNumber());
			this.routeRule = main.getRouteRule();
			this.ncCloseReason = main.getNcCloseReason();
//			this.pepFlag = main.getPepFlag();
//			this.pnmpFlag = main.getPnmpFlag();
			this.branchNumber = main.getBranchNumber();
			this.businessUnitId = main.getBusinessUnitId();
			this.hitSeq = main.getHitSeq();
			
			this.seq = new ArrayList<NameCheckStatusOutputDetailBean>();
			if(detailList != null && detailList.size() > 0){
				for(NameCheckRecordDetail detail : detailList){
					seq.add(new NameCheckStatusOutputDetailBean(detail));
				}
			}
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


	public List<NameCheckStatusOutputDetailBean> getSeq() {
		return seq;
	}

	public void setSeq(List<NameCheckStatusOutputDetailBean> seq) {
		this.seq = seq;
	}

	public String getPepFlag() {
		return pepFlag;
	}

	public void setPepFlag(String pepFlag) {
		this.pepFlag = pepFlag;
	}

	public String getPnmpFlag() {
		return pnmpFlag;
	}

	public void setPnmpFlag(String pnmpFlag) {
		this.pnmpFlag = pnmpFlag;
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

	public String getHitSeq() {
		return hitSeq;
	}

	public void setHitSeq(String hitSeq) {
		this.hitSeq = hitSeq;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
