package com.sas.constraint;


/**
 * Global properties name settings
 * @author SAS
 *
 */
public class SwiftMtConst {
	//DEVELOPER NEED TO CHANGE DATABASE ENG FOR SIT/UAT/PROD
	public static final String COM_SAS_JPACFG_PROPERTIES = "jpaCfg";
	
	public static final String COM_SAS_JPACFG_AML_CATALOG = "AML";
	public static final String COM_SAS_JPACFG_AML_ECM_SCHEMA = "ECM";
	public static final String COM_SAS_JPACFG_AML_FCFCORE_SCHEMA = "FCFCORE";
	public static final String COM_SAS_JPACFG_AML_FCFKC_SCHEMA="FCFKC";
	public static final String COM_SAS_JPACFG_NCSC_CATALOG = "AML";
	public static final String COM_SAS_JPACFG_NCSC_SCHEMA = "NCSC";
	public static final String COM_SAS_JPACFG_AML_ECM_CASE_RK_SEQ_INC = "ECM.case_rk_seq_inc";
	public static final String COM_SAS_JPACFG_AML_ECM_CASE_RK_SEQ_NEXT = "ECM.case_rk_seq_next";
	public static final String COM_SAS_JPACFG_AML_ECM_INCIDENT_RK_SEQ_INC = "ECM.incident_rk_seq_inc";
	public static final String COM_SAS_JPACFG_AML_ECM_INCIDENT_RK_SEQ_NEXT= "ECM.incident_rk_seq_next";

	//global properties name settings
	public static final String COM_SAS_DATAFLUX_USERNAME = "com.sas.dataFlux.userName";
	public static final String COM_SAS_DATAFLUX_PASSWORD = "com.sas.dataFlux.password";
	public static final String COM_SAS_DATAFLUX_WSDLFILEPATH = "com.sas.dataFlux.wsdlFilePath";
	public static final String COM_SAS_DATAFLUXOPTION_WSDLFILEPATH = "com.sas.dataFluxOption.wsdlFilePath"; //2018.04.17 新加入
	public static final String COM_SAS_WORKFLOW_WSDLFILEPATH = "com.sas.workflow.wsdlFilePath";
	public static final String COM_SAS_ARRANGEMENTSELECT_LIMITINDEX = "com.sas.arrangementSelect.limitIndex";
	public static final String COM_SAS_ARRANGEMENTSELECT_MAXINDEX = "com.sas.arrangementSelect.maxIndex";
	public static final String COM_SAS_ARRANGEMENTSELECT_FIELDMAXLENGTH = "com.sas.arrangementSelect.fieldMaxLength";
	public static final String COM_SAS_STANDARDIZATION_CHINESE = "com.sas.standardization.chinese";
	public static final String COM_SAS_STANDARDIZATION_ENGLISH = "com.sas.standardization.english";
	public static final String COM_SAS_SWIFT_POBOX = "com.sas.swift.pobox";
	public static final String COM_SAS_SWIFTCHECKREPORT_WSDLFILEPATH = "com.sas.swiftCheckReport.wsdlFilePath";
	public static final String COM_SAS_SWIFTCHECK_WSDLFILEPATH = "http://localhost:8080/AmlCheck/ws/amlSwiftCheck?wsdl";
	public static final String COM_SAS_SWIFTCHECKREPORT_URL = "com.sas.swiftCheckReport.url";
	public static final String COM_SAS_SWIFTCHECK_URL = "com.sas.swiftCheck.url";
	public static final String COM_EAI_SERVICE_URL = "com.eai.service.url";
	public static final String COM_EAI_SERVICE_MSGID = "com.eai.service.msgid";	
	
	public static final String COM_SAS_SWIFT_ENTITY_WL_X_LIST_IS_USING_EXCLUDE_IND = "com.sas.swift.entity.wl.x.list.is.using.exclude.ind";
	public static final String COM_SAS_NAMECHECK_ENTITY_WL_X_LIST_IS_USING_EXCLUDE_IND = "com.sas.namecheck.entity.wl.x.list.is.using.exclude.ind";
	public static final String COM_SAS_BATCHNAMECHECK_ENTITY_WL_X_LIST_IS_USING_EXCLUDE_IND = "com.sas.batchnamecheck.entity.wl.x.list.is.using.exclude.ind";
	
	public static final String COM_SAS_NAMELISTSCREENING_SCREENPROCESS = "com.sas.nameListScreening.screenProcess";
	public static final String COM_SAS_TRANSCATIONSCREENING_SCREENPROCESS = "com.sas.transactionScreening.screenProcess";
	
