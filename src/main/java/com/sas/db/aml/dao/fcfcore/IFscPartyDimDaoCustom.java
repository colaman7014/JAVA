package com.sas.db.aml.dao.fcfcore;

import java.util.List;
import java.util.Map;

import com.sas.db.aml.orm.fcfcore.FscPartyDim;

public interface IFscPartyDimDaoCustom {
	
	List<Map<String, Object>> queryExactTransactionCheck(String partyIdentificationId, String partyName, String beginDate, String endDate, boolean isChineseName);
	
	List<FscPartyDim> queryInclusiveNameCheck(List<String> inculsiveList, boolean isChineseName);

	List<Map<String, Object>> queryFuzzyTransactionCheck(List<String> matchCodeList, String beginDate, String endDate, boolean isChineseName);
	
	List<Map<String, Object>> queryInclusiveTransactionCheck(List<String> inculsiveList, String beginDate, String endDate, boolean isChineseName);

	List<FscPartyDim> queryBatchNameCheck(String externalPartyInd, int indexFrom , int indexTo);
	
	int countBatchNameCheck(String externalPartyInd);

	List<FscPartyDim> nativeSql(String sql);

	List<Long> queryPartyKeyCandidateByExactOrFuzzyOrId();

	Map<Long, List<String>> queryCandidateInclusive(List<String> inculsiveList, String entityTypeCode);

	List<FscPartyDim> queryCandidatePartyKey(String externalPartyInd, List<Long> partyKey);

	int insertPartyKeyCandidateByExactOrFuzzyOrId();
	
}
