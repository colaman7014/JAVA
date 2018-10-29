package com.sas.db.aml.orm.ecm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the INCIDENT_UDF_CHAR_VALUE database table.
 * 
 */
@Entity
@Table(name="INCIDENT_UDF_CHAR_VALUE", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="IncidentUdfCharValue.findAll", query="SELECT i FROM IncidentUdfCharValue i")
public class IncidentUdfCharValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IncidentUdfCharValuePK id;

	@Column(name="UDF_VALUE")
	private String udfValue;

	public IncidentUdfCharValue() {
	}

	public IncidentUdfCharValuePK getId() {
		return this.id;
	}

	public void setId(IncidentUdfCharValuePK id) {
		this.id = id;
	}

	public String getUdfValue() {
		return this.udfValue;
	}

	public void setUdfValue(String udfValue) {
		this.udfValue = udfValue;
	}

}