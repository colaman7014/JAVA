package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt450;
import com.sas.db.wlf.orm.sc.SwiftMt450PK;

/**
 * NCSC-SWIFT_MT410(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt450Dao extends CrudRepository<SwiftMt450, SwiftMt450PK>{
	SwiftMt450 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
