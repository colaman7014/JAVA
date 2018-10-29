package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT105 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT105", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt105.findAll", query="SELECT s FROM SwiftMt105 s")
public class SwiftMt105 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt105PK id;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="EDIFACT_Message")
	private String EDIFACT_Message;

	@Column(name="Related_Reference")
	private String related_Reference;

	@Column(name="Sequence_of_Total")
	private String sequence_of_Total;

	@Column(name="Sub_Message_Type")
	private String sub_Message_Type;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	public SwiftMt105() {
	}

	public SwiftMt105PK getId() {
		return this.id;
	}

	public void setId(SwiftMt105PK id) {
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

	public String getEDIFACT_Message() {
		return this.EDIFACT_Message;
	}

	public void setEDIFACT_Message(String EDIFACT_Message) {
		this.EDIFACT_Message = EDIFACT_Message;
	}

	public String getRelated_Reference() {
		return this.related_Reference;
	}

	public void setRelated_Reference(String related_Reference) {
		this.related_Reference = related_Reference;
	}

	public String getSequence_of_Total() {
		return this.sequence_of_Total;
	}

	public void setSequence_of_Total(String sequence_of_Total) {
		this.sequence_of_Total = sequence_of_Total;
	}

	public String getSub_Message_Type() {
		return this.sub_Message_Type;
	}

	public void setSub_Message_Type(String sub_Message_Type) {
		this.sub_Message_Type = sub_Message_Type;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTransaction_Reference_Number() {
		return this.transaction_Reference_Number;
	}

	public void setTransaction_Reference_Number(String transaction_Reference_Number) {
		this.transaction_Reference_Number = transaction_Reference_Number;
	}

}