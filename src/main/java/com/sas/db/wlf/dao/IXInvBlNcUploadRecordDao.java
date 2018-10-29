package com.sas.db.wlf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.XInvBlNcUploadRecord;

public interface IXInvBlNcUploadRecordDao extends CrudRepository<XInvBlNcUploadRecord, String>, IXInvBlNcUploadRecordDaoCustomer{
	
	@Query("select s from XInvBlNcUploadRecord s where s.uploadType='5' and s.scanStatus='N' and scanType in ('1','2','3')")
	List<XInvBlNcUploadRecord> queryUploadNameCheckRecord();
	
	List<XInvBlNcUploadRecord> findByUploadTypeAndScanStatusAndScanTypeIn(String uploadType, String scanStatus, List<String> scanType);
	
	XInvBlNcUploadRecord findByFileKey(String fileKey);
}
