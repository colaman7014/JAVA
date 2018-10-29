package com.sas.webservice.batch.screening;

import javax.jws.WebService;

import com.sas.webservice.batch.screening.bean.BatchNameCheckingResultInputBean;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingResultOutputBean;
@WebService
public interface BatchNameCheckingResult {
	public BatchNameCheckingResultOutputBean batchNameCheckingResultImpl(BatchNameCheckingResultInputBean input);	
}
