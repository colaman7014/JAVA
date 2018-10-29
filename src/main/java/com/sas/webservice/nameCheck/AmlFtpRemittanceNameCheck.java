package com.sas.webservice.nameCheck;

import java.io.File;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sas.multipleNC.bean.NCOnlineErrMsgBean;
import com.sas.webservice.nameCheck.bean.AmlFtpRemittanceNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.AmlFtpRemittanceNameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;

/**
 * AML Ftp Remittance Name Check - 國外匯款
 * 
 * @author Danniel
 *
 */
@WebService
public interface AmlFtpRemittanceNameCheck {
	public AmlFtpRemittanceNameCheckOutputBean doFtpRemittanceNameCheck(AmlFtpRemittanceNameCheckInputBean input);
	public List<NameCheckInputBean> FtpRemittanceNameCheck(File file, String dataFileCharset, NCOnlineErrMsgBean errMsg, String callingUser);
}
