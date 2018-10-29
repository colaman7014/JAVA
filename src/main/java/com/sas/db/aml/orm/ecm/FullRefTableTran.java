package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the FULL_REF_TABLE_TRANS database table.
 * 
 */
@Entity
@Table(name="FULL_REF_TABLE_TRANS", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="FullRefTableTran.findAll", query="SELECT f FROM FullRefTableTran f")
public class FullRefTableTran implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private FullRefTableTranPK id;
	
	@Column(name="value_desc")
	private String valueDesc;
	
	@Column(name="active_flg")
	private String activeFlg;
	
	@Column(name="display_order_no")
	private BigDecimal displayOrderNo;
	
	@Column(name="parent_ref_table_nm")
	private String parentRefTableNm;

	@Column(name="parent_value_cd")
	private String parentValueCd;

	public FullRefTableTran() {
	}

	public FullRefTableTranPK getId() {
		return id;
	}

	public void setId(FullRefTableTranPK id) {
		this.id = id;
	}

	public String getValueDesc() {
		return valueDesc;
	}

	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}

	public String getActiveFlg() {
		return activeFlg;
	}

	public void setActiveFlg(String activeFlg) {
		this.activeFlg = activeFlg;
	}

	public BigDecimal getDisplayOrderNo() {
		return displayOrderNo;
	}

	public void setDisplayOrderNo(BigDecimal displayOrderNo) {
		this.displayOrderNo = displayOrderNo;
	}

	public String getParentRefTableNm() {
		return parentRefTableNm;
	}

	public void setParentRefTableNm(String parentRefTableNm) {
		this.parentRefTableNm = parentRefTableNm;
	}

	public String getParentValueCd() {
		return parentValueCd;
	}

	public void setParentValueCd(String parentValueCd) {
		this.parentValueCd = parentValueCd;
	}
}