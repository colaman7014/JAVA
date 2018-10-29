package com.sas.db.wlf.orm.nc;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;

/**
 * The persistent class for the FTP_DOWNLOAD_NAMECHECK_CTRL database table.
 * 
 */
@Entity
@Table(name = "FTP_DOWNLOAD_NAMECHECK_CTRL", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name = "FtpDownloadNamecheckCtrl.findAll", query = "SELECT f FROM FtpDownloadNamecheckCtrl f")
public class FtpDownloadNamecheckCtrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private Integer seq;

	@Column(name = "DOWNLOAD_FILE")
	private String downloadFile;

	@Column(name = "DOWNLOAD_TIME")
	private Timestamp downloadTime;

	@Column(name = "DOWNLOAD_TYPE")
	private String downloadType;

	@Column(name = "SCAN_STATUS")
	private String scanStatus;

	@Column(name = "UPLOAD_FILE")
	private String uploadFile;

	@Column(name = "UPLOAD_PATH")
	private String uploadPath;

	public FtpDownloadNamecheckCtrl() {
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDownloadFile() {
		return this.downloadFile;
	}

	public void setDownloadFile(String downloadFile) {
		this.downloadFile = downloadFile;
	}

	public Timestamp getDownloadTime() {
		return this.downloadTime;
	}

	public void setDownloadTime(Timestamp downloadTime) {
		this.downloadTime = downloadTime;
	}

	public String getDownloadType() {
		return this.downloadType;
	}

	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}

	public String getScanStatus() {
		return this.scanStatus;
	}

	public void setScanStatus(String scanStatus) {
		this.scanStatus = scanStatus;
	}

	public String getUploadFile() {
		return this.uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadPath() {
		return this.uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

}