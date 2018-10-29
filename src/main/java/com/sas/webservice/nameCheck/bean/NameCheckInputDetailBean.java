package com.sas.webservice.nameCheck.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sas.constraint.SwiftMtConst;

/**
 * AML Name Check Input Detail Bean
 * @author SAS
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NameCheckInputDetailBean {
	@XmlElement(name = "check_seq", required = true)
	private String checkSeq;

	@XmlElement(name = "entity_type", required = true)
	private String entityType;

	@XmlElement(name = "entity_relationship", required = true)
	private String entityRelationship;

	@XmlElement(name = "entity_relationship_desc", required = false)
	private String entityRelationshipDesc;

	@XmlElement(name = "english_name", required = false)
	private String englishName;

	@XmlElement(name = "non_english_name", required = false)
	private String nonEnglishName;
	
	@XmlElement(name = "ccc_code", required = false)
	private String cccCode;
	
	@XmlElement(name = "id_number", required = false)
	private String idNumber;

	@XmlElement(name = "bic_swift_code", required = false)
	private String bicSwiftCode;

//	@XmlElement(name = "free_format_text", required = false)
	@XmlTransient
	private String freeFormatText;	// BOT 沒有
	
	@XmlElement(name = "country", required = false)
	private String country;

	@XmlElement(name = "year_of_birth", required = false)
	private String yearOfBirth;

//	@XmlElement(name = "gender", required = false)
	@XmlTransient
	private String gender;	// BOT 沒有
	
	public NameCheckInputDetailBean(){
		
	}
	
	public NameCheckInputDetailBean(FtpNameCheckInputBean ftpBean) {
		this.checkSeq = ftpBean.getCheckSeq();
		this.entityType = ftpBean.getEntityType();
		this.entityRelationship = ftpBean.getEntityRelationship();
		this.entityRelationshipDesc = ftpBean.getEntityRelationshipDesc();
		this.englishName = ftpBean.getEnglishName();
		this.nonEnglishName = ftpBean.getNonEnglishName();
		this.cccCode = ftpBean.getCccCode();
		this.idNumber = ftpBean.getIdNumber();
		this.bicSwiftCode = ftpBean.getBicSwiftCode();
		this.country = ftpBean.getCountry();
		this.yearOfBirth = ftpBean.getYearofBirth();
	}

	//貿易融資專用建構子
	public NameCheckInputDetailBean(String checkSeq, String entityType, String entityRelationship, String entityRelationshipDesc, String englishName){
		this.checkSeq = checkSeq;
		this.entityType = entityType;
		this.entityRelationship = entityRelationship;
		this.entityRelationshipDesc = entityRelationshipDesc;
		this.englishName = englishName;
		if(SwiftMtConst.ENTITY_TYPE_COUNTRY.equals(entityType)){  //假如entity_type=08時，將englishName寫入country
			this.country = englishName;
		}
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getFreeFormatText() {
		return freeFormatText;
	}

	public void setFreeFormatText(String freeFormatText) {
		this.freeFormatText = freeFormatText;
	}

	public String getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCccCode() {
		return cccCode;
	}

	public void setCccCode(String cccCode) {
		this.cccCode = cccCode;
	}

	@Override
	public String toString() {
		return "NameCheckInputDetail [checkSeq=" + checkSeq + ", entityType="
				+ entityType + ", entityRelationship=" + entityRelationship
				+ ", entityRelationshipDesc=" + entityRelationshipDesc
				+ ", englishName=" + englishName + ", nonEnglishName="
				+ nonEnglishName + ", cccCode=" + cccCode + ", idNumber="
				+ idNumber + ", bicSwiftCode=" + bicSwiftCode
				+ ", freeFormatText=" + freeFormatText + ", country=" + country
				+ ", yearOfBirth=" + yearOfBirth + ", gender=" + gender + "]";
	}
}
