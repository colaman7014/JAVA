package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the X_SCREEN_PROCESS_SETTING database table.
 * 
 */
@Entity
@Table(name="X_SCREEN_PROCESS_SETTING")
@NamedQuery(name="XScreenProcessSetting.findAll", query="SELECT x FROM XScreenProcessSetting x")
public class XScreenProcessSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XScreenProcessSettingPK id;

	@Column(name="Change_Current_Ind")
	private String changeCurrentInd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Change_End_Date")
	private Date changeEndDate;

	@Column(name="Create_User_Id")
	private String createUserId;

	@Column(name="Delete_Ind")
	private String deleteInd;

	@Column(name="Score")
	private BigDecimal score;

	@Column(name="Update_User_Id")
	private String updateUserId;

	public XScreenProcessSetting() {
	}

	public XScreenProcessSettingPK getId() {
		return id;
	}

	public void setId(XScreenProcessSettingPK id) {
		this.id = id;
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

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getDeleteInd() {
		return deleteInd;
	}

	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

}