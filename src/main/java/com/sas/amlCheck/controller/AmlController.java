package com.sas.amlCheck.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sas.aml.uploader.bean.NameCheckRecordDetailBean;
import com.sas.aml.uploader.bean.QueryInvBlUploadFileResp;
import com.sas.aml.uploader.bean.QueryNameCheckRecordDetailResp;
import com.sas.aml.uploader.bean.QueryNameCheckRecordMainResp;
import com.sas.aml.uploader.bean.QueryNameCheckUploadReq;
import com.sas.aml.uploader.bean.QueryNameCheckUploadResp;
import com.sas.aml.uploader.bean.QueryUploadRecordReq;
import com.sas.aml.uploader.bean.QueryUploadRecordResp;
import com.sas.amlCase.viewBean.MailUploadBean;
import com.sas.amlCase.viewBean.MailUploaderReq;
import com.sas.amlCase.viewBean.MailUploaderResp;
import com.sas.amlCheck.service.AmlBlInvUploadDataQueryService;
import com.sas.amlCheck.service.AmlNameCheckService;
import com.sas.amlCheck.service.AmlNameCheckStatusService;
import com.sas.amlCheck.service.AmlUploadFileQueryService;
import com.sas.amlCheck.service.AmlUploaderMailCheckService;
import com.sas.amlCheck.service.CurrencyOptionQueryService;
import com.sas.amlCheck.service.OnlineNameQueryOptionService;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.ecm.FullRefTableTran;
import com.sas.db.aml.orm.fcfcore.FscCurrencyDim;
import com.sas.db.wlf.orm.XInvBlNcUploadRecord;
import com.sas.webservice.nameCheck.bean.NameCheckInputRestBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputRestBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusDetailInputRestBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusDetailoutputRestBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusMainInputRestBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusMainoutputRestBean;

