package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT110 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT110", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt110.findAll", query="SELECT s FROM SwiftMt110 s")
public class SwiftMt110 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt110PK id;

	@Column(name="Amount")
	private String amount;

	@Column(name="Cheque_Number")
	private String cheque_Number;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_of_Issue")
	private String date_of_Issue;

	@Column(name="Drawer_Bank")
	private String drawer_Bank;

	@Column(name="Payee")
	private String payee;

	@Column(name="Receivers_Correspondent")
	private String receivers_Correspondent;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Senders_Correspondent")
	private String senders_Correspondent;

	@Column(name="Senders_Reference")
	private String senders_Reference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt110() {
	}

	public SwiftMt110PK getId() {
		return this.id;
	}

	public void setId(SwiftMt110PK id) {
		this.id = id;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCheque_Number() {
		return this.cheque_Number;
	}

	public void setCheque_Number(String cheque_Number) {
		this.cheque_Number = cheque_Number;
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

	public String getDrawer_Bank() {
		return this.drawer_Bank;
	}

	public void setDrawer_Bank(String drawer_Bank) {
		this.drawer_Bank = drawer_Bank;
	}

	public String getPayee() {
		return this.payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getReceivers_Correspondent() {
		return this.receivers_Correspondent;
	}

	public void setReceivers_Correspondent(String receivers_Correspondent) {
		this.receivers_Correspondent = receivers_Correspondent;
	}

	public String getSender_to_Receiver_Information() {
		return this.sender_to_Receiver_Information;
	}

	public void setSender_to_Receiver_Information(String sender_to_Receiver_Information) {
		this.sender_to_Receiver_Information = sender_to_Receiver_Information;
	}

	public String getSenders_Correspondent() {
		return this.senders_Correspondent;
	}

	public void setSenders_Correspondent(String senders_Correspondent) {
		this.senders_Correspondent = senders_Correspondent;
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

}