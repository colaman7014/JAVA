package com.sas.tradeFinance.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.sas.db.wlf.orm.tf.XInvImport;

public class XInvImportBean {

	private String uniqueKey;

	private int seq;
	
	private String fileKey;
	
	private BigDecimal amount;

	private String applicant;
	
	private String blNo;

	private BigDecimal caseRk;

	private String categoryOfGoods;

	private String currnecy;

	private String descriptionOfGoods;

	private String hkHsCode;

	private String ibNo;

	private String invoiceNo;

	private int itemSeq;

	private String lCNo;
	
	private String ourCustomer;

	private BigDecimal quantity;

	private BigDecimal queryPrice;
	
	private String scrNo;

	private String type;

	private BigDecimal unitPrice;
	
	private Timestamp createDttm;
	
	private String createUser;
	// Set 'True', If Data Has Error ,Else 'False'
	private boolean checkError;
	// Key: Data Row Index; Value: Column Index List;
	private Map<String, List<String>> errorRow;
	
	
	
	public XInvImportBean() {}
	
	
	public XInvImportBean(XInvImport xInvImportdao) {
		this.uniqueKey = xInvImportdao.getId().getUniqueKey();
		this.seq = xInvImportdao.getId().getSeq();
		this.fileKey = xInvImportdao.getId().getFileKey();
		this.amount = xInvImportdao.getAmount();
		this.applicant = xInvImportdao.getApplicant();
		this.blNo = xInvImportdao.getBlNo();
		this.caseRk = xInvImportdao.getCaseRk();
		this.categoryOfGoods = xInvImportdao.getCategoryOfGoods();
		this.currnecy = xInvImportdao.getCurrnecy();
		this.descriptionOfGoods = xInvImportdao.getDescriptionOfGoods();
		this.hkHsCode = xInvImportdao.getHkHsCode();
		this.ibNo = xInvImportdao.getIbNo();
		this.invoiceNo = xInvImportdao.getInvoiceNo();
		this.itemSeq = xInvImportdao.getItemSeq();
		this.lCNo = xInvImportdao.getLCNo();
		this.ourCustomer = xInvImportdao.getOurCustomer();
		this.quantity = xInvImportdao.getQuantity();
		this.queryPrice = xInvImportdao.getQueryPrice();
		this.scrNo = xInvImportdao.getScrNo();
		this.type = xInvImportdao.getType();
		this.unitPrice = xInvImportdao.getUnitPrice();
		this.createDttm = xInvImportdao.getCreateDttm();
		this.createUser = xInvImportdao.getCreateUser();
	}


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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getBlNo() {
		return blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}
	public BigDecimal getCaseRk() {
		return caseRk;
	}
	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
	}
	public String getCategoryOfGoods() {
		return categoryOfGoods;
	}
	public void setCategoryOfGoods(String categoryOfGoods) {
		this.categoryOfGoods = categoryOfGoods;
	}
	public String getCurrnecy() {
		return currnecy;
	}
	public void setCurrnecy(String currnecy) {
		this.currnecy = currnecy;
	}
	public String getDescriptionOfGoods() {
		return descriptionOfGoods;
	}
	public void setDescriptionOfGoods(String descriptionOfGoods) {
		this.descriptionOfGoods = descriptionOfGoods;
	}
	public String getHkHsCode() {
		return hkHsCode;
	}
	public void setHkHsCode(String hkHsCode) {
		this.hkHsCode = hkHsCode;
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
	public int getItemSeq() {
		return itemSeq;
	}
	public void setItemSeq(int itemSeq) {
		this.itemSeq = itemSeq;
	}
	public String getLCNo() {
		return lCNo;
	}
	public void setLCNo(String lCNo) {
		this.lCNo = lCNo;
	}
	public String getOurCustomer() {
		return ourCustomer;
	}
	public void setOurCustomer(String ourCustomer) {
		this.ourCustomer = ourCustomer;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getQueryPrice() {
		return queryPrice;
	}
	public void setQueryPrice(BigDecimal queryPrice) {
		this.queryPrice = queryPrice;
	}
	public String getScrNo() {
		return scrNo;
	}
	public void setScrNo(String scrNo) {
		this.scrNo = scrNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Timestamp getCreateDttm() {
		return createDttm;
	}
	public void setCreateDttm(Timestamp createDttm) {
		this.createDttm = createDttm;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public boolean isCheckError() {
		return checkError;
	}
	public void setCheckError(boolean checkError) {
		this.checkError = checkError;
	}
	public Map<String, List<String>> getErrorRow() {
		return errorRow;
	}
	public void setErrorRow(Map<String, List<String>> errorRow) {
		this.errorRow = errorRow;
	}
}
