package com.sas.db.wlf.dao.tf;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.tf.XInvImportCaseItem;
import com.sas.db.wlf.orm.tf.XInvImportCaseItemPK;

public interface IXInvImportCaseItemDao extends CrudRepository<XInvImportCaseItem, XInvImportCaseItemPK>, IXInvImportCaseItemDaoCustom{

	
	public List<XInvImportCaseItem> findByCaseRkOrderByCreateDttmDescHkHsCodeAscCategoryOfGoodsAsc(BigDecimal caseRk);
}
