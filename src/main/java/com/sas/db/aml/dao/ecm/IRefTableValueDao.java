package com.sas.db.aml.dao.ecm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.RefTableValue;
import com.sas.db.aml.orm.ecm.RefTableValuePK;

/**
 * REF_TABLE_VALUE(根據refTableNm撈取相關資訊) DAO
 * @author SAS
 *
 */
public interface IRefTableValueDao extends CrudRepository<RefTableValue, RefTableValuePK>{
	public List<RefTableValue> findByIdRefTableNm(String refTableNm);
}