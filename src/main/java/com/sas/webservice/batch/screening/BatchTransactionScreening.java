package com.sas.webservice.batch.screening;

import javax.jws.WebService;

import com.sas.webservice.batch.screening.bean.BatchTransactionScreeningInputBean;
import com.sas.webservice.batch.screening.bean.BatchTransactionScreeningOutputBean;

/**
 * 夜批掃描 Transaction Screening Interface
 * @author SAS
 *
 */
@WebService
public interface BatchTransactionScreening {

	public BatchTransactionScreeningOutputBean batchTransactionScreeningImpl(BatchTransactionScreeningInputBean input);

}
