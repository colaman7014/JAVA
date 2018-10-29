package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt455;
import com.sas.db.wlf.orm.sc.SwiftMt455PK;

/**
 * NCSC-SWIFT_MT455(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt455Dao extends CrudRepository<SwiftMt455, SwiftMt455PK>{
	SwiftMt455 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
