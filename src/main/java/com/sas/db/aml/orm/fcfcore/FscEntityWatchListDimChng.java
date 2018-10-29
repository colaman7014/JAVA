package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the FSC_ENTITY_WATCH_LIST_DIM_CHNG database table.
 * 
 */
@Entity
@Table(name="FSC_ENTITY_WATCH_LIST_DIM_CHNG")
@NamedQuery(name="FscEntityWatchListDimChng.findAll", query="SELECT f FROM FscEntityWatchListDimChng f")
public class FscEntityWatchListDimChng implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="entity_watch_list_key")
	private long entityWatchListKey;

	private String address;

	@Column(name="category_desc")
	private String categoryDesc;

	@Column(name="change_begin_date")
	private Timestamp changeBeginDate;

	@Column(name="change_current_ind")
	private String changeCurrentInd;

	@Column(name="change_end_date")
	private Timestamp changeEndDate;

	@Column(name="citizenship_country_code")
	private String citizenshipCountryCode;

	@Column(name="citizenship_country_name")
	private String citizenshipCountryName;

	@Column(name="city_name")
	private String cityName;

	@Column(name="country_code")
	private String countryCode;

	@Column(name="country_name")
	private String countryName;

	@Column(name="create_date")
	private Timestamp createDate;

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

	@Column(name="ENTITY_NAME_LOCATION")
	private String entityNameLocation;

	@Column(name="ENTITY_NAME_NOSPACE")
	private String entityNameNospace;

	@Column(name="ENTITY_NAME_NSIG")
	private String entityNameNsig;

	@Column(name="ENTITY_NAME_SORTED")
	private String entityNameSorted;

	@Column(name="entity_number_md5")
	private String entityNumberMd5;

	@Column(name="entity_title")
	private String entityTitle;

	@Column(name="entity_watch_list_number")
	private String entityWatchListNumber;

	@Column(name="exclude_ind")
	private String excludeInd;

	@Column(name="first_name")
	private String firstName;

	@Column(name="full_address")
	private String fullAddress;

	@Column(name="GENDER")
	private String gender;

	@Column(name="identification_id")
	private String identificationId;

	@Column(name="identification_type_desc")
	private String identificationTypeDesc;

	@Column(name="last_name")
	private String lastName;

	@Column(name="match_code_addr_lines")
	private String matchCodeAddrLines;

	@Column(name="match_code_citizenship")
	private String matchCodeCitizenship;

	@Column(name="match_code_city")
	private String matchCodeCity;

	@Column(name="match_code_country")
	private String matchCodeCountry;

	@Column(name="match_code_entity_name")
	private String matchCodeEntityName;

	@Column(name="MATCH_CODE_ENTITY_NAME_SORTED")
	private String matchCodeEntityNameSorted;

	@Column(name="match_code_full_address")
	private String matchCodeFullAddress;

	@Column(name="match_code_individual")
	private String matchCodeIndividual;

	@Column(name="match_code_nationality")
	private String matchCodeNationality;

	@Column(name="match_code_org_country")
	private String matchCodeOrgCountry;

	@Column(name="match_code_organization")
	private String matchCodeOrganization;

	@Column(name="match_code_state")
	private String matchCodeState;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="nationality_country_code")
	private String nationalityCountryCode;

	@Column(name="nationality_country_name")
	private String nationalityCountryName;

	@Column(name="occupation_desc")
	private String occupationDesc;

	@Column(name="org_country_of_business_code")
	private String orgCountryOfBusinessCode;

	@Column(name="org_country_of_business_name")
	private String orgCountryOfBusinessName;

	@Column(name="place_of_birth")
	private String placeOfBirth;

	@Column(name="politically_exposed_person_ind")
	private String politicallyExposedPersonInd;

	@Column(name="postal_code")
	private String postalCode;

	@Column(name="process_dttm")
	private Timestamp processDttm;

	private String programs;

	@Column(name="REMARK")
	private String remark;

	@Column(name="state_name")
	private String stateName;

	@Column(name="sub_category_desc")
	private String subCategoryDesc;

	@Column(name="tax_id")
	private String taxId;

	@Column(name="tax_id_type_code")
	private String taxIdTypeCode;

	@Column(name="type_desc")
	private String typeDesc;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="watch_list_name")
	private String watchListName;

	@Column(name="year_of_birth")
	private BigDecimal yearOfBirth;

	public FscEntityWatchListDimChng() {
	}

	public long getEntityWatchListKey() {
		return this.entityWatchListKey;
	}

	public void setEntityWatchListKey(long entityWatchListKey) {
		this.entityWatchListKey = entityWatchListKey;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Timestamp getChangeEndDate() {
		return this.changeEndDate;
	}

	public void setChangeEndDate(Timestamp changeEndDate) {
		this.changeEndDate = changeEndDate;
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

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
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

	public String getEntityNameLocation() {
		return this.entityNameLocation;
	}

	public void setEntityNameLocation(String entityNameLocation) {
		this.entityNameLocation = entityNameLocation;
	}

	public String getEntityNameNospace() {
		return this.entityNameNospace;
	}

	public void setEntityNameNospace(String entityNameNospace) {
		this.entityNameNospace = entityNameNospace;
	}

	public String getEntityNameNsig() {
		return this.entityNameNsig;
	}

	public void setEntityNameNsig(String entityNameNsig) {
		this.entityNameNsig = entityNameNsig;
	}

	public String getEntityNameSorted() {
		return this.entityNameSorted;
	}

	public void setEntityNameSorted(String entityNameSorted) {
		this.entityNameSorted = entityNameSorted;
	}

	public String getEntityNumberMd5() {
		return this.entityNumberMd5;
	}

	public void setEntityNumberMd5(String entityNumberMd5) {
		this.entityNumberMd5 = entityNumberMd5;
	}

	public String getEntityTitle() {
		return this.entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
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

	public String getFullAddress() {
		return this.fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getMatchCodeAddrLines() {
		return this.matchCodeAddrLines;
	}

	public void setMatchCodeAddrLines(String matchCodeAddrLines) {
		this.matchCodeAddrLines = matchCodeAddrLines;
	}

	public String getMatchCodeCitizenship() {
		return this.matchCodeCitizenship;
	}

	public void setMatchCodeCitizenship(String matchCodeCitizenship) {
		this.matchCodeCitizenship = matchCodeCitizenship;
	}

	public String getMatchCodeCity() {
		return this.matchCodeCity;
	}

	public void setMatchCodeCity(String matchCodeCity) {
		this.matchCodeCity = matchCodeCity;
	}

	public String getMatchCodeCountry() {
		return this.matchCodeCountry;
	}

	public void setMatchCodeCountry(String matchCodeCountry) {
		this.matchCodeCountry = matchCodeCountry;
	}

	public String getMatchCodeEntityName() {
		return this.matchCodeEntityName;
	}

	public void setMatchCodeEntityName(String matchCodeEntityName) {
		this.matchCodeEntityName = matchCodeEntityName;
	}

	public String getMatchCodeEntityNameSorted() {
		return this.matchCodeEntityNameSorted;
	}

	public void setMatchCodeEntityNameSorted(String matchCodeEntityNameSorted) {
		this.matchCodeEntityNameSorted = matchCodeEntityNameSorted;
	}

	public String getMatchCodeFullAddress() {
		return this.matchCodeFullAddress;
	}

	public void setMatchCodeFullAddress(String matchCodeFullAddress) {
		this.matchCodeFullAddress = matchCodeFullAddress;
	}

	public String getMatchCodeIndividual() {
		return this.matchCodeIndividual;
	}

	public void setMatchCodeIndividual(String matchCodeIndividual) {
		this.matchCodeIndividual = matchCodeIndividual;
	}

	public String getMatchCodeNationality() {
		return this.matchCodeNationality;
	}

	public void setMatchCodeNationality(String matchCodeNationality) {
		this.matchCodeNationality = matchCodeNationality;
	}

	public String getMatchCodeOrgCountry() {
		return this.matchCodeOrgCountry;
	}

	public void setMatchCodeOrgCountry(String matchCodeOrgCountry) {
		this.matchCodeOrgCountry = matchCodeOrgCountry;
	}

	public String getMatchCodeOrganization() {
		return this.matchCodeOrganization;
	}

	public void setMatchCodeOrganization(String matchCodeOrganization) {
		this.matchCodeOrganization = matchCodeOrganization;
	}

	public String getMatchCodeState() {
		return this.matchCodeState;
	}

	public void setMatchCodeState(String matchCodeState) {
		this.matchCodeState = matchCodeState;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getNationalityCountryCode() {
		return this.nationalityCountryCode;
	}

	public void setNationalityCountryCode(String nationalityCountryCode) {
		this.nationalityCountryCode = nationalityCountryCode;
	}

	public String getNationalityCountryName() {
		return this.nationalityCountryName;
	}

	public void setNationalityCountryName(String nationalityCountryName) {
		this.nationalityCountryName = nationalityCountryName;
	}

	public String getOccupationDesc() {
		return this.occupationDesc;
	}

	public void setOccupationDesc(String occupationDesc) {
		this.occupationDesc = occupationDesc;
	}

	public String getOrgCountryOfBusinessCode() {
		return this.orgCountryOfBusinessCode;
	}

	public void setOrgCountryOfBusinessCode(String orgCountryOfBusinessCode) {
		this.orgCountryOfBusinessCode = orgCountryOfBusinessCode;
	}

	public String getOrgCountryOfBusinessName() {
		return this.orgCountryOfBusinessName;
	}

	public void setOrgCountryOfBusinessName(String orgCountryOfBusinessName) {
		this.orgCountryOfBusinessName = orgCountryOfBusinessName;
	}

	public String getPlaceOfBirth() {
		return this.placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getPoliticallyExposedPersonInd() {
		return this.politicallyExposedPersonInd;
	}

	public void setPoliticallyExposedPersonInd(String politicallyExposedPersonInd) {
		this.politicallyExposedPersonInd = politicallyExposedPersonInd;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Timestamp getProcessDttm() {
		return this.processDttm;
	}

	public void setProcessDttm(Timestamp processDttm) {
		this.processDttm = processDttm;
	}

	public String getPrograms() {
		return this.programs;
	}

	public void setPrograms(String programs) {
		this.programs = programs;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getSubCategoryDesc() {
		return this.subCategoryDesc;
	}

	public void setSubCategoryDesc(String subCategoryDesc) {
		this.subCategoryDesc = subCategoryDesc;
	}

	public String getTaxId() {
		return this.taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getTaxIdTypeCode() {
		return this.taxIdTypeCode;
	}

	public void setTaxIdTypeCode(String taxIdTypeCode) {
		this.taxIdTypeCode = taxIdTypeCode;
	}

	public String getTypeDesc() {
		return this.typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
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