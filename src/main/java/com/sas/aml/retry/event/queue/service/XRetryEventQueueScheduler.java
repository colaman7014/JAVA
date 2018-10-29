package com.sas.aml.retry.event.queue.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueue;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueueFileDto;
import com.sas.aml.retry.event.queue.repository.XRetryEventQueueRepository;

@Component
@PropertySource("classpath:retryQueue.properties")
public class XRetryEventQueueScheduler {
	private static final Logger logger = LoggerFactory.getLogger(XRetryEventQueueScheduler.class);

	@Value("${retry.schedule.enable}")
	private boolean isJobEnable;

	@Value("${retry.schedule.reproduce.record.enable}")
	private boolean isReproduceRecordEnable;

	@Value("${retry.schedule.delete.error.record.enable}")
	private boolean isDeleteRecordEnable;
	
	@Value("${retry.schedule.mail.notice.enable}")
	private boolean isMailNoticeEnable;
	
	@Value("${retry.save.bean.error.log.path}")
	private String logPath;

	@Autowired
	private XRetryEventQueueHandler xRetryEventQueueHandler;

	@Autowired
	private XRetryEventQueueRepository xRetryQueueRepository;
	
	

	@Scheduled(fixedDelayString = "${retry.schedule.trigger.rate}") // 每隔5分鐘，從上一個任務完成開始到下一個任務開始的間隔，單位毫秒
	public void doRetrySchedule() throws InterruptedException {
		if (isJobEnable) {
			logger.info("================= doRetrySchedule Begin =================");
			List<XRetryEventQueue> xRetryEventQueues = xRetryQueueRepository
					.findByStatus(QueueConstraint.QUEUE_EVENT_STATUS_WAITING);
			if (xRetryEventQueues != null && !xRetryEventQueues.isEmpty()) {
				logger.info(xRetryEventQueues.toString());
				for (XRetryEventQueue xRetryEventQueue : xRetryEventQueues) {
					xRetryEventQueueHandler.process(xRetryEventQueue);
				}
			}
			logger.info("================= doRetrySchedule End =================");
		} else {
			logger.info("================= doRetrySchedule Disable =================");
		}
	}

	@Scheduled(fixedDelayString = "${retry.schedule.reproduce.record.trigger.rate}") // 每隔5分鐘，從上一個任務完成開始到下一個任務開始的間隔，單位毫秒
	public void insertErrorEventQueue() throws InterruptedException {
		if (isReproduceRecordEnable) {
			logger.info("=================   insertErrorEventQueue Begin   =================");
			File[] listOfFiles = XRetryEventQueueService.getErrorEventFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile() && listOfFiles[i].getName().startsWith("Retry_")) {
					File curFile =  listOfFiles[i];
					String curName = curFile.getName();
					logger.info("Process File : " + curName);
					ObjectMapper mapper = new ObjectMapper();
					try {
						// Convert JSON string from file to Object
						XRetryEventQueueFileDto dto = mapper.readValue(curFile, XRetryEventQueueFileDto.class);
						Timestamp now = new Timestamp(System.currentTimeMillis());
						XRetryEventQueue xRetryEventQueue = new XRetryEventQueue();
						xRetryEventQueue.setCallbackFunc(dto.getCallbackFunc());
						xRetryEventQueue.setCreateDttm(dto.getCreateDttm());
						xRetryEventQueue.setEndPointUrl(dto.getEndPointUrl());
						xRetryEventQueue.setEntityContent(dto.getEntityContent());
						xRetryEventQueue.setEntityName(dto.getEntityName());
						xRetryEventQueue.setHttpMethod(dto.getHttpMethod());
						xRetryEventQueue.setMailList(dto.getMailList());
						xRetryEventQueue.setMailNotice(dto.isMailNotice());
						xRetryEventQueue.setMaxRetryTimes(dto.getMaxRetryTimes());
						xRetryEventQueue.setModule(dto.getModule());
						xRetryEventQueue.setRetryId(dto.getRetryId());
						xRetryEventQueue.setStatus(dto.getStatus());
						xRetryEventQueue.setUpdateDttm(now);
						XRetryEventQueue currXRetryEventQueue = xRetryQueueRepository.findOneByRetryId(dto.getRetryId());
						if(currXRetryEventQueue != null) {
							currXRetryEventQueue.setId(xRetryEventQueue.getId());
							xRetryEventQueue = xRetryQueueRepository.save(currXRetryEventQueue);
						} else {
							xRetryEventQueue = xRetryQueueRepository.save(xRetryEventQueue);
						}
						
						if (xRetryEventQueue != null) {
							String newFileName = String.format("%s%s", QueueConstraint.QUEUE_EVENT_PROCESSED_ERROR_FILE_PREFIX , curName);
							boolean isSuccess = XRetryEventQueueService.renameProccessedErrorEventFiles(curFile, new File(logPath, newFileName));
							if(isSuccess) {
								logger.info("File : " + curName + " has produced successfully!");
							}
						}
					} catch (JsonGenerationException e) {
						logger.error("File : " + curName + " has failed, Cause JsonGenerationException!");
						logger.error("JsonGenerationException : "+ e.getMessage(), e);
					} catch (JsonMappingException e) {
						logger.error("File : " + curName + " has failed, Cause JsonMappingException!");
						logger.error("JsonMappingException : "+ e.getMessage(), e);
					} catch (IOException e) {
						logger.error("File : " + curName + " has failed, Cause IOException!");
						logger.error("IOException : "+ e.getMessage(), e);
					} catch (Exception e) {
						logger.error("File : " + curName + " has failed, Cause Exception!");
						logger.error("Exception : "+ e.getMessage(), e);
					}
				} else if (listOfFiles[i].isDirectory()) {
					logger.info("File : " + listOfFiles[i] + " is Directory!");
				}
			}
			logger.info("=================   insertErrorEventQueue End     =================");
		} else {
			logger.info("=================   insertErrorEventQueue Disable =================");
		}
	}

	@Scheduled(cron = "${retry.schedule.delete.error.record.trigger.rate}")
	public void deleteErrorEventQueue() throws InterruptedException {
		if (isDeleteRecordEnable) {
			logger.info("=================   deleteErrorEventQueue Begin   =================");
			boolean isSuccess = XRetryEventQueueService.deleteProccessedDirFiles();
			if(isSuccess) {
				logger.info("File delete has produced successfully!");
			} else {
				logger.error("File delete has produced failed!");
			}
			logger.info("=================   deleteErrorEventQueue End     =================");
		} else {
			logger.info("=================   deleteErrorEventQueue Disable =================");
		}
	}
	
	@Scheduled(cron = "${retry.schedule.mail.notice.trigger.rate}")
	public void pendingEventQueueNotice() throws InterruptedException {
		if (isMailNoticeEnable) {
			logger.info("=================   pendingEventQueueNotice Begin   =================");
			boolean isSuccess = xRetryEventQueueHandler.mailNoticeProccess();
			if(isSuccess) {
				logger.info("File delete has produced successfully!");
			} else {
				logger.error("File delete has produced failed!");
			}
			logger.info("=================   pendingEventQueueNotice End     =================");
		} else {
			logger.info("=================   pendingEventQueueNotice Disable =================");
		}
	}
}
