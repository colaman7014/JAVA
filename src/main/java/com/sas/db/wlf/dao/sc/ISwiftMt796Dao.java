package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt796;
import com.sas.db.wlf.orm.sc.SwiftMt796PK;

/**
 * NCSC-SWIFT_MT796(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt796Dao extends CrudRepository<SwiftMt796, SwiftMt796PK>{
	SwiftMt796 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
