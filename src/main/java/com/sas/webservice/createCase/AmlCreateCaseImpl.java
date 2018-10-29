package com.sas.webservice.createCase;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eai.wsdl.sendRecv.EAIServiceInputBean;
import com.eai.wsdl.sendRecv.InputHeader;
import com.eai.wsdl.sendRecv.TrxRq;
import com.eai.wsdl.sendRecv.TrxSvcRq;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.mt.AbstractMT;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueue;
import com.sas.aml.retry.event.queue.repository.XRetryEventQueueRepository;
import com.sas.aml.retry.event.queue.service.QueueConstraint;
import com.sas.aml.retry.event.queue.service.XRetryEventQueueService;
import com.sas.amlCase.viewBean.QueryAllHitRecordReq;
import com.sas.amlCase.viewBean.QueryAllHitRecordResp;
import com.sas.amlCase.viewBean.UpdateChkRsltReq;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.ICallingSystemXUserGroupDao;
import com.sas.db.aml.dao.ecm.ICaseConfigDao;
import com.sas.db.aml.dao.ecm.ICaseConfigXUserGroupDao;
import com.sas.db.aml.dao.ecm.ICaseLiveDao;
import com.sas.db.aml.dao.ecm.ICaseRkSeqDao;
import com.sas.db.aml.dao.ecm.ICaseUdfCharValueDao;
import com.sas.db.aml.dao.ecm.ICaseVersionDao;
import com.sas.db.aml.dao.ecm.ICaseXUserGroupDao;
import com.sas.db.aml.dao.ecm.IFullRefTableTranDao;
import com.sas.db.aml.dao.ecm.IIncidentConfigDao;
import com.sas.db.aml.dao.ecm.IIncidentConfigXUserGroupDao;
import com.sas.db.aml.dao.ecm.IIncidentLiveDao;
import com.sas.db.aml.dao.ecm.IIncidentRkSeqDao;
import com.sas.db.aml.dao.ecm.IIncidentUdfCharValueDao;
import com.sas.db.aml.dao.ecm.IIncidentVersionDao;
import com.sas.db.aml.dao.ecm.IIncidentXUserGroupDao;
import com.sas.db.aml.dao.ecm.IRefTableValueDao;
import com.sas.db.aml.dao.fcfcore.IAccountIntegrationDao;
import com.sas.db.aml.dao.fcfcore.IAccountIntegrationRoleDao;
import com.sas.db.aml.orm.ecm.CallingSystemXUserGroup;
import com.sas.db.aml.orm.ecm.CaseConfig;
import com.sas.db.aml.orm.ecm.CaseConfigXUserGroup;
import com.sas.db.aml.orm.ecm.CaseLive;
import com.sas.db.aml.orm.ecm.CaseUdfCharValue;
import com.sas.db.aml.orm.ecm.CaseUdfCharValuePK;
import com.sas.db.aml.orm.ecm.CaseVersion;
import com.sas.db.aml.orm.ecm.CaseVersionPK;
import com.sas.db.aml.orm.ecm.CaseXUserGroup;
import com.sas.db.aml.orm.ecm.CaseXUserGroupPK;
import com.sas.db.aml.orm.ecm.FullRefTableTran;
import com.sas.db.aml.orm.ecm.IncidentConfig;
import com.sas.db.aml.orm.ecm.IncidentConfigXUserGroup;
import com.sas.db.aml.orm.ecm.IncidentLive;
import com.sas.db.aml.orm.ecm.IncidentUdfCharValue;
import com.sas.db.aml.orm.ecm.IncidentUdfCharValuePK;
import com.sas.db.aml.orm.ecm.IncidentVersion;
import com.sas.db.aml.orm.ecm.IncidentVersionPK;
import com.sas.db.aml.orm.ecm.IncidentXUserGroup;
import com.sas.db.aml.orm.ecm.IncidentXUserGroupPK;
import com.sas.db.aml.orm.ecm.RefTableValue;
import com.sas.db.aml.orm.fcfcore.AccountIntegration;
import com.sas.db.aml.orm.fcfcore.AccountIntegrationRole;
import com.sas.db.wlf.dao.CaseCustomDao;
import com.sas.db.wlf.dao.IXNcscCaseResultDao;
import com.sas.db.wlf.dao.IXNcscCaseResultTmpDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordDetailDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordMainDao;
import com.sas.db.wlf.dao.nc.INameHitRecordDao;
import com.sas.db.wlf.dao.sc.ISwiftCheckRecordDao;
import com.sas.db.wlf.dao.sc.ISwiftHitRecordDao;
import com.sas.db.wlf.dao.tf.IXInvExportCaseStatusDao;
import com.sas.db.wlf.dao.tf.IXInvImportCaseStatusDao;
import com.sas.db.wlf.orm.XNcscCaseResult;
import com.sas.db.wlf.orm.XNcscCaseResultPK;
import com.sas.db.wlf.orm.XNcscCaseResultTmp;
import com.sas.db.wlf.orm.XNcscCaseResultTmpPK;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;
import com.sas.db.wlf.orm.nc.NameCheckRecordMainPK;
import com.sas.db.wlf.orm.nc.NameHitRecord;
import com.sas.db.wlf.orm.nc.NameHitRecordPK;
import com.sas.db.wlf.orm.sc.SwiftCheckRecord;
import com.sas.db.wlf.orm.sc.SwiftCheckRecordPK;
import com.sas.db.wlf.orm.sc.SwiftHitRecord;
import com.sas.db.wlf.orm.sc.SwiftHitRecordPK;
import com.sas.db.wlf.orm.tf.XInvExportCaseStatus;
import com.sas.db.wlf.orm.tf.XInvImportCaseStatus;
import com.sas.util.AmlConfiguration;
import com.sas.util.AmlRoutRuleComparator;
import com.sas.util.DateUtil;
import com.sas.util.NameCheckUtil;
import com.sas.webservice.batch.screening.bean.BatchNameCheckingOutputRealTimeBean;
import com.sas.webservice.billLadingsInvoiceStatus.AmlBLInvStatus;
import com.sas.webservice.billLadingsInvoiceStatus.bean.BillLadingsInvoiceStatusInputBean;
import com.sas.webservice.billLadingsInvoiceStatus.bean.BillLadingsInvoiceStatusOutputBean;
import com.sas.webservice.createCase.bean.CreateCaseInputBean;
import com.sas.webservice.createCase.bean.QueryHitRecordBean;
import com.sas.webservice.nameCheckStatus.AmlNameCheckStatus;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusInputBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusOutputBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusOutputDetailBean;
import com.sas.webservice.swiftCheckStatus.AmlSwiftCheckStatus;
import com.sas.webservice.swiftCheckStatus.bean.SwiftCheckStatusInputBean;
import com.sas.webservice.swiftCheckStatus.bean.SwiftCheckStatusOutputBean;
import com.sas.wsdl.swiftCheckReport.AmlSwiftCheckReport;
import com.sas.wsdl.swiftCheckReport.AmlSwiftCheckReportImplService;
import com.sas.wsdl.swiftCheckReport.SwiftCheckReportInputBean;
import com.sas.wsdl.swiftCheckReport.SwiftCheckReportOutputBean;

@Component
public class AmlCreateCaseImpl implements AmlCreateCase {
	
	private static final Logger logger = LoggerFactory.getLogger(AmlCreateCaseImpl.class);
	
	private static final String UDF_TABLE_NAME = "CASE";
	private static final String IC_UDF_TABLE_NAME = "INCIDENT";
	private static final long ROW_NO = 1;
	@Autowired
	ServletContext context; 
	
	@Autowired
	ICaseUdfCharValueDao iCaseUdfCharValueDao;
	
	@Autowired
	CaseCustomDao caseCustomDao;
	
	@Autowired
	IXNcscCaseResultTmpDao iXNcscCaseResultTmpDao;
	
	@Autowired
	IXNcscCaseResultDao iXNcscCaseResultDao;
	
	@Autowired
	ICaseLiveDao iCaseLiveDao;
	
	@Autowired
	ICaseConfigDao caseConfigDao;
	
	@Autowired
	ICaseRkSeqDao iCaseRkSeqDao;
	
	@Autowired
	ICaseXUserGroupDao iCaseXUserGroupDao;
	
	@Autowired
	IIncidentRkSeqDao iIncidentRkSeqDao;
	
	@Autowired
	INameCheckRecordMainDao iNameCheckRecordMainDao;
	
	@Autowired
	INameCheckRecordDetailDao iNameCheckRecordDetailDao; 
	
	@Autowired
	ISwiftCheckRecordDao iSwiftCheckRecordDao;
	
	@Autowired
	ICaseVersionDao iCaseVersionDao;
	
	@Autowired
	ICaseConfigXUserGroupDao iCaseConfigXUserGroupDao; 
	
	@Autowired
	IIncidentConfigDao iIncidentConfigDao;
	
	@Autowired
	IIncidentLiveDao iIncidentLiveDao;
	
	@Autowired
	IIncidentConfigXUserGroupDao iIncidentConfigXUserGroupDao;
	
	@Autowired
	IIncidentXUserGroupDao iIncidentXUserGroupDao;
	
	@Autowired
	IIncidentVersionDao iIncidentVersionDao;
	
	@Autowired
	IIncidentUdfCharValueDao iIncidentUdfCharValueDao;
	
	@Autowired
	AmlNameCheckStatus amlNameCheckStatus;
	
	@Autowired
	AmlSwiftCheckStatus amlSwiftCheckStatus;
	
	@Autowired
	AmlComboWhitelistCase amlComboWhitelistCase;
	
	@Autowired
	IXInvExportCaseStatusDao iXInvExportCaseStatusDao;
	
	@Autowired
	IXInvImportCaseStatusDao iXInvImportCaseStatusDao;
	
	@Autowired
	IAccountIntegrationDao iAccountIntegrationDao;
	
	@Autowired
	IFullRefTableTranDao iFullRefTableTranDao;
	
	@Autowired
	IRefTableValueDao iRefTableValueDao;
	
	@Autowired
	AmlBLInvStatus amlBLInvStatus;
	
	@Autowired
	IAccountIntegrationRoleDao iAccountIntegrationRoleDao;
	
	@Autowired
	ICallingSystemXUserGroupDao iCallingSystemXUserGroupDao;
	
	@Autowired
	XRetryEventQueueRepository xRetryEventQueueRepository;
	
	@Autowired
	XRetryEventQueueService xRetryEventQueueService;
	
	@Autowired
	AmlCreateCaseCustomize amlCreateCaseCustomize;
	
	@Autowired
	ISwiftHitRecordDao iSwiftHitRecordDao;
	
	@Autowired
	INameHitRecordDao iNameHitRecordDao;
	
