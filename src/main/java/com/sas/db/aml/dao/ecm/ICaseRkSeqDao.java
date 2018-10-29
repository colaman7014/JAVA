package com.sas.db.aml.dao.ecm;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.ecm.CaseRkSeq;

/**
 * AML Stored Procedure (Create Case產生序號使用) DAO
 * @author SAS
 *
 */
public interface ICaseRkSeqDao extends CrudRepository<CaseRkSeq, BigDecimal>{
	@Procedure(procedureName=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_CASE_RK_SEQ_INC)
	public BigDecimal getCaseRkSeqInc(Integer count);
	
	@Procedure(procedureName=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_CASE_RK_SEQ_NEXT)
	public BigDecimal getCaseRkSeqNext();
}