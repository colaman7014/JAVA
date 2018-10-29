package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt707;
import com.sas.db.wlf.orm.sc.SwiftMt707PK;

/**
 * NCSC-SWIFT_MT707(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt707Dao extends CrudRepository<SwiftMt707, SwiftMt707PK>{
	SwiftMt707 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
