package com.sas.db.aml.dao.ecm;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.IncidentXUserGroup;
import com.sas.db.aml.orm.ecm.IncidentXUserGroupPK;

/**
 * AML.ECM.INCIDENT_X_USER_GROUP(Create Incident使用) DAO
 * @author SAS
 *
 */
public interface IIncidentXUserGroupDao extends CrudRepository<IncidentXUserGroup, IncidentXUserGroupPK>{
	
}
