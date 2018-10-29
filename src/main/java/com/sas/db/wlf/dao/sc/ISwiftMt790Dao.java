package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt790;
import com.sas.db.wlf.orm.sc.SwiftMt790PK;

/**
 * NCSC-SWIFT_MT790(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt790Dao extends CrudRepository<SwiftMt790, SwiftMt790PK>{
	SwiftMt790 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
