package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.XFscEntityWlXListSetting;
import com.sas.db.aml.orm.fcfcore.XFscEntityWlXListSettingPK;

/**
 * AML.FCFCORE.X_FSC_ENTITY_WL_X_LIST_SETTING(黑名單entity_watch_list_number 對應到名單分類方法) DAO 方法
 * @author SAS
 *
 */
public interface IFscEntityWlXListSettingDao extends CrudRepository<XFscEntityWlXListSetting, XFscEntityWlXListSettingPK>{
	List<XFscEntityWlXListSetting> findByIdChangeCurrentIndAndEntityWatchListNumber(String changeCurrentInd, String entityWatchListNumber);
	List<XFscEntityWlXListSetting> findByIdChangeCurrentIndAndEntityWatchListNumberIn(String changeCurrentInd, List<String> entityWatchListNumberList);
	List<XFscEntityWlXListSetting> findByIdChangeCurrentIndAndEntityWatchListNumberInAndWatchListSubTypeCdIn(String changeCurrentInd, List<String> entityWatchListNumberList, List<String> watchListSubTypeCdList);
}