package com.sas.webservice.nameCheckStatus;

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
import com.sas.db.wlf.dao.nc.INameCheckRecordDetailDao;
import com.sas.db.wlf.dao.nc.INameCheckRecordMainDao;
import com.sas.db.wlf.orm.KeyGenerate;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;
import com.sas.db.wlf.orm.nc.NameCheckRecordMainPK;
import com.sas.util.StringUtils;
import com.sas.webservice.billLadingsInvoiceStatus.bean.BillLadingsInvoiceStatusOutputBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusInputBean;
import com.sas.webservice.nameCheckStatus.bean.NameCheckStatusOutputBean;

/**
 * AML Name Check Status 主程式
 * @author SAS
 *
 */
@Component
@WebService(endpointInterface = "com.sas.webservice.nameCheckStatus.AmlNameCheckStatus")
public class AmlNameCheckStatusImpl implements AmlNameCheckStatus {
	private static final Logger logger = LoggerFactory.getLogger(AmlNameCheckStatusImpl.class);
	@Autowired
	IKeyGenerateDao iKeyGenerateDao;
	@Autowired
	INameCheckRecordMainDao iNameCheckRecordMainDao;
	@Autowired
	INameCheckRecordDetailDao iNameCheckRecordDetailDao;
	@Autowired
	ICaseLiveDao iCaseLiveDao;
	/**
	 * AML Name Check Status 主程式
	 */
	@Override
	public NameCheckStatusOutputBean NameCheckStatus(NameCheckStatusInputBean input) {
		logger.info("\n --------------- NameCheckStatus Begin......  --------------- \n");
		logger.info("NameCheckStatus input : " + input.toString());
		NameCheckStatusOutputBean nameCheckStatusOutputBean = null;
		
		String interfaceName = SwiftMtConst.INTERFACE_TYPE_NAMECHECK_STATUS;
		String callingSystem = input.getCallingSystem();
		Date transactionDate = new Date();
		String ncReferenceId = input.getNcReferenceId();
		String referenceNumber = input.getReferenceNumber();
		String uniqueKey = input.getUniqueKey();
		
		try{
			if(chkScanFields(interfaceName, callingSystem, ncReferenceId, uniqueKey)){
				nameCheckStatusOutputBean = new NameCheckStatusOutputBean();
				nameCheckStatusOutputBean.setInterfaceName(interfaceName);
				nameCheckStatusOutputBean.setNcReferenceId("");
				nameCheckStatusOutputBean.setReferenceNumber(referenceNumber);
				nameCheckStatusOutputBean.setUniqueKey(uniqueKey);
				nameCheckStatusOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_FORMATERR);
				nameCheckStatusOutputBean.setErrorMessage("");
				nameCheckStatusOutputBean.setNcResult("");
				logger.warn(String.format("NameCheckStatus ERROR_CODE_FORMATERR TRANSACTION_DATE : %s, NC_REFERENCE_ID : %s, UNIQUE_KEY : %s, REFERENCE_NUMBER : %s", transactionDate, ncReferenceId, uniqueKey, referenceNumber));
			}else{
				KeyGenerate keyGenerate = null;
				NameCheckRecordMain nameCheckRecordMain = null;
				List<NameCheckRecordDetail> nameCheckRecordDetailList = null;
				
				if(!StringUtils.isEmpty(ncReferenceId)){
					keyGenerate = iKeyGenerateDao.findOne(Integer.parseInt(ncReferenceId));
					if(keyGenerate != null){
						int db_referenceId = keyGenerate.getReferenceId();
						String db_uniqueKey = keyGenerate.getUniqueKey();
						
						nameCheckRecordMain = iNameCheckRecordMainDao.findOne(new NameCheckRecordMainPK(db_uniqueKey, db_referenceId));
						nameCheckRecordDetailList = iNameCheckRecordDetailDao.findByIdUniqueKeyAndIdNcReferenceId(db_uniqueKey, db_referenceId);
					}
				}else if(!StringUtils.isEmpty(uniqueKey)){
					nameCheckRecordMain = iNameCheckRecordMainDao.findByIdUniqueKeyAndCallingSystem(uniqueKey, callingSystem);
					if(nameCheckRecordMain != null){
						nameCheckRecordDetailList = iNameCheckRecordDetailDao.findByIdUniqueKeyAndIdNcReferenceId(uniqueKey, nameCheckRecordMain.getId().getNcReferenceId());
					}
				}
				
				if(nameCheckRecordMain != null && nameCheckRecordDetailList != null){					
					nameCheckStatusOutputBean = new NameCheckStatusOutputBean(nameCheckRecordMain, nameCheckRecordDetailList);
					nameCheckStatusOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SUCCESS);
					nameCheckStatusOutputBean.setErrorMessage("");
					setNcCaseStatusnCloseReason(nameCheckRecordMain.getCaseRk(), nameCheckRecordMain.getNcCloseReason(), nameCheckStatusOutputBean);
				}else{
					nameCheckStatusOutputBean = new NameCheckStatusOutputBean();
					nameCheckStatusOutputBean.setInterfaceName(interfaceName);
					nameCheckStatusOutputBean.setNcReferenceId("");
					nameCheckStatusOutputBean.setUniqueKey(uniqueKey);
					nameCheckStatusOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_NODATAFOUND);
					nameCheckStatusOutputBean.setErrorMessage("");
				}
			}
		}catch(Exception e){
			logger.error(String.format("NameCheckStatus fail exception : %s", e.getMessage()), e);
			nameCheckStatusOutputBean = new NameCheckStatusOutputBean();
			nameCheckStatusOutputBean.setInterfaceName(interfaceName);
			nameCheckStatusOutputBean.setNcReferenceId("");
			nameCheckStatusOutputBean.setReferenceNumber(referenceNumber);
			nameCheckStatusOutputBean.setUniqueKey(uniqueKey);
			nameCheckStatusOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SYSTEMERR);
			nameCheckStatusOutputBean.setErrorMessage(e.getMessage());
			nameCheckStatusOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
		}
		
		logger.info("NameCheckStatus output : " + nameCheckStatusOutputBean.toString());
		return nameCheckStatusOutputBean;
	}
	
	/**
	 * 設定name check的案件調查狀態及結案原因
	 * @param caseRk
	 * @param ncCloseReason
	 * @param bean
	 */
	private void setNcCaseStatusnCloseReason(long caseRk, String ncCloseReason, NameCheckStatusOutputBean bean){
		if(caseRk != 0){
			CaseLive caseLive = iCaseLiveDao.findOne(new BigDecimal(caseRk));
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
			bean.setNcCaseId(caseLive.getCaseId());
			bean.setNcCaseStatus(caseStatus==null?" ":caseStatus);
		}
	}
	
	/**
	 * Input 輸入欄位資訊檢查
	 * @param interfaceName
	 * @param callingSystem
	 * @param ncReferenceId
	 * @param uniqueKey
	 * @return
	 */
	private boolean chkScanFields(String interfaceName, String callingSystem, String ncReferenceId, String uniqueKey){
		boolean result = false;
		if(StringUtils.isEmpty(interfaceName) || !SwiftMtConst.INTERFACE_TYPE_NAMECHECK_STATUS.equals(interfaceName)){
			result = true;
		}
		
		if(StringUtils.isEmpty(ncReferenceId) && StringUtils.isEmpty(uniqueKey)){
			result = true;
		}
				
		return result;
	}
}
