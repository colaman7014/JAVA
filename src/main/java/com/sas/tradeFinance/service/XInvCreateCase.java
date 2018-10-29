package com.sas.tradeFinance.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.ICaseConfigDao;
import com.sas.db.aml.dao.ecm.ICaseConfigXUserGroupDao;
import com.sas.db.aml.dao.ecm.ICaseLiveDao;
import com.sas.db.aml.dao.ecm.ICaseRkSeqDao;
import com.sas.db.aml.dao.ecm.ICaseUdfCharValueDao;
import com.sas.db.aml.dao.ecm.ICaseVersionDao;
import com.sas.db.aml.dao.ecm.ICaseXUserGroupDao;
import com.sas.db.aml.orm.ecm.CaseConfig;
import com.sas.db.aml.orm.ecm.CaseConfigXUserGroup;
import com.sas.db.aml.orm.ecm.CaseLive;
import com.sas.db.aml.orm.ecm.CaseUdfCharValue;
import com.sas.db.aml.orm.ecm.CaseUdfCharValuePK;
import com.sas.db.aml.orm.ecm.CaseVersion;
import com.sas.db.aml.orm.ecm.CaseVersionPK;
import com.sas.db.aml.orm.ecm.CaseXUserGroup;
import com.sas.db.aml.orm.ecm.CaseXUserGroupPK;
import com.sas.db.wlf.dao.tf.IXInvExportCaseItemDao;
import com.sas.db.wlf.dao.tf.IXInvExportCaseStatusDao;
import com.sas.db.wlf.dao.tf.IXInvExportDao;
import com.sas.db.wlf.dao.tf.IXInvImportCaseItemDao;
import com.sas.db.wlf.dao.tf.IXInvImportCaseStatusDao;
import com.sas.db.wlf.dao.tf.IXInvImportDao;
import com.sas.db.wlf.orm.tf.XInvExport;
import com.sas.db.wlf.orm.tf.XInvExportCaseItem;
import com.sas.db.wlf.orm.tf.XInvExportCaseItemPK;
import com.sas.db.wlf.orm.tf.XInvExportCaseStatus;
import com.sas.db.wlf.orm.tf.XInvExportCaseStatusPK;
import com.sas.db.wlf.orm.tf.XInvImport;
import com.sas.db.wlf.orm.tf.XInvImportCaseItem;
import com.sas.db.wlf.orm.tf.XInvImportCaseItemPK;
import com.sas.db.wlf.orm.tf.XInvImportCaseStatus;
import com.sas.db.wlf.orm.tf.XInvImportCaseStatusPK;
import com.sas.util.DateUtil;

@Service
public class XInvCreateCase {
	
	private static Logger logger = LoggerFactory.getLogger(XInvCreateCase.class);
	
	@Autowired
	IXInvExportDao  iXInvExportDao;
	
	@Autowired
	IXInvImportDao iXInvImportDao;
	
	@Autowired
	IXInvImportCaseStatusDao iXInvImportCaseStatusDao;
	
	@Autowired
	IXInvExportCaseStatusDao iXInvExportCaseStatusDao;
	
	@Autowired
	ICaseRkSeqDao iCaseRkSeqDao;
	
	@Autowired
	ICaseLiveDao iCaseLiveDao;
	
	@Autowired
	ICaseVersionDao iCaseVersionDao;
	
	@Autowired
	ICaseConfigDao iCaseConfigDao;
	
	@Autowired
	ICaseXUserGroupDao iCaseXUserGroupDao;
	
	@Autowired
	ICaseUdfCharValueDao iCaseUdfCharValueDao;
	
	@Autowired
	ICaseConfigXUserGroupDao iCaseConfigXUserGroupDao; 
	
	@Autowired
	IXInvImportCaseItemDao iXInvImportCaseItemDao;
	
