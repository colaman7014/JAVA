package com.sas.webservice.billLadingsInvoiceStatus;

import javax.jws.WebService;

import com.sas.webservice.billLadingsInvoiceStatus.bean.BillLadingsInvoiceStatusInputBean;
import com.sas.webservice.billLadingsInvoiceStatus.bean.BillLadingsInvoiceStatusOutputBean;

@WebService
public interface AmlBLInvStatus {
	
	public BillLadingsInvoiceStatusOutputBean amlBLInvStatus(BillLadingsInvoiceStatusInputBean input);
}
