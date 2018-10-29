package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the SWIFT_MT410 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT410", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt410.findAll", query="SELECT s FROM SwiftMt410 s")
public class SwiftMt410 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt410PK id;

	@Column(name="AMOUNT_ACKNOWLEDGED")
	private String amountAcknowledged;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDING_BANKS_TRN")
	private String sendingBanksTrn;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt410() {
	}

	public SwiftMt410PK getId() {
		return this.id;
	}

	public void setId(SwiftMt410PK id) {
		this.id = id;
	}

	public String getAmountAcknowledged() {
		return this.amountAcknowledged;
	}

	public void setAmountAcknowledged(String amountAcknowledged) {
		this.amountAcknowledged = amountAcknowledged;
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

	public String getRelatedReference() {
		return this.relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}

	public String getSenderToReceiverInformation() {
		return this.senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSendingBanksTrn() {
		return this.sendingBanksTrn;
	}

	public void setSendingBanksTrn(String sendingBanksTrn) {
		this.sendingBanksTrn = sendingBanksTrn;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

}