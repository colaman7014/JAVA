package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt200;
import com.sas.db.wlf.orm.sc.SwiftMt200PK;

/**
 * NCSC-SWIFT_MT200(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt200Dao extends CrudRepository<SwiftMt200, SwiftMt200PK>{
	SwiftMt200 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