	public static final String COM_SAS_SWIFTCHECK_TOKEN_DIFFERENCENUMBER = "com.sas.swiftCheck.token.differenceNumber";
	
	// 20180418 加入Damerau Levenshtein Algorithm 差異度參數
	public static final String COM_SAS_DAMERAU_LEVENSHTEIN_PARAMETER = "com.sas.damerau.levenshtein.parameter";
	public static final String COM_SAS_DAMERAU_LEVENSHTEIN_DELETECOST = "com.sas.damerau.levenshtein.deleteCost";
	public static final String COM_SAS_DAMERAU_LEVENSHTEIN_INSERTCOST = "com.sas.damerau.levenshtein.insertCost";
	public static final String COM_SAS_DAMERAU_LEVENSHTEIN_REPLACECOST = "com.sas.damerau.levenshtein.replaceCost";
	public static final String COM_SAS_DAMERAU_LEVENSHTEIN_SWAPCOST = "com.sas.damerau.levenshtein.swapCost";
	
	public static final String COM_SAS_DAMERAU_LEVENSHTEIN_SWIFTCHECK_HITWORDRATIO = "com.sas.damerau.levenshtein.swiftCheck.hitWordRatio";
	
	// 2018012 新增Charset Deteched Collection
	public static final String COM_SAS_CHARSET_DETECHED = "com.sas.detech.charsetlist";
	public static final String COM_SAS_CHARSET_DETECHED_BIG5_WEIGHT_SCORE = "com.sas.detech.decode.big5.weight.score";
	public static final String COM_SAS_CHARSET_DETECHED_UNICODE_WEIGHT_SCORE = "com.sas.detech.decode.unicode.weight.scroe";
	public static final String COM_SAS_CHARSET_DETECHED_DEAFULT_DECODE = "com.sas.detech.deafult.decode";
	//Multi swift message split pattern
	public static final String MULTI_SWIFT_SPLIT_PATTERN = "\\}\\}\\{1:";
	public static final String RLPLACE_MULTI_SWIFT_SPLIT_PATTERN = "!@!";
	//Swift message replace line feed from "\n" to "" �A�g�JDB
	public static final String[] LINEFEED = {"\r\n", "\n\r", "\n", "\r"};
	public static final String REPLACELINEFEED = "!@!";
	//Swift message some field is repetitive need to add "" �H���Ϲj,�A�g�JDB
	public static final String MULTIFIELDSPLIT = "!@!";
	//"P O Box", "PO Box", "P.O. Box", "P. O. Box", "GPO Box", "Box", "Postbus"
	public static final String[] POBOX = {"P O BOX", "PO BOX", "P.O. BOX", "P. O. BOX", "GPO BOX", "BOX", "POSTBUS"};

	public static final String [] COVERTWORD = {"~","!","@","#","$","%","^","&","*","(",")","_","+","`","-","=","{","}","|","[","]","\\",":","<",">","?",",",".","1/","2/","3/","/"};
	public static final String [] LR_TRIM_WORD = {"\\s*$","^\\s*"};
	//Error Code 0: ���\ 1: �榡���~ 2: �t�ο��~
	public static final String ERROR_CODE_SUCCESS = "0";
	public static final String ERROR_CODE_FORMATERR = "1";
	public static final String ERROR_CODE_SYSTEMERR = "2";
	public static final String ERROR_CODE_UNIQUE_KEY_CONFLICT = "3";
	public static final String ERROR_CODE_NODATAFOUND = "4";
	
	public static final String ERROR_CODE_FORMATERR_MESSAGE = "Input Format Error";
	public static final String ERROR_CODE_INTERNALERR_MESSAGE = "Internal Server Error";
	
	public static final String ERROR_CODE_EMPTY_MESSAGE = "\u200b";

	//Nc Result for BOT
	public static final String NC_RESULT_NO_HIT = "00";
	public static final String NC_RESULT_HIT = "01";
	public static final String NC_RESULT_PASS = "02";
	public static final String NC_RESULT_FAIL = "03";
	public static final String NC_RESULT_NON_CHECK = "04";
	public static final String NC_RESULT_CANCELLED = "05";
	public static final String NC_RESULT_WAITING = "06";
	public static final String NC_RESULT_PENDING = "12";
	public static final String NC_RESULT_TRUE_HIT = "10";		//True Hit
	public static final String NC_RESULT_FALSE_HIT = "11";		//False Hit
	public static final String NC_RESULT_TF_NOT_ALL_READY = "13";

