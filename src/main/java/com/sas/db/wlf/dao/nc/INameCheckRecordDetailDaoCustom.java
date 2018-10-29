package com.sas.db.wlf.dao.nc;

import java.util.List;

import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;

/**
 * NCSC-NAME_CHECK_RECORD_DETAIL(Name Check 紀錄明細檔) Custom DAO 方法
 * @author SAS
 *
 */
public interface INameCheckRecordDetailDaoCustom{
	public void batchSave(List<NameCheckRecordDetail> nameCheckRecordDetailList);
	
	public List<NameCheckRecordDetail> queryNameCheckRecordDetailByUniqueKey(String uniqueKey);
}
