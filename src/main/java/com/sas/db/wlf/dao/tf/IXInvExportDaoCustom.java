package com.sas.db.wlf.dao.tf;

import java.util.List;

import com.sas.db.wlf.orm.tf.XInvExport;

/**
 * @author meng
 * date 2017/12/15
 */
public interface IXInvExportDaoCustom {

	// To Query Inv Data List By File Key
	public List<XInvExport> queryInvExportDataByFileKey(String fileKey);
}
