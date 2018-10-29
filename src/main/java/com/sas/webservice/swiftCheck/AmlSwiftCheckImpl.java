
package com.sas.webservice.swiftCheck;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.dataflux.xsd.archserver.RowIn;
import com.prowidesoftware.swift.model.mt.AbstractMT;
import com.sas.aml.retry.event.queue.bean.XRetryEventQueue;
import com.sas.aml.retry.event.queue.service.QueueConstraint;
import com.sas.aml.retry.event.queue.service.XRetryEventQueueService;
import com.sas.amlCheck.initCfg.AmlJpaCfg;
import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.dao.ecm.IRefTableValueDao;
import com.sas.db.aml.dao.fcfcore.IFscEntityWlXListSettingDao;
import com.sas.db.aml.dao.fcfcore.IXComboWhitelistMainDao;
import com.sas.db.aml.dao.fcfcore.IXScreenProcessSettingDao;
import com.sas.db.aml.dao.fcfcore.IXWatchlistCompressStringDao;
import com.sas.db.aml.dao.fcfcore.IXWatchlistSettingDao;
import com.sas.db.aml.orm.ecm.RefTableValue;
import com.sas.db.aml.orm.fcfcore.XComboWhitelistMain;
import com.sas.db.aml.orm.fcfcore.XFscEntityWlXListSetting;
import com.sas.db.aml.orm.fcfcore.XScreenProcessSetting;
import com.sas.db.aml.orm.fcfcore.XWatchlistCompressString;
import com.sas.db.aml.orm.fcfcore.XWatchlistSetting;
import com.sas.db.wlf.dao.ICallingSystemActionDao;
import com.sas.db.wlf.dao.IKeyGenerateDao;
import com.sas.db.wlf.dao.sc.ISwiftCheckRecordDao;
import com.sas.db.wlf.dao.sc.ISwiftConfigDao;
import com.sas.db.wlf.dao.sc.ISwiftHitRecordDao;
import com.sas.db.wlf.dao.sc.ISwiftLogTimeDao;
import com.sas.db.wlf.dao.sc.ISwiftMainConfigDao;
import com.sas.db.wlf.dao.sc.ISwiftWaitRecordDao;
import com.sas.db.wlf.orm.CallingSystemAction;
import com.sas.db.wlf.orm.CallingSystemActionPK;
import com.sas.db.wlf.orm.KeyGenerate;
import com.sas.db.wlf.orm.sc.SwiftCheckRecord;
import com.sas.db.wlf.orm.sc.SwiftConfig;
import com.sas.db.wlf.orm.sc.SwiftHitRecord;
import com.sas.db.wlf.orm.sc.SwiftHitRecordPK;
import com.sas.db.wlf.orm.sc.SwiftLogTime;
import com.sas.db.wlf.orm.sc.SwiftMainConfig;
import com.sas.db.wlf.orm.sc.SwiftWaitRecord;
import com.sas.db.wlf.orm.sc.SwiftWaitRecordPK;
import com.sas.swift.bean.BicCodeBean;
import com.sas.swift.bean.SwiftConfigBean;
import com.sas.swift.bean.SwiftHitRecordBean;
import com.sas.util.AmlConfiguration;
import com.sas.util.ConnectionProvider;
import com.sas.util.MapUtils;
import com.sas.util.NameCheckUtil;
import com.sas.util.StringUtils;
import com.sas.webservice.createCase.AmlCreateCase;
import com.sas.webservice.createCase.bean.CreateCaseInputBean;
import com.sas.webservice.swiftCheck.bean.SwiftCheckInputBean;
import com.sas.webservice.swiftCheck.bean.SwiftCheckOutputBean;
import com.sas.webservice.swiftCheck.bean.SwiftMergeBean;
import com.sas.wlsearch.bean.MatchCodeResultBean;
import com.sas.wlsearch.business.DataFluxMatchCode;
import com.sas.wlsearch.business.DataFluxMatchCodeOption;
import com.sas.wlsearch.dao.WatchListSearchDAO;
import com.sas.wlsearch.util.WatchListUtil;

/**
 * AML SWIFT CHECK Interface 主程式
 * @author SAS
 *
 */
@Component
@WebService(endpointInterface = "com.sas.webservice.swiftCheck.AmlSwiftCheck")
public class AmlSwiftCheckImpl implements AmlSwiftCheck{
	private static final Logger logger = LoggerFactory.getLogger(AmlSwiftCheckImpl.class);
	//never forget to let DEBUGMODE in false to production
	private static final boolean DEBUGMODE = false;
	@Autowired
	ServletContext context; 
	@Autowired @Lazy
	AmlJpaCfg amlJpaCfg;
	@Autowired @Lazy
	ApplicationContext ctx;
	@Autowired @Lazy
	ISwiftConfigDao iSwiftConfigDao;
	@Autowired @Lazy
	ISwiftHitRecordDao iSwiftHitRecordDao;
	@Autowired @Lazy
	ISwiftCheckRecordDao iSwiftCheckRecordDao;
	@Autowired @Lazy
	IKeyGenerateDao iKeyGenerateDao;
	@Autowired @Lazy
	IFscEntityWlXListSettingDao iFscEntityWlXListSetDao;
	@Autowired @Lazy
	DataFluxMatchCode dataFluxMatchCode;
	@Autowired @Lazy
	WatchListSearchDAO watchListSearchDAO;
	@Autowired @Lazy
	ISwiftMainConfigDao iSwiftMainConfigDao;
	@Autowired @Lazy
	IXScreenProcessSettingDao iXScreenProcessSettingDao;
	@Autowired @Lazy
	IRefTableValueDao iRefTableValueDao;
	@Autowired @Lazy
	AmlCreateCase amlCreateCase;
	@Autowired @Lazy
	IXComboWhitelistMainDao iXComboWhitelistMainDao;	
	@Autowired @Lazy
	AsyncTaskExecutor taskAsyncExecutor;
	@Autowired @Lazy
	AmlSwiftAsyncCheck swiftAsyncCheck;
	@Autowired @Lazy
	ICallingSystemActionDao iCallingSystemActionDao;
	@Autowired
	IXWatchlistCompressStringDao iXWatchlistCompressStringDao;
	@Autowired
	ISwiftWaitRecordDao iSwiftWaitRecordDao;
	@Autowired
	XRetryEventQueueService xRetryEventQueueService;
	@Autowired
	DataFluxMatchCodeOption dataFluxMatchCodeOption;
	@Autowired
	IXWatchlistSettingDao iXWatchlistSettingDao;
	@Autowired 
	ISwiftLogTimeDao iSwiftLogTimeDao;
	
