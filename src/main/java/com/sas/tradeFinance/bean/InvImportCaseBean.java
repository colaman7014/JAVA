package com.sas.tradeFinance.bean;


public class InvImportCaseBean {
	
	private String uniqueKey;

	private int seq;

	private String fileKey;

	private String scrNo;
	
	private String lCNo;
	
	private String type;

	private String ibNo;

	private String invoiceNo;
	
	private String sourceType;
	
	private String screenProccess;
	
	private String createUser;

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getScrNo() {
		return scrNo;
	}

	public void setScrNo(String scrNo) {
		this.scrNo = scrNo;
	}

	public String getlCNo() {
		return lCNo;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setlCNo(String lCNo) {
		this.lCNo = lCNo;
	}

	public String getIbNo() {
		return ibNo;
	}

	public void setIbNo(String ibNo) {
		this.ibNo = ibNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getScreenProccess() {
		return screenProccess;
	}

	public void setScreenProccess(String screenProccess) {
		this.screenProccess = screenProccess;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	
	
	


}
