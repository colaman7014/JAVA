package com.sas.webservice.nameCheck.bean;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FtpRemittanceNameCheckInputBean {
	private FtpRemittanceNameCheckInputHeadBean headBean;
	private List<FtpRemittanceNameCheckInputDetailBean> detailsBean;
	private FtpRemittanceNameCheckInputEndBean endBean;

	public FtpRemittanceNameCheckInputBean() {
	}

	public FtpRemittanceNameCheckInputBean(String[] strList) {
		setValues(strList);
	}

	public FtpRemittanceNameCheckInputBean(List<String> strList) {
		setValues(strList);
	}

	private void setValues(String[] strList) {
		setValues(Arrays.asList(strList));
	}

	private void setValues(List<String> strList) {
		int i = 0;
//		this.callingSystem = strList.get(i++);
//		this.realtimeFlag = strList.get(i++);
//		this.screenFunction = strList.get(i++);
//		this.callingUser = strList.get(i++);
//		this.branchNumber = strList.get(i++);
//		this.unit = strList.get(i++);
//		this.uniqueKey = strList.get(i++);
//		this.partyNumber = strList.get(i++);
//
//		this.checkSeq = strList.get(i++);
//		this.entityType = strList.get(i++);
//		this.entityRelationship = strList.get(i++);
//		this.entityRelationshipDesc = strList.get(i++);
//		this.englishName = strList.get(i++);
//		this.nonEnglishName = strList.get(i++);
//		this.cccCode = strList.get(i++);
//		this.idNumber = strList.get(i++);
//		this.bicSwiftCode = strList.get(i++);
//		this.country = strList.get(i++);
//		this.yearofBirth = strList.get(i++);
	}

	
	public FtpRemittanceNameCheckInputHeadBean getHeadBean() {
		return headBean;
	}

	public void setHeadBean(FtpRemittanceNameCheckInputHeadBean headBean) {
		this.headBean = headBean;
	}

	public List<FtpRemittanceNameCheckInputDetailBean> getDetailsBean() {
		return detailsBean;
	}

	public void setDetailsBean(List<FtpRemittanceNameCheckInputDetailBean> detailsBean) {
		this.detailsBean = detailsBean;
	}

	public FtpRemittanceNameCheckInputEndBean getEndBean() {
		return endBean;
	}

	public void setEndBean(FtpRemittanceNameCheckInputEndBean endBean) {
		this.endBean = endBean;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
