package com.sas.amlCase.viewBean;

import org.springframework.web.multipart.MultipartFile;

public class MailUploaderReq {
	private MultipartFile uploadedfile;
	private String mailContent;
	private String receiver;
	private String sender;
	private String subject;
	
	
	public MultipartFile getUploadedfile() {
		return uploadedfile;
	}
	public void setUploadedfile(MultipartFile uploadedfile) {
		this.uploadedfile = uploadedfile;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
