package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt695;
import com.sas.db.wlf.orm.sc.SwiftMt695PK;

/**
 * NCSC-SWIFT_MT695(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt695Dao extends CrudRepository<SwiftMt695, SwiftMt695PK>{
	SwiftMt695 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
