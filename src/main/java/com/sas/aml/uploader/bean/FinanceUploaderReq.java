package com.sas.aml.uploader.bean;

import org.springframework.web.multipart.MultipartFile;

public class FinanceUploaderReq {
	
	private MultipartFile uploadedfile;
	private String type;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
}