	@Autowired
	IXInvExportCaseItemDao iXInvExportCaseItemDao;
	
	
	
	
	/**
	 * 建立進口發票案件
	 * @param importBean
	 * @return
	 */
	public BigDecimal createInvImportCase(XInvImport importBean) {
		XInvImportCaseStatus invImportCase = iXInvImportCaseStatusDao.findByInvoiceNoAndIdFileKey(importBean.getInvoiceNo(), importBean.getId().getFileKey());
		if(invImportCase != null) {
			//如果已經建過案了 就直接回傳caseRk
			return invImportCase.getCaseRk();
		} else {
			
			//取得CASERK
			BigDecimal caseRk = iCaseRkSeqDao.getCaseRkSeqNext();
			caseRk = caseRk.setScale(0);
			Date nowDate = new Date(); 
			CaseConfig caseConfig = iCaseConfigDao.findByCaseTypeCdAndCaseCategoryCdAndCaseSubcategoryCd(SwiftMtConst.NCSC_CASE_TYPE, SwiftMtConst.SOURCE_TYPE_OL_PRICE, SwiftMtConst.SUB_CATEGORY_TYPE_IV_IM);
			//找到當初設定可以使用的User群組
			List<CaseConfigXUserGroup> caseUserGroups = iCaseConfigXUserGroupDao.findByIdCaseConfigSeqNo(caseConfig.getCaseConfigSeqNo());
			String caseDesc = String.format(SwiftMtConst.INV_IMPORT_CASE_DESC_TPL, importBean.getInvoiceNo());
			
			//建立CASE LIVE
			CaseLive caseLive = newCaseLive(caseRk, caseConfig, nowDate, caseDesc, importBean.getCreateUser());
			//建立CASE VERSION
			newCaseVersion(caseLive);
			//建立當件案件可用使用者群組
			newCaseUserConfig(caseRk, caseUserGroups);
			//建立CASE UDF
			setInvImportCaseCharUdf(caseRk, nowDate, importBean);
			
			List<XInvImport> invoiceItems = iXInvImportDao.findByIdFileKeyAndScrNoAndTypeAndLCNoAndIbNoAndInvoiceNo(
					importBean.getId().getFileKey(), importBean.getScrNo(), importBean.getType(), importBean.getlCNo(), importBean.getIbNo(), importBean.getInvoiceNo());
			List<XInvImport> allMatchItems = new ArrayList<XInvImport>();
			List<XInvImport> matchItems = null;
			logger.debug("invoice item count: {}", invoiceItems.size());
			for(XInvImport item: invoiceItems) {
				//如果HkHsCode沒有就拿Category of Goods找最近20筆資料
				if(StringUtils.isNotBlank(item.getHkHsCode())) {
					matchItems = iXInvImportDao.findTop20ByHkHsCodeAndIdUniqueKeyNotAndInvoiceNoNotAndCreateDttmLessThanEqualOrderByCreateDttmDesc(
							item.getHkHsCode(), importBean.getId().getUniqueKey(), importBean.getInvoiceNo(), importBean.getCreateDttm());
					
				} else if(StringUtils.isNotBlank(item.getCategoryOfGoods())){
					matchItems = iXInvImportDao.findTop20ByCategoryOfGoodsAndIdUniqueKeyNotAndInvoiceNoNotAndCreateDttmLessThanEqualOrderByCreateDttmDesc(
							item.getCategoryOfGoods(), importBean.getId().getUniqueKey(), importBean.getInvoiceNo(), importBean.getCreateDttm());
				}
				allMatchItems.add(item);
				if(matchItems != null && matchItems.size() > 0 ) {
					allMatchItems.addAll(matchItems);
				}
			}
			
			//建立命中的ITEM
			XInvImportCaseItem caseItem = null;
			XInvImportCaseItemPK caseItemPk = null;
			List<XInvImportCaseItem> allCaseItem = new ArrayList<XInvImportCaseItem>();
			for(XInvImport matchItem : allMatchItems) {
				caseItem = new XInvImportCaseItem();
				caseItemPk = new XInvImportCaseItemPK();
				BeanUtils.copyProperties(matchItem, caseItem);
				BeanUtils.copyProperties(matchItem.getId(), caseItemPk);
				caseItem.setId(caseItemPk);
				caseItem.setCaseRk(caseRk);
				allCaseItem.add(caseItem);
			}
			iXInvImportCaseItemDao.batchSave(allCaseItem);
			
			//紀錄CASE
			XInvImportCaseStatus status = new XInvImportCaseStatus();
			XInvImportCaseStatusPK statusPk = new XInvImportCaseStatusPK();
			BeanUtils.copyProperties(importBean.getId(), statusPk);
			BeanUtils.copyProperties(importBean, status);
			status.setId(statusPk);
			status.setCaseRk(caseRk);
			status.setCreateDttm(new Timestamp(nowDate.getTime()));
			iXInvImportCaseStatusDao.save(status);
			return caseRk;
		}
		
	}
	
