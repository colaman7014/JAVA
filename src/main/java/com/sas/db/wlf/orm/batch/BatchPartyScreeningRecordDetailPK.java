package com.sas.db.wlf.orm.batch;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * The primary key class for the BATCH_PARTY_SCREENING_RECORD_DETAIL database table.
 * 
 */
@Embeddable
public class BatchPartyScreeningRecordDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SCREENING_TYPE")
	private String screeningType;

	@Column(name="PARTY_NUMBER")
	private String partyNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PROCESS_DTTM")
	private java.util.Date processDttm;
	
	@Column(name="ENTITY_WATCH_LIST_KEY")
	private BigDecimal entityWatchListKey;

	public BatchPartyScreeningRecordDetailPK() {
	}
	public String getScreeningType() {
		return this.screeningType;
	}
	public void setScreeningType(String screeningType) {
		this.screeningType = screeningType;
	}
	public String getPartyNumber() {
		return this.partyNumber;
	}
	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}
	public java.util.Date getProcessDttm() {
		return this.processDttm;
	}
	public void setProcessDttm(java.util.Date processDttm) {
		this.processDttm = processDttm;
	}
	public BigDecimal getEntityWatchListKey() {
		return entityWatchListKey;
	}
	public void setEntityWatchListKey(BigDecimal entityWatchListKey) {
		this.entityWatchListKey = entityWatchListKey;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BatchPartyScreeningRecordDetailPK)) {
			return false;
		}
		BatchPartyScreeningRecordDetailPK castOther = (BatchPartyScreeningRecordDetailPK)other;
		return 
			this.screeningType.equals(castOther.screeningType)
			&& this.partyNumber.equals(castOther.partyNumber)
			&& this.processDttm.equals(castOther.processDttm)&& this.entityWatchListKey.equals(castOther.entityWatchListKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.screeningType.hashCode();
		hash = hash * prime + this.partyNumber.hashCode();
		hash = hash * prime + this.processDttm.hashCode();
		hash = hash * prime + this.entityWatchListKey.hashCode();
		
		return hash;
	}
}