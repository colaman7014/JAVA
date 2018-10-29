package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt899;
import com.sas.db.wlf.orm.sc.SwiftMt899PK;

/**
 * NCSC-SWIFT_MT899(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt899Dao extends CrudRepository<SwiftMt899, SwiftMt899PK>{
	SwiftMt899 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
