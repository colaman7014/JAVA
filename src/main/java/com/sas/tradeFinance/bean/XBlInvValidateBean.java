package com.sas.tradeFinance.bean;

import java.util.List;

public class XBlInvValidateBean {
	private boolean docStatusErr;
	@SuppressWarnings("rawtypes")
	private List dataList;
	private XBlInvErrMsgBean respUIBean;
	
	@SuppressWarnings("rawtypes")
	public XBlInvValidateBean(boolean docStatusErr, List dataList, XBlInvErrMsgBean respUIBean){
		this.docStatusErr = docStatusErr;
		this.dataList = dataList;
		this.respUIBean = respUIBean;
	}
	
	public boolean isDocStatusErr() {
		return docStatusErr;
	}

	public void setDocStatusErr(boolean docStatusErr) {
		this.docStatusErr = docStatusErr;
	}

	@SuppressWarnings("rawtypes")
	public List getDataList() {
		return dataList;
	}
	@SuppressWarnings("rawtypes")
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	
	public XBlInvErrMsgBean getRespUIBean() {
		return respUIBean;
	}
	public void setRespUIBean(XBlInvErrMsgBean respUIBean) {
		this.respUIBean = respUIBean;
	} 
}
