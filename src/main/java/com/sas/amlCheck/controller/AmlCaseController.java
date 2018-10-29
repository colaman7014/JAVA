package com.sas.amlCheck.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sas.amlCase.viewBean.QueryAllHitRecordReq;
import com.sas.amlCase.viewBean.QueryAllHitRecordResp;
import com.sas.amlCase.viewBean.SaveCaseResultReq;
import com.sas.amlCase.viewBean.UpdateChkRsltReq;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.fcfcore.XComboWhitelistMain;
import com.sas.db.wlf.orm.tf.XInvExportCaseItem;
import com.sas.db.wlf.orm.tf.XInvImportCaseItem;
import com.sas.tradeFinance.service.XInvCreateCase;
import com.sas.webservice.createCase.AmlComboWhitelistCase;
import com.sas.webservice.createCase.AmlCreateCase;
import com.sas.webservice.createCase.bean.CreateCaseInputBean;
import com.sas.webservice.nameCheck.AmlAsynBatchCheck;

@Controller
@RequestMapping("/rest")
public class AmlCaseController{
	private static final Logger logger = LoggerFactory.getLogger(AmlCaseController.class);
	
	@Autowired
	AmlCreateCase amlCreateCase;
	
	@Autowired
	XInvCreateCase xInvCreateCase;
	
	@Autowired
	AmlComboWhitelistCase amlComboWhitelistCase;
	
	@Autowired
	AmlAsynBatchCheck amlAsynBatchCheck;
	
