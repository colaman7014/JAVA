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
 * The persistent class for the SWIFT_MT707 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT707", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt707.findAll", query="SELECT s FROM SwiftMt707 s")
public class SwiftMt707 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt707PK id;

	@Column(name="ADDITIONAL_AMOUNTS_COVERED")
	private String additionalAmountsCovered;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DATE_OF_AMENDMENT")
	private String dateOfAmendment;

	@Column(name="DATE_OF_ISSUE")
	private String dateOfIssue;

	@Column(name="DECREASE_OF_DOCUMENTARY_CREDIT_AMOUNT")
	private String decreaseOfDocumentaryCreditAmount;

	@Column(name="INCREASE_OF_DOCUMENTARY_CREDIT_AMOUNT")
	private String increaseOfDocumentaryCreditAmount;

	@Column(name="ISSUING_BANK")
	private String issuingBank;

	@Column(name="ISSUING_BANKS_REFERENCE")
	private String issuingBanksReference;

	@Column(name="LATEST_DATE_OF_SHIPMENT")
	private String latestDateOfShipment;

	@Column(name="MAXIMUM_CREDIT_AMOUNT")
	private String maximumCreditAmount;

	@Column(name="NARRATIVE")
	private String narrative;

	@Column(name="NEW_BENEFICIARY")
	private String newBeneficiary;

	@Column(name="NEW_DATE_OF_EXPIRY")
	private String newDateOfExpiry;

	@Column(name="NEW_DOCUMENTARY_CREDIT_AMOUNT_AFTER_AMENDMENT")
	private String newDocumentaryCreditAmountAfterAmendment;

	@Column(name="NUMBER_OF_AMENDMENT")
	private String numberOfAmendment;

	@Column(name="PERCENTAGE_CREDIT_AMOUNT_TOLERANCE")
	private String percentageCreditAmountTolerance;

	@Column(name="PLACE_OF_FINAL_DESTINATION_FOR_TRANSPORTATION_TO_PLACE_OF_DELIVERY")
	private String placeOfFinalDestinationForTransportationToPlaceOfDelivery;

	@Column(name="PLACE_OF_TAKING_IN_CHARGE_DISPATCH_FROM_PLACE_OF_RECEIPT")
	private String placeOfTakingInChargeDispatchFromPlaceOfReceipt;

	@Column(name="PORT_OF_DISCHARGE_AIRPORT_OF_DESTINATION")
	private String portOfDischargeAirportOfDestination;

	@Column(name="PORT_OF_LOADING_AIRPORT_OF_DEPARTURE")
	private String portOfLoadingAirportOfDeparture;

	@Column(name="RECEIVERS_REFERENCE")
	private String receiversReference;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDERS_REFERENCE")
	private String sendersReference;

	@Column(name="SHIPMENT_PERIOD")
	private String shipmentPeriod;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt707() {
	}

	public SwiftMt707PK getId() {
		return this.id;
	}

	public void setId(SwiftMt707PK id) {
		this.id = id;
	}

	public String getAdditionalAmountsCovered() {
		return this.additionalAmountsCovered;
	}

	public void setAdditionalAmountsCovered(String additionalAmountsCovered) {
		this.additionalAmountsCovered = additionalAmountsCovered;
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

	public String getDateOfAmendment() {
		return this.dateOfAmendment;
	}

	public void setDateOfAmendment(String dateOfAmendment) {
		this.dateOfAmendment = dateOfAmendment;
	}

	public String getDateOfIssue() {
		return this.dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getDecreaseOfDocumentaryCreditAmount() {
		return this.decreaseOfDocumentaryCreditAmount;
	}

	public void setDecreaseOfDocumentaryCreditAmount(String decreaseOfDocumentaryCreditAmount) {
		this.decreaseOfDocumentaryCreditAmount = decreaseOfDocumentaryCreditAmount;
	}

	public String getIncreaseOfDocumentaryCreditAmount() {
		return this.increaseOfDocumentaryCreditAmount;
	}

	public void setIncreaseOfDocumentaryCreditAmount(String increaseOfDocumentaryCreditAmount) {
		this.increaseOfDocumentaryCreditAmount = increaseOfDocumentaryCreditAmount;
	}

	public String getIssuingBank() {
		return this.issuingBank;
	}

	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}

	public String getIssuingBanksReference() {
		return this.issuingBanksReference;
	}

	public void setIssuingBanksReference(String issuingBanksReference) {
		this.issuingBanksReference = issuingBanksReference;
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

	public String getNarrative() {
		return this.narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public String getNewBeneficiary() {
		return this.newBeneficiary;
	}

	public void setNewBeneficiary(String newBeneficiary) {
		this.newBeneficiary = newBeneficiary;
	}

	public String getNewDateOfExpiry() {
		return this.newDateOfExpiry;
	}

	public void setNewDateOfExpiry(String newDateOfExpiry) {
		this.newDateOfExpiry = newDateOfExpiry;
	}

	public String getNewDocumentaryCreditAmountAfterAmendment() {
		return this.newDocumentaryCreditAmountAfterAmendment;
	}

	public void setNewDocumentaryCreditAmountAfterAmendment(String newDocumentaryCreditAmountAfterAmendment) {
		this.newDocumentaryCreditAmountAfterAmendment = newDocumentaryCreditAmountAfterAmendment;
	}

	public String getNumberOfAmendment() {
		return this.numberOfAmendment;
	}

	public void setNumberOfAmendment(String numberOfAmendment) {
		this.numberOfAmendment = numberOfAmendment;
	}

	public String getPercentageCreditAmountTolerance() {
		return this.percentageCreditAmountTolerance;
	}

	public void setPercentageCreditAmountTolerance(String percentageCreditAmountTolerance) {
		this.percentageCreditAmountTolerance = percentageCreditAmountTolerance;
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

	public String getReceiversReference() {
		return this.receiversReference;
	}

	public void setReceiversReference(String receiversReference) {
		this.receiversReference = receiversReference;
	}

	public String getSenderToReceiverInformation() {
		return this.senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSendersReference() {
		return this.sendersReference;
	}

	public void setSendersReference(String sendersReference) {
		this.sendersReference = sendersReference;
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

}