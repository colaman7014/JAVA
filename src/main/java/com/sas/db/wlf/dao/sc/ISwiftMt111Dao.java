package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt111;
import com.sas.db.wlf.orm.sc.SwiftMt111PK;

/**
 * NCSC-SWIFT_MT111(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt111Dao extends CrudRepository<SwiftMt111, SwiftMt111PK>{
	SwiftMt111 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
