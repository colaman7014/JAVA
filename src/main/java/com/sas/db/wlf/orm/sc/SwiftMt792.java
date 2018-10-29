package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT792 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT792", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt792.findAll", query="SELECT s FROM SwiftMt792 s")
public class SwiftMt792 implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt792PK id;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	private String MT_and_Date_of_the_Original_Message;

	@Column(name="Narrative_Description_of_the_Original_Message")
	private String narrative_Description_of_the_Original_Message;

	@Column(name="Related_Reference")
	private String related_Reference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	public SwiftMt792() {
	}

	public SwiftMt792PK getId() {
		return this.id;
	}

	public void setId(SwiftMt792PK id) {
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

	public String getMT_and_Date_of_the_Original_Message() {
		return this.MT_and_Date_of_the_Original_Message;
	}

	public void setMT_and_Date_of_the_Original_Message(String MT_and_Date_of_the_Original_Message) {
		this.MT_and_Date_of_the_Original_Message = MT_and_Date_of_the_Original_Message;
	}

	public String getNarrative_Description_of_the_Original_Message() {
		return this.narrative_Description_of_the_Original_Message;
	}

	public void setNarrative_Description_of_the_Original_Message(String narrative_Description_of_the_Original_Message) {
		this.narrative_Description_of_the_Original_Message = narrative_Description_of_the_Original_Message;
	}

	public String getRelated_Reference() {
		return this.related_Reference;
	}

	public void setRelated_Reference(String related_Reference) {
		this.related_Reference = related_Reference;
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
