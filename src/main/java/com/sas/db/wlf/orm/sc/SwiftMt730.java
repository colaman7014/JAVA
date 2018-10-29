package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT730 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT730", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt730.findAll", query="SELECT s FROM SwiftMt730 s")
public class SwiftMt730 implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt730PK id;

	@Column(name="Account_Identification")
	private String account_Identification;

	@Column(name="Account_With_Bank")
	private String account_With_Bank;

	@Column(name="Amount_of_Charges")
	private String amount_of_Charges;

	@Column(name="Charges")
	private String charges;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_of_Message_Being_Acknowledged")
	private String date_of_Message_Being_Acknowledged;

	@Column(name="Receivers_Reference")
	private String receivers_Reference;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Senders_Reference")
	private String senders_Reference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;
	
	@Column(name="NARRATIVE")
	private String narrative;

	public SwiftMt730() {
	}

	public SwiftMt730PK getId() {
		return this.id;
	}

	public void setId(SwiftMt730PK id) {
		this.id = id;
	}

	public String getAccount_Identification() {
		return this.account_Identification;
	}

	public void setAccount_Identification(String account_Identification) {
		this.account_Identification = account_Identification;
	}

	public String getAccount_With_Bank() {
		return this.account_With_Bank;
	}

	public void setAccount_With_Bank(String account_With_Bank) {
		this.account_With_Bank = account_With_Bank;
	}

	public String getAmount_of_Charges() {
		return this.amount_of_Charges;
	}

	public void setAmount_of_Charges(String amount_of_Charges) {
		this.amount_of_Charges = amount_of_Charges;
	}

	public String getCharges() {
		return this.charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
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

	public String getDate_of_Message_Being_Acknowledged() {
		return this.date_of_Message_Being_Acknowledged;
	}

	public void setDate_of_Message_Being_Acknowledged(String date_of_Message_Being_Acknowledged) {
		this.date_of_Message_Being_Acknowledged = date_of_Message_Being_Acknowledged;
	}

	public String getReceivers_Reference() {
		return this.receivers_Reference;
	}

	public void setReceivers_Reference(String receivers_Reference) {
		this.receivers_Reference = receivers_Reference;
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

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

}
