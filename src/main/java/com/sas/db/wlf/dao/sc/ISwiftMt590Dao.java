package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt590;
import com.sas.db.wlf.orm.sc.SwiftMt590PK;

/**
 * NCSC-SWIFT_MT590(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt590Dao extends CrudRepository<SwiftMt590, SwiftMt590PK>{
	SwiftMt590 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
