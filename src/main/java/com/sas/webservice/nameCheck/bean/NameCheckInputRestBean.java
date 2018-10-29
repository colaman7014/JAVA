package com.sas.webservice.nameCheck.bean;

/**
 * Online Name Check Input Bean
 * @author SAS
 *
 */
public class NameCheckInputRestBean {
	private String type_desc;
	private String ch_name;
	private String en_name;
	private String ccc_code;
	private String id_input;
	private String country_cd;
	private String yearOfBirth;
	
	public String getType_desc() {
		return type_desc;
	}
	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	public String getCcc_code() {
		return ccc_code;
	}
	public void setCcc_code(String ccc_code) {
		this.ccc_code = ccc_code;
	}
	public String getId_input() {
		return id_input;
	}
	public void setId_input(String id_input) {
		this.id_input = id_input;
	}
	public String getCountry_cd() {
		return country_cd;
	}
	public void setCountry_cd(String country_cd) {
		this.country_cd = country_cd;
	}
	public String getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	@Override
	public String toString() {
		return "NameCheckInputRestBean [type_desc=" + type_desc + ", ch_name="
				+ ch_name + ", en_name=" + en_name + ", ccc_code=" + ccc_code
				+ ", id_input=" + id_input + ", country_cd=" + country_cd
				+ ", yearOfBirth=" + yearOfBirth + "]";
	}
}
