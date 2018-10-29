package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt201;
import com.sas.db.wlf.orm.sc.SwiftMt201PK;

/**
 * NCSC-SWIFT_MT201(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt201Dao extends CrudRepository<SwiftMt201, SwiftMt201PK>{
	SwiftMt201 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
