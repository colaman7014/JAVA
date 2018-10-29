package com.sas.amlCheck.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import com.sas.amlCase.viewBean.QueryPartyWhiteListResp; 
import com.sas.webservice.createCase.PartyWhiteListCase;
import com.sas.webservice.createCase.bean.PartyWhiteListInputBean;
import com.sas.webservice.createCase.bean.PartyWhiteListOutputBean;

/**
 * 客製化功能/rest/cuzWhitelist 客戶白名單
 * @author SAS
 *
 */
@Controller
@RequestMapping("/rest/cuzWhitelist")
public class AmlCaseCustomizeWhitelistController{
	private static final Logger logger = LoggerFactory.getLogger(AmlCaseCustomizeWhitelistController.class);
	 
	
	@Autowired
	PartyWhiteListCase partyWhiteListCase; //客製化的客戶白名單
	
	 
	 
	/**
	 * 客戶白名單
	 * 給WorkFlow save將CASE_UDF_CHAR_VALUE 等欄位 寫入到X_PARTY_WHITELIST_MAIN
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/workFlowSavePartyWhiteListMain", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> workFlowSavePartyWhiteListMain(@RequestParam String caseId, @RequestParam String userId) {
		logger.debug(String.format("workFlowSavePartyWhiteListMain: %s , %s" , caseId , userId));
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PartyWhiteListOutputBean resultBean =  partyWhiteListCase.workFlowSavePartyWhiteListMain(caseId,userId);
			if (Boolean.TRUE.equals(resultBean.getIsSuccess()))
				result.put("status", "success");
			else {
				result.put("status", "error");
				result.put("errormsg", resultBean.getErrorMsg());
			}
			logger.debug(resultBean.toString());
		} catch (Exception e) {
			logger.error("workFlowSavePartyWhiteListMain error", e);
			result.put("status", "errors");
			result.put("errormsg", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 客戶白名單
	 * 給workFlow CloseCase異動X_PARTY_WHITELIST_MAIN].IS_WHITELIST
	 * IS_WHITELIST規則:
	 * 停用STOP已核准APPROVAL=N
     * 啟用NEW已核准APPROVAL=Y
	 * @param caseId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/workFlowCloseCasePartyWhiteList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> workFlowCloseCasePartyWhiteList(@RequestParam String caseId, @RequestParam String userId, @RequestParam String disposition) {
		logger.debug(String.format("workFlowCloseCasePartyWhiteList:caseId: %s , userId: %s, disposition: %s" , caseId , userId,disposition));
 		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PartyWhiteListOutputBean resultBean = partyWhiteListCase.workFlowCloseCasePartyWhiteList(caseId, userId,disposition);
			if (Boolean.TRUE.equals(resultBean.getIsSuccess()))
				result.put("status", "success");
			else {
				result.put("status", "error");
				result.put("errormsg", resultBean.getErrorMsg());
			}
			logger.debug(resultBean.toString());
		} catch (Exception e) {
			logger.error("workFlowCloseCasePartyWhiteList error", e);
			result.put("status", "errors");
			result.put("errormsg", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 客戶白名單
	 * 給client script 檢查客戶編號是否存在PARTY
	 * @param partyNumber
	 * @return true/false
	 */
	@RequestMapping(value="/scriptClientCheckPartyNumberExist", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String scriptClientCheckPartyNumberExist(
			@RequestParam String partyNumber) {
		boolean isPartyNumberExist = false;
		 
		logger.info(String.format("scriptClientCheckPartyNumberExist: %s ",
				partyNumber));
		
		try {
			PartyWhiteListOutputBean resultBean = partyWhiteListCase.scriptClientCheckPartyNumberExist(partyNumber);
			if (resultBean != null) {
				if (resultBean.getScriptCheckPartyNumberExist() != null) {
					isPartyNumberExist = resultBean.getScriptCheckPartyNumberExist().booleanValue();
				}
				if (Boolean.FALSE.equals(resultBean.getIsSuccess())) {
					logger.error("scriptClientCheckPartyNumberExist error:" + resultBean.toString());
				} else {
					logger.debug("scriptClientCheckPartyNumberExist OK:" + resultBean.toString());
				}
			}
		} catch (Exception e) {
			logger.error("scriptClientCheckPartyNumberExist error", e);			
		}
		return String.valueOf(isPartyNumberExist);
	}
	
	
	/**
	 * 客戶白名單
	 * 給client script  檢查可以新增X_PARTY_WHITELIST_MAIN
	 * 新增時需檢核同一客戶編號，客戶名稱是否已在[FCFCOREHK0].[X_PARTY_WHITELIST_MAIN]，如不存在即可新增，如存在需判斷如下
	 * 
	 * 1.ActionNEW新增邏輯
	 *   if( old_MAIN不存在)  then return canAdd=true;
	 *   if( old_MAIN.CASE_ACTION==NEW  && old_MAIN.CASE_DISPOSITION==CANCEL)   then return canAdd=true;
	 *   if( old_MAIN.CASE_ACTION==STOP && old_MAIN.CASE_DISPOSITION==APPROVAL) then return canAdd=true;
	 * 
	 * 2.ActionSTOP停用邏輯
	 *   if( old_MAIN不存在)  then return canAdd=false;
	 *   if( old_MAIN.CASE_ACTION==NEW  && old_MAIN.CASE_DISPOSITION==APPROVAL) then return canAdd=true;
	 *   if( old_MAIN.CASE_ACTION==STOP && old_MAIN.CASE_DISPOSITION==CANCEL)   then return canAdd=true;
	 * @param req CaseAction PartyNumber PartyName
	 * @return true/false
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/scriptClientIsCanAddPartyWhite", method={RequestMethod.POST, RequestMethod.GET}, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public String scriptClientIsCanAddPartyWhite(
			@RequestParam String caseAction ,@RequestParam String partyNumber ,@RequestParam String partyName) throws UnsupportedEncodingException {
		logger.info(String.format("scriptClientIsCanAddPartyWhite req: CaseAction:%s, PartyNumber:%s , PartyName:%s ",
				caseAction, partyNumber, partyName));
		if(partyName!=null){
				try {
					partyName=UriUtils.decode(partyName, "UTF-8");
				}  catch (UnsupportedEncodingException e) {
					logger.error("UnsupportedEncodingException:", e);
				} 
		}
		boolean isCanAddPartyWhite = false;
		PartyWhiteListInputBean req = new PartyWhiteListInputBean();
		req.setCaseAction(caseAction);
		req.setPartyNumber(partyNumber);
		req.setPartyName(partyName);
		try {
			PartyWhiteListOutputBean resultBean = partyWhiteListCase.scriptClientIsCanAddPartyWhite(req);
			if (resultBean != null) {
				if (resultBean.getScriptIsCanAddPartyWhite() != null) {
					isCanAddPartyWhite = resultBean.getScriptIsCanAddPartyWhite().booleanValue();
				}
				if (Boolean.FALSE.equals(resultBean.getIsSuccess())) {
					logger.error("scriptClientIsCanAddPartyWhite error:" + resultBean.toString());
				} else {
					logger.debug("scriptClientIsCanAddPartyWhite success return resultBean:" + resultBean.toString());
				}
			}
		} catch (Exception e) {
			logger.error("scriptClientIsCanAddPartyWhite error", e);	
		}
		logger.debug(" scriptClientIsCanAddPartyWhite return's boolean value:" + String.valueOf(isCanAddPartyWhite));		
		return String.valueOf(isCanAddPartyWhite);
	}
	
	/**
	 * 客戶白名單
	 * 1.畫面查詢條件為客戶編號，客戶名稱(like)
     * 2.查詢結果須包含名單編號，客戶編號，客戶名稱，NEW啟用/STOP停用，狀態，建立者，建立時間
     * 
     * 狀態  "APPROVAL":已核准  ;  "CANCEL":已拒絕 ; "":待覆核
	 * @param req PartyNumber PartyName
	 * @return
	 */
	@RequestMapping(value="/queryPartyWhiteList", method={RequestMethod.POST, RequestMethod.GET}, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public QueryPartyWhiteListResp scriptClientQueryPartyWhiteList(
			@RequestBody PartyWhiteListInputBean req) {
		logger.info(String.format("scriptClientQueryPartyWhiteList req: PartyNumber:%s , PartyName:%s ",
				req.getPartyNumber(), req.getPartyName()));
		String partyName=req.getPartyName();
		if(partyName!=null){
			try {
				partyName=UriUtils.decode(partyName, "UTF-8");
			}  catch (UnsupportedEncodingException e) {
				logger.error("UnsupportedEncodingException:", e);
			} 
		}
		QueryPartyWhiteListResp resp = new QueryPartyWhiteListResp();
		try {
			List<PartyWhiteListOutputBean> resultList = partyWhiteListCase.scriptClientQueryPartyWhiteList(req);
			resp.setResultList(resultList);
			if (resultList != null && resultList.size() > 0) {
				resp.setStatus("success");
			} else {
				resp.setStatus("warnings");
				resp.setMessage("noData");
			}
		} catch (Exception e) {
			logger.error("queryHitRecord", e);
			resp.setStatus("error");
		}
		return resp;
	}
	@RequestMapping(value="/queryPartyWhiteHisList", method={RequestMethod.POST, RequestMethod.GET}, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public QueryPartyWhiteListResp scriptClientQueryPartyWhiteHisList(
			@RequestBody PartyWhiteListInputBean req) {
		logger.info(String.format("scriptClientQueryPartyWhiteHisList req: PartyNumber:%s , PartyName:%s ",
				req.getPartyNumber(), req.getPartyName()));
		String partyName=req.getPartyName();
		if(partyName!=null){
			try {
				partyName=UriUtils.decode(partyName, "UTF-8");
			}  catch (UnsupportedEncodingException e) {
				logger.error("UnsupportedEncodingException:", e);
			} 
		}	
		QueryPartyWhiteListResp resp = new QueryPartyWhiteListResp();
		try {
			List<PartyWhiteListOutputBean> resultList = partyWhiteListCase.scriptClientQueryPartyWhiteHisList(req);
			resp.setResultList(resultList);
			if (resultList != null && resultList.size() > 0) {
				resp.setStatus("success");
			} else {
				resp.setStatus("warnings");
				resp.setMessage("noData");
			}
		} catch (Exception e) {
			logger.error("queryHitRecord", e);
			resp.setStatus("error");
		}
		return resp;
	}
	
}
