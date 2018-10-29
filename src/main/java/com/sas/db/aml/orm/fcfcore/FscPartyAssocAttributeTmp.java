package com.sas.db.aml.orm.fcfcore;

/**
 * 客戶關係暫存Bean檔
 * @author SAS
 *
 */
public class FscPartyAssocAttributeTmp {
	
	private String partyNumber="";
	
	private String relatedPartyNumber="";
	
	private String relationshipTypeCode="";
	
	private String attributeCharValue="";

	public String getPartyNumber() {
		return partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

	public String getRelatedPartyNumber() {
		return relatedPartyNumber;
	}

	public void setRelatedPartyNumber(String relatedPartyNumber) {
		this.relatedPartyNumber = relatedPartyNumber;
	}

	public String getRelationshipTypeCode() {
		return relationshipTypeCode;
	}

	public void setRelationshipTypeCode(String relationshipTypeCode) {
		this.relationshipTypeCode = relationshipTypeCode;
	}

	public String getAttributeCharValue() {
		return attributeCharValue;
	}

	public void setAttributeCharValue(String attributeCharValue) {
		this.attributeCharValue = attributeCharValue;
	}
	
}
