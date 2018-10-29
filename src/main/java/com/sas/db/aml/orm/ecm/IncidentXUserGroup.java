package com.sas.db.aml.orm.ecm;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;

/**
 * The persistent class for the INCIDENT_X_USER_GROUP database table.
 * 
 */
@Entity
@Table(name="INCIDENT_X_USER_GROUP", schema=SwiftMtConst.COM_SAS_JPACFG_AML_ECM_SCHEMA)
@NamedQuery(name="IncidentXUserGroup.findAll", query="SELECT i FROM IncidentXUserGroup i")
public class IncidentXUserGroup   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private IncidentXUserGroupPK id;

	
	public IncidentXUserGroupPK getId() {
		return id;
	}

	public void setId(IncidentXUserGroupPK id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
