package com.sas.webservice.createCase;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sas.db.aml.dao.ecm.ICaseLiveDao;
import com.sas.db.aml.dao.ecm.ICaseUdfCharValueDao;
import com.sas.db.aml.dao.fcfcore.IFscPartyDimCustomizeDao;
import com.sas.db.aml.dao.fcfcore.IFscPartyDimDao;
import com.sas.db.aml.dao.fcfcore.IXPartyWhitelistHisDao;
import com.sas.db.aml.dao.fcfcore.IXPartyWhitelistMainDao;
import com.sas.db.aml.orm.ecm.CaseLive;
import com.sas.db.aml.orm.ecm.CaseUdfCharValue;
import com.sas.db.aml.orm.fcfcore.FscPartyDim;
import com.sas.db.aml.orm.fcfcore.FscPartyDimPK;
import com.sas.db.aml.orm.fcfcore.XComboWhitelistMain;
import com.sas.db.aml.orm.fcfcore.XPartyWhitelistHis;
import com.sas.db.aml.orm.fcfcore.XPartyWhitelistMain;
import com.sas.db.aml.orm.fcfcore.XPartyWhitelistMainPK;
import com.sas.webservice.createCase.bean.PartyWhiteListInputBean;
import com.sas.webservice.createCase.bean.PartyWhiteListOutputBean;
@Component
public class PartyWhiteListCaseImpl implements PartyWhiteListCase{
	private static final Logger logger = LoggerFactory.getLogger(PartyWhiteListCaseImpl.class);
	private static final String CASE_DISPOSITION_APPROVAL = "APPROVAL";  //已核准
	private static final String CASE_DISPOSITION_CANCEL = "CANCEL";      //已拒絕
	private static final String CASE_ACTION_NEW  = "NEW";                //新增
	private static final String CASE_ACTION_STOP = "STOP";               //停用
		
	@Autowired
	IXPartyWhitelistMainDao iXPartyWhitelistMainDao;
	
	@Autowired
	IXPartyWhitelistHisDao iXPartyWhitelistHisDao;
	
	@Autowired
	ICaseLiveDao iCaseLiveDao;
	
	@Autowired
	ICaseUdfCharValueDao iCaseUdfCharValueDao;
	
	@Autowired
	IFscPartyDimCustomizeDao iFscPartyDimCustomizeDao;

