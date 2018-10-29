package com.sas.db.wlf.dao;

import java.util.List;

import com.sas.db.wlf.orm.XInvBlNcUploadRecord;


/**
 * @author meng
 * date 2017/12/04
 */
public interface IXInvBlNcUploadRecordDaoCustomer {
	// To Query File Upload List By Status
	public List<XInvBlNcUploadRecord> queryFileUploadCondition(
			String createUser, String uploadType, String scanType, String startDate, String endDate, String scanStatus);
	public List<XInvBlNcUploadRecord> queryFileUploadConditionByUsers(
			List<String> createUsers, String uploadType, String scanType, String startDate, String endDate, String scanStatus);

}
