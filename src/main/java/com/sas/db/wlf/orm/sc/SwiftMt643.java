package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT643 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT643", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt643.findAll", query="SELECT s FROM SwiftMt643 s")
public class SwiftMt643 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt643PK id;

	@Column(name="ACCOUNT_WITH_INSTITUTION")
	private String accountWithInstitution;

	@Column(name="AMOUNT_OF_DRAWDOWN")
	private String amountOfDrawdown;

	@Column(name="[BORROWER(S)]")
	private String borrower_s_;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DETAILS_OF_ADJUSTMENTS")
	private String detailsOfAdjustments;

	@Column(name="DRAWDOWN_PERIOD")
	private String drawdownPeriod;

	@Column(name="DRAWING_IDENTIFICATION")
	private String drawingIdentification;

	@Column(name="[DRAWING_IDENTIFICATION_(NEW)]")
	private String drawingIdentification_new_;

	@Column(name="[FROM]")
	private String from;

	@Column(name="FURTHER_IDENTIFICATION")
	private String furtherIdentification;

	@Column(name="INTEREST_AMOUNT")
	private String interestAmount;

	@Column(name="NET_INTEREST_AMOUNT")
	private String netInterestAmount;

	@Column(name="ORIGINAL_FACILITY_AMOUNT")
	private String originalFacilityAmount;

	@Column(name="RATE_FIXING_DATE")
	private String rateFixingDate;

	@Column(name="RECEIVERS_PARTICIPATION")
	private String receiversParticipation;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="REPAYMENT_OF_PRINCIPAL")
	private String repaymentOfPrincipal;

	@Column(name="SELECTED_CURRENCY")
	private String selectedCurrency;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SEQUENCE_OF_TOTAL")
	private String sequenceOfTotal;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TO_THE_ATTENTION_OF")
	private String toTheAttentionOf;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	public SwiftMt643() {
	}

	public SwiftMt643PK getId() {
		return this.id;
	}

	public void setId(SwiftMt643PK id) {
		this.id = id;
	}

	public String getAccountWithInstitution() {
		return this.accountWithInstitution;
	}

	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
	}

	public String getAmountOfDrawdown() {
		return this.amountOfDrawdown;
	}

	public void setAmountOfDrawdown(String amountOfDrawdown) {
		this.amountOfDrawdown = amountOfDrawdown;
	}

	public String getBorrower_s_() {
		return this.borrower_s_;
	}

	public void setBorrower_s_(String borrower_s_) {
		this.borrower_s_ = borrower_s_;
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

	public String getDrawdownPeriod() {
		return this.drawdownPeriod;
	}

	public void setDrawdownPeriod(String drawdownPeriod) {
		this.drawdownPeriod = drawdownPeriod;
	}

	public String getDrawingIdentification() {
		return this.drawingIdentification;
	}

	public void setDrawingIdentification(String drawingIdentification) {
		this.drawingIdentification = drawingIdentification;
	}

	public String getDrawingIdentification_new_() {
		return this.drawingIdentification_new_;
	}

	public void setDrawingIdentification_new_(String drawingIdentification_new_) {
		this.drawingIdentification_new_ = drawingIdentification_new_;
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

	public String getInterestAmount() {
		return this.interestAmount;
	}

	public void setInterestAmount(String interestAmount) {
		this.interestAmount = interestAmount;
	}

	public String getNetInterestAmount() {
		return this.netInterestAmount;
	}

	public void setNetInterestAmount(String netInterestAmount) {
		this.netInterestAmount = netInterestAmount;
	}

	public String getOriginalFacilityAmount() {
		return this.originalFacilityAmount;
	}

	public void setOriginalFacilityAmount(String originalFacilityAmount) {
		this.originalFacilityAmount = originalFacilityAmount;
	}

	public String getRateFixingDate() {
		return this.rateFixingDate;
	}

	public void setRateFixingDate(String rateFixingDate) {
		this.rateFixingDate = rateFixingDate;
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

	public String getRepaymentOfPrincipal() {
		return this.repaymentOfPrincipal;
	}

	public void setRepaymentOfPrincipal(String repaymentOfPrincipal) {
		this.repaymentOfPrincipal = repaymentOfPrincipal;
	}

	public String getSelectedCurrency() {
		return this.selectedCurrency;
	}

	public void setSelectedCurrency(String selectedCurrency) {
		this.selectedCurrency = selectedCurrency;
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

	public String getTransactionReferenceNumber() {
		return this.transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}

}