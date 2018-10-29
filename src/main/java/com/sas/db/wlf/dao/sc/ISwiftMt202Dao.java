package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt202;
import com.sas.db.wlf.orm.sc.SwiftMt202PK;

public interface ISwiftMt202Dao extends CrudRepository<SwiftMt202, SwiftMt202PK>{
	//Terry 新增
	SwiftMt202 findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
