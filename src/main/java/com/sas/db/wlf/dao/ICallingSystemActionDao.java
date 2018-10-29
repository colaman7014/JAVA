package com.sas.db.wlf.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.CallingSystemAction;
import com.sas.db.wlf.orm.CallingSystemActionPK;

/**
 * CallingSystemAction (Calling System Action) DAO 方法
 * 
 * @author Eric Su
 *
 */
public interface ICallingSystemActionDao extends CrudRepository<CallingSystemAction, CallingSystemActionPK> {
	List<CallingSystemAction> findByIdCallingSystem(String callingSystem);
}
