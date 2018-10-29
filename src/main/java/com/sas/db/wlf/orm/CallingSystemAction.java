package com.sas.db.wlf.orm;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the CALLING_SYSTEM_ACTION database table.
 * 
 */
@Entity
@Table(name="CALLING_SYSTEM_ACTION", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="CallingSystemAction.findAll", query="SELECT c FROM CallingSystemAction c")
public class CallingSystemAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CallingSystemActionPK id;

	@Column(name="CREATE_CASE")
	private String createCase;

	public CallingSystemAction() {
	}

	public CallingSystemActionPK getId() {
		return this.id;
	}

	public void setId(CallingSystemActionPK id) {
		this.id = id;
	}

	public String getCreateCase() {
		return this.createCase;
	}

	public void setCreateCase(String createCase) {
		this.createCase = createCase;
	}

}