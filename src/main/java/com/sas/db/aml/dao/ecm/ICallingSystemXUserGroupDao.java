package com.sas.db.aml.dao.ecm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.ecm.CallingSystemXUserGroup;
import com.sas.db.aml.orm.ecm.CallingSystemXUserGroupPK;

public interface ICallingSystemXUserGroupDao extends CrudRepository<CallingSystemXUserGroup, CallingSystemXUserGroupPK>{

	public List<CallingSystemXUserGroup> findByIdCallingSystemCd(String callingSystemCd);
}