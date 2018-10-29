package com.sas.webservice.nameCheck.bean;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FtpRemittanceNameCheckInputEndBean {
	private String section;
	private String branchNumber;
	private String batchNo;
	private String seqNo;
	private String transactionDate;
	private String totalCount;
	private String totalAmount;
	private String fee;
	private String filler;

	public FtpRemittanceNameCheckInputEndBean() {
	}

	public FtpRemittanceNameCheckInputEndBean(String[] strList) {
		setValues(strList);
	}

	public FtpRemittanceNameCheckInputEndBean(List<String> strList) {
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
		this.totalCount = strList.get(i++);
		this.totalAmount = strList.get(i++);
		this.fee = strList.get(i++);
		this.filler = strList.get(i++);
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

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
