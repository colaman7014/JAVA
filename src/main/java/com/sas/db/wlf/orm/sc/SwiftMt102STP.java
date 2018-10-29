package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT102STP database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT102STP", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt102STP.findAll", query="SELECT s FROM SwiftMt102STP s")
public class SwiftMt102STP implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt102STPPK id;

	@Column(name="ACCOUNT_WITH_INSTITUTION")
	private String accountWithInstitution;

	@Column(name="BANK_OPERATION_CODE")
	private String bankOperationCode;

	@Column(name="BENEFICIARY_CUSTOMER")
	private String beneficiaryCustomer;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="CURRENCY_INSTRUCTED_AMOUNT")
	private String currencyInstructedAmount;

	@Column(name="DETAILS_OF_CHARGES")
	private String detailsOfCharges;

	@Column(name="EXCHANGE_RATE")
	private String exchangeRate;

	@Column(name="FILE_REFERENCE")
	private String fileReference;

	@Column(name="ORDERING_CUSTOMER")
	private String orderingCustomer;

	@Column(name="ORDERING_INSTITUTION")
	private String orderingInstitution;

	@Column(name="RECEIVERS_CHARGES")
	private String receiversCharges;

	@Column(name="RECEIVERS_CORRESPONDENT")
	private String receiversCorrespondent;

	@Column(name="REGULATORY_REPORTING")
	private String regulatoryReporting;

	@Column(name="REMITTANCE_INFORMATION")
	private String remittanceInformation;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDERS_CHARGES")
	private String sendersCharges;

	@Column(name="SENDERS_CORRESPONDENT")
	private String sendersCorrespondent;

	@Column(name="SUM_OF_AMOUNTS")
	private String sumOfAmounts;

	@Column(name="SUM_OF_RECEIVERS_CHARGES")
	private String sumOfReceiversCharges;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TIME_INDICATION")
	private String timeIndication;

	@Column(name="TRANSACTION_AMOUNT")
	private String transactionAmount;

	@Column(name="TRANSACTION_REFERENCE")
	private String transactionReference;

	@Column(name="TRANSACTION_TYPE_CODE")
	private String transactionTypeCode;

	@Column(name="VALUE_DATE_CURRENCY_CODE_AMOUNT")
	private String valueDateCurrencyCodeAmount;

	public SwiftMt102STP() {
	}

	public SwiftMt102STPPK getId() {
		return this.id;
	}

	public void setId(SwiftMt102STPPK id) {
		this.id = id;
	}

	public String getAccountWithInstitution() {
		return this.accountWithInstitution;
	}

	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
	}

	public String getBankOperationCode() {
		return this.bankOperationCode;
	}

	public void setBankOperationCode(String bankOperationCode) {
		this.bankOperationCode = bankOperationCode;
	}

	public String getBeneficiaryCustomer() {
		return this.beneficiaryCustomer;
	}

	public void setBeneficiaryCustomer(String beneficiaryCustomer) {
		this.beneficiaryCustomer = beneficiaryCustomer;
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

	public String getCurrencyInstructedAmount() {
		return this.currencyInstructedAmount;
	}

	public void setCurrencyInstructedAmount(String currencyInstructedAmount) {
		this.currencyInstructedAmount = currencyInstructedAmount;
	}

	public String getDetailsOfCharges() {
		return this.detailsOfCharges;
	}

	public void setDetailsOfCharges(String detailsOfCharges) {
		this.detailsOfCharges = detailsOfCharges;
	}

	public String getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getFileReference() {
		return this.fileReference;
	}

	public void setFileReference(String fileReference) {
		this.fileReference = fileReference;
	}

	public String getOrderingCustomer() {
		return this.orderingCustomer;
	}

	public void setOrderingCustomer(String orderingCustomer) {
		this.orderingCustomer = orderingCustomer;
	}

	public String getOrderingInstitution() {
		return this.orderingInstitution;
	}

	public void setOrderingInstitution(String orderingInstitution) {
		this.orderingInstitution = orderingInstitution;
	}

	public String getReceiversCharges() {
		return this.receiversCharges;
	}

	public void setReceiversCharges(String receiversCharges) {
		this.receiversCharges = receiversCharges;
	}

	public String getReceiversCorrespondent() {
		return this.receiversCorrespondent;
	}

	public void setReceiversCorrespondent(String receiversCorrespondent) {
		this.receiversCorrespondent = receiversCorrespondent;
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

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTimeIndication() {
		return this.timeIndication;
	}

	public void setTimeIndication(String timeIndication) {
		this.timeIndication = timeIndication;
	}

	public String getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
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

	public String getValueDateCurrencyCodeAmount() {
		return this.valueDateCurrencyCodeAmount;
	}

	public void setValueDateCurrencyCodeAmount(String valueDateCurrencyCodeAmount) {
		this.valueDateCurrencyCodeAmount = valueDateCurrencyCodeAmount;
	}

}