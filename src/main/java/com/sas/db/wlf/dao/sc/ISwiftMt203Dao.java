package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt203;
import com.sas.db.wlf.orm.sc.SwiftMt203PK;

/**
 * NCSC-SWIFT_MT203(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 */
public interface ISwiftMt203Dao extends CrudRepository<SwiftMt203, SwiftMt203PK>{
	SwiftMt203 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
