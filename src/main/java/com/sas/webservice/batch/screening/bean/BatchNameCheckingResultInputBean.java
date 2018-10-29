package com.sas.webservice.batch.screening.bean;

/**
 * Batch Name Checking Input Bean
 * @author SAS
 *
 */
public class BatchNameCheckingResultInputBean {
	private boolean trigger;

	public boolean isTrigger() {
		return trigger;
	}

	public void setTrigger(boolean trigger) {
		this.trigger = trigger;
	}
}
