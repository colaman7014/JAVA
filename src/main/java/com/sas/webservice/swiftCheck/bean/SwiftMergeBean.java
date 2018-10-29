package com.sas.webservice.swiftCheck.bean;

public class SwiftMergeBean {
	private int seq = 0;
	private String swiftType = "";
	private String fieldName = "";
	private String fieldTag = "";
	private String fieldValue = "";

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSwiftType() {
		return swiftType;
	}
	public void setSwiftType(String swiftType) {
		this.swiftType = swiftType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldTag() {
		return fieldTag;
	}
	public void setFieldTag(String fieldTag) {
		this.fieldTag = fieldTag;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	@Override
	public String toString() {
		return "SwiftMergeBean [seq=" + seq + ", swiftType=" + swiftType
				+ ", fieldName=" + fieldName + ", fieldTag=" + fieldTag
				+ ", fieldValue=" + fieldValue + "]";
	}
}
