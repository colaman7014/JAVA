package com.sas.amlCase.viewBean;

public class UpdateChkRsltReq {

	
	private Long caseRk;
	private String uniqueKey;
	private int ncReferenceId;
	private int seq;
	private int rowNo;
	private String checkResult;
	private String whitelistInd;
	private String checkSeq;
	private String viewId;
	public Long getCaseRk() {
		return caseRk;
	}
	public void setCaseRk(Long caseRk) {
		this.caseRk = caseRk;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public int getNcReferenceId() {
		return ncReferenceId;
	}
	public void setNcReferenceId(int ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getWhitelistInd() {
		return whitelistInd;
	}
	public void setWhitelistInd(String whitelistInd) {
		this.whitelistInd = whitelistInd;
	}
	
	public String getCheckSeq() {
		return checkSeq;
	}
	public void setCheckSeq(String checkSeq) {
		this.checkSeq = checkSeq;
	}
	
	public String getViewId() {
		return viewId;
	}
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	@Override
	public String toString() {
		return "UpdateChkRsltReq [caseRk=" + caseRk + ", uniqueKey=" + uniqueKey + ", ncReferenceId=" + ncReferenceId
				+ ", seq=" + seq + ", checkResult=" + checkResult + ", whitelistInd=" + whitelistInd + ", checkSeq="
				+ checkSeq + ", viewId=" + viewId + "]";
	}
	
	
	
	
}
