package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FSC_PARTY_ASSOC_ATTRIBUTE database table.
 * 
 */
@Embeddable
public class FscPartyAssocAttributePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="party_number")
	private String partyNumber;

	@Column(name="related_party_number")
	private String relatedPartyNumber;

	@Column(name="relationship_type_code")
	private String relationshipTypeCode;

	@Column(name="segment_id")
	private String segmentId;

	@Column(name="attribute_type_code")
	private String attributeTypeCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="change_begin_date")
	private java.util.Date changeBeginDate;

	public FscPartyAssocAttributePK() {
	}
	public String getPartyNumber() {
		return this.partyNumber;
	}
	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}
	public String getRelatedPartyNumber() {
		return this.relatedPartyNumber;
	}
	public void setRelatedPartyNumber(String relatedPartyNumber) {
		this.relatedPartyNumber = relatedPartyNumber;
	}
	public String getRelationshipTypeCode() {
		return this.relationshipTypeCode;
	}
	public void setRelationshipTypeCode(String relationshipTypeCode) {
		this.relationshipTypeCode = relationshipTypeCode;
	}
	public String getSegmentId() {
		return this.segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}
	public java.util.Date getChangeBeginDate() {
		return this.changeBeginDate;
	}
	public void setChangeBeginDate(java.util.Date changeBeginDate) {
		this.changeBeginDate = changeBeginDate;
	}
	

	public String getAttributeTypeCode() {
		return attributeTypeCode;
	}
	public void setAttributeTypeCode(String attributeTypeCode) {
		this.attributeTypeCode = attributeTypeCode;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FscPartyAssocAttributePK)) {
			return false;
		}
		FscPartyAssocAttributePK castOther = (FscPartyAssocAttributePK)other;
		return 
			this.partyNumber.equals(castOther.partyNumber)
			&& this.relatedPartyNumber.equals(castOther.relatedPartyNumber)
			&& this.relationshipTypeCode.equals(castOther.relationshipTypeCode)
			&& this.segmentId.equals(castOther.segmentId)
			&& this.changeBeginDate.equals(castOther.changeBeginDate)
			&& this.relationshipTypeCode.equals(castOther.relationshipTypeCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.partyNumber.hashCode();
		hash = hash * prime + this.relatedPartyNumber.hashCode();
		hash = hash * prime + this.relationshipTypeCode.hashCode();
		hash = hash * prime + this.segmentId.hashCode();
		hash = hash * prime + this.changeBeginDate.hashCode();
		hash = hash * prime + this.relationshipTypeCode.hashCode();
		
		return hash;
	}
}