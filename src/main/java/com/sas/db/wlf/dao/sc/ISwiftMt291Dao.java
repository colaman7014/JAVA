package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt291;
import com.sas.db.wlf.orm.sc.SwiftMt291PK;

/**
 * NCSC-SWIFT_MT291(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt291Dao extends CrudRepository<SwiftMt291, SwiftMt291PK>{
	SwiftMt291 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
