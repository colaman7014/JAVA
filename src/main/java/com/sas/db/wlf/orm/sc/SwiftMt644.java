package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT644 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT644", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt644.findAll", query="SELECT s FROM SwiftMt644 s")
public class SwiftMt644 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt644PK id;

	@Column(name="ACCOUNT_WITH_INSTITUTION")
	private String accountWithInstitution;

	@Column(name="BASIS_RATE")
	private String basisRate;

	@Column(name="BORROWERS")
	private String borrowers;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DIFFERENTIAL_AMOUNT")
	private String differentialAmount;

	@Column(name="DRAWING_IDENTIFICATION")
	private String drawingIdentification;

	@Column(name="EXCHANGE_RATE")
	private String exchangeRate;

	@Column(name="[FROM]")
	private String from;

	@Column(name="INTEREST_MARGIN")
	private String interestMargin;

	@Column(name="INTEREST_PERIOD")
	private String interestPeriod;

	@Column(name="ORIGINAL_FACILITY_AMOUNT")
	private String originalFacilityAmount;

	@Column(name="RECEIVERS_PARTICIPATION")
	private String receiversParticipation;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="RESERVE_INTEREST")
	private String reserveInterest;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SEQUENCE_OF_TOTAL")
	private String sequenceOfTotal;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TO_THE_ATTENTION_OF")
	private String toTheAttentionOf;

	@Column(name="TOTAL_INTEREST_RATE")
	private String totalInterestRate;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	public SwiftMt644() {
	}

	public SwiftMt644PK getId() {
		return this.id;
	}

	public void setId(SwiftMt644PK id) {
		this.id = id;
	}

	public String getAccountWithInstitution() {
		return this.accountWithInstitution;
	}

	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
	}

	public String getBasisRate() {
		return this.basisRate;
	}

	public void setBasisRate(String basisRate) {
		this.basisRate = basisRate;
	}

	public String getBorrowers() {
		return this.borrowers;
	}

	public void setBorrowers(String borrowers) {
		this.borrowers = borrowers;
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

	public String getDifferentialAmount() {
		return this.differentialAmount;
	}

	public void setDifferentialAmount(String differentialAmount) {
		this.differentialAmount = differentialAmount;
	}

	public String getDrawingIdentification() {
		return this.drawingIdentification;
	}

	public void setDrawingIdentification(String drawingIdentification) {
		this.drawingIdentification = drawingIdentification;
	}

	public String getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getInterestMargin() {
		return this.interestMargin;
	}

	public void setInterestMargin(String interestMargin) {
		this.interestMargin = interestMargin;
	}

	public String getInterestPeriod() {
		return this.interestPeriod;
	}

	public void setInterestPeriod(String interestPeriod) {
		this.interestPeriod = interestPeriod;
	}

	public String getOriginalFacilityAmount() {
		return this.originalFacilityAmount;
	}

	public void setOriginalFacilityAmount(String originalFacilityAmount) {
		this.originalFacilityAmount = originalFacilityAmount;
	}

	public String getReceiversParticipation() {
		return this.receiversParticipation;
	}

	public void setReceiversParticipation(String receiversParticipation) {
		this.receiversParticipation = receiversParticipation;
	}

	public String getRelatedReference() {
		return this.relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}

	public String getReserveInterest() {
		return this.reserveInterest;
	}

	public void setReserveInterest(String reserveInterest) {
		this.reserveInterest = reserveInterest;
	}

	public String getSenderToReceiverInformation() {
		return this.senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSequenceOfTotal() {
		return this.sequenceOfTotal;
	}

	public void setSequenceOfTotal(String sequenceOfTotal) {
		this.sequenceOfTotal = sequenceOfTotal;
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

	public String getTotalInterestRate() {
		return this.totalInterestRate;
	}

	public void setTotalInterestRate(String totalInterestRate) {
		this.totalInterestRate = totalInterestRate;
	}

	public String getTransactionReferenceNumber() {
		return this.transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}

}