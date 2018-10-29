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
 * The persistent class for the SWIFT_MT700 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT700", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt700.findAll", query="SELECT s FROM SwiftMt700 s")
public class SwiftMt700 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt700PK id;

	@Column(name="ADDITIONAL_AMOUNTS_COVERED")
	private String additionalAmountsCovered;

	@Column(name="ADDITIONAL_CONDITIONS")
	private String additionalConditions;

	@Column(name="ADVISE_THROUGH_BANK")
	private String adviseThroughBank;

	@Column(name="APPLICABLE_RULES")
	private String applicableRules;

	@Column(name="APPLICANT")
	private String applicant;

	@Column(name="APPLICANT_BANK")
	private String applicantBank;

	@Column(name="AVAILABLE_WITH_BY")
	private String availableWithBy;

	@Column(name="BENEFICIARY")
	private String beneficiary;

	@Column(name="CHARGES")
	private String charges;

	@Column(name="CONFIRMATION_INSTRUCTIONS")
	private String confirmationInstructions;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="CURRENCY_CODE_AMOUNT")
	private String currencyCodeAmount;

	@Column(name="DATE_AND_PLACE_OF_EXPIRY")
	private String dateAndPlaceOfExpiry;

	@Column(name="DATE_OF_ISSUE")
	private String dateOfIssue;

	@Column(name="DESCRIPTION_OF_GOODS_AND_OR_SERVICES")
	private String descriptionOfGoodsAndOrServices;

	@Column(name="DOCUMENTARY_CREDIT_NUMBER")
	private String documentaryCreditNumber;

	@Column(name="DOCUMENTS_REQUIRED")
	private String documentsRequired;

	@Column(name="DRAFTS_AT")
	private String draftsAt;

	@Column(name="DRAWEE")
	private String drawee;

	@Column(name="FORM_OF_DOCUMENTARY_CREDIT")
	private String formOfDocumentaryCredit;

	@Column(name="INSTRUCTIONS_TO_THE_PAYING_ACCEPTING_NEGOTIATING_BANK")
	private String instructionsToThePayingAcceptingNegotiatingBank;

	@Column(name="LATEST_DATE_OF_SHIPMENT")
	private String latestDateOfShipment;

	@Column(name="MAXIMUM_CREDIT_AMOUNT")
	private String maximumCreditAmount;

	@Column(name="MIXED_PAYMENT_DETAILS")
	private String mixedPaymentDetails;

	@Column(name="NEGOTIATION_DEFERRED_PAYMENT_DETAILS")
	private String negotiationDeferredPaymentDetails;

	@Column(name="PARTIAL_SHIPMENTS")
	private String partialShipments;

	@Column(name="PERCENTAGE_CREDIT_AMOUNT_TOLERANCE")
	private String percentageCreditAmountTolerance;

	@Column(name="PERIOD_FOR_PRESENTATION")
	private String periodForPresentation;

	@Column(name="PLACE_OF_FINAL_DESTINATION_FOR_TRANSPORTATION_TO_PLACE_OF_DELIVERY")
	private String placeOfFinalDestinationForTransportationToPlaceOfDelivery;

	@Column(name="PLACE_OF_TAKING_IN_CHARGE_DISPATCH_FROM_PLACE_OF_RECEIPT")
	private String placeOfTakingInChargeDispatchFromPlaceOfReceipt;

	@Column(name="PORT_OF_DISCHARGE_AIRPORT_OF_DESTINATION")
	private String portOfDischargeAirportOfDestination;

	@Column(name="PORT_OF_LOADING_AIRPORT_OF_DEPARTURE")
	private String portOfLoadingAirportOfDeparture;

	@Column(name="REFERENCE_TO_PRE_ADVICE")
	private String referenceToPreAdvice;

	@Column(name="REIMBURSING_BANK")
	private String reimbursingBank;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SEQUENCE_OF_TOTAL")
	private String sequenceOfTotal;

	@Column(name="SHIPMENT_PERIOD")
	private String shipmentPeriod;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSHIPMENT")
	private String transhipment;

	public SwiftMt700() {
	}

	public SwiftMt700PK getId() {
		return this.id;
	}

	public void setId(SwiftMt700PK id) {
		this.id = id;
	}

	public String getAdditionalAmountsCovered() {
		return this.additionalAmountsCovered;
	}

	public void setAdditionalAmountsCovered(String additionalAmountsCovered) {
		this.additionalAmountsCovered = additionalAmountsCovered;
	}

	public String getAdditionalConditions() {
		return this.additionalConditions;
	}

	public void setAdditionalConditions(String additionalConditions) {
		this.additionalConditions = additionalConditions;
	}

	public String getAdviseThroughBank() {
		return this.adviseThroughBank;
	}

	public void setAdviseThroughBank(String adviseThroughBank) {
		this.adviseThroughBank = adviseThroughBank;
	}

	public String getApplicableRules() {
		return this.applicableRules;
	}

	public void setApplicableRules(String applicableRules) {
		this.applicableRules = applicableRules;
	}

	public String getApplicant() {
		return this.applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplicantBank() {
		return this.applicantBank;
	}

	public void setApplicantBank(String applicantBank) {
		this.applicantBank = applicantBank;
	}

	public String getAvailableWithBy() {
		return this.availableWithBy;
	}

	public void setAvailableWithBy(String availableWithBy) {
		this.availableWithBy = availableWithBy;
	}

	public String getBeneficiary() {
		return this.beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getCharges() {
		return this.charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public String getConfirmationInstructions() {
		return this.confirmationInstructions;
	}

	public void setConfirmationInstructions(String confirmationInstructions) {
		this.confirmationInstructions = confirmationInstructions;
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

	public String getCurrencyCodeAmount() {
		return this.currencyCodeAmount;
	}

	public void setCurrencyCodeAmount(String currencyCodeAmount) {
		this.currencyCodeAmount = currencyCodeAmount;
	}

	public String getDateAndPlaceOfExpiry() {
		return this.dateAndPlaceOfExpiry;
	}

	public void setDateAndPlaceOfExpiry(String dateAndPlaceOfExpiry) {
		this.dateAndPlaceOfExpiry = dateAndPlaceOfExpiry;
	}

	public String getDateOfIssue() {
		return this.dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getDescriptionOfGoodsAndOrServices() {
		return this.descriptionOfGoodsAndOrServices;
	}

	public void setDescriptionOfGoodsAndOrServices(String descriptionOfGoodsAndOrServices) {
		this.descriptionOfGoodsAndOrServices = descriptionOfGoodsAndOrServices;
	}

	public String getDocumentaryCreditNumber() {
		return this.documentaryCreditNumber;
	}

	public void setDocumentaryCreditNumber(String documentaryCreditNumber) {
		this.documentaryCreditNumber = documentaryCreditNumber;
	}

	public String getDocumentsRequired() {
		return this.documentsRequired;
	}

	public void setDocumentsRequired(String documentsRequired) {
		this.documentsRequired = documentsRequired;
	}

	public String getDraftsAt() {
		return this.draftsAt;
	}

	public void setDraftsAt(String draftsAt) {
		this.draftsAt = draftsAt;
	}

	public String getDrawee() {
		return this.drawee;
	}

	public void setDrawee(String drawee) {
		this.drawee = drawee;
	}

	public String getFormOfDocumentaryCredit() {
		return this.formOfDocumentaryCredit;
	}

	public void setFormOfDocumentaryCredit(String formOfDocumentaryCredit) {
		this.formOfDocumentaryCredit = formOfDocumentaryCredit;
	}

	public String getInstructionsToThePayingAcceptingNegotiatingBank() {
		return this.instructionsToThePayingAcceptingNegotiatingBank;
	}

	public void setInstructionsToThePayingAcceptingNegotiatingBank(String instructionsToThePayingAcceptingNegotiatingBank) {
		this.instructionsToThePayingAcceptingNegotiatingBank = instructionsToThePayingAcceptingNegotiatingBank;
	}

	public String getLatestDateOfShipment() {
		return this.latestDateOfShipment;
	}

	public void setLatestDateOfShipment(String latestDateOfShipment) {
		this.latestDateOfShipment = latestDateOfShipment;
	}

	public String getMaximumCreditAmount() {
		return this.maximumCreditAmount;
	}

	public void setMaximumCreditAmount(String maximumCreditAmount) {
		this.maximumCreditAmount = maximumCreditAmount;
	}

	public String getMixedPaymentDetails() {
		return this.mixedPaymentDetails;
	}

	public void setMixedPaymentDetails(String mixedPaymentDetails) {
		this.mixedPaymentDetails = mixedPaymentDetails;
	}

	public String getNegotiationDeferredPaymentDetails() {
		return this.negotiationDeferredPaymentDetails;
	}

	public void setNegotiationDeferredPaymentDetails(String negotiationDeferredPaymentDetails) {
		this.negotiationDeferredPaymentDetails = negotiationDeferredPaymentDetails;
	}

	public String getPartialShipments() {
		return this.partialShipments;
	}

	public void setPartialShipments(String partialShipments) {
		this.partialShipments = partialShipments;
	}

	public String getPercentageCreditAmountTolerance() {
		return this.percentageCreditAmountTolerance;
	}

	public void setPercentageCreditAmountTolerance(String percentageCreditAmountTolerance) {
		this.percentageCreditAmountTolerance = percentageCreditAmountTolerance;
	}

	public String getPeriodForPresentation() {
		return this.periodForPresentation;
	}

	public void setPeriodForPresentation(String periodForPresentation) {
		this.periodForPresentation = periodForPresentation;
	}

	public String getPlaceOfFinalDestinationForTransportationToPlaceOfDelivery() {
		return this.placeOfFinalDestinationForTransportationToPlaceOfDelivery;
	}

	public void setPlaceOfFinalDestinationForTransportationToPlaceOfDelivery(String placeOfFinalDestinationForTransportationToPlaceOfDelivery) {
		this.placeOfFinalDestinationForTransportationToPlaceOfDelivery = placeOfFinalDestinationForTransportationToPlaceOfDelivery;
	}

	public String getPlaceOfTakingInChargeDispatchFromPlaceOfReceipt() {
		return this.placeOfTakingInChargeDispatchFromPlaceOfReceipt;
	}

	public void setPlaceOfTakingInChargeDispatchFromPlaceOfReceipt(String placeOfTakingInChargeDispatchFromPlaceOfReceipt) {
		this.placeOfTakingInChargeDispatchFromPlaceOfReceipt = placeOfTakingInChargeDispatchFromPlaceOfReceipt;
	}

	public String getPortOfDischargeAirportOfDestination() {
		return this.portOfDischargeAirportOfDestination;
	}

	public void setPortOfDischargeAirportOfDestination(String portOfDischargeAirportOfDestination) {
		this.portOfDischargeAirportOfDestination = portOfDischargeAirportOfDestination;
	}

	public String getPortOfLoadingAirportOfDeparture() {
		return this.portOfLoadingAirportOfDeparture;
	}

	public void setPortOfLoadingAirportOfDeparture(String portOfLoadingAirportOfDeparture) {
		this.portOfLoadingAirportOfDeparture = portOfLoadingAirportOfDeparture;
	}

	public String getReferenceToPreAdvice() {
		return this.referenceToPreAdvice;
	}

	public void setReferenceToPreAdvice(String referenceToPreAdvice) {
		this.referenceToPreAdvice = referenceToPreAdvice;
	}

	public String getReimbursingBank() {
		return this.reimbursingBank;
	}

	public void setReimbursingBank(String reimbursingBank) {
		this.reimbursingBank = reimbursingBank;
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

	public String getShipmentPeriod() {
		return this.shipmentPeriod;
	}

	public void setShipmentPeriod(String shipmentPeriod) {
		this.shipmentPeriod = shipmentPeriod;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTranshipment() {
		return this.transhipment;
	}

	public void setTranshipment(String transhipment) {
		this.transhipment = transhipment;
	}

}