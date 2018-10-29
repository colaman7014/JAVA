package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.AccountIntegration;

public interface IAccountIntegrationDao extends CrudRepository<AccountIntegration, String>{
	@Query
	List<AccountIntegration> findByDeptNo(String DeptNo);
}