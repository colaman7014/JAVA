package com.sas.webservice.nameCheck;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.IFullRefTableTranDao;
import com.sas.db.aml.dao.fcfcore.IAmlCheckDailyRecordDao;
import com.sas.db.aml.orm.ecm.FullRefTableTran;
import com.sas.db.aml.orm.fcfcore.AmlCheckDailyRecord;
import com.sas.db.wlf.orm.nc.NameHitRecord;
import com.sas.util.AmlConfiguration;
import com.sas.util.InputCheck;
import com.sas.webservice.nameCheck.bean.CheckAMLBean;
import com.sas.webservice.nameCheck.bean.CheckEntitiesBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputBean;
import com.sas.webservice.nameCheck.bean.NameCheckInputDetailBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputBean;
import com.sas.webservice.nameCheck.bean.NameCheckOutputDetail;
import com.sas.webservice.nameCheck.bean.OutputBean;

@Component
@WebService(endpointInterface = "com.sas.webservice.nameCheck.KgiCheckAML")
public class KgiCheckAMLImpl implements KgiCheckAML {
	private static final Logger logger = LoggerFactory.getLogger(KgiCheckAMLImpl.class);

	@Autowired
	AmlNameCheck amlNameCheck;

	@Autowired
	IAmlCheckDailyRecordDao iAmlCheckDailyRecordDao;
	
	@Autowired
	IFullRefTableTranDao iFullRefTableTranDao;

