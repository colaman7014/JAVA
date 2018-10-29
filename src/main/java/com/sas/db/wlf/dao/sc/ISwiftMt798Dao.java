package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt798;
import com.sas.db.wlf.orm.sc.SwiftMt798PK;

/**
 * NCSC-SWIFT_MT798(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt798Dao extends CrudRepository<SwiftMt798, SwiftMt798PK>{
	SwiftMt798 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
