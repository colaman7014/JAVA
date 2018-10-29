package com.sas.webservice.nameCheckStatus.bean;

import java.math.BigDecimal;

import com.sas.db.wlf.orm.nc.NameHitRecord;


public class NameCheckStatusHitoutputRestBean {
	private String uniqueKey;
	private int ncReferenceId;
	private String checkSeq;
	private String seq;
	private String citizenshipCountryCode;
	private String citizenshipCountryName;
	private String entityName;
	private String entityNameDisplay;
	private int exactMatchScore;
	private String identificationId;
	private String identificationTypeDesc;
	private String firstName;
	private int fuzzyMatchScore;
	private int inclusiveMatchScore;
	private String lastName;
	private String middleName;
	private int mixScore;
	private String placeOfBirth;
	private String typeDesc;
	private String watchListTypeCd;
	private String watchListSubTypeCd;
	private int weightScore;
	private BigDecimal yearOfBirth;
	
	public NameCheckStatusHitoutputRestBean(){
		
	}
	public NameCheckStatusHitoutputRestBean(NameHitRecord hitRecord){
		this.uniqueKey = hitRecord.getId().getUniqueKey();
		this.ncReferenceId = hitRecord.getId().getNcReferenceId();
		this.checkSeq = hitRecord.getId().getCheckSeq();
		this.seq = hitRecord.getId().getSeq();
		this.citizenshipCountryCode = hitRecord.getCitizenshipCountryCode();
		this.citizenshipCountryName = hitRecord.getCitizenshipCountryName();
		this.entityName = hitRecord.getEntityName();
		this.entityNameDisplay = hitRecord.getEntityNameDisplay();
		this.exactMatchScore = hitRecord.getExactMatchScore();
		this.firstName = hitRecord.getFirstName();
		this.fuzzyMatchScore = hitRecord.getFuzzyMatchScore();
		this.inclusiveMatchScore = hitRecord.getInclusiveMatchScore();
		this.identificationId = hitRecord.getIdentificationId();
		this.identificationTypeDesc = hitRecord.getIdentificationTypeDesc();
		this.lastName = hitRecord.getLastName();
		this.middleName = hitRecord.getMiddleName();
		this.mixScore = hitRecord.getMixScore();
		this.placeOfBirth = hitRecord.getPlaceOfBirth();
		this.typeDesc = hitRecord.getTypeDesc();
		this.watchListTypeCd = hitRecord.getWatchListTypeCd();
		this.watchListSubTypeCd = hitRecord.getWatchListSubTypeCd();
		this.weightScore = hitRecord.getWeightScore();
		this.yearOfBirth = hitRecord.getYearOfBirth();
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public int getNcReferenceId() {
		return ncReferenceId;
	}
	public void setNcReferenceId(int ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
	}
	public String getCheckSeq() {
		return checkSeq;
	}
	public void setCheckSeq(String checkSeq) {
		this.checkSeq = checkSeq;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	@Override
	public String toString() {
		return "NameCheckStatusHitoutputRestBean [uniqueKey=" + uniqueKey
				+ ", ncReferenceId=" + ncReferenceId + ", checkSeq=" + checkSeq
				+ ", seq=" + seq + ", citizenshipCountryCode="
				+ citizenshipCountryCode + ", citizenshipCountryName="
				+ citizenshipCountryName + ", entityName=" + entityName
				+ ", entityNameDisplay=" + entityNameDisplay
				+ ", exactMatchScore=" + exactMatchScore
				+ ", identificationId=" + identificationId
				+ ", identificationTypeDesc=" + identificationTypeDesc
				+ ", firstName=" + firstName + ", fuzzyMatchScore="
				+ fuzzyMatchScore + ", inclusiveMatchScore="
				+ inclusiveMatchScore + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", mixScore=" + mixScore
				+ ", placeOfBirth=" + placeOfBirth + ", typeDesc=" + typeDesc
				+ ", watchListTypeCd=" + watchListTypeCd
				+ ", watchListSubTypeCd=" + watchListSubTypeCd
				+ ", weightScore=" + weightScore + ", yearOfBirth="
				+ yearOfBirth + "]";
	}
	
}
