package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt103;
import com.sas.db.wlf.orm.sc.SwiftMt103PK;

/**
 * NCSC-SWIFT_MT103(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt103Dao extends CrudRepository<SwiftMt103, SwiftMt103PK>{
	//Terry 新增
	SwiftMt103 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
	
}
