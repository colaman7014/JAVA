package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscPartyAssoc;
import com.sas.db.aml.orm.fcfcore.FscPartyAssocPK;


/**
 * NCSC.FCFCORE.FSC_PARTY_ASSOC(客戶關係人檔) DAO
 * @author SAS
 *
 */
public interface IFscPartyAssocDao extends CrudRepository<FscPartyAssoc, FscPartyAssocPK>{
	public List<FscPartyAssoc> findByIdPartyNumberIn(List<String> partyNumberList);
	public List<FscPartyAssoc> findByIdPartyNumber(String partyNumber);
	public List<FscPartyAssoc> findOneByIdRelatedPartyNumberOrderByIdRelatedPartyNumber(String relatedPartyNumber);
}