package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.amlCase.viewBean.MailUploadBean;
import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the SEND_MAIL database table.
 * 
 */
@Entity
@Table(name="SEND_MAIL", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="SendMail.findAll", query="SELECT s FROM SendMail s")
public class SendMail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SEQ")
	private int seq;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	@Column(name="IS_ATTACHEMENT")
	private String isAttachement;

	@Column(name="IS_SEND")
	private boolean isSend;

	@Column(name="MAIL_CONTENT")
	private String mailContent;

	@Column(name="PROCESS_DATE")
	private Timestamp processDate;

	@Column(name="RECEIVER")
	private String receiver;

	@Column(name="SENDER")
	private String sender;

	@Column(name="SOURCE")
	private String source;

	@Column(name="SUBJECT")
	private String subject;

	public SendMail() {
	}
	
	public SendMail(MailUploadBean mailUploadBean) {
		this.seq = mailUploadBean.getSeq();
		this.fileName = mailUploadBean.getFileName();
		this.filePath = mailUploadBean.getFilePath();
		this.isAttachement = mailUploadBean.getIsAttachement();
		this.isSend = mailUploadBean.isSend();
		this.mailContent = mailUploadBean.getMailContent();
		this.processDate = mailUploadBean.getProcessDate();
		this.receiver = mailUploadBean.getReceiver();
		this.sender = mailUploadBean.getSender();
		this.source = mailUploadBean.getSource();
		this.subject = mailUploadBean.getSubject();
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public String getIsAttachement() {
		return this.isAttachement;
	}

	public void setIsAttachement(String isAttachement) {
		this.isAttachement = isAttachement;
	}

	public boolean getIsSend() {
		return this.isSend;
	}

	public void setIsSend(boolean isSend) {
		this.isSend = isSend;
	}

	public String getMailContent() {
		return this.mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public Timestamp getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Timestamp processDate) {
		this.processDate = processDate;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}