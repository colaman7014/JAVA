package com.sas.aml.retry.event.queue.bean;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class XRetryEventQueueRespDto {
	private HttpStatus status;
    private String message;
    private String code;
    private List<XRetryEventQueue> xRetryEventQueues;
 
    public XRetryEventQueueRespDto(HttpStatus status, String code, String message, List<XRetryEventQueue> xRetryEventQueues) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.xRetryEventQueues = xRetryEventQueues;
    }
 
    public XRetryEventQueueRespDto(HttpStatus status, String code, String message, XRetryEventQueue xRetryEventQueue) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        xRetryEventQueues = Arrays.asList(xRetryEventQueue);
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<XRetryEventQueue> getxRetryEventQueues() {
		return xRetryEventQueues;
	}

	public void setxRetryEventQueues(List<XRetryEventQueue> xRetryEventQueues) {
		this.xRetryEventQueues = xRetryEventQueues;
	}
}
