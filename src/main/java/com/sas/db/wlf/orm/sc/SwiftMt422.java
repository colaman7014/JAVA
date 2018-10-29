package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT422 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT422", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt422.findAll", query="SELECT s FROM SwiftMt422 s")
public class SwiftMt422 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt422PK id;

	@Column(name="AMOUNT_OF_COLLECTION")
	private String amountOfCollection;

	@Column(name="ANSWERS")
	private String answers;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="QUERIES")
	private String queries;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt422() {
	}

	public SwiftMt422PK getId() {
		return this.id;
	}

	public void setId(SwiftMt422PK id) {
		this.id = id;
	}

	public String getAmountOfCollection() {
		return this.amountOfCollection;
	}

	public void setAmountOfCollection(String amountOfCollection) {
		this.amountOfCollection = amountOfCollection;
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

	public String getQueries() {
		return this.queries;
	}

	public void setQueries(String queries) {
		this.queries = queries;
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

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

}