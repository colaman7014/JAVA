package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt295;
import com.sas.db.wlf.orm.sc.SwiftMt295PK;

/**
 * NCSC-SWIFT_MT295(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt295Dao extends CrudRepository<SwiftMt295, SwiftMt295PK>{
	SwiftMt295 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
