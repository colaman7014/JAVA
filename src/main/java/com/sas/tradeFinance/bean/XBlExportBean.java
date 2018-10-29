package com.sas.tradeFinance.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.sas.db.wlf.orm.tf.XBlExport;

public class XBlExportBean {

	private String uniqueKey;

	private int seq;

	private String fileKey;
	
	private String blNo;
	
	private BigDecimal caseRk;

	private String consignee;

	private String countryOfDelivery;

	private String countryOfDischarge;

	private String countryOfLanding;

	private String countryOfOrigin;

	private String countryOfReceipt;

	private String countryOfTranshipment;

	private Timestamp createDttm;

	private String createUser;

	private String currency;

	private String forworder;

	private String lCNo;

	private String notifyParty;

	private String ourRefNo;

	private String placeOfDelievery;

	private String placeOfReceipt;

	private String portOfDischarge;

	private String portOfLanding;

	private String preCarriageVessel;

	private String scrNo;

	private String secondNotifyParty;

	private String seqNo;

	private String shipper;

	private String shippingAgent;

	private String shippingCompany;

	private String transhipmentPort;

	private String vessel;
	// Set 'True', If Data Has Error ,Else 'False'
	private boolean checkError;
	// Key: Data Row Index; Value: Column Index List;
	private Map<String, List<String>> errorRow;
	
	
	public XBlExportBean() {}
	public XBlExportBean(XBlExport xBlExportdao) {
		this.uniqueKey = xBlExportdao.getId().getUniqueKey();
		this.seq = xBlExportdao.getId().getSeq();
		this.fileKey = xBlExportdao.getId().getFileKey();
		this.blNo = xBlExportdao.getBlNo();
		this.consignee = xBlExportdao.getConsignee();
		this.countryOfDelivery = xBlExportdao.getCountryOfDelivery();
		this.countryOfDischarge = xBlExportdao.getCountryOfDischarge();
		this.countryOfLanding = xBlExportdao.getCountryOfLanding();
		this.countryOfOrigin = xBlExportdao.getCountryOfOrigin();
		this.countryOfReceipt = xBlExportdao.getCountryOfReceipt();
		this.countryOfTranshipment = xBlExportdao.getCountryOfTranshipment();
		this.createDttm = xBlExportdao.getCreateDttm();
		this.createUser = xBlExportdao.getCreateUser();
		this.currency = xBlExportdao.getCurrency();
		this.forworder = xBlExportdao.getForworder();
		this.lCNo = xBlExportdao.getlCNo();
		this.notifyParty = xBlExportdao.getNotifyParty();
		this.ourRefNo = xBlExportdao.getOurRefNo();
		this.placeOfDelievery = xBlExportdao.getPlaceOfDelievery();
		this.placeOfReceipt = xBlExportdao.getPlaceOfReceipt();
		this.portOfDischarge = xBlExportdao.getPortOfDischarge();
		this.portOfLanding = xBlExportdao.getPortOfLanding();
		this.preCarriageVessel = xBlExportdao.getPreCarriageVessel();
		this.scrNo = xBlExportdao.getScrNo();
		this.secondNotifyParty = xBlExportdao.getSecondNotifyParty();
		this.seqNo = xBlExportdao.getSeqNo();
		this.shipper = xBlExportdao.getShipper();
		this.shippingAgent = xBlExportdao.getShippingAgent();
		this.shippingCompany = xBlExportdao.getShippingCompany();
		this.transhipmentPort = xBlExportdao.getTranshipmentPort();
		this.vessel = xBlExportdao.getVessel();
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
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getCountryOfDelivery() {
		return countryOfDelivery;
	}
	public void setCountryOfDelivery(String countryOfDelivery) {
		this.countryOfDelivery = countryOfDelivery;
	}
	public String getCountryOfDischarge() {
		return countryOfDischarge;
	}
	public void setCountryOfDischarge(String countryOfDischarge) {
		this.countryOfDischarge = countryOfDischarge;
	}
	public String getCountryOfLanding() {
		return countryOfLanding;
	}
	public void setCountryOfLanding(String countryOfLanding) {
		this.countryOfLanding = countryOfLanding;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public String getCountryOfReceipt() {
		return countryOfReceipt;
	}
	public void setCountryOfReceipt(String countryOfReceipt) {
		this.countryOfReceipt = countryOfReceipt;
	}
	public String getCountryOfTranshipment() {
		return countryOfTranshipment;
	}
	public void setCountryOfTranshipment(String countryOfTranshipment) {
		this.countryOfTranshipment = countryOfTranshipment;
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getForworder() {
		return forworder;
	}
	public void setForworder(String forworder) {
		this.forworder = forworder;
	}
	public String getlCNo() {
		return lCNo;
	}
	public void setlCNo(String lCNo) {
		this.lCNo = lCNo;
	}
	public String getNotifyParty() {
		return notifyParty;
	}
	public void setNotifyParty(String notifyParty) {
		this.notifyParty = notifyParty;
	}
	public String getOurRefNo() {
		return ourRefNo;
	}
	public void setOurRefNo(String ourRefNo) {
		this.ourRefNo = ourRefNo;
	}
	public String getPlaceOfDelievery() {
		return placeOfDelievery;
	}
	public void setPlaceOfDelievery(String placeOfDelievery) {
		this.placeOfDelievery = placeOfDelievery;
	}
	public String getPlaceOfReceipt() {
		return placeOfReceipt;
	}
	public void setPlaceOfReceipt(String placeOfReceipt) {
		this.placeOfReceipt = placeOfReceipt;
	}
	public String getPortOfDischarge() {
		return portOfDischarge;
	}
	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}
	public String getPortOfLanding() {
		return portOfLanding;
	}
	public void setPortOfLanding(String portOfLanding) {
		this.portOfLanding = portOfLanding;
	}
	public String getPreCarriageVessel() {
		return preCarriageVessel;
	}
	public void setPreCarriageVessel(String preCarriageVessel) {
		this.preCarriageVessel = preCarriageVessel;
	}
	public String getScrNo() {
		return scrNo;
	}
	public void setScrNo(String scrNo) {
		this.scrNo = scrNo;
	}
	public String getSecondNotifyParty() {
		return secondNotifyParty;
	}
	public void setSecondNotifyParty(String secondNotifyParty) {
		this.secondNotifyParty = secondNotifyParty;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getShippingAgent() {
		return shippingAgent;
	}
	public void setShippingAgent(String shippingAgent) {
		this.shippingAgent = shippingAgent;
	}
	public String getShippingCompany() {
		return shippingCompany;
	}
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}
	public String getTranshipmentPort() {
		return transhipmentPort;
	}
	public void setTranshipmentPort(String transhipmentPort) {
		this.transhipmentPort = transhipmentPort;
	}
	public String getVessel() {
		return vessel;
	}
	public void setVessel(String vessel) {
		this.vessel = vessel;
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
