package com.sas.webservice.batch.screening.bean;

import java.sql.Date;

public class BatchNameCheckingOutputRealTimeBean {
	private String interface_name="";
	private Date name_check_date;
	private String party_number="";
	private String related_party_number="";
	private String confirm_hit_route="";
	private String nc_case_id="";
	private String nc_case_status="";
	private String nc_close_reason="";
//	private String pep_flag="";
//	private String pnmp_flag="";
	
	public String getInterface_name() {
		return interface_name;
	}
	public void setInterface_name(String interface_name) {
		this.interface_name = interface_name;
	}
	public Date getName_check_date() {
		return name_check_date;
	}
	public void setName_check_date(Date name_check_date) {
		this.name_check_date = name_check_date;
	}
	public String getParty_number() {
		return party_number;
	}
	public void setParty_number(String party_number) {
		this.party_number = party_number;
	}
	public String getRelated_party_number() {
		return related_party_number;
	}
	public void setRelated_party_number(String related_party_number) {
		this.related_party_number = related_party_number;
	}
	public String getConfirm_hit_route() {
		return confirm_hit_route;
	}
	public void setConfirm_hit_route(String confirm_hit_route) {
		this.confirm_hit_route = confirm_hit_route;
	}
	public String getNc_case_id() {
		return nc_case_id;
	}
	public void setNc_case_id(String nc_case_id) {
		this.nc_case_id = nc_case_id;
	}
	public String getNc_case_status() {
		return nc_case_status;
	}
	public void setNc_case_status(String nc_case_status) {
		this.nc_case_status = nc_case_status;
	}
	public String getNc_close_reason() {
		return nc_close_reason;
	}
	public void setNc_close_reason(String nc_close_reason) {
		this.nc_close_reason = nc_close_reason;
	}
//	public String getPep_flag() {
//		return pep_flag;
//	}
//	public void setPep_flag(String pep_flag) {
//		this.pep_flag = pep_flag;
//	}
//	public String getPnmp_flag() {
//		return pnmp_flag;
//	}
//	public void setPnmp_flag(String pnmp_flag) {
//		this.pnmp_flag = pnmp_flag;
//	}

}
