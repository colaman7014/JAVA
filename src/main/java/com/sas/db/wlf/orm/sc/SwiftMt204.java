package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT204 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT204", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt204.findAll", query="SELECT s FROM SwiftMt204 s")
public class SwiftMt204 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt204PK id;

	@Column(name="Account_With_Institution")
	private String account_With_Institution;

	@Column(name="Beneficiary_Institution")
	private String beneficiary_Institution;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Debit_Institution")
	private String debit_Institution;

	@Column(name="Related_Reference")
	private String related_Reference;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Sum_of_Amounts")
	private String sum_of_Amounts;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Amount")
	private String transaction_Amount;

	@Column(name="Transaction_Reference_Number")
	private String transaction_Reference_Number;

	@Column(name="Value_Date")
	private String value_Date;

	public SwiftMt204() {
	}

	public SwiftMt204PK getId() {
		return this.id;
	}

	public void setId(SwiftMt204PK id) {
		this.id = id;
	}

	public String getAccount_With_Institution() {
		return this.account_With_Institution;
	}

	public void setAccount_With_Institution(String account_With_Institution) {
		this.account_With_Institution = account_With_Institution;
	}

	public String getBeneficiary_Institution() {
		return this.beneficiary_Institution;
	}

	public void setBeneficiary_Institution(String beneficiary_Institution) {
		this.beneficiary_Institution = beneficiary_Institution;
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

	public String getDebit_Institution() {
		return this.debit_Institution;
	}

	public void setDebit_Institution(String debit_Institution) {
		this.debit_Institution = debit_Institution;
	}

	public String getRelated_Reference() {
		return this.related_Reference;
	}

	public void setRelated_Reference(String related_Reference) {
		this.related_Reference = related_Reference;
	}

	public String getSender_to_Receiver_Information() {
		return this.sender_to_Receiver_Information;
	}

	public void setSender_to_Receiver_Information(String sender_to_Receiver_Information) {
		this.sender_to_Receiver_Information = sender_to_Receiver_Information;
	}

	public String getSum_of_Amounts() {
		return this.sum_of_Amounts;
	}

	public void setSum_of_Amounts(String sum_of_Amounts) {
		this.sum_of_Amounts = sum_of_Amounts;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTransaction_Amount() {
		return this.transaction_Amount;
	}

	public void setTransaction_Amount(String transaction_Amount) {
		this.transaction_Amount = transaction_Amount;
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