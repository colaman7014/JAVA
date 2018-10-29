package com.sas.aml.uploader.bean;

import org.springframework.web.multipart.MultipartFile;

public class NameCheckUploadReq {
	private MultipartFile uploadedfile;
	private String type;
	private String fileType;
	private String startDate;
	private String endDate;
	private String userId;
	public MultipartFile getUploadedfile() {
		return uploadedfile;
	}
	public void setUploadedfile(MultipartFile uploadedfile) {
		this.uploadedfile = uploadedfile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
