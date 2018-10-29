package com.sas.amlCase.viewBean;

public class QueryHitRecordReq {

	private String refId;
	private String sourceType;
	private String caseRk;
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getCaseRk() {
		return caseRk;
	}
	public void setCaseRk(String caseRk) {
		this.caseRk = caseRk;
	}
	@Override
	public String toString() {
		return "QueryHitRecordReq [refId=" + refId + ", sourceType=" + sourceType + ", caseRk=" + caseRk + "]";
	}
	
	
}
