package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt701;
import com.sas.db.wlf.orm.sc.SwiftMt701PK;

/**
 * NCSC-SWIFT_MT701(SWIFT Check 電文記錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftMt701Dao extends CrudRepository<SwiftMt701, SwiftMt701PK>{
	SwiftMt701 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
