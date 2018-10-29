package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT496 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT496", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt496.findAll", query="SELECT s FROM SwiftMt496 s")
public class SwiftMt496 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt496PK id;

	@Column(name="ANSWERS")
	private String answers;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="MT_AND_DATE_OF_THE_ORIGINAL_MESSAGE")
	private String mtAndDateOfTheOriginalMessage;

	@Column(name="NARRATIVE")
	private String narrative;

	@Column(name="NARRATIVE_DESCRIPTION_OF_THE_ORIGINAL_MESSAGE_TO_WHICH_THE_ANSWER_RELATES")
	private String narrativeDescriptionOfTheOriginalMessageToWhichTheAnswerRelates;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	public SwiftMt496() {
	}

	public SwiftMt496PK getId() {
		return this.id;
	}

	public void setId(SwiftMt496PK id) {
		this.id = id;
	}

	public String getAnswers() {
		return this.answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
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

	public String getMtAndDateOfTheOriginalMessage() {
		return this.mtAndDateOfTheOriginalMessage;
	}

	public void setMtAndDateOfTheOriginalMessage(String mtAndDateOfTheOriginalMessage) {
		this.mtAndDateOfTheOriginalMessage = mtAndDateOfTheOriginalMessage;
	}

	public String getNarrative() {
		return this.narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public String getNarrativeDescriptionOfTheOriginalMessageToWhichTheAnswerRelates() {
		return this.narrativeDescriptionOfTheOriginalMessageToWhichTheAnswerRelates;
	}

	public void setNarrativeDescriptionOfTheOriginalMessageToWhichTheAnswerRelates(String narrativeDescriptionOfTheOriginalMessageToWhichTheAnswerRelates) {
		this.narrativeDescriptionOfTheOriginalMessageToWhichTheAnswerRelates = narrativeDescriptionOfTheOriginalMessageToWhichTheAnswerRelates;
	}

	public String getRelatedReference() {
		return this.relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTransactionReferenceNumber() {
		return this.transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}

}