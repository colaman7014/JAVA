package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt600;
import com.sas.db.wlf.orm.sc.SwiftMt600PK;

/**
 * NCSC-SWIFT_MT600(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt600Dao extends CrudRepository<SwiftMt600, SwiftMt600PK>{
	SwiftMt600 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
