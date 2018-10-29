package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt711;
import com.sas.db.wlf.orm.sc.SwiftMt711PK;

/**
 * NCSC-SWIFT_MT711(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt711Dao extends CrudRepository<SwiftMt711, SwiftMt711PK>{
	SwiftMt711 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
