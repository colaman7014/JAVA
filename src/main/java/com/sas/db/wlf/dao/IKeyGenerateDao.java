package com.sas.db.wlf.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.wlf.orm.KeyGenerate;

/**
 * NCSC- KEY_GENERATE(Real Time Gen Key) DAO方法
 * @author SAS
 *
 */
public interface IKeyGenerateDao extends CrudRepository<KeyGenerate, Integer>{
	List<KeyGenerate> findByUniqueKey(String uniqueKey);
	List<KeyGenerate> findByUniqueKeyOrderByProcessDttmDesc(String uniqueKey);
	List<KeyGenerate> findByUniqueKeyAndProcessDttmStartingWithOrderByProcessDttmDesc(String uniqueKey, Date processDttm);
	List<KeyGenerate> findByUniqueKeyAndProcessDttmBetweenOrderByProcessDttmDesc(String uniqueKey, Date processDttmBegin, Date processDttmEnd);

	//Terry新增
	List<KeyGenerate> findByInterfaceTypeAndProcessDttm(String interfaceType, Date processDttm);
}
