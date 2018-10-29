package com.sas.webservice.swiftCheckStatus;

import javax.jws.WebService;

import com.sas.webservice.swiftCheckStatus.bean.SwiftCheckStatusInputBean;
import com.sas.webservice.swiftCheckStatus.bean.SwiftCheckStatusOutputBean;

/**
 * AML SWIFT CHECK Status Interface
 * @author SAS
 *
 */
@WebService
public interface AmlSwiftCheckStatus {
	
	public SwiftCheckStatusOutputBean SwiftCheckStatus(SwiftCheckStatusInputBean input);
}
