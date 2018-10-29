package com.sas.db.wlf.orm.sc;

import java.io.Serializable;

import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT455 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT455", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt455.findAll", query="SELECT s FROM SwiftMt455 s")
public class SwiftMt455 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt455PK id;

	@Column(name="ACCOUNT_IDENTIFICATION")
	private String accountIdentification;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DATE_OF_CASH_LETTER")
	private String dateOfCashLetter;

	@Column(name="REASON_FOR_ADJUSTMENT")
	private String reasonForAdjustment;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SENDER_OF_THE_CASH_LETTER")
	private String senderOfTheCashLetter;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	@Column(name="VALUE_DATE_AND_ADJUSTMENT_AMOUNT")
	private String valueDateAndAdjustmentAmount;

	@Column(name="VALUE_DATE_AND_ORIGINAL_AMOUNT")
	private String valueDateAndOriginalAmount;

	public SwiftMt455() {
	}

	public SwiftMt455PK getId() {
		return this.id;
	}

	public void setId(SwiftMt455PK id) {
		this.id = id;
	}

	public String getAccountIdentification() {
		return this.accountIdentification;
	}

	public void setAccountIdentification(String accountIdentification) {
		this.accountIdentification = accountIdentification;
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

	public String getDateOfCashLetter() {
		return this.dateOfCashLetter;
	}

	public void setDateOfCashLetter(String dateOfCashLetter) {
		this.dateOfCashLetter = dateOfCashLetter;
	}

	public String getReasonForAdjustment() {
		return this.reasonForAdjustment;
	}

	public void setReasonForAdjustment(String reasonForAdjustment) {
		this.reasonForAdjustment = reasonForAdjustment;
	}

	public String getRelatedReference() {
		return this.relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}

	public String getSenderOfTheCashLetter() {
		return this.senderOfTheCashLetter;
	}

	public void setSenderOfTheCashLetter(String senderOfTheCashLetter) {
		this.senderOfTheCashLetter = senderOfTheCashLetter;
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

	public String getValueDateAndAdjustmentAmount() {
		return this.valueDateAndAdjustmentAmount;
	}

	public void setValueDateAndAdjustmentAmount(String valueDateAndAdjustmentAmount) {
		this.valueDateAndAdjustmentAmount = valueDateAndAdjustmentAmount;
	}

	public String getValueDateAndOriginalAmount() {
		return this.valueDateAndOriginalAmount;
	}

	public void setValueDateAndOriginalAmount(String valueDateAndOriginalAmount) {
		this.valueDateAndOriginalAmount = valueDateAndOriginalAmount;
	}

}