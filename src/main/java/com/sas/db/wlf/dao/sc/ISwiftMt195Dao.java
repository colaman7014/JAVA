package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt195;
import com.sas.db.wlf.orm.sc.SwiftMt195PK;

/**
 * NCSC-SWIFT_MT195(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt195Dao extends CrudRepository<SwiftMt195, SwiftMt195PK>{
	SwiftMt195 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
