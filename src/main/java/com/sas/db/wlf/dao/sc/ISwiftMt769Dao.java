package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt769;
import com.sas.db.wlf.orm.sc.SwiftMt769PK;

/**
 * NCSC-SWIFT_MT769(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt769Dao extends CrudRepository<SwiftMt769, SwiftMt769PK>{
	SwiftMt769 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
