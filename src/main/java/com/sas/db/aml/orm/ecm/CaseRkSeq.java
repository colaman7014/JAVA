package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the case_rk_seq database table.
 * 
 */
@Entity
@Table(name="case_rk_seq", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="CaseRkSeq.findAll", query="SELECT c FROM CaseRkSeq c")
public class CaseRkSeq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private BigDecimal id;

	public CaseRkSeq() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

}