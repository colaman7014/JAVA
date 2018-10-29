package com.sas.webservice.nameCheck.bean;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FtpRemittanceNameCheckInputHeadBean {
	private String section;
	private String branchNumber;
	private String screenFunction;
	private String batchNo;
	private String seqNo;
	private String transactionDate;
	private String filler1;
	private String remitterName;
	private String filler2;
	private String ctrlNum;
	private String formosaLength;
	
	public FtpRemittanceNameCheckInputHeadBean() {
	}

	public FtpRemittanceNameCheckInputHeadBean(String[] strList) {
		setValues(strList);
	}

	public FtpRemittanceNameCheckInputHeadBean(List<String> strList) {
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
		this.filler1 = strList.get(i++);
		this.remitterName = strList.get(i++);
		this.filler2 = strList.get(i++);
		this.ctrlNum = strList.get(i++);
		this.formosaLength = strList.get(i++);
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

	public String getScreenFunction() {
		return screenFunction;
	}

	public void setScreenFunction(String screenFunction) {
		this.screenFunction = screenFunction;
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

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getRemitterName() {
		return remitterName;
	}

	public void setRemitterName(String remitterName) {
		this.remitterName = remitterName;
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}
	
	public String getCtrlNum() {
		return ctrlNum;
	}

	public void setCtrlNum(String ctrlNum) {
		this.ctrlNum = ctrlNum;
	}

	public String getFormosaLength() {
		return formosaLength;
	}

	public void setFormosaLength(String formosaLength) {
		this.formosaLength = formosaLength;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
