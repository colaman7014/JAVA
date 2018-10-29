package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.XScreenProcessSetting;
import com.sas.db.aml.orm.fcfcore.XScreenProcessSettingPK;

/**
 * AML.FCFCORE.X_SCREEN_PROCESS_SETTING(Screen對應名單類型) DAO 方法
 * @author SAS
 *
 */
public interface IXScreenProcessSettingDao extends CrudRepository<XScreenProcessSetting, XScreenProcessSettingPK>{
	List<XScreenProcessSetting> findByChangeCurrentIndAndDeleteIndAndIdScreenProcessTypeCd(String changeCurrentInd, String deleteInd, String screenProcessTypeCd);
}