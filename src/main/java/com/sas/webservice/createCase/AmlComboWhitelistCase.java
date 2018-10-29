package com.sas.webservice.createCase;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.ICaseConfigDao;
import com.sas.db.aml.dao.ecm.ICaseConfigXUserGroupDao;
import com.sas.db.aml.dao.ecm.ICaseLiveDao;
import com.sas.db.aml.dao.ecm.ICaseRkSeqDao;
import com.sas.db.aml.dao.ecm.ICaseUdfCharValueDao;
import com.sas.db.aml.dao.ecm.ICaseVersionDao;
import com.sas.db.aml.dao.ecm.ICaseXUserGroupDao;
import com.sas.db.aml.dao.fcfcore.IXComboWhitelistDao;
import com.sas.db.aml.dao.fcfcore.IXComboWhitelistHisDao;
import com.sas.db.aml.dao.fcfcore.IXComboWhitelistMainDao;
import com.sas.db.aml.orm.ecm.CaseConfig;
import com.sas.db.aml.orm.ecm.CaseConfigXUserGroup;
import com.sas.db.aml.orm.ecm.CaseLive;
import com.sas.db.aml.orm.ecm.CaseUdfCharValue;
import com.sas.db.aml.orm.ecm.CaseUdfCharValuePK;
import com.sas.db.aml.orm.ecm.CaseVersion;
import com.sas.db.aml.orm.ecm.CaseVersionPK;
import com.sas.db.aml.orm.ecm.CaseXUserGroup;
import com.sas.db.aml.orm.ecm.CaseXUserGroupPK;
import com.sas.db.aml.orm.fcfcore.XComboWhitelist;
import com.sas.db.aml.orm.fcfcore.XComboWhitelistHis;
import com.sas.db.aml.orm.fcfcore.XComboWhitelistMain;
import com.sas.db.aml.orm.fcfcore.XComboWhitelistPK;
import com.sas.db.wlf.dao.IXNcscCaseResultDao;
import com.sas.db.wlf.dao.nc.INameHitRecordDao;
import com.sas.db.wlf.dao.sc.ISwiftHitRecordDao;
import com.sas.db.wlf.orm.XNcscCaseResult;
import com.sas.db.wlf.orm.nc.NameHitRecord;
import com.sas.db.wlf.orm.nc.NameHitRecordPK;
import com.sas.db.wlf.orm.sc.SwiftHitRecord;
import com.sas.db.wlf.orm.sc.SwiftHitRecordPK;
import com.sas.util.DateUtil;
import com.sas.util.StringUtils;

/**
 * 交易組合白名單建立案件
 * @author sas
 *
 */
@Service
public class AmlComboWhitelistCase {
	
	private static Logger logger = LoggerFactory.getLogger(AmlComboWhitelistCase.class);

	@Autowired
	IXComboWhitelistMainDao xComboWhitelistMainDao;
	
	@Autowired
	IXNcscCaseResultDao iXNcscCaseResultDao;
	
	@Autowired
	INameHitRecordDao iNameHitRecordDao;
	
	@Autowired
	ISwiftHitRecordDao iSwiftHitRecordDao; 
	
	@Autowired
	IXComboWhitelistMainDao iXComboWhitelistMainDao; 
	
	@Autowired 
	IXComboWhitelistHisDao iXComboWhitelistHisDao;
	
	@Autowired
	IXComboWhitelistDao iXComboWhitelistDao;
	
	@Autowired
	ICaseUdfCharValueDao iCaseUdfCharValueDao;
	
	@Autowired
	ICaseRkSeqDao iCaseRkSeqDao;
	
	@Autowired
	ICaseLiveDao iCaseLiveDao;
	
	@Autowired
	ICaseXUserGroupDao iCaseXUserGroupDao;
	
	@Autowired
	ICaseVersionDao iCaseVersionDao;
	
	@Autowired
	ICaseConfigDao caseConfigDao;
	
