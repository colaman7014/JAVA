package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftLogTime;

/**
 * NCSC-SWIFT_CHECK_RECORD(SWIFT掃描紀錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftLogTimeDao extends CrudRepository<SwiftLogTime, String>{
	
}
