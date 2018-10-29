package com.sas.db.wlf.dao.nc;

import java.util.List;

import com.sas.db.wlf.orm.nc.FtpDownloadNamecheckCtrl;

/**
 * FtpDownloadNamecheckCtrl (Ftp Name Check 紀錄檔) Custom DAO 方法
 * 
 * @author Eric Su
 *
 */
public interface IFtpDownloadNamecheckCtrlDaoCustom {
	public int batchSave(List<FtpDownloadNamecheckCtrl> ftpDownloadNamecheckCtrlList);
}
