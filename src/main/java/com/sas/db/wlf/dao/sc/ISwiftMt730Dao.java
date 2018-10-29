package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt730;
import com.sas.db.wlf.orm.sc.SwiftMt730PK;

/**
 * NCSC-SWIFT_MT730(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt730Dao extends CrudRepository<SwiftMt730, SwiftMt730PK>{
	SwiftMt730 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
