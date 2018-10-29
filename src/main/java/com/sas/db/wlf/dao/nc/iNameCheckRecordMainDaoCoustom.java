package com.sas.db.wlf.dao.nc;

import java.util.List;

import com.sas.db.wlf.orm.nc.NameCheckRecordMain;

public interface iNameCheckRecordMainDaoCoustom {

	public List<NameCheckRecordMain> queryNameCheckRecordByUniqueKey(String uniqueKey);
}
