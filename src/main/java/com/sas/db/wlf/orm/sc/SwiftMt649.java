package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT649 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT649", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt649.findAll", query="SELECT s FROM SwiftMt649 s")
public class SwiftMt649 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt649PK id;

	@Column(name="BORROWERS")
	private String borrowers;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="FACILITY_AMOUNT")
	private String facilityAmount;

	@Column(name="[FROM]")
	private String from;

	@Column(name="NARRATIVE")
	private String narrative;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SEQUENCE_OF_TOTAL")
	private String sequenceOfTotal;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TO_THE_ATTENTION_OF")
	private String toTheAttentionOf;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	public SwiftMt649() {
	}

	public SwiftMt649PK getId() {
		return this.id;
	}

	public void setId(SwiftMt649PK id) {
		this.id = id;
	}

	public String getBorrowers() {
		return this.borrowers;
	}

	public void setBorrowers(String borrowers) {
		this.borrowers = borrowers;
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

	public String getFacilityAmount() {
		return this.facilityAmount;
	}

	public void setFacilityAmount(String facilityAmount) {
		this.facilityAmount = facilityAmount;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
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

	public String getSequenceOfTotal() {
		return this.sequenceOfTotal;
	}

	public void setSequenceOfTotal(String sequenceOfTotal) {
		this.sequenceOfTotal = sequenceOfTotal;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getToTheAttentionOf() {
		return this.toTheAttentionOf;
	}

	public void setToTheAttentionOf(String toTheAttentionOf) {
		this.toTheAttentionOf = toTheAttentionOf;
	}

	public String getTransactionReferenceNumber() {
		return this.transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}

}