package com.sas.db.aml.orm.ecm;

import java.io.Serializable;

import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the INCIDENT_CONFIG database table.
 * 
 */
@Entity
@Table(name="INCIDENT_CONFIG", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="IncidentConfig.findAll", query="SELECT i FROM IncidentConfig i")
public class IncidentConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INCIDENT_CONFIG_SEQ_NO")
	private long incidentConfigSeqNo;

	@Column(name="INCIDENT_CATEGORY_CD")
	private String incidentCategoryCd;

	@Column(name="INCIDENT_SUBCATEGORY_CD")
	private String incidentSubcategoryCd;

	@Column(name="INCIDENT_TYPE_CD")
	private String incidentTypeCd;

	@Column(name="UI_DEF_FILE_NM")
	private String uiDefFileNm;

	public IncidentConfig() {
	}

	public long getIncidentConfigSeqNo() {
		return this.incidentConfigSeqNo;
	}

	public void setIncidentConfigSeqNo(long incidentConfigSeqNo) {
		this.incidentConfigSeqNo = incidentConfigSeqNo;
	}

	public String getIncidentCategoryCd() {
		return this.incidentCategoryCd;
	}

	public void setIncidentCategoryCd(String incidentCategoryCd) {
		this.incidentCategoryCd = incidentCategoryCd;
	}

	public String getIncidentSubcategoryCd() {
		return this.incidentSubcategoryCd;
	}

	public void setIncidentSubcategoryCd(String incidentSubcategoryCd) {
		this.incidentSubcategoryCd = incidentSubcategoryCd;
	}

	public String getIncidentTypeCd() {
		return this.incidentTypeCd;
	}

	public void setIncidentTypeCd(String incidentTypeCd) {
		this.incidentTypeCd = incidentTypeCd;
	}

	public String getUiDefFileNm() {
		return this.uiDefFileNm;
	}

	public void setUiDefFileNm(String uiDefFileNm) {
		this.uiDefFileNm = uiDefFileNm;
	}

}