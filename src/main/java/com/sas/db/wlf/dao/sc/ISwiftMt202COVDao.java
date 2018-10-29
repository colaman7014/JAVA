package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt202COV;
import com.sas.db.wlf.orm.sc.SwiftMt202COVPK;

public interface ISwiftMt202COVDao extends CrudRepository<SwiftMt202COV, SwiftMt202COVPK>{
	//Terry 新增
	SwiftMt202COV findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
