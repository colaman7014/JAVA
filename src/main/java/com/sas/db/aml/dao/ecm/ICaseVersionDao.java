package com.sas.db.aml.dao.ecm;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.CaseVersion;
import com.sas.db.aml.orm.ecm.CaseVersionPK;

/**
 * AML.ECM.CASE_VERSION(Create Case使用) DAO
 * @author SAS
 *
 */
public interface ICaseVersionDao extends CrudRepository<CaseVersion, CaseVersionPK>{
	List<CaseVersion> findByCaseCategoryCdAndValidToDttmGreaterThanEqualAndCaseStatusCdIn(String caseCategory, Date validToDttm , List<String> caseStatusCd);
}
