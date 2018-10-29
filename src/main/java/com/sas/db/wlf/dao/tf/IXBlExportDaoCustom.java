package com.sas.db.wlf.dao.tf;

import java.util.List;

import com.sas.db.wlf.orm.tf.XBlExport;

/**
 * @author meng
 * date 2017/12/15
 */
public interface IXBlExportDaoCustom {

	// To Query Bl Export Data By File Key
	public List<XBlExport> queryBlExportDataByFileKey(String fileKey);
}
