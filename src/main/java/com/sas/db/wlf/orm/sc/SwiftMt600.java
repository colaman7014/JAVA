package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT600 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT600", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt600.findAll", query="SELECT s FROM SwiftMt600 s")
public class SwiftMt600 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt600PK id;

	@Column(name="ACCOUNT_WITH_INSTITUTION")
	private String accountWithInstitution;

	@Column(name="ADDITIONAL_REPORTING_INFORMATION")
	private String additionalReportingInformation;

	@Column(name="ALLOCATION_INDICATOR")
	private String allocationIndicator;

	@Column(name="CLEARED_PRODUCT_IDENTIFICATION")
	private String clearedProductIdentification;

	@Column(name="CLEARING_BROKER_IDENTIFICATION")
	private String clearingBrokerIdentification;

	@Column(name="CLEARING_EXCEPTION_PARTY")
	private String clearingExceptionParty;

	@Column(name="CLEARING_THRESHOLD_INDICATOR")
	private String clearingThresholdIndicator;

	@Column(name="CODE_COMMON_REFERENCE")
	private String codeCommonReference;

	@Column(name="COLLATERAL_PORTFOLIO_CODE")
	private String collateralPortfolioCode;

	@Column(name="COLLATERAL_PORTFOLIO_INDICATOR")
	private String collateralPortfolioIndicator;

	@Column(name="COLLATERALISATION_INDICATOR")
	private String collateralisationIndicator;

	@Column(name="COMMERCIAL_OR_TREASURY_FINANCING_INDICATOR")
	private String commercialOrTreasuryFinancingIndicator;

	@Column(name="CONSIDERATION")
	private String consideration;

	@Column(name="CORPORATE_SECTOR_INDICATOR")
	private String corporateSectorIndicator;

	@Column(name="COUNTERPARTYS_REFERENCE")
	private String counterpartysReference;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DATE_CONTRACT_AGREED_AMENDED")
	private String dateContractAgreedAmended;

	@Column(name="DELIVERER_OF_THE_COMMODITY")
	private String delivererOfTheCommodity;

	@Column(name="DETAILS_OF_ADJUSTMENTS")
	private String detailsOfAdjustments;

	@Column(name="EXECUTION_TIMESTAMP")
	private String executionTimestamp;

	@Column(name="EXECUTION_VENUE")
	private String executionVenue;

	@Column(name="FINANCIAL_NATURE_OF_THE_COUNTERPARTY_INDICATOR")
	private String financialNatureOfTheCounterpartyIndicator;

	@Column(name="FUND_OR_BENEFICIARY_CUSTOMER")
	private String fundOrBeneficiaryCustomer;

	@Column(name="IDENTIFICATION_OF_THE_COMMODITY")
	private String identificationOfTheCommodity;

	@Column(name="INTERMEDIARY")
	private String intermediary;

	@Column(name="INTRAGROUP_TRADE_INDICATOR")
	private String intragroupTradeIndicator;

	@Column(name="NEW_SEQUENCE")
	private String newSequence;

	@Column(name="NON_STANDARD_FLAG")
	private String nonStandardFlag;

	@Column(name="PARTY_A")
	private String partyA;

	@Column(name="PARTY_B")
	private String partyB;

	@Column(name="PORTFOLIO_COMPRESSION_INDICATOR")
	private String portfolioCompressionIndicator;

	@Column(name="PRICE_PER_UNIT")
	private String pricePerUnit;

	@Column(name="PRIOR_TRANSACTION_IDENTIFIER")
	private String priorTransactionIdentifier;

	@Column(name="PUTI_NAMESPACE_ISSUER_CODE")
	private String putiNamespaceIssuerCode;

	@Column(name="QUANTITY_OF_THE_COMMODITY")
	private String quantityOfTheCommodity;

	@Column(name="RECEIVER_OF_THE_COMMODITY")
	private String receiverOfTheCommodity;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="REPORTING_JURISDICTION")
	private String reportingJurisdiction;

	@Column(name="REPORTING_PARTY")
	private String reportingParty;

	@Column(name="SCOPE_OF_OPERATION")
	private String scopeOfOperation;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDERS_CORRESPONDENT")
	private String sendersCorrespondent;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TERMS_AND_CONDITIONS")
	private String termsAndConditions;

	@Column(name="TRADE_WITH_NON_EEA_COUNTERPARTY_INDICATOR")
	private String tradeWithNonEeaCounterpartyIndicator;

	@Column(name="TRANSACTION_IDENTIFIER")
	private String transactionIdentifier;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	@Column(name="TYPE_DATE_VERSION_OF_THE_AGREEMENT")
	private String typeDateVersionOfTheAgreement;

	@Column(name="UNDERLYING_PRODUCT_IDENTIFIER")
	private String underlyingProductIdentifier;

	@Column(name="UTI_NAMESPACE_ISSUER_CODE")
	private String utiNamespaceIssuerCode;

	@Column(name="YEAR_OF_DEFINITIONS")
	private String yearOfDefinitions;

	public SwiftMt600() {
	}

	public SwiftMt600PK getId() {
		return this.id;
	}

	public void setId(SwiftMt600PK id) {
		this.id = id;
	}

	public String getAccountWithInstitution() {
		return this.accountWithInstitution;
	}

	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
	}

	public String getAdditionalReportingInformation() {
		return this.additionalReportingInformation;
	}

	public void setAdditionalReportingInformation(String additionalReportingInformation) {
		this.additionalReportingInformation = additionalReportingInformation;
	}

	public String getAllocationIndicator() {
		return this.allocationIndicator;
	}

	public void setAllocationIndicator(String allocationIndicator) {
		this.allocationIndicator = allocationIndicator;
	}

	public String getClearedProductIdentification() {
		return this.clearedProductIdentification;
	}

	public void setClearedProductIdentification(String clearedProductIdentification) {
		this.clearedProductIdentification = clearedProductIdentification;
	}

	public String getClearingBrokerIdentification() {
		return this.clearingBrokerIdentification;
	}

	public void setClearingBrokerIdentification(String clearingBrokerIdentification) {
		this.clearingBrokerIdentification = clearingBrokerIdentification;
	}

	public String getClearingExceptionParty() {
		return this.clearingExceptionParty;
	}

	public void setClearingExceptionParty(String clearingExceptionParty) {
		this.clearingExceptionParty = clearingExceptionParty;
	}

	public String getClearingThresholdIndicator() {
		return this.clearingThresholdIndicator;
	}

	public void setClearingThresholdIndicator(String clearingThresholdIndicator) {
		this.clearingThresholdIndicator = clearingThresholdIndicator;
	}

	public String getCodeCommonReference() {
		return this.codeCommonReference;
	}

	public void setCodeCommonReference(String codeCommonReference) {
		this.codeCommonReference = codeCommonReference;
	}

	public String getCollateralPortfolioCode() {
		return this.collateralPortfolioCode;
	}

	public void setCollateralPortfolioCode(String collateralPortfolioCode) {
		this.collateralPortfolioCode = collateralPortfolioCode;
	}

	public String getCollateralPortfolioIndicator() {
		return this.collateralPortfolioIndicator;
	}

	public void setCollateralPortfolioIndicator(String collateralPortfolioIndicator) {
		this.collateralPortfolioIndicator = collateralPortfolioIndicator;
	}

	public String getCollateralisationIndicator() {
		return this.collateralisationIndicator;
	}

	public void setCollateralisationIndicator(String collateralisationIndicator) {
		this.collateralisationIndicator = collateralisationIndicator;
	}

	public String getCommercialOrTreasuryFinancingIndicator() {
		return this.commercialOrTreasuryFinancingIndicator;
	}

	public void setCommercialOrTreasuryFinancingIndicator(String commercialOrTreasuryFinancingIndicator) {
		this.commercialOrTreasuryFinancingIndicator = commercialOrTreasuryFinancingIndicator;
	}

	public String getConsideration() {
		return this.consideration;
	}

	public void setConsideration(String consideration) {
		this.consideration = consideration;
	}

	public String getCorporateSectorIndicator() {
		return this.corporateSectorIndicator;
	}

	public void setCorporateSectorIndicator(String corporateSectorIndicator) {
		this.corporateSectorIndicator = corporateSectorIndicator;
	}

	public String getCounterpartysReference() {
		return this.counterpartysReference;
	}

	public void setCounterpartysReference(String counterpartysReference) {
		this.counterpartysReference = counterpartysReference;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreatedTimestamp() {
		return this.createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getDateContractAgreedAmended() {
		return this.dateContractAgreedAmended;
	}

	public void setDateContractAgreedAmended(String dateContractAgreedAmended) {
		this.dateContractAgreedAmended = dateContractAgreedAmended;
	}

	public String getDelivererOfTheCommodity() {
		return this.delivererOfTheCommodity;
	}

	public void setDelivererOfTheCommodity(String delivererOfTheCommodity) {
		this.delivererOfTheCommodity = delivererOfTheCommodity;
	}

	public String getDetailsOfAdjustments() {
		return this.detailsOfAdjustments;
	}

	public void setDetailsOfAdjustments(String detailsOfAdjustments) {
		this.detailsOfAdjustments = detailsOfAdjustments;
	}

	public String getExecutionTimestamp() {
		return this.executionTimestamp;
	}

	public void setExecutionTimestamp(String executionTimestamp) {
		this.executionTimestamp = executionTimestamp;
	}

	public String getExecutionVenue() {
		return this.executionVenue;
	}

	public void setExecutionVenue(String executionVenue) {
		this.executionVenue = executionVenue;
	}

	public String getFinancialNatureOfTheCounterpartyIndicator() {
		return this.financialNatureOfTheCounterpartyIndicator;
	}

	public void setFinancialNatureOfTheCounterpartyIndicator(String financialNatureOfTheCounterpartyIndicator) {
		this.financialNatureOfTheCounterpartyIndicator = financialNatureOfTheCounterpartyIndicator;
	}

	public String getFundOrBeneficiaryCustomer() {
		return this.fundOrBeneficiaryCustomer;
	}

	public void setFundOrBeneficiaryCustomer(String fundOrBeneficiaryCustomer) {
		this.fundOrBeneficiaryCustomer = fundOrBeneficiaryCustomer;
	}

	public String getIdentificationOfTheCommodity() {
		return this.identificationOfTheCommodity;
	}

	public void setIdentificationOfTheCommodity(String identificationOfTheCommodity) {
		this.identificationOfTheCommodity = identificationOfTheCommodity;
	}

	public String getIntermediary() {
		return this.intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public String getIntragroupTradeIndicator() {
		return this.intragroupTradeIndicator;
	}

	public void setIntragroupTradeIndicator(String intragroupTradeIndicator) {
		this.intragroupTradeIndicator = intragroupTradeIndicator;
	}

	public String getNewSequence() {
		return this.newSequence;
	}

	public void setNewSequence(String newSequence) {
		this.newSequence = newSequence;
	}

	public String getNonStandardFlag() {
		return this.nonStandardFlag;
	}

	public void setNonStandardFlag(String nonStandardFlag) {
		this.nonStandardFlag = nonStandardFlag;
	}

	public String getPartyA() {
		return this.partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getPartyB() {
		return this.partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}

	public String getPortfolioCompressionIndicator() {
		return this.portfolioCompressionIndicator;
	}

	public void setPortfolioCompressionIndicator(String portfolioCompressionIndicator) {
		this.portfolioCompressionIndicator = portfolioCompressionIndicator;
	}

	public String getPricePerUnit() {
		return this.pricePerUnit;
	}

	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public String getPriorTransactionIdentifier() {
		return this.priorTransactionIdentifier;
	}

	public void setPriorTransactionIdentifier(String priorTransactionIdentifier) {
		this.priorTransactionIdentifier = priorTransactionIdentifier;
	}

	public String getPutiNamespaceIssuerCode() {
		return this.putiNamespaceIssuerCode;
	}

	public void setPutiNamespaceIssuerCode(String putiNamespaceIssuerCode) {
		this.putiNamespaceIssuerCode = putiNamespaceIssuerCode;
	}

	public String getQuantityOfTheCommodity() {
		return this.quantityOfTheCommodity;
	}

	public void setQuantityOfTheCommodity(String quantityOfTheCommodity) {
		this.quantityOfTheCommodity = quantityOfTheCommodity;
	}

	public String getReceiverOfTheCommodity() {
		return this.receiverOfTheCommodity;
	}

	public void setReceiverOfTheCommodity(String receiverOfTheCommodity) {
		this.receiverOfTheCommodity = receiverOfTheCommodity;
	}

	public String getRelatedReference() {
		return this.relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}

	public String getReportingJurisdiction() {
		return this.reportingJurisdiction;
	}

	public void setReportingJurisdiction(String reportingJurisdiction) {
		this.reportingJurisdiction = reportingJurisdiction;
	}

	public String getReportingParty() {
		return this.reportingParty;
	}

	public void setReportingParty(String reportingParty) {
		this.reportingParty = reportingParty;
	}

	public String getScopeOfOperation() {
		return this.scopeOfOperation;
	}

	public void setScopeOfOperation(String scopeOfOperation) {
		this.scopeOfOperation = scopeOfOperation;
	}

	public String getSenderToReceiverInformation() {
		return this.senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSendersCorrespondent() {
		return this.sendersCorrespondent;
	}

	public void setSendersCorrespondent(String sendersCorrespondent) {
		this.sendersCorrespondent = sendersCorrespondent;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTermsAndConditions() {
		return this.termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public String getTradeWithNonEeaCounterpartyIndicator() {
		return this.tradeWithNonEeaCounterpartyIndicator;
	}

	public void setTradeWithNonEeaCounterpartyIndicator(String tradeWithNonEeaCounterpartyIndicator) {
		this.tradeWithNonEeaCounterpartyIndicator = tradeWithNonEeaCounterpartyIndicator;
	}

	public String getTransactionIdentifier() {
		return this.transactionIdentifier;
	}

	public void setTransactionIdentifier(String transactionIdentifier) {
		this.transactionIdentifier = transactionIdentifier;
	}

	public String getTransactionReferenceNumber() {
		return this.transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}

	public String getTypeDateVersionOfTheAgreement() {
		return this.typeDateVersionOfTheAgreement;
	}

	public void setTypeDateVersionOfTheAgreement(String typeDateVersionOfTheAgreement) {
		this.typeDateVersionOfTheAgreement = typeDateVersionOfTheAgreement;
	}

	public String getUnderlyingProductIdentifier() {
		return this.underlyingProductIdentifier;
	}

	public void setUnderlyingProductIdentifier(String underlyingProductIdentifier) {
		this.underlyingProductIdentifier = underlyingProductIdentifier;
	}

	public String getUtiNamespaceIssuerCode() {
		return this.utiNamespaceIssuerCode;
	}

	public void setUtiNamespaceIssuerCode(String utiNamespaceIssuerCode) {
		this.utiNamespaceIssuerCode = utiNamespaceIssuerCode;
	}

	public String getYearOfDefinitions() {
		return this.yearOfDefinitions;
	}

	public void setYearOfDefinitions(String yearOfDefinitions) {
		this.yearOfDefinitions = yearOfDefinitions;
	}

}