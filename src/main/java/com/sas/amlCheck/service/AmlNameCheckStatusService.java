package com.sas.amlCheck.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sas.db.wlf.dao.nc.INameCheckRecordDetailDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordMainDao;
import com.sas.db.wlf.dao.nc.INameHitRecordDao;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;
import com.sas.db.wlf.orm.nc.NameHitRecord;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusDetailInputRestBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusDetailoutputRestBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusHitoutputRestBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusMainInputRestBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusMainoutputRestBean;

@Service
public class AmlNameCheckStatusService {
	private static final Logger logger = LoggerFactory.getLogger(AmlNameCheckStatusService.class);
	@Autowired
	INameCheckRecordMainDao iNameCheckRecordMainDao;
	@Autowired
	INameCheckRecordDetailDao iNameCheckRecordDetailDao;
	@Autowired
	INameHitRecordDao iNameHitRecordDao;
	public List<NameCheckStatusMainoutputRestBean> nameCheckStatusMain(NameCheckStatusMainInputRestBean nameCheckStatusMainInputRestBean){
		logger.debug("nameCheckStatusMain input : " + nameCheckStatusMainInputRestBean.toString());
		String calling_user = nameCheckStatusMainInputRestBean.getCalling_user();
		String bussiness_unit_id = nameCheckStatusMainInputRestBean.getBussiness_unit_id();
		String branch_number = nameCheckStatusMainInputRestBean.getBranch_number();
		
		List<NameCheckStatusMainoutputRestBean> outputList = new ArrayList<NameCheckStatusMainoutputRestBean>();
		Iterable<NameCheckRecordMain> mainList = iNameCheckRecordMainDao.findAll();
		
		for(NameCheckRecordMain main : mainList){
			outputList.add(new NameCheckStatusMainoutputRestBean(main));
		}
		logger.debug("nameCheckStatusMain output size : " + outputList.size());
		return outputList;
	}
	
	public List<NameCheckStatusDetailoutputRestBean> nameCheckStatusDetail(NameCheckStatusDetailInputRestBean nameCheckStatusDetailInputRestBean){
		logger.debug("nameCheckStatusMain input : " + nameCheckStatusDetailInputRestBean.toString());
		String uniqueKey = nameCheckStatusDetailInputRestBean.getUniqueKey();
		String ncReferenceId = nameCheckStatusDetailInputRestBean.getNcReferenceId();
		
		List<NameCheckStatusDetailoutputRestBean> outputList = new ArrayList<NameCheckStatusDetailoutputRestBean>();
		
		List<NameCheckRecordDetail> detailList = iNameCheckRecordDetailDao.findByIdUniqueKeyAndIdNcReferenceId(uniqueKey, Integer.parseInt(ncReferenceId));
		List<NameHitRecord> hitRecordList = iNameHitRecordDao.findByIdUniqueKeyAndIdNcReferenceId(uniqueKey, Integer.parseInt(ncReferenceId));
		
		Map<String, List<NameCheckStatusHitoutputRestBean>> hitRecordMap = new HashMap<String, List<NameCheckStatusHitoutputRestBean>>();
		for(NameHitRecord hitRecord : hitRecordList){
			List<NameCheckStatusHitoutputRestBean> hitOutPutList = null;
			if(hitRecordMap.get(hitRecord.getId().getCheckSeq()) != null){
				hitOutPutList = hitRecordMap.get(hitRecord.getId().getCheckSeq());
			}else{
				hitOutPutList = new ArrayList<NameCheckStatusHitoutputRestBean>();
			}
			hitOutPutList.add(new NameCheckStatusHitoutputRestBean(hitRecord));
			
			hitRecordMap.put(hitRecord.getId().getCheckSeq(), hitOutPutList);
		}
		
		for(NameCheckRecordDetail detail : detailList){
			outputList.add(new NameCheckStatusDetailoutputRestBean(detail, hitRecordMap.get(detail.getId().getCheckSeq())));
		}
		logger.debug("nameCheckStatusMain output size : " + outputList.size());
		return outputList;
	}
}
