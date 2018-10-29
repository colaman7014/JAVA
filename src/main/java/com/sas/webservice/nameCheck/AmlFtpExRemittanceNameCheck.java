package com.sas.webservice.nameCheck;

import java.io.File;
import java.util.List;

import javax.jws.WebService;

import com.sas.multipleNC.bean.NCOnlineErrMsgBean;
import com.sas.webservice.nameCheck.bean.AmlFtpExRemittanceNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.AmlFtpExRemittanceNameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;

/**
 * AML Ftp Remittance Name Check
 * 
 * @author Danniel
 *
 */
@WebService
public interface AmlFtpExRemittanceNameCheck {
	public AmlFtpExRemittanceNameCheckOutputBean doFtpExRemittanceNameCheck(AmlFtpExRemittanceNameCheckInputBean input);
	public List<NameCheckInputBean> FtpExRemittanceNameCheck(File file, String dataFileCharset, NCOnlineErrMsgBean errMsg, String branchNumber, String callingUser);
}