	/**
	 * 
	 * @param exportCaseBean
	 * @return
	 */
	public BigDecimal createInvExportCase(XInvExport exportCaseBean) {
		XInvExportCaseStatus exportCase = iXInvExportCaseStatusDao.findByInvoiceNoAndIdFileKey(exportCaseBean.getInvoiceNo(), exportCaseBean.getId().getFileKey());
		//如果已經建過案了 就直接回傳caseRk
		if(exportCase != null) {
			return exportCase.getCaseRk();
		}  else {
			//取得CASERK
			BigDecimal caseRk = iCaseRkSeqDao.getCaseRkSeqNext();
			caseRk = caseRk.setScale(0);
			Date nowDate = new Date(); 
			CaseConfig caseConfig = iCaseConfigDao.findByCaseTypeCdAndCaseCategoryCdAndCaseSubcategoryCd(SwiftMtConst.NCSC_CASE_TYPE, SwiftMtConst.SOURCE_TYPE_OL_PRICE, SwiftMtConst.SUB_CATEGORY_TYPE_IV_EX);
			//找到當初設定可以使用的User群組
			List<CaseConfigXUserGroup> caseUserGroups = iCaseConfigXUserGroupDao.findByIdCaseConfigSeqNo(caseConfig.getCaseConfigSeqNo());
			String caseDesc = String.format(SwiftMtConst.INV_EXPORT_CASE_DESC_TPL, exportCaseBean.getInvoiceNo());
			
			//建立CASE LIVE
			CaseLive caseLive = newCaseLive(caseRk, caseConfig, nowDate, caseDesc, exportCaseBean.getCreateUser());
			//建立CASE VERSION
			newCaseVersion(caseLive);
			//建立當件案件可用使用者群組
			newCaseUserConfig(caseRk, caseUserGroups);
			//建立CASE UDF
			setInvExportCaseCharUdf(caseRk, nowDate, exportCaseBean);
			
			List<XInvExport> invoiceItems = iXInvExportDao.findByIdFileKeyAndScrNoAndCurrnecyAndOurRefNoAndSeqNoAndInvoiceNo(
					 exportCaseBean.getId().getFileKey(), exportCaseBean.getScrNo(), exportCaseBean.getCurrnecy(), exportCaseBean.getOurRefNo(), 
					 exportCaseBean.getSeqNo(), exportCaseBean.getInvoiceNo());
			List<XInvExport> allMatchItems = new ArrayList<XInvExport>();
			List<XInvExport> matchItems = null;
			for(XInvExport item : invoiceItems) {
				//如果HkHsCode沒有就拿Category of Goods找最近20筆資料
				if(StringUtils.isNotBlank(item.getHkHsCode())) {
					matchItems  = iXInvExportDao.findTop20ByHkHsCodeAndIdUniqueKeyNotAndInvoiceNoNotAndCreateDttmLessThanEqualOrderByCreateDttmDesc(
							item.getHkHsCode(), exportCaseBean.getId().getUniqueKey(), exportCaseBean.getInvoiceNo(), exportCaseBean.getCreateDttm());
				} else if(StringUtils.isNotBlank(item.getCategoryOfGoods())){
					matchItems = iXInvExportDao.findTop20ByCategoryOfGoodsAndIdUniqueKeyNotAndInvoiceNoNotAndCreateDttmLessThanEqualOrderByCreateDttmDesc(
							item.getCategoryOfGoods(), exportCaseBean.getId().getUniqueKey(),  exportCaseBean.getInvoiceNo(), exportCaseBean.getCreateDttm());
				}
				allMatchItems.add(item);
				if(matchItems != null && matchItems.size() > 0 ) {
					allMatchItems.addAll(matchItems);
				}
				
			}
			
			//建立命中的ITEM
			List<XInvExportCaseItem> allCaseItems = new ArrayList<XInvExportCaseItem>();
			XInvExportCaseItem caseItem = null;
			XInvExportCaseItemPK caseItemPk = null;
			for(XInvExport matchItem : allMatchItems) {
				caseItem = new XInvExportCaseItem();
				caseItemPk = new XInvExportCaseItemPK();
				BeanUtils.copyProperties(matchItem, caseItem);
				BeanUtils.copyProperties(matchItem.getId(), caseItemPk);
				caseItem.setId(caseItemPk);
				caseItem.setCaseRk(caseRk);
				allCaseItems.add(caseItem);
			}
			iXInvExportCaseItemDao.batchSave(allCaseItems);
			
			//紀錄CASE
			XInvExportCaseStatus status = new XInvExportCaseStatus();
			XInvExportCaseStatusPK statusPk = new XInvExportCaseStatusPK();
			BeanUtils.copyProperties(exportCaseBean.getId(), statusPk);
			BeanUtils.copyProperties(exportCaseBean, status);
			status.setId(statusPk);
			status.setCaseRk(caseRk);
			status.setCreateDttm(new Timestamp(nowDate.getTime()));
			iXInvExportCaseStatusDao.save(status);
			return caseRk;
		}
	}
	
