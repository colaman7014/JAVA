package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt700;
import com.sas.db.wlf.orm.sc.SwiftMt700PK;

/**
 * NCSC-SWIFT_MT700(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt700Dao extends CrudRepository<SwiftMt700, SwiftMt700PK>{
	SwiftMt700 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
