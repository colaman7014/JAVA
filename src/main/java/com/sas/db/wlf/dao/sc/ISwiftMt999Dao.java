package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt999;
import com.sas.db.wlf.orm.sc.SwiftMt999PK;

/**
 * NCSC-SWIFT_MT999(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt999Dao extends CrudRepository<SwiftMt999, SwiftMt999PK>{
	SwiftMt999 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
