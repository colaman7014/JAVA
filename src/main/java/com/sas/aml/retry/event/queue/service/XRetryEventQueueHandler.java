package com.sas.aml.retry.event.queue.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueue;
import com.sas.aml.retry.event.queue.repository.XRetryEventQueueRepository;
import com.sas.aml.retry.event.queue.util.XRetryMailUtils;

@Service
public class XRetryEventQueueHandler {
	private static final Logger logger = LoggerFactory.getLogger(XRetryEventQueueHandler.class);

	@Autowired
	XRetryEventQueueRepository xRetryEventQueueRepository;

	public void process(XRetryEventQueue queue) {
		String result = doCallBack(queue);
		queue.setRetryTimes(queue.getRetryTimes() + 1);
		logger.info("XRetryEventQueue ::: " + queue.toString());
		xRetryEventQueueRepository.save(queue);
	}

	private String doCallBack(XRetryEventQueue queue) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			queue.setUpdateDttm(timestamp);
			String checkFiledResult = checkQueueItems(queue);
			if (QueueConstraint.QUEUE_EVENT_ERRORCODE_FORMAT_NO_ERROR != checkFiledResult) {
				queue.setLastExecResult(checkFiledResult);
				return checkFiledResult;
			}
	
			if (!(queue.getHttpMethod() == null || queue.getHttpMethod().isEmpty() || "".equals(queue.getHttpMethod()))) {
				String httpMethodString = queue.getHttpMethod();
				HttpMethod httpMethod = HttpMethod.valueOf(httpMethodString);
				boolean doRetryResult = false;
				switch (httpMethod) {
					case GET: {
						doRetryResult = doGetCall(queue);
						break;
					}
					case POST: {
						doRetryResult = doPostCall(queue);
						break;
					}
					default: {
						queue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_PENDING);
						queue.setLastExecResult(QueueConstraint.QUEUE_EVENT_ERRORCODE_HTTPMETHOD_FORMAT_ERROR);
						return QueueConstraint.QUEUE_EVENT_ERRORCODE_HTTPMETHOD_FORMAT_ERROR;
					}
				}
	
