package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT210 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT210", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt210.findAll", query="SELECT s FROM SwiftMt210 s")
public class SwiftMt210 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt210PK id;

	@Column(name="Account_Identification")
	private String account_Identification;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Currency_Code_Amount")
	private String currency_Code_Amount;

	@Column(name="Intermediary")
	private String intermediary;

	@Column(name="Ordering_Customer")
	private String ordering_Customer;

	@Column(name="Ordering_Institution")
	private String ordering_Institution;

	@Column(name="Related_Reference")
	private String related_Reference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	@Column(name="Value_Date")
	private String value_Date;

	public SwiftMt210() {
	}

	public SwiftMt210PK getId() {
		return this.id;
	}

	public void setId(SwiftMt210PK id) {
		this.id = id;
	}

	public String getAccount_Identification() {
		return this.account_Identification;
	}

	public void setAccount_Identification(String account_Identification) {
		this.account_Identification = account_Identification;
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

	public String getCurrency_Code_Amount() {
		return this.currency_Code_Amount;
	}

	public void setCurrency_Code_Amount(String currency_Code_Amount) {
		this.currency_Code_Amount = currency_Code_Amount;
	}

	public String getIntermediary() {
		return this.intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public String getOrdering_Customer() {
		return this.ordering_Customer;
	}

	public void setOrdering_Customer(String ordering_Customer) {
		this.ordering_Customer = ordering_Customer;
	}

	public String getOrdering_Institution() {
		return this.ordering_Institution;
	}

	public void setOrdering_Institution(String ordering_Institution) {
		this.ordering_Institution = ordering_Institution;
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

	public String getValue_Date() {
		return this.value_Date;
	}

	public void setValue_Date(String value_Date) {
		this.value_Date = value_Date;
	}

}