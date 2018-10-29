package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT430 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT430", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt430.findAll", query="SELECT s FROM SwiftMt430 s")
public class SwiftMt430 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt430PK id;

	@Column(name="AMENDED_MATURITY_DATE_CURRENCY_CODE_AMOUNT")
	private String amendedMaturityDateCurrencyCodeAmount;

	@Column(name="AMENDMENTS")
	private String amendments;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DRAWEE")
	private String drawee;

	@Column(name="EXISTING_MATURITY_DATE_CURRENCY_CODE_AMOUNT")
	private String existingMaturityDateCurrencyCodeAmount;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SENDING_BANKS_TRN")
	private String sendingBanksTrn;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt430() {
	}

	public SwiftMt430PK getId() {
		return this.id;
	}

	public void setId(SwiftMt430PK id) {
		this.id = id;
	}

	public String getAmendedMaturityDateCurrencyCodeAmount() {
		return this.amendedMaturityDateCurrencyCodeAmount;
	}

	public void setAmendedMaturityDateCurrencyCodeAmount(String amendedMaturityDateCurrencyCodeAmount) {
		this.amendedMaturityDateCurrencyCodeAmount = amendedMaturityDateCurrencyCodeAmount;
	}

	public String getAmendments() {
		return this.amendments;
	}

	public void setAmendments(String amendments) {
		this.amendments = amendments;
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

	public String getDrawee() {
		return this.drawee;
	}

	public void setDrawee(String drawee) {
		this.drawee = drawee;
	}

	public String getExistingMaturityDateCurrencyCodeAmount() {
		return this.existingMaturityDateCurrencyCodeAmount;
	}

	public void setExistingMaturityDateCurrencyCodeAmount(String existingMaturityDateCurrencyCodeAmount) {
		this.existingMaturityDateCurrencyCodeAmount = existingMaturityDateCurrencyCodeAmount;
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