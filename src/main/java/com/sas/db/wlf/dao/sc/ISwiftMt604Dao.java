package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt604;
import com.sas.db.wlf.orm.sc.SwiftMt604PK;

/**
 * NCSC-SWIFT_MT604(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt604Dao extends CrudRepository<SwiftMt604, SwiftMt604PK>{
	SwiftMt604 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
