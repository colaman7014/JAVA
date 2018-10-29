package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT420 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT420", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt420.findAll", query="SELECT s FROM SwiftMt420 s")
public class SwiftMt420 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt420PK id;

	@Column(name="Amount_Traced")
	private String amountTraced;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_of_Collection_Instruction")
	private String dateOfCollectionInstruction;

	@Column(name="Drawee")
	private String drawee;

	@Column(name="Related_Reference")
	private String relatedReference;

	@Column(name="Sender_to_Receiver_Information")
	private String senderToReceiverInformation;

	@Column(name="Sending_Banks_TRN")
	private String sendingBanksTRN;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt420() {
	}

	public SwiftMt420PK getId() {
		return id;
	}

	public void setId(SwiftMt420PK id) {
		this.id = id;
	}

	public String getAmountTraced() {
		return amountTraced;
	}

	public void setAmountTraced(String amountTraced) {
		this.amountTraced = amountTraced;
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

	public String getDateOfCollectionInstruction() {
		return dateOfCollectionInstruction;
	}

	public void setDateOfCollectionInstruction(String dateOfCollectionInstruction) {
		this.dateOfCollectionInstruction = dateOfCollectionInstruction;
	}

	public String getDrawee() {
		return drawee;
	}

	public void setDrawee(String drawee) {
		this.drawee = drawee;
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