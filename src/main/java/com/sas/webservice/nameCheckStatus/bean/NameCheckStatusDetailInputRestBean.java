package com.sas.webservice.nameCheckStatus.bean;
/**
 * AML Name Check Status Input Rest Bean
 * @author SAS
 *
 */
public class NameCheckStatusDetailInputRestBean {
	private String uniqueKey;
	private String ncReferenceId;
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getNcReferenceId() {
		return ncReferenceId;
	}
	public void setNcReferenceId(String ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
	}
	@Override
	public String toString() {
		return "NameCheckStatusDetailInputRestBean [uniqueKey=" + uniqueKey
				+ ", ncReferenceId=" + ncReferenceId + "]";
	}
}
