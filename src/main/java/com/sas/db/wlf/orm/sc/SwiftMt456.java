package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT456 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT456", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt456.findAll", query="SELECT s FROM SwiftMt456 s")
public class SwiftMt456 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt456PK id;

	@Column(name="Account_Identification")
	private String accountIdentification;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_and_Face_Amount_of_Financial_Document")
	private String dateAndFaceAmountOfFinancialDocument;

	@Column(name="Details_of_Dishonoured_Item")
	private String detailsOfDishonouredItem;

	@Column(name="Fee")
	private String fee;

	@Column(name="Reason_for_Dishonour")
	private String reasonForDishonour;

	@Column(name="Related_Reference")
	private String relatedReference;

	@Column(name="Sender_of_Cash_Letter")
	private String senderOfCashLetter;

	@Column(name="Sender_to_Receiver_Information")
	private String senderToReceiverInformation;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Total_Amount_Debited")
	private String totalAmountDebited;

	@Column(name="Transaction_Reference_Number")
	private String transactionReferenceNumber;

	public SwiftMt456() {
	}

	public SwiftMt456PK getId() {
		return id;
	}

	public void setId(SwiftMt456PK id) {
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

	public String getDateAndFaceAmountOfFinancialDocument() {
		return dateAndFaceAmountOfFinancialDocument;
	}

	public void setDateAndFaceAmountOfFinancialDocument(
			String dateAndFaceAmountOfFinancialDocument) {
		this.dateAndFaceAmountOfFinancialDocument = dateAndFaceAmountOfFinancialDocument;
	}

	public String getDetailsOfDishonouredItem() {
		return detailsOfDishonouredItem;
	}

	public void setDetailsOfDishonouredItem(String detailsOfDishonouredItem) {
		this.detailsOfDishonouredItem = detailsOfDishonouredItem;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getReasonForDishonour() {
		return reasonForDishonour;
	}

	public void setReasonForDishonour(String reasonForDishonour) {
		this.reasonForDishonour = reasonForDishonour;
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

	public String getTotalAmountDebited() {
		return totalAmountDebited;
	}

	public void setTotalAmountDebited(String totalAmountDebited) {
		this.totalAmountDebited = totalAmountDebited;
	}

	public String getTransactionReferenceNumber() {
		return transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}
}