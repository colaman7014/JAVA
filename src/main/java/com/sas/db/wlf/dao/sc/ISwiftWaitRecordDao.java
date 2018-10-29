
package com.sas.db.wlf.dao.sc;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.sc.SwiftWaitRecord;
import com.sas.db.wlf.orm.sc.SwiftWaitRecordPK;

/**
 * NCSC-SWIFT_WAIT_RECORD() DAO
 * @author SAS
 *
 */
public interface ISwiftWaitRecordDao extends CrudRepository<SwiftWaitRecord, SwiftWaitRecordPK>{
	List<SwiftWaitRecord> findByDocumentaryCreditNumber(String documentaryCreditNumber);
	List<SwiftWaitRecord> findByIdUniqueKey(String uniqueKey);
	List<SwiftWaitRecord> findByIdGroupUniqueKey(String groupUniqueKey);
	List<SwiftWaitRecord> findByCheckStatusAndSwiftTypeOrderByCreatedTimestamp(String checkStatus, String swiftType);
}
