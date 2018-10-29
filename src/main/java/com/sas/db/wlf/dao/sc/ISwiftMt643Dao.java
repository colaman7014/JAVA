package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt643;
import com.sas.db.wlf.orm.sc.SwiftMt643PK;

/**
 * NCSC-SWIFT_MT643(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt643Dao extends CrudRepository<SwiftMt643, SwiftMt643PK>{
	SwiftMt643 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
