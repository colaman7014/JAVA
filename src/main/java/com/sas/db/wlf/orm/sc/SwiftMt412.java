package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT412 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT412", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt412.findAll", query="SELECT s FROM SwiftMt412 s")
public class SwiftMt412 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt412PK id;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Maturity_Date_Currency_Code_Amount_Accepted")
	private String maturityDateCurrencyCodeAmountAccepted;

	@Column(name="Related_Reference")
	private String relatedReference;

	@Column(name="Sender_to_Receiver_Information")
	private String senderToReceiverInformation;

	@Column(name="Sending_Banks_TRN")
	private String sendingBanksTRN;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt412() {
	}

	public SwiftMt412PK getId() {
		return id;
	}

	public void setId(SwiftMt412PK id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getMaturityDateCurrencyCodeAmountAccepted() {
		return maturityDateCurrencyCodeAmountAccepted;
	}

	public void setMaturityDateCurrencyCodeAmountAccepted(
			String maturityDateCurrencyCodeAmountAccepted) {
		this.maturityDateCurrencyCodeAmountAccepted = maturityDateCurrencyCodeAmountAccepted;
	}

	public String getRelatedReference() {
		return relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}

	public String getSenderToReceiverInformation() {
		return senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSendingBanksTRN() {
		return sendingBanksTRN;
	}

	public void setSendingBanksTRN(String sendingBanksTRN) {
		this.sendingBanksTRN = sendingBanksTRN;
	}

	public String getSwiftFullText() {
		return swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

}