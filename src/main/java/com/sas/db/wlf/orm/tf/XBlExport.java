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
import com.sas.tradeFinance.bean.XBlExportBean;


/**
 * The persistent class for the X_BL_EXPORT database table.
 * 
 */
@Entity
@Table(name="X_BL_EXPORT", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="XBlExport.findAll", query="SELECT x FROM XBlExport x")
public class XBlExport implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XBlExportPK id;

	@Column(name="BL_NO")
	private String blNo;
	
	@Column(name="CASE_RK")
	private BigDecimal caseRk;

	@Column(name="CONSIGNEE")
	private String consignee;

	@Column(name="COUNTRY_OF_DELIVERY")
	private String countryOfDelivery;

	@Column(name="COUNTRY_OF_DISCHARGE")
	private String countryOfDischarge;

	@Column(name="COUNTRY_OF_LANDING")
	private String countryOfLanding;

	@Column(name="COUNTRY_OF_ORIGIN")
	private String countryOfOrigin;

	@Column(name="COUNTRY_OF_RECEIPT")
	private String countryOfReceipt;

	@Column(name="COUNTRY_OF_TRANSHIPMENT")
	private String countryOfTranshipment;

	@Column(name="CREATE_DTTM")
	private Timestamp createDttm;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="CURRENCY")
	private String currency;

	@Column(name="FORWORDER")
	private String forworder;

	@Column(name="L_C_NO")
	private String lCNo;

	@Column(name="NOTIFY_PARTY")
	private String notifyParty;

	@Column(name="OUR_REF_NO")
	private String ourRefNo;

	@Column(name="PLACE_OF_DELIEVERY")
	private String placeOfDelievery;

	@Column(name="PLACE_OF_RECEIPT")
	private String placeOfReceipt;

	@Column(name="PORT_OF_DISCHARGE")
	private String portOfDischarge;

	@Column(name="PORT_OF_LANDING")
	private String portOfLanding;

	@Column(name="PRE_CARRIAGE_VESSEL")
	private String preCarriageVessel;

	@Column(name="SCR_NO")
	private String scrNo;

	@Column(name="SECOND_NOTIFY_PARTY")
	private String secondNotifyParty;

	@Column(name="SEQ_NO")
	private String seqNo;

	@Column(name="SHIPPER")
	private String shipper;

	@Column(name="SHIPPING_AGENT")
	private String shippingAgent;

	@Column(name="SHIPPING_COMPANY")
	private String shippingCompany;

	@Column(name="TRANSHIPMENT_PORT")
	private String transhipmentPort;

	@Column(name="VESSEL")
	private String vessel;
	
	public XBlExport(XBlExportBean dtoBean){
		XBlExportPK id = new XBlExportPK();
		id.setUniqueKey(dtoBean.getUniqueKey());
		id.setSeq(dtoBean.getSeq());
		this.id = id;
		this.blNo = dtoBean.getBlNo();
		this.caseRk = dtoBean.getCaseRk();
		this.consignee = dtoBean.getConsignee();
		this.countryOfDelivery = dtoBean.getCountryOfDelivery();
		this.countryOfDischarge = dtoBean.getCountryOfDischarge();
		this.countryOfLanding = dtoBean.getCountryOfLanding();
		this.countryOfOrigin = dtoBean.getCountryOfOrigin();
		this.countryOfReceipt = dtoBean.getCountryOfReceipt();
		this.countryOfTranshipment = dtoBean.getCountryOfTranshipment();
		this.createDttm = dtoBean.getCreateDttm();
		this.createUser = dtoBean.getCreateUser();
		this.currency = dtoBean.getCurrency();
		this.forworder = dtoBean.getForworder();
		this.lCNo = dtoBean.getlCNo();
		this.notifyParty = dtoBean.getNotifyParty();
		this.ourRefNo = dtoBean.getOurRefNo();
		this.placeOfDelievery = dtoBean.getPlaceOfDelievery();
		this.placeOfReceipt = dtoBean.getPlaceOfReceipt();
		this.portOfDischarge = dtoBean.getPortOfDischarge();
		this.portOfLanding = dtoBean.getPortOfLanding();
		this.preCarriageVessel = dtoBean.getPreCarriageVessel();
		this.scrNo = dtoBean.getScrNo();
		this.secondNotifyParty = dtoBean.getSecondNotifyParty();
		this.seqNo = dtoBean.getSeqNo();
		this.shipper = dtoBean.getShipper();
		this.shippingAgent = dtoBean.getShippingAgent();
		this.shippingCompany = dtoBean.getShippingCompany();
		this.transhipmentPort = dtoBean.getTranshipmentPort();
		this.vessel = dtoBean.getVessel();
	}

	public XBlExport() {
	}

	public XBlExportPK getId() {
		return this.id;
	}

	public void setId(XBlExportPK id) {
		this.id = id;
	}

	public String getBlNo() {
		return this.blNo;
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

	public String getlCNo() {
		return lCNo;
	}

	public void setlCNo(String lCNo) {
		this.lCNo = lCNo;
	}

	public String getConsignee() {
		return this.consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getCountryOfDelivery() {
		return this.countryOfDelivery;
	}

	public void setCountryOfDelivery(String countryOfDelivery) {
		this.countryOfDelivery = countryOfDelivery;
	}

	public String getCountryOfDischarge() {
		return this.countryOfDischarge;
	}

	public void setCountryOfDischarge(String countryOfDischarge) {
		this.countryOfDischarge = countryOfDischarge;
	}

	public String getCountryOfLanding() {
		return this.countryOfLanding;
	}

	public void setCountryOfLanding(String countryOfLanding) {
		this.countryOfLanding = countryOfLanding;
	}

	public String getCountryOfOrigin() {
		return this.countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getCountryOfReceipt() {
		return this.countryOfReceipt;
	}

	public void setCountryOfReceipt(String countryOfReceipt) {
		this.countryOfReceipt = countryOfReceipt;
	}

	public String getCountryOfTranshipment() {
		return this.countryOfTranshipment;
	}

	public void setCountryOfTranshipment(String countryOfTranshipment) {
		this.countryOfTranshipment = countryOfTranshipment;
	}

	public Timestamp getCreateDttm() {
		return this.createDttm;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getForworder() {
		return this.forworder;
	}

	public void setForworder(String forworder) {
		this.forworder = forworder;
	}

	public String getLCNo() {
		return this.lCNo;
	}

	public void setLCNo(String lCNo) {
		this.lCNo = lCNo;
	}

	public String getNotifyParty() {
		return this.notifyParty;
	}

	public void setNotifyParty(String notifyParty) {
		this.notifyParty = notifyParty;
	}

	public String getOurRefNo() {
		return this.ourRefNo;
	}

	public void setOurRefNo(String ourRefNo) {
		this.ourRefNo = ourRefNo;
	}

	public String getPlaceOfDelievery() {
		return this.placeOfDelievery;
	}

	public void setPlaceOfDelievery(String placeOfDelievery) {
		this.placeOfDelievery = placeOfDelievery;
	}

	public String getPlaceOfReceipt() {
		return this.placeOfReceipt;
	}

	public void setPlaceOfReceipt(String placeOfReceipt) {
		this.placeOfReceipt = placeOfReceipt;
	}

	public String getPortOfDischarge() {
		return this.portOfDischarge;
	}

	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}

	public String getPortOfLanding() {
		return this.portOfLanding;
	}

	public void setPortOfLanding(String portOfLanding) {
		this.portOfLanding = portOfLanding;
	}

	public String getPreCarriageVessel() {
		return this.preCarriageVessel;
	}

	public void setPreCarriageVessel(String preCarriageVessel) {
		this.preCarriageVessel = preCarriageVessel;
	}

	public String getScrNo() {
		return this.scrNo;
	}

	public void setScrNo(String scrNo) {
		this.scrNo = scrNo;
	}

	public String getSecondNotifyParty() {
		return this.secondNotifyParty;
	}

	public void setSecondNotifyParty(String secondNotifyParty) {
		this.secondNotifyParty = secondNotifyParty;
	}

	public String getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getShipper() {
		return this.shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getShippingAgent() {
		return this.shippingAgent;
	}

	public void setShippingAgent(String shippingAgent) {
		this.shippingAgent = shippingAgent;
	}

	public String getShippingCompany() {
		return this.shippingCompany;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	public String getTranshipmentPort() {
		return this.transhipmentPort;
	}

	public void setTranshipmentPort(String transhipmentPort) {
		this.transhipmentPort = transhipmentPort;
	}

	public String getVessel() {
		return this.vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	@Override
	public String toString() {
		return "XBlExport [id=" + id + ", blNo=" + blNo + ", caseRk=" + caseRk
				+ ", consignee=" + consignee + ", countryOfDelivery="
				+ countryOfDelivery + ", countryOfDischarge="
				+ countryOfDischarge + ", countryOfLanding=" + countryOfLanding
				+ ", countryOfOrigin=" + countryOfOrigin
				+ ", countryOfReceipt=" + countryOfReceipt
				+ ", countryOfTranshipment=" + countryOfTranshipment
				+ ", createDttm=" + createDttm + ", createUser=" + createUser
				+ ", currency=" + currency + ", forworder=" + forworder
				+ ", lCNo=" + lCNo + ", notifyParty=" + notifyParty
				+ ", ourRefNo=" + ourRefNo + ", placeOfDelievery="
				+ placeOfDelievery + ", placeOfReceipt=" + placeOfReceipt
				+ ", portOfDischarge=" + portOfDischarge + ", portOfLanding="
				+ portOfLanding + ", preCarriageVessel=" + preCarriageVessel
				+ ", scrNo=" + scrNo + ", secondNotifyParty="
				+ secondNotifyParty + ", seqNo=" + seqNo + ", shipper="
				+ shipper + ", shippingAgent=" + shippingAgent
				+ ", shippingCompany=" + shippingCompany
				+ ", transhipmentPort=" + transhipmentPort + ", vessel="
				+ vessel + "]";
	}

}