	public Map<String, List<XInvImportCaseItem>> getInvImportCaseItemByCaseRk(BigDecimal caseRk) {
		Map<String, List<XInvImportCaseItem>> result = new HashMap<String, List<XInvImportCaseItem>>();
		logger.info("case Rk: {}", caseRk); 
		List<XInvImportCaseItem> caseItems = iXInvImportCaseItemDao.findByCaseRkOrderByCreateDttmDescHkHsCodeAscCategoryOfGoodsAsc(caseRk);
		List<XInvImportCaseItem> listByKey = null;
		String key = "";
		logger.debug("Query caseItems size: {}", caseItems.size());
		for(XInvImportCaseItem item : caseItems) {
			if(StringUtils.isNotBlank(item.getHkHsCode())) {
				key = item.getHkHsCode();
			} else if(StringUtils.isNotBlank(item.getCategoryOfGoods())) {
				key = item.getCategoryOfGoods();
			}
			
			if(result.get(key) == null) {
				listByKey = new ArrayList<XInvImportCaseItem>();
			} else {
				listByKey = result.get(key);
			}
			listByKey.add(item);
			result.put(key, listByKey);
		}
		return result;
	}
	
	public Map<String, List<XInvExportCaseItem>> getInvExportCaseItemByCaseRk(BigDecimal caseRk) {
		Map<String, List<XInvExportCaseItem>> result = new HashMap<String, List<XInvExportCaseItem>>();
		List<XInvExportCaseItem> caseItems = iXInvExportCaseItemDao.findByCaseRkOrderByCreateDttmDescHkHsCodeAscCategoryOfGoodsAsc(caseRk);
		List<XInvExportCaseItem> listByKey = null;
		String key = "";
		for(XInvExportCaseItem item : caseItems) {
			if(StringUtils.isNotBlank(item.getHkHsCode())) {
				key = item.getHkHsCode();
			} else if(StringUtils.isNotBlank(item.getCategoryOfGoods())) {
				key = item.getCategoryOfGoods();
			}
			
			if(result.get(key) == null) {
				listByKey = new ArrayList<XInvExportCaseItem>();
			} else {
				listByKey = result.get(key);
			}
			listByKey.add(item);
			result.put(key, listByKey);
		}
		return result;
	}
	
	

	/**
	 * 建立Case Live
	 * @param caseRK
	 * @param caseConfig
	 * @param nowDate
	 * @param desc
	 * @param createuser
	 * @return
	 */
	private CaseLive newCaseLive(BigDecimal caseRK, CaseConfig caseConfig, Date nowDate, String desc, String createuser) {
	
		String caseIdFormat = "%s-%s";
		CaseLive caseLive = new CaseLive();
		caseLive.setCaseRk(caseRK);
		caseLive.setCaseCategoryCd(caseConfig.getCaseCategoryCd());
		caseLive.setCaseSubcategoryCd(caseConfig.getCaseSubcategoryCd());
		caseLive.setCaseTypeCd(caseConfig.getCaseTypeCd());
		caseLive.setCaseId(String.format(caseIdFormat, DateUtil.getNowWestYr(nowDate), caseRK.toString()));
		caseLive.setValidFromDttm(new Timestamp(nowDate.getTime()));
		caseLive.setCaseDesc(desc);
		caseLive.setCreateUserId(createuser);
		caseLive.setCreateDttm(new Timestamp(nowDate.getTime()));
		caseLive.setUiDefFileNm(caseConfig.getUiDefFileNm());
		caseLive.setVersionNo(1);
		caseLive.setRegulatoryRptRqdFlg("0"); 
		caseLive.setDeleteFlg("0");
		caseLive.setInvestigatorUserId(createuser);
		caseLive.setSourceSystemCd(SwiftMtConst.NCSC_CASE_SOURCE_SYSTEM);
		iCaseLiveDao.save(caseLive);
		return caseLive;
	}
	
	private void newCaseUserConfig(BigDecimal caseRk, List<CaseConfigXUserGroup> caseUserGroups) {
		
		CaseXUserGroup userGroup  = null;
		CaseXUserGroupPK pk = null;
		for(CaseConfigXUserGroup configUserGroup : caseUserGroups) {
			userGroup = new CaseXUserGroup();
			pk = new CaseXUserGroupPK();
			userGroup.setId(pk);
			pk.setCaseRk(caseRk);
			pk.setUserGroupNm(configUserGroup.getId().getUserGroupNm());
			iCaseXUserGroupDao.save(userGroup);
		}
	}
	
