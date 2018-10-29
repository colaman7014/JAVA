package com.sas.webservice.createCase;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eai.wsdl.sendRecv.EAIServiceInputBean;
import com.sas.amlCase.viewBean.QueryAllHitRecordReq;
import com.sas.amlCase.viewBean.QueryAllHitRecordResp;
import com.sas.amlCase.viewBean.UpdateChkRsltReq;
import com.sas.webservice.createCase.bean.CreateCaseInputBean;
import com.sas.webservice.createCase.bean.QueryHitRecordBean;
import com.sas.wsdl.swiftCheckReport.SwiftCheckReportInputBean;

@Service
public interface AmlCreateCase {
	

	
	public String getSwiftFullText(Long caseRk);
	
	public void createCaseTitle(CreateCaseInputBean input);

	public List<QueryHitRecordBean> queryHitRecord(String refId, String caseRk, String sourceType,String locale)  throws Exception;

	public QueryAllHitRecordResp queryCaseHitRecord(QueryAllHitRecordReq req,String locale) throws Exception;
	
	public String updateCheckResult(UpdateChkRsltReq updateChkRslt);

	public String updateWhiteList( UpdateChkRsltReq  updateChkRslt);

	public String saveCaseResult(String refId, String caseRk, String viewId);

	public String closeCase(long caseRk, String sourceType, String ncResult, String closeReason);
	
	public BigDecimal createCase (CreateCaseInputBean input);
	
	public BigDecimal createIncident(BigDecimal caseRK,  CreateCaseInputBean input);
	
	public BigDecimal createIncident(BigDecimal caseRK, Date nowDate, CreateCaseInputBean input);
	
	public List<Map<String, Object>> queryNCDetail(String caseRk);

	public boolean AcSwallowSwiftStatus(SwiftCheckReportInputBean swiftCheckReportInputBean, long id);
	
	public boolean callEaiServiceSendRecv(EAIServiceInputBean eaiServiceInputBean, long id);
}