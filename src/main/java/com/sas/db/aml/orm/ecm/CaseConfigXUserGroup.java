package com.sas.db.aml.orm.ecm;

import java.io.Serializable;

import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the CASE_CONFIG_X_USER_GROUP database table.
 * 
 */
@Entity
@Table(name="CASE_CONFIG_X_USER_GROUP", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="CaseConfigXUserGroup.findAll", query="SELECT c FROM CaseConfigXUserGroup c")
public class CaseConfigXUserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CaseConfigXUserGroupPK id;

	public CaseConfigXUserGroup() {
	}

	public CaseConfigXUserGroupPK getId() {
		return this.id;
	}

	public void setId(CaseConfigXUserGroupPK id) {
		this.id = id;
	}

}