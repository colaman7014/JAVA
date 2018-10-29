package com.sas.db.wlf.orm.batch;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BATCH_PARTY_SCREENING_RECORD_MAIN database table.
 * 
 */
@Entity
@Table(name="BATCH_PARTY_SCREENING_RECORD_MAIN")
@NamedQuery(name="BatchPartyScreeningRecordMain.findAll", query="SELECT b FROM BatchPartyScreeningRecordMain b")
public class BatchPartyScreeningRecordMain implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BatchPartyScreeningRecordMainPK id;

	@Column(name="CASE_RK")
	private BigDecimal caseRk;

	@Column(name="CONFIFM_HIT_ROUTE")
	private String confifmHitRoute="";

	@Column(name="EXTERNAL_PARTY_IND")
	private String externalPartyInd="";

	@Column(name="HIT_ROUTE")
	private String hitRoute="";

	@Column(name="INCIDENT_RK")
	private BigDecimal incidentRk;

	@Column(name="NAME_CHECK_STATUS")
	private String nameCheckStatus="";

	@Column(name="PARTY_NAME1")
	private String partyName1="";

	@Column(name="PARTY_NAME2")
	private String partyName2="";

	@Column(name="RELACTIONSHIP_TYPE_CODE")
	private String relactionshipTypeCode="";
	
//	@Column(name = "PEP_FLAG")
//	private String pepFlag="";
	
	@Column(name = "PANA_FLAG")
	private String panaFlag="";

	public BatchPartyScreeningRecordMain() {
	}

	public BatchPartyScreeningRecordMainPK getId() {
		return this.id;
	}

	public void setId(BatchPartyScreeningRecordMainPK id) {
		this.id = id;
	}

	public BigDecimal getCaseRk() {
		return this.caseRk;
	}

	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
	}

	public String getConfifmHitRoute() {
		return confifmHitRoute;
	}

	public void setConfifmHitRoute(String confifmHitRoute) {
		this.confifmHitRoute = confifmHitRoute;
	}

	public String getExternalPartyInd() {
		return externalPartyInd;
	}

	public void setExternalPartyInd(String externalPartyInd) {
		this.externalPartyInd = externalPartyInd;
	}

	public String getHitRoute() {
		return hitRoute;
	}

	public void setHitRoute(String hitRoute) {
		this.hitRoute = hitRoute;
	}

	public BigDecimal getIncidentRk() {
		return incidentRk;
	}

	public void setIncidentRk(BigDecimal incidentRk) {
		this.incidentRk = incidentRk;
	}

	public String getNameCheckStatus() {
		return nameCheckStatus;
	}

	public void setNameCheckStatus(String nameCheckStatus) {
		this.nameCheckStatus = nameCheckStatus;
	}

	public String getPartyName1() {
		return partyName1;
	}

	public void setPartyName1(String partyName1) {
		this.partyName1 = partyName1;
	}

	public String getPartyName2() {
		return partyName2;
	}

	public void setPartyName2(String partyName2) {
		this.partyName2 = partyName2;
	}

	public String getRelactionshipTypeCode() {
		return relactionshipTypeCode;
	}

	public void setRelactionshipTypeCode(String relactionshipTypeCode) {
		this.relactionshipTypeCode = relactionshipTypeCode;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public String getPepFlag() {
//		return pepFlag;
//	}
//
//	public void setPepFlag(String pepFlag) {
//		this.pepFlag = pepFlag;
//	}

	public String getPanaFlag() {
		return panaFlag;
	}

	public void setPanaFlag(String panaFlag) {
		this.panaFlag = panaFlag;
	}


}