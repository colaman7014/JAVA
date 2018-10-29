package com.sas.amlCase.viewBean;

import java.sql.Timestamp;

public class MailUploadBean {
	
	private int seq;

	private String fileName;

	private String filePath;

	private String isAttachement;

	private boolean isSend;

	private String mailContent;

	private Timestamp processDate;

	private String receiver;

	private String sender;

	private String source;

	private String subject;
	
	private boolean formatErr;
	
	private String errMsg;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public String getIsAttachement() {
		return isAttachement;
	}

	public void setIsAttachement(String isAttachement) {
		this.isAttachement = isAttachement;
	}

	public boolean isSend() {
		return isSend;
	}

	public void setSend(boolean isSend) {
		this.isSend = isSend;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public Timestamp getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Timestamp processDate) {
		this.processDate = processDate;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean isFormatErr() {
		return formatErr;
	}

	public void setFormatErr(boolean formatErr) {
		this.formatErr = formatErr;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
