package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;



@Entity
@Table(name="FSC_CURRENCY_DIM", schema=SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA)
@NamedQuery(name="FscCurrencyDim.findAll", query="SELECT a FROM FscCurrencyDim a")
public class FscCurrencyDim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="currency_key")
	private BigDecimal currencyKey;
	
	@Column(name="currency_code")
	private String currencyCode;

	@Column(name="currency_name")
	private String currencyName;

	@Column(name="CURRENCY_KEY_FOR_BOT")
	private String currencyKeyForBot;

	@Column(name="CURRENCY_ZH_NAME")
	private String currencyZhName;

	@Column(name="process_dttm")
	private Timestamp processDttm;

	public BigDecimal getCurrencyKey() {
		return currencyKey;
	}

	public void setCurrencyKey(BigDecimal currencyKey) {
		this.currencyKey = currencyKey;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyKeyForBot() {
		return currencyKeyForBot;
	}

	public void setCurrencyKeyForBot(String currencyKeyForBot) {
		this.currencyKeyForBot = currencyKeyForBot;
	}

	public String getCurrencyZhName() {
		return currencyZhName;
	}

	public void setCurrencyZhName(String currencyZhName) {
		this.currencyZhName = currencyZhName;
	}

	public Timestamp getProcessDttm() {
		return processDttm;
	}

	public void setProcessDttm(Timestamp processDttm) {
		this.processDttm = processDttm;
	}



}