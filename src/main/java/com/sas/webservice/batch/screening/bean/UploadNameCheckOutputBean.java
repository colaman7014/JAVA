package com.sas.webservice.batch.screening.bean;

import java.math.BigDecimal;

public class UploadNameCheckOutputBean {
	private String csvId;
	private String csvName;
	private String partyIdentificationId;
	private String partyName;
	private String partyName2;
	private BigDecimal score;
	
	private String transactionKey;
	private String transactionName;
	private String transactionNo;
	private String trancactionDate;
	private String trancactionType;
	private BigDecimal trancactionAmount;
	
	
	public String getCsvId() {
		return csvId;
	}
	public void setCsvId(String csvId) {
		this.csvId = csvId;
	}
	public String getCsvName() {
		return csvName;
	}
	public void setCsvName(String csvName) {
		this.csvName = csvName;
	}
	public String getPartyIdentificationId() {
		return partyIdentificationId;
	}
	public void setPartyIdentificationId(String partyIdentificationId) {
		this.partyIdentificationId = partyIdentificationId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyName2() {
		return partyName2;
	}
	public void setPartyName2(String partyName2) {
		this.partyName2 = partyName2;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public String getTransactionKey() {
		return transactionKey;
	}
	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getTrancactionDate() {
		return trancactionDate;
	}
	public void setTrancactionDate(String trancactionDate) {
		this.trancactionDate = trancactionDate;
	}
	public String getTrancactionType() {
		return trancactionType;
	}
	public void setTrancactionType(String trancactionType) {
		this.trancactionType = trancactionType;
	}
	public BigDecimal getTrancactionAmount() {
		return trancactionAmount;
	}
	public void setTrancactionAmount(BigDecimal trancactionAmount) {
		this.trancactionAmount = trancactionAmount;
	}
	
	

}
