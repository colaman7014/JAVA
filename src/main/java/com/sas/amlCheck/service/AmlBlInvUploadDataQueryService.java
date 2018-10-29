package com.sas.amlCheck.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sas.aml.uploader.bean.NameCheckRecordDetailBean;
import com.sas.aml.uploader.bean.NameCheckRecordMainBean;
import com.sas.aml.uploader.bean.XInvBlNcUploadRecordBean;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.IFullRefTableTranDao;
import com.sas.db.aml.dao.fcfcore.IAccountIntegrationDao;
import com.sas.db.aml.orm.ecm.FullRefTableTran;
import com.sas.db.aml.orm.fcfcore.AccountIntegration;
import com.sas.db.wlf.dao.IXInvBlNcUploadRecordDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordDetailDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordMainDao;
import com.sas.db.wlf.dao.tf.IXBlExportDao;
import com.sas.db.wlf.dao.tf.IXBlImportDao;
import com.sas.db.wlf.dao.tf.IXInvExportDao;
import com.sas.db.wlf.dao.tf.IXInvImportDao;
import com.sas.db.wlf.dao.tf.IXOnlineNameCheckUploadDao;
import com.sas.db.wlf.orm.XInvBlNcUploadRecord;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;
import com.sas.db.wlf.orm.tf.XBlExport;
import com.sas.db.wlf.orm.tf.XBlImport;
import com.sas.db.wlf.orm.tf.XInvExport;
import com.sas.db.wlf.orm.tf.XInvImport;
import com.sas.db.wlf.orm.tf.XOnlineNamecheckUpload;
import com.sas.multipleNC.bean.XOnlineNamecheckUploadBean;
import com.sas.tradeFinance.bean.XBlExportBean;
import com.sas.tradeFinance.bean.XBlImportBean;
import com.sas.tradeFinance.bean.XInvExportBean;
import com.sas.tradeFinance.bean.XInvImportBean;
import com.sas.util.DateUtil;

@Service
public class AmlBlInvUploadDataQueryService {

	@Autowired
	IXBlImportDao iXBlImportDao;
	@Autowired 
	IXBlExportDao iXBlExportDao;
	@Autowired
	IXInvImportDao iXInvImportDao;
	@Autowired
	IXInvExportDao iXInvExportDao;
	
	@Autowired
	IXInvBlNcUploadRecordDao iXInvBlNcUploadRecordDaoCustomer;
	@Autowired
	IXOnlineNameCheckUploadDao iXOnlineNameCheckUploadDao;
	@Autowired
	INameCheckRecordMainDao iNameCheckRecordMainDao;
	@Autowired 
	INameCheckRecordDetailDao iNameCheckRecordDetailDao;
	@Autowired
	IAccountIntegrationDao iAccountIntegrationDao;
	@Autowired
	IFullRefTableTranDao iFullRefTableTranDao;	
	
