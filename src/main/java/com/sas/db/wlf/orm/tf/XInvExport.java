package com.sas.db.wlf.orm.tf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;
import com.sas.tradeFinance.bean.XInvExportBean;


/**
 * The persistent class for the X_INV_EXPORT database table.
 * 
 */
@Entity
@Table(name="X_INV_EXPORT", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="XInvExport.findAll", query="SELECT x FROM XInvExport x")
public class XInvExport implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XInvExportPK id;

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
	
	@Column(name="QUANTITY")
	private BigDecimal quantity;

	@Column(name="SCR_NO")
	private String scrNo;

	@Column(name="SEQ_NO")
	private String seqNo;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;
	
	@Column(name="INV_CURRENCY")
	private String invCurrency;
	
	@Column(name="CREATE_DTTM")
	private Timestamp createDttm;
	
	@Column(name="CREATE_USER")
	private String createUser;

	public XInvExport() {
	}
	
	public XInvExport(XInvExportBean dtoBean) {
		XInvExportPK id = new XInvExportPK();
		id.setUniqueKey(dtoBean.getUniqueKey());
		id.setSeq(dtoBean.getSeq());
		this.id = id;
		this.amount = dtoBean.getAmount();
		this.applicant = dtoBean.getApplicant();
		this.caseRk = dtoBean.getCaseRk();
		this.categoryOfGoods = dtoBean.getCategoryOfGoods();
		this.currnecy = dtoBean.getCurrnecy();
		this.descriptionOfGoods = dtoBean.getDescriptionOfGoods();
		this.hkHsCode = dtoBean.getHkHsCode();
		this.invoiceNo = dtoBean.getInvoiceNo();
		this.itemSeq = dtoBean.getItemSeq();
		this.ourRefNo = dtoBean.getOurRefNo();
		this.quantity = dtoBean.getQuantity();
		this.scrNo = dtoBean.getScrNo();
		this.seqNo = dtoBean.getSeqNo();
		this.unitPrice = dtoBean.getUnitPrice();
		this.invCurrency = dtoBean.getInvCurrency();
		this.createDttm = dtoBean.getCreateDttm();
		this.createUser = dtoBean.getCreateUser();
	}

	public XInvExportPK getId() {
		return this.id;
	}

	public void setId(XInvExportPK id) {
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

	public String getInvCurrency() {
		return invCurrency;
	}

	public void setInvCurrency(String invCurrency) {
		this.invCurrency = invCurrency;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Override
	public String toString() {
		return "XInvExport [id=" + id + ", amount=" + amount + ", applicant="
				+ applicant + ", caseRk=" + caseRk + ", categoryOfGoods="
				+ categoryOfGoods + ", currnecy=" + currnecy
				+ ", descriptionOfGoods=" + descriptionOfGoods + ", hkHsCode="
				+ hkHsCode + ", invoiceNo=" + invoiceNo + ", itemSeq="
				+ itemSeq + ", ourRefNo=" + ourRefNo + ", quantity=" + quantity
				+ ", scrNo=" + scrNo + ", seqNo=" + seqNo + ", unitPrice="
				+ unitPrice + ", invCurrency=" + invCurrency + ", createDttm="
				+ createDttm + ", createUser=" + createUser + "]";
	}
}