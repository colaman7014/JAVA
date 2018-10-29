package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt705;
import com.sas.db.wlf.orm.sc.SwiftMt705PK;

/**
 * NCSC-SWIFT_MT705(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt705Dao extends CrudRepository<SwiftMt705, SwiftMt705PK>{
	SwiftMt705 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
