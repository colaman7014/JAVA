package com.sas.db.wlf.orm.nc;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the NAME_CHECK_RECORD_DETAIL database table.
 * 
 */
@Entity
@Table(name="NAME_CHECK_RECORD_DETAIL", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="NameCheckRecordDetail.findAll", query="SELECT n FROM NameCheckRecordDetail n")
public class NameCheckRecordDetail implements Serializable {

	@EmbeddedId
	private NameCheckRecordDetailPK id;

	@Column(name="BIC_SWIFT_CODE")
	private String bicSwiftCode;
	
	@Column(name="CCC_CODE")
	private String cccCode;
	
	@Column(name="CHECK_RESULT")
	private String checkResult;

	@Column(name="COUNTRY")
	private String country;

	@Column(name="ENGLISH_NAME")
	private String englishName;

	@Column(name="ENTITY_RELATIONSHIP")
	private String entityRelationship;

	@Column(name="ENTITY_RELATIONSHIP_DESC")
	private String entityRelationshipDesc;

	@Column(name="ENTITY_TYPE")
	private String entityType;

	@Column(name="FREE_FORMAT_TEXT")
	private String freeFormatText;

	@Column(name="GENDER")
	private String gender;

	@Column(name="ID_NUMBER")
	private String idNumber;

	@Column(name="NON_ENGLISH_NAME")
	private String nonEnglishName;

	@Column(name="ROUTE_RULE")
	private String routeRule;

	@Column(name="YEAR_OF_BIRTH")
	private BigDecimal yearOfBirth;

	public NameCheckRecordDetail() {
	}

	public NameCheckRecordDetailPK getId() {
		return id;
	}

	public void setId(NameCheckRecordDetailPK id) {
		this.id = id;
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
}