package com.sas.webservice.batch.screening.bean;

/**
 * Batch Name Checking Output Bean
 * @author SAS
 *
 */
public class BatchNameCheckingOutputBean {
	private boolean finish;
	private boolean message;
	private String serverName;

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public boolean isMessage() {
		return message;
	}

	public void setMessage(boolean message) {
		this.message = message;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	
}
