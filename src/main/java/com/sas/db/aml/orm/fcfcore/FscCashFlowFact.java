package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the FSC_CASH_FLOW_FACT database table.
 * 
 */
@Entity
@Table(name="FSC_CASH_FLOW_FACT")
@NamedQuery(name="FscCashFlowFact.findAll", query="SELECT f FROM FscCashFlowFact f")
public class FscCashFlowFact implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FscCashFlowFactPK id;

	@Column(name="account_key")
	private BigDecimal accountKey;

	@Column(name="associate_key")
	private BigDecimal associateKey;

	@Column(name="beneficiary_ext_party_key")
	private BigDecimal beneficiaryExtPartyKey;

	@Column(name="branch_key")
	private BigDecimal branchKey;

	@Column(name="country_key")
	private BigDecimal countryKey;

	@Column(name="currency_amount")
	private BigDecimal currencyAmount;

	@Column(name="currency_amount_in_account_ccy")
	private BigDecimal currencyAmountInAccountCcy;

	@Column(name="currency_amount_in_txn_ccy")
	private BigDecimal currencyAmountInTxnCcy;

	@Column(name="date_key")
	private BigDecimal dateKey;

	@Column(name="executing_ext_party_key")
	private BigDecimal executingExtPartyKey;

	@Column(name="executing_party_key")
	private BigDecimal executingPartyKey;

	@Column(name="posted_date_key")
	private BigDecimal postedDateKey;

	@Column(name="related_ind")
	private String relatedInd;

	@Column(name="remitter_ext_party_key")
	private BigDecimal remitterExtPartyKey;

	@Column(name="secondary_account_key")
	private BigDecimal secondaryAccountKey;


	@Column(name="third_party_ind")
	private String thirdPartyInd;

	@Column(name="time_key")
	private BigDecimal timeKey;

	@Column(name="transaction_currency_key")
	private BigDecimal transactionCurrencyKey;


	@Column(name="transaction_status_key")
	private BigDecimal transactionStatusKey;

	@Column(name="transaction_type_key")
	private BigDecimal transactionTypeKey;

	public FscCashFlowFact() {
	}

	public BigDecimal getAccountKey() {
		return this.accountKey;
	}

	public void setAccountKey(BigDecimal accountKey) {
		this.accountKey = accountKey;
	}

	public BigDecimal getAssociateKey() {
		return this.associateKey;
	}

	public void setAssociateKey(BigDecimal associateKey) {
		this.associateKey = associateKey;
	}

	public BigDecimal getBeneficiaryExtPartyKey() {
		return this.beneficiaryExtPartyKey;
	}

	public void setBeneficiaryExtPartyKey(BigDecimal beneficiaryExtPartyKey) {
		this.beneficiaryExtPartyKey = beneficiaryExtPartyKey;
	}

	public BigDecimal getBranchKey() {
		return this.branchKey;
	}

	public void setBranchKey(BigDecimal branchKey) {
		this.branchKey = branchKey;
	}

	public BigDecimal getCountryKey() {
		return this.countryKey;
	}

	public void setCountryKey(BigDecimal countryKey) {
		this.countryKey = countryKey;
	}

	public BigDecimal getCurrencyAmount() {
		return this.currencyAmount;
	}

	public void setCurrencyAmount(BigDecimal currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public BigDecimal getCurrencyAmountInAccountCcy() {
		return this.currencyAmountInAccountCcy;
	}

	public void setCurrencyAmountInAccountCcy(BigDecimal currencyAmountInAccountCcy) {
		this.currencyAmountInAccountCcy = currencyAmountInAccountCcy;
	}

	public BigDecimal getCurrencyAmountInTxnCcy() {
		return this.currencyAmountInTxnCcy;
	}

	public void setCurrencyAmountInTxnCcy(BigDecimal currencyAmountInTxnCcy) {
		this.currencyAmountInTxnCcy = currencyAmountInTxnCcy;
	}

	public BigDecimal getDateKey() {
		return this.dateKey;
	}

	public void setDateKey(BigDecimal dateKey) {
		this.dateKey = dateKey;
	}

	public BigDecimal getExecutingExtPartyKey() {
		return this.executingExtPartyKey;
	}

	public void setExecutingExtPartyKey(BigDecimal executingExtPartyKey) {
		this.executingExtPartyKey = executingExtPartyKey;
	}

	public BigDecimal getExecutingPartyKey() {
		return this.executingPartyKey;
	}

	public void setExecutingPartyKey(BigDecimal executingPartyKey) {
		this.executingPartyKey = executingPartyKey;
	}

	public BigDecimal getPostedDateKey() {
		return this.postedDateKey;
	}

	public void setPostedDateKey(BigDecimal postedDateKey) {
		this.postedDateKey = postedDateKey;
	}

	public String getRelatedInd() {
		return this.relatedInd;
	}

	public void setRelatedInd(String relatedInd) {
		this.relatedInd = relatedInd;
	}

	public BigDecimal getRemitterExtPartyKey() {
		return this.remitterExtPartyKey;
	}

	public void setRemitterExtPartyKey(BigDecimal remitterExtPartyKey) {
		this.remitterExtPartyKey = remitterExtPartyKey;
	}

	public BigDecimal getSecondaryAccountKey() {
		return this.secondaryAccountKey;
	}

	public void setSecondaryAccountKey(BigDecimal secondaryAccountKey) {
		this.secondaryAccountKey = secondaryAccountKey;
	}

	public String getThirdPartyInd() {
		return this.thirdPartyInd;
	}

	public void setThirdPartyInd(String thirdPartyInd) {
		this.thirdPartyInd = thirdPartyInd;
	}

	public BigDecimal getTimeKey() {
		return this.timeKey;
	}

	public void setTimeKey(BigDecimal timeKey) {
		this.timeKey = timeKey;
	}

	public BigDecimal getTransactionCurrencyKey() {
		return this.transactionCurrencyKey;
	}

	public void setTransactionCurrencyKey(BigDecimal transactionCurrencyKey) {
		this.transactionCurrencyKey = transactionCurrencyKey;
	}

	public BigDecimal getTransactionStatusKey() {
		return this.transactionStatusKey;
	}

	public void setTransactionStatusKey(BigDecimal transactionStatusKey) {
		this.transactionStatusKey = transactionStatusKey;
	}

	public BigDecimal getTransactionTypeKey() {
		return this.transactionTypeKey;
	}

	public void setTransactionTypeKey(BigDecimal transactionTypeKey) {
		this.transactionTypeKey = transactionTypeKey;
	}

}