package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT798 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT798", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt798.findAll", query="SELECT s FROM SwiftMt798 s")
public class SwiftMt798 implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt798PK id;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Proprietary_Message")
	private String proprietary_Message;

	@Column(name="Sub_Message_Type")
	private String sub_Message_Type;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	public SwiftMt798() {
	}

	public SwiftMt798PK getId() {
		return this.id;
	}

	public void setId(SwiftMt798PK id) {
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

	public String getProprietary_Message() {
		return this.proprietary_Message;
	}

	public void setProprietary_Message(String proprietary_Message) {
		this.proprietary_Message = proprietary_Message;
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
