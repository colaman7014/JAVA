package com.sas.amlCheck.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sas.aml.uploader.bean.FinanceUploaderReq;
import com.sas.aml.uploader.bean.MultipleUploaderResp;
import com.sas.aml.uploader.bean.NameCheckUploadReq;
import com.sas.aml.uploader.bean.UploaderReq;
import com.sas.aml.uploader.bean.UploaderResp;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.orm.XInvBlNcUploadRecord;
import com.sas.multipleNC.bean.NCOnlineErrMsgBean;
import com.sas.multipleNC.service.AmlNCOnlineCheckService;
import com.sas.tradeFinance.bean.XBlInvErrMsgBean;
import com.sas.tradeFinance.service.AmlTradeFinanceCheckService;
import com.sas.util.AmlConfiguration;
import com.sas.util.Base64Util;

@RestController
@RequestMapping(value = "/uploader")
public class FileController {
	private static final String DEAFAULT_UPLOAD_FAILED_MSG = "{\"status\":\"error\",\"message\":\"Upload Failed\"}";

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	AmlTradeFinanceCheckService amlTradeFinanceCheckService;

	@Autowired
	AmlNCOnlineCheckService amlNCOnlineCheckService;

	@RequestMapping(value = "/financeManual")
	public String checkFinance(@ModelAttribute FinanceUploaderReq financeUploader, HttpServletResponse response) {
		UploaderResp resp = new UploaderResp();
		try {
			String uploadType = financeUploader.getType();
			String userId = financeUploader.getUserId();
			MultipartFile multipartFile = financeUploader.getUploadedfile();
			XBlInvErrMsgBean result = amlTradeFinanceCheckService.saveNCheckFile(uploadType, userId, multipartFile,
					null, null, null);

			resp.setCount(result.getEffCount());
			resp.setSuccessCount(result.getSuccessCount());
			resp.setErrorCount(result.getErrorCount());
			if (!result.isValidateError()) {
				resp.setStatus("infos");
				resp.setMessage("Upload Success");
			} else {
				resp.setStatus("errors");
				resp.setMessage("Upload Failed");
				resp.setErrList(result.getErrorMsgStringList());
			}
		} catch (Exception e) {
			logger.error("checkFinance", e);
			resp.setStatus("errors");
			resp.setMessage("Upload Failed");
		}
		
		ObjectMapper om = new ObjectMapper();
		String result = null;
		try {
			result = om.writeValueAsString(resp);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			result = DEAFAULT_UPLOAD_FAILED_MSG;
		}
		if (StringUtils.isNotBlank(result)){
			response.setStatus(HttpServletResponse.SC_OK);
			try {
				response.getWriter().write(result);
				response.getWriter().flush();
			} catch (IOException e) {
			}
		}
		
		
		return null;
	}

