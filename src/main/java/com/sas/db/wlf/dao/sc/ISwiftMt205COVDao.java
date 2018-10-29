package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMt205COV;
import com.sas.db.wlf.orm.sc.SwiftMt205COVPK;

public interface ISwiftMt205COVDao extends CrudRepository<SwiftMt205COV, SwiftMt205COVPK>{
	//Terry 新增
	SwiftMt205COV findByIdUniqueKeyAndIdReferenceId(String uniqueKey, int referenceId); 
}
