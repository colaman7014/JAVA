package com.sas.db.wlf.dao.tf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.tf.XInvExportCaseStatus;
import com.sas.db.wlf.orm.tf.XInvExportCaseStatusPK;

public interface IXInvExportCaseStatusDao extends CrudRepository<XInvExportCaseStatus, XInvExportCaseStatusPK>{

	public XInvExportCaseStatus findByInvoiceNoAndIdFileKey(String invoiceNo, String fileKey);
	public XInvExportCaseStatus findByCaseRk(Long caseRk);
	
	//add by Jacob 發票-查價案件狀態
	public List<XInvExportCaseStatus> findByIdUniqueKeyOrderByCreateDttmDesc(String uniqueKey);
}
