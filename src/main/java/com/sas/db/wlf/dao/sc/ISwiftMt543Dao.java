package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt543;
import com.sas.db.wlf.orm.sc.SwiftMt543PK;

/**
 * NCSC-SWIFT_MT543(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt543Dao extends CrudRepository<SwiftMt543, SwiftMt543PK>{
	SwiftMt543 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
