package com.sas.webservice.batch.screening;

import javax.jws.WebService;

import com.sas.webservice.batch.screening.bean.BatchSwiftScreeningInputBean;
import com.sas.webservice.batch.screening.bean.BatchSwiftScreeningOutputBean;

/**
 * 夜批掃描 Swift Screening Interface
 * @author SAS
 *
 */
@WebService
public interface BatchSwiftScreening {

	public BatchSwiftScreeningOutputBean batchSwiftScreeningImpl(BatchSwiftScreeningInputBean input);

}
