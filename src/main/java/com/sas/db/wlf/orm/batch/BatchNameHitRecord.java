package com.sas.db.wlf.orm.batch;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.db.aml.orm.fcfcore.FscBankWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscEntityWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscLocationWatchListDim;


/**
 * The persistent class for the NAME_HIT_RECORD database table.
 * 
 */
@Entity
@Table(name="BATHC_NAME_HIT_RECORD")
@NamedQuery(name="BatchNameHitRecord.findAll", query="SELECT n FROM BatchNameHitRecord n")
public class BatchNameHitRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BatchNameHitRecordPK id;
	
	@Column(name="CITIZENSHIP_COUNTRY_CODE")
	private String citizenshipCountryCode;
	
	@Column(name="CITIZENSHIP_COUNTRY_NAME")
	private String citizenshipCountryName;

	@Column(name="ENTITY_NAME")
	private String entityName;
	
	@Column(name="ENTITY_NAME_DISPLAY")
	private String entityNameDisplay;
	
	@Column(name="ENTITY_WATCH_LIST_NUMBER")
	private String entityWatchListNumber;

	@Column(name="EXACT_MATCH_SCORE")
	private int exactMatchScore;
	
	@Column(name="IDENTIFICATION_ID")
	private String identificationId;
	
	@Column(name="IDENTIFICATION_TYPE_DESC")
	private String identificationTypeDesc;
	
	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="FUZZY_MATCH_SCORE")
	private int fuzzyMatchScore;

	@Column(name="INCLUSIVE_MATCH_SCORE")
	private int inclusiveMatchScore;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="MIDDLE_NAME")
	private String middleName;

	@Column(name="MIX_SCORE")
	private int mixScore;

	@Column(name="PLACE_OF_BIRTH")
	private String placeOfBirth;
	
	@Column(name="TYPE_DESC")
	private String typeDesc;

	@Column(name="WATCHLIST_TYPE_CD")
	private String watchListTypeCd;
	
	@Column(name="WATCHLIST_SUB_TYPE_CD")
	private String watchListSubTypeCd;

	@Column(name="WEIGHT_SCORE")
	private int weightScore;

	@Column(name="YEAR_OF_BIRTH")
	private BigDecimal yearOfBirth;

	public BatchNameHitRecord() {
	}
	
	public BatchNameHitRecord(FscEntityWatchListDim fscEntityWatchListDim, String uniqueKey, int ncReferenceId, String checkSeq, int seq){
		BatchNameHitRecordPK batchNameHitRecordPK = new BatchNameHitRecordPK();
		batchNameHitRecordPK.setUniqueKey(uniqueKey);
		batchNameHitRecordPK.setNcReferenceId(ncReferenceId);
		batchNameHitRecordPK.setCheckSeq(checkSeq);
		batchNameHitRecordPK.setSeq(String.valueOf(seq));
		this.id = batchNameHitRecordPK;
		this.citizenshipCountryCode = fscEntityWatchListDim.getCitizenshipCountryCode();
		this.citizenshipCountryName = fscEntityWatchListDim.getCitizenshipCountryName();
		this.entityName = fscEntityWatchListDim.getEntityName();
		this.entityNameDisplay = fscEntityWatchListDim.getEntityNameDisplay();
		this.entityWatchListNumber = fscEntityWatchListDim.getEntityWatchListNumber();
		this.identificationId = fscEntityWatchListDim.getIdentificationId();
		this.identificationTypeDesc = fscEntityWatchListDim.getIdentificationTypeDesc();
		this.firstName = fscEntityWatchListDim.getFirstName();
		this.lastName = fscEntityWatchListDim.getLastName();
		this.middleName = fscEntityWatchListDim.getMiddleName();
		this.placeOfBirth = fscEntityWatchListDim.getPlaceOfBirth();
		this.typeDesc = fscEntityWatchListDim.getTypeDesc();
		this.yearOfBirth = fscEntityWatchListDim.getYearOfBirth();
//		this.exactMatchScore = fscEntityWatchListDim.get;
//		this.fuzzyMatchScore = fscEntityWatchListDim.get;
//		this.inclusiveMatchScore = fscEntityWatchListDim.get;
//		this.weightScore = fscEntityWatchListDim.get;
//		this.mixScore = fscEntityWatchListDim.get;
	}
	
	public BatchNameHitRecord(FscBankWatchListDim fscBankWatchListDim, String uniqueKey, int ncReferenceId, String checkSeq, int seq){
		BatchNameHitRecordPK batchNameHitRecordPK = new BatchNameHitRecordPK();
		batchNameHitRecordPK.setUniqueKey(uniqueKey);
		batchNameHitRecordPK.setNcReferenceId(ncReferenceId);
		batchNameHitRecordPK.setCheckSeq(checkSeq);
		batchNameHitRecordPK.setSeq(String.valueOf(seq));
		this.id = batchNameHitRecordPK;
		this.entityName = fscBankWatchListDim.getBankName();
		this.entityNameDisplay = fscBankWatchListDim.getBankNameDisplay();
		this.entityWatchListNumber = fscBankWatchListDim.getBankWatchListNumber();
//		this.exactMatchScore = fscEntityWatchListDim.get;
//		this.fuzzyMatchScore = fscEntityWatchListDim.get;
//		this.inclusiveMatchScore = fscEntityWatchListDim.get;
//		this.weightScore = fscEntityWatchListDim.get;
//		this.mixScore = fscEntityWatchListDim.get;
	}
	
	public BatchNameHitRecord(FscLocationWatchListDim fscLocationWatchListDim, String uniqueKey, int ncReferenceId, String checkSeq, int seq){
		BatchNameHitRecordPK batchNameHitRecordPK = new BatchNameHitRecordPK();
		batchNameHitRecordPK.setUniqueKey(uniqueKey);
		batchNameHitRecordPK.setNcReferenceId(ncReferenceId);
		batchNameHitRecordPK.setCheckSeq(checkSeq);
		batchNameHitRecordPK.setSeq(String.valueOf(seq));
		this.id = batchNameHitRecordPK;
		this.citizenshipCountryCode = fscLocationWatchListDim.getCountryCode();
		this.entityName = fscLocationWatchListDim.getCountryName();
		this.entityNameDisplay = fscLocationWatchListDim.getCountryNameDisplay();
		this.entityWatchListNumber = fscLocationWatchListDim.getLocationWatchListNumber();
//		this.exactMatchScore = fscEntityWatchListDim.get;
//		this.fuzzyMatchScore = fscEntityWatchListDim.get;
//		this.inclusiveMatchScore = fscEntityWatchListDim.get;
//		this.weightScore = fscEntityWatchListDim.get;
//		this.mixScore = fscEntityWatchListDim.get;
	}
	
	public BatchNameHitRecordPK getId() {
		return id;
	}

	public void setId(BatchNameHitRecordPK id) {
		this.id = id;
	}

	public String getCitizenshipCountryCode() {
		return citizenshipCountryCode;
	}

	public void setCitizenshipCountryCode(String citizenshipCountryCode) {
		this.citizenshipCountryCode = citizenshipCountryCode;
	}

	public String getCitizenshipCountryName() {
		return citizenshipCountryName;
	}

	public void setCitizenshipCountryName(String citizenshipCountryName) {
		this.citizenshipCountryName = citizenshipCountryName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityNameDisplay() {
		return entityNameDisplay;
	}

	public void setEntityNameDisplay(String entityNameDisplay) {
		this.entityNameDisplay = entityNameDisplay;
	}

	public String getEntityWatchListNumber() {
		return entityWatchListNumber;
	}

	public void setEntityWatchListNumber(String entityWatchListNumber) {
		this.entityWatchListNumber = entityWatchListNumber;
	}

	public int getExactMatchScore() {
		return exactMatchScore;
	}

	public void setExactMatchScore(int exactMatchScore) {
		this.exactMatchScore = exactMatchScore;
	}

	public String getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}

	public String getIdentificationTypeDesc() {
		return identificationTypeDesc;
	}

	public void setIdentificationTypeDesc(String identificationTypeDesc) {
		this.identificationTypeDesc = identificationTypeDesc;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getFuzzyMatchScore() {
		return fuzzyMatchScore;
	}

	public void setFuzzyMatchScore(int fuzzyMatchScore) {
		this.fuzzyMatchScore = fuzzyMatchScore;
	}

	public int getInclusiveMatchScore() {
		return inclusiveMatchScore;
	}

	public void setInclusiveMatchScore(int inclusiveMatchScore) {
		this.inclusiveMatchScore = inclusiveMatchScore;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public int getMixScore() {
		return mixScore;
	}

	public void setMixScore(int mixScore) {
		this.mixScore = mixScore;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getWatchListTypeCd() {
		return watchListTypeCd;
	}

	public void setWatchListTypeCd(String watchListTypeCd) {
		this.watchListTypeCd = watchListTypeCd;
	}

	public String getWatchListSubTypeCd() {
		return watchListSubTypeCd;
	}

	public void setWatchListSubTypeCd(String watchListSubTypeCd) {
		this.watchListSubTypeCd = watchListSubTypeCd;
	}

	public int getWeightScore() {
		return weightScore;
	}

	public void setWeightScore(int weightScore) {
		this.weightScore = weightScore;
	}

	public BigDecimal getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(BigDecimal yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
}