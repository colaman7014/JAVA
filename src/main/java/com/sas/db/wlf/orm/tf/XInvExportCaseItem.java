package com.sas.db.wlf.orm.tf;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the X_INV_EXPORT_CASE_ITEM database table.
 * 
 */
@Entity
@Table(name="X_INV_EXPORT_CASE_ITEM", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="XInvExportCaseItem.findAll", query="SELECT x FROM XInvExportCaseItem x")
public class XInvExportCaseItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XInvExportCaseItemPK id;

	@Column(name="AMOUNT")
	private BigDecimal amount;

	@Column(name="APPLICANT")
	private String applicant;

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

	@Column(name="INVOICE_NO")
	private String invoiceNo;

	@Column(name="ITEM_SEQ")
	private int itemSeq;

	@Column(name="OUR_REF_NO")
	private String ourRefNo;

	@Column(name="INV_CURRENCY")
	private String invCurrency;

	@Column(name="QUANTITY")
	private BigDecimal quantity;

	@Column(name="SCR_NO")
	private String scrNo;

	@Column(name="SEQ_NO")
	private String seqNo;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

	@Column(name="CREATE_DTTM")
	private Timestamp createDttm;

	public XInvExportCaseItem() {
	}

	public XInvExportCaseItemPK getId() {
		return this.id;
	}

	public void setId(XInvExportCaseItemPK id) {
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

	public String getOurRefNo() {
		return this.ourRefNo;
	}

	public void setOurRefNo(String ourRefNo) {
		this.ourRefNo = ourRefNo;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	public String getInvCurrency() {
		return invCurrency;
	}

	public void setInvCurrency(String invCurrency) {
		this.invCurrency = invCurrency;
	}

	public String getScrNo() {
		return this.scrNo;
	}

	public void setScrNo(String scrNo) {
		this.scrNo = scrNo;
	}

	public String getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
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
	
	

}