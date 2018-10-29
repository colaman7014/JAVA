package com.sas.webservice.nameCheck.bean;

import java.util.List;

/**
 * Online Name Check Output Bean
 * @author SAS
 *
 */
public class NameCheckOutputRestBean {
	private String inputCnName;
	private String inputEnName;
	private String inputCccCode;
	private String inputId;
	private String hitCount;
	private List<NameCheckOutputRestDetailBean> SASTableData_MERGE;
	public String getInputCnName() {
		return inputCnName;
	}
	public void setInputCnName(String inputCnName) {
		this.inputCnName = inputCnName;
	}
	public String getInputEnName() {
		return inputEnName;
	}
	public void setInputEnName(String inputEnName) {
		this.inputEnName = inputEnName;
	}
	public String getHitCount() {
		return hitCount;
	}
	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}
	public List<NameCheckOutputRestDetailBean> getSASTableData_MERGE() {
		return SASTableData_MERGE;
	}
	public void setSASTableData_MERGE(
			List<NameCheckOutputRestDetailBean> sASTableData_MERGE) {
		SASTableData_MERGE = sASTableData_MERGE;
	}
	public String getInputCccCode() {
		return inputCccCode;
	}
	public void setInputCccCode(String inputCccCode) {
		this.inputCccCode = inputCccCode;
	}
	public String getInputId() {
		return inputId;
	}
	public void setInputId(String inputId) {
		this.inputId = inputId;
	}
	@Override
	public String toString() {
		return "NameCheckOutputRestBean [inputCnName=" + inputCnName
				+ ", inputEnName=" + inputEnName + ", inputCccCode="
				+ inputCccCode + ", inputId=" + inputId + ", hitCount="
				+ hitCount + ", SASTableData_MERGE=" + SASTableData_MERGE + "]";
	}
}
