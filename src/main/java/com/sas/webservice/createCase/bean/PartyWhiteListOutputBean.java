package com.sas.webservice.createCase.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sas.webservice.adapter.Y4MMDDAdapter;

/**
 * 客戶白名單
 * AML PartyWhiteList Output Bean
 * @author SAS
 *
 */
public class PartyWhiteListOutputBean {
	 
	private String partyNumber;                  //客戶編號
	private String partyName;                    //客戶名稱
	private String remark;                       //備註
	private String caseId;                       //案件編號 : 西元年-caseRK 
	private String caseAction;                   //NEW(新增)/STOP(停用)
	private Long createDate;                   //新增時間
	private String createUser;                   //新增人員
	
	private String caseDisposition;              //只有結案 WorkFlowCloseCase()   才需要更新  from caseLive.CaseDispositionCd 為   APPROVAL / CANCEL 
	private String isWhitelist;                  //只有結案 WorkFlowCloseCase()   才需要依[Disposition規則]更新 isWhitelist
	
	private Boolean isExistPartyWhitelistMain;   //是否已有資料XPartyWhitelistMain
	private Boolean scriptCheckPartyNumberExist; //script檢查客戶編號是否存在PARTY
	private Boolean scriptIsCanAddPartyWhite;    //script檢查是否可以新增
	private String historyCaseId;                //新增的一筆X_PARTY_WHITELIST_HIS.CASE_ID
	
	private String serviceName;                  //服務功能 
	private String logMsg;                       //log處理訊息	
	private String errorMsg;                     //錯誤訊息
	private Boolean isSuccess;                   //是否成功
	
	public PartyWhiteListOutputBean() {
		
	}
	 
	 
	public String getPartyNumber() {
		return partyNumber;
	}
	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCaseAction() {
		return caseAction;
	}
	public void setCaseAction(String caseAction) {
		this.caseAction = caseAction;
	}
	public String getCaseDisposition() {
		return caseDisposition;
	}
	public void setCaseDisposition(String caseDisposition) {
		this.caseDisposition = caseDisposition;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getIsWhitelist() {
		return isWhitelist;
	}
	public void setIsWhitelist(String isWhitelist) {
		this.isWhitelist = isWhitelist;
	}
	public Boolean getIsExistPartyWhitelistMain() {
		return isExistPartyWhitelistMain;
	}
	public void setIsExistPartyWhitelistMain(Boolean isExistPartyWhitelistMain) {
		this.isExistPartyWhitelistMain = isExistPartyWhitelistMain;
	}
	public Boolean getScriptCheckPartyNumberExist() {
		return scriptCheckPartyNumberExist;
	}
	public void setScriptCheckPartyNumberExist(Boolean scriptCheckPartyNumberExist) {
		this.scriptCheckPartyNumberExist = scriptCheckPartyNumberExist;
	}
	public Boolean getScriptIsCanAddPartyWhite() {
		return scriptIsCanAddPartyWhite;
	}
	public void setScriptIsCanAddPartyWhite(Boolean scriptIsCanAddPartyWhite) {
		this.scriptIsCanAddPartyWhite = scriptIsCanAddPartyWhite;
	}
	public String getHistoryCaseId() {
		return historyCaseId;
	}
	public void setHistoryCaseId(String historyCaseId) {
		this.historyCaseId = historyCaseId;
	}
	public String getLogMsg() {
		return logMsg;
	}
	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	@Override
	public String toString() {
		return "PartyWhiteListOutputBean [partyNumber=" + partyNumber
				+ ", partyName=" + partyName + ", remark=" + remark
				+ ", caseId=" + caseId + ", caseAction=" + caseAction
				+ ", createDate=" + createDate + ", createUser=" + createUser
				+ ", caseDisposition=" + caseDisposition + ", isWhitelist="
				+ isWhitelist + ", isExistPartyWhitelistMain="
				+ isExistPartyWhitelistMain + ", scriptCheckPartyNumberExist="
				+ scriptCheckPartyNumberExist + ", scriptIsCanAddPartyWhite="
				+ scriptIsCanAddPartyWhite + ", historyCaseId=" + historyCaseId
				+ ", serviceName=" + serviceName + ", logMsg=" + logMsg
				+ ", errorMsg=" + errorMsg + ", isSuccess=" + isSuccess + "]";
	}
	
	 
	  
	
}
