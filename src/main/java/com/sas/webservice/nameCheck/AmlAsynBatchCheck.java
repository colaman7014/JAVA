package com.sas.webservice.nameCheck;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.dao.nc.INameCheckRecordMainDao;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;
import com.sas.util.NameCheckUtil;
import com.sas.webservice.batch.screening.BatchNameCheckingChangeScan;
import com.sas.webservice.batch.screening.BatchNameCheckingFullPartyFullScan;
import com.sas.webservice.createCase.AmlCreateCase;
import com.sas.webservice.createCase.bean.CreateCaseInputBean;

@Async
@Component
@Scope("prototype")
public class AmlAsynBatchCheck {
	private static final Logger logger = LoggerFactory.getLogger(AmlAsynBatchCheck.class);
	private long Count = 0;
	
	@Autowired @Lazy
	BatchNameCheckingChangeScan batchNameCheckingChangeScan;
	
	@Autowired @Lazy
	BatchNameCheckingFullPartyFullScan batchNameCheckingFullPartyFullScan;
	
	@Autowired
	INameCheckRecordMainDao iNameCheckRecordMainDao;
	
	@Autowired
	AmlCreateCase amlCreateCase;
	
	@Async (value = "threadPoolTaskExecutor")
    public Future<String> doAsyncBatchNameCheckingChangeScan(){  
		Count++;
		logger.info("============ 啟動新線程::::: "+ Thread.currentThread().getName() + "===============\n" );
		logger.info("(((((((((((((((((((((( 第幾"+Count+"次))))))))))))))))))))))，時間::::" + System.currentTimeMillis());
		try {
			logger.info("Do batchNameCheckingChangeScan Thread begin" );
			//變數定義
			Timestamp now = new Timestamp(System.currentTimeMillis());
			int countUniqueKey=0;
			String uniqueKey = SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK+new SimpleDateFormat("yyyyMMddHHmmss").format(now);
			String referenceNumber = new SimpleDateFormat("MMddHHmm").format(now);
			String interfaceName = SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK;
			String callingSystem = SwiftMtConst.CALLING_SYSTEM_NON_SWIFT;
			String screenProcess = SwiftMtConst.SCREEN_PROCESS_BATCH_NAME_CHECKING;
			String callingUser = "SYSTEM";
			String genAlertFlag = "Y";

			//Step1  異動客戶掃全名單 ChangePartyFullScan
			batchNameCheckingChangeScan.changePartyFullScan(now, countUniqueKey, uniqueKey, referenceNumber, interfaceName, callingSystem,
					screenProcess, callingUser, genAlertFlag);	
		
			//Step2 全客戶掃異動名單 FullPartyChangeScan
			batchNameCheckingChangeScan.fullPartyChangeScan(now, countUniqueKey, uniqueKey, referenceNumber, interfaceName, callingSystem,
					screenProcess, callingUser, genAlertFlag);
			
			logger.info("Do batchNameCheckingChangeScan Thread end" );
		} catch (Exception ex) {
			return new AsyncResult<>("Fail");
		}
        return new AsyncResult<>("Complete");  
    }  
	
	
	@Async (value = "threadPoolTaskExecutor")
    public Future<String> doAsyncBatchNameCheckingFullPartyFullScan(){  
		Count++;
		logger.info("============ 啟動新線程::::: "+ Thread.currentThread().getName() + "===============\n" );
		logger.info("(((((((((((((((((((((( 第幾"+Count+"次))))))))))))))))))))))，時間::::" + System.currentTimeMillis());
		try {
			logger.info("Do BatchNameCheckingFullPartyFullScan Thread begin" );
			batchNameCheckingFullPartyFullScan.doBatchNameCheckingFullPartyFullScan();
			logger.info("Do BatchNameCheckingFullPartyFullScan Thread end" );
		} catch (Exception ex) {
			return new AsyncResult<>("Fail");
		}
        return new AsyncResult<>("Complete");  
    }  
	
	
	
    @Async (value = "threadPoolTaskExecutor")
	public Future<StringBuilder> createCaseByBatchNameCheckResult(Date fromDate, Date endDate) {
		StringBuilder failMessage = new StringBuilder("");  ;
		try {
			String interfaceName = SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK;
			String ncResultHit = SwiftMtConst.NC_RESULT_HIT;
			String screenProcess = SwiftMtConst.SCREEN_PROCESS_BATCH_NAME_CHECKING;
			SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd");
			endDate.setHours(23);
			endDate.setMinutes(59);
			endDate.setSeconds(59);
			List<NameCheckRecordMain> nameCheckRecordMainList = iNameCheckRecordMainDao
					.findByTransactionDateBetweenAndInterfaceNameAndNcResultAndCaseRkIs(fromDate, endDate, interfaceName,
							ncResultHit, 0);
			
			List<NameCheckRecordMain> newNameCheckRecordMainList = new ArrayList<>();
			failMessage.append("Has "+ newNameCheckRecordMainList.size() +" record.");
			String sourceType = NameCheckUtil.getSourceType(interfaceName, screenProcess);
			for (NameCheckRecordMain nameCheckRecordMain : nameCheckRecordMainList) {
				CreateCaseInputBean createCaseInputBean = new CreateCaseInputBean(String.valueOf(nameCheckRecordMain.getId().getNcReferenceId()), 
						nameCheckRecordMain.getCallingSystem(), nameCheckRecordMain.getCallingUser(), 
						nameCheckRecordMain.getBranchNumber(), nameCheckRecordMain.getRouteRule(), spf.format(nameCheckRecordMain.getTransactionDate()), 
						nameCheckRecordMain.getHitSeq(), nameCheckRecordMain.getPartyNumber(), nameCheckRecordMain.getReferenceNumber(), 
						nameCheckRecordMain.getId().getUniqueKey(), sourceType, false,
						screenProcess);
				
				BigDecimal caseRk = null;
				caseRk = amlCreateCase.createCase(createCaseInputBean);
				nameCheckRecordMain.setNcResult(SwiftMtConst.NC_RESULT_PENDING);
				nameCheckRecordMain.setCaseRk(caseRk.longValue());
				iNameCheckRecordMainDao.save(nameCheckRecordMain);
				newNameCheckRecordMainList.add(nameCheckRecordMain);
			}
			
			if(newNameCheckRecordMainList.size() == nameCheckRecordMainList.size()) {
				failMessage.append(" All Success!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new AsyncResult<>(failMessage);
	}
}
