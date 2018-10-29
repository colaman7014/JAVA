package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt720;
import com.sas.db.wlf.orm.sc.SwiftMt720PK;

/**
 * NCSC-SWIFT_MT720(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt720Dao extends CrudRepository<SwiftMt720, SwiftMt720PK>{
	SwiftMt720 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
