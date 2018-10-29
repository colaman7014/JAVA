package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt299;
import com.sas.db.wlf.orm.sc.SwiftMt299PK;

/**
 * NCSC-SWIFT_MT299(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt299Dao extends CrudRepository<SwiftMt299, SwiftMt299PK>{
	SwiftMt299 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