	public static final String NC_RESULT_101_TRUE_HIT = "True Hit";
	public static final String NC_RESULT_102_FALSE_HIT = "False Hit";
	
	//watch_list_model
	public static final String WATCH_LIST_MODEL_FIELD = "Screen Pobox";
	public static final String WATCH_LIST_MODEL_COUNTRY_CITY = "Screen Country City";
	public static final String WATCH_LIST_MODEL_BIC_COUNTRY = "Screen Bic Country";
	public static final String WATCH_LIST_MODEL_POBOX = "Screen Pobox";
	public static final String WATCH_LIST_MODEL_CCC_CODE = "Screen CCC CODE";
	//interace type
	public static final String INTERFACE_TYPE_SWIFTCHECK = "AML_SWIFTCheck";
	public static final String INTERFACE_TYPE_NAMECHECK = "AML_NameCheck";
	public static final String INTERFACE_TYPE_SWIFTCHECK_STATUS = "AML_SWIFTCheckStatus";
	public static final String INTERFACE_TYPE_NAMECHECK_STATUS = "AML_NameCheckStatus";
	public static final String INTERFACE_TYPE_BLINVSTATUS = "AML_BLInvStatus";
	public static final String INTERFACE_NAME_TAG_START = "<interface_name>";
	public static final String INTERFACE_NAME_TAG_END = "</interface_name>";
	
	//Calling System
	public static final String CALLING_SYSTEM_SWALLOW = "SWALLOW";
	
	//Terry新增20171116
	public static final String INTERFACE_TYPE_BATCHNAMECHECK = "AML_BatchNameCheck";
	public static final String INTERFACE_TYPE_BATCHSWIFTCHECK = "AML_BatchSwiftCheck";
	public static final String INTERFACE_TYPE_BATCHTRANSACTIONSCREENING = "AML_BatchTransactionScreening";
	public static final String INTERFACE_TYPE_BATCHNAMECHECKSTATUS = "AML_BatchNameCheckStatus";
	
	public static final String CREATE_BY_SYSTEM = "sasdemo";
	
	//Name Check
	public static final String NAME_CHECK_TYPE_DESC_INDIVIDUAL = "INDIVIDUAL";
	public static final String NAME_CHECK_TYPE_DESC_ORGANIZATION = "ORGANIZATION";
	public static final String NAME_CHECK_EXACT = "EXACT";
	public static final String NAME_CHECK_FUZZY = "FUZZY";
	public static final String NAME_CHECK_INCLUSIVE = "INCLUSIVE";
	public static final String NAME_CHECK_Y = "Y";
	
	public static int EXACT_MATCH_SCORE_DEFAULT = 100;
	public static int FUZZY_MATCH_SCORE_DEFAULT = 60;
	public static int INCLUSIVE_MATCH_SOCRE_DEFALUT = 0; // 
	public static int INCLUSIVE_MATCH_SOCRE_BASE = 60; // 20180208 新增暫時使用Inclusive Match Score
	public static int INCLUSIVE_MATCH_CH_NAME_TOKEN_5 = 5; // 20180611 新增，Inclusive Match Score中文五個字以內做順序性精準比對
	public static int INCLUSIVE_MATCH_EN_NAME_TOKEN_3 = 3; // 20180611 新增，Inclusive Match Score英文三個Token，做順序性比對
	public static int INCLUSIVE_MATCH_EN_NAME_TOKEN_4 = 4; // 20180611 新增，Inclusive Match Score英文三個Token，做順序性比對
	public static int INCLUSIVE_MATCH_HITWORD_COUNT_2 = 2; // 20180611 新增，Inclusive Match Score英文三個Token，做順序性比對
	
	// default weight score 
	public static int ID_MATCH_DEFAULT = 100;
	public static int COUNTRY_MATCH_DEFAULT = 5;
	public static int YEAR_MATCH_DEFAULT = 5;
	
	public static final String CHANGE_CURRENT_IND_Y = "Y";
	public static final String CHANGE_CURRENT_IND_N = "N";
	public static final String DELETE_IND_Y = "Y";
	public static final String DELETE_IND_N = "N";
	public static final String EXCLUDE_IND_Y = "Y";
	public static final String EXCLUDE_IND_N = "N";
	
	public static final String SWIFT_MAIN_CONFIG_Y = "Y";
	

	public static final String HK_DEFAULT_LOCALE = "en";
	
	public static final String TF_CASESTATUS_Y = "Y";
	public static final String TF_CASESTATUS_N = "N";
	public static final String TF_TO_ORDER ="TO ORDER";
	
