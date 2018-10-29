package com.sas.wlsearch.bean;

public class ScoreWeightConfig {


	private float exact_match_score_default = 100;
	private float fuzzy_match_score_default = 60;
	private float inclusive_match_socre_defalut = 0;

	private float id_match_default = 100;
	private float country_match_default = 5;
	private float year_match_default = 5;

	public float getExact_match_score_default() {
		return exact_match_score_default;
	}

	public void setExact_match_score_default(float exact_match_score_default) {
		this.exact_match_score_default = exact_match_score_default;
	}

	public float getFuzzy_match_score_default() {
		return fuzzy_match_score_default;
	}

	public void setFuzzy_match_score_default(float fuzzy_match_score_default) {
		this.fuzzy_match_score_default = fuzzy_match_score_default;
	}

	public float getInclusive_match_socre_defalut() {
		return inclusive_match_socre_defalut;
	}

	public void setInclusive_match_socre_defalut(float inclusive_match_socre_defalut) {
		this.inclusive_match_socre_defalut = inclusive_match_socre_defalut;
	}

	public float getId_match_default() {
		return id_match_default;
	}

	public void setId_match_default(float id_match_default) {
		this.id_match_default = id_match_default;
	}

	public float getCountry_match_default() {
		return country_match_default;
	}

	public void setCountry_match_default(float country_match_default) {
		this.country_match_default = country_match_default;
	}

	public float getYear_match_default() {
		return year_match_default;
	}

	public void setYear_match_default(float year_match_default) {
		this.year_match_default = year_match_default;
	}

}
