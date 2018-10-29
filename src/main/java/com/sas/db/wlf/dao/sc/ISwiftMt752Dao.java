package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt752;
import com.sas.db.wlf.orm.sc.SwiftMt752PK;

/**
 * NCSC-SWIFT_MT752(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt752Dao extends CrudRepository<SwiftMt752, SwiftMt752PK>{
	SwiftMt752 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
