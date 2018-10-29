package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt112;
import com.sas.db.wlf.orm.sc.SwiftMt112PK;

/**
 * NCSC-SWIFT_MT112(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt112Dao extends CrudRepository<SwiftMt112, SwiftMt112PK>{
	SwiftMt112 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
