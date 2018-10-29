package com.sas.webservice.swiftCheckStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.ICaseLiveDao;
import com.sas.db.aml.orm.ecm.CaseLive;
import com.sas.db.wlf.dao.IKeyGenerateDao;
import com.sas.db.wlf.dao.sc.ISwiftCheckRecordDao;
import com.sas.db.wlf.orm.KeyGenerate;
import com.sas.db.wlf.orm.sc.SwiftCheckRecord;
import com.sas.db.wlf.orm.sc.SwiftCheckRecordPK;
import com.sas.util.StringUtils;
import com.sas.webservice.swiftCheckStatus.bean.SwiftCheckStatusInputBean;
import com.sas.webservice.swiftCheckStatus.bean.SwiftCheckStatusOutputBean;
/**
 * AML SWIFT CHECK Status 主程式
 * @author SAS
 *
 */
@Component
@WebService(endpointInterface = "com.sas.webservice.swiftCheckStatus.AmlSwiftCheckStatus")
public class AmlSwiftCheckStatusImpl implements AmlSwiftCheckStatus {
	private static final Logger logger = LoggerFactory.getLogger(AmlSwiftCheckStatusImpl.class);
	@Autowired
	IKeyGenerateDao iKeyGenerateDao;
	@Autowired
	ISwiftCheckRecordDao iSwiftCheckRecordDao;
	@Autowired
	ICaseLiveDao iCaseLiveDao;
	/**
	 * AML SWIFT CHECK Status 主程式
	 */
	@Override
	public SwiftCheckStatusOutputBean SwiftCheckStatus(SwiftCheckStatusInputBean input) {
		logger.info("\n --------------- SwiftCheckStatus Begin......  --------------- \n");
		logger.info("SwiftCheckStatus input : " + input.toString());
		SwiftCheckStatusOutputBean swiftCheckStatusOutputBean = null;
		String interfaceName = SwiftMtConst.INTERFACE_TYPE_SWIFTCHECK_STATUS;
		Date transactionDate = new Date();
		String ncReferenceId = input.getNcReferenceId();
		String uniqueKey = input.getUniqueKey();
		String callingSystem = input.getCallingSystem();
		try{
			if(chkScanFields(interfaceName, callingSystem, ncReferenceId, uniqueKey)){
				String errorMessage = "";//"TRANSACTION_DATE, NC_REFERENCE_ID or UNIQUE_KEY can't empty.";
				swiftCheckStatusOutputBean = new SwiftCheckStatusOutputBean(
						interfaceName, "", SwiftMtConst.ERROR_CODE_FORMATERR, 
						errorMessage, SwiftMtConst.NC_RESULT_NON_CHECK, uniqueKey, 
						null, null, null, null, null, null);
				logger.warn(String.format("SwiftCheckStatus ERROR_CODE_FORMATERR TRANSACTION_DATE : %s, NC_REFERENCE_ID : %s, UNIQUE_KEY : %s", transactionDate, ncReferenceId, uniqueKey));
			}else{
				KeyGenerate keyGenerate = null;
				SwiftCheckRecord swiftCheckRecord = null;
				if(!StringUtils.isEmpty(ncReferenceId)){
					keyGenerate = iKeyGenerateDao.findOne(Integer.parseInt(ncReferenceId));
					swiftCheckRecord = iSwiftCheckRecordDao.findOne(new SwiftCheckRecordPK(keyGenerate.getUniqueKey(), keyGenerate.getReferenceId()));
				}else if(!StringUtils.isEmpty(uniqueKey)){
					List<SwiftCheckRecord> tmpList = iSwiftCheckRecordDao.findByIdUniqueKeyAndCallingSystem(uniqueKey, callingSystem);
					if(tmpList != null && tmpList.size() > 0){
						swiftCheckRecord = tmpList.get(0);
					}
				}
				
				if(swiftCheckRecord != null){
					swiftCheckStatusOutputBean = new SwiftCheckStatusOutputBean(swiftCheckRecord);
					swiftCheckStatusOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SUCCESS);
					swiftCheckStatusOutputBean.setErrorMessage("");
					setNcCaseStatusnCloseReason(swiftCheckRecord.getCaseRk(), swiftCheckRecord.getNcCloseReason(), swiftCheckStatusOutputBean);
				}else{
					swiftCheckStatusOutputBean = new SwiftCheckStatusOutputBean(
							interfaceName, "", SwiftMtConst.ERROR_CODE_NODATAFOUND, 
							"", "", uniqueKey, 
							null, null, null, null, null, null);
				}
				
			}
		}catch(Exception e){
			logger.error(String.format("SwiftCheckStatus fail exception : %s", e.getMessage()), e);
			swiftCheckStatusOutputBean = new SwiftCheckStatusOutputBean(
					interfaceName, "", SwiftMtConst.ERROR_CODE_SYSTEMERR, 
					e.getMessage(), SwiftMtConst.NC_RESULT_NON_CHECK, uniqueKey, 
					null, null, null, null, null, null);
		}
		
		logger.info("SwiftCheckStatus output : " + swiftCheckStatusOutputBean.toString());
		return swiftCheckStatusOutputBean;		
	}
	
	/**
	 * 設定swift check的案件調查狀態及結案原因
	 * @param caseRk
	 * @param ncCloseReason
	 * @param bean
	 */
	private void setNcCaseStatusnCloseReason(long caseRk, String ncCloseReason, SwiftCheckStatusOutputBean bean){
		if(caseRk != 0){
			CaseLive caseLive = iCaseLiveDao.findOne(new BigDecimal(caseRk));
			String caseId = caseLive.getCaseId();
			String caseStatus = caseLive.getCaseStatusCd();
			if(SwiftMtConst.NC_CASE_STATUS_CLOSED.equalsIgnoreCase(caseStatus)){
				if(SwiftMtConst.NC_RESULT_101_TRUE_HIT.equalsIgnoreCase(ncCloseReason)){
					ncCloseReason = SwiftMtConst.NC_RESULT_TRUE_HIT;
				}else if(SwiftMtConst.NC_RESULT_102_FALSE_HIT.equalsIgnoreCase(ncCloseReason)){
					ncCloseReason = SwiftMtConst.NC_RESULT_FALSE_HIT;
				}else{
					ncCloseReason = "";
				}
			}else{
				ncCloseReason = "";
			}
			bean.setNcCaseId(caseId);
			bean.setNcCaseStatus(StringUtils.isEmpty(caseStatus)?"":caseStatus);
			bean.setNcCloseReason(ncCloseReason);
		}
	}
	
	/**
	 * Input 資訊內容檢查
	 * @param interfaceName
	 * @param uniqueKey
	 * @param ncReferenceId
	 * @return boolean
	 */
	private boolean chkScanFields(String interfaceName, String callingSystem, String ncReferenceId, String uniqueKey){
		boolean result = false;
		if(StringUtils.isEmpty(interfaceName) || !SwiftMtConst.INTERFACE_TYPE_SWIFTCHECK_STATUS.equals(interfaceName)){
			result = true;
		}
		
		if(StringUtils.isEmpty(ncReferenceId) && StringUtils.isEmpty(uniqueKey)){
			result = true;
		}
		
		return result;
	}
}
