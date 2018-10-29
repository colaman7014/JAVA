package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT498 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT498", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt498.findAll", query="SELECT s FROM SwiftMt498 s")
public class SwiftMt498 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt498PK id;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="PROPRIETARY_MESSAGE")
	private String proprietaryMessage;

	@Column(name="SUBMESSAGE_TYPE")
	private String submessageType;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	public SwiftMt498() {
	}

	public SwiftMt498PK getId() {
		return this.id;
	}

	public void setId(SwiftMt498PK id) {
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

	public String getProprietaryMessage() {
		return this.proprietaryMessage;
	}

	public void setProprietaryMessage(String proprietaryMessage) {
		this.proprietaryMessage = proprietaryMessage;
	}

	public String getSubmessageType() {
		return this.submessageType;
	}

	public void setSubmessageType(String submessageType) {
		this.submessageType = submessageType;
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