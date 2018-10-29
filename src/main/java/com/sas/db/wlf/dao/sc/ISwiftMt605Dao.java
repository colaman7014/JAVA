package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt605;
import com.sas.db.wlf.orm.sc.SwiftMt605PK;

/**
 * NCSC-SWIFT_MT605(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt605Dao extends CrudRepository<SwiftMt605, SwiftMt605PK>{
	SwiftMt605 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
