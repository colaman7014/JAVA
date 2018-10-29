package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT490 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT490", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt490.findAll", query="SELECT s FROM SwiftMt490 s")
public class SwiftMt490 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt490PK id;

	@Column(name="ACCOUNT_IDENTIFICATION")
	private String accountIdentification;

	@Column(name="CHARGES")
	private String charges;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="ORDERING_INSTITUTION")
	private String orderingInstitution;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	@Column(name="VALUE_DATE_CURRENCY_CODE_AMOUNT")
	private String valueDateCurrencyCodeAmount;

	public SwiftMt490() {
	}

	public SwiftMt490PK getId() {
		return this.id;
	}

	public void setId(SwiftMt490PK id) {
		this.id = id;
	}

	public String getAccountIdentification() {
		return this.accountIdentification;
	}

	public void setAccountIdentification(String accountIdentification) {
		this.accountIdentification = accountIdentification;
	}

	public String getCharges() {
		return this.charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
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

	public String getSenderToReceiverInformation() {
		return this.senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
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