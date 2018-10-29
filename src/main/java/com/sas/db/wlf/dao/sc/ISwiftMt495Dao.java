package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt495;
import com.sas.db.wlf.orm.sc.SwiftMt495PK;

/**
 * NCSC-SWIFT_MT495(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt495Dao extends CrudRepository<SwiftMt495, SwiftMt495PK>{
	SwiftMt495 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
