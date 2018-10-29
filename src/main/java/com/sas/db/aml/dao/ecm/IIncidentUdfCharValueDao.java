package com.sas.db.aml.dao.ecm;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.IncidentUdfCharValue;
import com.sas.db.aml.orm.ecm.IncidentUdfCharValuePK;

/**
 * AML.ECM.INCIDENT_UDF_CHAR_VALUE(Create Incident使用) DAO
 * @author SAS
 *
 */
public interface IIncidentUdfCharValueDao  extends CrudRepository<IncidentUdfCharValue, IncidentUdfCharValuePK>{

}
