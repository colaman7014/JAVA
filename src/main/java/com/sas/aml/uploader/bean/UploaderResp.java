package com.sas.aml.uploader.bean;

import java.util.List;

public class UploaderResp {
	
	private String status;
	private String message;
	private int count;
	private int successCount;
	private int errorCount;
	private List<String> errList;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public List<String> getErrList() {
		return errList;
	}
	public void setErrList(List<String> errList) {
		this.errList = errList;
	}
	
}
