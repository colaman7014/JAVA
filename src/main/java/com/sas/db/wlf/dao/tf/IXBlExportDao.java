package com.sas.db.wlf.dao.tf;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.tf.XBlExport;
import com.sas.db.wlf.orm.tf.XBlExportPK;

/**
 * NCSC-X_BL_EXPORT(貿易融資Bill Export) DAO
 * @author SAS
 *
 */
public interface IXBlExportDao extends CrudRepository<XBlExport, XBlExportPK>,IXBlExportDaoCustom{
	
}
