package com.sas.aml.uploader.bean;

import java.math.BigDecimal;

import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;

public class NameCheckRecordDetailBean {

	private String uniqueKey;

	private int ncReferenceId;
	
	private String checkSeq;
	
	private String bicSwiftCode;

	private String cccCode;
	
	private String checkResult;

	private String country;

	private String englishName;

	private String entityRelationship;

	private String entityRelationshipDesc;

	private String entityType;

	private String freeFormatText;

	private String gender;

	private String idNumber;

	private String nonEnglishName;

	private String routeRule;

	private BigDecimal yearOfBirth;
	
	private String transCheckResult;
	
	private String transEntityRelationship;
	
	private String transRouteRule;
	
	private String transEntityType;
	
	
	public NameCheckRecordDetailBean() {}
	
	
	public NameCheckRecordDetailBean(NameCheckRecordDetail nameCheckRecordDetail) {
		this.uniqueKey = nameCheckRecordDetail.getId().getUniqueKey();
		this.ncReferenceId = nameCheckRecordDetail.getId().getNcReferenceId();
		this.checkSeq = nameCheckRecordDetail.getId().getCheckSeq();
		this.bicSwiftCode = nameCheckRecordDetail.getBicSwiftCode();
		this.cccCode = nameCheckRecordDetail.getCccCode();
		this.checkResult = nameCheckRecordDetail.getCheckResult();
		this.country = nameCheckRecordDetail.getCountry();
		this.englishName = nameCheckRecordDetail.getEnglishName();
		this.entityRelationship = nameCheckRecordDetail.getEntityRelationship();
		this.entityRelationshipDesc = nameCheckRecordDetail.getEntityRelationshipDesc();
		this.entityType = nameCheckRecordDetail.getEntityType();
		this.freeFormatText = nameCheckRecordDetail.getFreeFormatText();
		this.gender = nameCheckRecordDetail.getGender();
		this.idNumber = nameCheckRecordDetail.getIdNumber();
		this.nonEnglishName = nameCheckRecordDetail.getNonEnglishName();
		this.routeRule = nameCheckRecordDetail.getRouteRule();
		this.yearOfBirth = nameCheckRecordDetail.getYearOfBirth();
	}



	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public int getNcReferenceId() {
		return ncReferenceId;
	}

	public void setNcReferenceId(int ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
	}

	public String getCheckSeq() {
		return checkSeq;
	}

	public void setCheckSeq(String checkSeq) {
		this.checkSeq = checkSeq;
	}

	public String getBicSwiftCode() {
		return bicSwiftCode;
	}

	public void setBicSwiftCode(String bicSwiftCode) {
		this.bicSwiftCode = bicSwiftCode;
	}

	public String getCccCode() {
		return cccCode;
	}

	public void setCccCode(String cccCode) {
		this.cccCode = cccCode;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
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

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getFreeFormatText() {
		return freeFormatText;
	}

	public void setFreeFormatText(String freeFormatText) {
		this.freeFormatText = freeFormatText;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getNonEnglishName() {
		return nonEnglishName;
	}

	public void setNonEnglishName(String nonEnglishName) {
		this.nonEnglishName = nonEnglishName;
	}

	public String getRouteRule() {
		return routeRule;
	}

	public void setRouteRule(String routeRule) {
		this.routeRule = routeRule;
	}

	public BigDecimal getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(BigDecimal yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}


	public String getTransCheckResult() {
		return transCheckResult;
	}


	public void setTransCheckResult(String transCheckResult) {
		this.transCheckResult = transCheckResult;
	}


	public String getTransEntityRelationship() {
		return transEntityRelationship;
	}


	public void setTransEntityRelationship(String transEntityRelationship) {
		this.transEntityRelationship = transEntityRelationship;
	}


	public String getTransRouteRule() {
		return transRouteRule;
	}


	public void setTransRouteRule(String transRouteRule) {
		this.transRouteRule = transRouteRule;
	}


	public String getTransEntityType() {
		return transEntityType;
	}


	public void setTransEntityType(String transEntityType) {
		this.transEntityType = transEntityType;
	}
	
}
