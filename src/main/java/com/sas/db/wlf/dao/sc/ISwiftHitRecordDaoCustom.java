package com.sas.db.wlf.dao.sc;

import java.util.List;

import com.sas.db.wlf.orm.sc.SwiftHitRecord;

/**
 * NCSC-SWIFT_HIT_RECORD(SWIFT Check 掃中名單紀錄檔) Custom DAO
 * @author SAS
 *
 */
public interface ISwiftHitRecordDaoCustom{
	public void batchSave(List<SwiftHitRecord> swiftHitRecordList);

}
