package com.sas.db.wlf.dao.tf;

import java.util.List;

import com.sas.db.wlf.orm.tf.XInvExportCaseItem;

public interface IXInvExportCaseItemDaoCustom {

	/**
	 * 批次新增
	 * @param exportItems
	 */
	public void batchSave(List<XInvExportCaseItem> exportItems);
}
