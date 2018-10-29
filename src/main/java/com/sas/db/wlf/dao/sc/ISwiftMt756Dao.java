package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt754;
import com.sas.db.wlf.orm.sc.SwiftMt756;
import com.sas.db.wlf.orm.sc.SwiftMt756PK;

/**
 * NCSC-SWIFT_MT754(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt756Dao extends CrudRepository<SwiftMt756, SwiftMt756PK>{
	SwiftMt754 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
