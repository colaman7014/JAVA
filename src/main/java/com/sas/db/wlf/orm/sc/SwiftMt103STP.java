package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT103_STP database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT103STP", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt103STP.findAll", query="SELECT s FROM SwiftMt103STP s")
public class SwiftMt103STP implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt103STPPK id;

	@Column(name="Account_With_Institution")
	private String accountWithInstitution;

	@Column(name="Bank_Operation_Code")
	private String bankOperationCode;

	@Column(name="Beneficiary_Customer")
	private String beneficiaryCustomer;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="Currency_Instructed_Amount")
	private String currencyInstructedAmount;

	@Column(name="Details_of_Charges")
	private String detailsofCharges;

	@Column(name="Exchange_Rate")
	private String exchangeRate;

	@Column(name="Instruction_Code")
	private String instructionCode;

	@Column(name="Intermediary_Institution")
	private String intermediaryInstitution;

	@Column(name="Ordering_Customer")
	private String orderingCustomer;

	@Column(name="Ordering_Institution")
	private String orderingInstitution;

	@Column(name="Receivers_Charges")
	private String receiversCharges;

	@Column(name="Receivers_Correspondent")
	private String receiversCorrespondent;

	@Column(name="Regulatory_Reporting")
	private String regulatoryReporting;

	@Column(name="Remittance_Information")
	private String remittanceInformation;

	@Column(name="Sender_to_Receiver_Information")
	private String senderToReceiverInformation;

	@Column(name="Senders_Charges")
	private String sendersCharges;

	@Column(name="Senders_Correspondent")
	private String sendersCorrespondent;

	@Column(name="Senders_Reference")
	private String sendersReference;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="Third_Reimbursement_Institution")
	private String thirdReimbursementInstitution;

	@Column(name="Time_Indication")
	private String timeIndication;

	@Column(name="Transaction_Type_Code")
	private String transactionTypeCode;

	@Column(name="Value_Date_Currency_Interbank_Settled_Amount")
	private String valueDateCurrencyInterbankSettledAmount;

	public SwiftMt103STP() {
	}

	public SwiftMt103STPPK getId() {
		return this.id;
	}

	public void setId(SwiftMt103STPPK id) {
		this.id = id;
	}

	public String getAccountWithInstitution() {
		return this.accountWithInstitution;
	}

	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
	}

	public String getBankOperationCode() {
		return this.bankOperationCode;
	}

	public void setBankOperationCode(String bankOperationCode) {
		this.bankOperationCode = bankOperationCode;
	}

	public String getBeneficiaryCustomer() {
		return this.beneficiaryCustomer;
	}

	public void setBeneficiaryCustomer(String beneficiaryCustomer) {
		this.beneficiaryCustomer = beneficiaryCustomer;
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

	public String getCurrencyInstructedAmount() {
		return this.currencyInstructedAmount;
	}

	public void setCurrencyInstructedAmount(String currencyInstructedAmount) {
		this.currencyInstructedAmount = currencyInstructedAmount;
	}

	public String getDetailsofCharges() {
		return this.detailsofCharges;
	}

	public void setDetailsofCharges(String detailsofCharges) {
		this.detailsofCharges = detailsofCharges;
	}

	public String getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getInstructionCode() {
		return this.instructionCode;
	}

	public void setInstructionCode(String instructionCode) {
		this.instructionCode = instructionCode;
	}

	public String getIntermediaryInstitution() {
		return this.intermediaryInstitution;
	}

	public void setIntermediaryInstitution(String intermediaryInstitution) {
		this.intermediaryInstitution = intermediaryInstitution;
	}

	public String getOrderingCustomer() {
		return this.orderingCustomer;
	}

	public void setOrderingCustomer(String orderingCustomer) {
		this.orderingCustomer = orderingCustomer;
	}

	public String getOrderingInstitution() {
		return this.orderingInstitution;
	}

	public void setOrderingInstitution(String orderingInstitution) {
		this.orderingInstitution = orderingInstitution;
	}

	public String getReceiversCharges() {
		return this.receiversCharges;
	}

	public void setReceiversCharges(String receiversCharges) {
		this.receiversCharges = receiversCharges;
	}

	public String getReceiversCorrespondent() {
		return this.receiversCorrespondent;
	}

	public void setReceiversCorrespondent(String receiversCorrespondent) {
		this.receiversCorrespondent = receiversCorrespondent;
	}

	public String getRegulatoryReporting() {
		return this.regulatoryReporting;
	}

	public void setRegulatoryReporting(String regulatoryReporting) {
		this.regulatoryReporting = regulatoryReporting;
	}

	public String getRemittanceInformation() {
		return this.remittanceInformation;
	}

	public void setRemittanceInformation(String remittanceInformation) {
		this.remittanceInformation = remittanceInformation;
	}

	public String getSenderToReceiverInformation() {
		return this.senderToReceiverInformation;
	}

	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}

	public String getSendersCharges() {
		return this.sendersCharges;
	}

	public void setSendersCharges(String sendersCharges) {
		this.sendersCharges = sendersCharges;
	}

	public String getSendersCorrespondent() {
		return this.sendersCorrespondent;
	}

	public void setSendersCorrespondent(String sendersCorrespondent) {
		this.sendersCorrespondent = sendersCorrespondent;
	}

	public String getSendersReference() {
		return this.sendersReference;
	}

	public void setSendersReference(String sendersReference) {
		this.sendersReference = sendersReference;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getThirdReimbursementInstitution() {
		return this.thirdReimbursementInstitution;
	}

	public void setThirdReimbursementInstitution(String thirdReimbursementInstitution) {
		this.thirdReimbursementInstitution = thirdReimbursementInstitution;
	}

	public String getTimeIndication() {
		return this.timeIndication;
	}

	public void setTimeIndication(String timeIndication) {
		this.timeIndication = timeIndication;
	}

	public String getTransactionTypeCode() {
		return this.transactionTypeCode;
	}

	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	public String getValueDateCurrencyInterbankSettledAmount() {
		return this.valueDateCurrencyInterbankSettledAmount;
	}

	public void setValueDateCurrencyInterbankSettledAmount(String valueDateCurrencyInterbankSettledAmount) {
		this.valueDateCurrencyInterbankSettledAmount = valueDateCurrencyInterbankSettledAmount;
	}

}