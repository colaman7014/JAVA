package com.sas.webservice.nameCheckStatus.bean;

import java.text.SimpleDateFormat;

import com.sas.db.wlf.orm.nc.NameCheckRecordMain;

public class NameCheckStatusMainoutputRestBean {
	private String unique_key;
	private String ncReferenceId;
	private String calling_system;
	private String screen_process;
	private String calling_user;
	private String bussiness_unit_id;
	private String branch_number;
	private String transaction_date;
	private String route_rule;
	private String nc_result;
	
	public NameCheckStatusMainoutputRestBean(){
		
	}
	
	public NameCheckStatusMainoutputRestBean(NameCheckRecordMain main){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		this.unique_key = main.getId().getUniqueKey();
		this.ncReferenceId = String.valueOf(main.getId().getNcReferenceId());
		this.calling_system = main.getCallingSystem();
		this.screen_process = main.getScreenProcess();
		this.calling_user = main.getCallingUser();
		this.bussiness_unit_id = main.getBusinessUnitId();
		this.branch_number = main.getBranchNumber();
		if(main.getTransactionDate() != null){
			this.transaction_date = sdf.format(main.getTransactionDate());
		}
		this.route_rule = main.getRouteRule();
		this.nc_result = main.getNcResult();
	}
	
	public String getUnique_key() {
		return unique_key;
	}
	public void setUnique_key(String unique_key) {
		this.unique_key = unique_key;
	}
	
	public String getNcReferenceId() {
		return ncReferenceId;
	}
	public void setNcReferenceId(String ncReferenceId) {
		this.ncReferenceId = ncReferenceId;
	}

	public String getCalling_system() {
		return calling_system;
	}
	public void setCalling_system(String calling_system) {
		this.calling_system = calling_system;
	}
	public String getScreen_process() {
		return screen_process;
	}
	public void setScreen_process(String screen_process) {
		this.screen_process = screen_process;
	}
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
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getRoute_rule() {
		return route_rule;
	}
	public void setRoute_rule(String route_rule) {
		this.route_rule = route_rule;
	}
	public String getNc_result() {
		return nc_result;
	}
	public void setNc_result(String nc_result) {
		this.nc_result = nc_result;
	}

	@Override
	public String toString() {
		return "NameCheckStatusMainoutputRestBean [unique_key=" + unique_key
				+ ", ncReferenceId=" + ncReferenceId + ", calling_system="
				+ calling_system + ", screen_process=" + screen_process
				+ ", calling_user=" + calling_user + ", bussiness_unit_id="
				+ bussiness_unit_id + ", branch_number=" + branch_number
				+ ", transaction_date=" + transaction_date + ", route_rule="
				+ route_rule + ", nc_result=" + nc_result + "]";
	}
}
