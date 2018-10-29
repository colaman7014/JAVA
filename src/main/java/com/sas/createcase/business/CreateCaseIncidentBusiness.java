package com.sas.createcase.business;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sas.db.aml.dao.ecm.ICaseLiveDao;
import com.sas.db.aml.dao.ecm.ICaseRkSeqDao;
import com.sas.db.aml.dao.ecm.ICaseVersionDao;
import com.sas.db.aml.dao.ecm.ICaseXUserGroupDao;
import com.sas.db.aml.dao.ecm.IIncidentLiveDao;
import com.sas.db.aml.dao.ecm.IIncidentRkSeqDao;
import com.sas.db.aml.dao.ecm.IIncidentVersionDao;
import com.sas.db.aml.dao.ecm.IIncidentXUserGroupDao;
import com.sas.db.aml.orm.ecm.CaseLive;
import com.sas.db.aml.orm.ecm.CaseVersion;
import com.sas.db.aml.orm.ecm.CaseVersionPK;
import com.sas.db.aml.orm.ecm.CaseXUserGroup;
import com.sas.db.aml.orm.ecm.CaseXUserGroupPK;
import com.sas.db.aml.orm.ecm.IncidentLive;
import com.sas.db.aml.orm.ecm.IncidentVersion;
import com.sas.db.aml.orm.ecm.IncidentVersionPK;
import com.sas.db.aml.orm.ecm.IncidentXUserGroup;
import com.sas.db.aml.orm.ecm.IncidentXUserGroupPK;

@Component
public class CreateCaseIncidentBusiness {
	private static final Logger logger = LoggerFactory.getLogger(CreateCaseIncidentBusiness.class);

	@Autowired
	ICaseLiveDao iCaseLiveDao;

	@Autowired
	ICaseVersionDao iCaseVersionDao;

	@Autowired
	ICaseXUserGroupDao iCaseXUserGroupDao;

	@Autowired
	IIncidentLiveDao iIncidentLiveDao;

	@Autowired
	IIncidentVersionDao iIncidentVersionDao;

	@Autowired
	IIncidentXUserGroupDao iIncidentXUserGroupDao;

	@Autowired
	ICaseRkSeqDao iCaseRkSeqDao;

	@Autowired
	IIncidentRkSeqDao iIncidentRkSeqDao;

	/*
	 * Create Case
	 * 
	 * @param caseLive
	 * 
	 * @param caseVersion
	 * 
	 * @param caseXUserGroup
	 * 
	 * @return CaseRK
	 */
	@Transactional
	public boolean createCase(CaseLive caseLive, CaseVersion caseVersion, List<CaseXUserGroup> caseXUserGroupList) {

		if (!caseLive.getCaseRk().equals(0)) {
			
			// Step2 Insert CASE_LIVE
			iCaseLiveDao.save(caseLive);
			
			// Step3 Insert CASE_VERSION
			iCaseVersionDao.save(caseVersion);
			
			
			// Step4 Insert CASE_X_USER_GROUP
			for (CaseXUserGroup caseXUserGroupBe : caseXUserGroupList) {
				iCaseXUserGroupDao.save(caseXUserGroupBe);
			}
		}
		return true;
	}

	/**
	 * Create Incident(Alert)
	 * 
	 * @param incidentLive
	 * @param incidentVersion
	 * @param incidentXUserGroup
	 * @return 返回 IncidentRK
	 */
	@Transactional
	public boolean createIncident(IncidentLive incidentLive, IncidentVersion incidentVersion,List<IncidentXUserGroup> incidentXUserGroupList) {

		if (!incidentLive.getCaseRk().equals(0)) {
			// Step2 Insert INCIDENT_LIVE
			iIncidentLiveDao.save(incidentLive);

			// Step3 Insert INCIDENT_VERSION
			iIncidentVersionDao.save(incidentVersion);

			// Step4 Insert INCIDENT_X_USER_GROUP
			for (IncidentXUserGroup caseXUserGroupBe : incidentXUserGroupList) {
				iIncidentXUserGroupDao.save(caseXUserGroupBe);
			}
		}

		return true;
	}

