package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt646;
import com.sas.db.wlf.orm.sc.SwiftMt646PK;

/**
 * NCSC-SWIFT_MT646(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt646Dao extends CrudRepository<SwiftMt646, SwiftMt646PK>{
	SwiftMt646 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
