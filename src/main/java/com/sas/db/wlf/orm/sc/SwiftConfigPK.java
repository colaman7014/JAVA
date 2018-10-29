package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SWIFT_CONFIG database table.
 * 
 */
@Embeddable
public class SwiftConfigPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FIELD_TAG")
	private String fieldTag;

	@Column(name="SWIFT_TYPE")
	private String swiftType;

	public SwiftConfigPK() {
	}
	public String getFieldTag() {
		return this.fieldTag;
	}
	public void setFieldTag(String fieldTag) {
		this.fieldTag = fieldTag;
	}
	public String getSwiftType() {
		return this.swiftType;
	}
	public void setSwiftType(String swiftType) {
		this.swiftType = swiftType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SwiftConfigPK)) {
			return false;
		}
		SwiftConfigPK castOther = (SwiftConfigPK)other;
		return 
			this.fieldTag.equals(castOther.fieldTag)
			&& this.swiftType.equals(castOther.swiftType);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fieldTag.hashCode();
		hash = hash * prime + this.swiftType.hashCode();
		
		return hash;
	}
}