	public static final String UPLOAD_TYPE_BL_IMPORT ="1";
	public static final String UPLOAD_TYPE_BL_EXPORT ="2";
	public static final String UPLOAD_TYPE_INV_IMPORT ="3";
	public static final String UPLOAD_TYPE_INV_EXPORT ="4";
	public static final String UPLOAD_TYPE_NC_MANUAL ="5";

	public static final String UPLOAD_TYPE_FTP_STANDARD_IMPORT ="7";
	public static final String UPLOAD_TYPE_FTP_REMITTANCE_IMPORT ="8";
	public static final String UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT ="9";
	
	public static final String UPLOAD_TYPE_FTP_STANDARD_IMPORT_FILE_PREFIX = "NC_";
	public static final String UPLOAD_TYPE_FTP_REMITTANCE_IMPORT_FILE_PREFIX = "NC1_";
	public static final String UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT_FILE_PREFIX = "NC2_";
	
	public static final String UPLOAD_TYPE_FTP_STANDARD_IMPORT_FILE_RESULT_PREFIX = "NCRESULT_";
	public static final String UPLOAD_TYPE_FTP_REMITTANCE_IMPORT_FILE_RESULT_PREFIX = "NC1RESULT_";
	public static final String UPLOAD_TYPE_FTP_EXREMITTANCE_IMPORT_FILE_RESULT_PREFIX = "NC2RESULT_";
	
	public static final String UPLOAD_TYPE_TRADEFINANCE_IMPORT_FILE_GENALERTFLAG = "com.sas.tradeFinanceNameCheck.input.genAlertFlag";
	public static final String UPLOAD_TYPE_TRADEFINANCE_IMPORT_FILE_CALLINGSYSTEM = "com.sas.tradeFinanceNameCheck.input.callingSystem";
	
	public static final String SCAN_TYPE_PARTY ="1";
	public static final String SCAN_TYPE_TRANSACTION ="2";
	
	//Terry 新增 
	public static final String PEP_WATCH_LIST_SUB_TYPE_CD = "CL2-09-0-01";
	public static final String PANA_WATCH_LIST_SUB_TYPE_CD = "CL2-09-0-02";
	public static final String AML_BATCH_NC = "AML_Batch_NC";
	public static final String SCAN_STATUS_Y ="Y";
	public static final String SCAN_STATUS_N ="N";

	public static final String CALLING_SYSTEM_01 ="01";
	public static final String CALLING_SYSTEM_GEB ="02";
	public static final String CALLING_SYSTEM_03 ="03";
	public static final String CALLING_SYSTEM_04 ="04";
	public static final String CALLING_SYSTEM_05 ="05";
	public static final String CALLING_SYSTEM_06 ="06";
	public static final String CALLING_SYSTEM_07 ="07";
	public static final String CALLING_SYSTEM_ELOAN ="08";
	public static final String CALLING_SYSTEM_09 ="09";
	public static final String CALLING_SYSTEM_SWIFT ="10";
	public static final String CALLING_SYSTEM_NON_SWIFT ="99";
	
	public static final String SCREEN_PROCESS_Account_Opening ="1";
	public static final String SCREEN_PROCESS_Customer_Event ="2";
	public static final String SCREEN_PROCESS_Transaction_Screening ="3";
	public static final String SCREEN_PROCESS_Trade_Finance_Screening ="4";
	public static final String SCREEN_PROCESS_SWIFT_Screening ="5";
	public static final String SCREEN_PROCESS_BATCH_NAME_CHECKING ="6";
	public static final String SCREEN_PROCESS_BATCH_TRANCTION_SCREENING ="7";
	public static final String SCREEN_PROCESS_BATCH_TRADE_FINANCE_SCREENING ="8";
	public static final String SCREEN_PROCESS_BATCH_SWIFT_SCREENING ="9";
	public static final String SCREEN_PROCESS_ONLINE_NAME_CHECKING ="10";
	public static final String SCREEN_PROCESS_ONLINE_TRADE_FINANCE_BL_SCREENING ="11";
	public static final String SCREEN_PROCESS_ONLINE_TRADE_FINANCE_INV_SCREENING ="12";
	
	public static final String SOURCE_TYPE_RT_SC = "RT-SC";
	public static final String SOURCE_TYPE_BT_SC = "BT-SC";  //Batch Swift Screengin
	public static final String SOURCE_TYPE_RT_NC = "RT-NC";
	public static final String SOURCE_TYPE_RT_TC = "RT-TC";
	public static final String SOURCE_TYPE_BT_NC = "BT-NC";  //Batch Name Check
	public static final String SOURCE_TYPE_BT_TC = "BT-TC";  //Batch Transaction Check
	public static final String SOURCE_TYPE_OL_BOL = "OL-BOL";  //線上提單掃描
	public static final String SOURCE_TYPE_OL_INV = "OL-INV";  //線上發票掃描
	public static final String SOURCE_TYPE_OL_PRICE = "OL-PRICE";  //查價案件
		
