package com.sas.db.aml.orm.ecm;

import java.io.Serializable;

import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the INCIDENT_CONFIG_X_USER_GROUP database table.
 * 
 */
@Entity
@Table(name="INCIDENT_CONFIG_X_USER_GROUP", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="IncidentConfigXUserGroup.findAll", query="SELECT i FROM IncidentConfigXUserGroup i")
public class IncidentConfigXUserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IncidentConfigXUserGroupPK id;

	public IncidentConfigXUserGroup() {
	}

	public IncidentConfigXUserGroupPK getId() {
		return this.id;
	}

	public void setId(IncidentConfigXUserGroupPK id) {
		this.id = id;
	}

}