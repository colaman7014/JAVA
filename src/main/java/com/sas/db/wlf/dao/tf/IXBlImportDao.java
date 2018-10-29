package com.sas.db.wlf.dao.tf;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.tf.XBlImport;
import com.sas.db.wlf.orm.tf.XBlImportPK;

/**
 * NCSC-X_BL_EXPORT(貿易融資Bill Import) DAO
 * @author SAS
 *
 */
public interface IXBlImportDao extends CrudRepository<XBlImport, XBlImportPK>, IXBlImportDaoCustom{

}
