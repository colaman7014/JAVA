package com.sas.db.wlf.dao.tf;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.tf.XInvExportCaseItem;
import com.sas.db.wlf.orm.tf.XInvExportCaseItemPK;

public interface IXInvExportCaseItemDao extends CrudRepository<XInvExportCaseItem, XInvExportCaseItemPK>, IXInvExportCaseItemDaoCustom{
	public List<XInvExportCaseItem> findByCaseRkOrderByCreateDttmDescHkHsCodeAscCategoryOfGoodsAsc(BigDecimal caseRk);
}
