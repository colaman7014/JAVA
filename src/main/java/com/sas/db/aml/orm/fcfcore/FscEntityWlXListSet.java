package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the X_FSC_ENTITY_WL_X_LIST_SET database table.
 * 
 */
@Entity
@Table(name="X_FSC_ENTITY_WL_X_LIST_SETTING")
@NamedQuery(name="FscEntityWlXListSet.findAll", query="SELECT f FROM FscEntityWlXListSet f")
public class FscEntityWlXListSet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Unique_Key")
	private long uniqueKey;

	@Column(name="Change_Begin_Date")
	private Date changeBeginDate;

	@Column(name="Change_Current_Ind")
	private String changeCurrentInd;

	@Column(name="Change_End_Date")
	private Date changeEndDate;

	@Column(name="entity_watch_list_number")
	private String entityWatchListNumber;

	@Column(name="watch_list_name")
	private String watchListName;

	@Column(name="WatchList_Sub_Type_Cd")
	private String watchListSubTypeCd;

	@Column(name="WatchList_Type_Cd")
	private String watchListTypeCd;
	
	@Column(name="Entity_Type_Cd")
	private String entityTypeCd;
	
	public FscEntityWlXListSet() {
	}

	public long getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(long uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public Date getChangeBeginDate() {
		return changeBeginDate;
	}

	public void setChangeBeginDate(Date changeBeginDate) {
		this.changeBeginDate = changeBeginDate;
	}

	public String getChangeCurrentInd() {
		return changeCurrentInd;
	}

	public void setChangeCurrentInd(String changeCurrentInd) {
		this.changeCurrentInd = changeCurrentInd;
	}

	public Date getChangeEndDate() {
		return changeEndDate;
	}

	public void setChangeEndDate(Date changeEndDate) {
		this.changeEndDate = changeEndDate;
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

	public String getEntityTypeCd() {
		return entityTypeCd;
	}

	public void setEntityTypeCd(String entityTypeCd) {
		this.entityTypeCd = entityTypeCd;
	}
}