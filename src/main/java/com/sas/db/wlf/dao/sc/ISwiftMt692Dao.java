package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt692;
import com.sas.db.wlf.orm.sc.SwiftMt692PK;

/**
 * NCSC-SWIFT_MT692(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt692Dao extends CrudRepository<SwiftMt692, SwiftMt692PK>{
	SwiftMt692 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
