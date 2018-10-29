package com.sas.multipleNC.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;

import com.sas.db.wlf.orm.tf.XOnlineNamecheckUpload;

public class XOnlineNamecheckUploadBean {
	
	private String uniqueKey;
	
	private int seq;
	
	private String fileKey;
	
	private String branchNumber;
	
	private String branchNumberName;
	
	private String callingUser;
	
	private BigDecimal caseRk;
	
	private Timestamp createDttm;
	
	private String createUser;
	
	private String callingUserName;
	
	private String ncReferenceId;
	
	private String type;
	
	public XOnlineNamecheckUploadBean(XOnlineNamecheckUpload entity) {
		this.uniqueKey = entity.getId().getUniqueKey();
		this.seq = entity.getId().getSeq();
		this.fileKey = entity.getId().getFileKey();
		this.branchNumber = entity.getBranchNumber();
		this.callingUser = entity.getCallingUser();
		this.ncReferenceId = entity.getNcReferenceId();
		this.type = entity.getType();
		this.caseRk = entity.getCaseRk();
		this.createDttm = entity.getCreateDttm();
		this.createUser = entity.getCreateUser();
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getCallingUser() {
		return callingUser;
	}

	public void setCallingUser(String callingUser) {
		this.callingUser = callingUser;
	}

	public BigDecimal getCaseRk() {
		return caseRk;
	}

	public void setCaseRk(BigDecimal caseRk) {
		this.caseRk = caseRk;
	}

	public Timestamp getCreateDttm() {
		return createDttm;
	}

	public void setCreateDttm(Timestamp createDttm) {
		this.createDttm = createDttm;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getNcReferenceId() {
		return ncReferenceId;
	}

	public void setNcReferenceId(String ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBranchNumberName() {
		return branchNumberName;
	}

	public void setBranchNumberName(String branchNumberName) {
		this.branchNumberName = branchNumberName;
	}

	public String getCallingUserName() {
		return callingUserName;
	}

	public void setCallingUserName(String callingUserName) {
		this.callingUserName = callingUserName;
	}

}