	// To Query Name Check Upload File Date By [uploadType] And [fileKey]
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List queryDataByUploadType(String uploadType, String fileKey) {
		List returnList = new ArrayList();

		if("1".equals(uploadType)) {
			//BL Import
			List<XBlImport> resultList = new ArrayList<XBlImport>();
			resultList = iXBlImportDao.queryBlImportDataByFileKey(fileKey);
			if(!CollectionUtils.isEmpty(resultList)) {
				for(XBlImport entity : resultList) {
					returnList.add(new XBlImportBean(entity));
				}
			}
		}else if("2".equals(uploadType)) {
			//BL Export
			List<XBlExport> resultList = new ArrayList<XBlExport>();
			resultList = iXBlExportDao.queryBlExportDataByFileKey(fileKey);
			if(!CollectionUtils.isEmpty(resultList)) {
				for(XBlExport entity : resultList) {
					returnList.add(new XBlExportBean(entity));
				}
			}
		}else if("3".equals(uploadType)) {
			//INV Import
			List<XInvImport> resultList = new ArrayList<XInvImport>();
			resultList = iXInvImportDao.queryInvImportDataByFileKey(fileKey);
			if(!CollectionUtils.isEmpty(resultList)) {
				for(XInvImport entity : resultList) {
					returnList.add(new XInvImportBean(entity));
				}
			}
		}else if("4".equals(uploadType)) {
			//INV Export
			List<XInvExport> resultList = new ArrayList<XInvExport>();
			resultList = iXInvExportDao.queryInvExportDataByFileKey(fileKey);
			if(!CollectionUtils.isEmpty(resultList)) {
				for(XInvExport entity : resultList) {
					returnList.add(new XInvExportBean(entity));
				}
			}
		}else if("7".equals(uploadType) || "8".equals(uploadType) || "9".equals(uploadType)) {
			//Standard
			List<XOnlineNamecheckUpload> resultList = new ArrayList<>();
			resultList = iXOnlineNameCheckUploadDao.queryOnlineMulitDataByFileKey(fileKey);
			if(!CollectionUtils.isEmpty(resultList)) {
				for(XOnlineNamecheckUpload entity : resultList) {
					XOnlineNamecheckUploadBean xOnlineNamecheckUploadBean= new XOnlineNamecheckUploadBean(entity);
					returnList.add(xOnlineNamecheckUploadBean);
					if(entity.getCallingUser() != null) {
						AccountIntegration accountIntegration = iAccountIntegrationDao.findOne(entity.getCallingUser());
						xOnlineNamecheckUploadBean.setBranchNumberName(accountIntegration.getDeptName()!=null?accountIntegration.getDeptName():"其他");
						xOnlineNamecheckUploadBean.setCallingUserName(accountIntegration.getEmpName()!=null?accountIntegration.getEmpName():"無資料");
					}
				}
			}
		}
		return returnList;
	}
	
	public List<XInvBlNcUploadRecordBean> queryUploadRecordData(String uploadType, String scanStatus, String createUser,
			String createDateStart, String createDateend, String locale) {
		// To Query DB By Condition 
		String startDate = "";
		String endDate = "";
		String branchNum="";
		List<String> users = new ArrayList<>();
		
		if(!StringUtils.isBlank(createDateStart)) {
			startDate = createDateStart + " 00:00:00";
		}
		if(!StringUtils.isBlank(createDateend)){
			endDate = createDateend + " 23:59:59";
		}
		List<XInvBlNcUploadRecordBean> returnList = new ArrayList<XInvBlNcUploadRecordBean>();
		if(createUser != null && !"".equals(createUser)) {
			AccountIntegration accountIntegration = iAccountIntegrationDao.findOne(createUser);
			if(accountIntegration!=null) {
				branchNum = accountIntegration.getDeptNo();
				List<AccountIntegration> accountIntegrations = iAccountIntegrationDao.findByDeptNo(branchNum);
				for (AccountIntegration anAccountIntegration : accountIntegrations) {
					users.add(anAccountIntegration.getEmpId());
				}
			} else {
				users.add(createUser);
			}
		}
		
		List<XInvBlNcUploadRecord> resultData= iXInvBlNcUploadRecordDaoCustomer.
			queryFileUploadConditionByUsers(users, uploadType, "",  startDate, endDate, scanStatus);
		Map<String, List<FullRefTableTran>> fullRefTableTranMap = getFullRefTableTranMap(locale);
		List<FullRefTableTran> refNCUploadTypeList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_NC_UPLOAD_TYPE);
		
