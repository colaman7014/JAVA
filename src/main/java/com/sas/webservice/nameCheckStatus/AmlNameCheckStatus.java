package com.sas.webservice.nameCheckStatus;

import javax.jws.WebService;

import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusInputBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusOutputBean;

/**
 * AML Name Check Status Interface
 * @author SAS
 *
 */
@WebService
public interface AmlNameCheckStatus {
	
	public NameCheckStatusOutputBean NameCheckStatus(NameCheckStatusInputBean input);
}
