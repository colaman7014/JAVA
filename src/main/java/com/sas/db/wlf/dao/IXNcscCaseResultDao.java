package com.sas.db.wlf.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.XNcscCaseResult;
import com.sas.db.wlf.orm.XNcscCaseResultPK;

public interface IXNcscCaseResultDao extends CrudRepository<XNcscCaseResult, XNcscCaseResultPK>{
	
	/**
	 * 查詢案件調查該CASE下命中狀況
	 * @param caseRk
	 * @param ncReferenceId
	 * @return
	 */
	List<XNcscCaseResult> findByIdCaseRkAndIdNcReferenceId(long caseRk, int ncReferenceId);
	
	List<XNcscCaseResult> findByIdCaseRk(Long caseRk);
	
	Long countByIdCaseRkAndIdNcReferenceIdAndIdUniqueKeyAndCheckSeqAndCheckResult(long caseRk, int ncReferenceId, String uniqueKey, String checkSeq, String  checkResult);
	
	/**
	 * 查詢案件調查該CASE有無命中(T/F)的資料
	 * @param caseRk
	 * @param checkResult
	 * @return
	 */
	List<XNcscCaseResult> findByIdCaseRkAndCheckResult(Long caseRk, String checkResult);
	
	/**
	 * 查詢案件調查該CASE有無命中(T/F)的資料
	 * @param caseRk
	 * @param ncReferenceId
	 * @param uniqueKey
	 * @param checkSeq
	 * @param checkResult
	 * @return
	 */
	List<XNcscCaseResult>  findByIdCaseRkAndIdNcReferenceIdAndIdUniqueKeyAndCheckSeqAndCheckResult(long caseRk, int ncReferenceId, String uniqueKey, String checkSeq, String checkResult);
}
