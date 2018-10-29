package com.sas.webservice.nameCheckStatus.bean;

public class NameCheckStatusMainInputRestBean {
	private String calling_user;
	private String bussiness_unit_id;
	private String branch_number;
	
	public String getCalling_user() {
		return calling_user;
	}
	public void setCalling_user(String calling_user) {
		this.calling_user = calling_user;
	}
	public String getBussiness_unit_id() {
		return bussiness_unit_id;
	}
	public void setBussiness_unit_id(String bussiness_unit_id) {
		this.bussiness_unit_id = bussiness_unit_id;
	}
	public String getBranch_number() {
		return branch_number;
	}
	public void setBranch_number(String branch_number) {
		this.branch_number = branch_number;
	}
	@Override
	public String toString() {
		return "NameCheckStatusMainInputRestBean [calling_user=" + calling_user
				+ ", bussiness_unit_id=" + bussiness_unit_id
				+ ", branch_number=" + branch_number + "]";
	}
}
