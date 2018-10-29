package com.sas.db.wlf.dao.nc;

import java.util.List;

import com.sas.db.wlf.orm.nc.NameHitRecord;

/**
 * NCSC-NAME_HIT_RECORD(Name Check 掃中名單紀錄檔) Custom DAO 方法
 * @author SAS
 *
 */
public interface INameHitRecordDaoCustom{
	public void batchSave(List<NameHitRecord> nameHitRecordList);
	
}
