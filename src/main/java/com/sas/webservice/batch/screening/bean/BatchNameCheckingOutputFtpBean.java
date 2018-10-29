package com.sas.webservice.batch.screening.bean;

import java.util.Date;

public class BatchNameCheckingOutputFtpBean {
	private String party_number="";
	private String related_party_number="";
	private String relationship_type_code="";
	private String name_check_status="";
	private String hit_route="";
	private String confirm_hit_route="";
	private Date name_check_date;
	
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
	public String getRelationship_type_code() {
		return relationship_type_code;
	}
	public void setRelationship_type_code(String relationship_type_code) {
		this.relationship_type_code = relationship_type_code;
	}
	public String getName_check_status() {
		return name_check_status;
	}
	public void setName_check_status(String name_check_status) {
		this.name_check_status = name_check_status;
	}
	public String getHit_route() {
		return hit_route;
	}
	public void setHit_route(String hit_route) {
		this.hit_route = hit_route;
	}
	public String getConfirm_hit_route() {
		return confirm_hit_route;
	}
	public void setConfirm_hit_route(String confirm_hit_route) {
		this.confirm_hit_route = confirm_hit_route;
	}
	public Date getName_check_date() {
		return name_check_date;
	}
	public void setName_check_date(Date name_check_date) {
		this.name_check_date = name_check_date;
	}
	
	
}
