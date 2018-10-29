package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt740;
import com.sas.db.wlf.orm.sc.SwiftMt740PK;

/**
 * NCSC-SWIFT_MT740(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt740Dao extends CrudRepository<SwiftMt740, SwiftMt740PK>{
	SwiftMt740 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
