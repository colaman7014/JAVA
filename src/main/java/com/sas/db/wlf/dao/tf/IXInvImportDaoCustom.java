package com.sas.db.wlf.dao.tf;

import java.util.List;

import com.sas.db.wlf.orm.tf.XInvImport;


/**
 * @author meng
 * date 2017/12/15
 */
public interface IXInvImportDaoCustom {

	// To Query Inv Import Data List By FileKey
	public List<XInvImport> queryInvImportDataByFileKey(String fileKey);
}
