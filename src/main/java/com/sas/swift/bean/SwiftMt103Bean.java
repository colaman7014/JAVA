package com.sas.swift.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.prowidesoftware.swift.model.field.Field13C;
import com.prowidesoftware.swift.model.field.Field23E;
import com.prowidesoftware.swift.model.field.Field71F;
import com.prowidesoftware.swift.model.mt.AbstractMT;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;

/**
 * SWIFT SwiftMt103 Bean
 * @author SAS
 *
 */
public class SwiftMt103Bean extends SwiftMtBaseBean {
	private MT103 mt103;
	
	private String msgId;
	private String accountWithInstitution;
	private String bankOperationCode;
	private String beneficiaryCustomer;
	private String createBy;
	private Timestamp createdTimestamp;
	private String detailsOfCharges;
	private String exchangeRate;
	private String instructedAmount;
	private String instructionCode;
	private String intermediaryInstitution;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedTimestamp;
	private String logicalTerminalAddr;
	private String orderingCustomer;
	private String orderingInstitution;
	private String receiverCharges;
	private String receiverCorrespondent;
	private String regulatoryReporting;
	private String remittanceInformation;
	private String senderCharges;
	private String senderCorrespondent;
	private String senderInformation;
	private String senderReference;
	private String sendingInstitution;
	private String settledAmount;
	private String swiftCaseId;
	private String thirdReimburseInstitution;
	private String timeIndication;
	private String transactionTypeCode;
	
