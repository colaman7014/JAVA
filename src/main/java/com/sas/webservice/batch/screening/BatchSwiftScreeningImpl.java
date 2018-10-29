package com.sas.webservice.batch.screening;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.jws.WebService;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.wlf.dao.sc.ISwiftCheckRecordDao;
import com.sas.db.wlf.orm.sc.SwiftCheckRecord;
import com.sas.webservice.batch.screening.bean.BatchSwiftScreeningInputBean;
import com.sas.webservice.batch.screening.bean.BatchSwiftScreeningOutputBean;
import com.sas.webservice.swiftCheck.AmlSwiftCheck;
import com.sas.webservice.swiftCheck.bean.SwiftCheckInputBean;
import com.sas.webservice.swiftCheck.bean.SwiftCheckOutputBean;

/**
 * 夜批掃描 Swift Screening 主程式
 * @author SAS
 *
 */
@Component
@WebService(endpointInterface = "com.sas.webservice.batch.screening.BatchSwiftScreening")
public class BatchSwiftScreeningImpl implements BatchSwiftScreening {
	private static final Logger logger = LoggerFactory.getLogger(BatchSwiftScreeningImpl.class);
	
	@Autowired
	ISwiftCheckRecordDao iSwiftCheckRecordDao;
	@Autowired
	AmlSwiftCheck amlSwiftCheck;
	
	/**
	 * 夜批掃描 Swift Screening 主程式
	 */
	@Override
	@Transactional
	public BatchSwiftScreeningOutputBean batchSwiftScreeningImpl(BatchSwiftScreeningInputBean inputTmp) {
		// TODO Auto-generated method stub
		BatchSwiftScreeningOutputBean batchSwiftScreeningOutputBean = new BatchSwiftScreeningOutputBean();
		batchSwiftScreeningOutputBean.setFinish(false);
		batchSwiftScreeningOutputBean.setServerName(System.getenv("COMPUTERNAME"));
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp now = new Timestamp(System.currentTimeMillis());

		//UniqueKey組合 BatchNameChecking+yyyyMMddHHmmss+countUniqueKey
		int countUniqueKey=0;
		String uniqueKey = SwiftMtConst.INTERFACE_TYPE_BATCHNAMECHECK+"_"+new SimpleDateFormat("yyyyMMddHHmmss").format(now);
		
		
		//撈取當天需要處理的
		List<SwiftCheckRecord> swiftCheckRecordList = iSwiftCheckRecordDao.findByScreenProcessAndTransactionDateGreaterThanEqual(SwiftMtConst.SCREEN_PROCESS_SWIFT_Screening, Timestamp.valueOf(sdf.format(now)+" 00:00:00"));
		
		for(SwiftCheckRecord SwiftCheckRecordBe : swiftCheckRecordList){	
			countUniqueKey++;
			
			String swiftRje = SwiftCheckRecordBe.getSwiftFullText();
			
			 if(!"".equals(swiftRje)){
				 SwiftCheckInputBean input = new SwiftCheckInputBean();
//				 input.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_BATCHSWIFTCHECK);
				 input.setCallingSystem(SwiftMtConst.CALLING_SYSTEM_NON_SWIFT);
				 input.setScreenProcess(SwiftMtConst.SCREEN_PROCESS_BATCH_SWIFT_SCREENING);
				 input.setCallingUser(SwiftCheckRecordBe.getCallingUser());
				 input.setBusinessUnitId(SwiftCheckRecordBe.getBusinessUnitId());
				 input.setBranchNumber(SwiftCheckRecordBe.getBranchNumber());
//				 input.setTransactionDate(String.valueOf(SwiftCheckRecordBe.getTransactionDate()));
				 input.setUniqueKey(uniqueKey+String.valueOf(countUniqueKey));
				 input.setSwiftRje(swiftRje);		 
				 
				 SwiftCheckOutputBean swiftCheckOutputBean = amlSwiftCheck.SwiftCheck(input);
			 }
		}
		
		batchSwiftScreeningOutputBean.setFinish(true);
		return batchSwiftScreeningOutputBean;
	}
		
}

