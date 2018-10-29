package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt599;
import com.sas.db.wlf.orm.sc.SwiftMt599PK;

/**
 * NCSC-SWIFT_MT599(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt599Dao extends CrudRepository<SwiftMt599, SwiftMt599PK>{
	SwiftMt599 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
