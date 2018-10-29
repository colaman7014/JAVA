package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt496;
import com.sas.db.wlf.orm.sc.SwiftMt496PK;

/**
 * NCSC-SWIFT_MT496(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt496Dao extends CrudRepository<SwiftMt496, SwiftMt496PK>{
	SwiftMt496 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