	/**
	 * AML SWIFT CHECK Interface 主程式
	 */
	@SuppressWarnings("unused")
	@Override
	@Transactional
	public SwiftCheckOutputBean SwiftAsyncCheck(SwiftCheckInputBean input, long id) {
		logger.info("\n --------------- SwiftAsyncCheck Begin......  --------------- \n");
		logger.info(input.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SwiftCheckOutputBean swiftCheckOutputBean = new SwiftCheckOutputBean();
		String interfaceName = SwiftMtConst.INTERFACE_TYPE_SWIFTCHECK;//input.getInterfaceName();
		String callingSystem = input.getCallingSystem();
		String screenProcess = input.getScreenProcess();
		String branchNumber = input.getBranchNumber();
		String transactionDate = "";//input.getTransactionDate();
		String replaceWord = String.format("}}%s{1:", SwiftMtConst.RLPLACE_MULTI_SWIFT_SPLIT_PATTERN);
		String swiftRje = input.getSwiftRje().replaceAll(SwiftMtConst.MULTI_SWIFT_SPLIT_PATTERN, replaceWord);
		String uniqueKey = input.getUniqueKey();
		String genAlertFlag = input.getGenAlertFlag();
		
		if(chkFields(interfaceName, callingSystem, screenProcess, branchNumber, uniqueKey, swiftRje)){
			swiftCheckOutputBean.setInterfaceName(interfaceName);
			swiftCheckOutputBean.setNcReferenceId("");
			swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_FORMATERR);
			swiftCheckOutputBean.setErrorMessage(SwiftMtConst.ERROR_CODE_FORMATERR_MESSAGE);
			swiftCheckOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
			swiftCheckOutputBean.setUniqueKey(uniqueKey);
			swiftCheckOutputBean.setRouteRule("");
			logger.info("chkFields error......"+swiftCheckOutputBean.toString());
			return swiftCheckOutputBean;
		}
		boolean isAllAck = true;
		boolean needCheck = true;
		Integer referenceId = null;
		try {
			if(swiftRje.indexOf(SwiftMtConst.RLPLACE_MULTI_SWIFT_SPLIT_PATTERN) > 0){
				String[] eachContents = swiftRje.split(SwiftMtConst.RLPLACE_MULTI_SWIFT_SPLIT_PATTERN);
				for(String eachCotnent : eachContents){
					logger.debug(eachCotnent);
					if(!isAckSwift(eachCotnent) && isNeedCheck(eachCotnent)){
						isAllAck = false;
						needCheck = false;
						if (referenceId == null) {
							referenceId = getOrGenerateReferenceId(uniqueKey);
						}
						doSwiftCheck(input, eachCotnent, swiftCheckOutputBean, referenceId, id);
					}
				}
			}else{
				if(!isAckSwift(swiftRje) && isNeedCheck(swiftRje)){
					isAllAck = false;
					needCheck = false;
					if (referenceId == null) {
						referenceId = getOrGenerateReferenceId(uniqueKey);
					}
					doSwiftCheck(input, swiftRje, swiftCheckOutputBean, referenceId, id);
				}
			}
			if(isAllAck || needCheck){
				swiftCheckOutputBean.setInterfaceName(interfaceName);
				swiftCheckOutputBean.setRouteRule("");
				swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SUCCESS);
				swiftCheckOutputBean.setErrorMessage("");
				swiftCheckOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
				swiftCheckOutputBean.setUniqueKey(uniqueKey);
				swiftCheckOutputBean.setNcReferenceId("");
			}
		} catch (IOException ioe){
			logger.error(String.format("parse swift message fail, IO exception : %s", ioe.getMessage()), ioe);
			swiftCheckOutputBean.setInterfaceName(interfaceName);
			try {
				swiftCheckOutputBean.setTransactionDate(sdf.parse(transactionDate));
			} catch (ParseException e) {
				logger.error(String.format("parse transactionDate fail, ParseException : %s", e.getMessage()), e);
			}
			swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_FORMATERR);
			swiftCheckOutputBean.setErrorMessage(ioe.getMessage());
			swiftCheckOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
			swiftCheckOutputBean.setUniqueKey(uniqueKey);
			swiftCheckOutputBean.setNcReferenceId("");
			swiftCheckOutputBean.setRouteRule("");
			XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.saveEntityToXRetryEventQueue(SwiftMtConst.SOURCE_TYPE_RT_SC, SwiftCheckInputBean.class.getName(),   
					input, HttpMethod.POST, AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) +  context.getContextPath() + "/rest/retrySwiftCheck", 
					3, "retrySwiftCheck", id, QueueConstraint.QUEUE_EVENT_STATUS_WAITING, false, "");
			if(xRetryEventQueue == null) {
				logger.error("Retry id insert fail");
			}
		} catch (Exception e) {
			logger.error(String.format("parse swift message fail, exception : %s", e.getMessage()), e);
			swiftCheckOutputBean.setInterfaceName(interfaceName);
			try {
				swiftCheckOutputBean.setTransactionDate(sdf.parse(transactionDate));
			} catch (ParseException e1) {
				logger.error(String.format("parse transactionDate fail, ParseException : %s", e.getMessage()), e);
			}
			swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SYSTEMERR);
			swiftCheckOutputBean.setErrorMessage(e.getMessage());
			swiftCheckOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
			swiftCheckOutputBean.setUniqueKey(uniqueKey);
			swiftCheckOutputBean.setNcReferenceId("");
			swiftCheckOutputBean.setRouteRule("");
			XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.saveEntityToXRetryEventQueue(SwiftMtConst.SOURCE_TYPE_RT_SC, SwiftCheckInputBean.class.getName(),   
					input, HttpMethod.POST, AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) +  context.getContextPath() + "/rest/retrySwiftCheck", 
					3, "retrySwiftCheck", id, QueueConstraint.QUEUE_EVENT_STATUS_WAITING, false, "");
			if(xRetryEventQueue == null) {
				logger.error("Retry id insert fail");
			}
		}
		if(swiftCheckOutputBean != null)
			logger.info("\n ------------------- SwiftAsyncCheck SwiftCheckOutputBean:::::"+swiftCheckOutputBean.toString() +" ----------------- \n");
		else 
			logger.error("\n ------------------- SwiftAsyncCheck SwiftCheckInputBean:::::"+input.toString() +" ----------------- \n");
		return swiftCheckOutputBean;
	}

	/**
	 * interface swift Check 各欄位檢核
	 * @param interfaceName
	 * @param callingSystem
	 * @param screenProcess
	 * @param branchNumber
	 * @param genAlertFlag
	 * @param transactionDate
	 * @param uniqueKey
	 * @param referenceNumber
	 * @param swiftRje
	 */
	private boolean chkFields(String interfaceName, String callingSystem, String screenProcess, String branchNumber, String uniqueKey, String swiftRje){
		boolean result = false;
		
		if (!SwiftMtConst.CALLING_SYSTEM_SWALLOW.equals(callingSystem)) {
			result = true;
		}
		
		if(StringUtils.isEmpty(screenProcess)
				|| (!SwiftMtConst.SCREEN_PROCESS_SWIFT_Screening.equals(screenProcess) && !SwiftMtConst.SCREEN_PROCESS_BATCH_SWIFT_SCREENING.equals(screenProcess))
		){
			result = true;
		}
		
		if(StringUtils.isEmpty(branchNumber)
		){
			result = true;
		}
		
		if(StringUtils.isEmpty(uniqueKey)
		){
			result = true;
		}
				
		if(StringUtils.isEmpty(swiftRje)
		){
			result = true;
		}
		return result;
	}
	
    /**
     * 判斷是否為等待電文工具程式
     * @param swiftRje
     * @return boolean
     * @throws IOException
     */
	@SuppressWarnings("unused")
	private boolean isNeedWait(String swiftRje) throws IOException{
		boolean result = false;
		String swiftType = getSwiftType(swiftRje);
		SwiftMainConfig swiftMainConfig = iSwiftMainConfigDao.findOne(swiftType);
		if(swiftMainConfig != null && SwiftMtConst.SWIFT_MAIN_CONFIG_Y.equalsIgnoreCase(swiftMainConfig.getNeedWait())){
			result = true;
		}
		return result;
	}
	
	/**
	 * 判斷電文是否需要掃描工具程式
	 * @param swiftRje
	 * @return boolean
	 * @throws IOException
	 */
	private boolean isNeedCheck(String swiftRje) throws IOException{
		boolean result = false;
		boolean isOutgoing = isOutgoingSwift(swiftRje);
		boolean isIncoming = isIncomingSwift(swiftRje);
		logger.debug("isOutgoing : {}, isIncoming : {}", isOutgoing, isIncoming);
		String swiftType = getSwiftType(swiftRje);
		SwiftMainConfig swiftMainConfig = iSwiftMainConfigDao.findOne(swiftType);
		
		if(swiftMainConfig != null){
			logger.debug("getScreenOutgoing : {}, getScreenIncoming : {}", swiftMainConfig.getScreenOutgoing(), swiftMainConfig.getScreenIncoming());
			if((isOutgoing && SwiftMtConst.SWIFT_MAIN_CONFIG_Y.equalsIgnoreCase(swiftMainConfig.getScreenOutgoing())) 
					|| 
				(isIncoming && SwiftMtConst.SWIFT_MAIN_CONFIG_Y.equalsIgnoreCase(swiftMainConfig.getScreenIncoming()))){
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * 判斷電文型態工具程式
	 * @param swiftRje
	 * @return String
	 * @throws IOException
	 */
	private String getSwiftType(String swiftRje) throws IOException{
		String result = "";
		String variant = AbstractMT.parse(swiftRje).getSwiftMessage().getMtId().getVariant() == null ? "" : AbstractMT.parse(swiftRje).getSwiftMessage().getMtId().getVariant();
		result = String.format("Mt%s%s", AbstractMT.parse(swiftRje).getMessageType(), variant);
		return result;
	}
	
	/**
	 * 是否為Outgoing電文判斷
	 * @param swiftRje
	 * @return boolean
	 * @throws IOException
	 */
	private boolean isOutgoingSwift(String swiftRje) throws IOException{
		return AbstractMT.parse(swiftRje).getSwiftMessage().isOutgoing();
	}
	
	/**
	 * 是否為Incoming電文判斷
	 * @param swiftRje
	 * @return boolean
	 * @throws IOException
	 */
	private boolean isIncomingSwift(String swiftRje) throws IOException{
		return AbstractMT.parse(swiftRje).getSwiftMessage().isIncoming();
	}
	
	/**
	 * 是否為Ack電文判斷
	 * @param swiftRje
	 * @return boolean
	 * @throws IOException
	 */
	private boolean isAckSwift(String swiftRje) throws IOException{
		return AbstractMT.parse(swiftRje).getSwiftMessage().isAck();
	}
	
	/**
	 * 將原本Map<FieldName, List<SwiftMergeBean>>轉換為Map<SwiftType, Map<Tag, SwiftMergeBean>>
	 * @param fieldNameMergeBeanMap
	 * @return
	 */
	@SuppressWarnings("unused")
	private Map<String, Map<String, SwiftMergeBean>> reorganizeMap(Map<String, LinkedList<SwiftMergeBean>> fieldNameMergeBeanMap){
		Map<String, Map<String, SwiftMergeBean>> result = new HashMap<String, Map<String, SwiftMergeBean>>();
		for ( LinkedList<SwiftMergeBean> swiftMergeBeans : fieldNameMergeBeanMap.values() ){
			for ( SwiftMergeBean swiftMergeBean : swiftMergeBeans ){
				String swiftType = swiftMergeBean.getSwiftType();
				Map<String, SwiftMergeBean> map = result.get(swiftType);
				if ( map == null ){
					map = new HashMap<String, SwiftMergeBean>();
				}
				map.put(swiftMergeBean.getFieldTag(), swiftMergeBean);
				result.put(swiftType, map);
			}
		}
		return result;
	}

	/**
	 * 建立Swift與Field Name之間的Mapping Table
	 * Map<fieldName, LinkedList<MergeBean>>
	 * @param swiftWaitRecords
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private Map<String, LinkedList<SwiftMergeBean>> createSwiftFieldMappingTable(List<SwiftWaitRecord> swiftWaitRecords) throws IOException{
		logger.debug("Create Swift Field Mapping Table.");
		//<fieldName, List<MergeBean>>
		Map<String, LinkedList<SwiftMergeBean>> map = new HashMap<String, LinkedList<SwiftMergeBean>>();

		for ( SwiftWaitRecord swr : swiftWaitRecords ){
			AbstractMT amt = AbstractMT.parse(swr.getSwiftFullText());
			String variant = amt.getMtId().getVariant() == null ? "" : amt.getMtId().getVariant();
			String swiftType = String.format("Mt%s%s", amt.getMessageType(), variant);
			int seq = Integer.parseInt( swr.getSeqOfTotal().split(SwiftMtConst.SEQUENCE_OF_TOTAL_SPLIT)[0] );

			List<SwiftConfig> swiftConfigs = iSwiftConfigDao.findByIdSwiftType(swiftType);
			for ( SwiftConfig swiftConfig : swiftConfigs ){
				SwiftMergeBean bean = new SwiftMergeBean();
				bean.setSeq(seq);
				bean.setSwiftType( swiftType );
				String fieldName = swiftConfig.getFieldName().toUpperCase() ;
				bean.setFieldName( fieldName );
				String fieldTagId = swiftConfig.getId().getFieldTag();
				// Seq_of_Total No Combine.
				if ( fieldTagId.equals(AmlConfiguration.getString("SwiftWaitFieldName", "ignore_combine_tag_id")) ){
					continue;
				}
				bean.setFieldTag(fieldTagId);
				com.prowidesoftware.swift.model.Tag tag = amt.getSwiftMessage().getBlock4().getTagByName(fieldTagId);
				if ( tag == null ){
					logger.warn( "The Swift no have Tag ID({}).", fieldTagId );
					continue;
				}
				bean.setFieldValue( tag == null ? "" : tag.getValue() );

				LinkedList<SwiftMergeBean> list = map.get(fieldName);
				if ( CollectionUtils.isEmpty(list) ){
					list = new LinkedList<SwiftMergeBean>();
					list.add(bean);
					map.put(fieldName, list);
				} else {
					list.add(bean);
				}
			}
		}

		combineFieldValue(map);
		return map;
	}

	/**
	 * 確認每個Swift Merge Bean中的Value是否需要進行Combine
	 * @param map
	 */
	private void combineFieldValue(Map<String, LinkedList<SwiftMergeBean>> map){
		logger.debug("Process Field Value Combine.");
		for ( String fieldName : map.keySet() ){
			LinkedList<SwiftMergeBean> beanList = map.get(fieldName);
			// Map<FieldName, FieldValue>
			Map<String, String> tagValueMap = new HashMap<String, String>();
			for ( SwiftMergeBean bean : beanList){
				String fieldValue = tagValueMap.get(fieldName);
				if ( fieldValue == null ){
					tagValueMap.put(fieldName, bean.getFieldValue());
				} else {
					tagValueMap.put(fieldName, checkAndCombinSwiftMergeBeanValue( fieldValue, bean.getFieldValue() ) );
				}
			}

			for ( SwiftMergeBean bean : beanList){
				bean.setFieldValue( tagValueMap.get(fieldName) );
			}
		}
	}

	/**
	 * 判斷是否需將兩組字串合併
	 * @param s1
	 * @param s2
	 * @return
	 */
	private String checkAndCombinSwiftMergeBeanValue(String s1, String s2){
		if ( s1.equals(s2) ) {
			return s1;
		} else {
			return String.format("%s %s", s1,s2);
		}
	}
	
	/**
	 * List<swiftWaitRecord>利用Sequence_of_Total進行排序
	 */
	static class swiftSort implements Comparator<Object>{
		@Override
		public int compare(Object paramT1, Object paramT2) {
			// TODO Auto-generated method stub
			SwiftWaitRecord swr1 = (SwiftWaitRecord) paramT1;
			SwiftWaitRecord swr2 = (SwiftWaitRecord) paramT2;
			String s1 = swr1.getSeqOfTotal();
			String s2 = swr2.getSeqOfTotal();
			int s1Count = Integer.parseInt( s1.split(SwiftMtConst.SEQUENCE_OF_TOTAL_SPLIT)[0] );
			int s2Count = Integer.parseInt( s2.split(SwiftMtConst.SEQUENCE_OF_TOTAL_SPLIT)[0] );
			return s1Count - s2Count;
		}
	}

	/**
	 * 將需等待之Wait Swift存入Database(SWIFT_WAIT_RECORD)
	 * @param input
	 * @param swiftRje
	 * @param groupUniqueKey
	 * @param seqOfTotal
	 * @param documentaryCreditNumber
	 */
	@SuppressWarnings("unused")
	private void saveWaitSwift(SwiftCheckInputBean input,String swiftType, String swiftRje, String groupUniqueKey, String seqOfTotal, String documentaryCreditNumber ){
		SwiftWaitRecordPK pk = new SwiftWaitRecordPK();
		pk.setGroupUniqueKey(groupUniqueKey);
		pk.setUniqueKey(input.getUniqueKey());

		SwiftWaitRecord swRecord = new SwiftWaitRecord(input, swiftRje);
		swRecord.setId(pk);
		swRecord.setSwiftType(swiftType);
		swRecord.setSeqOfTotal(seqOfTotal);
		swRecord.setDocumentaryCreditNumber(documentaryCreditNumber);
		swRecord.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		swRecord.setCheckStatus("N");
		saveWaitSwift(swRecord);
		logger.debug("Save Swift Wait to Database");
	}

	private void saveWaitSwift(SwiftWaitRecord swiftWaitRecord){
		iSwiftWaitRecordDao.save(swiftWaitRecord);
	}
	
	/**
	 * 取得Documentary Credit Number
	 * @param amt
	 * @param swiftType
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getDocumentaryCreditNumber(AbstractMT amt, String swiftType){
		String preSwiftType = swiftType.substring(0, 4);
		List<SwiftConfig> swiftConfigs = iSwiftConfigDao.findByFieldNameAndIdSwiftTypeIgnoreCase(
				AmlConfiguration.getString("SwiftWaitFieldName", "documentary_credit_number"), swiftType);
		String fieldTag = swiftConfigs.get(0).getId().getFieldTag();
		logger.debug("Documentary Credit Number Field Tag : " + fieldTag );
		if ( "Mt70".equalsIgnoreCase(preSwiftType) ){
			logger.debug("The Swift is Mt70X series");
			return amt.getSwiftMessage().field(fieldTag).getValue();
		} else if ( "Mt71".equalsIgnoreCase(preSwiftType) || "Mt72".equalsIgnoreCase(preSwiftType) )  {
			logger.debug("The Swift is Mt71X or Mt72X series");
			return "";
		} else {
			logger.debug("The Swift Type is no define.");
			return "";
		}
	}

	/**
	 * 取得Sequence_of_Total
	 * @param amt
	 * @param swiftType
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getSequenceOfTotal(AbstractMT amt, String swiftType){
		List<SwiftConfig> swiftConfigs = iSwiftConfigDao.findByFieldNameAndIdSwiftTypeIgnoreCase(
				AmlConfiguration.getString("SwiftWaitFieldName", "seq_of_total"), swiftType);
		String fieldTag = swiftConfigs.get(0).getId().getFieldTag();
		return amt.getSwiftMessage().field(fieldTag).getValue();
	}

	/**
	 * 確認所有 Sequence_of_Total 是否一致
	 * @param swiftWaitRecords
	 * @param seqOfTotal
	 * @return
	 */
	@SuppressWarnings("unused")
	private String checkSequenceOfTotleMatch(List<SwiftWaitRecord> swiftWaitRecords, String seqOfTotal){
		logger.debug("Check Sequence Of Totle Column.");
		String limitSwiftCount =  seqOfTotal.split(SwiftMtConst.SEQUENCE_OF_TOTAL_SPLIT)[1];
		List<String> countList = new ArrayList<String>();
		//測試是否總數一致與內容
		for ( SwiftWaitRecord swr : swiftWaitRecords ){
			String[] swrSwiftSOT =  swr.getSeqOfTotal().split(SwiftMtConst.SEQUENCE_OF_TOTAL_SPLIT);
			String swrCount =  swrSwiftSOT[0];
			String swrTotalCount =  swrSwiftSOT[1];
			if( !limitSwiftCount.equals(swrTotalCount) ){
				//總數不一致
				return "The sequence of total is not match.";
			} else if ( countList.indexOf(swrCount) > -1 ) {
				//重複出現
				return "Tag 20 is duplicate";
			} else {
				countList.add(swrCount);
			}
		}
		return "";
	}

	/**
	 * 確認電文數量是否到齊
	 * @param swiftWaitRecords
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean checkSequenceOfTotleCount(List<SwiftWaitRecord> swiftWaitRecords){
		logger.debug("Check Sequence Of Totle Count.");
		String seqOfTotal = swiftWaitRecords.get(0).getSeqOfTotal();
		// "1/2" -> ["1","2"] -> "2" -> 2
		int totleSwiftCount = Integer.parseInt( seqOfTotal.split(SwiftMtConst.SEQUENCE_OF_TOTAL_SPLIT)[1] );

		if ( (swiftWaitRecords.size() ) < totleSwiftCount ){
			return false ;
		}
		return true;
	}
	
	/**
	 * 實作SWIFT電文判斷
	 * @param input
	 * @param swiftRje
	 * @param swiftCheckOutputBean
	 * @param referenceId
	 */
	@SuppressWarnings("unused")
	private void doSwiftCheck(SwiftCheckInputBean input, String swiftRje, SwiftCheckOutputBean swiftCheckOutputBean, Integer referenceId, long id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		AbstractMT amt;
		SwiftCheckRecord swiftCheckRecord;
		String interfaceName = SwiftMtConst.INTERFACE_TYPE_SWIFTCHECK;//input.getInterfaceName();
		String callingSystem = input.getCallingSystem();
		String screenProcess = input.getScreenProcess();
		String callingUser = NameCheckUtil.alignmentEmpNo(input.getCallingUser());
		String businessUnitId = input.getBusinessUnitId();
		String branchNumber = input.getBranchNumber();
		String transactionDate = input.getTransactionDate();
		String uniqueKey = input.getUniqueKey();
		String swiftType = "";
		String ncResult = "";
		String routeRule = "";
//		Integer referenceId = null;
		String partyNumber = input.getPartyNumber();
		String genAlertFlag = input.getGenAlertFlag();
		Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap= xWatchlistCompressString();
		try {
			boolean isOutgoing = isOutgoingSwift(swiftRje);
			
			List<SwiftHitRecordBean> swiftHitRecordBeanList = new ArrayList<SwiftHitRecordBean>();
			List<SwiftHitRecord> swiftHitRecordList = new ArrayList<SwiftHitRecord>();
			amt = AbstractMT.parse(swiftRje);
			
			String variant = amt.getMtId().getVariant() == null ? "" : amt.getMtId().getVariant();
			swiftType = String.format("Mt%s%s", amt.getMessageType(), variant);
			Map<String, List<SwiftConfigBean>> swiftConfigBeanListMap = new HashMap<String, List<SwiftConfigBean>>();
			List<SwiftConfig> swiftConfigList = iSwiftConfigDao.findByIdSwiftType(swiftType);
			Map<String, String> swiftTagFieldNameMap = new HashMap<String, String>();
			for(SwiftConfig swiftConfig: swiftConfigList){
				swiftTagFieldNameMap.put(swiftConfig.getId().getFieldTag(), swiftConfig.getFieldName());
				List<SwiftConfigBean> beanList = swiftConfigBeanListMap.get(swiftConfig.getFieldName());
				if(beanList == null){
					beanList = new ArrayList<SwiftConfigBean>();
				}
				beanList.add(new SwiftConfigBean(swiftConfig));
				swiftConfigBeanListMap.put(swiftConfig.getFieldName(), beanList);
			}
			//sender and reciver bank
			String senderBic = amt.getSender();
			String reciverBic = amt.getReceiver();
			logger.debug(String.format("senderBic : [%s] length : %s, reciverBic = [%s] length : %s", senderBic, senderBic.length(), reciverBic, reciverBic.length()));
			
			Map<String, SwiftConfigBean> swiftConfigBeanMap = saveToSwiftMt(swiftType, amt, swiftConfigBeanListMap, uniqueKey, referenceId);
			Map<String, List<String>> toFuzzyListMap = new HashMap<String, List<String>>();
			Map<String, String> screenFieldToSelectMap = new HashMap<String, String>();
			Map<String, String> screenFieldToSelectFuzzyMap = new HashMap<String, String>();
			Map<String, String> screenCountryCityMap = new HashMap<String, String>();
			Map<String, BicCodeBean> screenBicCountryMap = new HashMap<String, BicCodeBean>();
			Map<String, String> screenCccCodeMap = new HashMap<String, String>();
			
			int fieldMaxLength = Integer.parseInt(AmlConfiguration.getString(SwiftMtConst.COM_SAS_ARRANGEMENTSELECT_FIELDMAXLENGTH));
			for (Map.Entry<String, SwiftConfigBean> entry : swiftConfigBeanMap.entrySet()) {
			    String key = entry.getKey();
			    SwiftConfigBean bean = entry.getValue();
			    
			    String[] fieldValues = org.apache.commons.lang.StringUtils.split(bean.getFieldValue(), SwiftMtConst.MULTIFIELDSPLIT);
			    for(String fieldValue : fieldValues) {
			    	if(fieldValue != null && fieldValue.length() > 0){
			    		String newFieldValue=StringUtils.standardrizeSwiftInput(fieldValue,xWatchlistCompressStringMap);
				    	//0:noncheck  1.Exact+Fuzzy   2.Free Format  3.(Free Format)+Fuzzy
					    if(bean.getScreenField() != null && bean.getScreenField().length() > 0 && !"0".equals(bean.getScreenField())){
					    	if("1".equals(bean.getScreenField())){
					    		//1:Exact+Fuzzy
					    		if(newFieldValue.length() < fieldMaxLength){
					    			if(toFuzzyListMap.get(newFieldValue) != null){
					    				toFuzzyListMap.get(newFieldValue).add(String.format("%s@ScreenField", key));
					    			}else{
					    				List<String> strList = new ArrayList<String>();
					    				strList.add(String.format("%s@ScreenField", key));
					    				toFuzzyListMap.put(newFieldValue, strList);
					    			}
					    		}else{
					    			logger.debug(String.format("%s ScreenField parameter need to be ?, value : %s", key, newFieldValue));
					    		}
					    	}else if("2".equals(bean.getScreenField())){
					    		//2:Free Format			    	
					    		screenFieldToSelectMap.put(String.format("%s@ScreenField", key), newFieldValue);
					    	}else if("3".equals(bean.getScreenField())){
					    		//3:(Free Format)+Fuzzy			    	
					    		screenFieldToSelectFuzzyMap.put(String.format("%s@ScreenField", key), newFieldValue);
					    	}else if("4".equals(bean.getScreenField())){
					    		//4:(Free Format : sort order)+Fuzzy			    	
					    		screenFieldToSelectFuzzyMap.put(String.format("%s@ScreenField", key), fieldValue);
					    		logger.debug(String.format("%s@ScreenField", key) + " : " + fieldValue);
					    	}
					    }
					    
					    if(bean.getScreenCountryCity()){
					    	screenCountryCityMap.put(String.format("%s@ScreenCountryCity", key), fieldValue);
					    }
					    
					    if(bean.getScreenBicCountry()){
					    	handelBicCountry(bean.getFieldTag(), bean.getFieldName(), fieldValue, screenBicCountryMap);
							handleFreeFormatBicScan(bean.getFieldTag(), bean.getFieldName(), newFieldValue, screenBicCountryMap);
					    }
					    
					    if(bean.getScreenPobox()){
					    	findPoboxRecord(uniqueKey, bean.getFieldName(), newFieldValue, swiftHitRecordList);
					    }
					    
					    if(bean.getScreenCccCode()){
					    	screenCccCodeMap.put(newFieldValue, String.format("%s@ScreenCccCode", key));
					    }
				    }
			    }
			}
			
			logger.debug(String
					.format("\n ------------------------------------------ "
							+ "screenFieldToSelectMap size : %s, toFuzzyMap size : %s, "
							+ "screenFieldToSelectFuzzyMap size : %s, screenCountryCityMap size : %s, "
							+ "getScreenBicCountry size : %s, screenCccCodeMap size : %s "
							+ " -------------------------------------------- \n",
							screenFieldToSelectMap.size(), toFuzzyListMap.size(),
							screenFieldToSelectFuzzyMap.size(), screenCountryCityMap.size(),
							screenBicCountryMap.size(), screenCccCodeMap.size()));
			
			if(DEBUGMODE){
				for (Map.Entry<String, String> entry : screenFieldToSelectFuzzyMap.entrySet()) {
					String key = entry.getKey();
					String value = screenFieldToSelectFuzzyMap.get(key);
					logger.debug(String.format("[key : %s], [value : %s]", key, value));			
				}
			}
			
			Map<String, List<String>> toSelectListMap = new HashMap<String, List<String>>();
			NameCheckUtil.setToDestinationListMap(screenFieldToSelectMap, toSelectListMap);
			NameCheckUtil.setToDestinationListMap(screenFieldToSelectFuzzyMap, toFuzzyListMap, xWatchlistCompressStringMap);
//			toFuzzyListMap = MapUtils.sortByValue(toFuzzyListMap);

			if(DEBUGMODE){
				for (Map.Entry<String, List<String>> entry : toFuzzyListMap.entrySet()) {
					String key = entry.getKey();
					List<String> value = toFuzzyListMap.get(key);
					for(String str : value){
						logger.debug(String.format("[key : %s], [value : %s]", key, str));
					}
				}
			}
			
			
			Map<String, String> fuzzyMap = new HashMap<String, String>();//getFuzzyMatchCode(toFuzzyMap);
//			Map<String, String> screenFieldFuzzyMap = new HashMap<String, String>();
			Map<String, List<String>> screenFieldFuzzyListMap = new HashMap<String, List<String>>();
			Map<String, Set<String>> fieldValueFuzzyMap = new HashMap<String, Set<String>>();
			
			Map<String, MatchCodeResultBean> fuzzyOptionMap = getFuzzyListMatchCodeOption(toFuzzyListMap, xWatchlistCompressStringMap);
			for (Map.Entry<String, MatchCodeResultBean> entry : fuzzyOptionMap.entrySet()) {
				String fuzzyKey = entry.getKey();
				MatchCodeResultBean fuzzyValue = entry.getValue();
				List<String> fieldKeyList = toFuzzyListMap.get(fuzzyKey);
				for(String fieldKey : fieldKeyList){
					if(fieldKey.indexOf("ScreenField") > 0){
						String matchcodeInd = fuzzyValue.getMatchcodeInd();
						String matchcodeOrg = fuzzyValue.getMatchcodeOrg();
						if(screenFieldFuzzyListMap.get(matchcodeInd) != null){
							screenFieldFuzzyListMap.get(matchcodeInd).add(fieldKey);
						}else{
							List<String> strList = new ArrayList<String>();
							strList.add(fieldKey);
							screenFieldFuzzyListMap.put(matchcodeInd, strList);
						}

						if(fieldValueFuzzyMap.containsKey(matchcodeInd)) {
							Set<String> valueKeySet = fieldValueFuzzyMap.get(matchcodeInd);
							if(!valueKeySet.contains(fuzzyKey)){
								fieldValueFuzzyMap.get(matchcodeInd).add(fuzzyKey);
							}
						}
						else {
							Set<String> mapValueSet = new HashSet<String>();
							mapValueSet.add(fuzzyKey);
							fieldValueFuzzyMap.put(matchcodeInd, mapValueSet);
						}
						
						if(screenFieldFuzzyListMap.get(matchcodeOrg) != null){
							screenFieldFuzzyListMap.get(matchcodeOrg).add(fieldKey);
						}else{
							List<String> strList = new ArrayList<String>();
							strList.add(fieldKey);
							screenFieldFuzzyListMap.put(matchcodeOrg, strList);
						}

						if(fieldValueFuzzyMap.containsKey(matchcodeOrg)) {
							Set<String> valueKeySet = fieldValueFuzzyMap.get(matchcodeOrg);
							if(!valueKeySet.contains(fuzzyKey)){
								fieldValueFuzzyMap.get(matchcodeOrg).add(fuzzyKey);
							}
						}
						else {
							Set<String> mapValueSet = new HashSet<String>();
							mapValueSet.add(fuzzyKey);
							fieldValueFuzzyMap.put(matchcodeOrg, mapValueSet);
						}
					}
				}
			}
			logger.debug("screenFieldFuzzyListMap : " + screenFieldFuzzyListMap.size());
			if(DEBUGMODE){
				for (Map.Entry<String, List<String>> entry : screenFieldFuzzyListMap.entrySet()) {
					String matchcode = entry.getKey();
					List<String> fieldKey = screenFieldFuzzyListMap.get(matchcode);
					for(String str : fieldKey){
						logger.debug(String.format("[fieldKey : %s], [matchcode : %s]", str, matchcode));
					}
				}
			}
			
			Map<String, String> screenCountryCityFuzzyMap = new HashMap<String, String>();
			toFuzzyListMap = new HashMap<String, List<String>>();
			
			NameCheckUtil.setToDestinationListMap(screenCountryCityMap, toFuzzyListMap);
//			toFuzzyMap = MapUtils.sortByValue(toFuzzyMap);
			
			Map<String, MatchCodeResultBean> countryCityFuzzyOptionMap = getFuzzyListMatchCodeOption(toFuzzyListMap, xWatchlistCompressStringMap);
			Map<String, Set<String>> countryCityFieldValueFuzzyMap = new HashMap<String, Set<String>>();
			for (Map.Entry<String, MatchCodeResultBean> entry : countryCityFuzzyOptionMap.entrySet()) {
				String fuzzyKey = entry.getKey();
				MatchCodeResultBean fuzzyValue = entry.getValue();
				List<String> fieldKeyList = toFuzzyListMap.get(fuzzyKey);
				for(String fieldKey : fieldKeyList){
					if(fieldKey.indexOf("ScreenCountryCity") > 0){
						String matchcodeOrg = fuzzyValue.getMatchcodeOrg();
						screenCountryCityFuzzyMap.put(matchcodeOrg, fieldKey);
						
						if(countryCityFieldValueFuzzyMap.containsKey(matchcodeOrg)) {
							Set<String> valueKeySet = countryCityFieldValueFuzzyMap.get(matchcodeOrg);
							if(!valueKeySet.contains(fuzzyKey)){
								countryCityFieldValueFuzzyMap.get(matchcodeOrg).add(fuzzyKey);
							}
						}
						else {
							Set<String> mapValueSet = new HashSet<String>();
							mapValueSet.add(fuzzyKey);
							countryCityFieldValueFuzzyMap.put(matchcodeOrg, mapValueSet);
						}
					}
				}				
			}
			
			//sort by value
//			screenFieldFuzzyMap = MapUtils.sortByValue(screenFieldFuzzyMap);
			screenCountryCityFuzzyMap = MapUtils.sortByValue(screenCountryCityFuzzyMap);
			
			logger.debug(String.format("\n ------------------------------------------- "
					+ "screenFieldFuzzyListMap size : %s, screenCountryCityFuzzyMap size : %s"
					+ " ------------------------------------------- \n", 
					screenFieldFuzzyListMap.size(), screenCountryCityFuzzyMap.size()));
			
			String unionAllSql = "";
			List<String> fieldToSelectSqlList = watchListSearchDAO.getScreenFieldToSelectList(toSelectListMap);
			if(fieldToSelectSqlList.size() > 1){
				for(String sql : fieldToSelectSqlList){
					watchListSearchDAO.getSwiftHitRecord(swiftHitRecordBeanList, sql, uniqueKey, referenceId, ConnectionProvider.getConnection(), fieldValueFuzzyMap, countryCityFieldValueFuzzyMap, swiftTagFieldNameMap);
					}
			}else{
				unionAllSql = getAllSql(unionAllSql, fieldToSelectSqlList.get(0));
			}
			
			String fieldToSelectFuzzySql = watchListSearchDAO.getScreenFieldToSelectFuzzyList(screenFieldFuzzyListMap);
			unionAllSql = getAllSql(unionAllSql, fieldToSelectFuzzySql);
			String bicCountrySql = watchListSearchDAO.getScreenBicCountry(screenBicCountryMap);
			unionAllSql = getAllSql(unionAllSql, bicCountrySql);
			String CountryCityFuzzySql = watchListSearchDAO.getCountryCityFuzzy(screenCountryCityFuzzyMap);
			unionAllSql = getAllSql(unionAllSql, CountryCityFuzzySql);
			String cccCdoeSql = watchListSearchDAO.getScreenFieldToSelect(screenCccCodeMap);
			unionAllSql = getAllSql(unionAllSql, cccCdoeSql);			
			logger.debug("unionAllSql : " + unionAllSql);
			
			if(unionAllSql.length() > 0){
				watchListSearchDAO.getSwiftHitRecord(swiftHitRecordBeanList, unionAllSql, uniqueKey, referenceId, ConnectionProvider.getConnection(), fieldValueFuzzyMap, countryCityFieldValueFuzzyMap, swiftTagFieldNameMap);
			}
			
			for(SwiftHitRecordBean swiftHitRecordBean : swiftHitRecordBeanList){
				swiftHitRecordList.add(new SwiftHitRecord(swiftHitRecordBean));
			}
			
			Map<String, String> rankOfWatchListMap = getRankOfWatchListMap();
			if(swiftHitRecordList.size() > 0){
				filterHitRecord(partyNumber, isOutgoing, screenProcess, swiftHitRecordList, fuzzyOptionMap, xWatchlistCompressStringMap);
				swiftHitRecordList = setWatchListType(swiftHitRecordList, screenProcess,rankOfWatchListMap);				
				List<SwiftHitRecord> newSwiftHitRecordList = setSwiftHitRecordListWatchListSubTypeCd(swiftHitRecordList);
				iSwiftHitRecordDao.batchSave(newSwiftHitRecordList);
				
				Map<String, String> subWatchListTypeAndWatchListTypeMappingMap = getSubWatchListTypeAndWatchListTypeMapping();
				for(SwiftHitRecord hitRecord: newSwiftHitRecordList){
					routeRule = NameCheckUtil.seriousRule(routeRule,  hitRecord.getWatchListSubTypeCd(), getSubWatchListTypeAndWatchListTypeMapping(), rankOfWatchListMap);
				}
				
				if(newSwiftHitRecordList.size() > 0){
					ncResult = SwiftMtConst.NC_RESULT_HIT;
				}else{
					ncResult = SwiftMtConst.NC_RESULT_NO_HIT;
				}
			}else{
				ncResult = SwiftMtConst.NC_RESULT_NO_HIT;
			}
			
			swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SUCCESS);
			swiftCheckOutputBean.setErrorMessage(" ");
			
			swiftCheckRecord = new SwiftCheckRecord(uniqueKey, referenceId, branchNumber, genAlertFlag, businessUnitId, callingSystem, callingUser, null, ncResult, routeRule, screenProcess, swiftType, null,partyNumber,  swiftRje);
			iSwiftCheckRecordDao.save(swiftCheckRecord);
			
			if(swiftHitRecordList.size() > 0){
				//create case for swift check
				BigDecimal caseRK = null;
				String sourceType = NameCheckUtil.getSourceType(interfaceName, screenProcess);
				boolean isCreateIncident = false;
				CreateCaseInputBean createCaseInputBean = new CreateCaseInputBean(String.valueOf(referenceId), callingSystem, callingUser, 
						branchNumber, routeRule, transactionDate, 
						"1", partyNumber, "", 
						uniqueKey, sourceType, isCreateIncident,
						screenProcess);
				
				boolean needCreateCase = SwiftMtConst.CREATE_CASE_N.equalsIgnoreCase(genAlertFlag);
				if(SwiftMtConst.CREATE_CASE_Y.equalsIgnoreCase(genAlertFlag)) {
					CallingSystemAction callingSystemAction = iCallingSystemActionDao.findOne(new CallingSystemActionPK(callingSystem, "*"));
					if (callingSystemAction != null) {
						String dbNeedCreateCase = callingSystemAction.getCreateCase();
						if (StringUtils.isEmpty(dbNeedCreateCase) == false) {
							needCreateCase = SwiftMtConst.CREATE_CASE_Y.equalsIgnoreCase(dbNeedCreateCase);
						}
					}
				}
				
				logger.debug("needCreateCase ::: "+needCreateCase);
				//20180110 still waiting BOT judge to create case or incident.
				if (needCreateCase) {
					caseRK = amlCreateCase.createCase(createCaseInputBean);
					ncResult = SwiftMtConst.NC_RESULT_PENDING;
//					amlCreateCase.createIncident(caseRK, createCaseInputBean);
					swiftCheckRecord.setNcResult(ncResult);
					swiftCheckRecord.setCaseRk(caseRK.longValue());
					iSwiftCheckRecordDao.save(swiftCheckRecord);
					
					logger.debug("================ StoredProcedureMailUtil Begin ==============");
					SimpleJdbcCall jdbcCall = new SimpleJdbcCall(amlJpaCfg.amlDataSource()).withCatalogName("ECM").withProcedureName("USP_INS_XMaillist_ECMCase");
					jdbcCall.withoutProcedureColumnMetaDataAccess();
					jdbcCall.declareParameters(new SqlParameter("DEPT_NO", Types.VARCHAR),
							new SqlParameter("EMP_ID", Types.VARCHAR), 
							new SqlParameter("SEND_FLG", Types.VARCHAR),
							new SqlParameter("CASE_RK", Types.VARCHAR));
					MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("DEPT_NO", branchNumber);
					Map<String, Object> params=new HashMap<String, Object>();
					logger.debug("================ StoredProcedureMailUtil Param =============="
							+ "DEPT_NO ::: " + branchNumber + ",EMP_ID ::: " + callingUser + ", CASE_RK ::: " + caseRK.toString());
				    params.put("DEPT_NO", branchNumber);
				    params.put("EMP_ID", callingUser);
				    params.put("SEND_FLG", "SC");
				    params.put("CASE_RK", caseRK.toString());
				    mapSqlParameterSource.addValues(params);
				    logger.debug("================ execute() ==============");
					// Execute the stored procedure
					Map<String, Object> out =  jdbcCall.execute(mapSqlParameterSource);
//					for (Object key : out.keySet()) {
//						logger.debug(key + " : " + out.get(key));
//			        }
					logger.debug("================ StoredProcedureMailUtil End ==============");
				}
			}
			
		} catch (IOException ioe){
			logger.error(String.format("parse swift message fail, exception : %s", ioe.getMessage()), ioe);
			swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_FORMATERR);
			swiftCheckOutputBean.setErrorMessage(ioe.getMessage());
			ncResult = SwiftMtConst.NC_RESULT_NON_CHECK;
			routeRule = "";
			
			logger.error(String.format("NameCheck fail, exception : %s", ioe.getMessage()), ioe);
			XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.saveEntityToXRetryEventQueue(SwiftMtConst.SOURCE_TYPE_RT_SC, SwiftCheckInputBean.class.getName(),   
					input, HttpMethod.POST, AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) +  context.getContextPath() + "/rest/retrySwiftCheck", 
					3, "retrySwiftCheck", id, QueueConstraint.QUEUE_EVENT_STATUS_WAITING, false, "");
			if(xRetryEventQueue == null) {
				logger.error("Retry id insert fail");
			}
		} catch (Exception e) {
			logger.error(String.format("parse swift message fail, exception : %s", e.getMessage()), e);
			swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SYSTEMERR);
			swiftCheckOutputBean.setErrorMessage(e.getMessage());
			ncResult = SwiftMtConst.NC_RESULT_NON_CHECK;
			routeRule = "";
			
			logger.error(String.format("NameCheck fail, exception : %s", e.getMessage()), e);
			XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.saveEntityToXRetryEventQueue(SwiftMtConst.SOURCE_TYPE_RT_SC, SwiftCheckInputBean.class.getName(),   
					input, HttpMethod.POST, AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) +  context.getContextPath() + "/rest/retrySwiftCheck", 
					3, "retrySwiftCheck", id, QueueConstraint.QUEUE_EVENT_STATUS_WAITING, false, "");
			if(xRetryEventQueue == null) {
				logger.error("Retry id insert fail");
			}
		} finally {
			//check Format
			swiftCheckOutputBean.setInterfaceName(interfaceName);
			swiftCheckOutputBean.setNcReferenceId(String.valueOf(referenceId));
			swiftCheckOutputBean.setNcResult(ncResult);
			swiftCheckOutputBean.setRouteRule(routeRule);
			swiftCheckOutputBean.setUniqueKey(uniqueKey);
		}
	}
	
	/**
	 * 將SwiftWaitRecord清單中的Unique Key進行合併，並使用逗號相隔
	 * @param swiftWaitRecords
	 * @return
	 */
	@SuppressWarnings("unused")
	private String combineUniqueKey(List<SwiftWaitRecord> swiftWaitRecords){
		List<String> uniqueKeyList = new LinkedList<String>(); 
		for (SwiftWaitRecord swiftWaitRecord : swiftWaitRecords){
			uniqueKeyList.add( swiftWaitRecord.getId().getUniqueKey() );
		}
		return org.apache.commons.lang.StringUtils.join(uniqueKeyList,",");
	}
	
	/**
	 * 	2018.2.22 jerry SwiftHitRecord 大分類=小分類
	 */
	public 	List<SwiftHitRecord> setSwiftHitRecordListWatchListSubTypeCd(List<SwiftHitRecord> swiftHitRecordList){
		List<SwiftHitRecord> newSwiftHitRecordList = swiftHitRecordList;
		for(SwiftHitRecord newSwiftHitRecord : newSwiftHitRecordList){
			newSwiftHitRecord.setWatchListSubTypeCd(newSwiftHitRecord.getWatchListTypeCd());
		}				
		return newSwiftHitRecordList;
	}
	/**
	 * 2018.02.22 jerry
	 * 
	 * 將X_WATCHLIST_COMPRESS_STRING的資料依照個人法人和符號分類存成map
	 */
	public Map<String, List<XWatchlistCompressString>> xWatchlistCompressString(){
		Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap = new HashMap<String, List<XWatchlistCompressString>>();
		List<XWatchlistCompressString> allCompressStringList= iXWatchlistCompressStringDao.findAll();
		List<XWatchlistCompressString> CorpCompressStringList=new ArrayList<XWatchlistCompressString>();
		List<XWatchlistCompressString> IndCompressStringList=new ArrayList<XWatchlistCompressString>();
		List<XWatchlistCompressString> SignCompressStringList=new ArrayList<XWatchlistCompressString>();	
		if(allCompressStringList != null && allCompressStringList.size() > 0){
			for(XWatchlistCompressString xWatchlistCompressString:allCompressStringList){
				if("CORP".equalsIgnoreCase(xWatchlistCompressString.getCompressType())){
					CorpCompressStringList.add(xWatchlistCompressString);
				}else if("IND".equalsIgnoreCase(xWatchlistCompressString.getCompressType())){
					IndCompressStringList.add(xWatchlistCompressString);
				}else if("SIG".equalsIgnoreCase(xWatchlistCompressString.getCompressType())){
					SignCompressStringList.add(xWatchlistCompressString);
				}
			}
		}
		xWatchlistCompressStringMap.put("CORP", CorpCompressStringList);
		xWatchlistCompressStringMap.put("IND", IndCompressStringList);
		xWatchlistCompressStringMap.put("SIG", SignCompressStringList);
		
		return xWatchlistCompressStringMap;
	}	
	/**
	 * 剔除交易組合白名單
	 * @param partyNumber
	 * @param nameHitRecordList
	 */
	@SuppressWarnings("unused")
	private void filterXComboWhitelist(String partyNumber, List<SwiftHitRecord> swiftHitRecordList){
		List<XComboWhitelistMain> xComboWhitelistMainList = iXComboWhitelistMainDao.findByPartyNumber(partyNumber);
		Map<String, XComboWhitelistMain> xComboWhitelistMainMap = new HashMap<String, XComboWhitelistMain>();
		for(XComboWhitelistMain main :  xComboWhitelistMainList){
			xComboWhitelistMainMap.put(main.getBeneficiaryEntityWatchListKey(), main);
		}
		
		Iterator<SwiftHitRecord> iterator = swiftHitRecordList.iterator();
		int count = 0;
		while(iterator.hasNext()){
			SwiftHitRecord swiftHitRecord = iterator.next();
			XComboWhitelistMain main = xComboWhitelistMainMap.get(String.valueOf(swiftHitRecord.getEntityWatchListKey()));
			if(main != null){
				iterator.remove();
			}
			count++;
		}
	}
	
	/*
	 * 取得名單大小類的對應資訊
	 * */
	private Map<String, String> getSubWatchListTypeAndWatchListTypeMapping() {
		Map<String, String> resultMap = new HashMap<String, String>();
		List<RefTableValue> watchListTypeCdList = iRefTableValueDao
				.findByIdRefTableNm(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);
		for(RefTableValue value : watchListTypeCdList){
			resultMap.put(value.getId().getValueCd(), value.getRefTableValue().getId().getValueCd());
		}
		return resultMap;
	}
	
	/**
	 * 取得名單大類的顯示順序，來代表嚴重程度
	 */
	private Map<String, String> getRankOfWatchListMap(){
		Map<String, String> resultMap = new HashMap<String, String>();
		List<RefTableValue> watchListTypeCdList = iRefTableValueDao.findByIdRefTableNm(SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);
		for(RefTableValue value : watchListTypeCdList){
			resultMap.put(value.getId().getValueCd(), value.getDisplayOrderNo().toString());
		}
		
		return resultMap;
	}
	
	
	/**
	 * 1.過濾非Screening Process查詢的名單  2.並找出名單類型(WatchListTypeCd) 與名單子類型(WatchListSubTypeCd)
	 * @param swiftHitRecordList
	 * @param ScreenProcessTypeCd
	 */
	private List<SwiftHitRecord> setWatchListType(List<SwiftHitRecord> swiftHitRecordList, String ScreenProcessTypeCd,Map<String, String> rankOfWatchListMap){
		List<SwiftHitRecord> resultList = new ArrayList<SwiftHitRecord>();
		List<String> entityWatchListNumberList = new ArrayList<String>();
		for(SwiftHitRecord swiftHitRecord : swiftHitRecordList){
			if(!entityWatchListNumberList.contains(swiftHitRecord.getEntityWatchListNumber())){
				entityWatchListNumberList.add(swiftHitRecord.getEntityWatchListNumber());
			}
		}
		
		List<String> watchlistSubTypeCdList = new ArrayList<String>();
		List<XScreenProcessSetting> screenProcessList =  new ArrayList<XScreenProcessSetting>();

		if(SwiftMtConst.SCREEN_PROCESS_BATCH_SWIFT_SCREENING.equals(ScreenProcessTypeCd)){ //夜批需要排除白天有掃過的名單
			//撈取 Batch Transaction(ScreenProcess=09) 
			List<XScreenProcessSetting> sourcexScreenProcessSettingList = iXScreenProcessSettingDao.findByChangeCurrentIndAndDeleteIndAndIdScreenProcessTypeCd(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.SCREEN_PROCESS_BATCH_SWIFT_SCREENING);
		
			//Batch Transaction(ScreenProcess=05) & 整理需要排除的 watchListSubTypeCd
			List<XScreenProcessSetting> excludeXScreenProcessSettingList = iXScreenProcessSettingDao.findByChangeCurrentIndAndDeleteIndAndIdScreenProcessTypeCd(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, SwiftMtConst.SCREEN_PROCESS_SWIFT_Screening);
		
			Set<String> excludeSet = new HashSet<String>();
			for(XScreenProcessSetting be : excludeXScreenProcessSettingList){
				excludeSet.add(be.getId().getWatchListSubTypeCd());
			}
			
			for(XScreenProcessSetting be : sourcexScreenProcessSettingList){
				if(!excludeSet.contains(be.getId().getWatchListSubTypeCd())){
					screenProcessList.add(be);
				}
			}
		}else{ // RealTime使用的
			screenProcessList = iXScreenProcessSettingDao.findByChangeCurrentIndAndDeleteIndAndIdScreenProcessTypeCd(SwiftMtConst.CHANGE_CURRENT_IND_Y, SwiftMtConst.DELETE_IND_N, ScreenProcessTypeCd);
		}
		for(XScreenProcessSetting screenProcess : screenProcessList){
			if(!watchlistSubTypeCdList.contains(screenProcess.getId().getWatchListSubTypeCd())){
				watchlistSubTypeCdList.add(screenProcess.getId().getWatchListSubTypeCd());
			}
		}
		
		Map<String, List<XFscEntityWlXListSetting>> setMap = new HashMap<String, List<XFscEntityWlXListSetting>>();
		if(entityWatchListNumberList != null && entityWatchListNumberList.size() > 0){
//			List<FscEntityWlXListSet> setList = iFscEntityWlXListSetDao.findByIdChangeCurrentIndAndEntityWatchListNumberIn(SwiftMtConst.CHANGE_CURRENT_IND, entityWatchListNumberList);
			int limit = 2000;		
			if(entityWatchListNumberList.size() > limit){
				int i = 0;
				List<String> tmpList = new ArrayList<String>();
				for(String number : entityWatchListNumberList){
					i++;			
					tmpList.add(number);				
					if(i % limit == 0 || i == entityWatchListNumberList.size()){
						//FIXME
						List<XFscEntityWlXListSetting> setList = iFscEntityWlXListSetDao.findByIdChangeCurrentIndAndEntityWatchListNumberInAndWatchListSubTypeCdIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, tmpList, watchlistSubTypeCdList);
				
						for(XFscEntityWlXListSetting set : setList){
							String entityWatchListNumber=set.getEntityWatchListNumber();
							if(setMap.get(entityWatchListNumber)==null){
								List<XFscEntityWlXListSetting> setMapResultList = new ArrayList<XFscEntityWlXListSetting>();
								setMapResultList.add(set);
								setMap.put(set.getEntityWatchListNumber(), setMapResultList);
							}else{
								List<XFscEntityWlXListSetting> oldResultList = setMap.get(entityWatchListNumber);
								oldResultList.add(set);
								setMap.put(set.getEntityWatchListNumber(), oldResultList);
							}
						}
						tmpList = new ArrayList<String>();
					}
				}
			}else{
			List<XFscEntityWlXListSetting> setList = iFscEntityWlXListSetDao.findByIdChangeCurrentIndAndEntityWatchListNumberInAndWatchListSubTypeCdIn(SwiftMtConst.CHANGE_CURRENT_IND_Y, entityWatchListNumberList, watchlistSubTypeCdList);
			
			for(XFscEntityWlXListSetting set : setList){
					String entityWatchListNumber=set.getEntityWatchListNumber();
					if(setMap.get(entityWatchListNumber)==null){
						List<XFscEntityWlXListSetting> setMapResultList = new ArrayList<XFscEntityWlXListSetting>();
						setMapResultList.add(set);
						setMap.put(set.getEntityWatchListNumber(), setMapResultList);
					}else{
						List<XFscEntityWlXListSetting> oldResultList = setMap.get(entityWatchListNumber);
						oldResultList.add(set);
						setMap.put(set.getEntityWatchListNumber(), oldResultList);
					}
				}
			}
		}
		
		if(setMap != null){
			for(SwiftHitRecord swiftHitRecord : swiftHitRecordList){
				List<XFscEntityWlXListSetting> setList = setMap.get(swiftHitRecord.getEntityWatchListNumber());
				if(setList != null){
					String type = "";
					String watchListTypeCd = "";
					String watchListSubTypeCd = "";		
					for(XFscEntityWlXListSetting set : setList){
					
						if(type == null || "".equals(type)){
							type = set.getEntityTypeCd();
						}else{
							if(type.indexOf(set.getEntityTypeCd()) < 0) {
								type += SwiftMtConst.SUB_TYPE_CD_SPLIT+set.getEntityTypeCd();
							}
						}
						
						if(watchListTypeCd == null || "".equals(watchListTypeCd)){
							watchListTypeCd = set.getWatchListTypeCd();
						}else{
							watchListTypeCd = NameCheckUtil.sortRule(watchListTypeCd,set.getWatchListTypeCd(),rankOfWatchListMap,SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_TYPE_CD);				

						}
						
						if(watchListSubTypeCd == null || "".equals(watchListSubTypeCd)){

							watchListSubTypeCd = set.getWatchListSubTypeCd();
						}else{
							watchListSubTypeCd = NameCheckUtil.sortRule(watchListSubTypeCd,set.getWatchListSubTypeCd(),rankOfWatchListMap,SwiftMtConst.REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD);	
						}
					}
					
					swiftHitRecord.setTypeDesc(type);
					swiftHitRecord.setWatchListTypeCd(watchListTypeCd);
					swiftHitRecord.setWatchListSubTypeCd(watchListSubTypeCd);
					resultList.add(swiftHitRecord);
				}
			}
		}
	
		
		return resultList;
	}
	
	/**
	 * 組合SQL語法工具程式
	 * @param unionAllSql
	 * @param addSql
	 * @return
	 */
	private String getAllSql(String unionAllSql, String addSql){
		if(unionAllSql != null && unionAllSql.trim().length() > 0){
			if(addSql != null && addSql.trim().length() > 0){
				unionAllSql += " UNION ALL " + addSql;
			}
		}else{
			unionAllSql = addSql;
		}
		return unionAllSql;
	}
	
	/**
	 * 儲存命中名單於紀錄檔
	 * @param messageType
	 * @param amt
	 * @param swiftConfigBeanMap
	 * @param uniqueKey
	 * @param referenceId
	 * @retur Map<String, SwiftConfigBean>
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 */
	private Map<String, SwiftConfigBean> saveToSwiftMt(String messageType, AbstractMT amt, Map<String, List<SwiftConfigBean>> swiftConfigBeanMap, String uniqueKey, int referenceId) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException{
		String className = String.format("com.sas.db.wlf.orm.sc.Swift%s", messageType);
		Class<?> clazz = Class.forName(className);
		Object obj = Class.forName(className).newInstance();
		Map<String, SwiftConfigBean> resultBeanMap = new HashMap<String, SwiftConfigBean>();
		
		for(java.lang.reflect.Field field : clazz.getDeclaredFields()){
			if(!field.isAccessible()){
				field.setAccessible(true);
			}
			for (Annotation annotation : field.getDeclaredAnnotations()) {
	            Class<? extends Annotation> type = annotation.annotationType();
	            if("javax.persistence.Column".equalsIgnoreCase(type.getName())){
	            	Method nameMethod = type.getDeclaredMethod("name");
		            Object nameValue = nameMethod.invoke(annotation, (Object[])null);
		            if(nameValue!=null) {
		            	logger.debug("1.nameValue != null ::: " + String.valueOf(nameValue));
		            	nameValue = String.valueOf(nameValue).toUpperCase();
		            }
		            List<SwiftConfigBean> swiftConfigBeanList = swiftConfigBeanMap.get(nameValue);
		            
		            if(swiftConfigBeanList != null && swiftConfigBeanList.size() > 0){
		            	logger.debug("3.swiftConfigBeanList != null");
		            	StringBuffer messageValue = new StringBuffer();
		            	for(SwiftConfigBean swiftConfigBean : swiftConfigBeanList){
		            		logger.debug("4. swiftConfigBean:::"+swiftConfigBean.toString());
		            		List<com.prowidesoftware.swift.model.field.Field> messageFields = amt.getSwiftMessage().fields(swiftConfigBean.getFieldTag());
			            	if(messageFields != null && messageFields.size() > 0){
			            		logger.debug("5. messageFields:::"+messageFields);
			            		String fieldValue = "";
				            	StringBuffer sb = new StringBuffer();
				            	for(com.prowidesoftware.swift.model.field.Field messageField : messageFields){
				            		sb.append(" ").append(WatchListUtil.convertWordTo(messageField.getValue(), SwiftMtConst.LINEFEED, " "));
				            	}
				            	if(sb.toString().startsWith(SwiftMtConst.MULTIFIELDSPLIT)){
				            		fieldValue = sb.substring(SwiftMtConst.MULTIFIELDSPLIT.length());
				            		messageValue.append(sb);
				            	}else{
				            		fieldValue = sb.toString().trim();
				            		messageValue.append(sb);
				            	}
				            	logger.debug("messageValue::::"+messageValue);
				            	logger.debug(String.format("%s (%d) : [ %s ] ", swiftConfigBean.getFieldTag(), fieldValue.length(), fieldValue) );
				            	sb = null;
				            	logger.debug(swiftConfigBean.getFieldTag() + " : [" + messageValue+"]");
				            	swiftConfigBean.setFieldValue(fieldValue);
				            	resultBeanMap.put(swiftConfigBean.getFieldTag(), swiftConfigBean);
			            	}
		            	}
		            	if(messageValue.toString().startsWith(SwiftMtConst.MULTIFIELDSPLIT)){
		            		field.set(obj, messageValue.substring(SwiftMtConst.MULTIFIELDSPLIT.length()));
		            	}else{
		            		field.set(obj, messageValue.toString().trim());
		            	}
		            }else{
		            	logger.debug(String.format("%s do not set in SwiftConfig table.", nameValue));
		            	if("CREATE_BY".equals(nameValue)){
		            		field.set(obj, SwiftMtConst.CREATE_BY_SYSTEM);
		            	}else if("CREATED_TIMESTAMP".equals(nameValue)){
		            		field.set(obj, new Timestamp(System.currentTimeMillis()));
		            	}else if("SWIFT_FULL_TEXT".equals(nameValue)){
		            		field.set(obj, amt.message());
		            	}
		            }
	            }else if("javax.persistence.EmbeddedId".equalsIgnoreCase(type.getName())){
	            	String idClassName = String.format("com.sas.db.wlf.orm.sc.Swift%sPK", messageType);
	            	logger.debug("idClassName::::"+idClassName);
	        		Class<?> idClazz = Class.forName(idClassName);
	        		Object idObj = Class.forName(idClassName).newInstance();
	            	
	            	for(java.lang.reflect.Field idField : idClazz.getDeclaredFields()){
	            		if(!idField.isAccessible()){
	            			idField.setAccessible(true);
	        			}
	            		for (Annotation idAnnotation : idField.getAnnotations()) {
		            		Class<? extends Annotation> idType = idAnnotation.annotationType();
		            		if("javax.persistence.Column".equalsIgnoreCase(idType.getName())){
		            			logger.debug("idType.getName():::"+idType.getName());
		            			Method nameMethod = idType.getDeclaredMethod("name");
		    		            Object nameValue = nameMethod.invoke(idAnnotation, (Object[])null);
		    		            if("UNIQUE_KEY".equals(nameValue)){
		    		            	idField.set(idObj, uniqueKey);
				            	}else if("REFERENCE_ID".equals(nameValue)){
				            		idField.set(idObj, referenceId);
				            	}
		            		}
		            	}
	            	}
	            	Method idMethod = clazz.getMethod("setId", idClazz);
	            	logger.debug("idMethod.getName() : " + idMethod.getName());
	            	idMethod.invoke(obj, idObj);
	            }
	        }
		}
		
		doSave(messageType, obj);
		logger.debug(String.format("save to db : %s", obj.toString()));
		return resultBeanMap;
	}
	
    /**
     * 實作儲存命中名單於紀錄檔
     * @param messageType
     * @param obj
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
	private void doSave(String messageType, Object obj) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		logger.info(String.format("com.sas.db.wlf.dao.sc.ISwift%sDao", messageType));
		Class<?> daoClazz = Class.forName(String.format("com.sas.db.wlf.dao.sc.ISwift%sDao", messageType));
		Object daoObj = ctx.getBean(daoClazz);
		Method saveMethod = daoClazz.getMethod("save", java.lang.Object.class);
		if(!saveMethod.isAccessible()){
			saveMethod.setAccessible(true);
		}
		saveMethod.invoke(daoObj, obj);
	}
	
	/**
	 * PoBox字串處理
	 * @param rawStr
	 * @return List<String> 
	 */
	public List<String> containsPOBOX(String rawStr){
		List<String> result = new ArrayList<String>();
		String pobox = AmlConfiguration.getString(SwiftMtConst.COM_SAS_SWIFT_POBOX);
		if(pobox != null && pobox.length() > 0 && rawStr != null){
			if(pobox.contains(",")){
				for(String str : pobox.split(",")){
					if(rawStr.toUpperCase().contains(str.toUpperCase())){
						result.add(str);
					}
				}
			}else{
				if(rawStr.toUpperCase().contains(pobox.toUpperCase())){
					result.add(pobox);
				}
			}
		}
		return result;
	}
	
	/**
	 * 檢查是否有PoBox內容處理
	 * @param uniqueKey
	 * @param fieldName
	 * @param fieldValue
	 * @param swiftHitRecordList
	 */
	private void findPoboxRecord(String uniqueKey, String fieldName, String fieldValue, List<SwiftHitRecord> swiftHitRecordList){
		for(String pobox : containsPOBOX(fieldValue)){
    		SwiftHitRecord swiftHitRecord = new SwiftHitRecord();
    		SwiftHitRecordPK id = new SwiftHitRecordPK();
    		swiftHitRecord.setId(id);
    		id.setUniqueKey(uniqueKey);
    		id.setSeq(String.valueOf(swiftHitRecordList.size()+1));
    		swiftHitRecord.setWatchListModel(SwiftMtConst.WATCH_LIST_MODEL_POBOX);
    		swiftHitRecord.setFieldName(fieldName);
    		swiftHitRecord.setFieldValue(fieldValue);
    		swiftHitRecord.setPoBox(pobox);
    		
    		swiftHitRecordList.add(swiftHitRecord);
    	}
	}
	
	/**
	 * BIC Code & Country 欄位拆解處理
	 * @param fieldTag
	 * @param fidleName
	 * @param fieldValue
	 * @param screenBicCountryMap
	 */
	private void handelBicCountry(String fieldTag, String fidleName, String fieldValue, Map<String, BicCodeBean> screenBicCountryMap){
		BicCodeBean bicCodeBean = new BicCodeBean();
		String prefix = "3/";
    	if(fieldTag.toUpperCase().endsWith("A")){
			bicCodeBean.setBicCode(fieldValue);
			if(fieldValue != null && fieldValue.length() >= 6){
				bicCodeBean.setCountryCode(fieldValue.substring(4, 6));
			}
    	}else if(fieldTag.toUpperCase().endsWith("F") && fieldValue.indexOf(prefix) > 0){
    		int prefixIndex = fieldValue.indexOf(prefix);
    		if(fieldValue != null && fieldValue.length() >= (prefixIndex + 4)){
    			bicCodeBean.setCountryCode(fieldValue.substring(prefixIndex + 2, prefixIndex + 4));
    		}
    	}else{
    		bicCodeBean.setBicCode(fieldValue);
			bicCodeBean.setCountryCode(fieldValue);
    	}
    	screenBicCountryMap.put(String.format("%s@ScreenBicCountry", fieldTag), bicCodeBean);
	
	}
	
	/**
	 * BIC Code & Country freetext 欄位拆解處理
	 * @param fieldTag
	 * @param fidleName
	 * @param fieldValue
	 * @param screenBicCountryMap
	 */
	private void handleFreeFormatBicScan(String fieldTag, String fidleName, String fieldValue,
			Map<String, BicCodeBean> screenBicCountryMap) {
		BicCodeBean bicCodeBean = null;
		if(screenBicCountryMap.get(String.format("%s@ScreenBicCountry", fieldTag)) != null) {
			bicCodeBean=screenBicCountryMap.get(String.format("%s@ScreenBicCountry", fieldTag));
		} else {
			bicCodeBean = new BicCodeBean();
		}
		if (!fieldTag.toUpperCase().endsWith("A") && !(fieldTag.toUpperCase().endsWith("F"))) {
			if(fieldValue != null) {
				fieldValue=StringUtils.lianxuStr(fieldValue);
				if(bicCodeBean.getCountryCode() == null) {
					bicCodeBean.setCountryCode(fieldValue);
				}
				bicCodeBean.setBicCode(fieldValue);
			}
		}
		screenBicCountryMap.put(String.format("%s@ScreenBicCountry", fieldTag), bicCodeBean);
	}
	
	/**
	 * 名稱模糊化轉換(呼叫Dataflux工具)
	 * @param conditionMap
	 * @return Map<String, String>
	 */
	private Map<String, String> getFuzzyMatchCode(Map<String, String> conditionMap) throws Exception{
		Map<String, String> fuzzyMatchMap = new HashMap<String, String>();
		List<RowIn> rowInList =  new ArrayList<RowIn>();
		int i = 0;
		for(String key : conditionMap.keySet()){
			RowIn rowIn = new RowIn();
			rowIn.setChName("");
			rowIn.setEnName(key);
			rowInList.add(rowIn);
			
			i++;
			//dataflux max rowin number : 50000 for each request
			if(i % 50000 == 0 || i == conditionMap.size()){
				
				List<Map<String, String>> datafluxMatchCodeList = dataFluxMatchCode.datafluxMatchCodeList(rowInList);
				
				for(Map<String, String> matchCodeMap : datafluxMatchCodeList){
					fuzzyMatchMap.putAll(matchCodeMap);
				}
				rowInList =  new ArrayList<RowIn>();
			}
		}
		
		return fuzzyMatchMap;
	}
	
	/**
	 * 讀取或產生 Reference ID
	 * @param uniqueKey
	 * @return Integer
	 */
	private Integer getOrGenerateReferenceId(String uniqueKey) {
		List<KeyGenerate> keys = iKeyGenerateDao.findByUniqueKeyOrderByProcessDttmDesc(uniqueKey);
		if (keys == null || keys.isEmpty()) {
			KeyGenerate keyGenerate = new KeyGenerate();
			keyGenerate.setUniqueKey(uniqueKey);
			keyGenerate.setInterfaceType(SwiftMtConst.INTERFACE_TYPE_SWIFTCHECK);
			keyGenerate.setProcessDttm(new Date());
			iKeyGenerateDao.save(keyGenerate);
			return keyGenerate.getReferenceId();
		}
		else {
			return keys.get(0).getReferenceId();
		}
	}

	/**
	 * 有存在 uniqueKey 嗎？
	 * 
	 * @param uniqueKey
	 * @return Integer
	 */
	private Integer getReferenceId(String uniqueKey) {
		List<KeyGenerate> keys = iKeyGenerateDao.findByUniqueKeyOrderByProcessDttmDesc(uniqueKey);
		if (keys == null || keys.isEmpty()) {
			return null;
		} else {
			return keys.get(0).getReferenceId();
		}
	}

	/**
	 * 產生 Reference ID
	 * 
	 * @param uniqueKey
	 * @return Integer
	 */
	private Integer generateReferenceId(String uniqueKey) {
		KeyGenerate keyGenerate = new KeyGenerate();
		keyGenerate.setUniqueKey(uniqueKey);
		keyGenerate.setInterfaceType(SwiftMtConst.INTERFACE_TYPE_SWIFTCHECK);
		keyGenerate.setProcessDttm(new Date());
		iKeyGenerateDao.save(keyGenerate);
		return keyGenerate.getReferenceId();
	}
	
	/**
	 * BOT AML SWIFT ASYNC CHECK Interface 主程式
	 */
	@Override
	@SuppressWarnings("unused")
	@Transactional
	public SwiftCheckOutputBean SwiftCheck(final SwiftCheckInputBean input) {
		logger.info("##################### SwiftCheck Begin #################\n");
		Date currentDateTime = new Date();
		Timestamp currentTimestamp = new Timestamp(currentDateTime.getTime());
		SwiftLogTime swiftLogTime = new SwiftLogTime();
		swiftLogTime.setUniqueKey(input.getUniqueKey());
		swiftLogTime.setTime1(currentTimestamp);
		iSwiftLogTimeDao.save(swiftLogTime);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SwiftCheckOutputBean swiftCheckOutputBean = new SwiftCheckOutputBean();

		String interfaceName = SwiftMtConst.INTERFACE_TYPE_SWIFTCHECK;
		String callingSystem = input.getCallingSystem();
		String screenProcess = input.getScreenProcess();
		String branchNumber = input.getBranchNumber();
//		String transactionDate = sdf.format(new Date());
		String replaceWord = String.format("}}%s{1:", SwiftMtConst.RLPLACE_MULTI_SWIFT_SPLIT_PATTERN);
		String swiftRje = input.getSwiftRje().replaceAll(SwiftMtConst.MULTI_SWIFT_SPLIT_PATTERN, replaceWord);
		String uniqueKey = input.getUniqueKey();
		String genAlertFlag = getCreateCaseFlag(callingSystem);
		logger.info("SwiftCheck Input Content :::" + ToStringBuilder.reflectionToString(input));

		input.setGenAlertFlag(genAlertFlag);
		
		if (chkFields(interfaceName, callingSystem, screenProcess, branchNumber, uniqueKey, swiftRje)) {
			swiftCheckOutputBean.setNcReferenceId("");
			swiftCheckOutputBean.setUniqueKey(uniqueKey);
			swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_FORMATERR);
			swiftCheckOutputBean.setErrorMessage(SwiftMtConst.ERROR_CODE_FORMATERR_MESSAGE);
			logger.info("chkFields error " );
			return swiftCheckOutputBean;
		}

		try {
			Integer referenceId = getReferenceId(uniqueKey);
			if (referenceId != null) {
				swiftCheckOutputBean.setInterfaceName(interfaceName);
				swiftCheckOutputBean.setNcReferenceId(String.valueOf(referenceId));
				swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_UNIQUE_KEY_CONFLICT);
				swiftCheckOutputBean.setErrorMessage(String.format("Unique Key : %s already in used.", uniqueKey));
				swiftCheckOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
				swiftCheckOutputBean.setUniqueKey(uniqueKey);
				swiftCheckOutputBean.setRouteRule("");
				logger.info("referenceId != null ::::" + referenceId);
				return swiftCheckOutputBean;
			}
			referenceId = generateReferenceId(uniqueKey);
			swiftCheckOutputBean.setInterfaceName(interfaceName);
			swiftCheckOutputBean.setNcReferenceId(String.valueOf(referenceId));
			swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SUCCESS);
			swiftCheckOutputBean.setErrorMessage("");
			swiftCheckOutputBean.setUniqueKey(uniqueKey);
			
			
			swiftLogTime.setNcreferenceId(referenceId);
			

			//非同步執行第二階段，並將結果透過 Web Service 回傳taskAsyncExecutor Swallow
			swiftAsyncCheck.doAsyncSwiftCheck(input, 0);
			logger.info("============ 主線程 :::: "+ Thread.currentThread().getName() +"===============\n" );
		} catch (Exception e) {
			logger.error(String.format("parse swift message fail, exception : %s", e.getMessage()), e);
			swiftCheckOutputBean.setInterfaceName(interfaceName);
			swiftCheckOutputBean.setErrorCode(SwiftMtConst.ERROR_CODE_SYSTEMERR);
			swiftCheckOutputBean.setErrorMessage(e.getMessage());
			swiftCheckOutputBean.setNcResult(SwiftMtConst.NC_RESULT_NON_CHECK);
			swiftCheckOutputBean.setUniqueKey(uniqueKey);
			swiftCheckOutputBean.setNcReferenceId("");
			swiftCheckOutputBean.setRouteRule("");
			XRetryEventQueue xRetryEventQueue = xRetryEventQueueService.saveEntityToXRetryEventQueue(SwiftMtConst.SOURCE_TYPE_RT_NC, SwiftCheckInputBean.class.getName(),   
					input, HttpMethod.POST, AmlConfiguration.getString(QueueConstraint.COM_SAS_BASE_URL) +  context.getContextPath() + "/rest/retrySwiftCheck", 
					3, "retrySwiftCheck", 0, QueueConstraint.QUEUE_EVENT_STATUS_WAITING, false, "");
			if(xRetryEventQueue == null) {
				logger.error("Retry id insert fail");
			}
		}
		
		currentDateTime = new Date();
		currentTimestamp = new Timestamp(currentDateTime.getTime());
		swiftLogTime.setTime2(currentTimestamp);
		iSwiftLogTimeDao.save(swiftLogTime);
		
		logger.info("\n ##################### SwitchCheck Response ##################### \n");
		return swiftCheckOutputBean;
	}
	
	private String getCreateCaseFlag(String callingSystem){
		String result = SwiftMtConst.CREATE_CASE_N;
		CallingSystemAction callingSystemAction = iCallingSystemActionDao.findOne(new CallingSystemActionPK(callingSystem, "*"));
		if (callingSystemAction != null) {
			result = callingSystemAction.getCreateCase();
		}
		return result;
	}

	/**
	 * 名稱模糊化轉換(呼叫Dataflux工具)
	 * @param conditionMap
	 * @return Map<String, MatchCodeResultBean>
	 */
	private Map<String, MatchCodeResultBean> getFuzzyMatchCodeOption(Map<String, String> conditionMap, Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap){
		Map<String, MatchCodeResultBean> fuzzyMatchMap = new HashMap<String, MatchCodeResultBean>();
		List<com.dataflux.wsdl.archserver.option.RowIn> rowInList =  new ArrayList<com.dataflux.wsdl.archserver.option.RowIn>();
		int i = 0;
		XWatchlistSetting xWatchlistSetting = iXWatchlistSettingDao.findByChangeCurrentInd(SwiftMtConst.CHANGE_CURRENT_IND_Y);
		String sensitivity = String.valueOf(xWatchlistSetting.getFuzzyMatchingSenstive().setScale(0, BigDecimal.ROUND_HALF_UP));
		int dqMaxNumber = 5000;
		for(String key : conditionMap.keySet()){
			com.dataflux.wsdl.archserver.option.RowIn rowIn = new com.dataflux.wsdl.archserver.option.RowIn();
			String sigFieldValue = StringUtils.CompressStringByEntityType(null, key, null, xWatchlistCompressStringMap).get("en");
			if(NameCheckUtil.shortTokenStr(sigFieldValue) || NameCheckUtil.shortStr(sigFieldValue)){
				MatchCodeResultBean bean = new MatchCodeResultBean();
				bean.setMatchcodeInd(sigFieldValue);
				bean.setMatchcodeOrg(sigFieldValue);
				fuzzyMatchMap.put(key, bean);
			}else{
				rowIn.setSensitivity(sensitivity);
				rowIn.setChName("");
				rowIn.setEnName(key);
				rowInList.add(rowIn);
			}
			
			i++;
			//dataflux max rowin number : 50000 for each request
			if(i % dqMaxNumber == 0 || i == conditionMap.size()){
				List<Map<String, MatchCodeResultBean>> datafluxMatchCodeList = dataFluxMatchCodeOption.datafluxMatchCodeList(sensitivity, rowInList);
				
				for(Map<String, MatchCodeResultBean> matchCodeMap : datafluxMatchCodeList){
					fuzzyMatchMap.putAll(matchCodeMap);
				}
				rowInList =  new ArrayList<com.dataflux.wsdl.archserver.option.RowIn>();
			}
		}
		logger.debug("fuzzyMatchMap : " + fuzzyMatchMap.size());
		return fuzzyMatchMap;
	}
	
	/**
	 * 名稱模糊化轉換(呼叫Dataflux工具)
	 * @param conditionMap
	 * @return Map<String, MatchCodeResultBean>
	 */
	private Map<String, MatchCodeResultBean> getFuzzyListMatchCodeOption(Map<String, List<String>> conditionMap, Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap){
		Map<String, MatchCodeResultBean> fuzzyMatchMap = new HashMap<String, MatchCodeResultBean>();
		List<com.dataflux.wsdl.archserver.option.RowIn> rowInList =  new ArrayList<com.dataflux.wsdl.archserver.option.RowIn>();
		int i = 0;
		XWatchlistSetting xWatchlistSetting = iXWatchlistSettingDao.findByChangeCurrentInd(SwiftMtConst.CHANGE_CURRENT_IND_Y);
		String sensitivity = String.valueOf(xWatchlistSetting.getFuzzyMatchingSenstive().setScale(0, BigDecimal.ROUND_HALF_UP));
		int dqMaxNumber = 5000;
		for(String key : conditionMap.keySet()){
			com.dataflux.wsdl.archserver.option.RowIn rowIn = new com.dataflux.wsdl.archserver.option.RowIn();
			String sigFieldValue = StringUtils.CompressStringByEntityType(null, key, null, xWatchlistCompressStringMap).get("en");
			if(NameCheckUtil.shortTokenStr(sigFieldValue) || NameCheckUtil.shortStr(sigFieldValue)){
				MatchCodeResultBean bean = new MatchCodeResultBean();
				bean.setMatchcodeInd(sigFieldValue);
				bean.setMatchcodeOrg(sigFieldValue);
				fuzzyMatchMap.put(key, bean);
			}else{
				rowIn.setSensitivity(sensitivity);
				rowIn.setChName("");
				rowIn.setEnName(key);
				rowInList.add(rowIn);
			}
			
			i++;
			//dataflux max rowin number : 50000 for each request
			if(i % dqMaxNumber == 0 || i == conditionMap.size()){
				List<Map<String, MatchCodeResultBean>> datafluxMatchCodeList = dataFluxMatchCodeOption.datafluxMatchCodeList(sensitivity, rowInList);
				
				for(Map<String, MatchCodeResultBean> matchCodeMap : datafluxMatchCodeList){
					fuzzyMatchMap.putAll(matchCodeMap);
				}
				rowInList =  new ArrayList<com.dataflux.wsdl.archserver.option.RowIn>();
			}
		}
		logger.debug("fuzzyMatchMap : " + fuzzyMatchMap.size());
		return fuzzyMatchMap;
	}
	
	/**
	 * 為提升效能，重購過濾swiftHitRecordList，避免重複執行迴圈
	 * @param swiftHitRecordList
	 * @param fuzzyOptionMap
	 */
	private void filterHitRecord(String partyNumber, boolean isOutgoing, String screenProcess, List<SwiftHitRecord> swiftHitRecordList, Map<String, MatchCodeResultBean> fuzzyOptionMap, Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap){
		Iterator<SwiftHitRecord> iterator = swiftHitRecordList.iterator();
//		Map<String, SwiftHitRecord> swiftHitRecordMd5Map = new HashMap<String, SwiftHitRecord>();
		List<String> md5List = new ArrayList<String>();
		while(iterator.hasNext()){
			SwiftHitRecord swiftHitRecord = iterator.next();
			MatchCodeResultBean fuzzyBean = fuzzyOptionMap.get(swiftHitRecord.getFieldValue());
			if(filterByTokenDifsAndFulFillDifs(swiftHitRecord, xWatchlistCompressStringMap) ||
					filterXComboWhitelist(partyNumber, isOutgoing, screenProcess, swiftHitRecord) ||
					filterByTypeDesc(swiftHitRecord)){
				iterator.remove();
			}
		}
	}
	
	private boolean filterByTokenDifsAndFulFillDifs(SwiftHitRecord swiftHitRecord, Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap){
		boolean result = false;
		String fieldValue = swiftHitRecord.getFieldValue();
		String fieldValues[] = fieldValue.split(SwiftMtConst.RLPLACE_MULTI_SWIFT_SPLIT_PATTERN);
		List<String> fieldValueList = new ArrayList<String>();
		String sigFieldValue = StringUtils.CompressStringByEntityType(null, swiftHitRecord.getEntityNameDisplay(), null, xWatchlistCompressStringMap).get("en");
		String sigFieldValues[] = sigFieldValue.split(" ");
		String tokenDiff = AmlConfiguration.getString(SwiftMtConst.COM_SAS_SWIFTCHECK_TOKEN_DIFFERENCENUMBER);
		int diffNum = "".equals(tokenDiff) ? 2 : Integer.parseInt(tokenDiff);
//		logger.debug("fieldValue : " + fieldValue + ", sigFieldValue : " + sigFieldValue + ", tokenDiff : " + diffNum);
		if(!"Y".equalsIgnoreCase(swiftHitRecord.getNospace_name_ind())){
			for(int i = 1; i < fieldValues.length; i++){
				boolean isNeed = false;
				String temStr = StringUtils.CompressStringByEntityType(null, fieldValues[i], null, xWatchlistCompressStringMap).get("en");
				String values[] = temStr != null ? temStr.split(" ") : new String[0];
				//filter by token difference
//				logger.debug(String.format("token difference ----- diffNum : %s, values length : %s, sigFieldValues length : %s", diffNum, values.length, sigFieldValues.length));
				logger.debug(String.format("value : %s, length : %s, sigFieldValue : %s, length : %s" , temStr, values.length, sigFieldValue, sigFieldValues.length));
				if(temStr != null && Math.abs(values.length - sigFieldValues.length) > diffNum){
					logger.debug(String.format("%s > %s", Math.abs(values.length - sigFieldValues.length), diffNum));
					logger.debug(swiftHitRecord.toString());
					isNeed = true;
				}else if (values.length == sigFieldValues.length && NameCheckUtil.isFulFillDifference(temStr.toUpperCase(), sigFieldValue.toUpperCase())){
					logger.debug("isFulFillDifference : true");
					logger.debug(swiftHitRecord.toString());
					isNeed = true;
				}else {
					
				}
				
				if(!isNeed){
					fieldValueList.add(fieldValues[i]);
				}
			}
			
			if(fieldValueList.size() == 0){
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * 剔除交易組合白名單
	 * @param partyNumber
	 * @param swiftHitRecordList
	 */
	private boolean filterXComboWhitelist(String partyNumber, boolean isOutgoing, String screenProcess, SwiftHitRecord swiftHitRecord){
		boolean result = false;
		Map<String, XComboWhitelistMain> xComboWhitelistMainMap = getXComboWhitelistMap(partyNumber);
		XComboWhitelistMain main = xComboWhitelistMainMap.get(String.valueOf(swiftHitRecord.getEntityWatchListKey()));
		if(isOutgoing && !StringUtils.isEmpty(partyNumber) && main != null && 
				(SwiftMtConst.SCREEN_PROCESS_Transaction_Screening.equals(screenProcess) || 
						SwiftMtConst.SCREEN_PROCESS_Trade_Finance_Screening.equals(screenProcess))){
			result = true;
		}
		return result;
	}
	
	/**
	 * 剔除交易組合白名單
	 * @param partyNumber
	 * @param swiftHitRecordList
	 */
	private Map<String, XComboWhitelistMain> getXComboWhitelistMap(String partyNumber){
		List<XComboWhitelistMain> xComboWhitelistMainList = iXComboWhitelistMainDao.findByPartyNumber(partyNumber);
		Map<String, XComboWhitelistMain> xComboWhitelistMainMap = new HashMap<String, XComboWhitelistMain>();
		for(XComboWhitelistMain main :  xComboWhitelistMainList){
			xComboWhitelistMainMap.put(main.getBeneficiaryEntityWatchListKey(), main);
		}
		return xComboWhitelistMainMap;
	}
	
	private boolean filterByTypeDesc(SwiftHitRecord swiftHitRecord){
		boolean result = false;
		String fieldValue = swiftHitRecord.getFieldValue();
//		String fieldValues[] = fieldValue.split(SwiftMtConst.RLPLACE_MULTI_SWIFT_SPLIT_PATTERN);
//		String entityNameDisplay = swiftHitRecord.getEntityNameDisplay();
		String typeDesc = swiftHitRecord.getTypeDesc() != null ? swiftHitRecord.getTypeDesc() : "";
		String watchListModle = swiftHitRecord.getWatchListModel();
		//typeDesc : 03, 04
		if("ScreenField".equalsIgnoreCase(watchListModle) && typeDesc.indexOf(SwiftMtConst.ENTITY_TYPE_CORP) > 0 && fieldValue.toUpperCase().indexOf("BANK") > 0){
			result = true;
		}
		return result;
	}
}
