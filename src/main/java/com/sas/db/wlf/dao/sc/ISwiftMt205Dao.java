package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt205;
import com.sas.db.wlf.orm.sc.SwiftMt205PK;

/**
 * NCSC-SWIFT_MT205(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt205Dao extends CrudRepository<SwiftMt205, SwiftMt205PK>{
	SwiftMt205 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
