package com.sas.db.aml.dao.ecm;

import java.util.List;

import com.sas.db.aml.orm.ecm.CaseUdfCharValue;

/**
 * AML.ECM.CASE_UDF_CHAR_VALUE(Create Case使用) Custom DAO
 * @author SAS
 *
 */
public interface ICaseUdfCharValueDaoCustom {
	public void batchSave(List<CaseUdfCharValue> caseUdfCharValueList);
}