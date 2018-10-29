package com.sas.webservice.nameCheck.bean;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FtpExRemittanceNameCheckInputBean {
	private String orderNo;
	private String space;
	private String customerName1;
	private String customerName2;
	private String customerAddr1;
	private String customerAddr2;
	private String idNo;
	private String country;
	private String beneficiaryFormat;
	private String beneficiarySwiftCode;
	private String beneficiaryBankNo;
	private String beneficiaryName1;
	private String beneficiaryName2;
	private String beneficiaryAddr1;
	private String beneficiaryAddr2;
	private String accountNo;
	private String amount;
	private String detailsOfPayment;

	public FtpExRemittanceNameCheckInputBean() {
	}

	public FtpExRemittanceNameCheckInputBean(String[] strList) {
		setValues(strList);
	}

	public FtpExRemittanceNameCheckInputBean(List<String> strList) {
		setValues(strList);
	}

	private void setValues(String[] strList) {
		setValues(Arrays.asList(strList));
	}

	private void setValues(List<String> strList) {
		int i = 0;
		this.orderNo = strList.get(i++);
		this.space = strList.get(i++);
		this.customerName1 = strList.get(i++);
		this.customerName2 = strList.get(i++);
		this.customerAddr1 = strList.get(i++);
		this.customerAddr2 = strList.get(i++);
		this.idNo = strList.get(i++);
		this.country = strList.get(i++);
		this.beneficiaryFormat = strList.get(i++);
		this.beneficiarySwiftCode = strList.get(i++);
		this.beneficiaryBankNo = strList.get(i++);
		this.beneficiaryName1 = strList.get(i++);
		this.beneficiaryName2 = strList.get(i++);
		this.beneficiaryAddr1 = strList.get(i++);
		this.beneficiaryAddr2 = strList.get(i++);
		this.accountNo = strList.get(i++);
		this.amount = strList.get(i++);
		this.detailsOfPayment = strList.get(i++);
	}


	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBeneficiaryFormat() {
		return beneficiaryFormat;
	}

	public void setBeneficiaryFormat(String beneficiaryFormat) {
		this.beneficiaryFormat = beneficiaryFormat;
	}

	public String getBeneficiarySwiftCode() {
		return beneficiarySwiftCode;
	}

	public void setBeneficiarySwiftCode(String beneficiarySwiftCode) {
		this.beneficiarySwiftCode = beneficiarySwiftCode;
	}

	public String getBeneficiaryBankNo() {
		return beneficiaryBankNo;
	}

	public void setBeneficiaryBankNo(String beneficiaryBankNo) {
		this.beneficiaryBankNo = beneficiaryBankNo;
	}


	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDetailsOfPayment() {
		return detailsOfPayment;
	}

	public void setDetailsOfPayment(String detailsOfPayment) {
		this.detailsOfPayment = detailsOfPayment;
	}

	public String getCustomerName1() {
		return customerName1;
	}

	public void setCustomerName1(String customerName1) {
		this.customerName1 = customerName1;
	}

	public String getCustomerName2() {
		return customerName2;
	}

	public void setCustomerName2(String customerName2) {
		this.customerName2 = customerName2;
	}

	public String getCustomerAddr1() {
		return customerAddr1;
	}

	public void setCustomerAddr1(String customerAddr1) {
		this.customerAddr1 = customerAddr1;
	}

	public String getCustomerAddr2() {
		return customerAddr2;
	}

	public void setCustomerAddr2(String customerAddr2) {
		this.customerAddr2 = customerAddr2;
	}

	public String getBeneficiaryName1() {
		return beneficiaryName1;
	}

	public void setBeneficiaryName1(String beneficiaryName1) {
		this.beneficiaryName1 = beneficiaryName1;
	}

	public String getBeneficiaryName2() {
		return beneficiaryName2;
	}

	public void setBeneficiaryName2(String beneficiaryName2) {
		this.beneficiaryName2 = beneficiaryName2;
	}

	public String getBeneficiaryAddr1() {
		return beneficiaryAddr1;
	}

	public void setBeneficiaryAddr1(String beneficiaryAddr1) {
		this.beneficiaryAddr1 = beneficiaryAddr1;
	}

	public String getBeneficiaryAddr2() {
		return beneficiaryAddr2;
	}

	public void setBeneficiaryAddr2(String beneficiaryAddr2) {
		this.beneficiaryAddr2 = beneficiaryAddr2;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
