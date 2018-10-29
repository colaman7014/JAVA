package com.sas.db.aml.dao.fcfcore;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.XComboWhitelistHis;

/**
 * AML.FCFCORE.X_COMBO_WHITELIST_MAIN(交易組合白名單主檔資料) DAO 方法
 * @author SAS
 *
 */
public interface IXComboWhitelistHisDao extends CrudRepository<XComboWhitelistHis, String>{
	
	
}