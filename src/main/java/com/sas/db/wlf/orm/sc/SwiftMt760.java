package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT760 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT760", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt760.findAll", query="SELECT s FROM SwiftMt760 s")
public class SwiftMt760 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt760PK id;

	@Column(name="Applicable_Rules")
	private String applicable_Rules;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date")
	private String date;

	@Column(name="Details_of_Guarantee")
	private String details_of_Guarantee;

	@Column(name="Further_Identification")
	private String further_Identification;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Sequence_of_Total")
	private String sequence_of_Total;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	public SwiftMt760() {
	}

	public SwiftMt760PK getId() {
		return this.id;
	}

	public void setId(SwiftMt760PK id) {
		this.id = id;
	}

	public String getApplicable_Rules() {
		return this.applicable_Rules;
	}

	public void setApplicable_Rules(String applicable_Rules) {
		this.applicable_Rules = applicable_Rules;
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

	public String getDetails_of_Guarantee() {
		return this.details_of_Guarantee;
	}

	public void setDetails_of_Guarantee(String details_of_Guarantee) {
		this.details_of_Guarantee = details_of_Guarantee;
	}

	public String getFurther_Identification() {
		return this.further_Identification;
	}

	public void setFurther_Identification(String further_Identification) {
		this.further_Identification = further_Identification;
	}

	public String getSender_to_Receiver_Information() {
		return this.sender_to_Receiver_Information;
	}

	public void setSender_to_Receiver_Information(String sender_to_Receiver_Information) {
		this.sender_to_Receiver_Information = sender_to_Receiver_Information;
	}

	public String getSequence_of_Total() {
		return sequence_of_Total;
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
