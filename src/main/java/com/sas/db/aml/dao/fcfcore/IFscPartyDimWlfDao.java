package com.sas.db.aml.dao.fcfcore;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.LockModeType;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sas.db.aml.orm.fcfcore.FscPartyDimWlf;
import com.sas.db.aml.orm.fcfcore.FscPartyDimWlfPK;

/**
 * AML.FCFCORE.FSC_PARTY_DIM(客戶主檔) DAO
 * @author SAS
 *
 */
public interface IFscPartyDimWlfDao extends JpaRepository<FscPartyDimWlf, FscPartyDimWlfPK>, IFscPartyDimWlfDaoCustom{
	public List<FscPartyDimWlf> findByChangeCurrentIndAndExternalPartyIndAndChangeBeginDateGreaterThanEqual(String changeCurrentInd, String externalParty, Timestamp changeBeginDate);
	
	public List<FscPartyDimWlf> findByChangeCurrentIndAndExternalPartyInd(String changeCurrentInd, String externalParty);
	
	public List<FscPartyDimWlf> findByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescNot(String changeCurrentInd, String externalParty, String partyStatusDesc);
	
	//測試用
	public List<FscPartyDimWlf> findTop30ByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescNot(String changeCurrentInd, String externalParty, String partyStatusDesc);
	
	public List<FscPartyDimWlf> findByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescIsNull(String changeCurrentInd, String externalParty);
	
	//測試用
	public List<FscPartyDimWlf> findTop30ByChangeCurrentIndAndExternalPartyIndAndPartyStatusDescIsNull(String changeCurrentInd, String externalParty);
	
	public List<FscPartyDimWlf> findByChangeCurrentIndAndExternalPartyIndAndPartyNumber(String changeCurrentInd, String externalParty, String partyNumber);
	
	public List<FscPartyDimWlf> findByPartyNumberStartingWithAndPartyNameNotNull(String partyNumber);
	
	public List<FscPartyDimWlf> findByChangeCurrentIndAndChangeBeginDateLessThan(String changeCurrentInd, Timestamp changeBeginDate);

	public List<FscPartyDimWlf> findByChangeCurrentIndAndPartyNumberIn(String changeCurrentInd, List<String> partyNumberList);
	
	public List<FscPartyDimWlf> findByPartyIdentificationIdOrPartyName(String partyIdentificationId, String partyName);

	public List<FscPartyDimWlf> findByMatchCodePartyNameIn(List<String> matchCodeList);
	
	public List<FscPartyDimWlf> findByPartyIdentificationIdOrPartyNameEng(String partyIdentificationId, String partyName);

	public List<FscPartyDimWlf> findByMatchCodePartyNameEngIn(List<String> matchCodeList);
	
	public List<FscPartyDimWlf> findByIdPartyKeyIn(List<Long> PartyKeyList);

	@Modifying()
	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("UPDATE FscPartyDimWlf fs set fs.wlfFlg='Y' WHERE fs.id.partyKey in ?1")
	public void updateBatchNameCheckWlf(List<Long> ids);

}