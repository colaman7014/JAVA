package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT752 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT752", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt752.findAll", query="SELECT s FROM SwiftMt752 s")
public class SwiftMt752 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt752PK id;

	@Column(name="Charges_Deducted")
	private String charges_Deducted;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_of_Advice_of_Discrepancy_or_Mailing")
	private String date_of_Advice_of_Discrepancy_or_Mailing;

	@Column(name="Documentary_Credit_Number")
	private String documentary_Credit_Number;

	@Column(name="Further_Identification")
	private String further_Identification;

	@Column(name="Net_Amount")
	private String net_Amount;

	@Column(name="Presenting_Banks_Reference")
	private String presenting_Banks_Reference;

	@Column(name="Receivers_Correspondent")
	private String receivers_Correspondent;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Senders_Correspondent")
	private String senders_Correspondent;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Total_Amount_Advised")
	private String total_Amount_Advised;

	@Column(name="NET_AMOUNT")
	private String netAmount;
	
	@Column(name="NARRATIVE")
	private String narrative;
	
	public SwiftMt752() {
	}

	public SwiftMt752PK getId() {
		return this.id;
	}

	public void setId(SwiftMt752PK id) {
		this.id = id;
	}

	public String getCharges_Deducted() {
		return this.charges_Deducted;
	}

	public void setCharges_Deducted(String charges_Deducted) {
		this.charges_Deducted = charges_Deducted;
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

	public String getDate_of_Advice_of_Discrepancy_or_Mailing() {
		return this.date_of_Advice_of_Discrepancy_or_Mailing;
	}

	public void setDate_of_Advice_of_Discrepancy_or_Mailing(String date_of_Advice_of_Discrepancy_or_Mailing) {
		this.date_of_Advice_of_Discrepancy_or_Mailing = date_of_Advice_of_Discrepancy_or_Mailing;
	}

	public String getDocumentary_Credit_Number() {
		return this.documentary_Credit_Number;
	}

	public void setDocumentary_Credit_Number(String documentary_Credit_Number) {
		this.documentary_Credit_Number = documentary_Credit_Number;
	}

	public String getFurther_Identification() {
		return this.further_Identification;
	}

	public void setFurther_Identification(String further_Identification) {
		this.further_Identification = further_Identification;
	}

	public String getNet_Amount() {
		return this.net_Amount;
	}

	public void setNet_Amount(String net_Amount) {
		this.net_Amount = net_Amount;
	}

	public String getPresenting_Banks_Reference() {
		return this.presenting_Banks_Reference;
	}

	public void setPresenting_Banks_Reference(String presenting_Banks_Reference) {
		this.presenting_Banks_Reference = presenting_Banks_Reference;
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

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTotal_Amount_Advised() {
		return this.total_Amount_Advised;
	}

	public void setTotal_Amount_Advised(String total_Amount_Advised) {
		this.total_Amount_Advised = total_Amount_Advised;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

}
