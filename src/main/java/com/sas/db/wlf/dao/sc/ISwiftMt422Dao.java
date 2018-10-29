package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt422;
import com.sas.db.wlf.orm.sc.SwiftMt422PK;

/**
 * NCSC-SWIFT_MT422(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt422Dao extends CrudRepository<SwiftMt422, SwiftMt422PK>{
	SwiftMt422 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