	public static final String SUB_CATEGORY_TYPE_IV_EX = "IVEX";  //Invoice Export
	public static final String SUB_CATEGORY_TYPE_IV_IM = "IVIM";  //Invoice Import
	
	public static final String NCSC_CASE_TYPE = "NCKINV";
	public static final String NCSC_CASE_UDF_TABLE_NAME = "CASE";
	public static final String NCSC_INCIDENT_TYPE = "NCKINV";
	public static final String NCSC_CASE_SOURCE_SYSTEM = "FCFBU1";
	
	public static final String NCSC_CASE_DESC_TPL = "Unique Key: %s NC Reference Id: %s ScreenFunction: %s CallingSystem: %s";//"ReferenceNo:%s ScreenProccess: %s CallingSystem: %s";
	public static final String NCSC_SC_CASE_DESC_TPL = "Unique Key: %s NC Reference Id: %s  ScreenFunction: %s SwiftType: %s CallingSystem: %s";//"ReferenceNo:%s ScreenProccess: %s CallingSystem: %s SwiftType: %s";
	public static final String NCSC_INCIDENT_DESC_TPL = "Unique Key: %s ScreenFunction: %s";//"ReferenceNo:%s ScreenProccess: %s";
	public static final String WORKFLOW_NAME = "WLFWorkflow01";
	public static final String WORKFLOW_CASE_RK_TPL = "|%s|%s";
	
	public static final String INV_IMPORT_CASE_DESC_TPL = "Invoice No:%s";
	public static final String INV_EXPORT_CASE_DESC_TPL = "Invoice No:%s";
	public static final String INV_IMPORT_CASE_CATEGORY = "BT-TC";
	
	public static final String REF_TABLE_NM_X_WATCHLIST_TYPE_CD = "X_WatchList_Type_Cd";
	public static final String REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD = "X_WatchList_Sub_Type_Cd";
	public static final String REF_TABLE_NM_X_ENTITY_TYPE_CD = "X_Entity_Type_Cd";
	public static final String REF_TABLE_NM_X_ENTITY_RELATIONSHIP = "X_Entity_Relationship";	
	public static final String REF_TABLE_NM_X_NC_RESULT = "X_NC_Result";		
	public static final String REF_TABLE_NM_X_BRANCH_LIST = "RT_BRANCH_NUMBER";	
	public static final String REF_TABLE_NM_X_CALLING_SYSTEM = "X_Case_Calling_System";	
	public static final String REF_TABLE_NM_X_SCREEN_PROCESS =	"X_Screen_Function";
	public static final String REF_TABLE_NM_X_NC_UPLOAD_TYPE = "X_NC_UPLOAD_TYPE";
	public static final String REF_TABLE_NM_X_HIT_RESULT = "X_Hit_Check_Result";

	public static final String QueryAllMap_ENTITY_RELATIONSHIP = "ENTITY_RELATIONSHIP";	
	public static final String QueryAllMap_ENTITY_TYPE = "ENTITY_TYPE";	
	
	
	public static final String CREATE_CASE_Y ="Y";
	public static final String CREATE_CASE_N ="N";
	public static final String CREATE_INCIDENT_Y ="Y";
	public static final String CREATE_INCIDENT_N ="N";
	
	public static final String UPLOAD_RECORD_STATUS_ONPROCESS = "U";
	public static final String UPLOAD_RECORD_STATUS_SUCCESS = "Y";
	public static final String UPLOAD_RECORD_STATUS_FAIL = "F";
	public static final String UPLOAD_RECORD_CSV_SPLIT = ",";
	
	
	public static final String MAIL_ATTACHMENT_Y = "Y";
	public static final String MAIL_ATTACHMENT_N = "N";
	
