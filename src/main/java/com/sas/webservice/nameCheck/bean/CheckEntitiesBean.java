package com.sas.webservice.nameCheck.bean;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Terry
 * 
 *
 */
public class CheckEntitiesBean {
	private String seq="";
	@NotEmpty(message = "entity_type not empty")
//	@Pattern(regexp = "^[0]{1}[1,3-5,8-9]{1}$", message = "entity_type 超過定義範圍")
	@Pattern(regexp = "^[1,3-5,8-9,99]{1}$", message = "entity_type 超過定義範圍")
	private String entity_type;
	private String id="";
	private String ch_name="";
	private String en_name="";
	private String country="" ;
	
	public String getEntity_type() {
		return entity_type;
	}
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
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
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
    public String toString() {
    	return ReflectionToStringBuilder.toString(this);
    }
}
