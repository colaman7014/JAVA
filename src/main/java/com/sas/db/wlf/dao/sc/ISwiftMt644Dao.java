package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt644;
import com.sas.db.wlf.orm.sc.SwiftMt644PK;

/**
 * NCSC-SWIFT_MT644(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt644Dao extends CrudRepository<SwiftMt644, SwiftMt644PK>{
	SwiftMt644 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
