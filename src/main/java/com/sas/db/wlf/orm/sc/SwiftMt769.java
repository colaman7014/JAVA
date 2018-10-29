package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT769 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT769", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt769.findAll", query="SELECT s FROM SwiftMt769 s")
public class SwiftMt769 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt769PK id;

	@Column(name="Account_Identification")
	private String account_Identification;

	@Column(name="Account_With_Bank")
	private String account_With_Bank;

	@Column(name="Amount_of_Charges")
	private String amount_of_Charges;

	@Column(name="Amount_Outstanding")
	private String amount_Outstanding;

	@Column(name="Amount_Reduced_or_Released")
	private String amount_Reduced_or_Released;

	@Column(name="Amount_Specification")
	private String amount_Specification;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_of_Reduction_or_Release")
	private String date_of_Reduction_or_Release;

	@Column(name="Details_of_Charges")
	private String details_of_Charges;

	@Column(name="Related_Reference")
	private String related_Reference;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	public SwiftMt769() {
	}

	public SwiftMt769PK getId() {
		return this.id;
	}

	public void setId(SwiftMt769PK id) {
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

	public String getAmount_Outstanding() {
		return this.amount_Outstanding;
	}

	public void setAmount_Outstanding(String amount_Outstanding) {
		this.amount_Outstanding = amount_Outstanding;
	}

	public String getAmount_Reduced_or_Released() {
		return this.amount_Reduced_or_Released;
	}

	public void setAmount_Reduced_or_Released(String amount_Reduced_or_Released) {
		this.amount_Reduced_or_Released = amount_Reduced_or_Released;
	}

	public String getAmount_Specification() {
		return this.amount_Specification;
	}

	public void setAmount_Specification(String amount_Specification) {
		this.amount_Specification = amount_Specification;
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

	public String getDate_of_Reduction_or_Release() {
		return this.date_of_Reduction_or_Release;
	}

	public void setDate_of_Reduction_or_Release(String date_of_Reduction_or_Release) {
		this.date_of_Reduction_or_Release = date_of_Reduction_or_Release;
	}

	public String getDetails_of_Charges() {
		return this.details_of_Charges;
	}

	public void setDetails_of_Charges(String details_of_Charges) {
		this.details_of_Charges = details_of_Charges;
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

}
