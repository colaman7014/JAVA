package com.sas.webservice.batch.screening.bean;

import java.util.Date;

public class ScreenControlBean {
	private boolean isBatchNameCheckingFullParty=false;   //是否為全客戶掃異動名單
	private Date screenDate ;                             //需要掃描的日期判斷
	public boolean isBatchNameCheckingFullParty() {
		return isBatchNameCheckingFullParty;
	}
	public void setBatchNameCheckingFullParty(boolean isBatchNameCheckingFullParty) {
		this.isBatchNameCheckingFullParty = isBatchNameCheckingFullParty;
	}
	public Date getScreenDate() {
		return screenDate;
	}
	public void setScreenDate(Date screenDate) {
		this.screenDate = screenDate;
	}
}
