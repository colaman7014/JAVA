package com.sas.db.aml.dao.fcfcore;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscPartyDim;
import com.sas.db.aml.orm.fcfcore.FscPartyDimPK;

/**
 * AML.FCFCORE.FSC_PARTY_DIM(客戶主檔) DAO
 * @author SAS
 *
 */
public interface IFscPartyDimDao extends CrudRepository<FscPartyDim, FscPartyDimPK>, IFscPartyDimDaoCustom{
	public List<FscPartyDim> findByChangeCurrentIndAndExternalPartyIndAndChangeBeginDateGreaterThanEqual(String changeCurrentInd, String externalParty, Timestamp changeBeginDate);
	
	public List<FscPartyDim> findByChangeCurrentIndAndExternalPartyInd(String changeCurrentInd, String externalParty);
	
	public List<FscPartyDim> findByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescNot(String changeCurrentInd, String externalParty, String partyStatusDesc);
	
	//測試用
	public List<FscPartyDim> findTop30ByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescNot(String changeCurrentInd, String externalParty, String partyStatusDesc);
	
	public List<FscPartyDim> findByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescIsNull(String changeCurrentInd, String externalParty);
	
	//測試用
	public List<FscPartyDim> findTop30ByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescIsNull(String changeCurrentInd, String externalParty);
	
	public List<FscPartyDim> findByChangeCurrentIndAndExternalPartyIndAndPartyNumber(String changeCurrentInd, String externalParty, String partyNumber);
	
	public List<FscPartyDim> findByPartyNumberStartingWithAndPartyNameNotNull(String partyNumber);
	
	public List<FscPartyDim> findByChangeCurrentIndAndChangeBeginDateLessThan(String changeCurrentInd, Timestamp changeBeginDate);

	public List<FscPartyDim> findByChangeCurrentIndAndPartyNumberIn(String changeCurrentInd, List<String> partyNumberList);
	
	public List<FscPartyDim> findByPartyIdentificationIdOrPartyName(String partyIdentificationId, String partyName);

	public List<FscPartyDim> findByMatchCodePartyNameIn(List<String> matchCodeList);
	
	public List<FscPartyDim> findByPartyIdentificationIdOrPartyNameEng(String partyIdentificationId, String partyName);

	public List<FscPartyDim> findByMatchCodePartyNameEngIn(List<String> matchCodeList);

	public List<FscPartyDim> findByPartyNameInOrPartyNameEngIn(List<String> partyName, List<String> partyNameEng, String changeCurrentInd);
	
	public List<FscPartyDim> findByMatchCodePartyNameInOrMatchCodePartyNameEngIn(List<String> MatchCodePartyName, List<String> MatchCodePartyNameEng, String changeCurrentInd);
	
	public List<FscPartyDim> findByPartyIdentificationId(String partyIdentificationId, String changeCurrentInd);
	
}