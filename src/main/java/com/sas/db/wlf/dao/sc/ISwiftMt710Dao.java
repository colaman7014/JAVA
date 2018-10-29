package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt710;
import com.sas.db.wlf.orm.sc.SwiftMt710PK;

/**
 * NCSC-SWIFT_MT710(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt710Dao extends CrudRepository<SwiftMt710, SwiftMt710PK>{
	SwiftMt710 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
