package com.sas.db.aml.dao.fcfcore;


import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.FscPartyDimScanCandidate;

/**
 * AML.FCFCORE.FSC_PARTY_DIM_SCAN_CANDIDATE(客戶主檔) DAO
 * @author SAS
 *
 */
public interface IFscPartyDimScanCandidateDao extends CrudRepository<FscPartyDimScanCandidate, Long>, IFscPartyDimScanCandidateDaoCustom{
	
}