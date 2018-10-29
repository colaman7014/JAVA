package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT205 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT205", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt205.findAll", query="SELECT s FROM SwiftMt205 s")
public class SwiftMt205 implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt205PK id;

	@Column(name="Account_With_Institution")
	private String account_With_Institution;

	@Column(name="Beneficiary_Institution")
	private String beneficiary_Institution;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Intermediary")
	private String intermediary;

	@Column(name="Ordering_Institution")
	private String ordering_Institution;

	@Column(name="Related_Reference")
	private String related_Reference;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Senders_Correspondent")
	private String senders_Correspondent;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Time_Indication")
	private String time_Indication;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	@Column(name="Value_Date_Currency_Code_Amount")
	private String value_Date_Currency_Code_Amount;

	public SwiftMt205() {
	}

	public SwiftMt205PK getId() {
		return this.id;
	}

	public void setId(SwiftMt205PK id) {
		this.id = id;
	}

	public String getAccount_With_Institution() {
		return this.account_With_Institution;
	}

	public void setAccount_With_Institution(String account_With_Institution) {
		this.account_With_Institution = account_With_Institution;
	}

	public String getBeneficiary_Institution() {
		return this.beneficiary_Institution;
	}

	public void setBeneficiary_Institution(String beneficiary_Institution) {
		this.beneficiary_Institution = beneficiary_Institution;
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

	public String getIntermediary() {
		return this.intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public String getOrdering_Institution() {
		return this.ordering_Institution;
	}

	public void setOrdering_Institution(String ordering_Institution) {
		this.ordering_Institution = ordering_Institution;
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

	public String getTime_Indication() {
		return this.time_Indication;
	}

	public void setTime_Indication(String time_Indication) {
		this.time_Indication = time_Indication;
	}

	public String getTransaction_Reference_Number() {
		return this.transaction_Reference_Number;
	}

	public void setTransaction_Reference_Number(String transaction_Reference_Number) {
		this.transaction_Reference_Number = transaction_Reference_Number;
	}

	public String getValue_Date_Currency_Code_Amount() {
		return this.value_Date_Currency_Code_Amount;
	}

	public void setValue_Date_Currency_Code_Amount(String value_Date_Currency_Code_Amount) {
		this.value_Date_Currency_Code_Amount = value_Date_Currency_Code_Amount;
	}

}