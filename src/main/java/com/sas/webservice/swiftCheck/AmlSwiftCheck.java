package com.sas.webservice.swiftCheck;

import javax.jws.WebService;
import javax.transaction.Transactional;

import com.sas.webservice.swiftCheck.bean.SwiftCheckInputBean;
import com.sas.webservice.swiftCheck.bean.SwiftCheckOutputBean;

/**
 * AML SWIFT CHECK Interface
 * 
 * @author SAS
 *
 */
@WebService
public interface AmlSwiftCheck {
	public SwiftCheckOutputBean SwiftCheck(SwiftCheckInputBean input);
	
	@Transactional
	public SwiftCheckOutputBean SwiftAsyncCheck(SwiftCheckInputBean input, long id);
}