	public static final String COMBO_WHITE_LIST_CASE_TYPE = "COMBOWHITELIST";
	public static final String COMBO_WHITE_LIST_STOP_CASE_TYPE = "STOPCOMBOWHITELIST";
	public static final String COMBO_WHITE_LIST_CATEGORY_TYPE = "COMBO";
	public static final String COMBO_WHITE_LIST_APPROVAL = "APPROVAL";
	public static final String COMBO_WHITE_LIST_CANCEL = "CANCEL";
	public static final String COMBO_WHITE_LIST_ACTION_NEW = "NEW";
	public static final String COMBO_WHITE_LIST_ACTION_STOP = "STOP";
	public static final String COMBO_WHITE_LIST_EMPTY = "EMPTY*";
	public static final String COMBO_WHITE_LIST_CLOSE_Y = "Y";
	public static final String COMBO_WHITE_LIST_Y = "Y";
	public static final String COMBO_WHITE_LIST_N = "N";
	public static final String COMBOWHITE_SCREEN_PROP = "com.sas.case.comboWhiteList.screen";
	public static final String COMBOWHITE_SWIFT_TYPE_PROP = "com.sas.case.comboWhiteList.swiftType";
	public static final String COMBOWHITE_SWIFT_TYPE_TAG_PROP = "com.sas.case.comboWhiteList.swiftType.%s.tag";
	public static final String COMBOWHITE_NAMECHECK_RELATION_PROP = "com.sas.case.comboWhiteList.nc.relation";
	
	public static final String NC_CASE_STATUS_UNDER_INVESTIGATION = "I";
	public static final String NC_CASE_STATUS_UNDER_L1_MANAGER_REVIEW = "R1";
	public static final String NC_CASE_STATUS_UNDER_L2_MANAGER_REVIEW = "R2";
	public static final String NC_CASE_STATUS_CLOSED = "C";
	
	// Upload "BL","INV" Import Export File constants
	public static final String BL_IMPORT_SCR_NO_5410 = "5410";
	public static final String BL_IMPORT_SCR_NO_6210 = "6210";
	public static final String BL_EXPORT_SCR_NO_7220 = "7220";
	public static final String BL_EXPORT_SCR_NO_7310 = "7310";
	
	public static final String INV_IMPORT_SCR_NO_5410 = "5410";
	public static final String INV_IMPORT_SCR_NO_6210 = "6210";
	
	public static final String Err_SEQ_NUM_START = "0";
	public static final String UPLOAD_EXCEL_ROW_VALIDATE_ERR_MSG = "Validate Error, Row:[%s],Column:[%s]";
	
	// Upload Mail constants
	public static final String MAIL_ADDR_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	public static final String GUI_MSG_SPLIT = "<br>";
	public static final String MAIL_EXIST_ATTACHEMENT_N = "N";
	public static final String MAIL_EXIST_ATTACHEMENT_Y = "Y";
	public static final String MAIL_RECEIVER_SPLIT = ",";
	
	public static final String MAIL_ATTACHEMENT_UPLOAD_ERROR_MSG = "Upload File Fail. [Exception Message]: %s";
	public static final String MAIL_LENGTH_ERROR_MSG = "[%s] String Value Length Over %d. %s";
	public static final String MAIL_ADDRESS_ERROR_MSG = "Mail Address Format Error.Value = '%s'. %s";
	public static final String CHECK_UPLOAD_MAIL_EMPTY_MSG = "[%s] Validate Fail, Value Can't Be Empty or String Length over %d. %s";

	public static final String NC_PASSWORD_CRYPT_KEY = "NameCheck@SAS";
	public static final String NC_FTP_FIELD_SPLIT = "$!";
	public static final String NC_FTP_FILE_CHARSET = "BIG5";

	/* 2018 0209 FTP 國內匯款及國外匯款長度設定值 */
	public static final String BOT_NC1_FTP_REMMITTANCE_HEAD_LENGTH = "1,3,10,5,8,23,68,82,1,68";
	public static final String BOT_NC1_FTP_REMMITTANCE_DETAIL_LENGTH = "1,3,10,5,8,7,2,14,68,14,52,7,10,68";
	public static final String BOT_NC1_FTP_REMMITTANCE_END_LENGTH = "1,3,10,5,8,4,14,7,217";
	public static final String BOT_NC2_FTP_EXREMMITTANCE_LENGTH = "4,1,35,35,35,35,10,2,3,1,105,30,35,35,35,35,34,12,140";

	/* 2018 0209 FTP 國內匯款預設設定值 */
	public static final String BOT_NC_DEFAULT_CALLING_SYSTEM = "BOT";
	public static final String BOT_NC_DEFAULT_ALERT_FLAG = "Y";
	public static final String BOT_NC_DEFAULT_SCREEN_PROCESS = "3";
	public static final String BOT_NC_DEFAULT_CALLING_USER = "";
	public static final String BOT_NC_DEFAULT_PARTY_NUMBER = "";
	public static final String BOT_NC_DEFAULT_BUSINESS_UNIT_ID = "";
	public static final String BOT_NC_DEFAULT_COUNTRY = "TW";
	
