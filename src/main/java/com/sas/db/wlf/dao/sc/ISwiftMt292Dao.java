package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt292;
import com.sas.db.wlf.orm.sc.SwiftMt292PK;

/**
 * NCSC-SWIFT_MT292(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt292Dao extends CrudRepository<SwiftMt292, SwiftMt292PK>{
	SwiftMt292 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
