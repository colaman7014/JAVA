package com.sas.webservice.nameCheck;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sas.webservice.nameCheck.bean.BatchNameCheckInputBean;



public class AMLBatchNameCheckThread implements Callable<Integer> {
	private static final Logger logger = LoggerFactory.getLogger(AMLBatchNameCheckThread.class);


	AmlBatchNameCheck amlBatchNameCheck;
	
	public BatchNameCheckInputBean batchNameCheckInputBean;

	private Integer number;

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		amlBatchNameCheck.NameCheck(this.batchNameCheckInputBean);
		return number;
	}

	public void setNameCheckInputBean(BatchNameCheckInputBean batchNameCheckInputBean) {
		this.batchNameCheckInputBean = batchNameCheckInputBean;
	}

	public AmlBatchNameCheck getAmlNameCheck() {
		return amlBatchNameCheck;
	}

	public void setAmlBatchNameCheck(AmlBatchNameCheck amlBatchlNameCheck) {
		this.amlBatchNameCheck = amlBatchlNameCheck;
	}
	
	
}


