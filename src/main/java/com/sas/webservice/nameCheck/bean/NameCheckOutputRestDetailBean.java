package com.sas.webservice.nameCheck.bean;


import com.sas.db.aml.orm.fcfcore.FscBankNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscEntityNcWatchListDim;
import com.sas.db.aml.orm.fcfcore.FscLocationNcWatchListDim;

/**
 * Online Name Check Output Detail Bean
 * @author SAS
 *
 */
public class NameCheckOutputRestDetailBean {
	private String entity_watch_list_number;
	private String watchList_type_cd;
	private String watchList_sub_type_cd;
	private String identification_id;
	private String year_of_birth;
	private String citizenship_country_name;
	private float total_score;
	private String url;
	private String watchListName;
	
	private Long entity_watch_list_key;
	private String entity_name;
	private String entity_name_display;
	private String citizenship_country_code;
	
	private String category_desc;
	private String exact;
	private String flux;
	private String isEn;
	private String inclusive;
	private float exactScore;
	private float fluxScore;
	private float inclusiveScore;
	private float weightScore;
	private float needHitCount;
	private float nameCount;
	private float birthiDiff;
	private float finalScore;
	
	private String watchList_type_cd_nm;
	private String watchList_sub_type_cd_nm;
	
	public NameCheckOutputRestDetailBean(){	
	}
	
	public NameCheckOutputRestDetailBean(FscEntityNcWatchListDim dim){
		this.watchListName = dim.getWatchListName();
		this.entity_watch_list_number = dim.getEntityWatchListNumber();
		this.identification_id = dim.getIdentificationId();
		this.year_of_birth = String.valueOf(dim.getYearOfBirth());
		this.citizenship_country_name = dim.getCountryName();
		this.entity_watch_list_key = dim.getEntityWatchListKey();
		this.entity_name = dim.getEntityName();
		this.entity_name_display = dim.getEntityNameDisplay();
		this.citizenship_country_code = dim.getCountryCode();
	}
	
	public NameCheckOutputRestDetailBean(FscLocationNcWatchListDim dim){
		this.watchListName = dim.getWatchListName();
		this.entity_watch_list_number = dim.getLocationWatchListNumber();
		this.entity_watch_list_key = dim.getLocationWatchListKey();
		this.entity_name = dim.getCountryName();
		this.entity_name_display = dim.getCountryNameDisplay();
		this.citizenship_country_code = dim.getCountryCode();
		this.citizenship_country_name = dim.getCountryNameDisplay();
	}
	
	public NameCheckOutputRestDetailBean(FscBankNcWatchListDim dim){
		this.watchListName = dim.getWatchListName();
		this.entity_watch_list_number = dim.getBankWatchListNumber();
		this.entity_watch_list_key = dim.getBankWatchListKey();
		this.entity_name = dim.getBankName();
		this.entity_name_display = dim.getBankNameDisplay();
	}
	
