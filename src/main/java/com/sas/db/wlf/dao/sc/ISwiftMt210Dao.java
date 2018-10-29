package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt210;
import com.sas.db.wlf.orm.sc.SwiftMt210PK;

/**
 * NCSC-SWIFT_MT210(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt210Dao extends CrudRepository<SwiftMt210, SwiftMt210PK>{
	SwiftMt210 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
