package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT112 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT112", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt112.findAll", query="SELECT s FROM SwiftMt112 s")
public class SwiftMt112 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt112PK id;

	@Column(name="Amount")
	private String amount;

	@Column(name="Answers")
	private String answers;

	@Column(name="Cheque_Number")
	private String cheque_Number;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Date_of_Issue")
	private String date_of_Issue;

	@Column(name="Drawer_Bank")
	private String drawer_Bank;

	@Column(name="Payee")
	private String payee;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	public SwiftMt112() {
	}

	public SwiftMt112PK getId() {
		return this.id;
	}

	public void setId(SwiftMt112PK id) {
		this.id = id;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAnswers() {
		return this.answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public String getCheque_Number() {
		return this.cheque_Number;
	}

	public void setCheque_Number(String cheque_Number) {
		this.cheque_Number = cheque_Number;
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

	public String getDate_of_Issue() {
		return this.date_of_Issue;
	}

	public void setDate_of_Issue(String date_of_Issue) {
		this.date_of_Issue = date_of_Issue;
	}

	public String getDrawer_Bank() {
		return this.drawer_Bank;
	}

	public void setDrawer_Bank(String drawer_Bank) {
		this.drawer_Bank = drawer_Bank;
	}

	public String getPayee() {
		return this.payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
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