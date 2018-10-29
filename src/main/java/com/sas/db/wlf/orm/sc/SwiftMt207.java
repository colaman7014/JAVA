package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT207 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT207", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt207.findAll", query="SELECT s FROM SwiftMt207 s")
public class SwiftMt207 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt207PK id;

	@Column(name="Account_Servicing_Institution")
	private String account_Servicing_Institution;

	@Column(name="Account_With_Institution")
	private String account_With_Institution;

	@Column(name="Beneficiary_Institution")
	private String beneficiary_Institution;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Currency_Transaction_Amount")
	private String currency_Transaction_Amount;

	@Column(name="Initiating_Institution")
	private String initiating_Institution;

	@Column(name="Instruction_Code")
	private String instruction_Code;

	@Column(name="Intermediary")
	private String intermediary;

	@Column(name="Message_Index_Total")
	private String message_Index_Total;

	@Column(name="Ordering_Institution")
	private String ordering_Institution;

	@Column(name="Requested_Execution_Date")
	private String requested_Execution_Date;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Senders_Reference")
	private String senders_Reference;

	@Column(name="Specified_Reference_of_the_Ordering_Institution")
	private String specified_Reference_of_the_Ordering_Institution;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference")
	private String transaction_Reference;

	public SwiftMt207() {
	}

	public SwiftMt207PK getId() {
		return this.id;
	}

	public void setId(SwiftMt207PK id) {
		this.id = id;
	}

	public String getAccount_Servicing_Institution() {
		return this.account_Servicing_Institution;
	}

	public void setAccount_Servicing_Institution(String account_Servicing_Institution) {
		this.account_Servicing_Institution = account_Servicing_Institution;
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

	public String getCurrency_Transaction_Amount() {
		return this.currency_Transaction_Amount;
	}

	public void setCurrency_Transaction_Amount(String currency_Transaction_Amount) {
		this.currency_Transaction_Amount = currency_Transaction_Amount;
	}

	public String getInitiating_Institution() {
		return this.initiating_Institution;
	}

	public void setInitiating_Institution(String initiating_Institution) {
		this.initiating_Institution = initiating_Institution;
	}

	public String getInstruction_Code() {
		return this.instruction_Code;
	}

	public void setInstruction_Code(String instruction_Code) {
		this.instruction_Code = instruction_Code;
	}

	public String getIntermediary() {
		return this.intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public String getMessage_Index_Total() {
		return this.message_Index_Total;
	}

	public void setMessage_Index_Total(String message_Index_Total) {
		this.message_Index_Total = message_Index_Total;
	}

	public String getOrdering_Institution() {
		return this.ordering_Institution;
	}

	public void setOrdering_Institution(String ordering_Institution) {
		this.ordering_Institution = ordering_Institution;
	}

	public String getRequested_Execution_Date() {
		return this.requested_Execution_Date;
	}

	public void setRequested_Execution_Date(String requested_Execution_Date) {
		this.requested_Execution_Date = requested_Execution_Date;
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

	public String getSpecified_Reference_of_the_Ordering_Institution() {
		return this.specified_Reference_of_the_Ordering_Institution;
	}

	public void setSpecified_Reference_of_the_Ordering_Institution(String specified_Reference_of_the_Ordering_Institution) {
		this.specified_Reference_of_the_Ordering_Institution = specified_Reference_of_the_Ordering_Institution;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTransaction_Reference() {
		return this.transaction_Reference;
	}

	public void setTransaction_Reference(String transaction_Reference) {
		this.transaction_Reference = transaction_Reference;
	}

}