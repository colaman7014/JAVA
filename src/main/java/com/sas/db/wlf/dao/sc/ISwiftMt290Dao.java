package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt290;
import com.sas.db.wlf.orm.sc.SwiftMt290PK;

/**
 * NCSC-SWIFT_MT290(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt290Dao extends CrudRepository<SwiftMt290, SwiftMt290PK>{
	SwiftMt290 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
