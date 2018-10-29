package com.sas.aml.retry.event.queue.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the X_RETRY_EVENT_QUEUE database table.
 * 
 */

@Entity
@Table(name="X_RETRY_EVENT_QUEUE", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="XRetryEventQueue.findAll", query="SELECT x FROM XRetryEventQueue x")
public class XRetryEventQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
  private Long id;
	
	@Column(name="RETRY_ID")
	private String retryId;
	
	@Column(name="MAIL_NOTICE")
	private boolean mailNotice;
	
	@Column(name="MAIL_LIST")
	private String mailList;
	
	@Column(name="CALLBACK_FUNC")
	private String callbackFunc;

	@Column(name="CREATE_DTTM")
	private Timestamp createDttm;

	@Column(name="END_POINT_URL")
	private String endPointUrl;

	@Column(name="ENTITY_CONTENT")
	private String entityContent;

	@Column(name="ENTITY_NAME")
	private String entityName;

	@Column(name="HTTP_METHOD")
	private String httpMethod;

	@Column(name="LAST_EXEC_RESULT")
	private String lastExecResult;

	@Column(name="MAX_RETRY_TIMES")
	private int maxRetryTimes;

	@Column(name="[MODULE]")
	private String module;

	@Column(name="RETRY_TIMES")
	private int retryTimes;

	@Column(name="STATUS")
	private String status;

	@Column(name="UPDATE_DTTM")
	private Timestamp updateDttm;

	public XRetryEventQueue() {
	}
  
	public String getRetryId() {
		return retryId;
	}
	public void setRetryId(String retryId) {
		this.retryId = retryId;
	}
	public boolean isMailNotice() {
		return mailNotice;
	}
	public void setMailNotice(boolean mailNotice) {
		this.mailNotice = mailNotice;
	}
	public String getMailList() {
		return mailList;
	}
	public void setMailList(String mailList) {
		this.mailList = mailList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCallbackFunc() {
		return this.callbackFunc;
	}
	public void setCallbackFunc(String callbackFunc) {
		this.callbackFunc = callbackFunc;
	}
	public Timestamp getCreateDttm() {
		return this.createDttm;
	}
	public void setCreateDttm(Timestamp createDttm) {
		this.createDttm = createDttm;
	}
	public String getEndPointUrl() {
		return this.endPointUrl;
	}
	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}
	public String getEntityContent() {
		return this.entityContent;
	}
	public void setEntityContent(String entityContent) {
		this.entityContent = entityContent;
	}
	public String getEntityName() {
		return this.entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getHttpMethod() {
		return this.httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getLastExecResult() {
		return this.lastExecResult;
	}
	public void setLastExecResult(String lastExecResult) {
		this.lastExecResult = lastExecResult;
	}
	public int getMaxRetryTimes() {
		return this.maxRetryTimes;
	}
	public void setMaxRetryTimes(int maxRetryTimes) {
		this.maxRetryTimes = maxRetryTimes;
	}
	public String getModule() {
		return this.module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public int getRetryTimes() {
		return this.retryTimes;
	}
	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getUpdateDttm() {
		return this.updateDttm;
	}
	public void setUpdateDttm(Timestamp updateDttm) {
		this.updateDttm = updateDttm;
	}
}