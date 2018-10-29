package com.sas.db.wlf.dao.sc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftMainConfig;

/**
 * NCSC-SWIFT_MAIN_CONFIG(SWIFT主要參數控制檔) Custom DAO
 * @author SAS
 *
 */
public interface ISwiftMainConfigDao extends CrudRepository<SwiftMainConfig, String>{
		
}
