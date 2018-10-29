package com.sas.db.wlf.orm.batch;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

import java.math.BigDecimal;


/**
 * The persistent class for the BATCH_PARTY_SCREENING_RECORD_DETAIL database table.
 * 
 */
@Entity
@Table(name="BATCH_PARTY_SCREENING_RECORD_DETAIL")
@NamedQuery(name="BatchPartyScreeningRecordDetail.findAll", query="SELECT b FROM BatchPartyScreeningRecordDetail b")
public class BatchPartyScreeningRecordDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BatchPartyScreeningRecordDetailPK id;

	@Column(name="ENTITY_NAME")
	private String entityName="";

	@Column(name="ENTITY_NAME_DISPLAY")
	private String entityNameDisplay="";

	@Column(name="ENTITY_WATCH_LIST_NUMBER")
	private String entityWatchListNumber="";

	@Column(name="EXACT_MATCH_SCORE")
	private int exactMatchScore;

	@Column(name="FUZZY_MATCH_SCORE")
	private int fuzzyMatchScore;

	@Column(name="INCLUSIVE_MATCH_SCORE")
	private int inclusiveMatchScore;

	@Column(name="MIX_SCORE")
	private int mixScore;

	@Column(name="WATCHLIST_SUB_TYPE_CD")
	private String watchlistSubTypeCd="";

	@Column(name="WATCHLIST_TYPE_CD")
	private String watchlistTypeCd="";
	
//	@Column(name = "PEP_FLAG")
//	private String pepFlag="";
	
	@Column(name = "PANA_FLAG")
	private String panaFlag="";

	public BatchPartyScreeningRecordDetail() {
	}

	public BatchPartyScreeningRecordDetailPK getId() {
		return this.id;
	}

	public void setId(BatchPartyScreeningRecordDetailPK id) {
		this.id = id;
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

	public int getMixScore() {
		return mixScore;
	}

	public void setMixScore(int mixScore) {
		this.mixScore = mixScore;
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

//	public String getPepFlag() {
//		return pepFlag;
//	}
//
//	public void setPepFlag(String pepFlag) {
//		this.pepFlag = pepFlag;
//	}

	public String getPanaFlag() {
		return panaFlag;
	}

	public void setPanaFlag(String panaFlag) {
		this.panaFlag = panaFlag;
	}



}