	@Autowired
	ICaseConfigXUserGroupDao iCaseConfigXUserGroupDao; 
	
	
	/**
	 * 建立NAME CHECK交易組合白名單邏輯入口
	 */
	public void createHitComboWhiteListCaseNC(Long screenCaseRk, String partyNo, String branchNo) {
		String caseIdFormat = "%s-%s";
		List<XNcscCaseResult> caseResultLst = iXNcscCaseResultDao.findByIdCaseRk(screenCaseRk);
		CaseLive screenCase = iCaseLiveDao.findOne(new BigDecimal(screenCaseRk));
		NameHitRecord hit = null; 
		XComboWhitelistMain whiteList = null;
		for(XNcscCaseResult caseResult : caseResultLst) {
			if(SwiftMtConst.COMBO_WHITE_LIST_Y.equals(caseResult.getWhitelistInd())) {
				hit = findNameHit(caseResult);
				whiteList = findComboWhiteList(partyNo, String.valueOf(hit.getEntityWatchListKey()));
				if(!isCanCreate(whiteList)) continue;
				BigDecimal caseRk= iCaseRkSeqDao.getCaseRkSeqNext().setScale(0);  //取得CaseRK
				Date nowDate = new Date();
				String caseId = String.format(caseIdFormat, DateUtil.getNowWestYr(nowDate), caseRk.toString());
				whiteList = saveNewXComboWhitelistMainByNc(caseId, partyNo, branchNo, hit, screenCase.getCreateUserId());
				createCase(caseRk, nowDate, screenCase.getCreateUserId(), whiteList, SwiftMtConst.COMBO_WHITE_LIST_ACTION_NEW, SwiftMtConst.COMBO_WHITE_LIST_CASE_TYPE);
			}
		}
	}
	
	/**
	 * 建立SWIFT CHECK交易組合白名單邏輯入口
	 */
	public void createHitComboWhiteListCaseSC(Long screenCaseRk, String partyNo, String branchNo) {
		String caseIdFormat = "%s-%s";
		List<XNcscCaseResult> caseResultLst = iXNcscCaseResultDao.findByIdCaseRk(screenCaseRk);
		CaseLive screenCase = iCaseLiveDao.findOne(new BigDecimal(screenCaseRk));
		SwiftHitRecord hit = null; 
		XComboWhitelistMain whiteList = null;
		for(XNcscCaseResult caseResult : caseResultLst) {
			if(SwiftMtConst.COMBO_WHITE_LIST_Y.equals(caseResult.getWhitelistInd())) {
				hit = findSwiftHit(caseResult);
				whiteList = findComboWhiteList(partyNo, String.valueOf(hit.getEntityWatchListKey()));
				if(!isCanCreate(whiteList)) continue;
				BigDecimal caseRk= iCaseRkSeqDao.getCaseRkSeqNext().setScale(0);  //取得CaseRK
				Date nowDate = new Date();
				String caseId = String.format(caseIdFormat, DateUtil.getNowWestYr(nowDate), caseRk.toString());
				whiteList = saveNewXComboWhitelistMainBySC(caseId, partyNo, branchNo, hit, screenCase.getCreateUserId());
				logger.info("WhiteList GetBranch ::: " + whiteList.getBranch());
				createCase(caseRk, nowDate, screenCase.getCreateUserId(), whiteList, SwiftMtConst.COMBO_WHITE_LIST_ACTION_NEW, SwiftMtConst.COMBO_WHITE_LIST_CASE_TYPE);
			}
		}
	}
	
	/**
	 * 停用白名單邏輯入口
	 * 前端已經建立CASE不用再建立CASE
	 * @param caseId
	 */
	public void createStopComboWhiteListCase(String caseId, String userId) {
		Long caseRk = Long.valueOf(caseId.split("-")[1]);
		String partyNo = findCharUdfValue(caseRk, "X_COMBO_WHITE_LIST_PARTY_NO");
		String watchListKey = findCharUdfValue(caseRk, "X_COMBO_WHITE_LIST_ENTITY_KEY");
		XComboWhitelistMain oldMain = findComboWhiteList(partyNo, watchListKey);
		if(oldMain != null) {
			copyWhiteListToHis(oldMain);
		}

		XComboWhitelistMain main = new XComboWhitelistMain();		
		main.setCaseId(caseId);
		main.setCreateDate(new Date());
		main.setEntityDisplayName(findCharUdfValue(caseRk, "X_COMBO_WHITE_LIST_ENTITY_NAME"));
		main.setCaseAction(findCharUdfValue(caseRk, "X_COMBO_WHITE_LIST_ACTION"));
		main.setBeneficiaryEntityWatchListKey(watchListKey);
		main.setBranch(findCharUdfValue(caseRk, "X_COMBO_WHITE_LIST_BRANCH_NO"));
		main.setPartyNumber(partyNo);
		main.setCreateUser(userId);
		iXComboWhitelistMainDao.save(main);
	}
	
