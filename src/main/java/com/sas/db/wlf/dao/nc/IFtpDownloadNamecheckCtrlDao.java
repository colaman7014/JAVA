package com.sas.db.wlf.dao.nc;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.nc.FtpDownloadNamecheckCtrl;

/**
 * FtpDownloadNamecheckCtrl (Ftp Name Check 紀錄檔) DAO 方法
 * 
 * @author Eric Su
 *
 */
public interface IFtpDownloadNamecheckCtrlDao extends CrudRepository<FtpDownloadNamecheckCtrl, Integer>, IFtpDownloadNamecheckCtrlDaoCustom {
}
