package com.sas.db.wlf.dao.tf;

import java.util.List;

import com.sas.db.wlf.orm.tf.XBlImport;

/**
 * @author meng
 * date 2017/12/15
 */
public interface IXBlImportDaoCustom {

	// To Query B/L Import Data List By FileKey;
	public List<XBlImport> queryBlImportDataByFileKey(String fileKey);
}
