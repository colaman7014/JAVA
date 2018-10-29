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

	//global properties name settings
	public static final String COM_SAS_DATAFLUX_USERNAME = "com.sas.dataFlux.userName";
	public static final String COM_SAS_DATAFLUX_PASSWORD = "com.sas.dataFlux.password";
	public static final String COM_SAS_DATAFLUXOPTION_WSDLFILEPATH = "com.sas.dataFluxOption.wsdlFilePath"; //2018.04.17 新加入
	public static final String COM_SAS_ARRANGEMENTSELECT_LIMITINDEX = "com.sas.arrangementSelect.limitIndex";
	public static final String COM_SAS_ARRANGEMENTSELECT_MAXINDEX = "com.sas.arrangementSelect.maxIndex";
	public static final String COM_SAS_ARRANGEMENTSELECT_FIELDMAXLENGTH = "com.sas.arrangementSelect.fieldMaxLength";
	
	// 20180418 加入Damerau Levenshtein Algorithm 差異度參數
	public static final String COM_SAS_DAMERAU_LEVENSHTEIN_PARAMETER = "com.sas.damerau.levenshtein.parameter";
	
	public static final String [] COVERTWORD = {"~","!","@","#","$","%","^","&","*","(",")","_","+","`","-","=","{","}","|","[","]","\\",":","<",">","?",",",".","1/","2/","3/","/"};
	//Error Code
	public static final String ERROR_CODE_SUCCESS = "0";
	public static final String ERROR_CODE_FORMATERR = "1";
	public static final String ERROR_CODE_SYSTEMERR = "2";
	public static final String ERROR_CODE_UNIQUE_KEY_CONFLICT = "3";
	public static final String ERROR_CODE_NODATAFOUND = "4";
	
	public static final String ERROR_CODE_FORMATERR_MESSAGE = "Input Format Error";
	public static final String ERROR_CODE_INTERNALERR_MESSAGE = "Internal Server Error";
	
	public static final String ERROR_CODE_EMPTY_MESSAGE = "\u200b";

	//Name Check Result
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
	
	//Interface type
	public static final String INTERFACE_TYPE_SWIFTCHECK = "AML_SWIFTCheck";
	public static final String INTERFACE_TYPE_NAMECHECK = "AML_NameCheck";
	
	public static final String INTERFACE_TYPE_BATCHNAMECHECK = "AML_BatchNameCheck";
	public static final String INTERFACE_TYPE_BATCHSWIFTCHECK = "AML_BatchSwiftCheck";
	public static final String INTERFACE_TYPE_BATCHTRANSACTIONSCREENING = "AML_BatchTransactionScreening";
	public static final String INTERFACE_TYPE_BATCHNAMECHECKSTATUS = "AML_BatchNameCheckStatus";
	
	//Name Check
	public static final String NAME_CHECK_EXACT = "EXACT";
	public static final String NAME_CHECK_FUZZY = "FUZZY";
	public static final String NAME_CHECK_INCLUSIVE = "INCLUSIVE";
	public static final String NAME_CHECK_Y = "Y";
	
	public static int EXACT_MATCH_SCORE_DEFAULT = 100;
	public static int FUZZY_MATCH_SCORE_DEFAULT = 60;
	public static int INCLUSIVE_MATCH_SOCRE_DEFALUT = 0; // 
	public static int INCLUSIVE_MATCH_SOCRE_BASE = 60; // 20180208 新增暫時使用Inclusive Match Score
	public static int INCLUSIVE_MATCH_CH_NAME_TOKEN_LIMIT = 3; // 20180606 新增，Inclusive Match Score中文五個字以內做順序性精準比對
	public static int INCLUSIVE_MATCH_EN_NAME_TOKEN_LIMIT = 3; // 20180606 新增，Inclusive Match Score英文三個Token，做順序性比對
	
	public static final String CHANGE_CURRENT_IND_Y = "Y";
	public static final String CHANGE_CURRENT_IND_N = "N";
	public static final String DELETE_IND_Y = "Y";
	public static final String DELETE_IND_N = "N";
	public static final String EXCLUDE_IND_Y = "Y";
	public static final String EXCLUDE_IND_N = "N";

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
		
	public static final String REF_TABLE_NM_X_WATCHLIST_TYPE_CD = "X_WatchList_Type_Cd";
	public static final String REF_TABLE_NM_X_WATCHLIST_SUB_TYPE_CD = "X_WatchList_Sub_Type_Cd";

	public static final String ENTITY_TYPE_ALL = "9";
	public static final String ENTITY_TYPE_IND = "3";
	public static final String ENTITY_TYPE_CORP = "8";
	public static final String ENTITY_TYPE_BANK = "5";
	public static final String ENTITY_TYPE_COUNTRY = "1";
	public static final String ENTITY_TYPE_INDANDCORP = "9";
	public static final String ENTITY_TYPE_FREEFPRMAT = "99";
	
	public static final String KGI_SYSTEM_TYPE_01 = "01";  //01-台幣開戶、04-金市、05-互動式網銀、06-影像系統
	public static final String KGI_SYSTEM_TYPE_02 = "02";  //02-外幣開戶
	public static final String KGI_SYSTEM_TYPE_03 = "03";  //03-外匯交易
	public static final String KGI_SYSTEM_TYPE_04 = "04";  //04-金市
	public static final String KGI_SYSTEM_TYPE_05 = "05";  //05-互動式網銀
	public static final String KGI_SYSTEM_TYPE_06 = "06";  //06-影像系統
	
	public static final String SUB_TYPE_CD_SPLIT = ",";	
	
	public static final String COMPRESSSTRING_CORP = "CORP";
	public static final String COMPRESSSTRING_SIG = "SIG";
	
}
