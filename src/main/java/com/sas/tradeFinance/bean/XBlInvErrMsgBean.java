package com.sas.tradeFinance.bean;

import java.util.List;

public class XBlInvErrMsgBean {
	
	protected boolean validateError;
	protected int effCount;
	protected int errorCount;
	protected int successCount;
	protected List<String> errorMsgStringList;
	
	public XBlInvErrMsgBean(){
		
	}
	
	public XBlInvErrMsgBean(boolean validateError, int effCount, int errorCount, int successCount,List<String> errorMsgStringList){
		this.validateError = validateError;
		this.effCount = effCount;
		this.successCount = successCount;
		this.errorCount = errorCount;
		this.errorMsgStringList = errorMsgStringList; 
	}
	
	public boolean isValidateError() {
		return validateError;
	}

	public void setValidateError(boolean validateError) {
		this.validateError = validateError;
	}

	public int getEffCount() {
		return effCount;
	}

	public void setEffCount(int effCount) {
		this.effCount = effCount;
	}

	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public List<String> getErrorMsgStringList() {
		return errorMsgStringList;
	}
	public void setErrorMsgStringList(List<String> errorMsgStringList) {
		this.errorMsgStringList = errorMsgStringList;
	}
}
