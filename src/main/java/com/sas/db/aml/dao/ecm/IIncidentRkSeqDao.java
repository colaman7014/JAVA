package com.sas.db.aml.dao.ecm;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.ecm.IncidentRkSeq;

/**
 * AML Stored Procedure (Create Incident產生序號使用) DAO
 * @author SAS
 *
 */
public interface IIncidentRkSeqDao extends CrudRepository<IncidentRkSeq, BigDecimal>{
	@Procedure(procedureName=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_INCIDENT_RK_SEQ_INC)
	public BigDecimal getIncidentRkSeqInc(Integer count);
	
	@Procedure(procedureName=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_INCIDENT_RK_SEQ_NEXT)
	public BigDecimal getIncidentRkSeqNext();
}