	public String getEntity_watch_list_number() {
		return entity_watch_list_number;
	}
	public void setEntity_watch_list_number(String entity_watch_list_number) {
		this.entity_watch_list_number = entity_watch_list_number;
	}
	public String getWatchList_type_cd() {
		return watchList_type_cd;
	}
	public void setWatchList_type_cd(String watchList_type_cd) {
		this.watchList_type_cd = watchList_type_cd;
	}
	public String getWatchList_sub_type_cd() {
		return watchList_sub_type_cd;
	}
	public void setWatchList_sub_type_cd(String watchList_sub_type_cd) {
		this.watchList_sub_type_cd = watchList_sub_type_cd;
	}
	public String getIdentification_id() {
		return identification_id;
	}
	public void setIdentification_id(String identification_id) {
		this.identification_id = identification_id;
	}
	public String getYear_of_birth() {
		return year_of_birth;
	}
	public void setYear_of_birth(String year_of_birth) {
		this.year_of_birth = year_of_birth;
	}
	public String getCitizenship_country_name() {
		return citizenship_country_name;
	}
	public void setCitizenship_country_name(String citizenship_country_name) {
		this.citizenship_country_name = citizenship_country_name;
	}
	public String getUrl() {
		return url;
	}
	public float getTotal_score() {
		return total_score;
	}
	public void setTotal_score(float total_score) {
		this.total_score = total_score;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getEntity_watch_list_key() {
		return entity_watch_list_key;
	}
	public void setEntity_watch_list_key(Long entity_watch_list_key) {
		this.entity_watch_list_key = entity_watch_list_key;
	}

	public String getEntity_name() {
		return entity_name;
	}
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	public String getCitizenship_country_code() {
		return citizenship_country_code;
	}
	public void setCitizenship_country_code(String citizenship_country_code) {
		this.citizenship_country_code = citizenship_country_code;
	}
	public String getCategory_desc() {
		return category_desc;
	}
	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}
	public String getExact() {
		return exact;
	}
	public void setExact(String exact) {
		this.exact = exact;
	}
	public String getFlux() {
		return flux;
	}
	public void setFlux(String flux) {
		this.flux = flux;
	}
	public String getIsEn() {
		return isEn;
	}
	public void setIsEn(String isEn) {
		this.isEn = isEn;
	}
	public String getInclusive() {
		return inclusive;
	}
	public void setInclusive(String inclusive) {
		this.inclusive = inclusive;
	}
	public float getExactScore() {
		return exactScore;
	}
	public void setExactScore(float exactScore) {
		this.exactScore = exactScore;
	}
	public float getFluxScore() {
		return fluxScore;
	}
	public void setFluxScore(float fluxScore) {
		this.fluxScore = fluxScore;
	}
	public float getInclusiveScore() {
		return inclusiveScore;
	}
	public void setInclusiveScore(float inclusiveScore) {
		this.inclusiveScore = inclusiveScore;
	}
	public float getWeightScore() {
		return weightScore;
	}
	public void setWeightScore(float weightScore) {
		this.weightScore = weightScore;
	}
	public float getNeedHitCount() {
		return needHitCount;
	}
	public void setNeedHitCount(float needHitCount) {
		this.needHitCount = needHitCount;
	}
	public float getNameCount() {
		return nameCount;
	}
	public void setNameCount(float nameCount) {
		this.nameCount = nameCount;
	}
	public float getBirthiDiff() {
		return birthiDiff;
	}
	public void setBirthiDiff(float birthiDiff) {
		this.birthiDiff = birthiDiff;
	}
	public float getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(float finalScore) {
		this.finalScore = finalScore;
	}
	public String getEntity_name_display() {
		return entity_name_display;
	}

	public void setEntity_name_display(String entity_name_display) {
		this.entity_name_display = entity_name_display;
	}
	
	public String getWatchList_type_cd_nm() {
		return watchList_type_cd_nm;
	}

	public void setWatchList_type_cd_nm(String watchList_type_cd_nm) {
		this.watchList_type_cd_nm = watchList_type_cd_nm;
	}

	public String getWatchList_sub_type_cd_nm() {
		return watchList_sub_type_cd_nm;
	}

	public void setWatchList_sub_type_cd_nm(String watchList_sub_type_cd_nm) {
		this.watchList_sub_type_cd_nm = watchList_sub_type_cd_nm;
	}

	public String getWatchListName() {
		return watchListName;
	}

	public void setWatchListName(String watchListName) {
		this.watchListName = watchListName;
	}

	@Override
	public String toString() {
		return "NameCheckOutputRestDetailBean [entity_watch_list_number="
				+ entity_watch_list_number + ", watchList_type_cd="
				+ watchList_type_cd + ", watchList_sub_type_cd="
				+ watchList_sub_type_cd + ", identification_id="
				+ identification_id + ", year_of_birth=" + year_of_birth
				+ ", citizenship_country_name=" + citizenship_country_name
				+ ", total_score=" + total_score + ", url=" + url
				+ ", watchListName=" + watchListName
				+ ", entity_watch_list_key=" + entity_watch_list_key
				+ ", entity_name=" + entity_name + ", entity_name_display="
				+ entity_name_display + ", citizenship_country_code="
				+ citizenship_country_code + ", category_desc=" + category_desc
				+ ", exact=" + exact + ", flux=" + flux + ", isEn=" + isEn
				+ ", inclusive=" + inclusive + ", exactScore=" + exactScore
				+ ", fluxScore=" + fluxScore + ", inclusiveScore="
				+ inclusiveScore + ", weightScore=" + weightScore
				+ ", needHitCount=" + needHitCount + ", nameCount=" + nameCount
				+ ", birthiDiff=" + birthiDiff + ", finalScore=" + finalScore
				+ ", watchList_type_cd_nm=" + watchList_type_cd_nm
				+ ", watchList_sub_type_cd_nm=" + watchList_sub_type_cd_nm
				+ "]";
	}
}
