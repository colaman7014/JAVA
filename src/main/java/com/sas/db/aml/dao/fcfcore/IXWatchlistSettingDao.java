package com.sas.db.aml.dao.fcfcore;

import org.springframework.data.repository.CrudRepository;
import com.sas.db.aml.orm.fcfcore.XWatchlistSetting;

/**
 * AML.FCFCORE.X_WATCHLIST_SETTING(命中名單類型分數定義) DAO 方法
 * @author SAS
 *
 */
public interface IXWatchlistSettingDao extends CrudRepository<XWatchlistSetting, Long>{
	public XWatchlistSetting findByChangeCurrentInd(String changeCurrentInd);
}