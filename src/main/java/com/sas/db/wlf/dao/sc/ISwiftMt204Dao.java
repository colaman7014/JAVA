package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt204;
import com.sas.db.wlf.orm.sc.SwiftMt204PK;

/**
 * NCSC-SWIFT_MT204(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt204Dao extends CrudRepository<SwiftMt204, SwiftMt204PK>{
	SwiftMt204 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
