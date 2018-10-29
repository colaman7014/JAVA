package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the X_FSC_ENTITY_WL_X_LIST_SETTING database table.
 * 
 */
@Entity
@Table(name="X_FSC_ENTITY_WL_X_LIST_SETTING")
@NamedQuery(name="XFscEntityWlXListSetting.findAll", query="SELECT x FROM XFscEntityWlXListSetting x")
public class XFscEntityWlXListSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XFscEntityWlXListSettingPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Change_End_Date")
	private Date changeEndDate;

	@Column(name="Entity_Type_Cd")
	private String entityTypeCd;

	@Column(name="entity_watch_list_number")
	private String entityWatchListNumber;

	@Column(name="watch_list_name")
	private String watchListName;

	@Column(name="WatchList_Source_Cd")
	private String watchListSourceCd;

	@Column(name="WatchList_Sub_Type_Cd")
	private String watchListSubTypeCd;

	@Column(name="WatchList_Type_Cd")
	private String watchListTypeCd;

	public XFscEntityWlXListSetting() {
	}

	public XFscEntityWlXListSettingPK getId() {
		return id;
	}

	public void setId(XFscEntityWlXListSettingPK id) {
		this.id = id;
	}

	public Date getChangeEndDate() {
		return changeEndDate;
	}

	public void setChangeEndDate(Date changeEndDate) {
		this.changeEndDate = changeEndDate;
	}

	public String getEntityTypeCd() {
		return entityTypeCd;
	}

	public void setEntityTypeCd(String entityTypeCd) {
		this.entityTypeCd = entityTypeCd;
	}

	public String getEntityWatchListNumber() {
		return entityWatchListNumber;
	}

	public void setEntityWatchListNumber(String entityWatchListNumber) {
		this.entityWatchListNumber = entityWatchListNumber;
	}

	public String getWatchListName() {
		return watchListName;
	}

	public void setWatchListName(String watchListName) {
		this.watchListName = watchListName;
	}

	public String getWatchListSourceCd() {
		return watchListSourceCd;
	}

	public void setWatchListSourceCd(String watchListSourceCd) {
		this.watchListSourceCd = watchListSourceCd;
	}

	public String getWatchListSubTypeCd() {
		return watchListSubTypeCd;
	}

	public void setWatchListSubTypeCd(String watchListSubTypeCd) {
		this.watchListSubTypeCd = watchListSubTypeCd;
	}

	public String getWatchListTypeCd() {
		return watchListTypeCd;
	}

	public void setWatchListTypeCd(String watchListTypeCd) {
		this.watchListTypeCd = watchListTypeCd;
	}

}