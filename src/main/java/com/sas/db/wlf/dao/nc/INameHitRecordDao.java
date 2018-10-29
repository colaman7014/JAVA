package com.sas.db.wlf.dao.nc;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.nc.NameHitRecord;
import com.sas.db.wlf.orm.nc.NameHitRecordPK;

/**
 * NCSC-NAME_HIT_RECORD(命中名單記錄檔) DAO
 * @author SAS
 *
 */
public interface INameHitRecordDao extends CrudRepository<NameHitRecord, NameHitRecordPK>, INameHitRecordDaoCustom{
	List<NameHitRecord> findByIdUniqueKeyAndIdNcReferenceId(String uniqueKey, int ncReferenceId);
}