	@Override
	public void createCaseTitle(CreateCaseInputBean input) {
		List<CaseUdfCharValue> caseUdfCharValueList = new ArrayList<CaseUdfCharValue>();
		
		CaseUdfCharValue ncReferenceId = new CaseUdfCharValue();
		CaseUdfCharValuePK ncReferenceIdPK = new CaseUdfCharValuePK();
		ncReferenceIdPK.setCaseRk(input.getCaseRk());
		ncReferenceIdPK.setValidFromDttm(input.getValidFromDttm());
		ncReferenceIdPK.setRowNo(ROW_NO);
		ncReferenceIdPK.setUdfTableName(UDF_TABLE_NAME);
		ncReferenceIdPK.setUdfName("X_NCSC_NC_REFERENCE_ID");
		ncReferenceId.setId(ncReferenceIdPK);
		ncReferenceId.setUdfValue(input.getNcReferenceId());
		caseUdfCharValueList.add(ncReferenceId);
		
		CaseUdfCharValue callingSystem = new CaseUdfCharValue();
		CaseUdfCharValuePK callingSystemPK = new CaseUdfCharValuePK();
		callingSystemPK.setCaseRk(input.getCaseRk());
		callingSystemPK.setValidFromDttm(input.getValidFromDttm());
		callingSystemPK.setRowNo(ROW_NO);
		callingSystemPK.setUdfTableName(UDF_TABLE_NAME);
		callingSystemPK.setUdfName("X_NCSC_CALLING_SYSTEM");
		callingSystem.setId(callingSystemPK);
		callingSystem.setUdfValue(input.getCallingSystem());
		caseUdfCharValueList.add(callingSystem);
		
		CaseUdfCharValue callingUser = new CaseUdfCharValue();
		CaseUdfCharValuePK callingUserPK = new CaseUdfCharValuePK();
		callingUserPK.setCaseRk(input.getCaseRk());
		callingUserPK.setValidFromDttm(input.getValidFromDttm());
		callingUserPK.setRowNo(ROW_NO);
		callingUserPK.setUdfTableName(UDF_TABLE_NAME);
		callingUserPK.setUdfName("X_NCSC_CALLING_USER");
		callingUser.setId(callingUserPK);
		callingUser.setUdfValue(input.getCallingUser());
		caseUdfCharValueList.add(callingUser);
		
		CaseUdfCharValue branchNo = new CaseUdfCharValue();
		CaseUdfCharValuePK branchNoPK = new CaseUdfCharValuePK();
		branchNoPK.setCaseRk(input.getCaseRk());
		branchNoPK.setValidFromDttm(input.getValidFromDttm());
		branchNoPK.setRowNo(ROW_NO);
		branchNoPK.setUdfTableName(UDF_TABLE_NAME);
		branchNoPK.setUdfName("X_NCSC_BRANCH_NUMBER");
		branchNo.setId(branchNoPK);
		branchNo.setUdfValue(input.getBranchNumber());
		caseUdfCharValueList.add(branchNo);
		
		CaseUdfCharValue routeRule = new CaseUdfCharValue();
		CaseUdfCharValuePK routeRulePK = new CaseUdfCharValuePK();
		routeRulePK.setCaseRk(input.getCaseRk());
		routeRulePK.setValidFromDttm(input.getValidFromDttm());
		routeRulePK.setRowNo(ROW_NO);
		routeRulePK.setUdfTableName(UDF_TABLE_NAME);
		routeRulePK.setUdfName("X_NCSC_ROUTE_RULE");
		routeRule.setId(routeRulePK);
		routeRule.setUdfValue(input.getRouteRule());
		caseUdfCharValueList.add(routeRule);
		
		CaseUdfCharValue transactionDate = new CaseUdfCharValue();
		CaseUdfCharValuePK transactionDatePK = new CaseUdfCharValuePK();
		transactionDatePK.setCaseRk(input.getCaseRk());
		transactionDatePK.setValidFromDttm(input.getValidFromDttm());
		transactionDatePK.setRowNo(ROW_NO);
		transactionDatePK.setUdfTableName(UDF_TABLE_NAME);
		transactionDatePK.setUdfName("X_NCSC_TRANSACTION_DATE");
		transactionDate.setId(transactionDatePK);
		transactionDate.setUdfValue(input.getTransactionDate());
		caseUdfCharValueList.add(transactionDate);
		
		CaseUdfCharValue hitSeq = new CaseUdfCharValue();
		CaseUdfCharValuePK hitSeqPK = new CaseUdfCharValuePK();
		hitSeqPK.setCaseRk(input.getCaseRk());
		hitSeqPK.setValidFromDttm(input.getValidFromDttm());
		hitSeqPK.setRowNo(ROW_NO);
		hitSeqPK.setUdfTableName(UDF_TABLE_NAME);
		hitSeqPK.setUdfName("X_NCSC_HIT_SEQ");
		hitSeq.setId(hitSeqPK);
		hitSeq.setUdfValue(input.getHitSeq());
		caseUdfCharValueList.add(hitSeq);
		
		CaseUdfCharValue partyNo = new CaseUdfCharValue();
		CaseUdfCharValuePK partyNoPK = new CaseUdfCharValuePK();
		partyNoPK.setCaseRk(input.getCaseRk());
		partyNoPK.setValidFromDttm(input.getValidFromDttm());
		partyNoPK.setRowNo(ROW_NO);
		partyNoPK.setUdfTableName(UDF_TABLE_NAME);
		partyNoPK.setUdfName("X_NCSC_PARTY_NO");
		partyNo.setId(partyNoPK);
		partyNo.setUdfValue(input.getPartyNo());
		caseUdfCharValueList.add(partyNo);
		
		CaseUdfCharValue referenceNumber = new CaseUdfCharValue();
		CaseUdfCharValuePK referenceNumberPK = new CaseUdfCharValuePK();
		referenceNumberPK.setCaseRk(input.getCaseRk());
		referenceNumberPK.setValidFromDttm(input.getValidFromDttm());
		referenceNumberPK.setRowNo(ROW_NO);
		referenceNumberPK.setUdfTableName(UDF_TABLE_NAME);
		referenceNumberPK.setUdfName("X_NCSC_REFERENCE_NUMBER");
		referenceNumber.setId(referenceNumberPK);
		referenceNumber.setUdfValue(input.getReferenceNumber());
		caseUdfCharValueList.add(referenceNumber);
		
		CaseUdfCharValue uniqueKey = new CaseUdfCharValue();
		CaseUdfCharValuePK uniqueKeyPK = new CaseUdfCharValuePK();
		uniqueKeyPK.setCaseRk(input.getCaseRk());
		uniqueKeyPK.setValidFromDttm(input.getValidFromDttm());
		uniqueKeyPK.setRowNo(ROW_NO);
		uniqueKeyPK.setUdfTableName(UDF_TABLE_NAME);
		uniqueKeyPK.setUdfName("X_NCSC_UNIQUE_KEY");
		uniqueKey.setId(uniqueKeyPK);
		uniqueKey.setUdfValue(input.getUniqueKey());
		caseUdfCharValueList.add(uniqueKey);
		
		CaseUdfCharValue screenProcess = new CaseUdfCharValue();
		CaseUdfCharValuePK screenProcessPK = new CaseUdfCharValuePK();
		screenProcessPK.setCaseRk(input.getCaseRk());
		screenProcessPK.setValidFromDttm(input.getValidFromDttm());
		screenProcessPK.setRowNo(ROW_NO);
		screenProcessPK.setUdfTableName(UDF_TABLE_NAME);
		screenProcessPK.setUdfName("X_NCSC_SCREEN_PROCESS");
		screenProcess.setId(screenProcessPK);
		screenProcess.setUdfValue(input.getScreenProcess());
		caseUdfCharValueList.add(screenProcess);
		
		CaseUdfCharValue sourceType = new CaseUdfCharValue();
		CaseUdfCharValuePK sourceTypePK = new CaseUdfCharValuePK();
		sourceTypePK.setCaseRk(input.getCaseRk());
		sourceTypePK.setValidFromDttm(input.getValidFromDttm());
		sourceTypePK.setRowNo(ROW_NO);
		sourceTypePK.setUdfTableName(UDF_TABLE_NAME);
		sourceTypePK.setUdfName("X_NCSC_SOURCE_TYPE");
		sourceType.setId(sourceTypePK);
		sourceType.setUdfValue(input.getSourceType());
		caseUdfCharValueList.add(sourceType);
		
		CaseUdfCharValue sourceTrueHit = new CaseUdfCharValue();
		CaseUdfCharValuePK sourceTrueHitPkK = new CaseUdfCharValuePK();
		sourceTrueHitPkK.setCaseRk(input.getCaseRk());
		sourceTrueHitPkK.setValidFromDttm(input.getValidFromDttm());
		sourceTrueHitPkK.setRowNo(ROW_NO);
		sourceTrueHitPkK.setUdfTableName(UDF_TABLE_NAME);
		sourceTrueHitPkK.setUdfName("X_WATCHLISTTYPECD_0102TRUEHIT");
		sourceTrueHit.setId(sourceTrueHitPkK);
		sourceTrueHit.setUdfValue("N");
		caseUdfCharValueList.add(sourceTrueHit);
		
		caseUdfCharValueList.add(toCharUdfValue(new BigDecimal(input.getCaseRk()), input.getValidFromDttm(), 1, "X_NCSC_FALSE_IND", ""));
		iCaseUdfCharValueDao.save(caseUdfCharValueList);
		
	}

	
	public List<QueryHitRecordBean> queryHitRecord(String refId, String caseRk,
			String sourceType,String locale) throws Exception {
		List<QueryHitRecordBean> hitRecordLst = caseCustomDao.queryHitRecord(refId, caseRk, sourceType);
		List<QueryHitRecordBean> resultList =new ArrayList<QueryHitRecordBean>();
		Map<String, List<FullRefTableTran>> fullRefTableTranMap = getFullRefTableTranMap(locale);
		List<FullRefTableTran> refEntityTypeCdList =fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_ENTITY_TYPE_CD);
		List<FullRefTableTran> refEntityRelationshipList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_ENTITY_RELATIONSHIP);
		List<FullRefTableTran> refNCResultList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_HIT_RESULT);
		List<FullRefTableTran> refWatchListTypeCdList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
