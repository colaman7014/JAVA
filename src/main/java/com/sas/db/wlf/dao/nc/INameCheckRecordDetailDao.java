package com.sas.db.wlf.dao.nc;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.nc.NameCheckRecordDetail;
import com.sas.db.wlf.orm.nc.NameCheckRecordDetailPK;

/**
 * NCSC-NAME_CHECK_RECORD_DETAIL(名單查詢紀錄明細檔) DAO
 * @author SAS
 *
 */
public interface INameCheckRecordDetailDao extends CrudRepository<NameCheckRecordDetail, NameCheckRecordDetailPK>, INameCheckRecordDetailDaoCustom{
	List<NameCheckRecordDetail> findByIdUniqueKeyAndIdNcReferenceId(String uniqueKey, int ncReferenceId);
}
