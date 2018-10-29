package com.sas.db.wlf.dao.nc;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.nc.NameCheckRecordMain;
import com.sas.db.wlf.orm.nc.NameCheckRecordMainPK;

/**
 * NCSC-NAME_CHECK_RECORD_MAIN(名單查詢紀錄主檔) DAO
 * @author SAS
 *
 */
public interface INameCheckRecordMainDao extends CrudRepository<NameCheckRecordMain, NameCheckRecordMainPK>, iNameCheckRecordMainDaoCoustom{
	NameCheckRecordMain findOneByReferenceNumberAndTransactionDateOrderByTransactionDateDesc(int referenceNumber, Date transactionDate);
	NameCheckRecordMain findByIdUniqueKeyAndCallingSystem(String uniqueKey, String callingSystem);
	List<NameCheckRecordMain> findByIdUniqueKeyAndScreenProcessOrderByIdNcReferenceIdDesc(String uniqueKey, String screenProcess);
	//Terry新增
	List<NameCheckRecordMain> findByScreenProcessInAndTransactionDateGreaterThanEqual(List<String> ScreenProcess, Date transactionDate);

	//Terry新增，FullPartyFullScan使用
	List<NameCheckRecordMain> findByInterfaceNameAndScreenProcessAndTransactionDateGreaterThanEqual(String interfaceName, String ScreenProcess, Date transactionDate);
	
	//Terry新增，FullPartyFullScan使用
	List<NameCheckRecordMain> findByInterfaceNameAndScreenProcessAndReferenceNumber(String interfaceName, String ScreenProcess, String referenceNumber);
	
	//Terry新增，撈取當日的
	List<NameCheckRecordMain> findByInterfaceNameAndScreenProcessAndTransactionDateGreaterThanEqualAndNcResult(String interfaceName, String ScreenProcess, Date transactionDate, String ncResult);

	//Terry新增，撈取當日之前的
	List<NameCheckRecordMain> findByInterfaceNameAndScreenProcessAndTransactionDateLessThanAndNcResult(String interfaceName, String ScreenProcess, Date transactionDate, String ncResult);

	//Terry新增，找出當日結案的
	List<NameCheckRecordMain> findByInterfaceNameAndScreenProcessAndCaseRkIn(String interfaceName, String ScreenProcess, List<Long> caseRk);
	
	
	List<NameCheckRecordMain> findByCaseRk(long caseRk);
}
