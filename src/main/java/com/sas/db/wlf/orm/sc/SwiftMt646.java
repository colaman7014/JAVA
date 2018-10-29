package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT646 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT646", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt646.findAll", query="SELECT s FROM SwiftMt646 s")
public class SwiftMt646 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt646PK id;

	@Column(name="ACCOUNT_WITH_INSTITUTION")
	private String accountWithInstitution;

	@Column(name="BORROWERS")
	private String borrowers;

	@Column(name="COMPUTATION_BASE_AMOUNT")
	private String computationBaseAmount;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DETAILS_OF_ADJUSTMENTS")
	private String detailsOfAdjustments;

	@Column(name="DRAWING_IDENTIFICATION")
	private String drawingIdentification;

	@Column(name="[FROM]")
	private String from;

	@Column(name="FURTHER_IDENTIFICATION")
	private String furtherIdentification;

	@Column(name="GROSS_INTEREST_AMOUNT_DUE_TO_RECEIVER")
	private String grossInterestAmountDueToReceiver;

	@Column(name="INTEREST_AMOUNT")
	private String interestAmount;

	@Column(name="INTEREST_PERIOD")
	private String interestPeriod;

	@Column(name="INTEREST_RATE")
	private String interestRate;

	@Column(name="NET_INTEREST_AMOUNT_DUE_TO_RECEIVER")
	private String netInterestAmountDueToReceiver;

	@Column(name="ORIGINAL_FACILITY_AMOUNT")
	private String originalFacilityAmount;

	@Column(name="PRINCIPAL_AMOUNT_DUE_TO_RECEIVER")
	private String principalAmountDueToReceiver;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TO_THE_ATTENTION_OF")
	private String toTheAttentionOf;

	@Column(name="TOTAL_AMOUNT_TRANSFERRED")
	private String totalAmountTransferred;

	@Column(name="TOTAL_PRINCIPAL_AMOUNT_REPAID_PREPAID")
	private String totalPrincipalAmountRepaidPrepaid;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	public SwiftMt646() {
	}

	public SwiftMt646PK getId() {
		return this.id;
	}

	public void setId(SwiftMt646PK id) {
		this.id = id;
	}

	public String getAccountWithInstitution() {
		return this.accountWithInstitution;
	}

	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
	}

	public String getBorrowers() {
		return this.borrowers;
	}

	public void setBorrowers(String borrowers) {
		this.borrowers = borrowers;
	}

	public String getComputationBaseAmount() {
		return this.computationBaseAmount;
	}

	public void setComputationBaseAmount(String computationBaseAmount) {
		this.computationBaseAmount = computationBaseAmount;
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

	public String getDetailsOfAdjustments() {
		return this.detailsOfAdjustments;
	}

	public void setDetailsOfAdjustments(String detailsOfAdjustments) {
		this.detailsOfAdjustments = detailsOfAdjustments;
	}

	public String getDrawingIdentification() {
		return this.drawingIdentification;
	}

	public void setDrawingIdentification(String drawingIdentification) {
		this.drawingIdentification = drawingIdentification;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFurtherIdentification() {
		return this.furtherIdentification;
	}

	public void setFurtherIdentification(String furtherIdentification) {
		this.furtherIdentification = furtherIdentification;
	}

	public String getGrossInterestAmountDueToReceiver() {
		return this.grossInterestAmountDueToReceiver;
	}

	public void setGrossInterestAmountDueToReceiver(String grossInterestAmountDueToReceiver) {
		this.grossInterestAmountDueToReceiver = grossInterestAmountDueToReceiver;
	}

	public String getInterestAmount() {
		return this.interestAmount;
	}

	public void setInterestAmount(String interestAmount) {
		this.interestAmount = interestAmount;
	}

	public String getInterestPeriod() {
		return this.interestPeriod;
	}

	public void setInterestPeriod(String interestPeriod) {
		this.interestPeriod = interestPeriod;
	}

	public String getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getNetInterestAmountDueToReceiver() {
		return this.netInterestAmountDueToReceiver;
	}

	public void setNetInterestAmountDueToReceiver(String netInterestAmountDueToReceiver) {
		this.netInterestAmountDueToReceiver = netInterestAmountDueToReceiver;
	}

	public String getOriginalFacilityAmount() {
		return this.originalFacilityAmount;
	}

	public void setOriginalFacilityAmount(String originalFacilityAmount) {
		this.originalFacilityAmount = originalFacilityAmount;
	}

	public String getPrincipalAmountDueToReceiver() {
		return this.principalAmountDueToReceiver;
	}

	public void setPrincipalAmountDueToReceiver(String principalAmountDueToReceiver) {
		this.principalAmountDueToReceiver = principalAmountDueToReceiver;
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

	public String getToTheAttentionOf() {
		return this.toTheAttentionOf;
	}

	public void setToTheAttentionOf(String toTheAttentionOf) {
		this.toTheAttentionOf = toTheAttentionOf;
	}

	public String getTotalAmountTransferred() {
		return this.totalAmountTransferred;
	}

	public void setTotalAmountTransferred(String totalAmountTransferred) {
		this.totalAmountTransferred = totalAmountTransferred;
	}

	public String getTotalPrincipalAmountRepaidPrepaid() {
		return this.totalPrincipalAmountRepaidPrepaid;
	}

	public void setTotalPrincipalAmountRepaidPrepaid(String totalPrincipalAmountRepaidPrepaid) {
		this.totalPrincipalAmountRepaidPrepaid = totalPrincipalAmountRepaidPrepaid;
	}

	public String getTransactionReferenceNumber() {
		return this.transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}

}