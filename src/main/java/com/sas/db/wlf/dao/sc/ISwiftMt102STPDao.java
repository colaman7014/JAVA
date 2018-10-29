package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt102STP;
import com.sas.db.wlf.orm.sc.SwiftMt102STPPK;

/**
 * NCSC-SWIFT_MT102STP(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt102STPDao extends CrudRepository<SwiftMt102STP, SwiftMt102STPPK>{
	//Terry 新增
	SwiftMt102STP findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
	
}
