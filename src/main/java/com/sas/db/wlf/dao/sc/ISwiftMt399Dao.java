package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt399;
import com.sas.db.wlf.orm.sc.SwiftMt399PK;

/**
 * NCSC-SWIFT_MT399(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt399Dao extends CrudRepository<SwiftMt399, SwiftMt399PK>{
	SwiftMt399 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
