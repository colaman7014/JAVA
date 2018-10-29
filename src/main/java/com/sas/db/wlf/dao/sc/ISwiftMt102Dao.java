package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt102;
import com.sas.db.wlf.orm.sc.SwiftMt102PK;

/**
 * NCSC-SWIFT_MT102(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt102Dao extends CrudRepository<SwiftMt102, SwiftMt102PK>{
	//Terry 新增
	SwiftMt102 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
	
}
