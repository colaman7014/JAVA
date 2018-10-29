package com.sas.webservice.nameCheck.bean;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 
 * @author Terry
 * 
 *
 */
public class CheckAMLBean {
	@NotEmpty(message = "System_type empty")
	private String system_type;
	@NotEmpty(message = "Check_type empty")
	@Pattern(regexp = "^[1-3]$", message = "check_type 超過定義範圍")
	private String check_type;
	@NotEmpty(message = "unique_key empty")
	private String unique_key;
	@NotEmpty(message = "check_dept empty")
	private String check_dept;
	@NotEmpty(message = "checkEntities empty")
	@Valid
	private List<CheckEntitiesBean> checkEntities;
	
	public String getSystem_type() {
		return system_type;
	}
	public void setSystem_type(String system_type) {
		this.system_type = system_type;
	}
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	public String getUnique_key() {
		return unique_key;
	}
	public void setUnique_key(String unique_key) {
		this.unique_key = unique_key;
	}
	public String getCheck_dept() {
		return check_dept;
	}
	public void setCheck_dept(String check_dept) {
		this.check_dept = check_dept;
	}
	public List<CheckEntitiesBean> getCheckEntities() {
		return checkEntities;
	}
	public void setCheckEntities(List<CheckEntitiesBean> checkEntities) {
		this.checkEntities = checkEntities;
	}
	
	@Override
    public String toString() {
    	return ReflectionToStringBuilder.toString(this);
    }

}
