package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT767 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT767", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt767.findAll", query="SELECT s FROM SwiftMt767 s")
public class SwiftMt767 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt767PK id;

	@Column(name="Amendment_Details")
	private String amendment_Details;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date")
	private String date;

	@Column(name="Date_of_Issue_or_Request_to_Issue")
	private String date_of_Issue_or_Request_to_Issue;

	@Column(name="Further_Identification")
	private String further_Identification;

	@Column(name="Number_of_Amendment")
	private String number_of_Amendment;

	@Column(name="Related_Reference")
	private String related_Reference;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Sequence_of_Total")
	private String sequence_of_Total;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	public SwiftMt767() {
	}

	public SwiftMt767PK getId() {
		return this.id;
	}

	public void setId(SwiftMt767PK id) {
		this.id = id;
	}

	public String getAmendment_Details() {
		return this.amendment_Details;
	}

	public void setAmendment_Details(String amendment_Details) {
		this.amendment_Details = amendment_Details;
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

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate_of_Issue_or_Request_to_Issue() {
		return this.date_of_Issue_or_Request_to_Issue;
	}

	public void setDate_of_Issue_or_Request_to_Issue(String date_of_Issue_or_Request_to_Issue) {
		this.date_of_Issue_or_Request_to_Issue = date_of_Issue_or_Request_to_Issue;
	}

	public String getFurther_Identification() {
		return this.further_Identification;
	}

	public void setFurther_Identification(String further_Identification) {
		this.further_Identification = further_Identification;
	}

	public String getNumber_of_Amendment() {
		return this.number_of_Amendment;
	}

	public void setNumber_of_Amendment(String number_of_Amendment) {
		this.number_of_Amendment = number_of_Amendment;
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

	public String getSequence_of_Total() {
		return this.sequence_of_Total;
	}

	public void setSequence_of_Total(String sequence_of_Total) {
		this.sequence_of_Total = sequence_of_Total;
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
