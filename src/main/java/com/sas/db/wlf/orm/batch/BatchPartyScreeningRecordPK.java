package com.sas.db.wlf.orm.batch;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * The primary key class for the BATCH_PARTY_SCREENING_RECORD database table.
 * 
 */
@Embeddable
public class BatchPartyScreeningRecordPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PARTY_NUMBER")
	private String partyNumber;

	@Column(name="ENTITY_WATCH_LIST_KEY")
	private BigDecimal entityWatchListKey;

	public BatchPartyScreeningRecordPK() {
	}
	public String getPartyNumber() {
		return this.partyNumber;
	}
	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}


	public BigDecimal getEntityWatchListKey() {
		return entityWatchListKey;
	}
	public void setEntityWatchListKey(BigDecimal entityWatchListKey) {
		this.entityWatchListKey = entityWatchListKey;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BatchPartyScreeningRecordPK)) {
			return false;
		}
		BatchPartyScreeningRecordPK castOther = (BatchPartyScreeningRecordPK)other;
		return 
			this.partyNumber.equals(castOther.partyNumber)
			&& (this.entityWatchListKey == castOther.entityWatchListKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.partyNumber.hashCode();
		hash = hash * prime + ((int) (this.entityWatchListKey.intValue() ^ (this.entityWatchListKey.intValue() >>> 32)));
		
		return hash;
	}
}