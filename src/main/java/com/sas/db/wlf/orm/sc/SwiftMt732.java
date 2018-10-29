package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT732 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT732", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt732.findAll", query="SELECT s FROM SwiftMt732 s")
public class SwiftMt732 implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt732PK id;

	@Column(name="Amount_of_Utilisation")
	private String amount_of_Utilisation;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_of_Advice_of_Payment_Acceptance_Negotiation")
	private String date_of_Advice_of_Payment_Acceptance_Negotiation;

	@Column(name="Presenting_Banks_Reference")
	private String presenting_Banks_Reference;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Senders_TRN")
	private String senders_TRN;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt732() {
	}

	public SwiftMt732PK getId() {
		return this.id;
	}

	public void setId(SwiftMt732PK id) {
		this.id = id;
	}

	public String getAmount_of_Utilisation() {
		return this.amount_of_Utilisation;
	}

	public void setAmount_of_Utilisation(String amount_of_Utilisation) {
		this.amount_of_Utilisation = amount_of_Utilisation;
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

	public String getDate_of_Advice_of_Payment_Acceptance_Negotiation() {
		return this.date_of_Advice_of_Payment_Acceptance_Negotiation;
	}

	public void setDate_of_Advice_of_Payment_Acceptance_Negotiation(String date_of_Advice_of_Payment_Acceptance_Negotiation) {
		this.date_of_Advice_of_Payment_Acceptance_Negotiation = date_of_Advice_of_Payment_Acceptance_Negotiation;
	}

	public String getPresenting_Banks_Reference() {
		return this.presenting_Banks_Reference;
	}

	public void setPresenting_Banks_Reference(String presenting_Banks_Reference) {
		this.presenting_Banks_Reference = presenting_Banks_Reference;
	}

	public String getSender_to_Receiver_Information() {
		return this.sender_to_Receiver_Information;
	}

	public void setSender_to_Receiver_Information(String sender_to_Receiver_Information) {
		this.sender_to_Receiver_Information = sender_to_Receiver_Information;
	}

	public String getSenders_TRN() {
		return this.senders_TRN;
	}

	public void setSenders_TRN(String senders_TRN) {
		this.senders_TRN = senders_TRN;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

}
