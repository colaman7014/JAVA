package com.sas.aml.retry.event.queue.bean;

import org.springframework.http.HttpStatus;

public class XRetryEventQueueEndpointRespDto {
	private HttpStatus status;
	private String message;
	private String code;

	public XRetryEventQueueEndpointRespDto(HttpStatus status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
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

	@Override
	public String toString() {
		return "XRetryEventQueueEndpointRespDto [status=" + status + ", message=" + message + ", code=" + code + "]";
	}

}
