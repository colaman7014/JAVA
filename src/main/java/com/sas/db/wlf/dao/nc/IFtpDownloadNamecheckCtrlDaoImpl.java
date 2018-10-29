package com.sas.db.wlf.dao.nc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sas.db.wlf.orm.nc.FtpDownloadNamecheckCtrl;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;

/**
 * FtpDownloadNamecheckCtrl (Ftp Name Check 紀錄檔) Custom DAO 方法實作
 * 
 * @author Eric Su
 *
 */
public class IFtpDownloadNamecheckCtrlDaoImpl implements IFtpDownloadNamecheckCtrlDaoCustom {

	@PersistenceContext
	private EntityManager entityManager;

	private final int batchSize = 20;

	@Override
	@Transactional
	public int batchSave(List<FtpDownloadNamecheckCtrl> ftpDownloadNamecheckCtrlList) {
		int i = 0;
		for (FtpDownloadNamecheckCtrl ftpDownloadNamecheckCtrl : ftpDownloadNamecheckCtrlList) {
			if (ftpDownloadNamecheckCtrl.getSeq() != null && entityManager.find(NameCheckRecordDetail.class, ftpDownloadNamecheckCtrl.getSeq()) != null) {
				entityManager.merge(ftpDownloadNamecheckCtrl);
			} else {
				entityManager.persist(ftpDownloadNamecheckCtrl);
			}
			i++;
			if (i % batchSize == 0 || i == ftpDownloadNamecheckCtrlList.size()) {
				// Flush a batch of inserts and release memory.
				entityManager.flush();
				entityManager.clear();
			}
		}

		return i;
	}
}
