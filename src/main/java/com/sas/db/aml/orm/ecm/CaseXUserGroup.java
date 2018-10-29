package com.sas.db.aml.orm.ecm;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;

/**
 * The persistent class for the CASE_X_USER_GROUP database table.
 * 
 */
@Entity
@Table(name="CASE_X_USER_GROUP", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
public class CaseXUserGroup  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CaseXUserGroupPK id;
	

	public CaseXUserGroupPK getId() {
		return id;
	}

	public void setId(CaseXUserGroupPK id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
