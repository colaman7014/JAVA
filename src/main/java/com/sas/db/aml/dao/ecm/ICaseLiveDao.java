package com.sas.db.aml.dao.ecm;

import java.math.BigDecimal;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.CaseLive;

/**
 * AML.ECM.CASE_LIVE(Create Case使用) DAO
 * @author SAS
 *
 */
public interface ICaseLiveDao extends CrudRepository<CaseLive, BigDecimal>{
	
}
