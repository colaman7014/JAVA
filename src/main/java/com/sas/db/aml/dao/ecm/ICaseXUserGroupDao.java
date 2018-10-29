package com.sas.db.aml.dao.ecm;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.CaseXUserGroup;
import com.sas.db.aml.orm.ecm.CaseXUserGroupPK;

/**
 * AML.ECM.CASE_X_USER_GROUP(Create Case使用) DAO
 * @author SAS
 *
 */
public interface ICaseXUserGroupDao extends CrudRepository<CaseXUserGroup, CaseXUserGroupPK>{
	
}
