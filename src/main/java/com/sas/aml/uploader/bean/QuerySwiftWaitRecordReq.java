package com.sas.aml.uploader.bean;

public class QuerySwiftWaitRecordReq {
	
	private String createUser;
	private String swiftType;
	private String checkStatus;
	
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getSwiftType() {
		return swiftType;
	}
	public void setSwiftType(String swiftType) {
		this.swiftType = swiftType;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}
