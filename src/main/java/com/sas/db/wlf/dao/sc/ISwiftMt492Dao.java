package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt492;
import com.sas.db.wlf.orm.sc.SwiftMt492PK;

/**
 * NCSC-SWIFT_MT492(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt492Dao extends CrudRepository<SwiftMt492, SwiftMt492PK>{
	SwiftMt492 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
