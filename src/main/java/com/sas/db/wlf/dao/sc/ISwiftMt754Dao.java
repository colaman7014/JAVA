package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt754;
import com.sas.db.wlf.orm.sc.SwiftMt754PK;

/**
 * NCSC-SWIFT_MT754(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt754Dao extends CrudRepository<SwiftMt754, SwiftMt754PK>{
	SwiftMt754 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
