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
 * The persistent class for the SWIFT_MT202 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT202", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt202.findAll", query="SELECT s FROM SwiftMt202 s")
public class SwiftMt202 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt202PK id;

	@Column(name="ACCOUNT_WITH_INSTITUTION")
	private String accountWithInstitution;

	@Column(name="BENEFICIARY_INSTITUTION")
	private String beneficiaryInstitution;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="INTERMEDIARY")
	private String intermediary;

	@Column(name="ORDERING_INSTITUTION")
	private String orderingInstitution;

	@Column(name="RECEIVERS_CORRESPONDENT")
	private String receiversCorrespondent;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

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

	public SwiftMt202() {
	}

	public SwiftMt202PK getId() {
		return this.id;
	}

	public void setId(SwiftMt202PK id) {
		this.id = id;
	}

	public String getAccountWithInstitution() {
		return this.accountWithInstitution;
	}

	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
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

	public String getIntermediary() {
		return this.intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public String getOrderingInstitution() {
		return this.orderingInstitution;
	}

	public void setOrderingInstitution(String orderingInstitution) {
		this.orderingInstitution = orderingInstitution;
	}

	public String getReceiversCorrespondent() {
		return this.receiversCorrespondent;
	}

	public void setReceiversCorrespondent(String receiversCorrespondent) {
		this.receiversCorrespondent = receiversCorrespondent;
	}

	public String getRelatedReference() {
		return this.relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
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