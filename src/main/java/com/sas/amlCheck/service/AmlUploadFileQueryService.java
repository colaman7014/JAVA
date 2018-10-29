package com.sas.amlCheck.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sas.aml.uploader.bean.QueryNameCheckUploadBean;
import com.sas.aml.uploader.bean.QueryNameCheckUploadReq;
import com.sas.aml.uploader.bean.QueryNameCheckUploadResp;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.dao.IXInvBlNcUploadRecordDao;
import com.sas.db.wlf.orm.XInvBlNcUploadRecord;
import com.sas.util.DateUtil;

@Service
public class AmlUploadFileQueryService {

	@Autowired
	IXInvBlNcUploadRecordDao iXInvBlNcUploadRecordDao;
	
	public QueryNameCheckUploadResp getManualUploadFileList(QueryNameCheckUploadReq req){
		
		// To initial Response
		QueryNameCheckUploadResp resp = new QueryNameCheckUploadResp();
		// To Query DB By Condition 
		String startDate = "";
		String endDate = "";
		if(req.getCreateDateStart()  !=  null) {
			startDate = DateUtil.getNowDate(req.getCreateDateStart(), "") + " 00:00:00";
		}
		if(req.getCreateDateEnd()  !=  null){
			endDate = DateUtil.getNowDate(req.getCreateDateEnd(), "") + " 23:59:59";
		}
		// To Query All Manual Data
		List<XInvBlNcUploadRecord> resultList = iXInvBlNcUploadRecordDao.queryFileUploadCondition(req.getCreateUser(), 
				req.getUploadType(), req.getScanType(), startDate, endDate , req.getScanStatus());
		
		
		// To Split Data By Status [Y,N,F&U]
		List<QueryNameCheckUploadBean> scanList = new ArrayList<QueryNameCheckUploadBean>();
		List<QueryNameCheckUploadBean> unScanList = new ArrayList<QueryNameCheckUploadBean>();
		List<QueryNameCheckUploadBean> otherList = new ArrayList<QueryNameCheckUploadBean>();
		if(!CollectionUtils.isEmpty(resultList)){
			for(XInvBlNcUploadRecord entity : resultList){
				if(SwiftMtConst.SCAN_STATUS_Y.equals(entity.getScanStatus())) {
					scanList.add(new QueryNameCheckUploadBean(entity));
				}
					
				if(SwiftMtConst.SCAN_STATUS_N.equals(entity.getScanStatus())) {
					unScanList.add(new QueryNameCheckUploadBean(entity));
				}
				
				if(!SwiftMtConst.SCAN_STATUS_Y.equals(entity.getScanStatus()) &&
						!SwiftMtConst.SCAN_STATUS_N.equals(entity.getScanStatus())) {
					otherList.add(new QueryNameCheckUploadBean(entity));
				}
			}
		}
		// To Set Date To Response
		resp.setScanList(scanList);
		resp.setUnScanList(unScanList);
		resp.setOtherList(otherList);
		
		return resp;
	}
	
	// To Query File Upload Record By File Key
	public XInvBlNcUploadRecord getFilePathByFileKey(String fileKey) {
		XInvBlNcUploadRecord fileData = iXInvBlNcUploadRecordDao.findOne(fileKey);
		return fileData;
	}
}
