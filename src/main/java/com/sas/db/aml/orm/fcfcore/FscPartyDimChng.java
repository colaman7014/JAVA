package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FSC_PARTY_DIM database table.
 * 
 */
@Entity
@Table(name="FSC_PARTY_DIM_CHNG")
@NamedQuery(name="FscPartyDimChng.findAll", query="SELECT f FROM FscPartyDimChng f")
public class FscPartyDimChng implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FscPartyDimChngPK id;

	@Column(name="annual_income_amount")
	private BigDecimal annualIncomeAmount;

	@Column(name="change_begin_date")
	private Timestamp changeBeginDate;

	@Column(name="change_current_ind")
	private String changeCurrentInd;

	@Column(name="change_end_date")
	private Timestamp changeEndDate;

	@Column(name="check_casher_ind")
	private String checkCasherInd;

	@Column(name="citizenship_country_code")
	private String citizenshipCountryCode;

	@Column(name="citizenship_country_name")
	private String citizenshipCountryName;

	@Column(name="currency_exchange_ind")
	private String currencyExchangeInd;

	@Column(name="customer_since_date")
	private Timestamp customerSinceDate;

	@Column(name="doing_business_as_name")
	private String doingBusinessAsName;

	@Column(name="email_address")
	private String emailAddress;

	@Column(name="employee_ind")
	private String employeeInd;

	@Column(name="employee_number")
	private String employeeNumber;

	@Column(name="employer_name")
	private String employerName;

	@Column(name="employer_phone_number")
	private String employerPhoneNumber;

	@Column(name="external_party_ind")
	private String externalPartyInd;

	@Column(name="foreign_consulate_embassy_ind")
	private String foreignConsulateEmbassyInd;

	@Column(name="industry_code")
	private String industryCode;

	@Column(name="industry_code_rr")
	private String industryCodeRr;

	@Column(name="industry_desc")
	private String industryDesc;

	@Column(name="internet_gambling_ind")
	private String internetGamblingInd;

	@Column(name="issues_bearer_shares_ind")
	private String issuesBearerSharesInd;

	@Column(name="last_cash_trans_rpt_date")
	private Timestamp lastCashTransRptDate;

	@Column(name="last_contact_date")
	private Timestamp lastContactDate;

	@Column(name="last_risk_assessment_date")
	private Timestamp lastRiskAssessmentDate;

	@Column(name="last_susp_actv_rpt_date")
	private Timestamp lastSuspActvRptDate;

	@Column(name="legal_entity_type")
	private String legalEntityType;

	@Column(name="lst_update_date")
	private Timestamp lstUpdateDate;

	@Column(name="mailing_address_1")
	private String mailingAddress1;

	@Column(name="mailing_address_2")
	private String mailingAddress2;

	@Column(name="mailing_city_name")
	private String mailingCityName;

	@Column(name="mailing_country_code")
	private String mailingCountryCode;

	@Column(name="mailing_country_name")
	private String mailingCountryName;

	@Column(name="mailing_postal_code")
	private String mailingPostalCode;

	@Column(name="mailing_state_code")
	private String mailingStateCode;

	@Column(name="mailing_state_name")
	private String mailingStateName;

	@Column(name="marital_status_desc")
	private String maritalStatusDesc;

	@Column(name="match_code_individual")
	private String matchCodeIndividual;

	@Column(name="match_code_mailing_addr_lines")
	private String matchCodeMailingAddrLines;

	@Column(name="match_code_mailing_address")
	private String matchCodeMailingAddress;

	@Column(name="match_code_mailing_city")
	private String matchCodeMailingCity;

	@Column(name="match_code_mailing_country")
	private String matchCodeMailingCountry;

	@Column(name="match_code_mailing_state")
	private String matchCodeMailingState;

	@Column(name="match_code_organization")
	private String matchCodeOrganization;

	@Column(name="match_code_street_addr_lines")
	private String matchCodeStreetAddrLines;

	@Column(name="match_code_street_address")
	private String matchCodeStreetAddress;

	@Column(name="match_code_street_city")
	private String matchCodeStreetCity;

	@Column(name="match_code_street_country")
	private String matchCodeStreetCountry;

	@Column(name="match_code_street_state")
	private String matchCodeStreetState;

	@Column(name="money_orders_ind")
	private String moneyOrdersInd;

	@Column(name="money_transmitter_ind")
	private String moneyTransmitterInd;

	@Column(name="msb_ind")
	private String msbInd;

	@Column(name="negative_news_ind")
	private String negativeNewsInd;

	@Column(name="net_worth_amount")
	private BigDecimal netWorthAmount;

	@Column(name="non_profit_org_ind")
	private String nonProfitOrgInd;

	@Column(name="occupation_desc")
	private String occupationDesc;

	@Column(name="org_country_of_business_code")
	private String orgCountryOfBusinessCode;

	@Column(name="org_country_of_business_name")
	private String orgCountryOfBusinessName;

	@Column(name="owner_user_long_id")
	private String ownerUserLongId;

	@Column(name="party_date_of_birth")
	private Timestamp partyDateOfBirth;

	@Column(name="party_first_name")
	private String partyFirstName;

	@Column(name="party_id_country_code")
	private String partyIdCountryCode;

	@Column(name="party_id_state_code")
	private String partyIdStateCode;

	@Column(name="party_identification_id")
	private String partyIdentificationId;

	@Column(name="party_identification_type_desc")
	private String partyIdentificationTypeDesc;

	@Column(name="party_last_name")
	private String partyLastName;

	@Column(name="party_middle_name")
	private String partyMiddleName;

	@Column(name="party_name")
	private String partyName;

	@Column(name="party_number")
	private String partyNumber;

	@Column(name="party_status_desc")
	private String partyStatusDesc;

	@Column(name="party_tax_id")
	private String partyTaxId;

	@Column(name="party_tax_id_type_code")
	private String partyTaxIdTypeCode;
	
	@Column(name="party_type_code")
	private String partyTypeCode;

	@Column(name="party_type_desc")
	private String partyTypeDesc;

	@Column(name="phone_number_1")
	private String phoneNumber1;

	@Column(name="phone_number_2")
	private String phoneNumber2;

	@Column(name="phone_number_3")
	private String phoneNumber3;

	@Column(name="politically_exposed_person_ind")
	private String politicallyExposedPersonInd;

	@Column(name="prepaid_cards_ind")
	private String prepaidCardsInd;

	@Column(name="residence_country_code")
	private String residenceCountryCode;

	@Column(name="residence_country_name")
	private String residenceCountryName;

	@Column(name="risk_classification")
	private BigDecimal riskClassification;

	@Column(name="street_address_1")
	private String streetAddress1;

	@Column(name="street_address_2")
	private String streetAddress2;

	@Column(name="street_city_name")
	private String streetCityName;

	@Column(name="street_country_code")
	private String streetCountryCode;

	@Column(name="street_country_name")
	private String streetCountryName;

	@Column(name="street_postal_code")
	private String streetPostalCode;

	@Column(name="street_state_code")
	private String streetStateCode;

	@Column(name="street_state_name")
	private String streetStateName;

	@Column(name="susp_actv_rpt_count")
	private BigDecimal suspActvRptCount;

	@Column(name="travelers_cheques_ind")
	private String travelersChequesInd;

	@Column(name="trust_account_ind")
	private String trustAccountInd;

	@Column(name="ultimate_parent_name")
	private String ultimateParentName;
	
	@Column(name="match_code_party_name")
	private String matchCodePartyName;

	@Column(name="match_code_party_name_eng")
	private String matchCodePartyNameEng;

	@Column(name="party_name_eng")
	private String partyNameEng;

	@Column(name="primary_branch_number")
	private String primaryBranchNumber;
	
	public FscPartyDimChng() {
	}

	public FscPartyDimChngPK getId() {
		return this.id;
	}

	public void setId(FscPartyDimChngPK id) {
		this.id = id;
	}

	public BigDecimal getAnnualIncomeAmount() {
		return annualIncomeAmount;
	}

	public void setAnnualIncomeAmount(BigDecimal annualIncomeAmount) {
		this.annualIncomeAmount = annualIncomeAmount;
	}

	public Timestamp getChangeBeginDate() {
		return changeBeginDate;
	}

	public void setChangeBeginDate(Timestamp changeBeginDate) {
		this.changeBeginDate = changeBeginDate;
	}

	public String getChangeCurrentInd() {
		return changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	public Timestamp getChangeEndDate() {
		return changeEndDate;
	}

	public void setChangeEndDate(Timestamp changeEndDate) {
		this.changeEndDate = changeEndDate;
	}

	public String getCheckCasherInd() {
		return checkCasherInd;
	}

	public void setCheckCasherInd(String checkCasherInd) {
		this.checkCasherInd = checkCasherInd;
	}

	public String getCitizenshipCountryCode() {
		return citizenshipCountryCode;
	}

	public void setCitizenshipCountryCode(String citizenshipCountryCode) {
		this.citizenshipCountryCode = citizenshipCountryCode;
	}

	public String getCitizenshipCountryName() {
		return citizenshipCountryName;
	}

	public void setCitizenshipCountryName(String citizenshipCountryName) {
		this.citizenshipCountryName = citizenshipCountryName;
	}

	public String getCurrencyExchangeInd() {
		return currencyExchangeInd;
	}

	public void setCurrencyExchangeInd(String currencyExchangeInd) {
		this.currencyExchangeInd = currencyExchangeInd;
	}

	public Timestamp getCustomerSinceDate() {
		return customerSinceDate;
	}

	public void setCustomerSinceDate(Timestamp customerSinceDate) {
		this.customerSinceDate = customerSinceDate;
	}

	public String getDoingBusinessAsName() {
		return doingBusinessAsName;
	}

	public void setDoingBusinessAsName(String doingBusinessAsName) {
		this.doingBusinessAsName = doingBusinessAsName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmployeeInd() {
		return employeeInd;
	}

	public void setEmployeeInd(String employeeInd) {
		this.employeeInd = employeeInd;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getEmployerPhoneNumber() {
		return employerPhoneNumber;
	}

	public void setEmployerPhoneNumber(String employerPhoneNumber) {
		this.employerPhoneNumber = employerPhoneNumber;
	}

	public String getExternalPartyInd() {
		return externalPartyInd;
	}

	public void setExternalPartyInd(String externalPartyInd) {
		this.externalPartyInd = externalPartyInd;
	}

	public String getForeignConsulateEmbassyInd() {
		return foreignConsulateEmbassyInd;
	}

	public void setForeignConsulateEmbassyInd(String foreignConsulateEmbassyInd) {
		this.foreignConsulateEmbassyInd = foreignConsulateEmbassyInd;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getIndustryCodeRr() {
		return industryCodeRr;
	}

	public void setIndustryCodeRr(String industryCodeRr) {
		this.industryCodeRr = industryCodeRr;
	}

	public String getIndustryDesc() {
		return industryDesc;
	}

	public void setIndustryDesc(String industryDesc) {
		this.industryDesc = industryDesc;
	}

	public String getInternetGamblingInd() {
		return internetGamblingInd;
	}

	public void setInternetGamblingInd(String internetGamblingInd) {
		this.internetGamblingInd = internetGamblingInd;
	}

	public String getIssuesBearerSharesInd() {
		return issuesBearerSharesInd;
	}

	public void setIssuesBearerSharesInd(String issuesBearerSharesInd) {
		this.issuesBearerSharesInd = issuesBearerSharesInd;
	}

	public Timestamp getLastCashTransRptDate() {
		return lastCashTransRptDate;
	}

	public void setLastCashTransRptDate(Timestamp lastCashTransRptDate) {
		this.lastCashTransRptDate = lastCashTransRptDate;
	}

	public Timestamp getLastContactDate() {
		return lastContactDate;
	}

	public void setLastContactDate(Timestamp lastContactDate) {
		this.lastContactDate = lastContactDate;
	}

	public Timestamp getLastRiskAssessmentDate() {
		return lastRiskAssessmentDate;
	}

	public void setLastRiskAssessmentDate(Timestamp lastRiskAssessmentDate) {
		this.lastRiskAssessmentDate = lastRiskAssessmentDate;
	}

	public Timestamp getLastSuspActvRptDate() {
		return lastSuspActvRptDate;
	}

	public void setLastSuspActvRptDate(Timestamp lastSuspActvRptDate) {
		this.lastSuspActvRptDate = lastSuspActvRptDate;
	}

	public String getLegalEntityType() {
		return legalEntityType;
	}

	public void setLegalEntityType(String legalEntityType) {
		this.legalEntityType = legalEntityType;
	}

	public Timestamp getLstUpdateDate() {
		return lstUpdateDate;
	}

	public void setLstUpdateDate(Timestamp lstUpdateDate) {
		this.lstUpdateDate = lstUpdateDate;
	}

	public String getMailingAddress1() {
		return mailingAddress1;
	}

	public void setMailingAddress1(String mailingAddress1) {
		this.mailingAddress1 = mailingAddress1;
	}

	public String getMailingAddress2() {
		return mailingAddress2;
	}

	public void setMailingAddress2(String mailingAddress2) {
		this.mailingAddress2 = mailingAddress2;
	}

	public String getMailingCityName() {
		return mailingCityName;
	}

	public void setMailingCityName(String mailingCityName) {
		this.mailingCityName = mailingCityName;
	}

	public String getMailingCountryCode() {
		return mailingCountryCode;
	}

	public void setMailingCountryCode(String mailingCountryCode) {
		this.mailingCountryCode = mailingCountryCode;
	}

	public String getMailingCountryName() {
		return mailingCountryName;
	}

	public void setMailingCountryName(String mailingCountryName) {
		this.mailingCountryName = mailingCountryName;
	}

	public String getMailingPostalCode() {
		return mailingPostalCode;
	}

	public void setMailingPostalCode(String mailingPostalCode) {
		this.mailingPostalCode = mailingPostalCode;
	}

	public String getMailingStateCode() {
		return mailingStateCode;
	}

	public void setMailingStateCode(String mailingStateCode) {
		this.mailingStateCode = mailingStateCode;
	}

	public String getMailingStateName() {
		return mailingStateName;
	}

	public void setMailingStateName(String mailingStateName) {
		this.mailingStateName = mailingStateName;
	}

	public String getMaritalStatusDesc() {
		return maritalStatusDesc;
	}

	public void setMaritalStatusDesc(String maritalStatusDesc) {
		this.maritalStatusDesc = maritalStatusDesc;
	}

	public String getMatchCodeIndividual() {
		return matchCodeIndividual;
	}

	public void setMatchCodeIndividual(String matchCodeIndividual) {
		this.matchCodeIndividual = matchCodeIndividual;
	}

	public String getMatchCodeMailingAddrLines() {
		return matchCodeMailingAddrLines;
	}

	public void setMatchCodeMailingAddrLines(String matchCodeMailingAddrLines) {
		this.matchCodeMailingAddrLines = matchCodeMailingAddrLines;
	}

	public String getMatchCodeMailingAddress() {
		return matchCodeMailingAddress;
	}

	public void setMatchCodeMailingAddress(String matchCodeMailingAddress) {
		this.matchCodeMailingAddress = matchCodeMailingAddress;
	}

	public String getMatchCodeMailingCity() {
		return matchCodeMailingCity;
	}

	public void setMatchCodeMailingCity(String matchCodeMailingCity) {
		this.matchCodeMailingCity = matchCodeMailingCity;
	}

	public String getMatchCodeMailingCountry() {
		return matchCodeMailingCountry;
	}

	public void setMatchCodeMailingCountry(String matchCodeMailingCountry) {
		this.matchCodeMailingCountry = matchCodeMailingCountry;
	}

	public String getMatchCodeMailingState() {
		return matchCodeMailingState;
	}

	public void setMatchCodeMailingState(String matchCodeMailingState) {
		this.matchCodeMailingState = matchCodeMailingState;
	}

	public String getMatchCodeOrganization() {
		return matchCodeOrganization;
	}

	public void setMatchCodeOrganization(String matchCodeOrganization) {
		this.matchCodeOrganization = matchCodeOrganization;
	}

	public String getMatchCodeStreetAddrLines() {
		return matchCodeStreetAddrLines;
	}

	public void setMatchCodeStreetAddrLines(String matchCodeStreetAddrLines) {
		this.matchCodeStreetAddrLines = matchCodeStreetAddrLines;
	}

	public String getMatchCodeStreetAddress() {
		return matchCodeStreetAddress;
	}

	public void setMatchCodeStreetAddress(String matchCodeStreetAddress) {
		this.matchCodeStreetAddress = matchCodeStreetAddress;
	}

	public String getMatchCodeStreetCity() {
		return matchCodeStreetCity;
	}

	public void setMatchCodeStreetCity(String matchCodeStreetCity) {
		this.matchCodeStreetCity = matchCodeStreetCity;
	}

	public String getMatchCodeStreetCountry() {
		return matchCodeStreetCountry;
	}

	public void setMatchCodeStreetCountry(String matchCodeStreetCountry) {
		this.matchCodeStreetCountry = matchCodeStreetCountry;
	}

	public String getMatchCodeStreetState() {
		return matchCodeStreetState;
	}

	public void setMatchCodeStreetState(String matchCodeStreetState) {
		this.matchCodeStreetState = matchCodeStreetState;
	}

	public String getMoneyOrdersInd() {
		return moneyOrdersInd;
	}

	public void setMoneyOrdersInd(String moneyOrdersInd) {
		this.moneyOrdersInd = moneyOrdersInd;
	}

	public String getMoneyTransmitterInd() {
		return moneyTransmitterInd;
	}

	public void setMoneyTransmitterInd(String moneyTransmitterInd) {
		this.moneyTransmitterInd = moneyTransmitterInd;
	}

	public String getMsbInd() {
		return msbInd;
	}

	public void setMsbInd(String msbInd) {
		this.msbInd = msbInd;
	}

	public String getNegativeNewsInd() {
		return negativeNewsInd;
	}

	public void setNegativeNewsInd(String negativeNewsInd) {
		this.negativeNewsInd = negativeNewsInd;
	}

	public BigDecimal getNetWorthAmount() {
		return netWorthAmount;
	}

	public void setNetWorthAmount(BigDecimal netWorthAmount) {
		this.netWorthAmount = netWorthAmount;
	}

	public String getNonProfitOrgInd() {
		return nonProfitOrgInd;
	}

	public void setNonProfitOrgInd(String nonProfitOrgInd) {
		this.nonProfitOrgInd = nonProfitOrgInd;
	}

	public String getOccupationDesc() {
		return occupationDesc;
	}

	public void setOccupationDesc(String occupationDesc) {
		this.occupationDesc = occupationDesc;
	}

	public String getOrgCountryOfBusinessCode() {
		return orgCountryOfBusinessCode;
	}

	public void setOrgCountryOfBusinessCode(String orgCountryOfBusinessCode) {
		this.orgCountryOfBusinessCode = orgCountryOfBusinessCode;
	}

	public String getOrgCountryOfBusinessName() {
		return orgCountryOfBusinessName;
	}

	public void setOrgCountryOfBusinessName(String orgCountryOfBusinessName) {
		this.orgCountryOfBusinessName = orgCountryOfBusinessName;
	}

	public String getOwnerUserLongId() {
		return ownerUserLongId;
	}

	public void setOwnerUserLongId(String ownerUserLongId) {
		this.ownerUserLongId = ownerUserLongId;
	}

	public Timestamp getPartyDateOfBirth() {
		return partyDateOfBirth;
	}

	public void setPartyDateOfBirth(Timestamp partyDateOfBirth) {
		this.partyDateOfBirth = partyDateOfBirth;
	}

	public String getPartyFirstName() {
		return partyFirstName;
	}

	public void setPartyFirstName(String partyFirstName) {
		this.partyFirstName = partyFirstName;
	}

	public String getPartyIdCountryCode() {
		return partyIdCountryCode;
	}

	public void setPartyIdCountryCode(String partyIdCountryCode) {
		this.partyIdCountryCode = partyIdCountryCode;
	}

	public String getPartyIdStateCode() {
		return partyIdStateCode;
	}

	public void setPartyIdStateCode(String partyIdStateCode) {
		this.partyIdStateCode = partyIdStateCode;
	}

	public String getPartyIdentificationId() {
		return partyIdentificationId;
	}

	public void setPartyIdentificationId(String partyIdentificationId) {
		this.partyIdentificationId = partyIdentificationId;
	}

	public String getPartyIdentificationTypeDesc() {
		return partyIdentificationTypeDesc;
	}

	public void setPartyIdentificationTypeDesc(String partyIdentificationTypeDesc) {
		this.partyIdentificationTypeDesc = partyIdentificationTypeDesc;
	}

	public String getPartyLastName() {
		return partyLastName;
	}

	public void setPartyLastName(String partyLastName) {
		this.partyLastName = partyLastName;
	}

	public String getPartyMiddleName() {
		return partyMiddleName;
	}

	public void setPartyMiddleName(String partyMiddleName) {
		this.partyMiddleName = partyMiddleName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyNumber() {
		return partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

	public String getPartyStatusDesc() {
		return partyStatusDesc;
	}

	public void setPartyStatusDesc(String partyStatusDesc) {
		this.partyStatusDesc = partyStatusDesc;
	}

	public String getPartyTaxId() {
		return partyTaxId;
	}

	public void setPartyTaxId(String partyTaxId) {
		this.partyTaxId = partyTaxId;
	}

	public String getPartyTaxIdTypeCode() {
		return partyTaxIdTypeCode;
	}

	public void setPartyTaxIdTypeCode(String partyTaxIdTypeCode) {
		this.partyTaxIdTypeCode = partyTaxIdTypeCode;
	}

	public String getPartyTypeDesc() {
		return partyTypeDesc;
	}

	public void setPartyTypeDesc(String partyTypeDesc) {
		this.partyTypeDesc = partyTypeDesc;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getPhoneNumber3() {
		return phoneNumber3;
	}

	public void setPhoneNumber3(String phoneNumber3) {
		this.phoneNumber3 = phoneNumber3;
	}

	public String getPoliticallyExposedPersonInd() {
		return politicallyExposedPersonInd;
	}

	public void setPoliticallyExposedPersonInd(String politicallyExposedPersonInd) {
		this.politicallyExposedPersonInd = politicallyExposedPersonInd;
	}

	public String getPrepaidCardsInd() {
		return prepaidCardsInd;
	}

	public void setPrepaidCardsInd(String prepaidCardsInd) {
		this.prepaidCardsInd = prepaidCardsInd;
	}

	public String getResidenceCountryCode() {
		return residenceCountryCode;
	}

	public void setResidenceCountryCode(String residenceCountryCode) {
		this.residenceCountryCode = residenceCountryCode;
	}

	public String getResidenceCountryName() {
		return residenceCountryName;
	}

	public void setResidenceCountryName(String residenceCountryName) {
		this.residenceCountryName = residenceCountryName;
	}

	public BigDecimal getRiskClassification() {
		return riskClassification;
	}

	public void setRiskClassification(BigDecimal riskClassification) {
		this.riskClassification = riskClassification;
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getStreetCityName() {
		return streetCityName;
	}

	public void setStreetCityName(String streetCityName) {
		this.streetCityName = streetCityName;
	}

	public String getStreetCountryCode() {
		return streetCountryCode;
	}

	public void setStreetCountryCode(String streetCountryCode) {
		this.streetCountryCode = streetCountryCode;
	}

	public String getStreetCountryName() {
		return streetCountryName;
	}

	public void setStreetCountryName(String streetCountryName) {
		this.streetCountryName = streetCountryName;
	}

	public String getStreetPostalCode() {
		return streetPostalCode;
	}

	public void setStreetPostalCode(String streetPostalCode) {
		this.streetPostalCode = streetPostalCode;
	}

	public String getStreetStateCode() {
		return streetStateCode;
	}

	public void setStreetStateCode(String streetStateCode) {
		this.streetStateCode = streetStateCode;
	}

	public String getStreetStateName() {
		return streetStateName;
	}

	public void setStreetStateName(String streetStateName) {
		this.streetStateName = streetStateName;
	}

	public BigDecimal getSuspActvRptCount() {
		return suspActvRptCount;
	}

	public void setSuspActvRptCount(BigDecimal suspActvRptCount) {
		this.suspActvRptCount = suspActvRptCount;
	}

	public String getTravelersChequesInd() {
		return travelersChequesInd;
	}

	public void setTravelersChequesInd(String travelersChequesInd) {
		this.travelersChequesInd = travelersChequesInd;
	}

	public String getTrustAccountInd() {
		return trustAccountInd;
	}

	public void setTrustAccountInd(String trustAccountInd) {
		this.trustAccountInd = trustAccountInd;
	}

	public String getUltimateParentName() {
		return ultimateParentName;
	}

	public void setUltimateParentName(String ultimateParentName) {
		this.ultimateParentName = ultimateParentName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMatchCodePartyNameEng() {
		return matchCodePartyNameEng;
	}

	public void setMatchCodePartyNameEng(String matchCodePartyNameEng) {
		this.matchCodePartyNameEng = matchCodePartyNameEng;
	}

	public String getPartyNameEng() {
		return partyNameEng;
	}

	public void setPartyNameEng(String partyNameEng) {
		this.partyNameEng = partyNameEng;
	}

	public String getMatchCodePartyName() {
		return matchCodePartyName;
	}

	public void setMatchCodePartyName(String matchCodePartyName) {
		this.matchCodePartyName = matchCodePartyName;
	}

	public String getPartyTypeCode() {
		return partyTypeCode;
	}

	public void setPartyTypeCode(String partyTypeCode) {
		this.partyTypeCode = partyTypeCode;
	}

	public String getPrimaryBranchNumber() {
		return primaryBranchNumber;
	}

	public void setPrimaryBranchNumber(String primaryBranchNumber) {
		this.primaryBranchNumber = primaryBranchNumber;
	}

	
	

}