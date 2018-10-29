package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt601;
import com.sas.db.wlf.orm.sc.SwiftMt601PK;

/**
 * NCSC-SWIFT_MT601(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt601Dao extends CrudRepository<SwiftMt601, SwiftMt601PK>{
	SwiftMt601 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
