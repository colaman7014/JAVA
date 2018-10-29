package com.sas.amlCheck.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eai.wsdl.sendRecv.EAIServiceInputBean;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueueEndpointRespDto;
import com.sas.webservice.createCase.AmlCreateCase;
import com.sas.webservice.nameCheck.AmlNameCheck;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputBean;
import com.sas.webservice.swiftCheck.AmlSwiftAsyncCheck;
import com.sas.webservice.swiftCheck.AmlSwiftCheck;
import com.sas.webservice.swiftCheck.bean.SwiftCheckInputBean;
import com.sas.wsdl.swiftCheckReport.SwiftCheckReportInputBean;

@Controller
@RequestMapping("/rest")
public class AmlRetryController{
	private static final Logger logger = LoggerFactory.getLogger(AmlRetryController.class);
	
	@Autowired
	AmlCreateCase amlCreateCase;
	
	@Autowired
	AmlSwiftCheck amlSwiftCheck;
	
	@Autowired
	AmlNameCheck amlNameCheck;
	
	@Autowired
	AmlSwiftAsyncCheck amlSwiftAsyncCheck;
	
	@RequestMapping(value="/reportSwiftCheckStatus/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<XRetryEventQueueEndpointRespDto> reportSwiftCheckStatus(@RequestBody SwiftCheckReportInputBean swiftCheckReportInputBean, @PathVariable String id, HttpServletRequest req) {
		if(id != null) {
			boolean isSuccess = amlCreateCase.AcSwallowSwiftStatus(swiftCheckReportInputBean, Long.parseLong(id));
			if (!isSuccess) {
				 return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.NO_CONTENT, "fail", "ReportSwiftCheckStatus Fail.") , HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.OK, "success", "ReportSwiftCheckStatus Success.") , HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.BAD_REQUEST, "fail", "RetryNameCheck Success.") , HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@RequestMapping(value="/retrySwiftCheck/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<XRetryEventQueueEndpointRespDto> retrySwiftCheck(@RequestBody SwiftCheckInputBean swiftCheckInputBean, @PathVariable String id, HttpServletRequest req) {
		if(id != null) {
			Future<String> future;
			try {
				future = amlSwiftAsyncCheck.doAsyncSwiftCheck(swiftCheckInputBean, Long.parseLong(id));
				String result = future.get();
				if ("Fail".equalsIgnoreCase(result)) {
					 return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.NO_CONTENT, "fail", "RetrySwiftCheck Fail.") , HttpStatus.NO_CONTENT);
				}
			} catch (NumberFormatException e) {
				logger.error(e.getMessage(), e);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			} catch (ExecutionException e) {
				logger.error(e.getMessage(), e);
			}
			return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.OK, "success", "RetryNameCheck Success.") , HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.BAD_REQUEST, "fail", "RetryNameCheck Success.") , HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@RequestMapping(value="/retryNameCheck/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<XRetryEventQueueEndpointRespDto> retryNameCheck(@RequestBody NameCheckInputBean nameCheckInputBean, @PathVariable String id, HttpServletRequest req) {
		if(id != null) {
			NameCheckOutputBean nameCheckOutputBean = amlNameCheck.NonTransactionNameCheck(nameCheckInputBean, Long.parseLong(id));
			if (nameCheckOutputBean == null) {
				 return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.NO_CONTENT, "fail", "RetryNameCheck Fail.") , HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.OK, "success", "RetryNameCheck Success.") , HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.BAD_REQUEST, "fail", "RetryNameCheck Success.") , HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@RequestMapping(value="/callEaiServiceSendRecv/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<XRetryEventQueueEndpointRespDto> callEaiServiceSendRecv(@RequestBody EAIServiceInputBean eaiServiceInputBean, @PathVariable String id, HttpServletRequest req) {
		logger.info(String.format("retry call EaiService id %s", id));
		if(id != null) {
			boolean isSuccess = amlCreateCase.callEaiServiceSendRecv(eaiServiceInputBean, Long.parseLong(id));
			if (!isSuccess) {
				 return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.NO_CONTENT, "fail", "callEaiServiceSendRecv Fail.") , HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.OK, "success", "callEaiServiceSendRecv Success.") , HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new XRetryEventQueueEndpointRespDto(HttpStatus.BAD_REQUEST, "fail", "callEaiServiceSendRecv Fail.") , HttpStatus.BAD_REQUEST);
			
		}
	}
}