	@PostMapping(value = "/multiManual" )
	public String amlOnlineMultiNameCheck(@ModelAttribute UploaderReq fileUploader, HttpServletResponse response) {
		MultipleUploaderResp resp = new MultipleUploaderResp();
		try {
			String uploadType = fileUploader.getType();
			String userId = fileUploader.getUserId();

			MultipartFile multipartFile = fileUploader.getUploadedfile();
			NCOnlineErrMsgBean result = amlNCOnlineCheckService.saveNCheckFile(uploadType, userId, multipartFile, null,
					null, null);
			resp.setFileKey(result.getFileKey());
			resp.setCount(result.getEffCount());
			resp.setSuccessCount(result.getSuccessCount());
			resp.setErrorCount(result.getErrorCount());
			if (!result.isValidateError()) {
				resp.setStatus("infos");
				resp.setMessage("Upload Success");
			} else {
				resp.setStatus("errors");
				resp.setMessage("Upload Failed");
				resp.setErrList(result.getErrorMsgStringList());
			}
		} catch (Exception e) {
			logger.error("uploader NameCheck file error", e);
			resp.setStatus("errors");
			resp.setMessage("Upload Failed");
		}
		
		ObjectMapper om = new ObjectMapper();
		String result = null;
		try {
			result = om.writeValueAsString(resp);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			result = DEAFAULT_UPLOAD_FAILED_MSG;
		}
		if (StringUtils.isNotBlank(result)){
			response.setStatus(HttpServletResponse.SC_OK);
			try {
				response.getWriter().write(result);
				response.getWriter().flush();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		return null;
	}

	@RequestMapping(value = "/multiManual/download/{fileKey}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[]  download(final HttpServletRequest request,
			@PathVariable("fileKey") String fileKey, HttpServletResponse resp) throws Exception {
		XInvBlNcUploadRecord xInvBlNcUploadRecord = amlNCOnlineCheckService.getNCCheckFile(fileKey);
		String path = "";
		String templateFileName = "";
		String realFileName = "";
		try {
			if (xInvBlNcUploadRecord.getResultFile()!= null && xInvBlNcUploadRecord.getUploadType() != null) {
				path = AmlConfiguration.getString(String.format("com.sas.upload.filePath.%s", xInvBlNcUploadRecord.getUploadType()));
				switch (xInvBlNcUploadRecord.getUploadType()) {
					case SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT:
						realFileName = String.format("%s%s.xlsx", SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT_FILE_RESULT_PREFIX, fileKey);
						break;
					case SwiftMtConst.UPLOAD_TYPE_FTP_REMITTANCE_IMPORT:
						realFileName = String.format("%s%s.d", SwiftMtConst.UPLOAD_TYPE_FTP_REMITTANCE_IMPORT_FILE_RESULT_PREFIX, fileKey);
						break;
					case SwiftMtConst.UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT:
						realFileName = String.format("%s%s.d", SwiftMtConst.UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT_FILE_RESULT_PREFIX, fileKey);
						break;
					default:
						realFileName = String.format("%s%s.xlsx", SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT_FILE_RESULT_PREFIX, fileKey);
						break;
				}
				templateFileName = xInvBlNcUploadRecord.getResultFile() != null ? xInvBlNcUploadRecord.getResultFile() : realFileName;
			} 
			File file = new File(String.format("%s%s", path, realFileName));
			String enResultFile = xInvBlNcUploadRecord.getEnResultFile();
			if (!file.exists())
				FileUtils.copyInputStreamToFile(Base64Util.decodeFileByBase64(enResultFile), file);
			logger.debug("file.getAbsolutePath() : " + file.getAbsolutePath());
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				resp.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				resp.setHeader("Content-Length", String.valueOf(file.length()));
				resp.setHeader("Content-Disposition", String.format("inline; filename=%s", templateFileName));
				return IOUtils.toByteArray(fis);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@RequestMapping(value = "/multiManual/download/origin/{fileKey}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[] downloadOrigin(final HttpServletRequest request,
			@PathVariable("fileKey") String fileKey, HttpServletResponse resp) throws Exception {
		try {
			XInvBlNcUploadRecord xInvBlNcUploadRecord = amlNCOnlineCheckService.getNCCheckFile(fileKey);
			final String path = AmlConfiguration.getString(String.format("com.sas.upload.filePath.%s", xInvBlNcUploadRecord.getUploadType()));
			String realFileName = "";
			switch (xInvBlNcUploadRecord.getUploadType()) {
				case SwiftMtConst.UPLOAD_TYPE_FTP_STANDARD_IMPORT:
				case SwiftMtConst.UPLOAD_TYPE_FTP_REMITTANCE_IMPORT:
				case SwiftMtConst.UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT:
					realFileName = String.format("%s%s", path, fileKey);
					break;
				default:
					realFileName = String.format("%s%s", path, xInvBlNcUploadRecord.getFileName());
					break;
			}
			String templateFileName = "";
			File file = new File(realFileName);
			if (xInvBlNcUploadRecord.getFileName()!= null) {
				templateFileName = xInvBlNcUploadRecord.getFileName() != null ? xInvBlNcUploadRecord.getFileName() : "";
			}
			
			String enOrgFile = xInvBlNcUploadRecord.getEnOrgFile();
			
			if (!file.exists())
				FileUtils.copyInputStreamToFile(Base64Util.decodeFileByBase64(enOrgFile), file);
			
			logger.debug("file.getAbsolutePath() : " + file.getAbsolutePath());
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				resp.setHeader("Content-Type", "application/octet-stream");
				resp.setHeader("Content-Length", String.valueOf(file.length()));
				resp.setHeader("Content-Disposition", String.format("inline; filename=%s", templateFileName));
				return IOUtils.toByteArray(fis);
			}
		} catch (Exception e) {
			logger.error("downloadTemplate", e);
		}
		return null;
	}

	@RequestMapping(value = "/nameChekManual")
	@ResponseBody
	public UploaderResp nameChekManual(@ModelAttribute NameCheckUploadReq nameCheckUploader) {
		UploaderResp resp = new UploaderResp();
		try {
			MultipartFile multipartFile = nameCheckUploader.getUploadedfile();
			String uploadType = nameCheckUploader.getType();
			String scanType = nameCheckUploader.getFileType();
			String startDate = nameCheckUploader.getStartDate();
			String endDate = nameCheckUploader.getEndDate();
			String userId = nameCheckUploader.getUserId();

			if (!SwiftMtConst.SCAN_TYPE_PARTY.equals(scanType) && (startDate == null || endDate == null)) {
				resp.setStatus("errors");
				resp.setMessage("Start Date or End Date can't empty.");
			} else {
				XBlInvErrMsgBean result = amlTradeFinanceCheckService.saveNCheckFile(uploadType, userId, multipartFile,
						scanType, startDate, endDate);
				resp.setStatus("infos");
				resp.setMessage("Upload Success");
				resp.setCount(result.getEffCount());
			}
		} catch (Exception e) {
			logger.error("nameChekManual", e);
			resp.setStatus("errors");
			resp.setMessage("Upload Failed");
		}
		return resp;
	}

	@RequestMapping(value = "/downloadTemplate/{itemType}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[] downloadTemplate(@PathVariable String itemType, HttpServletResponse resp) {
		try {
			String filePath = AmlConfiguration.getString("com.sas.template.download.filePath");// "D:/template/".concat(itemType).concat(".xlsx");
			String templateFileName = AmlConfiguration.getString(String.format("com.sas.template.download.file.%s", itemType));
			
			File file = new File(String.format("%s%s", filePath, templateFileName));
			logger.debug("file.getAbsolutePath() : " + file.getAbsolutePath());
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				resp.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				resp.setHeader("Content-Length", String.valueOf(file.length()));
				resp.setHeader("Content-Disposition", String.format("inline; filename=%s", templateFileName));
				return IOUtils.toByteArray(fis);
			}
		} catch (Exception e) {
			logger.error("downloadTemplate", e);
		}

		return null;
	}
}
