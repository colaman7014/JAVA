package com.sas.db.aml.dao.fcfcore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sas.db.aml.orm.fcfcore.AccountIntegrationRole;
import com.sas.db.aml.orm.fcfcore.AccountIntegrationRolePK;

public interface IAccountIntegrationRoleDao extends CrudRepository<AccountIntegrationRole, AccountIntegrationRolePK> {

	
	public List<AccountIntegrationRole> findByIdEmpId(String empId);
}