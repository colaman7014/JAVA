package com.sas.db.aml.dao.ecm;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.IncidentLive;

/**
 * AML.ECM.INCIDENT_LIVE(Create Incident使用) DAO
 * @author SAS
 *
 */
public interface IIncidentLiveDao extends CrudRepository<IncidentLive, Integer>{
	
}