	public SwiftMt103Bean(AbstractMT amt){
		boolean chkPOBOX = true;
		this.bicCodeBeanList = new ArrayList<BicCodeBean>();
		this.mt103 = (MT103)amt;
		
		// this' list need to confirm
		for(Field13C field13c : mt103.getField13C()){
			this.timeIndication = field13c.getValue();
		}
		this.senderReference = mt103.getField20() == null ? null : mt103.getField20().getValue();
		this.bankOperationCode =  mt103.getField23B() == null ? null : mt103.getField23B().getValue();
		// this' list need to confirm
		for(Field23E field23e : mt103.getField23E()){
			this.instructionCode = field23e.getValue();
		}
		
		this.transactionTypeCode = mt103.getField26T() == null ? null : mt103.getField26T().getValue();
		this.settledAmount = mt103.getField32A() == null ? null : mt103.getField32A().getValue();
		this.instructedAmount = mt103.getField33B() == null ? null : mt103.getField33B().getValue();
		this.exchangeRate = mt103.getField36() == null ? null : mt103.getField36().getValue();
		
		if(mt103.getField50A() != null){
			this.orderingCustomer = mt103.getField50A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", orderingCustomer);
			screenCondition("A", orderingCustomer, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}else if(mt103.getField50F() != null){
			this.orderingCustomer = mt103.getField50F().getValue();
//			this.bicCodeBeanList = parseBicCode("F", orderingCustomer);
			screenCondition("F", orderingCustomer, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}else if(mt103.getField50K() != null){
			this.orderingCustomer = mt103.getField50K().getValue();
//			this.bicCodeBeanList = parseBicCode("K", orderingCustomer);
			screenCondition("K", orderingCustomer, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}
		
		this.sendingInstitution = mt103.getField51A() == null ? null : mt103.getField51A().getValue();
		
		if(mt103.getField52A() != null){
			this.orderingInstitution = mt103.getField52A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", orderingInstitution);
			screenCondition("A", orderingInstitution, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}else if(mt103.getField52D() != null){
			this.orderingInstitution = mt103.getField52D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", orderingInstitution);
			screenCondition("D", orderingInstitution, this.bicCodeBeanList, this.orConditionMap, chkPOBOX);
		}
		
		if(mt103.getField53A() != null){
			this.senderCorrespondent = mt103.getField53A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", senderCorrespondent);
			screenCondition("A", senderCorrespondent, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField53B() != null){
			this.senderCorrespondent = mt103.getField53B().getValue();
//			this.bicCodeBeanList = parseBicCode("B", senderCorrespondent);
			screenCondition("B", senderCorrespondent, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField53D() != null){
			this.senderCorrespondent = mt103.getField53D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", senderCorrespondent);
			screenCondition("D", senderCorrespondent, this.bicCodeBeanList, this.orConditionMap);
		}
		
		if(mt103.getField54A() != null){
			this.receiverCorrespondent = mt103.getField54A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", receiverCorrespondent);
			screenCondition("A", receiverCorrespondent, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField54B() != null){
			this.receiverCorrespondent = mt103.getField54B().getValue();
//			this.bicCodeBeanList = parseBicCode("B", receiverCorrespondent);
			screenCondition("B", receiverCorrespondent, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField54D() != null){
			this.receiverCorrespondent = mt103.getField54D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", receiverCorrespondent);
			screenCondition("D", receiverCorrespondent, this.bicCodeBeanList, this.orConditionMap);
		}
		
		if(mt103.getField55A() != null){
			this.thirdReimburseInstitution = mt103.getField55A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", thirdReimburseInstitution);
			screenCondition("A", thirdReimburseInstitution, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField55B() != null){
			this.thirdReimburseInstitution = mt103.getField55B().getValue();
//			this.bicCodeBeanList = parseBicCode("B", thirdReimburseInstitution);
			screenCondition("B", thirdReimburseInstitution, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField55D() != null){
			this.thirdReimburseInstitution = mt103.getField55D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", thirdReimburseInstitution);
			screenCondition("D", thirdReimburseInstitution, this.bicCodeBeanList, this.orConditionMap);
		}
		
		if(mt103.getField56A() != null){
			this.intermediaryInstitution = mt103.getField56A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", intermediaryInstitution);
			screenCondition("A", intermediaryInstitution, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField56C() != null){
			this.intermediaryInstitution = mt103.getField56C().getValue();
//			this.bicCodeBeanList = parseBicCode("C", intermediaryInstitution);
			screenCondition("C", intermediaryInstitution, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField56D() != null){
			this.intermediaryInstitution = mt103.getField56D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", intermediaryInstitution);
			screenCondition("D", intermediaryInstitution, this.bicCodeBeanList, this.orConditionMap);
		}
		
		if(mt103.getField57A() != null){
			this.accountWithInstitution = mt103.getField57A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", accountWithInstitution);
			screenCondition("A", accountWithInstitution, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField57B() != null){
			this.accountWithInstitution = mt103.getField57B().getValue();
//			this.bicCodeBeanList = parseBicCode("B", accountWithInstitution);
			screenCondition("B", accountWithInstitution, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField57C() != null){
			this.accountWithInstitution = mt103.getField57D().getValue();
//			this.bicCodeBeanList = parseBicCode("C", accountWithInstitution);
			screenCondition("C", accountWithInstitution, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField57D() != null){
			this.accountWithInstitution = mt103.getField57D().getValue();
//			this.bicCodeBeanList = parseBicCode("D", accountWithInstitution);
			screenCondition("D", accountWithInstitution, this.bicCodeBeanList, this.orConditionMap);
		}
		
		if(mt103.getField59A() != null){
			this.beneficiaryCustomer = mt103.getField59A().getValue();
//			this.bicCodeBeanList = parseBicCode("A", beneficiaryCustomer);
			screenCondition("A", beneficiaryCustomer, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField59F() != null){
			this.beneficiaryCustomer = mt103.getField59F().getValue();
//			this.bicCodeBeanList = parseBicCode("F", beneficiaryCustomer);
			screenCondition("F", beneficiaryCustomer, this.bicCodeBeanList, this.orConditionMap);
		}else if(mt103.getField59() != null){
			this.beneficiaryCustomer = mt103.getField59().getValue();
//			this.bicCodeBeanList = parseBicCode("", beneficiaryCustomer);
			screenCondition("", beneficiaryCustomer, this.bicCodeBeanList, this.orConditionMap);
		}
		
		this.remittanceInformation = mt103.getField70() == null ? null : mt103.getField70().getValue();
		//screenCondition("", remittanceInformation, this.bicCodeBeanList, this.orConditionMap);
		
		this.detailsOfCharges = mt103.getField71A() == null ? null : mt103.getField71A().getValue();
		// this' list need to confirm
		for(Field71F field71f : mt103.getField71F()){
			this.senderCharges = field71f.getValue();
		}
		
		this.receiverCharges = mt103.getField71G() == null ? null : mt103.getField71G().getValue();
		this.senderInformation = mt103.getField72() == null ? null : mt103.getField72().getValue();
		//screenCondition("", senderInformation, this.bicCodeBeanList, this.orConditionMap);
		
		this.regulatoryReporting = mt103.getField77B() == null ? null : mt103.getField77B().getValue();
		//screenCondition("", regulatoryReporting, this.bicCodeBeanList, this.orConditionMap);
		
		//screening fields' or condition to entity_name 
//		orCond.add(this.orderingCustomer);
//		orCond.add(this.sendingInstitution);
//		orCond.add(this.orderingInstitution);
//		orCond.add(this.senderCorrespondent);
//		orCond.add(this.receiverCorrespondent);
//		orCond.add(this.thirdReimburseInstitution);
//		orCond.add(this.intermediaryInstitution);
//		orCond.add(this.accountWithInstitution);
//		orCond.add(this.beneficiaryCustomer);
		
		// free text using charIndex
		inclusiveCond.add(this.remittanceInformation);
		inclusiveCond.add(this.senderInformation);
		inclusiveCond.add(this.regulatoryReporting);
	}
	
	public MT103 getMt103() {
		return mt103;
	}
	public void setMt103(MT103 mt103) {
		this.mt103 = mt103;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getAccountWithInstitution() {
		return accountWithInstitution;
	}
	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
	}
	public String getBankOperationCode() {
		return bankOperationCode;
	}
	public void setBankOperationCode(String bankOperationCode) {
		this.bankOperationCode = bankOperationCode;
	}
	public String getBeneficiaryCustomer() {
		return beneficiaryCustomer;
	}
	public void setBeneficiaryCustomer(String beneficiaryCustomer) {
		this.beneficiaryCustomer = beneficiaryCustomer;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public String getDetailsOfCharges() {
		return detailsOfCharges;
	}
	public void setDetailsOfCharges(String detailsOfCharges) {
		this.detailsOfCharges = detailsOfCharges;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getInstructedAmount() {
		return instructedAmount;
	}
	public void setInstructedAmount(String instructedAmount) {
		this.instructedAmount = instructedAmount;
	}
	public String getInstructionCode() {
		return instructionCode;
	}
	public void setInstructionCode(String instructionCode) {
		this.instructionCode = instructionCode;
	}
	public String getIntermediaryInstitution() {
		return intermediaryInstitution;
	}
	public void setIntermediaryInstitution(String intermediaryInstitution) {
		this.intermediaryInstitution = intermediaryInstitution;
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
	public String getLogicalTerminalAddr() {
		return logicalTerminalAddr;
	}
	public void setLogicalTerminalAddr(String logicalTerminalAddr) {
		this.logicalTerminalAddr = logicalTerminalAddr;
	}
	public String getOrderingCustomer() {
		return orderingCustomer;
	}
	public void setOrderingCustomer(String orderingCustomer) {
		this.orderingCustomer = orderingCustomer;
	}
	public String getOrderingInstitution() {
		return orderingInstitution;
	}
	public void setOrderingInstitution(String orderingInstitution) {
		this.orderingInstitution = orderingInstitution;
	}
	public String getReceiverCharges() {
		return receiverCharges;
	}
	public void setReceiverCharges(String receiverCharges) {
		this.receiverCharges = receiverCharges;
	}
	public String getReceiverCorrespondent() {
		return receiverCorrespondent;
	}
	public void setReceiverCorrespondent(String receiverCorrespondent) {
		this.receiverCorrespondent = receiverCorrespondent;
	}
	public String getRegulatoryReporting() {
		return regulatoryReporting;
	}
	public void setRegulatoryReporting(String regulatoryReporting) {
		this.regulatoryReporting = regulatoryReporting;
	}
	public String getRemittanceInformation() {
		return remittanceInformation;
	}
	public void setRemittanceInformation(String remittanceInformation) {
		this.remittanceInformation = remittanceInformation;
	}
	public String getSenderCharges() {
		return senderCharges;
	}
	public void setSenderCharges(String senderCharges) {
		this.senderCharges = senderCharges;
	}
	public String getSenderCorrespondent() {
		return senderCorrespondent;
	}
	public void setSenderCorrespondent(String senderCorrespondent) {
		this.senderCorrespondent = senderCorrespondent;
	}
	public String getSenderInformation() {
		return senderInformation;
	}
	public void setSenderInformation(String senderInformation) {
		this.senderInformation = senderInformation;
	}
	public String getSenderReference() {
		return senderReference;
	}
	public void setSenderReference(String senderReference) {
		this.senderReference = senderReference;
	}
	public String getSendingInstitution() {
		return sendingInstitution;
	}
	public void setSendingInstitution(String sendingInstitution) {
		this.sendingInstitution = sendingInstitution;
	}
	public String getSettledAmount() {
		return settledAmount;
	}
	public void setSettledAmount(String settledAmount) {
		this.settledAmount = settledAmount;
	}
	public String getSwiftCaseId() {
		return swiftCaseId;
	}
	public void setSwiftCaseId(String swiftCaseId) {
		this.swiftCaseId = swiftCaseId;
	}
	public String getThirdReimburseInstitution() {
		return thirdReimburseInstitution;
	}
	public void setThirdReimburseInstitution(String thirdReimburseInstitution) {
		this.thirdReimburseInstitution = thirdReimburseInstitution;
	}
	public String getTimeIndication() {
		return timeIndication;
	}
	public void setTimeIndication(String timeIndication) {
		this.timeIndication = timeIndication;
	}
	public String getTransactionTypeCode() {
		return transactionTypeCode;
	}
	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	@Override
	public String toString() {
		return "SwiftMt103Bean [mt103=" + mt103 + ", msgId=" + msgId
				+ ", accountWithInstitution=" + accountWithInstitution
				+ ", bankOperationCode=" + bankOperationCode
				+ ", beneficiaryCustomer=" + beneficiaryCustomer
				+ ", createBy=" + createBy + ", createdTimestamp="
				+ createdTimestamp + ", detailsOfCharges=" + detailsOfCharges
				+ ", exchangeRate=" + exchangeRate + ", instructedAmount="
				+ instructedAmount + ", instructionCode=" + instructionCode
				+ ", intermediaryInstitution=" + intermediaryInstitution
				+ ", lastUpdatedBy=" + lastUpdatedBy
				+ ", lastUpdatedTimestamp=" + lastUpdatedTimestamp
				+ ", logicalTerminalAddr=" + logicalTerminalAddr
				+ ", orderingCustomer=" + orderingCustomer
				+ ", orderingInstitution=" + orderingInstitution
				+ ", receiverCharges=" + receiverCharges
				+ ", receiverCorrespondent=" + receiverCorrespondent
				+ ", regulatoryReporting=" + regulatoryReporting
				+ ", remittanceInformation=" + remittanceInformation
				+ ", senderCharges=" + senderCharges + ", senderCorrespondent="
				+ senderCorrespondent + ", senderInformation="
				+ senderInformation + ", senderReference=" + senderReference
				+ ", sendingInstitution=" + sendingInstitution
				+ ", settledAmount=" + settledAmount + ", swiftCaseId="
				+ swiftCaseId + ", thirdReimburseInstitution="
				+ thirdReimburseInstitution + ", timeIndication="
				+ timeIndication + ", transactionTypeCode="
				+ transactionTypeCode + "]";
	}
}
