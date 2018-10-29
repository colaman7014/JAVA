package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT450 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT450", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt450.findAll", query="SELECT s FROM SwiftMt450 s")
public class SwiftMt450 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt450PK id;

	@Column(name="Account_Identification")
	private String accountIdentification;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_of_Cash_Letter")
	private String dateOfCashLetter;

	@Column(name="Related_Reference")
	private String relatedReference;

	@Column(name="Sender_of_Cash_Letter")
	private String senderOfCashLetter;

	@Column(name="Sender_to_Receiver_Information")
	private String senderToReceiverInformation;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transactionReferenceNumber;

	@Column(name="Value_Date_Currency_Code_Amount")
	private String valueDateCurrencyCodeAmount;

	public SwiftMt450() {
	}

	public SwiftMt450PK getId() {
		return id;
	}

	public void setId(SwiftMt450PK id) {
		this.id = id;
	}

	public String getAccountIdentification() {
		return accountIdentification;
	}

	public void setAccountIdentification(String accountIdentification) {
		this.accountIdentification = accountIdentification;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getDateOfCashLetter() {
		return dateOfCashLetter;
	}

	public void setDateOfCashLetter(String dateOfCashLetter) {
		this.dateOfCashLetter = dateOfCashLetter;
	}

	public String getRelatedReference() {
		return relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}

	public String getSenderOfCashLetter() {
		return senderOfCashLetter;
	}

	public void setSenderOfCashLetter(String senderOfCashLetter) {
		this.senderOfCashLetter = senderOfCashLetter;
	}

	public String getSenderToReceiverInformation() {
		return senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSwiftFullText() {
		return swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTransactionReferenceNumber() {
		return transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}

	public String getValueDateCurrencyCodeAmount() {
		return valueDateCurrencyCodeAmount;
	}

	public void setValueDateCurrencyCodeAmount(String valueDateCurrencyCodeAmount) {
		this.valueDateCurrencyCodeAmount = valueDateCurrencyCodeAmount;
	}
}