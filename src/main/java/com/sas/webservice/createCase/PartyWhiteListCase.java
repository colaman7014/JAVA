package com.sas.webservice.createCase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sas.webservice.createCase.bean.PartyWhiteListInputBean;
import com.sas.webservice.createCase.bean.PartyWhiteListOutputBean;

/**
 * 客戶白名單
 * @author SAS
 *
 */
@Service
public interface PartyWhiteListCase {
	
	/** 
	 * 給WorkFlow save
	 * 將CASE_UDF_CHAR_VALUE 等欄位  寫入到X_PARTY_WHITELIST_MAIN
	 * 
	 * case1 [NEW] if (全新)  then {   add _MAIN & empty _HIS }
     * case2 [NEW] if ( PARTY_NUMBER && PARTY_NAME 存在於  _MAIN) then {
     *    A. copy old_MAIN to _HIS  ; 
     *    B. update old_MAIN from Case & keep oldMain.IS_WHITE
     *       (PS old_MAIN.is_white不可以修改)  
     *    }
     *  
	 * @param caseId
	 * @param userId
	 * @return Output.historyCaseId  , Output.*
	 */
	public PartyWhiteListOutputBean workFlowSavePartyWhiteListMain(String caseId, String userId);
	
	
	/**
	 * 給WorkFlow CloseCase
	 * 異動[FCFCOREHK0].[X_PARTY_WHITELIST_MAIN].IS_WHITELIST
	 * 1.update  IS_WHITELIST規則:
	 *    停用STOP已核准APPROVAL =N
     *    啟用NEW已核准APPROVAL =Y
     * 2.update DISPOSITION from CASE_LIVE
     * 
	 * @param caseId
	 * @param userId
	 * @return  Output.isWhitelist
	 */
	public PartyWhiteListOutputBean workFlowCloseCasePartyWhiteList(String caseId, String userId,String disposition);
	
	
	/**
	 * 給client script 檢查客戶編號是否存在[FCFCOREHK0].[FSC_PARTY_DIM]
	 * @param PartyNumber
	 * @return Output.scriptCheckPartyNumberExist
	 */
	public PartyWhiteListOutputBean scriptClientCheckPartyNumberExist(String partyNumber);
	
	
	/**
	 * 給client script  檢查可以新增X_PARTY_WHITELIST_MAIN
	 * 新增時需檢核同一客戶編號，客戶名稱是否已在[FCFCOREHK0].[X_PARTY_WHITELIST_MAIN]，如不存在即可新增，如存在需判斷如下
	 * 
	 * 1.Action[NEW新增]邏輯
	 *   if( old_MAIN不存在)  then return canAdd=true;
	 *   if( 存在old_MAIN.CASE_ACTION==NEW  && _MAIN.CASE_DISPOSITION==CANCEL)   then return canAdd=true;
	 *   if( 存在_MAIN.CASE_ACTION==STOP && _MAIN.CASE_DISPOSITION==APPROVAL) then return canAdd=true;
	 * 
	 * 2.Action[STOP停用]邏輯
	 *   if( old_MAIN不存在)  then return canAdd=false; //不存在不可停用
	 *   if( 存在_MAIN.CASE_ACTION==NEW  && _MAIN.CASE_DISPOSITION==APPROVAL) then return canAdd=true;
	 *   if( 存在_MAIN.CASE_ACTION==STOP && _MAIN.CASE_DISPOSITION==CANCEL)   then return canAdd=true;
	 * @param input caseAction PartyName PartyNumber
	 * @return  Output.scriptIsCanAddPartyWhite
	 */
	public PartyWhiteListOutputBean scriptClientIsCanAddPartyWhite(PartyWhiteListInputBean input);
	
	
	/**
	 * 1.畫面查詢條件為客戶編號，客戶名稱(like)
	 * 2.查詢結果須包含名單編號，客戶編號，客戶名稱，NEW啟用/STOP停用，狀態，建立者，建立時間
	 * 
	 * 狀態  "APPROVAL":已核准  ;  "CANCEL":已拒絕 ; "":待覆核	 
	 * @param input PartyName PartyNumber
	 * @return
	 */
	public List<PartyWhiteListOutputBean> scriptClientQueryPartyWhiteList(PartyWhiteListInputBean input);
	public List<PartyWhiteListOutputBean> scriptClientQueryPartyWhiteHisList(PartyWhiteListInputBean input);
	
}
