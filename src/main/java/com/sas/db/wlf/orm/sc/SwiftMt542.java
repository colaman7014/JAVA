package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the SWIFT_MT542 database table.
 * 
 */
@Entity
@Table(name="SWIFT_MT542", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="SwiftMt542.findAll", query="SELECT s FROM SwiftMt542 s")
public class SwiftMt542 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SwiftMt542PK id;

	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name="ACCOUNT_OWNER")
	private String accountOwner;

	@Column(name="ACTIVITY_FLAG")
	private String activityFlag;

	@Column(name="AMOUNT")
	private String amount;

	@Column(name="BOOK_LOT_PRICE")
	private String bookLotPrice;

	@Column(name="CERTIFICATE_NUMBER")
	private String certificateNumber;

	@Column(name="CREATE_BY")
	private String createBy;

	@Column(name="CREATED_TIMESTAMP")
	private Timestamp createdTimestamp;

	@Column(name="CURRENCY_OF_DENOMINATION")
	private String currencyOfDenomination;

	@Column(name="CURRENCY_TO_BUY")
	private String currencyToBuy;

	@Column(name="DEAL_PRICE")
	private String dealPrice;

	@Column(name="DENOMINATION_CHOICE")
	private String denominationChoice;

	@Column(name="END_OF_BLOCK")
	private String endOfBlock;

	@Column(name="EXCHANGE_RATE")
	private String exchangeRate;

	@Column(name="FINANCIAL_INSTRUMENT_ATTRIBUTE_NARRATIVE")
	private String financialInstrumentAttributeNarrative;

	@Column(name="FUNCTION_OF_THE_MESSAGE")
	private String functionOfTheMessage;

	@Column(name="IDENTIFICATION_OF_THE_FINANCIAL_INSTRUMENT")
	private String identificationOfTheFinancialInstrument;

	@Column(name="LINKAGE_TYPE_INDICATOR")
	private String linkageTypeIndicator;

	@Column(name="LINKED_MESSAGE")
	private String linkedMessage;

	@Column(name="LOT_DATE_TIME")
	private String lotDateTime;

	@Column(name="LOT_NUMBER")
	private String lotNumber;

	@Column(name="NARRATIVE")
	private String narrative;

	@Column(name="NUMBER_OF_DAYS_ACCRUED")
	private String numberOfDaysAccrued;

	@Column(name="PLACE_OF_LISTING")
	private String placeOfListing;

	@Column(name="PLACE_OF_SAFEKEEPING")
	private String placeOfSafekeeping;

	@Column(name="PREPARATION_DATE_TIME")
	private String preparationDateTime;

	@Column(name="PROCESSING_DATE_TIME")
	private String processingDateTime;

	@Column(name="PROCESSING_REFERENCE")
	private String processingReference;

	@Column(name="PROCESSING_STATUS")
	private String processingStatus;

	@Column(name="QUANTITY_OF_FINANCIAL_INSTRUMENT_IN_THE_LOT")
	private String quantityOfFinancialInstrumentInTheLot;

	@Column(name="QUANTITY_OF_FINANCIAL_INSTRUMENT_TO_BE_SETTLED")
	private String quantityOfFinancialInstrumentToBeSettled;

	@Column(name="RATE")
	private String rate;

	@Column(name="SAFEKEEPING_ACCOUNT")
	private String safekeepingAccount;

	@Column(name="SECOND_LEG_NARRATIVE")
	private String secondLegNarrative;

	@Column(name="SENDERS_MESSAGE_REFERENCE")
	private String sendersMessageReference;

	@Column(name="START_OF_BLOCK")
	private String startOfBlock;

	@Column(name="SWIFT_FULL_TEXT")
	private String swiftFullText;

	@Column(name="TRANSACTION_CALL_DELAY")
	private String transactionCallDelay;

	@Column(name="TYPE_OF_FINANCIAL_INSTRUMENT")
	private String typeOfFinancialInstrument;

	@Column(name="TYPE_OF_PRICE_INDICATOR")
	private String typeOfPriceIndicator;

	public SwiftMt542() {
	}

	public SwiftMt542PK getId() {
		return this.id;
	}

	public void setId(SwiftMt542PK id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountOwner() {
		return this.accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getActivityFlag() {
		return this.activityFlag;
	}

	public void setActivityFlag(String activityFlag) {
		this.activityFlag = activityFlag;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBookLotPrice() {
		return this.bookLotPrice;
	}

	public void setBookLotPrice(String bookLotPrice) {
		this.bookLotPrice = bookLotPrice;
	}

	public String getCertificateNumber() {
		return this.certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreatedTimestamp() {
		return this.createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getCurrencyOfDenomination() {
		return this.currencyOfDenomination;
	}

	public void setCurrencyOfDenomination(String currencyOfDenomination) {
		this.currencyOfDenomination = currencyOfDenomination;
	}

	public String getCurrencyToBuy() {
		return this.currencyToBuy;
	}

	public void setCurrencyToBuy(String currencyToBuy) {
		this.currencyToBuy = currencyToBuy;
	}

	public String getDealPrice() {
		return this.dealPrice;
	}

	public void setDealPrice(String dealPrice) {
		this.dealPrice = dealPrice;
	}

	public String getDenominationChoice() {
		return this.denominationChoice;
	}

	public void setDenominationChoice(String denominationChoice) {
		this.denominationChoice = denominationChoice;
	}

	public String getEndOfBlock() {
		return this.endOfBlock;
	}

	public void setEndOfBlock(String endOfBlock) {
		this.endOfBlock = endOfBlock;
	}

	public String getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getFinancialInstrumentAttributeNarrative() {
		return this.financialInstrumentAttributeNarrative;
	}

	public void setFinancialInstrumentAttributeNarrative(String financialInstrumentAttributeNarrative) {
		this.financialInstrumentAttributeNarrative = financialInstrumentAttributeNarrative;
	}

	public String getFunctionOfTheMessage() {
		return this.functionOfTheMessage;
	}

	public void setFunctionOfTheMessage(String functionOfTheMessage) {
		this.functionOfTheMessage = functionOfTheMessage;
	}

	public String getIdentificationOfTheFinancialInstrument() {
		return this.identificationOfTheFinancialInstrument;
	}

	public void setIdentificationOfTheFinancialInstrument(String identificationOfTheFinancialInstrument) {
		this.identificationOfTheFinancialInstrument = identificationOfTheFinancialInstrument;
	}

	public String getLinkageTypeIndicator() {
		return this.linkageTypeIndicator;
	}

	public void setLinkageTypeIndicator(String linkageTypeIndicator) {
		this.linkageTypeIndicator = linkageTypeIndicator;
	}

	public String getLinkedMessage() {
		return this.linkedMessage;
	}

	public void setLinkedMessage(String linkedMessage) {
		this.linkedMessage = linkedMessage;
	}

	public String getLotDateTime() {
		return this.lotDateTime;
	}

	public void setLotDateTime(String lotDateTime) {
		this.lotDateTime = lotDateTime;
	}

	public String getLotNumber() {
		return this.lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getNarrative() {
		return this.narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public String getNumberOfDaysAccrued() {
		return this.numberOfDaysAccrued;
	}

	public void setNumberOfDaysAccrued(String numberOfDaysAccrued) {
		this.numberOfDaysAccrued = numberOfDaysAccrued;
	}

	public String getPlaceOfListing() {
		return this.placeOfListing;
	}

	public void setPlaceOfListing(String placeOfListing) {
		this.placeOfListing = placeOfListing;
	}

	public String getPlaceOfSafekeeping() {
		return this.placeOfSafekeeping;
	}

	public void setPlaceOfSafekeeping(String placeOfSafekeeping) {
		this.placeOfSafekeeping = placeOfSafekeeping;
	}

	public String getPreparationDateTime() {
		return this.preparationDateTime;
	}

	public void setPreparationDateTime(String preparationDateTime) {
		this.preparationDateTime = preparationDateTime;
	}

	public String getProcessingDateTime() {
		return this.processingDateTime;
	}

	public void setProcessingDateTime(String processingDateTime) {
		this.processingDateTime = processingDateTime;
	}

	public String getProcessingReference() {
		return this.processingReference;
	}

	public void setProcessingReference(String processingReference) {
		this.processingReference = processingReference;
	}

	public String getProcessingStatus() {
		return this.processingStatus;
	}

	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}

	public String getQuantityOfFinancialInstrumentInTheLot() {
		return this.quantityOfFinancialInstrumentInTheLot;
	}

	public void setQuantityOfFinancialInstrumentInTheLot(String quantityOfFinancialInstrumentInTheLot) {
		this.quantityOfFinancialInstrumentInTheLot = quantityOfFinancialInstrumentInTheLot;
	}

	public String getQuantityOfFinancialInstrumentToBeSettled() {
		return this.quantityOfFinancialInstrumentToBeSettled;
	}

	public void setQuantityOfFinancialInstrumentToBeSettled(String quantityOfFinancialInstrumentToBeSettled) {
		this.quantityOfFinancialInstrumentToBeSettled = quantityOfFinancialInstrumentToBeSettled;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getSafekeepingAccount() {
		return this.safekeepingAccount;
	}

	public void setSafekeepingAccount(String safekeepingAccount) {
		this.safekeepingAccount = safekeepingAccount;
	}

	public String getSecondLegNarrative() {
		return this.secondLegNarrative;
	}

	public void setSecondLegNarrative(String secondLegNarrative) {
		this.secondLegNarrative = secondLegNarrative;
	}

	public String getSendersMessageReference() {
		return this.sendersMessageReference;
	}

	public void setSendersMessageReference(String sendersMessageReference) {
		this.sendersMessageReference = sendersMessageReference;
	}

	public String getStartOfBlock() {
		return this.startOfBlock;
	}

	public void setStartOfBlock(String startOfBlock) {
		this.startOfBlock = startOfBlock;
	}

	public String getSwiftFullText() {
		return this.swiftFullText;
	}

	public void setSwiftFullText(String swiftFullText) {
		this.swiftFullText = swiftFullText;
	}

	public String getTransactionCallDelay() {
		return this.transactionCallDelay;
	}

	public void setTransactionCallDelay(String transactionCallDelay) {
		this.transactionCallDelay = transactionCallDelay;
	}

	public String getTypeOfFinancialInstrument() {
		return this.typeOfFinancialInstrument;
	}

	public void setTypeOfFinancialInstrument(String typeOfFinancialInstrument) {
		this.typeOfFinancialInstrument = typeOfFinancialInstrument;
	}

	public String getTypeOfPriceIndicator() {
		return this.typeOfPriceIndicator;
	}

	public void setTypeOfPriceIndicator(String typeOfPriceIndicator) {
		this.typeOfPriceIndicator = typeOfPriceIndicator;
	}

}