package com.sas.aml.uploader.bean;

import java.util.List;

public class QueryNameCheckUploadResp {

	private String status;
	private String message;
	private List<QueryNameCheckUploadBean> scanList;
	private List<QueryNameCheckUploadBean> unScanList;
	private List<QueryNameCheckUploadBean> otherList;

	
	
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

	public List<QueryNameCheckUploadBean> getScanList() {
		return scanList;
	}

	public void setScanList(List<QueryNameCheckUploadBean> scanList) {
		this.scanList = scanList;
	}

	public List<QueryNameCheckUploadBean> getUnScanList() {
		return unScanList;
	}

	public void setUnScanList(List<QueryNameCheckUploadBean> unScanList) {
		this.unScanList = unScanList;
	}

	public List<QueryNameCheckUploadBean> getOtherList() {
		return otherList;
	}

	public void setOtherList(List<QueryNameCheckUploadBean> otherList) {
		this.otherList = otherList;
	}
}
