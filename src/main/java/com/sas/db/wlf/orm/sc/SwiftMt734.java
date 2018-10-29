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
 * The persistent class for the SWIFT_MT734 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT734", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt734.findAll", query="SELECT s FROM SwiftMt734 s")
public class SwiftMt734 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt734PK id;

	@Column(name="ACCOUNT_WITH_BANK")
	private String accountWithBank;

	@Column(name="CHARGES_CLAIMED")
	private String chargesClaimed;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DATE_AND_AMOUNT_OF_UTILISATION")
	private String dateAndAmountOfUtilisation;

	@Column(name="DISCREPANCIES")
	private String discrepancies;

	@Column(name="DISPOSAL_OF_DOCUMENTS")
	private String disposalOfDocuments;

	@Column(name="PRESENTING_BANKS_REFERENCE")
	private String presentingBanksReference;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDERS_TRN")
	private String sendersTrn;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TOTAL_AMOUNT_CLAIMED")
	private String totalAmountClaimed;

	public SwiftMt734() {
	}

	public SwiftMt734PK getId() {
		return this.id;
	}

	public void setId(SwiftMt734PK id) {
		this.id = id;
	}

	public String getAccountWithBank() {
		return this.accountWithBank;
	}

	public void setAccountWithBank(String accountWithBank) {
		this.accountWithBank = accountWithBank;
	}

	public String getChargesClaimed() {
		return this.chargesClaimed;
	}

	public void setChargesClaimed(String chargesClaimed) {
		this.chargesClaimed = chargesClaimed;
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

	public String getDateAndAmountOfUtilisation() {
		return this.dateAndAmountOfUtilisation;
	}

	public void setDateAndAmountOfUtilisation(String dateAndAmountOfUtilisation) {
		this.dateAndAmountOfUtilisation = dateAndAmountOfUtilisation;
	}

	public String getDiscrepancies() {
		return this.discrepancies;
	}

	public void setDiscrepancies(String discrepancies) {
		this.discrepancies = discrepancies;
	}

	public String getDisposalOfDocuments() {
		return this.disposalOfDocuments;
	}

	public void setDisposalOfDocuments(String disposalOfDocuments) {
		this.disposalOfDocuments = disposalOfDocuments;
	}

	public String getPresentingBanksReference() {
		return this.presentingBanksReference;
	}

	public void setPresentingBanksReference(String presentingBanksReference) {
		this.presentingBanksReference = presentingBanksReference;
	}

	public String getSenderToReceiverInformation() {
		return this.senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSendersTrn() {
		return this.sendersTrn;
	}

	public void setSendersTrn(String sendersTrn) {
		this.sendersTrn = sendersTrn;
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

}