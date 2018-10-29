package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the FSC_ENTITY_SC_WATCH_LIST_DIM database table.
 * 
 */
@Entity
@Table(name="FSC_ENTITY_SC_WATCH_LIST_DIM")
@NamedQuery(name="FscEntityScWatchListDim.findAll", query="SELECT f FROM FscEntityScWatchListDim f")
public class FscEntityScWatchListDim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int SC_Key;

	@Column(name="category_desc")
	private String categoryDesc;

	@Column(name="change_begin_date")
	private Timestamp changeBeginDate;

	@Column(name="change_current_ind")
	private String changeCurrentInd;

	@Column(name="citizenship_country_code")
	private String citizenshipCountryCode;

	@Column(name="citizenship_country_name")
	private String citizenshipCountryName;

	@Column(name="country_code")
	private String countryCode;

	@Column(name="country_name")
	private String countryName;

	@Column(name="date_of_birth")
	private Timestamp dateOfBirth;

	@Column(name="deceased_ind")
	private String deceasedInd;

	@Column(name="delete_ind")
	private String deleteInd;

	@Column(name="entity_name")
	private String entityName;

	@Column(name="entity_name_display")
	private String entityNameDisplay;

	@Column(name="entity_watch_list_key")
	private long entityWatchListKey;

	@Column(name="entity_watch_list_number")
	private String entityWatchListNumber;

	@Column(name="exclude_ind")
	private String excludeInd;

	@Column(name="first_name")
	private String firstName;

	@Column(name="identification_id")
	private String identificationId;

	@Column(name="identification_type_desc")
	private String identificationTypeDesc;

	@Column(name="last_name")
	private String lastName;

	@Column(name="match_code_entity_name")
	private String matchCodeEntityName;

	@Column(name="match_code_individual")
	private String matchCodeIndividual;

	@Column(name="match_code_organization")
	private String matchCodeOrganization;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="nospace_name_ind")
	private String nospaceNameInd;

	@Column(name="process_dttm")
	private Timestamp processDttm;

	@Column(name="sort_name_ind")
	private String sortNameInd;

	@Column(name="sub_category_desc")
	private String subCategoryDesc;

	@Column(name="type_desc")
	private String typeDesc;

	@Column(name="watch_list_name")
	private String watchListName;

	@Column(name="year_of_birth")
	private BigDecimal yearOfBirth;

	public FscEntityScWatchListDim() {
	}

	public int getSC_Key() {
		return this.SC_Key;
	}

	public void setSC_Key(int SC_Key) {
		this.SC_Key = SC_Key;
	}

	public String getCategoryDesc() {
		return this.categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public Timestamp getChangeBeginDate() {
		return this.changeBeginDate;
	}

	public void setChangeBeginDate(Timestamp changeBeginDate) {
		this.changeBeginDate = changeBeginDate;
	}

	public String getChangeCurrentInd() {
		return this.changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	public String getCitizenshipCountryCode() {
		return this.citizenshipCountryCode;
	}

	public void setCitizenshipCountryCode(String citizenshipCountryCode) {
		this.citizenshipCountryCode = citizenshipCountryCode;
	}

	public String getCitizenshipCountryName() {
		return this.citizenshipCountryName;
	}

	public void setCitizenshipCountryName(String citizenshipCountryName) {
		this.citizenshipCountryName = citizenshipCountryName;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Timestamp getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDeceasedInd() {
		return this.deceasedInd;
	}

	public void setDeceasedInd(String deceasedInd) {
		this.deceasedInd = deceasedInd;
	}

	public String getDeleteInd() {
		return this.deleteInd;
	}

	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityNameDisplay() {
		return this.entityNameDisplay;
	}

	public void setEntityNameDisplay(String entityNameDisplay) {
		this.entityNameDisplay = entityNameDisplay;
	}

	public long getEntityWatchListKey() {
		return this.entityWatchListKey;
	}

	public void setEntityWatchListKey(long entityWatchListKey) {
		this.entityWatchListKey = entityWatchListKey;
	}

	public String getEntityWatchListNumber() {
		return this.entityWatchListNumber;
	}

	public void setEntityWatchListNumber(String entityWatchListNumber) {
		this.entityWatchListNumber = entityWatchListNumber;
	}

	public String getExcludeInd() {
		return this.excludeInd;
	}

	public void setExcludeInd(String excludeInd) {
		this.excludeInd = excludeInd;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getIdentificationId() {
		return this.identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}

	public String getIdentificationTypeDesc() {
		return this.identificationTypeDesc;
	}

	public void setIdentificationTypeDesc(String identificationTypeDesc) {
		this.identificationTypeDesc = identificationTypeDesc;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMatchCodeEntityName() {
		return this.matchCodeEntityName;
	}

	public void setMatchCodeEntityName(String matchCodeEntityName) {
		this.matchCodeEntityName = matchCodeEntityName;
	}

	public String getMatchCodeIndividual() {
		return this.matchCodeIndividual;
	}

	public void setMatchCodeIndividual(String matchCodeIndividual) {
		this.matchCodeIndividual = matchCodeIndividual;
	}

	public String getMatchCodeOrganization() {
		return this.matchCodeOrganization;
	}

	public void setMatchCodeOrganization(String matchCodeOrganization) {
		this.matchCodeOrganization = matchCodeOrganization;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getNospaceNameInd() {
		return this.nospaceNameInd;
	}

	public void setNospaceNameInd(String nospaceNameInd) {
		this.nospaceNameInd = nospaceNameInd;
	}

	public Timestamp getProcessDttm() {
		return this.processDttm;
	}

	public void setProcessDttm(Timestamp processDttm) {
		this.processDttm = processDttm;
	}

	public String getSortNameInd() {
		return this.sortNameInd;
	}

	public void setSortNameInd(String sortNameInd) {
		this.sortNameInd = sortNameInd;
	}

	public String getSubCategoryDesc() {
		return this.subCategoryDesc;
	}

	public void setSubCategoryDesc(String subCategoryDesc) {
		this.subCategoryDesc = subCategoryDesc;
	}

	public String getTypeDesc() {
		return this.typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getWatchListName() {
		return this.watchListName;
	}

	public void setWatchListName(String watchListName) {
		this.watchListName = watchListName;
	}

	public BigDecimal getYearOfBirth() {
		return this.yearOfBirth;
	}

	public void setYearOfBirth(BigDecimal yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

}