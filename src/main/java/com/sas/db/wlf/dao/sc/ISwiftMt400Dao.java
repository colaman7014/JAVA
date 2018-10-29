package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt399;
import com.sas.db.wlf.orm.sc.SwiftMt400;
import com.sas.db.wlf.orm.sc.SwiftMt400PK;

/**
 * NCSC-SWIFT_MT399(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt400Dao extends CrudRepository<SwiftMt400, SwiftMt400PK>{
	SwiftMt400 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
