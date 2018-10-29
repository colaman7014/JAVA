package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt101;
import com.sas.db.wlf.orm.sc.SwiftMt101PK;

/**
 * NCSC-SWIFT_MT101(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt101Dao extends CrudRepository<SwiftMt101, SwiftMt101PK>{
	//Terry 新增
	SwiftMt101 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
	
}
