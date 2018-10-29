package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt416;
import com.sas.db.wlf.orm.sc.SwiftMt416PK;

/**
 * NCSC-SWIFT_MT416(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt416Dao extends CrudRepository<SwiftMt416, SwiftMt416PK>{
	SwiftMt416 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
