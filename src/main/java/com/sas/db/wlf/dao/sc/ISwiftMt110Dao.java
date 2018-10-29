package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt110;
import com.sas.db.wlf.orm.sc.SwiftMt110PK;

/**
 * NCSC-SWIFT_MT110(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt110Dao extends CrudRepository<SwiftMt110, SwiftMt110PK>{
	SwiftMt110 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
