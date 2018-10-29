package com.sas.webservice.nameCheck;

import java.io.File;
import java.util.List;

import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputBean;

/**
 * AML Name Check Interface
 * @author SAS
 *
 */
@WebService()
public interface AmlNameCheck {

//	public List<NameCheckInputBean> FtpNameCheck(File file);

	public NameCheckOutputBean NameCheck(NameCheckInputBean input);
	
	public NameCheckOutputBean NonTransactionNameCheck(NameCheckInputBean input, long id);

}