		if(!CollectionUtils.isEmpty(resultData)) {
			for(XInvBlNcUploadRecord entity : resultData) {
				XInvBlNcUploadRecordBean xInvBlNcUploadRecordBean = new XInvBlNcUploadRecordBean(entity);
				if(entity.getCreateUser() != null) {
					AccountIntegration accountIntegration = iAccountIntegrationDao.findOne(entity.getCreateUser());
					if(accountIntegration!=null) {
						xInvBlNcUploadRecordBean.setCreateUserName(accountIntegration.getEmpName()!=null?accountIntegration.getEmpName():"無資料");
					} else {
						xInvBlNcUploadRecordBean.setCreateUserName("無資料");
					}
				}
				for(FullRefTableTran refNCUploadTypeCd:refNCUploadTypeList){
					if(xInvBlNcUploadRecordBean.getUploadType().equals(refNCUploadTypeCd.getId().getValueCd())){						
						xInvBlNcUploadRecordBean.setUploadTypeDesc(refNCUploadTypeCd.getValueDesc());
					}
				}
				returnList.add(xInvBlNcUploadRecordBean);
			}
		}
		return returnList;
	}
	
	public List<NameCheckRecordMainBean> queryNameCheckRecordMain(String uniqueKey,String locale) {
		List<NameCheckRecordMainBean> returnList = new ArrayList<NameCheckRecordMainBean>();
		List<NameCheckRecordMain> resultList = new ArrayList<NameCheckRecordMain>();
		resultList = iNameCheckRecordMainDao.queryNameCheckRecordByUniqueKey(uniqueKey);
		Map<String, List<FullRefTableTran>> fullRefTableTranMap = getFullRefTableTranMap(locale);
		List<FullRefTableTran> refBranchNumberList =fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_BRANCH_LIST);
		List<FullRefTableTran> refCallingSystemList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_CALLING_SYSTEM);
		List<FullRefTableTran> refNCResultList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_NC_RESULT);
		List<FullRefTableTran> refScreenProcessList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_SCREEN_PROCESS);
		List<FullRefTableTran> refWatchListTypeCdList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
		
