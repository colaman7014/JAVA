package com.sas.aml.uploader.bean;

public class QueryInvBlUploadFileReq {

	private String queryLevel;
	private String uploadType;
	private String fileKey;
	private String uniqueKey;
	public String getQueryLevel() {
		return queryLevel;
	}
	public void setQueryLevel(String queryLevel) {
		this.queryLevel = queryLevel;
	}
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	public String getFileKey() {
		return fileKey;
	}
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
