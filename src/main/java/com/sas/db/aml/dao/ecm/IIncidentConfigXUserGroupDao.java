package com.sas.db.aml.dao.ecm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.IncidentConfigXUserGroup;
import com.sas.db.aml.orm.ecm.IncidentConfigXUserGroupPK;

/**
 * AML.ECM.INCIDENT_CONFIG_X_USER_GROUP(Create Incident使用) DAO
 * @author SAS
 *
 */
public interface IIncidentConfigXUserGroupDao extends CrudRepository<IncidentConfigXUserGroup, IncidentConfigXUserGroupPK>{

	public List<IncidentConfigXUserGroup> findByIdIncidentConfigSeqNo(long incidentConfigSeqNo);
}
