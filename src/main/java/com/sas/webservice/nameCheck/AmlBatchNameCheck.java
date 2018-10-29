package com.sas.webservice.nameCheck;

import javax.jws.WebService;

import com.sas.webservice.nameCheck.bean.BatchNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.BatchNameCheckOutputBean;

/**
 * AML Name Check Interface
 * @author SAS
 *
 */
@WebService
public interface AmlBatchNameCheck {
	
	public BatchNameCheckOutputBean NameCheck(BatchNameCheckInputBean input);
	
	public BatchNameCheckOutputBean NonTransactionNameCheck(BatchNameCheckInputBean input);

}
