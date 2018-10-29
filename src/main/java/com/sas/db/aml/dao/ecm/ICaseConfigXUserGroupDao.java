package com.sas.db.aml.dao.ecm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.CaseConfigXUserGroup;
import com.sas.db.aml.orm.ecm.CaseConfigXUserGroupPK;

/**
 * AML.ECM.CASE_CONFIG_X_USER_GROUP(Create Case使用) DAO
 * @author SAS
 *
 */
public interface ICaseConfigXUserGroupDao extends CrudRepository<CaseConfigXUserGroup, CaseConfigXUserGroupPK>{

	public List<CaseConfigXUserGroup> findByIdCaseConfigSeqNo(long caseConfigSeqNo);
}
