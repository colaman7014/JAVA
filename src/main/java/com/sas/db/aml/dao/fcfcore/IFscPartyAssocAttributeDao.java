package com.sas.db.aml.dao.fcfcore;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscPartyAssocAttribute;
import com.sas.db.aml.orm.fcfcore.FscPartyAssocAttributePK;


/**
 * NCSC.FCFCORE.FSC_PARTY_ASSOC_ATTRIBUTE(客戶關係人檔) DAO
 * @author SAS
 *
 */
public interface IFscPartyAssocAttributeDao extends CrudRepository<FscPartyAssocAttribute, FscPartyAssocAttributePK>{
	public List<FscPartyAssocAttribute> findOneByIdPartyNumberOrderByIdPartyNumber(String partyNumber);
}