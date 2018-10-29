package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt732;
import com.sas.db.wlf.orm.sc.SwiftMt734;
import com.sas.db.wlf.orm.sc.SwiftMt734PK;

/**
 * NCSC-SWIFT_MT732(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt734Dao extends CrudRepository<SwiftMt734, SwiftMt734PK>{
	SwiftMt732 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
