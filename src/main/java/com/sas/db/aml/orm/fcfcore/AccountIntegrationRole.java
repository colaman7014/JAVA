package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ACCOUNT_INTEGRATION_ROLE database table.
 * 
 */
@Entity
@Table(name="ACCOUNT_INTEGRATION_ROLE")
@NamedQuery(name="AccountIntegrationRole.findAll", query="SELECT a FROM AccountIntegrationRole a")
public class AccountIntegrationRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccountIntegrationRolePK id;

	@Column(name="ROLE_DESC")
	private String roleDesc;

	public AccountIntegrationRole() {
	}

	public AccountIntegrationRolePK getId() {
		return this.id;
	}

	public void setId(AccountIntegrationRolePK id) {
		this.id = id;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}