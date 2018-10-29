package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt795;
import com.sas.db.wlf.orm.sc.SwiftMt795PK;

/**
 * NCSC-SWIFT_MT795(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt795Dao extends CrudRepository<SwiftMt795, SwiftMt795PK>{
	SwiftMt795 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