	/**
	 * 案件結案
	 * @param caseId
	 */
	public void closeCase(String caseId) {
		Long caseRk = Long.valueOf(caseId.split("-")[1]);
		CaseLive caseLive = iCaseLiveDao.findOne(new BigDecimal(caseRk));
		XComboWhitelistMain main = iXComboWhitelistMainDao.findByCaseId(caseId);
		main.setClosedInd(SwiftMtConst.COMBO_WHITE_LIST_CLOSE_Y);
		main.setCaseDisposition(caseLive.getCaseDispositionCd());
		iXComboWhitelistMainDao.save(main);
		
		XComboWhitelist whiteList = new XComboWhitelist();
		XComboWhitelistPK pk = new XComboWhitelistPK();
		whiteList.setId(pk);
		BeanUtils.copyProperties(main, pk);
		BeanUtils.copyProperties(main, whiteList);
		if(SwiftMtConst.COMBO_WHITE_LIST_APPROVAL.equals(main.getCaseDisposition())) {
			if(SwiftMtConst.COMBO_WHITE_LIST_ACTION_NEW.equals(main.getCaseAction())) {
				whiteList.setChangeCurrentInd(SwiftMtConst.COMBO_WHITE_LIST_Y);
			} else {
				whiteList.setChangeCurrentInd(SwiftMtConst.COMBO_WHITE_LIST_N);
			}
			whiteList.setCaseId(caseId);
			iXComboWhitelistDao.save(whiteList);
		}
	}
	
	
	/**
	 * 找到對應的Name hit record
	 * @param caseResult
	 * @return
	 */
	public NameHitRecord findNameHit(XNcscCaseResult caseResult) {
		NameHitRecordPK pk = new NameHitRecordPK();
		BeanUtils.copyProperties(caseResult.getId(), pk);
		BeanUtils.copyProperties(caseResult, pk);
		NameHitRecord hitRecord = iNameHitRecordDao.findOne(pk);
		return hitRecord;
	}
	
	/**
	 * 找到對應的Name hit record
	 * @param caseResult
	 * @return
	 */
	public SwiftHitRecord findSwiftHit(XNcscCaseResult caseResult) {
		SwiftHitRecordPK pk = new SwiftHitRecordPK();
		pk.setUniqueKey(caseResult.getId().getUniqueKey());
		pk.setReferenceId(caseResult.getId().getNcReferenceId());
		pk.setSeq(String.valueOf(caseResult.getId().getSeq()));
		SwiftHitRecord hitRecord = iSwiftHitRecordDao.findOne(pk);
		return hitRecord;
	}
	
	/**
	 * 找到目前存在的交易組合白名單
	 * @param partyNo
	 * @param entityWatchListKey
	 * @return
	 */
	public XComboWhitelistMain findComboWhiteList(String partyNo, String entityWatchListKey) {
		logger.info("partyNo: {}, entityWatchListKey:{}", partyNo, entityWatchListKey);
		XComboWhitelistMain whiteList = iXComboWhitelistMainDao.findByPartyNumberAndBeneficiaryEntityWatchListKey(partyNo, entityWatchListKey);
		
		if(isCanStop(whiteList)) {
			logger.info("findComboWhiteList ok!!!!!!");
			return whiteList;
		}	else {
			return null;
		}
		
	}
	
