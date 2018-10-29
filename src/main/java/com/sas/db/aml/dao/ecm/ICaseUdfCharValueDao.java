package com.sas.db.aml.dao.ecm;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.CaseUdfCharValue;
import com.sas.db.aml.orm.ecm.CaseUdfCharValuePK;

/**
 * AML.ECM.CASE_UDF_CHAR_VALUE(Create Case使用) DAO
 * @author SAS
 *
 */
public interface ICaseUdfCharValueDao extends CrudRepository<CaseUdfCharValue, CaseUdfCharValuePK>, ICaseUdfCharValueDaoCustom{

	
	
	public CaseUdfCharValue findFirstByIdCaseRkAndIdUdfNameOrderByIdValidFromDttmDesc(Long caseRk, String udfTableName);
}