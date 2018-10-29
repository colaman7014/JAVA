package com.sas.aml.retry.event.queue.bean;


public class XRetryEventQueueDto {
	
	private boolean mailNotice;
	private int maxRetryTimes;
	private String mailList;
	private String callbackFunc;
	private String endPointUrl;
	private String entityContent;
	private String entityName;
	private String httpMethod;
	private String module;
	private String status;
	
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getEndPointUrl() {
		return endPointUrl;
	}
	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}
	public String getEntityContent() {
		return entityContent;
	}
	public void setEntityContent(String entityContent) {
		this.entityContent = entityContent;
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
	public int getMaxRetryTimes() {
		return maxRetryTimes;
	}
	public void setMaxRetryTimes(int maxRetryTimes) {
		this.maxRetryTimes = maxRetryTimes;
	}
	public String getCallbackFunc() {
		return callbackFunc;
	}
	public void setCallbackFunc(String callbackFunc) {
		this.callbackFunc = callbackFunc;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getHttpMethod() {
		return httpMethod;
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
	
	@Override
	public String toString() {
		return "XRetryEventQueueDto [mailNotice=" + mailNotice + ", maxRetryTimes=" + maxRetryTimes + ", mailList="
				+ mailList + ", callbackFunc=" + callbackFunc + ", endPointUrl=" + endPointUrl + ", entityContent="
				+ entityContent + ", entityName=" + entityName + ", httpMethod=" + httpMethod + ", module=" + module
				+ ", status=" + status + "]";
	}
	
}
