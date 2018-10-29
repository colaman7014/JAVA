package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT200 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT200", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt200.findAll", query="SELECT s FROM SwiftMt200 s")
public class SwiftMt200 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt200PK id;

	@Column(name="Account_With_Institution")
	private String Account_With_Institution;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Intermediary")
	private String intermediary;

	@Column(name="Sender_to_Receiver_Information")
	private String sendertoReceiverInformation;

	@Column(name="Senders_Correspondent")
	private String sendersCorrespondent;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Transaction_Reference_Number")
	private String transactionReferenceNumber;

	@Column(name="Value_Date_Currency_Code_Amount")
	private String valueDateCurrencyCodeAmount;

	public SwiftMt200() {
	}

	public SwiftMt200PK getId() {
		return id;
	}

	public void setId(SwiftMt200PK id) {
		this.id = id;
	}

	
	
//	public String getAccountWithInstitution() {
//		return accountWithInstitution;
//	}
//
//	public void setAccountWithInstitution(String accountWithInstitution) {
//		this.accountWithInstitution = accountWithInstitution;
//	}

	public String getAccount_With_Institution() {
		return Account_With_Institution;
	}

	public void setAccount_With_Institution(String account_With_Institution) {
		Account_With_Institution = account_With_Institution;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getIntermediary() {
		return intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public String getSendertoReceiverInformation() {
		return sendertoReceiverInformation;
	}

	public void setSendertoReceiverInformation(String sendertoReceiverInformation) {
		this.sendertoReceiverInformation = sendertoReceiverInformation;
	}

	public String getSendersCorrespondent() {
		return sendersCorrespondent;
	}

	public void setSendersCorrespondent(String sendersCorrespondent) {
		this.sendersCorrespondent = sendersCorrespondent;
	}

	public String getSwiftFullText() {
		return swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTransactionReferenceNumber() {
		return transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}

	public String getValueDateCurrencyCodeAmount() {
		return valueDateCurrencyCodeAmount;
	}

	public void setValueDateCurrencyCodeAmount(String valueDateCurrencyCodeAmount) {
		this.valueDateCurrencyCodeAmount = valueDateCurrencyCodeAmount;
	}
}