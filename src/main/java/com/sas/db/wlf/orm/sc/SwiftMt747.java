package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT747 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT747", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt747.findAll", query="SELECT s FROM SwiftMt747 s")
public class SwiftMt747 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt747PK id;

	@Column(name="Additional_Amounts_Covered")
	private String additional_Amounts_Covered;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_of_the_Original_Authorisation_to_Reimburse")
	private String date_of_the_Original_Authorisation_to_Reimburse;

	@Column(name="Decrease_of_Documentary_Credit_Amount")
	private String decrease_of_Documentary_Credit_Amount;

	@Column(name="Documentary_Credit_Number")
	private String documentary_Credit_Number;

	@Column(name="Increase_of_Documentary_Credit_Amount")
	private String increase_of_Documentary_Credit_Amount;

	@Column(name="Maximum_Credit_Amount")
	private String maximum_Credit_Amount;

	@Column(name="Narrative")
	private String narrative;

	@Column(name="New_Date_of_Expiry")
	private String new_Date_of_Expiry;

	@Column(name="New_Documentary_Credit_Amount_After_Amendment")
	private String new_Documentary_Credit_Amount_After_Amendment;

	@Column(name="Percentage_Credit_Amount_Tolerance")
	private String percentage_Credit_Amount_Tolerance;

	@Column(name="Reimbursing_Banks_Reference")
	private String reimbursing_Banks_Reference;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt747() {
	}

	public SwiftMt747PK getId() {
		return this.id;
	}

	public void setId(SwiftMt747PK id) {
		this.id = id;
	}

	public String getAdditional_Amounts_Covered() {
		return this.additional_Amounts_Covered;
	}

	public void setAdditional_Amounts_Covered(String additional_Amounts_Covered) {
		this.additional_Amounts_Covered = additional_Amounts_Covered;
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

	public String getDate_of_the_Original_Authorisation_to_Reimburse() {
		return this.date_of_the_Original_Authorisation_to_Reimburse;
	}

	public void setDate_of_the_Original_Authorisation_to_Reimburse(String date_of_the_Original_Authorisation_to_Reimburse) {
		this.date_of_the_Original_Authorisation_to_Reimburse = date_of_the_Original_Authorisation_to_Reimburse;
	}

	public String getDecrease_of_Documentary_Credit_Amount() {
		return this.decrease_of_Documentary_Credit_Amount;
	}

	public void setDecrease_of_Documentary_Credit_Amount(String decrease_of_Documentary_Credit_Amount) {
		this.decrease_of_Documentary_Credit_Amount = decrease_of_Documentary_Credit_Amount;
	}

	public String getDocumentary_Credit_Number() {
		return this.documentary_Credit_Number;
	}

	public void setDocumentary_Credit_Number(String documentary_Credit_Number) {
		this.documentary_Credit_Number = documentary_Credit_Number;
	}

	public String getIncrease_of_Documentary_Credit_Amount() {
		return this.increase_of_Documentary_Credit_Amount;
	}

	public void setIncrease_of_Documentary_Credit_Amount(String increase_of_Documentary_Credit_Amount) {
		this.increase_of_Documentary_Credit_Amount = increase_of_Documentary_Credit_Amount;
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

	public String getNew_Date_of_Expiry() {
		return this.new_Date_of_Expiry;
	}

	public void setNew_Date_of_Expiry(String new_Date_of_Expiry) {
		this.new_Date_of_Expiry = new_Date_of_Expiry;
	}

	public String getNew_Documentary_Credit_Amount_After_Amendment() {
		return this.new_Documentary_Credit_Amount_After_Amendment;
	}

	public void setNew_Documentary_Credit_Amount_After_Amendment(String new_Documentary_Credit_Amount_After_Amendment) {
		this.new_Documentary_Credit_Amount_After_Amendment = new_Documentary_Credit_Amount_After_Amendment;
	}

	public String getPercentage_Credit_Amount_Tolerance() {
		return this.percentage_Credit_Amount_Tolerance;
	}

	public void setPercentage_Credit_Amount_Tolerance(String percentage_Credit_Amount_Tolerance) {
		this.percentage_Credit_Amount_Tolerance = percentage_Credit_Amount_Tolerance;
	}

	public String getReimbursing_Banks_Reference() {
		return this.reimbursing_Banks_Reference;
	}

	public void setReimbursing_Banks_Reference(String reimbursing_Banks_Reference) {
		this.reimbursing_Banks_Reference = reimbursing_Banks_Reference;
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
