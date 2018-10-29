package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT721 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT721", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt721.findAll", query="SELECT s FROM SwiftMt721 s")
public class SwiftMt721 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt721PK id;

	@Column(name="ADDITIONAL_CONDITIONS")
	private String additionalConditions;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="DESCRIPTION_OF_GOODS_AND_OR_SERVICES")
	private String descriptionOfGoodsAndOrServices;

	@Column(name="DOCUMENTARY_CREDIT_NUMBER")
	private String documentaryCreditNumber;

	@Column(name="DOCUMENTS_REQUIRED")
	private String documentsRequired;

	@Column(name="SEQUENCE_OF_TOTAL")
	private String sequenceOfTotal;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSFERRING_BANKS_REFERENCE")
	private String transferringBanksReference;
	
	@Column(name="SPECIAL_PAYMENT_CONDITIONS_FOR_BENEFICIARY")
	private String specialPaymentConditionsForBeneficiary;
	
	@Column(name="SPECIAL_PAYMENT_CONDITIONS_FOR_RECEIVING_BANK")
	private String specialPaymentConditionsForReceivingBank;
	
	public SwiftMt721() {
	}

	public SwiftMt721PK getId() {
		return this.id;
	}

	public void setId(SwiftMt721PK id) {
		this.id = id;
	}

	public String getAdditionalConditions() {
		return this.additionalConditions;
	}

	public void setAdditionalConditions(String additionalConditions) {
		this.additionalConditions = additionalConditions;
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

	public String getDescriptionOfGoodsAndOrServices() {
		return this.descriptionOfGoodsAndOrServices;
	}

	public void setDescriptionOfGoodsAndOrServices(String descriptionOfGoodsAndOrServices) {
		this.descriptionOfGoodsAndOrServices = descriptionOfGoodsAndOrServices;
	}

	public String getDocumentaryCreditNumber() {
		return this.documentaryCreditNumber;
	}

	public void setDocumentaryCreditNumber(String documentaryCreditNumber) {
		this.documentaryCreditNumber = documentaryCreditNumber;
	}

	public String getDocumentsRequired() {
		return this.documentsRequired;
	}

	public void setDocumentsRequired(String documentsRequired) {
		this.documentsRequired = documentsRequired;
	}

	public String getSequenceOfTotal() {
		return this.sequenceOfTotal;
	}

	public void setSequenceOfTotal(String sequenceOfTotal) {
		this.sequenceOfTotal = sequenceOfTotal;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTransferringBanksReference() {
		return this.transferringBanksReference;
	}

	public void setTransferringBanksReference(String transferringBanksReference) {
		this.transferringBanksReference = transferringBanksReference;
	}

	public String getSpecialPaymentConditionsForBeneficiary() {
		return specialPaymentConditionsForBeneficiary;
	}

	public void setSpecialPaymentConditionsForBeneficiary(String specialPaymentConditionsForBeneficiary) {
		this.specialPaymentConditionsForBeneficiary = specialPaymentConditionsForBeneficiary;
	}

	public String getSpecialPaymentConditionsForReceivingBank() {
		return specialPaymentConditionsForReceivingBank;
	}

	public void setSpecialPaymentConditionsForReceivingBank(String specialPaymentConditionsForReceivingBank) {
		this.specialPaymentConditionsForReceivingBank = specialPaymentConditionsForReceivingBank;
	}

}