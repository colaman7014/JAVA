package com.sas.db.wlf.dao.sc;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftConfig;
import com.sas.db.wlf.orm.sc.SwiftConfigPK;

/**
 * NCSC-SWIFT_CONFIG(SWIFT參數設定檔) DAO
 * @author SAS
 *
 */
public interface ISwiftConfigDao extends CrudRepository<SwiftConfig, SwiftConfigPK>{
	List<SwiftConfig> findByIdSwiftType(String swiftType);
	List<SwiftConfig> findByFieldName(String fieldName);
	//20171206 Wen added.
	List<SwiftConfig> findByFieldNameAndIdSwiftTypeIgnoreCase(String fieldName, String swiftType);
}