				if (doRetryResult != true) {
					queue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_WAITING);
				} else {
					queue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_COMPLETED);
					queue.setLastExecResult(QueueConstraint.QUEUE_EVENT_RESP_SUCCESS);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return QueueConstraint.QUEUE_EVENT_RESP_SUCCESS;

	}

	private String checkQueueItems(XRetryEventQueue queue) {
		if (queue.getEndPointUrl() == null || queue.getEndPointUrl().isEmpty() || "".equals(queue.getEndPointUrl())) {
			queue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_PENDING);
			return QueueConstraint.QUEUE_EVENT_ERRORCODE_ENDPOINTURL_FORMAT_ERROR;
		}

		if (queue.getCallbackFunc() == null || queue.getCallbackFunc().isEmpty()
				|| "".equals(queue.getCallbackFunc())) {
			queue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_PENDING);
			return QueueConstraint.QUEUE_EVENT_ERRORCODE_NO_CALLBACKFUNC_ERROR;
		}

		if ((queue.getEntityContent() == null || queue.getEntityContent().isEmpty()
				|| "".equals(queue.getEntityContent()))
				&& (queue.getEntityName() == null || queue.getEntityName().isEmpty()
						|| "".equals(queue.getEntityName()))) {
			queue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_PENDING);
			return QueueConstraint.QUEUE_EVENT_ERRORCODE_ENTITY_FORMAT_ERROR;
		}

		if ((queue.getHttpMethod() == null || queue.getHttpMethod().isEmpty() || "".equals(queue.getHttpMethod()))) {
			queue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_PENDING);
			return QueueConstraint.QUEUE_EVENT_ERRORCODE_HTTPMETHOD_FORMAT_ERROR;
		}

		if (queue.getMaxRetryTimes() != 0 && queue.getMaxRetryTimes() <= queue.getRetryTimes()) {
			queue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_PENDING);
			return QueueConstraint.QUEUE_EVENT_ERRORCODE_OVER_RETRY_LIMIT;
		}

		return QueueConstraint.QUEUE_EVENT_ERRORCODE_FORMAT_NO_ERROR;
	}

	private boolean doPostCall(XRetryEventQueue queue) {
		boolean isOk = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			// Add the Jackson message converter
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			// create request body
			String input = queue.getEntityContent();

			// set headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(input, headers);

			// send request and parse result
			ResponseEntity<String> response = restTemplate.exchange(queue.getEndPointUrl() + "/" + queue.getId(),
					HttpMethod.POST, entity, String.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				isOk = true;
				logger.info("DoPostCall response :::: " + response.getBody());
				ObjectMapper responseMapper = new ObjectMapper();
				JsonNode actualObj;
				if (response.getBody() != null) {
					actualObj = responseMapper.readTree(response.getBody());
					if (actualObj.get("code") != null) {
						String success = actualObj.get("code").toString();
						if (QueueConstraint.QUEUE_EVENT_EXEC_SUCCESS.equalsIgnoreCase(success)) {
							isOk = true;
						}
					}
				}
			} else {
				queue.setLastExecResult(QueueConstraint.QUEUE_EVENT_ERRORCODE_EXEC_FAIL);
			}
		} catch (JsonProcessingException e) {
			queue.setLastExecResult(QueueConstraint.QUEUE_EVENT_ERRORCODE_ENTITY_FORMAT_ERROR);
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			queue.setLastExecResult(QueueConstraint.QUEUE_EVENT_ERRORCODE_INTERNAL_SERVER_ERROR);
			logger.error(e.getMessage(), e);
		}
		return isOk;
	}

	@SuppressWarnings("rawtypes")
	private boolean doGetCall(XRetryEventQueue queue) {
		boolean isOk = false;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			UriComponentsBuilder builder = UriComponentsBuilder
					.fromHttpUrl(queue.getEndPointUrl() + "/" + queue.getId());
			if (queue.getEntityContent() == null || queue.getEntityContent().isEmpty()
					|| "".equals(queue.getEntityContent())) {
				logger.info("Entity Content ::: " + queue.getEntityContent());
				String jsonStr = queue.getEntityContent();
				/**
				 * Read JSON from a file into a Map
				 */
				Map<String, String> resultMap = new HashMap<String, String>();
				ObjectMapper mapperObj = new ObjectMapper();
				System.out.println("Input Json: " + jsonStr);

				resultMap = mapperObj.readValue(jsonStr, new TypeReference<HashMap<String, String>>() {
				});
				Iterator<Entry<String, String>> iter = resultMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String key = (String) entry.getKey();
					Object val = entry.getValue();
					builder.queryParam(key, val);
				}
			}

			logger.info("No Entity Content!");
			HttpEntity<?> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
					entity, String.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				isOk = true;
				ObjectMapper responseMapper = new ObjectMapper();
				JsonNode actualObj;
				if (response.getBody() != null) {
					actualObj = responseMapper.readTree(response.getBody());
					if (actualObj.get("code") != null) {
						String success = actualObj.get("code").toString();
						if (QueueConstraint.QUEUE_EVENT_EXEC_SUCCESS.equalsIgnoreCase(success)) {
							isOk = true;
						}
					}
				}
			} else {
				queue.setLastExecResult(QueueConstraint.QUEUE_EVENT_ERRORCODE_EXEC_FAIL);
			}
		} catch (JsonProcessingException e) {
			queue.setLastExecResult(QueueConstraint.QUEUE_EVENT_ERRORCODE_ENTITY_FORMAT_ERROR);
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			queue.setLastExecResult(QueueConstraint.QUEUE_EVENT_ERRORCODE_INTERNAL_SERVER_ERROR);
			logger.error(e.getMessage(), e);
		}
		return isOk;
	}

	public static String getParamsString(Map<String, String> params) {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			try {
				result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
				result.append("=");
				result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				result.append("&");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			}

		}

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}

	public boolean mailNoticeProccess() {
		boolean isSuccess = false, mailBodyIsHtml = false;
		String mailSubject = null, mailBody = "";
		File[] attachments = null;
		List<XRetryEventQueue> xRetryEventQueueList = xRetryEventQueueRepository.findByMailNoticeTrueAndMailListNotNullAndStatus(QueueConstraint.QUEUE_EVENT_STATUS_PENDING);
		for (XRetryEventQueue xRetryEventQueue : xRetryEventQueueList) {
			String toAddressString = xRetryEventQueue.getMailList() != null ? xRetryEventQueue.getMailList() : "";
			String[] toAddress = toAddressString.split(",");
			if(toAddress.length > 0) {
				mailSubject="";
				mailBody="";
				isSuccess = XRetryMailUtils.sendMails(toAddress, mailSubject, mailBody, mailBodyIsHtml, attachments);
				if (isSuccess) {
					xRetryEventQueue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_MAILED);
					xRetryEventQueueRepository.save(xRetryEventQueue);
				}
			}
		}
		return isSuccess;
	}

}
