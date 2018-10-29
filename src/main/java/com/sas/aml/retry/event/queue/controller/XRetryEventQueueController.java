package com.sas.aml.retry.event.queue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sas.aml.retry.event.queue.bean.XRetryEventQueue;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueueDto;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueueRespDto;
import com.sas.aml.retry.event.queue.service.XRetryEventQueueService;

@RestController
@RequestMapping("retry")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class XRetryEventQueueController {
	
	private static final Logger logger = LoggerFactory.getLogger(XRetryEventQueueController.class);
	 
    @Autowired
    XRetryEventQueueService xRetryEventQueueService; //Service which will do all data retrieval/manipulation work
 
    // -------------------Retrieve All records---------------------------------------------
  
    @GetMapping("/records")
    public ResponseEntity<XRetryEventQueueRespDto> listAllXRetryEventQueue(String module, String callbackType) {
        List<XRetryEventQueue> xRetryEventQueues = xRetryEventQueueService.findAllByModuleAndCallbackType(module, callbackType);
        if (xRetryEventQueues.isEmpty()) {
            return new ResponseEntity(new XRetryEventQueueRespDto(HttpStatus.NO_CONTENT, "fail", "Not find any record", xRetryEventQueues), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new XRetryEventQueueRespDto(HttpStatus.OK, "success", "Find "+ xRetryEventQueues.size() + "records. ", xRetryEventQueues) , HttpStatus.OK);
    }
 
    // -------------------Retrieve Single record------------------------------------------

    @GetMapping("/record/find/{id}")
    public ResponseEntity<XRetryEventQueueRespDto> getXRetryEventQueue(@PathVariable("id") long id) {
        logger.info("Fetching User with id : " + id);
        XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.findById(id);
        if (xRetryEventQueue == null) {
            logger.error("RetryEventQueue with " + id + " not found.");
            return new ResponseEntity(new XRetryEventQueueRespDto(HttpStatus.NOT_FOUND, "fail", "User with id " + id 
                    + " not found", xRetryEventQueue), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<XRetryEventQueueRespDto>(new XRetryEventQueueRespDto(HttpStatus.OK, "success", "User with id : " + id 
                + " founded.", xRetryEventQueue), HttpStatus.OK);
    }
 
    // -------------------Create a record-------------------------------------------
 
    @PostMapping(value = "/record/add")
    public ResponseEntity<XRetryEventQueueRespDto> createXRetryEventQueue(@RequestBody XRetryEventQueueDto xRetryEventQueueDto) {
        logger.info("Creating XRetryEventQueue : {}", xRetryEventQueueDto.toString());
        XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.isContetntExist(xRetryEventQueueDto);
        if (xRetryEventQueue != null) {
            logger.error("Unable to create. A RetryEventQueue with content already exist : " +  xRetryEventQueueDto.toString());
            return new ResponseEntity(new XRetryEventQueueRespDto(HttpStatus.CONFLICT, "fail", "Unable to create. An Entity with content " + 
            		xRetryEventQueueDto.getEntityContent() + " already exist.", xRetryEventQueue), HttpStatus.CONFLICT);
        }
        xRetryEventQueue = xRetryEventQueueService.saveRecord(xRetryEventQueueDto);
 
        return new ResponseEntity<XRetryEventQueueRespDto>(new XRetryEventQueueRespDto(HttpStatus.OK, "success", "Id created : " + xRetryEventQueue.getId() 
          		, xRetryEventQueue), HttpStatus.CREATED);
    }
 
    // ------------------- Update a record ------------------------------------------------
 
    @PutMapping("/record/edit/{id}")
    public ResponseEntity<?> updateXRetryEventQueue(@PathVariable("id") long id, @RequestBody XRetryEventQueueDto xRetryEventQueueDto) {
        logger.info("Updating XRetryEventQueue with id : " + id);
        XRetryEventQueue currentXRetryEventQueue = xRetryEventQueueService.findById(id);
        if (currentXRetryEventQueue == null) {
            logger.error("Unable to update. XRetryEventQueue with id not found." + id);
            return new ResponseEntity(new XRetryEventQueueRespDto(HttpStatus.NOT_FOUND, "fail", "Unable to upate. XRetryEventQueue with id " + id + " not found.", currentXRetryEventQueue), 
            		HttpStatus.NOT_FOUND);
        } else {
        	currentXRetryEventQueue = xRetryEventQueueService.updateRecord(currentXRetryEventQueue, xRetryEventQueueDto);
        }
        return new ResponseEntity<XRetryEventQueue>(currentXRetryEventQueue, HttpStatus.OK);
    }
 
    // ------------------- Delete a record-----------------------------------------

	@DeleteMapping("/record/delete/{id}")
    public ResponseEntity<?> deleteXRetryEventQueue(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting XRetryEventQueue with id {}", id);
 
        XRetryEventQueue currentXRetryEventQueue = xRetryEventQueueService.findById(id);
        if (currentXRetryEventQueue == null) {
            logger.error("Unable to delete. XRetryEventQueue with "+ id + " not found.");
            return new ResponseEntity(new XRetryEventQueueRespDto(HttpStatus.NOT_FOUND, "fail", "Unable to delete. XRetryEventQueue with id " + id + " not found.", currentXRetryEventQueue), 
            		HttpStatus.NOT_FOUND);
        }
        xRetryEventQueueService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // ------------------- Delete all records -----------------------------------------
    
    @DeleteMapping("/record/delete/{module}/{callbacktype}")
    public ResponseEntity<XRetryEventQueueRespDto> deleteAllXRetryEventQueueByModuleAndCallbackType(@PathVariable("module") String module, @PathVariable("callbacktype") String callbacktype) {
        logger.info("Deleting All {} XRetryEventQueue , callbacktype : {}", module, callbacktype);
        xRetryEventQueueService.deleteAllXRetryEventQueueByModuleAndCallbackType(module, callbacktype);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    // ------------------- Cancel All records-----------------------------
 
    @PostMapping("/record/cancel/{module}/{callbacktype}")
    public ResponseEntity<XRetryEventQueueRespDto> cancelAllXRetryEventQueueByModule(@PathVariable("module") String module, @PathVariable("callbacktype") String callbacktype) {
        logger.info("Deleting All {} XRetryEventQueue", module);
        boolean isOk = xRetryEventQueueService.cancelAllXRetryEventQueueByModuleAndCallbackType(module, callbacktype);
        if(!isOk)
        	new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
 // ------------------- Cancel a record -----------------------------
    
    @PostMapping("/record/cancel/{module}")
    public ResponseEntity<XRetryEventQueueRespDto> cancelXRetryEventQueueByPayload(@PathVariable("module") String module, @RequestBody String payload) {
        logger.info("Cancel  XRetryEventQueue : {} , {} ", module, payload);
        boolean isOk = xRetryEventQueueService.cancelXRetryEventQueueByModuleAndEntityContent(module, payload);
        if(!isOk)
        	new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/record/cancel/{id}")
    public ResponseEntity<XRetryEventQueueRespDto> cancelXRetryEventQueueById(@PathVariable("id") long id) {
        logger.info("Cancel  XRetryEventQueue id : ", id);
        boolean isOk = xRetryEventQueueService.cancelXRetryEventQueueById(id);
        if(!isOk)
        	new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    

}