//		List<FullRefTableTran> refWatchListSubTypeCdList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
		String[] routeRuleArray;
		
		for(QueryHitRecordBean hitRecordDetail:hitRecordLst){
			QueryHitRecordBean hitRecordDetailDetailBean=new QueryHitRecordBean(hitRecordDetail);
				if(hitRecordDetail.getEntityType()!=null){
					for(FullRefTableTran refEntityTypeCd:refEntityTypeCdList){
						if(hitRecordDetail.getEntityType().equals(refEntityTypeCd.getId().getValueCd())){
							hitRecordDetailDetailBean.setTransEntityType(refEntityTypeCd.getValueDesc());
							break;
						}
					}
				}
				if(hitRecordDetail.getEntityRelationship()!=null){
					for(FullRefTableTran refEntityRelationship:refEntityRelationshipList){
						if(hitRecordDetail.getEntityRelationship().equals(refEntityRelationship.getId().getValueCd())){
							hitRecordDetailDetailBean.setTransEntityRelationship(refEntityRelationship.getValueDesc());
						break;
						}
					}
				}
				if(hitRecordDetail.getCheckResult()!=null){
						for(FullRefTableTran refNCResult:refNCResultList){
							if(hitRecordDetail.getCheckResult().equals(refNCResult.getId().getValueCd())){
								hitRecordDetailDetailBean.setTransCheckResult(refNCResult.getValueDesc());
							break;
						}
					}
				}
				String routeRule=hitRecordDetail.getWatchListTypeCd();
				routeRuleArray=routeRule.split(SwiftMtConst.ENTITY_TYPE_CD_SPLIT);
				String newRouteRule = null;
				for(String routeRuleNumber:routeRuleArray){
					if(newRouteRule==null){
						for(FullRefTableTran refWatchListTypeCd:refWatchListTypeCdList){
							if(routeRuleNumber.equals(refWatchListTypeCd.getId().getValueCd())){
								newRouteRule=refWatchListTypeCd.getValueDesc();
							}
						}
					}else{
						for(FullRefTableTran refWatchListTypeCd:refWatchListTypeCdList){
							if(routeRuleNumber.equals(refWatchListTypeCd.getId().getValueCd())){						
								newRouteRule=newRouteRule.concat(SwiftMtConst.ENTITY_TYPE_CD_SPLIT+refWatchListTypeCd.getValueDesc());
							break;
							}
						}
					}
				}
				hitRecordDetailDetailBean.setTransRouteRule(newRouteRule);		
				if(hitRecordDetailDetailBean != null && hitRecordDetailDetailBean.getWatchListTypeCd()!=null && 
						!"".equals(hitRecordDetailDetailBean.getWatchListTypeCd())) {
					resultList.add(hitRecordDetailDetailBean);
				}
		}
				
		if(!resultList.isEmpty()) {
			AmlRoutRuleComparator amlRoutRuleComparator = new AmlRoutRuleComparator(getRouteRuleOrder());
			Collections.sort(resultList, amlRoutRuleComparator);
		}
		
		return resultList;
	}
	private Map<String, List<FullRefTableTran>> getFullRefTableTranMap(String locale){
		Map<String, List<FullRefTableTran>> fullRefTableTranMap = new HashMap<String, List<FullRefTableTran>>();
		List<FullRefTableTran> refEntityTypeCdList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_ENTITY_TYPE_CD);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_ENTITY_TYPE_CD,refEntityTypeCdList);
		List<FullRefTableTran> refEntityRelationshipList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_ENTITY_RELATIONSHIP);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_ENTITY_RELATIONSHIP,refEntityRelationshipList);
		List<FullRefTableTran> refNCResultList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_HIT_RESULT);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_HIT_RESULT,refNCResultList);
		List<FullRefTableTran> refWatchListTypeCdList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD, refWatchListTypeCdList);
		List<FullRefTableTran> refWatchListSubTypeCdList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD, refWatchListSubTypeCdList);
		List<FullRefTableTran> refBranchNumberCdList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_BRANCH_LIST);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_BRANCH_LIST, refBranchNumberCdList);
		List<FullRefTableTran> refCallingSystemCdList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_CALLING_SYSTEM);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_CALLING_SYSTEM, refCallingSystemCdList);
		List<FullRefTableTran> refScreenProcessCdList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_SCREEN_PROCESS);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_SCREEN_PROCESS, refScreenProcessCdList);
		
		return fullRefTableTranMap;	
	}	
	@Override
	public List<Map<String, Object>> queryNCDetail(String caseRk) {
		return  caseCustomDao.queryNCDetail(caseRk);
	}
	
	public QueryAllHitRecordResp queryCaseHitRecord(QueryAllHitRecordReq req,String locale) throws Exception {
		QueryAllHitRecordResp resp = new QueryAllHitRecordResp();
		List<AccountIntegrationRole> usersRoles = iAccountIntegrationRoleDao.findByIdEmpId(req.getUserId());
		List<String> roleNms = new ArrayList<String>();
		for(AccountIntegrationRole role: usersRoles) {
			roleNms.add(role.getId().getRole());
		}
		List<QueryHitRecordBean> queryHitRecord = queryHitRecord(req.getRefId(), req.getCaseRk(), req.getSourceType(),locale);
		
		Map<String, List<FullRefTableTran>> fullRefTableTranMap = getFullRefTableTranMap(locale);
		List<FullRefTableTran> refEntityTypeCdList =fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_ENTITY_TYPE_CD);
		List<FullRefTableTran> refEntityRelationshipList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_ENTITY_RELATIONSHIP);

		resp.setHitRecord(queryHitRecord);
		resp.setIsViewMode(roleNms.contains(SwiftMtConst.CASE_VIEW_MODE_ROLE) ? "Y" : "N");
		if(!"SC".equals(req.getSourceType().split("-")[1])){
			List<NameCheckRecordMain> nameCheckMain = iNameCheckRecordMainDao.findByCaseRk(Long.valueOf(req.getCaseRk()));
			resp.setIsSwift("N");
			resp.setPartyNo(nameCheckMain.get(0).getPartyNumber());
			resp.setCheckSeq(queryNCDetail(req.getCaseRk()));
			ncIsOpenComboWhite(req.getSourceType(), queryHitRecord, nameCheckMain.get(0).getPartyNumber());
		} else {
			resp.setIsSwift("Y");
			List<SwiftCheckRecord> swiftRecords = iSwiftCheckRecordDao.findByCaseRk(Long.valueOf(req.getCaseRk()));
			resp.setSwiftType(swiftRecords.get(0).getSwiftType());
			resp.setPartyNo(swiftRecords.get(0).getPartyNumber());
			scIsOpenComboWhite(req.getSourceType(), queryHitRecord, swiftRecords.get(0).getPartyNumber(), swiftRecords.get(0).getSwiftType());
		}
		
		List<Map<String, Object>> checkSeqList=resp.getCheckSeq();
		if(checkSeqList!=null){
			List<Map<String, Object>> newCheckSeqList=new ArrayList<Map<String,Object>>();
			for(Map<String, Object> checkSeqMap:checkSeqList){
				Map<String, Object> newCheckSeqMap=new HashMap<String, Object>();
				for (Entry<String, Object> entry : checkSeqMap.entrySet()) {
				    String key = entry.getKey();
				    Object value = entry.getValue();
				    newCheckSeqMap.put(key, value);
				    
				    //entity_type
					if(SwiftMtConst.QueryAllMap_ENTITY_TYPE.equals(key)){
						for(FullRefTableTran refEntityTypeCd:refEntityTypeCdList){
							if(value.toString().equals(refEntityTypeCd.getId().getValueCd())){
								newCheckSeqMap.put("TRANS_ENTITY_TYPE", refEntityTypeCd.getValueDesc());
								break;
							}
						}
					}
					
					//entity_relationship
					if(SwiftMtConst.QueryAllMap_ENTITY_RELATIONSHIP.equals(key)){
						for(FullRefTableTran refEntityRelationship:refEntityRelationshipList){
							if(value.toString().equals(refEntityRelationship.getId().getValueCd())){
								newCheckSeqMap.put("TRANS_ENTITY_RELATIONSHIP", refEntityRelationship.getValueDesc());
								break;
							}
						}
					}
	
				}
				newCheckSeqList.add(newCheckSeqMap);
			}
			resp.setCheckSeq(newCheckSeqList);
		}	
		return resp;
	}

	@Override
	public String updateCheckResult(UpdateChkRsltReq updateChkRslt) {
		try{
			XNcscCaseResultTmp xNcscCaseResultTmp = new XNcscCaseResultTmp();
			XNcscCaseResultTmpPK pk = new XNcscCaseResultTmpPK();
			xNcscCaseResultTmp.setId(pk);
			BeanUtils.copyProperties(updateChkRslt, pk);
			BeanUtils.copyProperties(updateChkRslt, xNcscCaseResultTmp);
			XNcscCaseResultTmp bean = iXNcscCaseResultTmpDao.findOne(xNcscCaseResultTmp.getId());
			if(bean==null){
				iXNcscCaseResultTmpDao.save(xNcscCaseResultTmp);
			}
			else{
				bean.setViewId(updateChkRslt.getViewId());
				bean.setCheckResult(xNcscCaseResultTmp.getCheckResult());
				iXNcscCaseResultTmpDao.save(bean);
			}
		}
		catch(Exception ex){
			logger.error("updateCheckResult", ex);
			return "error";
		}
		return "success";
	}

	@Override
	public String updateWhiteList( UpdateChkRsltReq  updateChkRslt) {
		try{
			XNcscCaseResultTmp xNcscCaseResultTmp = new XNcscCaseResultTmp();
			XNcscCaseResultTmpPK pk = new XNcscCaseResultTmpPK();
			xNcscCaseResultTmp.setId(pk);
			BeanUtils.copyProperties(updateChkRslt, pk);
			BeanUtils.copyProperties(updateChkRslt, xNcscCaseResultTmp);
			XNcscCaseResultTmp bean = iXNcscCaseResultTmpDao.findOne(xNcscCaseResultTmp.getId());
			if(bean==null){
				iXNcscCaseResultTmpDao.save(xNcscCaseResultTmp);
			}
			else{
				bean.setViewId(updateChkRslt.getViewId());
				bean.setWhitelistInd(xNcscCaseResultTmp.getWhitelistInd());
				iXNcscCaseResultTmpDao.save(bean);
			}
		}
		catch(Exception ex){
			logger.error("updateWhiteList", ex);
			return "error";
		}
		return "success";
	}

	
	@Override
	@Transactional(propagation= Propagation.REQUIRES_NEW)
	public String saveCaseResult(String refId, String caseRk, String viewId) {
		logger.info("inner saveCaseResult, refId:" + refId + ", caseRk:" + caseRk);
		try{
			List<XNcscCaseResult> caseResultList = iXNcscCaseResultDao.findByIdCaseRkAndIdNcReferenceId(Long.valueOf(caseRk), Integer.valueOf(refId));
			long caseRkInt = Long.valueOf(caseRk).longValue();
			boolean isAllFalseHit = true;
			boolean isAllChlRsltFilled = true;
			CaseLive caseLive = iCaseLiveDao.findOne(BigDecimal.valueOf(caseRkInt));
			for(XNcscCaseResult caseResult : caseResultList){
				logger.info("caseResult : "+caseResult.toString());
				XNcscCaseResultTmp queryObject = new XNcscCaseResultTmp(caseResult);
				XNcscCaseResultTmp tmp = iXNcscCaseResultTmpDao.findOne(queryObject.getId());
				if(tmp!=null){
					if(viewId.equals(tmp.getViewId())) {
						caseResult.setCheckResult(tmp.getCheckResult());
						caseResult.setWhitelistInd(tmp.getWhitelistInd());
						iXNcscCaseResultDao.save(caseResult);
					}
				}
				if("T".equals(caseResult.getCheckResult())) {
					isAllFalseHit = false;
				}
				if(StringUtils.isBlank(caseResult.getCheckResult())) {
					isAllChlRsltFilled = false;
				}
			}
			logger.debug("isAllFalseHit: {}", isAllFalseHit);
			logger.debug("isAllChlRsltFilled: {}", isAllChlRsltFilled);
			Date validFromDttm = caseLive.getValidFromDttm();
			if(isAllChlRsltFilled) {
				iCaseUdfCharValueDao.save(toCharUdfValue(new BigDecimal(caseRk), validFromDttm, 1, "X_NCSC_FALSE_IND", isAllFalseHit ? "Y" : "N"));
			} else {
				iCaseUdfCharValueDao.save(toCharUdfValue(new BigDecimal(caseRk), validFromDttm, 1, "X_NCSC_FALSE_IND", ""));
			}
			logger.info("saveUdfWatchListTypeCd0102truehit");
			amlCreateCaseCustomize.saveUdfWatchListTypeCd0102truehit(refId, caseRk); //BOT 名單掃描結果(人名和電文): 命中名單中01、02大類 只要有一個TrueHit 要寫入UDF(X_WATCHLISTTYPECD01_02TRUEHIT=Y)
		}
		catch(Exception ex){
			logger.error("saveCaseResult", ex);
			return "error";
		}
		return "success";
	}
	
	/*
	 * 取得名單大小類的對應資訊
	 * */
	private Map<String, String> getSubWatchListTypeAndWatchListTypeMapping() {
		Map<String, String> resultMap = new HashMap<String, String>();
		List<RefTableValue> watchListTypeCdList = iRefTableValueDao
				.findByIdRefTableNm(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
		for(RefTableValue value : watchListTypeCdList){
			resultMap.put(value.getId().getValueCd(), value.getRefTableValue().getId().getValueCd());
		}
		return resultMap;
	}
	
	/**
	 * 取得名單大小類的顯示順序，來代表嚴重程度
	 */
	private Map<String, String> getRankOfWatchListMap(){
		Map<String, String> resultMap = new HashMap<String, String>();
		List<RefTableValue> watchListTypeCdList = iRefTableValueDao.findByIdRefTableNm(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
		List<RefTableValue> watchListSubTypeCdList = iRefTableValueDao.findByIdRefTableNm(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
		for(RefTableValue value : watchListTypeCdList){
			resultMap.put(value.getId().getValueCd(), value.getDisplayOrderNo().toString());
		}
		for(RefTableValue value : watchListSubTypeCdList){
			resultMap.put(value.getId().getValueCd(), value.getDisplayOrderNo().toString());
		}		
		return resultMap;
	}
	
	private String getSwiftCheckTrueHitRouteRule(List<XNcscCaseResult> trueHitCaseResultRecord, Map<String, String> rankOfWatchListMap){
		String result = "";
		Map<String, String> subWatchListTypeAndWatchListTypeMappingMap = getSubWatchListTypeAndWatchListTypeMapping();
		if(trueHitCaseResultRecord != null && trueHitCaseResultRecord.size() > 0){
			for(XNcscCaseResult xncscCaseResult : trueHitCaseResultRecord){
				String uniqueKey = xncscCaseResult.getId().getUniqueKey();
				int ncReferenceId = xncscCaseResult.getId().getNcReferenceId();
				String seq = String.valueOf(xncscCaseResult.getId().getSeq());
				SwiftHitRecord  swiftHitRecord = iSwiftHitRecordDao.findOne(new SwiftHitRecordPK(uniqueKey, ncReferenceId, seq));
				logger.debug(swiftHitRecord.toString());
				if(swiftHitRecord != null && swiftHitRecord.getWatchListTypeCd() != null){
					logger.debug(result + " : " + swiftHitRecord.getWatchListTypeCd());
					result = NameCheckUtil.seriousRule(result, swiftHitRecord.getWatchListSubTypeCd(), subWatchListTypeAndWatchListTypeMappingMap, rankOfWatchListMap);
				}
			}
		}
		logger.debug("getSwiftCheckTrueHitRouteRule : " + result);
		return result;
	}
	
	private String getNameCheckMainTrueHitRouteRule(List<XNcscCaseResult> trueHitCaseResultRecord, Map<String, String> rankOfWatchListMap){
		String result = "";
		Map<String, String> subWatchListTypeAndWatchListTypeMappingMap = getSubWatchListTypeAndWatchListTypeMapping();
		if(trueHitCaseResultRecord != null && trueHitCaseResultRecord.size() > 0){
			for(XNcscCaseResult xncscCaseResult : trueHitCaseResultRecord){
				String uniqueKey = xncscCaseResult.getId().getUniqueKey();
				int ncReferenceId = xncscCaseResult.getId().getNcReferenceId();
				String checkSeq = xncscCaseResult.getCheckSeq();
				String seq = String.valueOf(xncscCaseResult.getId().getSeq());
				NameHitRecord nameHitRecord = iNameHitRecordDao.findOne(new NameHitRecordPK(uniqueKey, ncReferenceId, checkSeq, seq));
				logger.debug(nameHitRecord.toString());
				if(nameHitRecord != null && nameHitRecord.getWatchListTypeCd() != null){
					logger.debug(result + " : " + nameHitRecord.getWatchListTypeCd());
					//result = NameCheckUtil.seriousRule(result, nameHitRecord.getWatchListSubTypeCd(), rankOfWatchListMap);
					result = NameCheckUtil.seriousRule(result, nameHitRecord.getWatchListSubTypeCd(), subWatchListTypeAndWatchListTypeMappingMap, rankOfWatchListMap);
				}
			}
		}
		logger.debug("getNameCheckTrueHitRouteRule : " + result);
		return result;
	}
	
	private String getNameCheckDetailTrueHitRouteRule(List<XNcscCaseResult> trueHitCaseResultRecord, Map<String, String> rankOfWatchListMap){
		String result = "";
		if(trueHitCaseResultRecord != null && trueHitCaseResultRecord.size() > 0){
			for(XNcscCaseResult xncscCaseResult : trueHitCaseResultRecord){
				String uniqueKey = xncscCaseResult.getId().getUniqueKey();
				int ncReferenceId = xncscCaseResult.getId().getNcReferenceId();
				String checkSeq = xncscCaseResult.getCheckSeq();
				String seq = String.valueOf(xncscCaseResult.getId().getSeq());
				NameHitRecord nameHitRecord = iNameHitRecordDao.findOne(new NameHitRecordPK(uniqueKey, ncReferenceId, checkSeq, seq));
				logger.debug(nameHitRecord.toString());
				if(nameHitRecord != null && nameHitRecord.getWatchListTypeCd() != null){
					logger.debug(result + " : " + nameHitRecord.getWatchListTypeCd());
					result = NameCheckUtil.sortRule(result, nameHitRecord.getWatchListTypeCd(), rankOfWatchListMap, SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
				}
			}
		}
		logger.debug("getNameCheckDetailTrueHitRouteRule : " + result);
		return result;
	}
	
	@Override
	public String closeCase(long caseRk, String sourceType, String ncResult, String closeReason) {
		logger.info("#####################CloseCase Begin#################\n");
		try{
			String TRUE_HIT = "True Hit";
			String workFlowCloseReason = TRUE_HIT;
			String NONE_HIT = "None Hit";
			if("Y".equals(closeReason)) {
				workFlowCloseReason = NONE_HIT;
			}
			String trueHitCheckResult = SwiftMtConst.CASE_RESULT_TRUEFIT;
			List<XNcscCaseResult> trueHitCaseResultRecord = iXNcscCaseResultDao.findByIdCaseRkAndCheckResult(caseRk, trueHitCheckResult);
			Map<String, String> rankOfWatchListMap = getRankOfWatchListMap();
			if(sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_RT_SC) || sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_BT_SC)){
				List<SwiftCheckRecord> recordList = iSwiftCheckRecordDao.findByCaseRk(caseRk);
				if(recordList!=null && recordList.size()>0){
					SwiftCheckRecord record = recordList.get(0);
					//回壓案件調查結束後，true hit的資料中，全部的名單分類
					if(trueHitCaseResultRecord.size()>0&&trueHitCaseResultRecord!=null){
						String routeRule = getSwiftCheckTrueHitRouteRule(trueHitCaseResultRecord, rankOfWatchListMap);
						record.setRouteRule(routeRule);
					}else{
						record.setRouteRule("");
					}
					//workFlow NC
					record.setNcResult(ncResult); 
					record.setNcCloseReason(workFlowCloseReason);
					iSwiftCheckRecordDao.save(record);
					amlComboWhitelistCase.createHitComboWhiteListCaseSC(caseRk, record.getPartyNumber(), record.getBranchNumber());
					
					if(sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_RT_SC)){
						SwiftCheckStatusInputBean input = new SwiftCheckStatusInputBean();
						input.setCallingSystem(record.getCallingSystem());
						input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_SWIFTCHECK_STATUS);
						input.setNcReferenceId(String.valueOf(record.getId().getReferenceId()));
						input.setTransactionDate(record.getTransactionDate());
						input.setUniqueKey(record.getId().getUniqueKey());
						SwiftCheckStatusOutputBean output = amlSwiftCheckStatus.SwiftCheckStatus(input);
						
						// BOT SWIFT AC SWALLOW
						SwiftCheckRecord newRecord = iSwiftCheckRecordDao.findOne(new SwiftCheckRecordPK(record.getId().getUniqueKey(), record.getId().getReferenceId()));
						
						CaseLive nowCase = iCaseLiveDao.findOne(new BigDecimal(record.getCaseRk()));
						SwiftCheckReportInputBean swiftCheckReportInputBean = new SwiftCheckReportInputBean();
						swiftCheckReportInputBean.setNcReferenceId(output.getNcReferenceId() != null ? output.getNcReferenceId() : "");
						swiftCheckReportInputBean.setUniqueKey(output.getUniqueKey() != null ? output.getUniqueKey() : "");
						swiftCheckReportInputBean.setBranchNumber(record.getBranchNumber() != null ? newRecord.getBranchNumber() : "");
						swiftCheckReportInputBean.setUnit(record.getBusinessUnitId() != null ? newRecord.getBusinessUnitId() : "");
						swiftCheckReportInputBean.setNcResult(record.getNcResult() != null ? newRecord.getNcResult() : "");
						swiftCheckReportInputBean.setHitListSession(output.getRouteRule() != null ? output.getRouteRule() : "");
						swiftCheckReportInputBean.setNcCaseId(nowCase != null ? nowCase.getCaseId() : "");
						swiftCheckReportInputBean.setNcCaseStatus(record.getNcResult() != null ? newRecord.getNcResult() : "");
			    		
						logger.info("##################### Close Case BOT Swift AC To SWALLOW #####################\n");
						boolean isSuccess = AcSwallowSwiftStatus(swiftCheckReportInputBean, 0);
						logger.info("============ Close Case AC To SWALLOW isSuccess :::: "+ isSuccess +" ===============\n" );
					}
					else{
						logger.warn("sourceType=" + sourceType + ", sourceType not in (RT-SC), ignore amlSwiftCheckStatus.SwiftCheckStatus() and send message to mq");
					}
				}
				else{
					logger.warn("can't find SwiftCheckRecord by caseRk:" + caseRk);
				}
			} else if(sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_OL_PRICE)) {
				CaseLive nowCase = iCaseLiveDao.findOne(new BigDecimal(caseRk));
				String caseResult = ncResult;//SwiftMtConst.NC_RESULT_PASS.equals(ncResult) ? "Y" : SwiftMtConst.NC_RESULT_FAIL.equals(ncResult) ? "N" : "";
				if(nowCase != null) {
					String uniqueKey = "";
					if(SwiftMtConst.SUB_CATEGORY_TYPE_IV_EX.equals(nowCase.getCaseSubcategoryCd())) {
						XInvExportCaseStatus caseStatus = iXInvExportCaseStatusDao.findByCaseRk(caseRk);
						uniqueKey = caseStatus.getId().getUniqueKey();
						caseStatus.setCaseStatus(caseResult);
						iXInvExportCaseStatusDao.save(caseStatus);
					} else  if(SwiftMtConst.SUB_CATEGORY_TYPE_IV_IM.equals(nowCase.getCaseSubcategoryCd())) {
						XInvImportCaseStatus caseStatus = iXInvImportCaseStatusDao.findByCaseRk(caseRk);
						uniqueKey = caseStatus.getId().getUniqueKey();
						caseStatus.setCaseStatus(caseResult);
						iXInvImportCaseStatusDao.save(caseStatus);
					}
					
					BillLadingsInvoiceStatusInputBean input = new BillLadingsInvoiceStatusInputBean();
					input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_BLINVSTATUS);
					input.setUniqueKey(uniqueKey);
					
					BillLadingsInvoiceStatusOutputBean output = amlBLInvStatus.amlBLInvStatus(input);
				}
			}
			else{
				List<NameCheckRecordMain> recordList = iNameCheckRecordMainDao.findByCaseRk(caseRk);
				if(recordList!=null && recordList.size()>0){
					NameCheckRecordMain record = recordList.get(0);
					//PEP���毀�嚉擐砍�滚鱓
					CaseUdfCharValue pnmp = iCaseUdfCharValueDao.findFirstByIdCaseRkAndIdUdfNameOrderByIdValidFromDttmDesc(caseRk, "X_NCSC_PNMP_FLAG");
					CaseUdfCharValue pep = iCaseUdfCharValueDao.findFirstByIdCaseRkAndIdUdfNameOrderByIdValidFromDttmDesc(caseRk, "X_NCSC_PEP_FLAG");
					logger.debug("PEP CASE RK: {}", caseRk);
					logger.debug("PEP : {}", pep);
					logger.debug("pnmp : {}", pnmp);
					if(pep != null) {
						record.setPepFlag(pep.getUdfValue());
					}
					if(pnmp != null) {
						record.setPnmpFlag(pnmp.getUdfValue());
					}
					//回壓案件調查結束後，true hit的資料中，全部的名單分類
					if(trueHitCaseResultRecord.size()>0&&trueHitCaseResultRecord!=null){
						String routeRule = getNameCheckMainTrueHitRouteRule(trueHitCaseResultRecord, rankOfWatchListMap);
						record.setRouteRule(routeRule);
					}else{
						record.setRouteRule("");
					}
					record.setNcResult(ncResult);  
					record.setNcCloseReason(workFlowCloseReason);
					iNameCheckRecordMainDao.save(record);
					amlComboWhitelistCase.createHitComboWhiteListCaseNC(caseRk, record.getPartyNumber(), record.getBranchNumber());
					
					//蝣箏�朞府蝑�checkSeq�糓�炏��匧𦶢銝�
					List<NameCheckRecordDetail> details = iNameCheckRecordDetailDao.findByIdUniqueKeyAndIdNcReferenceId(record.getId().getUniqueKey(), record.getId().getNcReferenceId());
					if(details != null && details.size() > 0) {
						for(NameCheckRecordDetail detail : details) {
							if(StringUtils.isNotBlank(detail.getRouteRule())){
								Long trueHitCnt = iXNcscCaseResultDao.countByIdCaseRkAndIdNcReferenceIdAndIdUniqueKeyAndCheckSeqAndCheckResult(
										caseRk, detail.getId().getNcReferenceId(), detail.getId().getUniqueKey(), detail.getId().getCheckSeq(), "T");
								List<XNcscCaseResult> xncscCaseResultList = iXNcscCaseResultDao.findByIdCaseRkAndIdNcReferenceIdAndIdUniqueKeyAndCheckSeqAndCheckResult(caseRk, detail.getId().getNcReferenceId(), detail.getId().getUniqueKey(), detail.getId().getCheckSeq(), trueHitCheckResult);
								if(trueHitCaseResultRecord.size()>0&&trueHitCaseResultRecord!=null){
									String tmpRouteRule = getNameCheckDetailTrueHitRouteRule(xncscCaseResultList, rankOfWatchListMap);
									detail.setRouteRule(tmpRouteRule);
								}else{
									detail.setRouteRule("");
								}
								detail.setCheckResult(trueHitCnt > 0 ? SwiftMtConst.NC_RESULT_TRUE_HIT: SwiftMtConst.NC_RESULT_FALSE_HIT);
								iNameCheckRecordDetailDao.save(detail);
							}
						}
					}
					
					if(sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_RT_TC) || sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_RT_NC)){
						NameCheckStatusInputBean input = new NameCheckStatusInputBean();
						input.setCallingSystem(record.getCallingSystem());
//						input.setInterfaceName(record.getInterfaceName());
						input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_NAMECHECK_STATUS);
						input.setNcReferenceId(String.valueOf(record.getId().getNcReferenceId()));
						input.setReferenceNumber(String.valueOf(record.getReferenceNumber()));
						input.setTransactionDate(record.getTransactionDate());
						input.setUniqueKey(record.getId().getUniqueKey());
						NameCheckStatusOutputBean output = amlNameCheckStatus.NameCheckStatus(input);
					}else if(sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_BT_NC)){ // 憭𨀣鸌����讛矽�䰻蝯鞉�𣈯�誯�穽Q�䲮撘誩�𧼮��						

						BatchNameCheckingOutputRealTimeBean output = new BatchNameCheckingOutputRealTimeBean();				
						output.setInterface_name(SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECKSTATUS); //Interface_Name
						output.setParty_number(record.getPartyNumber());  //PartyNumber
						
						NameCheckStatusInputBean input = new NameCheckStatusInputBean();
						input.setCallingSystem(record.getCallingSystem());
//						input.setInterfaceName(record.getInterfaceName());
						input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_NAMECHECK_STATUS);
						input.setNcReferenceId(String.valueOf(record.getId().getNcReferenceId()));
						input.setReferenceNumber(String.valueOf(record.getReferenceNumber()));
						input.setTransactionDate(record.getTransactionDate());
						input.setUniqueKey(record.getId().getUniqueKey());
						NameCheckStatusOutputBean nameCheckStatusOutputBe = amlNameCheckStatus.NameCheckStatus(input);							
						
						output.setNc_case_id(nameCheckStatusOutputBe.getNcCaseId());  		
						output.setNc_case_status(nameCheckStatusOutputBe.getNcCaseStatus());
						output.setNc_close_reason(nameCheckStatusOutputBe.getNcCloseReason());
