package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt747;
import com.sas.db.wlf.orm.sc.SwiftMt747PK;

/**
 * NCSC-SWIFT_MT747(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt747Dao extends CrudRepository<SwiftMt747, SwiftMt747PK>{
	SwiftMt747 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
