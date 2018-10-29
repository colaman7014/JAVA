package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT701 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT701", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt701.findAll", query="SELECT s FROM SwiftMt701 s")
public class SwiftMt701 implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt701PK id;

	@Column(name="Additional_Conditions")
	private String additional_Conditions;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Description_of_Goods_and_or_Services")
	private String description_of_Goods_and_or_Services;

	@Column(name="Documentary_Credit_Number")
	private String documentary_Credit_Number;

	@Column(name="Documents_Required")
	private String documents_Required;

	@Column(name="Sequence_of_Total")
	private String sequence_of_Total;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	public SwiftMt701() {
	}

	public SwiftMt701PK getId() {
		return this.id;
	}

	public void setId(SwiftMt701PK id) {
		this.id = id;
	}

	public String getAdditional_Conditions() {
		return this.additional_Conditions;
	}

	public void setAdditional_Conditions(String additional_Conditions) {
		this.additional_Conditions = additional_Conditions;
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

	public String getDescription_of_Goods_and_or_Services() {
		return this.description_of_Goods_and_or_Services;
	}

	public void setDescription_of_Goods_and_or_Services(String description_of_Goods_and_or_Services) {
		this.description_of_Goods_and_or_Services = description_of_Goods_and_or_Services;
	}

	public String getDocumentary_Credit_Number() {
		return this.documentary_Credit_Number;
	}

	public void setDocumentary_Credit_Number(String documentary_Credit_Number) {
		this.documentary_Credit_Number = documentary_Credit_Number;
	}

	public String getDocuments_Required() {
		return this.documents_Required;
	}

	public void setDocuments_Required(String documents_Required) {
		this.documents_Required = documents_Required;
	}

	public String getSequence_of_Total() {
		return this.sequence_of_Total;
	}

	public void setSequence_of_Total(String sequence_of_Total) {
		this.sequence_of_Total = sequence_of_Total;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

}