	@Override
	public List<OutputBean> checkAML(CheckAMLBean caBean) {
		logger.warn(String.format("[Input Information] %s", ReflectionToStringBuilder.toString(caBean)) );
		
		List<OutputBean> outPutBeanList = new ArrayList<OutputBean>();
		List<AmlCheckDailyRecord> amlCheckDailyRecordList = new ArrayList<AmlCheckDailyRecord>();
		String systemType = caBean.getSystem_type().trim();
		String checkType = caBean.getCheck_type().trim();
		String uniqueKey = caBean.getUnique_key().trim();
		String checkDept = caBean.getCheck_dept().trim();
		
		// Step1. 修正CheckEntity，當前端seq沒有值時，自動輸入補齊內容
		// Step2. Entity Type 為0開頭時，則Entity Type過濾0開頭資料
		int count = 1;
		for (int i = 0; i < caBean.getCheckEntities().size(); i++) {
			if (StringUtils.startsWith(caBean.getCheckEntities().get(i).getEntity_type(), "0"))
				caBean.getCheckEntities().get(i).setEntity_type(caBean.getCheckEntities().get(i).getEntity_type().substring(1));
			if ("".equals(caBean.getCheckEntities().get(i).getSeq())) {
				caBean.getCheckEntities().get(i).setSeq(String.valueOf(count));
			}
			count++;
		}

		// Step2. Check Input ，如果欄位檢核有Error，則直接回傳錯訊息代碼與錯誤內容
		String error = InputCheck.validate(caBean);
		if (StringUtils.isNotBlank(error)) {
			OutputBean be = new OutputBean();
			be.setError_code("1"); // 有檢核出錯誤
			be.setError_message(error); // 寫入錯誤訊息
			outPutBeanList.add(be);
			logger.warn(String.format("欄位檢核發生Error -> %s" , error));
			logger.warn(String.format("[Output Information] %s", Arrays.toString(outPutBeanList.toArray())) );
			return outPutBeanList;
		}
		
		// Step3.黑名單檢核結果查詢
		if ("2".equals(checkType)) { // 如果Check_type=2 ，撈取結果紀錄檔後直接輸出
			try {
				// 撈取資料庫紀錄檔AML_CHECK_DAILY_RECORD
				amlCheckDailyRecordList = iAmlCheckDailyRecordDao.findByUniqueKeyAndSystemTypeAndCheckDept(uniqueKey, systemType, checkDept);
				if (amlCheckDailyRecordList.size() > 0) {
					for (AmlCheckDailyRecord be : amlCheckDailyRecordList) {
						OutputBean outputBean = new OutputBean();
						outputBean.setError_code(SwiftMtConst.ERROR_CODE_SUCCESS);
						outputBean.setId(be.getId());
						outputBean.setCategory_desc(be.getCategoryDesc());
						outputBean.setDob(be.getDob());
						outputBean.setList_type(be.getListType());
						outputBean.setName(be.getName());
						outputBean.setNationality(be.getNationality());
						outputBean.setOriginal_source(be.getOriginalSource());
						outputBean.setPassport(be.getPassport());
						outputBean.setSeq(be.getSeq());
						outputBean.setAml_result(be.getAmlResult());
						outputBean.setInvest_comment(be.getInvestComment());
						outputBean.setInvest_result(be.getInvestResult());
						outPutBeanList.add(outputBean);
					}
				} else {
					outPutBeanList = new ArrayList<OutputBean>();
					OutputBean be = new OutputBean();
					be.setAml_result("");
					be.setDob("");
					be.setError_code("1");
					be.setError_message("查無需檢核的黑名單");
					be.setId("");
					be.setInvest_comment("");
					be.setInvest_result("");
					be.setList_type("");
					be.setName("");
					be.setNationality("");
					be.setOriginal_source("");
					be.setPassport("");
					be.setSeq("");
					be.setCategory_desc("");
					outPutBeanList.add(be);
				}
				logger.warn(String.format("[Output Information] %s", Arrays.toString(outPutBeanList.toArray())) );
				return outPutBeanList;
			} catch (Exception ex) {
				OutputBean be = new OutputBean();
				be.setError_code("1"); // 有檢核出錯誤
				be.setError_message("撈取黑名單紀錄檔發生錯誤"); // 寫入錯誤訊息
				outPutBeanList.add(be);
				logger.warn(String.format("CheckAMLDao-queryAmlCheckDailyRecord Error! Message = %s" , ex));
				logger.warn(String.format("[Output Information] %s", Arrays.toString(outPutBeanList.toArray())));
				return outPutBeanList;
			}
		}

		// Step4 當 System_type in {01,02,04,05,06,07,12}資料庫有紀錄的話，就直接輸出，不用重掃
		String noCheckSystemType = AmlConfiguration.getString("com.kgi.systemType.noCheck");
		if (noCheckSystemType.contains(systemType)) {
			try {
				amlCheckDailyRecordList = iAmlCheckDailyRecordDao.findByUniqueKeyAndSystemTypeAndCheckDept(uniqueKey, systemType, checkDept);
				for (AmlCheckDailyRecord be : amlCheckDailyRecordList) {
					OutputBean outputBean = new OutputBean();
					outputBean.setError_code(SwiftMtConst.ERROR_CODE_SUCCESS);
					outputBean.setId(be.getId());
					outputBean.setCategory_desc(be.getCategoryDesc());
					outputBean.setDob(be.getDob());
					outputBean.setList_type(be.getListType());
					outputBean.setName(be.getName());
					outputBean.setNationality(be.getNationality());
					outputBean.setOriginal_source(be.getOriginalSource());
					outputBean.setPassport(be.getPassport());
					outputBean.setSeq(be.getSeq());
					outputBean.setAml_result(be.getAmlResult());
					outputBean.setInvest_comment(be.getInvestComment());
					outputBean.setInvest_result(be.getInvestResult());
					outPutBeanList.add(outputBean);
				}

				if (outPutBeanList.size() > 0) {
					logger.warn(String.format("[Output Information] %s", Arrays.toString(outPutBeanList.toArray())) );
					return outPutBeanList;
				} else {
					outPutBeanList = new ArrayList<OutputBean>();
				}
			} catch (Exception ex) {
				OutputBean be = new OutputBean();
				be.setError_code("1"); // 有檢核出錯誤
				be.setError_message("撈取黑名單紀錄檔發生錯誤"); // 寫入錯誤訊息
				outPutBeanList.add(be);
				logger.warn(String.format("CheckAMLDao-queryAmlCheckDailyRecord Error! Message = %s" , ex));
				logger.warn(String.format("[Output Information] %s", Arrays.toString(outPutBeanList.toArray())) );
				return outPutBeanList;
			}
		}

		// Step5. 外匯系統，
		//當有查詢對象為銀行(且有帶入國別)，則重新再產生一個SEQ 
		if (SwiftMtConst.KGI_SYSTEM_TYPE_03.equals(systemType)) {
			List<CheckEntitiesBean> checkEntitiesList = new ArrayList<CheckEntitiesBean>();
			int checkEntitiesSize = caBean.getCheckEntities().size();
			caBean.getCheckEntities().addAll(checkEntitiesList);
			for (int i = 0; i < checkEntitiesSize; i++) {
				if (SwiftMtConst.ENTITY_TYPE_BANK.equals(caBean.getCheckEntities().get(i).getEntity_type())) {
					if (!"".equals(caBean.getCheckEntities().get(i).getCountry())) {
						CheckEntitiesBean be = new CheckEntitiesBean();
						be.setCountry(caBean.getCheckEntities().get(i).getCountry());
						be.setSeq("9" + caBean.getCheckEntities().get(i).getSeq());// 避免序號重複使用9開頭
						be.setEntity_type(SwiftMtConst.ENTITY_TYPE_COUNTRY);
						logger.warn(String.format("外匯系統，查詢對象為銀行，且有代入國別，新增一筆Seq=%s 查詢對象為國家Entity_Type = 1 ,Country=%s",
								be.getSeq(), be.getCountry()));
						caBean.getCheckEntities().add(be);
					}
				}
			}
		}

		// Step6. 查看是否有中黑名單
		try {
			// Input 資料輸入內容轉換
			NameCheckInputBean nameCheckInputBean = inputKGITranslatePublic(caBean);
			NameCheckOutputBean nameCheckOutputBean = amlNameCheck.NameCheck(nameCheckInputBean);
			amlCheckDailyRecordList = new ArrayList<AmlCheckDailyRecord>(); //寫DB用的
			outputPublicTranslateKGI(nameCheckOutputBean, caBean, outPutBeanList , amlCheckDailyRecordList); // 將公版結果檔轉換成KGI版本
			iAmlCheckDailyRecordDao.save(amlCheckDailyRecordList); // 儲存

		} catch (Exception ex) {
			OutputBean be = new OutputBean();
			be.setError_code("1"); // 有檢核出錯誤
			be.setError_message("撈取黑名單檔發生錯誤"); // 寫入錯誤訊息
			outPutBeanList.add(be);
			logger.warn(String.format("CheckAML NameCheck Error! Message = %s" , ex));
			logger.warn(String.format("[Output Information] %s", Arrays.toString(outPutBeanList.toArray())) );
			return outPutBeanList;
		}

		logger.warn(String.format("[Output Information] %s", Arrays.toString(outPutBeanList.toArray())) );

		return outPutBeanList;
	}

