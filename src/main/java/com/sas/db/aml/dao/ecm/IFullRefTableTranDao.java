package com.sas.db.aml.dao.ecm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.FullRefTableTran;
import com.sas.db.aml.orm.ecm.FullRefTableTranPK;

/**
 * FULL_REF_TABLE_TRANS(根據Locale撈取相關資訊) DAO
 * @author SAS
 *
 */
public interface IFullRefTableTranDao extends CrudRepository<FullRefTableTran, FullRefTableTranPK>{
	public List<FullRefTableTran> findByIdLocaleAndIdRefTableNm(String locale, String refTableNm);
	public List<FullRefTableTran> findByIdLocaleAndIdRefTableNmAndIdValueCd(String locale, String refTableNm, String valueCd);
	
	public List<FullRefTableTran> findByIdLocaleAndIdRefTableNmAndIdValueCdInOrderByDisplayOrderNoAsc(String locale, String refTableNm, String[] valueCds);
}