package com.sas.aml.uploader.bean;

import java.sql.Timestamp;

import com.sas.db.wlf.orm.XInvBlNcUploadRecord;

public class XInvBlNcUploadRecordBean {
	
	private String fileKey;
	private Timestamp createTime;
	private String createUser;
	private String createUserName;
	private String fileName;
	private String filePath;
	private Timestamp scanPeriodEnd;
	private Timestamp scanPeriodStart;
	private String scanStatus;
	private String scanType;
	private Timestamp updateTime;
	private String uploadType;
	private String uploadTypeDesc;
	private String resultPath;
	private String resultFile;
	
	public XInvBlNcUploadRecordBean() {}
	public XInvBlNcUploadRecordBean(XInvBlNcUploadRecord xInvBlNcUploadRecord) {

		this.fileKey = xInvBlNcUploadRecord.getFileKey();
		this.createTime = xInvBlNcUploadRecord.getCreateTime();
		this.createUser = xInvBlNcUploadRecord.getCreateUser();
		this.fileName = xInvBlNcUploadRecord.getFileName();
		this.filePath = xInvBlNcUploadRecord.getFilePath();
		this.scanPeriodEnd = xInvBlNcUploadRecord.getScanPeriodEnd();
		this.scanPeriodStart = xInvBlNcUploadRecord.getScanPeriodStart();
		this.scanStatus = xInvBlNcUploadRecord.getScanStatus();
		this.scanType = xInvBlNcUploadRecord.getScanType();
		this.updateTime = xInvBlNcUploadRecord.getUpdateTime();
		this.uploadType = xInvBlNcUploadRecord.getUploadType();
		this.resultPath = xInvBlNcUploadRecord.getResultPath();
		this.resultFile = xInvBlNcUploadRecord.getResultFile();
	}
	
	public String getFileKey() {
		return fileKey;
	}
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Timestamp getScanPeriodEnd() {
		return scanPeriodEnd;
	}
	public void setScanPeriodEnd(Timestamp scanPeriodEnd) {
		this.scanPeriodEnd = scanPeriodEnd;
	}
	public Timestamp getScanPeriodStart() {
		return scanPeriodStart;
	}
	public void setScanPeriodStart(Timestamp scanPeriodStart) {
		this.scanPeriodStart = scanPeriodStart;
	}
	public String getScanStatus() {
		return scanStatus;
	}
	public void setScanStatus(String scanStatus) {
		this.scanStatus = scanStatus;
	}
	public String getScanType() {
		return scanType;
	}
	public void setScanType(String scanType) {
		this.scanType = scanType;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	public String getResultPath() {
		return resultPath;
	}
	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}
	public String getResultFile() {
		return resultFile;
	}
	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getUploadTypeDesc() {
		return uploadTypeDesc;
	}
	public void setUploadTypeDesc(String uploadTypeDesc) {
		this.uploadTypeDesc = uploadTypeDesc;
	}
	
}
