package com.sas.db.wlf.orm.nc;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.fcfcore.FscBankNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscEntityNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscEntityWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscLocationNcWatchListDim;


/**
 * The persistent class for the NAME_HIT_RECORD database table.
 * 
 */
@Entity
@Table(name="NAME_HIT_RECORD", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="NameHitRecord.findAll", query="SELECT n FROM NameHitRecord n")
public class NameHitRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NameHitRecordPK id;
	
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
	
	@Column(name="ENTITY_WATCH_LIST_KEY")
	private long entityWatchListKey;

	@Column(name="WATCH_LIST_NAME")
	private String watchListName;

	public NameHitRecord() {
	}
	
	public NameHitRecord(FscEntityNcWatchListDim fscEntityNcWatchListDim, String uniqueKey, int ncReferenceId, String checkSeq, int seq){
		NameHitRecordPK nameHitRecordPK = new NameHitRecordPK();
		nameHitRecordPK.setUniqueKey(uniqueKey);
		nameHitRecordPK.setNcReferenceId(ncReferenceId);
		nameHitRecordPK.setCheckSeq(checkSeq);
		nameHitRecordPK.setSeq(String.valueOf(seq));
		this.id = nameHitRecordPK;
		this.citizenshipCountryCode = fscEntityNcWatchListDim.getCountryCode();
		this.citizenshipCountryName = fscEntityNcWatchListDim.getCountryName();
		this.entityName = fscEntityNcWatchListDim.getEntityName();
		this.entityNameDisplay = fscEntityNcWatchListDim.getEntityNameDisplay();
		this.entityWatchListNumber = fscEntityNcWatchListDim.getEntityWatchListNumber();
		this.identificationId = fscEntityNcWatchListDim.getIdentificationId();
		//this.identificationTypeDesc = fscEntityNcWatchListDim.getIdentificationTypeDesc();
		this.firstName = fscEntityNcWatchListDim.getFirstName();
		this.lastName = fscEntityNcWatchListDim.getLastName();
		this.middleName = fscEntityNcWatchListDim.getMiddleName();
		//this.placeOfBirth = fscEntityNcWatchListDim.getPlaceOfBirth();
		this.typeDesc = fscEntityNcWatchListDim.getTypeDesc();
		this.yearOfBirth = fscEntityNcWatchListDim.getYearOfBirth();
		this.entityWatchListKey = fscEntityNcWatchListDim.getEntityWatchListKey();
		this.watchListName = fscEntityNcWatchListDim.getWatchListName();
//		this.exactMatchScore = fscEntityWatchListDim.get;
//		this.fuzzyMatchScore = fscEntityWatchListDim.get;
//		this.inclusiveMatchScore = fscEntityWatchListDim.get;
//		this.weightScore = fscEntityWatchListDim.get;
//		this.mixScore = fscEntityWatchListDim.get;
	}
	
	public NameHitRecord(FscBankNcWatchListDim fscBankNcWatchListDim, String uniqueKey, int ncReferenceId, String checkSeq, int seq){
		NameHitRecordPK nameHitRecordPK = new NameHitRecordPK();
		nameHitRecordPK.setUniqueKey(uniqueKey);
		nameHitRecordPK.setNcReferenceId(ncReferenceId);
		nameHitRecordPK.setCheckSeq(checkSeq);
		nameHitRecordPK.setSeq(String.valueOf(seq));
		this.id = nameHitRecordPK;
		this.entityName = fscBankNcWatchListDim.getBankName();
		this.entityNameDisplay = fscBankNcWatchListDim.getBankNameDisplay();
		this.entityWatchListNumber = fscBankNcWatchListDim.getBankWatchListNumber();
		this.entityWatchListKey = fscBankNcWatchListDim.getBankWatchListKey();
		this.watchListName = fscBankNcWatchListDim.getWatchListName();
//		this.exactMatchScore = fscEntityWatchListDim.get;
//		this.fuzzyMatchScore = fscEntityWatchListDim.get;
//		this.inclusiveMatchScore = fscEntityWatchListDim.get;
//		this.weightScore = fscEntityWatchListDim.get;
//		this.mixScore = fscEntityWatchListDim.get;
	}
	
	public NameHitRecord(FscLocationNcWatchListDim fscLocationNcWatchListDim, String uniqueKey, int ncReferenceId, String checkSeq, int seq){
		NameHitRecordPK nameHitRecordPK = new NameHitRecordPK();
		nameHitRecordPK.setUniqueKey(uniqueKey);
		nameHitRecordPK.setNcReferenceId(ncReferenceId);
		nameHitRecordPK.setCheckSeq(checkSeq);
		nameHitRecordPK.setSeq(String.valueOf(seq));
		this.id = nameHitRecordPK;
		this.citizenshipCountryCode = fscLocationNcWatchListDim.getCountryCode();
		this.entityName = fscLocationNcWatchListDim.getCountryName();
		this.entityNameDisplay = fscLocationNcWatchListDim.getCountryNameDisplay();
		this.entityWatchListNumber = fscLocationNcWatchListDim.getLocationWatchListNumber();
		this.entityWatchListKey = fscLocationNcWatchListDim.getLocationWatchListKey();
		this.watchListName = fscLocationNcWatchListDim.getWatchListName();
//		this.exactMatchScore = fscEntityWatchListDim.get;
//		this.fuzzyMatchScore = fscEntityWatchListDim.get;
//		this.inclusiveMatchScore = fscEntityWatchListDim.get;
//		this.weightScore = fscEntityWatchListDim.get;
//		this.mixScore = fscEntityWatchListDim.get;
	}
	
	public NameHitRecordPK getId() {
		return id;
	}

	public void setId(NameHitRecordPK id) {
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

	public long getEntityWatchListKey() {
		return entityWatchListKey;
	}

	public void setEntityWatchListKey(long entityWatchListKey) {
		this.entityWatchListKey = entityWatchListKey;
	}

	public String getWatchListName() {
		return watchListName;
	}

	public void setWatchListName(String watchListName) {
		this.watchListName = watchListName;
	}
}