//						output.setPnmp_flag(nameCheckStatusOutputBe.getPnmpFlag());
//						output.setPep_flag(nameCheckStatusOutputBe.getPepFlag());
					
						for(NameCheckStatusOutputDetailBean be : nameCheckStatusOutputBe.getSeq()){
							String [] checkSeqSpilit = be.getCheckSeq().split("_!_");
							if(checkSeqSpilit.length > 1){  //隞�”��厰�靝�
								output.setRelated_party_number(output.getParty_number());
								output.setParty_number(checkSeqSpilit[1]);
							}
						}
					}else if(sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_OL_BOL) || sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_OL_INV)){
						BillLadingsInvoiceStatusInputBean input = new BillLadingsInvoiceStatusInputBean();
						input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_BLINVSTATUS);
						input.setUniqueKey(record.getId().getUniqueKey());
						
						BillLadingsInvoiceStatusOutputBean output = amlBLInvStatus.amlBLInvStatus(input);
					}else{
						logger.warn("sourceType=" + sourceType + ", sourceType not in (RT-TC, RT-NC, BT_NC), ignore amlNameCheckStatus.NameCheckStatus() and send message to mq");
					}
					
					//Name Check 相關才需呼叫EAI Service
					if (StringUtils.equalsIgnoreCase(sourceType,
							SwiftMtConst.SOURCE_TYPE_BT_NC)
							|| StringUtils.equalsIgnoreCase(sourceType,
									SwiftMtConst.SOURCE_TYPE_RT_NC)) {
						CaseLive nowCase = iCaseLiveDao.findOne(new BigDecimal(
								caseRk));
						EAIServiceInputBean eaiServiceInputBean = new EAIServiceInputBean();
						InputHeader header = new InputHeader();

						TrxSvcRq trxSvcRq = new TrxSvcRq();
						TrxRq trxRq = new TrxRq();
						trxRq.setLastUpdateDate("");
						trxRq.setLastUpdateTime("");
						trxRq.setDataValue("");
						trxSvcRq.setTrxRq(trxRq);
						eaiServiceInputBean.setTrxSvcRq(trxSvcRq);
						eaiServiceInputBean.setModule(sourceType);
						eaiServiceInputBean.setHeader(header);

						header.setClientAppSeq(UUID.randomUUID().toString()
								.replaceAll("-", ""));
						header.setMsgId(AmlConfiguration
								.getString(SwiftMtConst.COM_EAI_SERVICE_MSGID));
						header.setServerAppSeq("");

						trxRq.setCustPermId(record.getPartyNumber());
						trxRq.setDataContentCode("1017");// Name Check :1017
						// 0 : 案件調查結案，但調查結果為取消交易 (Cancelled)
						// Y：有CASE調查結案，且真命中 (Any true hit) or
						// N：所有的CASE結案，且未命中 (ALL false hit)
						
						if (StringUtils.equals(
								SwiftMtConst.NC_RESULT_CANCELLED, ncResult))
							trxRq.setDataValue("0");
						else if (StringUtils.equals(
								SwiftMtConst.NC_RESULT_PASS, ncResult)
								|| StringUtils.equals(
										SwiftMtConst.NC_RESULT_FAIL, ncResult)){
							if (StringUtils.equals(NONE_HIT, record.getNcCloseReason()))
								trxRq.setDataValue("N");
							else if (StringUtils.equals(TRUE_HIT, record.getNcCloseReason()))
								trxRq.setDataValue("Y");
						}
							
						if (nowCase.getCloseDttm() != null){
							trxRq.setLastUpdateDate(DateUtil.getNowDate(
									nowCase.getCloseDttm(), "yyyyMMdd"));
							trxRq.setLastUpdateTime(DateUtil.getNowDate(
									nowCase.getCloseDttm(), "HHmmss"));
						}
						
						trxRq.setRefID(nowCase.getCaseId());

						callEaiServiceSendRecv(eaiServiceInputBean, 0);
					}
				}
				else{
					logger.warn("can't find NameCheckRecordMain by caseRk:" + caseRk);
				}
			}
		}
		catch(Exception ex){
			logger.error("saveCaseResult", ex);
			return "error";
		}
		return "success";
	}
	
	@SuppressWarnings("unused")
	public boolean AcSwallowSwiftStatus(SwiftCheckReportInputBean swiftCheckReportInputBean, long id){  
    	boolean isOk = false;
    	int retryCount = 0;
    	try {
    		// JAX-WS
			URL swiftChkRptUrl = new URL(AmlConfiguration.getString(SwiftMtConst.COM_SAS_SWIFTCHECKREPORT_URL));

			AmlSwiftCheckReportImplService amlSwiftCheckReportImplService = new AmlSwiftCheckReportImplService(swiftChkRptUrl);			
			if(amlSwiftCheckReportImplService != null){
				AmlSwiftCheckReport port = amlSwiftCheckReportImplService.getAmlSwiftCheckReportImplPort();
				HTTPConduit httpConduit = (HTTPConduit) ClientProxy.getClient(port).getConduit();
				TLSClientParameters tlsCP = new TLSClientParameters();
				// other TLS/SSL configuration like setting up TrustManagers
				tlsCP.setDisableCNCheck(true);
				httpConduit.setTlsClientParameters(tlsCP);

				logger.info("\n ---------------------- Request ----------------------- \n "
						+ ToStringBuilder.reflectionToString(swiftCheckReportInputBean, ToStringStyle.SHORT_PREFIX_STYLE)
						+ "\n ---------------------- swiftCheckReportInputBean ----------------------- \n "
				);
				if (port != null) {
					SwiftCheckReportOutputBean swiftCheckReportOutputBean = port.swiftCheckReport(swiftCheckReportInputBean);
					logger.info("\n ---------------------- Response ----------------------- \n "
							+ ToStringBuilder.reflectionToString(swiftCheckReportOutputBean, ToStringStyle.SHORT_PREFIX_STYLE)
							+ "\n---------------------- swiftCheckReportOutputBean ----------------------- \n "
					);
				
					if( swiftCheckReportOutputBean != null ) {
						isOk = true;
					} else {
						XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.saveEntityToXRetryEventQueue(SwiftMtConst.SOURCE_TYPE_RT_SC, SwiftCheckReportInputBean.class.getName(), 
								swiftCheckReportInputBean,  HttpMethod.POST, 
								AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) + context.getContextPath() + "/rest/reportSwiftCheckStatus",
								3, "reportSwiftCheckStatus", id, QueueConstraint.QUEUE_EVENT_STATUS_WAITING, false, "");
						if(xRetryEventQueue == null) {
							logger.error("Retry id insert fail");
						}
					}
				} else {
					logger.error("Load AmlSwiftCheckReportImplService fail !");
					XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.saveEntityToXRetryEventQueue(SwiftMtConst.SOURCE_TYPE_RT_SC, SwiftCheckReportInputBean.class.getName(), 
							swiftCheckReportInputBean,  HttpMethod.POST, 
							AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) + context.getContextPath() + "/rest/reportSwiftCheckStatus",
							3, "reportSwiftCheckStatus", id, QueueConstraint.QUEUE_EVENT_STATUS_WAITING, false, "");
					if(xRetryEventQueue == null) {
						logger.error("Retry id insert fail");
					}
					return isOk;
					
				}
			}else{
				logger.error("AcSwallowSwiftStatus amlSwiftCheckReportImplService is null");
			}
		} catch (Exception ex) {
			logger.error(String.format("doSwiftAsyncCheck fail, exception : ", ex.getMessage()), ex);
			XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.saveEntityToXRetryEventQueue(SwiftMtConst.SOURCE_TYPE_RT_SC, SwiftCheckReportInputBean.class.getName(), 
					swiftCheckReportInputBean,  HttpMethod.POST, 
					AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) + context.getContextPath() + "/rest/reportSwiftCheckStatus",
					3, "reportSwiftCheckStatus", id, QueueConstraint.QUEUE_EVENT_STATUS_WAITING, false, "");
			if(xRetryEventQueue == null) {
				logger.error("Retry id insert fail");
			}
			return isOk;
		}
          
        return isOk;  
    }  
	
	
	@Override
	@Transactional
	public BigDecimal createCase(CreateCaseInputBean input)  {
		//=====create Case Start=====
		BigDecimal caseRK = iCaseRkSeqDao.getCaseRkSeqNext();  //��硋�䊼aseRK
		caseRK = caseRK.setScale(0);
		input.setCaseRk(caseRK.longValue());
		Date nowDate = new Date();
		input.setValidFromDttm(nowDate);
		//�𪄳��case type�摸CKINV 銝� CategoryCd = sourceType ��caseConfig
		//銋见�屸�閬��訜��肽身摰𡁶� 閰猓ype�鍂ui-definition��𩥅orkflow
		CaseConfig caseConfig = caseConfigDao.findByCaseTypeCdAndCaseCategoryCd(SwiftMtConst.NCSC_CASE_TYPE, input.getSourceType());
		//�𪄳���訜��肽身摰𡁜虾隞乩蝙�鍂��User蝢斤�
		List<CaseConfigXUserGroup> caseUserGroups = iCaseConfigXUserGroupDao.findByIdCaseConfigSeqNo(caseConfig.getCaseConfigSeqNo());
		//�鰵撱慢ASE
		
		CaseLive caseLive = newCaseLive(caseRK, nowDate, input, caseConfig);
		List<Map<String, Object>> hitRecord = caseCustomDao.queryCheckHit(input.getSourceType(), input.getUniqueKey(), input.getNcReferenceId());	
		//��硋�堒恥鋆賢�𣇉黎蝯�
		//List<String> newCustomerGroups = getCustomerGroups(input);
		List<String> newCustomerGroups = null;
		//獢�辣撱箇�见�峕�銋罸�閬�撱箇�讠洵銝�����version
		
		newCaseVersion(caseLive);
		//�虾隞乩蝙�鍂��User Group嚗屸�躰ㄐ�凒�𦻖敺𧼮�𥕦�𥟇��枂靘���蝢斤�銴�ˊ銝�隞賡�𤾸縧
		newCaseXUserGroup(caseRK, caseUserGroups, newCustomerGroups);
		//撱箇�𨶙CSC CASE ��閬���銝餉�鞈���(�𧞄�𢒰銝𦠜䲮鞈���)
		createCaseTitle(input);
		//撱箇�𨶙CSC HIT RECORD��鞈���(�𧞄�𢒰銝𧢲䲮GRID鞈���)
		saveHitCharUdf(caseRK, nowDate, hitRecord);
		//=====create Case End=====
		
		//=====create Incident Start=====
		if(input.isCreateIncident()) {
			createIncident(caseRK, nowDate, input);
		}
		//		triggerWorkFlow(caseRK.toString());
		//=====create Incident End=====
		return caseRK;
	}
	
