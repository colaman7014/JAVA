package com.sas.aml.retry.event.queue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sas.aml.retry.event.queue.bean.XRetryEventQueue;


@Repository
public interface XRetryEventQueueRepository extends CrudRepository<XRetryEventQueue, Long> {
	
	@Query
	List<XRetryEventQueue> findByStatus(String status);
	
	@Query
	List<XRetryEventQueue> findByModule(String module);
	
	@Query
	XRetryEventQueue findOneByRetryId(String retryId);
	
	@Query
	boolean existsByEntityContent(String entityContent);
	
	@Query
	XRetryEventQueue findByEntityContent(String entityContent);

	@Query
	List<XRetryEventQueue> findByModuleAndCallbackFunc(String module, String callbackFunc);

	@Query
	XRetryEventQueue findByModuleAndEntityContent(String module, String entityContent);
	
	@Query
	void deleteByModuleAndEntityContent(String module, String entityContent);

	@Query
	void deleteByModuleAndCallbackFunc(String module, String callbackType);

	@Query
	List<XRetryEventQueue> findByMailNoticeTrueAndMailListNotNullAndStatus(String queueEventStatusPending);
	
}
