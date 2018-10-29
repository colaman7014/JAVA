package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt190;
import com.sas.db.wlf.orm.sc.SwiftMt190PK;

/**
 * NCSC-SWIFT_MT190(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt190Dao extends CrudRepository<SwiftMt190, SwiftMt190PK>{
	SwiftMt190 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
