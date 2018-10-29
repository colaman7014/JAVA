package com.sas.webservice.nameCheck.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.sas.db.wlf.orm.nc.NameHitRecord;


/**
 * AML Name Check Output Detail Bean
 * @author SAS
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NameCheckOutputDetail {
	@XmlElement(name = "check_seq", required = true)
	private String checkSeq;

	@XmlElement(name = "check_result", required = true)
	private String checkResult;

	@XmlElement(name = "hit_list", required = true)
	private String routeRule;
	
	@XmlElement(name = "english_name", required = false)
	private String englishName;
	
	@XmlElement(name = "non_english_name", required = false)
	private String nonEnglishName;
	
	@XmlElement(name = "id_number", required = false)
	private String idNumber;
	
	@XmlElement(name = "country", required = false)
	private String country;
	
	@XmlElement(name = "year_of_birth", required = false)
	private String yearOfBirth;

	@XmlElement(name = "entity_type", required = false)
	private String entityType;
	
	@XmlElement(name = "nameHitRecordList", required = true)
	private List<NameHitRecord> nameHitRecordList;

	public String getCheckSeq() {
		return checkSeq;
	}

	public void setCheckSeq(String checkSeq) {
		this.checkSeq = checkSeq;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getRouteRule() {
		return routeRule;
	}

	public void setRouteRule(String routeRule) {
		this.routeRule = routeRule;
	}

	
	public List<NameHitRecord> getNameHitRecordList() {
		return nameHitRecordList;
	}

	public void setNameHitRecordList(List<NameHitRecord> nameHitRecordList) {
		this.nameHitRecordList = nameHitRecordList;
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

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@Override
	public String toString() {
		return "NameCheckOutputDeatil [checkSeq=" + checkSeq
				+ ", checkResult=" + checkResult + ", nameHitRecordList=" + nameHitRecordList + 
				", routeRule="
				+ routeRule + "]";
	}
}
