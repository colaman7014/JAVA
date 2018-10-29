package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the FSC_LOCATION_SC_WATCH_LIST_DIM database table.
 * 
 */
@Entity
@Table(name="FSC_LOCATION_SC_WATCH_LIST_DIM")
@NamedQuery(name="FscLocationScWatchListDim.findAll", query="SELECT f FROM FscLocationScWatchListDim f")
public class FscLocationScWatchListDim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int SC_Key;

	@Column(name="change_current_ind")
	private String changeCurrentInd;

	@Column(name="country_code")
	private String countryCode;

	@Column(name="country_name")
	private String countryName;

	@Column(name="country_name_display")
	private String countryNameDisplay;

	@Column(name="delete_ind")
	private String deleteInd;

	@Column(name="exclude_ind")
	private String excludeInd;

	@Column(name="location_watch_list_key")
	private long locationWatchListKey;

	@Column(name="location_watch_list_number")
	private String locationWatchListNumber;

	@Column(name="match_code_country_name")
	private String matchCodeCountryName;

	@Column(name="nospace_name_ind")
	private String nospaceNameInd;

	@Column(name="process_dttm")
	private Timestamp processDttm;

	@Column(name="sort_name_ind")
	private String sortNameInd;

	@Column(name="watch_list_name")
	private String watchListName;

	public FscLocationScWatchListDim() {
	}

	public int getSC_Key() {
		return this.SC_Key;
	}

	public void setSC_Key(int SC_Key) {
		this.SC_Key = SC_Key;
	}

	public String getChangeCurrentInd() {
		return this.changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryNameDisplay() {
		return this.countryNameDisplay;
	}

	public void setCountryNameDisplay(String countryNameDisplay) {
		this.countryNameDisplay = countryNameDisplay;
	}

	public String getDeleteInd() {
		return this.deleteInd;
	}

	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	public String getExcludeInd() {
		return this.excludeInd;
	}

	public void setExcludeInd(String excludeInd) {
		this.excludeInd = excludeInd;
	}

	public long getLocationWatchListKey() {
		return this.locationWatchListKey;
	}

	public void setLocationWatchListKey(long locationWatchListKey) {
		this.locationWatchListKey = locationWatchListKey;
	}

	public String getLocationWatchListNumber() {
		return this.locationWatchListNumber;
	}

	public void setLocationWatchListNumber(String locationWatchListNumber) {
		this.locationWatchListNumber = locationWatchListNumber;
	}

	public String getMatchCodeCountryName() {
		return this.matchCodeCountryName;
	}

	public void setMatchCodeCountryName(String matchCodeCountryName) {
		this.matchCodeCountryName = matchCodeCountryName;
	}

	public String getNospaceNameInd() {
		return this.nospaceNameInd;
	}

	public void setNospaceNameInd(String nospaceNameInd) {
		this.nospaceNameInd = nospaceNameInd;
	}

	public Timestamp getProcessDttm() {
		return this.processDttm;
	}

	public void setProcessDttm(Timestamp processDttm) {
		this.processDttm = processDttm;
	}

	public String getSortNameInd() {
		return this.sortNameInd;
	}

	public void setSortNameInd(String sortNameInd) {
		this.sortNameInd = sortNameInd;
	}

	public String getWatchListName() {
		return this.watchListName;
	}

	public void setWatchListName(String watchListName) {
		this.watchListName = watchListName;
	}

}