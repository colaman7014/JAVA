package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT107 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT107", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt107.findAll", query="SELECT s FROM SwiftMt107 s")
public class SwiftMt107 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt107PK id;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="CREDITOR")
	private String creditor;

	@Column(name="CREDITORS_BANK")
	private String creditorsBank;

	@Column(name="CURRENCY_AND_SETTLEMENT_AMOUNT")
	private String currencyAndSettlementAmount;

	@Column(name="CURRENCY_AND_TRANSACTION_AMOUNT")
	private String currencyAndTransactionAmount;

	@Column(name="CURRENCY_ORIGINAL_ORDERED_AMOUNT")
	private String currencyOriginalOrderedAmount;

	@Column(name="DEBTOR")
	private String debtor;

	@Column(name="DEBTORS_BANK")
	private String debtorsBank;

	@Column(name="DETAILS_OF_CHARGES")
	private String detailsOfCharges;

	@Column(name="DIRECT_DEBIT_REFERENCE")
	private String directDebitReference;

	@Column(name="EXCHANGE_RATE")
	private String exchangeRate;

	@Column(name="INSTRUCTING_PARTY")
	private String instructingParty;

	@Column(name="INSTRUCTION_CODE")
	private String instructionCode;

	@Column(name="MANDATE_REFERENCE")
	private String mandateReference;

	@Column(name="RECEIVERS_CHARGES")
	private String receiversCharges;

	@Column(name="REGISTRATION_REFERENCE")
	private String registrationReference;

	@Column(name="REGULATORY_REPORTING")
	private String regulatoryReporting;

	@Column(name="REMITTANCE_INFORMATION")
	private String remittanceInformation;

	@Column(name="REQUESTED_EXECUTION_DATE")
	private String requestedExecutionDate;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDERS_CHARGES")
	private String sendersCharges;

	@Column(name="SENDERS_CORRESPONDENT")
	private String sendersCorrespondent;

	@Column(name="SENDERS_REFERENCE")
	private String sendersReference;

	@Column(name="SENDING_INSTITUTION")
	private String sendingInstitution;

	@Column(name="SUM_OF_AMOUNTS")
	private String sumOfAmounts;

	@Column(name="SUM_OF_RECEIVERS_CHARGES")
	private String sumOfReceiversCharges;

	@Column(name="SUM_OF_SENDERS_CHARGES")
	private String sumOfSendersCharges;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSACTION_REFERENCE")
	private String transactionReference;

	@Column(name="TRANSACTION_TYPE_CODE")
	private String transactionTypeCode;

	public SwiftMt107() {
	}

	public SwiftMt107PK getId() {
		return this.id;
	}

	public void setId(SwiftMt107PK id) {
		this.id = id;
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

	public String getCreditor() {
		return this.creditor;
	}

	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}

	public String getCreditorsBank() {
		return this.creditorsBank;
	}

	public void setCreditorsBank(String creditorsBank) {
		this.creditorsBank = creditorsBank;
	}

	public String getCurrencyAndSettlementAmount() {
		return this.currencyAndSettlementAmount;
	}

	public void setCurrencyAndSettlementAmount(String currencyAndSettlementAmount) {
		this.currencyAndSettlementAmount = currencyAndSettlementAmount;
	}

	public String getCurrencyAndTransactionAmount() {
		return this.currencyAndTransactionAmount;
	}

	public void setCurrencyAndTransactionAmount(String currencyAndTransactionAmount) {
		this.currencyAndTransactionAmount = currencyAndTransactionAmount;
	}

	public String getCurrencyOriginalOrderedAmount() {
		return this.currencyOriginalOrderedAmount;
	}

	public void setCurrencyOriginalOrderedAmount(String currencyOriginalOrderedAmount) {
		this.currencyOriginalOrderedAmount = currencyOriginalOrderedAmount;
	}

	public String getDebtor() {
		return this.debtor;
	}

	public void setDebtor(String debtor) {
		this.debtor = debtor;
	}

	public String getDebtorsBank() {
		return this.debtorsBank;
	}

	public void setDebtorsBank(String debtorsBank) {
		this.debtorsBank = debtorsBank;
	}

	public String getDetailsOfCharges() {
		return this.detailsOfCharges;
	}

	public void setDetailsOfCharges(String detailsOfCharges) {
		this.detailsOfCharges = detailsOfCharges;
	}

	public String getDirectDebitReference() {
		return this.directDebitReference;
	}

	public void setDirectDebitReference(String directDebitReference) {
		this.directDebitReference = directDebitReference;
	}

	public String getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getInstructingParty() {
		return this.instructingParty;
	}

	public void setInstructingParty(String instructingParty) {
		this.instructingParty = instructingParty;
	}

	public String getInstructionCode() {
		return this.instructionCode;
	}

	public void setInstructionCode(String instructionCode) {
		this.instructionCode = instructionCode;
	}

	public String getMandateReference() {
		return this.mandateReference;
	}

	public void setMandateReference(String mandateReference) {
		this.mandateReference = mandateReference;
	}

	public String getReceiversCharges() {
		return this.receiversCharges;
	}

	public void setReceiversCharges(String receiversCharges) {
		this.receiversCharges = receiversCharges;
	}

	public String getRegistrationReference() {
		return this.registrationReference;
	}

	public void setRegistrationReference(String registrationReference) {
		this.registrationReference = registrationReference;
	}

	public String getRegulatoryReporting() {
		return this.regulatoryReporting;
	}

	public void setRegulatoryReporting(String regulatoryReporting) {
		this.regulatoryReporting = regulatoryReporting;
	}

	public String getRemittanceInformation() {
		return this.remittanceInformation;
	}

	public void setRemittanceInformation(String remittanceInformation) {
		this.remittanceInformation = remittanceInformation;
	}

	public String getRequestedExecutionDate() {
		return this.requestedExecutionDate;
	}

	public void setRequestedExecutionDate(String requestedExecutionDate) {
		this.requestedExecutionDate = requestedExecutionDate;
	}

	public String getSenderToReceiverInformation() {
		return this.senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSendersCharges() {
		return this.sendersCharges;
	}

	public void setSendersCharges(String sendersCharges) {
		this.sendersCharges = sendersCharges;
	}

	public String getSendersCorrespondent() {
		return this.sendersCorrespondent;
	}

	public void setSendersCorrespondent(String sendersCorrespondent) {
		this.sendersCorrespondent = sendersCorrespondent;
	}

	public String getSendersReference() {
		return this.sendersReference;
	}

	public void setSendersReference(String sendersReference) {
		this.sendersReference = sendersReference;
	}

	public String getSendingInstitution() {
		return this.sendingInstitution;
	}

	public void setSendingInstitution(String sendingInstitution) {
		this.sendingInstitution = sendingInstitution;
	}

	public String getSumOfAmounts() {
		return this.sumOfAmounts;
	}

	public void setSumOfAmounts(String sumOfAmounts) {
		this.sumOfAmounts = sumOfAmounts;
	}

	public String getSumOfReceiversCharges() {
		return this.sumOfReceiversCharges;
	}

	public void setSumOfReceiversCharges(String sumOfReceiversCharges) {
		this.sumOfReceiversCharges = sumOfReceiversCharges;
	}

	public String getSumOfSendersCharges() {
		return this.sumOfSendersCharges;
	}

	public void setSumOfSendersCharges(String sumOfSendersCharges) {
		this.sumOfSendersCharges = sumOfSendersCharges;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTransactionReference() {
		return this.transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	public String getTransactionTypeCode() {
		return this.transactionTypeCode;
	}

	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

}