//	@Transactional	
	@Override
	public BigDecimal createIncident(BigDecimal caseRK,  CreateCaseInputBean input) {
		return createIncident(caseRK, new Date(), input);
	}
	
	@Override
	public BigDecimal createIncident(BigDecimal caseRK, Date nowDate, CreateCaseInputBean input) {
		//�躰ㄐ憭扯稲銝𡃏��遣蝡𠵾ASE銝�璅��峕�����枂�訜��肽身摰𡁶�config瑼䈑�𣬚�嗅�䔶�萘�批遣蝡𡃇ncident��閬���鞈��𠹺�嘥�誩‵�脣縧
		BigDecimal incidentRk = iIncidentRkSeqDao.getIncidentRkSeqNext(); //��硋�鎸ncidentRk
		incidentRk = incidentRk.setScale(0);
		IncidentConfig incidentConfig = iIncidentConfigDao.findByIncidentTypeCdAndIncidentCategoryCd(SwiftMtConst.NCSC_INCIDENT_TYPE, input.getSourceType());
		IncidentLive incidentLive = newIncidentLive(caseRK, incidentRk, nowDate, input, incidentConfig);
		List<IncidentConfigXUserGroup> incidentConfigXUserGroups = iIncidentConfigXUserGroupDao.findByIdIncidentConfigSeqNo(incidentConfig.getIncidentConfigSeqNo());
		// To Get Common String 
		String startWith = AmlConfiguration.getString("com.sas.create.customer.group");
		// To Get Dept_no By empNo(calling User)
		AccountIntegration account = iAccountIntegrationDao.findOne(input.getCallingUser());
		// To Generate New Custom User Group String 
		String newCustomerGroup = account != null ? String.format(startWith, account.getDeptNo()) : null;
		
		newIncidentXUserGroup(incidentRk, incidentConfigXUserGroups, newCustomerGroup);
		newIncidentVersion(incidentLive);
		newIncidentCharUdf(incidentLive, input);
		return incidentRk;
	}
	
	private CaseLive newCaseLive(BigDecimal caseRK, Date nowDate, CreateCaseInputBean input, CaseConfig caseConfig) {
		CaseLive caseLive = new CaseLive();
		String caseIdFormat = "%s-%s";
		String ScreenProccess = "";
		String CallingSystem = "";
		
		if("SC".equalsIgnoreCase(input.getSourceType().split("-")[1])) {
			SwiftCheckRecord swiftCheckRecord = getSwiftCheckRecord(input);
			ScreenProccess = getRefTransString(SwiftMtConst.REF_TABLE_NM_X_SCREEN_PROCESS, swiftCheckRecord.getScreenProcess());
			CallingSystem = getRefTransString(SwiftMtConst.REF_TABLE_NM_X_CALLING_SYSTEM, swiftCheckRecord.getCallingSystem());
			String income_outgo = getIncomeOrOutGoing(swiftCheckRecord.getSwiftFullText());
			String swiftTypeAndIncome_outgo = String.format("%s-%s", swiftCheckRecord.getSwiftType(),  income_outgo);
			//caseLive.setCaseDesc(String.format(SwiftMtConst.NCSC_SC_CASE_DESC_TPL, swiftCheckRecord.getReferenceNumber(), ScreenProccess, CallingSystem, swiftTypeAndIncome_outgo));
			caseLive.setCaseDesc(String.format(SwiftMtConst.NCSC_SC_CASE_DESC_TPL, input.getUniqueKey(), swiftCheckRecord.getId().getReferenceId(),  swiftCheckRecord.getScreenProcess(), swiftTypeAndIncome_outgo, swiftCheckRecord.getCallingSystem() ));	
		} else {
			NameCheckRecordMain nameCheckRecord = getNameCheckRecord(input);
			ScreenProccess = nameCheckRecord.getScreenProcess();
			ScreenProccess = getRefTransString(SwiftMtConst.REF_TABLE_NM_X_SCREEN_PROCESS, ScreenProccess);
			CallingSystem = getRefTransString(SwiftMtConst.REF_TABLE_NM_X_CALLING_SYSTEM, nameCheckRecord.getCallingSystem());
			//caseLive.setCaseDesc(String.format(SwiftMtConst.NCSC_CASE_DESC_TPL, input.getReferenceNumber(), ScreenProccess, CallingSystem));
			caseLive.setCaseDesc(String.format(SwiftMtConst.NCSC_CASE_DESC_TPL, input.getUniqueKey(), nameCheckRecord.getId().getNcReferenceId(), ScreenProccess, nameCheckRecord.getCallingSystem()));
		}
		
//		caseLive.setCaseStatusCd("I");
		caseLive.setCaseRk(caseRK);
		caseLive.setCaseCategoryCd(caseConfig.getCaseCategoryCd());
		caseLive.setCaseTypeCd(caseConfig.getCaseTypeCd());
		caseLive.setCaseId(String.format(caseIdFormat, DateUtil.getNowWestYr(nowDate), caseRK.toString()));
		caseLive.setValidFromDttm(new Timestamp(nowDate.getTime()));
		
		caseLive.setCreateUserId(input.getCallingUser());
		caseLive.setCreateDttm(new Timestamp(nowDate.getTime()));
		caseLive.setUiDefFileNm(caseConfig.getUiDefFileNm());
		caseLive.setVersionNo(1);
		caseLive.setRegulatoryRptRqdFlg("0"); 
		caseLive.setDeleteFlg("0");
//		caseLive.setInvestigatorUserId(input.getCallingUser());
		caseLive.setSourceSystemCd(AmlConfiguration.getString("com.sas.case.source.system.cd"));
		iCaseLiveDao.save(caseLive);
		return caseLive;
	}
	
	private IncidentLive newIncidentLive(BigDecimal caseRK, BigDecimal incidentRk, Date nowDate, CreateCaseInputBean input,IncidentConfig incidentConfig) {
		String incidentIdFormat = "%s-%s";
		String ScreenProccess = "";
		if("SC".equalsIgnoreCase(input.getSourceType().split("-")[1])) {
			SwiftCheckRecord swiftCheckRecord = getSwiftCheckRecord(input);
			ScreenProccess = swiftCheckRecord.getScreenProcess();
		} else {
			NameCheckRecordMain nameCheckRecord = getNameCheckRecord(input);
			ScreenProccess = nameCheckRecord.getScreenProcess();
		}
		IncidentLive incidentLive = new IncidentLive();
		incidentLive.setCaseRk(caseRK);
		incidentLive.setIncidentRk(incidentRk);
		incidentLive.setIncidentId(String.format(incidentIdFormat, DateUtil.getNowWestYr(nowDate), caseRK!=null?caseRK.toString():""));
		incidentLive.setCreateDttm(new Timestamp(nowDate.getTime()));
		incidentLive.setIncidentTypeCd(incidentConfig.getIncidentTypeCd());
		incidentLive.setIncidentCategoryCd(incidentConfig.getIncidentCategoryCd());
		//incidentLive.setIncidentDesc(String.format(SwiftMtConst.NCSC_INCIDENT_DESC_TPL, input.getReferenceNumber(), ScreenProccess));
		incidentLive.setIncidentDesc(String.format(SwiftMtConst.NCSC_INCIDENT_DESC_TPL, input.getUniqueKey()));
		incidentLive.setValidFromDttm(new Timestamp(nowDate.getTime()));
		incidentLive.setCreateUserId(input.getCallingUser());
		incidentLive.setUiDefFileNm(incidentConfig.getUiDefFileNm());
		incidentLive.setVersionNo(1);
		incidentLive.setDeleteFlg("0");
		incidentLive.setSourceSystemCd(AmlConfiguration.getString("com.sas.case.source.system.cd"));
		iIncidentLiveDao.save(incidentLive);
		return incidentLive;
	}
	
	private CaseVersion newCaseVersion(CaseLive caseLive) {
		CaseVersion caseVersion = new CaseVersion(); 
		CaseVersionPK pk = new CaseVersionPK();
		caseVersion.setId(pk);
		
		pk.setCaseRk(caseLive.getCaseRk());
		pk.setValidFromDttm(caseLive.getValidFromDttm());
		caseVersion.setCaseCategoryCd(caseLive.getCaseCategoryCd());
		caseVersion.setCaseDesc(caseLive.getCaseDesc());
		caseVersion.setCaseId(caseLive.getCaseId());
		caseVersion.setCaseTypeCd(caseLive.getCaseTypeCd());
		caseVersion.setCreateDttm(caseLive.getCreateDttm());
		caseVersion.setCreateUserId(caseLive.getCreateUserId());
		caseVersion.setDeleteFlg(caseLive.getDeleteFlg());
		caseVersion.setUiDefFileNm(caseLive.getUiDefFileNm());
		caseVersion.setRegulatoryRptRqdFlg(caseLive.getRegulatoryRptRqdFlg());
		caseVersion.setSourceSystemCd(caseLive.getSourceSystemCd());
		caseVersion.setVersionNo(1);
		
		
		iCaseVersionDao.save(caseVersion);
		return caseVersion;
	}
	
	private IncidentVersion newIncidentVersion(IncidentLive incidentLive) {
		IncidentVersion version = new IncidentVersion();
		IncidentVersionPK pk = new IncidentVersionPK();
		pk.setIncidentRk(incidentLive.getIncidentRk());
		pk.setValidFromDttm(incidentLive.getValidFromDttm());
		version.setId(pk);
		version.setCaseRk(incidentLive.getCaseRk());
		version.setIncidentCategoryCd(incidentLive.getIncidentCategoryCd());
		version.setIncidentDesc(incidentLive.getIncidentDesc());
		version.setIncidentId(incidentLive.getIncidentId());
		version.setIncidentTypeCd(incidentLive.getIncidentTypeCd());
		version.setCreateDttm(incidentLive.getCreateDttm());
		version.setCreateUserId(incidentLive.getCreateUserId());
		version.setDeleteFlg(incidentLive.getDeleteFlg());
		version.setUiDefFileNm(incidentLive.getUiDefFileNm());
		version.setSourceSystemCd(incidentLive.getSourceSystemCd());
		version.setVersionNo(1);
		
		iIncidentVersionDao.save(version);
		return version;
	}
	
	
	private void newCaseXUserGroup(BigDecimal caseRK, List<CaseConfigXUserGroup> caseUserGroups, List<String> customerGroup) {
		List<CaseXUserGroup> userGroups = new ArrayList<CaseXUserGroup>();
		CaseXUserGroup saveGroup = null;
		CaseXUserGroupPK pk = null;
		for(CaseConfigXUserGroup configGroup : caseUserGroups) {
			saveGroup = new CaseXUserGroup();
			pk = new CaseXUserGroupPK();
			saveGroup.setId(pk);
			pk.setCaseRk(caseRK);
			pk.setUserGroupNm(configGroup.getId().getUserGroupNm());
			userGroups.add(saveGroup);
		}
		
		// To Add Customer User Group
		if(customerGroup != null && customerGroup.size() > 0){
			for(String group : customerGroup) {
				saveGroup = new CaseXUserGroup();
				pk = new CaseXUserGroupPK();
				saveGroup.setId(pk);
				pk.setCaseRk(caseRK);
				pk.setUserGroupNm(group);
				userGroups.add(saveGroup);
			}
		}
			
		iCaseXUserGroupDao.save(userGroups);
	}
	
	private void newIncidentXUserGroup(BigDecimal incidentRk , List<IncidentConfigXUserGroup> incidentUserGroups, String customerGroup) {
		List<IncidentXUserGroup> userGroups = new ArrayList<IncidentXUserGroup>();
		IncidentXUserGroup saveGroup = null;
		IncidentXUserGroupPK pk = null;
		for(IncidentConfigXUserGroup configGroup : incidentUserGroups) {
			saveGroup = new IncidentXUserGroup();
			pk = new IncidentXUserGroupPK();
			saveGroup.setId(pk);
			pk.setIncidentRk(incidentRk);
			pk.setUserGroupNm(configGroup.getId().getUserGroupNm());
			userGroups.add(saveGroup);
		}
		
		// To Add Customer User Group
		if(customerGroup != null && !"".endsWith(customerGroup)){
			saveGroup = new IncidentXUserGroup();
			pk = new IncidentXUserGroupPK();
			saveGroup.setId(pk);
			pk.setIncidentRk(incidentRk);
			pk.setUserGroupNm(customerGroup);
			userGroups.add(saveGroup);
		}
		
		iIncidentXUserGroupDao.save(userGroups);
	}
	
	private void newIncidentCharUdf(IncidentLive incidentLive, CreateCaseInputBean input) {
		List<IncidentUdfCharValue> icUdfCharValueList = new ArrayList<IncidentUdfCharValue>();
		Date date =incidentLive.getValidFromDttm();
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_NC_REFERENCE_ID", input.getNcReferenceId()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_CALLING_SYSTEM", input.getCallingSystem()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_CALLING_USER", input.getCallingUser()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_BRANCH_NUMBER", input.getBranchNumber()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_ROUTE_RULE", input.getRouteRule()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_TRANSACTION_DATE", input.getTransactionDate()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_HIT_SEQ", input.getHitSeq()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_PARTY_NO", input.getPartyNo()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_REFERENCE_NUMBER", input.getReferenceNumber()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_UNIQUE_KEY", input.getUniqueKey()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_SCREEN_PROCESS", input.getScreenProcess()));
		icUdfCharValueList.add(toIncidentCharUdfValue(incidentLive.getIncidentRk(), date, 1, "X_NCSC_SOURCE_TYPE", input.getSourceType()));
		
		iIncidentUdfCharValueDao.save(icUdfCharValueList);
	}
	
	private void saveHitCharUdf(BigDecimal caseRK, Date nowDate, List<Map<String, Object>> hitRecords) {
		List<CaseUdfCharValue> caseUdfCharValueList = new ArrayList<CaseUdfCharValue>();
		List<XNcscCaseResult> ncscCaseResultList = new ArrayList<XNcscCaseResult>();
		XNcscCaseResult ncscCaseResult = null;
		XNcscCaseResultPK ncscCaseResultPk = null;
		Map<String, Object> row= null;
		int rowNo = 0;
		for(int i = 0; i < hitRecords.size(); i++) {
			row = hitRecords.get(i);
			rowNo = i+1;
//			caseUdfCharValueList.add(toCharUdfValue(caseRK, nowDate, rowNo, "X_NCSC_HIT_UNIQUE_KEY", row.get("UNIQUE_KEY")));
//			caseUdfCharValueList.add(toCharUdfValue(caseRK, nowDate, rowNo, "X_NCSC_HIT_HIT_SEQ", row.get("SEQ")));
//			caseUdfCharValueList.add(toCharUdfValue(caseRK, nowDate, rowNo, "X_NCSC_HIT_CHECK_SEQ", row.get("CHECK_SEQ")));
//			caseUdfCharValueList.add(toCharUdfValue(caseRK, nowDate, rowNo, "X_NCSC_HIT_CHECK_RESULT", row.get("CHECK_RESULT")));
//			caseUdfCharValueList.add(toCharUdfValue(caseRK, nowDate, rowNo, "X_NCSC_HIT_WHITELIST_IND", row.get("WHITELIST_IND")));
			
			ncscCaseResult = new XNcscCaseResult();
			ncscCaseResultPk = new XNcscCaseResultPK();
			ncscCaseResult.setId(ncscCaseResultPk);
			ncscCaseResult.setCheckSeq(row.get("CHECK_SEQ") != null ? row.get("CHECK_SEQ").toString() : null); 
			ncscCaseResultPk.setRowNo(rowNo);
			ncscCaseResultPk.setCaseRk(caseRK.longValue());
			ncscCaseResultPk.setNcReferenceId(Integer.valueOf(row.get("NC_REFERENCE_ID").toString()));
			ncscCaseResultPk.setSeq(Integer.valueOf(row.get("SEQ").toString()));
			ncscCaseResultPk.setUniqueKey(row.get("UNIQUE_KEY").toString());
			ncscCaseResultList.add(ncscCaseResult);
		}
		iCaseUdfCharValueDao.save(caseUdfCharValueList);
		iXNcscCaseResultDao.save(ncscCaseResultList);
	} 
	
	private CaseUdfCharValue toCharUdfValue(BigDecimal caseRK, Date nowDate, int rowNo, String udfNm,  Object value) {
		CaseUdfCharValue udf = new CaseUdfCharValue();
		CaseUdfCharValuePK udfPK = new CaseUdfCharValuePK();
		udfPK.setCaseRk(caseRK.longValue());
		udfPK.setValidFromDttm(nowDate);
		udfPK.setRowNo(rowNo);
		udfPK.setUdfTableName(UDF_TABLE_NAME);
		udfPK.setUdfName(udfNm);
		udf.setId(udfPK);
		udf.setUdfValue(value == null ? null : value.toString());
		return udf;
	}
	
	private IncidentUdfCharValue toIncidentCharUdfValue(BigDecimal incidentRK, Date nowDate, int rowNo, String udfNm,  Object value) {
		IncidentUdfCharValue udf = new IncidentUdfCharValue();
		IncidentUdfCharValuePK udfPK = new IncidentUdfCharValuePK();
		udfPK.setRowNo(rowNo);
		udfPK.setIncidentRk(incidentRK.longValue());
		udfPK.setValidFromDttm(nowDate);
		udfPK.setUdfTableNm(IC_UDF_TABLE_NAME);;
		udfPK.setUdfNm(udfNm);
		udf.setId(udfPK);
		udf.setUdfValue(value == null ? null : value.toString());
		return udf;
	}
	
	
	private SwiftCheckRecord getSwiftCheckRecord(CreateCaseInputBean input) {
		SwiftCheckRecordPK pk = new SwiftCheckRecordPK();
		pk.setReferenceId(Integer.valueOf(input.getNcReferenceId()));
		pk.setUniqueKey(input.getUniqueKey());
		return iSwiftCheckRecordDao.findOne(pk);
	}
	
	private NameCheckRecordMain getNameCheckRecord(CreateCaseInputBean input) {
		NameCheckRecordMainPK pk = new NameCheckRecordMainPK();
		pk.setNcReferenceId(Integer.valueOf(input.getNcReferenceId()));
		pk.setUniqueKey(input.getUniqueKey());
		return iNameCheckRecordMainDao.findOne(pk);
	}

	@Override
	public String getSwiftFullText(Long caseRk) {
		List<SwiftCheckRecord> swiftCheckRecords = iSwiftCheckRecordDao.findByCaseRk(caseRk);
		if(swiftCheckRecords != null && swiftCheckRecords.size() > 0) {
			StringBuffer bf = new StringBuffer();			
			bf.append("<pre>"); 
			bf.append(swiftCheckRecords.get(0).getSwiftFullText());
			bf.append("</pre>");
			return bf.toString();
		} else {
			return "";
		}
	}	
	
	public Map<String, BigDecimal > getRouteRuleOrder(){
		Map<String, BigDecimal > result = new HashMap<String, BigDecimal>();
		
		List<RefTableValue> watchListCdLst = iRefTableValueDao.findByIdRefTableNm(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
		if(watchListCdLst != null && watchListCdLst.size() > 0) {
			for(RefTableValue refTable : watchListCdLst) {
				result.put(refTable.getId().getValueCd(), refTable.getDisplayOrderNo());
			}
		}
		
		return result;
	}
	
	private String getRefTransString(String refTableName, String code) {
		List<FullRefTableTran> refTableList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNmAndIdValueCd(SwiftMtConst.HK_DEFAULT_LOCALE, 
				refTableName, code);
		
		if(refTableList != null  && refTableList.size() > 0) {
			return refTableList.get(0).getValueDesc();
		} else {
			return code;
		} 
	}
	
	private List<String> getCustomerGroups(CreateCaseInputBean input) {
		List<String> result = new ArrayList<String>();
		// To Get Common String 
		String startWith = AmlConfiguration.getString("com.sas.create.customer.group");
		// To Get Dept_no By empNo(calling User)
//		AccountIntegration account = iAccountIntegrationDao.findOne(input.getCallingUser());
		// To Generate New Custom User Group String 
//		if(account != null ) result.add(String.format(startWith, account.getDeptNo()));
		//getCallingSystemUserGroup
		List<CallingSystemXUserGroup> callingSystemCdUserGroup = iCallingSystemXUserGroupDao.findByIdCallingSystemCd(input.getCallingSystem());
		if(callingSystemCdUserGroup != null && callingSystemCdUserGroup.size() > 0 ) {
			for(CallingSystemXUserGroup usrGroup : callingSystemCdUserGroup) {
				result.add(usrGroup.getId().getUserGroupNm());
			}
		}
		return  result;
	}
	
	private String getIncomeOrOutGoing(String fullText){
		SwiftMessage swiftMessage = null;
		try {
			swiftMessage = AbstractMT.parse(fullText).getSwiftMessage();
		} catch (Exception e) {
			logger.error("getIncomeOrOutGoing", e);
			return "";
		}
		if(swiftMessage.isIncoming()) {
			return SwiftMtConst.SWIFT_INCOMING;
		}
		if(swiftMessage.isOutgoing()) {
			return SwiftMtConst.SWIFT_OUTGOING;
		}
		return "";		
	}
	
	private void scIsOpenComboWhite(String sourceType, List<QueryHitRecordBean> hitRecords, String partyNo, String swiftType) {
		String screen = AmlConfiguration.getString(SwiftMtConst.COMBOWHITE_SCREEN_PROP);
		String allowSwiftType = AmlConfiguration.getString(SwiftMtConst.COMBOWHITE_SWIFT_TYPE_PROP);
		List<String> screenLst = Arrays.asList(screen.split(","));
		List<String> allowSwiftTypeLst = Arrays.asList(allowSwiftType.split(","));
		if(screenLst.contains(sourceType) && StringUtils.isNotBlank(partyNo)) {
			if(allowSwiftTypeLst.contains(swiftType)) {
				String allowTagName = AmlConfiguration.getString(String.format(SwiftMtConst.COMBOWHITE_SWIFT_TYPE_TAG_PROP,swiftType.toLowerCase()));
				List<String> allowTagNameLst = Arrays.asList(allowTagName.split(","));
				for(QueryHitRecordBean hit : hitRecords) {
					if(allowTagNameLst.contains(hit.getFieldName())) {
						hit.setIsNeedWhiteList(SwiftMtConst.COMBO_WHITE_LIST_Y);
					} else {
						hit.setIsNeedWhiteList(SwiftMtConst.COMBO_WHITE_LIST_N);
					}
				}
			}
		}
	}
	
	
	private void ncIsOpenComboWhite(String sourceType, List<QueryHitRecordBean> hitRecords, String partyNo) {
		String screen = AmlConfiguration.getString(SwiftMtConst.COMBOWHITE_SCREEN_PROP);
		String relation = AmlConfiguration.getString(SwiftMtConst.COMBOWHITE_NAMECHECK_RELATION_PROP);
		List<String> screenLst = Arrays.asList(screen.split(","));
		List<String> relationLst = Arrays.asList(relation.split(","));
		if(screenLst.contains(sourceType) && StringUtils.isNotBlank(partyNo)) {
			for(QueryHitRecordBean hit : hitRecords) {
				if(relationLst.contains(hit.getEntityRelationship())) {
					hit.setIsNeedWhiteList(SwiftMtConst.COMBO_WHITE_LIST_Y);
				} else {
					hit.setIsNeedWhiteList(SwiftMtConst.COMBO_WHITE_LIST_N);
				}
			}
		}
	}

	@Override
	public boolean callEaiServiceSendRecv(
			EAIServiceInputBean eaiServiceInputBean, long id) {
		return true;
	}

}