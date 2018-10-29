package com.sas.aml.uploader.bean;

import java.util.List;

@SuppressWarnings("rawtypes")
public class QueryInvBlUploadFileResp {

	private String status;
	private List dataList;
	private String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
