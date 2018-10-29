package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt649;
import com.sas.db.wlf.orm.sc.SwiftMt649PK;

/**
 * NCSC-SWIFT_MT649(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt649Dao extends CrudRepository<SwiftMt649, SwiftMt649PK>{
	SwiftMt649 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
