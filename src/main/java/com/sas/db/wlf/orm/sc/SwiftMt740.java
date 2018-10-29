package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT740 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT740", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt740.findAll", query="SELECT s FROM SwiftMt740 s")
public class SwiftMt740 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt740PK id;

	@Column(name="Account_Identification")
	private String account_Identification;

	@Column(name="Additional_Amounts_Covered")
	private String additional_Amounts_Covered;

	@Column(name="Applicable_Rules")
	private String applicable_Rules;

	@Column(name="Available_With_By")
	private String available_With_By;

	@Column(name="Beneficiary")
	private String beneficiary;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Credit_Amount")
	private String credit_Amount;

	@Column(name="Date_and_Place_of_Expiry")
	private String date_and_Place_of_Expiry;

	@Column(name="Deferred_Payment_Details")
	private String deferred_Payment_Details;

	@Column(name="Documentary_Credit_Number")
	private String documentary_Credit_Number;

	@Column(name="Drafts_at")
	private String drafts_at;

	@Column(name="Drawee")
	private String drawee;

	@Column(name="Maximum_Credit_Amount")
	private String maximum_Credit_Amount;

	@Column(name="Mixed_Payment_Details")
	private String mixed_Payment_Details;

	@Column(name="Negotiating_Bank")
	private String negotiating_Bank;

	@Column(name="Other_Charges")
	private String other_Charges;

	@Column(name="Percentage_Credit_Amount_Tolerance")
	private String percentage_Credit_Amount_Tolerance;

	@Column(name="Reimbursing_Banks_Charges")
	private String reimbursing_Banks_Charges;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt740() {
	}

	public SwiftMt740PK getId() {
		return this.id;
	}

	public void setId(SwiftMt740PK id) {
		this.id = id;
	}

	public String getAccount_Identification() {
		return this.account_Identification;
	}

	public void setAccount_Identification(String account_Identification) {
		this.account_Identification = account_Identification;
	}

	public String getAdditional_Amounts_Covered() {
		return this.additional_Amounts_Covered;
	}

	public void setAdditional_Amounts_Covered(String additional_Amounts_Covered) {
		this.additional_Amounts_Covered = additional_Amounts_Covered;
	}

	public String getApplicable_Rules() {
		return this.applicable_Rules;
	}

	public void setApplicable_Rules(String applicable_Rules) {
		this.applicable_Rules = applicable_Rules;
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

	public String getCredit_Amount() {
		return this.credit_Amount;
	}

	public void setCredit_Amount(String credit_Amount) {
		this.credit_Amount = credit_Amount;
	}

	public String getDate_and_Place_of_Expiry() {
		return this.date_and_Place_of_Expiry;
	}

	public void setDate_and_Place_of_Expiry(String date_and_Place_of_Expiry) {
		this.date_and_Place_of_Expiry = date_and_Place_of_Expiry;
	}

	public String getDeferred_Payment_Details() {
		return this.deferred_Payment_Details;
	}

	public void setDeferred_Payment_Details(String deferred_Payment_Details) {
		this.deferred_Payment_Details = deferred_Payment_Details;
	}

	public String getDocumentary_Credit_Number() {
		return this.documentary_Credit_Number;
	}

	public void setDocumentary_Credit_Number(String documentary_Credit_Number) {
		this.documentary_Credit_Number = documentary_Credit_Number;
	}

	public String getDrafts_at() {
		return this.drafts_at;
	}

	public void setDrafts_at(String drafts_at) {
		this.drafts_at = drafts_at;
	}

	public String getDrawee() {
		return this.drawee;
	}

	public void setDrawee(String drawee) {
		this.drawee = drawee;
	}

	public String getMaximum_Credit_Amount() {
		return this.maximum_Credit_Amount;
	}

	public void setMaximum_Credit_Amount(String maximum_Credit_Amount) {
		this.maximum_Credit_Amount = maximum_Credit_Amount;
	}

	public String getMixed_Payment_Details() {
		return this.mixed_Payment_Details;
	}

	public void setMixed_Payment_Details(String mixed_Payment_Details) {
		this.mixed_Payment_Details = mixed_Payment_Details;
	}

	public String getNegotiating_Bank() {
		return this.negotiating_Bank;
	}

	public void setNegotiating_Bank(String negotiating_Bank) {
		this.negotiating_Bank = negotiating_Bank;
	}

	public String getOther_Charges() {
		return this.other_Charges;
	}

	public void setOther_Charges(String other_Charges) {
		this.other_Charges = other_Charges;
	}

	public String getPercentage_Credit_Amount_Tolerance() {
		return this.percentage_Credit_Amount_Tolerance;
	}

	public void setPercentage_Credit_Amount_Tolerance(String percentage_Credit_Amount_Tolerance) {
		this.percentage_Credit_Amount_Tolerance = percentage_Credit_Amount_Tolerance;
	}

	public String getReimbursing_Banks_Charges() {
		return this.reimbursing_Banks_Charges;
	}

	public void setReimbursing_Banks_Charges(String reimbursing_Banks_Charges) {
		this.reimbursing_Banks_Charges = reimbursing_Banks_Charges;
	}

	public String getSender_to_Receiver_Information() {
		return this.sender_to_Receiver_Information;
	}

	public void setSender_to_Receiver_Information(String sender_to_Receiver_Information) {
		this.sender_to_Receiver_Information = sender_to_Receiver_Information;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

}
