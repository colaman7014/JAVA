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
 * The persistent class for the SWIFT_MT400 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT400", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt400.findAll", query="SELECT s FROM SwiftMt400 s")
public class SwiftMt400 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt400PK id;

	@Column(name="ACCOUNT_WITH_BANK")
	private String accountWithBank;

	@Column(name="AMOUNT_COLLECTED")
	private String amountCollected;

	@Column(name="BENEFICIARY_BANK")
	private String beneficiaryBank;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DETAILS_OF_AMOUNTS_ADDED")
	private String detailsOfAmountsAdded;

	@Column(name="DETAILS_OF_CHARGES")
	private String detailsOfCharges;

	@Column(name="ORDERING_BANK")
	private String orderingBank;

	@Column(name="PROCEEDS_REMITTED")
	private String proceedsRemitted;

	@Column(name="RECEIVERS_CORRESPONDENT")
	private String receiversCorrespondent;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDERS_CORRESPONDENT")
	private String sendersCorrespondent;

	@Column(name="SENDING_BANKS_TRN")
	private String sendingBanksTrn;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt400() {
	}

	public SwiftMt400PK getId() {
		return this.id;
	}

	public void setId(SwiftMt400PK id) {
		this.id = id;
	}

	public String getAccountWithBank() {
		return this.accountWithBank;
	}

	public void setAccountWithBank(String accountWithBank) {
		this.accountWithBank = accountWithBank;
	}

	public String getAmountCollected() {
		return this.amountCollected;
	}

	public void setAmountCollected(String amountCollected) {
		this.amountCollected = amountCollected;
	}

	public String getBeneficiaryBank() {
		return this.beneficiaryBank;
	}

	public void setBeneficiaryBank(String beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
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

	public String getDetailsOfAmountsAdded() {
		return this.detailsOfAmountsAdded;
	}

	public void setDetailsOfAmountsAdded(String detailsOfAmountsAdded) {
		this.detailsOfAmountsAdded = detailsOfAmountsAdded;
	}

	public String getDetailsOfCharges() {
		return this.detailsOfCharges;
	}

	public void setDetailsOfCharges(String detailsOfCharges) {
		this.detailsOfCharges = detailsOfCharges;
	}

	public String getOrderingBank() {
		return this.orderingBank;
	}

	public void setOrderingBank(String orderingBank) {
		this.orderingBank = orderingBank;
	}

	public String getProceedsRemitted() {
		return this.proceedsRemitted;
	}

	public void setProceedsRemitted(String proceedsRemitted) {
		this.proceedsRemitted = proceedsRemitted;
	}

	public String getReceiversCorrespondent() {
		return this.receiversCorrespondent;
	}

	public void setReceiversCorrespondent(String receiversCorrespondent) {
		this.receiversCorrespondent = receiversCorrespondent;
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

	public String getSendersCorrespondent() {
		return this.sendersCorrespondent;
	}

	public void setSendersCorrespondent(String sendersCorrespondent) {
		this.sendersCorrespondent = sendersCorrespondent;
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