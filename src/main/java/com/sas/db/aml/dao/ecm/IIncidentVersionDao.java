package com.sas.db.aml.dao.ecm;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.IncidentVersion;
import com.sas.db.aml.orm.ecm.IncidentVersionPK;

/**
 * AML.ECM.INCIDENT_VERSION(Create Incident使用) DAO
 * @author SAS
 *
 */
public interface IIncidentVersionDao extends CrudRepository<IncidentVersion, IncidentVersionPK>{
	
}
