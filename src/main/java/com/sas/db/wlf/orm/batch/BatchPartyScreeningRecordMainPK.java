package com.sas.db.wlf.orm.batch;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the BATCH_PARTY_SCREENING_RECORD_MAIN database table.
 * 
 */
@Embeddable
public class BatchPartyScreeningRecordMainPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SCREENING_TYPE")
	private String screeningType;

	@Column(name="PARTY_NUMBER")
	private String partyNumber;
	
	@Column(name="RELATED_PARTY_NUMBER")
	private String relatedPartyNumber="";

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PROCESS_DTTM")
	private java.util.Date processDttm;

	public BatchPartyScreeningRecordMainPK() {
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
	public String getRelatedPartyNumber() {
		return relatedPartyNumber;
	}
	public void setRelatedPartyNumber(String relatedPartyNumber) {
		this.relatedPartyNumber = relatedPartyNumber;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BatchPartyScreeningRecordMainPK)) {
			return false;
		}
		BatchPartyScreeningRecordMainPK castOther = (BatchPartyScreeningRecordMainPK)other;
		return 
			this.screeningType.equals(castOther.screeningType)
			&& this.partyNumber.equals(castOther.partyNumber)
			&& this.processDttm.equals(castOther.processDttm)
		    && this.relatedPartyNumber.equals(castOther.relatedPartyNumber);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.screeningType.hashCode();
		hash = hash * prime + this.partyNumber.hashCode();
		hash = hash * prime + this.processDttm.hashCode();
		hash = hash * prime + this.relatedPartyNumber.hashCode();
		
		return hash;
	}
}