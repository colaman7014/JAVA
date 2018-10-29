package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt699;
import com.sas.db.wlf.orm.sc.SwiftMt699PK;

/**
 * NCSC-SWIFT_MT699(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt699Dao extends CrudRepository<SwiftMt699, SwiftMt699PK>{
	SwiftMt699 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
 