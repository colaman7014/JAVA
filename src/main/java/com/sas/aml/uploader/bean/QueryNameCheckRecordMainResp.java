package com.sas.aml.uploader.bean;

import java.util.List;

public class QueryNameCheckRecordMainResp {
	private String status;
	private String message;
	private List<NameCheckRecordMainBean> dataList;
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
	public List<NameCheckRecordMainBean> getDataList() {
		return dataList;
	}
	public void setDataList(List<NameCheckRecordMainBean> dataList) {
		this.dataList = dataList;
	}
	
	
}
