package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FSC_LOCATION_WATCH_LIST_DIM database table.
 * 
 */
@Entity
@Table(name="FSC_LOCATION_WATCH_LIST_DIM")
@NamedQuery(name="FscLocationWatchListDim.findAll", query="SELECT f FROM FscLocationWatchListDim f")
public class FscLocationWatchListDim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="location_watch_list_key")
	private long locationWatchListKey;

	@Column(name="change_begin_date")
	private Timestamp changeBeginDate;

	@Column(name="change_current_ind")
	private String changeCurrentInd;

	@Column(name="change_end_date")
	private Timestamp changeEndDate;

	@Column(name="country_code")
	private String countryCode;

	@Column(name="country_name")
	private String countryName;

	@Column(name="country_name_display")
	private String countryNameDisplay;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="delete_ind")
	private String deleteInd;

	@Column(name="exclude_ind")
	private String excludeInd;

	@Column(name="location_watch_list_number")
	private String locationWatchListNumber;

	@Column(name="match_code_country_name")
	private String matchCodeCountryName;

	@Column(name="process_dttm")
	private Timestamp processDttm;

	private String program;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="watch_list_name")
	private String watchListName;

	public FscLocationWatchListDim() {
	}

	public long getLocationWatchListKey() {
		return this.locationWatchListKey;
	}

	public void setLocationWatchListKey(long locationWatchListKey) {
		this.locationWatchListKey = locationWatchListKey;
	}

	public Timestamp getChangeBeginDate() {
		return this.changeBeginDate;
	}

	public void setChangeBeginDate(Timestamp changeBeginDate) {
		this.changeBeginDate = changeBeginDate;
	}

	public String getChangeCurrentInd() {
		return this.changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	public Timestamp getChangeEndDate() {
		return this.changeEndDate;
	}

	public void setChangeEndDate(Timestamp changeEndDate) {
		this.changeEndDate = changeEndDate;
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

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
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

	public Timestamp getProcessDttm() {
		return this.processDttm;
	}

	public void setProcessDttm(Timestamp processDttm) {
		this.processDttm = processDttm;
	}

	public String getProgram() {
		return this.program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getWatchListName() {
		return this.watchListName;
	}

	public void setWatchListName(String watchListName) {
		this.watchListName = watchListName;
	}

}