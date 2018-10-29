package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt791;
import com.sas.db.wlf.orm.sc.SwiftMt791PK;

/**
 * NCSC-SWIFT_MT791(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt791Dao extends CrudRepository<SwiftMt791, SwiftMt791PK>{
	SwiftMt791 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
