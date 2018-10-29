package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt542;
import com.sas.db.wlf.orm.sc.SwiftMt542PK;

/**
 * NCSC-SWIFT_MT542(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt542Dao extends CrudRepository<SwiftMt542, SwiftMt542PK>{
	SwiftMt542 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
