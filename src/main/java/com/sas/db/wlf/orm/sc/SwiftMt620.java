package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT620 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT620", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt620.findAll", query="SELECT s FROM SwiftMt620 s")
public class SwiftMt620 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt620PK id;

	@Column(name="AMOUNT_TO_BE_SETTLED")
	private String amountToBeSettled;

	@Column(name="BENEFICIARY_INSTITUTION")
	private String beneficiaryInstitution;

	@Column(name="BROKER_IDENTIFICATION")
	private String brokerIdentification;

	@Column(name="BROKERS_COMMISSION")
	private String brokersCommission;

	@Column(name="BROKERS_REFERENCE")
	private String brokersReference;

	@Column(name="CONTACT_INFORMATION")
	private String contactInformation;

	@Column(name="CONTRACT_NUMBER_PARTY_A")
	private String contractNumberPartyA;

	@Column(name="COUNTERPARTYS_REFERENCE")
	private String counterpartysReference;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="CURRENCY_AND_INTEREST_AMOUNT")
	private String currencyAndInterestAmount;

	@Column(name="CURRENCY_AND_PRINCIPAL_AMOUNT")
	private String currencyAndPrincipalAmount;

	@Column(name="DAY_COUNT_FRACTION")
	private String dayCountFraction;

	@Column(name="DEALING_BRANCH_PARTY_A")
	private String dealingBranchPartyA;

	@Column(name="DEALING_BRANCH_PARTY_B")
	private String dealingBranchPartyB;

	@Column(name="DEALING_METHOD")
	private String dealingMethod;

	@Column(name="DELIVERY_AGENT")
	private String deliveryAgent;

	@Column(name="EXCHANGE_RATE")
	private String exchangeRate;

	@Column(name="FUND_OR_INSTRUCTING_PARTY")
	private String fundOrInstructingParty;

	@Column(name="IDENTIFICATION_OF_THE_COMMODITY")
	private String identificationOfTheCommodity;

	@Column(name="INTEREST_RATE")
	private String interestRate;

	@Column(name="INTERMEDIARY")
	private String intermediary;

	@Column(name="INTERMEDIARY_2")
	private String intermediary2;

	@Column(name="LAST_DAY_OF_THE_FIRST_INTEREST_PERIOD")
	private String lastDayOfTheFirstInterestPeriod;

	@Column(name="MATURITY_DATE")
	private String maturityDate;

	@Column(name="NEW_SEQUENCE")
	private String newSequence;

	@Column(name="NEXT_INTEREST_DUE_DATE")
	private String nextInterestDueDate;

	@Column(name="NUMBER_OF_DAYS")
	private String numberOfDays;

	@Column(name="PARTY_A")
	private String partyA;

	@Column(name="PARTY_AS_ROLE")
	private String partyAsRole;

	@Column(name="PARTY_B")
	private String partyB;

	@Column(name="RECEIVING_AGENT")
	private String receivingAgent;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="REPORTING_CURRENCY_AND_TAX_AMOUNT")
	private String reportingCurrencyAndTaxAmount;

	@Column(name="SCOPE_OF_OPERATION")
	private String scopeOfOperation;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDERS_REFERENCE")
	private String sendersReference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TAX_RATE")
	private String taxRate;

	@Column(name="TERMS_AND_CONDITIONS")
	private String termsAndConditions;

	@Column(name="TRADE_DATE")
	private String tradeDate;

	@Column(name="TRANSACTION_CURRENCY_AND_NET_INTEREST_AMOUNT")
	private String transactionCurrencyAndNetInterestAmount;

	@Column(name="TYPE_OF_EVENT")
	private String typeOfEvent;

	@Column(name="TYPE_OF_OPERATION")
	private String typeOfOperation;

	@Column(name="UNDERLYING_PRODUCT_IDENTIFIER")
	private String underlyingProductIdentifier;

	@Column(name="VALUE_DATE")
	private String valueDate;

	public SwiftMt620() {
	}

	public SwiftMt620PK getId() {
		return this.id;
	}

	public void setId(SwiftMt620PK id) {
		this.id = id;
	}

	public String getAmountToBeSettled() {
		return this.amountToBeSettled;
	}

	public void setAmountToBeSettled(String amountToBeSettled) {
		this.amountToBeSettled = amountToBeSettled;
	}

	public String getBeneficiaryInstitution() {
		return this.beneficiaryInstitution;
	}

	public void setBeneficiaryInstitution(String beneficiaryInstitution) {
		this.beneficiaryInstitution = beneficiaryInstitution;
	}

	public String getBrokerIdentification() {
		return this.brokerIdentification;
	}

	public void setBrokerIdentification(String brokerIdentification) {
		this.brokerIdentification = brokerIdentification;
	}

	public String getBrokersCommission() {
		return this.brokersCommission;
	}

	public void setBrokersCommission(String brokersCommission) {
		this.brokersCommission = brokersCommission;
	}

	public String getBrokersReference() {
		return this.brokersReference;
	}

	public void setBrokersReference(String brokersReference) {
		this.brokersReference = brokersReference;
	}

	public String getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getContractNumberPartyA() {
		return this.contractNumberPartyA;
	}

	public void setContractNumberPartyA(String contractNumberPartyA) {
		this.contractNumberPartyA = contractNumberPartyA;
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

	public String getCurrencyAndInterestAmount() {
		return this.currencyAndInterestAmount;
	}

	public void setCurrencyAndInterestAmount(String currencyAndInterestAmount) {
		this.currencyAndInterestAmount = currencyAndInterestAmount;
	}

	public String getCurrencyAndPrincipalAmount() {
		return this.currencyAndPrincipalAmount;
	}

	public void setCurrencyAndPrincipalAmount(String currencyAndPrincipalAmount) {
		this.currencyAndPrincipalAmount = currencyAndPrincipalAmount;
	}

	public String getDayCountFraction() {
		return this.dayCountFraction;
	}

	public void setDayCountFraction(String dayCountFraction) {
		this.dayCountFraction = dayCountFraction;
	}

	public String getDealingBranchPartyA() {
		return this.dealingBranchPartyA;
	}

	public void setDealingBranchPartyA(String dealingBranchPartyA) {
		this.dealingBranchPartyA = dealingBranchPartyA;
	}

	public String getDealingBranchPartyB() {
		return this.dealingBranchPartyB;
	}

	public void setDealingBranchPartyB(String dealingBranchPartyB) {
		this.dealingBranchPartyB = dealingBranchPartyB;
	}

	public String getDealingMethod() {
		return this.dealingMethod;
	}

	public void setDealingMethod(String dealingMethod) {
		this.dealingMethod = dealingMethod;
	}

	public String getDeliveryAgent() {
		return this.deliveryAgent;
	}

	public void setDeliveryAgent(String deliveryAgent) {
		this.deliveryAgent = deliveryAgent;
	}

	public String getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getFundOrInstructingParty() {
		return this.fundOrInstructingParty;
	}

	public void setFundOrInstructingParty(String fundOrInstructingParty) {
		this.fundOrInstructingParty = fundOrInstructingParty;
	}

	public String getIdentificationOfTheCommodity() {
		return this.identificationOfTheCommodity;
	}

	public void setIdentificationOfTheCommodity(String identificationOfTheCommodity) {
		this.identificationOfTheCommodity = identificationOfTheCommodity;
	}

	public String getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getIntermediary() {
		return this.intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public String getIntermediary2() {
		return this.intermediary2;
	}

	public void setIntermediary2(String intermediary2) {
		this.intermediary2 = intermediary2;
	}

	public String getLastDayOfTheFirstInterestPeriod() {
		return this.lastDayOfTheFirstInterestPeriod;
	}

	public void setLastDayOfTheFirstInterestPeriod(String lastDayOfTheFirstInterestPeriod) {
		this.lastDayOfTheFirstInterestPeriod = lastDayOfTheFirstInterestPeriod;
	}

	public String getMaturityDate() {
		return this.maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getNewSequence() {
		return this.newSequence;
	}

	public void setNewSequence(String newSequence) {
		this.newSequence = newSequence;
	}

	public String getNextInterestDueDate() {
		return this.nextInterestDueDate;
	}

	public void setNextInterestDueDate(String nextInterestDueDate) {
		this.nextInterestDueDate = nextInterestDueDate;
	}

	public String getNumberOfDays() {
		return this.numberOfDays;
	}

	public void setNumberOfDays(String numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public String getPartyA() {
		return this.partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getPartyAsRole() {
		return this.partyAsRole;
	}

	public void setPartyAsRole(String partyAsRole) {
		this.partyAsRole = partyAsRole;
	}

	public String getPartyB() {
		return this.partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}

	public String getReceivingAgent() {
		return this.receivingAgent;
	}

	public void setReceivingAgent(String receivingAgent) {
		this.receivingAgent = receivingAgent;
	}

	public String getRelatedReference() {
		return this.relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}

	public String getReportingCurrencyAndTaxAmount() {
		return this.reportingCurrencyAndTaxAmount;
	}

	public void setReportingCurrencyAndTaxAmount(String reportingCurrencyAndTaxAmount) {
		this.reportingCurrencyAndTaxAmount = reportingCurrencyAndTaxAmount;
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

	public String getSendersReference() {
		return this.sendersReference;
	}

	public void setSendersReference(String sendersReference) {
		this.sendersReference = sendersReference;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getTermsAndConditions() {
		return this.termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public String getTradeDate() {
		return this.tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTransactionCurrencyAndNetInterestAmount() {
		return this.transactionCurrencyAndNetInterestAmount;
	}

	public void setTransactionCurrencyAndNetInterestAmount(String transactionCurrencyAndNetInterestAmount) {
		this.transactionCurrencyAndNetInterestAmount = transactionCurrencyAndNetInterestAmount;
	}

	public String getTypeOfEvent() {
		return this.typeOfEvent;
	}

	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}

	public String getTypeOfOperation() {
		return this.typeOfOperation;
	}

	public void setTypeOfOperation(String typeOfOperation) {
		this.typeOfOperation = typeOfOperation;
	}

	public String getUnderlyingProductIdentifier() {
		return this.underlyingProductIdentifier;
	}

	public void setUnderlyingProductIdentifier(String underlyingProductIdentifier) {
		this.underlyingProductIdentifier = underlyingProductIdentifier;
	}

	public String getValueDate() {
		return this.valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

}