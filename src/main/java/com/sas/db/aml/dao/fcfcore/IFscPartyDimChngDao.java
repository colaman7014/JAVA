package com.sas.db.aml.dao.fcfcore;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscPartyDimChng;
import com.sas.db.aml.orm.fcfcore.FscPartyDimChngPK;

/**
 * AML.FCFCORE.FSC_PARTY_DIM(客戶主檔) DAO
 * @author SAS
 *
 */
public interface IFscPartyDimChngDao extends CrudRepository<FscPartyDimChng, FscPartyDimChngPK>{
	public List<FscPartyDimChng> findByChangeCurrentIndAndExternalPartyIndAndChangeBeginDateGreaterThanEqual(String changeCurrentInd, String externalParty, Timestamp changeBeginDate);
	
	public List<FscPartyDimChng> findByChangeCurrentIndAndExternalPartyInd(String changeCurrentInd, String externalParty);
	
	public List<FscPartyDimChng> findByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescNot(String changeCurrentInd, String externalParty, String partyStatusDesc);
	
	//測試用
	public List<FscPartyDimChng> findTop30ByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescNot(String changeCurrentInd, String externalParty, String partyStatusDesc);
	
	public List<FscPartyDimChng> findByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescIsNull(String changeCurrentInd, String externalParty);
	
	//測試用
	public List<FscPartyDimChng> findTop30ByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescIsNull(String changeCurrentInd, String externalParty);

}