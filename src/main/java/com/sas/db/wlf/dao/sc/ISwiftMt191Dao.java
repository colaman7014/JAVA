package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt191;
import com.sas.db.wlf.orm.sc.SwiftMt191PK;

/**
 * NCSC-SWIFT_MT191(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt191Dao extends CrudRepository<SwiftMt191, SwiftMt191PK>{
	SwiftMt191 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
