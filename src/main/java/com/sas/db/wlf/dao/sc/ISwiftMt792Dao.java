package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt792;
import com.sas.db.wlf.orm.sc.SwiftMt792PK;

/**
 * NCSC-SWIFT_MT792(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt792Dao extends CrudRepository<SwiftMt792, SwiftMt792PK>{
	SwiftMt792 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
