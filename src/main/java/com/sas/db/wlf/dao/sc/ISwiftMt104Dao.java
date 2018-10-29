package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt104;
import com.sas.db.wlf.orm.sc.SwiftMt104PK;

/**
 * NCSC-SWIFT_MT104(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt104Dao extends CrudRepository<SwiftMt104, SwiftMt104PK>{
	//Terry 新增
	SwiftMt104 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
	
}
