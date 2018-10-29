package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt760;
import com.sas.db.wlf.orm.sc.SwiftMt760PK;

/**
 * NCSC-SWIFT_MT760(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt760Dao extends CrudRepository<SwiftMt760, SwiftMt760PK>{
	SwiftMt760 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
