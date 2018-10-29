package com.sas.db.wlf.orm;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the X_INV_BL_NC_UPLOAD_RECORD database table.
 * 
 */
@Entity
@Table(name="X_INV_BL_NC_UPLOAD_RECORD", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="XInvBlNcUploadRecord.findAll", query="SELECT x FROM XInvBlNcUploadRecord x")
public class XInvBlNcUploadRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FILE_KEY")
	private String fileKey;

	@Column(name="CREATE_TIME")
	private Timestamp createTime;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	@Column(name="SCAN_PERIOD_END")
	private Timestamp scanPeriodEnd;

	@Column(name="SCAN_PERIOD_START")
	private Timestamp scanPeriodStart;

	@Column(name="SCAN_STATUS")
	private String scanStatus;

	@Column(name="SCAN_TYPE")
	private String scanType;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	@Column(name="UPLOAD_TYPE")
	private String uploadType;
	
	@Column(name="RESULT_PATH")
	private String resultPath;
	
	@Column(name="RESULT_FILE")
	private String resultFile;
	
	@Column(name="EN_ORG_FILE")
	private String enOrgFile;
	
	@Column(name="EN_RESULT_FILE")
	private String enResultFile;

	public XInvBlNcUploadRecord() {
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Timestamp getScanPeriodEnd() {
		return this.scanPeriodEnd;
	}

	public void setScanPeriodEnd(Timestamp scanPeriodEnd) {
		this.scanPeriodEnd = scanPeriodEnd;
	}

	public Timestamp getScanPeriodStart() {
		return this.scanPeriodStart;
	}

	public void setScanPeriodStart(Timestamp scanPeriodStart) {
		this.scanPeriodStart = scanPeriodStart;
	}

	public String getScanStatus() {
		return this.scanStatus;
	}

	public void setScanStatus(String scanStatus) {
		this.scanStatus = scanStatus;
	}

	public String getScanType() {
		return this.scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUploadType() {
		return this.uploadType;
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

	public String getEnOrgFile() {
		return enOrgFile;
	}

	public void setEnOrgFile(String enOrgFile) {
		this.enOrgFile = enOrgFile;
	}

	public String getEnResultFile() {
		return enResultFile;
	}

	public void setEnResultFile(String enResultFile) {
		this.enResultFile = enResultFile;
	}

}