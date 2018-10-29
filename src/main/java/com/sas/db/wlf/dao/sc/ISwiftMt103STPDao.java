package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt103STP;
import com.sas.db.wlf.orm.sc.SwiftMt103STPPK;

/**
 * NCSC-SWIFT_MT103(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt103STPDao extends CrudRepository<SwiftMt103STP, SwiftMt103STPPK>{
	//Terry 新增
	SwiftMt103STP findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
	
}