//		List<FullRefTableTran> refWatchListSubTypeCdList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
		String[] routeRuleArray;
		
		for(NameCheckRecordMain nameCheckRecordMain:resultList){
			NameCheckRecordMainBean nameCheckRecordMainBean=new NameCheckRecordMainBean(nameCheckRecordMain);
				if(nameCheckRecordMain.getBranchNumber()!=null){
					for(FullRefTableTran refBranchNumber:refBranchNumberList){
						if(nameCheckRecordMain.getBranchNumber().equals(refBranchNumber.getId().getValueCd())){
							nameCheckRecordMainBean.setTransBranchNumber(refBranchNumber.getValueDesc());
							break;
						}
					}
				}
				if(nameCheckRecordMain.getCallingSystem()!=null){
					for(FullRefTableTran refCallingSystem:refCallingSystemList){
						if(nameCheckRecordMain.getCallingSystem().equals(refCallingSystem.getId().getValueCd())){
							nameCheckRecordMainBean.setTransCallingSystem(refCallingSystem.getValueDesc());
						break;
						}
					}
				}
				if(nameCheckRecordMain.getNcResult()!=null){
						for(FullRefTableTran refNCResult:refNCResultList){
							if(nameCheckRecordMain.getNcResult().equals(refNCResult.getId().getValueCd())){
								nameCheckRecordMainBean.setTransNcResult(refNCResult.getValueDesc());
							break;
						}
					}
				}
				if(nameCheckRecordMain.getScreenProcess()!=null){
					for(FullRefTableTran refScreenProcess:refScreenProcessList){
						if(nameCheckRecordMain.getScreenProcess().equals(refScreenProcess.getId().getValueCd())){
							nameCheckRecordMainBean.setTransScreenProcess(refScreenProcess.getValueDesc());
						break;
					}
				}
			}
				String routeRule=nameCheckRecordMain.getRouteRule();
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
				nameCheckRecordMainBean.setTransRouteRule(newRouteRule);	
				
				if(nameCheckRecordMainBean.getCallingUser() != null) {
					AccountIntegration accountIntegration = iAccountIntegrationDao.findOne(nameCheckRecordMainBean.getCallingUser());
					if(accountIntegration!=null){
						nameCheckRecordMainBean.setBranchNumberName(accountIntegration.getDeptName()!=null?accountIntegration.getDeptName():"其他");
						nameCheckRecordMainBean.setCallingUserName(accountIntegration.getEmpName()!=null?accountIntegration.getEmpName():"無資料");
					}else{
						nameCheckRecordMainBean.setBranchNumberName("不存在");
						nameCheckRecordMainBean.setCallingUserName("不存在");
					}
					
				}				

			returnList.add(nameCheckRecordMainBean);
		}
		
		return returnList;
	}
	
	public List<NameCheckRecordDetailBean> queryNameCheckRecordDetail(String uniqueKey,String locale){
		List<NameCheckRecordDetailBean> returnList = new ArrayList<NameCheckRecordDetailBean>();
		List<NameCheckRecordDetail> resultList = new ArrayList<NameCheckRecordDetail>();
		resultList = iNameCheckRecordDetailDao.queryNameCheckRecordDetailByUniqueKey(uniqueKey);
		Map<String, List<FullRefTableTran>> fullRefTableTranMap = getFullRefTableTranMap(locale);
		List<FullRefTableTran> refEntityTypeCdList =fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_ENTITY_TYPE_CD);
		List<FullRefTableTran> refEntityRelationshipList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_ENTITY_RELATIONSHIP);
		List<FullRefTableTran> refNCResultList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_NC_RESULT);
		List<FullRefTableTran> refWatchListTypeCdList=fullRefTableTranMap.get(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
		String[] routeRuleArray;
		
		for(NameCheckRecordDetail nameCheckRecordDetail:resultList){
			NameCheckRecordDetailBean nameCheckRecordDetailBean=new NameCheckRecordDetailBean(nameCheckRecordDetail);
				if(nameCheckRecordDetail.getEntityType()!=null){
					for(FullRefTableTran refEntityTypeCd:refEntityTypeCdList){
						if(nameCheckRecordDetail.getEntityType().equals(refEntityTypeCd.getId().getValueCd())){
							nameCheckRecordDetailBean.setTransEntityType(refEntityTypeCd.getValueDesc());
							break;
						}
					}
				}
				if(nameCheckRecordDetail.getEntityRelationship()!=null){
					for(FullRefTableTran refEntityRelationship:refEntityRelationshipList){
						if(nameCheckRecordDetail.getEntityRelationship().equals(refEntityRelationship.getId().getValueCd())){
							nameCheckRecordDetailBean.setTransEntityRelationship(refEntityRelationship.getValueDesc());
						break;
						}
					}
				}
				if(nameCheckRecordDetail.getCheckResult()!=null){
						for(FullRefTableTran refNCResult:refNCResultList){
							if(nameCheckRecordDetail.getCheckResult().equals(refNCResult.getId().getValueCd())){
								nameCheckRecordDetailBean.setTransCheckResult(refNCResult.getValueDesc());
							break;
						}
					}
				}
				String routeRule=nameCheckRecordDetail.getRouteRule();
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
				nameCheckRecordDetailBean.setTransRouteRule(newRouteRule);				
				

			returnList.add(nameCheckRecordDetailBean);
		}
		
		return returnList;
	}
	private Map<String, List<FullRefTableTran>> getFullRefTableTranMap(String locale){
		
		Map<String, List<FullRefTableTran>> fullRefTableTranMap = new HashMap<String, List<FullRefTableTran>>();
		List<FullRefTableTran> refEntityTypeCdList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_ENTITY_TYPE_CD);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_ENTITY_TYPE_CD,refEntityTypeCdList);
		List<FullRefTableTran> refEntityRelationshipList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_ENTITY_RELATIONSHIP);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_ENTITY_RELATIONSHIP,refEntityRelationshipList);
		List<FullRefTableTran> refNCResultList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_NC_RESULT);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_NC_RESULT,refNCResultList);
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
		List<FullRefTableTran> refNCUploadTypeList = iFullRefTableTranDao.findByIdLocaleAndIdRefTableNm(locale, SwiftMtConst.REF_TABLE_NM_X_NC_UPLOAD_TYPE);
			fullRefTableTranMap.put(SwiftMtConst.REF_TABLE_NM_X_NC_UPLOAD_TYPE, refNCUploadTypeList);
		return fullRefTableTranMap;	
	}		
}
