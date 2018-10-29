package com.sas.webservice.batch.screening;

import java.sql.Timestamp;

import javax.jws.WebService;

import com.sas.webservice.batch.screening.bean.BatchNameCheckingInputBean;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingOutputBean;
/**
 * 夜批掃描 Name Checking Interface(異動客戶掃全名單 & 全客戶掃異動名單)
 * @author SAS
 *
 */
@WebService
public interface BatchNameCheckingChangeScan {

	public BatchNameCheckingOutputBean batchNameCheckingChangeScanImpl(BatchNameCheckingInputBean input);

	public void changePartyFullScan(Timestamp now, int countUniqueKey, String uniqueKey, String referenceNumber,
			String interfaceName, String callingSystem, String screenProcess, String callingUser, String genAlertFlag);

	public void fullPartyChangeScan(Timestamp now, int countUniqueKey, String uniqueKey, String referenceNumber,
			String interfaceName, String callingSystem, String screenProcess, String callingUser, String genAlertFlag);
}
