package com.sas.db.aml.orm.fcfcore;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sas.constraint.SwiftMtConst;


/**
 * The persistent class for the ACCOUNT_INTEGRATION database table.
 * 
 */
@Entity
@Table(name="ACCOUNT_INTEGRATION", schema=SwiftMtConst.COM_SAS_JPACFG_AML_FCFCORE_SCHEMA)
@NamedQuery(name="AccountIntegration.findAll", query="SELECT a FROM AccountIntegration a")
public class AccountIntegration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMP_ID")
	private String empId;
	
	@Column(name="DEPT_NAME")
	private String deptName;

	@Column(name="DEPT_NO")
	private String deptNo;

	@Column(name="EMAIL")
	private String email;

	@Column(name="EMP_NAME")
	private String empName;

	@Column(name="ENCRYPW")
	private String encrypw;

	public AccountIntegration() {
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEncrypw() {
		return this.encrypw;
	}

	public void setEncrypw(String encrypw) {
		this.encrypw = encrypw;
	}

}