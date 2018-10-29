package com.sas.webservice.billLadingsInvoiceStatus;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

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
import com.sas.db.wlf.dao.tf.IXInvExportCaseStatusDao;
import com.sas.db.wlf.dao.tf.IXInvExportDao;
import com.sas.db.wlf.dao.tf.IXInvImportCaseStatusDao;
import com.sas.db.wlf.dao.tf.IXInvImportDao;
import com.sas.db.wlf.orm.KeyGenerate;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordMain;
import com.sas.db.wlf.orm.nc.NameCheckRecordMainPK;
import com.sas.db.wlf.orm.tf.XInvExport;
import com.sas.db.wlf.orm.tf.XInvExportCaseStatus;
import com.sas.db.wlf.orm.tf.XInvExportCaseStatusPK;
import com.sas.db.wlf.orm.tf.XInvImport;
import com.sas.db.wlf.orm.tf.XInvImportCaseStatus;
import com.sas.db.wlf.orm.tf.XInvImportCaseStatusPK;
import com.sas.util.StringUtils;
import com.sas.webservice.billLadingsInvoiceStatus.bean.BillLadingsInvoiceStatusInputBean;
import com.sas.webservice.billLadingsInvoiceStatus.bean.BillLadingsInvoiceStatusOutputBean;
/**
 * AML Trade Finance Status 主程式
 * @author SAS
 *
 */