	/**
	 * 將KGI的Input轉換成公版的Input
	 * 
	 * @param checkAmlBean
	 * @return
	 */
	public NameCheckInputBean inputKGITranslatePublic(CheckAMLBean checkAmlBean) {

		List<NameCheckInputDetailBean> nameCheckInputDetailList = new ArrayList<NameCheckInputDetailBean>();
		for (CheckEntitiesBean checkEntities : checkAmlBean.getCheckEntities()) {
			NameCheckInputDetailBean nameCheckInputDetailBe = new NameCheckInputDetailBean();
			nameCheckInputDetailBe.setCheckSeq(checkEntities.getSeq()); // 序號

			// KGI Entity_type 轉換為公版的
			nameCheckInputDetailBe.setEntityType(checkEntities.getEntity_type());
			nameCheckInputDetailBe.setEnglishName(checkEntities.getEn_name());    // 英文名稱
			nameCheckInputDetailBe.setNonEnglishName(checkEntities.getCh_name()); // 中文名稱
			nameCheckInputDetailBe.setCountry(checkEntities.getCountry());        // 國家
			nameCheckInputDetailBe.setIdNumber(checkEntities.getId());            // ID
			
			//國家類別
			//1. 有輸入英文名稱 未輸入國家 將英文名稱帶入國家欄位查詢
			//2. 國家及英文名稱均有輸入資料時，則產生一筆新的查詢條件，將英文名稱帶入國家欄位查詢
			if (StringUtils.equals(SwiftMtConst.ENTITY_TYPE_COUNTRY, checkEntities.getEntity_type())
					&& StringUtils.isNoneBlank(checkEntities.getEn_name())) {
				if (StringUtils.isBlank(checkEntities.getCountry()))
					nameCheckInputDetailBe.setCountry(checkEntities.getEn_name());        // 國家
				else {
					NameCheckInputDetailBean newDetail = new NameCheckInputDetailBean();
					newDetail.setEntityType(SwiftMtConst.ENTITY_TYPE_COUNTRY);
					newDetail.setCheckSeq(String.format("%s%s", "8", checkEntities.getSeq())); // 產生新序號 避免序號重複使用8開頭
					newDetail.setCountry(checkEntities.getEn_name());        // 國家
					nameCheckInputDetailList.add(newDetail);
				}
			}
			
			//銀行類別 將輸入ID 帶入BicSwiftCode
			if (StringUtils.equals(SwiftMtConst.ENTITY_TYPE_BANK, checkEntities.getEntity_type())) {
				if (StringUtils.isNotBlank(checkEntities.getId()))
					nameCheckInputDetailBe.setBicSwiftCode(checkEntities.getId());
			}
			nameCheckInputDetailList.add(nameCheckInputDetailBe);
		}

		NameCheckInputBean inputBean = new NameCheckInputBean();
		inputBean.setInterfaceName(SwiftMtConst.INTERFACE_TYPE_NAMECHECK);
		inputBean.setCallingSystem(checkAmlBean.getSystem_type());
		inputBean.setScreenProcess(screenProcessKGIToPublic(checkAmlBean.getCheck_type()));   // ***根據check type set screen process
		inputBean.setUniqueKey(checkAmlBean.getUnique_key());                                  // ***注意，會不會有Unique過長的現象
		inputBean.setBranchNumber(checkAmlBean.getCheck_dept());                               // 分行代碼
		inputBean.setGenAlertFlag("Y");
		inputBean.setSeq(nameCheckInputDetailList);
		

		return inputBean;
	}

