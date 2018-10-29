package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt491;
import com.sas.db.wlf.orm.sc.SwiftMt491PK;

/**
 * NCSC-SWIFT_MT491(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt491Dao extends CrudRepository<SwiftMt491, SwiftMt491PK>{
	SwiftMt491 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
