package com.sas.aml.retry.event.queue.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueue;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueueDto;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueueFileDto;
import com.sas.aml.retry.event.queue.repository.XRetryEventQueueRepository;
import com.sas.aml.retry.event.queue.util.XRetryPropertyUtils;
import com.sas.constraint.SwiftMtConst;
import com.sas.util.AmlConfiguration;
import com.sas.webservice.swiftCheck.bean.SwiftCheckInputBean;

@Service
public class XRetryEventQueueService {
	private static final Logger logger = LoggerFactory.getLogger(XRetryEventQueueService.class);
	private static final String logPath = XRetryPropertyUtils.getString("retry.save.bean.error.log.path");

	@Autowired
	XRetryEventQueueRepository xRetryEventQueueRepository;

	public XRetryEventQueue findById(long id) {
		XRetryEventQueue xRetryEventQueue = xRetryEventQueueRepository.findOne(id);
		return xRetryEventQueue;
	}

	public List<XRetryEventQueue> findAllByModuleAndCallbackType(String module, String callbackType) {
		List<XRetryEventQueue> xRetryEventQueues = xRetryEventQueueRepository.findByModuleAndCallbackFunc(module,
				callbackType);
		return xRetryEventQueues;
	}

	public void deleteAllXRetryEventQueueByModuleAndCallbackType(String module, String callbackType) {
		xRetryEventQueueRepository.deleteByModuleAndCallbackFunc(module, callbackType);
	}

	public void deleteById(long id) {
		xRetryEventQueueRepository.delete(id);
	}

