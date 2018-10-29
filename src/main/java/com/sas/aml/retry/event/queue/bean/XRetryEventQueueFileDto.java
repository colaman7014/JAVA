package com.sas.aml.retry.event.queue.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;

import java.sql.Timestamp;


/**
 * The persistent class for the X_RETRY_EVENT_QUEUE database table.
 * 
 */
public class XRetryEventQueueFileDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String callbackFunc;
	
	private String retryId;
	
	private boolean mailNotice;
	
	private String mailList;

	private Timestamp createDttm;

	private String endPointUrl;

	private String entityContent;

	private String entityName;

	private String httpMethod;

	private String status;

	private int maxRetryTimes;

	private String module;

	public XRetryEventQueueFileDto() {
	}



	public XRetryEventQueueFileDto(XRetryEventQueue xRetryEventQueue) {
		this.setCallbackFunc(xRetryEventQueue.getCallbackFunc());
		this.setCreateDttm(xRetryEventQueue.getCreateDttm());
		this.setEndPointUrl(xRetryEventQueue.getEndPointUrl());
		this.setEntityContent(xRetryEventQueue.getEntityContent());
		this.setEntityName(xRetryEventQueue.getEntityName());
		this.setHttpMethod(xRetryEventQueue.getHttpMethod());
		this.setMailNotice(xRetryEventQueue.isMailNotice());
		this.setMailList(xRetryEventQueue.getMailList());
		this.setMaxRetryTimes(xRetryEventQueue.getMaxRetryTimes());
		this.setStatus(xRetryEventQueue.getStatus());
		this.setModule(xRetryEventQueue.getModule());
		this.setRetryId(xRetryEventQueue.getRetryId());
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}