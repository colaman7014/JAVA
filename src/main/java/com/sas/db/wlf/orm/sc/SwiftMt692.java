package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT692 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT692", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt692.findAll", query="SELECT s FROM SwiftMt692 s")
public class SwiftMt692 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt692PK id;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="MT_AND_DATE_OF_THE_ORIGINAL_MESSAGE")
	private String mtAndDateOfTheOriginalMessage;

	@Column(name="NARRATIVE")
	private String narrative;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	public SwiftMt692() {
	}

	public SwiftMt692PK getId() {
		return this.id;
	}

	public void setId(SwiftMt692PK id) {
		this.id = id;
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