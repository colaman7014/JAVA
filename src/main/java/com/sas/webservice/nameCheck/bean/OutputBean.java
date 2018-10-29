package com.sas.webservice.nameCheck.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Terry
 * 
 *  aml_check_daily_record
 * 
 **/
public class OutputBean {
	private String error_code="";
	private String error_message="";
	private String seq=""; 
	private String aml_result="";
	private String list_type="";
	private String original_source="" ;
	private String category_desc="";
	private String name="" ;
	private String dob="" ;
	private String nationality="" ;
	private String id="" ;
	private String passport="";
	private String invest_result="" ;
	private String invest_comment="" ;
	
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getAml_result() {
		return aml_result;
	}
	public void setAml_result(String aml_result) {
		this.aml_result = aml_result;
	}
	public String getList_type() {
		return list_type;
	}
	public void setList_type(String list_type) {
		this.list_type = list_type;
	}
	public String getOriginal_source() {
		return original_source;
	}
	public void setOriginal_source(String original_source) {
		this.original_source = original_source;
	}
	public String getCategory_desc() {
		return category_desc;
	}
	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getInvest_result() {
		return invest_result;
	}
	public void setInvest_result(String invest_result) {
		this.invest_result = invest_result;
	}
	public String getInvest_comment() {
		return invest_comment;
	}
	public void setInvest_comment(String invest_comment) {
		this.invest_comment = invest_comment;
	}
	
	@Override
    public String toString() {
    	return ReflectionToStringBuilder.toString(this);
    }
}
