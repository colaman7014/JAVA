package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt598;
import com.sas.db.wlf.orm.sc.SwiftMt598PK;

/**
 * NCSC-SWIFT_MT598(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt598Dao extends CrudRepository<SwiftMt598, SwiftMt598PK>{
	SwiftMt598 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
