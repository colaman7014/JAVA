package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt750;
import com.sas.db.wlf.orm.sc.SwiftMt750PK;

/**
 * NCSC-SWIFT_MT750(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt750Dao extends CrudRepository<SwiftMt750, SwiftMt750PK>{
	SwiftMt750 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
