package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt199;
import com.sas.db.wlf.orm.sc.SwiftMt199PK;

/**
 * NCSC-SWIFT_MT199(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt199Dao extends CrudRepository<SwiftMt199, SwiftMt199PK>{
	SwiftMt199 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
