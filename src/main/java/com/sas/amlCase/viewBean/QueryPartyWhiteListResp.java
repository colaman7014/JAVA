package com.sas.amlCase.viewBean;
import java.util.List;

import com.sas.webservice.createCase.bean.PartyWhiteListOutputBean;
/**
 * 查詢客戶白名單的結果
 * @author SAS
 *
 */
public class QueryPartyWhiteListResp {
	private String status;//success,error,warnings
	private String message;
	private List<PartyWhiteListOutputBean> resultList;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<PartyWhiteListOutputBean> getResultList() {
		return resultList;
	}
	public void setResultList(List<PartyWhiteListOutputBean> resultList) {
		this.resultList = resultList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