	/**
	 * 
	 * 產生CASE & Incident 範例
	 */
	public static void main(String args[]) {

		Timestamp now = new Timestamp(System.currentTimeMillis());
		int year = Calendar.getInstance().get(Calendar.YEAR);

		BigDecimal case_rk = BigDecimal.valueOf(1234);

		// Step1 �ǳ�CaseLive
		CaseLive caseLiveBean = new CaseLive();
		caseLiveBean.setCaseRk(case_rk); // �n��g
		caseLiveBean.setValidFromDttm(now); // now
		caseLiveBean.setValidToDttm(null);
		caseLiveBean.setCaseId("NCKINV" + "-" + String.valueOf(case_rk).replace(".0", "")); // �զX��
																								// �ANameCheck='NCKINV-xxxxx'
																								// or
																								// SWIFTCheck='SCKINV-xxxxx'
		caseLiveBean.setSourceSystemCd("FCFBU1"); // DB Schema�W��
		caseLiveBean.setCaseTypeCd("NCKINV");
		caseLiveBean.setCaseCategoryCd(null);
		caseLiveBean.setCaseSubcategoryCd(null);
		caseLiveBean.setCaseStatusCd("I"); // ��I
		caseLiveBean.setCaseDispositionCd(null); // ?
		caseLiveBean.setCaseDesc(null); // NameCheck or SWIFTCheck ??
		caseLiveBean.setCaseLinkSk(0);
		caseLiveBean.setPriorityCd(null);
		caseLiveBean.setRegulatoryRptRqdFlg("0");
		caseLiveBean.setInvestigatorUserId("sasdemo"); // �u�ߵ���?
		caseLiveBean.setOpenDttm(null); // ??
		caseLiveBean.setReopenDttm(null);
		caseLiveBean.setCloseDttm(null);
		caseLiveBean.setUiDefFileNm("aml-case-details-namecheck-01.xml"); // �Ϥ�NameCheck='aml-alert-details-namecheck-01.xml'
																				// �P
																				// SWIFTCheck='aml-alert-details-swiftcheck-01.xml'
		caseLiveBean.setCreateUserId("sasdemo");
		caseLiveBean.setCreateDttm(now);
		caseLiveBean.setUpdateUserId("sasdemo");
		caseLiveBean.setVersionNo(1); // �w�]��1
		caseLiveBean.setDeleteFlg("0");

		// Step2 �ǳ�CaseVersion
		CaseVersion caseVersionBean = new CaseVersion();
		CaseVersionPK pk = new CaseVersionPK();
		caseVersionBean.setId(pk);
		pk.setCaseRk(case_rk); // �n��g
		pk.setValidFromDttm(now); // now
		
		caseVersionBean.setValidToDttm(null);
		caseVersionBean.setCaseId("NCKINV" + "-" + String.valueOf(case_rk).replace(".0", "") ); // �զX��
																								// �ANameCheck='NCKINV-xxxxx'
																								// or
																								// SWIFTCheck='SCKINV-xxxxx'
		caseVersionBean.setSourceSystemCd("FCFBU1"); // DB Schema�W��
		caseVersionBean.setCaseTypeCd("NCKINV");
		caseVersionBean.setCaseCategoryCd(null);
		caseVersionBean.setCaseSubcategoryCd(null);
		caseVersionBean.setCaseStatusCd("I"); // ��I
		caseVersionBean.setCaseDispositionCd(null); // ?
		caseVersionBean.setCaseDesc(null); // NameCheck or SWIFTCheck ??
		caseVersionBean.setCaseLinkSk(0);
		caseVersionBean.setPriorityCd(null);
		caseVersionBean.setRegulatoryRptRqdFlg("0");
		caseVersionBean.setInvestigatorUserId("sasdemo"); // �u�ߵ���?
		caseVersionBean.setOpenDttm(null); // ??
		caseVersionBean.setReopenDttm(null);
		caseVersionBean.setCloseDttm(null);
		caseVersionBean.setUiDefFileNm("aml-case-details-namecheck-01.xml"); // �Ϥ�NameCheck='aml-alert-details-namecheck-01.xml'
																				// �P
																				// SWIFTCheck='aml-alert-details-swiftcheck-01.xml'
		caseVersionBean.setCreateUserId("sasdemo");
		caseVersionBean.setCreateDttm(now);
		caseVersionBean.setUpdateUserId("sasdemo");
		caseVersionBean.setVersionNo(1); // �w�]��1
		caseVersionBean.setDeleteFlg("0");

		// Step3 �ǳ�CaseXUserGroupBean
		List<CaseXUserGroup> cxugList = new ArrayList<CaseXUserGroup>();
		CaseXUserGroup be = new CaseXUserGroup();
		CaseXUserGroupPK id = new CaseXUserGroupPK();
		be.setId(id);
		id.setCaseRk(case_rk);
		id.setUserGroupNm("AML Analysts"); // ������W��
		cxugList.add(be);

		BigDecimal incident_rk = BigDecimal.valueOf(4321);

		// Step4 �ǳ� IncidentLive
		IncidentLive ilBean = new IncidentLive();
		ilBean.setIncidentRk(incident_rk);
		ilBean.setValidFromDttm(now);
		ilBean.setValidToDttm(null);
		ilBean.setCaseRk(case_rk); // ���T�w
		ilBean.setIncidentId("NCKINV" + "-" + String.valueOf(incident_rk).replace(".0", "")); // �զX��
																								// �ANameCheck='NCKINV-xxxxx'
																								// or
																								// SWIFTCheck='SCKINV-xxxxx'
		ilBean.setSourceSystemCd("FCFBU1");
		ilBean.setIncidentTypeCd("NCKINV"); // NameCheck='NCKINV' or
												// SWIFTCheck='SCKINV'
		ilBean.setIncidentCategoryCd(null);
		ilBean.setIncidentSubcategoryCd(null);
		ilBean.setIncidentDesc(null);
		ilBean.setIncidentFromDt(null);
		ilBean.setIncidentFromTm(null);
		ilBean.setIncidentToDt(null);
		ilBean.setIncidentToTm(null);
		ilBean.setDetectionDt(null);
		ilBean.setDetectionTm(null);
		ilBean.setNotificationDt(null);
		ilBean.setNotificationTm(null);
		ilBean.setUiDefFileNm("aml-alert-details-swiftcheck-01.xml"); // �Ϥ�NameCheck='aml-alert-details-namecheck-01.xml'
																			// �P
																			// SWIFTCheck='aml-alert-details-swiftcheck-01.xml'
		ilBean.setCreateDttm(now);
		ilBean.setUpdateUserId("sasdmeo");
		ilBean.setVersionNo(1);
		ilBean.setDeleteFlg("0");
		ilBean.setInvestigatorUserId("sasdemo");
		ilBean.setIncidentDispositionCd(null);
		ilBean.setCloseDttm(null);
		ilBean.setIncidentStatusCd("ncsc");

		// Step6 �ǳ�IncidentVersion
		IncidentVersion ivBean = new IncidentVersion();
		IncidentVersionPK incidentVersionPK = new IncidentVersionPK();
		ivBean.setId(incidentVersionPK);
		incidentVersionPK.setIncidentRk(incident_rk);
		incidentVersionPK.setValidFromDttm(now);
		ivBean.setValidToDttm(null);
		ivBean.setCaseRk(case_rk); // ���T�w
		ivBean.setIncidentId("NCKINV" + "-" + String.valueOf(incident_rk).replace(".0", "")); // �զX��
																								// �ANameCheck='NCKINV-xxxxx'
																								// or
																								// SWIFTCheck='SCKINV-xxxxx'
		ivBean.setSourceSystemCd("FCFBU1");
		ivBean.setIncidentTypeCd("AMLNCSC");
		ivBean.setIncidentCategoryCd(null);
		ivBean.setIncidentSubcategoryCd(null);
		ivBean.setIncidentDesc(null);
		ivBean.setIncidentFromDt(null);
		ivBean.setIncidentFromTm(null);
		ivBean.setIncidentToDt(null);
		ivBean.setIncidentToTm(null);
		ivBean.setDetectionDt(null);
		ivBean.setDetectionTm(null);
		ivBean.setNotificationDt(null);
		ivBean.setNotificationTm(null);
		ivBean.setUiDefFileNm("aml-alert-details-swiftcheck-01.xml"); // �Ϥ�NameCheck='aml-alert-details-namecheck-01.xml'
																			// �P
																			// SWIFTCheck='aml-alert-details-swiftcheck-01.xml'
		ivBean.setCreateDttm(now);
		ivBean.setUpdateUserId("sasdmeo");
		ivBean.setVersionNo(1);
		ivBean.setDeleteFlg("0");
		ivBean.setInvestigatorUserId("sasdemo");
		ivBean.setIncidentDispositionCd(null);
		ivBean.setCloseDttm(null);
		ivBean.setIncidentStatusCd("ncsc");

		// Step7 �ǳ�IncidentXUserGroup
		List<IncidentXUserGroup> ixugList = new ArrayList<IncidentXUserGroup>();
		IncidentXUserGroup be1 = new IncidentXUserGroup();
		IncidentXUserGroupPK incidentXUserGroupPK = new IncidentXUserGroupPK();
		be1.setId(incidentXUserGroupPK);
		incidentXUserGroupPK.setIncidentRk(incident_rk);
		incidentXUserGroupPK.setUserGroupNm("AML Analysts");
		ixugList.add(be1);

	}

}
