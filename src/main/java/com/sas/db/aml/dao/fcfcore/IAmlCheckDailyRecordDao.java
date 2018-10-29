package com.sas.db.aml.dao.fcfcore;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.sas.db.aml.orm.fcfcore.AmlCheckDailyRecord;

public interface IAmlCheckDailyRecordDao extends CrudRepository<AmlCheckDailyRecord, Integer>{
	List<AmlCheckDailyRecord> findByUniqueKey(String uniqueKey);
	List<AmlCheckDailyRecord> findByUniqueKeyAndSystemTypeAndCheckDept(String uniqueKey, String systemType, String checkDept);
}