	/**
	 * 建立Case Version
	 * @param caseLive
	 * @return
	 */
	private CaseVersion newCaseVersion(CaseLive caseLive) {
		CaseVersion caseVersion = new CaseVersion(); 
		CaseVersionPK pk = new CaseVersionPK();
		caseVersion.setId(pk);
		
		pk.setCaseRk(caseLive.getCaseRk());
		pk.setValidFromDttm(caseLive.getValidFromDttm());
		caseVersion.setCaseCategoryCd(caseLive.getCaseCategoryCd());
		caseVersion.setCaseDesc(caseLive.getCaseDesc());
		caseVersion.setCaseId(caseLive.getCaseId());
		caseVersion.setCaseTypeCd(caseLive.getCaseTypeCd());
		caseVersion.setCreateDttm(caseLive.getCreateDttm());
		caseVersion.setCreateUserId(caseLive.getCreateUserId());
		caseVersion.setDeleteFlg(caseLive.getDeleteFlg());
		caseVersion.setUiDefFileNm(caseLive.getUiDefFileNm());
		caseVersion.setRegulatoryRptRqdFlg(caseLive.getRegulatoryRptRqdFlg());
		caseVersion.setSourceSystemCd(caseLive.getSourceSystemCd());
		caseVersion.setVersionNo(1);
		
		
		iCaseVersionDao.save(caseVersion);
		return caseVersion;
	}
	
	/**
	 * 建立進口發票Case Char Udf
	 * @param caseRK
	 * @param nowDate
	 * @param importBean
	 */
	private void setInvImportCaseCharUdf(BigDecimal caseRK, Date nowDate, XInvImport importBean) {
		List<CaseUdfCharValue> charUdfValue = new ArrayList<CaseUdfCharValue>();
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_IMPORT_UNIQUE_KEY", importBean.getId().getUniqueKey()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_IMPORT_SEQ", importBean.getId().getSeq()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_IMPORT_FILE_KEY", importBean.getId().getFileKey()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_IMPORT_SCR_NO", importBean.getScrNo()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_IMPORT_TYPE", importBean.getType()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_IMPORT_IB_NO", importBean.getIbNo()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_IMPORT_LC_NO", importBean.getlCNo()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_IMPORT_INVOICE_NO", importBean.getInvoiceNo()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_IMPORT_APPLICANT", importBean.getApplicant()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_NCSC_SOURCE_TYPE", SwiftMtConst.SOURCE_TYPE_OL_INV));
		iCaseUdfCharValueDao.save(charUdfValue);
	}
	
	/**
	 * 建立出口發票Case Char Udf
	 * @param caseRK
	 * @param nowDate
	 * @param exportBean
	 */
	private void setInvExportCaseCharUdf(BigDecimal caseRK, Date nowDate, XInvExport exportBean) {
		List<CaseUdfCharValue> charUdfValue = new ArrayList<CaseUdfCharValue>();
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_EXPORT_UNIQUE_KEY", exportBean.getId().getUniqueKey()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_EXPORT_SEQ", exportBean.getId().getSeq()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_EXPORT_FILE_KEY", exportBean.getId().getFileKey()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_EXPORT_SCR_NO", exportBean.getScrNo()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_EXPORT_CURRENCY", exportBean.getCurrnecy()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_EXPORT_OUR_REF_NO", exportBean.getOurRefNo()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_EXPORT_SEQ_NO", exportBean.getSeqNo()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_EXPORT_INVOICE_NO", exportBean.getInvoiceNo()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_INV_EXPORT_APPLICANT", exportBean.getApplicant()));
		charUdfValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_NCSC_SOURCE_TYPE", SwiftMtConst.SOURCE_TYPE_OL_INV));
		iCaseUdfCharValueDao.save(charUdfValue);
	}
	
	/**
	 * 建立Char Udf
	 * @param caseRK
	 * @param nowDate
	 * @param rowNo
	 * @param udfNm
	 * @param value
	 * @return
	 */
	private CaseUdfCharValue toCharUdfValue(BigDecimal caseRK, Date nowDate, int rowNo, String udfNm,  Object value) {
		CaseUdfCharValue udf = new CaseUdfCharValue();
		CaseUdfCharValuePK udfPK = new CaseUdfCharValuePK();
		udfPK.setCaseRk(caseRK.longValue());
		udfPK.setValidFromDttm(nowDate);
		udfPK.setRowNo(rowNo);
		udfPK.setUdfTableName(SwiftMtConst.NCSC_CASE_UDF_TABLE_NAME);
		udfPK.setUdfName(udfNm);
		udf.setId(udfPK);
		udf.setUdfValue(value == null ? null : value.toString());
		return udf;
	}
	
}
