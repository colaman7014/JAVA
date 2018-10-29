package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT190 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT190", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt190.findAll", query="SELECT s FROM SwiftMt190 s")
public class SwiftMt190 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt190PK id;

	@Column(name="Account_Identification")
	private String account_Identification;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Details_of_Charges")
	private String details_of_Charges;

	@Column(name="Ordering_Institution")
	private String ordering_Institution;

	@Column(name="Related_Reference")
	private String related_Reference;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	@Column(name="Value_Date_Currency_Code_Amount")
	private String value_Date_Currency_Code_Amount;

	public SwiftMt190() {
	}

	public SwiftMt190PK getId() {
		return this.id;
	}

	public void setId(SwiftMt190PK id) {
		this.id = id;
	}

	public String getAccount_Identification() {
		return this.account_Identification;
	}

	public void setAccount_Identification(String account_Identification) {
		this.account_Identification = account_Identification;
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

	public String getDetails_of_Charges() {
		return this.details_of_Charges;
	}

	public void setDetails_of_Charges(String details_of_Charges) {
		this.details_of_Charges = details_of_Charges;
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

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
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