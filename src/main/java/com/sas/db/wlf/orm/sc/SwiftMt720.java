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
 * The persistent class for the SWIFT_MT720 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT720", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt720.findAll", query="SELECT s FROM SwiftMt720 s")
public class SwiftMt720 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt720PK id;

	@Column(name="ADDITIONAL_AMOUNTS_COVERED")
	private String additionalAmountsCovered;

	@Column(name="ADDITIONAL_CONDITIONS")
	private String additionalConditions;

	@Column(name="ADIVSE_THROUGH_BANK")
	private String adivseThroughBank;

	@Column(name="APPLICABLE_RULES")
	private String applicableRules;

	@Column(name="AVAILABLE_WITH_BY")
	private String availableWithBy;

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

	@Column(name="DEFERRED_PAYMENT_DETAILS")
	private String deferredPaymentDetails;

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

	@Column(name="FIRST_BENEFICIARY")
	private String firstBeneficiary;

	@Column(name="FORM_OF_DOCUMENTARY_CREDIT")
	private String formOfDocumentaryCredit;

	@Column(name="INSTRUCTIONS_TO_THE_PAYING_ACCEPTING_NEGOTIATING_BANK")
	private String instructionsToThePayingAcceptingNegotiatingBank;

	@Column(name="ISSUING_BANK_OF_THE_ORIGINAL_DOCUMENTARY_CREDIT")
	private String issuingBankOfTheOriginalDocumentaryCredit;

	@Column(name="LATEST_DATE_OF_SHIPMENT")
	private String latestDateOfShipment;

	@Column(name="MAXIMUM_CREDIT_AMOUNT")
	private String maximumCreditAmount;

	@Column(name="MIXED_PAYMENT_DETAILS")
	private String mixedPaymentDetails;

	@Column(name="NON_BANK_ISSUER_OF_THE_ORIGINAL_DOCUMENTARY_CREDIT")
	private String nonBankIssuerOfTheOriginalDocumentaryCredit;

	@Column(name="PARTIAL_SHIPMENTS")
	private String partialShipments;

	@Column(name="PERCENTAGE_CREDIT_AMOUNT_TOLERANCE")
	private String percentageCreditAmountTolerance;

	@Column(name="PERIOD_FOR_PRESENTATION_IN_DAYS")
	private String periodForPresentationInDays;

	@Column(name="PLACE_OF_FINAL_DESTINATION_FOR_TRANSPORTATION_TO_PLACE_OF_DELIVERY")
	private String placeOfFinalDestinationForTransportationToPlaceOfDelivery;

	@Column(name="PLACE_OF_TAKING_IN_CHARGE_DISPATCH_FROM_PLACE_OF_RECEIPT")
	private String placeOfTakingInChargeDispatchFromPlaceOfReceipt;

	@Column(name="PORT_OF_DISCHARGE_AIRPORT_OF_DESTINATION")
	private String portOfDischargeAirportOfDestination;

	@Column(name="PORT_OF_LOADING_AIRPORT_OF_DEPARTURE")
	private String portOfLoadingAirportOfDeparture;

	@Column(name="SECOND_BENEFICIARY")
	private String secondBeneficiary;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SEQUENCE_OF_TOTAL")
	private String sequenceOfTotal;

	@Column(name="SHIPMENT_PERIOD")
	private String shipmentPeriod;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSFERRING_BANKS_REFERENCE")
	private String transferringBanksReference;

	@Column(name="TRANSHIPMENT")
	private String transhipment;

	@Column(name="SPECIAL_PAYMENT_CONDITIONS_FOR_BENEFICIARY")
	private String specialPaymentConditionsForBeneficiary;
	
	@Column(name="SPECIAL_PAYMENT_CONDITIONS_FOR_RECEIVING_BANK")
	private String specialPaymentConditionsForReceivingBank;
	
	@Column(name="REQUESTED_CONFIRMATION_PARTY")
	private String requestedConfirmationParty;
	
	public SwiftMt720() {
	}

	public SwiftMt720PK getId() {
		return this.id;
	}

	public void setId(SwiftMt720PK id) {
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

	public String getAdivseThroughBank() {
		return this.adivseThroughBank;
	}

	public void setAdivseThroughBank(String adivseThroughBank) {
		this.adivseThroughBank = adivseThroughBank;
	}

	public String getApplicableRules() {
		return this.applicableRules;
	}

	public void setApplicableRules(String applicableRules) {
		this.applicableRules = applicableRules;
	}

	public String getAvailableWithBy() {
		return this.availableWithBy;
	}

	public void setAvailableWithBy(String availableWithBy) {
		this.availableWithBy = availableWithBy;
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

	public String getDeferredPaymentDetails() {
		return this.deferredPaymentDetails;
	}

	public void setDeferredPaymentDetails(String deferredPaymentDetails) {
		this.deferredPaymentDetails = deferredPaymentDetails;
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

	public String getFirstBeneficiary() {
		return this.firstBeneficiary;
	}

	public void setFirstBeneficiary(String firstBeneficiary) {
		this.firstBeneficiary = firstBeneficiary;
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

	public String getIssuingBankOfTheOriginalDocumentaryCredit() {
		return this.issuingBankOfTheOriginalDocumentaryCredit;
	}

	public void setIssuingBankOfTheOriginalDocumentaryCredit(String issuingBankOfTheOriginalDocumentaryCredit) {
		this.issuingBankOfTheOriginalDocumentaryCredit = issuingBankOfTheOriginalDocumentaryCredit;
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

	public String getNonBankIssuerOfTheOriginalDocumentaryCredit() {
		return this.nonBankIssuerOfTheOriginalDocumentaryCredit;
	}

	public void setNonBankIssuerOfTheOriginalDocumentaryCredit(String nonBankIssuerOfTheOriginalDocumentaryCredit) {
		this.nonBankIssuerOfTheOriginalDocumentaryCredit = nonBankIssuerOfTheOriginalDocumentaryCredit;
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

	public String getPeriodForPresentationInDays() {
		return this.periodForPresentationInDays;
	}

	public void setPeriodForPresentationInDays(String periodForPresentationInDays) {
		this.periodForPresentationInDays = periodForPresentationInDays;
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

	public String getSecondBeneficiary() {
		return this.secondBeneficiary;
	}

	public void setSecondBeneficiary(String secondBeneficiary) {
		this.secondBeneficiary = secondBeneficiary;
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

	public String getTransferringBanksReference() {
		return this.transferringBanksReference;
	}

	public void setTransferringBanksReference(String transferringBanksReference) {
		this.transferringBanksReference = transferringBanksReference;
	}

	public String getTranshipment() {
		return this.transhipment;
	}

	public void setTranshipment(String transhipment) {
		this.transhipment = transhipment;
	}

	public String getSpecialPaymentConditionsForBeneficiary() {
		return specialPaymentConditionsForBeneficiary;
	}

	public void setSpecialPaymentConditionsForBeneficiary(String specialPaymentConditionsForBeneficiary) {
		this.specialPaymentConditionsForBeneficiary = specialPaymentConditionsForBeneficiary;
	}

	public String getSpecialPaymentConditionsForReceivingBank() {
		return specialPaymentConditionsForReceivingBank;
	}

	public void setSpecialPaymentConditionsForReceivingBank(String specialPaymentConditionsForReceivingBank) {
		this.specialPaymentConditionsForReceivingBank = specialPaymentConditionsForReceivingBank;
	}

	public String getRequestedConfirmationParty() {
		return requestedConfirmationParty;
	}

	public void setRequestedConfirmationParty(String requestedConfirmationParty) {
		this.requestedConfirmationParty = requestedConfirmationParty;
	}

}