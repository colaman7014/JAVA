package com.sas.amlCase.viewBean;

import java.util.List;

import com.sas.webservice.createCase.bean.QueryHitRecordBean;

public class QueryHitRecordResp {

	private String status;
	private List<QueryHitRecordBean> result;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<QueryHitRecordBean> getResult() {
		return result;
	}
	public void setResult(List<QueryHitRecordBean> result) {
		this.result = result;
	}
	
	
}
