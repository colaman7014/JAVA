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
 * The persistent class for the SWIFT_MT205COV database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT205COV", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt205COV.findAll", query="SELECT s FROM SwiftMt205COV s")
public class SwiftMt205COV implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt205COVPK id;

	@Column(name="ACCOUNT_WITH_INSTITUTION")
	private String accountWithInstitution;

	@Column(name="BENEFICIARY_CUSTOMER")
	private String beneficiaryCustomer;

	@Column(name="BENEFICIARY_INSTITUTION")
	private String beneficiaryInstitution;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="CURRENCY_INSTRUCTED_AMOUNT")
	private String currencyInstructedAmount;

	@Column(name="INTERMEDIARY_INSTITUTION")
	private String intermediaryInstitution;

	@Column(name="ORDERING_INSTITUTION")
	private String orderingInstitution;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="REMITTANCE_INFORMATION")
	private String remittanceInformation;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDERS_CORRESPONDENT")
	private String sendersCorrespondent;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TIME_INDICATION")
	private String timeIndication;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	@Column(name="VALUE_DATE_CURRENCY_CODE_AMOUNT")
	private String valueDateCurrencyCodeAmount;

	public SwiftMt205COV() {
	}

	public SwiftMt205COVPK getId() {
		return this.id;
	}

	public void setId(SwiftMt205COVPK id) {
		this.id = id;
	}

	public String getAccountWithInstitution() {
		return this.accountWithInstitution;
	}

	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
	}

	public String getBeneficiaryCustomer() {
		return this.beneficiaryCustomer;
	}

	public void setBeneficiaryCustomer(String beneficiaryCustomer) {
		this.beneficiaryCustomer = beneficiaryCustomer;
	}

	public String getBeneficiaryInstitution() {
		return this.beneficiaryInstitution;
	}

	public void setBeneficiaryInstitution(String beneficiaryInstitution) {
		this.beneficiaryInstitution = beneficiaryInstitution;
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

	public String getIntermediaryInstitution() {
		return this.intermediaryInstitution;
	}

	public void setIntermediaryInstitution(String intermediaryInstitution) {
		this.intermediaryInstitution = intermediaryInstitution;
	}

	public String getOrderingInstitution() {
		return this.orderingInstitution;
	}

	public void setOrderingInstitution(String orderingInstitution) {
		this.orderingInstitution = orderingInstitution;
	}

	public String getRelatedReference() {
		return this.relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
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

	public String getTimeIndication() {
		return this.timeIndication;
	}

	public void setTimeIndication(String timeIndication) {
		this.timeIndication = timeIndication;
	}

	public String getTransactionReferenceNumber() {
		return this.transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}

	public String getValueDateCurrencyCodeAmount() {
		return this.valueDateCurrencyCodeAmount;
	}

	public void setValueDateCurrencyCodeAmount(String valueDateCurrencyCodeAmount) {
		this.valueDateCurrencyCodeAmount = valueDateCurrencyCodeAmount;
	}

}