package com.sas.swift.bean;

import com.sas.db.wlf.orm.sc.SwiftConfig;

/**
 * SWIFT SWiftConfig Bean
 * @author SAS
 *
 */
public class SwiftConfigBean {
	private String swiftType;
	private String fieldTag;
	private String fieldName;

	private Boolean screenBicCountry;
	private Boolean screenCccCode;
	private Boolean screenCountryCity;
	private Boolean screenPobox;
	
//	private Boolean screenField;
	private String screenField;
	
	private String fieldValue;
	
	public SwiftConfigBean(){
	}
	
	public SwiftConfigBean(SwiftConfig swiftConfig){
		this.swiftType = swiftConfig.getId().getSwiftType();
		this.fieldTag = swiftConfig.getId().getFieldTag();
		this.fieldName = swiftConfig.getFieldName();
		
		this.screenBicCountry = isEnable(swiftConfig.getScreenBicCountry());
		this.screenCccCode = isEnable(swiftConfig.getScreenCccCode());
		this.screenCountryCity = isEnable(swiftConfig.getScreenCountryCity());
		this.screenPobox = isEnable(swiftConfig.getScreenPobox());
		
//		this.screenField = isEnable(swiftConfig.getScreenField());
		this.screenField = swiftConfig.getScreenField();
		
	}
	
	private boolean isEnable(String funcFlag){
		boolean flag = false;
		if("Y".equalsIgnoreCase(funcFlag)){
			flag = true;
		}
		return flag;
	}
	
	public String getSwiftType() {
		return swiftType;
	}
	public void setSwiftType(String swiftType) {
		this.swiftType = swiftType;
	}
	public String getFieldTag() {
		return fieldTag;
	}
	public void setFieldTag(String fieldTag) {
		this.fieldTag = fieldTag;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Boolean getScreenBicCountry() {
		return screenBicCountry;
	}
	public void setScreenBicCountry(Boolean screenBicCountry) {
		this.screenBicCountry = screenBicCountry;
	}
	public Boolean getScreenCccCode() {
		return screenCccCode;
	}
	public void setScreenCccCode(Boolean screenCccCode) {
		this.screenCccCode = screenCccCode;
	}
	public Boolean getScreenCountryCity() {
		return screenCountryCity;
	}
	public void setScreenCountryCity(Boolean screenCountryCity) {
		this.screenCountryCity = screenCountryCity;
	}
//	public Boolean getScreenField() {
//		return screenField;
//	}
//	public void setScreenField(Boolean screenField) {
//		this.screenField = screenField;
//	}
	
	public Boolean getScreenPobox() {
		return screenPobox;
	}
	public String getScreenField() {
		return screenField;
	}
	
	public void setScreenField(String screenField) {
		this.screenField = screenField;
	}

	public void setScreenPobox(Boolean screenPobox) {
		this.screenPobox = screenPobox;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	@Override
	public String toString() {
		return "SwiftConfigBean [swiftType=" + swiftType + ", fieldTag="
				+ fieldTag + ", fieldName=" + fieldName + ", screenBicCountry="
				+ screenBicCountry + ", screenCccCode=" + screenCccCode
				+ ", screenCountryCity=" + screenCountryCity + ", screenField="
				+ screenField + ", screenPobox=" + screenPobox
				+ ", fieldValue=" + fieldValue + "]";
	}
}
