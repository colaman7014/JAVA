package com.sas.multipleNC.bean;

import java.util.List;

import com.sas.tradeFinance.bean.XBlInvErrMsgBean;

public class NCOnlineErrMsgBean extends XBlInvErrMsgBean {
	
	protected boolean isFileFormatError;
	private String fileKey;
	
	public NCOnlineErrMsgBean(){
		
	}
	
	public NCOnlineErrMsgBean(
			boolean isFileFormatError,
			boolean validateError, 
			int effCount,
			int errorCount,
			int successCount,
			List<String> errorMsgStringList,
			String fileKey){
		this.validateError = validateError;
		this.effCount = effCount;
		this.successCount = successCount;
		this.errorCount = errorCount;
		this.errorMsgStringList = errorMsgStringList; 
		this.isFileFormatError = isFileFormatError;
		this.fileKey = fileKey;
	}

	public boolean isFileFormatError() {
		return isFileFormatError;
	}

	public void setFileFormatError(boolean isFileFormatError) {
		this.isFileFormatError = isFileFormatError;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	
	
}
