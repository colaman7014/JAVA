package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt198;
import com.sas.db.wlf.orm.sc.SwiftMt198PK;

/**
 * NCSC-SWIFT_MT198(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt198Dao extends CrudRepository<SwiftMt198, SwiftMt198PK>{
	SwiftMt198 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
