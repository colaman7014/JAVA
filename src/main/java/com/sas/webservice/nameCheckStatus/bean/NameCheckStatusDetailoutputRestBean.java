package com.sas.webservice.nameCheckStatus.bean;

import java.math.BigDecimal;
import java.util.List;

import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;

/**
 * AML Name Check Status Output Rest Bean
 * @author SAS
 *
 */
public class NameCheckStatusDetailoutputRestBean {
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
	private List<NameCheckStatusHitoutputRestBean> hitRecords;
	
	public NameCheckStatusDetailoutputRestBean(){
			
	}

	public NameCheckStatusDetailoutputRestBean(NameCheckRecordDetail detail, List<NameCheckStatusHitoutputRestBean> hitRecords){
		this.uniqueKey = detail.getId().getUniqueKey();
		this.ncReferenceId = detail.getId().getNcReferenceId();
		this.checkSeq = detail.getId().getCheckSeq();
		this.bicSwiftCode = detail.getBicSwiftCode();
		this.cccCode = detail.getCccCode();
		this.checkResult = detail.getCheckResult();
		this.country = detail.getCountry();
		this.englishName = detail.getEnglishName();
		this.entityRelationship = detail.getEntityRelationship();
		this.entityRelationshipDesc = detail.getEntityRelationshipDesc();
		this.entityType = detail.getEntityType();
		this.freeFormatText = detail.getFreeFormatText();
		this.gender = detail.getGender();
		this.idNumber = detail.getIdNumber();
		this.nonEnglishName = detail.getNonEnglishName();
		this.routeRule = detail.getRouteRule();
		this.yearOfBirth = detail.getYearOfBirth();
		this.hitRecords = hitRecords;
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
	public List<NameCheckStatusHitoutputRestBean> getHitRecords() {
		return hitRecords;
	}
	public void setHitRecords(List<NameCheckStatusHitoutputRestBean> hitRecords) {
		this.hitRecords = hitRecords;
	}
	@Override
	public String toString() {
		return "NameCheckStatusDetailoutputRestBean [uniqueKey=" + uniqueKey
				+ ", ncReferenceId=" + ncReferenceId + ", checkSeq=" + checkSeq
				+ ", bicSwiftCode=" + bicSwiftCode + ", cccCode=" + cccCode
				+ ", checkResult=" + checkResult + ", country=" + country
				+ ", englishName=" + englishName + ", entityRelationship="
				+ entityRelationship + ", entityRelationshipDesc="
				+ entityRelationshipDesc + ", entityType=" + entityType
				+ ", freeFormatText=" + freeFormatText + ", gender=" + gender
				+ ", idNumber=" + idNumber + ", nonEnglishName="
				+ nonEnglishName + ", routeRule=" + routeRule
				+ ", yearOfBirth=" + yearOfBirth + ", hitRecords=" + hitRecords
				+ "]";
	}
}
