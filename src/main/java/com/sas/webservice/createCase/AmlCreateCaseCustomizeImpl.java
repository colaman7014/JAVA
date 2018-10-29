package com.sas.webservice.createCase;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.ICaseLiveDao;
import com.sas.db.aml.dao.ecm.ICaseUdfCharValueDao;
import com.sas.db.aml.orm.ecm.CaseLive;
import com.sas.db.aml.orm.ecm.CaseUdfCharValue;
import com.sas.db.aml.orm.ecm.CaseUdfCharValuePK;
import com.sas.db.wlf.dao.CaseCustomDao;
import com.sas.db.wlf.dao.IXNcscCaseResultDao;
import com.sas.db.wlf.dao.IXNcscCaseResultTmpDao;
import com.sas.db.wlf.orm.XNcscCaseResult;
import com.sas.webservice.createCase.bean.QueryHitRecordBean;

/**
 * BOT 名單掃描結果客製化
 * @author SAS
 *
 */
@Component
public class AmlCreateCaseCustomizeImpl implements AmlCreateCaseCustomize {
	
	private static final Logger logger = LoggerFactory.getLogger(AmlCreateCaseCustomizeImpl.class);

	@Autowired
	AmlCreateCase amlCreateCase;
	@Autowired
	ICaseUdfCharValueDao iCaseUdfCharValueDao;
	@Autowired
	ICaseLiveDao iCaseLiveDao;
	@Autowired
	IXNcscCaseResultDao iXNcscCaseResultDao; 
	@Autowired
	CaseCustomDao caseCustomDao;
	@Autowired
	IXNcscCaseResultTmpDao iXNcscCaseResultTmpDao;
	
	
	@Override
	public String saveUdfWatchListTypeCd0102truehit(String refId, String caseRk) throws Exception {
		logger.info("updateUdfWatchListTypeCd0102truehit caseRK:" + caseRk);
		Long caseRK = Long.valueOf(caseRk);
		String sourceType = querySourceType(caseRK);
		Timestamp  caseValidFromDttm = null;
		CaseLive caseLive = iCaseLiveDao.findOne(BigDecimal.valueOf(caseRK));
		if(caseLive!=null){
			logger.info("caseLive caseRK:" + caseLive.toString());
			caseValidFromDttm = caseLive.getValidFromDttm(); 
		}else{
			logger.info("CaseRk not found : "+caseRk);
		}
		
		List<QueryHitRecordBean> hitRecord = caseCustomDao.queryHitRecord(refId, caseRk, sourceType);
		boolean isTrueHit = checkWatchListTypeCd0102truehit(refId, caseRk,sourceType, hitRecord);
		logger.info("checkWatchListTypeCd0102truehit  Result:" + isTrueHit);
		if (isTrueHit) {
			//寫入UDF "X_WATCHLISTTYPECD01_02TRUEHIT" =Y
			boolean isInsertUdfOK = this.insertUdfCharValueWatchTypeTruehitYes(Long.valueOf(caseRk), caseValidFromDttm);
			logger.info("isInsertUdfOK:"+isInsertUdfOK);
			return "Y";
		}
		
		return null;
	}
	
	
	/**
	 * 檢核 WatchListTypeCd 01 02 有設為trueHit
	 * @param refId
	 * @param caseRk
	 * @param hitRecord
	 * @return
	 */
	private boolean checkWatchListTypeCd0102truehit(String refId, String caseRk,String sourceType,
			List<QueryHitRecordBean> hitRecordList) {
		boolean isTrueHit = false;
		List<XNcscCaseResult> caseResultList = iXNcscCaseResultDao
				.findByIdCaseRkAndIdNcReferenceId(Long.valueOf(caseRk), Integer.valueOf(refId));
		String checkResultTrueHit = "T";
		List<String> checkResultTrueHitList = new ArrayList<String>(); // Check_Seq+ _ +seq

		for (XNcscCaseResult caseResult : caseResultList) {
			logger.info(caseResult.getCheckResult());
			// 把所有勾TrueHit 的CheckSeq和Seq 當KEY
			if (checkResultTrueHit.equals(caseResult.getCheckResult())) {
				String key = null;
				if (sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_RT_SC)
						|| sourceType.equalsIgnoreCase(SwiftMtConst.SOURCE_TYPE_BT_SC)) {
					key = String.format("%s_%s", "1", String.valueOf(caseResult.getId().getSeq()));
				} else {
					key = String.format("%s_%s", caseResult.getCheckSeq(), String.valueOf(caseResult.getId().getSeq()));
				}
				checkResultTrueHitList.add(key);
			}
		}
		logger.info("checkResultTrueHitList :" + checkResultTrueHitList.size());
		if (hitRecordList != null) {
			logger.info("hitRecordList size:" + hitRecordList.size());
		}
		for(String cString : checkResultTrueHitList) {
			logger.info(cString);
		}
		
		List<String> hitRecord0102List = new ArrayList<String>();
		for (QueryHitRecordBean hitRecord : hitRecordList) {
			boolean is01_02 = hitRecord.getWatchListTypeCd().matches("01|02"); // 01和02大類
			String key = String.format("%s_%s", hitRecord.getCheckSeq(), String.valueOf(hitRecord.getSeq()));
			if (is01_02){
				hitRecord0102List.add(key);
			}
		}
		
		isTrueHit = Collections.disjoint(hitRecord0102List, checkResultTrueHitList) == true ? false : true ;
		logger.info("Found TrueHit :" + isTrueHit);

		return isTrueHit;
	}
	
	/**
	 * 查詢SourceType
	 * 
	 * @param caseRk
	 * @return
	 */
	private String querySourceType(Long caseRk) {
		String udfNm = "X_NCSC_SOURCE_TYPE";
		CaseUdfCharValue udf = iCaseUdfCharValueDao.findFirstByIdCaseRkAndIdUdfNameOrderByIdValidFromDttmDesc(caseRk,
				udfNm);
		if (udf != null) {
			return udf.getUdfValue();
		}
		logger.debug("findCharUdfValue:{}", udf);
		return "";
	} 
	
	 
	
	/**    
	 * insert UDF  "X_WATCHLISTTYPECD01_02TRUEHIT" =Y
	 * @param caseRk
	 * @return
	 */
	private  boolean insertUdfCharValueWatchTypeTruehitYes(Long caseRk, Timestamp caseValidFromDttm) {
		logger.info("caseRk:"+caseRk+",caseValidFromDttm: "+ caseValidFromDttm.toString());
		CaseUdfCharValue caseUdf = iCaseUdfCharValueDao.findFirstByIdCaseRkAndIdUdfNameOrderByIdValidFromDttmDesc(caseRk, "X_WATCHLISTTYPECD_0102TRUEHIT");
		if(caseUdf == null) {
			iCaseUdfCharValueDao.save(toCharUdfValue(new BigDecimal(caseRk), caseValidFromDttm, 1, "X_WATCHLISTTYPECD_0102TRUEHIT", "Y"));
		} else {
			caseUdf.setUdfValue("Y");
			iCaseUdfCharValueDao.save(caseUdf);
		}
		return (caseUdf!=null)? true:false;
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