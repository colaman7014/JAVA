package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FSC_PARTY_ASSOC_ATTRIBUTE database table.
 * 
 */
@Entity
@Table(name="FSC_PARTY_ASSOC_ATTRIBUTE")
@NamedQuery(name="FscPartyAssocAttribute.findAll", query="SELECT f FROM FscPartyAssocAttribute f")
public class FscPartyAssocAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FscPartyAssocAttributePK id;

	@Column(name="change_current_ind")
	private String changeCurrentInd;

	@Column(name="change_end_date")
	private Timestamp changeEndDate;
	
	@Column(name="attribute_char_value")
	private String attributeCharValue;

	@Column(name="attribute_num_value")
	private BigDecimal attributeNumValue;

	@Column(name="attribute_date_value")
	private Timestamp attributeDateValue;

	public FscPartyAssocAttribute() {
	}

	public FscPartyAssocAttributePK getId() {
		return id;
	}

	public void setId(FscPartyAssocAttributePK id) {
		this.id = id;
	}

	public String getChangeCurrentInd() {
		return changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	public Timestamp getChangeEndDate() {
		return changeEndDate;
	}

	public void setChangeEndDate(Timestamp changeEndDate) {
		this.changeEndDate = changeEndDate;
	}

	public String getAttributeCharValue() {
		return attributeCharValue;
	}

	public void setAttributeCharValue(String attributeCharValue) {
		this.attributeCharValue = attributeCharValue;
	}

	public BigDecimal getAttributeNumValue() {
		return attributeNumValue;
	}

	public void setAttributeNumValue(BigDecimal attributeNumValue) {
		this.attributeNumValue = attributeNumValue;
	}

	public Timestamp getAttributeDateValue() {
		return attributeDateValue;
	}

	public void setAttributeDateValue(Timestamp attributeDateValue) {
		this.attributeDateValue = attributeDateValue;
	}



}