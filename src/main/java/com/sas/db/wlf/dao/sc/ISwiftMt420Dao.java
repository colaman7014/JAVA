package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt420;
import com.sas.db.wlf.orm.sc.SwiftMt420PK;

/**
 * NCSC-SWIFT_MT410(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt420Dao extends CrudRepository<SwiftMt420, SwiftMt420PK>{
	SwiftMt420 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
