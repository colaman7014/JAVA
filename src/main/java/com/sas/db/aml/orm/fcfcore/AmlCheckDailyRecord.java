package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * AML Name Check Input Bean
 * 
 * @author SAS
 *
 */
@Entity
@Table(name="AML_CHECK_DAILY_RECORD")
@NamedQuery(name="AmlCheckDailyRecord.findAll", query="SELECT f FROM AmlCheckDailyRecord f")
public class AmlCheckDailyRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name="Unique_Key")
	private String uniqueKey="";
	
	@Column(name = "system_type")
	private String systemType="";
	
	@Column(name = "check_type")
	private String checkType="";
	
	@Column(name = "check_dept")
	private String checkDept="";

	@Column(name = "seq")
	private String seq="";
	
	@Column(name = "entity_type")
	private BigDecimal entityType;

	@Column(name = "input_id")
	private String inputId="";
	
	@Column(name = "input_ch_name")
	private String inputChName="";

	@Column(name = "input_en_name")
	private String inputEnName="";
	
	@Column(name = "input_country")
	private String inputCountry="";

	@Column(name = "aml_result")
	private String amlResult="";
	
	@Column(name = "list_type")
	private String listType="";

	@Column(name = "original_source")
	private String originalSource="";

	@Column(name = "category_desc")
	private String categoryDesc="";
	
	@Column(name = "sub_category_desc")
	private String subCategoryDesc="";
	
	@Column(name = "name")
	private String name="";
	
	@Column(name = "entity_name")
	private String entityName="";
	
	@Column(name = "first_name")
	private String firstName="";
	
	@Column(name = "last_name")
	private String lastName="";
	
	@Column(name = "middle_name")
	private String middleName="";
	
	@Column(name = "dob")
	private String dob="";
	
	@Column(name = "nationality")
	private String nationality="";
	
	@Column(name = "passport")
	private String passport="";
	
	@Column(name = "id")
	private String id="";
	
	@Column(name = "invest_result")
	private String investResult="";
	
	@Column(name = "invest_comment")
	private String investComment="";
	
	@Id
	@Column(name = "record_timestamp")
	private Timestamp recordTimestamp;
	
	@Column(name="MIX_SCORE")
	private int mixScore;
	
	@Column(name="accuity_url")
	private String watchListDetailUrl;

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getCheckDept() {
		return checkDept;
	}

	public void setCheckDept(String checkDept) {
		this.checkDept = checkDept;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public BigDecimal getEntityType() {
		return entityType;
	}

	public void setEntityType(BigDecimal entityType) {
		this.entityType = entityType;
	}

	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public String getInputChName() {
		return inputChName;
	}

	public void setInputChName(String inputChName) {
		this.inputChName = inputChName;
	}

	public String getInputEnName() {
		return inputEnName;
	}

	public void setInputEnName(String inputEnName) {
		this.inputEnName = inputEnName;
	}

	public String getInputCountry() {
		return inputCountry;
	}

	public void setInputCountry(String inputCountry) {
		this.inputCountry = inputCountry;
	}

	public String getAmlResult() {
		return amlResult;
	}

	public void setAmlResult(String amlResult) {
		this.amlResult = amlResult;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getOriginalSource() {
		return originalSource;
	}

	public void setOriginalSource(String originalSource) {
		this.originalSource = originalSource;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvestResult() {
		return investResult;
	}

	public void setInvestResult(String investResult) {
		this.investResult = investResult;
	}

	public String getInvestComment() {
		return investComment;
	}

	public void setInvestComment(String investComment) {
		this.investComment = investComment;
	}

	public Timestamp getRecordTimestamp() {
		return recordTimestamp;
	}

	public void setRecordTimestamp(Timestamp now) {
		this.recordTimestamp = now;
	}

	public int getMixScore() {
		return mixScore;
	}

	public void setMixScore(int mixScore) {
		this.mixScore = mixScore;
	}

	public String getWatchListDetailUrl() {
		return watchListDetailUrl;
	}

	public void setWatchListDetailUrl(String watchListDetailUrl) {
		this.watchListDetailUrl = watchListDetailUrl;
	}

	public String getSubCategoryDesc() {
		return subCategoryDesc;
	}

	public void setSubCategoryDesc(String subCategoryDesc) {
		this.subCategoryDesc = subCategoryDesc;
	}

}
