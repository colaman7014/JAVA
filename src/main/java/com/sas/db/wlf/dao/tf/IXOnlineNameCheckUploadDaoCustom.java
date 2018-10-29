package com.sas.db.wlf.dao.tf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.tf.XBlExport;
import com.sas.db.wlf.orm.tf.XOnlineNamecheckUpload;
import com.sas.db.wlf.orm.tf.XOnlineNamecheckUploadPK;

/**
 * NCSC-X_ONLINE_NAMECHECK_UPLOAD(ONLINE_UPLOAD) DAO
 * @author SAS
 *
 */
public interface IXOnlineNameCheckUploadDaoCustom{
	public List<XOnlineNamecheckUpload> queryOnlineMulitDataByFileKey(String fileKey);
}
