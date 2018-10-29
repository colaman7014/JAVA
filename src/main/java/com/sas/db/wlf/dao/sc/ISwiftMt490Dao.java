package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt490;
import com.sas.db.wlf.orm.sc.SwiftMt490PK;

/**
 * NCSC-SWIFT_MT490(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt490Dao extends CrudRepository<SwiftMt490, SwiftMt490PK>{
	SwiftMt490 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
