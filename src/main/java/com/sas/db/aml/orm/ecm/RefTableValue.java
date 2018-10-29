package com.sas.db.aml.orm.ecm;

import java.io.Serializable;

import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the REF_TABLE_VALUE database table.
 * 
 */
@Entity
@Table(name="REF_TABLE_VALUE", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="RefTableValue.findAll", query="SELECT r FROM RefTableValue r")
public class RefTableValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RefTableValuePK id;

	@Column(name="ACTIVE_FLG")
	private String activeFlg;

	@Column(name="DISPLAY_ORDER_NO")
	private BigDecimal displayOrderNo;

	@Column(name="VALUE_DESC")
	private String valueDesc;

	//bi-directional many-to-one association to RefTableValue
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="PARENT_REF_TABLE_NM", referencedColumnName="REF_TABLE_NM"),
		@JoinColumn(name="PARENT_VALUE_CD", referencedColumnName="VALUE_CD")
		})
	private RefTableValue refTableValue;

	//bi-directional many-to-one association to RefTableValue
	@OneToMany(mappedBy="refTableValue")
	private List<RefTableValue> refTableValues;

	public RefTableValue() {
	}

	public RefTableValuePK getId() {
		return this.id;
	}

	public void setId(RefTableValuePK id) {
		this.id = id;
	}

	public String getActiveFlg() {
		return this.activeFlg;
	}

	public void setActiveFlg(String activeFlg) {
		this.activeFlg = activeFlg;
	}

	public BigDecimal getDisplayOrderNo() {
		return this.displayOrderNo;
	}

	public void setDisplayOrderNo(BigDecimal displayOrderNo) {
		this.displayOrderNo = displayOrderNo;
	}

	public String getValueDesc() {
		return this.valueDesc;
	}

	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}

	public RefTableValue getRefTableValue() {
		return this.refTableValue;
	}

	public void setRefTableValue(RefTableValue refTableValue) {
		this.refTableValue = refTableValue;
	}

	public List<RefTableValue> getRefTableValues() {
		return this.refTableValues;
	}

	public void setRefTableValues(List<RefTableValue> refTableValues) {
		this.refTableValues = refTableValues;
	}

	public RefTableValue addRefTableValue(RefTableValue refTableValue) {
		getRefTableValues().add(refTableValue);
		refTableValue.setRefTableValue(this);

		return refTableValue;
	}

	public RefTableValue removeRefTableValue(RefTableValue refTableValue) {
		getRefTableValues().remove(refTableValue);
		refTableValue.setRefTableValue(null);

		return refTableValue;
	}

}