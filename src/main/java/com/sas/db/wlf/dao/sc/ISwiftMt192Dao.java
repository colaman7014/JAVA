package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt192;
import com.sas.db.wlf.orm.sc.SwiftMt192PK;

/**
 * NCSC-SWIFT_MT192(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt192Dao extends CrudRepository<SwiftMt192, SwiftMt192PK>{
	SwiftMt192 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
