package com.sas.db.aml.dao.ecm;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.CaseConfig;

/**
 * AML.ECM.CASE_CONFIG(Create Case使用) DAO
 * @author SAS
 *
 */
public interface ICaseConfigDao extends CrudRepository<CaseConfig, Long>{
	
	public CaseConfig findByCaseTypeCd(String caseTypeCd);
	public CaseConfig findByCaseTypeCdAndCaseCategoryCd(String caseTypeCd, String caseCategoryCd);
	public CaseConfig findByCaseTypeCdAndCaseCategoryCdAndCaseSubcategoryCd(String caseTypeCd, String caseCategoryCd, String caseSubcategoryCd);
}