	/* 2018 0209 FTP 國內匯款CHECK SEQ 預設設定值 */
	public static final String BOT_NC1_FTP_CHECKSEQ_HEAD = "00001";
	public static final String BOT_NC1_FTP_CHECKSEQ_DETAIL_UNIFORM = "00001";
	public static final String BOT_NC1_FTP_CHECKSEQ_DETAIL_BENEFICIARY = "00004";
	
	public static final String NC2_DEFAULT_CALLING_SYSTEM = "SASUL";
	public static final String NC2_DEFAULT_SCREEN_PROCESS = "3";
	public static final String BOT_NC2_FTP_CHECKSEQ_DETAIL_UNIFORM1 = "00001";
	public static final String BOT_NC2_FTP_CHECKSEQ_DETAIL_UNIFORM2= "00002";
	public static final String BOT_NC2_FTP_CHECKSEQ_DETAIL_BENEFICIARY1 = "00004";
	public static final String BOT_NC2_FTP_CHECKSEQ_DETAIL_BENEFICIARY2 = "00005";
	public static final String BOT_NC2_FTP_CHECKSEQ_DETAIL_BENEFICIARY_COUNTRY = "00008";
	
	public static final String FTP_NC_FILE_CHARSET_ERROR = "File Charset Error!";
	public static final String FTP_NC_ACCOUNT_BRANCH_NOT_EXIST_ERROR = "This user have no Branch!";
	public static final String FTP_NC_FILENAME_ERROR = "File Name Format Error!";
	public static final String FTP_NC_CONVERT_NAMECHECK_FORMAT_ERROR = "NameCheck File Format Error!";
	public static final String FTP_NC_INTERNAL_SERVER_ERROR = "NameCheck Internal Server Error!";
	public static final String FTP_NC_SAVE_RESULT_FILE_ERROR = "Save FTP Result File Error!";
	public static final String FTP_NC_OUTPUT_NAMECHECK_EMPTY_ERROR = "Output NameCheck Result Error!";
	
	
	public static final String CASE_VIEW_MODE_ROLE = "AML_ROLE_1";
	public static final String SWIFT_INCOMING = "Incoming";
	public static final String SWIFT_OUTGOING = "Outgoing";
	
	public static final String SWIFT_WAIT_UNIQUEKEY_PREFIX = "GROUP-";

	// 2018012 新增ENTITY TYPE數值對應，用於字串標準化
	public static final String ENTITY_TYPE_ALL = "01";
	public static final String ENTITY_TYPE_IND = "02";
	public static final String ENTITY_TYPE_CORP = "03";
	public static final String ENTITY_TYPE_BANK = "04";
	public static final String ENTITY_TYPE_PORT = "05";	
	public static final String ENTITY_TYPE_BOAT = "06";
	public static final String ENTITY_TYPE_GOOD = "07";
	public static final String ENTITY_TYPE_COUNTRY = "08";
	public static final String ENTITY_TYPE_INDANDCORP = "09";
	public static final String ENTITY_TYPE_FREEFPRMAT = "99";
	
	public static final String SUB_TYPE_CD_SPLIT = ",";	
    public static final String ENTITY_TYPE_CD_SPLIT = ","; 
	public static final String SEQUENCE_OF_TOTAL_SPLIT = "/";	
	
	public static final String COMPRESSSTRING_CORP = "CORP";
	public static final String COMPRESSSTRING_IND = "IND";
	public static final String COMPRESSSTRING_SIG = "SIG";
	
	public static final String CASE_RESULT_TRUEFIT = "T"; 
    public static final String CASE_RESULT_FALSEHIT = "F";
	
    //2018/06/11 new add for dm service request time out setting
  	public static final int COM_SUN_XML_INTERNAL_WS_CONNECT_TIMEOUT_MS = 10000;
  	public static final int COM_SUN_XML_INTERNAL_WS_REQUEST_TIMEOUT_MS = 30000;
  	public static final int JAVAX_XML_WS_CLIENT_RECEIVETIMEOUT_MS = 600000;
  	public static final int JAVAX_XML_WS_CLIENT_CONNECTIONTIMEOUT_MS = 600000;
  	
  	//FTP SERVER INFO
  	public static final String COM_SAS_FTP_SERVER = "com.sas.ftp.server";
	public static final String COM_SAS_FTP_PORT = "com.sas.ftp.port";
	public static final String COM_SAS_FTP_USER = "com.sas.ftp.user";
	public static final String COM_SAS_FTP_PASSWORD = "com.sas.ftp.password";
	
