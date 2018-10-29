package com.sas.wlsearch.bean;

public class WatchListBean {

	private boolean exact_match=false;
	private boolean fuzzy_match=false;
	private boolean inclusive_match=false;
	private float exact_match_score=0;
	private float fuzzy_match_score=0;
	private float inclusive_match_score=0;
	private float entity_watch_list_key=0;
	private String entity_name = "";
	private String identification_id = "";
	private String country_code = "";
	private String year_of_birth = "";
	private String watch_list_name = "";
	private String category_desc = "";
	private float final_score = 0;
	public boolean isExact_match() {
		return exact_match;
	}
	public void setExact_match(boolean exact_match) {
		this.exact_match = exact_match;
	}
	public boolean isFuzzy_match() {
		return fuzzy_match;
	}
	public void setFuzzy_match(boolean fuzzy_match) {
		this.fuzzy_match = fuzzy_match;
	}
	public boolean isInclusive_match() {
		return inclusive_match;
	}
	public void setInclusive_match(boolean inclusive_match) {
		this.inclusive_match = inclusive_match;
	}
	public float getExact_match_score() {
		return exact_match_score;
	}
	public void setExact_match_score(float exact_match_score) {
		this.exact_match_score = exact_match_score;
	}
	public float getFuzzy_match_score() {
		return fuzzy_match_score;
	}
	public void setFuzzy_match_score(float fuzzy_match_score) {
		this.fuzzy_match_score = fuzzy_match_score;
	}
	public float getInclusive_match_score() {
		return inclusive_match_score;
	}
	public void setInclusive_match_score(float inclusive_match_score) {
		this.inclusive_match_score = inclusive_match_score;
	}

	public float getEntity_watch_list_key() {
		return entity_watch_list_key;
	}
	public void setEntity_watch_list_key(float entity_watch_list_key) {
		this.entity_watch_list_key = entity_watch_list_key;
	}
	public String getEntity_name() {
		return entity_name;
	}
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	public String getIdentification_id() {
		return identification_id;
	}
	public void setIdentification_id(String identification_id) {
		this.identification_id = identification_id;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getYear_of_birth() {
		return year_of_birth;
	}
	public void setYear_of_birth(String year_of_birth) {
		this.year_of_birth = year_of_birth;
	}
	public String getWatch_list_name() {
		return watch_list_name;
	}
	public void setWatch_list_name(String watch_list_name) {
		this.watch_list_name = watch_list_name;
	}
	public String getCategory_desc() {
		return category_desc;
	}
	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}
	public float getFinal_score() {
		return final_score;
	}
	public void setFinal_score(float final_score) {
		this.final_score = final_score;
	}

}
