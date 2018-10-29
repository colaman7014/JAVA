package com.sas.aml.retry.event.queue.service;


public class QueueConstraint {
	public static final String QUEUE_EVENT_STATUS_WAITING = "waiting";
	public static final String QUEUE_EVENT_STATUS_PENDING = "pending";
	public static final String QUEUE_EVENT_STATUS_COMPLETED = "completed";
	public static final String QUEUE_EVENT_STATUS_CANCELED = "canceled";
	public static final String QUEUE_EVENT_STATUS_ERROR = "error";
	public static final String QUEUE_EVENT_STATUS_NON_NEED_RETRY = "non-retry";
	public static final String QUEUE_EVENT_STATUS_MAILED = "mailed";
	
	
	
	public static final String QUEUE_EVENT_RESP_SUCCESS = "0";
	public static final String QUEUE_EVENT_ERRORCODE_FORMAT_NO_ERROR = "1";
	public static final String QUEUE_EVENT_ERRORCODE_NO_CALLBACKFUNC_ERROR = "11";
	public static final String QUEUE_EVENT_ERRORCODE_ENDPOINTURL_FORMAT_ERROR = "12";
	public static final String QUEUE_EVENT_ERRORCODE_HTTPMETHOD_FORMAT_ERROR = "13";
	public static final String QUEUE_EVENT_ERRORCODE_ENTITY_FORMAT_ERROR = "14";
	
	public static final String QUEUE_EVENT_ERRORCODE_EXEC_FAIL = "2";
	public static final String QUEUE_EVENT_ERRORCODE_OVER_RETRY_LIMIT = "3";
	public static final String QUEUE_EVENT_ERRORCODE_HTTP_TRANSFER_ERROR = "4";
	public static final String QUEUE_EVENT_ERRORCODE_INTERNAL_SERVER_ERROR = "9";
	
	public static final String QUEUE_EVENT_EXEC_SUCCESS = "SUCCESS";
	public static final String QUEUE_EVENT_EXEC_FAIL = "FAIL";
	public static final String QUEUE_EVENT_PROCESSED_ERROR_FILE_PREFIX = "Save_";
	public static final String QUEUE_EVENT_DEPROCESSED_ERROR_FILE_PREFIX = "Retry_";
		
	public static final int QUEUE_EVENT_NO_MAX_RETRYTIME_LIMIT = 0;
	
	public static final String COM_SAS_BASE_URL = "com.sas.base.url";
}
