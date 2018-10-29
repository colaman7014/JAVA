package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.XComboWhitelistMain;

/**
 * AML.FCFCORE.X_COMBO_WHITELIST_HIS(交易組合白名單歷史暫存資料) DAO 方法
 * @author SAS
 *
 */
public interface IXComboWhitelistMainDao extends CrudRepository<XComboWhitelistMain, String>{
	
	public XComboWhitelistMain findByPartyNumberAndBeneficiaryEntityWatchListKey(String partyNo, String entityWatchListKey);
	public XComboWhitelistMain findByPartyNumberAndBranchAndBeneficiaryEntityWatchListKey(String partyNo, String branch, String entityWatchListKey);
	
	public List<XComboWhitelistMain> findByPartyNumber(String partyNumber);
	public List<XComboWhitelistMain> findByEntityDisplayNameLike(String entityName);
	public List<XComboWhitelistMain> findByPartyNumberAndEntityDisplayNameLike(String partyNumber, String entityName);
	public XComboWhitelistMain findByCaseId(String caseId);
	
}
