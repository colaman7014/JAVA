package com.sas.webservice.nameCheck.bean;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FtpRemittanceNameCheckInputDetailBean {

	private String section;
	private String branchNumber;
	private String batchNo;
	private String seqNo;
	private String transactionDate;
	private String beneficiaryBank;
	private String transactionType;
	private String beneficiaryAccount;
	private String beneficiaryName;
	private String transactionAmount;
	private String note;
	private String fee;
	private String uniformNum;
	private String uniformName;

	
	
	public FtpRemittanceNameCheckInputDetailBean() {
	}

	public FtpRemittanceNameCheckInputDetailBean(String[] strList) {
		setValues(strList);
	}

	public FtpRemittanceNameCheckInputDetailBean(List<String> strList) {
		setValues(strList);
	}

	private void setValues(String[] strList) {
		setValues(Arrays.asList(strList));
	}

	private void setValues(List<String> strList) {
		int i = 0;
		this.section = strList.get(i++);
		this.branchNumber = strList.get(i++);
		this.batchNo = strList.get(i++);
		this.seqNo = strList.get(i++);
		this.transactionDate = strList.get(i++);
		this.beneficiaryBank = strList.get(i++);
		this.transactionType = strList.get(i++);
		this.beneficiaryAccount = strList.get(i++);

		this.beneficiaryName = strList.get(i++);
		this.transactionAmount = strList.get(i++);
		this.note = strList.get(i++);
		this.fee = strList.get(i++);
		this.uniformNum = strList.get(i++);
		this.uniformName = strList.get(i++);
		
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getBeneficiaryBank() {
		return beneficiaryBank;
	}

	public void setBeneficiaryBank(String beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getBeneficiaryAccount() {
		return beneficiaryAccount;
	}

	public void setBeneficiaryAccount(String beneficiaryAccount) {
		this.beneficiaryAccount = beneficiaryAccount;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}
	
	public String getUniformNum() {
		return uniformNum;
	}

	public void setUniformNum(String uniformNum) {
		this.uniformNum = uniformNum;
	}

	public String getUniformName() {
		return uniformName;
	}

	public void setUniformName(String uniformName) {
		this.uniformName = uniformName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
