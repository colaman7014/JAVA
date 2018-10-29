package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt620;
import com.sas.db.wlf.orm.sc.SwiftMt620PK;

/**
 * NCSC-SWIFT_MT620(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt620Dao extends CrudRepository<SwiftMt620, SwiftMt620PK>{
	SwiftMt620 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
