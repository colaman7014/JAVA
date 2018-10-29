package com.sas.webservice.createCase;

import org.springframework.stereotype.Service;

/**
 * BOT 名單掃描結果客製化
 * @author SAS
 *
 */
@Service
public interface AmlCreateCaseCustomize {
	
 
	/**
	 * 
	 * BOT 名單掃描結果(人名和電文): 命中名單中01、02大類 只要有一個TrueHit 要寫入[UDF自訂欄位=Y]
	 * X_WATCHLISTTYPECD01_02TRUEHIT
	 * 
	 * 在saveCaseResult() 呼叫執行
	 * @param refId
	 * @param caseRk
	 * @param viewId
	 * @return
	 */
	public String saveUdfWatchListTypeCd0102truehit(String refId, String caseRk)throws Exception;
 
}