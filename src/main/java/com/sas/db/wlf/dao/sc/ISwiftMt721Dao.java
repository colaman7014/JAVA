package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt721;
import com.sas.db.wlf.orm.sc.SwiftMt721PK;

/**
 * NCSC-SWIFT_MT721(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt721Dao extends CrudRepository<SwiftMt721, SwiftMt721PK>{
	SwiftMt721 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
