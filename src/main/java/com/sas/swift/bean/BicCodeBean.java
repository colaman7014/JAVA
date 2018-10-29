package com.sas.swift.bean;

/**
 * SWIFT BICCode Bean
 * @author SAS
 *
 */
public class BicCodeBean {
	private String countryCode;
	private String bicCode;
	private boolean containsPOBOX;
	
	public BicCodeBean(){
	}
	
	public BicCodeBean(String countryCode, String bicCode){
		this.countryCode = countryCode;
		this.bicCode = bicCode;
	}
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getBicCode() {
		return bicCode;
	}

	public void setBicCode(String bicCode) {
		this.bicCode = bicCode;
	}

	public boolean isContainsPOBOX() {
		return containsPOBOX;
	}

	public void setContainsPOBOX(boolean containsPOBOX) {
		this.containsPOBOX = containsPOBOX;
	}

	@Override
	public String toString() {
		return "BicCodeBean [countryCode=" + countryCode + ", bicCode="
				+ bicCode + ", containsPOBOX=" + containsPOBOX + "]";
	}	
}
