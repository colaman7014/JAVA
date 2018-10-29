package com.sas.swift.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.prowidesoftware.swift.model.mt.AbstractMT;
import com.prowidesoftware.swift.model.mt.mt7xx.MT700;

/**
 * SWIFT SwiftMt700 Bean
 * @author SAS
 *
 */
public class SwiftMt700Bean extends SwiftMtBaseBean {
	private MT700 mt700;
	
	private String additionalAmountsCovered;
	private String additionalConditions;
	private String adviseThroughBank;
	private String amount;
	private String applicableRules;
	private String applicant;
	private String applicantBank;
	private String availableWithBy;
	private String beneficiary;
	private String charges;
	private String confirmationInstructions;
	private String createdBy;
	private Timestamp createdTimestamp;
	private String creditAmountTolerance;
	private String dateOfIssue;
	private String datePlaceExpiry;
	private String documentaryCreditForm;
	private String documentaryCreditNumber;
	private String documentsRequired;
	private String draftsAt;
	private String drawee;
	private String goodsDescription;
	private String instructions;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedTimestamp;
	private String latestShipmentDate;
	private String logicalTerminalAddr;
	private String maxCreditAmount;
	private String mixedPaymentDetails;
	private String msgId;
	private String partialShipments;
	private String paymentDetails;
	private String placeOfDestination;
	private String plactOfTicDisRec;
	private String portOfDischarge;
	private String portOfLoading;
	private String presentationPeriod;
	private String referenceToPreadvice;
	private String reimbursingBank;
	private String senderInformation;
	private String seqOfTotal;
	private String shipmentPeriod;
	private String transhipment;
	