	/**
	 * 找到目前存在的交易組合白名單
	 * @param partyNo
	 * @param entityWatchListKey
	 * @return
	 */
	public List<XComboWhitelistMain> findComboWhiteListbyPartyNumberAndEntityName(String partyNumber, String entityName) {
		List<XComboWhitelistMain> resultList = new ArrayList<XComboWhitelistMain>();
		if(!StringUtils.isEmpty(partyNumber) && !StringUtils.isEmpty(entityName)){
			resultList.addAll(iXComboWhitelistMainDao.findByPartyNumberAndEntityDisplayNameLike(partyNumber, entityName));
		}else if(!StringUtils.isEmpty(partyNumber)){
			resultList.addAll(iXComboWhitelistMainDao.findByPartyNumber(partyNumber));
		}else if(!StringUtils.isEmpty(entityName)){
			resultList.addAll(iXComboWhitelistMainDao.findByEntityDisplayNameLike(entityName));
		} else {
			Iterable<XComboWhitelistMain> result = iXComboWhitelistMainDao.findAll();
			if(result != null) {
				Iterator<XComboWhitelistMain> iterator = result.iterator();
				while(iterator.hasNext()) {
					resultList.add(iterator.next());
				}
			}
		}
		return resultList;
	}
	
	
	/**
	 * 判斷該交易組合是否已存在白名單。
	 * @param whiteList
	 * @return
	 */
	public boolean isCanCreate(XComboWhitelistMain whiteList) {
		boolean isCanCreate = false;
		if(whiteList != null) {
			if(SwiftMtConst.COMBO_WHITE_LIST_ACTION_STOP.equals(whiteList.getCaseAction()) && 
					SwiftMtConst.COMBO_WHITE_LIST_APPROVAL.equals(whiteList.getCaseDisposition())) {
				isCanCreate = true;
				copyWhiteListToHis(whiteList);
			}
			if(SwiftMtConst.COMBO_WHITE_LIST_ACTION_NEW.equals(whiteList.getCaseAction()) && 
					SwiftMtConst.COMBO_WHITE_LIST_CANCEL.equalsIgnoreCase(whiteList.getCaseDisposition())){
				isCanCreate = true;
				copyWhiteListToHis(whiteList);
			}
		} else {
			isCanCreate =  true;
		}
		return isCanCreate;
	}
	
	/**
	 * 判斷該交易組合是否已存在白名單。
	 * @param whiteList
	 * @return
	 */
	public boolean isCanStop(XComboWhitelistMain whiteList) {
		boolean isCanStop = false;
		if(whiteList != null) {
			if(SwiftMtConst.COMBO_WHITE_LIST_ACTION_STOP.equals(whiteList.getCaseAction()) && 
					SwiftMtConst.COMBO_WHITE_LIST_CANCEL.equals(whiteList.getCaseDisposition())) {
				isCanStop = true;
			}
			if(SwiftMtConst.COMBO_WHITE_LIST_ACTION_NEW.equals(whiteList.getCaseAction()) &&
					SwiftMtConst.COMBO_WHITE_LIST_APPROVAL.equals(whiteList.getCaseDisposition())){
				isCanStop = true;
			}
		}
		return isCanStop;
	}
	
	/**
	 * 將已經存在的白名單複製到歷史檔
	 * 並刪掉原本在主檔的檔案
	 * @param whiteList
	 */
	public void copyWhiteListToHis(XComboWhitelistMain whiteList) {
		XComboWhitelistHis his = new XComboWhitelistHis();
		BeanUtils.copyProperties(whiteList, his);
		iXComboWhitelistHisDao.save(his);
		iXComboWhitelistMainDao.delete(whiteList);
	}
	
	/**
	 * 儲存新的白名單
	 * @param whiteList
	 */
	public void iXComboWhitelistMainDao(XComboWhitelistMain whiteList) {
		iXComboWhitelistMainDao.save(whiteList);
	}
	