@RestController
@RequestMapping("/rest")
public class AmlController{
	private static final Logger logger = LoggerFactory.getLogger(AmlController.class);
	@Autowired
	AmlNameCheckService amlNameCheckService;
	@Autowired
	AmlNameCheckStatusService amlNameCheckStatusService;
	@Autowired
	OnlineNameQueryOptionService onlineNameQueryOptionService;
	@Autowired 
	AmlUploaderMailCheckService amlUploaderMailCheckService;
	@Autowired
	AmlUploadFileQueryService amlUploadFileQueryService;
	@Autowired
	AmlBlInvUploadDataQueryService amlBlInvUploadDataQueryService;
	@Autowired
	CurrencyOptionQueryService currencyOptionQueryService;	
	@RequestMapping(value="/test/{str}", method=RequestMethod.GET)
	public void test(@PathVariable String str) {
		// TODO Auto-generated method stub
		logger.debug("test str : {}", str);
	}

	
	@RequestMapping(value="/onlineNameCheck", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public NameCheckOutputRestBean onlineNameCheck(@RequestBody NameCheckInputRestBean nameCheckInputRestBean, HttpServletRequest req) {
		String locale = com.sas.util.StringUtils.getLocale(req.getLocale());
		return amlNameCheckService.onlineNameCheck(nameCheckInputRestBean, locale);
	}
	
	@RequestMapping(value="/nameCheckStatusMain", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<NameCheckStatusMainoutputRestBean> nameCheckStatusMain(@RequestBody NameCheckStatusMainInputRestBean nameCheckStatusMainInputRestBean) {
		// TODO Auto-generated method stub
		return amlNameCheckStatusService.nameCheckStatusMain(nameCheckStatusMainInputRestBean);
	}
	
	@RequestMapping(value="/nameCheckStatusDetail", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<NameCheckStatusDetailoutputRestBean> nameCheckStatusDetail(@RequestBody NameCheckStatusDetailInputRestBean nameCheckStatusDetailInputRestBean) {
		// TODO Auto-generated method stub
		return amlNameCheckStatusService.nameCheckStatusDetail(nameCheckStatusDetailInputRestBean);
	}
	
	@RequestMapping(value="/onlineNameQueryOptionService", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<FullRefTableTran> onlineNameQuerytypeDesc(HttpServletRequest req) {
		String locale = com.sas.util.StringUtils.getLocale(req.getLocale());
		return onlineNameQueryOptionService.onlineNameQuerytypeDesc(locale);
	}
	
	@RequestMapping(value="/uploadDataForMailBatchSend")
	@ResponseBody
	public MailUploaderResp uploadDataForMailBatchSend(@ModelAttribute MailUploaderReq mailUploaderReq){
		// To Initial Response
		MailUploaderResp resp = new MailUploaderResp();
		// To Generate Input Data By Upload Data
		MailUploadBean inputData = new MailUploadBean();
		MultipartFile multipartFile = mailUploaderReq.getUploadedfile();
		
		inputData.setSend(false);
		inputData.setMailContent(mailUploaderReq.getMailContent());
		inputData.setReceiver(mailUploaderReq.getReceiver());
		inputData.setSender(mailUploaderReq.getSender());
		inputData.setSubject(mailUploaderReq.getSubject());
		inputData.setSource("ManualUploadMail");
		// To Validate Upload Data Format
		
		try {
			inputData = amlUploaderMailCheckService.checkUploadMailFormat(inputData, multipartFile);
		} catch (Exception e) {
			logger.error("uploadDataForMailBatchSend", e);
			inputData.setFormatErr(true);
			inputData.setErrMsg(
					String.format(SwiftMtConst.MAIL_ATTACHEMENT_UPLOAD_ERROR_MSG, e.getMessage()));
		}
		
		
		// To Check Validated Data
		if(inputData.isFormatErr()){
			resp.setStatus("errors");
			resp.setMessage(inputData.getErrMsg());
		}else{
			resp.setStatus("infos");
		}
		return resp;
	}
	
	/**************************************************************************************/
	// To Download Result File From [X_INV_BL_NC_UPLOAD_RECORD] 
	@RequestMapping(value="/downloadResultFile/{fileKey}/{fileIdx}", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)  
	@ResponseBody
	public byte[] downloadResultFile(@PathVariable String fileKey, @PathVariable int fileIdx, HttpServletResponse resp) {
		try {
			logger.debug("fileKey : " + fileKey);
			
			// To query File Path By file path by file key
			XInvBlNcUploadRecord resultfile = amlUploadFileQueryService.getFilePathByFileKey(fileKey);
			String filePath = resultfile.getResultPath();
			String fileNames[] = resultfile.getResultFile().split(",");
			logger.debug("fileName : " + fileNames[fileIdx]);
			logger.debug("Full File Path : " + String.format("%s%s", filePath, fileNames[fileIdx]));
			File file = new File(String.format("%s%s", filePath, fileNames[fileIdx]));
			logger.debug("file.getAbsolutePath() : " + file.getAbsolutePath());
			if(file.exists()){
				FileInputStream fis = new FileInputStream(file);
				resp.setHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
				resp.setHeader("Content-Length", String.valueOf(file.length()));
				resp.setHeader("Content-Disposition", String.format("inline; filename=%s", fileNames[fileIdx]));
				return IOUtils.toByteArray(fis);
			}
		} catch (Exception e) {
			logger.error("downloadResultFile", e);
		}
		
		return null;
	}
	
	// To Download Origin File From [X_INV_BL_NC_UPLOAD_RECORD] 
	@RequestMapping(value="/downloadOrigineFile/{fileKey}", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[] downloadOrigineFile(@PathVariable String fileKey, HttpServletResponse resp) {
		logger.debug("fileKey : " + fileKey);
		
		try {
			// To query File Path By file path by file key
			XInvBlNcUploadRecord resultfile = amlUploadFileQueryService.getFilePathByFileKey(fileKey);
			String filePath = resultfile.getFilePath();
			String fileName = resultfile.getFilePath();
			File file = new File(String.format("%s%s", filePath, fileName));
			if(file.exists()){
				FileInputStream fis = new FileInputStream(file);
				resp.setHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
				resp.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				resp.setHeader("Content-Disposition", String.format("inline; filename=%s", fileName));
				return IOUtils.toByteArray(fis);
			}
		} catch (Exception e) {
			logger.error("downloadOrigineFile", e);
		}
		
		return null;
	}
	
	//To Query Manual Upload Data list
	@RequestMapping(value="/queryManualUploadList", 
			method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE
			)
	public QueryNameCheckUploadResp queryManualUploadList(@RequestBody QueryNameCheckUploadReq req){
		logger.debug("Create start time str :", req.getCreateDateStart());
		logger.debug("Create end time str :", req.getCreateDateEnd());
		// To Query Data
		
		QueryNameCheckUploadResp resp = new QueryNameCheckUploadResp();;
		try {
			// Add To Response Data List
			resp = amlUploadFileQueryService.getManualUploadFileList(req);
			resp.setStatus("infos");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resp.setStatus("errors");
			resp.setMessage("Query Fail. Exception:" + e.getMessage());
		}
		return resp;
	}
	
	/**
	 *  To Query BL/INV Upload Record By Condition 
	 *  UploadType=[1,2,3,4,7,8,9]
	 *  ScanStatus='Y/N'
	 *  Create User 
	 *  Create Time
	 */
	@RequestMapping(value="/queryUploadRecord", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public QueryUploadRecordResp queryUploadRecord(@RequestBody QueryUploadRecordReq req, HttpServletRequest hreq){
		logger.debug("uploadType str :", req.getUploadType());
		logger.debug("ScanStatus str :", req.getScanStatus());
		logger.debug("Create start time str :", req.getCreateDateStart());
		logger.debug("Create end time str :", req.getCreateDateEnd());
//		logger.debug("Create User :", req.getCreateUser());
		
		String locale = com.sas.util.StringUtils.getLocale(hreq.getLocale());
		QueryUploadRecordResp resp =new QueryUploadRecordResp();
		try {
			resp.setDataList(
					amlBlInvUploadDataQueryService.queryUploadRecordData(
							req.getUploadType(), req.getScanStatus(), req.getCreateUser(),
							req.getCreateDateStart(), req.getCreateDateEnd(), locale)
					);
			resp.setStatus("infos");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resp.setStatus("errors");
			resp.setMessage("Query Fail. Exception:" + e.getMessage());
		}
		
		return resp;
	}
	
	// To Query BL/INV Import/Export DataList
	@RequestMapping(value="/queryNameCheckImport/{uploadType}/{fileKey}", method=RequestMethod.GET)
	public QueryInvBlUploadFileResp queryBlimport(@PathVariable String uploadType, @PathVariable String fileKey) {
		logger.debug("uploadType str :", uploadType);
		logger.debug("uniqueKey str :", fileKey);
		
		QueryInvBlUploadFileResp resp = new QueryInvBlUploadFileResp(); 
		
		try {
			resp.setDataList(amlBlInvUploadDataQueryService.queryDataByUploadType(uploadType, fileKey));
			resp.setStatus("infos");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resp.setStatus("errors");
			resp.setMessage("Query Fail. Exception:" + e.getMessage());
		}
		
		return resp;
	}
	
	// To Query Name Check Record name main 
	@RequestMapping(value="/queryNameCheckRecordMain/{uniqueKey}", method=RequestMethod.GET)
	public QueryNameCheckRecordMainResp queryNameCheckRecordMain(@PathVariable String uniqueKey, HttpServletRequest req) {
		String locale = com.sas.util.StringUtils.getLocale(req.getLocale());
		logger.debug("Query Name Check Record Main  uniqueKey :", uniqueKey);
		logger.debug("Query Name Check Record Detail locale:", locale);
		QueryNameCheckRecordMainResp resp = new QueryNameCheckRecordMainResp();
		try {
			resp.setDataList(amlBlInvUploadDataQueryService.queryNameCheckRecordMain(uniqueKey,locale));
			resp.setStatus("infos");
		} catch (Exception e) {
			resp.setStatus("errors");
			resp.setMessage("Query Fail. Exception:" + e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resp;
	}
	
	// To Query Name Check Record name detail
	@RequestMapping(value="/queryNameCheckRecordDetail/{uniqueKey}", method=RequestMethod.GET)
	public QueryNameCheckRecordDetailResp queryNameCheckRecordDetail(@PathVariable String uniqueKey, HttpServletRequest req) {
		String locale = com.sas.util.StringUtils.getLocale(req.getLocale());
		logger.debug("Query Name Check Record Detail :", uniqueKey);
		logger.debug("Query Name Check Record Detail locale:", locale);
		QueryNameCheckRecordDetailResp resp = new QueryNameCheckRecordDetailResp();
		List<NameCheckRecordDetailBean> dataList =amlBlInvUploadDataQueryService.queryNameCheckRecordDetail(uniqueKey,locale);
		try {
			resp.setDataList(dataList);
			resp.setStatus("infos");
		} catch (Exception e) {
			resp.setStatus("errors");
			resp.setMessage("Query Fail. Exception:" + e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resp;
	}
	//Query product price currency data
	@RequestMapping(value="/currencyOptionQuery", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<FscCurrencyDim> currencyOptionQuery(HttpServletRequest req) {
		return currencyOptionQueryService.currencyOptionQueryService();
	}
	
	@RequestMapping(value="/healthChk/{function}", method=RequestMethod.GET)
	public String healthChk(@PathVariable String function, HttpServletRequest req) {
		String locale = com.sas.util.StringUtils.getLocale(req.getLocale());
		String resp = "0";
		if (StringUtils.equals("1", function ))
			return resp;
		else if (StringUtils.equals("2", function)){
			//check dataflux service and database is live?
		}
		return resp;
	}
}
