package com.sas.aml.uploader.bean;

import java.util.List;

public class QuerySwiftWaitRecordResp {
	private String status;
	private String message;
	private List<SwiftWaitRecordBean> dataList;
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
	public List<SwiftWaitRecordBean> getDataList() {
		return dataList;
	}
	public void setDataList(List<SwiftWaitRecordBean> dataList) {
		this.dataList = dataList;
	}
	
}
