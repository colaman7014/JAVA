package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt412;
import com.sas.db.wlf.orm.sc.SwiftMt412PK;

/**
 * NCSC-SWIFT_MT410(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt412Dao extends CrudRepository<SwiftMt412, SwiftMt412PK>{
	SwiftMt412 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
