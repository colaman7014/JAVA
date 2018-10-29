package com.sas.db.aml.dao.fcfcore;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.sas.db.aml.orm.fcfcore.FscPartyDim;
import com.sas.db.aml.orm.fcfcore.FscPartyDimPK;

/**
 * 客製化Customize
 * AML.FCFCORE.FSC_PARTY_DIM(客戶主檔) DAO
 * @author SAS
 *
 */
public interface IFscPartyDimCustomizeDao extends CrudRepository<FscPartyDim, FscPartyDimPK>{
	 
	/**
	 * 客戶白名單查詢
	 * @param partyNumber
	 * @return
	 */
	public List<FscPartyDim> findByPartyNumber(String partyNumber);


}