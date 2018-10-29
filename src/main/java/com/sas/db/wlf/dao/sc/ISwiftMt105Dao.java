package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt105;
import com.sas.db.wlf.orm.sc.SwiftMt105PK;

/**
 * NCSC-SWIFT_MT105(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt105Dao extends CrudRepository<SwiftMt105, SwiftMt105PK>{
	SwiftMt105 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
