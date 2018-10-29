package com.sas.db.aml.orm.ecm;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the CALLING_SYSTEM_X_USER_GROUP database table.
 * 
 */
@Entity
@Table(name="CALLING_SYSTEM_X_USER_GROUP", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="CallingSystemXUserGroup.findAll", query="SELECT c FROM CallingSystemXUserGroup c")
public class CallingSystemXUserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CallingSystemXUserGroupPK id;

	public CallingSystemXUserGroup() {
	}

	public CallingSystemXUserGroupPK getId() {
		return this.id;
	}

	public void setId(CallingSystemXUserGroupPK id) {
		this.id = id;
	}

}