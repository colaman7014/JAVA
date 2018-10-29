package com.sas.db.aml.orm.ecm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;

/**
 * The persistent class for the CASE_UDF_CHAR_VALUE database table.
 * 
 */
@Entity
@Table(name="CASE_UDF_CHAR_VALUE", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="CaseUdfCharValue.findAll", query="SELECT c FROM CaseLive c")
public class CaseUdfCharValue  implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CaseUdfCharValuePK id;
	
	@Column(name="UDF_VALUE")
	private String udfValue;


	public CaseUdfCharValuePK getId() {
		return id;
	}

	public void setId(CaseUdfCharValuePK id) {
		this.id = id;
	}

	public String getUdfValue() {
		return udfValue;
	}

	public void setUdfValue(String udfValue) {
		this.udfValue = udfValue;
	}

	@Override
	public String toString() {
		return "CaseUdfCharValue [id=" + id + ", udfValue=" + udfValue + "]";
	}
	
	
}
