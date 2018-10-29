package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt103REMIT;
import com.sas.db.wlf.orm.sc.SwiftMt103REMITPK;

/**
 * NCSC-SWIFT_MT103Remit(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt103REMITDao extends CrudRepository<SwiftMt103REMIT, SwiftMt103REMITPK>{
	//Terry 新增
	SwiftMt103REMIT findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
	
}
