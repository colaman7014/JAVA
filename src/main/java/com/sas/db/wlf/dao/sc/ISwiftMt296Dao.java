package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt296;
import com.sas.db.wlf.orm.sc.SwiftMt296PK;

/**
 * NCSC-SWIFT_MT296(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt296Dao extends CrudRepository<SwiftMt296, SwiftMt296PK>{
	SwiftMt296 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
