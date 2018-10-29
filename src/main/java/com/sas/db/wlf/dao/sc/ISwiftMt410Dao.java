package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt410;
import com.sas.db.wlf.orm.sc.SwiftMt410PK;

/**
 * NCSC-SWIFT_MT410(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt410Dao extends CrudRepository<SwiftMt410, SwiftMt410PK>{
	SwiftMt410 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