@Component
@WebService(endpointInterface = "com.sas.webservice.billLadingsInvoiceStatus.AmlBLInvStatus")
public class AmlBLInvStatusImpl implements AmlBLInvStatus {
	private static final Logger logger = LoggerFactory.getLogger(AmlBLInvStatusImpl.class);
	@Autowired
	IKeyGenerateDao iKeyGenerateDao;
	@Autowired
	INameCheckRecordMainDao iNameCheckRecordMainDao;
	@Autowired
	INameCheckRecordDetailDao iNameCheckRecordDetailDao;
	@Autowired
	ICaseLiveDao iCaseLiveDao;
	@Autowired
	IXInvExportDao iXInvExportDao;
	@Autowired
	IXInvExportCaseStatusDao iXInvExportCaseStatusDao;
	@Autowired
	IXInvImportDao iXInvImportDao;
	@Autowired
	IXInvImportCaseStatusDao iXInvImportCaseStatusDao;
	/**
	 * AML Trade Finance Status 主程式
	 */
	@Override
	public BillLadingsInvoiceStatusOutputBean amlBLInvStatus(BillLadingsInvoiceStatusInputBean input) {
		logger.info("billLadingsInvoiceStatus input : " + input.toString());
		BillLadingsInvoiceStatusOutputBean billLadingsInvoiceStatusOutputBean = null;
		String interfaceName = input.getInterfaceName();
		String ncReferenceId = input.getNcReferenceId();
		String uniqueKey = input.getUniqueKey();
		try{
			if(chkScanFields(interfaceName, ncReferenceId, uniqueKey)){
				billLadingsInvoiceStatusOutputBean = new BillLadingsInvoiceStatusOutputBean();
				billLadingsInvoiceStatusOutputBean.setInterfaceName(interfaceName);
				billLadingsInvoiceStatusOutputBean.setNcReferenceId("");
				billLadingsInvoiceStatusOutputBean.setUniqueKey(uniqueKey);
				billLadingsInvoiceStatusOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_FORMATERR);
				billLadingsInvoiceStatusOutputBean.setErrorMessage("");
				billLadingsInvoiceStatusOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
				billLadingsInvoiceStatusOutputBean.setRouteRule("");
				billLadingsInvoiceStatusOutputBean.setNcCaseId("");
				billLadingsInvoiceStatusOutputBean.setNcCaseStatus("");
				billLadingsInvoiceStatusOutputBean.setNcCloseReason("");
//				billLadingsInvoiceStatusOutputBean.setPepFlag("");
//				billLadingsInvoiceStatusOutputBean.setPnmpFlag("");
			}else{
				KeyGenerate keyGenerate = null;
				NameCheckRecordMain nameCheckRecordMain = null;
				List<NameCheckRecordDetail> nameCheckRecordDetailList = null;
				if(!StringUtils.isEmpty(uniqueKey)){
					List<KeyGenerate> keyGenerateList = iKeyGenerateDao.findByUniqueKeyOrderByProcessDttmDesc(uniqueKey);
					if(keyGenerateList != null && keyGenerateList.size() > 0){
						keyGenerate = keyGenerateList.get(0);
						
						int db_referenceId = keyGenerate.getReferenceId();
						String db_uniqueKey = keyGenerate.getUniqueKey();
						
						nameCheckRecordMain = iNameCheckRecordMainDao.findOne(new NameCheckRecordMainPK(db_uniqueKey, db_referenceId));
						nameCheckRecordDetailList = iNameCheckRecordDetailDao.findByIdUniqueKeyAndIdNcReferenceId(db_uniqueKey, db_referenceId);
					}
				}else if(!StringUtils.isEmpty(ncReferenceId)){
					keyGenerate = iKeyGenerateDao.findOne(Integer.parseInt(ncReferenceId));
					if(keyGenerate != null){
						int db_referenceId = keyGenerate.getReferenceId();
						String db_uniqueKey = keyGenerate.getUniqueKey();
						
						nameCheckRecordMain = iNameCheckRecordMainDao.findOne(new NameCheckRecordMainPK(db_uniqueKey, db_referenceId));
						nameCheckRecordDetailList = iNameCheckRecordDetailDao.findByIdUniqueKeyAndIdNcReferenceId(db_uniqueKey, db_referenceId);
					}
				}
				
				if(nameCheckRecordMain != null && nameCheckRecordDetailList != null){
					List<XInvExport> xInvExportList = iXInvExportDao.findByIdUniqueKeyOrderByCreateDttmDesc(uniqueKey);
					List<XInvImport> xInvImportList = iXInvImportDao.findByIdUniqueKeyOrderByCreateDttmDesc(uniqueKey);
					String ncStatus = nameCheckRecordMain.getNcResult();
					String priceStatus = "";
					if(xInvExportList != null && xInvExportList.size() > 0){
						XInvExport xInvExport = xInvExportList.get(0);
						XInvExportCaseStatus xInvExportCaseStatus = iXInvExportCaseStatusDao.findOne(new XInvExportCaseStatusPK(xInvExport.getId().getUniqueKey(), xInvExport.getId().getSeq(), xInvExport.getId().getFileKey()));
						priceStatus = xInvExportCaseStatus.getCaseStatus();
					}else if(xInvImportList != null && xInvImportList.size() > 0){
						XInvImport xInvImport = xInvImportList.get(0);
						XInvImportCaseStatus xInvInvImportCaseStatus = iXInvImportCaseStatusDao.findOne(new XInvImportCaseStatusPK(xInvImport.getId().getUniqueKey(), xInvImport.getId().getSeq(), xInvImport.getId().getFileKey()));
						priceStatus = xInvInvImportCaseStatus.getCaseStatus();
					}
					billLadingsInvoiceStatusOutputBean = new BillLadingsInvoiceStatusOutputBean(nameCheckRecordMain, nameCheckRecordDetailList);
					setNcCaseStatusnCloseReason(nameCheckRecordMain.getCaseRk(), nameCheckRecordMain.getNcCloseReason(), billLadingsInvoiceStatusOutputBean, ncStatus, priceStatus);
					billLadingsInvoiceStatusOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SUCCESS);
					billLadingsInvoiceStatusOutputBean.setErrorMessage("");
				}else{
					billLadingsInvoiceStatusOutputBean = new BillLadingsInvoiceStatusOutputBean();
					billLadingsInvoiceStatusOutputBean.setInterfaceName(interfaceName);
					billLadingsInvoiceStatusOutputBean.setNcReferenceId("");
					billLadingsInvoiceStatusOutputBean.setUniqueKey(uniqueKey);
					billLadingsInvoiceStatusOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_NODATAFOUND);
					billLadingsInvoiceStatusOutputBean.setErrorMessage("");
					billLadingsInvoiceStatusOutputBean.setRouteRule("");
					billLadingsInvoiceStatusOutputBean.setNcCaseId("");
					billLadingsInvoiceStatusOutputBean.setNcCaseStatus("");
					billLadingsInvoiceStatusOutputBean.setNcCloseReason("");
//					billLadingsInvoiceStatusOutputBean.setPepFlag("");
//					billLadingsInvoiceStatusOutputBean.setPnmpFlag("");
				}
			}
		}catch(Exception e){
			logger.error(String.format("amlBLInvStatus fail exception : %s", e.getMessage()), e);
			billLadingsInvoiceStatusOutputBean = new BillLadingsInvoiceStatusOutputBean();
			billLadingsInvoiceStatusOutputBean.setInterfaceName(interfaceName);
			billLadingsInvoiceStatusOutputBean.setNcReferenceId("");
			billLadingsInvoiceStatusOutputBean.setUniqueKey(uniqueKey);
			billLadingsInvoiceStatusOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SYSTEMERR);
			billLadingsInvoiceStatusOutputBean.setErrorMessage(e.getMessage());
			billLadingsInvoiceStatusOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
			billLadingsInvoiceStatusOutputBean.setRouteRule("");
			billLadingsInvoiceStatusOutputBean.setNcCaseId("");
			billLadingsInvoiceStatusOutputBean.setNcCaseStatus("");
			billLadingsInvoiceStatusOutputBean.setNcCloseReason("");
