package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT604 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT604", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt604.findAll", query="SELECT s FROM SwiftMt604 s")
public class SwiftMt604 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt604PK id;

	@Column(name="BENEFICIARY_OF_THE_COMMODITY")
	private String beneficiaryOfTheCommodity;

	@Column(name="[CERTIFICATE_NUMBER(S)_AND_OR_BAR_NUMBER(S)]")
	private String certificateNumber_s_AndOrBarNumber_s_;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="FURTHER_ACCOUNT_IDENTIFICATION")
	private String furtherAccountIdentification;

	@Column(name="FURTHER_IDENTIFICATION")
	private String furtherIdentification;

	@Column(name="IDENTIFICATION_OF_THE_COMMODITY_AND_THE_COMMODITY_ACCOUNT")
	private String identificationOfTheCommodityAndTheCommodityAccount;

	@Column(name="INSTRUCTING_PARTY")
	private String instructingParty;

	@Column(name="INTERMEDIARY")
	private String intermediary;

	@Column(name="QUANTITY_OF_COMMODITY")
	private String quantityOfCommodity;

	@Column(name="RECEIVER_OF_THE_COMMODITY")
	private String receiverOfTheCommodity;

	@Column(name="RELATED_REFERENCE")
	private String relatedReference;

	@Column(name="SENDER_TO_RECEIVER_INFORMATION")
	private String senderToReceiverInformation;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSACTION_REFERENCE_NUMBER")
	private String transactionReferenceNumber;

	@Column(name="VALUE_DATE")
	private String valueDate;

	public SwiftMt604() {
	}

	public SwiftMt604PK getId() {
		return this.id;
	}

	public void setId(SwiftMt604PK id) {
		this.id = id;
	}

	public String getBeneficiaryOfTheCommodity() {
		return this.beneficiaryOfTheCommodity;
	}

	public void setBeneficiaryOfTheCommodity(String beneficiaryOfTheCommodity) {
		this.beneficiaryOfTheCommodity = beneficiaryOfTheCommodity;
	}

	public String getCertificateNumber_s_AndOrBarNumber_s_() {
		return this.certificateNumber_s_AndOrBarNumber_s_;
	}

	public void setCertificateNumber_s_AndOrBarNumber_s_(String certificateNumber_s_AndOrBarNumber_s_) {
		this.certificateNumber_s_AndOrBarNumber_s_ = certificateNumber_s_AndOrBarNumber_s_;
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

	public String getFurtherAccountIdentification() {
		return this.furtherAccountIdentification;
	}

	public void setFurtherAccountIdentification(String furtherAccountIdentification) {
		this.furtherAccountIdentification = furtherAccountIdentification;
	}

	public String getFurtherIdentification() {
		return this.furtherIdentification;
	}

	public void setFurtherIdentification(String furtherIdentification) {
		this.furtherIdentification = furtherIdentification;
	}

	public String getIdentificationOfTheCommodityAndTheCommodityAccount() {
		return this.identificationOfTheCommodityAndTheCommodityAccount;
	}

	public void setIdentificationOfTheCommodityAndTheCommodityAccount(String identificationOfTheCommodityAndTheCommodityAccount) {
		this.identificationOfTheCommodityAndTheCommodityAccount = identificationOfTheCommodityAndTheCommodityAccount;
	}

	public String getInstructingParty() {
		return this.instructingParty;
	}

	public void setInstructingParty(String instructingParty) {
		this.instructingParty = instructingParty;
	}

	public String getIntermediary() {
		return this.intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public String getQuantityOfCommodity() {
		return this.quantityOfCommodity;
	}

	public void setQuantityOfCommodity(String quantityOfCommodity) {
		this.quantityOfCommodity = quantityOfCommodity;
	}

	public String getReceiverOfTheCommodity() {
		return this.receiverOfTheCommodity;
	}

	public void setReceiverOfTheCommodity(String receiverOfTheCommodity) {
		this.receiverOfTheCommodity = receiverOfTheCommodity;
	}

	public String getRelatedReference() {
		return this.relatedReference;
	}

	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}

	public String getSenderToReceiverInformation() {
		return this.senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
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

	public String getValueDate() {
		return this.valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

}