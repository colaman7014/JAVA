package com.sas.webservice.nameCheck.bean;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FtpNameCheckInputBean {
	private String callingSystem;
	private String realtimeFlag;
	private String screenFunction;
	private String callingUser;
	private String branchNumber;
	private String unit;
	private String uniqueKey;
	private String partyNumber;

	private String checkSeq;
	private String entityType;
	private String entityRelationship;
	private String entityRelationshipDesc;
	private String englishName;
	private String nonEnglishName;
	private String cccCode;
	private String idNumber;
	private String bicSwiftCode;
	private String country;
	private String yearofBirth;

	public FtpNameCheckInputBean() {
	}

	public FtpNameCheckInputBean(String[] strList) {
		setValues(strList);
	}

	public FtpNameCheckInputBean(List<String> strList) {
		setValues(strList);
	}

	private void setValues(String[] strList) {
		setValues(Arrays.asList(strList));
	}

	private void setValues(List<String> strList) {
		int i = 0;
		this.callingSystem = strList.get(i++);
		this.realtimeFlag = strList.get(i++);
		this.screenFunction = strList.get(i++);
		this.callingUser = strList.get(i++);
		this.branchNumber = strList.get(i++);
		this.unit = strList.get(i++);
		this.uniqueKey = strList.get(i++);
		this.partyNumber = strList.get(i++);

		this.checkSeq = strList.get(i++);
		this.entityType = strList.get(i++);
		this.entityRelationship = strList.get(i++);
		this.entityRelationshipDesc = strList.get(i++);
		this.englishName = strList.get(i++);
		this.nonEnglishName = strList.get(i++);
		this.cccCode = strList.get(i++);
		this.idNumber = strList.get(i++);
		this.bicSwiftCode = strList.get(i++);
		this.country = strList.get(i++);
		this.yearofBirth = strList.get(i++);
	}

	public String getCallingSystem() {
		return callingSystem;
	}

	public void setCallingSystem(String callingSystem) {
		this.callingSystem = callingSystem;
	}

	public String getScreenFunction() {
		return screenFunction;
	}

	public void setScreenFunction(String screenFunction) {
		this.screenFunction = screenFunction;
	}

	public String getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getCallingUser() {
		return callingUser;
	}

	public void setCallingUser(String callingUser) {
		this.callingUser = callingUser;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getCheckSeq() {
		return checkSeq;
	}

	public void setCheckSeq(String checkSeq) {
		this.checkSeq = checkSeq;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityRelationship() {
		return entityRelationship;
	}

	public void setEntityRelationship(String entityRelationship) {
		this.entityRelationship = entityRelationship;
	}

	public String getEntityRelationshipDesc() {
		return entityRelationshipDesc;
	}

	public void setEntityRelationshipDesc(String entityRelationshipDesc) {
		this.entityRelationshipDesc = entityRelationshipDesc;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getNonEnglishName() {
		return nonEnglishName;
	}

	public void setNonEnglishName(String nonEnglishName) {
		this.nonEnglishName = nonEnglishName;
	}

	public String getCccCode() {
		return cccCode;
	}

	public void setCccCode(String cccCode) {
		this.cccCode = cccCode;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getBicSwiftCode() {
		return bicSwiftCode;
	}

	public void setBicSwiftCode(String bicSwiftCode) {
		this.bicSwiftCode = bicSwiftCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getYearofBirth() {
		return yearofBirth;
	}

	public void setYearofBirth(String yearofBirth) {
		this.yearofBirth = yearofBirth;
	}

	public String getRealtimeFlag() {
		return realtimeFlag;
	}

	public void setRealtimeFlag(String realtimeFlag) {
		this.realtimeFlag = realtimeFlag;
	}

	public String getPartyNumber() {
		return partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
