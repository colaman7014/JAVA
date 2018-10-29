package com.sas.webservice.batch.screening;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.dao.nc.INameCheckRecordDetailDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordMainDao;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;
import com.sas.webservice.batch.screening.bean.BatchTransactionScreeningInputBean;
import com.sas.webservice.batch.screening.bean.BatchTransactionScreeningOutputBean;
import com.sas.webservice.nameCheck.AmlNameCheck;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputBean;

/**
 * 夜批掃描 Transaction Screening 主程式
 * @author SAS
 *
 */
@Component
@WebService(endpointInterface = "com.sas.webservice.batch.screening.BatchTransactionScreening")
public class BatchTransactionScreeningImpl implements BatchTransactionScreening {
	private static final Logger logger = LoggerFactory.getLogger(BatchTransactionScreeningImpl.class);
	
	@Autowired
	INameCheckRecordMainDao iNameCheckRecordMainDao;
	@Autowired
	INameCheckRecordDetailDao iNameCheckRecordDetailDao;
	@Autowired
	AmlNameCheck amlNameCheck;
	
	/**
	 * 夜批掃描 Transaction Screening 主程式
	 */
	@Override
	@Transactional
	public BatchTransactionScreeningOutputBean batchTransactionScreeningImpl(BatchTransactionScreeningInputBean inputTmp) {
		// TODO Auto-generated method stub
		BatchTransactionScreeningOutputBean batchNameCheckOutputBean = new BatchTransactionScreeningOutputBean();
	
		batchNameCheckOutputBean.setFinish(false);
		batchNameCheckOutputBean.setServerName(System.getenv("COMPUTERNAME"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		//logger.info("11111111111111111111111111111111111111111111111");
		//掃描條件
		List<String> screenProcessList = new ArrayList<String>();
		screenProcessList.add(SwiftMtConst.SCREEN_PROCESS_Transaction_Screening);
		screenProcessList.add(SwiftMtConst.SCREEN_PROCESS_Trade_Finance_Screening);
		
		// 1.Screen_process=3,4,03,04  2.需要當天的   
		List<NameCheckRecordMain> nameCheckRecordMainList =  (List<NameCheckRecordMain>) iNameCheckRecordMainDao.findByScreenProcessInAndTransactionDateGreaterThanEqual(screenProcessList, Timestamp.valueOf(sdf.format(now)+" 00:00:00"));
		
		for(NameCheckRecordMain nameCheckRecordMainBean : nameCheckRecordMainList){
			
			//String uniqueKey = nameCheckRecordMainBean.getId().getUniqueKey();
			//UniqueKey組合 BatchNameChecking+yyyyMMddHHmmss+countUniqueKey
			String uniqueKey = SwiftMtConst.INTERFACE_TYPE_BATCHTRANSACTIONSCREENING+"_"+new SimpleDateFormat("yyyyMMddHHmmss").format(now);
			String referenceNumber = String.valueOf(nameCheckRecordMainBean.getReferenceNumber());
			String interfaceName = SwiftMtConst.INTERFACE_TYPE_BATCHTRANSACTIONSCREENING;
			String callingSystem = nameCheckRecordMainBean.getCallingSystem();
			String screenProcess = "";
			if(SwiftMtConst.SCREEN_PROCESS_Transaction_Screening.equals(nameCheckRecordMainBean.getScreenProcess())){
				screenProcess = SwiftMtConst.SCREEN_PROCESS_BATCH_TRANCTION_SCREENING;
			}else{
				screenProcess =  SwiftMtConst.SCREEN_PROCESS_BATCH_TRADE_FINANCE_SCREENING;
			}
			String callingUser = nameCheckRecordMainBean.getCallingUser();
			String businessUnitID = nameCheckRecordMainBean.getBusinessUnitId();
			String branchNumber = nameCheckRecordMainBean.getBranchNumber();
			String genAlertFlag = nameCheckRecordMainBean.getGenAlertFlag();
			Date transactionDate = nameCheckRecordMainBean.getTransactionDate();

			
			NameCheckInputBean input = new NameCheckInputBean();
			input.setUniqueKey(uniqueKey);
			input.setInterfaceName(interfaceName);
			input.setReferenceNumber(referenceNumber);
			input.setCallingSystem(callingSystem);
			input.setScreenProcess(screenProcess);
			input.setCallingUser(callingUser);
			input.setBusinessUnitID(businessUnitID);
			input.setBranchNumber(branchNumber);
			input.setGenAlertFlag(genAlertFlag);
			input.setTransactionDate(transactionDate);
			
			List<NameCheckInputDetailBean> detailList = new ArrayList<NameCheckInputDetailBean>();
			

			
			
			//撈取名單紀錄明細檔 
			List<NameCheckRecordDetail> nameCheckRecordDetailList = iNameCheckRecordDetailDao.findByIdUniqueKeyAndIdNcReferenceId(nameCheckRecordMainBean.getId().getUniqueKey(), nameCheckRecordMainBean.getId().getNcReferenceId());
			

				for(NameCheckRecordDetail nameCheckRecordDetailBean : nameCheckRecordDetailList){
					NameCheckInputDetailBean detail = new NameCheckInputDetailBean();
					detail.setCheckSeq(nameCheckRecordDetailBean.getId().getCheckSeq());
					detail.setEntityType(nameCheckRecordDetailBean.getEntityType());
					detail.setEntityRelationship(nameCheckRecordDetailBean.getEntityRelationship());
					detail.setEntityRelationshipDesc(nameCheckRecordDetailBean.getEntityRelationshipDesc());
					detail.setEnglishName(nameCheckRecordDetailBean.getEnglishName());
					detail.setNonEnglishName(nameCheckRecordDetailBean.getNonEnglishName());
					detail.setCccCode(nameCheckRecordDetailBean.getCccCode());
					detail.setIdNumber(nameCheckRecordDetailBean.getIdNumber());
					detail.setBicSwiftCode(nameCheckRecordDetailBean.getBicSwiftCode());
					detail.setFreeFormatText(nameCheckRecordDetailBean.getFreeFormatText());
					detail.setCountry(nameCheckRecordDetailBean.getCountry());
					if(nameCheckRecordDetailBean.getYearOfBirth()!=null){
						detail.setYearOfBirth(nameCheckRecordDetailBean.getYearOfBirth().toString());
					}
					detail.setGender(nameCheckRecordDetailBean.getGender());
					detailList.add(detail);
				}
	

			
			input.setSeq(detailList);

			
			logger.info("input.getInterfaceName()="+input.getInterfaceName() );
			logger.info("input.getCallingSystem()="+input.getCallingSystem() );
			logger.info("input.getScreenProcess()="+input.getScreenProcess() );
			logger.info("input.getCallingUser()="+input.getCallingUser());
			logger.info("input.getGenAlertFlag()="+input.getGenAlertFlag());
			logger.info("input.getTransactionDate="+input.getTransactionDate());
			logger.info("input.getUniqueKey()" + input.getUniqueKey());
			
			
			List<NameCheckInputDetailBean> nameCheckInputDetailBeanList =  input.getSeq();
			for(NameCheckInputDetailBean be :  nameCheckInputDetailBeanList){
				logger.info("Detail be.getCheckSeq()="+be.getCheckSeq());
				logger.info("Detail be.getEnglishName()="+be.getEnglishName());
				logger.info("Detail be.getNonEnglishName()="+be.getNonEnglishName());
				logger.info("Detail be.getEntityRelationship()="+be.getEntityRelationship());

			}
			
			NameCheckOutputBean nameCheckOutputBean  = amlNameCheck.NameCheck(input); //開始掃描
			

	
		}
		batchNameCheckOutputBean.setFinish(true);
		return batchNameCheckOutputBean;
		
	}
	
}

