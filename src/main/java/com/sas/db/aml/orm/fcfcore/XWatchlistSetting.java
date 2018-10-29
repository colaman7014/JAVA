package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the X_WATCHLIST_SETTING database table.
 * 
 */
@Entity
@Table(name="X_WATCHLIST_SETTING")
@NamedQuery(name="XWatchlistSetting.findAll", query="SELECT x FROM XWatchlistSetting x")
public class XWatchlistSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SETTING_KEY")
	private long settingKey;

	@Column(name="BIR_WEIGHT_MATCHING_SCORE")
	private BigDecimal birWeightMatchingScore;

	@Column(name="BIR_WEIGHT_NON_MATCHING_SCORE")
	private BigDecimal birWeightNonMatchingScore;

	@Column(name="BIRTH_YERAS")
	private BigDecimal birthYeras;

	@Column(name="CHANGE_BEGIN_DATE")
	private Timestamp changeBeginDate;

	@Column(name="CHANGE_CURRENT_IND")
	private String changeCurrentInd;

	@Column(name="CHANGE_END_DATE")
	private Timestamp changeEndDate;

	@Column(name="CREATE_USER_ID")
	private String createUserId;

	@Column(name="CRY_WEIGHT_MATCHING_SCORE")
	private BigDecimal cryWeightMatchingScore;

	@Column(name="CRY_WEIGHT_NON_MATCHING_SCORE")
	private BigDecimal cryWeightNonMatchingScore;

	@Column(name="EXACT_MATCHING_SCORE")
	private BigDecimal exactMatchingScore;

	@Column(name="FUZZY_MATCHING_SCORE")
	private BigDecimal fuzzyMatchingScore;

	@Column(name="FUZZY_MATCHING_SENSTIVE")
	private BigDecimal fuzzyMatchingSenstive;

	@Column(name="ID_WEIGHT_MATCHING_SCORE")
	private BigDecimal idWeightMatchingScore;

	@Column(name="ID_WEIGHT_NON_MATCHING_SCORE")
	private BigDecimal idWeightNonMatchingScore;

	@Column(name="INCLUSIVE_MATCHING_SCORE")
	private BigDecimal inclusiveMatchingScore;

	@Column(name="PRE_TEST_HIT_RATE")
	private BigDecimal preTestHitRate;

	public XWatchlistSetting() {
	}

	public long getSettingKey() {
		return this.settingKey;
	}

	public void setSettingKey(long settingKey) {
		this.settingKey = settingKey;
	}

	public BigDecimal getBirWeightMatchingScore() {
		return this.birWeightMatchingScore;
	}

	public void setBirWeightMatchingScore(BigDecimal birWeightMatchingScore) {
		this.birWeightMatchingScore = birWeightMatchingScore;
	}

	public BigDecimal getBirWeightNonMatchingScore() {
		return this.birWeightNonMatchingScore;
	}

	public void setBirWeightNonMatchingScore(BigDecimal birWeightNonMatchingScore) {
		this.birWeightNonMatchingScore = birWeightNonMatchingScore;
	}

	public BigDecimal getBirthYeras() {
		return this.birthYeras;
	}

	public void setBirthYeras(BigDecimal birthYeras) {
		this.birthYeras = birthYeras;
	}

	public Timestamp getChangeBeginDate() {
		return this.changeBeginDate;
	}

	public void setChangeBeginDate(Timestamp changeBeginDate) {
		this.changeBeginDate = changeBeginDate;
	}

	public String getChangeCurrentInd() {
		return this.changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	public Timestamp getChangeEndDate() {
		return this.changeEndDate;
	}

	public void setChangeEndDate(Timestamp changeEndDate) {
		this.changeEndDate = changeEndDate;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getCryWeightMatchingScore() {
		return this.cryWeightMatchingScore;
	}

	public void setCryWeightMatchingScore(BigDecimal cryWeightMatchingScore) {
		this.cryWeightMatchingScore = cryWeightMatchingScore;
	}

	public BigDecimal getCryWeightNonMatchingScore() {
		return this.cryWeightNonMatchingScore;
	}

	public void setCryWeightNonMatchingScore(BigDecimal cryWeightNonMatchingScore) {
		this.cryWeightNonMatchingScore = cryWeightNonMatchingScore;
	}

	public BigDecimal getExactMatchingScore() {
		return this.exactMatchingScore;
	}

	public void setExactMatchingScore(BigDecimal exactMatchingScore) {
		this.exactMatchingScore = exactMatchingScore;
	}

	public BigDecimal getFuzzyMatchingScore() {
		return this.fuzzyMatchingScore;
	}

	public void setFuzzyMatchingScore(BigDecimal fuzzyMatchingScore) {
		this.fuzzyMatchingScore = fuzzyMatchingScore;
	}

	public BigDecimal getFuzzyMatchingSenstive() {
		return this.fuzzyMatchingSenstive;
	}

	public void setFuzzyMatchingSenstive(BigDecimal fuzzyMatchingSenstive) {
		this.fuzzyMatchingSenstive = fuzzyMatchingSenstive;
	}

	public BigDecimal getIdWeightMatchingScore() {
		return this.idWeightMatchingScore;
	}

	public void setIdWeightMatchingScore(BigDecimal idWeightMatchingScore) {
		this.idWeightMatchingScore = idWeightMatchingScore;
	}

	public BigDecimal getIdWeightNonMatchingScore() {
		return this.idWeightNonMatchingScore;
	}

	public void setIdWeightNonMatchingScore(BigDecimal idWeightNonMatchingScore) {
		this.idWeightNonMatchingScore = idWeightNonMatchingScore;
	}

	public BigDecimal getInclusiveMatchingScore() {
		return this.inclusiveMatchingScore;
	}

	public void setInclusiveMatchingScore(BigDecimal inclusiveMatchingScore) {
		this.inclusiveMatchingScore = inclusiveMatchingScore;
	}

	public BigDecimal getPreTestHitRate() {
		return this.preTestHitRate;
	}

	public void setPreTestHitRate(BigDecimal preTestHitRate) {
		this.preTestHitRate = preTestHitRate;
	}

}