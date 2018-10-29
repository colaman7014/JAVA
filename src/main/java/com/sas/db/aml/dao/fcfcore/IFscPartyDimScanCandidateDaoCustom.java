package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import com.sas.db.aml.orm.fcfcore.FscPartyDimScanCandidate;

public interface IFscPartyDimScanCandidateDaoCustom {

	List<FscPartyDimScanCandidate> nativeSql(String sql);

	int queryCountFscPartyDimScanCandidate();

	List<Long> queryCountFscPartyDimScanCandidate(int indexFrom, int indexTo);

	void truncateFscPartyDimScanCandidate();



}
