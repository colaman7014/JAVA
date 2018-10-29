package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt107;
import com.sas.db.wlf.orm.sc.SwiftMt107PK;

/**
 * NCSC-SWIFT_MT107(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt107Dao extends CrudRepository<SwiftMt107, SwiftMt107PK>{
	//Terry 新增
	SwiftMt107 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
	
}
