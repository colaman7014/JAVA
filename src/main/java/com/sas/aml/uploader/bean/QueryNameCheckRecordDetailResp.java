package com.sas.aml.uploader.bean;

import java.util.List;

public class QueryNameCheckRecordDetailResp {

	private String status;
	private String message;
	private List<NameCheckRecordDetailBean> dataList;
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
	public List<NameCheckRecordDetailBean> getDataList() {
		return dataList;
	}
	public void setDataList(List<NameCheckRecordDetailBean> dataList) {
		this.dataList = dataList;
	}

}