	public SwiftMt700Bean(AbstractMT amt){
		boolean chkPOBOX = true;
		this.bicCodeBeanList = new ArrayList<BicCodeBean>();
		this.mt700 = (MT700)amt;
		
		this.seqOfTotal = mt700.getField27() == null ? null : mt700.getField27().getValue();
		this.documentaryCreditForm = mt700.getField40A() == null ? null : mt700.getField40A().getValue();
		this.documentaryCreditNumber = mt700.getField20() == null ? null : mt700.getField20().getValue();
		this.referenceToPreadvice = mt700.getField23() == null ? null : mt700.getField23().getValue();
		this.dateOfIssue = mt700.getField31C() == null ? null : mt700.getField31C().getValue();
		this.applicableRules = mt700.getField40E() == null ? null : mt700.getField40E().getValue();
		this.datePlaceExpiry = mt700.getField31D() == null ? null : mt700.getField31D().getValue();
		screenCondition("D", datePlaceExpiry, this.bicCodeBeanList, this.orConditionMap);
		
		if(mt700.getField51A() != null){
			this.applicantBank = mt700.getField51A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", applicantBank);
			screenCondition("A", applicantBank, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}else if(mt700.getField51D() != null){
			this.applicantBank = mt700.getField51D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", applicantBank);
			screenCondition("D", applicantBank, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}		
		
		this.applicant = mt700.getField50() == null ? null : mt700.getField50().getValue();
		screenCondition("", applicant, this.bicCodeBeanList, this.orConditionMap);
		
		this.beneficiary = mt700.getField59() == null ? null : mt700.getField59().getValue();
		screenCondition("", beneficiary, this.bicCodeBeanList, this.orConditionMap);
		
		this.amount = mt700.getField32B() == null ? null : mt700.getField32B().getValue();
		this.creditAmountTolerance = mt700.getField39A() == null ? null : mt700.getField39A().getValue();
		this.maxCreditAmount = mt700.getField39B() == null ? null : mt700.getField39B().getValue();
		this.additionalAmountsCovered = mt700.getField39C() == null ? null : mt700.getField39C().getValue();
		
		if(mt700.getField41A() != null){
			this.availableWithBy = mt700.getField41A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", availableWithBy);
			screenCondition("A", availableWithBy, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}else if(mt700.getField41D() != null){
			this.availableWithBy = mt700.getField41D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", availableWithBy);
			screenCondition("D", availableWithBy, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}
		
		this.draftsAt = mt700.getField42C() == null ? null : mt700.getField42C().getValue();
		
		if(mt700.getField42A() != null){
			this.drawee = mt700.getField42A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", drawee);
			screenCondition("A", drawee, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}else if(mt700.getField42D() != null){
			this.drawee = mt700.getField42D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", drawee);
			screenCondition("D", drawee, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}
		
		this.mixedPaymentDetails = mt700.getField42M() == null ? null : mt700.getField42M().getValue();
		this.paymentDetails = mt700.getField42P() == null ? null : mt700.getField42P().getValue();
		this.partialShipments = mt700.getField43P() == null ? null : mt700.getField43P().getValue();
		this.transhipment = mt700.getField43T() == null ? null : mt700.getField43T().getValue();
		this.plactOfTicDisRec = mt700.getField44A() == null ? null : mt700.getField44A().getValue();
		screenCondition("", plactOfTicDisRec, this.bicCodeBeanList, this.orConditionMap);
		
		this.portOfLoading = mt700.getField44E() == null ? null : mt700.getField44E().getValue();
		screenCondition("E", portOfLoading, this.bicCodeBeanList, this.orConditionMap);
		
		this.portOfDischarge = mt700.getField44F() == null ? null : mt700.getField44F().getValue();
		screenCondition("F", portOfDischarge, this.bicCodeBeanList, this.orConditionMap);
		
		this.placeOfDestination = mt700.getField44B() == null ? null : mt700.getField44B().getValue();
		screenCondition("B", placeOfDestination, this.bicCodeBeanList, this.orConditionMap);
		
		this.latestShipmentDate = mt700.getField44C() == null ? null : mt700.getField44C().getValue();
		this.shipmentPeriod = mt700.getField44D() == null ? null : mt700.getField44D().getValue();
		this.goodsDescription = mt700.getField45A() == null ? null : mt700.getField45A().getValue();
		screenCondition("", goodsDescription, this.bicCodeBeanList, this.orConditionMap);
		
		this.documentsRequired = mt700.getField46A() == null ? null : mt700.getField46A().getValue();
		screenCondition("", documentsRequired, this.bicCodeBeanList, this.orConditionMap);
		
		this.additionalConditions = mt700.getField47A() == null ? null : mt700.getField47A().getValue();
		screenCondition("", additionalConditions, this.bicCodeBeanList, this.orConditionMap);
		
		this.charges = mt700.getField71B() == null ? null : mt700.getField71B().getValue();
		this.presentationPeriod = mt700.getField48() == null ? null : mt700.getField48().getValue();
		this.confirmationInstructions = mt700.getField49() == null ? null : mt700.getField49().getValue();
		
		if(mt700.getField53A() != null){
			this.reimbursingBank = mt700.getField53A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", reimbursingBank);
			screenCondition("A", reimbursingBank, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}else if(mt700.getField53D() != null){
			this.reimbursingBank = mt700.getField53D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", reimbursingBank);
			screenCondition("D", reimbursingBank, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}
		
		this.instructions = mt700.getField78() == null ? null : mt700.getField78().getValue();
//		screenCondition("", instructions, this.bicCodeBeanList, this.orConditionMap);
		
		if(mt700.getField57A() != null){
			this.adviseThroughBank = mt700.getField57A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", adviseThroughBank);
			screenCondition("A", adviseThroughBank, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}else if(mt700.getField57B() != null){
			this.adviseThroughBank = mt700.getField57B().getValue();
//			this.bicCodeBeanList = parseBicCode("B", adviseThroughBank);
			screenCondition("B", adviseThroughBank, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}else if(mt700.getField57D() != null){
			this.adviseThroughBank = mt700.getField57D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", adviseThroughBank);
			screenCondition("D", adviseThroughBank, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}
		
		this.senderInformation = mt700.getField72() == null ? null : mt700.getField72().getValue();
//		screenCondition("", senderInformation, this.bicCodeBeanList, this.orConditionMap);
		
		// free text using charIndex
		inclusiveCond.add(this.instructions);
		inclusiveCond.add(this.senderInformation);
	}
	
	public MT700 getMt700() {
		return mt700;
	}
	public void setMt700(MT700 mt700) {
		this.mt700 = mt700;
	}
	public String getAdditionalAmountsCovered() {
		return additionalAmountsCovered;
	}
	public void setAdditionalAmountsCovered(String additionalAmountsCovered) {
		this.additionalAmountsCovered = additionalAmountsCovered;
	}
	public String getAdditionalConditions() {
		return additionalConditions;
	}
	public void setAdditionalConditions(String additionalConditions) {
		this.additionalConditions = additionalConditions;
	}
	public String getAdviseThroughBank() {
		return adviseThroughBank;
	}
	public void setAdviseThroughBank(String adviseThroughBank) {
		this.adviseThroughBank = adviseThroughBank;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getApplicableRules() {
		return applicableRules;
	}
	public void setApplicableRules(String applicableRules) {
		this.applicableRules = applicableRules;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getApplicantBank() {
		return applicantBank;
	}
	public void setApplicantBank(String applicantBank) {
		this.applicantBank = applicantBank;
	}
	public String getAvailableWithBy() {
		return availableWithBy;
	}
	public void setAvailableWithBy(String availableWithBy) {
		this.availableWithBy = availableWithBy;
	}
	public String getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getConfirmationInstructions() {
		return confirmationInstructions;
	}
	public void setConfirmationInstructions(String confirmationInstructions) {
		this.confirmationInstructions = confirmationInstructions;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public String getCreditAmountTolerance() {
		return creditAmountTolerance;
	}
	public void setCreditAmountTolerance(String creditAmountTolerance) {
		this.creditAmountTolerance = creditAmountTolerance;
	}
	public String getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public String getDatePlaceExpiry() {
		return datePlaceExpiry;
	}
	public void setDatePlaceExpiry(String datePlaceExpiry) {
		this.datePlaceExpiry = datePlaceExpiry;
	}
	public String getDocumentaryCreditForm() {
		return documentaryCreditForm;
	}
	public void setDocumentaryCreditForm(String documentaryCreditForm) {
		this.documentaryCreditForm = documentaryCreditForm;
	}
	public String getDocumentaryCreditNumber() {
		return documentaryCreditNumber;
	}
	public void setDocumentaryCreditNumber(String documentaryCreditNumber) {
		this.documentaryCreditNumber = documentaryCreditNumber;
	}
	public String getDocumentsRequired() {
		return documentsRequired;
	}
	public void setDocumentsRequired(String documentsRequired) {
		this.documentsRequired = documentsRequired;
	}
	public String getDraftsAt() {
		return draftsAt;
	}
	public void setDraftsAt(String draftsAt) {
		this.draftsAt = draftsAt;
	}
	public String getDrawee() {
		return drawee;
	}
	public void setDrawee(String drawee) {
		this.drawee = drawee;
	}
	public String getGoodsDescription() {
		return goodsDescription;
	}
	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Timestamp getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	public void setLastUpdatedTimestamp(Timestamp lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	public String getLatestShipmentDate() {
		return latestShipmentDate;
	}
	public void setLatestShipmentDate(String latestShipmentDate) {
		this.latestShipmentDate = latestShipmentDate;
	}
	public String getLogicalTerminalAddr() {
		return logicalTerminalAddr;
	}
	public void setLogicalTerminalAddr(String logicalTerminalAddr) {
		this.logicalTerminalAddr = logicalTerminalAddr;
	}
	public String getMaxCreditAmount() {
		return maxCreditAmount;
	}
	public void setMaxCreditAmount(String maxCreditAmount) {
		this.maxCreditAmount = maxCreditAmount;
	}
	public String getMixedPaymentDetails() {
		return mixedPaymentDetails;
	}
	public void setMixedPaymentDetails(String mixedPaymentDetails) {
		this.mixedPaymentDetails = mixedPaymentDetails;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getPartialShipments() {
		return partialShipments;
	}
	public void setPartialShipments(String partialShipments) {
		this.partialShipments = partialShipments;
	}
	public String getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public String getPlaceOfDestination() {
		return placeOfDestination;
	}
	public void setPlaceOfDestination(String placeOfDestination) {
		this.placeOfDestination = placeOfDestination;
	}
	public String getPlactOfTicDisRec() {
		return plactOfTicDisRec;
	}
	public void setPlactOfTicDisRec(String plactOfTicDisRec) {
		this.plactOfTicDisRec = plactOfTicDisRec;
	}
	public String getPortOfDischarge() {
		return portOfDischarge;
	}
	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}
	public String getPortOfLoading() {
		return portOfLoading;
	}
	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}
	public String getPresentationPeriod() {
		return presentationPeriod;
	}
	public void setPresentationPeriod(String presentationPeriod) {
		this.presentationPeriod = presentationPeriod;
	}
	public String getReferenceToPreadvice() {
		return referenceToPreadvice;
	}
	public void setReferenceToPreadvice(String referenceToPreadvice) {
		this.referenceToPreadvice = referenceToPreadvice;
	}
	public String getReimbursingBank() {
		return reimbursingBank;
	}
	public void setReimbursingBank(String reimbursingBank) {
		this.reimbursingBank = reimbursingBank;
	}
	public String getSenderInformation() {
		return senderInformation;
	}
	public void setSenderInformation(String senderInformation) {
		this.senderInformation = senderInformation;
	}
	public String getSeqOfTotal() {
		return seqOfTotal;
	}
	public void setSeqOfTotal(String seqOfTotal) {
		this.seqOfTotal = seqOfTotal;
	}
	public String getShipmentPeriod() {
		return shipmentPeriod;
	}
	public void setShipmentPeriod(String shipmentPeriod) {
		this.shipmentPeriod = shipmentPeriod;
	}
	public String getTranshipment() {
		return transhipment;
	}
	public void setTranshipment(String transhipment) {
		this.transhipment = transhipment;
	}

	@Override
	public String toString() {
		return "SwiftMt700Bean [mt700=" + mt700 + ", additionalAmountsCovered="
				+ additionalAmountsCovered + ", additionalConditions="
				+ additionalConditions + ", adviseThroughBank="
				+ adviseThroughBank + ", amount=" + amount
				+ ", applicableRules=" + applicableRules + ", applicant="
				+ applicant + ", applicantBank=" + applicantBank
				+ ", availableWithBy=" + availableWithBy + ", beneficiary="
				+ beneficiary + ", charges=" + charges
				+ ", confirmationInstructions=" + confirmationInstructions
				+ ", createdBy=" + createdBy + ", createdTimestamp="
				+ createdTimestamp + ", creditAmountTolerance="
				+ creditAmountTolerance + ", dateOfIssue=" + dateOfIssue
				+ ", datePlaceExpiry=" + datePlaceExpiry
				+ ", documentaryCreditForm=" + documentaryCreditForm
				+ ", documentaryCreditNumber=" + documentaryCreditNumber
				+ ", documentsRequired=" + documentsRequired + ", draftsAt="
				+ draftsAt + ", drawee=" + drawee + ", goodsDescription="
				+ goodsDescription + ", instructions=" + instructions
				+ ", lastUpdatedBy=" + lastUpdatedBy
				+ ", lastUpdatedTimestamp=" + lastUpdatedTimestamp
				+ ", latestShipmentDate=" + latestShipmentDate
				+ ", logicalTerminalAddr=" + logicalTerminalAddr
				+ ", maxCreditAmount=" + maxCreditAmount
				+ ", mixedPaymentDetails=" + mixedPaymentDetails + ", msgId="
				+ msgId + ", partialShipments=" + partialShipments
				+ ", paymentDetails=" + paymentDetails
				+ ", placeOfDestination=" + placeOfDestination
				+ ", plactOfTicDisRec=" + plactOfTicDisRec
				+ ", portOfDischarge=" + portOfDischarge + ", portOfLoading="
				+ portOfLoading + ", presentationPeriod=" + presentationPeriod
				+ ", referenceToPreadvice=" + referenceToPreadvice
				+ ", reimbursingBank=" + reimbursingBank
				+ ", senderInformation=" + senderInformation + ", seqOfTotal="
				+ seqOfTotal + ", shipmentPeriod=" + shipmentPeriod
				+ ", transhipment=" + transhipment + "]";
	}
}
