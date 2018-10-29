package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt207;
import com.sas.db.wlf.orm.sc.SwiftMt207PK;

/**
 * NCSC-SWIFT_MT207(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt207Dao extends CrudRepository<SwiftMt207, SwiftMt207PK>{
	SwiftMt207 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
