package com.sas.db.wlf.dao.sc;


import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftCheckRecord;
import com.sas.db.wlf.orm.sc.SwiftCheckRecordPK;
/**
 * NCSC-SWIFT_CHECK_RECORD(SWIFT掃描紀錄檔) DAO
 * @author SAS
 *
 */
public interface ISwiftCheckRecordDao extends CrudRepository<SwiftCheckRecord, SwiftCheckRecordPK>{
	List<SwiftCheckRecord> findByIdUniqueKeyAndCallingSystemAndTransactionDateOrderByIdReferenceIdDesc(String uniqueKey, String callingSystem, Date transactionDate);
	//Terry新增
	List<SwiftCheckRecord> findByScreenProcessAndTransactionDateGreaterThanEqual(String ScreenProcess, Date transactionDate);

	List<SwiftCheckRecord> findByCaseRk(long caseRk);

	List<SwiftCheckRecord> findByIdUniqueKeyAndCallingSystem(String uniqueKey, String callingSystem);
}
