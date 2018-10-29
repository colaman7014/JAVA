package com.sas.db.wlf.orm.batch;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BATCH_PARTY_SCREENING_RECORD database table.
 * 
 */
@Entity
@Table(name="BATCH_PARTY_SCREENING_RECORD")
@NamedQuery(name="BatchPartyScreeningRecord.findAll", query="SELECT b FROM BatchPartyScreeningRecord b")
public class BatchPartyScreeningRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BatchPartyScreeningRecordPK id;

	@Column(name="CASE_RK")
	private BigDecimal caseRk;
	
	@Column(name="ENTITY_WATCH_LIST_NUMBER")
	private String  entityWatchListNumber;

	@Column(name="ENTITY_NAME")
	private String entityName;

	@Column(name="ENTITY_NAME_DISPLAY")
	private String entityNameDisplay;

	@Column(name="EXACT_MATCH_SCORE")
	private int exactMatchScore;

	@Column(name="FUZZY_MATCH_SCORE")
	private int fuzzyMatchScore;

	@Column(name="INCIDENT_RK")
	private BigDecimal incidentRk;

	@Column(name="INCLUSIVE_MATCH_SCORE")
	private int inclusiveMatchScore;

	@Column(name="MIX_SCORE")
	private int mixScore;

	@Column(name="PARTY_NAME1")
	private String partyName1;

	@Column(name="PARTY_NAME2")
	private String partyNameEng;

	@Column(name="PROCESS_DTTM")
	private Timestamp processDttm;

	@Column(name="SCREENING_TYPE")
	private String screeningType;

	@Column(name="WATCHLIST_SUB_TYPE_CD")
	private String watchlistSubTypeCd;

	@Column(name="WATCHLIST_TYPE_CD")
	private String watchlistTypeCd;

	@Column(name="EXTERNAL_PARTY_IND")
	private String externalPartyInd;
	
	@Column(name="RELATED_PARTY_NUMBER")
	private String relatedPartyNumber;
	
	@Column(name="RELACTIONSHIP_TYPE_CODE")
	private String relactionshipTypeCode;
	
	public BatchPartyScreeningRecord() {
	}

	public BatchPartyScreeningRecordPK getId() {
		return this.id;
	}

	public void setId(BatchPartyScreeningRecordPK id) {
		this.id = id;
	}

	public BigDecimal getCaseRk() {
		return caseRk;
	}

	
	
	public String getEntityWatchListNumber() {
		return entityWatchListNumber;
	}

	public void setEntityWatchListNumber(String entityWatchListNumber) {
		this.entityWatchListNumber = entityWatchListNumber;
	}

	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
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

	public int getFuzzyMatchScore() {
		return fuzzyMatchScore;
	}

	public void setFuzzyMatchScore(int fuzzyMatchScore) {
		this.fuzzyMatchScore = fuzzyMatchScore;
	}

	public BigDecimal getIncidentRk() {
		return incidentRk;
	}

	public void setIncidentRk(BigDecimal incidentRk) {
		this.incidentRk = incidentRk;
	}

	public int getInclusiveMatchScore() {
		return inclusiveMatchScore;
	}

	public void setInclusiveMatchScore(int inclusiveMatchScore) {
		this.inclusiveMatchScore = inclusiveMatchScore;
	}

	public int getMixScore() {
		return mixScore;
	}

	public void setMixScore(int mixScore) {
		this.mixScore = mixScore;
	}

	public String getPartyName1() {
		return partyName1;
	}

	public void setPartyName1(String partyName1) {
		this.partyName1 = partyName1;
	}

	public String getPartyNameEng() {
		return partyNameEng;
	}

	public void setPartyNameEng(String partyNameEng) {
		this.partyNameEng = partyNameEng;
	}

	public Timestamp getProcessDttm() {
		return processDttm;
	}

	public void setProcessDttm(Timestamp processDttm) {
		this.processDttm = processDttm;
	}

	public String getScreeningType() {
		return screeningType;
	}

	public void setScreeningType(String screeningType) {
		this.screeningType = screeningType;
	}

	public String getWatchlistSubTypeCd() {
		return watchlistSubTypeCd;
	}

	public void setWatchlistSubTypeCd(String watchlistSubTypeCd) {
		this.watchlistSubTypeCd = watchlistSubTypeCd;
	}

	public String getWatchlistTypeCd() {
		return watchlistTypeCd;
	}

	public void setWatchlistTypeCd(String watchlistTypeCd) {
		this.watchlistTypeCd = watchlistTypeCd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExternalPartyInd() {
		return externalPartyInd;
	}

	public void setExternalPartyInd(String externalPartyInd) {
		this.externalPartyInd = externalPartyInd;
	}

	public String getRelatedPartyNumber() {
		return relatedPartyNumber;
	}

	public void setRelatedPartyNumber(String relatedPartyNumber) {
		this.relatedPartyNumber = relatedPartyNumber;
	}

	public String getRelactionshipTypeCode() {
		return relactionshipTypeCode;
	}

	public void setRelactionshipTypeCode(String relactionshipTypeCode) {
		this.relactionshipTypeCode = relactionshipTypeCode;
	}



}