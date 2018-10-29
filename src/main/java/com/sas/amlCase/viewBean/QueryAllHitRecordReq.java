package com.sas.amlCase.viewBean;

public class QueryAllHitRecordReq {

	private String refId;
	private String sourceType;
	private String caseRk;
	private String userId;
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
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "QueryHitRecordReq [refId=" + refId + ", sourceType=" + sourceType + ", caseRk=" + caseRk + ", userId="
				+ userId + "]";
	}
}