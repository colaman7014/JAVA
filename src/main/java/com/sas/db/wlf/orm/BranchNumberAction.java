package com.sas.db.wlf.orm;

import java.io.Serializable;
import javax.persistence.*;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the BRANCH_NUMBER_ACTION database table.
 * 
 */
@Entity
@Table(name="BRANCH_NUMBER_ACTION", schema=SwiftMtConst.COM_SAS_JPACFG_NCSC_SCHEMA)
@NamedQuery(name="BranchNumberAction.findAll", query="SELECT b FROM BranchNumberAction b")
public class BranchNumberAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BRANCH_NUMBER")
	private String branchNumber;

	@Column(name="BRANCH_NUMBER_NAME")
	private String branchNumberName;

	public BranchNumberAction() {
	}

	public String getBranchNumber() {
		return this.branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getBranchNumberName() {
		return this.branchNumberName;
	}

	public void setBranchNumberName(String branchNumberName) {
		this.branchNumberName = branchNumberName;
	}

}