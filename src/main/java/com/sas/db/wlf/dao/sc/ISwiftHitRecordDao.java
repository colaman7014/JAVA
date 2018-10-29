package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftHitRecord;
import com.sas.db.wlf.orm.sc.SwiftHitRecordPK;

/**
 * NCSC-SWIFT_HIT_RECORD(SWIFT Check 掃中名單紀錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftHitRecordDao extends CrudRepository<SwiftHitRecord, SwiftHitRecordPK>, ISwiftHitRecordDaoCustom{
	
}
