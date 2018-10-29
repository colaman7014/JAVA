package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT750 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT750", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt750.findAll", query="SELECT s FROM SwiftMt750 s")
public class SwiftMt750 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt750PK id;

	@Column(name="Account_With_Bank")
	private String account_With_Bank;

	@Column(name="Additional_Amount")
	private String additional_Amount;

	@Column(name="Charges_to_be_Added")
	private String charges_to_be_Added;

	@Column(name="Charges_to_be_Deducted")
	private String charges_to_be_Deducted;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Discrepancies")
	private String discrepancies;

	@Column(name="Principal_Amount")
	private String principal_Amount;

	@Column(name="Related_Reference")
	private String related_Reference;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Senders_Reference")
	private String senders_Reference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Total_Amount_to_be_Paid")
	private String total_Amount_to_be_Paid;

	public SwiftMt750() {
	}

	public SwiftMt750PK getId() {
		return this.id;
	}

	public void setId(SwiftMt750PK id) {
		this.id = id;
	}

	public String getAccount_With_Bank() {
		return this.account_With_Bank;
	}

	public void setAccount_With_Bank(String account_With_Bank) {
		this.account_With_Bank = account_With_Bank;
	}

	public String getAdditional_Amount() {
		return this.additional_Amount;
	}

	public void setAdditional_Amount(String additional_Amount) {
		this.additional_Amount = additional_Amount;
	}

	public String getCharges_to_be_Added() {
		return this.charges_to_be_Added;
	}

	public void setCharges_to_be_Added(String charges_to_be_Added) {
		this.charges_to_be_Added = charges_to_be_Added;
	}

	public String getCharges_to_be_Deducted() {
		return this.charges_to_be_Deducted;
	}

	public void setCharges_to_be_Deducted(String charges_to_be_Deducted) {
		this.charges_to_be_Deducted = charges_to_be_Deducted;
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

	public String getDiscrepancies() {
		return this.discrepancies;
	}

	public void setDiscrepancies(String discrepancies) {
		this.discrepancies = discrepancies;
	}

	public String getPrincipal_Amount() {
		return this.principal_Amount;
	}

	public void setPrincipal_Amount(String principal_Amount) {
		this.principal_Amount = principal_Amount;
	}

	public String getRelated_Reference() {
		return this.related_Reference;
	}

	public void setRelated_Reference(String related_Reference) {
		this.related_Reference = related_Reference;
	}

	public String getSender_to_Receiver_Information() {
		return this.sender_to_Receiver_Information;
	}

	public void setSender_to_Receiver_Information(String sender_to_Receiver_Information) {
		this.sender_to_Receiver_Information = sender_to_Receiver_Information;
	}

	public String getSenders_Reference() {
		return this.senders_Reference;
	}

	public void setSenders_Reference(String senders_Reference) {
		this.senders_Reference = senders_Reference;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTotal_Amount_to_be_Paid() {
		return this.total_Amount_to_be_Paid;
	}

	public void setTotal_Amount_to_be_Paid(String total_Amount_to_be_Paid) {
		this.total_Amount_to_be_Paid = total_Amount_to_be_Paid;
	}

}
