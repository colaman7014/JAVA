package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt540;
import com.sas.db.wlf.orm.sc.SwiftMt540PK;

/**
 * NCSC-SWIFT_MT540(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt540Dao extends CrudRepository<SwiftMt540, SwiftMt540PK>{
	SwiftMt540 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
