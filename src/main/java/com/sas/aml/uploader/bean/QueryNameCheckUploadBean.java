package com.sas.aml.uploader.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sas.db.wlf.orm.XInvBlNcUploadRecord;
import com.sas.util.DateUtil;

public class QueryNameCheckUploadBean {
	private String fileKey;
	private String uploadtype;
	private String scanType;
	private String scanPeriodStart;
	private String scanPeriodEnd;
	private String scanStatus;
	private String createUser;
	private Date createTime;
	private Date updatTime;
	private String fileName;
	private String resultFile;
	private String resultFilePath;
	
	public QueryNameCheckUploadBean() {
		
	}

	public QueryNameCheckUploadBean(XInvBlNcUploadRecord entity) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		this.fileKey = entity.getFileKey();
		this.uploadtype = entity.getUploadType();
		this.scanType = entity.getScanType();
		if(null !=entity.getScanPeriodStart()) {
			this.scanPeriodStart = sdf.format(DateUtil.transDate(entity.getScanPeriodStart()));
		}
		if(null !=entity.getScanPeriodEnd()) {
			this.scanPeriodEnd = sdf.format(DateUtil.transDate(entity.getScanPeriodEnd()));
		}

		this.scanStatus = entity.getScanStatus();
		this.createUser = entity.getCreateUser();
		this.createTime =DateUtil.transDate(entity.getCreateTime());
		this.updatTime = DateUtil.transDate(entity.getUpdateTime());
		this.fileName = entity.getFileName();
		this.resultFile = entity.getResultFile();
		this.resultFilePath = entity.getResultPath();
	}


	public String getFileKey() {
		return fileKey;
	}
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	public String getUploadtype() {
		return uploadtype;
	}
	public void setUploadtype(String uploadtype) {
		this.uploadtype = uploadtype;
	}
	public String getScanType() {
		return scanType;
	}
	public void setScanType(String scanType) {
		this.scanType = scanType;
	}
	public String getScanPeriodStart() {
		return scanPeriodStart;
	}
	public void setScanPeriodStart(String scanPeriodStart) {
		this.scanPeriodStart = scanPeriodStart;
	}
	public String getScanPeriodEnd() {
		return scanPeriodEnd;
	}
	public void setScanPeriodEnd(String scanPeriodEnd) {
		this.scanPeriodEnd = scanPeriodEnd;
	}
	public String getScanStatus() {
		return scanStatus;
	}
	public void setScanStatus(String scanStatus) {
		this.scanStatus = scanStatus;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdatTime() {
		return updatTime;
	}
	public void setUpdatTime(Date updatTime) {
		this.updatTime = updatTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getResultFile() {
		return resultFile;
	}
	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}
	public String getResultFilePath() {
		return resultFilePath;
	}
	public void setResultFilePath(String resultFilePath) {
		this.resultFilePath = resultFilePath;
	}
	
}
