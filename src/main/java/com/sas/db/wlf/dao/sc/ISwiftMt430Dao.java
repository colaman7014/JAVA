package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt430;
import com.sas.db.wlf.orm.sc.SwiftMt430PK;

/**
 * NCSC-SWIFT_MT430(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt430Dao extends CrudRepository<SwiftMt430, SwiftMt430PK>{
	SwiftMt430 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