//			billLadingsInvoiceStatusOutputBean.setPepFlag("");
//			billLadingsInvoiceStatusOutputBean.setPnmpFlag("");
		}
		
		logger.info("billLadingsInvoiceStatus output : " + billLadingsInvoiceStatusOutputBean.toString());
		return billLadingsInvoiceStatusOutputBean;
	}
	
	/**
	 * 設定Trade Finance的案件調查狀態及結案原因
	 * @param caseRk
	 * @param ncCloseReason
	 * @param bean
	 */
	private void setNcCaseStatusnCloseReason(long caseRk, String ncCloseReason, BillLadingsInvoiceStatusOutputBean bean, String ncStatus, String priceStatus){
		if(caseRk != 0){
			CaseLive caseLive = iCaseLiveDao.findOne(new BigDecimal(caseRk));
			String caseStatus = caseLive.getCaseStatusCd();
			
			if(ncStatus == null || priceStatus == null){
				//只要其中一個案件未結案，回覆NC_RESULT_TF_NOT_ALL_READY
				bean.setNcResult(SwiftMtConst.NC_RESULT_TF_NOT_ALL_READY);
			}else if(SwiftMtConst.NC_RESULT_FAIL.equalsIgnoreCase(ncStatus) || SwiftMtConst.TF_CASESTATUS_N.equalsIgnoreCase(priceStatus)){
				//只要其中一個案件不放行，回覆NC_RESULT_FAIL
				bean.setNcResult(SwiftMtConst.NC_RESULT_FAIL);
			}else if(SwiftMtConst.NC_RESULT_PASS.equalsIgnoreCase(ncStatus) && SwiftMtConst.TF_CASESTATUS_Y.equalsIgnoreCase(priceStatus)){
				//只要其中一個案件不放行，回覆NC_RESULT_FAIL
				bean.setNcResult(SwiftMtConst.NC_RESULT_PASS);
			}
			
			//名單案件調查結果 note:需注意是否加入查價案件結果判斷
			//12/01 discuss with nova, do nothing to this for now.
			if(SwiftMtConst.NC_CASE_STATUS_CLOSED.equalsIgnoreCase(caseStatus)){
				if("True Hit".equalsIgnoreCase(ncCloseReason)){
					ncCloseReason = SwiftMtConst.NC_RESULT_TRUE_HIT;
				}else if("False Hit".equalsIgnoreCase(ncCloseReason)){
					ncCloseReason = SwiftMtConst.NC_RESULT_FALSE_HIT;
				}else{
					ncCloseReason = "";
				}
			}else{
				ncCloseReason = "";
			}
			
			bean.setNcCaseStatus(caseStatus);
			bean.setNcCloseReason(ncCloseReason);
		}
	}
	
	/**
	 * Input 輸入欄位資訊檢查
	 * @param ncReferenceId
	 * @param uniqueKey
	 * @return boolean
	 */
	private boolean chkScanFields(String interfaceName, String ncReferenceId, String uniqueKey){
		boolean result = false;
		if(StringUtils.isEmpty(interfaceName) || !SwiftMtConst.INTERFACE_TYPE_BLINVSTATUS.equals(interfaceName)){
			
		}
		if(StringUtils.isEmpty(ncReferenceId) && StringUtils.isEmpty(uniqueKey)){
			result = true;
		}
		return result;
	}
}
