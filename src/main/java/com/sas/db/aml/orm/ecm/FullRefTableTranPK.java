package com.sas.db.aml.orm.ecm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * The persistent class for the FULL_REF_TABLE_TRANS database table.
 * 
 */
@Embeddable
public class FullRefTableTranPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="locale")
	private String locale;
	
	@Column(name="ref_table_nm")
	private String refTableNm;

	@Column(name="value_cd")
	private String valueCd;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getRefTableNm() {
		return refTableNm;
	}

	public void setRefTableNm(String refTableNm) {
		this.refTableNm = refTableNm;
	}

	public String getValueCd() {
		return valueCd;
	}

	public void setValueCd(String valueCd) {
		this.valueCd = valueCd;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FullRefTableTranPK)) {
			return false;
		}
		FullRefTableTranPK castOther = (FullRefTableTranPK)other;
		return 
			this.locale.equals(castOther.locale)
			&& this.refTableNm.equals(castOther.refTableNm)
			&& this.valueCd.equals(castOther.valueCd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.locale.hashCode();
		hash = hash * prime + this.refTableNm.hashCode();
		hash = hash * prime + this.valueCd.hashCode();
		
		return hash;
	}
}