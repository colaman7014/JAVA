package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FSC_PARTY_DIM database table.
 * 
 */
@Entity
@Table(name="FSC_PARTY_DIM_SCAN_CANDIDATE")
@NamedQuery(name="FscPartyDimScanCandidate.findAll", query="SELECT f FROM FscPartyDimScanCandidate f")
public class FscPartyDimScanCandidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="party_key")
	private long partyKey;

	public long getPartyKey() {
		return partyKey;
	}

	public void setPartyKey(long partyKey) {
		this.partyKey = partyKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}