	//FTP NC SERVER INFO
  	public static final String COM_SAS_FTP_NC_SERVER = "com.sas.ftp.nc.server";
	public static final String COM_SAS_FTP_NC_PORT = "com.sas.ftp.nc.port";
	public static final String COM_SAS_FTP_NC_USER = "com.sas.ftp.nc.user";
	public static final String COM_SAS_FTP_NC_PASSWORD = "com.sas.ftp.nc.password";
	
	//FTP NC BATCH SERVER INFO
  	public static final String COM_SAS_FTP_NCBATCH_SERVER = "com.sas.ftp.ncbatch.server";
	public static final String COM_SAS_FTP_NCBATCH_PORT = "com.sas.ftp.ncbatch.port";
	public static final String COM_SAS_FTP_NCBATCH_USER = "com.sas.ftp.ncbatch.user";
	public static final String COM_SAS_FTP_NCBATCH_PASSWORD = "com.sas.ftp.ncbatch.password";
	public static final String COM_SAS_FTP_NCBATCH_UPLOADFOLER = "com.sas.ftp.ncbatch.uploadFolder";
	public static final String COM_SAS_FTP_NCBATCH_FILENAME = "com.sas.ftp.ncbatch.filename";
  	
  	//FTP Standard Name Check
	public static final String COM_SAS_FTP_SCHUDLE_ENABLE = "${com.sas.ftp.standard.namecheck.schedule.enable}";
	public static final String COM_SAS_FTP_TRIGGER_RATE = "${com.sas.ftp.standard.namecheck.trigger.rate}";
  	public static final String COM_SAS_FTP_LOCALURI = "com.sas.ftp.localURI";
  	public static final String COM_SAS_FTP_LOCALRESULTURI = "com.sas.ftp.localResultURI";
  	public static final String COM_SAS_FTP_LOCALRESULTOKURI = "com.sas.ftp.localResultOkURI";
  	public static final String COM_SAS_FTP_LOCALRESULTNGURI = "com.sas.ftp.localResultNgURI";
  	
  	public static final String COM_SAS_FTP_REMOTEURI = "com.sas.ftp.remoteURI";
  	public static final String COM_SAS_FTP_REMOTERESULTURI = "com.sas.ftp.remoteResultURI";
  	
  	//FTP Remittance Name Check
  	public static final String COM_SAS_FTP_REMITTANCE_SCHUDLE_ENABLE = "${com.sas.ftp.remittance.namecheck.schedule.enable}";
  	public static final String COM_SAS_FTP_REMITTANCE_TRIGGER_RATE = "${com.sas.ftp.remittance.namecheck.trigger.rate}";
  	public static final String COM_SAS_FTP_REMITTANCE_LOCALURI = "com.sas.ftp.remittance.localURI";
  	public static final String COM_SAS_FTP_REMITTANCE_LOCALRESULTURI = "com.sas.ftp.remittance.localResultURI";
  	public static final String COM_SAS_FTP_REMITTANCE_LOCALRESULTOKURI = "com.sas.ftp.remittance.localResultOkURI";
  	public static final String COM_SAS_FTP_REMITTANCE_LOCALRESULTNGURI = "com.sas.ftp.remittance.localResultNgURI";
  	
  	public static final String COM_SAS_FTP_REMITTANCE_REMOTEURI = "com.sas.ftp.remittance.remoteURI";
  	public static final String COM_SAS_FTP_REMITTANCE_REMOTERESULTURI = "com.sas.ftp.remittance.remoteResultURI";
  	
  	//FTP Ex Remittance Name Check
  	public static final String COM_SAS_FTP_EXREMITTANCE_LOCALURI = "com.sas.ftp.exremittance.localURI";
  	public static final String COM_SAS_FTP_EXREMITTANCE_LOCALRESULTURI = "com.sas.ftp.exremittance.localResultURI";
  	public static final String COM_SAS_FTP_EXREMITTANCE_LOCALRESULTOKURI = "com.sas.ftp.exremittance.localResultOkURI";
  	public static final String COM_SAS_FTP_EXREMITTANCE_LOCALRESULTNGURI = "com.sas.ftp.exremittance.localResultNgURI";
  	
  	public static final String COM_SAS_FTP_EXREMITTANCE_REMOTEURI = "com.sas.ftp.exremittance.remoteURI";
  	public static final String COM_SAS_FTP_EXREMITTANCE_REMOTERESULTURI = "com.sas.ftp.exremittance.remoteResultURI";

}
