package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT742 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT742", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt742.findAll", query="SELECT s FROM SwiftMt742 s")
public class SwiftMt742 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt742PK id;

	@Column(name="Account_With_Bank")
	private String account_With_Bank;

	@Column(name="Additional_Amount_Claimed_as_Allowed_for_in_Excess_of_Principal_Amount")
	private String additional_Amount_Claimed_as_Allowed_for_in_Excess_of_Principal_Amount;

	@Column(name="Beneficiary_Bank")
	private String beneficiary_Bank;

	@Column(name="Charges")
	private String charges;

	@Column(name="Claiming_Banks_Reference")
	private String claiming_Banks_Reference;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_of_Issue")
	private String date_of_Issue;

	@Column(name="Documentary_Credit_Number")
	private String documentary_Credit_Number;

	@Column(name="Issuing_Bank")
	private String issuing_Bank;

	@Column(name="Principal_Amount_Claimed")
	private String principal_Amount_Claimed;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Total_Amount_Claimed")
	private String total_Amount_Claimed;

	public SwiftMt742() {
	}

	public SwiftMt742PK getId() {
		return this.id;
	}

	public void setId(SwiftMt742PK id) {
		this.id = id;
	}

	public String getAccount_With_Bank() {
		return this.account_With_Bank;
	}

	public void setAccount_With_Bank(String account_With_Bank) {
		this.account_With_Bank = account_With_Bank;
	}

	public String getAdditional_Amount_Claimed_as_Allowed_for_in_Excess_of_Principal_Amount() {
		return additional_Amount_Claimed_as_Allowed_for_in_Excess_of_Principal_Amount;
	}

	public void setAdditional_Amount_Claimed_as_Allowed_for_in_Excess_of_Principal_Amount(String additional_Amount_Claimed_as_Allowed_for_in_Excess_of_Principal_Amount) {
		this.additional_Amount_Claimed_as_Allowed_for_in_Excess_of_Principal_Amount = additional_Amount_Claimed_as_Allowed_for_in_Excess_of_Principal_Amount;
	}

	public String getBeneficiary_Bank() {
		return this.beneficiary_Bank;
	}

	public void setBeneficiary_Bank(String beneficiary_Bank) {
		this.beneficiary_Bank = beneficiary_Bank;
	}

	public String getCharges() {
		return this.charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public String getClaiming_Banks_Reference() {
		return this.claiming_Banks_Reference;
	}

	public void setClaiming_Banks_Reference(String claiming_Banks_Reference) {
		this.claiming_Banks_Reference = claiming_Banks_Reference;
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

	public String getDate_of_Issue() {
		return this.date_of_Issue;
	}

	public void setDate_of_Issue(String date_of_Issue) {
		this.date_of_Issue = date_of_Issue;
	}

	public String getDocumentary_Credit_Number() {
		return this.documentary_Credit_Number;
	}

	public void setDocumentary_Credit_Number(String documentary_Credit_Number) {
		this.documentary_Credit_Number = documentary_Credit_Number;
	}

	public String getIssuing_Bank() {
		return this.issuing_Bank;
	}

	public void setIssuing_Bank(String issuing_Bank) {
		this.issuing_Bank = issuing_Bank;
	}

	public String getPrincipal_Amount_Claimed() {
		return this.principal_Amount_Claimed;
	}

	public void setPrincipal_Amount_Claimed(String principal_Amount_Claimed) {
		this.principal_Amount_Claimed = principal_Amount_Claimed;
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

	public String getTotal_Amount_Claimed() {
		return this.total_Amount_Claimed;
	}

	public void setTotal_Amount_Claimed(String total_Amount_Claimed) {
		this.total_Amount_Claimed = total_Amount_Claimed;
	}

}
