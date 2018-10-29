package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the REF_TABLE_VALUE database table.
 * 
 */
@Embeddable
public class RefTableValuePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="REF_TABLE_NM")
	private String refTableNm;

	@Column(name="VALUE_CD")
	private String valueCd;

	public RefTableValuePK() {
	}
	public String getRefTableNm() {
		return this.refTableNm;
	}
	public void setRefTableNm(String refTableNm) {
		this.refTableNm = refTableNm;
	}
	public String getValueCd() {
		return this.valueCd;
	}
	public void setValueCd(String valueCd) {
		this.valueCd = valueCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RefTableValuePK)) {
			return false;
		}
		RefTableValuePK castOther = (RefTableValuePK)other;
		return 
			this.refTableNm.equals(castOther.refTableNm)
			&& this.valueCd.equals(castOther.valueCd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.refTableNm.hashCode();
		hash = hash * prime + this.valueCd.hashCode();
		
		return hash;
	}
}