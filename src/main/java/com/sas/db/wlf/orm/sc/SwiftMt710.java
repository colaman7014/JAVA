package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT710 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT710", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt710.findAll", query="SELECT s FROM SwiftMt710 s")
public class SwiftMt710 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt710PK id;

	@Column(name="Additional_Amounts_Covered")
	private String additionalAmountsCovered;

	@Column(name="Additional_Conditions")
	private String additionalConditions;

	@Column(name="Adivse_Through_Bank")
	private String adivseThroughBank;

	@Column(name="Applicable_Rules")
	private String applicableRules;

	@Column(name="Applicant")
	private String applicant;

	@Column(name="Applicant_Bank")
	private String applicantBank;

	@Column(name="Available_With_By")
	private String availableWithBy;

	@Column(name="Beneficiary")
	private String beneficiary;

	@Column(name="Charges")
	private String charges;

	@Column(name="Confirmation_Instructions")
	private String confirmationInstructions;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Currency_Code_Amount")
	private String currencyCodeAmount;

	@Column(name="Date_and_Place_of_Expiry")
	private String dateAndPlaceOfExpiry;

	@Column(name="Date_of_Issue")
	private String dateOfIssue;

	@Column(name="Deferred_Payment_Details")
	private String deferredPaymentDetails;

	@Column(name="Description_of_Goods_and_or_Services")
	private String descriptionOfGoodsAndOrServices;

	@Column(name="Documentary_Credit_Number")
	private String documentaryCreditNumber;

	@Column(name="Documents_Required")
	private String documentsRequired;

	@Column(name="Drafts_at")
	private String draftsAt;

	@Column(name="Drawee")
	private String drawee;

	@Column(name="Form_of_Documentary_Credit")
	private String formOfDocumentaryCredit;

	@Column(name="Issuing_Bank")
	private String issuingBank;

	@Column(name="Latest_Date_of_Shipment")
	private String latestDateOfShipment;

	@Column(name="Maximum_Credit_Amount")
	private String maximumCreditAmount;

	@Column(name="Mixed_Payment_Details")
	private String mixedPaymentDetails;

	@Column(name="Non_Bank_Issuer")
	private String nonBankIssuer;

	@Column(name="Partial_Shipments")
	private String partialShipments;

	@Column(name="Percentage_Credit_Amount_Tolerance")
	private String percentageCreditAmountTolerance;

	@Column(name="Period_for_Presentation")
	private String periodForPresentation;

	@Column(name="Port_of_Discharge_Airport_of_Destination")
	private String portOfDischargeAirportOfDestination;

	@Column(name="Port_of_Loading_Airport_of_Departure")
	private String portOfLoadingAirportOfDeparture;

	@Column(name="Reimbursing_Bank")
	private String reimbursingBank;

	@Column(name="Sender_to_Receiver_Information")
	private String senderToReceiverInformation;

	@Column(name="Sequence_of_Total")
	private String sequenceOfTotal;

	@Column(name="Shipment_Period")
	private String shipmentPeriod;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transhipment")
	private String transhipment;
	
	@Column(name="REFERENCE_TO_PRE_ADVICE")
	private String referenceToPreAdvice;
	
	@Column(name="INSTRUCTIONS_TO_THE_PAYING_ACCEPTING_NEGOTIATING_BANK")
	private String instructionsToThePayingAcceptingNegotiatingBank;
	
	@Column(name="PLACE_OF_TAKING_IN_CHARGE_DISPATCH_FROM_PLACE_OF_RECEIPT")
	private String placeOfTakingInChargeDispatchFromPlaceOfReceipt;
	
	@Column(name="PLACE_OF_FINAL_DESTINATION_FOR_TRANSPORTATION_TO_PLACE_OF_DELIVERY")
	private String placeOfFinalDestinationForTransportationToPlaceOfDelivery;
	
	@Column(name="SENDERS_REFERENCE")
	private String sendersReference;
	
	@Column(name="SPECIAL_PAYMENT_CONDITIONS_FOR_BENEFICIARY")
	private String specialPaymentConditionsForBeneficiary;
	
	@Column(name="SPECIAL_PAYMENT_CONDITIONS_FOR_RECEIVING_BANK")
	private String specialPaymentConditionsForReceivingBank;
	
	@Column(name="REQUESTED_CONFIRMATION_PARTY")
	private String requestedConfirmationParty;

	public SwiftMt710() {
	}

	public SwiftMt710PK getId() {
		return this.id;
	}

	public void setId(SwiftMt710PK id) {
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

	public String getDateandPlaceOfExpiry() {
		return this.dateAndPlaceOfExpiry;
	}

	public void setDateandPlaceOfExpiry(String dateandPlaceOfExpiry) {
		this.dateAndPlaceOfExpiry = dateandPlaceOfExpiry;
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

//	public String getDescriptionOfGoodsAndOrServices() {
//		return this.descriptionOfGoodsAndOrServices;
//	}
//
//	public void setDescriptionOfGoodsAndOrServices(String descriptionOfGoodsAndOrServices) {
//		this.descriptionOfGoodsAndOrServices = descriptionOfGoodsAndOrServices;
//	}

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
	
	

	public String getDescriptionOfGoodsAndOrServices() {
		return descriptionOfGoodsAndOrServices;
	}

	public void setDescriptionOfGoodsAndOrServices(String descriptionOfGoodsAndOrServices) {
		this.descriptionOfGoodsAndOrServices = descriptionOfGoodsAndOrServices;
	}

	public String getFormofDocumentaryCredit() {
		return this.formOfDocumentaryCredit;
	}

	public void setFormofDocumentaryCredit(String formofDocumentaryCredit) {
		this.formOfDocumentaryCredit = formofDocumentaryCredit;
	}

	public String getIssuingBank() {
		return this.issuingBank;
	}

	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
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

	public String getNonBankIssuer() {
		return this.nonBankIssuer;
	}

	public void setNonBankIssuer(String nonBankIssuer) {
		this.nonBankIssuer = nonBankIssuer;
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

	public String getReferenceToPreAdvice() {
		return referenceToPreAdvice;
	}

	public void setReferenceToPreAdvice(String referenceToPreAdvice) {
		this.referenceToPreAdvice = referenceToPreAdvice;
	}

	public String getInstructionsToThePayingAcceptingNegotiatingBank() {
		return instructionsToThePayingAcceptingNegotiatingBank;
	}

	public void setInstructionsToThePayingAcceptingNegotiatingBank(
			String instructionsToThePayingAcceptingNegotiatingBank) {
		this.instructionsToThePayingAcceptingNegotiatingBank = instructionsToThePayingAcceptingNegotiatingBank;
	}

	public String getPlaceOfTakingInChargeDispatchFromPlaceOfReceipt() {
		return placeOfTakingInChargeDispatchFromPlaceOfReceipt;
	}

	public void setPlaceOfTakingInChargeDispatchFromPlaceOfReceipt(
			String placeOfTakingInChargeDispatchFromPlaceOfReceipt) {
		this.placeOfTakingInChargeDispatchFromPlaceOfReceipt = placeOfTakingInChargeDispatchFromPlaceOfReceipt;
	}

	public String getPlaceOfFinalDestinationForTransportationToPlaceOfDelivery() {
		return placeOfFinalDestinationForTransportationToPlaceOfDelivery;
	}

	public void setPlaceOfFinalDestinationForTransportationToPlaceOfDelivery(
			String placeOfFinalDestinationForTransportationToPlaceOfDelivery) {
		this.placeOfFinalDestinationForTransportationToPlaceOfDelivery = placeOfFinalDestinationForTransportationToPlaceOfDelivery;
	}

	public String getSendersReference() {
		return sendersReference;
	}

	public void setSendersReference(String sendersReference) {
		this.sendersReference = sendersReference;
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
