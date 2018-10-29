package com.sas.webservice.nameCheck;

import java.io.File;
import java.util.List;

import javax.jws.WebService;

import com.sas.multipleNC.bean.NCOnlineErrMsgBean;
import com.sas.webservice.nameCheck.bean.AmlFtpNameCheckInputBean;
import com.sas.webservice.nameCheck.bean.AmlFtpNameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;

/**
 * AML Ftp Name Check 標準格式
 * 
 * @author Eric Su
 *
 */
@WebService
public interface AmlFtpNameCheck {
	public AmlFtpNameCheckOutputBean doFtpNameCheck(AmlFtpNameCheckInputBean input);
	public List<NameCheckInputBean> FtpNameCheck(File file, NCOnlineErrMsgBean errMsg, String dataFileCharset);
}
