package com.sas.tradeFinance.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.sas.db.wlf.orm.tf.XInvExport;

public class XInvExportBean {

	private String uniqueKey;

	private int seq;
	
	private String fileKey;

	private BigDecimal amount;

	private String applicant;

	private BigDecimal caseRk;

	private String categoryOfGoods;

	private String currnecy;

	private String descriptionOfGoods;

	private String hkHsCode;

	private String invoiceNo;

	private int itemSeq;

	private String ourRefNo;

	private BigDecimal quantity;

	private String scrNo;

	private String seqNo;

	private BigDecimal unitPrice;

	private String invCurrency;

	private Timestamp createDttm;

	private String createUser;
	
	// Set 'True', If Data Has Error ,Else 'False'
	private boolean checkError;
	// Key: Data Row Index; Value: Column Index List;
	private Map<String, List<String>> errorRow;
	
	
	public XInvExportBean() {}
	
	
	public XInvExportBean(XInvExport xInvExportdao) {
		this.uniqueKey = xInvExportdao.getId().getUniqueKey();
		this.seq = xInvExportdao.getId().getSeq();
		this.fileKey = xInvExportdao.getId().getFileKey();
		this.amount = xInvExportdao.getAmount();
		this.applicant = xInvExportdao.getApplicant();
		this.caseRk = xInvExportdao.getCaseRk();
		this.categoryOfGoods = xInvExportdao.getCategoryOfGoods();
		this.currnecy = xInvExportdao.getCurrnecy();
		this.descriptionOfGoods = xInvExportdao.getDescriptionOfGoods();
		this.hkHsCode = xInvExportdao.getHkHsCode();
		this.invoiceNo = xInvExportdao.getInvoiceNo();
		this.itemSeq = xInvExportdao.getItemSeq();
		this.ourRefNo = xInvExportdao.getOurRefNo();
		this.quantity = xInvExportdao.getQuantity();
		this.scrNo = xInvExportdao.getScrNo();
		this.seqNo = xInvExportdao.getSeqNo();
		this.unitPrice = xInvExportdao.getUnitPrice();
		this.invCurrency = xInvExportdao.getInvCurrency();
		this.createDttm = xInvExportdao.getCreateDttm();
		this.createUser = xInvExportdao.getCreateUser();
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
	public String getOurRefNo() {
		return ourRefNo;
	}
	public void setOurRefNo(String ourRefNo) {
		this.ourRefNo = ourRefNo;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getScrNo() {
		return scrNo;
	}
	public void setScrNo(String scrNo) {
		this.scrNo = scrNo;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getInvCurrency() {
		return invCurrency;
	}
	public void setInvCurrency(String invCurrency) {
		this.invCurrency = invCurrency;
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
