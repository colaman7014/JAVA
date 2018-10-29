package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt498;
import com.sas.db.wlf.orm.sc.SwiftMt498PK;

/**
 * NCSC-SWIFT_MT498(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt498Dao extends CrudRepository<SwiftMt498, SwiftMt498PK>{
	SwiftMt498 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
