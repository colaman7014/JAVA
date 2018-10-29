package com.sas.db.wlf.dao.nc;


import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.nc.NameCheckLogTime;

/**
 * NCSC-NAME_HIT_RECORD(命中名單記錄檔) DAO
 * @author SAS
 *
 */
public interface INameCheckLogTimeDao extends CrudRepository<NameCheckLogTime, String>{
}
