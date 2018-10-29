package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the SWIFT_MT103 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT103", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt103.findAll", query="SELECT s FROM SwiftMt103 s")
public class SwiftMt103 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt103PK id;

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

	@Column(name="DETAILS_OF_CHARGES")
	private String detailsOfCharges;

	@Column(name="EXCHANGE_RATE")
	private String exchangeRate;

//	@Column(name="INSTRUCTED_AMOUNT")
//	private String instructedAmount;

	@Column(name="INSTRUCTION_CODE")
	private String instructionCode;

	@Column(name="INTERMEDIARY_INSTITUTION")
	private String intermediaryInstitution;

	@Column(name="ORDERING_CUSTOMER")
	private String orderingCustomer;

	@Column(name="ORDERING_INSTITUTION")
	private String orderingInstitution;

	@Column(name="RECEIVERS_CHARGES")
	private String receiverCharges;

	@Column(name="RECEIVERS_CORRESPONDENT")
	private String receiverCorrespondent;

	@Column(name="REGULATORY_REPORTING")
	private String regulatoryReporting;

	@Column(name="REMITTANCE_INFORMATION")
	private String remittanceInformation;

	@Column(name="SENDERS_CHARGES")
	private String senderCharges;

	@Column(name="SENDERS_CORRESPONDENT")
	private String senderCorrespondent;

//	@Column(name="SENDER_INFORMATION")
//	private String senderInformation;

	@Column(name="SENDERS_REFERENCE")
	private String senderReference;

	@Column(name="SENDING_INSTITUTION")
	private String sendingInstitution;

//	@Column(name="SETTLED_AMOUNT")
//	private String settledAmount;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="THIRD_REIMBURSEMENT_INSTITUTION")
	private String thirdReimburseInstitution;

	@Column(name="TIME_INDICATION")
	private String timeIndication;

	@Column(name="TRANSACTION_TYPE_CODE")
	private String transactionTypeCode;
	
	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;
	
	@Column(name="CURRENCY_INSTRUCTED_AMOUNT")
	private String currencyInstructedAmount;
	
	@Column(name="VALUE_DATE_CURRENCY_INTERBANK_SETTLED_AMOUNT")
	private String valueDateCurrencyInterbankSettledAmount;

	public SwiftMt103() {
	}

	public SwiftMt103PK getId() {
		return id;
	}

	public void setId(SwiftMt103PK id) {
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

//	public String getInstructedAmount() {
//		return this.instructedAmount;
//	}
//
//	public void setInstructedAmount(String instructedAmount) {
//		this.instructedAmount = instructedAmount;
//	}

	public String getInstructionCode() {
		return this.instructionCode;
	}

	public void setInstructionCode(String instructionCode) {
		this.instructionCode = instructionCode;
	}

	public String getIntermediaryInstitution() {
		return this.intermediaryInstitution;
	}

	public void setIntermediaryInstitution(String intermediaryInstitution) {
		this.intermediaryInstitution = intermediaryInstitution;
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

	public String getReceiverCharges() {
		return this.receiverCharges;
	}

	public void setReceiverCharges(String receiverCharges) {
		this.receiverCharges = receiverCharges;
	}

	public String getReceiverCorrespondent() {
		return this.receiverCorrespondent;
	}

	public void setReceiverCorrespondent(String receiverCorrespondent) {
		this.receiverCorrespondent = receiverCorrespondent;
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

	public String getSenderCharges() {
		return this.senderCharges;
	}

	public void setSenderCharges(String senderCharges) {
		this.senderCharges = senderCharges;
	}

	public String getSenderCorrespondent() {
		return this.senderCorrespondent;
	}

	public void setSenderCorrespondent(String senderCorrespondent) {
		this.senderCorrespondent = senderCorrespondent;
	}

//	public String getSenderInformation() {
//		return this.senderInformation;
//	}
//
//	public void setSenderInformation(String senderInformation) {
//		this.senderInformation = senderInformation;
//	}

	public String getSenderReference() {
		return this.senderReference;
	}

	public void setSenderReference(String senderReference) {
		this.senderReference = senderReference;
	}

	public String getSendingInstitution() {
		return this.sendingInstitution;
	}

	public void setSendingInstitution(String sendingInstitution) {
		this.sendingInstitution = sendingInstitution;
	}

//	public String getSettledAmount() {
//		return this.settledAmount;
//	}
//
//	public void setSettledAmount(String settledAmount) {
//		this.settledAmount = settledAmount;
//	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getThirdReimburseInstitution() {
		return this.thirdReimburseInstitution;
	}

	public void setThirdReimburseInstitution(String thirdReimburseInstitution) {
		this.thirdReimburseInstitution = thirdReimburseInstitution;
	}

	public String getTimeIndication() {
		return this.timeIndication;
	}

	public void setTimeIndication(String timeIndication) {
		this.timeIndication = timeIndication;
	}

	public String getTransactionTypeCode() {
		return this.transactionTypeCode;
	}

	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	public String getSenderToReceiverInformation() {
		return senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getCurrencyInstructedAmount() {
		return currencyInstructedAmount;
	}

	public void setCurrencyInstructedAmount(String currencyInstructedAmount) {
		this.currencyInstructedAmount = currencyInstructedAmount;
	}

	public String getValueDateCurrencyInterbankSettledAmount() {
		return valueDateCurrencyInterbankSettledAmount;
	}

	public void setValueDateCurrencyInterbankSettledAmount(
			String valueDateCurrencyInterbankSettledAmount) {
		this.valueDateCurrencyInterbankSettledAmount = valueDateCurrencyInterbankSettledAmount;
	}

}