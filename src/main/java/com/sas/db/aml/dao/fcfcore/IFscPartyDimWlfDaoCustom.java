package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import com.sas.db.aml.orm.fcfcore.FscPartyDimWlf;

public interface IFscPartyDimWlfDaoCustom {

	List<FscPartyDimWlf> queryBatchNameCheck(String externalPartyInd, int indexFrom , int indexTo);
	
	List<FscPartyDimWlf> queryBatchNameCheckWlf(String externalPartyInd, int handleCount);
	
	int countBatchNameCheck(String externalPartyInd);

	void updateBatchNameCheck(List<Long> partyKeyList);
	
//	void updateBatchNameCheck(List<Long> partyKey);
	
}
