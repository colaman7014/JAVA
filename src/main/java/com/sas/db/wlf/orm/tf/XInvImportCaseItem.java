package com.sas.db.wlf.orm.tf;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the X_INV_IMPORT_CASE_ITEM database table.
 * 
 */
@Entity
@Table(name="X_INV_IMPORT_CASE_ITEM", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="XInvImportCaseItem.findAll", query="SELECT x FROM XInvImportCaseItem x")
public class XInvImportCaseItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XInvImportCaseItemPK id;

	@Column(name="AMOUNT")
	private BigDecimal amount;

	@Column(name="APPLICANT")
	private String applicant;

	@Column(name="BL_NO")
	private String blNo;

	@Column(name="CASE_RK")
	private BigDecimal caseRk;
	
	@Column(name="CATEGORY_OF_GOODS")
	private String categoryOfGoods;

	@Column(name="CURRNECY")
	private String currnecy;

	@Column(name="DESCRIPTION_OF_GOODS")
	private String descriptionOfGoods;

	@Column(name="HK_HS_CODE")
	private String hkHsCode;

	@Column(name="IB_NO")
	private String ibNo;

	@Column(name="INVOICE_NO")
	private String invoiceNo;

	@Column(name="ITEM_SEQ")
	private int itemSeq;

	@Column(name="L_C_NO")
	private String lCNo;

	@Column(name="OUR_CUSTOMER")
	private String ourCustomer;

	@Column(name="QUANTITY")
	private BigDecimal quantity;

	@Column(name="QUERY_PRICE")
	private BigDecimal queryPrice;

	@Column(name="SCR_NO")
	private String scrNo;

	@Column(name="[TYPE]")
	private String type;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

	@Column(name="CREATE_DTTM")
	private Timestamp createDttm;

	public XInvImportCaseItem() {
	}

	public XInvImportCaseItemPK getId() {
		return this.id;
	}

	public void setId(XInvImportCaseItemPK id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getApplicant() {
		return this.applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getBlNo() {
		return this.blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public BigDecimal getCaseRk() {
		return this.caseRk;
	}

	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
	}
	
	public String getCategoryOfGoods() {
		return this.categoryOfGoods;
	}

	public void setCategoryOfGoods(String categoryOfGoods) {
		this.categoryOfGoods = categoryOfGoods;
	}

	public String getCurrnecy() {
		return this.currnecy;
	}

	public void setCurrnecy(String currnecy) {
		this.currnecy = currnecy;
	}

	public String getDescriptionOfGoods() {
		return this.descriptionOfGoods;
	}

	public void setDescriptionOfGoods(String descriptionOfGoods) {
		this.descriptionOfGoods = descriptionOfGoods;
	}

	public String getHkHsCode() {
		return this.hkHsCode;
	}

	public void setHkHsCode(String hkHsCode) {
		this.hkHsCode = hkHsCode;
	}

	public String getIbNo() {
		return this.ibNo;
	}

	public void setIbNo(String ibNo) {
		this.ibNo = ibNo;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public int getItemSeq() {
		return this.itemSeq;
	}

	public void setItemSeq(int itemSeq) {
		this.itemSeq = itemSeq;
	}

	public String getLCNo() {
		return this.lCNo;
	}

	public void setLCNo(String lCNo) {
		this.lCNo = lCNo;
	}

	public String getOurCustomer() {
		return this.ourCustomer;
	}

	public void setOurCustomer(String ourCustomer) {
		this.ourCustomer = ourCustomer;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getQueryPrice() {
		return this.queryPrice;
	}

	public void setQueryPrice(BigDecimal queryPrice) {
		this.queryPrice = queryPrice;
	}

	public String getScrNo() {
		return this.scrNo;
	}

	public void setScrNo(String scrNo) {
		this.scrNo = scrNo;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getlCNo() {
		return lCNo;
	}

	public void setlCNo(String lCNo) {
		this.lCNo = lCNo;
	}

	public Timestamp getCreateDttm() {
		return createDttm;
	}

	public void setCreateDttm(Timestamp createDttm) {
		this.createDttm = createDttm;
	}
	
	

}