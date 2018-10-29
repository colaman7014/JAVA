package com.sas.db.aml.dao.ecm;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.IncidentConfig;

/**
 * AML.ECM.INCIDENT_CONFIG(Create Incident使用) DAO
 * @author SAS
 *
 */
public interface IIncidentConfigDao extends CrudRepository<IncidentConfig, Long>{
	public IncidentConfig findByIncidentTypeCdAndIncidentCategoryCd(String incidentTypeCd, String incidentCategoryCd);
}
