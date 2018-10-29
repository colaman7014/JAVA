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
 * The persistent class for the SWIFT_MT756 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT756", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt756.findAll", query="SELECT s FROM SwiftMt756 s")
public class SwiftMt756 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt756PK id;

	@Column(name="AMOUNT_REIMBURSED_OR_PAID")
	private String amountReimbursedOrPaid;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="PRESENTING_BANKS_REFERENCE")
	private String presentingBanksReference;

	@Column(name="RECEIVERS_CORRESPONDENT")
	private String receiversCorrespondent;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDERS_CORRESPONDENT")
	private String sendersCorrespondent;

	@Column(name="SENDERS_REFERENCE")
	private String sendersReference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TOTAL_AMOUNT_CLAIMED")
	private String totalAmountClaimed;

	@Column(name="NARRATIVE")
	private String narrative;
	
	public SwiftMt756() {
	}

	public SwiftMt756PK getId() {
		return this.id;
	}

	public void setId(SwiftMt756PK id) {
		this.id = id;
	}

	public String getAmountReimbursedOrPaid() {
		return this.amountReimbursedOrPaid;
	}

	public void setAmountReimbursedOrPaid(String amountReimbursedOrPaid) {
		this.amountReimbursedOrPaid = amountReimbursedOrPaid;
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

	public String getPresentingBanksReference() {
		return this.presentingBanksReference;
	}

	public void setPresentingBanksReference(String presentingBanksReference) {
		this.presentingBanksReference = presentingBanksReference;
	}

	public String getReceiversCorrespondent() {
		return this.receiversCorrespondent;
	}

	public void setReceiversCorrespondent(String receiversCorrespondent) {
		this.receiversCorrespondent = receiversCorrespondent;
	}

	public String getSenderToReceiverInformation() {
		return this.senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSendersCorrespondent() {
		return this.sendersCorrespondent;
	}

	public void setSendersCorrespondent(String sendersCorrespondent) {
		this.sendersCorrespondent = sendersCorrespondent;
	}

	public String getSendersReference() {
		return this.sendersReference;
	}

	public void setSendersReference(String sendersReference) {
		this.sendersReference = sendersReference;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTotalAmountClaimed() {
		return this.totalAmountClaimed;
	}

	public void setTotalAmountClaimed(String totalAmountClaimed) {
		this.totalAmountClaimed = totalAmountClaimed;
	}

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

}