	@RequestMapping(value="/queryHitRecord", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public QueryAllHitRecordResp queryHitRecord(@RequestBody QueryAllHitRecordReq req,HttpServletRequest httpReq) throws Exception {
		String locale = com.sas.util.StringUtils.getLocale(httpReq.getLocale());
		QueryAllHitRecordResp resp = new QueryAllHitRecordResp();
		logger.debug("Query Name Check Record Detail locale:", locale);
		try {
			if(!"SC".equals(req.getSourceType().split("-")[1])){
				resp.setCheckSeq(amlCreateCase.queryNCDetail(req.getCaseRk()));
			}
			resp.setHitRecord(amlCreateCase.queryHitRecord(req.getRefId(), req.getCaseRk(), req.getSourceType(),locale));
			resp.setStatus("success");
		} catch (Exception e) {
			logger.error("queryHitRecord", e);
			resp.setStatus("error");
		}
		return resp;
	}
	
	@RequestMapping(value="/queryAllHitRecord", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public QueryAllHitRecordResp queryAllHitRecord(@RequestBody QueryAllHitRecordReq req, HttpServletRequest httpReq) throws Exception {
		String locale = com.sas.util.StringUtils.getLocale(httpReq.getLocale());
		logger.debug("Query Name Check Record Detail locale:", locale);
		QueryAllHitRecordResp resp = new QueryAllHitRecordResp();
		try {
			resp = amlCreateCase.queryCaseHitRecord(req,locale);
			resp.setStatus("success");
		} catch (Exception e) {
			logger.error("queryHitRecord", e);
			resp.setStatus("error");
		}
		return resp;
	}
	
	@RequestMapping(value="/updateCheckResult", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> updateCheckResult(@RequestBody UpdateChkRsltReq  updateChkRslt) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info(updateChkRslt.toString());
		try {
			String saveStatus =   amlCreateCase.updateCheckResult(updateChkRslt);
			result.put("status", saveStatus);
		} catch (Exception e) {
			logger.error("updateCheckResult", e);
			result.put("status", "errors");
		}
		
		return result;
	}
	
	@RequestMapping(value="/updateWhiteList", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> updateWhiteList(@RequestBody UpdateChkRsltReq  updateChkRslt) {

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String saveStatus =  amlCreateCase.updateWhiteList(updateChkRslt);
			result.put("status", saveStatus);
		} catch (Exception e) {
			logger.error("updateWhiteList", e);
			result.put("status", "errors");
		}
		return result;
	}
	
	@RequestMapping(value="/saveCaseResult", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> saveCaseResult(@RequestBody SaveCaseResultReq  req) {
		logger.info("saveCaseResult, refId:" + req.getRefId() + ", caseRk:" + req.getCaseRk());
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String saveStatus = amlCreateCase.saveCaseResult(req.getRefId(), req.getCaseRk(), req.getViewId());
			result.put("status", saveStatus);
		} catch (Exception e) {
			logger.error("saveCaseResult", e);
			result.put("status", "errors");
		}
		return result;
	}
	
	@RequestMapping(value="/ncscCloseCase", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> closeCase(@RequestParam String caseRk, @RequestParam String sourceType, @RequestParam String ncResult, @RequestParam String closeReason) {
		logger.info("closeCase, caseRk:" + caseRk + ", sourceType:" + sourceType + ", ncResult: "+ ncResult);
		String saveStatus = amlCreateCase.closeCase(Long.valueOf(caseRk), sourceType, ncResult, closeReason);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", saveStatus);
		return result;
	}
	
	@RequestMapping(value="createCase", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> createCase(@RequestBody CreateCaseInputBean input) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			BigDecimal caseRk = amlCreateCase.createCase(input);
			result.put("result", caseRk);
		} catch (Exception e) {

			logger.error("createCase", e);
			
			result.put("result", "error");
		}
		return result;
		
	}
	
	/**
	 * 依據案件編號查詢該案件的電文原始內容
	 * @param caseRk
	 * @return
	 */
	@RequestMapping(value="getSwiftFullText", method={RequestMethod.GET},  produces=MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String getSwiftFullText(@RequestParam String caseRk) {
		Map<String, Object> result = new HashMap<String, Object> ();
		if(StringUtils.isBlank(caseRk)) {
			return "Show Swift Msg Failed";
		}
		try {
			return amlCreateCase.getSwiftFullText(Long.valueOf(caseRk));
		} catch (Exception e) {
			return "Show Swift Msg Failed";
		}
	} 
	
	/**
	 * 依據案件編號查詢該案件的出口發票內各品項明細
	 * @param caseRk
	 * @return
	 */
	@RequestMapping(value="getInvImportMatchItem", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getInvImportMatchItem(@RequestParam BigDecimal caseRk) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, List<XInvImportCaseItem>> matchItems = xInvCreateCase.getInvImportCaseItemByCaseRk(caseRk);
			result.put("status", "success");
			result.put("result", matchItems);
		} catch (Exception e) {
			logger.error("getInvImportMatchItem", e);
			result.put("status", "error");
		}
		return result;
	} 
	
	/**
	 * 依據案件編號查詢該案件的進口發票內各品項明細
	 * @param caseRk
	 * @return
	 */
	@RequestMapping(value="getInvExportMatchItem", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getInvExportMatchItem(@RequestParam BigDecimal caseRk) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, List<XInvExportCaseItem>> matchItems = xInvCreateCase.getInvExportCaseItemByCaseRk(caseRk);
			result.put("status", "success");
			result.put("result", matchItems);
		} catch (Exception e) {
			logger.error("getInvImportMatchItem", e);
			result.put("status", "error");
		}
		return result;
	}
	/**
	 * 取得交易組合白名單顯示名稱
	 * 判斷使否已存在白名單內，或是已建案但未完成、
	 * @param partyNumber
	 * @param watchListKey
	 * @return
	 */
	@RequestMapping(value="getComboWhiteListEntityName", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String getComboWhiteListEntityName(@RequestParam String partyNumber, @RequestParam String watchListKey) {
		try {
			XComboWhitelistMain  main = amlComboWhitelistCase.findComboWhiteList(partyNumber, watchListKey);
			if(amlComboWhitelistCase.isCanCreate(main)){
				return main.getEntityDisplayName();
			}
			
		} catch (Exception e) {
			logger.error("getComboWhiteListEntityName", e);
		}
		return SwiftMtConst.COMBO_WHITE_LIST_EMPTY;
	}
	
	/**
	 * 取得交易組合白名單顯示名稱
	 * 判斷使否已存在白名單內，或是已建案但未完成、
	 * @param partyNumber
	 * @param watchListKey
	 * @return
	 */
	@RequestMapping(value="getStopComboWhiteListEntityName", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String getStopComboWhiteListEntityName(@RequestParam String partyNumber, @RequestParam String watchListKey) {
		try {
			XComboWhitelistMain  main = amlComboWhitelistCase.findComboWhiteList(partyNumber, watchListKey);
			if(main != null ) {
				return main.getEntityDisplayName();
			} else {
				return SwiftMtConst.COMBO_WHITE_LIST_EMPTY;
			}
		} catch (Exception e) {
			logger.error("getComboWhiteListEntityName", e);
		}
		return SwiftMtConst.COMBO_WHITE_LIST_EMPTY;
	}
	/**
	 * 依據partyNumber及entityName尋找目前交易組合白名單
	 * @param partyNumber
	 * @param entityName
	 * @return
	 */
	@RequestMapping(value="getComboWhitelist", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getComboWhitelist(@RequestParam String partyNumber, @RequestParam String entityName) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			result.put("status", "success");
			result.put("result", amlComboWhitelistCase.findComboWhiteListbyPartyNumberAndEntityName(partyNumber, entityName)) ;
		} catch (Exception e) {
			logger.error("getComboWhiteListEntityName", e);
			result.put("status", "error");
		}
		return result;
	}
	
	/**
	 * 依據partyNumber及entityName尋找目前交易組合白名單
	 * @param partyNumber
	 * @param entityName
	 * @return
	 */
	@RequestMapping(value="/comboWhiteListcloseCase", method={RequestMethod.POST})
	@ResponseBody
	public void comboWhiteListcloseCase(@RequestParam String caseId) {
		logger.info("comboWhiteListcloseCase:{}", caseId);
		try {
			amlComboWhitelistCase.closeCase(caseId);;
		} catch (Exception e) {
			logger.error("comboWhiteListcloseCase", e);
		}
	}
	
	
	/**
	 * 停用交易組合白名單建立CASE
	 * @param partyNumber
	 * @param entityName
	 * @return
	 */
	@RequestMapping(value="/createStopComboWhiteListCase", method={RequestMethod.POST})
	@ResponseBody
	public void createStopComboWhiteListCase(@RequestParam String caseId, @RequestParam String userId) {
		logger.info("createStopComboWhiteListCase:{}", caseId);
		try {
			amlComboWhitelistCase.createStopComboWhiteListCase(caseId, userId);;
		} catch (Exception e) {
			logger.error("createStopComboWhiteListCase", e);
		}
	}
	
	/**
	 * 夜批名單掃描手動建立CASE
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value="/createBatchNameCheckCase", method={RequestMethod.GET})
	@ResponseBody
	public String createBatchNameCheckCase(@RequestParam("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
		logger.info("createBatchNameCheckCase:", fromDate, endDate);
		StringBuilder stringBuilder = new StringBuilder("Process Started");
		try {
			amlAsynBatchCheck.createCaseByBatchNameCheckResult(fromDate, endDate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return stringBuilder.toString();
	}
	
}