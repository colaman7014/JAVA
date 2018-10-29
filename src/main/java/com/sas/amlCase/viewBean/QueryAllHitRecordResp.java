package com.sas.amlCase.viewBean;

import java.util.List;
import java.util.Map;

import com.sas.webservice.createCase.bean.QueryHitRecordBean;

public class QueryAllHitRecordResp {

	private String status;
	private String isSwift;
	private String swiftType;
	private String isViewMode;
	private String partyNo;
	private List<Map<String, Object>> checkSeq;
	private List<QueryHitRecordBean> hitRecord;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsSwift() {
		return isSwift;
	}
	public void setIsSwift(String isSwift) {
		this.isSwift = isSwift;
	}
	public String getSwiftType() {
		return swiftType;
	}
	public void setSwiftType(String swiftType) {
		this.swiftType = swiftType;
	}
	public String getIsViewMode() {
		return isViewMode;
	}
	public void setIsViewMode(String isViewMode) {
		this.isViewMode = isViewMode;
	}
	public List<Map<String, Object>> getCheckSeq() {
		return checkSeq;
	}
	public void setCheckSeq(List<Map<String, Object>> checkSeq) {
		this.checkSeq = checkSeq;
	}
	public List<QueryHitRecordBean> getHitRecord() {
		return hitRecord;
	}
	public void setHitRecord(List<QueryHitRecordBean> hitRecord) {
		this.hitRecord = hitRecord;
	}
	public String getPartyNo() {
		return partyNo;
	}
	public void setPartyNo(String partyNo) {
		this.partyNo = partyNo;
	}
}