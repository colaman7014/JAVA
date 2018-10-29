package com.sas.db.wlf.orm.sc;

import java.io.Serializable;

import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT705 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT705", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt705.findAll", query="SELECT s FROM SwiftMt705 s")
public class SwiftMt705 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt705PK id;

	@Column(name="Additional_Amounts_Covered")
	private String additional_Amounts_Covered;

	@Column(name="Advise_Through_Bank")
	private String advise_Through_Bank;

	@Column(name="Applicant")
	private String applicant;

	@Column(name="Available_With_By")
	private String available_With_By;

	@Column(name="Beneficiary")
	private String beneficiary;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Currency_Code_Amount")
	private String currency_Code_Amount;

	@Column(name="Date_and_Place_of_Expiry")
	private String date_and_Place_of_Expiry;

	@Column(name="Description_of_Goods_and_or_Services")
	private String description_of_Goods_and_or_Services;

	@Column(name="Documentary_Credit_Number")
	private String documentary_Credit_Number;

	@Column(name="Form_of_Documentary_Credit")
	private String form_of_Documentary_Credit;

	@Column(name="Latest_Date_of_Shipment")
	private String latest_Date_of_Shipment;

	@Column(name="Maximum_Credit_Amount")
	private String maximum_Credit_Amount;

	@Column(name="Narrative")
	private String narrative;

	@Column(name="Percentage_Credit_Amount_Tolerance")
	private String percentage_Credit_Amount_Tolerance;

	@Column(name="Place_of_Final_Destination_For_Transportation_to_Place_of_Delivery")
	private String place_of_Final_Destination_For_Transportation_to_Place_of_Delivery;

	@Column(name="Place_of_Taking_in_Charge_Dispatch_from_Place_of_Receipt")
	private String place_of_Taking_in_Charge_Dispatch_from_Place_of_Receipt;

	@Column(name="Port_of_Discharge_Airport_of_Destination")
	private String port_of_Discharge_Airport_of_Destination;

	@Column(name="Port_of_Loading_Airport_of_Departure")
	private String port_of_Loading_Airport_of_Departure;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Shipment_Period")
	private String shipment_Period;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt705() {
	}

	public SwiftMt705PK getId() {
		return this.id;
	}

	public void setId(SwiftMt705PK id) {
		this.id = id;
	}

	public String getAdditional_Amounts_Covered() {
		return this.additional_Amounts_Covered;
	}

	public void setAdditional_Amounts_Covered(String additional_Amounts_Covered) {
		this.additional_Amounts_Covered = additional_Amounts_Covered;
	}

	public String getAdvise_Through_Bank() {
		return this.advise_Through_Bank;
	}

	public void setAdvise_Through_Bank(String advise_Through_Bank) {
		this.advise_Through_Bank = advise_Through_Bank;
	}

	public String getApplicant() {
		return this.applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getAvailable_With_By() {
		return this.available_With_By;
	}

	public void setAvailable_With_By(String available_With_By) {
		this.available_With_By = available_With_By;
	}

	public String getBeneficiary() {
		return this.beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
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

	public String getCurrency_Code_Amount() {
		return this.currency_Code_Amount;
	}

	public void setCurrency_Code_Amount(String currency_Code_Amount) {
		this.currency_Code_Amount = currency_Code_Amount;
	}

	public String getDate_and_Place_of_Expiry() {
		return this.date_and_Place_of_Expiry;
	}

	public void setDate_and_Place_of_Expiry(String date_and_Place_of_Expiry) {
		this.date_and_Place_of_Expiry = date_and_Place_of_Expiry;
	}

	public String getDescription_of_Goods_and_or_Services() {
		return description_of_Goods_and_or_Services;
	}

	public void setDescription_of_Goods_and_or_Services(
			String description_of_Goods_and_or_Services) {
		this.description_of_Goods_and_or_Services = description_of_Goods_and_or_Services;
	}

	public String getDocumentary_Credit_Number() {
		return this.documentary_Credit_Number;
	}

	public void setDocumentary_Credit_Number(String documentary_Credit_Number) {
		this.documentary_Credit_Number = documentary_Credit_Number;
	}

	public String getForm_of_Documentary_Credit() {
		return this.form_of_Documentary_Credit;
	}

	public void setForm_of_Documentary_Credit(String form_of_Documentary_Credit) {
		this.form_of_Documentary_Credit = form_of_Documentary_Credit;
	}

	public String getLatest_Date_of_Shipment() {
		return this.latest_Date_of_Shipment;
	}

	public void setLatest_Date_of_Shipment(String latest_Date_of_Shipment) {
		this.latest_Date_of_Shipment = latest_Date_of_Shipment;
	}

	public String getMaximum_Credit_Amount() {
		return this.maximum_Credit_Amount;
	}

	public void setMaximum_Credit_Amount(String maximum_Credit_Amount) {
		this.maximum_Credit_Amount = maximum_Credit_Amount;
	}

	public String getNarrative() {
		return this.narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public String getPercentage_Credit_Amount_Tolerance() {
		return this.percentage_Credit_Amount_Tolerance;
	}

	public void setPercentage_Credit_Amount_Tolerance(String percentage_Credit_Amount_Tolerance) {
		this.percentage_Credit_Amount_Tolerance = percentage_Credit_Amount_Tolerance;
	}

	public String getPlace_of_Final_Destination_For_Transportation_to_Place_of_Delivery() {
		return place_of_Final_Destination_For_Transportation_to_Place_of_Delivery;
	}

	public void setPlace_of_Final_Destination_For_Transportation_to_Place_of_Delivery(
			String place_of_Final_Destination_For_Transportation_to_Place_of_Delivery) {
		this.place_of_Final_Destination_For_Transportation_to_Place_of_Delivery = place_of_Final_Destination_For_Transportation_to_Place_of_Delivery;
	}

	public String getPlace_of_Taking_in_Charge_Dispatch_from_Place_of_Receipt() {
		return place_of_Taking_in_Charge_Dispatch_from_Place_of_Receipt;
	}

	public void setPlace_of_Taking_in_Charge_Dispatch_from_Place_of_Receipt(
			String place_of_Taking_in_Charge_Dispatch_from_Place_of_Receipt) {
		this.place_of_Taking_in_Charge_Dispatch_from_Place_of_Receipt = place_of_Taking_in_Charge_Dispatch_from_Place_of_Receipt;
	}

	public String getPort_of_Discharge_Airport_of_Destination() {
		return port_of_Discharge_Airport_of_Destination;
	}

	public void setPort_of_Discharge_Airport_of_Destination(
			String port_of_Discharge_Airport_of_Destination) {
		this.port_of_Discharge_Airport_of_Destination = port_of_Discharge_Airport_of_Destination;
	}

	public String getPort_of_Loading_Airport_of_Departure() {
		return port_of_Loading_Airport_of_Departure;
	}

	public void setPort_of_Loading_Airport_of_Departure(
			String port_of_Loading_Airport_of_Departure) {
		this.port_of_Loading_Airport_of_Departure = port_of_Loading_Airport_of_Departure;
	}

	public String getSender_to_Receiver_Information() {
		return this.sender_to_Receiver_Information;
	}

	public void setSender_to_Receiver_Information(String sender_to_Receiver_Information) {
		this.sender_to_Receiver_Information = sender_to_Receiver_Information;
	}

	public String getShipment_Period() {
		return this.shipment_Period;
	}

	public void setShipment_Period(String shipment_Period) {
		this.shipment_Period = shipment_Period;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

}
