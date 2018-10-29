package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt768;
import com.sas.db.wlf.orm.sc.SwiftMt768PK;

/**
 * NCSC-SWIFT_MT768(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt768Dao extends CrudRepository<SwiftMt768, SwiftMt768PK>{
	SwiftMt768 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
