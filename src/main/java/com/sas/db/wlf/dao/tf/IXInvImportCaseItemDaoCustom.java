package com.sas.db.wlf.dao.tf;

import java.util.List;

import com.sas.db.wlf.orm.tf.XInvImportCaseItem;

public interface IXInvImportCaseItemDaoCustom{

	/**
	 * 批次新增
	 * @param caseItems
	 */
	public void batchSave(List<XInvImportCaseItem> caseItems);
}
