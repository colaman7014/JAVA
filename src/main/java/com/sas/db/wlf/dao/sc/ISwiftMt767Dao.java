package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt767;
import com.sas.db.wlf.orm.sc.SwiftMt767PK;

/**
 * NCSC-SWIFT_MT767(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt767Dao extends CrudRepository<SwiftMt767, SwiftMt767PK>{
	SwiftMt767 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
