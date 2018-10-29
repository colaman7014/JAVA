package com.sas.aml.uploader.bean;

import java.util.Date;

public class QueryNameCheckUploadReq {
	private String uploadType;
	private String scanType;
	private String fileType;
	private String createUser;
	private Date createDateStart;
	private Date createDateEnd;
	private String scanStatus;
	
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	public String getScanType() {
		return scanType;
	}
	public void setScanType(String scanType) {
		this.scanType = scanType;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDateStart() {
		return createDateStart;
	}
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}
	public Date getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getScanStatus() {
		return scanStatus;
	}
	public void setScanStatus(String scanStatus) {
		this.scanStatus = scanStatus;
	}
}
