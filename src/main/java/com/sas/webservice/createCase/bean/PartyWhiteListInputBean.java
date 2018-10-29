package com.sas.webservice.createCase.bean;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sas.webservice.adapter.Y4MMDDAdapter;

/**
 * 客戶白名單
 * PartyWhiteList Input Bean
 * @author SAS
 *
 */
public class PartyWhiteListInputBean {
	
	private String partyNumber;
	private String partyName;
	private String remark;
	private String caseId;
	private String caseAction;
	private String caseDisposition;
	private String createDate;
	private String createUser;
	private String isWhitelist;
	public PartyWhiteListInputBean() {
		
	}
	 
	
	public PartyWhiteListInputBean(String partyNumber, String partyName,
			String remark, String caseId, String caseAction,
			String caseDisposition, String createDate, String createUser,
			String isWhitelist) {
		super();
		this.partyNumber = partyNumber;
		this.partyName = partyName;
		this.remark = remark;
		this.caseId = caseId;
		this.caseAction = caseAction;
		this.caseDisposition = caseDisposition;
		this.createDate = createDate;
		this.createUser = createUser;
		this.isWhitelist = isWhitelist;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
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
	@Override
	public String toString() {
		return "PartyWhiteListInputBean [partyNumber=" + partyNumber
				+ ", partyName=" + partyName + ", remark=" + remark
				+ ", caseId=" + caseId + ", caseAction=" + caseAction
				+ ", caseDisposition=" + caseDisposition + ", createDate="
				+ createDate + ", createUser=" + createUser + ", isWhitelist="
				+ isWhitelist + "]";
	}
}