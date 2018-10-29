package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt196;
import com.sas.db.wlf.orm.sc.SwiftMt196PK;

/**
 * NCSC-SWIFT_MT196(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt196Dao extends CrudRepository<SwiftMt196, SwiftMt196PK>{
	SwiftMt196 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
