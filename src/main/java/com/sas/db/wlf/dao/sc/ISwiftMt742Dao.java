package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt742;
import com.sas.db.wlf.orm.sc.SwiftMt742PK;

/**
 * NCSC-SWIFT_MT742(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt742Dao extends CrudRepository<SwiftMt742, SwiftMt742PK>{
	SwiftMt742 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
