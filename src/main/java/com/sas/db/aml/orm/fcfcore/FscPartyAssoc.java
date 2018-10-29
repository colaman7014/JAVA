package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FSC_PARTY_ASSOC database table.
 * 
 */
@Entity
@Table(name="FSC_PARTY_ASSOC")
@NamedQuery(name="FscPartyAssoc.findAll", query="SELECT f FROM FscPartyAssoc f")
public class FscPartyAssoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FscPartyAssocPK id;

	@Column(name="change_current_ind")
	private String changeCurrentInd;

	@Column(name="change_end_date")
	private Timestamp changeEndDate;

	public FscPartyAssoc() {
	}

	public FscPartyAssocPK getId() {
		return this.id;
	}

	public void setId(FscPartyAssocPK id) {
		this.id = id;
	}

	public String getChangeCurrentInd() {
		return changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getChangeEndDate() {
		return this.changeEndDate;
	}

	public void setChangeEndDate(Timestamp changeEndDate) {
		this.changeEndDate = changeEndDate;
	}

}