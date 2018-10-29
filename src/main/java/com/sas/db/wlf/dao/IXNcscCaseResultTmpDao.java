package com.sas.db.wlf.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.XNcscCaseResultTmp;
import com.sas.db.wlf.orm.XNcscCaseResultTmpPK;

public interface IXNcscCaseResultTmpDao extends CrudRepository<XNcscCaseResultTmp, XNcscCaseResultTmpPK>, IXNcscCaseResultTmpDaoCustomer{

	List<XNcscCaseResultTmp> findByIdCaseRkAndIdNcReferenceId(long caseRk, int ncReferenceId);
	
	XNcscCaseResultTmp findByIdCaseRkAndIdUniqueKeyAndIdNcReferenceIdAndIdSeqAndViewId(long caseRk, String uniqueKey, int ncReferenceId, int seq, String viewId);
	List<XNcscCaseResultTmp> findByIdCaseRk(long caseRk);
}
