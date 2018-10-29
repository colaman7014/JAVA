package com.sas.db.wlf.orm.sc;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SWIFT_WAIT_RECORD database table.
 * 
 */
@Embeddable
public class SwiftWaitRecordPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="GROUP_UNIQUE_KEY")
	private String groupUniqueKey;

	@Column(name="UNIQUE_KEY")
	private String uniqueKey;

	public SwiftWaitRecordPK() {
	}
	public String getGroupUniqueKey() {
		return this.groupUniqueKey;
	}
	public void setGroupUniqueKey(String groupUniqueKey) {
		this.groupUniqueKey = groupUniqueKey;
	}
	public String getUniqueKey() {
		return this.uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SwiftWaitRecordPK)) {
			return false;
		}
		SwiftWaitRecordPK castOther = (SwiftWaitRecordPK)other;
		return 
			this.groupUniqueKey.equals(castOther.groupUniqueKey)
			&& this.uniqueKey.equals(castOther.uniqueKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.groupUniqueKey.hashCode();
		hash = hash * prime + this.uniqueKey.hashCode();
		
		return hash;
	}
}