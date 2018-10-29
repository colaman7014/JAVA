package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt298;
import com.sas.db.wlf.orm.sc.SwiftMt298PK;

/**
 * NCSC-SWIFT_MT298(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt298Dao extends CrudRepository<SwiftMt298, SwiftMt298PK>{
	SwiftMt298 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
