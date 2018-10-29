package com.sas.db.wlf.dao.tf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.tf.XInvImportCaseStatus;
import com.sas.db.wlf.orm.tf.XInvImportCaseStatusPK;

public interface IXInvImportCaseStatusDao extends CrudRepository<XInvImportCaseStatus, XInvImportCaseStatusPK>{
	
	public XInvImportCaseStatus findByInvoiceNoAndIdFileKey(String invoiceNo, String fileKey);
	public XInvImportCaseStatus findByCaseRk(Long caseRk);
	
	//add by Jacob 發票-查價案件狀態
    public List<XInvImportCaseStatus> findByIdUniqueKeyOrderByCreateDttmDesc(String uniqueKey);
}
