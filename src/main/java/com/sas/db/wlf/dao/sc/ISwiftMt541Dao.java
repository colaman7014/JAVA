package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt541;
import com.sas.db.wlf.orm.sc.SwiftMt541PK;

/**
 * NCSC-SWIFT_MT541(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt541Dao extends CrudRepository<SwiftMt541, SwiftMt541PK>{
	SwiftMt541 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
