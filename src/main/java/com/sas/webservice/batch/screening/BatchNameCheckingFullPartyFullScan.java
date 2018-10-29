package com.sas.webservice.batch.screening;

import javax.jws.WebService;

import com.sas.webservice.batch.screening.bean.BatchNameCheckingInputBean;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingOutputBean;
/**
 * 夜批掃描 Name Checking Interface
 * @author SAS
 *
 */
@WebService
public interface BatchNameCheckingFullPartyFullScan {

	public BatchNameCheckingOutputBean batchNameCheckingFullPartyFullScanImpl(BatchNameCheckingInputBean input);

	public void doBatchNameCheckingFullPartyFullScan();
	
}