	public boolean cancelAllXRetryEventQueueByModuleAndCallbackType(String module, String callbackType) {
		boolean isSuccess = false;
		List<XRetryEventQueue> xRetryEventQueues = xRetryEventQueueRepository.findByModuleAndCallbackFunc(module,
				callbackType);
		if (xRetryEventQueues != null) {
			for (XRetryEventQueue xRetryEventQueue : xRetryEventQueues) {
				xRetryEventQueue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_CANCELED);
				xRetryEventQueue.setUpdateDttm(new Timestamp(System.currentTimeMillis()));
			}
			xRetryEventQueueRepository.save(xRetryEventQueues);
			isSuccess = true;
		}
		return isSuccess;
	}

	public boolean cancelXRetryEventQueueByModuleAndEntityContent(String module, String entityContent) {
		boolean isSuccess = false;
		XRetryEventQueue xRetryEventQueue = xRetryEventQueueRepository.findByModuleAndEntityContent(module,
				entityContent);
		if (xRetryEventQueue != null) {
			xRetryEventQueue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_CANCELED);
			xRetryEventQueue.setUpdateDttm(new Timestamp(System.currentTimeMillis()));
			xRetryEventQueueRepository.save(xRetryEventQueue);
			isSuccess = true;
		}
		return isSuccess;
	}

	public boolean cancelXRetryEventQueueById(long id) {
		boolean isSuccess = false;
		XRetryEventQueue xRetryEventQueue = xRetryEventQueueRepository.findOne(id);
		if (xRetryEventQueue != null) {
			xRetryEventQueue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_CANCELED);
			xRetryEventQueue.setUpdateDttm(new Timestamp(System.currentTimeMillis()));
			xRetryEventQueueRepository.save(xRetryEventQueue);
			isSuccess = true;
		}
		return isSuccess;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public XRetryEventQueue sqlSaveEntityToRetryEventQueue(XRetryEventQueue xRetryEventQueue) {
		logger.info(" ～ sqlSaveEntityToRetryEventQueue ～ \n");
		xRetryEventQueue = xRetryEventQueueRepository.save(xRetryEventQueue);
		return xRetryEventQueue;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public XRetryEventQueue sqlFindEntityFromRetryEventQueue(Long id) {
		logger.info(" ～ sqlFindEntityFromRetryEventQueue ～ \n");
		XRetryEventQueue xRetryEventQueue = xRetryEventQueueRepository.findOne(id);
		return xRetryEventQueue;
	}
	
	public XRetryEventQueue saveEntityToXRetryEventQueue(String module, String entityName, Object object,
			HttpMethod httpMethod, String url, int retryTimes, String callbackFunc, long id, String status,
			boolean isMailNotice, String mailList) {
		logger.info("============ saveEntityToXRetryEventQueue =========== \n");
		String entityContent = "";
		XRetryEventQueue xRetryEventQueue = null;
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			if (id == 0) {
				xRetryEventQueue = new XRetryEventQueue();
				xRetryEventQueue.setRetryId(UUID.randomUUID().toString());
				xRetryEventQueue.setCreateDttm(now);
				xRetryEventQueue.setUpdateDttm(now);
				xRetryEventQueue.setModule(module);
				xRetryEventQueue.setHttpMethod(httpMethod.toString());
				xRetryEventQueue.setMaxRetryTimes(retryTimes);
				xRetryEventQueue.setEndPointUrl(url);
				xRetryEventQueue.setEntityName(entityName);
				xRetryEventQueue.setMailList(mailList);
				xRetryEventQueue.setMailNotice(isMailNotice);

				ObjectMapper objectMapper = new ObjectMapper();
				entityContent = objectMapper.writeValueAsString(object);
				xRetryEventQueue.setEntityContent((entityContent != null) ? entityContent : "");
				xRetryEventQueue.setCallbackFunc(callbackFunc);
				xRetryEventQueue.setStatus(status);
				
				logger.info("============ Log Retry Module : " + module + " ,EntityContent : " + entityContent + " \n");
				xRetryEventQueue = this.sqlSaveEntityToRetryEventQueue(xRetryEventQueue);
			} else {
				xRetryEventQueue = this.sqlFindEntityFromRetryEventQueue(id);
				if (xRetryEventQueue != null && module.equalsIgnoreCase(xRetryEventQueue.getModule())
						&& callbackFunc.equalsIgnoreCase(xRetryEventQueue.getCallbackFunc())) {
					xRetryEventQueue.setCreateDttm(now);
					xRetryEventQueue.setUpdateDttm(now);
					xRetryEventQueue.setModule(module);
					xRetryEventQueue.setHttpMethod(httpMethod.toString());
					xRetryEventQueue.setMaxRetryTimes(retryTimes);
					xRetryEventQueue.setEndPointUrl(url);
					xRetryEventQueue.setEntityName(entityName);
					xRetryEventQueue.setMailList(mailList);
					xRetryEventQueue.setMailNotice(isMailNotice);

					ObjectMapper objectMapper = new ObjectMapper();
					entityContent = objectMapper.writeValueAsString(object);
					xRetryEventQueue.setEntityContent((entityContent != null) ? entityContent : "");
					xRetryEventQueue.setCallbackFunc(callbackFunc);
					xRetryEventQueue.setStatus(status);

					logger.info("============ Log Retry Module : " + module + " ,EntityContent : " + entityContent + " \n");
					xRetryEventQueue = this.sqlSaveEntityToRetryEventQueue(xRetryEventQueue);
				}
			}
			if (xRetryEventQueue != null) {
				if(xRetryEventQueue.getId() != 0){
					logger.info("Retry id save success : " + xRetryEventQueue.getId());
				}
			} else {
				logger.info("Retry id save fail : " + id);
			}
		} catch (Exception ex) {
			logger.error("Save Retry Event Queue Error Cause ::: " + ex.getMessage() + ", EntityContent :::: "
					+ entityContent + "\n");
			
			String fileLogEnableFlag = "".equals(XRetryPropertyUtils.getString("retry.save.bean.error.log.enable"))
					? "true"
					: XRetryPropertyUtils.getString("retry.save.bean.error.log.enable");
			
			boolean isFileLogEnable = "true".equalsIgnoreCase(fileLogEnableFlag) ? true : false;
			if (isFileLogEnable) {
				try {
					ObjectMapper objectMapper = new ObjectMapper();
					XRetryEventQueueFileDto xRetryEventQueueFileDto = new XRetryEventQueueFileDto(xRetryEventQueue);
					Date today = Calendar.getInstance().getTime();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
					StringBuffer sBuffer = new StringBuffer("Retry_").append(formatter.format(today))
							.append("_" + module != null ? module + "_" : "").append(callbackFunc).append(".json");

					StringBuffer bodyBuffer = new StringBuffer(
							objectMapper.writeValueAsString(xRetryEventQueueFileDto));
					logger.info(bodyBuffer == null ? "XRetryEventQueueFileDto Error Content!" : bodyBuffer.toString());

					// write object to file
					File file = new File(logPath);
					if (!file.exists())
						file.mkdirs();
					File resFile = new File(logPath, sBuffer.toString());
					Writer fWriter  = new OutputStreamWriter(new FileOutputStream(resFile), StandardCharsets.UTF_8) ;
					BufferedWriter bw = new BufferedWriter(fWriter);
					bw.write(bodyBuffer.toString());
					bw.flush();
					fWriter.close();
					bw.close();
				} catch (IOException e) {
					logger.error(String.format("Save Retry Log File IO Error Module : %s, Callback : %s, Entity : %s",
							module, callbackFunc, entityContent), e);
				} catch (Exception e) {
					logger.error(String.format("Save Retry Log File Other Error Module : %s, Callback : %s, Entity : %s",
							module, callbackFunc, entityContent), e);
				}
			}
		}

	

		return xRetryEventQueue;
	}

	public static File[] getErrorEventFiles() {
		File folder = new File(logPath);
		if (!folder.exists())
			folder.mkdirs();
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}

	public static boolean renameProccessedErrorEventFiles(File sourceFile, File targetFile) {
		boolean isSuccess = false;
		if (sourceFile.renameTo(targetFile)) {
			logger.info("File : " + sourceFile + " is Renamed successfully");
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean deleteProccessedDirFiles() {
		boolean isSuccess = false;
		File[] listOfFiles = getErrorEventFiles();
		for (File file : listOfFiles) {
			if (file.getName().startsWith("Save_")) {
				if (file.delete()) {
					logger.info("File deleted successfully");
					isSuccess = true;
				} else {
					logger.error("Failed to delete the file");
					return isSuccess;
				}
			}
		}
		return isSuccess;
	}

	public XRetryEventQueue isContetntExist(XRetryEventQueueDto xRetryEventQueueDto) {
		XRetryEventQueue xRetryEventQueue = null;
		if (xRetryEventQueueDto != null && xRetryEventQueueDto.getEntityContent() != null) {
			xRetryEventQueue = xRetryEventQueueRepository.findByEntityContent(xRetryEventQueueDto.getEntityContent());
		}
		return xRetryEventQueue;
	}

	public XRetryEventQueue saveRecord(XRetryEventQueueDto xRetryEventQueueDto) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		XRetryEventQueue xRetryEventQueue = new XRetryEventQueue();
		xRetryEventQueue.setCreateDttm(now);
		xRetryEventQueue.setRetryId(UUID.randomUUID().toString());
		xRetryEventQueue.setCallbackFunc(xRetryEventQueueDto.getCallbackFunc());
		xRetryEventQueue.setEndPointUrl(xRetryEventQueueDto.getEndPointUrl());
		xRetryEventQueue.setEntityContent(xRetryEventQueueDto.getEntityContent());
		xRetryEventQueue.setEntityName(xRetryEventQueueDto.getEntityName());
		xRetryEventQueue.setHttpMethod(xRetryEventQueueDto.getHttpMethod());
		xRetryEventQueue.setMailList(xRetryEventQueueDto.getMailList());
		xRetryEventQueue.setMailNotice(xRetryEventQueueDto.isMailNotice());
		xRetryEventQueue.setMaxRetryTimes(xRetryEventQueueDto.getMaxRetryTimes());
		xRetryEventQueue.setModule(xRetryEventQueueDto.getModule());
		xRetryEventQueue.setRetryTimes(0);
		if (xRetryEventQueueDto.getStatus() == null || "".equals(xRetryEventQueueDto.getStatus())
				|| (!QueueConstraint.QUEUE_EVENT_STATUS_WAITING.equals(xRetryEventQueueDto.getStatus())
						&& !QueueConstraint.QUEUE_EVENT_STATUS_PENDING.equals(xRetryEventQueueDto.getStatus())
						&& !QueueConstraint.QUEUE_EVENT_STATUS_COMPLETED.equals(xRetryEventQueueDto.getStatus())
						&& !QueueConstraint.QUEUE_EVENT_STATUS_CANCELED.equals(xRetryEventQueueDto.getStatus())
						&& !QueueConstraint.QUEUE_EVENT_STATUS_NON_NEED_RETRY
								.equals(xRetryEventQueueDto.getStatus()))) {
			xRetryEventQueue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_WAITING);

		} else {
			xRetryEventQueue.setStatus(xRetryEventQueueDto.getStatus());
		}
		xRetryEventQueue = xRetryEventQueueRepository.save(xRetryEventQueue);
		return xRetryEventQueue;
	}

	public XRetryEventQueue updateRecord(XRetryEventQueue currentXRetryEventQueue,
			XRetryEventQueueDto xRetryEventQueueDto) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		currentXRetryEventQueue.setUpdateDttm(now);
		currentXRetryEventQueue.setRetryId(UUID.randomUUID().toString());
		if (xRetryEventQueueDto.getCallbackFunc() != null && !"".equals(xRetryEventQueueDto.getCallbackFunc())) {
			currentXRetryEventQueue.setCallbackFunc(xRetryEventQueueDto.getCallbackFunc());
		}
		if (xRetryEventQueueDto.getEndPointUrl() != null && !"".equals(xRetryEventQueueDto.getEndPointUrl())) {
			currentXRetryEventQueue.setEndPointUrl(xRetryEventQueueDto.getEndPointUrl());
		}
		if (xRetryEventQueueDto.getEntityContent() != null && !"".equals(xRetryEventQueueDto.getEntityContent())) {
			currentXRetryEventQueue.setEntityContent(xRetryEventQueueDto.getEntityContent());
		}
		if (xRetryEventQueueDto.getEntityName() != null && !"".equals(xRetryEventQueueDto.getEntityName())) {
			currentXRetryEventQueue.setEntityName(xRetryEventQueueDto.getEntityName());
		}
		if (xRetryEventQueueDto.getHttpMethod() != null && !"".equals(xRetryEventQueueDto.getHttpMethod())) {
			currentXRetryEventQueue.setHttpMethod(xRetryEventQueueDto.getHttpMethod());
		}
		if (xRetryEventQueueDto.getMailList() != null && !"".equals(xRetryEventQueueDto.getMailList())) {
			currentXRetryEventQueue.setMailList(xRetryEventQueueDto.getMailList());
		}
		if (xRetryEventQueueDto.getMaxRetryTimes() != 0) {
			currentXRetryEventQueue.setMaxRetryTimes(xRetryEventQueueDto.getMaxRetryTimes());
			currentXRetryEventQueue.setRetryTimes(0);
		}
		if (xRetryEventQueueDto.getModule() != null && !"".equals(xRetryEventQueueDto.getModule())) {
			currentXRetryEventQueue.setModule(xRetryEventQueueDto.getModule());
		}

		if (xRetryEventQueueDto.getStatus() == null || "".equals(xRetryEventQueueDto.getStatus())
				|| (!QueueConstraint.QUEUE_EVENT_STATUS_WAITING.equals(xRetryEventQueueDto.getStatus())
						&& !QueueConstraint.QUEUE_EVENT_STATUS_PENDING.equals(xRetryEventQueueDto.getStatus())
						&& !QueueConstraint.QUEUE_EVENT_STATUS_COMPLETED.equals(xRetryEventQueueDto.getStatus())
						&& !QueueConstraint.QUEUE_EVENT_STATUS_CANCELED.equals(xRetryEventQueueDto.getStatus())
						&& !QueueConstraint.QUEUE_EVENT_STATUS_NON_NEED_RETRY
								.equals(xRetryEventQueueDto.getStatus()))) {
			currentXRetryEventQueue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_WAITING);

		} else {
			currentXRetryEventQueue.setStatus(xRetryEventQueueDto.getStatus());
		}
		currentXRetryEventQueue.setMailNotice(xRetryEventQueueDto.isMailNotice());
		currentXRetryEventQueue = xRetryEventQueueRepository.save(currentXRetryEventQueue);
		return currentXRetryEventQueue;
	}

	public static void main(String[] args) {
		try {
			SwiftCheckInputBean swiftCheckInputBean = new SwiftCheckInputBean();
			swiftCheckInputBean.setBranchNumber("030");
			swiftCheckInputBean.setBusinessUnitId("01");
			// swiftCheckInputBean.setBusinessType("");
			swiftCheckInputBean.setCallingSystem("SWALLOW");
			swiftCheckInputBean.setCallingUser("sasdemo");
			swiftCheckInputBean.setInterfaceName("Aml_SWIFTCheck");
			swiftCheckInputBean.setPartyNumber("A123456789");
			// swiftCheckInputBean.setReferenceNumber("");
			swiftCheckInputBean.setScreenProcess("5");
			swiftCheckInputBean.setSwiftRje("");
			swiftCheckInputBean.setTransactionDate("2018-04-15 08:00:00");
			swiftCheckInputBean.setUniqueKey("test-swiftcheck-0001");

			Timestamp now = new Timestamp(System.currentTimeMillis());
			XRetryEventQueue xRetryEventQueue = new XRetryEventQueue();

			ObjectMapper objectMapper = new ObjectMapper();
			String entityContent = objectMapper.writeValueAsString(swiftCheckInputBean);
			xRetryEventQueue.setEntityContent((entityContent != null) ? entityContent : "");
			xRetryEventQueue.setCallbackFunc("");
			xRetryEventQueue.setStatus(QueueConstraint.QUEUE_EVENT_STATUS_WAITING);
			String module = SwiftMtConst.SOURCE_TYPE_RT_NC;
			String callbackFunc = "retrySwiftCheck";
			int retryTimes = 3;
			String url = AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) + "/AmlCheck/rest/retrySwiftCheck";
			xRetryEventQueue.setCallbackFunc("");
			xRetryEventQueue.setCreateDttm(now);
			xRetryEventQueue.setUpdateDttm(now);
			xRetryEventQueue.setModule("RT-SC");
			xRetryEventQueue.setHttpMethod(HttpMethod.POST.toString());
			xRetryEventQueue.setMaxRetryTimes(3);
			xRetryEventQueue.setEndPointUrl(url);
			xRetryEventQueue.setEntityName("SwiftCheckInputBean");
			xRetryEventQueue.setMailList("");
			xRetryEventQueue.setMailNotice(false);
			String fileLogEnableFlag = "".equals(XRetryPropertyUtils.getString("retry.save.bean.error.log.enable"))
					? "true"
					: XRetryPropertyUtils.getString("retry.save.bean.error.log.enable");
			boolean isFileLogEnable = "true".equalsIgnoreCase(fileLogEnableFlag) ? true : false;
			if (isFileLogEnable) {
				try {
					XRetryEventQueueFileDto xRetryEventQueueFileDto = new XRetryEventQueueFileDto(xRetryEventQueue);
					Date today = Calendar.getInstance().getTime();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
					StringBuffer sBuffer = new StringBuffer("Retry_").append(formatter.format(today))
							.append("_" + module != null ? module + "_" : "").append(callbackFunc).append(".json");

					StringBuffer bodyBuffer = new StringBuffer(
							objectMapper.writeValueAsString(xRetryEventQueueFileDto));
					logger.info(bodyBuffer == null ? "XRetryEventQueueFileDto Error Content!" : bodyBuffer.toString());

					// write object to file
					File file = new File(logPath);
					if (!file.exists())
						file.mkdirs();
					File resFile = new File(logPath, sBuffer.toString());
					FileWriter fWriter = new FileWriter(resFile);
					BufferedWriter bw = new BufferedWriter(fWriter);
					bw.write(bodyBuffer.toString());
					bw.flush();
					fWriter.close();
					bw.close();
				} catch (IOException e) {
					logger.info("Save Retry Log File Error Module : {}, Callback : {}, Entity : {}", module,
							callbackFunc, entityContent);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