	/**
	 * 從NAME CHECK資料設定新的白名單
	 * @param caseId
	 * @param partyNo
	 * @param branchNo
	 * @param hit
	 * @return
	 */
	public XComboWhitelistMain saveNewXComboWhitelistMainByNc(String caseId, String partyNo,String branchNo, NameHitRecord hit, String userId) {
		XComboWhitelistMain main = new XComboWhitelistMain();
		main.setCaseId(caseId);
		main.setBranch(branchNo);
		main.setCaseAction(SwiftMtConst.COMBO_WHITE_LIST_ACTION_NEW);
		main.setCreateDate(new Date());
		main.setCreateUser(userId);
		main.setBeneficiaryEntityWatchListKey(String.valueOf(hit.getEntityWatchListKey()));
		main.setEntityDisplayName(hit.getEntityNameDisplay());
		main.setPartyNumber(partyNo);
		iXComboWhitelistMainDao.save(main);
		return main;
	}
	
	/**
	 * 從NAME CHECK資料設定新的白名單
	 * @param caseId
	 * @param partyNo
	 * @param branchNo
	 * @param hit
	 * @return
	 */
	public XComboWhitelistMain saveNewXComboWhitelistMainBySC(String caseId, String partyNo,String branchNo, SwiftHitRecord hit, String userId) {
		XComboWhitelistMain main = new XComboWhitelistMain();
		main.setCaseId(caseId);
		main.setBranch(branchNo);
		main.setCaseAction(SwiftMtConst.COMBO_WHITE_LIST_ACTION_NEW);
		main.setCreateDate(new Date());
		main.setCreateUser(userId);
		main.setBeneficiaryEntityWatchListKey(String.valueOf(hit.getEntityWatchListKey()));
		main.setEntityDisplayName(hit.getEntityNameDisplay());
		main.setPartyNumber(partyNo);
		iXComboWhitelistMainDao.save(main);
		return main;
	}
	
	/**
	 * @param caseRk
	 * @param nowDate
	 * @param createUserId
	 * @param whiteList
	 */
	public void createCase(BigDecimal caseRk, Date nowDate, String createUserId,  XComboWhitelistMain whiteList, String action, String caseType) {
		CaseConfig config = caseConfigDao.findByCaseTypeCd(caseType);
		List<CaseConfigXUserGroup> caseUserGroups = iCaseConfigXUserGroupDao.findByIdCaseConfigSeqNo(config.getCaseConfigSeqNo());
		CaseLive newCaseLive = newCaseLive(caseRk, config, nowDate, "", createUserId);
		newCaseVersion(newCaseLive);
		newCaseUserConfig(caseRk, caseUserGroups);
		saveUdfCharValue(caseRk, nowDate, whiteList, action);
	}
	
	/**
	 * @param caseRK
	 * @param nowDate
	 * @param whiteList
	 */
	public void saveUdfCharValue(BigDecimal caseRK, Date nowDate, XComboWhitelistMain whiteList, String action) {
		List<CaseUdfCharValue> charValue = new ArrayList<CaseUdfCharValue>();
		charValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_COMBO_WHITE_LIST_BRANCH_NO", whiteList.getBranch()));
		charValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_COMBO_WHITE_LIST_PARTY_NO", whiteList.getPartyNumber()));
		charValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_COMBO_WHITE_LIST_ENTITY_KEY", whiteList.getBeneficiaryEntityWatchListKey()));
		charValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_COMBO_WHITE_LIST_ENTITY_NAME", whiteList.getEntityDisplayName()));
		charValue.add(toCharUdfValue(caseRK, nowDate, 1, "X_COMBO_WHITE_LIST_ACTION", action));
		iCaseUdfCharValueDao.save(charValue);
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
	
	/**
	 * 建立CaseUserConfig
	 * @param caseRk
	 * @param caseUserGroups
	 */
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
	
	public String findCharUdfValue(Long caseRk, String udfNm) { 
		CaseUdfCharValue udf = iCaseUdfCharValueDao.findFirstByIdCaseRkAndIdUdfNameOrderByIdValidFromDttmDesc(caseRk, udfNm);
		if(udf != null) {
			return udf.getUdfValue();
		}
		logger.debug("findCharUdfValue:{}", udf);
		return "";
	}
	
}
