package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT754 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT754", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt754.findAll", query="SELECT s FROM SwiftMt754 s")
public class SwiftMt754 implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt754PK id;

	@Column(name="Account_With_Bank")
	private String account_With_Bank;

	@Column(name="Additional_Amounts")
	private String additional_Amounts;

	@Column(name="Beneficiary_Bank")
	private String beneficiary_Bank;

	@Column(name="Charges_Added")
	private String charges_Added;

	@Column(name="Charges_Deducted")
	private String charges_Deducted;

	@Column(name="CREATE_BY")
	private String createBy;

	
	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Narrative")
	private String narrative;

	@Column(name="Principal_Amount_Paid_Accepted_Negotiated")
	private String principal_Amount_Paid_Accepted_Negotiated;

	@Column(name="Reimbursing_Bank")
	private String reimbursing_Bank;

	@Column(name="Related_Reference")
	private String related_Reference;

	@Column(name="Sender_to_Receiver_Information")
	private String sender_to_Receiver_Information;

	@Column(name="Senders_Reference")
	private String senders_Reference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Total_Amount_Claimed")
	private String total_Amount_Claimed;

	public SwiftMt754() {
	}

	public SwiftMt754PK getId() {
		return this.id;
	}

	public void setId(SwiftMt754PK id) {
		this.id = id;
	}

	public String getAccount_With_Bank() {
		return this.account_With_Bank;
	}

	public void setAccount_With_Bank(String account_With_Bank) {
		this.account_With_Bank = account_With_Bank;
	}

	public String getAdditional_Amounts() {
		return this.additional_Amounts;
	}

	public void setAdditional_Amounts(String additional_Amounts) {
		this.additional_Amounts = additional_Amounts;
	}

	public String getBeneficiary_Bank() {
		return this.beneficiary_Bank;
	}

	public void setBeneficiary_Bank(String beneficiary_Bank) {
		this.beneficiary_Bank = beneficiary_Bank;
	}

	public String getCharges_Added() {
		return this.charges_Added;
	}

	public void setCharges_Added(String charges_Added) {
		this.charges_Added = charges_Added;
	}

	public String getCharges_Deducted() {
		return this.charges_Deducted;
	}

	public void setCharges_Deducted(String charges_Deducted) {
		this.charges_Deducted = charges_Deducted;
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

	public String getNarrative() {
		return this.narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public String getPrincipal_Amount_Paid_Accepted_Negotiated() {
		return this.principal_Amount_Paid_Accepted_Negotiated;
	}

	public void setPrincipal_Amount_Paid_Accepted_Negotiated(String principal_Amount_Paid_Accepted_Negotiated) {
		this.principal_Amount_Paid_Accepted_Negotiated = principal_Amount_Paid_Accepted_Negotiated;
	}

	public String getReimbursing_Bank() {
		return this.reimbursing_Bank;
	}

	public void setReimbursing_Bank(String reimbursing_Bank) {
		this.reimbursing_Bank = reimbursing_Bank;
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

	public String getSenders_Reference() {
		return this.senders_Reference;
	}

	public void setSenders_Reference(String senders_Reference) {
		this.senders_Reference = senders_Reference;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTotal_Amount_Claimed() {
		return this.total_Amount_Claimed;
	}

	public void setTotal_Amount_Claimed(String total_Amount_Claimed) {
		this.total_Amount_Claimed = total_Amount_Claimed;
	}

}
