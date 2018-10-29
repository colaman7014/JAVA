package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT101 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT101", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt101.findAll", query="SELECT s FROM SwiftMt101 s")
public class SwiftMt101 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt101PK id;

	@Column(name="ACCOUNT_SERVICING_INSTITUTION")
	private String accountServicingInstitution;

	@Column(name="ACCOUNT_WITH_INSTITUTION")
	private String accountWithInstitution;

	@Column(name="AUTHORISATION")
	private String authorisation;

	@Column(name="BENEFICIARY")
	private String beneficiary;

	@Column(name="CHARGES_ACCOUNT")
	private String chargesAccount;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="CURRENCY_ORIGINAL_ORDERED_AMOUNT")
	private String currencyOriginalOrderedAmount;

	@Column(name="CURRENCY_TRANSACTION_AMOUNT")
	private String currencyTransactionAmount;

	@Column(name="CUSTOMER_SPECIFIED_REFERENCE")
	private String customerSpecifiedReference;

	@Column(name="DETAILS_OF_CHARGES")
	private String detailsOfCharges;

	@Column(name="EXCHANGE_RATE")
	private String exchangeRate;

	@Column(name="F_X_DEAL_REFERENCE")
	private String fXDealReference;

	@Column(name="INSTRUCTING_PARTY")
	private String instructingParty;

	@Column(name="INSTRUCTION_CODE")
	private String instructionCode;

	@Column(name="INTERMEDIARY")
	private String intermediary;

	@Column(name="MESSAGE_INDEX_TOTAL")
	private String messageIndexTotal;

	@Column(name="ORDERING_CUSTOMER")
	private String orderingCustomer;

	@Column(name="REGULATORY_REPORTING")
	private String regulatoryReporting;

	@Column(name="REMITTANCE_INFORMATION")
	private String remittanceInformation;

	@Column(name="REQUESTED_EXECUTION_DATE")
	private String requestedExecutionDate;

	@Column(name="SENDERS_REFERENCE")
	private String sendersReference;

	@Column(name="SENDING_INSTITUTION")
	private String sendingInstitution;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSACTION_REFERENCE")
	private String transactionReference;

	public SwiftMt101() {
	}

	public SwiftMt101PK getId() {
		return this.id;
	}

	public void setId(SwiftMt101PK id) {
		this.id = id;
	}

	public String getAccountServicingInstitution() {
		return this.accountServicingInstitution;
	}

	public void setAccountServicingInstitution(String accountServicingInstitution) {
		this.accountServicingInstitution = accountServicingInstitution;
	}

	public String getAccountWithInstitution() {
		return this.accountWithInstitution;
	}

	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
	}

	public String getAuthorisation() {
		return this.authorisation;
	}

	public void setAuthorisation(String authorisation) {
		this.authorisation = authorisation;
	}

	public String getBeneficiary() {
		return this.beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getChargesAccount() {
		return this.chargesAccount;
	}

	public void setChargesAccount(String chargesAccount) {
		this.chargesAccount = chargesAccount;
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

	public String getCurrencyOriginalOrderedAmount() {
		return this.currencyOriginalOrderedAmount;
	}

	public void setCurrencyOriginalOrderedAmount(String currencyOriginalOrderedAmount) {
		this.currencyOriginalOrderedAmount = currencyOriginalOrderedAmount;
	}

	public String getCurrencyTransactionAmount() {
		return this.currencyTransactionAmount;
	}

	public void setCurrencyTransactionAmount(String currencyTransactionAmount) {
		this.currencyTransactionAmount = currencyTransactionAmount;
	}

	public String getCustomerSpecifiedReference() {
		return this.customerSpecifiedReference;
	}

	public void setCustomerSpecifiedReference(String customerSpecifiedReference) {
		this.customerSpecifiedReference = customerSpecifiedReference;
	}

	public String getDetailsOfCharges() {
		return this.detailsOfCharges;
	}

	public void setDetailsOfCharges(String detailsOfCharges) {
		this.detailsOfCharges = detailsOfCharges;
	}

	public String getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getFXDealReference() {
		return this.fXDealReference;
	}

	public void setFXDealReference(String fXDealReference) {
		this.fXDealReference = fXDealReference;
	}

	public String getInstructingParty() {
		return this.instructingParty;
	}

	public void setInstructingParty(String instructingParty) {
		this.instructingParty = instructingParty;
	}

	public String getInstructionCode() {
		return this.instructionCode;
	}

	public void setInstructionCode(String instructionCode) {
		this.instructionCode = instructionCode;
	}

	public String getIntermediary() {
		return this.intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public String getMessageIndexTotal() {
		return this.messageIndexTotal;
	}

	public void setMessageIndexTotal(String messageIndexTotal) {
		this.messageIndexTotal = messageIndexTotal;
	}

	public String getOrderingCustomer() {
		return this.orderingCustomer;
	}

	public void setOrderingCustomer(String orderingCustomer) {
		this.orderingCustomer = orderingCustomer;
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

	public String getRequestedExecutionDate() {
		return this.requestedExecutionDate;
	}

	public void setRequestedExecutionDate(String requestedExecutionDate) {
		this.requestedExecutionDate = requestedExecutionDate;
	}

	public String getSendersReference() {
		return this.sendersReference;
	}

	public void setSendersReference(String sendersReference) {
		this.sendersReference = sendersReference;
	}

	public String getSendingInstitution() {
		return this.sendingInstitution;
	}

	public void setSendingInstitution(String sendingInstitution) {
		this.sendingInstitution = sendingInstitution;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTransactionReference() {
		return this.transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

}