	private String screenProcessKGIToPublic(String checkType) {
		String screenProcess = "";
		if (StringUtils.equals("1", checkType))//Check_type=1 黑名單檢核
			screenProcess = SwiftMtConst.SCREEN_PROCESS_Account_Opening;
		else if (StringUtils.equals("3", checkType))//Check_type=3 禁制名單檢核
			screenProcess = SwiftMtConst.SCREEN_PROCESS_Transaction_Screening;
		
		return screenProcess;
	}
	
	private String getWatchlistTypeCdName(String subWatchlistTypeCd, String refTableNm){
		List<FullRefTableTran> trans = iFullRefTableTranDao
				.findByIdLocaleAndIdRefTableNmAndIdValueCdInOrderByDisplayOrderNoAsc(
						"zh_TW",
						refTableNm,
						subWatchlistTypeCd.split(","));
		String watchlistSubTypeCdName = "";
		if (!trans.isEmpty()){
			for (FullRefTableTran fullRefTableTran : trans) {
				watchlistSubTypeCdName += fullRefTableTran.getValueDesc() +",";
			}
			watchlistSubTypeCdName = watchlistSubTypeCdName.substring(0, watchlistSubTypeCdName.length()-1);
		}
		return watchlistSubTypeCdName;
	}
	
	/**
	 * 將公版的的Output轉換換成KGI版的Output
	 * 
	 * @param CABBean
	 * @return
	 */
	public void outputPublicTranslateKGI(NameCheckOutputBean NCOBean, CheckAMLBean CABBean, List<OutputBean> OutputBeanList, List<AmlCheckDailyRecord> amlCheckDailyRecordList) {

		String errorCode = NCOBean.getErrorCode();
		String errorMessage = NCOBean.getErrorMessage();
		String url = AmlConfiguration.getString("com.sas.onlineNameCheck.detail.url");
		
		//判斷NameCheck是否發生錯誤 ex: 欄位檢核 系統錯誤
		if (StringUtils.equals(errorCode, SwiftMtConst.ERROR_CODE_SUCCESS)) {
			long recordTimestamp = System.currentTimeMillis();
			for(NameCheckOutputDetail nameCheckOutputDetail : NCOBean.getSeq()){
				if(nameCheckOutputDetail.getNameHitRecordList().size() > 0){ //代表有Hit到名單		
					for(NameHitRecord nameHitRecord : nameCheckOutputDetail.getNameHitRecordList()){
						OutputBean outputBean = new OutputBean();
						outputBean.setError_code(errorCode); // **需要注意Error代碼的轉換
						outputBean.setError_message(errorMessage);
						if(nameHitRecord.getExactMatchScore()==100){ 
							outputBean.setAml_result("Y");
						}else{
							outputBean.setAml_result("V");
						}
						outputBean.setSeq(nameCheckOutputDetail.getCheckSeq());
						outputBean.setCategory_desc(nameHitRecord.getWatchListTypeCd());
						outputBean.setList_type(nameHitRecord.getWatchListName());
						outputBean
								.setOriginal_source(getWatchlistTypeCdName(
										nameHitRecord.getWatchListTypeCd(),
										SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD));
						outputBean.setName(nameHitRecord.getEntityName());
						if (nameHitRecord.getYearOfBirth() == null) {                         // 出生年月
							outputBean.setDob("");
						} else {
							outputBean.setDob(String.valueOf(nameHitRecord.getYearOfBirth().intValue()));
						}
						outputBean.setNationality(nameHitRecord.getCitizenshipCountryCode());
						// outputBean.setPassport(passport); //新版沒有passport
						outputBean.setId(nameHitRecord.getIdentificationId());
						OutputBeanList.add(outputBean);
						
						//因AML_CHECK_DAILY_RECORD無key值
						//故jpa enetity中設定RecordTimestamp為key
						//取得系統時間Long值,每筆增加10毫秒使RecordTimestamp不重複
						recordTimestamp = recordTimestamp+10l;
						AmlCheckDailyRecord amlCheckDailyRecordBean = new AmlCheckDailyRecord();
						amlCheckDailyRecordBean.setSystemType(CABBean.getSystem_type());
						amlCheckDailyRecordBean.setCheckType(CABBean.getCheck_type());
						amlCheckDailyRecordBean.setUniqueKey(CABBean.getUnique_key());
						amlCheckDailyRecordBean.setCheckDept(CABBean.getCheck_dept());
						amlCheckDailyRecordBean.setSeq(nameCheckOutputDetail.getCheckSeq());
						amlCheckDailyRecordBean.setInputId(nameCheckOutputDetail.getIdNumber());
						amlCheckDailyRecordBean.setInputEnName(nameCheckOutputDetail.getEnglishName());
						amlCheckDailyRecordBean.setInputChName(nameCheckOutputDetail.getNonEnglishName());
						amlCheckDailyRecordBean.setInputCountry(nameCheckOutputDetail.getCountry());
						amlCheckDailyRecordBean.setEntityType(new BigDecimal(nameCheckOutputDetail.getEntityType()));
						amlCheckDailyRecordBean.setMixScore(nameHitRecord.getMixScore());
						if(nameHitRecord.getExactMatchScore()==100){ 
							amlCheckDailyRecordBean.setAmlResult("Y");    //分數100，直接給Y
						}else{
							amlCheckDailyRecordBean.setAmlResult("V");    //否則V
						}
						amlCheckDailyRecordBean.setCategoryDesc(nameHitRecord.getWatchListTypeCd());
						amlCheckDailyRecordBean.setListType(nameHitRecord.getWatchListName());
						amlCheckDailyRecordBean
								.setOriginalSource(getWatchlistTypeCdName(
										nameHitRecord.getWatchListTypeCd(),
										SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD));
						amlCheckDailyRecordBean
								.setSubCategoryDesc(getWatchlistTypeCdName(
										nameHitRecord.getWatchListSubTypeCd(),
										SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD));
						amlCheckDailyRecordBean.setName(nameHitRecord.getEntityName());
						if (nameHitRecord.getYearOfBirth() == null) {                         // 出身年月
							amlCheckDailyRecordBean.setDob("");
						} else {
							amlCheckDailyRecordBean.setDob(String.valueOf(nameHitRecord.getYearOfBirth().intValue()));
						}
						amlCheckDailyRecordBean.setNationality(nameHitRecord.getCitizenshipCountryCode());
						// outputBean.setPassport(passport); //新版沒有passport
//						amlCheckDailyRecordBean.setPassport(passport);
						amlCheckDailyRecordBean.setWatchListDetailUrl(String.format(url, nameHitRecord.getWatchListName(), nameHitRecord.getEntityWatchListNumber(), nameHitRecord.getEntityWatchListKey()));
						amlCheckDailyRecordBean.setId(nameHitRecord.getIdentificationId());
						amlCheckDailyRecordBean.setRecordTimestamp(new Timestamp(recordTimestamp));
						amlCheckDailyRecordList.add(amlCheckDailyRecordBean);
						logger.debug(ToStringBuilder.reflectionToString(amlCheckDailyRecordBean));
					}
				}else{
					//因AML_CHECK_DAILY_RECORD無key值
					//故jpa enetity中設定RecordTimestamp為key
					//取得系統時間Long值,每筆增加10毫秒使RecordTimestamp不重複
					recordTimestamp = recordTimestamp+10l;
					// OutputBean 部分
					OutputBean outputBean = new OutputBean();
					outputBean.setError_code(errorCode); // **需要注意Error代碼的轉換
					outputBean.setError_message(errorMessage);
					outputBean.setAml_result("N");
					outputBean.setSeq(nameCheckOutputDetail.getCheckSeq());
					OutputBeanList.add(outputBean);
					
					// AmlCheckDailyRecord 部分
					AmlCheckDailyRecord amlCheckDailyRecordBean = new AmlCheckDailyRecord();
					amlCheckDailyRecordBean.setAmlResult("N");
					amlCheckDailyRecordBean.setSystemType(CABBean.getSystem_type());
					amlCheckDailyRecordBean.setCheckType(CABBean.getCheck_type());
					amlCheckDailyRecordBean.setUniqueKey(CABBean.getUnique_key());
					amlCheckDailyRecordBean.setCheckDept(CABBean.getCheck_dept());
					amlCheckDailyRecordBean.setSeq(nameCheckOutputDetail.getCheckSeq());
					amlCheckDailyRecordBean.setInputId(nameCheckOutputDetail.getIdNumber());
					amlCheckDailyRecordBean.setInputEnName(nameCheckOutputDetail.getEnglishName());
					amlCheckDailyRecordBean.setInputChName(nameCheckOutputDetail.getNonEnglishName());
					amlCheckDailyRecordBean.setInputCountry(nameCheckOutputDetail.getCountry());
					amlCheckDailyRecordBean.setEntityType(new BigDecimal(nameCheckOutputDetail.getEntityType()));
					amlCheckDailyRecordBean.setRecordTimestamp(new Timestamp(recordTimestamp));
					amlCheckDailyRecordList.add(amlCheckDailyRecordBean);
					logger.debug(ToStringBuilder.reflectionToString(amlCheckDailyRecordBean));
					
				}
			}
			
		} else { 
			// 回傳系統所帶錯誤訊息及錯誤代碼
			OutputBean outputBean = new OutputBean();
			outputBean.setError_code(errorCode); 
			outputBean.setError_message(errorMessage);
			OutputBeanList.add(outputBean);
		}
	}
	

}
