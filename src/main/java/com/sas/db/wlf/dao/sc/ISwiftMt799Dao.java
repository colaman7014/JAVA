package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt798;
import com.sas.db.wlf.orm.sc.SwiftMt799;
import com.sas.db.wlf.orm.sc.SwiftMt799PK;

/**
 * NCSC-SWIFT_MT798(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt799Dao extends CrudRepository<SwiftMt799, SwiftMt799PK>{
	SwiftMt798 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