	/* 
	 * 
	 * 給WorkFlow save
	 * 將CASE_UDF_CHAR_VALUE 等欄位  寫入到X_PARTY_WHITELIST_MAIN
	 * 
	 * case1 [NEW] if (全新)  then {   add _MAIN & empty _HIS }
     * case2 [NEW] if ( PARTY_NUMBER && PARTY_NAME 存在於  _MAIN) then {
     *    A. copy old_MAIN to _HIS  ; 
     *    B. update old_MAIN from Case & keep oldMain.IS_WHITE
     *       (PS old_MAIN.is_white不可以修改)  
     *    }
     * 給WorkFlow 將CASE_UDF CHAR/Date 等UDF欄位 寫入到X_PARTY_WHITELIST_MAIN
	 * @see com.sas.webservice.createCase.PartyWhiteListCase#savePartyWhiteListMain(com.sas.webservice.createCase.bean.PartyWhiteListInputBean)
	 */
	@Override
	public PartyWhiteListOutputBean workFlowSavePartyWhiteListMain(String caseId, String userId) {
		logger.debug("workFlowSavePartyWhiteListMain");
		PartyWhiteListOutputBean output = new PartyWhiteListOutputBean();
		output.setServiceName("workFlowSavePartyWhiteListMain");
		StringBuffer log = new StringBuffer();
		log.append("\nworkFlowSavePartyWhiteListMain");
		try {
			Long caseRk = getCaseRk(caseId);
			String caseAction = this.findCharUdfValue(caseRk, "X_PARTY_WHITE_LIST_ACTION"); //NEW(新增)/STOP(停用)
			String partyNumber = this.findCharUdfValue(caseRk, "X_PARTY_NUMBER");  //客戶編號
			String partyName = this.findCharUdfValue(caseRk, "X_PARTY_NAME");      //客戶名稱
			String remark = this.findCharUdfValue(caseRk, "X_WHITE_LIST_REMARK");  //備註
			
			if (StringUtils.isBlank(caseAction) || StringUtils.isBlank(partyNumber)) {
				String errorMsg = String.format("error caseId:%s , not found caseAction/partyNumber in UDF  : caseAction:%s ; partyNumber:%s ; partyName:%s", caseId,
						caseAction, partyNumber, partyName);
				throw new Exception(errorMsg);
			}
			
			XPartyWhitelistMainPK mainPk = new XPartyWhitelistMainPK();
			mainPk.setPartyNumber(partyNumber);
			mainPk.setPartyName(partyName);
			
			XPartyWhitelistMain oldMainEntity = iXPartyWhitelistMainDao.findOne(mainPk);			
			XPartyWhitelistMain saveMain = null;
			
			//case1 [NEW] if (全新)  then {   add _MAIN & empty _HIS }
			if (oldMainEntity == null) {
				XPartyWhitelistMain mainEntity = new XPartyWhitelistMain();
				mainEntity.setId(mainPk);
				mainEntity.setCaseId(caseId);
				mainEntity.setCaseAction(caseAction);
				mainEntity.setRemark(remark);
				mainEntity.setCreateUser(userId);                     //CASE_LIVE.INVESTIGATOR_USER_ID
				mainEntity.setCreateDate(new Date());
				/*
				 * String caseDisposition = this.queryDisposition(BigDecimal.valueOf(caseRk));
				 * 只有結案 WorkFlow.CloseCase() workFlow to update caseLive.CaseDispositionCd 為   APPROVAL / CANCEL 
				   mainEntity.setCaseDisposition(caseDisposition);    //結案有 APPROVAL / CANCEL
				 */
				saveMain = iXPartyWhitelistMainDao.save(mainEntity);
				output.setIsExistPartyWhitelistMain(Boolean.FALSE);   //沒有存在Main
			}else{
				/* case2 [NEW] if ( PARTY_NUMBER && PARTY_NAME 存在於  _MAIN) then {
				     *    A. copy old_MAIN to _HIS  ; 
				     *    B. update old_MAIN from Case & keep oldMain.IS_WHITE
				     *       (PS old_MAIN.is_white不可以修改)  
				     *    } 
				*/				
				XPartyWhitelistHis history = new XPartyWhitelistHis();
				history.setCaseId(oldMainEntity.getCaseId());
				history.setCaseAction(oldMainEntity.getCaseAction());
				history.setCaseDisposition(oldMainEntity.getCaseDisposition());
				history.setCreateDate(oldMainEntity.getCreateDate());
				history.setCreateUser(oldMainEntity.getCreateUser());
				history.setPartyName(oldMainEntity.getId().getPartyName());
				history.setPartyNumber(oldMainEntity.getId().getPartyNumber());
				history.setRemark(oldMainEntity.getRemark());
				 
				XPartyWhitelistHis saveHistory = iXPartyWhitelistHisDao.save(history);
				
				oldMainEntity.setCaseId(caseId);
				oldMainEntity.setCaseAction(caseAction);
				oldMainEntity.setRemark(remark);
				oldMainEntity.setCreateUser(userId);                   //CASE_LIVE.INVESTIGATOR_USER_ID
				oldMainEntity.setCreateDate(new Date());
				oldMainEntity.setCaseDisposition(null);				
				String oldIsWhitelist = oldMainEntity.getIsWhitelist();
				oldMainEntity.setIsWhitelist(oldIsWhitelist);          //old_MAIN.is_white不可以修改
				saveMain = iXPartyWhitelistMainDao.save(oldMainEntity);
				
				output.setIsExistPartyWhitelistMain(Boolean.TRUE);     //已經存在Main
				output.setHistoryCaseId(saveHistory.getCaseId());
			}
			
			output.setPartyName(saveMain.getId().getPartyName());
			output.setPartyNumber(saveMain.getId().getPartyNumber());
			output.setCaseId(saveMain.getCaseId());
			output.setCaseDisposition(saveMain.getCaseDisposition());
			output.setCreateUser(saveMain.getCreateUser());
			output.setCreateDate(saveMain.getCreateDate().getTime());
			
			log.append("\nOutputBean :" + output.toString());
			log.append("\nIsSuccess:true");
			output.setIsSuccess(true);
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
			output.setErrorMsg(e.getMessage());
			output.setIsSuccess(false);
			log.append("\nIsSuccess:false");
			log.append("\nsavePartyWhiteListMain exception:" + e.getMessage());
		}

		output.setLogMsg(log.toString());
		return output;
	}

	
	/* 
	 * 
     * 給WorkFlow CloseCase
	 * 異動[FCFCOREHK0].[X_PARTY_WHITELIST_MAIN].IS_WHITELIST
	 * 1.update  IS_WHITELIST規則:
	 *    停用STOP已核准APPROVAL =N
     *    啟用NEW已核准APPROVAL =Y
     * 2.update DISPOSITION from CASE_LIVE
     *
	 * 給WorkFlow 異動[FCFCOREHK0].[X_PARTY_WHITELIST_MAIN].IS_WHITELIST
	 * @see com.sas.webservice.createCase.PartyWhiteListCase#updateIsWhiteList(com.sas.webservice.createCase.bean.PartyWhiteListInputBean)
	 */
	@Override
	public PartyWhiteListOutputBean workFlowCloseCasePartyWhiteList(String caseId, String userId,String disposition) {
 		logger.debug("workFlowCloseCasePartyWhiteList");
		PartyWhiteListOutputBean output = new PartyWhiteListOutputBean();
		output.setServiceName("workFlowCloseCasePartyWhiteList");
		StringBuffer log = new StringBuffer();
		try {
			logger.debug("updateIsWhiteList");
			log.append("\nworkFlowCloseCasePartyWhiteList");
			Long caseRk = getCaseRk(caseId);
			String partyNumber = this.findCharUdfValue(caseRk, "X_PARTY_NUMBER");    //客戶編號
			String partyName = this.findCharUdfValue(caseRk, "X_PARTY_NAME");        //客戶名稱
//			String disposition = this.queryDisposition(BigDecimal.valueOf(caseRk));  //APPROVAL or CANCEL
			if("REJECT".equals(disposition)){
				disposition = CASE_DISPOSITION_CANCEL;
			}
			XPartyWhitelistMainPK mainPk = new XPartyWhitelistMainPK();
			mainPk.setPartyNumber(partyNumber);
			mainPk.setPartyName(partyName);
			
			XPartyWhitelistMain oldMainEntity = iXPartyWhitelistMainDao.findOne(mainPk);
			if (oldMainEntity == null) {
				output.setIsExistPartyWhitelistMain(Boolean.FALSE);     //不存在Main 
				throw new Exception(String.format("PartyWhitelistMain not found caseId:%s  ;  partyNumber:%s  ;  partyName:%s "
						,caseId,partyNumber,partyName)); // 查無資料   (如不存在不可停用)
			}
			
			 
			
			String caseAction = oldMainEntity.getCaseAction();			
			
			/*update  IS_WHITELIST規則:
		    * 停用STOP已核准APPROVAL =N
			* 啟用NEW已核准APPROVAL =Y
			*/
			String isWhitelist = null;
			if (CASE_DISPOSITION_APPROVAL.equalsIgnoreCase(disposition)) {
				if (CASE_ACTION_STOP.equalsIgnoreCase(caseAction)) {
					isWhitelist = "N"; // 同意停用 不是白名單
				} else if (CASE_ACTION_NEW.equalsIgnoreCase(caseAction)) {
					isWhitelist = "Y"; // 同意新增 是白名單
				} else {
					throw new Exception("empty caseAction ,caseId:" + caseId);
				}
			} else if (CASE_DISPOSITION_CANCEL.equalsIgnoreCase(disposition)) {
				if (CASE_ACTION_STOP.equalsIgnoreCase(caseAction)) {
					isWhitelist = "Y"; // 拒絕停用 仍是白名單
				} else if (CASE_ACTION_NEW.equalsIgnoreCase(caseAction)) {
					isWhitelist = "N"; // 拒絕新增 不是白名單
				} else {
					throw new Exception("empty caseAction ,caseId:" + caseId);
				}
			} else {
				//沒有進行設定
				Exception noDispositionException = new Exception(
						String.format("no got CASE_DISPOSITION , caseId:%s  CASE_DISPOSITION:%s", caseId, disposition));
				logger.error(noDispositionException.getMessage(),
						noDispositionException);
				output.setErrorMsg(noDispositionException.getMessage());
				output.setIsSuccess(false);
			}
			
			if (isWhitelist != null) {
				oldMainEntity.setCaseDisposition(disposition);
				oldMainEntity.setIsWhitelist(isWhitelist);
				XPartyWhitelistMain saveMain = iXPartyWhitelistMainDao
						.save(oldMainEntity);

				output.setCaseId(saveMain.getCaseId());
				output.setPartyNumber(saveMain.getId().getPartyNumber());
				output.setPartyName(saveMain.getId().getPartyName());
				output.setIsExistPartyWhitelistMain(Boolean.TRUE); // 已經存在Main

				log.append(String.format("\n  Save caseAction:%s" , caseAction));
				log.append(String.format(", caseId:%s" , caseId));
				log.append(String.format(", userId:%s" , userId));
				log.append(String.format(", isWhitelist:%s" , isWhitelist));
				log.append(String.format(", disposition:%s" , disposition));
				log.append(" IsSuccess:true");
				output.setIsSuccess(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			output.setErrorMsg(e.getMessage());
			output.setIsSuccess(false);
			output.setIsExistPartyWhitelistMain(Boolean.FALSE); 
			log.append("\nIsSuccess:false");
			log.append("\nupdateIsWhiteList exception:" + e.getMessage());
		}
		output.setLogMsg(log.toString());
		return output;
	}
	
	
	/* 
	 * 給client script 檢查客戶編號是否存在[FCFCOREHK0].[FSC_PARTY_DIM]
	 * (non-Javadoc)
	 * @see com.sas.webservice.createCase.PartyWhiteListCase#scriptClientCheckPartyNumberExist(java.lang.String)
	 */
	@Override
	public PartyWhiteListOutputBean scriptClientCheckPartyNumberExist(
			String partyNumber) {
		logger.debug("scriptClientCheckPartyNumberExist");
		PartyWhiteListOutputBean output = new PartyWhiteListOutputBean();
		output.setServiceName("scriptClientCheckPartyNumberExist");
		StringBuffer log = new StringBuffer();
		try {
			if (StringUtils.isBlank(partyNumber)) {  //空白partyNumber
				output.setScriptCheckPartyNumberExist(Boolean.FALSE);
				throw new Exception("empty partyNumber");
			} else {
				List<FscPartyDim> partyList = iFscPartyDimCustomizeDao.findByPartyNumber(partyNumber);
				if (partyList != null && partyList.size() > 0) {  //會有多筆記錄
					output.setIsSuccess(Boolean.TRUE);
					output.setScriptCheckPartyNumberExist(Boolean.TRUE); // 有查到資料
				} else {
					output.setScriptCheckPartyNumberExist(Boolean.FALSE); // 沒有查到資料
				}
			}
			log.append("\nIsSuccess:true");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			output.setErrorMsg(e.getMessage());
			output.setIsSuccess(Boolean.FALSE);
			log.append("\nIsSuccess:false");
			log.append("\nupdateIsWhiteList exception:" + e.getMessage());
		}

		return output;
	}

	/* 
	 * 給client script 檢查可以新增X_PARTY_WHITELIST_MAIN
	 * (non-Javadoc)
	 * @see com.sas.webservice.createCase.PartyWhiteListCase#scriptClientIsCanAddPartyWhite(com.sas.webservice.createCase.bean.PartyWhiteListInputBean)
	 */
	@Override
	public PartyWhiteListOutputBean scriptClientIsCanAddPartyWhite(
			PartyWhiteListInputBean input) {
		//logger.debug("scriptClientIsCanAddPartyWhite");
		PartyWhiteListOutputBean output = new PartyWhiteListOutputBean();
		output.setServiceName("scriptClientIsCanAddPartyWhite");
		StringBuffer log = new StringBuffer();
		String caseAction = input.getCaseAction();
		String partyNumber = input.getPartyNumber();
		String partyName = input.getPartyName();
		XPartyWhitelistMainPK mainPk = new XPartyWhitelistMainPK();
			
		
		mainPk.setPartyNumber(partyNumber);
		mainPk.setPartyName(partyName);			
		XPartyWhitelistMain oldMainEntity = iXPartyWhitelistMainDao.findOne(mainPk);
		
		output.setPartyName(partyName);
		output.setPartyNumber(partyNumber);
		Boolean canAdd = Boolean.FALSE; //是否可以新增
		
		String oldCaseAction = null;
		String oldCaseDisposition = null;
		String oldCaseId = null;
		Boolean isExistMain = Boolean.FALSE;
		String checkStep = "";
		try {
			
			if(StringUtils.isBlank(caseAction) || StringUtils.isBlank(partyNumber) || StringUtils.isBlank(partyName)){
				//沒有對應參數
				checkStep = "input 0.0";
				canAdd = Boolean.FALSE;
				throw new Exception("empty input");
			} else if (CASE_ACTION_NEW.equalsIgnoreCase(caseAction)) { //1  新增[NEW]邏輯
				
				if (oldMainEntity == null) {                      //沒有Main,可以add NEW
					checkStep = "new 1.1";
					canAdd = Boolean.TRUE; 
				} else {
					isExistMain = Boolean.TRUE;
					oldCaseAction = oldMainEntity.getCaseAction();
					oldCaseDisposition = oldMainEntity.getCaseDisposition();
					oldCaseId = oldMainEntity.getCaseId();
					if (CASE_ACTION_NEW.equals(oldCaseAction) 
							&& CASE_DISPOSITION_CANCEL.equals(oldCaseDisposition)) {
						                                          // 原來 NEW && CANCEL拒絕 ,可以add[NEW]
						checkStep = "new 1.2";
						canAdd = Boolean.TRUE;        
					} else if (CASE_ACTION_STOP.equals(oldCaseAction)
							&& CASE_DISPOSITION_APPROVAL.equals(oldCaseDisposition)) {
						                                          // 原來 STOP && APPROVAL核准 ,可以add[NEW]
						checkStep = "new 1.3";
						canAdd = Boolean.TRUE; 
					} else {                         
						checkStep = "new 1.4";
						canAdd = Boolean.FALSE; 
					}
				}
			} else if (CASE_ACTION_STOP.equalsIgnoreCase(caseAction)) { //2  停用[STOP]邏輯
				if (oldMainEntity == null) {
					                                              // 沒有Main,不可以 add STOP(如不存在不可停用)
					checkStep = "stop 2.1";
					canAdd = Boolean.FALSE; 
				} else {
					isExistMain = Boolean.TRUE;
					oldCaseAction = oldMainEntity.getCaseAction();
					oldCaseDisposition = oldMainEntity.getCaseDisposition();
					oldCaseId = oldMainEntity.getCaseId();
					if (CASE_ACTION_NEW.equals(oldCaseAction)
							&& CASE_DISPOSITION_APPROVAL.equals(oldCaseDisposition)) {
						                                          // 原來 NEW && APPROVAL核准 ,可以add[STOP]
						checkStep = "stop 2.2";
						canAdd = Boolean.TRUE; 
					} else if (CASE_ACTION_STOP.equals(oldCaseAction)
							&& CASE_DISPOSITION_CANCEL.equals(oldCaseDisposition)) {
						                                          // 原來 STOP && CANCEL拒絕 ,可以add[STOP]
						checkStep = "stop 2.3";
						canAdd = Boolean.TRUE; 
					} else {                         
						checkStep = "stop 2.4";
						canAdd = Boolean.FALSE; 
					}
				}
			}
			String logMsg = String.format("input.caseAction:%s ; input.partyNumber :%s  ; input.partyName:%s ;\n isExistMain: %s ;     oldCaseAction: %s ;  oldCaseDisposition: %s  ; oldCaseId:%s  ; checkStep: %s  ;canAdd:%s  ",
							caseAction ,partyNumber ,partyName, isExistMain.toString(), oldCaseAction,
							oldCaseDisposition, oldCaseId,checkStep,String.valueOf(canAdd));
			output.setLogMsg(logMsg);
			output.setScriptIsCanAddPartyWhite(canAdd);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			output.setErrorMsg(e.getMessage());
			output.setIsSuccess(Boolean.FALSE);
			log.append("\nIsSuccess:false");
		}
		return output;
	}


	@Override
	public List<PartyWhiteListOutputBean> scriptClientQueryPartyWhiteList(
			PartyWhiteListInputBean input) {
		String partyName = input.getPartyName();
		String partyNumber = input.getPartyNumber();
		List<PartyWhiteListOutputBean> resultList = new ArrayList<PartyWhiteListOutputBean>();
		List<XPartyWhitelistMain> list =  new ArrayList<XPartyWhitelistMain>();;
		try{
			if (StringUtils.isNotBlank(partyName)) {
				partyName = "%" + partyName + "%"; 
			}
			
	
			if (StringUtils.isNotBlank(partyName) && StringUtils.isBlank(partyNumber))
				list = iXPartyWhitelistMainDao.findByIdPartyNameLike(partyName);
			else if (StringUtils.isBlank(partyName) && StringUtils.isNotBlank(partyNumber))
				list = iXPartyWhitelistMainDao.findByIdPartyNumber(partyNumber);
			else if (StringUtils.isNotBlank(partyName) && StringUtils.isNotBlank(partyNumber))
				list = iXPartyWhitelistMainDao.findByIdPartyNumberAndIdPartyNameLike(
						partyNumber, partyName);
			else {
				List<XPartyWhitelistMain> result = iXPartyWhitelistMainDao.findByCaseActionAndCaseDisposition(CASE_ACTION_NEW, CASE_DISPOSITION_APPROVAL);
				list.addAll(result);
			}
			if (list != null && list.size()>0) {
				for (XPartyWhitelistMain main : list) {
					PartyWhiteListOutputBean out = new PartyWhiteListOutputBean();
					out.setPartyNumber(main.getId().getPartyNumber());                  
					out.setPartyName(main.getId().getPartyName());                      
					out.setRemark(main.getRemark());                                    
					out.setCaseId(main.getCaseId());                                    
					out.setCaseAction(main.getCaseAction());                            
					out.setCreateDate(main.getCreateDate().getTime());                 
					out.setCreateUser(main.getCreateUser());                           			
					out.setCaseDisposition(main.getCaseDisposition());                  
					out.setIsWhitelist(main.getIsWhitelist());                         
					resultList.add(out);
				}
			} 
		}catch(Exception e){
			logger.error("scriptClientQueryPartyWhiteList method error:", e.getMessage());	
		}
		return resultList;
	}
	@Override
	public List<PartyWhiteListOutputBean> scriptClientQueryPartyWhiteHisList(PartyWhiteListInputBean input)  {
		String partyName = input.getPartyName();
		String partyNumber = input.getPartyNumber();
		List<PartyWhiteListOutputBean> resultList = new ArrayList<PartyWhiteListOutputBean>();
		List<XPartyWhitelistHis> listHis = new ArrayList<XPartyWhitelistHis>();
		List<XPartyWhitelistMain> list = new ArrayList<XPartyWhitelistMain>();
		List<XPartyWhitelistHis> combineList = new ArrayList<XPartyWhitelistHis>();

		try{
			if (StringUtils.isNotBlank(partyName)) {
				partyName = "%" + partyName + "%";
			}
			
	
			if (StringUtils.isNotBlank(partyName) && StringUtils.isBlank(partyNumber)){
				listHis = iXPartyWhitelistHisDao.findByPartyNameLike(partyName);
				list = iXPartyWhitelistMainDao.findByIdPartyNameLikeAndCaseAction(partyName,CASE_ACTION_STOP);
				combineList=convertMainToHisList(list,listHis);
			}else if (StringUtils.isBlank(partyName) && StringUtils.isNotBlank(partyNumber)){
				listHis = iXPartyWhitelistHisDao.findByPartyNumber(partyNumber);
				list = iXPartyWhitelistMainDao.findByIdPartyNumberAndCaseAction(partyNumber,CASE_ACTION_STOP);
				combineList=convertMainToHisList(list,listHis);
			}else if (StringUtils.isNotBlank(partyName) && StringUtils.isNotBlank(partyNumber)){
				listHis = iXPartyWhitelistHisDao.findByPartyNumberAndPartyNameLike(partyNumber, partyName);
				list = iXPartyWhitelistMainDao.findByIdPartyNumberAndIdPartyNameLikeAndCaseAction(partyNumber, partyName,CASE_ACTION_STOP);
				combineList=convertMainToHisList(list,listHis);
			}else {
				listHis = iXPartyWhitelistHisDao.findAll();
				list = iXPartyWhitelistMainDao.findByCaseAction(CASE_ACTION_STOP);

				combineList=convertMainToHisList(list,listHis);
			}
			if (combineList != null && combineList.size()>0) {
				for (XPartyWhitelistHis his : combineList) {
					PartyWhiteListOutputBean out = new PartyWhiteListOutputBean();
					out.setPartyNumber(his.getPartyNumber());                  
					out.setPartyName(his.getPartyName());                      
					out.setRemark(his.getRemark());                                    
					out.setCaseId(his.getCaseId());                                    
					out.setCaseAction(his.getCaseAction());                            
					out.setCreateDate(his.getCreateDate().getTime());                 
					out.setCreateUser(his.getCreateUser());                           			
					out.setCaseDisposition(his.getCaseDisposition());                 
					resultList.add(out);
				}
			}
		}catch(Exception e){
			logger.error("scriptClientQueryPartyWhiteHisList method error:", e.getMessage());	
		}
		return resultList;
	}		
	
	/**
	 * 從CaseId 取出CaseRk 
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	private Long getCaseRk(String caseId) throws Exception {
		if (StringUtils.indexOf(caseId, "-") > 0)
			return Long.valueOf(caseId.split("-")[1]);
		else
			throw new Exception("getCaseRk,Error caseId:" + caseId);
	}
	
	/**
	 * 從CaseId 取出CaseRk
	 * @param input
	 * @return
	 * @throws Exception
	 */
	private Long getCaseRk(PartyWhiteListInputBean input) throws Exception{
		return getCaseRk(input.getCaseId());
	}
	
	/**
	 * 
	 * 只有WorkFlow作closeCase 才查的到caseLive.CaseDispositionCd
	 * 
	 * APPROVAL / CANCEL
	 * 
	 * caseLive.getCaseDispositionCd
	 * @return APPROVAL or CANCEL or null(尚未進行審核)
	 */
	private String queryDisposition(BigDecimal caseRK) throws Exception {
		CaseLive caseLive = iCaseLiveDao.findOne(caseRK);
		String disposition = null; // APPROVAL,CANCEL
		if (caseLive != null) {
			disposition = caseLive.getCaseDispositionCd(); // APPROVAL,CANCEL
		} else {
			throw new Exception("not Found Case:" + caseRK);
		}
		return disposition;
	}
	
	/**
	 * 取得自訂欄位的值
	 * @param caseRk
	 * @param udfNm
	 * @return
	 */
	public String findCharUdfValue(Long caseRk, String udfNm) { 
		CaseUdfCharValue udf = iCaseUdfCharValueDao.findFirstByIdCaseRkAndIdUdfNameOrderByIdValidFromDttmDesc(caseRk, udfNm);
		if(udf != null) {
			return udf.getUdfValue();
		}
		logger.debug("findCharUdfValue:{}", udf);
		return "";
	} 
	private ArrayList<XPartyWhitelistHis> convertMainToHisList(List<XPartyWhitelistMain> list, List<XPartyWhitelistHis> listHis) {
		ArrayList<XPartyWhitelistHis> resultList = new ArrayList<XPartyWhitelistHis>();
		for(XPartyWhitelistMain xPartyWhitelistMain:list){
			XPartyWhitelistHis xPartyWhitelistHis=new XPartyWhitelistHis();
			xPartyWhitelistHis.setCaseAction(xPartyWhitelistMain.getCaseAction());
			xPartyWhitelistHis.setCaseDisposition(xPartyWhitelistMain.getCaseDisposition());
			xPartyWhitelistHis.setCaseId(xPartyWhitelistMain.getCaseId());
			xPartyWhitelistHis.setCreateDate(xPartyWhitelistMain.getCreateDate());
			xPartyWhitelistHis.setCreateUser(xPartyWhitelistMain.getCreateUser());
			xPartyWhitelistHis.setPartyName(xPartyWhitelistMain.getId().getPartyName());
			xPartyWhitelistHis.setPartyNumber(xPartyWhitelistMain.getId().getPartyNumber());
			xPartyWhitelistHis.setRemark(xPartyWhitelistMain.getRemark());
			resultList.add(xPartyWhitelistHis);
		}
		resultList.addAll(listHis);
		return resultList;
	}		
}
